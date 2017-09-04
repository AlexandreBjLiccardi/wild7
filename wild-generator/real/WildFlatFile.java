
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

// Dépendances, bibliothèques JAVA par exemple.
import org.apache.commons.csv.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * Code généré automatiquement par l'outil Wild
 * Fichier "plat" générique. Fichier contenant des données séparées par un caractère séparateur de lignes, un caractère séparateur de colonne, un "protecteur de texte", un caractère d'échappement. Toutes les lignes appartiennent à un même "onglet" (une seule définition de ligne d'en-tête, de caractères etc... par fichier).
 */
public abstract class WildFlatFile extends WildFile{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildFlatFile(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected Integer fileLineHeader; // Numéro de ligne des en-têtes
	protected HashMap<Integer,String> fileHeaders; // En-têtes ordonnées

// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	protected String pathToFile;	//Chemin vers le fichier{}
	protected String mimeEncoding;	//Encodage du fichier{}
	protected String delimiter;	//séparateur de colonnes{}
	protected String quoter;	//Caractère de protection de texte{}
	protected String escapeChar;	//caractère d'échappement{}
	protected String lineDelimiter;	//Caractère de retour à la ligne{}


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
 * @param i_delimiter	séparateur de colonnes{}
 * @param i_quoter	Caractère de protection de texte{}
 * @param i_escapeChar	caractère d'échappement{}
 * @param i_lineDelimiter	Caractère de retour à la ligne{}
 */
protected void WILD_initialize_WildFlatFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding,
	String i_delimiter,
	String i_quoter,
	String i_escapeChar,
	String i_lineDelimiter
) throws Exception {

//	// Amorce de la classe
	// Initialisation de la classe d'objet selon le schéma Wild
	WILD_dObject = i_WILD_dObject ;
	WILD_initialize_WildFile(i_WILD_dObject/*** Paramètres à actualiser ***/);
	// Préparation des variables d'invocation (considérées comme champs globaux)
	this.pathToFile = i_pathToFile;
	this.mimeEncoding = i_mimeEncoding;
	this.delimiter = i_delimiter;
	this.quoter = i_quoter;
	this.escapeChar = i_escapeChar;
	this.lineDelimiter = i_lineDelimiter;

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
 * @param i_delimiter	séparateur de colonnes{}
 * @param i_quoter	Caractère de protection de texte{}
 * @param i_escapeChar	caractère d'échappement{}
 * @param i_lineDelimiter	Caractère de retour à la ligne{}
 */

public WildFlatFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding,
	String i_delimiter,
	String i_quoter,
	String i_escapeChar,
	String i_lineDelimiter
) throws Exception{
	if(mimeEncoding==null)mimeEncoding="UTF-8";
	this.WILD_initialize_WildFlatFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,i_delimiter,i_quoter,i_escapeChar,i_lineDelimiter);
}

// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Constructeur, appelle nécessairement WILD_initialize()
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_pathToFile	Chemin vers le fichier{}
 * @param i_delimiter	séparateur de colonnes{}
 * @param i_quoter	Caractère de protection de texte{}
 * @param i_escapeChar	caractère d'échappement{}
 * @param i_lineDelimiter	Caractère de retour à la ligne{}
 */

public WildFlatFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_delimiter,
	String i_quoter,
	String i_escapeChar,
	String i_lineDelimiter
) throws Exception{
	this.WILD_initialize_WildFlatFile(i_WILD_dObject,i_pathToFile,"UTF-8",i_delimiter,i_quoter,i_escapeChar,i_lineDelimiter);
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
 * @param i_columnName	Nom de la colonne{}
 * @param i_colContents	Contenu de la colonne{}
 */
public void addColumn (
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

//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
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
 * @param i_columnName	Nom de la colonne à ajouter{}
 * @param i_columnCompare_dataset	Nom de la colonne comparée, dataset{}
 * @param i_inputJoin	Valeurs de comparaison{}
 * @param i_colContents	Contenu de la colonne{}
 */
public void addColumnJoinBy (
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

//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
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

/*	### NOUVELLE METHODE ###
	Méthode : castFile - Méthode de conversion standard du contenu vers un jeu de données.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Méthode de conversion standard du contenu vers un jeu de données.{ 
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

//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec cast vers WildDataSet (++)
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
 * @param i_col_1	Nom de colonne devant{}
 * @param i_col_2	Nom de colonne derrière{}
 * @param i_link	Opérateur de liaison{}
 */
public void concatenate (
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
 * @param i_col_1	Nom de colonne devant{}
 * @param i_col_2	Nom de colonne derrière{}
 * @param i_link	Opérateur de liaison{}
 * @param i_colContents	Motif à reconnaître pour affectation (filtre){}
 */
public void concatenateFilterBy (
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
 * @param i_columnName	Nom de la colonne{}
 */
public void dropColumn (
	String i_columnName
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

//	//	//	Output	"dropColumn_output1" : Position de la colonne supprimée (Integer)
	//	this.WILD_setOutput("dropColumn_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : dropHeaders - Supprime la ligne des en-têtes de colonnes et les lignes la précédant{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Supprime la ligne des en-têtes de colonnes et les lignes la précédant{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void dropHeaders ()throws Exception{
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

//	//	//	Output	"fillWith_output1" : Chemin du fichier de sortie (String)
	//	this.WILD_setOutput("fillWith_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : getHeader - Renvoie la liste des en-têtes{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Renvoie la liste des en-têtes{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public HashMap<Integer,String> getHeader ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<Integer,String> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération de la variable
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"getHeader_output1" : Nombre de colonnes trouvées (Integer)
	//	this.WILD_setOutput("getHeader_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * @param i_formerValue	Valeur à remplacer{}
 * @param i_newValue	Valeur de remplacement{}
 */
public void replaceValue (
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

//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec sélection et réécriture (++)
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
 * @param i_formerValue	Valeur à remplacer{}
 * @param i_newValue	Valeur de remplacement{}
 * @param i_enrContents	Nouvel enregistrement, sous forme de Map{}
 */
public void replaceValueFilterBy (
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

/*	### NOUVELLE METHODE ###
	Méthode : setEscaper - Définit le caractère d'échappement{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Définit le caractère d'échappement{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_new_char	Nouveau caractère{}
 */
public void setEscaper (
	String i_new_char
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

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : setLineDelimiter - Définit le délimiteur de lignes{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Définit le délimiteur de lignes{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_delimiter	Nouveau délimiteur de ligne{}
 */
public void setLineDelimiter (
	String i_delimiter
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

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : setQuoter - Définit le caractère de protection des chaînes de texte{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Définit le caractère de protection des chaînes de texte{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_new_char	Nouveau caractère{}
 */
public void setQuoter (
	String i_new_char
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

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : setSeparator - Définit le séparateur de colonnes{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Définit le séparateur de colonnes{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_separator	Nouveau séparateur de colonne{}
 */
public void setSeparator (
	String i_separator
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

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : commit - Enregistrement du fichier sur le disque, avec applatissement du WildDataSet{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Enregistrement du fichier sur le disque, avec applatissement du WildDataSet{ 
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

//	//	//	Etape	"1" : poids relatif de 10, Ecriture du fichier
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
	Méthode : castBigFile - Parcours du fichier si gros volume, identification des noms de colonnes{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Parcours du fichier si gros volume, identification des noms de colonnes{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void castBigFile ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Contrôle de l'objet
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Parse du fichier (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"castBigFile_output1" : Map des noms de colonnes (HashMap<Integer, String>)
	//	this.WILD_setOutput("castBigFile_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : readBigFile - Ecriture du fichier si gros volume{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ecriture du fichier si gros volume{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_nRow	Numéro de ligne unique à extraire{}
 * @param i_nRow_from	Numéro de ligne à laquelle commence l'extraction, incluse{}
 * @param i_nRow_to	Numéro de ligne à laquelle s'arrête l'extraction, incluse{}
 * @return	{}
 */
public HashMap<Integer,HashMap<String,Object>> readBigFile (
	Integer i_nRow,
	Integer i_nRow_from,
	Integer i_nRow_to
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<Integer,HashMap<String,Object>> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Contrôle de l'objet
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Identification de la portion à récupérer
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Récupération des enregistrements sélectionnés
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
 * Ecriture du fichier si gros volume{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_nRow	Numéro de ligne unique à extraire {}
 * @return	Map des valeurs récupérées (HashMap<Integer,HashMap<String,Object>>), indexés par leur position {}
 */
 public HashMap<Integer,HashMap<String,Object>> readBigFile (
	Integer i_nRow
	)throws Exception{return readBigFile(i_nRow,null,null);
}
/**
 * Ecriture du fichier si gros volume{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_nRow_from	Numéro de ligne à laquelle commence l'extraction, incluse {}
 * @param i_nRow_to	Numéro de ligne à laquelle s'arrête l'extraction, incluse {}
 * @return	Map des valeurs récupérées (HashMap<Integer,HashMap<String,Object>>), indexés par leur position {}
 */
 public HashMap<Integer,HashMap<String,Object>> readBigFile (
	Integer i_nRow_from,
	Integer i_nRow_to
	)throws Exception{return readBigFile(null,i_nRow_from,i_nRow_to);
}
/*	### NOUVELLE METHODE ###
	Méthode : writeBigFile - Ecriture du fichier si gros volume{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ecriture du fichier si gros volume{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_mapToWrite	La source est une Map{}
 * @param i_appender	A true, écrit à la suite du fichier, sinon le remplace{}
 * @param i_iteratorToWrite	La source est un itérateur JAVA{}
 * @param i_inputToWrite	La source est un flux entrant{}
 */
public void writeBigFile (
	Map<Integer,Map<String, Object>> i_mapToWrite,
	Boolean i_appender,
	Iterator i_iteratorToWrite,
	InputStream i_inputToWrite
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Contrôle de l'objet
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Remplissage du fichier (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"writeBigFile_output1" : Chemin du fichier de sortie (String)
	//	this.WILD_setOutput("writeBigFile_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"writeBigFile_output2" : Nombre de lignes écrites (Integer)
	//	this.WILD_setOutput("writeBigFile_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"writeBigFile_output3" : Nombre de valeur écrites (Integer)
	//	this.WILD_setOutput("writeBigFile_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
/**
 * Ecriture du fichier si gros volume{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_mapToWrite	La source est une Map {}
 * @param i_appender	A true, écrit à la suite du fichier, sinon le remplace {}
 */
 public void writeBigFile (
	Map<Integer,Map<String, Object>> i_mapToWrite,
	Boolean i_appender
	)throws Exception{writeBigFile(i_mapToWrite,i_appender,null,null);
}
/**
 * Ecriture du fichier si gros volume{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_iteratorToWrite	La source est un itérateur JAVA {}
 * @param i_appender	A true, écrit à la suite du fichier, sinon le remplace {}
 */
 public void writeBigFile (
	Iterator i_iteratorToWrite,
	Boolean i_appender
	)throws Exception{writeBigFile(null,i_appender,i_iteratorToWrite,null);
}
/**
 * Ecriture du fichier si gros volume{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_inputToWrite	La source est un flux entrant {}
 * @param i_appender	A true, écrit à la suite du fichier, sinon le remplace {}
 */
 public void writeBigFile (
	InputStream i_inputToWrite,
	Boolean i_appender
	)throws Exception{writeBigFile(null,i_appender,null,i_inputToWrite);
}
/*	### NOUVELLE METHODE ###
	Méthode : deleteBigFile - Suppression du fichier si gros volume{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Suppression du fichier si gros volume{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void deleteBigFile ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Suppression
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
	Méthode : copyToBigFile - Copie du fichier si gros volume{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Copie du fichier si gros volume{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_pathToFile_OUT	Chemin vers le fichier de sortie{}
 * @return	{}
 */
public String copyToBigFile (
	String i_pathToFile_OUT
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Copie
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"copyToBigFile_output1" : Chemin du fichier de sortie (String)
	//	this.WILD_setOutput("copyToBigFile_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : moveToBigFile - déplacement du fichier si gros volume{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * déplacement du fichier si gros volume{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_pathToFile_OUT	Chemin vers le fichier de sortie{}
 * @return	{}
 */
public String moveToBigFile (
	String i_pathToFile_OUT
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Copie
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Suppression
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"moveToBigFile_output1" : Chemin du fichier de sortie (String)
	//	this.WILD_setOutput("moveToBigFile_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : readFile - Ecriture du fichier{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ecriture du fichier{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_nRow	Numéro de ligne unique à extraire{}
 * @param i_nRow_from	Numéro de ligne à laquelle commence l'extraction, incluse{}
 * @param i_nRow_to	Numéro de ligne à laquelle s'arrête l'extraction, incluse{}
 * @return	{}
 */
public HashMap<Integer,HashMap<String,Object>> readFile (
	Integer i_nRow,
	Integer i_nRow_from,
	Integer i_nRow_to
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<Integer,HashMap<String,Object>> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Contrôle de l'objet
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Identification de la portion à récupérer
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Récupération des enregistrements sélectionnés
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
 * Ecriture du fichier{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_nRow	Numéro de ligne unique à extraire {}
 * @return	Map des valeurs récupérées (HashMap<Integer,HashMap<String,Object>>), indexés par leur position {}
 */
 public HashMap<Integer,HashMap<String,Object>> readFile (
	Integer i_nRow
	)throws Exception{return readFile(i_nRow,null,null);
}
/**
 * Ecriture du fichier{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_nRow_from	Numéro de ligne à laquelle commence l'extraction, incluse {}
 * @param i_nRow_to	Numéro de ligne à laquelle s'arrête l'extraction, incluse {}
 * @return	Map des valeurs récupérées (HashMap<Integer,HashMap<String,Object>>), indexés par leur position {}
 */
 public HashMap<Integer,HashMap<String,Object>> readFile (
	Integer i_nRow_from,
	Integer i_nRow_to
	)throws Exception{return readFile(null,i_nRow_from,i_nRow_to);
}
/*	### NOUVELLE METHODE ###
	Méthode : writeFile - Ecriture du fichier{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ecriture du fichier{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_mapToWrite	La source est une Map{}
 * @param i_appender	A true, écrit à la suite du fichier, sinon le remplace{}
 * @param i_iteratorToWrite	La source est un itérateur JAVA{}
 * @param i_inputToWrite	La source est un flux entrant{}
 */
public void writeFile (
	Map<Integer,Map<String, Object>> i_mapToWrite,
	Boolean i_appender,
	Iterator i_iteratorToWrite,
	InputStream i_inputToWrite
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Contrôle de l'objet
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Remplissage du fichier (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"writeFile_output1" : Chemin du fichier de sortie (String)
	//	this.WILD_setOutput("writeFile_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"writeFile_output2" : Nombre de lignes écrites (Integer)
	//	this.WILD_setOutput("writeFile_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"writeFile_output3" : Nombre de valeur écrites (Integer)
	//	this.WILD_setOutput("writeFile_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
/**
 * Ecriture du fichier{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_mapToWrite	La source est une Map {}
 * @param i_appender	A true, écrit à la suite du fichier, sinon le remplace {}
 */
 public void writeFile (
	Map<Integer,Map<String, Object>> i_mapToWrite,
	Boolean i_appender
	)throws Exception{writeFile(i_mapToWrite,i_appender,null,null);
}
/**
 * Ecriture du fichier{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_iteratorToWrite	La source est un itérateur JAVA {}
 * @param i_appender	A true, écrit à la suite du fichier, sinon le remplace {}
 */
 public void writeFile (
	Iterator i_iteratorToWrite,
	Boolean i_appender
	)throws Exception{writeFile(null,i_appender,i_iteratorToWrite,null);
}
/**
 * Ecriture du fichier{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_inputToWrite	La source est un flux entrant {}
 * @param i_appender	A true, écrit à la suite du fichier, sinon le remplace {}
 */
 public void writeFile (
	InputStream i_inputToWrite,
	Boolean i_appender
	)throws Exception{writeFile(null,i_appender,null,i_inputToWrite);
}
/*	### NOUVELLE METHODE ###
	Méthode : findEqualBigFile - Récupération des numéros de lignes des enregistrements, correspondant à une égalité stricte, selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une égalité stricte, selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne{}
 * @param i_colValue	Valeur d'objet de comparaison{}
 * @param i_nullSens	Sensible aux valeurs vides{}
 * @param i_caseSens	Sensible à la casse{}
 * @param i_specSens	Sensible aux caractères spéciaux{}
 * @return	{}
 */
public Integer[] findEqualBigFile (
	String i_colName,
	Object i_colValue,
	Boolean i_nullSens,
	Boolean i_caseSens,
	Boolean i_specSens
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant à une égalité stricte, selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne {}
 * @param i_colValue	Valeur d'objet de comparaison {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findEqualBigFile (
	String i_colName,
	Object i_colValue
	)throws Exception{
return findEqualBigFile(i_colName,i_colValue,false,false,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findContainBigFile - Récupération des numéros de lignes des enregistrements, correspondant à une condition de contenu (la cible contient le terme recherché), selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non. Ne porte que sur les chaînes de caractère.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition de contenu (la cible contient le terme recherché), selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non. Ne porte que sur les chaînes de caractère.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne{}
 * @param i_colValue	Valeur d'objet de comparaison{}
 * @param i_nullSens	Sensible aux valeurs vides{}
 * @param i_caseSens	Sensible à la casse{}
 * @param i_specSens	Sensible aux caractères spéciaux{}
 * @return	{}
 */
public Integer[] findContainBigFile (
	String i_colName,
	Object i_colValue,
	Boolean i_nullSens,
	Boolean i_caseSens,
	Boolean i_specSens
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition de contenu (la cible contient le terme recherché), selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non. Ne porte que sur les chaînes de caractère.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne {}
 * @param i_colValue	Valeur d'objet de comparaison {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findContainBigFile (
	String i_colName,
	Object i_colValue
	)throws Exception{
return findContainBigFile(i_colName,i_colValue,false,false,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findRegexBigFile - Récupération des numéros de lignes des enregistrements, correspondant à un terme recherché (la cible répond à une expression régulière comportant le terme recherché).{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à un terme recherché (la cible répond à une expression régulière comportant le terme recherché).{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne{}
 * @param i_colRegexp	Expression régulière testée{}
 * @param i_nullSens	Sensible aux valeurs vides{}
 * @param i_caseSens	Sensible à la casse{}
 * @param i_specSens	Sensible aux caractères spéciaux{}
 * @return	{}
 */
public Integer[] findRegexBigFile (
	String i_colName,
	String i_colRegexp,
	Boolean i_nullSens,
	Boolean i_caseSens,
	Boolean i_specSens
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant à un terme recherché (la cible répond à une expression régulière comportant le terme recherché).{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne {}
 * @param i_colRegexp	Expression régulière testée {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findRegexBigFile (
	String i_colName,
	String i_colRegexp
	)throws Exception{
return findRegexBigFile(i_colName,i_colRegexp,false,false,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findWithinBigFile - Récupération des numéros de lignes des enregistrements, dont une valeur précise est contenue entre deux bornes (incluses). Pour les champs de type chaîne de caractères, l’ordre alphabétique est retenu (sensible à la casse, pas de suppression des caractères non signifiants). Une valeur « vide » signifie une absence de borne.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, dont une valeur précise est contenue entre deux bornes (incluses). Pour les champs de type chaîne de caractères, l’ordre alphabétique est retenu (sensible à la casse, pas de suppression des caractères non signifiants). Une valeur « vide » signifie une absence de borne.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne{}
 * @param i_minValue	Valeur inférieure{}
 * @param i_minExclude	Est exclu par le bas ?{}
 * @param i_maxValue	Valeur supérieure{}
 * @param i_maxExclude	Est exclu par le haut ?{}
 * @return	{}
 */
public Integer[] findWithinBigFile (
	String i_colName,
	Object i_minValue,
	Boolean i_minExclude,
	Object i_maxValue,
	Boolean i_maxExclude
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, dont une valeur précise est contenue entre deux bornes (incluses). Pour les champs de type chaîne de caractères, l’ordre alphabétique est retenu (sensible à la casse, pas de suppression des caractères non signifiants). Une valeur « vide » signifie une absence de borne.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne {}
 * @param i_minValue	Valeur inférieure {}
 * @param i_maxValue	Valeur supérieure {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findWithinBigFile (
	String i_colName,
	Object i_minValue,
	Object i_maxValue
	)throws Exception{
return findWithinBigFile(i_colName,i_minValue,false,i_maxValue,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findCompareBigFile - Récupération des numéros de lignes des enregistrements, correspondant à une comparaison mathématique (opérateur de comparaison mathématique : supériorité / infériorité, égalité -  numérique ou date). Choix de l’exclusion ou de l’inclusion des bornes.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une comparaison mathématique (opérateur de comparaison mathématique : supériorité / infériorité, égalité -  numérique ou date). Choix de l’exclusion ou de l’inclusion des bornes.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne{}
 * @param i_minValue	Valeur inférieure{}
 * @param i_minExclude	Est exclu par le bas ?{}
 * @param i_maxValue	Valeur supérieure{}
 * @param i_maxExclude	Est exclu par le haut ?{}
 * @return	{}
 */
public Integer[] findCompareBigFile (
	String i_colName,
	Object i_minValue,
	Boolean i_minExclude,
	Object i_maxValue,
	Boolean i_maxExclude
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant à une comparaison mathématique (opérateur de comparaison mathématique : supériorité / infériorité, égalité -  numérique ou date). Choix de l’exclusion ou de l’inclusion des bornes.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne {}
 * @param i_minValue	Valeur inférieure {}
 * @param i_maxValue	Valeur supérieure {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findCompareBigFile (
	String i_colName,
	Object i_minValue,
	Object i_maxValue
	)throws Exception{
return findCompareBigFile(i_colName,i_minValue,false,i_maxValue,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findLinesBigFile - Récupération des numéros de lignes des enregistrements, combinant l’ensemble des conditions précédemment listées. Une liste des indexations des conditions est fournie  en Tableau 1, et permet le stockage des conditions dans une table de paramètres. Chacune des conditions répond soit à une condition OU, soit à une condition ET (opérateur de combinaison de condition), à l’exception de la première condition listée. Chacune des conditions répond soit à une condition positive (on sélection les numéros de lignes répondant à la condition) soit négative (on exclut sélection les numéros de lignes répondant à la condition). Une condition négative est préfixée du caractère « ! ».{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, combinant l’ensemble des conditions précédemment listées. Une liste des indexations des conditions est fournie  en Tableau 1, et permet le stockage des conditions dans une table de paramètres. Chacune des conditions répond soit à une condition OU, soit à une condition ET (opérateur de combinaison de condition), à l’exception de la première condition listée. Chacune des conditions répond soit à une condition positive (on sélection les numéros de lignes répondant à la condition) soit négative (on exclut sélection les numéros de lignes répondant à la condition). Une condition négative est préfixée du caractère « ! ».{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_conditionList	liste des conditions{}
 * @return	{}
 */
public Integer[] findLinesBigFile (
	Map<Integer, Map<String,Object>> i_conditionList
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
	Méthode : calculateBigFile - Calcul de statistiques sur les valeurs des enregistrements, combinant l’ensemble des conditions précédemment listées. La liste des statistiques retenues est présentée en Tableau 1. Le Tableau 2 plus loin renvoie à un index des conditions pour implémentation.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Calcul de statistiques sur les valeurs des enregistrements, combinant l’ensemble des conditions précédemment listées. La liste des statistiques retenues est présentée en Tableau 1. Le Tableau 2 plus loin renvoie à un index des conditions pour implémentation.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Liste des conditions{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre associé à la statistique{}
 * @return	{}
 */
public Map<String, Object> calculateBigFile (
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Map<String, Object> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 10, Contrôle des flux
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
 * Calcul de statistiques sur les valeurs des enregistrements, combinant l’ensemble des conditions précédemment listées. La liste des statistiques retenues est présentée en Tableau 1. Le Tableau 2 plus loin renvoie à un index des conditions pour implémentation.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @return	Tableau de valeurs des résultats (Map<String, Object>) {}
 */
 public Map<String, Object> calculateBigFile (
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey
	)throws Exception{
return calculateBigFile(i_colName,i_conditionList,i_statKey,null);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findTopBigFile - Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Liste des conditions{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre associé à la statistique{}
 * @param i_nbTop	Nombre de valeurs du « top »{}
 * @return	{}
 */
public Integer[] findTopBigFile (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam,
	Integer i_nbTop
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colGroup	Nom de la colonne de groupement {}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbTop	Nombre de valeurs du « top » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findTopBigFile (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbTop
	)throws Exception{
return findTopBigFile(i_colGroup,i_colName,i_conditionList,i_statKey,null,i_nbTop);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findBottomBigFile - Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Liste des conditions{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre associé à la statistique{}
 * @param i_nbBottom	Nombre de valeurs du « bottom »{}
 * @return	{}
 */
public Integer[] findBottomBigFile (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam,
	Integer i_nbBottom
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colGroup	Nom de la colonne de groupement {}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbBottom	Nombre de valeurs du « bottom » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findBottomBigFile (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbBottom
	)throws Exception{
return findBottomBigFile(i_colGroup,i_colName,i_conditionList,i_statKey,null,i_nbBottom);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findTopPercentBigFile - Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Liste des conditions{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre associé à la statistique{}
 * @param i_nbTop	Percentile du « top »{}
 * @return	{}
 */
public Integer[] findTopPercentBigFile (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam,
	Integer i_nbTop
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colGroup	Nom de la colonne de groupement {}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbTop	Percentile du « top » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findTopPercentBigFile (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbTop
	)throws Exception{
return findTopPercentBigFile(i_colGroup,i_colName,i_conditionList,i_statKey,null,i_nbTop);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findBottomPercentBigFile - Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Liste des conditions{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre associé à la statistique{}
 * @param i_nbBottom	Percentile du « bottom »{}
 * @return	{}
 */
public Integer[] findBottomPercentBigFile (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam,
	Integer i_nbBottom
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colGroup	Nom de la colonne de groupement {}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbBottom	Percentile du « bottom » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findBottomPercentBigFile (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbBottom
	)throws Exception{
return findBottomPercentBigFile(i_colGroup,i_colName,i_conditionList,i_statKey,null,i_nbBottom);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findStatCompareBigFile - Récupération des numéros de lignes des enregistrements, correspondant à une condition mathématique sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « comparables » (C). Les opérateurs utilisée sont supériorité / infériorité (option inclusion / exclusion) et égalité.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition mathématique sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « comparables » (C). Les opérateurs utilisée sont supériorité / infériorité (option inclusion / exclusion) et égalité.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Conditions de recherche sur la colonne avant calcul de la statistique{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre de la statistique{}
 * @param i_minValue	Valeur inférieure{}
 * @param i_minExclude	Est exclu par le bas ?{}
 * @param i_maxValue	Valeur supérieure{}
 * @param i_maxExclude	Est exclu par le haut ?{}
 * @return	{}
 */
public Integer[] findStatCompareBigFile (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam,
	Object i_minValue,
	Boolean i_minExclude,
	Object i_maxValue,
	Boolean i_maxExclude
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition mathématique sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « comparables » (C). Les opérateurs utilisée sont supériorité / infériorité (option inclusion / exclusion) et égalité.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colGroup	Nom de la colonne de groupement {}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Conditions de recherche sur la colonne avant calcul de la statistique {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_minValue	Valeur inférieure {}
 * @param i_maxValue	Valeur supérieure {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findStatCompareBigFile (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Object i_minValue,
	Object i_maxValue
	)throws Exception{
return findStatCompareBigFile(i_colGroup,i_colName,i_conditionList,i_statKey,null,i_minValue,false,i_maxValue,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findRecordsBigFile - Récupération des enregistrements sous forme de WildDataSet (Map JAVA), pour l’ensemble des conditions opérables sur le fichier.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des enregistrements sous forme de WildDataSet (Map JAVA), pour l’ensemble des conditions opérables sur le fichier.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_conditionList	Conditions de recherche{}
 * @return	{}
 */
public Map<Integer,Map<String,Object>> findRecordsBigFile (
	Map<Integer, Map<String,Object>> i_conditionList
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Map<Integer,Map<String,Object>> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
	Méthode : saveRecordsBigFile - Récupération des enregistrements sous forme de fichier CSV, pour l’ensemble des conditions opérables sur le fichier.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des enregistrements sous forme de fichier CSV, pour l’ensemble des conditions opérables sur le fichier.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_conditionList	Conditions de recherche{}
 * @return	{}
 */
public String saveRecordsBigFile (
	Map<Integer, Map<String,Object>> i_conditionList
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
	Méthode : findEqual - Récupération des numéros de lignes des enregistrements, correspondant à une égalité stricte, selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une égalité stricte, selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne{}
 * @param i_colValue	Valeur d'objet de comparaison{}
 * @param i_nullSens	Sensible aux valeurs vides{}
 * @param i_caseSens	Sensible à la casse{}
 * @param i_specSens	Sensible aux caractères spéciaux{}
 * @return	{}
 */
public Integer[] findEqual (
	String i_colName,
	Object i_colValue,
	Boolean i_nullSens,
	Boolean i_caseSens,
	Boolean i_specSens
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant à une égalité stricte, selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne {}
 * @param i_colValue	Valeur d'objet de comparaison {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findEqual (
	String i_colName,
	Object i_colValue
	)throws Exception{
return findEqual(i_colName,i_colValue,false,false,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findContain - Récupération des numéros de lignes des enregistrements, correspondant à une condition de contenu (la cible contient le terme recherché), selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non. Ne porte que sur les chaînes de caractère.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition de contenu (la cible contient le terme recherché), selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non. Ne porte que sur les chaînes de caractère.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne{}
 * @param i_colValue	Valeur d'objet de comparaison{}
 * @param i_nullSens	Sensible aux valeurs vides{}
 * @param i_caseSens	Sensible à la casse{}
 * @param i_specSens	Sensible aux caractères spéciaux{}
 * @return	{}
 */
public Integer[] findContain (
	String i_colName,
	Object i_colValue,
	Boolean i_nullSens,
	Boolean i_caseSens,
	Boolean i_specSens
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition de contenu (la cible contient le terme recherché), selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non. Ne porte que sur les chaînes de caractère.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne {}
 * @param i_colValue	Valeur d'objet de comparaison {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findContain (
	String i_colName,
	Object i_colValue
	)throws Exception{
return findContain(i_colName,i_colValue,false,false,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findRegex - Récupération des numéros de lignes des enregistrements, correspondant à un terme recherché (la cible répond à une expression régulière comportant le terme recherché).{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à un terme recherché (la cible répond à une expression régulière comportant le terme recherché).{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne{}
 * @param i_colRegexp	Expression régulière testée{}
 * @param i_nullSens	Sensible aux valeurs vides{}
 * @param i_caseSens	Sensible à la casse{}
 * @param i_specSens	Sensible aux caractères spéciaux{}
 * @return	{}
 */
public Integer[] findRegex (
	String i_colName,
	String i_colRegexp,
	Boolean i_nullSens,
	Boolean i_caseSens,
	Boolean i_specSens
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant à un terme recherché (la cible répond à une expression régulière comportant le terme recherché).{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne {}
 * @param i_colRegexp	Expression régulière testée {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findRegex (
	String i_colName,
	String i_colRegexp
	)throws Exception{
return findRegex(i_colName,i_colRegexp,false,false,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findWithin - Récupération des numéros de lignes des enregistrements, dont une valeur précise est contenue entre deux bornes (incluses). Pour les champs de type chaîne de caractères, l’ordre alphabétique est retenu (sensible à la casse, pas de suppression des caractères non signifiants). Une valeur « vide » signifie une absence de borne.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, dont une valeur précise est contenue entre deux bornes (incluses). Pour les champs de type chaîne de caractères, l’ordre alphabétique est retenu (sensible à la casse, pas de suppression des caractères non signifiants). Une valeur « vide » signifie une absence de borne.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne{}
 * @param i_minValue	Valeur inférieure{}
 * @param i_minExclude	Est exclu par le bas ?{}
 * @param i_maxValue	Valeur supérieure{}
 * @param i_maxExclude	Est exclu par le haut ?{}
 * @return	{}
 */
public Integer[] findWithin (
	String i_colName,
	Object i_minValue,
	Boolean i_minExclude,
	Object i_maxValue,
	Boolean i_maxExclude
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, dont une valeur précise est contenue entre deux bornes (incluses). Pour les champs de type chaîne de caractères, l’ordre alphabétique est retenu (sensible à la casse, pas de suppression des caractères non signifiants). Une valeur « vide » signifie une absence de borne.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne {}
 * @param i_minValue	Valeur inférieure {}
 * @param i_maxValue	Valeur supérieure {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findWithin (
	String i_colName,
	Object i_minValue,
	Object i_maxValue
	)throws Exception{
return findWithin(i_colName,i_minValue,false,i_maxValue,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findCompare - Récupération des numéros de lignes des enregistrements, correspondant à une comparaison mathématique (opérateur de comparaison mathématique : supériorité / infériorité, égalité -  numérique ou date). Choix de l’exclusion ou de l’inclusion des bornes.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une comparaison mathématique (opérateur de comparaison mathématique : supériorité / infériorité, égalité -  numérique ou date). Choix de l’exclusion ou de l’inclusion des bornes.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne{}
 * @param i_minValue	Valeur inférieure{}
 * @param i_minExclude	Est exclu par le bas ?{}
 * @param i_maxValue	Valeur supérieure{}
 * @param i_maxExclude	Est exclu par le haut ?{}
 * @return	{}
 */
public Integer[] findCompare (
	String i_colName,
	Object i_minValue,
	Boolean i_minExclude,
	Object i_maxValue,
	Boolean i_maxExclude
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant à une comparaison mathématique (opérateur de comparaison mathématique : supériorité / infériorité, égalité -  numérique ou date). Choix de l’exclusion ou de l’inclusion des bornes.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne {}
 * @param i_minValue	Valeur inférieure {}
 * @param i_maxValue	Valeur supérieure {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findCompare (
	String i_colName,
	Object i_minValue,
	Object i_maxValue
	)throws Exception{
return findCompare(i_colName,i_minValue,false,i_maxValue,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findLines - Récupération des numéros de lignes des enregistrements, combinant l’ensemble des conditions précédemment listées. Une liste des indexations des conditions est fournie  en Tableau 1, et permet le stockage des conditions dans une table de paramètres. Chacune des conditions répond soit à une condition OU, soit à une condition ET (opérateur de combinaison de condition), à l’exception de la première condition listée. Chacune des conditions répond soit à une condition positive (on sélection les numéros de lignes répondant à la condition) soit négative (on exclut sélection les numéros de lignes répondant à la condition). Une condition négative est préfixée du caractère « ! ».{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, combinant l’ensemble des conditions précédemment listées. Une liste des indexations des conditions est fournie  en Tableau 1, et permet le stockage des conditions dans une table de paramètres. Chacune des conditions répond soit à une condition OU, soit à une condition ET (opérateur de combinaison de condition), à l’exception de la première condition listée. Chacune des conditions répond soit à une condition positive (on sélection les numéros de lignes répondant à la condition) soit négative (on exclut sélection les numéros de lignes répondant à la condition). Une condition négative est préfixée du caractère « ! ».{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_conditionList	liste des conditions{}
 * @return	{}
 */
public Integer[] findLines (
	Map<Integer, Map<String,Object>> i_conditionList
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
	Méthode : calculate - Calcul de statistiques sur les valeurs des enregistrements, combinant l’ensemble des conditions précédemment listées. La liste des statistiques retenues est présentée en Tableau 1. Le Tableau 2 plus loin renvoie à un index des conditions pour implémentation.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Calcul de statistiques sur les valeurs des enregistrements, combinant l’ensemble des conditions précédemment listées. La liste des statistiques retenues est présentée en Tableau 1. Le Tableau 2 plus loin renvoie à un index des conditions pour implémentation.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Liste des conditions{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre associé à la statistique{}
 * @return	{}
 */
public Map<String, Object> calculate (
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Map<String, Object> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 10, Contrôle des flux
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
 * Calcul de statistiques sur les valeurs des enregistrements, combinant l’ensemble des conditions précédemment listées. La liste des statistiques retenues est présentée en Tableau 1. Le Tableau 2 plus loin renvoie à un index des conditions pour implémentation.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @return	Tableau de valeurs des résultats (Map<String, Object>) {}
 */
 public Map<String, Object> calculate (
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey
	)throws Exception{
return calculate(i_colName,i_conditionList,i_statKey,null);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findTop - Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Liste des conditions{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre associé à la statistique{}
 * @param i_nbTop	Nombre de valeurs du « top »{}
 * @return	{}
 */
public Integer[] findTop (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam,
	Integer i_nbTop
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colGroup	Nom de la colonne de groupement {}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbTop	Nombre de valeurs du « top » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findTop (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbTop
	)throws Exception{
return findTop(i_colGroup,i_colName,i_conditionList,i_statKey,null,i_nbTop);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findBottom - Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Liste des conditions{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre associé à la statistique{}
 * @param i_nbBottom	Nombre de valeurs du « bottom »{}
 * @return	{}
 */
public Integer[] findBottom (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam,
	Integer i_nbBottom
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colGroup	Nom de la colonne de groupement {}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbBottom	Nombre de valeurs du « bottom » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findBottom (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbBottom
	)throws Exception{
return findBottom(i_colGroup,i_colName,i_conditionList,i_statKey,null,i_nbBottom);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findTopPercent - Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Liste des conditions{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre associé à la statistique{}
 * @param i_nbTop	Percentile du « top »{}
 * @return	{}
 */
public Integer[] findTopPercent (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam,
	Integer i_nbTop
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colGroup	Nom de la colonne de groupement {}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbTop	Percentile du « top » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findTopPercent (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbTop
	)throws Exception{
return findTopPercent(i_colGroup,i_colName,i_conditionList,i_statKey,null,i_nbTop);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findBottomPercent - Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Liste des conditions{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre associé à la statistique{}
 * @param i_nbBottom	Percentile du « bottom »{}
 * @return	{}
 */
public Integer[] findBottomPercent (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam,
	Integer i_nbBottom
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colGroup	Nom de la colonne de groupement {}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbBottom	Percentile du « bottom » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findBottomPercent (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbBottom
	)throws Exception{
return findBottomPercent(i_colGroup,i_colName,i_conditionList,i_statKey,null,i_nbBottom);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findStatCompare - Récupération des numéros de lignes des enregistrements, correspondant à une condition mathématique sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « comparables » (C). Les opérateurs utilisée sont supériorité / infériorité (option inclusion / exclusion) et égalité.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition mathématique sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « comparables » (C). Les opérateurs utilisée sont supériorité / infériorité (option inclusion / exclusion) et égalité.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Conditions de recherche sur la colonne avant calcul de la statistique{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre de la statistique{}
 * @param i_minValue	Valeur inférieure{}
 * @param i_minExclude	Est exclu par le bas ?{}
 * @param i_maxValue	Valeur supérieure{}
 * @param i_maxExclude	Est exclu par le haut ?{}
 * @return	{}
 */
public Integer[] findStatCompare (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam,
	Object i_minValue,
	Boolean i_minExclude,
	Object i_maxValue,
	Boolean i_maxExclude
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
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
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition mathématique sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « comparables » (C). Les opérateurs utilisée sont supériorité / infériorité (option inclusion / exclusion) et égalité.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colGroup	Nom de la colonne de groupement {}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Conditions de recherche sur la colonne avant calcul de la statistique {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_minValue	Valeur inférieure {}
 * @param i_maxValue	Valeur supérieure {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findStatCompare (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Object i_minValue,
	Object i_maxValue
	)throws Exception{
return findStatCompare(i_colGroup,i_colName,i_conditionList,i_statKey,null,i_minValue,false,i_maxValue,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findRecords - Récupération des enregistrements sous forme de WildDataSet (Map JAVA), pour l’ensemble des conditions opérables sur le fichier.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des enregistrements sous forme de WildDataSet (Map JAVA), pour l’ensemble des conditions opérables sur le fichier.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_conditionList	Conditions de recherche{}
 * @return	{}
 */
public Map<Integer,Map<String,Object>> findRecords (
	Map<Integer, Map<String,Object>> i_conditionList
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Map<Integer,Map<String,Object>> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
	Méthode : saveRecords - Récupération des enregistrements sous forme de fichier CSV, pour l’ensemble des conditions opérables sur le fichier.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des enregistrements sous forme de fichier CSV, pour l’ensemble des conditions opérables sur le fichier.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_conditionList	Conditions de recherche{}
 * @return	{}
 */
public String saveRecords (
	Map<Integer, Map<String,Object>> i_conditionList
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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

