
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

// Dépendances, bibliothèques JAVA par exemple.
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Node;


/**
 * Code généré automatiquement par l'outil Wild
 * Client d'un service protocole REST
 */
public class WildRestService extends WildWebService{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildRestService(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected Map<String,Object> cookies; // cookies nécessaires au appels
	protected String cookiesStr; // chaîne concaténée pour appel des cookies
	protected String urlService; // URL du service
	protected String urlCookie; // URL pour appel de cookie

// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	protected Node parametersNode;	//Paramètres sous forme de nœud XML{}
	protected String parametersXmlFile;	//Paramètres sous forme de fichier XML dont on donne le lien{}
	protected Map<String,Object> parametersMap;	//Paramètres sous forme de Map{}


// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***

// CE CONSTRUCTEUR DOIT ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Fonction d'initialisation, commune à tous les constructeurs.
 * "Constructeur unique"
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_parametersNode	Paramètres sous forme de nœud XML{}
 * @param i_parametersXmlFile	Paramètres sous forme de fichier XML dont on donne le lien{}
 * @param i_parametersMap	Paramètres sous forme de Map{}
 */
protected void WILD_initialize_WildRestService(
	WildObject i_WILD_dObject,
	Node i_parametersNode,
	String i_parametersXmlFile,
	Map<String,Object> i_parametersMap
) throws Exception {

//	// Amorce de la classe
	// Initialisation de la classe d'objet selon le schéma Wild
	WILD_dObject = i_WILD_dObject ;
	WILD_initialize_WildWebService(i_WILD_dObject/*** Paramètres à actualiser ***/);
	// Préparation des variables d'invocation (considérées comme champs globaux)
	this.parametersNode = i_parametersNode;
	this.parametersXmlFile = i_parametersXmlFile;
	this.parametersMap = i_parametersMap;

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
 * @param i_parametersNode	Paramètres sous forme de nœud XML{}
 */

public WildRestService(
	WildObject i_WILD_dObject,
	Node i_parametersNode
) throws Exception{
	this.WILD_initialize_WildRestService(i_WILD_dObject,i_parametersNode,null,null);
}


// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Constructeur, appelle nécessairement WILD_initialize()
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_parametersXmlFile	Paramètres sous forme de fichier XML dont on donne le lien{}
 */

public WildRestService(
	WildObject i_WILD_dObject,
	String i_parametersXmlFile
) throws Exception{
	this.WILD_initialize_WildRestService(i_WILD_dObject,null,i_parametersXmlFile,null);
}


// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Constructeur, appelle nécessairement WILD_initialize()
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_parametersMap	Paramètres sous forme de Map{}
 */

public WildRestService(
	WildObject i_WILD_dObject,
	Map<String,Object> i_parametersMap
) throws Exception{
	this.WILD_initialize_WildRestService(i_WILD_dObject,null,null,i_parametersMap);
}

/*	### NOUVELLE METHODE ###
	Méthode : urlConnect - Connexion à une URL distante (instanciation de l'objet){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Connexion à une URL distante (instanciation de l'objet){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void urlConnect ()throws Exception{
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

//	//	//	Etape	"2" : poids relatif de 10, Instanciation de la connexion
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
	Méthode : getCookie - Récupération d'un cookie correspondant à une adresse distante{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération d'un cookie correspondant à une adresse distante{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_urlCookie	URL spécifique de connexion pour obtention du cookie{}
 * @param i_varCookies	nom éventuel des variables à retenir, si null les retient tous{}
 * @return	{}
 */
public String getCookie (
	String i_urlCookie,
	List<String> i_varCookies
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Vérification des prérequis
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Connexion à l'adresse spécifique pour récupération de cookie
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Envoi de la requête
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Récupération des variables passées en cookie
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 10, Compilation des résultats
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

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
 
/**
 * Récupération d'un cookie correspondant à une adresse distante{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_urlCookie	URL spécifique de connexion pour obtention du cookie {}
 * @return	Valeur du cookie(String) {}
 */
 public String getCookie (
	String i_urlCookie
	)throws Exception{
return getCookie(i_urlCookie,null);
} 

/*	### NOUVELLE METHODE ###
	Méthode : setCookie - Affectation d'un cookie à une instance de connexion URL{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Affectation d'un cookie à une instance de connexion URL{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_valuesCookiesMap	Map de description des variables de cookies{}
 * @param i_valuesCookiesStr	Chaîne de caractères contenant les valeurs de cookies{}
 */
public void setCookie (
	Map<String,Object> i_valuesCookiesMap,
	String i_valuesCookiesStr
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Construction de la chaîne de caractères de concaténation des variables
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Passage des variables à l'URL
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
/**
 * Affectation d'un cookie à une instance de connexion URL{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_valuesCookiesMap	Map de description des variables de cookies {}
 */
 public void setCookie (
	Map<String,Object> i_valuesCookiesMap
	)throws Exception{setCookie(i_valuesCookiesMap,null);
}
/**
 * Affectation d'un cookie à une instance de connexion URL{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_valuesCookiesStr	Chaîne de caractères contenant les valeurs de cookies {}
 */
 public void setCookie (
	String i_valuesCookiesStr
	)throws Exception{setCookie(null,i_valuesCookiesStr);
}
/*	### NOUVELLE METHODE ###
	Méthode : setHeader - Construction de l'amorce de requête (chaînage des variables) - appel des cas POST ou GET{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Construction de l'amorce de requête (chaînage des variables) - appel des cas POST ou GET{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_urlConnexion	URL de connexion{}
 * @param i_xmlParameters	Paramètres sous forme XML{}
 * @param i_restMode	Protocole GET ou POST{}
 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres{}
 * @param i_xmlParametersMap	Paramètres sous forme de Map{}
 */
public void setHeader (
	String i_urlConnexion,
	Node i_xmlParameters,
	String i_restMode,
	String i_xmlParametersFile,
	Map<String,Object> i_xmlParametersMap
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Identification de la structure de passage de variables (Map ou XML)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Vérification des prérequis
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Appel de la méthode POST ou GET
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
/**
 * Construction de l'amorce de requête (chaînage des variables) - appel des cas POST ou GET{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_urlConnexion	URL de connexion {}
 * @param i_xmlParameters	Paramètres sous forme XML {}
 * @param i_restMode	Protocole GET ou POST {}
 */
 public void setHeader (
	String i_urlConnexion,
	Node i_xmlParameters,
	String i_restMode
	)throws Exception{setHeader(i_urlConnexion,i_xmlParameters,i_restMode,null,null);
}
/**
 * Construction de l'amorce de requête (chaînage des variables) - appel des cas POST ou GET{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_urlConnexion	URL de connexion {}
 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres {}
 * @param i_restMode	Protocole GET ou POST {}
 */
 public void setHeader (
	String i_urlConnexion,
	String i_xmlParametersFile,
	String i_restMode
	)throws Exception{setHeader(i_urlConnexion,null,i_restMode,i_xmlParametersFile,null);
}
/**
 * Construction de l'amorce de requête (chaînage des variables) - appel des cas POST ou GET{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_urlConnexion	URL de connexion {}
 * @param i_xmlParametersMap	Paramètres sous forme de Map {}
 * @param i_restMode	Protocole GET ou POST {}
 */
 public void setHeader (
	String i_urlConnexion,
	Map<String,Object> i_xmlParametersMap,
	String i_restMode
	)throws Exception{setHeader(i_urlConnexion,null,i_restMode,null,i_xmlParametersMap);
}
/*	### NOUVELLE METHODE ###
	Méthode : setGetHeader - Construction de l'amorce de requête (chaînage des variables) - GET{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Construction de l'amorce de requête (chaînage des variables) - GET{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_urlConnexion	URL de connexion{}
 * @param i_xmlParameters	Paramètres sous forme XML{}
 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres{}
 * @param i_xmlParametersMap	Paramètres sous forme de Map{}
 */
public void setGetHeader (
	String i_urlConnexion,
	Node i_xmlParameters,
	String i_xmlParametersFile,
	Map<String,Object> i_xmlParametersMap
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Identification de la structure de passage de variables (Map ou XML)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Parse de la structure
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Composition de la chaîne
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
/**
 * Construction de l'amorce de requête (chaînage des variables) - GET{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_urlConnexion	URL de connexion {}
 * @param i_xmlParameters	Paramètres sous forme XML {}
 */
 public void setGetHeader (
	String i_urlConnexion,
	Node i_xmlParameters
	)throws Exception{setGetHeader(i_urlConnexion,i_xmlParameters,null,null);
}
/**
 * Construction de l'amorce de requête (chaînage des variables) - GET{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_urlConnexion	URL de connexion {}
 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres {}
 */
 public void setGetHeader (
	String i_urlConnexion,
	String i_xmlParametersFile
	)throws Exception{setGetHeader(i_urlConnexion,null,i_xmlParametersFile,null);
}
/**
 * Construction de l'amorce de requête (chaînage des variables) - GET{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_urlConnexion	URL de connexion {}
 * @param i_xmlParametersMap	Paramètres sous forme de Map {}
 */
 public void setGetHeader (
	String i_urlConnexion,
	Map<String,Object> i_xmlParametersMap
	)throws Exception{setGetHeader(i_urlConnexion,null,null,i_xmlParametersMap);
}
/*	### NOUVELLE METHODE ###
	Méthode : setPostHeader - Construction de l'amorce de requête (chaînage des variables) - POST{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Construction de l'amorce de requête (chaînage des variables) - POST{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_urlConnexion	URL de connexion{}
 * @param i_xmlParameters	Paramètres sous forme XML{}
 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres{}
 * @param i_xmlParametersMap	Paramètres sous forme de Map{}
 */
public void setPostHeader (
	String i_urlConnexion,
	Node i_xmlParameters,
	String i_xmlParametersFile,
	Map<String,Object> i_xmlParametersMap
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Identification de la structure de passage de variables (Map ou XML)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Parse de la structure
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Composition de la chaîne
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
/**
 * Construction de l'amorce de requête (chaînage des variables) - POST{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_urlConnexion	URL de connexion {}
 * @param i_xmlParameters	Paramètres sous forme XML {}
 */
 public void setPostHeader (
	String i_urlConnexion,
	Node i_xmlParameters
	)throws Exception{setPostHeader(i_urlConnexion,i_xmlParameters,null,null);
}
/**
 * Construction de l'amorce de requête (chaînage des variables) - POST{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_urlConnexion	URL de connexion {}
 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres {}
 */
 public void setPostHeader (
	String i_urlConnexion,
	String i_xmlParametersFile
	)throws Exception{setPostHeader(i_urlConnexion,null,i_xmlParametersFile,null);
}
/**
 * Construction de l'amorce de requête (chaînage des variables) - POST{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_urlConnexion	URL de connexion {}
 * @param i_xmlParametersMap	Paramètres sous forme de Map {}
 */
 public void setPostHeader (
	String i_urlConnexion,
	Map<String,Object> i_xmlParametersMap
	)throws Exception{setPostHeader(i_urlConnexion,null,null,i_xmlParametersMap);
}
/*	### NOUVELLE METHODE ###
	Méthode : getDatas - Récupération des données pour enregistrement dans un fichier (capture de flux){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des données pour enregistrement dans un fichier (capture de flux){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public String getDatas ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Création des flux entrants et sortants
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Capture du flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Vérification des flux et clôture
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

}

