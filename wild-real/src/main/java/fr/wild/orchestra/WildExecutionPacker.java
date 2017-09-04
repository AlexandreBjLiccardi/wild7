/*
 *  ONEMA Dice project.
 *  Copyright (C) 2016 ONEMA
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this project. If not, see http://www.gnu.org/licenses.
 */
package fr.wild.orchestra;

import static fr.wild.common.IoWilds.*;
import static fr.wild.common.IoCommons.*;
import static fr.wild.common.IoFileSystem.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import fr.wild.hibernate.ExecutionChainsaw;

/**
 * Classe permettant la compression/décompression des informations de scénarios
 * @author alexandre.liccardi
 * @version [ab]
 */
public class WildExecutionPacker implements Callable<ConcurrentHashMap<String,Object>> {
    
    private ExecutionChainsaw execution ; // Exécution d'appel
    private LinkedHashMap<String, Object> config_table ; // table de configuration
    private String phpFolder_in ; // Dossier PHP d'entrée
    private String phpFolder_out ; // Dossier PHP de sortie
    private Boolean unzip = true ; // Le fichier est décompressé
    private Boolean zip = false ; // Le fichier est compressé
    private Float advance = (float) 1.000 ; // %age d'avancement
    private LinkedHashMap<String, Object> filesDescriber ; // Map de description des fichiers associés au scénario
    private String execFolder ; // Répertoire d'exécution
    private String execTempFolder ; // Répertoire d'exécution temporaire
    private String fileZipped; // Chemin d'archivage (fichier)
    private String archiveFolder ; // Chemin d'archivage (dossier)
    private Boolean forceUnzip = false ; // Décompresse systématiquement les fichiers transmis si archives

    /**
     * Constructeur
     * @param i_execution        Exécution d'origine
     * @param i_config_table    Map d'informations de configuration (congTab)
     */
    public WildExecutionPacker(ExecutionChainsaw i_execution, LinkedHashMap<String, Object> i_config_table) {
        execution =  i_execution ;
        config_table = i_config_table ;
        phpFolder_out = conf_translate(config_table.get("execution_php_path"),execution)+File.separator+"out" ;
        phpFolder_in = conf_translate(config_table.get("execution_php_path"),execution)+File.separator+"in" ;
        execFolder = conf_translate(config_table.get("execution_path"),execution) ;
        archiveFolder = conf_translate(config_table.get("archive_path"),execution) ;
        execTempFolder = conf_translate(config_table.get("execution_temp_path"),execution) ;
        try {
            forceUnzip = cast_2Boolean(xml_getFirstValue(cast_String2Node(i_execution.getInfoIn()),"WildScenario/@forceUnzip"),true);
        } catch (Exception e) {
            forceUnzip = false ;
        }
        file_mkDirs(execFolder); // répertoire d'exécution (production)
        file_mkDirs(execTempFolder); // répertoire d'exécution (temporaire)
        file_mkDirs(phpFolder_out); // répertoire d'entrée depuis php
        file_mkDirs(phpFolder_in); // répertoire de sortie vers php
    }

    /**
     * Récupère le chemin du dossier d'exécution temporaire
     * @return
     */
    public String getExecTempFolderPath(){
        return execTempFolder ;
    }

    /**
     * Récupère le chemin du dossier d'exécution
     * @return
     */
    public String getExecFolderPath(){
        return execFolder ;
    }

    /**
     * Récupère le chemin du dossier d'exécution où PHP dépose les fichiers
     * @return
     */
    public String getPhpFolder_outPath(){
        return phpFolder_out ;
    }

    /**
     * Récupère le chemin du dossier d'exécution où PHP lit les fichiers
     * @return
     */
    public String getPhpFolder_inPath(){
        return phpFolder_in ;
    }

    /**
     * Appel : si la précédente opération est une compression, décompresse,
     * si la précédente opération est une décompression, compresse
     */
    @Override
    public ConcurrentHashMap<String, Object> call() throws Exception {
        if(unzip){
            uncompress() ;
            zip = true ;
            unzip = false;
        }else if(zip){
            compress() ;
            zip = false;
        }
        return new ConcurrentHashMap<String, Object>(filesDescriber);
    }

