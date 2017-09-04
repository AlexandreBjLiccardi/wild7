
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

// Dépendances, bibliothèques JAVA par exemple.
import java.util.HashMap;
import java.util.List;


/**
 * Code généré automatiquement par l'outil Wild
 * Fichier texte délimité, norme française
 */
public class WildFrCsvFile extends WildFlatFile{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildFrCsvFile(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation

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
protected void WILD_initialize_WildFrCsvFile(
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
	WILD_initialize_WildFlatFile(i_WILD_dObject/*** Paramètres à actualiser ***/);
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

public WildFrCsvFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding,
	String i_delimiter,
	String i_quoter,
	String i_escapeChar,
	String i_lineDelimiter
) throws Exception{
	if(mimeEncoding==null)mimeEncoding="UTF-8";
	if(delimiter==null)delimiter=";";
	if(quoter==null)quoter=""";
	if(escapeChar==null)escapeChar="ESC8";
	if(lineDelimiter==null)lineDelimiter="ESC8n";
	this.WILD_initialize_WildFrCsvFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,i_delimiter,i_quoter,i_escapeChar,i_lineDelimiter);
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

public WildFrCsvFile(
	WildObject i_WILD_dObject,
	String i_pathToFile
) throws Exception{
	this.WILD_initialize_WildFrCsvFile(i_WILD_dObject,i_pathToFile,"UTF-8",";",""","ESC8","ESC8n");
}

}

