
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

// Dépendances, bibliothèques JAVA par exemple.


/**
 * Code généré automatiquement par l'outil Wild
 * Fichier SIG du rapportage DCE (fichier SHP embarquant des fonctionnalités complémentaires)
 */
public class WildWfdSigFile extends WildShpFile{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildWfdSigFile(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected String wfdSchema; // Nom du schéma DCE pour le fichier
	protected String wfdRBDistrict; // Code du bassin versant pour le fichier
	protected String wfdCreateDate; // Date de production du fichier
	protected WildXlsFile wfdReferenceBook; // Fichier XLS de description des formats de fichier
	protected WildPgService wfdPgConnexion; // Base de données PostgreSQL PostGIS de log et d'export éventuel
	protected HashMap<String,HashMap<Integer,HashMap<String,Object>>> wfdErrorList; // Map des erreurs récupérées selon tous les différents tests

// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	protected String pathToFile;	//Chemin vers le fichier{}
	protected String pathToDescriptionFile;	//Chemin vers le fichier Excel de description WFD{}
	protected String clientId;	//Identifiant de l'utilisateur{}
	protected String clientPwd;	//Mot de passe de l'utilisateur{}
	protected String hostName;	//Nom de l'hôte{}
	protected String hostIp;	//IP de l'hôte{}


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
 * @param i_pathToDescriptionFile	Chemin vers le fichier Excel de description WFD{}
 * @param i_clientId	Identifiant de l'utilisateur{}
 * @param i_clientPwd	Mot de passe de l'utilisateur{}
 * @param i_hostName	Nom de l'hôte{}
 * @param i_hostIp	IP de l'hôte{}
 */
protected void WILD_initialize_WildWfdSigFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_pathToDescriptionFile,
	String i_clientId,
	String i_clientPwd,
	String i_hostName,
	String i_hostIp
) throws Exception {

//	// Amorce de la classe
	// Initialisation de la classe d'objet selon le schéma Wild
	WILD_dObject = i_WILD_dObject ;
	WILD_initialize_WildShpFile(i_WILD_dObject/*** Paramètres à actualiser ***/);
	// Préparation des variables d'invocation (considérées comme champs globaux)
	this.pathToFile = i_pathToFile;
	this.pathToDescriptionFile = i_pathToDescriptionFile;
	this.clientId = i_clientId;
	this.clientPwd = i_clientPwd;
	this.hostName = i_hostName;
	this.hostIp = i_hostIp;

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
 * @param i_pathToDescriptionFile	Chemin vers le fichier Excel de description WFD{}
 * @param i_clientId	Identifiant de l'utilisateur{}
 * @param i_clientPwd	Mot de passe de l'utilisateur{}
 * @param i_hostName	Nom de l'hôte{}
 * @param i_hostIp	IP de l'hôte{}
 */

public WildWfdSigFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_pathToDescriptionFile,
	String i_clientId,
	String i_clientPwd,
	String i_hostName,
	String i_hostIp
) throws Exception{
	this.WILD_initialize_WildWfdSigFile(i_WILD_dObject,i_pathToFile,i_pathToDescriptionFile,i_clientId,i_clientPwd,i_hostName,i_hostIp);
}

/*	### NOUVELLE METHODE ###
	Méthode : checkWfdGeometryTypes - Vérification des types de géométries{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Vérification des types de géométries{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public HashMap<Integer,HashMap<String,Object>> checkWfdGeometryTypes ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<Integer,HashMap<String,Object>> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"checkWfdGeometryTypes_output1" : Nombre d'erreurs identifiées (Integer)
	//	this.WILD_setOutput("checkWfdGeometryTypes_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkWfdGeometryTypes_output2" : Map des erreurs récupérées (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("checkWfdGeometryTypes_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkWfdGeometryTypes_output3" : Tableau de score par type d'erreurs (HashMap<String,Integer>)
	//	this.WILD_setOutput("checkWfdGeometryTypes_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : checkWfdAttributes - Vérification des attributs (type, nom, ordre){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Vérification des attributs (type, nom, ordre){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public HashMap<Integer,HashMap<String,Object>> checkWfdAttributes ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<Integer,HashMap<String,Object>> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"checkWfdAttributes_output1" : Nombre d'erreurs identifiées (Integer)
	//	this.WILD_setOutput("checkWfdAttributes_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkWfdAttributes_output2" : Map des erreurs récupérées (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("checkWfdAttributes_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkWfdAttributes_output3" : Tableau de score par type d'erreurs (HashMap<String,Integer>)
	//	this.WILD_setOutput("checkWfdAttributes_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : checkWfdCrs - Vérification du système de projection{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Vérification du système de projection{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public HashMap<Integer,HashMap<String,Object>> checkWfdCrs ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<Integer,HashMap<String,Object>> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"checkWfdCrs_output1" : Nombre d'erreurs identifiées (Integer)
	//	this.WILD_setOutput("checkWfdCrs_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkWfdCrs_output2" : Map des erreurs récupérées (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("checkWfdCrs_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkWfdCrs_output3" : Tableau de score par type d'erreurs (HashMap<String,Integer>)
	//	this.WILD_setOutput("checkWfdCrs_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : pgWfdLog - Ecriture des logs de traitement vers une base PostgreSQL{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ecriture des logs de traitement vers une base PostgreSQL{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void pgWfdLog ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"pgWfdLog_output1" : Nombre de lignes écrites (Integer)
	//	this.WILD_setOutput("pgWfdLog_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"pgWfdLog_output2" : Contenu des lignes écrites (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("pgWfdLog_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : pgWfdExport - Export vers une base PostgreSQL / PostGIS{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Export vers une base PostgreSQL / PostGIS{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_outputSchema	Schéma d'export des données{}
 */
public void pgWfdExport (
	String i_outputSchema
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"pgWfdExport_output1" : Nombre de lignes écrites (Integer)
	//	this.WILD_setOutput("pgWfdExport_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"pgWfdExport_output2" : Nombre d'erreurs éventuelles (Integer)
	//	this.WILD_setOutput("pgWfdExport_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"pgWfdExport_output3" : Map des erreurs récupérées (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("pgWfdExport_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : merge - Fusion de plusieurs couches en une seule{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Fusion de plusieurs couches en une seule{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_shpToMerge	Liste des fichiers SHP à fusionner dans l'objet actuel{}
 */
public void merge (
	List<WildWfdSigFile> i_shpToMerge
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"merge_output1" : Nombre de couches fusionnées (Integer)
	//	this.WILD_setOutput("merge_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"merge_output2" : Nombre d'objets fusionnés (Integer)
	//	this.WILD_setOutput("merge_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"merge_output3" : Nombre d'erreurs éventuelles (Integer)
	//	this.WILD_setOutput("merge_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"merge_output4" : Map des erreurs récupérées (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("merge_output4",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : checkWfdEncoding - Vérification de l'encodage du fichier{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Vérification de l'encodage du fichier{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public HashMap<Integer,HashMap<String,Object>> checkWfdEncoding ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<Integer,HashMap<String,Object>> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"checkWfdEncoding_output1" : Nombre d'erreurs identifiées (Integer)
	//	this.WILD_setOutput("checkWfdEncoding_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkWfdEncoding_output2" : Map des erreurs récupérées (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("checkWfdEncoding_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkWfdEncoding_output3" : Tableau de score par type d'erreurs (HashMap<String,Integer>)
	//	this.WILD_setOutput("checkWfdEncoding_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : writeWfdErrorList - Ecriture vers un fichier externe des erreurs rencontrées{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ecriture vers un fichier externe des erreurs rencontrées{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_pathToFile_OUT	Chemin vers le fichier de sortie{}
 * @return	{}
 */
public String writeWfdErrorList (
	String i_pathToFile_OUT
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"writeWfdErrorList_output1" : Nombre de lignes écrites (Integer)
	//	this.WILD_setOutput("writeWfdErrorList_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"writeWfdErrorList_output2" : Chemin du fichier de sortie (String)
	//	this.WILD_setOutput("writeWfdErrorList_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * Ecriture vers un fichier externe des erreurs rencontrées{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @return	Chemin du fichier de sortie (String) {}
 */
 public String writeWfdErrorList ()throws Exception{
return writeWfdErrorList("WFD_errorList.csv");
} 

/*	### NOUVELLE METHODE ###
	Méthode : getWfdSchema - Récupération du nom de schéma WFD{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération du nom de schéma WFD{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public String getWfdSchema ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Renvoi de la valeur
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
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
	Méthode : isInstantiable - Récupération de l'information d'instanciation (si oui : le SHP est lisible){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération de l'information d'instanciation (si oui : le SHP est lisible){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public Boolean isInstantiable ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Boolean WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Renvoi de la valeur
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : freeFromTesters - Vide la mémoire du fichier, des informations de tests et des informations nécessaires à la réalisation des tests. Ne garde que le wildDataSet, les résultats de tests, les géométries WGS84, le fichier de configuration.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Vide la mémoire du fichier, des informations de tests et des informations nécessaires à la réalisation des tests. Ne garde que le wildDataSet, les résultats de tests, les géométries WGS84, le fichier de configuration.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void freeFromTesters ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Suppression des maps d'attributs
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Suppression des géométries inutilisées
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Libération de la connexion PostGreSQL
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
	Méthode : mergeDiff - Fusion de plusieurs couches en une seule, stocke la liste de bassins rencontrés, pour une alimentation différentielle{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Fusion de plusieurs couches en une seule, stocke la liste de bassins rencontrés, pour une alimentation différentielle{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_shpToMerge	Liste des fichiers SHP à fusionner dans l'objet actuel{}
 */
public void mergeDiff (
	List<WildWfdSigFile> i_shpToMerge
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"mergeDiff_output1" : Nombre de couches fusionnées (Integer)
	//	this.WILD_setOutput("mergeDiff_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"mergeDiff_output2" : Nombre d'objets fusionnés (Integer)
	//	this.WILD_setOutput("mergeDiff_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"mergeDiff_output3" : Nombre d'erreurs éventuelles (Integer)
	//	this.WILD_setOutput("mergeDiff_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"mergeDiff_output4" : Map des erreurs récupérées (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("mergeDiff_output4",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"mergeDiff_output5" : Nombre de bassins rencontrés (Integer)
	//	this.WILD_setOutput("mergeDiff_output5",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"mergeDiff_output6" : Liste des bassins rencontrés (List<String>)
	//	this.WILD_setOutput("mergeDiff_output6",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

}

