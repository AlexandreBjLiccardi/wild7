
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

// Dépendances, bibliothèques JAVA par exemple.
import java.io.File;
import java.util.HashMap;
import java.util.List;


/**
 * Code généré automatiquement par l'outil Wild
 * Fichier contenant des données géographiques / géométriques et possiblement ses attributs
 */
public class WildSigFile extends WildFile{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildSigFile(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected String crsAsWkt; // Référentiel de projection de coordonnées, en WKT
	protected String repWork; // Chemin relatif de l'ensemble des fichiers de travail
	protected String subWork; // Nom SHP commun à l'ensemble des fichiers de travail
	protected HashMap<Integer, Geometry> geometryList; // Liste de tous les objets contenus
	protected HashMap<Integer, HashMap<String, Object>> attributeList; // Carte des attributs des objets contenus dans le shape
	protected HashMap<String, String> typeList; // Carte des attributs des objets contenus dans le shape

// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	protected String pathToFile;	//Chemin vers le fichier{}
	protected Integer srid;	//Système de référence géographique{}


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
 * @param i_srid	Système de référence géographique{}
 */
protected void WILD_initialize_WildSigFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	Integer i_srid
) throws Exception {

//	// Amorce de la classe
	// Initialisation de la classe d'objet selon le schéma Wild
	WILD_dObject = i_WILD_dObject ;
	WILD_initialize_WildFile(i_WILD_dObject/*** Paramètres à actualiser ***/);
	// Préparation des variables d'invocation (considérées comme champs globaux)
	this.pathToFile = i_pathToFile;
	this.srid = i_srid;

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
 * @param i_srid	Système de référence géographique{}
 */

public WildSigFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	Integer i_srid
) throws Exception{
	if(srid==null)srid=4326;
	this.WILD_initialize_WildSigFile(i_WILD_dObject,i_pathToFile,i_srid);
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

public WildSigFile(
	WildObject i_WILD_dObject,
	String i_pathToFile
) throws Exception{
	this.WILD_initialize_WildSigFile(i_WILD_dObject,i_pathToFile,4326);
}

/*	### NOUVELLE METHODE ###
	Méthode : checkAttribute_fullness - Test de complétude (tous les éléments du référentiel ont au moins un objet géographique){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Test de complétude (tous les éléments du référentiel ont au moins un objet géographique){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_attributeToCheck	Nom de l'attribut à vérifier{}
 * @param i_listOfReferences	Eléments à comparer{}
 * @return	{}
 */
public String checkAttribute_fullness (
	String i_attributeToCheck,
	List<Object> i_listOfReferences
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Construction des objets
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Comparaison des listes
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Compilation des résultats
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"checkAttribute_fullness_output1" : Liste des erreurs rencontrées (List<String>)
	//	this.WILD_setOutput("checkAttribute_fullness_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkAttribute_fullness_output2" : Il existe des éléments à retenir (Boolean)
	//	this.WILD_setOutput("checkAttribute_fullness_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkAttribute_fullness_output3" : Chemin d'un fichier de sortie contenant la liste des erreurs (String)
	//	this.WILD_setOutput("checkAttribute_fullness_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : checkAttribute_isWithin - Test de cohérence (tous les objets géographiques sont représentés dans le référentiel){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Test de cohérence (tous les objets géographiques sont représentés dans le référentiel){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_attributeToCheck	Nom de l'attribut à vérifier{}
 * @param i_listOfReferences	Eléments à comparer{}
 * @return	{}
 */
public String checkAttribute_isWithin (
	String i_attributeToCheck,
	List<Object> i_listOfReferences
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Construction des objets
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Comparaison des listes
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Compilation des résultats
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"checkAttribute_isWithin_output1" : Liste des erreurs rencontrées (List<String>)
	//	this.WILD_setOutput("checkAttribute_isWithin_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkAttribute_isWithin_output2" : Il existe des éléments à retenir (Boolean)
	//	this.WILD_setOutput("checkAttribute_isWithin_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkAttribute_isWithin_output3" : Chemin d'un fichier de sortie contenant la liste des erreurs (String)
	//	this.WILD_setOutput("checkAttribute_isWithin_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : checkCrsFromRef - Vérification de la validité du système de projection{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Vérification de la validité du système de projection{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_crsToCheck	Définition du CRS de comparaison{}
 * @return	{}
 */
public Boolean checkCrsFromRef (
	String i_crsToCheck
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Boolean WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Opérateur de comparaison
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"checkCrsFromRef_output1" : Liste des erreurs rencontrées (List<String>)
	//	this.WILD_setOutput("checkCrsFromRef_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkCrsFromRef_output2" : Il existe des éléments à retenir (Boolean)
	//	this.WILD_setOutput("checkCrsFromRef_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : checkType - Vérification de la validité du type d'enregistrements{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Vérification de la validité du type d'enregistrements{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_typeToType	Nom du type de comparaison{}
 * @return	{}
 */
public Boolean checkType (
	String i_typeToType
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Boolean WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Opérateur de comparaison
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"checkType_output1" : Liste des erreurs rencontrées (List<String>)
	//	this.WILD_setOutput("checkType_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkType_output2" : Il existe des éléments à retenir (Boolean)
	//	this.WILD_setOutput("checkType_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : checkValidity - Validation WKT des géométries transmises{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Validation WKT des géométries transmises{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public Boolean checkValidity ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Boolean WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Opérateur de comparaison
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"checkValidity_output1" : Liste des erreurs rencontrées (List<String>)
	//	this.WILD_setOutput("checkValidity_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkValidity_output2" : Il existe des éléments à retenir (Boolean)
	//	this.WILD_setOutput("checkValidity_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : compileMetadatas - Récupère les métadonnées spécifiques à l'IG (CRS, Type, attributs…){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupère les métadonnées spécifiques à l'IG (CRS, Type, attributs…){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void compileMetadatas ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération de la variable
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"compileMetadatas_output1" : Map des métadonnées IG (HashMap<String, Object>)
	//	this.WILD_setOutput("compileMetadatas_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
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
	Méthode : changeCrs - Méthode de transformation des géométries, pour le changmeent de SRID ("reprojection"){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Méthode de transformation des géométries, pour le changmeent de SRID ("reprojection"){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_destSrid	SRID de destination{}
 */
public void changeCrs (
	Integer i_destSrid
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Parcours des géométries avec éventuelle reprojection
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
	Méthode : findCrs - Méthode de détermination du CRS si non renseigné ; en cas d’absence de SRID le constructeur identifie s’il s’agit d’un système géographique ou projeté. Si géométrique, il tente le WGS84, si projeté, il tente le L93. En cas d’erreur, il mentionne un null pour la géométrie et passe à la géométrie suivante. Un output conforme WILD (récupérable par l’orchestra et auto-généré dans la structure de code fournie) renseigne sur la liste des erreurs rencontrées.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Méthode de détermination du CRS si non renseigné ; en cas d’absence de SRID le constructeur identifie s’il s’agit d’un système géographique ou projeté. Si géométrique, il tente le WGS84, si projeté, il tente le L93. En cas d’erreur, il mentionne un null pour la géométrie et passe à la géométrie suivante. Un output conforme WILD (récupérable par l’orchestra et auto-généré dans la structure de code fournie) renseigne sur la liste des erreurs rencontrées.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_knownSrid	SRID transmis par le constructeur{}
 * @return	{}
 */
public Integer findCrs (
	Integer i_knownSrid
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Test d'existence de l'argument d'appel
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Test si projeté ou géographique
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Affectation en fonction
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Compilation et sauvegarde des erreurs
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"findCrs_output1" : Liste des erreurs (List<Object[]>)
	//	this.WILD_setOutput("findCrs_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * Méthode de détermination du CRS si non renseigné ; en cas d’absence de SRID le constructeur identifie s’il s’agit d’un système géographique ou projeté. Si géométrique, il tente le WGS84, si projeté, il tente le L93. En cas d’erreur, il mentionne un null pour la géométrie et passe à la géométrie suivante. Un output conforme WILD (récupérable par l’orchestra et auto-généré dans la structure de code fournie) renseigne sur la liste des erreurs rencontrées.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @return	SRID déterminé par algorithme (Integer) {}
 */
 public Integer findCrs ()throws Exception{
return findCrs(null);
} 

/*	### NOUVELLE METHODE ###
	Méthode : exportShp - Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_outputFilePath	Chemin du fichier de sortie{}
 * @param i_destSrid	SRID de destination{}
 * @return	{}
 */
public String exportShp (
	String i_outputFilePath,
	Integer i_destSrid
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Préparation des fichiers de flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Parcours des géométries avec éventuelle reprojection
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Fermeture des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Compilation et sauvegarde des erreurs
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"exportShp_output1" : Liste des erreurs (List<Object[]>)
	//	this.WILD_setOutput("exportShp_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_outputFilePath	Chemin du fichier de sortie {}
 * @return	Chemin de fichier produit (String) {}
 */
 public String exportShp (
	String i_outputFilePath
	)throws Exception{
return exportShp(i_outputFilePath,null);
} 

/*	### NOUVELLE METHODE ###
	Méthode : exportGeojson - Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_outputFilePath	Chemin du fichier de sortie{}
 * @param i_destSrid	SRID de destination{}
 * @return	{}
 */
public String exportGeojson (
	String i_outputFilePath,
	Integer i_destSrid
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Préparation des fichiers de flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Parcours des géométries avec éventuelle reprojection
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Fermeture des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Compilation et sauvegarde des erreurs
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"exportGeojson_output1" : Liste des erreurs (List<Object[]>)
	//	this.WILD_setOutput("exportGeojson_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_outputFilePath	Chemin du fichier de sortie {}
 * @return	Chemin de fichier produit (String) {}
 */
 public String exportGeojson (
	String i_outputFilePath
	)throws Exception{
return exportGeojson(i_outputFilePath,null);
} 

/*	### NOUVELLE METHODE ###
	Méthode : exportMifmid - Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_outputFilePath	Chemin du fichier de sortie{}
 * @param i_destSrid	SRID de destination{}
 * @return	{}
 */
public String exportMifmid (
	String i_outputFilePath,
	Integer i_destSrid
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Préparation des fichiers de flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Parcours des géométries avec éventuelle reprojection
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Fermeture des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Compilation et sauvegarde des erreurs
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"exportMifmid_output1" : Liste des erreurs (List<Object[]>)
	//	this.WILD_setOutput("exportMifmid_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_outputFilePath	Chemin du fichier de sortie {}
 * @return	Chemin de fichier produit (String) {}
 */
 public String exportMifmid (
	String i_outputFilePath
	)throws Exception{
return exportMifmid(i_outputFilePath,null);
} 

/*	### NOUVELLE METHODE ###
	Méthode : exportGml - Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_outputFilePath	Chemin du fichier de sortie{}
 * @param i_destSrid	SRID de destination{}
 * @return	{}
 */
public String exportGml (
	String i_outputFilePath,
	Integer i_destSrid
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Préparation des fichiers de flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Parcours des géométries avec éventuelle reprojection
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Fermeture des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Compilation et sauvegarde des erreurs
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"exportGml_output1" : Liste des erreurs (List<Object[]>)
	//	this.WILD_setOutput("exportGml_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_outputFilePath	Chemin du fichier de sortie {}
 * @return	Chemin de fichier produit (String) {}
 */
 public String exportGml (
	String i_outputFilePath
	)throws Exception{
return exportGml(i_outputFilePath,null);
} 

/*	### NOUVELLE METHODE ###
	Méthode : exportKml - Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_outputFilePath	Chemin du fichier de sortie{}
 * @param i_destSrid	SRID de destination{}
 * @return	{}
 */
public String exportKml (
	String i_outputFilePath,
	Integer i_destSrid
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Préparation des fichiers de flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Parcours des géométries avec éventuelle reprojection
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Fermeture des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Compilation et sauvegarde des erreurs
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"exportKml_output1" : Liste des erreurs (List<Object[]>)
	//	this.WILD_setOutput("exportKml_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_outputFilePath	Chemin du fichier de sortie {}
 * @return	Chemin de fichier produit (String) {}
 */
 public String exportKml (
	String i_outputFilePath
	)throws Exception{
return exportKml(i_outputFilePath,null);
} 

/*	### NOUVELLE METHODE ###
	Méthode : getWkt - Récupération de variable (getter),en cas d’absence de numéro de collection à extraire l’intégralité de la collection est produite.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération de variable (getter),en cas d’absence de numéro de collection à extraire l’intégralité de la collection est produite.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_numToGet	numéro de collection de la géométrie et des attributs à récupérer{}
 * @param i_maxLength	Nombre maximal d'enregistrements à récupérer (par défaut si idDébut == null alors longueurMaxListeRécupérée = 1000 // si idDébut != null alors longueurMaxListeRécupérée = 1){}
 * @return	{}
 */
public String getWkt (
	Integer i_numToGet,
	Integer i_maxLength
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération ou compilation de la variable
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Résolution de la longueur max à récupérer
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
 * Récupération de variable (getter),en cas d’absence de numéro de collection à extraire l’intégralité de la collection est produite.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @return	Géométrie sous forme de String WKT (String) {}
 */
 public String getWkt ()throws Exception{
return getWkt(null,1000);
} 

/*	### NOUVELLE METHODE ###
	Méthode : getGeojson - Récupération de variable (getter), en cas d’absence de numéro de collection à extraire l’intégralité de la collection est produite.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération de variable (getter), en cas d’absence de numéro de collection à extraire l’intégralité de la collection est produite.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_numToGet	numéro de collection de la géométrie et des attributs à récupérer{}
 * @param i_maxLength	Nombre maximal d'enregistrements à récupérer (par défaut si idDébut == null alors longueurMaxListeRécupérée = 1000 // si idDébut != null alors longueurMaxListeRécupérée = 1){}
 * @return	{}
 */
public String getGeojson (
	Integer i_numToGet,
	Integer i_maxLength
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération ou compilation de la variable
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Résolution de la longueur max à récupérer
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
 * Récupération de variable (getter), en cas d’absence de numéro de collection à extraire l’intégralité de la collection est produite.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @return	GeoJSON sous forme de String WKT (String) {}
 */
 public String getGeojson ()throws Exception{
return getGeojson(null,1000);
} 

}

