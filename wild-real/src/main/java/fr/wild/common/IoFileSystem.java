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
package fr.wild.common;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * Classe générique écriture de fichiers
 * @author alexandre.liccardi
 * @version 0.1a "Ambiant Ambivalence"
 */
public class IoFileSystem {
	public static String file_sha1(String datafile) throws NoSuchAlgorithmException, IOException{
		return file_sha1(new File(datafile)) ;
	}
	public static String file_sha1(File datafile) throws NoSuchAlgorithmException, IOException{
		MessageDigest md = MessageDigest.getInstance("SHA1");
	    FileInputStream fis = new FileInputStream(datafile);
	    try{
	    	byte[] dataBytes = new byte[1024];
		    int nread = 0;
		    while ((nread = fis.read(dataBytes)) != -1) md.update(dataBytes, 0, nread);
		    byte[] mdbytes = md.digest();
		    StringBuffer sb = new StringBuffer("");
		    for (int i = 0; i < mdbytes.length; i++)sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		    return sb.toString();
	    }catch(Exception e){throw e;}finally{
	    	fis.close();
	    }
	}
	
    public static String file_asString(String pathFile) throws IOException{
        if(!(new File(pathFile).exists()))return null;
        byte[] encoded = Files.readAllBytes(Paths.get(pathFile));
        return new String(encoded);
    }

    /**
     * Evalue la taille d'un fichier ou d'un dossier (et de ses sous éléments)
     * @param directory    Fichier dont la taille est évaluée
     * @return    Taille en Bytes
     */
    public static long file_size(File directory) {
        long length = 0;
        if(!directory.isDirectory())return directory.length();
        for (File file : directory.listFiles()) {
            if (file.isFile())
                length += file.length();
            else
                length += file_size(file);
        }
        return length;
    }

    /**
     * Listing du contenu d'un répertoire
     * @param    fileToList    Chemin physique du répertoire à explorer
     * @ return Liste des entrées contenues (noms de fichiers)
     */
    public static List<String> file_getDirContents(String fileToList){
        List<String> toRet = new ArrayList<String>() ;
        File nf = new File(fileToList);
        if(nf.isDirectory()){
            for(String file : nf.list())toRet.addAll(file_getDirContents(fileToList+File.separator+file));
        }else{
            toRet.add(nf.getPath());
        }
        return toRet;
    }

