
package fr.wild.real;
import static fr.wild.common.IoCommons.*;


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
	protected String pathToFile; // Chemin vers le fichier{}
	protected String mimeEncoding; // Encodage du fichier{}
	protected HashMap<String, String> nameSpaces; // Liste d'URL d'espaces de nommage{}
	protected String versionXml; // Code de version Xml{}
	protected String defaultNameSpace; // URL d'un espace de nommage par défaut{}

// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***
	/* DVP_resultsFromProcess : stocke les résultats des processus d'exécution de tests, avec les index "Nom de l'objet de test" (WildXslFile...), "Numéro du test".
	 * Les résultats sont accessibles par les méthodes DVP_getResult_*() etDVP_getLastResult_*()  
	 */
	private Map<String,Map<Integer,Object>> DVP_resultsFromProcess ;  


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

//		// Amorce de la classe
		// Initialisation de la classe d'objet selon le schéma Wild
		WILD_dObject = i_WILD_dObject ;
		WILD_initialize_WildFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,null,null,null);
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
			super.commit();
			NodeList i_fileNodeList = xml_getFileAsNode(this.getPath());
			if(i_nameSpaces == null)fileNodeList = i_fileNodeList.item(0).getChildNodes();
			for(Integer i = 0 ; i < fileNodeList.getLength() ; i++ ){
					NamedNodeMap nMap = fileNodeList.item(i).getAttributes();
					if(nMap!=null)for(Integer j = 0 ; j < nMap.getLength(); j++){
						String attrName = nMap.item(j).getNodeName();
						if(!attrName.startsWith("xmlns"))continue ;
						if(nameSpaces==null)nameSpaces = new HashMap<String, String>();
						String[] xmlns = attrName.split(":");
						if(xmlns.length>1)nameSpaces.put(xmlns[1],nMap.item(j).getNodeValue());
						else{
							this.defaultNameSpace = nMap.item(j).getNodeValue();
							nameSpaces.put("default",defaultNameSpace);
						}
					}
			}
			versionXml = this.getSchemaVersion();
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
	Méthode : fileContentAsString - Récupération du contenu comme une chaîne de caractère{ 
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
		WILD_toReturn = DVP_fileContentAsString();
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
		String schemas = xml_getFirstValue(fileNodeList.item(0),"@xsi:schemaLocation");
		if(schemas != null)WILD_toReturn = Arrays.asList(schemas.split(" "));		
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
		WILD_toReturn = this.nameSpaces.get(i_newXmlns);
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
		if(versionXml != null) WILD_toReturn = versionXml ;
		else {
			BufferedReader ir = new BufferedReader(new InputStreamReader(DVP_getStream()));
			String line;
			while ((line = ir.readLine()) != null) if (line.contains("version=\"")&&!line.endsWith("version=\""))
				WILD_toReturn = line.split("version=\"")[1].split("\"")[0];
		}
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
		commit();
		if(i_forProcess==null&&i_forProcessPath!=null) i_forProcess = new WildXqueryFile(this.WILD_dObject,  WILD_convertPath_choseFile(i_forProcessPath));
//	//	//	Etape	"2" : poids relatif de 10, Exécution de l'opération
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		i_fileOutput = WILD_convertPath(i_fileOutput);
		WILD_toReturn = i_forProcess.process(i_fileOutput, this);
//	//	//	Etape	"3" : poids relatif de 10, Récupération des résultats
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		DVP_addToResults("WildXqueryFile",i_forProcess.DVP_getResult());
//	//	//	Output	"queryWith_output1" : Liste des erreurs rencontrées (HashMap<String, Object>)
		this.WILD_setOutput("queryWith_output1", i_forProcess.DVP_getLastResultAsList()); // Ne pas modifier

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
	Méthode : setSchemaDefinition - Modifie le lien de définition du schéma (xmlns){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Modifie le lien de définition du schéma (xmlns){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_newUrl	Nouveau chemin vers un espace de nommage{}
 * @param i_newUrls	Nouvelle liste de chemins vers des espaces de nommage{}
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
		String xsdUrlValue = ""; // URL considérée pour le schéma
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(i_newUrls==null&&i_newUrl!=null)	xsdUrlValue = i_newUrl ;
		else if(i_newUrls!=null&&i_newUrl==null)for(String urlNew:i_newUrls)xsdUrlValue += urlNew+ " ";
		else if(i_newUrls!=null&&i_newUrl!=null){
			xsdUrlValue = i_newUrl;
			for(String urlNew:i_newUrls)xsdUrlValue += " " + urlNew;
		}
		xsdUrlValue = xsdUrlValue.trim();
//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(xsdUrlValue!=null&&xsdUrlValue.length()!=0)for(Integer i = 0 ; i < fileNodeList.getLength() ; i++ ){
			NamedNodeMap nMap = fileNodeList.item(i).getAttributes();
			if(nMap!=null)for(Integer j = 0 ; j < nMap.getLength(); j++){
				Node nUpdate = nMap.item(j);
				if(!nUpdate.getNodeName().equals("xsi:schemaLocation"))continue ;
				nUpdate.setTextContent(xsdUrlValue);
			}
		}
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
 * Modifie le lien de définition du schéma (xmlns){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_newUrl	Nouveau chemin vers un espace de nommage {}
 */
 public void setSchemaDefinition (
	String i_newUrl
	)throws Exception{setSchemaDefinition(i_newUrl,null);
}
/**
 * Modifie le lien de définition du schéma (xmlns){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_newUrls	Nouvelle liste de chemins vers des espaces de nommage {}
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
	// Inclusion du préfixe dans la recherche
		String searchXmlns = (i_newXmlns==null)?"xmlns":"xmlns:"+i_newXmlns;
//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(i_newUrl!=null&&i_newUrl.length()!=0)for(Integer i = 0 ; i < fileNodeList.getLength() ; i++ ){
			NamedNodeMap nMap = fileNodeList.item(i).getAttributes();
			if(nMap!=null)for(Integer j = 0 ; j < nMap.getLength(); j++){
				Node nUpdate = nMap.item(j);
				if(!nUpdate.getNodeName().equals(searchXmlns))continue ;
				nUpdate.setTextContent(i_newUrl);
			}
		}
//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(i_newXmlns == null ){
			i_newXmlns = "default" ;
			this.defaultNameSpace = i_newUrl ;
		}
		if(this.nameSpaces==null)this.nameSpaces = new HashMap<String,String>();
		this.nameSpaces.put(i_newXmlns, i_newUrl);
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
		this.versionXml = i_newVersion ;
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
		commit();
		if(i_forProcess==null&&i_forProcessPath!=null) i_forProcess = new WildXslFile(this.WILD_dObject,  WILD_convertPath_choseFile(i_forProcessPath));

//	//	//	Etape	"2" : poids relatif de 10, Exécution de l'opération
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		i_fileOutput = WILD_convertPath(i_fileOutput);
		WILD_toReturn = i_forProcess.transform(i_fileOutput, this);

//	//	//	Etape	"3" : poids relatif de 10, Récupération des résultats
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		DVP_addToResults("WildXslFile", i_forProcess.DVP_getResult());
		if(i_forProcessPath!=null&&i_forProcess.DVP_isURL()) i_forProcess.delete();
//	//	//	Output	"transformBy_output1" : Nom du fichier de sortie (String)
		this.WILD_setOutput("transformBy_output1",i_fileOutput); // Ne pas modifier

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

//	//	//	Etape	"3" : poids relatif de 50, Ecriture dufichier (++)
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
	Méthode : updateNode - Modification d'un nœud{ 
 }

*/

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
		commit();
		if(i_forProcess==null&&i_forProcessPath!=null) i_forProcess = new WildXsdFile(this.WILD_dObject,  WILD_convertPath_choseFile(i_forProcessPath));
//	//	//	Etape	"2" : poids relatif de 10, Exécution de l'opération
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		i_forProcess.validate(this);
//	//	//	Etape	"3" : poids relatif de 10, Récupération des résultats
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		List<HashMap<String, Object>> listErrors = i_forProcess.DVP_getErrors() ;
		DVP_addToResults("WildXsdFile",listErrors);
		if(listErrors == null)WILD_toReturn = true ;
		else WILD_toReturn = listErrors.isEmpty() ;
		i_forProcess.delete();
//	//	//	Output	"validateBy_output1" : Liste des erreurs rencontrées (List<String>)
		this.WILD_setOutput("validateBy_output1",listErrors); // Ne pas modifier

//	//	//	Output	"validateBy_output2" : Il existe des éléments à retenir (Boolean)
		this.WILD_setOutput("validateBy_output2",!WILD_toReturn); // Ne pas modifier

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
		for(String xmlnss:this.nameSpaces.keySet()){
			String i_newUrl = nameSpaces.get(xmlnss);
			String searchXmlns = (xmlnss.equals("default"))?"xmlns":"xmlns:"+xmlnss;
			if(i_newUrl!=null&&i_newUrl.length()!=0)for(Integer i = 0 ; i < fileNodeList.getLength() ; i++ ){
				NamedNodeMap nMap = fileNodeList.item(i).getAttributes();
				if(nMap!=null)nMap.removeNamedItem(searchXmlns);
			}
		}
//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		nameSpaces = null ;
		this.defaultNameSpace = null ;
	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}
/**
 * Ajout d'une entrée dans les archives de tests effectués sur le fichier
 * @param indexName
 * @param resultToStore
 */
private void DVP_addToResults(String indexName, Object resultToStore){
	if(DVP_resultsFromProcess==null)DVP_resultsFromProcess = new HashMap<>();
	HashMap<Integer, Object> mapResults  = new HashMap<>();
	mapResults.put(DVP_getMaxForIndex(indexName), resultToStore);		
	DVP_resultsFromProcess.put(indexName, mapResults);
}
/**
 * Récupération du prochain identifiant à utiliser, dans la liste des archives de tests
 * @param indexName
 * @return
 */
private Integer DVP_getMaxForIndex(String indexName){
	Integer max = 0  ;	
	if(this.DVP_resultsFromProcess.get(indexName)!=null)
		for(Integer ki : DVP_resultsFromProcess.get(indexName).keySet())
				if(ki>max)max = ki;
	return max + 1 ;
}
/**
 * Getter pour résultat du process d'un test XSD
 * @param testNb Numéro d'ordre du test
 * @return
 */
@SuppressWarnings("unchecked")
public <T> T DVP_getResult_xsd(Integer testNb){
	if(this.DVP_resultsFromProcess.get("WildXsdFile")!=null) 
		return (T)  this.DVP_resultsFromProcess.get("WildXsdFile").get(testNb);
	return null;
}
/**
 * Getter pour résultat du process du dernier test XSD réalisé sur le fichier
 * @return
 */
public <T> T DVP_getLastResult_xsd(){
	return DVP_getResult_xsd(DVP_getMaxForIndex("WildXsdFile")-1);
}

/**
 * Getter pour résultat du process d'un test XSL
 * @param testNb Numéro d'ordre du test
 * @return
 */
@SuppressWarnings("unchecked")
public <T> T DVP_getResult_xsl(Integer testNb){
	if(this.DVP_resultsFromProcess.get("WildXsdFile")!=null) 
		return (T)  this.DVP_resultsFromProcess.get("WildXslFile").get(testNb);
	return null;
}
/**
 * Getter pour résultat du process du dernier test XSL réalisé sur le fichier
 * @return
 */
public <T> T DVP_getLastResult_xsl(){
	return DVP_getResult_xsd(DVP_getMaxForIndex("WildXslFile")-1);
}
/**
 * Getter pour résultat du process d'un test Xquery
 * @param testNb Numéro d'ordre du test
 * @return
 */
@SuppressWarnings("unchecked")
public <T> T DVP_getResult_xquery(Integer testNb){
	if(this.DVP_resultsFromProcess.get("WildXqueryFile")!=null) 
		return (T)  this.DVP_resultsFromProcess.get("WildXsdFile").get(testNb);
	return null;
}
/**
 * Getter pour résultat du process du dernier test Xquery réalisé sur le fichier
 * @return
 */
public <T> T DVP_getLastResult_xquery(Integer testNb){
	return DVP_getResult_xsd(DVP_getMaxForIndex("WildXqueryFile")-1);
}
}

