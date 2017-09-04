
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

// Dépendances, bibliothèques JAVA par exemple.
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


/**
 * Code généré automatiquement par l'outil Wild
 * Jeu de données "virtuel". Traitement par HashMap, plutôt réservé aux "petits volumes".
 */
public class WildDataSet extends WildClass{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildDataSet(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected HashMap<Integer,String> headerNames; // headerNames (HashMap<Integer,String>)

// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	protected HashMap<Integer,HashMap<String,Object>> mapContents;	//Contenu à affecter d'office{}


// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***

// CE CONSTRUCTEUR DOIT ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Fonction d'initialisation, commune à tous les constructeurs.
 * "Constructeur unique"
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_mapContents	Contenu à affecter d'office{}
 */
protected void WILD_initialize_WildDataSet(
	WildObject i_WILD_dObject,
	HashMap<Integer,HashMap<String,Object>> i_mapContents
) throws Exception {

//	// Amorce de la classe
	// Initialisation de la classe d'objet selon le schéma Wild
	WILD_dObject = i_WILD_dObject ;
	WILD_initialize_WildClass(i_WILD_dObject/*** Paramètres à actualiser ***/);
	// Préparation des variables d'invocation (considérées comme champs globaux)
	this.mapContents = i_mapContents;

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
 * @param i_mapContents	Contenu à affecter d'office{}
 */

public WildDataSet(
	WildObject i_WILD_dObject,
	HashMap<Integer,HashMap<String,Object>> i_mapContents
) throws Exception{
	this.WILD_initialize_WildDataSet(i_WILD_dObject,i_mapContents);
}

/*	### NOUVELLE METHODE ###
	Méthode : add - Ajout d'un enregistrement.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ajout d'un enregistrement.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_enrContents	Nouvel enregistrement, sous forme de Map{}
 */
public void add (
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
 * @param i_filter	Motif à reconnaître pour suppression (filtre){}
 */
public void drop (
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
	Méthode : dropFilter - Suppression des enregistrements répondant à une condition.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Suppression des enregistrements répondant à une condition.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_filter	Motif à reconnaître pour suppression (filtre){}
 * @param i_modeFilter	Mode d'exécution, "dropIfContains", "dropIfNotContains"{}
 * @param i_searchValue	Valeur cherchée{}
 * @param i_targetedColumns	Colonnes dans lesquelles s'effectue la recherche{}
 */
public void dropFilter (
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
 * Suppression des enregistrements répondant à une condition.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_filter	Motif à reconnaître pour suppression (filtre) {}
 */
 public void dropFilter (
	HashMap<String,Object> i_filter
	)throws Exception{dropFilter(i_filter,null,null,null);
}
/**
 * Suppression des enregistrements répondant à une condition.{ 
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
	)throws Exception{dropFilter(null,i_modeFilter,i_searchValue,i_targetedColumns);
}
/*	### NOUVELLE METHODE ###
	Méthode : flatten - "Aplatit" le fichier vers un format texte séparateur{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * "Aplatit" le fichier vers un format texte séparateur{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public String flatten ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Composition du contenu
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
	Méthode : flattenToFile - "Aplatit" le fichier vers un format texte séparateur, l'enregistre dans un fichier de sortie{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * "Aplatit" le fichier vers un format texte séparateur, l'enregistre dans un fichier de sortie{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_fileName_OUT	Nom du fichier de sortie{}
 * @param i_keepFile	Suppression du fichier initial{}
 * @return	{}
 */
public String flattenToFile (
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

//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"flattenToFile_output1" : Adresse du fichier (String)
	//	this.WILD_setOutput("flattenToFile_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * "Aplatit" le fichier vers un format texte séparateur, l'enregistre dans un fichier de sortie{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fileName_OUT	Nom du fichier de sortie {}
 * @return	Adresse du fichier (String) {}
 */
 public String flattenToFile (
	String i_fileName_OUT
	)throws Exception{
return flattenToFile(i_fileName_OUT,false);
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
	Méthode : setData - Ajoute les données à la liste du WildDataSet{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ajoute les données à la liste du WildDataSet{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_mapContents	Contenu à affecter d'office{}
 */
public void setData (
	HashMap<Integer,HashMap<String,Object>> i_mapContents
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Affectation de la variable
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Elaboration des statistiques
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"setData_output1" : Nombre d'enregistrements, nombre de colonnes (Integer[])
	//	this.WILD_setOutput("setData_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : update - Mise à jour d'un enregistrement{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Mise à jour d'un enregistrement{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_filtre	Motif à reconnaître pour affection (filtre){}
 * @param i_newEnr	Données à mettre à jour{}
 */
public void update (
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

/*	### NOUVELLE METHODE ###
	Méthode : getColumn - Récupération d'une colonne, avec n*[numéro d'enregistrement et la valeur voulue]{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération d'une colonne, avec n*[numéro d'enregistrement et la valeur voulue]{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_getColumn	Nom de la colonne à récupérer{}
 * @return	{}
 */
public HashMap<Integer,Object> getColumn (
	String i_getColumn
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<Integer,Object> WILD_toReturn = null ; // Ne pas modifier

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
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : setHeaders - Actualisation des noms de colonnes{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Actualisation des noms de colonnes{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_newColumns	Nouveaux noms de colonne{}
 */
public void setHeaders (
	String[] i_newColumns
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Mise à jour de la variable
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Mise à jour des enregistrements
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

