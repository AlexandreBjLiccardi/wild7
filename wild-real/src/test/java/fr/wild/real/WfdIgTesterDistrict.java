package fr.wild.real;

import static fr.wild.common.IoFileSystem.file_getDirContents;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import fr.wild.orchestra.Wild4Test;

public class WfdIgTesterDistrict {
	private static final String PG_SCHEMA_NAME = "wfd_sig_2016";
	private static final String pathToDescriptionFile = "wfdSigFile/Automatisation_fusion_shp_rapportage_v0-3.xlsx";
	private static final String clientId = "postgres";
	private static final String clientPwd = "Dcie2904";
	private static final String hostName = "menatwork";
	private static final String hostIp = "op000056:5432";
	private List<File> fileList = new ArrayList() ;
	private HashMap<String,List<WildWfdSigFile>> encounteredSchemaList = new HashMap() ;
	private String pathExtLogError = null;
	private String dateToext = null;

	
	@Test
    public void wfdIgTester() throws Exception{
		//System.out.println(System.getProperty("user.name"));
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd_HHmm");
		Date now = new Date();
		dateToext = sdfDate.format(now) ;
		pathExtLogError = "wfdSigFile/logErrors_"+dateToext+".csv";
		
		if(new File(pathExtLogError).exists())new File(pathExtLogError).delete();
		Wild4Test wildModelTester = new Wild4Test();
	// Sélection du dossier ou du fichier shp
		final JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		if(chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)return;
		final File fis = chooser.getSelectedFile();
	// Construction de la liste des fichiers SHP  traiter		
		buildFileList(fis);
	// Si aucun fichier identifié : arrêt de l'exécution après avertissement.
		if(fileList.isEmpty()){
            JOptionPane.showMessageDialog(null, "Aucun fichier {SHP} trouvé.", "Information", JOptionPane.INFORMATION_MESSAGE);
			return ;
		}
	// Sinon test de chacun des fichiers
		Integer i = 0 , j = 0;
		for(File fileInput:fileList){
			i++;
			String fileInputPath = fileInput.getPath();
			System.out.println("___ Traitement du fichier : {"+fileInputPath+"}. ["+i+"/"+fileList.size()+"].");
			String schemaName = null ;
			try{
				
				System.out.println("*** Construction");
				wildModelTester
	     		.addObject("wfdTestFile_"+i,"real","WildWfdSigFile",new Object[]{
	     				fileInputPath,pathToDescriptionFile,clientId,clientPwd,hostName,hostIp
	     		});
			// Récupération du nom de schéma, sert de test de conformité du nom de fichier
				schemaName = wildModelTester
			     		.getFromMethod("wfdTestFile_"+i,"getWfdSchema");
				if(schemaName == null){
					System.out.println("*** Le fichier {"+fileInputPath+"} ne correspond à aucun schéma de nom WFD.");
					wildModelTester.free("wfdTestFile_"+i);
					continue ;
				}
				Boolean isInstantiable = wildModelTester
			     		.getFromMethod("wfdTestFile_"+i,"isInstantiable");
				if(!isInstantiable){
					System.out.println("*** Le fichier {"+fileInputPath+"} n'est pas accompagné des fichiers minimaux pour lire un SHP.");
					wildModelTester.free("wfdTestFile_"+i);
					continue ;
				}
				System.out.println("*** Tests préliminaires");
				System.out.println("*** *** Encoding");
				wildModelTester
	     		.executeMethod("wfdTestFile_"+i,"checkWfdEncoding"); // nécessairement en premier car charge le WildDataSet
				System.out.println("*** *** Système de projection");
				wildModelTester
	     		.executeMethod("wfdTestFile_"+i,"checkWfdCrs");
				System.out.println("*** *** Vérification des géométries");
				wildModelTester
	     		.executeMethod("wfdTestFile_"+i,"checkWfdGeometryTypes"); 
				System.out.println("*** *** Vérification des attributs");
				wildModelTester
	     		.executeMethod("wfdTestFile_"+i,"checkWfdAttributes");
				
				System.out.println("*** Tests terminés, écriture des erreurs.");
				wildModelTester
	     		.executeMethod("wfdTestFile_"+i,"writeWfdErrorList",new Object[]{
	     				pathExtLogError
	     		});
				
				System.out.println("*** Chargement dans Postgresql");
				wildModelTester
	     		.executeMethod("wfdTestFile_"+i,"pgWfdExport",new Object[]{
	     				PG_SCHEMA_NAME
	     		});
				
				
				
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(schemaName != null){
					j++;
					System.out.println("*** Ecriture du log dans PostgreSQL");
					wildModelTester
		     		.executeMethod("wfdTestFile_"+i,"pgWfdLog");
						
					WildWfdSigFile currentWfdSigFile = wildModelTester
							.getObject("wfdTestFile_"+i);
					wildModelTester
					.executeMethod("wfdTestFile_"+i,"freeFromTesters");
		// Construction de la liste des fichiers à fusionner pour chaque schéma rencontré
					if(encounteredSchemaList.keySet().contains(schemaName)){
						List newSchemas = encounteredSchemaList.get(schemaName);
						newSchemas.add(currentWfdSigFile);
						encounteredSchemaList.put(schemaName, newSchemas);
					}else{
						List newSchemas = new ArrayList<WildWfdSigFile>();
						newSchemas.add(currentWfdSigFile);
						encounteredSchemaList.put(schemaName, newSchemas);
					}
					wildModelTester
					.free("wfdTestFile_"+i);
				}
				
			}
				System.out.println("*** Traitement terminé.");
		}
			
		
				System.out.println("Fusion de "+j+" fichier(s).");
			
// Pour chaque schéma, on crée une couche SHP contenant la fusion des couches importées de même schéma
// Cette couche fusionnée est exportée vers PostgreSQL
		for(String shemaName : encounteredSchemaList.keySet()){
			try{
				System.out.println("___ Schema {"+shemaName+"}, "+encounteredSchemaList.get(shemaName).size()+" fichiers.");
				String destFileName = "wfdSigFile/wfdTestFile_merged_"+shemaName+"_"+dateToext+".shp" ;
				wildModelTester
	     		.addObject("wfdTestFile_"+shemaName,"real","WildWfdSigFile",new Object[]{
	     				destFileName,pathToDescriptionFile,clientId,clientPwd,hostName,hostIp
	     		});	
				
				String destFilePath = wildModelTester
	     		.getFromMethod("wfdTestFile_"+shemaName,"getPath");	
				System.out.println("*** Fichier de destination : {"+destFilePath+"}.");
				System.out.println("*** Fusion des fichiers {MODE DIFFERENTIEL}...");
				wildModelTester
	     		.executeMethod("wfdTestFile_"+shemaName,"mergeDiff",new Object[]{
	     				encounteredSchemaList.get(shemaName) 
	     		});
				System.out.println("*** Chargement dans Postgresql");
				wildModelTester
	     		.executeMethod("wfdTestFile_"+shemaName,"pgWfdExport",new Object[]{
	     				PG_SCHEMA_NAME
	     		});			
				System.out.println("*** Traitement terminé.");
			}catch(Exception e){e.printStackTrace();}
			finally{
				System.out.println("*** Ecriture du log dans PostgreSQL");
				wildModelTester
	     		.executeMethod("wfdTestFile_"+shemaName,"pgWfdLog");
				wildModelTester
				.executeMethod("wfdTestFile_"+shemaName,"freeFromTesters");
				wildModelTester.free("wfdTestFile_"+shemaName);
				
			}
		}
				System.out.println("Toutes les exécutions sont terminées.");
	}
	
	// Fonction de parse des fichiers ou des sous fichiers sélectionnés
	private void buildFileList(File selectedFile){
		System.out.println("Construit la liste des fichiers SHP...");
		if(selectedFile.isDirectory()){
        	List<String> fis_list = file_getDirContents(selectedFile.getAbsolutePath());
        	for(String lsFile:fis_list)
        		if(FilenameUtils.getExtension(lsFile).toLowerCase().equals("shp"))
    				fileList.add(new File(lsFile));
        }else{
        	if(FilenameUtils.getExtension(selectedFile.getAbsolutePath()).toLowerCase().equals("shp"));
        		fileList.add(selectedFile);
        }
		System.out.println("Construction terminée, "+fileList.size()+" fichier(s) trouvé(s).");
	}

	
}
