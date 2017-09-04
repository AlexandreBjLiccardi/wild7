
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

// Dépendances, bibliothèques JAVA par exemple.
import java.util.HashMap;
import java.util.List;


/**
 * Code généré automatiquement par l'outil Wild
 * Client d'un service fournisseur d'un flux web
 */
public abstract class WildWebService extends WildService{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildWebService(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected Map<String,Object> results; // Résultats élémentaires
	protected Map<String,Object> stats; // Statistiques d'exécution
	protected Map<Integer,Map<String,Object>> errors; // Erreurs rencontrées
	protected Boolean success; // Succès de l'opération
	protected Boolean isValid; // Validité du document de sortie
	protected String outputFilePath; // Chemin d'accès au fichier de récupération du flux

// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
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
 * @param i_clientId	Identifiant de l'utilisateur{}
 * @param i_clientPwd	Mot de passe de l'utilisateur{}
 * @param i_hostName	Nom de l'hôte{}
 * @param i_hostIp	IP de l'hôte{}
 */
protected void WILD_initialize_WildWebService(
	WildObject i_WILD_dObject,
	String i_clientId,
	String i_clientPwd,
	String i_hostName,
	String i_hostIp
) throws Exception {

//	// Amorce de la classe
	// Initialisation de la classe d'objet selon le schéma Wild
	WILD_dObject = i_WILD_dObject ;
	WILD_initialize_WildService(i_WILD_dObject/*** Paramètres à actualiser ***/);
	// Préparation des variables d'invocation (considérées comme champs globaux)
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
 * @param i_clientId	Identifiant de l'utilisateur{}
 * @param i_clientPwd	Mot de passe de l'utilisateur{}
 * @param i_hostName	Nom de l'hôte{}
 * @param i_hostIp	IP de l'hôte{}
 */

public WildWebService(
	WildObject i_WILD_dObject,
	String i_clientId,
	String i_clientPwd,
	String i_hostName,
	String i_hostIp
) throws Exception{
	if(clientId==null)clientId="null";
	if(clientPwd==null)clientPwd="null";
	if(hostName==null)hostName="null";
	if(hostIp==null)hostIp="null";
	this.WILD_initialize_WildWebService(i_WILD_dObject,i_clientId,i_clientPwd,i_hostName,i_hostIp);
}

// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Constructeur, appelle nécessairement WILD_initialize()
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 */

public WildWebService(
	WildObject i_WILD_dObject
) throws Exception{
	this.WILD_initialize_WildWebService(i_WILD_dObject,"null","null","null","null");
}

/*	### NOUVELLE METHODE ###
	Méthode : checkDatas - Contrôle des données obtenues par capture du flux{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Contrôle des données obtenues par capture du flux{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_serviceResponseFile	Chemin d'accès au fichier contenant la réponse du flux{}
 * @param i_xsdTesterFile	Chemin d'accès au fichier XSD auquel on vérifie la conformité, cas d'une réponse XML{}
 * @param i_jsonObjectsLists	Liste des adresses de parse natif json pour cas JSON{}
 * @param i_typeJson	Type de test JSON - 1,2 ou 3.{}
 * @return	{}
 */
public Boolean checkDatas (
	String i_serviceResponseFile,
	String i_xsdTesterFile,
	List<String> i_jsonObjectsLists,
	Integer i_typeJson
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Boolean WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Identification du cas de test (XML ou JSON)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Vérification des prérequis
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 50, Exécution du test (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 20, Compilation des résultats (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"checkDatas_output1" : Liste des erreurs (List<Object[]>)
	//	this.WILD_setOutput("checkDatas_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * Contrôle des données obtenues par capture du flux{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_serviceResponseFile	Chemin d'accès au fichier contenant la réponse du flux {}
 * @param i_xsdTesterFile	Chemin d'accès au fichier XSD auquel on vérifie la conformité, cas d'une réponse XML {}
 * @return	Succès de la validation (Boolean) {}
 */
 public Boolean checkDatas (
	String i_serviceResponseFile,
	String i_xsdTesterFile
	)throws Exception{return checkDatas(i_serviceResponseFile,i_xsdTesterFile,null,null);
}
/*	### NOUVELLE METHODE ###
	Méthode : parseDatas - Opération de reconnaissance et de lecture des données obtenues par capture du flux, construction d'une Map.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Opération de reconnaissance et de lecture des données obtenues par capture du flux, construction d'une Map.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_serviceResponseFile	Chemin d'accès au fichier contenant la réponse du flux{}
 * @param i_nodeKeyName	nom de l'élément de boucle initial - parse natif si JSON, xpath si XML{}
 * @param i_attributeNames	chemins relatifs depuis l'élément de boucle initial, pour chacune des valeurs récupérées (parse natif si JSON, xpath si XML){}
 */
public void parseDatas (
	String i_serviceResponseFile,
	String i_nodeKeyName,
	List<String> i_attributeNames
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Vérification des prérequis
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 50, Parse du fichier (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Compilation des résultats (+)
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
	Méthode : getStats - Récupération des statistiques d'exécution de la capture du flux{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des statistiques d'exécution de la capture du flux{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public Map<String, Object> getStats ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Map<String, Object> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Fourniture du résultat
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
	Méthode : getResult - Récupération d'un résultat élémentaire concernant la capture du flux{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération d'un résultat élémentaire concernant la capture du flux{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_resultName	nom du résultat élémentaire{}
 * @return	{}
 */
public Object getResult (
	String i_resultName
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Object WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Fourniture du résultat
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
	Méthode : getResultMap - Récupération des résultats élémentaires concernant la capture du flux{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des résultats élémentaires concernant la capture du flux{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public Map<String, Object> getResultMap ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Map<String, Object> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Fourniture du résultat
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
	Méthode : pgSaveTest - Sauvegarde des résultats élémentaires et des statistiques de test, dans une base distante PG{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Sauvegarde des résultats élémentaires et des statistiques de test, dans une base distante PG{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void pgSaveTest ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Vérification des prérequis
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 50, Passage de l'ordre SQL (++)
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