    /**
     * Récupère l'état d'avancement
     * @return {Message à afficher avec %age d'avancement, 0, 0}
     */
    public Object[] getOut() {
        Object[] toRet = new Object[3];
        Integer adv = (int) (advance*100) ;
        toRet[0] = (unzip)?((String) config_table.get("uncompress_dice_javamsg")).replaceAll("\\{A\\}",adv.toString())
                :((String) config_table.get("compress_dice_javamsg")).replaceAll("\\{A\\}",adv.toString()) ;
        toRet[1] = (short) 0;
        toRet[2] = (short) 0;
        return toRet;
    }

    /**
     * Récupère l'état d'avancement
     * @return {Message à afficher avec %age d'avancement, 0, 0}
     */
    public Object[] getInfos() {
        Object[] toRet = new Object[3];
        toRet[0] = (zip)?(String) config_table.get("uncompress_dice_javasuccessmsg"):(String) config_table.get("compress_dice_javasuccessmsg");
        toRet[1] = (short) 0;
        toRet[2] = (short) 0;
        return toRet;
    }

    /**
     * Méthode de décompression
     * advance indicque le %age d'avancement de l'opération
     * @throws IOException
     * @throws InterruptedException
     */
    private void uncompress() throws IOException, InterruptedException{
        List<String> formats_filecompress = Arrays.asList(((String)  config_table.get("formats_filecompress")).split(";"));
        File[] listOfPhpFiles = (new File(phpFolder_in)!=null)?new File(phpFolder_in).listFiles():null;
        filesDescriber = new LinkedHashMap<String, Object> ();
        Integer totFiles = (listOfPhpFiles!=null)?listOfPhpFiles.length : 0;
        Integer didFiles = 0;
        if(listOfPhpFiles!=null)for(File file : listOfPhpFiles){
            HashMap<String, Object> fileDescriber = file_describer(file);
            if(forceUnzip){if(formats_filecompress.contains(fileDescriber.get("type"))){
                filesDescriber.putAll(file_unzip((String)fileDescriber.get("path"),execTempFolder,true));
                continue ;
            }}
            filesDescriber.put((String)fileDescriber.get("name"), fileDescriber);
            advance = didFiles.floatValue()/totFiles.floatValue();
            didFiles++;
        }
    }

    /**
     * Méthode de compression
     * advance indique le %age d'avancement de l'opération
     * @throws IOException
     * @throws InterruptedException
     */
    private void compress() throws IOException, InterruptedException{

        byte[] buffer = new byte[2048];
        fileZipped = archiveFolder+File.separator+execution.getIdExecution()+".zip";
        filesDescriber = new LinkedHashMap<String,Object>();
        File fileSZipped = new File(fileZipped);
        File fileSToZip = new File(execFolder);
        if(fileSZipped.exists())fileSZipped.delete();
        fileSZipped.getParentFile().mkdirs();
        file_permissions(fileZipped);
        file_permissions(fileSZipped.getParent());
        FileOutputStream fos = new FileOutputStream(fileZipped);
        ZipOutputStream zos = new ZipOutputStream(fos);
        List<String> entriesToZip = file_getDirContents(execFolder);
        String fileToZipShortPath = null ;
        int numFile = 0 ;
        Long totBytes = (long) 0;
        Long didBytes = (long) 0;
        for(String file : entriesToZip){
            HashMap<String,Object> fileDescriber = file_describer(file) ;
            filesDescriber.put((String) fileDescriber.get("name"), fileDescriber);
            totBytes+= (Long)fileDescriber.get("size");
        }
        for(String file : entriesToZip){
            String zipedFile = file ;
            if(fileSToZip.isDirectory()&&numFile==0)fileToZipShortPath = fileSToZip.getPath().replaceAll(File.separator+File.separator, "/");
            if(!fileSToZip.isDirectory()&&numFile==0)fileToZipShortPath = fileSToZip.getParentFile().getPath().replaceAll(File.separator+File.separator, "/");
            if(fileToZipShortPath != null)zipedFile = file.replaceAll(File.separator+File.separator, "/").replaceAll("^"+fileToZipShortPath,"") ;
            zipedFile = zipedFile.replaceAll("^/", "");

            ZipEntry ze = new ZipEntry(zipedFile);
            zos.putNextEntry(ze);
            FileInputStream in = new FileInputStream(file);
            int len;
            while ((len = in.read(buffer)) > 0){
                zos.write(buffer, 0, len);
                didBytes += len ;
                advance = didBytes.floatValue()/totBytes.floatValue();
            }
            in.close();
            numFile++;
        }
        zos.closeEntry(); zos.close();
    }

    /**
     * Récupère l'adresse d'archivage des éléments compressés
     * @return
     */
    public String archive_path(){
        return fileZipped ;
    }

}