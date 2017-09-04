
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildObject;

// Dépendances, bibliothèques JAVA par exemple.


/**
 * Code généré automatiquement par l'outil Wild
 * Fichier XML à composante géographique « langage à base de balises géolocales », standard OGC
 */
public class WildKmlFile extends WildSigFile{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildKmlFile(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation

// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
//	protected String pathToFile;	//Chemin vers le fichier{}
//	protected Integer srid;	//Système de référence géographique{}


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
	 * @param i_confmodel	Configurations spécifiques au fichier{}
	 */
	protected void WILD_initialize_WildKmlFile(
		WildObject i_WILD_dObject,
		String i_pathToFile,
		Integer i_srid,
		Integer i_confmodel
	) throws Exception {

//		// Amorce de la classe
		// Initialisation de la classe d'objet selon le schéma Wild
//		WILD_dObject = i_WILD_dObject ;
		WILD_initialize_WildSigFile(i_WILD_dObject,i_pathToFile,(i_srid == null) ? 4326 : i_srid,(i_confmodel == null) ? 0 : i_confmodel);
		// Préparation des variables d'invocation (considérées comme champs globaux)
//		this.pathToFile = i_pathToFile;
//		this.srid = (i_srid == null) ? 4326 : i_srid;

//		// Mode try de récupération des erreurs pour log
		try {
//		// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

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
	* @param i_confmodel	Configurations spécifiques au fichier{}
	*/

	public WildKmlFile(
	 	WildObject i_WILD_dObject,
	 	String i_pathToFile,
	 	Integer i_srid,
	 	Integer i_confmodel
	 ) throws Exception{
	 	if(srid==null)srid=4326;
	 	if(confmodel==null)confmodel=0;
	 	this.WILD_initialize_WildKmlFile(i_WILD_dObject,i_pathToFile,(i_srid == null) ? 4326 : i_srid,(i_confmodel == null) ? 0 : i_confmodel);
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

	 public WildKmlFile(
	 	WildObject i_WILD_dObject,
	 	String i_pathToFile
	 ) throws Exception{
	 	this.WILD_initialize_WildKmlFile(i_WILD_dObject,i_pathToFile,4326,0);
	 }

	//CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
	/**
	* Code généré automatiquement par l'outil Wild
	* Constructeur, appelle nécessairement WILD_initialize()
	* NB. i_WILD_dObject est nécessairement passé
	*
	* @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
	* @param i_pathToFile	Chemin vers le fichier{}
	* @param i_srid	Système de référence géographique{}
	*/
	public WildKmlFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	Integer i_srid
	) throws Exception{
	this.WILD_initialize_WildKmlFile(i_WILD_dObject,i_pathToFile,(i_srid == null) ? 4326 : i_srid,0);
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

}

