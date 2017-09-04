
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;
import static fr.wild.common.IoFileSystem.file_write;
import static fr.wild.common.IoCommons.cast_2String;

import java.io.File;
import java.util.ArrayList;
// Dépendances, bibliothèques JAVA par exemple.
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


/**
 * Code généré automatiquement par l'outil Wild
 * Fichier de validation de schéma XSD
 */
public class WildXsdFile extends WildMarkUpFile{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildXsdFile(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation

// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	protected String pathToFile;	//Chemin vers le fichier{}
	protected String mimeEncoding;	//Encodage du fichier{}
	protected HashMap<String,String> nameSpaces;	//Liste d'URL d'espaces de nommage{}
	protected String versionXml;	//Code de version Xml{}
	protected String defaultNameSpace;	//URL d'un espace de nommage{}


// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***
	protected List<HashMap<String,Object>> DVP_resultFromProcess = new ArrayList<HashMap<String,Object>>() ; // Résultat d'un process de traitement d'un XML : rapport d'erreur

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
	 * @param i_nameSpaces	Liste d'URL d'espaces de nommage{}
	 * @param i_versionXml	Code de version Xml{}
	 * @param i_defaultNameSpace	URL d'un espace de nommage{}
	 */
	protected void WILD_initialize_WildXsdFile(
		WildObject i_WILD_dObject,
		String i_pathToFile,
		String i_mimeEncoding,
		HashMap<String,String> i_nameSpaces,
		String i_versionXml,
		String i_defaultNameSpace
	) throws Exception {

//		// Amorce de la classe
		// Initialisation de la classe d'objet selon le schéma Wild
		WILD_dObject = i_WILD_dObject ;
		WILD_initialize_WildMarkUpFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,i_nameSpaces,i_versionXml,i_defaultNameSpace);
		// Préparation des variables d'invocation (considérées comme champs globaux)
		this.pathToFile = i_pathToFile;
		this.mimeEncoding = i_mimeEncoding;
		this.nameSpaces = i_nameSpaces;
		this.versionXml = i_versionXml;
		this.defaultNameSpace = i_defaultNameSpace;

//		// Mode try de récupération des erreurs pour log
		try{	
//		// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

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

	public WildXsdFile(
		WildObject i_WILD_dObject,
		String i_pathToFile,
		String i_mimeEncoding
	) throws Exception{
		if(mimeEncoding==null)mimeEncoding="UTF-8";
		this.WILD_initialize_WildXsdFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,null,null,null);
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

	public WildXsdFile(
		WildObject i_WILD_dObject,
		String i_pathToFile
	) throws Exception{
		this.WILD_initialize_WildXsdFile(i_WILD_dObject,i_pathToFile,"UTF-8",null,null,null);
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
	 * @param i_nameSpaces	Liste d'URL d'espaces de nommage{}
	 * @param i_versionXml	Code de version Xml{}
	 */

	public WildXsdFile(
		WildObject i_WILD_dObject,
		String i_pathToFile,
		String i_mimeEncoding,
		HashMap<String,String> i_nameSpaces,
		String i_versionXml
	) throws Exception{
		if(mimeEncoding==null)mimeEncoding="UTF-8";
		this.WILD_initialize_WildXsdFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,i_nameSpaces,i_versionXml,null);
	}

	// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
	/**
	 * Code généré automatiquement par l'outil Wild
	 * Constructeur, appelle nécessairement WILD_initialize()
	 * NB. i_WILD_dObject est nécessairement passé
	 *
	 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
	 * @param i_pathToFile	Chemin vers le fichier{}
	 * @param i_nameSpaces	Liste d'URL d'espaces de nommage{}
	 * @param i_versionXml	Code de version Xml{}
	 */

	public WildXsdFile(
		WildObject i_WILD_dObject,
		String i_pathToFile,
		HashMap<String,String> i_nameSpaces,
		String i_versionXml
	) throws Exception{
		this.WILD_initialize_WildXsdFile(i_WILD_dObject,i_pathToFile,"UTF-8",i_nameSpaces,i_versionXml,null);
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
	 * @param i_defaultNameSpace	URL d'un espace de nommage{}
	 * @param i_versionXml	Code de version Xml{}
	 */

	public WildXsdFile(
		WildObject i_WILD_dObject,
		String i_pathToFile,
		String i_mimeEncoding,
		String i_defaultNameSpace,
		String i_versionXml
	) throws Exception{
		if(mimeEncoding==null)mimeEncoding="UTF-8";
		this.WILD_initialize_WildXsdFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,null,i_versionXml,i_defaultNameSpace);
	}

	// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
	/**
	 * Code généré automatiquement par l'outil Wild
	 * Constructeur, appelle nécessairement WILD_initialize()
	 * NB. i_WILD_dObject est nécessairement passé
	 *
	 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
	 * @param i_pathToFile	Chemin vers le fichier{}
	 * @param i_defaultNameSpace	URL d'un espace de nommage{}
	 * @param i_versionXml	Code de version Xml{}
	 */

	public WildXsdFile(
		WildObject i_WILD_dObject,
		String i_pathToFile,
		String i_defaultNameSpace,
		String i_versionXml
	) throws Exception{
		this.WILD_initialize_WildXsdFile(i_WILD_dObject,i_pathToFile,"UTF-8",null,i_versionXml,i_defaultNameSpace);
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
		super.commit();
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
		super.commit();
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
	Méthode : validate - Valide un fichier markUp selon la définition de schéma, alimente la variable globale DVP_resultFromProcess.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Valide un fichier markUp selon la définition de schéma, alimente la variable globale DVP_resultFromProcess.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_forProcess	Fichier à valider{}
 * @param i_forProcessFilePath	Chemin du fichier à valider{}
 */
public void validate (
	WildMarkUpFile i_forProcess,
	String i_forProcessFilePath
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		File xmlFileToProcess = null ; //  Variable File JAVA
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		commit();
//	//	//	Etape	"1" : poids relatif de 10, Contrôle de l'objet
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(i_forProcess!=null)xmlFileToProcess = i_forProcess.getFile();
		if(i_forProcessFilePath!=null&&i_forProcess==null)xmlFileToProcess = new File(WILD_convertPath_choseFile(i_forProcessFilePath));
//	//	//	Etape	"2" : poids relatif de 100, Application de la validation (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		DocumentBuilderFactory parserFactory = DocumentBuilderFactory.newInstance();
		parserFactory.setNamespaceAware(true);
		SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = factory.newSchema(new StreamSource(this.pathToFile));
		Validator validator = schema.newValidator();
		
		final List<String> exceptionsList = new ArrayList<String>();
		validator.setErrorHandler(new ErrorHandler() {
			public void warning(SAXParseException e) throws SAXException{
				e.printStackTrace();
				HashMap<String,Object> newError = new HashMap<String,Object>();
				newError.put("line", e.getLineNumber());
				newError.put("column", e.getColumnNumber());
				newError.put("message", e.getMessage());
				newError.put("MESSAGE",e.getMessage());
				newError.put("localisation", "Ligne : "+e.getLineNumber()+", Colonne : "+ e.getColumnNumber());
				newError.put("level", "warning");
				DVP_resultFromProcess.add(newError);
				exceptionsList.add("{"+e.getLineNumber()+","+e.getColumnNumber()+"};\""+e.getMessage()+"\"");
		    }
		    public void fatalError(SAXParseException e) throws SAXException{
		    	e.printStackTrace();
		    	HashMap<String,Object> newError = new HashMap<String,Object>();
				newError.put("line", e.getLineNumber());
				newError.put("column", e.getColumnNumber());
				newError.put("message", e.getMessage());
				newError.put("MESSAGE",e.getMessage());
				newError.put("localisation", "Ligne : "+e.getLineNumber()+", Colonne : "+ e.getColumnNumber());
				newError.put("level", "fatal");
				DVP_resultFromProcess.add(newError);
		    	exceptionsList.add("{"+e.getLineNumber()+","+e.getColumnNumber()+"};\""+e.getMessage()+"\"");
		    }
		    public void error(SAXParseException e) throws SAXException{
		    	
		    	HashMap<String,Object> newError = new HashMap<String,Object>();
				newError.put("line", e.getLineNumber());
				newError.put("column", e.getColumnNumber());
				newError.put("message", e.getMessage());
				newError.put("MESSAGE",e.getMessage());
				newError.put("localisation", "Ligne : "+e.getLineNumber()+"</BR>Colonne : "+ e.getColumnNumber());
				newError.put("level", "error");
				DVP_resultFromProcess.add(newError);
		    	exceptionsList.add("{"+e.getLineNumber()+","+e.getColumnNumber()+"};\""+e.getMessage()+"\"");
		    }
		  });
		 validator.validate(new StreamSource(xmlFileToProcess));
		 
//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		
//		//	//	Output	"validate_output1" : Au moins une erreur est rencontrée (Boolean)
		this.WILD_setOutput("validate_output1",exceptionsList.isEmpty()); // Ne pas modifier

//	//	//	Output	"validate_output2" : Liste des erreurs rencontrées (Map<String,Object>)
		this.WILD_setOutput("validate_output2",exceptionsList); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
/**
 * Valide un fichier markUp selon la définition de schéma, alimente la variable globale DVP_resultFromProcess.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_forProcess	Fichier à valider {}
 */
 public void validate (
	WildMarkUpFile i_forProcess
	)throws Exception{validate(i_forProcess,null);
}
/**
 * Valide un fichier markUp selon la définition de schéma, alimente la variable globale DVP_resultFromProcess.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_forProcessFilePath	Chemin du fichier à valider {}
 */
 public void validate (
	String i_forProcessFilePath
	)throws Exception{validate(null,i_forProcessFilePath);
}


/*	### NOUVELLE METHODE ###
	Méthode : dumpErrors - Copie les erreurs génrées par le test de structure vers un fichier plat{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Copie les erreurs génrées par le test de structure vers un fichier plat{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_fileOutput	Fichier de sortie{}
 */
public void dumpErrors (
	String i_fileOutput
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier
	if(DVP_resultFromProcess == null || DVP_resultFromProcess.size() == 0) return ;
//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		i_fileOutput = WILD_convertPath(i_fileOutput); // Chemin du fiohier de sortie
		if(new File(i_fileOutput).exists())new File(i_fileOutput).delete(); // Ecrasement de fichier
		Integer commitAt = 1000 ; // Nombre d'enregistrements avant écriture sur le disque
		Integer currentCount = 0 ; // Enregistrmeent courant
		String stringToWrite = "" ; // Tampon à écrire
		String LINE_SEP = "\n"; // Séparateur de ligne dans le fichier
		String FIELD_SEP = ";"; // Séparateur de champs dans le fichier

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		
		
//	//	//	Etape	"1" : poids relatif de 10, Contrôle de l'objet
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Remplissage du fichier (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		stringToWrite += "\"line\";\"column\";\"message\";\"describer\";\"localisation\";\"level\"";
		for(HashMap<String, Object> rec:DVP_resultFromProcess){
			String newLine = 	LINE_SEP
								+ DVP_castForDump(rec,"line")+FIELD_SEP
								+ DVP_castForDump(rec,"column")+FIELD_SEP
								+ DVP_castForDump(rec,"message")+FIELD_SEP
								+ DVP_castForDump(rec,"MESSAGE")+FIELD_SEP
								+ DVP_castForDump(rec,"localisation")+FIELD_SEP
								+ DVP_castForDump(rec,"level")
							 ;
			stringToWrite += newLine ;			
			if(currentCount++ >= commitAt){
				file_write(i_fileOutput, stringToWrite);
				stringToWrite = "" ;
			}
		}
//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(stringToWrite.length()>0)file_write(i_fileOutput, stringToWrite);
//	//	//	Output	"dumpErrors_output1" : Chemin du fichier de sortie (String)
		this.WILD_setOutput("dumpErrors_output1",i_fileOutput); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}
/**
 * Récupération de la liste des erreurs compilées par le dernier test
 * @return
 */
public List<HashMap<String, Object>> DVP_getErrors(){
	 return this.DVP_resultFromProcess;
}
/**
 * Récupération de la liste des erreurs compilées par le dernier test
 * Cette liste est "simplifiée" par code d'erreur
 * @return
 */
public List<HashMap<String, Object>> DVP_getErrors_light(){
	List<HashMap<String, Object>> toRet = new ArrayList(); 
	HashMap<String,HashMap<String, Object>> toRetPart = new HashMap();
	
	for(HashMap l:this.DVP_getErrors()){
		String errorCode = ((String)  l.get("message")).trim().split(":")[0].trim();
		HashMap<String, Object> errorMap = (toRetPart.get(errorCode)==null)? new HashMap():toRetPart.get(errorCode);
		Integer nbErrorCode = (toRetPart.get(errorCode)==null)? 1 : (Integer)(toRetPart.get(errorCode).get("error_nb"))+ 1 ;
		String errorLevel = (String) ((toRetPart.get(errorCode)==null)? l.get("level"):toRetPart.get(errorCode).get("level")) ;
		List<String> colPos = (List<String>) ((toRetPart.get(errorCode)==null)? new ArrayList():toRetPart.get(errorCode).get("error_list_pos"));
		colPos.add("c"+l.get("line")+",l"+l.get("column"));
		errorMap.put("error_nb", nbErrorCode);
		errorMap.put("level", errorLevel);
		errorMap.put("error_list_pos", colPos);
		errorMap.put("error_code", errorCode);
		toRetPart.put(errorCode, errorMap);		
	 };
	 
	 for(Entry<String,HashMap<String, Object>> l:toRetPart.entrySet()){
		HashMap<String, Object> subMap = (HashMap<String, Object>) l.getValue();
		String msg =(String) l.getValue().get("error_code")+" : "+  l.getValue().get("error_nb")+" errors";
		for(String s:((List<String>)l.getValue().get("error_list_pos")))msg += " - "+s;
		subMap.put("message", msg);
		subMap.put("line", 0);
		subMap.put("column", 0);
		toRet.add(subMap);
	 }
	 return toRet;
}
/**
 * Mise au format d'écriture ligne (séparateur ;, casse ")
 * @param rec		Enregistrement
 * @param cellRef	Nom de colonne (clé de l'élément dans la map)
 * @return
 */
private String DVP_castForDump(HashMap<String, Object> rec, String cellRef){
	return (rec.get(cellRef) == null)?"\"\"":"\""+(cast_2String(rec.get(cellRef))).replace("\"","\\\"")+"\"";
}
 
}

