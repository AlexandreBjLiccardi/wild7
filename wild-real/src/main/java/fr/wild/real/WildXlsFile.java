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
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

// Dépendances, bibliothèques JAVA par exemple.


/**
 * Code généré automatiquement par l'outil Wild
 * Fichier Microsoft Excel
 */
public class WildXlsFile extends WildTabsFile{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildXlsFile(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected Workbook workBook; // Classeur XLSX ou XLS en cours de lecture
	
// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	protected String pathToFile;	//Chemin vers le fichier{}
	protected String mimeEncoding;	//Encodage du fichier{}


// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***

// CE CONSTRUCTEUR DOIT ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Fonction d'initialisation, commune à tous les constructeurs.
 * "Constructeur unique"
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_pathToFile	Chemin vers le fichier{}
 * @param i_mimeEncoding	Encodage du fichier{}
 */
protected void WILD_initialize_WildXlsFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding
) throws Exception {

//	// Amorce de la classe
	// Initialisation de la classe d'objet selon le schéma Wild
	WILD_dObject = i_WILD_dObject ;
	WILD_initialize_WildTabsFile(i_WILD_dObject,i_pathToFile, i_mimeEncoding);
	// Préparation des variables d'invocation (considérées comme champs globaux)
	this.pathToFile = i_pathToFile;
	this.mimeEncoding = i_mimeEncoding;

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		castFile();
	}catch(Exception e){
		this.WILD_logException(e);
	}
}


// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Constructeur, appelle nécessairement WILD_initialize()
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_pathToFile	Chemin vers le fichier{}
 * @param i_mimeEncoding	Encodage du fichier{}
 */

public WildXlsFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding
) throws Exception{
	if(mimeEncoding==null)mimeEncoding="UTF-8";
	this.WILD_initialize_WildXlsFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding);
}

// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Constructeur, appelle nécessairement WILD_initialize()
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_pathToFile	Chemin vers le fichier{}
 */

public WildXlsFile(
	WildObject i_WILD_dObject,
	String i_pathToFile
) throws Exception{
	this.WILD_initialize_WildXlsFile(i_WILD_dObject,i_pathToFile,"UTF-8");
}

/*	### NOUVELLE METHODE ###
	Méthode : fillWith - Remplace le contenu du fichier ou de la collection par le cast d'un nouveau jeu de données.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Remplace le contenu du fichier ou de la collection par le cast d'un nouveau jeu de données.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_dataSet	Nouveau jeu de données à commit{}
 */
