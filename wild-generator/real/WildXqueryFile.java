
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

// Dépendances, bibliothèques JAVA par exemple.
import java.util.HashMap;
import java.util.List;


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
	WILD_initialize_WildScriptFile(i_WILD_dObject/*** Paramètres à actualiser ***/);
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
	if(commenter==null)commenter="##";
	if(commenterBegin==null)commenterBegin="#";
	if(commenterEnd==null)commenterEnd="#";
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
	this.WILD_initialize_WildXqueryFile(i_WILD_dObject,i_pathToFile,"UTF-8","##","#","#",";");
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

//	//	//	Etape	"5" : poids relatif de 50, Ecriture du fichier (++)
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

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Préparation du fichier xQuery
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Transformation du (des) fichier(s) XML (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 50, Enregistrement des résultats (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"process_output1" : Liste des erreurs rencontrées (HashMap<String, Object>)
	//	this.WILD_setOutput("process_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
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
 * @return	Nom du fichier de sortie (String) {}
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
 * @return	Nom du fichier de sortie (String) {}
 */
 public String process (
	String i_fileOutput,
	WildMarkUpFile i_toProcess
	)throws Exception{return process(i_fileOutput,null,i_toProcess);
}
}

