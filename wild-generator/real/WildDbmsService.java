
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

// Dépendances, bibliothèques JAVA par exemple.
import java.util.HashMap;
import java.util.List;


/**
 * Code généré automatiquement par l'outil Wild
 * Client d'un service fournisseur d'un accès base de données
 */
public abstract class WildDbmsService extends WildService{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildDbmsService(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected String amoJdbc; // Amorce JDBC à compléter

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
protected void WILD_initialize_WildDbmsService(
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

public WildDbmsService(
	WildObject i_WILD_dObject,
	String i_clientId,
	String i_clientPwd,
	String i_hostName,
	String i_hostIp
) throws Exception{
	if(clientId==null)clientId="null";
	if(clientPwd==null)clientPwd="null";
	this.WILD_initialize_WildDbmsService(i_WILD_dObject,i_clientId,i_clientPwd,i_hostName,i_hostIp);
}

// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Constructeur, appelle nécessairement WILD_initialize()
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_hostName	Nom de l'hôte{}
 * @param i_hostIp	IP de l'hôte{}
 */

public WildDbmsService(
	WildObject i_WILD_dObject,
	String i_hostName,
	String i_hostIp
) throws Exception{
	this.WILD_initialize_WildDbmsService(i_WILD_dObject,"null","null",i_hostName,i_hostIp);
}

/*	### NOUVELLE METHODE ###
	Méthode : loadTo - Charge un jeu de données (sous forme de Map) vers une table. Crée la table si elle n'existe pas.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Charge un jeu de données (sous forme de Map) vers une table. Crée la table si elle n'existe pas.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_mapToWrite	La source est une Map{}
 * @param i_outputSchema	Schéma d'export des données{}
 * @param i_tabSchema	Table d'export des données{}
 * @param i_appender	A true, écrit à la suite de la table si elle existe, à false écrase la liste{}
 */
public void loadTo (
	Map<Integer,Map<String, Object>> i_mapToWrite,
	String i_outputSchema,
	String i_tabSchema,
	Boolean i_appender
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Initialisation des connexions
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 20, Vérification de l'existence des éléments en base de données et création éventuelle (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 100, Ecriture des données (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 20, Ecriture des logs (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 10, Configuration des connexions
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"loadTo_output1" : Nombre de lignes écrites (Integer)
	//	this.WILD_setOutput("loadTo_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"loadTo_output2" : Nombre d'erreurs éventuelles (Integer)
	//	this.WILD_setOutput("loadTo_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"loadTo_output3" : Map des erreurs récupérées (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("loadTo_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
 
/**
 * Charge un jeu de données (sous forme de Map) vers une table. Crée la table si elle n'existe pas.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_mapToWrite	La source est une Map {}
 * @param i_outputSchema	Schéma d'export des données {}
 * @param i_tabSchema	Table d'export des données {}
 */
 public void loadTo (
	Map<Integer,Map<String, Object>> i_mapToWrite,
	String i_outputSchema,
	String i_tabSchema
	)throws Exception{
loadTo(i_mapToWrite,i_outputSchema,i_tabSchema,true);
} 

}

