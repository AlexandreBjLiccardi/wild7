
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

// Dépendances, bibliothèques JAVA par exemple.
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import fr.wild.orchestra.WildObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * Code généré automatiquement par l'outil Wild
 * Fichier structuré dit à balises ou en arborescence
 */
public abstract class WildMarkUpFile extends WildFile{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildMarkUpFile(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected String version; // code de version de schéma XML
	protected NodeList fileNodeList; // Contenu du fichier, sous forme de liste de nœuds
	protected String typeMarkUp; // Nom du MarkUp racine

// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	protected String pathToFile;	//Chemin vers le fichier{}
	protected String mimeEncoding;	//Encodage du fichier{}
	protected HashMap<String,String> nameSpaces;	//Liste d'URL d'espaces de nommage{}
	protected String versionXml;	//Code de version Xml{}
	protected String defaultNameSpace;	//URL d'un espace de nommage par défaut{}


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
 * @param i_nameSpaces	Liste d'URL d'espaces de nommage{}
 * @param i_versionXml	Code de version Xml{}
 * @param i_defaultNameSpace	URL d'un espace de nommage par défaut{}
 */
protected void WILD_initialize_WildMarkUpFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding,
	HashMap<String,String> i_nameSpaces,
	String i_versionXml,
	String i_defaultNameSpace
) throws Exception {

//	// Amorce de la classe
	// Initialisation de la classe d'objet selon le schéma Wild
	WILD_dObject = i_WILD_dObject ;
	WILD_initialize_WildFile(i_WILD_dObject/*** Paramètres à actualiser ***/);
	// Préparation des variables d'invocation (considérées comme champs globaux)
	this.pathToFile = i_pathToFile;
	this.mimeEncoding = i_mimeEncoding;
	this.nameSpaces = i_nameSpaces;
	this.versionXml = i_versionXml;
	this.defaultNameSpace = i_defaultNameSpace;

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
 */

public WildMarkUpFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding
) throws Exception{
	if(mimeEncoding==null)mimeEncoding="UTF-8";
	this.WILD_initialize_WildMarkUpFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,null,null,null);
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

public WildMarkUpFile(
	WildObject i_WILD_dObject,
	String i_pathToFile
) throws Exception{
	this.WILD_initialize_WildMarkUpFile(i_WILD_dObject,i_pathToFile,"UTF-8",null,null,null);
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

public WildMarkUpFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding,
	HashMap<String,String> i_nameSpaces,
	String i_versionXml
) throws Exception{
	if(mimeEncoding==null)mimeEncoding="UTF-8";
	this.WILD_initialize_WildMarkUpFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,i_nameSpaces,i_versionXml,null);
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

public WildMarkUpFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	HashMap<String,String> i_nameSpaces,
	String i_versionXml
) throws Exception{
	this.WILD_initialize_WildMarkUpFile(i_WILD_dObject,i_pathToFile,"UTF-8",i_nameSpaces,i_versionXml,null);
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
 * @param i_defaultNameSpace	URL d'un espace de nommage par défaut{}
 * @param i_versionXml	Code de version Xml{}
 */

public WildMarkUpFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding,
	String i_defaultNameSpace,
	String i_versionXml
) throws Exception{
	if(mimeEncoding==null)mimeEncoding="UTF-8";
	this.WILD_initialize_WildMarkUpFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,null,i_versionXml,i_defaultNameSpace);
}

// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Constructeur, appelle nécessairement WILD_initialize()
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_pathToFile	Chemin vers le fichier{}
 * @param i_defaultNameSpace	URL d'un espace de nommage par défaut{}
 * @param i_versionXml	Code de version Xml{}
 */

public WildMarkUpFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_defaultNameSpace,
	String i_versionXml
) throws Exception{
	this.WILD_initialize_WildMarkUpFile(i_WILD_dObject,i_pathToFile,"UTF-8",null,i_versionXml,i_defaultNameSpace);
}