    /**
     * Compression d'un fichier
     * Compresse le *zip* dans un répertoire
     * @param fileToZip fichier ou répertoire à compresser
     * @param fileZipped chemin du fichier compressé
     * @return Carte avec un nom (nom du fichier + (n) + extension si doublon, description (MIMEType ("type"), chemin physique ("path"), taille ("size"))
     * @throws IOException
     */
    public static HashMap<String,HashMap<String,Object>> file_zip(String fileToZip, String fileZipped, Boolean deleteFirst) throws IOException{
        byte[] buffer = new byte[2048];
        HashMap<String,HashMap<String,Object>> toRet = new HashMap<String,HashMap<String,Object>>();
        File fileSZipped = new File(fileZipped);
        File fileSToZip = new File(fileToZip);
        if(deleteFirst&&fileSZipped.exists())fileSZipped.delete();
        fileSZipped.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(fileZipped);
        ZipOutputStream zos = new ZipOutputStream(fos);

        List<String> entriesToZip = file_getDirContents(fileToZip);
        String fileToZipShortPath = null ;
        int numFile = 0 ;
        Long totBytes = (long) 0;
        // Evité car inaccessible : Long didBytes = (long) 0;
        for(String file : entriesToZip){
            HashMap<String,Object> fileDescriber = file_describer(file) ;
            toRet.put((String) fileDescriber.get("name"), fileDescriber);
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
                // Evité car inaccessible : didBytes += len ;
            }
            in.close();
            numFile++;
        }
        zos.closeEntry(); zos.close();
        return toRet;
    }

    /**
     * Décompression d'un fichier
     * Décompresse le *zip* dans un répertoire
     * @param zipFile *zip* à décompresser
     * @param outputFolder répertoire d'extraction
     * @return Carte avec un nom (nom du fichier + (n) + extension si doublon, description (MIMEType ("type"), chemin physique ("path"), taille ("size"))
     */
    public static HashMap<String,HashMap<String,Object>> file_unzip(String zipFile, String outputFolder, Boolean deleteFirst){
        byte[] buffer = new byte[2048];
        HashMap<String,HashMap<String,Object>> toRet = new HashMap<String,HashMap<String,Object>>();
        try{
            File folder = new File(outputFolder);
            if(deleteFirst&&folder.exists())file_delete(folder);
            if(folder.exists()&&!folder.isDirectory())folder.delete();
            if(!folder.exists())folder.mkdirs();
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry ze = zis.getNextEntry();
            while(ze!=null){
                String fileName = ze.getName();
                File newFile = new File(outputFolder + File.separator + fileName);
                if(!ze.isDirectory()){
                    new File(newFile.getParent()).mkdirs();
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0)fos.write(buffer, 0, len);
                    fos.close();
                    HashMap<String,Object> fileDescriber = file_describer(newFile) ;

                    String keyName = newFile.getName() ;

                    if(toRet.get(keyName)!=null){
                        int i = 1;
                        while(toRet.get(keyName)!=null){
                            keyName = fileDescriber.get("radical")+" ("+i+")."+fileDescriber.get("extension");
                            i++;
                        }
                    }
                    toRet.put(keyName, fileDescriber);
                }
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return toRet;
    }

    /**
     * Supprime et fichiers et/ou un dossier et ses sous éléments
     * @param file    Chemin du fichier
     * @throws IOException
     */
    public static void file_delete(String file) throws IOException{
        file_delete(new File(file));
    }

    /**
     * Supprime et fichiers et/ou un dossier et ses sous éléments
     * @param file    Instance JAVA du fichier
     * @throws IOException
     */
    public static void file_delete(File file) throws IOException{

        if(file.isDirectory()){
            if(file.list().length==0){
                file.delete();
            }else{
                String files[] = file.list();
                for (String temp : files) {
                    File fileDelete = new File(file, temp);
                    file_delete(fileDelete);
                }
                if(file.list().length==0)file.delete();
            }
        }else{
        		file.delete();
        }
    }

    /**
     * Renvoie une Map de description d'un fichier, contenant les informations :
     * name - nom = radical.extension
     * radical - nom avant extension
     * extension - extension seule
     * path - chemin sur le dique
     * type - type DICE simplifé
     * size - taille y compris des sous éléments
     * system_type - "File" par défaut
     * @param newFile    Chemin du fichier
     * @return    Map de description
     * @throws IOException
     */
    public static HashMap<String,Object> file_describer(String newFile) throws IOException{
        return file_describer(new File(newFile)) ;
    }

    /**
     * Renvoie une Map de descritption d'un fichier, contenant les informations :
     * name - nom = radical.extension
     * radical - nom avant extension
     * extension - extension seule
     * path - chemin sur le dique
     * type - type DICE simplifé
     * size - taille y compris des sous éléments
     * system_type - "File" par défaut
     * @param newFile    Instance JAVA du fichier
     * @return    Map de description
     * @throws IOException
     */
    public static HashMap<String,Object> file_describer(File newFile) throws IOException{
        HashMap<String,Object> fileDescriber = new HashMap<String,Object>();
        String[] splitted_keyName = newFile.getName().split("\\.");
        String rad_keyName = splitted_keyName[0];
        for(int i = 1 ; i < splitted_keyName.length-1 ; i++)rad_keyName+=("."+splitted_keyName[i]);
        fileDescriber.put("name", newFile.getName());
        fileDescriber.put("radical", rad_keyName);
        fileDescriber.put("extension", splitted_keyName[splitted_keyName.length-1].toLowerCase());
        fileDescriber.put("path", newFile.getPath());
       // Cette méthode pose des problèmes de concurency
       // try{fileDescriber.put("type", Files.probeContentType(Paths.get(newFile.getPath())));}catch(Exception e){}
        fileDescriber.put("size", newFile.length());
        fileDescriber.put("system_type", "File");
        if(fileDescriber.get("type")==null)fileDescriber.put("type", "ext:"+fileDescriber.get("extension"));
        return fileDescriber ;
    }

    /**
     * Crée les répertoire d'accès à un fichier, pour éviter erreur I/O
     * @param pathToDir    fichier dont on crée les répertoires parents
     */
    public static void file_mkDirs(String pathToDir){
        File nF_1 = new File(pathToDir);
        if(nF_1.exists()&&!nF_1.isDirectory())nF_1.delete();
        if(!nF_1.exists())nF_1.mkdirs();
        file_permissions(pathToDir);
    }

    public static void file_permissions(String pathToDir){
        File file = new File(pathToDir);
        file.setExecutable(true, false);
        file.setReadable(true, false);
        file.setWritable(true, false);
    }

    /**
     * Renvoie une erreur si un fichier n'existe pas
     * "Lève" volontairement une exception
     * @param source    Répertoire ou fichier source
     * @return
     * @throws IOException
     */
    public static void file_checkError(String source) throws IOException {
        if(!(new File(source)).isDirectory()){
            InputStream is = new FileInputStream(source);
            is.close() ;
        }
    }

    /**
     * Copie d'un fichier ou d'un dossier
     * @param source    Répertoire ou fichier source
     * @param dest        Répertoire ou fichier de destination
     * @throws IOException
     */
    public static void file_copy(String source, String dest) throws IOException {
        file_checkError(source);
        file_copy(new File(source), new File(dest)) ;
    }

    /**
     * Copie d'un fichier ou d'un dossier
     * @param source    Instance JAVA de répertoire ou fichier source
     * @param dest        Instance JAVA de répertoire ou fichier de destination
     * @throws IOException
     */
    public static void file_copy(File source, File dest) throws IOException {
        if(dest.exists())file_delete(dest);
        if(!source.isDirectory()){
            InputStream is = null;
            OutputStream os = null;
            try {
                if(!dest.exists())dest.getParentFile().mkdirs();
                is = new FileInputStream(source);
                os = new FileOutputStream(dest);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
            }catch(Exception e){
                e.printStackTrace();
            } finally {
                if(is!=null)is.close();
                if(os!=null)os.close();
            }
        }else{
            File[] listFile = source.listFiles() ;
            for(File f:listFile)file_copy(f, new File(dest.getPath()+File.separator+f.getName()));
        }
    }

    /**
     * Ecriture d'un message dans un fichier de destination, avec création éventuelle du fichier
     * @param dest    Instance JAVA du fichier à écrire
     * @param toWrite    Message à écrire
     * @throws IOException
     */
    public static void file_write(File dest, String toWrite) throws IOException{
        file_write(dest, toWrite,null);
    }

    public static void file_write(File dest, String toWrite, String encode) throws IOException{
        BufferedWriter os = null;
        if(encode==null)encode="UTF8";
        try {
            try{if(!dest.exists())dest.getParentFile().mkdirs();}catch(Exception  e){};
            os = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(dest,true), encode));
            os.write(toWrite);
            os.flush();
        }catch(Exception e){
            e.printStackTrace();
        } finally {
            os.close();
        }
    }

    public static void file_write(String dest, String toWrite, String encode) throws IOException{
        file_write(new File(dest), toWrite, encode);
    }

    public static void file_write(String dest, String toWrite) throws IOException{
        file_write(new File(dest), toWrite, null);
    }

    /**
     * Vérifie la disponibilité d'un service FTP
     * @param serverName    Nom du serveur FTP
     * @param serverPort    Port du service FTP
     * @param userName        Nom d'utilisateur FTP
     * @param userPwd        Mot de passe FTP
     * @param passiveMode    Mode passive (true)
     * @param pathSource    Chemin de la source sur le disque dur
     * @param pathDest        Chemin de la destination locale
     * @return                true si le connexion s'est effectuée avec succès
     * @throws IOException
     */
    public static Boolean FTP_check(String serverName, int serverPort, String userName, String userPwd, Boolean passiveMode) throws IOException{
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(serverName, serverPort);
            ftpClient.login(userName, userPwd);
            if(passiveMode)ftpClient.enterLocalPassiveMode();
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }finally{
            if(ftpClient!=null){
                ftpClient.logout();
                ftpClient.disconnect();
            }
        }
        return true ;
    }

    /**
     * Télécharge un répertoire ou un fichier en entier depuis une adresse ftp
     * @param serverName    Nom du serveur FTP
     * @param serverPort    Port du service FTP
     * @param userName        Nom d'utilisateur FTP
     * @param userPwd        Mot de passe FTP
     * @param passiveMode    Mode passive (true)
     * @param pathSource    Chemin de la source sur le disque dur
     * @param pathDest        Chemin de la destination locale
     * @return                true si le téléchargement s'est effectué (y compris avec erreurs de téléchargement, ne reporte que les erreurs de connexion)
     * @throws IOException
     */
    public static Boolean FTP_download(String serverName, int serverPort, String userName, String userPwd, Boolean passiveMode, String pathSource, String pathDest) throws IOException{
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(serverName, serverPort);
            ftpClient.login(userName, userPwd);
            if(passiveMode)ftpClient.enterLocalPassiveMode();
            downloadDirectory(ftpClient, pathSource, "", pathDest);
            ftpClient.logout();
            ftpClient.disconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }finally{
            if(ftpClient!=null){
                ftpClient.logout();
                ftpClient.disconnect();
            }
        }
        return true ;
    }
    
    /**
     * Télécharge un répertoire en entier depuis une adresse ftp
     * @param ftpClient Instance de la classe org.apache.commons.net.ftp.FTPClient.
     * @param parentDir chemin du répertoire parent du fichier en cours de téélchargement sur le serveur FTP (= répertoires précédents)
     * @param currentDir Chemin du répertorie en cours de téléchargement
     * @param saveDir chemin d'enregistrement local du répertoire complet
     * @throws IOException
     */
    public static void downloadDirectory(FTPClient ftpClient, String parentDir, String currentDir, String saveDir) throws IOException {
        // Au premier tour, il n'y a pas de répertoire courant
        String dirToList = parentDir;
        if (!currentDir.equals(""))dirToList += "/" + currentDir;
        FTPFile[] subFiles = ftpClient.listFiles(dirToList);
        if (subFiles != null && subFiles.length > 0) {
            for (FTPFile aFile : subFiles) {
                String currentFileName = aFile.getName();
                // Par convention, on évite les éléments systèmes "." et ".."
                if (currentFileName.equals(".") || currentFileName.equals(".."))continue;
                String filePath = parentDir + "/" + currentDir + "/" + currentFileName;
                if (currentDir.equals("")) filePath = parentDir + "/" + currentFileName;
                String newDirPath = saveDir + parentDir + File.separator + currentDir + File.separator + currentFileName;
                if (currentDir.equals("")) newDirPath = saveDir + parentDir + File.separator + currentFileName;
                if (aFile.isDirectory()) {
                    // Création d'un nouveau répertoire et lancement de la récursive si répertoire dans répertoire
                    File newDir = new File(newDirPath);
                    newDir.mkdirs();
                    downloadDirectory(ftpClient, dirToList, currentFileName, saveDir);
                } else {
                    // Sinon, téléchargmenet du fichier
                   downloadSingleFile(ftpClient, filePath, newDirPath);
                }
            }
        }
    }

    /**
     * Télécharge un fichier unique depuis une adresse FTP
     * @param ftpClient Instance de la classe org.apache.commons.net.ftp.FTPClient.
     * @param remoteFilePath chemin du fichier à télécharger sur le serveur FTP
     * @param savePath chemin d'enregistrement local du fichier
     * @return true si le téléchargement est fructueux
     * @throws IOException
     */
    public static boolean downloadSingleFile(FTPClient ftpClient, String remoteFilePath, String savePath) throws IOException {
        File downloadFile = new File(savePath);
        File parentDir = downloadFile.getParentFile();
        if (!parentDir.exists())parentDir.mkdir();
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpClient.retrieveFile(remoteFilePath, outputStream);
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (outputStream != null)outputStream.close();
        }
    }

    /**
 	* Recherche d'un chemin disponible (pas de fichier), si pas dispo incrémentation sur suffice (n)
 	* @param i_fileName_OUT	Chemin attenu pour la sortie
 	* @param extensionIfDifferent extension si l'on modifie le format de fichier
 	* @return
 	*/
     public static String DVP_findAvailableName(String i_fileName_OUT, String extensionIfDifferent){
     	    	String extension = FilenameUtils.getExtension(i_fileName_OUT) ;
     	    	if(extensionIfDifferent != null && extension != null && !extensionIfDifferent.equals(extension)){
     	    		i_fileName_OUT =  FilenameUtils.removeExtension(i_fileName_OUT)+"."+extensionIfDifferent;
     	    		extension = extensionIfDifferent ;
     	    	}
     	    	Integer i = 1 ;
     	    	String i_fileName_OUT_previous = i_fileName_OUT ;
     	    	while(new File(i_fileName_OUT).exists())i_fileName_OUT =  (extension==null)?
     	    			i_fileName_OUT_previous+" ("+(i++)+")"
     	    			: FilenameUtils.removeExtension(i_fileName_OUT_previous)+" ("+(i++)+")."+extension;
     	    	return i_fileName_OUT ;
     	    	
     }
}
