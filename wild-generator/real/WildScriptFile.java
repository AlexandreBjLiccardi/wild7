
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

// Dépendances, bibliothèques JAVA par exemple.
import java.util.HashMap;
import java.util.List;


/**
 * Code généré automatiquement par l'outil Wild
 * Fichier de script, contenant plusieurs ordres interprétés comme des lignes ou des enregistrements, une seule colonne ("command').
 */
public abstract class WildScriptFile extends WildFile{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildScriptFile(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected List<Class> canBeProcessed; // Classes pouvant être traitées
	protected HashMap<String, Object> results; // Résultats de traitement
	protected String currentResult; // Résultats du traitement en cours
	protected String currentProcessed; // Identifiant du fichier en cours de traitement (à défaut

// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	protected String pathToFile;	//Chemin vers le fichier{}
	protected String mimeEncoding;	//Encodage du fichier{}
	protected String commenter;	//Caractère(s) de commentaire{}
	protected String commenterBegin;	//Caractère(s) de commentaire multi-ligne, début{}
	protected String commenterEnd;	//Caractère(s) de commentaire multi-ligne, fin{}
	protected String commandEnd;	//Caractère(s) de fin de commande{}


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
 * @param i_commenter	Caractère(s) de commentaire{}
 * @param i_commenterBegin	Caractère(s) de commentaire multi-ligne, début{}
 * @param i_commenterEnd	Caractère(s) de commentaire multi-ligne, fin{}
 * @param i_commandEnd	Caractère(s) de fin de commande{}
 */
protected void WILD_initialize_WildScriptFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding,
	String i_commenter,
	String i_commenterBegin,
	String i_commenterEnd,
	String i_commandEnd
) throws Exception {

//	// Amorce de la classe
	// Initialisation de la classe d'objet selon le schéma Wild
	WILD_dObject = i_WILD_dObject ;
	WILD_initialize_WildFile(i_WILD_dObject/*** Paramètres à actualiser ***/);
	// Préparation des variables d'invocation (considérées comme champs globaux)
	this.pathToFile = i_pathToFile;
	this.mimeEncoding = i_mimeEncoding;
	this.commenter = i_commenter;
	this.commenterBegin = i_commenterBegin;
	this.commenterEnd = i_commenterEnd;
	this.commandEnd = i_commandEnd;

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
 * @param i_commenter	Caractère(s) de commentaire{}
 * @param i_commenterBegin	Caractère(s) de commentaire multi-ligne, début{}
 * @param i_commenterEnd	Caractère(s) de commentaire multi-ligne, fin{}
 * @param i_commandEnd	Caractère(s) de fin de commande{}
 */

public WildScriptFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding,
	String i_commenter,
	String i_commenterBegin,
	String i_commenterEnd,
	String i_commandEnd
) throws Exception{
	if(mimeEncoding==null)mimeEncoding="UTF-8";
	this.WILD_initialize_WildScriptFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,i_commenter,i_commenterBegin,i_commenterEnd,i_commandEnd);
}

// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Constructeur, appelle nécessairement WILD_initialize()
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_pathToFile	Chemin vers le fichier{}
 * @param i_commenter	Caractère(s) de commentaire{}
 * @param i_commenterBegin	Caractère(s) de commentaire multi-ligne, début{}
 * @param i_commenterEnd	Caractère(s) de commentaire multi-ligne, fin{}
 * @param i_commandEnd	Caractère(s) de fin de commande{}
 */

public WildScriptFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_commenter,
	String i_commenterBegin,
	String i_commenterEnd,
	String i_commandEnd
) throws Exception{
	this.WILD_initialize_WildScriptFile(i_WILD_dObject,i_pathToFile,"UTF-8",i_commenter,i_commenterBegin,i_commenterEnd,i_commandEnd);
}

/*	### NOUVELLE METHODE ###
	Méthode : setCommandEnd - Détermination du (des) caractères de fin d'ordre à exécuter{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Détermination du (des) caractères de fin d'ordre à exécuter{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_new_char	Nouveau caractère{}
 */
