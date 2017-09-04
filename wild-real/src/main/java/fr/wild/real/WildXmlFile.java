
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;
import static fr.wild.common.IoCommons.*;
// Dépendances, bibliothèques JAVA par exemple.
import java.util.HashMap;
import java.util.List;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Code généré automatiquement par l'outil Wild
 * Fichier standard XML
 */
public class WildXmlFile extends WildMarkUpFile{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildXmlFile(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation

// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	protected String pathToFile;	//Chemin vers le fichier{}
	protected String mimeEncoding;	//Encodage du fichier{}
	protected HashMap<String,String> nameSpaces;	//Liste d'URL d'espaces de nommage{}
	protected String versionXml;	//Code de version Xml{}
	protected String defaultNameSpace;	//URL d'un espace de nommage{}


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
 * @param i_defaultNameSpace	URL d'un espace de nommage{}
 */
protected void WILD_initialize_WildXmlFile(
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
	WILD_initialize_WildMarkUpFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,i_nameSpaces,i_versionXml,i_defaultNameSpace);
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

public WildXmlFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding
) throws Exception{
	if(mimeEncoding==null)mimeEncoding="UTF-8";
	this.WILD_initialize_WildXmlFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,null,null,null);
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

public WildXmlFile(
	WildObject i_WILD_dObject,
	String i_pathToFile
) throws Exception{
	this.WILD_initialize_WildXmlFile(i_WILD_dObject,i_pathToFile,"UTF-8",null,null,null);
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

public WildXmlFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding,
	HashMap<String,String> i_nameSpaces,
	String i_versionXml
) throws Exception{
	if(mimeEncoding==null)mimeEncoding="UTF-8";
	this.WILD_initialize_WildXmlFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,i_nameSpaces,i_versionXml,null);
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

public WildXmlFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	HashMap<String,String> i_nameSpaces,
	String i_versionXml
) throws Exception{
	this.WILD_initialize_WildXmlFile(i_WILD_dObject,i_pathToFile,"UTF-8",i_nameSpaces,i_versionXml,null);
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

public WildXmlFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding,
	String i_defaultNameSpace,
	String i_versionXml
) throws Exception{
	if(mimeEncoding==null)mimeEncoding="UTF-8";
	this.WILD_initialize_WildXmlFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,null,i_versionXml,i_defaultNameSpace);
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

public WildXmlFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_defaultNameSpace,
	String i_versionXml
) throws Exception{
	this.WILD_initialize_WildXmlFile(i_WILD_dObject,i_pathToFile,"UTF-8",null,i_versionXml,i_defaultNameSpace);
}

/*	### NOUVELLE METHODE ###
	Méthode : addNode - Ajout d'un nœud{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ajout d'un nœud{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_xpathRequest	xPath de localisation{}
 * @param i_nodeAsString	Contenu textuel du nœud{}
 * @param i_nodeName	Nom du nœud principal{}
 * @param i_nodeAsMap	Contenu du nœud sous forme de Map (clés comme noms d'attributs, sauf text(){}
 */
public void addNode (
	String i_xpathRequest,
	String i_nodeAsString,
	String i_nodeName,
	HashMap<String,Object> i_nodeAsMap
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		Node newChild = null ;
		Integer nListLength = 0 ;
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(i_nodeAsString==null && i_nodeAsMap!=null && i_nodeAsMap.size() > 0){
			i_nodeAsString = "<"+i_nodeName;
			for(String str:i_nodeAsMap.keySet())if(!str.equals("text()"))
				i_nodeAsString += " "+str+" = \""+cast_xmlEscape(i_nodeAsMap.get(str))+"\"";
			i_nodeAsString += (i_nodeAsMap.get("text()")==null)?
						"/>"
					:	">"+cast_xmlEscape(i_nodeAsMap.get("text()"))+"</"+i_nodeName+">";
			newChild = cast_String2Node(i_nodeAsString) ;
		}
		newChild = cast_String2Node(i_nodeAsString) ;
//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(newChild != null){
			XPathFactory xpf = XPathFactory.newInstance();
			XPathExpression expression = xpf.newXPath().compile(i_xpathRequest);
			NodeList nList = (NodeList) expression.evaluate(this.fileNodeList, XPathConstants.NODE);
			nListLength = (nList == null) ? 0 : nList.getLength();
			for(Integer i=0; i < nListLength ;i++)nList.item(i).appendChild(newChild);
		}