public void fillWith (
	WildDataSet i_dataSet
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 50, Cast du WildDataSet (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Vidage du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 50, Ecriture dufichier (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"fillWith_output1" : Chemin du fichier de sortie (String)
	//	this.WILD_setOutput("fillWith_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : castFile - Méthode de conversion standard du contenu vers un jeu de données (ou une collection).{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Méthode de conversion standard du contenu vers un jeu de données (ou une collection).{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void castFile ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		FileInputStream fis = new FileInputStream(this.fileFile);
	
		// Si extension XLSX
		if(DVP_getExtension().equals("xlsx"))
		try{
			// Essai en EXCEL 2010
			workBook = new XSSFWorkbook(fis);
		}catch(Exception e){
			// Essai en EXCEL 2007
			workBook = new HSSFWorkbook(fis);
		}
		else if(DVP_getExtension().equals("xls"))
			try{
				// Essai en EXCEL 2007
				workBook = new HSSFWorkbook(fis);
			}catch(Exception e){
				// Essai en EXCEL 2010
				workBook = new XSSFWorkbook(fis);
			}
		
		Iterator<Sheet> it_Sheets = workBook.sheetIterator();
		while(it_Sheets.hasNext()){	
			Sheet cur_Sheet  = it_Sheets.next();
			String cur_sheetName = cur_Sheet.getSheetName();
			
		// Détermination des blocs de jeux de données
			Integer maxCol_previous = 0;
			Integer nCol_previous = 0;
			Integer nb_unMove = 0;
			Integer last_header = 0;
			Boolean meta = true ;
			Boolean[] breaks =  new Boolean[cur_Sheet.getLastRowNum()+1];
			String[] breaks_str =  new String[cur_Sheet.getLastRowNum()+1];
			
		// Parcours qui sert à déterminer la forme du jeux de données
			Iterator<Row> rowIterator = cur_Sheet.iterator();
			while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Integer i = row.getRowNum();
                Integer nCol_cur = (int) row.getLastCellNum();
                if(nCol_previous == nCol_cur && nb_unMove < 12)nb_unMove++;
                else if(nCol_previous != nCol_cur || nCol_cur == null||nCol_cur == 0) nb_unMove = 0 ;
                if(nb_unMove==10){
                	for(int k=i-10;k<=i;k++){
                		breaks[k] = false ;
                		breaks_str[k] = String.valueOf(i-10);
                	}
                	breaks[i-10] = true ;
                	breaks_str[i-10] = "H";
                	last_header = i-10;
                }
                else breaks[i] = false ;
                
                if(nCol_cur>maxCol_previous){
                	if(i!=0)meta = false ;
                	maxCol_previous = nCol_cur;
                	nb_unMove = 0 ;
                	breaks[i] = true ;
                }  
                nCol_previous = nCol_cur;
                if(meta)breaks_str[i]="M";
                if(!meta&&breaks[i]){
                	breaks_str[i]="H";
                	last_header = i;
                }
                if(!meta&&!breaks[i])breaks_str[i] = String.valueOf(last_header);
			}
// Parcours de construction du jeu de données
			rowIterator = cur_Sheet.iterator();	
			Integer current_dataSet = 0 ;
			String dataSetName = null ;
			HashMap<String, WildDataSet> dataSets = new HashMap();
			HashMap<String, Object> dataSetsMeta = new HashMap();
			
			while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Integer i = row.getRowNum();
                Object[] datas ;     
                switch(breaks_str[i]){
                case "M":
                	// Add data to special map {cur_sheetName}#6D#meta
                	datas = DVP_xlsParseLine(row,true) ;
                	dataSetsMeta.put((String) datas[0], datas[1]);
                	break;
                case "H":
                	// Create new dataSet named {cur_sheetName}#6D#{current_dataSet}
                	// Parse headers
                	datas = DVP_xlsParseLine(row,true) ;
                	dataSetName = cur_sheetName ;
                	if(current_dataSet!=0)dataSetName+="#6D#"+current_dataSet++;
                	WildDataSet newWDs = new WildDataSet(this.WILD_dObject,null);
                	newWDs.setHeaders((String[]) datas);
                	dataSets.put(dataSetName, newWDs);
                	break;
                default:
                	// Add data to DataSet
                	datas = DVP_xlsParseLine(row,false) ;
                	WildDataSet eWDs = dataSets.get(dataSetName);
                	eWDs.DVP_add(datas);
                	dataSets.put(dataSetName, eWDs);
                }
                
			}
			// Ecriture de {cur_sheetName}#6D#meta si la map n'est pas vide
                if(!dataSetsMeta.isEmpty()){
                	HashMap<Integer,HashMap<String,Object>> newDatas = new HashMap();
                	newDatas.put(1, dataSetsMeta);
                	this.addDataSet(cur_sheetName+"#6D#meta",new WildDataSet(this.WILD_dObject,newDatas));
                }
                for(String k:dataSets.keySet())this.addDataSet(k,dataSets.get(k));
			
		}
		
	/*	for(String k:this.dataSets.keySet()){
			System.out.println(k);
			this.dataSets.get(k).DVP_show();
		}*/
		
//	//	//	Etape	"2" : poids relatif de 100, Parcours du jeu de données avec résolution des cas spéciaux (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Conversion en WildDataSet
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : commit - Enregistrement des modifications du WildDataSet vers le WildFile.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

private Object[] DVP_xlsParseLine(Row row, Boolean asString) {
	Iterator<Cell> cellIterator = row.cellIterator();
	Integer i = 0 ;
	Object[] toRet = new Object[row.getLastCellNum()];
	while (cellIterator.hasNext()) {
        Cell cell = cellIterator.next();
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_STRING:
        	toRet[i++] = cell.getStringCellValue() ;
            break;
        case Cell.CELL_TYPE_NUMERIC:
        	toRet[i++] = cell.getNumericCellValue() ;
            break;
        case Cell.CELL_TYPE_BOOLEAN:
        	toRet[i++] = cell.getBooleanCellValue() ;
            break;
        default :
        	toRet[i++] = cell.getRichStringCellValue();
        }
    }
	if(asString){
		String[] toRet2 = new String[row.getLastCellNum()];
		Integer r = 0 ;
		for(Object ret:toRet)toRet2[r++]= String.valueOf(ret) ;
		return toRet2;
	}
		
	
    return toRet ;
}


/**
 * Enregistrement des modifications du WildDataSet vers le WildFile.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void commit ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 50, Cast du WildDataSet et des métadonnées (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 20, Génération des propriétés et contextes (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 20, Vidage du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 50, Ecriture dufichier (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}



}

