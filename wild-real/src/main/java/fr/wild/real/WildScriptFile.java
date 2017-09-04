/*
 *  ONEMA Dice project.
 *  Copyright (C) 2016 ONEMA
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this project. If not, see http://www.gnu.org/licenses.
 */
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Arrays;
// Dépendances, bibliothèques JAVA par exemple.
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
 

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
	protected String currentProcessed; // Identifiant du fichier en cours de
										// traitement (à défaut

	// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	protected String pathToFile; // Chemin vers le fichier{}
	protected String mimeEncoding; // Encodage du fichier{}
	protected String commenter; // Caractère(s) de commentaire{}
	protected String commenterBegin; // Caractère(s) de commentaire multi-ligne,
										// début{}
	protected String commenterEnd; // Caractère(s) de commentaire multi-ligne,
									// fin{}
	protected String commandEnd; // Caractère(s) de fin de commande{}

	// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***
	protected String DVP_language; // Langage informatique
	protected String DVP_stringContent; // Contenu sous forme de String
	protected String DVP_result_processLine; // Variable de stockage des résultats
	protected Boolean DVP_setDepth = false; // Variable indiquant aux  algorithmes de parse des  
											//commentaires si le language  traité accepte des
											// sous-commentaires (exemple (::)  dans XML)

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

//		// Amorce de la classe
		// Initialisation de la classe d'objet selon le schéma Wild
		WILD_dObject = i_WILD_dObject ;
		WILD_initialize_WildFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,null,null,null);
		// Préparation des variables d'invocation (considérées comme champs globaux)
		this.pathToFile = i_pathToFile;
		this.mimeEncoding = i_mimeEncoding;
		this.commenter = i_commenter;
		this.commenterBegin = i_commenterBegin;
		this.commenterEnd = i_commenterEnd;
		this.commandEnd = i_commandEnd;

