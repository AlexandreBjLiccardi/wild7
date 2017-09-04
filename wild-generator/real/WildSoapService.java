
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
 * Client d'un service protocole SOAP
 */
public class WildSoapService extends WildWebService{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildSoapService(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected String soapHeader; // Entête SOAP

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
protected void WILD_initialize_WildSoapService(
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

public WildSoapService(
	WildObject i_WILD_dObject,
	Node i_parametersNode
) throws Exception{
	this.WILD_initialize_WildSoapService(i_WILD_dObject,i_parametersNode,null,null);
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

public WildSoapService(
	WildObject i_WILD_dObject,
	String i_parametersXmlFile
) throws Exception{
	this.WILD_initialize_WildSoapService(i_WILD_dObject,null,i_parametersXmlFile,null);
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

public WildSoapService(
	WildObject i_WILD_dObject,
	Map<String,Object> i_parametersMap
) throws Exception{
	this.WILD_initialize_WildSoapService(i_WILD_dObject,null,null,i_parametersMap);
}

/*	### NOUVELLE METHODE ###
	Méthode : setHeader - Construction de l'amorce de requête (entête SOAP){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Construction de l'amorce de requête (entête SOAP){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_urlConnexion	URL du WSDL connexion{}
 * @param i_methodName	Nom de la méthode SOAP appelée{}
 * @param i_endPoint	EndPoint si différent de celui spécifié dans le WSDL{}
 * @param i_nameSpace	schéma namespace si différent de celui spécifié dans le WSDL{}
 * @param i_xmlParameters	Paramètres sous forme XML{}
 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres{}
 * @param i_xmlParametersMap	Paramètres sous forme de Map{}
 */
public void setHeader (
	String i_urlConnexion,
	String i_methodName,
	String i_endPoint,
	String i_nameSpace,
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

//	//	//	Etape	"1" : poids relatif de 10, Identification de la structure de passage de variables (SOAP, Map ou XML)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Parse de la structure
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Composition du node
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
 * Construction de l'amorce de requête (entête SOAP){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_urlConnexion	URL du WSDL connexion {}
 * @param i_methodName	Nom de la méthode SOAP appelée {}
 * @param i_endPoint	EndPoint si différent de celui spécifié dans le WSDL {}
 * @param i_nameSpace	schéma namespace si différent de celui spécifié dans le WSDL {}
 * @param i_xmlParameters	Paramètres sous forme XML {}
 */
 public void setHeader (
	String i_urlConnexion,
	String i_methodName,
	String i_endPoint,
	String i_nameSpace,
	Node i_xmlParameters
	)throws Exception{
	if(i_endPoint==null)i_endPoint = null;
	if(i_nameSpace==null)i_nameSpace = null;setHeader(i_urlConnexion,i_methodName,i_endPoint,i_nameSpace,i_xmlParameters,null,null);
}
/**
 * Construction de l'amorce de requête (entête SOAP){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_urlConnexion	URL du WSDL connexion {}
 * @param i_methodName	Nom de la méthode SOAP appelée {}
 * @param i_endPoint	EndPoint si différent de celui spécifié dans le WSDL {}
 * @param i_nameSpace	schéma namespace si différent de celui spécifié dans le WSDL {}
 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres {}
 */
 public void setHeader (
	String i_urlConnexion,
	String i_methodName,
	String i_endPoint,
	String i_nameSpace,
	String i_xmlParametersFile
	)throws Exception{
	if(i_endPoint==null)i_endPoint = null;
	if(i_nameSpace==null)i_nameSpace = null;setHeader(i_urlConnexion,i_methodName,i_endPoint,i_nameSpace,null,i_xmlParametersFile,null);
}
/**
 * Construction de l'amorce de requête (entête SOAP){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_urlConnexion	URL du WSDL connexion {}
 * @param i_methodName	Nom de la méthode SOAP appelée {}
 * @param i_endPoint	EndPoint si différent de celui spécifié dans le WSDL {}
 * @param i_nameSpace	schéma namespace si différent de celui spécifié dans le WSDL {}
 * @param i_xmlParametersMap	Paramètres sous forme de Map {}
 */
 public void setHeader (
	String i_urlConnexion,
	String i_methodName,
	String i_endPoint,
	String i_nameSpace,
	Map<String,Object> i_xmlParametersMap
	)throws Exception{
	if(i_endPoint==null)i_endPoint = null;
	if(i_nameSpace==null)i_nameSpace = null;setHeader(i_urlConnexion,i_methodName,i_endPoint,i_nameSpace,null,null,i_xmlParametersMap);
}
 
/**
 * Construction de l'amorce de requête (entête SOAP){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_urlConnexion	URL du WSDL connexion {}
 * @param i_methodName	Nom de la méthode SOAP appelée {}
 * @param i_xmlParameters	Paramètres sous forme XML {}
 */
 public void setHeader (
	String i_urlConnexion,
	String i_methodName,
	Node i_xmlParameters
	)throws Exception{
setHeader(i_urlConnexion,i_methodName,"null","null",i_xmlParameters,null,null);
} 

 
/**
 * Construction de l'amorce de requête (entête SOAP){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_urlConnexion	URL du WSDL connexion {}
 * @param i_methodName	Nom de la méthode SOAP appelée {}
 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres {}
 */
 public void setHeader (
	String i_urlConnexion,
	String i_methodName,
	String i_xmlParametersFile
	)throws Exception{
setHeader(i_urlConnexion,i_methodName,"null","null",null,i_xmlParametersFile,null);
} 

 
/**
 * Construction de l'amorce de requête (entête SOAP){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_urlConnexion	URL du WSDL connexion {}
 * @param i_methodName	Nom de la méthode SOAP appelée {}
 * @param i_xmlParametersMap	Paramètres sous forme de Map {}
 */
 public void setHeader (
	String i_urlConnexion,
	String i_methodName,
	Map<String,Object> i_xmlParametersMap
	)throws Exception{
setHeader(i_urlConnexion,i_methodName,"null","null",null,null,i_xmlParametersMap);
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