public void setCommandEnd (
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
	Méthode : setCommenter - Détermination du (des) caractères de commentaire pour une ligne{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Détermination du (des) caractères de commentaire pour une ligne{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_new_char	Nouveau caractère{}
 */
public void setCommenter (
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
	Méthode : setCommenterBegin - Détermination du (des) caractères de commentaire multi-ligne, début{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Détermination du (des) caractères de commentaire multi-ligne, début{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_new_char	Nouveau caractère{}
 */
public void setCommenterBegin (
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
	Méthode : setCommenterEnd - Détermination du (des) caractères de commentaire multi-ligne, fin{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Détermination du (des) caractères de commentaire multi-ligne, fin{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_new_char	Nouveau caractère{}
 */
public void setCommenterEnd (
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
	Méthode : setLanguageName - Détermination du nom du langage{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Détermination du nom du langage{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_new_var	Nouveau nom de langage{}
 */
public void setLanguageName (
	String i_new_var
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
	Méthode : setProcessed - Détermination de la liste des WILDObjects susceptibles d'être traités par le script{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Détermination de la liste des WILDObjects susceptibles d'être traités par le script{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_new_var	Nouveau nom de classe{}
 */
public void setProcessed (
	String i_new_var
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

//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
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
	Méthode : uncommentDataset - Suppression des commentaires dans la liste d'ordre à exécuter (WildDataSet){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Suppression des commentaires dans la liste d'ordre à exécuter (WildDataSet){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void uncommentDataset ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Parcours du jeu de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"uncommentDataset_output1" : Nombre de lignes affectées (Integer)
	//	this.WILD_setOutput("uncommentDataset_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : unnerve - Désactivation d'une portion de code{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Désactivation d'une portion de code{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_fromToken_str	Motif où le commentaire commence{}
 * @param i_toToken_str	Motif où le commentaire finit{}
 * @param i_fromToken_int	Ligne où le commentaire commence{}
 * @param i_toToken_int	Ligne où le commentaire finit{}
 */
public void unnerve (
	String i_fromToken_str,
	String i_toToken_str,
	Integer i_fromToken_int,
	Integer i_toToken_int
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Parcours du jeu de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"unnerve_output1" : Nombre de lignes affectées (Integer)
	//	this.WILD_setOutput("unnerve_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
/**
 * Désactivation d'une portion de code{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fromToken_str	Motif où le commentaire commence {}
 * @param i_toToken_str	Motif où le commentaire finit {}
 */
 public void unnerve (
	String i_fromToken_str,
	String i_toToken_str
	)throws Exception{unnerve(i_fromToken_str,i_toToken_str,null,null);
}
/**
 * Désactivation d'une portion de code{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fromToken_int	Ligne où le commentaire commence {}
 * @param i_toToken_int	Ligne où le commentaire finit {}
 */
 public void unnerve (
	Integer i_fromToken_int,
	Integer i_toToken_int
	)throws Exception{unnerve(null,null,i_fromToken_int,i_toToken_int);
}
/*	### NOUVELLE METHODE ###
	Méthode : nerve - Activation d'une portion de code{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Activation d'une portion de code{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_fromToken_str	Motif où le commentaire commence{}
 * @param i_toToken_str	Motif où le commentaire finit{}
 * @param i_fromToken_int	Ligne où le commentaire commence{}
 * @param i_toToken_int	Ligne où le commentaire finit{}
 */
public void nerve (
	String i_fromToken_str,
	String i_toToken_str,
	Integer i_fromToken_int,
	Integer i_toToken_int
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Parcours du jeu de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"nerve_output1" : Nombre de lignes affectées (Integer)
	//	this.WILD_setOutput("nerve_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
/**
 * Activation d'une portion de code{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fromToken_str	Motif où le commentaire commence {}
 * @param i_toToken_str	Motif où le commentaire finit {}
 */
 public void nerve (
	String i_fromToken_str,
	String i_toToken_str
	)throws Exception{nerve(i_fromToken_str,i_toToken_str,null,null);
}
/**
 * Activation d'une portion de code{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fromToken_int	Ligne où le commentaire commence {}
 * @param i_toToken_int	Ligne où le commentaire finit {}
 */
 public void nerve (
	Integer i_fromToken_int,
	Integer i_toToken_int
	)throws Exception{nerve(null,null,i_fromToken_int,i_toToken_int);
}
/*	### NOUVELLE METHODE ###
	Méthode : alterCode - Modification d'une partie de code{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Modification d'une partie de code{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_toReplace	Ce qu'il faut remplacer{}
 * @param i_replaceBy	Ce par quoi on remplace{}
 * @param i_varFilter	Structure de sélection de variables en amont (###VAR### est remplacé par la liste de variables){}
 * @param i_okVars	Variables permettant un changement{}
 * @param i_koVars	Variables ne permettant pas de changements{}
 */
public void alterCode (
	String i_toReplace,
	String i_replaceBy,
	List<String> i_varFilter,
	List<String> i_okVars,
	List<String> i_koVars
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Parcours du jeu de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"alterCode_output1" : Nombre de lignes affectées (Integer)
	//	this.WILD_setOutput("alterCode_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
/**
 * Modification d'une partie de code{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_toReplace	Ce qu'il faut remplacer {}
 * @param i_replaceBy	Ce par quoi on remplace {}
 */
 public void alterCode (
	String i_toReplace,
	String i_replaceBy
	)throws Exception{alterCode(i_toReplace,i_replaceBy,null,null,null);
}
}

