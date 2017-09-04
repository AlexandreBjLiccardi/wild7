
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

// Dépendances, bibliothèques JAVA par exemple.
import static fr.wild.common.IoFileSystem.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.io.FilenameUtils;


/**
 * Code généré automatiquement par l'outil Wild
 * Fichier générique
 */
public abstract class WildFile extends WildClass{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildFile(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected File fileFile; // Instance de fichier JAVA du fichier ou du dossier
	protected String filePath; // Chemin absolu du fichier
	protected String fileSource; // Chemin relatif du fichier ou URL (avant commit sur disque dur)
	protected HashMap<String,Object> fileDescriptor; // Map de description du fichier ou des fichiers contenus par le dossier dossier
	protected BufferedInputStream fileStream; // Flux de lecture du fichier (nul pour les répertoires)
	protected HashMap<String,WildDataSet> dataSets; // Contenu du fichier, mappé et casté, sous forme d'objets WildDataSet

// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	protected String pathToFile;	//Chemin vers le fichier{}
	protected String mimeEncoding;	//Encodage du fichier{}
	protected Boolean isFromZip;	//Le fichier est issu d'un ZIP, il doit être dézippé{}
	protected HashMap<String,Object> fileService_Describer;	//Carte (Map) de description du service à contacter{}
	protected WildWebService fileWildWebService;	//WebService instancié, à connecter{}


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
 * @param i_isFromZip	Le fichier est issu d'un ZIP, il doit être dézippé{}
 * @param i_fileService_Describer	Carte (Map) de description du service à contacter{}
 * @param i_fileWildWebService	WebService instancié, à connecter{}
 */
protected void WILD_initialize_WildFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding,
	Boolean i_isFromZip,
	HashMap<String,Object> i_fileService_Describer,
	WildWebService i_fileWildWebService
) throws Exception {

//	// Amorce de la classe
	// Initialisation de la classe d'objet selon le schéma Wild
	WILD_dObject = i_WILD_dObject ;
	WILD_initialize_WildClass(i_WILD_dObject/*** Paramètres à actualiser ***/);
	// Préparation des variables d'invocation (considérées comme champs globaux)
	this.pathToFile = i_pathToFile;
	this.mimeEncoding = i_mimeEncoding;
	this.isFromZip = i_isFromZip;
	this.fileService_Describer = i_fileService_Describer;
	this.fileWildWebService = i_fileWildWebService;

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
 * @param i_isFromZip	Le fichier est issu d'un ZIP, il doit être dézippé{}
 */

public WildFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding,
	Boolean i_isFromZip
) throws Exception{
	if(mimeEncoding==null)mimeEncoding="UTF-8";
	if(isFromZip==null)isFromZip=false;
	this.WILD_initialize_WildFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,i_isFromZip,null,null);
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

public WildFile(
	WildObject i_WILD_dObject,
	String i_pathToFile
) throws Exception{
	this.WILD_initialize_WildFile(i_WILD_dObject,i_pathToFile,"UTF-8",false,null,null);
}


// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Constructeur, appelle nécessairement WILD_initialize()
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_fileService_Describer	Carte (Map) de description du service à contacter{}
 */

public WildFile(
	WildObject i_WILD_dObject,
	HashMap<String,Object> i_fileService_Describer
) throws Exception{
	this.WILD_initialize_WildFile(i_WILD_dObject,null,null,null,i_fileService_Describer,null);
}


// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Constructeur, appelle nécessairement WILD_initialize()
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_fileWildWebService	WebService instancié, à connecter{}
 */

public WildFile(
	WildObject i_WILD_dObject,
	WildWebService i_fileWildWebService
) throws Exception{
	this.WILD_initialize_WildFile(i_WILD_dObject,null,null,null,null,i_fileWildWebService);
}

/*	### NOUVELLE METHODE ###
	Méthode : add - Ajout d'un enregistrement. (WildDataSet){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ajout d'un enregistrement. (WildDataSet){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_dataSetName	Nom du dataset à affecter{}
 * @param i_enrContents	Nouvel enregistrement, sous forme de Map{}
 */
public void add (
	String i_dataSetName,
	HashMap<String,Object> i_enrContents
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Cast du paramètre
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Ajout du paramètre
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
 * Ajout d'un enregistrement. (WildDataSet){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_enrContents	Nouvel enregistrement, sous forme de Map {}
 */
 public void add (
	HashMap<String,Object> i_enrContents
	)throws Exception{
add("1",i_enrContents);
} 

/*	### NOUVELLE METHODE ###
	Méthode : addColumn - Ajoute une colonne, contenant une valeur précise{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ajoute une colonne, contenant une valeur précise{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_dataSetName	Nom du dataset à affecter{}
 * @param i_columnName	Nom de la colonne{}
 * @param i_colContents	Contenu de la colonne{}
 */
public void addColumn (
	String i_dataSetName,
	String i_columnName,
	HashMap<Integer,Object> i_colContents
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Cast du WildDataSet (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 50, Ecriture du jeu de données (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"addColumn_output1" : Position de la colonne rajoutée (Integer)
	//	this.WILD_setOutput("addColumn_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
 
/**
 * Ajoute une colonne, contenant une valeur précise{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_columnName	Nom de la colonne {}
 * @param i_colContents	Contenu de la colonne {}
 */
 public void addColumn (
	String i_columnName,
	HashMap<Integer,Object> i_colContents
	)throws Exception{
addColumn("1",i_columnName,i_colContents);
} 

/*	### NOUVELLE METHODE ###
	Méthode : addColumnJoinBy - Ajoute une colonne, à partir d'une matrice, avec jointure sur un nom de colonne donné{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ajoute une colonne, à partir d'une matrice, avec jointure sur un nom de colonne donné{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_dataSetName	Nom du dataset à affecter{}
 * @param i_columnName	Nom de la colonne à ajouter{}
 * @param i_columnCompare_dataset	Nom de la colonne comparée, dataset{}
 * @param i_inputJoin	Valeurs de comparaison{}
 * @param i_colContents	Contenu de la colonne{}
 */
public void addColumnJoinBy (
	String i_dataSetName,
	String i_columnName,
	String i_columnCompare_dataset,
	HashMap<Integer,Object> i_inputJoin,
	HashMap<Integer,Object> i_colContents
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Cast du WildDataSet (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 50, Ecriture du jeu de données (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"addColumnJoinBy_output1" : Position de la colonne rajoutée (Integer)
	//	this.WILD_setOutput("addColumnJoinBy_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
 
/**
 * Ajoute une colonne, à partir d'une matrice, avec jointure sur un nom de colonne donné{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_columnName	Nom de la colonne à ajouter {}
 * @param i_columnCompare_dataset	Nom de la colonne comparée, dataset {}
 * @param i_inputJoin	Valeurs de comparaison {}
 * @param i_colContents	Contenu de la colonne {}
 */
 public void addColumnJoinBy (
	String i_columnName,
	String i_columnCompare_dataset,
	HashMap<Integer,Object> i_inputJoin,
	HashMap<Integer,Object> i_colContents
	)throws Exception{
addColumnJoinBy("1",i_columnName,i_columnCompare_dataset,i_inputJoin,i_colContents);
} 

/*	### NOUVELLE METHODE ###
	Méthode : addDataSet - Ajoute à la liste des jeux de données, un nouveau jeu de données{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ajoute à la liste des jeux de données, un nouveau jeu de données{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_dataSetName	Nom du nouveau jeu de données{}
 * @param i_dataSet	Nouveau jeu de données à inclure{}
 */
public void addDataSet (
	String i_dataSetName,
	WildDataSet i_dataSet
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Cast du paramètre
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Ajout du paramètre
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
 * Ajoute à la liste des jeux de données, un nouveau jeu de données{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_dataSet	Nouveau jeu de données à inclure {}
 */
 public void addDataSet (
	WildDataSet i_dataSet
	)throws Exception{
addDataSet("1",i_dataSet);
} 

/*	### NOUVELLE METHODE ###
	Méthode : castFile - Méthode de conversion standard du contenu vers un jeu de données (ou une collection). Systématiquement surchargée.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Méthode de conversion standard du contenu vers un jeu de données (ou une collection). Systématiquement surchargée.{ 
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

//	//	//	Etape	"1" : poids relatif de 10, A surcharger
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
	Méthode : commit - Enregistrement des modifications du WildDataSet vers le WildFile. Systématiquement surchargée.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Enregistrement des modifications du WildDataSet vers le WildFile. Systématiquement surchargée.{ 
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

//	//	//	Etape	"1" : poids relatif de 10, A surcharger
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
	Méthode : concatenate - Concatène deux colonnes{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Concatène deux colonnes{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_dataSetName	Nom du dataset à affecter{}
 * @param i_col_1	Nom de colonne devant{}
 * @param i_col_2	Nom de colonne derrière{}
 * @param i_link	Opérateur de liaison{}
 */
public void concatenate (
	String i_dataSetName,
	String i_col_1,
	String i_col_2,
	String i_link
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

//	//	//	Output	"concatenate_output1" : Position de la colonne rajoutée (Integer)
	//	this.WILD_setOutput("concatenate_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
 
/**
 * Concatène deux colonnes{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_col_1	Nom de colonne devant {}
 * @param i_col_2	Nom de colonne derrière {}
 * @param i_link	Opérateur de liaison {}
 */
 public void concatenate (
	String i_col_1,
	String i_col_2,
	String i_link
	)throws Exception{
concatenate("1",i_col_1,i_col_2,i_link);
} 

/*	### NOUVELLE METHODE ###
	Méthode : concatenateFilterBy - Concatène deux colonnes, si les valeurs correspondent au filtre{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Concatène deux colonnes, si les valeurs correspondent au filtre{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_dataSetName	Nom du dataset à affecter{}
 * @param i_col_1	Nom de colonne devant{}
 * @param i_col_2	Nom de colonne derrière{}
 * @param i_link	Opérateur de liaison{}
 * @param i_colContents	Motif à reconnaître pour affectation (filtre){}
 */
public void concatenateFilterBy (
	String i_dataSetName,
	String i_col_1,
	String i_col_2,
	String i_link,
	HashMap<String,Object> i_colContents
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

//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec sélection et réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"concatenateFilterBy_output1" : Position de la colonne rajoutée (Integer)
	//	this.WILD_setOutput("concatenateFilterBy_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
 
/**
 * Concatène deux colonnes, si les valeurs correspondent au filtre{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_col_1	Nom de colonne devant {}
 * @param i_col_2	Nom de colonne derrière {}
 * @param i_link	Opérateur de liaison {}
 * @param i_colContents	Motif à reconnaître pour affectation (filtre) {}
 */
 public void concatenateFilterBy (
	String i_col_1,
	String i_col_2,
	String i_link,
	HashMap<String,Object> i_colContents
	)throws Exception{
concatenateFilterBy("1",i_col_1,i_col_2,i_link,i_colContents);
} 

/*	### NOUVELLE METHODE ###
	Méthode : copy - Copie le fichier d'un répertoire vers un autre (nécessairement copie locale){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Copie le fichier d'un répertoire vers un autre (nécessairement copie locale){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_pathToFile_OUT	Chemin vers le fichier de sortie{}
 */
public void copy (
	String i_pathToFile_OUT
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

//	//	//	Etape	"2" : poids relatif de 10, Copie des fichiers
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"copy_output1" : Chemin du fichier de sortie (String)
	//	this.WILD_setOutput("copy_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : dbChOwner - Change le propriétaire d'un fichier en base de données{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Change le propriétaire d'un fichier en base de données{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_userId	Nouvel identifiant unique de l'utilisateur{}
 */
public void dbChOwner (
	String i_userId
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération des identifiants base de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Mise à jour de la base de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"dbChOwner_output1" : Identifiant unique du fichier (String)
	//	this.WILD_setOutput("dbChOwner_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : dbCommit - Ajoute ou met à jour une ligne relative au fichier dans la base de données WilD{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ajoute ou met à jour une ligne relative au fichier dans la base de données WilD{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void dbCommit ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération des identifiants base de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Mise à jour de la base de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"dbCommit_output1" : Identifiant unique du fichier (String)
	//	this.WILD_setOutput("dbCommit_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : dbGetId - Récupère l'identifiant du fichier dans la base de données{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupère l'identifiant du fichier dans la base de données{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public String dbGetId ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération des identifiants base de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Mise à jour de la base de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"dbGetId_output1" : Identifiant unique du fichier (String)
	//	this.WILD_setOutput("dbGetId_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : dbUnvalidate - Change le niveau de validation d'un fichier à 'non validé'{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Change le niveau de validation d'un fichier à 'non validé'{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void dbUnvalidate ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération des identifiants base de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Mise à jour de la base de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"dbUnvalidate_output1" : Identifiant unique du fichier (String)
	//	this.WILD_setOutput("dbUnvalidate_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : dbValidate - Change le niveau de validation d'un fichier à 'validé'{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Change le niveau de validation d'un fichier à 'validé'{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void dbValidate ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération des identifiants base de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Mise à jour de la base de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"dbValidate_output1" : Identifiant unique du fichier (String)
	//	this.WILD_setOutput("dbValidate_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : delete - Suppression du fichier (physique){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Suppression du fichier (physique){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void delete ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Création de l'objet JAVA
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Suppression de l'objet
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des variables
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"delete_output1" : Chemin du fichier de sortie (String)
	//	this.WILD_setOutput("delete_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : download - Création des sources (local, URL, FTP...) pour disposer d'un fichier sur le système local{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Création des sources (local, URL, FTP...) pour disposer d'un fichier sur le système local{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_fileName_OUT	Nom du fichier de sortie{}
 * @param i_urlInput	Service de téléchargement{}
 * @param i_ftpService	Service de téléchargement{}
 */
public void download (
	String i_fileName_OUT,
	WildWebService i_urlInput,
	WildFtpService i_ftpService
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Résolution des URL
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Copie des fichiers
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"download_output1" : Chemin du fichier de sortie (String)
	//	this.WILD_setOutput("download_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
/**
 * Création des sources (local, URL, FTP...) pour disposer d'un fichier sur le système local{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fileName_OUT	Nom du fichier de sortie {}
 * @param i_urlInput	Service de téléchargement {}
 */
 public void download (
	String i_fileName_OUT,
	WildWebService i_urlInput
	)throws Exception{download(i_fileName_OUT,i_urlInput,null);
}
/**
 * Création des sources (local, URL, FTP...) pour disposer d'un fichier sur le système local{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fileName_OUT	Nom du fichier de sortie {}
 * @param i_ftpService	Service de téléchargement {}
 */
 public void download (
	String i_fileName_OUT,
	WildFtpService i_ftpService
	)throws Exception{download(i_fileName_OUT,null,i_ftpService);
}
/*	### NOUVELLE METHODE ###
	Méthode : drop - Suppression d'un enregistrement.(WildDataSet){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Suppression d'un enregistrement.(WildDataSet){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_dataSetName	Nom du dataset à affecter{}
 * @param i_filter	Motif à reconnaître pour suppression (filtre){}
 */
public void drop (
	String i_dataSetName,
	HashMap<String,Object> i_filter
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Sélection de l'enregistrement
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Suppression de l'entrée
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
 * Suppression d'un enregistrement.(WildDataSet){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_filter	Motif à reconnaître pour suppression (filtre) {}
 */
 public void drop (
	HashMap<String,Object> i_filter
	)throws Exception{
drop("1",i_filter);
} 

/*	### NOUVELLE METHODE ###
	Méthode : dropColumn - Supprime une colonne{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Supprime une colonne{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_dataSetName	Nom du dataset à affecter{}
 * @param i_columnName	Nom de la colonne{}
 */
public void dropColumn (
	String i_dataSetName,
	String i_columnName
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Cast du WildDataSet (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 50, Ecriture du jeu de données (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"dropColumn_output1" : Position de la colonne supprimée (Integer)
	//	this.WILD_setOutput("dropColumn_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
 
/**
 * Supprime une colonne{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_columnName	Nom de la colonne {}
 */
 public void dropColumn (
	String i_columnName
	)throws Exception{
dropColumn("1",i_columnName);
} 

/*	### NOUVELLE METHODE ###
	Méthode : dropFilter - Suppression des enregistrements répondant à une condition. (WildDataSet){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Suppression des enregistrements répondant à une condition. (WildDataSet){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_dataSetName	Nom du dataset à affecter{}
 * @param i_filter	Motif à reconnaître pour suppression (filtre){}
 * @param i_modeFilter	Mode d'exécution, "dropIfContains", "dropIfNotContains"{}
 * @param i_searchValue	Valeur cherchée{}
 * @param i_targetedColumns	Colonnes dans lesquelles s'effectue la recherche{}
 */
public void dropFilter (
	String i_dataSetName,
	HashMap<String,Object> i_filter,
	String i_modeFilter,
	String i_searchValue,
	List<String> i_targetedColumns
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Sélection des enregistrements
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Suppression des entrées
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"dropFilter_output1" : Nombre d'enregistrements supprimés (Integer)
	//	this.WILD_setOutput("dropFilter_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
/**
 * Suppression des enregistrements répondant à une condition. (WildDataSet){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_dataSetName	Nom du dataset à affecter {}
 * @param i_filter	Motif à reconnaître pour suppression (filtre) {}
 */
 public void dropFilter (
	String i_dataSetName,
	HashMap<String,Object> i_filter
	)throws Exception{
	if(i_dataSetName==null)i_dataSetName = 1;dropFilter(i_dataSetName,i_filter,null,null,null);
}
/**
 * Suppression des enregistrements répondant à une condition. (WildDataSet){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_dataSetName	Nom du dataset à affecter {}
 * @param i_modeFilter	Mode d'exécution, "dropIfContains", "dropIfNotContains" {}
 * @param i_searchValue	Valeur cherchée {}
 * @param i_targetedColumns	Colonnes dans lesquelles s'effectue la recherche {}
 */
 public void dropFilter (
	String i_dataSetName,
	String i_modeFilter,
	String i_searchValue,
	List<String> i_targetedColumns
	)throws Exception{
	if(i_dataSetName==null)i_dataSetName = 1;dropFilter(i_dataSetName,null,i_modeFilter,i_searchValue,i_targetedColumns);
}
 
/**
 * Suppression des enregistrements répondant à une condition. (WildDataSet){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_filter	Motif à reconnaître pour suppression (filtre) {}
 */
 public void dropFilter (
	HashMap<String,Object> i_filter
	)throws Exception{
dropFilter("1",i_filter,null,null,null);
} 

 
/**
 * Suppression des enregistrements répondant à une condition. (WildDataSet){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_modeFilter	Mode d'exécution, "dropIfContains", "dropIfNotContains" {}
 * @param i_searchValue	Valeur cherchée {}
 * @param i_targetedColumns	Colonnes dans lesquelles s'effectue la recherche {}
 */
 public void dropFilter (
	String i_modeFilter,
	String i_searchValue,
	List<String> i_targetedColumns
	)throws Exception{
dropFilter("1",null,i_modeFilter,i_searchValue,i_targetedColumns);
} 

/*	### NOUVELLE METHODE ###
	Méthode : fillWith - Remplace le contenu du fichier ou de la collection par le cast d'un nouveau jeu de données. Systématiquement surchargé.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Remplace le contenu du fichier ou de la collection par le cast d'un nouveau jeu de données. Systématiquement surchargé.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_dataSet	Nouveau jeu de données à commit{}
 * @param i_dataSets	Nouvelle collection de jeux de données à commit{}
 */
public void fillWith (
	WildDataSet i_dataSet,
	HashMap<String,WildDataSet> i_dataSets
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, A surcharger
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

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
/**
 * Remplace le contenu du fichier ou de la collection par le cast d'un nouveau jeu de données. Systématiquement surchargé.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_dataSet	Nouveau jeu de données à commit {}
 */
 public void fillWith (
	WildDataSet i_dataSet
	)throws Exception{fillWith(i_dataSet,null);
}
/**
 * Remplace le contenu du fichier ou de la collection par le cast d'un nouveau jeu de données. Systématiquement surchargé.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_dataSets	Nouvelle collection de jeux de données à commit {}
 */
 public void fillWith (
	HashMap<String,WildDataSet> i_dataSets
	)throws Exception{fillWith(null,i_dataSets);
}
/*	### NOUVELLE METHODE ###
	Méthode : getDataSet - Récupère un jeux de données chargé dans le fichier, résolu dans une Map{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupère un jeux de données chargé dans le fichier, résolu dans une Map{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_dataSetNum	Numéro du dataset à récupérer{}
 * @param i_dataSetName	Nom du dataset à récupérer{}
 * @return	{}
 */
public WildDataSet getDataSet (
	Integer i_dataSetNum,
	String i_dataSetName
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	WildDataSet WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération de la variable
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"getDataSet_output1" : Contenu du jeu de données (WildDataSet)
	//	this.WILD_setOutput("getDataSet_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * Récupère un jeux de données chargé dans le fichier, résolu dans une Map{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_dataSetNum	Numéro du dataset à récupérer {}
 * @return	Contenu du jeu de données (WildDataSet) {}
 */
 public WildDataSet getDataSet (
	Integer i_dataSetNum
	)throws Exception{return getDataSet(i_dataSetNum,null);
}
/**
 * Récupère un jeux de données chargé dans le fichier, résolu dans une Map{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_dataSetName	Nom du dataset à récupérer {}
 * @return	Contenu du jeu de données (WildDataSet) {}
 */
 public WildDataSet getDataSet (
	String i_dataSetName
	)throws Exception{return getDataSet(null,i_dataSetName);
}
/*	### NOUVELLE METHODE ###
	Méthode : getDataSets - Récupère l'ensemble des jeux de données chargés dans le fichier, résolus dans une Map{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupère l'ensemble des jeux de données chargés dans le fichier, résolus dans une Map{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public HashMap<String,WildDataSet> getDataSets ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<String,WildDataSet> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération de la variable
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"getDataSets_output1" : Contenu des jeux de données (HashMap<String,WildDataSet>)
	//	this.WILD_setOutput("getDataSets_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : getDescribers - Récupération des paramètres descripteurs du fichier considéré (taille, chemin, nom, extension...){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des paramètres descripteurs du fichier considéré (taille, chemin, nom, extension...){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public HashMap<String, Object> getDescribers ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<String, Object> WILD_toReturn = null ; // Ne pas modifier

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
	Méthode : getFile - Récupération de l'objet 'File' JAVA{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération de l'objet 'File' JAVA{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public File getFile ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	File WILD_toReturn = null ; // Ne pas modifier

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
	Méthode : getMetadatas - Récupération des métadonnées d'un fichier{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des métadonnées d'un fichier{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public WildGmdFile getMetadatas ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	WildGmdFile WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération des identifiants base de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Lecture de la base de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"getMetadatas_output1" : Contenu textuel des métadonnées (String)
	//	this.WILD_setOutput("getMetadatas_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"getMetadatas_output2" : Map des métadonnées (HashMap<String, Object>)
	//	this.WILD_setOutput("getMetadatas_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : getPath - Récupération du chemin du fichier en local{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération du chemin du fichier en local{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public String getPath ()throws Exception{
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
	Méthode : getSize - Récupération de la taille du fichier en octets{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération de la taille du fichier en octets{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public Integer getSize ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer WILD_toReturn = null ; // Ne pas modifier

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
	Méthode : getURL - Récupération du lien URL vers le fichier{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération du lien URL vers le fichier{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public String getURL ()throws Exception{
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
	Méthode : move - Copie le fichier d'un répertoire vers un autre (nécessairement copie locale) ; supprime le fichier initial{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Copie le fichier d'un répertoire vers un autre (nécessairement copie locale) ; supprime le fichier initial{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_fileName_OUT	Nom du fichier de sortie{}
 * @param i_keepFile	Suppression du fichier initial{}
 * @return	{}
 */
public String move (
	String i_fileName_OUT,
	Boolean i_keepFile
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Copie des fichiers
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Suppression du fichier
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"move_output1" : Chemin du fichier de sortie (String)
	//	this.WILD_setOutput("move_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * Copie le fichier d'un répertoire vers un autre (nécessairement copie locale) ; supprime le fichier initial{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fileName_OUT	Nom du fichier de sortie {}
 * @return	Chemin du fichier de sortie (String) {}
 */
 public String move (
	String i_fileName_OUT
	)throws Exception{
return move(i_fileName_OUT,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : publish - Déplacement du fichier vers une position où l'URL est publique (accessible sans identification){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Déplacement du fichier vers une position où l'URL est publique (accessible sans identification){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public String publish ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Copie des fichiers
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"publish_output1" : URL de sortie (String)
	//	this.WILD_setOutput("publish_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : replaceValue - Remplace une valeur dans la totalité du fichier{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Remplace une valeur dans la totalité du fichier{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_dataSetName	Nom du dataset à affecter{}
 * @param i_formerValue	Valeur à remplacer{}
 * @param i_newValue	Valeur de remplacement{}
 */
public void replaceValue (
	String i_dataSetName,
	Object i_formerValue,
	Object i_newValue
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

//	//	//	Output	"replaceValue_output1" : Nombre de remplacements (Integer)
	//	this.WILD_setOutput("replaceValue_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
 
/**
 * Remplace une valeur dans la totalité du fichier{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_formerValue	Valeur à remplacer {}
 * @param i_newValue	Valeur de remplacement {}
 */
 public void replaceValue (
	Object i_formerValue,
	Object i_newValue
	)throws Exception{
replaceValue("1",i_formerValue,i_newValue);
} 

/*	### NOUVELLE METHODE ###
	Méthode : replaceValueFilterBy - Remplace une valeur dans l'emprise indiquée{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Remplace une valeur dans l'emprise indiquée{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_dataSetName	Nom du dataset à affecter{}
 * @param i_formerValue	Valeur à remplacer{}
 * @param i_newValue	Valeur de remplacement{}
 * @param i_enrContents	Nouvel enregistrement, sous forme de Map{}
 */
public void replaceValueFilterBy (
	String i_dataSetName,
	Object i_formerValue,
	Object i_newValue,
	HashMap<String,Object> i_enrContents
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

//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec sélection et réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"replaceValueFilterBy_output1" : Nombre de remplacements (Integer)
	//	this.WILD_setOutput("replaceValueFilterBy_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
 
/**
 * Remplace une valeur dans l'emprise indiquée{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_formerValue	Valeur à remplacer {}
 * @param i_newValue	Valeur de remplacement {}
 * @param i_enrContents	Nouvel enregistrement, sous forme de Map {}
 */
 public void replaceValueFilterBy (
	Object i_formerValue,
	Object i_newValue,
	HashMap<String,Object> i_enrContents
	)throws Exception{
replaceValueFilterBy("1",i_formerValue,i_newValue,i_enrContents);
} 

/*	### NOUVELLE METHODE ###
	Méthode : saveAsFlat - Applatissement du fichier vers un CSV en sortie (WildFlatFile){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Applatissement du fichier vers un CSV en sortie (WildFlatFile){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_dataSetName	Nom du dataset à extraire{}
 * @param i_pathToFile_OUT	Chemin vers le fichier de sortie{}
 */
public void saveAsFlat (
	String i_dataSetName,
	String i_pathToFile_OUT
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Cast du WildDataSet (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 50, Ecriture du fichier (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"saveAsFlat_output1" : Chemin du fichier de sortie (String)
	//	this.WILD_setOutput("saveAsFlat_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
 
/**
 * Applatissement du fichier vers un CSV en sortie (WildFlatFile){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_pathToFile_OUT	Chemin vers le fichier de sortie {}
 */
 public void saveAsFlat (
	String i_pathToFile_OUT
	)throws Exception{
saveAsFlat("1",i_pathToFile_OUT);
} 

/*	### NOUVELLE METHODE ###
	Méthode : setEncoding - Modifie l'encodage du fichier{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Modifie l'encodage du fichier{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_mimeEncoding	Nouvel encodage{}
 */
public void setEncoding (
	String i_mimeEncoding
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Attribution de la variable
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Mise à jour des variables
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"setEncoding_output1" : Chemin du fichier de sortie (String)
	//	this.WILD_setOutput("setEncoding_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : setMetadatas - Association d'un fichier GMD ou d'une map à un fichier, pour renseignement de métadonnées{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Association d'un fichier GMD ou d'une map à un fichier, pour renseignement de métadonnées{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_contents_md	Contenu GMD à ajouter en tant que métadonnée{}
 * @param i_fileGmd_md	Fichier Gmd instancié WILD{}
 */
public void setMetadatas (
	String i_contents_md,
	WildGmdFile i_fileGmd_md
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Composition de la séquence GMD
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Récupération des identifiants base de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Mise à jour de la base de données
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
 * Association d'un fichier GMD ou d'une map à un fichier, pour renseignement de métadonnées{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_contents_md	Contenu GMD à ajouter en tant que métadonnée {}
 */
 public void setMetadatas (
	String i_contents_md
	)throws Exception{setMetadatas(i_contents_md,null);
}
/**
 * Association d'un fichier GMD ou d'une map à un fichier, pour renseignement de métadonnées{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fileGmd_md	Fichier Gmd instancié WILD {}
 */
 public void setMetadatas (
	WildGmdFile i_fileGmd_md
	)throws Exception{setMetadatas(null,i_fileGmd_md);
}
/*	### NOUVELLE METHODE ###
	Méthode : unpublish - Suppression du fichier depuis la position où l'URL est publique (accessible sans identification){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Suppression du fichier depuis la position où l'URL est publique (accessible sans identification){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public String unpublish ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Création de l'objet JAVA
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Suppression de l'objet
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des variables
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"unpublish_output1" : Chemin du fichier supprimé (String)
	//	this.WILD_setOutput("unpublish_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : unzip - Décompression d'un '*.zip' d'un fichier ou d'un répertoire{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Décompression d'un '*.zip' d'un fichier ou d'un répertoire{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_fileName_OUT	Nom du fichier de sortie{}
 * @param i_keepFile	Suppression du fichier initial{}
 * @return	{}
 */
public String unzip (
	String i_fileName_OUT,
	Boolean i_keepFile
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Création de l'archive
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Parcours des entrées et décodage de l'archive (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"unzip_output1" : Chemin du fichier de sortie (String)
	//	this.WILD_setOutput("unzip_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * Décompression d'un '*.zip' d'un fichier ou d'un répertoire{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fileName_OUT	Nom du fichier de sortie {}
 * @return	Chemin du fichier de sortie (String) {}
 */
 public String unzip (
	String i_fileName_OUT
	)throws Exception{
return unzip(i_fileName_OUT,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : updateFile - Mise à jour d'un enregistrement (WildDataSet){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Mise à jour d'un enregistrement (WildDataSet){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_dataSetName	Nom du dataset à affecter{}
 * @param i_filtre	Motif à reconnaître pour affection (filtre){}
 * @param i_newEnr	Données à mettre à jour{}
 */
public void updateFile (
	String i_dataSetName,
	HashMap<String,Object> i_filtre,
	HashMap<String,Object> i_newEnr
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Sélection de l'enregistrement
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Mise à jour de l'entrée
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
 * Mise à jour d'un enregistrement (WildDataSet){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_filtre	Motif à reconnaître pour affection (filtre) {}
 * @param i_newEnr	Données à mettre à jour {}
 */
 public void updateFile (
	HashMap<String,Object> i_filtre,
	HashMap<String,Object> i_newEnr
	)throws Exception{
updateFile("1",i_filtre,i_newEnr);
} 

/*	### NOUVELLE METHODE ###
	Méthode : zip - Compression en '*.zip' d'un fichier ou d'un répertoire{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Compression en '*.zip' d'un fichier ou d'un répertoire{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_fileName_OUT	Nom du fichier de sortie{}
 * @param i_keepFile	Suppression du fichier initial{}
 * @return	{}
 */
public String zip (
	String i_fileName_OUT,
	Boolean i_keepFile
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Création de l'archive
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Parcours des entrées et encodage de l'archive (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"zip_output1" : Chemin du fichier de sortie (String)
	//	this.WILD_setOutput("zip_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * Compression en '*.zip' d'un fichier ou d'un répertoire{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fileName_OUT	Nom du fichier de sortie {}
 * @return	Chemin du fichier de sortie (String) {}
 */
 public String zip (
	String i_fileName_OUT
	)throws Exception{
return zip(i_fileName_OUT,false);
} 

}

