
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

// Dépendances, bibliothèques JAVA par exemple.
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Node;


/**
 * Code généré automatiquement par l'outil Wild
 * Webservice spécifique à SEEE
 */
public class WildSeeeService extends WildWebService{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildSeeeService(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation

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
protected void WILD_initialize_WildSeeeService(
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

public WildSeeeService(
	WildObject i_WILD_dObject,
	Node i_parametersNode
) throws Exception{
	this.WILD_initialize_WildSeeeService(i_WILD_dObject,i_parametersNode,null,null);
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

public WildSeeeService(
	WildObject i_WILD_dObject,
	String i_parametersXmlFile
) throws Exception{
	this.WILD_initialize_WildSeeeService(i_WILD_dObject,null,i_parametersXmlFile,null);
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

public WildSeeeService(
	WildObject i_WILD_dObject,
	Map<String,Object> i_parametersMap
) throws Exception{
	this.WILD_initialize_WildSeeeService(i_WILD_dObject,null,null,i_parametersMap);
}

/*	### NOUVELLE METHODE ###
	Méthode : getTest - C9F1 : Test générique d'un service de calcul d'indicateur SEEE, configurations en ligne{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * C9F1 : Test générique d'un service de calcul d'indicateur SEEE, configurations en ligne{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void getTest ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Etape générique
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
	Méthode : getTestAsynch - C9F2 : Test spécifique d'un service de calcul d'indicateur SEEE, calcul asynchrone{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * C9F2 : Test spécifique d'un service de calcul d'indicateur SEEE, calcul asynchrone{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void getTestAsynch ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Etape générique
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
	Méthode : getTestRandomFile - C9F3 : Test spécifique d'un service de calcul d'indicateur SEEE, envoi d'un fichier lourd à traiter{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * C9F3 : Test spécifique d'un service de calcul d'indicateur SEEE, envoi d'un fichier lourd à traiter{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void getTestRandomFile ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Etape générique
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

