
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
// Dépendances, bibliothèques JAVA par exemple.
import java.util.HashMap;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.transform.sax.SAXSource;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQItemType;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;

import com.saxonica.xqj.SaxonXQDataSource;

import net.sf.saxon.Configuration;


/**
 * Code généré automatiquement par l'outil Wild
 * Script xQuery
 */
public class WildXqueryFile extends WildScriptFile{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildXqueryFile(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation

// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	protected String pathToFile;	//Chemin vers le fichier{}
	protected String mimeEncoding;	//Encodage du fichier{}
	protected String commenter;	//Caractère(s) de commentaire{}
	protected String commenterBegin;	//Caractère(s) de commentaire multi-ligne, début{}
	protected String commenterEnd;	//Caractère(s) de commentaire multi-ligne, fin{}
	protected String commandEnd;	//Caractère(s) de fin de commande{}


// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***
	private List<String> error_outputList ;
	
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
 * @param i_commenter	Caractère(s) de commentaire{}
 * @param i_commenterBegin	Caractère(s) de commentaire multi-ligne, début{}
 * @param i_commenterEnd	Caractère(s) de commentaire multi-ligne, fin{}
 * @param i_commandEnd	Caractère(s) de fin de commande{}
 */
protected void WILD_initialize_WildXqueryFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding,
	String i_commenter,
	String i_commenterBegin,
	String i_commenterEnd,
	String i_commandEnd
) throws Exception {

//	// Amorce de la classe
	// Initialisation de la classe d'objet selon le schéma Wild
	WILD_dObject = i_WILD_dObject ;
	DVP_setDepth = true ;
	WILD_initialize_WildScriptFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,i_commenter,i_commenterBegin,i_commenterEnd,i_commandEnd);
	// Préparation des variables d'invocation (considérées comme champs globaux)
	this.pathToFile = i_pathToFile;
	this.mimeEncoding = i_mimeEncoding;
	this.commenter = i_commenter;
	this.commenterBegin = i_commenterBegin;
	this.commenterEnd = i_commenterEnd;
	this.commandEnd = i_commandEnd;

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

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
 * @param i_commenter	Caractère(s) de commentaire{}
 * @param i_commenterBegin	Caractère(s) de commentaire multi-ligne, début{}
 * @param i_commenterEnd	Caractère(s) de commentaire multi-ligne, fin{}
 * @param i_commandEnd	Caractère(s) de fin de commande{}
 */

public WildXqueryFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding,
	String i_commenter,
	String i_commenterBegin,
	String i_commenterEnd,
	String i_commandEnd
) throws Exception{
	if(mimeEncoding==null)mimeEncoding="UTF-8";
	if(commenter==null)commenter="#########";
	if(commenterBegin==null)commenterBegin="(:";
	if(commenterEnd==null)commenterEnd=":)";
	if(commandEnd==null)commandEnd=";";
	this.WILD_initialize_WildXqueryFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,i_commenter,i_commenterBegin,i_commenterEnd,i_commandEnd);
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

public WildXqueryFile(
	WildObject i_WILD_dObject,
	String i_pathToFile
) throws Exception{
	this.WILD_initialize_WildXqueryFile(i_WILD_dObject,i_pathToFile,"UTF-8","#########","(:",":)",";");
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

		DVP_commit();
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
	Méthode : process - Exécution du code sur un WildFile ou une série de WildFiles{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Exécution du code sur un WildFile ou une série de WildFiles{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_fileOutput	Fichier de sortie{}
 * @param i_listToProcess	Liste des chemins de fichiers à processer{}
 * @param i_toProcess	Fichier à processer{}
 * @return	{}
 */
public String process (
	String i_fileOutput,
	HashMap<String, String> i_listToProcess,
	WildMarkUpFile i_toProcess
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier
	InputStream inputStream = null ; // Flux de lecture unique du fichier xQuery, local ou distant
	XQConnection conn = null ; // Opérateur de transformation xQuery
	OutputStream out_FileOutPut = null ; // Flux d'écriture du résultat de la transformation
	List<String> error_outputList = new ArrayList<>() ; // Variable de passage en output

	
	Configuration confXquery = new Configuration();

	
//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
	// Préparation du fichier résultat de la tranformation
		WILD_toReturn = WILD_convertPath(i_fileOutput);
		File out_File =  new File(WILD_toReturn) ; // Fichier résultat de la transformation
		// Accès au xQuery, notamment si distant car une modification FME est nécessaire
		
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(out_File.exists())out_File.delete();
		
//	//	//	Etape	"1" : poids relatif de 10, Préparation du fichier xQuery
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		this.commit(); // commit obligatoire du fichier xQuery (avec téléchargement éventuel)
		inputStream = new FileInputStream(fileFile);
		
	//	//	//	Etape	"2" : poids relatif de 100, Transformation du (des) fichier(s) XML (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		System.out.println("ok");
		try{
		XQDataSource ds = (XQDataSource) new SaxonXQDataSource();
		
		conn = ds.getConnection();
		}catch(Throwable e){e.printStackTrace();}
		
		XQPreparedExpression exp = conn.prepareExpression(inputStream);
		if(i_listToProcess!=null)for(String k:i_listToProcess.keySet())
			exp.bindString(new QName(k), (WILD_convertPath_choseFile(i_listToProcess.get(k)).replace("\\", "/")), conn.createAtomicType(XQItemType.XQBASETYPE_STRING));
		if(i_toProcess!=null)
			exp.bindString(new QName("source_url"), (i_toProcess.getPath()).replace("\\", "/"), conn.createAtomicType(XQItemType.XQBASETYPE_STRING));
		XQResultSequence result = exp.executeQuery();	
//	//	//	Etape	"3" : poids relatif de 50, Enregistrement des résultats (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		out_File.getParentFile().mkdirs();
		out_FileOutPut = new FileOutputStream(out_File);
		DVP_result_processLine = "";
		while (result.next()){
			String result_item = result.getItemAsString(null);
			DVP_result_processLine += result_item ; // Conservation de la variable de résultat
			error_outputList.add(result_item); // Ecriture en output
			out_FileOutPut.write(result_item.getBytes()); // Ecriture en fichier de résultat
		}

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
//		//	//	Output	"process_output1" : Liste des erreurs rencontrées (HashMap<String, Object>)
		this.WILD_setOutput("process_output1", error_outputList); // Ne pas modifier
	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
		if(inputStream!=null)inputStream.close();
		if(out_FileOutPut!=null)out_FileOutPut.close();
		if(conn!=null)conn.close();
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
/**
 * Exécution du code sur un WildFile ou une série de WildFiles{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fileOutput	Fichier de sortie {}
 * @param i_listToProcess	Liste des chemins de fichiers à processer {}
 * @return	Sortie de traitement (String) {}
 */
 public String process (
	String i_fileOutput,
	HashMap<String, String> i_listToProcess
	)throws Exception{return process(i_fileOutput,i_listToProcess,null);
}
/**
 * Exécution du code sur un WildFile ou une série de WildFiles{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fileOutput	Fichier de sortie {}
 * @param i_toProcess	Fichier à processer {}
 * @return	Sortie de traitement (String) {}
 */
 public String process (
	String i_fileOutput,
	WildMarkUpFile i_toProcess
	)throws Exception{return process(i_fileOutput,null,i_toProcess);
}

 public List<String> DVP_getLastResultAsList(){
	 return this.DVP_getLastResultAsList();
 }
 
}