/*	### NOUVELLE METHODE ###
	Méthode : fileContentAsString - Récupération du contenu comme une chaîne de caractère. Pour l'instant, ne retourne que le contenu du fichier. A terme, recomposera le fichier à partir des opérations exécutées avant le commit.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération du contenu comme une chaîne de caractère. Pour l'instant, ne retourne que le contenu du fichier. A terme, recomposera le fichier à partir des opérations exécutées avant le commit.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public String fileContentAsString ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Parcours du fichier
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Compilation des résultats
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
	Méthode : getSchemaDefinition - Renvoie le lien de définition du schéma (xsiSchemaLocation){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Renvoie le lien de définition du schéma (xsiSchemaLocation){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public List<String> getSchemaDefinition ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	List<String> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération de la variable
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
	Méthode : getNamespace - Renvoie le lien de définition du schéma (xmlns), et éventuellement des espaces de nommage préfixés{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Renvoie le lien de définition du schéma (xmlns), et éventuellement des espaces de nommage préfixés{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_newXmlns	préfixe de l'espace de nommage{}
 * @return	{}
 */
public String getNamespace (
	String i_newXmlns
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération de la variable
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
 * Renvoie le lien de définition du schéma (xmlns), et éventuellement des espaces de nommage préfixés{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @return	Lien de définition de l'espace de nommage (String) {}
 */
 public String getNamespace ()throws Exception{
return getNamespace("default");
} 

/*	### NOUVELLE METHODE ###
	Méthode : getSchemaVersion - Renvoie l'identifiant de version du schéma{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Renvoie l'identifiant de version du schéma{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public String getSchemaVersion ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération de la variable
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
	Méthode : queryWith - Interrogation par un fichier xQuery{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Interrogation par un fichier xQuery{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_fileOutput	Fichier de sortie{}
 * @param i_forProcess	Fichier à processer{}
 * @param i_forProcessPath	Fichier à processer{}
 * @return	{}
 */
public String queryWith (
	String i_fileOutput,
	WildXqueryFile i_forProcess,
	String i_forProcessPath
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Sélection du WildFile
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Exécution de l'opération
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Récupération des résultats
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"queryWith_output1" : Liste des erreurs rencontrées (HashMap<String, Object>)
	//	this.WILD_setOutput("queryWith_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * Interrogation par un fichier xQuery{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fileOutput	Fichier de sortie {}
 * @param i_forProcess	Fichier à processer {}
 * @return	Nom du fichier de sortie (String) {}
 */
 public String queryWith (
	String i_fileOutput,
	WildXqueryFile i_forProcess
	)throws Exception{return queryWith(i_fileOutput,i_forProcess,null);
}
/**
 * Interrogation par un fichier xQuery{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fileOutput	Fichier de sortie {}
 * @param i_forProcessPath	Fichier à processer {}
 * @return	Nom du fichier de sortie (String) {}
 */
 public String queryWith (
	String i_fileOutput,
	String i_forProcessPath
	)throws Exception{return queryWith(i_fileOutput,null,i_forProcessPath);
}
/*	### NOUVELLE METHODE ###
	Méthode : setSchemaDefinition - Modifie le lien de définition du schéma (xsiSchemaLocation){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Modifie le lien de définition du schéma (xsiSchemaLocation){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_newUrl	Nouveau chemin vers un schema XSD{}
 * @param i_newUrls	Nouvelle liste de chemins vers des schemas XSD{}
 */
public void setSchemaDefinition (
	String i_newUrl,
	List<String> i_newUrls
	)throws Exception{
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

//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
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
 * Modifie le lien de définition du schéma (xsiSchemaLocation){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_newUrl	Nouveau chemin vers un schema XSD {}
 */
 public void setSchemaDefinition (
	String i_newUrl
	)throws Exception{setSchemaDefinition(i_newUrl,null);
}
/**
 * Modifie le lien de définition du schéma (xsiSchemaLocation){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_newUrls	Nouvelle liste de chemins vers des schemas XSD {}
 */
 public void setSchemaDefinition (
	List<String> i_newUrls
	)throws Exception{setSchemaDefinition(null,i_newUrls);
}
/*	### NOUVELLE METHODE ###
	Méthode : setNamespace - Modifie le lien de définition de l'espace de nommage, éventuellement préfixé{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Modifie le lien de définition de l'espace de nommage, éventuellement préfixé{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_newUrl	Nouveau chemin vers un espace de nommage par défaut{}
 * @param i_newXmlns	préfixe de l'espace de nommage{}
 */
public void setNamespace (
	String i_newUrl,
	String i_newXmlns
	)throws Exception{
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

//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
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
 * Modifie le lien de définition de l'espace de nommage, éventuellement préfixé{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_newUrl	Nouveau chemin vers un espace de nommage par défaut {}
 */
 public void setNamespace (
	String i_newUrl
	)throws Exception{setNamespace(i_newUrl,null);
}
/*	### NOUVELLE METHODE ###
	Méthode : setSchemaVersion - Modifie l'identifiant de version du schéma{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Modifie l'identifiant de version du schéma{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_newVersion	Nouvel identifiant de version XML{}
 */
public void setSchemaVersion (
	String i_newVersion
	)throws Exception{
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

//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
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
	Méthode : transformBy - Transformation par un fichier XSLT{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Transformation par un fichier XSLT{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_fileOutput	Fichier de sortie{}
 * @param i_forProcess	Fichier à processer{}
 * @param i_forProcessPath	Fichier à processer{}
 * @return	{}
 */
public String transformBy (
	String i_fileOutput,
	WildXslFile i_forProcess,
	String i_forProcessPath
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Sélection du WildFile
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Exécution de l'opération
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Récupération des résultats
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"transformBy_output1" : Nom du fichier de sortie (String)
	//	this.WILD_setOutput("transformBy_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * Transformation par un fichier XSLT{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fileOutput	Fichier de sortie {}
 * @param i_forProcess	Fichier à processer {}
 * @return	Nom du fichier de sortie (String) {}
 */
 public String transformBy (
	String i_fileOutput,
	WildXslFile i_forProcess
	)throws Exception{return transformBy(i_fileOutput,i_forProcess,null);
}
/**
 * Transformation par un fichier XSLT{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fileOutput	Fichier de sortie {}
 * @param i_forProcessPath	Fichier à processer {}
 * @return	Nom du fichier de sortie (String) {}
 */
 public String transformBy (
	String i_fileOutput,
	String i_forProcessPath
	)throws Exception{return transformBy(i_fileOutput,null,i_forProcessPath);
}
/*	### NOUVELLE METHODE ###
	Méthode : uncomment - Suppression des commentaires dans le fichier{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Suppression des commentaires dans le fichier{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void uncomment ()throws Exception{
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

//	//	//	Etape	"2" : poids relatif de 20, Vidage du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 50, Ecriture du fichier (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"uncomment_output1" : Nombre de lignes affectées (Integer)
	//	this.WILD_setOutput("uncomment_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : validateBy - Validation par un fichier XSD{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Validation par un fichier XSD{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_forProcess	Fichier à processer{}
 * @param i_forProcessPath	Fichier à processer{}
 * @return	{}
 */
public Boolean validateBy (
	WildXsdFile i_forProcess,
	String i_forProcessPath
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Boolean WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Sélection du WildFile
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Exécution de l'opération
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Récupération des résultats
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"validateBy_output1" : Liste des erreurs rencontrées (List<String>)
	//	this.WILD_setOutput("validateBy_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"validateBy_output2" : Il existe des éléments à retenir (Boolean)
	//	this.WILD_setOutput("validateBy_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * Validation par un fichier XSD{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_forProcess	Fichier à processer {}
 * @return	TRUE si aucune erreur (Boolean) {}
 */
 public Boolean validateBy (
	WildXsdFile i_forProcess
	)throws Exception{return validateBy(i_forProcess,null);
}
/**
 * Validation par un fichier XSD{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_forProcessPath	Fichier à processer {}
 * @return	TRUE si aucune erreur (Boolean) {}
 */
 public Boolean validateBy (
	String i_forProcessPath
	)throws Exception{return validateBy(null,i_forProcessPath);
}
/*	### NOUVELLE METHODE ###
	Méthode : dropAllNamespaces - Suppression de tous les espaces de nommage, y compris dans le fichier lui-même (suppression des attributs dans l'en-tête de fichier).{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Suppression de tous les espaces de nommage, y compris dans le fichier lui-même (suppression des attributs dans l'en-tête de fichier).{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void dropAllNamespaces ()throws Exception{
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

//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
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