//		// Mode try de récupération des erreurs pour log
		try{	
//		// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			this.DVP_castFile();
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
	Méthode : setCommenter - Détermination du (des) caractères de commentaire pour une ligne{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Détermination du (des) caractères de commentaire pour une ligne{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à  l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_new_char	Nouveau caractère{}
 */
	public void setCommenter(String i_new_char) throws Exception {
		// // Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		// Ne pas modifier

		// // Mode try de récupération des erreurs pour log
		try {
			// // *** [Généré automatiquement] Ecrivez vos variables locales
			// ici. ***

			// // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

			// // // Etape "1" : poids relatif de 10, Attribution de la variable
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			this.commenter = i_new_char;
			// // // Etape "2" : poids relatif de 10, Mise à  jour des variables
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

		} catch (Exception e) {
			this.WILD_logException(e); // Ne pas modifier
		} finally {
			// // Fin de la méthode
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
 * Cette méthode répond à  l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_new_char	Nouveau caractère{}
 */
	public void setCommenterBegin(String i_new_char) throws Exception {
		// // Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		// Ne pas modifier

		// // Mode try de récupération des erreurs pour log
		try {
			// // *** [Généré automatiquement] Ecrivez vos variables locales
			// ici. ***

			// // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

			// // // Etape "1" : poids relatif de 10, Attribution de la variable
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			this.commenterBegin = i_new_char;
			// // // Etape "2" : poids relatif de 10, Mise à  jour des variables
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

		} catch (Exception e) {
			this.WILD_logException(e); // Ne pas modifier
		} finally {
			// // Fin de la méthode
			WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
		}
	}

/*	### NOUVELLE METHODE ###
Méthode : setCommenterEnd - Détermination du (des) caractères de commentaire multi-ligne, fin{ 
}

*/
//CETTE METHODE DOIT ETRE MODIFIE.

/**
* Détermination du (des) caractères de commentaire multi-ligne, fin{ 
}
* Code généré automatiquement par l'outil Wild
* Méthode susceptible d'être surchargée
* Cette méthode répond à  l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
* 
* @param i_new_char	Nouveau caractère{}
*/
	public void setCommenterEnd(String i_new_char) throws Exception {
		//// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		// Ne pas modifier

		//// Mode try de récupération des erreurs pour log
		try {
			//// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

			//// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

			//// // Etape "1" : poids relatif de 10, Attribution de la variable
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			this.commenterEnd = i_new_char;
			//// // Etape "2" : poids relatif de 10, Mise à  jour des variables
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

		} catch (Exception e) {
			this.WILD_logException(e); // Ne pas modifier
		} finally {
			//// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
		}
	}

/*	### NOUVELLE METHODE ###
	Méthode : setCommandEnd - Détermination du (des) caractères de fin d'ordre à  exécuter{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Détermination du (des) caractères de fin d'ordre à  exécuter{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à  l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_new_char	Nouveau caractère{}
 */
	public void setCommandEnd(String i_new_char) throws Exception {
		// // Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		// Ne pas modifier

		// // Mode try de récupération des erreurs pour log
		try {
			// // *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

			// // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

			// // // Etape "1" : poids relatif de 10, Attribution de la variable
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			this.commandEnd = i_new_char;
			// // // Etape "2" : poids relatif de 10, Mise à  jour des variables
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

		} catch (Exception e) {
			this.WILD_logException(e); // Ne pas modifier
		} finally {
			// // Fin de la méthode
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
	public void setLanguageName(String i_new_var) throws Exception {
		// // Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		// Ne pas modifier

		// // Mode try de récupération des erreurs pour log
		try {
			// // *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

			// // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

			// // // Etape "1" : poids relatif de 10, Attribution de la variable
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			this.DVP_language = i_new_var;
			// // // Etape "2" : poids relatif de 10, Mise à  jour des variables
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

		} catch (Exception e) {
			this.WILD_logException(e); // Ne pas modifier
		} finally {
			// // Fin de la méthode
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
	public void setProcessed(String i_new_var) throws Exception {
		// // Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		// Ne pas modifier

		// // Mode try de récupération des erreurs pour log
		try {
			// // *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

			// // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

			// // // Etape "1" : poids relatif de 10, Attribution de la variable
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			Class newClass = Class.forName("fr.wild." + i_new_var);
			if (!this.canBeProcessed.contains(newClass))
				this.canBeProcessed.add(newClass);

			// // // Etape "2" : poids relatif de 10, Mise à  jour des variables
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

		} catch (Exception e) {
			this.WILD_logException(e); // Ne pas modifier
		} finally {
			// // Fin de la méthode
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
	public void uncomment() throws Exception {
		// // Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		// Ne pas modifier

		// // Mode try de récupération des erreurs pour log
		try {
			// // *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

			// // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

			// // // Etape "1" : poids relatif de 10, Création des flux
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

			// // // Etape "2" : poids relatif de 50, Parcours du jeu de données avec réécriture (++)
			this.WILD_setStep(); // Ne pas modifier
			WildDataSet wDs = this.getDataSet("initial");
			Integer output = 0;
			for (Integer i = 1; i <= wDs.DVP_getLength(); i++)
				if (wDs.DVP_getData(i, "comment") != null)
					output++;
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			uncommentDataset();
			commit();
			// // // Etape "3" : poids relatif de 10, Contrà´le des flux
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

			// // // Output "uncomment_output1" : Nombre de lignes affectées (Integer)
			this.WILD_setOutput("uncomment_output1", output); // Ne pas modifier

		} catch (Exception e) {
			this.WILD_logException(e); // Ne pas modifier
		} finally {
			// // Fin de la méthode
			WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
		}
	}
	/*	### NOUVELLE METHODE ###
	Méthode : uncommentDataset - Suppression des commentaires dans la liste d'ordre à  exécuter (WildDataSet){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Suppression des commentaires dans la liste d'ordre à  exécuter (WildDataSet){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à  l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
	public void uncommentDataset() throws Exception {
		// // Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		// Ne pas modifier

		// // Mode try de récupération des erreurs pour log
		try {
			// // *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
			WildDataSet wDs = this.getDataSet("initial");
			Integer output = 0;
			for (Integer i = 1; i <= wDs.DVP_getLength(); i++)
				if (wDs.DVP_getData(i, "comment") != null)
					output++;
			// // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			wDs.dropColumn("comment");
			// // // Etape "1" : poids relatif de 10, Parcours du jeu de données
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

			// // // Output "uncommentDataset_output1" : Nombre de lignes affectées (Integer)
			this.WILD_setOutput("uncommentDataset_output1", output); // Ne pas
																		// modifier

		} catch (Exception e) {
			this.WILD_logException(e); // Ne pas modifier
		} finally {
			// // Fin de la méthode
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
	public void unnerve(String i_fromToken_str, String i_toToken_str, Integer i_fromToken_int, Integer i_toToken_int)
			throws Exception {
		// // Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		// Ne pas modifier

		// // Mode try de récupération des erreurs pour log
		try {
			// // *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
			WildDataSet wDs = this.getDataSet("initial");
			// // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			
			// En mode "retrouver motif", on redéfinit les lignes de début et de fin de commentaire
			// // Début de commentaire
			if (i_fromToken_int == null && i_fromToken_str != null) {
				for (Integer i = 1; i <= wDs.DVP_getLength(); i++) {
					String seekInto = wDs.DVP_getData(i, "exec");
					if (seekInto.contains(i_fromToken_str)) {
						i_fromToken_int = i;
						break;
					}
					seekInto = wDs.DVP_getData(i, "comment");
					if (seekInto.contains(i_fromToken_str)) {
						i_fromToken_int = i;
						break;
					}
				}
				if (i_fromToken_int == null)
					return;
			}
			// Fin de commentaire
			if (i_toToken_int == null && i_toToken_str != null) {
				for (Integer i = i_fromToken_int; i <= wDs.DVP_getLength(); i++) {
					String seekInto = wDs.DVP_getData(i, "exec");
					if (seekInto.contains(i_toToken_str)) {
						i_toToken_int = i;
						break;
					}
					seekInto = wDs.DVP_getData(i, "comment");
					if (seekInto.contains(i_toToken_str)) {
						i_toToken_int = i;
						break;
					}
				}
				if (i_toToken_int == null)
					i_toToken_int = wDs.DVP_getLength();
			}
			// // // Etape "1" : poids relatif de 10, Parcours du jeu de données
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			// Passage en commentaire de la zone d'exécution ciblée
			for (Integer i = i_fromToken_int; i <= i_toToken_int; i++) {
				String newComment = wDs.DVP_getData(i, "comment");
				newComment = (String) ((newComment == null || newComment.length() == 0) ? wDs.DVP_getData(i, "exec")
						: wDs.DVP_getData(i, "exec") + this.commenter + newComment);
				wDs.DVP_setData(i, "comment", newComment);
				wDs.DVP_setData(i, "exec", "");
			}

			// // // Output "unnerve_output1" : Nombre de lignes affectées (Integer)
			this.WILD_setOutput("unnerve_output1", i_toToken_int - i_fromToken_int + 1); // Ne pas modifier
		} catch (Exception e) {
			this.WILD_logException(e); // Ne pas modifier
		} finally {
			// // Fin de la méthode
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
	public void nerve(String i_fromToken_str, String i_toToken_str, Integer i_fromToken_int, Integer i_toToken_int)
			throws Exception {
		// // Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		// Ne pas modifier

		// // Mode try de récupération des erreurs pour log
		try {
			// // *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

			// // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			WildDataSet wDs = this.getDataSet("initial");
			// En mode "retrouver motif", on redéfinit les lignes de début et de fin de commentaire
			// // Début de commentaire
			if (i_fromToken_int == null && i_fromToken_str != null) {
				for (Integer i = 1; i <= wDs.DVP_getLength(); i++) {
					String seekInto = wDs.DVP_getData(i, "exec");
					if (seekInto.contains(i_fromToken_str)) {
						i_fromToken_int = i;
						break;
					}
					seekInto = wDs.DVP_getData(i, "comment");
					if (seekInto.contains(i_fromToken_str)) {
						i_fromToken_int = i;
						break;
					}
				}
				if (i_fromToken_int == null)
					return;
			}
			// // Fin de commentaire
			if (i_toToken_int == null && i_toToken_str != null) {
				for (Integer i = i_fromToken_int; i <= wDs.DVP_getLength(); i++) {
					String seekInto = wDs.DVP_getData(i, "exec");
					if (seekInto.contains(i_toToken_str)) {
						i_toToken_int = i;
						break;
					}
					seekInto = wDs.DVP_getData(i, "comment");
					if (seekInto.contains(i_toToken_str)) {
						i_toToken_int = i;
						break;
					}
				}
				if (i_toToken_int == null)
					i_toToken_int = wDs.DVP_getLength();
			}
			// // // Etape "1" : poids relatif de 10, Parcours du jeu de données
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			// Passage de la zone ciblée en exécution
			Boolean withinComments = false;
			for (Integer i = i_fromToken_int; i <= i_toToken_int; i++) {
				Object[] parsed = DVP_parseComment(
						new Object[] { withinComments, null, null, wDs.DVP_getData(i, "comment") });
				withinComments = (Boolean) parsed[0];
				wDs.DVP_setData(i, "comment", parsed[2]);
				String newExec = wDs.DVP_getData(i, "comment");
				newExec = (String) ((newExec == null || newExec.length() == 0) ? parsed[1] : newExec + parsed[1]);
				wDs.DVP_setData(i, "exec", parsed[1]);
			}
			// // // Output "nerve_output1" : Nombre de lignes affectées (Integer)
			this.WILD_setOutput("nerve_output1", i_toToken_int - i_fromToken_int + 1); // Ne
																						// pas
																						// modifier
		} catch (Exception e) {
			this.WILD_logException(e); // Ne pas modifier
		} finally {
			// // Fin de la méthode
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
	 * @param i_toReplace	Ce qu'il faut remplacer (###VAR### est remplacé par la liste de variables){}
	 * @param i_replaceBy	Ce par quoi on remplace (###VAR### est remplacé par la liste de variables){}
	 * @param i_varFilter	Structure de sélection de variables en amont (###VAR### est remplacé par la liste de variables){}
	 * @param i_okVars	Variables permettant un changement{}
	 * @param i_koVars	Variables ne permettant pas de changements{}
	 */
	public void alterCode(String i_toReplace, String i_replaceBy, List<String> i_varFilter, List<String> i_okVars,
			List<String> i_koVars) throws Exception {
		// // Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		// Ne pas modifier

		// // Mode try de récupération des erreurs pour log
		try {
			// // *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
			WildDataSet wDs = this.getDataSet("initial");
			Integer countMod = 0; // Nombre de lignes modifiées
			// // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

			// // // Etape "1" : poids relatif de 10, Parcours du jeu de données
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			// Remplacement d'une partie de code par une autre, en absence d motif plus élaboré
			if (i_varFilter == null)
				for (Integer i = 1; i <= wDs.DVP_getLength(); i++) {
					String codeLine = wDs.DVP_getData(i, "exec");
					if (codeLine.contains(i_toReplace)) {
						wDs.DVP_setData(i, "exec", codeLine.replace(i_toReplace, i_replaceBy));
						countMod++;
					}
				}
			// Il existe un motif à reconnaître
			else {
				Boolean change = false;
				String forReplacement = null;
				String forReplacementToReplace = null;

				for (Integer i = 1; i <= wDs.DVP_getLength(); i++) {
					String codeLine = wDs.DVP_getData(i, "exec");
					String codeLine_trim = codeLine.trim();
			// // Le motif se base sur des variables qui enclenchent le remplacement
					if (i_okVars != null)
						for (String varName : i_okVars) {
							Boolean op_done = false;
							for (String varFilter : i_varFilter) {
								varFilter = varFilter.replace("###VAR###", varName);
								if (codeLine_trim.startsWith(varFilter)) {
									forReplacement = i_replaceBy.replace("###VAR###", varName);
									forReplacementToReplace = i_toReplace.replace("###VAR###", varName);
									op_done = true;
									change = true;
									break;
								}
								if (op_done)
									break;
							}
						}
			// // Le motif se base sur des variables qui neutralisent le remplacement
					if (i_koVars != null)
						for (String varName : i_koVars) {
							Boolean op_done = false;
							for (String varFilter : i_varFilter) {
								varFilter = varFilter.replace("###VAR###", varName);
								if (codeLine_trim.startsWith(varFilter)) {
									forReplacement = i_replaceBy;
									forReplacementToReplace = i_toReplace;
									op_done = true;
									change = false;
									break;
								}
								if (op_done)
									break;
							}
						}
			// // Le remplacement a lieu en fonction des éléments précédemment rencontrés
					if (change && codeLine.contains(forReplacementToReplace)) {
						codeLine = codeLine.replace(forReplacementToReplace, forReplacement);
						wDs.DVP_setData(i, "exec", codeLine);
						countMod++;
					}
				}
			}
			// // // Output "alterCode_output1" : Nombre de lignes affectées (Integer)
			this.WILD_setOutput("alterCode_output1", countMod); // Ne pas modifier

		} catch (Exception e) {
			this.WILD_logException(e); // Ne pas modifier
		} finally {
			// // Fin de la méthode
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
	 * @param i_toReplace	Ce qu'il faut remplacer (###VAR### est remplacé par la liste de variables){}
	 * @param i_replaceBy	Ce par quoi on remplace (###VAR### est remplacé par la liste de variables){}
	 */
	 public void alterCode (
		String i_toReplace,
		String i_replaceBy
		)throws Exception{alterCode(i_toReplace,i_replaceBy,null,null,null);
	}
	 
	/**
	 * Méthode générique de cast du fichier
	 * Construit les DataSets initiaux
	 * @throws Exception
	 */
	protected void DVP_castFile() throws Exception {
		BufferedReader ir = null ;
		try{
		// Récupération des données du fichier
		ir = new BufferedReader(new InputStreamReader(DVP_getStream()));
		String line;
		WildDataSet wD_full = new WildDataSet(this.WILD_dObject, null);
		Boolean withinComments = false;
		// Pour chaque ligne, on détermine la "part de commentaire", la part "d'exécutable",
		// à sauver dans un jeu de données
		while ((line = ir.readLine()) != null) {
			HashMap<String, Object> record = new HashMap<String, Object>();
			Object[] parsed = DVP_parseComment(new Object[] { withinComments, null, null, line });
			withinComments = (Boolean) parsed[0];
			// Enregistrement des variables du jeu de données
			record.put("exec", parsed[1]);
			record.put("comment", parsed[2]);
			wD_full.add(record);
		}
		// Enregistrement du jeu de données
		addDataSet("initial", wD_full);
		}finally{
			if(ir != null)ir.close();
		}
	}
	/**
	 * Méthode qui parse une ligne de script et dit ce qui est en commentaire / ce qui n'est pas en commentaire
	 * Récursive
	 * @param toParse Tableau à parser : {Contexte : dans un commentaire ou non (en entrée) ?, A exécuter (null hors récursive), commentaire (null hors récursive), A parser }
	 * @return Tableau parsé : {Contexte : dans un commentaire ou non (en sortie) ?, A exécuter, commentaire, null }
	 */
	protected Object[] DVP_parseComment(Object[] toParse){
		Boolean withinComment = (Boolean) toParse[0] ; // Est-on dans un commentaire en début de parcours de ligne ?
		String execLine = (toParse[1]==null)?"":(String) toParse[1] ; // Partie du texte "exécutable" sur la ligne (à défaut, vide)
		String commentLine = (toParse[2]==null)?"":(String) toParse[2] ; // Partie du texte "commentaire" sur la ligne (à défaut, vide)
		String notKnown = (String) toParse[3] ; // Partie de la ligne qui n'a pas encore été triée 
		String[] commentnotEnd = {"(","{","/"}; //Liste des éléments ne pouvant terminer un commentaire, car interfèrent avec les balises de début et de fin de commentaires habituelles	
		Integer depth = (Integer) ((toParse.length>4)?toParse[4]:0) ; // Profondeur de commentaire, pour les cas où le langage supporte des commenataire "emboîtés" ((::) dans xQuery)
		if(notKnown ==null)return toParse; // Rién à traiter
		// On est à l'intérieur d'un commentaire
		if(withinComment){
			// Ligne ou caractère de fin de commentaire
			if(notKnown.equals(commenterEnd)){ 
				if(this.DVP_setDepth){
					depth-- ;
					if(depth<0)depth=0;
					if(depth==0)withinComment = false ;
					return new Object[]{withinComment, execLine,commentLine, null,depth};
				}
				return new Object[]{withinComment, execLine,commentLine, null};
			}
			// On rencontre : ligne ou caractère commençant un commentaire long
			if(this.DVP_setDepth&&notKnown.contains(this.commenterBegin)){
				// Le commentaire est fermé sur la même ligne
				if(!notKnown.contains(commenterEnd)){
					depth++;
					String commentRed = notKnown.substring(notKnown.indexOf(this.commenterBegin)+this.commenterBegin.length(), notKnown.length()) ;
									// commentRed : pour les cas où les caractères de commentaires se "marchent dessus", comme (:) en Xquery
					commentLine += (commentRed.length()>0&&Arrays.asList(commentnotEnd).contains(commentRed.substring(commentRed.length()-1)))? commentRed+" ":commentRed;
					return DVP_parseComment(
							new Object[]{true, execLine,commentLine, notKnown,depth}
							);
				}
				// Autres cas
				Integer beg = notKnown.indexOf(this.commenterBegin);
				Integer end = notKnown.indexOf(this.commenterEnd);
				if(beg<end){
					depth++;
					String commentRed = notKnown.substring(0,end+commenterEnd.length()) ;
					notKnown = notKnown.substring(end+commenterEnd.length()+commenterEnd.length()) ;
					commentLine += (commentRed.length()>0&&Arrays.asList(commentnotEnd).contains(commentRed.substring(commentRed.length()-1)))? commentRed+" ":commentRed;
					return DVP_parseComment(
							new Object[]{true, execLine,commentLine, notKnown,depth}
							);
				}
			}	
			// On rencontre : ligne ou caractère fermant un commentaire long
			if(notKnown.contains(this.commenterEnd)){
				String commentRed = notKnown.substring(0,notKnown.indexOf(this.commenterEnd)) ;
				commentLine += (commentRed.length()>0&&Arrays.asList(commentnotEnd).contains(commentRed.substring(commentRed.length()-1)))? commentRed+" ":commentRed;
				notKnown = notKnown.substring(notKnown.indexOf(this.commenterEnd)+this.commenterEnd.length(), notKnown.length()) ;
				// Cas des commentaires emboîtés (propriété de l'objet)
				if(this.DVP_setDepth){
					depth-- ;
					if(depth<0)depth=0;
					if(depth==0)withinComment = false ;
					return DVP_parseComment(
							new Object[]{withinComment, execLine,commentLine, notKnown,depth}
					);
				}			
				return DVP_parseComment(
						new Object[]{false, execLine,commentLine, notKnown}
				);
			}else{
			// Le commentaire n'est pas fermé sur la ligne
				commentLine += (notKnown.length()>0&&Arrays.asList(commentnotEnd).contains(notKnown.substring(notKnown.length()-1)))? notKnown+" ":notKnown;
				return new Object[]{true, execLine,commentLine, null};
			}
		}else{	
		// On est à l'extérieur d'un commentaire
			// Ouverture d'un commentaire
			if(notKnown.equals(commenterBegin))return new Object[]{true, execLine,commentLine, null};
			String notKnownTrim = notKnown.trim();
			// On ouvre un commentaire, mais la ligne n'est pas entièrement un commentaire
			if(notKnownTrim.startsWith(commenterBegin)&&notKnownTrim.endsWith(commenterEnd)){
				Integer marginDown = this.commenterBegin.length();
				Integer marginTop = notKnownTrim.length()-this.commenterEnd.length();		
				String commentRed = ((marginDown-marginTop)>0)?"":notKnownTrim.substring(marginDown,marginTop) ;
									// commentRed : pour les cas où les caractères de commentaires se "marchent dessus", comme (:) en Xquery
				commentLine += (commentRed.length()>0&&Arrays.asList(commentnotEnd).contains(commentRed.substring(commentRed.length()-1)))? commentRed+" ":commentRed;
				return new Object[]{false, execLine,commentLine, null};
			}
			// Dans la partie exécutable,n on rencontre un commentaire simple
			if(notKnown.split(Pattern.quote(this.commenterBegin))[0].contains(this.commenter)){
				execLine += notKnown.substring(0,notKnown.indexOf(this.commenter)) ;
				String commentRed = notKnown.substring(notKnown.indexOf(this.commenter)+this.commenter.length(), notKnown.length()) ;
									// commentRed : pour les cas où les caractères de commentaires se "marchent dessus", comme (:) en Xquery
				execLine = (execLine.endsWith(commenterEnd))? execLine.substring(0, execLine.indexOf(commenterEnd)):execLine ;
				commentLine += (commentRed.length()>0&&Arrays.asList(commentnotEnd).contains(commentRed.substring(commentRed.length()-1)))? commentRed+" ":commentRed;
				return new Object[]{false, execLine,commentLine, null};
			// Si les conditions suffisantes ne sont pas remplies, et que l'on commentce un commentaire long
			}else if(notKnown.contains(this.commenterBegin)){
				execLine += notKnown.substring(0,notKnown.indexOf(this.commenterBegin)) ;
				execLine = (execLine.endsWith(commenterEnd))? execLine.substring(0, execLine.indexOf(commenterEnd)):execLine ;
				notKnown = notKnown.substring(notKnown.indexOf(this.commenterBegin)+this.commenterBegin.length(), notKnown.length()) ;
				return DVP_parseComment(
						new Object[]{true, execLine,commentLine, notKnown}
				);
			// Autres cas
			}else{
				execLine += notKnown ;
				execLine = (execLine.endsWith(commenterEnd))? execLine.substring(0, execLine.indexOf(commenterEnd)):execLine ;
				return new Object[]{false, execLine,commentLine, null};
			}
		}			
	}

	/**
	 * Méthode de mise de mise à plat d'un fichier : 
	 * écrit le fichier sous forme de ficheir texte, à partir du dataSet
	 * (on récupère donc le tri exécution / commentaire et les éventuelles modifications à la volée)
	 * Commit sur disque automatique
	 * @throws Exception
	 */
	protected void DVP_flatten() throws Exception{
		String d_DVP_stringContent = "";
		WildDataSet wDs = this.getDataSet("initial");
		// En mode "retrouver motif", on redéfinit les lignes
		//Boolean contiguous_comment;
		for(Integer i = 1 ; i <= wDs.DVP_getLength() ; i++){
			String exec = wDs.DVP_getData(i,"exec");
			String exec_trim = exec.trim();
			String comment = wDs.DVP_getData(i,"comment");
			if(comment != null&&comment.startsWith(this.commenter))comment = comment.substring(comment.indexOf(this.commenter)+this.commenter.length(), comment.length());
			if(exec_trim!=null&&exec_trim.length()>0)
					d_DVP_stringContent += System.getProperty("line.separator")+exec ;
			if(comment!=null&&comment.length()>0
				&&(exec_trim==null||exec_trim.length()==0))
					d_DVP_stringContent += System.getProperty("line.separator")+this.commenterBegin+comment+this.commenterEnd
				;
			if(comment!=null&&comment.length()>0
					&&exec_trim!=null&&exec_trim.length()>0)
					d_DVP_stringContent += this.commenterBegin+comment+this.commenterEnd;
		}
		if(d_DVP_stringContent.length()>0)DVP_stringContent=d_DVP_stringContent;
	}

	/**
	 * Ecriture du ficheir sur le disque, 
	 * avec captage / téléchargement éventuel depuis une URL
	 * @throws Exception
	 */
	protected void DVP_commit() throws Exception{
		DVP_flatten();
		OutputStream out = null ;
		try{
		if(DVP_stringContent!=null&&DVP_stringContent.length()>0){
			if(fileFile.exists())fileFile.delete();
			fileFile.createNewFile();
			out = new FileOutputStream(fileFile);
			out.write(this.DVP_stringContent.getBytes());
		}
		}finally{
			if(out!=null)out.close();
		}
			
	}
	/**
	 * Récupérationdu résultat de traitement, 
	 * sous forme de String
	 * @return Résultat de traitement chaîné
	 */
	public String DVP_getResult(){
		return this.DVP_result_processLine;
	}
}