//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"addNode_output1" : Nombre de lignes affectées (Integer)
		this.WILD_setOutput("addNode_output1",nListLength); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

//LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
/**
* Ajout d'un nœud{ 
}
* Code généré automatiquement par l'outil Wild
* Méthode d'appel public non modifiable
* 
* @param i_xpathRequest	xPath de localisation {}
* @param i_nodeAsString	Contenu textuel du nœud {}
*/
public void addNode (
	String i_xpathRequest,
	String i_nodeAsString
	)throws Exception{addNode(i_xpathRequest,i_nodeAsString,null,null);
}
/**
* Ajout d'un nœud{ 
}
* Code généré automatiquement par l'outil Wild
* Méthode d'appel public non modifiable
* 
* @param i_xpathRequest	xPath de localisation {}
* @param i_nodeName	Nom du nœud principal {}
* @param i_nodeAsMap	Contenu du nœud sous forme de Map (clés comme noms d'attributs, sauf text() {}
*/
public void addNode (
	String i_xpathRequest,
	String i_nodeName,
	HashMap<String,Object> i_nodeAsMap
	)throws Exception{addNode(i_xpathRequest,null,i_nodeName,i_nodeAsMap);
}

/*	### NOUVELLE METHODE ###
	Méthode : updateNode - Modification d'un nœud{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Modification d'un nœud{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_xpathRequest	xPath de localisation{}
 * @param i_nodeAsString	Contenu textuel du nœud{}
 * @param i_nodeAsMap	Contenu du nœud sous forme de Map (clés{}
 */
public void updateNode (
	String i_xpathRequest,
	String i_nodeAsString,
	HashMap<String,Object> i_nodeAsMap
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
		if(i_nodeAsString==null && i_nodeAsMap!=null && i_nodeAsMap.size() > 0){
			i_nodeAsString = ">";
			for(String str:i_nodeAsMap.keySet())if(!str.equals("text()"))
				i_nodeAsString += " "+str+" = \""+cast_xmlEscape(i_nodeAsMap.get(str))+"\"";
			i_nodeAsString += (i_nodeAsMap.get("text()")==null)?
						"/>"
					:	">"+cast_xmlEscape(i_nodeAsMap.get("text()"));
		}
//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		XPathFactory xpf = XPathFactory.newInstance();
		XPathExpression expression = xpf.newXPath().compile(i_xpathRequest);
		NodeList nList = (NodeList) expression.evaluate(this.fileNodeList, XPathConstants.NODE);
		Integer nListLength = (nList == null) ? 0 : nList.getLength();
		for(Integer i=0; i < nListLength ;i++){
			String toWrite ;
			if(i_nodeAsString.startsWith("<")){
				toWrite = i_nodeAsString ;
			}else{
				String nodeName = nList.item(i).getNodeName();
				toWrite =  "<"+nodeName+i_nodeAsString;
				if(!toWrite.endsWith("/>"))toWrite += "</"+nodeName+">";
			}
			Node newChild = cast_String2Node(i_nodeAsString) ;
			nList.item(i).getParentNode().removeChild(nList.item(i));
			nList.item(i).getParentNode().appendChild(newChild);
		}
//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"updateNode_output1" : Nombre de nœuds affectés (Integer)
		this.WILD_setOutput("updateNode_output1",nListLength); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
/**
 * Modification d'un nœud{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_xpathRequest	xPath de localisation {}
 * @param i_nodeAsString	Contenu textuel du nœud {}
 */
 public void updateNode (
	String i_xpathRequest,
	String i_nodeAsString
	)throws Exception{updateNode(i_xpathRequest,i_nodeAsString,null);
}
/**
 * Modification d'un nœud{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_xpathRequest	xPath de localisation {}
 * @param i_nodeAsMap	Contenu du nœud sous forme de Map (clés {}
 */
 public void updateNode (
	String i_xpathRequest,
	HashMap<String,Object> i_nodeAsMap
	)throws Exception{updateNode(i_xpathRequest,null,i_nodeAsMap);
}
/*	### NOUVELLE METHODE ###
	Méthode : dropFirstNode - Suppression du premier nœud rencontré par xpath{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Suppression du premier nœud rencontré par xpath{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_xpathRequest	xPath à rechercher{}
 */
public void dropFirstNode (
	String i_xpathRequest
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
		XPathFactory xpf = XPathFactory.newInstance();
		XPathExpression expression = xpf.newXPath().compile(i_xpathRequest);
		NodeList nList = (NodeList) expression.evaluate(this.fileNodeList, XPathConstants.NODE);
		Integer nListLength = (nList == null) ? 0 : nList.getLength();
		for(Integer i=0; i < nListLength ;i++)nList.item(i).getParentNode().removeChild(nList.item(i));
		
		
		
//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"dropFirstNode_output1" : Nombre de lignes affectées (Integer)
		this.WILD_setOutput("dropFirstNode_output1",nListLength); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : getFirstNode - Récupération du premier nœud, par xpath{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération du premier nœud, par xpath{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_xpathRequest	xPath à rechercher{}
 * @return	{}
 */
public Node getFirstNode (
	String i_xpathRequest
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Node WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Résolution de la requête
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		WILD_toReturn = xml_getFirstNode(this.fileNodeList,i_xpathRequest);
//	//	//	Etape	"2" : poids relatif de 10, Cast des résultats
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
	Méthode : getFirstValue - Récupération de la première valeur, par xpath{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération de la première valeur, par xpath{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_xpathRequest	xPath à rechercher{}
 * @return	{}
 */
public String getFirstValue (
	String i_xpathRequest
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Résolution de la requête
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		WILD_toReturn = xml_getFirstValue(this.fileNodeList,i_xpathRequest);
//	//	//	Etape	"2" : poids relatif de 10, Cast des résultats
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
	Méthode : getNodes - Récupération d'une liste de nœuds, par xpath{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération d'une liste de nœuds, par xpath{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_xpathRequest	xPath à rechercher{}
 * @return	{}
 */
public HashMap<Integer,Node> getNodes (
	String i_xpathRequest
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<Integer,Node> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Résolution de la requête
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Cast des résultats
		this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		NodeList nL = xml_getNodes(this.fileNodeList, i_xpathRequest);
		if(nL!=null){
			WILD_toReturn = new HashMap<Integer, Node>();
			for(Integer i = 0 ; i < nL.getLength() ; i++)WILD_toReturn.put(i+1, nL.item(i));
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
	Méthode : getValues - Récupération d'une liste de valeur, par xpath{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération d'une liste de valeur, par xpath{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_xpathRequest	xPath à rechercher{}
 * @return	{}
 */
public HashMap<Integer,String> getValues (
	String i_xpathRequest
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<Integer,String> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Résolution de la requête
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Cast des résultats
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		NodeList nL = xml_getNodes(this.fileNodeList, i_xpathRequest);
		if(nL!=null){
			WILD_toReturn = new HashMap<Integer, String>();
			for(Integer i = 0 ; i < nL.getLength() ; i++)WILD_toReturn.put(i+1, xml_getText(nL.item(i)));
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

//	//	//	Etape	"5" : poids relatif de 50, Ecriture dufichier (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		super.commit();
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

}

