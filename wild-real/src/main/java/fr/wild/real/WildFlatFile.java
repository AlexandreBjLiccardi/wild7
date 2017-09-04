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
import fr.wild.utils.ConditionChain;
import fr.wild.utils.ConvertingIterator;
import fr.wild.utils.FilteringIterator;
import fr.wild.utils.FiniteNumberIterator;
import fr.wild.common.IoCommons;
import fr.wild.common.IoFileSystem;
import static fr.wild.common.IoFileSystem.*;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildObject;

// Dépendances, bibliothèques JAVA par exemple.
import org.apache.commons.csv.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Pattern;
import org.apache.sis.math.Statistics;
import org.apache.sis.util.ArgumentChecks;
import org.apache.sis.util.ObjectConverters;


/**
 * Code généré automatiquement par l'outil Wild
 * Fichier "plat" générique. Fichier contenant des données séparées par un caractère séparateur de lignes, un caractère séparateur de colonne, un "protecteur de texte", un caractère d'échappement. Toutes les lignes appartiennent à un même "onglet" (une seule définition de ligne d'en-tête, de caractères etc... par fichier).
 */
public abstract class WildFlatFile extends WildFile {

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
	private String[] DVP_extensionToUnzip= {"rar","zip","7z"} ; // Si l'extension du fichier est dans cette liste, on dézippe automatiquement le fichier
	private CSVFormat DVP_csvFormat = null ; // Format de lecture Apache common CSV pour parser chaque ligne
	private String DVP_regex = null ; // REGEX si le csvFormat n'est pas déterminable
	private Boolean DVP_exportWithHeaders = true ; // Indique si l'export vers un ficheir doit conserver les entêtes
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
	WILD_initialize_WildFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,null,null,null);
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
	// Décompression éventuelle du fichier, basée sur l'extension
		String extension_i = ((String)this.fileDescriptor.get("extension"));
		if(Arrays.asList(DVP_extensionToUnzip).contains(extension_i)){
			String filePath_f = pathToFile.replaceAll("\\."+extension_i+"$","") ;
			String outZipFile = this.unzip(filePath_f);
			List<String> fDir = file_getDirContents(outZipFile) ;
			if(fDir.size()==1){
				this.fileSource = i_pathToFile ;
				this.pathToFile = fDir.get(0);
				this.filePath = this.pathToFile;
				this.fileFile = new File(this.filePath);
			}
		}
		DVP_buildFormat();
		//castFile (); // Désactivé pour les essais mode "Big"
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
		this.delimiter = i_separator ;
//	//	//	Etape	"2" : poids relatif de 10, Mise à jour des variables
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		this.DVP_buildFormat();
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
		this.delimiter = i_delimiter ;
//		//	//	Etape	"2" : poids relatif de 10, Mise à jour des variables
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		this.DVP_buildFormat();
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
		this.quoter = i_new_char ;
//	//	//	Etape	"2" : poids relatif de 10, Mise à jour des variables
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		this.DVP_buildFormat();
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
		this.escapeChar = i_new_char ;
//	//	//	Etape	"2" : poids relatif de 10, Mise à jour des variables
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		this.DVP_buildFormat();
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
            final WildDataSet ds = this.getDataSet("1");
            if (ds == null)
                return new HashMap();
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		WILD_toReturn = ds.DVP_getHeader();
//	//	//	Output	"getHeader_output1" : Nombre de colonnes trouvées (Integer)
		this.WILD_setOutput("getHeader_output1",WILD_toReturn.size()); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
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
		DVP_exportWithHeaders = false;
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
		super.addColumn(i_columnName, i_colContents);
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
		super.addColumnJoinBy(i_columnName, i_columnCompare_dataset, i_inputJoin, i_colContents);
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
		super.dropColumn(i_columnName);
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
		super.replaceValue(i_formerValue, i_newValue);
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
		super.replaceValueFilterBy(i_formerValue, i_newValue, i_enrContents);
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
		super.concatenate(i_col_1, i_col_2, i_link);
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
		super.concatenateFilterBy(i_col_1, i_col_2, i_link, i_colContents);
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
	OutputStream out = null ;
//	// Mode try de récupération des erreurs pour log
	try{
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***


//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

		this.addDataSet(i_dataSet) ;
		WildDataSet wDs = this.getDataSet("1");
		HashMap<Integer,String> headers = wDs.DVP_getHeader() ;

//	//	//	Etape	"2" : poids relatif de 20, Vidage du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(fileFile!=null&&fileFile.exists())fileFile.delete();
		out = new FileOutputStream(fileFile,true);
		fileFile.createNewFile();
//	//	//	Etape	"3" : poids relatif de 50, Ecriture dufichier (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(this.DVP_exportWithHeaders){
			String lineValue = "" ;
			for(Integer j = 1 ; j <= headers.size() ; j++){
				if(j == headers.size())lineValue += DVP_castValue(headers.get(j))+ this.lineDelimiter ;
				else lineValue += DVP_castValue(headers.get(j))+this.delimiter;
			}
			out.write(lineValue.getBytes());
		}
		for(Integer i = 1 ; i <= wDs.DVP_getLength() ; i++){
			String lineValue = "" ;
			for(Integer j = 1 ; j <= headers.size() ; j++){
				if(j == headers.size()&&i == wDs.DVP_getLength())lineValue += DVP_castValue(wDs.DVP_getData(i, headers.get(j)));
				else if(j == headers.size()) lineValue += DVP_castValue(wDs.DVP_getData(i, headers.get(j)))+ this.lineDelimiter ;
				else if(headers.get(j)!=null)lineValue += DVP_castValue(wDs.DVP_getData(i, headers.get(j)))+this.delimiter;


			}
			// Affichage d'un signal toutes les 1000 lignes
			if(i%1000==0)WILD_echo("Commit : "+String.valueOf(i));
			out.write(lineValue.getBytes());
		}
//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"fillWith_output1" : Chemin du fichier de sortie (String)
	//	this.WILD_setOutput("fillWith_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
		if(out!=null)out.close();
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
	BufferedReader ir = null ;
//	// Mode try de récupération des erreurs pour log
	try{
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		String line;
		Integer nbLine = -1 ;

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec cast vers WildDataSet (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		// Identification de la ligne d'en-tête parmi les 100 premières lignes
		 Integer headerLine = DVP_getHeaderLine();
		// Pour chaque ligne après l'entête, définit les colonnes et leur valeurs
		ir = new BufferedReader(new InputStreamReader(DVP_getStream()));
		while ((line = ir.readLine()) != null) {
			nbLine++ ;
			if(nbLine < headerLine)continue ;
			if(nbLine == headerLine)DVP_setHeader(line);
			else DVP_addRecord(line);
			// Affichage d'un signal toutes les 1000 lignes
			if(nbLine%1000==0)WILD_echo("Cast : "+String.valueOf(nbLine));

		}
//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(this.fileHeaders==null)fileHeaders = this.getDataSet("1").DVP_getHeader();
	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
		if(ir != null)ir.close();
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
		this.fillWith(this.getDataSet("1"));

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}
/**
 * Redéfinition des entêtes
 * @param line	Ligne contenant les entêtes, à parser
 * @throws Exception
 */
private void DVP_setHeader(String line) throws Exception{
	DVP_setHeader(DVP_splitLine(line));
}

/**
 * Redéfinition des entêtes
 * @param elem	entêtes parsées
 * @throws Exception
 */
private void DVP_setHeader(String[] elem) throws Exception{
	if(elem==null)return;
	WildDataSet wds = this.getDataSet("1");
	if(wds==null)wds = new WildDataSet(this.WILD_dObject,null);
	wds.setHeaders(elem);
	if(this.fileHeaders==null)fileHeaders = wds.DVP_getHeader();
	this.addDataSet(wds);
}
/**
 * Ajout d'un enregistrement dans le dataset
 * à partir d'une ligne lue
 * @param line	Ligne contenant les valeurs, à parser
 * @throws Exception
 */
private void DVP_addRecord(String line) throws Exception{
	if(line == null) return ;
	String[] elt = DVP_splitLine(line);
	WildDataSet wds = this.getDataSet("1");
	if(wds==null)return ;
	wds.DVP_add(elt);
}

    /**
     * Caster ligne par ligne
     *
     * @param line Ligne à caster
     * @return
     * @throws Exception
     */
    private String[] DVP_splitLine(String line) throws Exception {
        String[] values;
        if (DVP_csvFormat != null) {
            // Si on peut, on utilise l'API common csv
            try (CSVParser parser = CSVParser.parse(line, DVP_csvFormat)) {
                final List<CSVRecord> records = parser.getRecords();
                if (records.size() <= 0)
                    values = new String[0];
                else {
                    final CSVRecord record = records.get(0);
                    values = new String[record.size()];
                    // TODO : This is a costly browsing. If we could point directly on record internal array, it would save a lot of time.
                    final Iterator<String> valueIt = record.iterator();
                    int i = 0;
                    while (valueIt.hasNext()) {
                        values[i++] = valueIt.next();
                    }
                }
            } catch (IOException ex) {
                //Erreur de parsing, enregistrement ignoré
                //TODO logger un warning ou lancer une ParsingException ?
                values = new String[0];
            }
        } else {
            // Sinon, c'est du regex
            values = (this.DVP_regex != null) ? null : line.split(DVP_regex, -1);
        }

        return values;
    }

/**
 * Méthode qui prend les nbRowTaken premières lignes, et retient la première qui contient le plus de champs consécutifs non nuls.
 * Suppose que cette ligne est celle des entêtes.
 * @return	Ligne d'entête supposée
 * @throws Exception
 */
private Integer DVP_getHeaderLine ()throws Exception{
	Integer nbRowTaken = 100 ; // Nombre maximal de lignes prises en compte pour le test des en-têtes
	Integer headerLine = 0;
	try (BufferedReader irh = new BufferedReader(new InputStreamReader(DVP_getStream()))) {
		// Pour chaque ligne après l'entête, on décompte le nombre de colonnes dont les valeurs consécutives ne sont pas nulles
		Integer maxConsecutiveColumns = 0 ;
		Integer lineNb = 0 ;
		String line;
		// Chaque ligne du flux, tant que l'on n'a pas dépassé la nbRowTaken-ième ligne
		while ((line = irh.readLine()) != null && nbRowTaken > lineNb) {
			Integer consecutiveColumnsforThisRow = 0 ;
			String[] tokens = DVP_splitLine(line);
		    if(tokens!=null)for(Integer i = 0 ; i < tokens.length ;i++) {
		        	if(tokens[i]!=null&&tokens[i].length()>0)consecutiveColumnsforThisRow++;
					else consecutiveColumnsforThisRow = 0 ;
		    }
			// Comparaison par enregistrement
			if(maxConsecutiveColumns < consecutiveColumnsforThisRow){
						maxConsecutiveColumns = consecutiveColumnsforThisRow ;
						headerLine = lineNb ;
			}
			lineNb++;
		}
		return headerLine;
	}
}
/**
 * Construction du parseur,
 * soit à partir de common.csv si les séparateurs sont assimilables à des caractères
 * soit à partir d'une regex
 */
private void DVP_buildFormat(){
	if(		escapeChar.length()==1
			&&	delimiter.length()==1
			&&	quoter.length()==1
		)
			DVP_csvFormat = CSVFormat.DEFAULT
					.withEscape(escapeChar.charAt(0))
					.withDelimiter(delimiter.charAt(0))
					.withQuote(quoter.charAt(0))
					.withRecordSeparator(lineDelimiter);
		else
			DVP_regex = this.delimiter+"(?=([^"+this.quoter+"]*"+this.quoter+"[^"+this.quoter+"]*"+this.quoter+")*[^"+this.quoter+"]*$)";

}
/**
 * Préparation d'une valeur pour écriture dans le fichier plat
 * Echappe les quotes, encadre éventuellement le texte
 * @param valueToCast	Valeur à caster
 * @return
 */
private String DVP_castValue(Object valueToCast){
	if( valueToCast == null ) return "" ;
	String toRet = String.valueOf(valueToCast) ;
	if(toRet.contains(this.quoter)||toRet.contains(this.delimiter))toRet = this.quoter+toRet.replace(this.quoter,this.escapeChar+this.quoter)+this.quoter;
	return toRet ;
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
		long nbLine = -1;
		boolean loop = true;

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Contrôle de l'objet
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Parse du fichier (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

		Integer headerIdx = DVP_getHeaderLine();
		try (Scanner scanner = new Scanner(DVP_newStream())) {
			while (scanner.hasNextLine() && loop) {
				nbLine++;
				if (nbLine < headerIdx) {
					continue;
				}

				String line = scanner.nextLine();
				if (nbLine == headerIdx.longValue()) {
					DVP_setHeader(DVP_splitLine(line));
					loop = false;
				}
			}
		}

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
public HashMap<Integer,Map<String,Object>> readBigFile (
	Integer i_nRow,
	Integer i_nRow_from,
	Integer i_nRow_to
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<Integer,Map<String,Object>> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		WILD_toReturn = new HashMap<>();
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Contrôle de l'objet
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Identification de la portion à récupérer
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

		long start;
		long end;
		if (i_nRow != null) {
			start = end = i_nRow.longValue();
		} else if (i_nRow_from != null && i_nRow_to != null) {
			start = i_nRow_from.longValue();
			end = i_nRow_to.longValue();
		} else {
			throw new IllegalStateException("Les indexes de lecture ne sont pas spécifié");
		}

		if (start > end) {
			throw new IllegalStateException("L'index de début de lecture est superieur à l'index de fin. Début:"+start+" Fin:"+end);
		}
		if ((end - start) >= Integer.MAX_VALUE) {
			throw new IllegalStateException("La plage d'extraction est trop grande et dépasse la capacitée d'un Integer.");
		}

//	//	//	Etape	"3" : poids relatif de 10, Récupération des enregistrements sélectionnés
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

        try (final DVP_LazyIterator it = new DVP_LazyIterator(start, end)) {
            while (it.hasNext())
                WILD_toReturn.put((int)it.getIndex(), (Map) it.next());
        }

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

	/**
	 * Map la ligne d'une record parsé avec la liste des header parsé par _nextIndex.
	 *
	 * @param headers ligne de header parsé
	 * @param recordElem ligne d'un record parsé
     * @return
     */
	private HashMap<String,String> DVP_mapRecordByIndex(String[] headers, String[] recordElem) {
		HashMap<String, String> outputMap = new LinkedHashMap<>(headers.length);
		for (int i = 0; i < headers.length /*&& i < recordElem.length*/; i++) {
			String value = null;
			if (i <= recordElem.length - 1) {
				value = recordElem[i];
			}
			outputMap.put(headers[i], value);
		}
		return outputMap;
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
 public HashMap<Integer,Map<String,Object>> readBigFile (
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
 public HashMap<Integer,Map<String,Object>> readBigFile (
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
		Path filePath;
		if(fileFile !=null) {
			filePath = fileFile.toPath();
		} else {
			filePath = Paths.get(pathToFile);
		}
		Charset charset = Charset.forName(mimeEncoding);
//	//	//	Etape	"1" : poids relatif de 10, Contrôle de l'objet
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

		if (i_mapToWrite == null && i_iteratorToWrite == null && i_inputToWrite == null) {
			throw new IllegalStateException("Les entrées à écrire sont null ou vide");
		}

		OpenOption[] openOptions = new OpenOption[3];
		openOptions[0] = StandardOpenOption.CREATE;
		openOptions[1] = StandardOpenOption.WRITE;
		if (i_appender) {
			openOptions[2] = StandardOpenOption.APPEND;
		} else {
			openOptions[2] = StandardOpenOption.TRUNCATE_EXISTING;
		}

		HashMap<Integer, String> headers = null;
		WildDataSet dataSet = this.getDataSet("1");
		if (dataSet != null) {
			headers = dataSet.DVP_getHeader();
		}

                /* We cannot find headers from existing dataset. If we're in
                 * append mode and the file has content, we'll search for an
                 * existing header. If there's no valid header in it, we must
                 * throw an exception.
                 * If the file does not exists or is empty, or we're in truncate
                 *  mode, we will take headers from data to write later.
                 */
                if (headers == null || headers.isEmpty()) {
                    if (i_appender && DVP_hasContent()) {
                        final Integer headerIdx = DVP_getHeaderLine();
                        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(DVP_newStream()))) {
                            int index = 0;
                            String line;
                            do {
                                line = reader.readLine();
                            } while (line != null && index <= headerIdx);

                            final String[] tmpHeaders;
                            if ((line != null && (tmpHeaders = DVP_splitLine(line)).length > 0)) {
                                DVP_setHeader(tmpHeaders);
                                headers = getDataSet("1").DVP_getHeader();
                            } else if (index > 0 || (line != null && !line.trim().isEmpty()) || reader.readLine() != null) { // Non empty file. We cannot add data without knowing its exact header syntax.
                                throw new IllegalStateException("Le fichier dans lequel les données doivent être ajoutées n'a pas d'entête valide !");
                            }
                        }
                    }
                }

		long recordNb = 0;
		try (BufferedWriter writer = Files.newBufferedWriter(filePath, charset, openOptions)) {
//	//	//	Etape	"2" : poids relatif de 100, Remplissage du fichier (+++)
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

			if(headers != null && !i_appender && this.DVP_exportWithHeaders) {
				DVP_writeHeaders(writer, headers);
			}

			if (i_iteratorToWrite != null || i_mapToWrite != null) {
                            Iterator<Map<String, Object>> readIte = i_iteratorToWrite != null ? i_iteratorToWrite : i_mapToWrite.values().iterator();
                            recordNb += DVP_writeIterator(writer, headers, readIte);
			} else {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(i_inputToWrite))) {
					//////
					// Si le fichier est vide et n'a pas encore de header,
					// lecture ligne à ligne tant que le caractère délimitant les colonnes est trouvé
					// puis création de colones nommées "Col1", "Col2", ... etc
					if (headers == null) {
						do {
							String line = reader.readLine();
							if (!line.trim().isEmpty() && line.contains(delimiter)) {
								headers = new LinkedHashMap<>();
								int nbCol = line.split(delimiter).length;
								for (int i = 1; i <= nbCol; i++) {
									headers.put(i, "Col"+i);
								}
								DVP_writeHeaders(writer, headers);
								DVP_setHeader(headers.values().toArray(new String[nbCol]));

								//écriture le la ligne qui a servit à créer les headers
								writer.newLine();
								writer.write(line);
								recordNb++;
							}
						} while (headers == null);
					}

					//depuis un stream
					String[] headersArray = new String[headers.size()];
					for (Integer key : headers.keySet()) {
						headersArray[key - 1] = headers.get(key);
					}
					CSVFormat readerFormat = DVP_csvFormat
							.withHeader(headersArray)
							.withSkipHeaderRecord(true);


					try (CSVParser parser = new CSVParser(reader, readerFormat)) {
						Iterator<CSVRecord> recordIte = parser.iterator();
						recordNb += DVP_writeIterator(writer, headers, recordIte);
					}
				}
			}

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
			this.WILD_setStep(); // Ne pas modifier
			// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		}
//	//	//	Output	"writeBigFile_output1" : Chemin du fichier de sortie (String)
		this.WILD_setOutput("writeBigFile_output1",this.fileFile.getAbsolutePath()); // Ne pas modifier

//	//	//	Output	"writeBigFile_output2" : Nombre de lignes écrites (Integer)
		this.WILD_setOutput("writeBigFile_output2",recordNb); // Ne pas modifier

//	//	//	Output	"writeBigFile_output3" : Nombre de valeur écrites (Integer)
		this.WILD_setOutput("writeBigFile_output3",recordNb * getHeader().size()); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

    /**
     * Ecrit chaque enregistrement contenu dans un itérateur dans un {@link BufferedWriter} de sortie
     *
     * @param writer writer dans lequel écrire
     * @param headers map {@code Map<Integer, String>} des headers du fichiers avec l'ordre en clé et leur nom en valeur.
     *                Si les headers sont nul (fichier vide), on utilise les noms des header du premier
     *                enregistrement de l'Iterateur.
     * @param recordIte itérateur sur une liste d'enregistrement
     * @return retourne le nombre d'enregistrement écrit
     * @throws IOException
     */
    private long DVP_writeIterator(BufferedWriter writer, HashMap<Integer, String> headers, Iterator recordIte) throws Exception {
        long recordNb = 0;
        if ((headers == null || headers.isEmpty()) && recordIte.hasNext()) {
            final Object record = recordIte.next();
            final Map<String, Object> recordMap = DVP_extractRecordMap(record);
            headers = new LinkedHashMap<>();
            int idx = 0;
            for (String headerName : recordMap.keySet()) {
                headers.put(idx++, headerName);
            }
            DVP_writeHeaders(writer, headers);
            DVP_setHeader(recordMap.keySet().toArray(new String[recordMap.size()]));

            DVP_writeRecord(writer, record, headers);
            recordNb++;
        }

        while (recordIte.hasNext()) {
            DVP_writeRecord(writer, recordIte.next(), headers);
            recordNb++;

            if (recordNb % 1000 == 0) {
                WILD_echo("Commit : " + String.valueOf(recordNb));
                writer.flush();
            }
        }

        //flush des record restant
        WILD_echo("Commit : " + String.valueOf(recordNb));
        writer.flush();
        return recordNb;
    }

	/**
	 * Ecrit la ligne des headers dans un {@link BufferedWriter}
	 *
	 * @param writer writer dans lequel ecrir
	 * @param headers map {@code Map<Integer, String>} des headers à écrire avec l'ordre en clé et leur nom en valeur.
	 * @throws IOException
     */
	private void DVP_writeHeaders(BufferedWriter writer, HashMap<Integer, String> headers) throws IOException {
		StringBuilder buffer = new StringBuilder();
		Set<Integer> headersIdx = new TreeSet<>(headers.keySet());

		if (headersIdx.size() > 0) {
			int i = 1;
			for (Integer idx : headersIdx) {
				buffer.append(DVP_castValue(headers.get(idx)));
				if (i != headers.size()) buffer.append(delimiter);
				i++;
			}
			writer.write(buffer.toString());
			writer.flush();
		}
	}

	/**
	 * Ecrit un enregistrement dans le writer d'entrée.
	 *
	 * @param writer writer dans lequel ecrire
	 * @param record un enregistrement de type {@link Map<String,Object>} ou {@link CSVRecord}
	 * @param headers map {@code Map<Integer, String>} des headers du fichiers avec l'ordre en clé et leur nom en valeur.
	 * @throws IOException
     */
	private void DVP_writeRecord(BufferedWriter writer, Object record, HashMap<Integer, String> headers) throws IOException {
		if (record != null && headers != null) {
			StringBuilder buffer = new StringBuilder();
			Set<Integer> headersIdx = new TreeSet<>(headers.keySet());

			Map<String, Object> recordMap = DVP_extractRecordMap(record);
			if (headersIdx.size() > 0) {
				buffer.append(lineDelimiter);
				int i = 1;
				for (Integer idx : headersIdx) {
					String headerName = headers.get(idx);
					Object recordValue = recordMap.get(headerName);
					buffer.append(DVP_castValue(recordValue));
					if (i != headers.size()) buffer.append(delimiter);
					i++;
				}
				writer.write(buffer.toString());
			}
		}
	}

	/**
	 * Convertie un objet de type enregistrement ({@link CSVRecord} ou {@link Map}) dans un enregistrement de type
	 * {@code Map<String, Object>} ou la clé correspond au nom du header et la value la valeur associée
	 *
	 * @param record {@link CSVRecord} ou {@link Map}
	 * @return
	 * @throws IllegalArgumentException si le type de l'objet n'est pas supporté
     */
	@SuppressWarnings("unchecked")
	private Map<String, Object> DVP_extractRecordMap(Object record) throws IllegalArgumentException {
		Map<String, Object> recordMap = new LinkedHashMap<>();
		if (record instanceof CSVRecord) {
            CSVRecord csvRecord = (CSVRecord) record;

            //conversion Map<String, String> vers Map<String, Object>
            Map<String, String> tmpMap = csvRecord.toMap();
            for (Map.Entry<String, String> entry : tmpMap.entrySet()) {
                recordMap.put(entry.getKey(), entry.getValue());
            }
        } else if (record instanceof Map) {
            recordMap = (Map<String, Object>) record;
        } else {
            throw new IllegalArgumentException("Le type de l'enregistrement "+record.getClass().getCanonicalName()
                    +" n'est pas supporté à l'écriture. Le types supporté sont CSVRecord ou Map<String, Object>");
        }
		return recordMap;
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
		Files.deleteIfExists(fileFile.toPath());
		this.WILD_setOutput("delete_output1",this.filePath);
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
		Path target = Paths.get(WILD_convertPath(i_pathToFile_OUT));
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Copie
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		Files.copy(DVP_newStream(), target, StandardCopyOption.REPLACE_EXISTING);

//	//	//	Output	"copyToBigFile_output1" : Chemin du fichier de sortie (String)
		this.WILD_setOutput("copyToBigFile_output1",target.toAbsolutePath().toString()); // Ne pas modifier

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
		Path source = fileFile.toPath();
		Path target = Paths.get(WILD_convertPath(i_pathToFile_OUT));
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Copie
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		//utilisation d'un copy + delete au lieu de Files.move pour une compatibilité windows
		Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
		Files.deleteIfExists(source);
//	//	//	Etape	"2" : poids relatif de 10, Suppression
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"moveToBigFile_output1" : Chemin du fichier de sortie (String)
		this.WILD_setOutput("moveToBigFile_output1",target.toAbsolutePath().toString()); // Ne pas modifier

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
		if(this.dataSets==null||this.dataSets.get("1")==null||this.dataSets.size()==0)this.castFile();
		WildDataSet wildDataSet = this.getDataSet("1");
//	//	//	Etape	"2" : poids relatif de 10, Identification de la portion à récupérer
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(i_nRow != null){
			HashMap<String,Object> toRet = wildDataSet.DVP_getData(i_nRow);
			if(toRet != null){
				WILD_toReturn = new HashMap();
				WILD_toReturn.put(1, toRet);
			}
		}else if(i_nRow_from != null && i_nRow_to != null)
				WILD_toReturn = wildDataSet.DVP_getData(i_nRow_from,i_nRow_to);

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
		//if(this.fileHeaders==null)fileHeaders = this.getDataSet("1").DVP_getHeader();

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Contrôle de l'objet
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(this.dataSets==null||this.dataSets.get("1")==null||this.dataSets.size()==0)this.castFile();
		WildDataSet wildDataSet = this.getDataSet("1");
		if(i_appender==false)wildDataSet.DVP_empty();


//	//	//	Etape	"2" : poids relatif de 100, Remplissage du fichier (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		Integer n_recordAdded = 0 ; // NOmbre d'enregistrements ajoutés

		if(i_mapToWrite!=null)for(Integer key:i_mapToWrite.keySet()){
			wildDataSet.add((HashMap<String,Object>)i_mapToWrite.get(key));
			n_recordAdded++;
		}
		if(i_iteratorToWrite!=null)while(i_iteratorToWrite.hasNext()) {
			HashMap<String,Object> element = (HashMap<String,Object>) i_iteratorToWrite.next();
			wildDataSet.add(element);
			n_recordAdded++;
		}
		if(i_inputToWrite!=null){
			String line;
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader( i_inputToWrite));
			while( (line = bufferedReader.readLine()) != null ){
				DVP_addRecord(line);
				n_recordAdded++;
			}
		}
		commit();
//	//	//	Output	"writeFile_output1" : Chemin du fichier de sortie (String)
			this.WILD_setOutput("writeFile_output1",this.fileFile.getAbsolutePath()); // Ne pas modifier

//	//	//	Output	"writeFile_output2" : Nombre de lignes écrites (Integer)
		this.WILD_setOutput("writeFile_output2",n_recordAdded); // Ne pas modifier

//	//	//	Output	"writeFile_output3" : Nombre de valeur écrites (Integer)

		this.WILD_setOutput("writeFile_output3", n_recordAdded*this.fileHeaders.size()); // Ne pas modifier

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
 public void DVP_writeFile (
	InputStream i_inputToWrite,
	Boolean i_appender
	)throws Exception{writeFile(null,i_appender,null,i_inputToWrite);
}
 /**
  * Remplissage du fichier par une Map avec écriture sur disque
  *
  * @param i_dataSet	Source à écrire, jeu de données
 * @param previousLineSep
  * @param i_appender	A true, écrit à la suite du fichier, sinon le remplace {}
  */
public void DVP_fillWith (
	WildDataSet i_dataSet,
	Boolean append
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier
	OutputStream out = null ;
//	// Mode try de récupération des erreurs pour log
	try{if(i_dataSet!=null&&i_dataSet.DVP_getLength()!=0){
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		 boolean previousLineSep = false ;

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

		this.addDataSet(i_dataSet) ;
		WildDataSet wDs = this.getDataSet("1");
		HashMap<Integer,String> headers = wDs.DVP_getHeader() ;

//	//	//	Etape	"2" : poids relatif de 20, Vidage du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(append&&fileFile.exists()&&fileFile.length()!=0){
			DVP_exportWithHeaders=false;
			previousLineSep = true ;
		}else DVP_exportWithHeaders = true ;
		out = new FileOutputStream(fileFile,append);
//	//	//	Etape	"3" : poids relatif de 50, Ecriture dufichier (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

		if(this.DVP_exportWithHeaders){
			String lineValue = "" ;
			for(Integer j = 1 ; j <= headers.size() ; j++){
				if(j == headers.size())lineValue += DVP_castValue(headers.get(j))+ this.lineDelimiter ;
				else lineValue += DVP_castValue(headers.get(j))+this.delimiter;
			}
			out.write(lineValue.getBytes());
		}
		if(previousLineSep&&wDs.DVP_getLength()>0){
			String lineValue=this.lineDelimiter;
			out.write(lineValue.getBytes());
		}
		for(Integer i = 1 ; i <= wDs.DVP_getLength() ; i++){
			String lineValue = "" ;
			for(Integer j = 1 ; j <= headers.size() ; j++){
				if(j == headers.size()&&i == wDs.DVP_getLength())lineValue += DVP_castValue(wDs.DVP_getData(i, headers.get(j)));
				else if(j == headers.size()) lineValue += DVP_castValue(wDs.DVP_getData(i, headers.get(j)))+ this.lineDelimiter ;
				else if(headers.get(j)!=null)lineValue += DVP_castValue(wDs.DVP_getData(i, headers.get(j)))+this.delimiter;


			}
			// Affichage d'un signal toutes les 1000 lignes
			if(i%1000==0)WILD_echo("Commit : "+String.valueOf(i));
			if(lineValue.trim().length()>0&&!lineValue.equals(this.lineDelimiter))out.write(lineValue.getBytes());
		}
//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"fillWith_output1" : Chemin du fichier de sortie (String)
	//	this.WILD_setOutput("fillWith_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

}}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
		if(out!=null)out.close();
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : findEqualBigFile - Récupération des numéros de lignes des enregistrements, correspondant à une égalité stricte, selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non.{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

    /**
     * Récupération des numéros de lignes des enregistrements, correspondant à
     * une égalité stricte, selon options de suppression des caractères non
     * signifiants en début, en fin ou en milieux de caractères, sensible à la
     * casse ou non, sensible au caractère spéciaux dont accents ou non.{ } Code
     * généré automatiquement par l'outil Wild Méthode susceptible d'être
     * surchargée Cette méthode répond à l'ensemble des sollicitations de la
     * "WildMethod" indiquée dans la documentation WILD
     *
     * @param i_colName	Nom de la colonne{}
     * @param i_colValue	Valeur d'objet de comparaison{}
     * @param i_nullSens	Sensible aux valeurs vides{}
     * @param i_caseSens	Sensible à la casse{}
     * @param i_specSens	Sensible aux caractères spéciaux{}
     * @return	{}
     */
    public Integer[] findEqualBigFile(
            String i_colName,
            Object i_colValue,
            Boolean i_nullSens,
            Boolean i_caseSens,
            Boolean i_specSens
    ) throws Exception {
//	// Amorce de la méthode
        WILD_beginMethod(); // Ne pas modifier
        //	Variable générique de retour
        List<Integer> WILD_toReturn = new ArrayList<>(); // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
        try {
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            final Map<String, Object> condition = new HashMap<>(6);
            condition.put("WildCode", "equal");
            condition.put("ColumnName", i_colName);
            condition.put("ColumnValue", i_colValue);
            condition.put("NullSens", i_nullSens);
            condition.put("CaseSens", i_caseSens);
            condition.put("SpecSens", i_specSens);

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            try (final DVP_LazyIterator sourceIt = new DVP_LazyIterator();
                    final DVP_ConditionIterator it = new DVP_ConditionIterator(sourceIt, Collections.singletonMap(1, condition))) {
                while (it.hasNext()) {
                    it.next();
                    WILD_toReturn.add((int) sourceIt.getIndex());
                }
            }

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

        } catch (Exception e) {
            this.WILD_logException(e); // Ne pas modifier
        } finally {
//	// Fin de la méthode
            WILD_endMethod(); // Ne pas modifier
            return WILD_toReturn.toArray(new Integer[WILD_toReturn.size()]); // Ne pas modifier
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
     * Récupération des numéros de lignes des enregistrements, correspondant à
     * une condition de contenu (la cible contient le terme recherché), selon
     * options de suppression des caractères non signifiants en début, en fin ou
     * en milieux de caractères, sensible à la casse ou non, sensible au
     * caractère spéciaux dont accents ou non. Ne porte que sur les chaînes de
     * caractère.{ } Code généré automatiquement par l'outil Wild Méthode
     * susceptible d'être surchargée Cette méthode répond à l'ensemble des
     * sollicitations de la "WildMethod" indiquée dans la documentation WILD
     *
     * @param i_colName	Nom de la colonne{}
     * @param i_colValue	Valeur d'objet de comparaison{}
     * @param i_nullSens	Sensible aux valeurs vides{}
     * @param i_caseSens	Sensible à la casse{}
     * @param i_specSens	Sensible aux caractères spéciaux{}
     * @return	{}
     */
    public Integer[] findContainBigFile(
            String i_colName,
            Object i_colValue,
            Boolean i_nullSens,
            Boolean i_caseSens,
            Boolean i_specSens
    ) throws Exception {
//	// Amorce de la méthode
        WILD_beginMethod(); // Ne pas modifier
        //	Variable générique de retour
        List<Integer> WILD_toReturn = new ArrayList<>();  // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
        try {
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            final Map<String, Object> condition = new HashMap<>(6);
            condition.put("WildCode", "contain");
            condition.put("ColumnName", i_colName);
            condition.put("ColumnValue", i_colValue);
            condition.put("NullSens", i_nullSens);
            condition.put("CaseSens", i_caseSens);
            condition.put("SpecSens", i_specSens);

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            try (final DVP_LazyIterator sourceIt = new DVP_LazyIterator();
                    final DVP_ConditionIterator it = new DVP_ConditionIterator(sourceIt, Collections.singletonMap(1, condition))) {
                while (it.hasNext()) {
                    it.next();
                    WILD_toReturn.add((int) sourceIt.getIndex());
                }
            }

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

        } catch (Exception e) {
            this.WILD_logException(e); // Ne pas modifier
        } finally {
//	// Fin de la méthode
            WILD_endMethod(); // Ne pas modifier
            return WILD_toReturn.toArray(new Integer[WILD_toReturn.size()]); // Ne pas modifier
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
     * Récupération des numéros de lignes des enregistrements, correspondant à
     * un terme recherché (la cible répond à une expression régulière comportant
     * le terme recherché).{ } Code généré automatiquement par l'outil Wild
     * Méthode susceptible d'être surchargée Cette méthode répond à l'ensemble
     * des sollicitations de la "WildMethod" indiquée dans la documentation WILD
     *
     * @param i_colName	Nom de la colonne{}
     * @param i_colRegexp	Expression régulière testée{}
     * @param i_nullSens	Sensible aux valeurs vides{}
     * @param i_caseSens	Sensible à la casse{}
     * @param i_specSens	Sensible aux caractères spéciaux{}
     * @return	{}
     */
    public Integer[] findRegexBigFile(
            String i_colName,
            String i_colRegexp,
            Boolean i_nullSens,
            Boolean i_caseSens,
            Boolean i_specSens
    ) throws Exception {
//	// Amorce de la méthode
        WILD_beginMethod(); // Ne pas modifier
        //	Variable générique de retour
        List<Integer> WILD_toReturn = new ArrayList<>(); // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
        try {
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            final Map<String, Object> condition = new HashMap<>(6);
            condition.put("WildCode", "regex");
            condition.put("ColumnName", i_colName);
            condition.put("ColumnValue", i_colRegexp);
            condition.put("NullSens", i_nullSens);
            condition.put("CaseSens", i_caseSens);
            condition.put("SpecSens", i_specSens);

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            try (final DVP_LazyIterator sourceIt = new DVP_LazyIterator();
                    final DVP_ConditionIterator it = new DVP_ConditionIterator(sourceIt, Collections.singletonMap(1, condition))) {
                while (it.hasNext()) {
                    it.next();
                    WILD_toReturn.add((int) sourceIt.getIndex());
                }
            }

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

        } catch (Exception e) {
            this.WILD_logException(e); // Ne pas modifier
        } finally {
//	// Fin de la méthode
            WILD_endMethod(); // Ne pas modifier
            return WILD_toReturn.toArray(new Integer[WILD_toReturn.size()]); // Ne pas modifier
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
     * Récupération des numéros de lignes des enregistrements, dont une valeur
     * précise est contenue entre deux bornes (incluses). Pour les champs de
     * type chaîne de caractères, l’ordre alphabétique est retenu (sensible à la
     * casse, pas de suppression des caractères non signifiants). Une valeur «
     * vide » signifie une absence de borne.{ } Code généré automatiquement par
     * l'outil Wild Méthode susceptible d'être surchargée Cette méthode répond à
     * l'ensemble des sollicitations de la "WildMethod" indiquée dans la
     * documentation WILD
     *
     * @param i_colName	Nom de la colonne{}
     * @param i_minValue	Valeur inférieure{}
     * @param i_minExclude	Est exclu par le bas ?{}
     * @param i_maxValue	Valeur supérieure{}
     * @param i_maxExclude	Est exclu par le haut ?{}
     * @return	{}
     */
    public Integer[] findWithinBigFile(
            String i_colName,
            Object i_minValue,
            Boolean i_minExclude,
            Object i_maxValue,
            Boolean i_maxExclude
    ) throws Exception {
//	// Amorce de la méthode
        WILD_beginMethod(); // Ne pas modifier
        //	Variable générique de retour
        List<Integer> WILD_toReturn = new ArrayList<>(); // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
        try {
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            final Map<String, Object> condition = new HashMap<>(6);
            condition.put("WildCode", "within");
            condition.put("ColumnName", i_colName);
            condition.put("ColumnMinValue", i_minValue);
            condition.put("ColumnMaxValue", i_maxValue);
            condition.put("ColumnMinValueInclude", !i_minExclude);
            condition.put("ColumnMaxValueInclude", !i_maxExclude);

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            try (final DVP_LazyIterator sourceIt = new DVP_LazyIterator();
                    final DVP_ConditionIterator it = new DVP_ConditionIterator(sourceIt, Collections.singletonMap(1, condition))) {
                while (it.hasNext()) {
                    it.next();
                    WILD_toReturn.add((int) sourceIt.getIndex());
                }
            }

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

        } catch (Exception e) {
            this.WILD_logException(e); // Ne pas modifier
        } finally {
//	// Fin de la méthode
            WILD_endMethod(); // Ne pas modifier
            return WILD_toReturn.toArray(new Integer[WILD_toReturn.size()]); // Ne pas modifier
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
	List<Integer> WILD_toReturn = new ArrayList<>(); // Ne pas modifier

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
        final Map<String, Object> condition = new HashMap<>(6);
        condition.put("WildCode", "compare");
        condition.put("ColumnName", i_colName);
        condition.put("ColumnMinValue", i_minValue);
        condition.put("ColumnMaxValue", i_maxValue);
        condition.put("ColumnMinValueInclude", !i_minExclude);
        condition.put("ColumnMaxValueInclude", !i_maxExclude);

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            try (final DVP_LazyIterator sourceIt = new DVP_LazyIterator();
                    final DVP_ConditionIterator it = new DVP_ConditionIterator(sourceIt, Collections.singletonMap(1, condition))) {
                while (it.hasNext()) {
                    it.next();
                    WILD_toReturn.add((int) sourceIt.getIndex());
                }
            }

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn.toArray(new Integer[WILD_toReturn.size()]); // Ne pas modifier
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
     * Récupération des numéros de lignes des enregistrements, combinant
     * l’ensemble des conditions précédemment listées. Une liste des indexations
     * des conditions est fournie en Tableau 1, et permet le stockage des
     * conditions dans une table de paramètres. Chacune des conditions répond
     * soit à une condition OU, soit à une condition ET (opérateur de
     * combinaison de condition), à l’exception de la première condition listée.
     * Chacune des conditions répond soit à une condition positive (on sélection
     * les numéros de lignes répondant à la condition) soit négative (on exclut
     * sélection les numéros de lignes répondant à la condition). Une condition
     * négative est préfixée du caractère « ! ».{ } Code généré automatiquement
     * par l'outil Wild Méthode susceptible d'être surchargée Cette méthode
     * répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans
     * la documentation WILD
     *
     * @param i_conditionList	liste des conditions{}
     * @return	{}
     */
    public Integer[] findLinesBigFile(
            Map<Integer, Map<String, Object>> i_conditionList
    ) throws Exception {
//	// Amorce de la méthode
        WILD_beginMethod(); // Ne pas modifier
        //	Variable générique de retour
        List<Integer> WILD_toReturn = new ArrayList<>(); // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
        try {
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
            try (final DVP_LazyIterator sourceIt = new DVP_LazyIterator();
                    final DVP_ConditionIterator it = new DVP_ConditionIterator(sourceIt, i_conditionList)) {
                while (it.hasNext()) {
                    it.next();
                    WILD_toReturn.add((int) sourceIt.getIndex());
                }
            }

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

        } catch (Exception e) {
            this.WILD_logException(e); // Ne pas modifier
        } finally {
//	// Fin de la méthode
            WILD_endMethod(); // Ne pas modifier
            return WILD_toReturn.toArray(new Integer[WILD_toReturn.size()]); // Ne pas modifier
        }
    }

/*	### NOUVELLE METHODE ###
	Méthode : calculateBigFile - Calcul de statistiques sur les valeurs des enregistrements, combinant l’ensemble des conditions précédemment listées. La liste des statistiques retenues est présentée en Tableau 1. Le Tableau 2 plus loin renvoie à un _nextIndex des conditions pour implémentation.{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

    /**
     * Calcul de statistiques sur les valeurs des enregistrements, combinant
     * l’ensemble des conditions précédemment listées. La liste des statistiques
 retenues est présentée en Tableau 1. Le Tableau 2 plus loin renvoie à un
 _nextIndex des conditions pour implémentation.{ } Code généré automatiquement
 par l'outil Wild Méthode susceptible d'être surchargée Cette méthode
 répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans
 la documentation WILD
     *
     * @param i_colName	Nom de la colonne de calcul{}
     * @param i_conditionList	Liste des conditions{}
     * @param i_statKey	Nom de la statistique{}
     * @param i_statParam	Paramètre associé à la statistique{}
     * @return	{}
     */
    public Map<String, Object> calculateBigFile(
            String i_colName,
            Map<Integer, Map<String, Object>> i_conditionList,
            String i_statKey,
            Integer i_statParam
    ) throws Exception {
//	// Amorce de la méthode
        WILD_beginMethod(); // Ne pas modifier
        //	Variable générique de retour
        Map<String, Object> WILD_toReturn = new HashMap<>();; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
        try {
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

            long count = -1;
            Object result = null;

            // If user asks for a percentile of 0 or 100 percent, it means he wants
            // an extremum value, so we can replace the operation to perform for faster results.
            if (i_statKey.toLowerCase().equals("percentile_x") && (i_statParam <= 0 || i_statParam >= 100))
                i_statKey = i_statParam <= 0 ? "minimum" : "maximum";

            switch (i_statKey.toLowerCase()) {
                case "maximum":
                    double max = Double.NEGATIVE_INFINITY;
                    try (FiniteNumberIterator it = new FiniteNumberIterator(DVP_getNumberColumnIterator(i_colName, i_conditionList))) {
                        while (it.hasNext()) {
                            max = Math.max(max, it.next().doubleValue());
                        }
                    }
                    if (max > Double.NEGATIVE_INFINITY)
                        result = max;
                    else
                        result = Double.NaN;

                    break;

                case "minimum":
                    double min = Double.POSITIVE_INFINITY;
                    try (FiniteNumberIterator it = new FiniteNumberIterator(DVP_getNumberColumnIterator(i_colName, i_conditionList))) {
                        while (it.hasNext()) {
                            min = Math.min(min, it.next().doubleValue());
                        }
                    }

                    if (min < Double.POSITIVE_INFINITY)
                        result = min;
                    else
                        result = Double.NaN;

                    break;

                case "percentile_x":
                    result = DVP_quantileBigFile(i_statParam / 100f, i_colName, i_conditionList);
                    break;

                case "median":
                    result = DVP_quantileBigFile(0.5f, i_colName, i_conditionList);
                    break;

                case "avg":
                    result = DVP_accumulate(i_statKey, i_conditionList, i_colName).mean();
                    break;

                case "geom_avg":
                    result = DVP_geometricAvgBigFile(i_colName, i_conditionList);
                    break;

                case "var":
                    result = Math.pow(
                            DVP_accumulate(i_statKey, i_conditionList, i_colName)
                            .standardDeviation(true),
                            2
                    );
                    break;

                case "std_dev":
                    result = DVP_accumulate(i_statKey, i_conditionList, i_colName).standardDeviation(true);
                    break;

                case "significant_nb":
                    count = 0;
                    Number n;
                    try (DVP_NumberIterator it = DVP_getNumberColumnIterator(i_colName, i_conditionList)) {
                        while (it.hasNext()) {
                            n = it.next();
                            if (n != null && !Double.isNaN(n.doubleValue()))
                                count++;
                        }
                    }

                    result = count;
                    break;

                case "unsignificant_nb":
                    count = 0;
                    try (DVP_NumberIterator it = DVP_getNumberColumnIterator(i_colName, i_conditionList)) {
                        while (it.hasNext()) {
                            n = it.next();
                            if (n == null || Double.isNaN(n.doubleValue()))
                                count++;
                        }
                    }

                    result = count;
                    break;
//
                case "distinct_nb":
                    final HashSet distinctValues = new HashSet();
                    try (DVP_ConditionIterator it = new DVP_ConditionIterator(new DVP_LazyIterator(), i_conditionList)) {
                        while (it.hasNext()) {
                            distinctValues.add(it.next().get(i_colName));
                        }
                    }

                    result = distinctValues.size();
                    break;

                case "values_nb":
                case "frequencies":
                case "modal_value":
                    // For the 3 statistics above, we start with the same process : counting occurences of each distinct values.
                    count = 0;
                    final HashMap<String, Number> repartition = new HashMap();
                    try (DVP_ConditionIterator it = new DVP_ConditionIterator(new DVP_LazyIterator(), i_conditionList)) {
                        String tmp;
                        AtomicLong l;
                        while (it.hasNext()) {
                            count++;
                            tmp = it.next().get(i_colName);
                            if (tmp == null)
                                continue;

                            l = (AtomicLong) repartition.get(tmp);
                            if (l == null) {
                                l = new AtomicLong(1);
                                repartition.put(tmp, l);
                            } else
                                l.incrementAndGet();
                        }
                    }

                    switch (i_statKey) {
                        case "values_nb":
                            result = repartition;
                            count = repartition.size();
                            break;

                        case "frequencies":
                            Iterator<Map.Entry<String, Number>> it = repartition.entrySet().iterator();
                            Map.Entry<String, Number> entry;
                            while (it.hasNext()) {
                                entry = it.next();
                                entry.setValue(entry.getValue().doubleValue() / count);
                            }
                            result = repartition;
                            count = repartition.size();
                            break;

                        case "modal_value":
                            long maxOccur = Long.MIN_VALUE, currentOccur;
                            final ArrayList<String> modals = new ArrayList<>();
                            it = repartition.entrySet().iterator();
                            // Find values which repeat the most in the file.
                            while (it.hasNext()) {
                                entry = it.next();
                                currentOccur = entry.getValue().longValue();
                                if (currentOccur == maxOccur)
                                    modals.add(entry.getKey());
                                else if (currentOccur > maxOccur) {
                                    modals.clear();
                                    modals.add(entry.getKey());
                                    maxOccur = currentOccur;
                                }
                            }
                            if (modals.size() == 1)
                                result = modals.get(0);
                            else if (modals.size() > 1)
                                result = modals.toArray();

                            WILD_toReturn.put("associated_max_value", maxOccur);
                    }

                    break;

                default:
                    break;
            }

//	//	//	Etape	"4" : poids relatif de 20, Construction du retour (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            WILD_toReturn.put("result", result);
            if (count >= 0)
                WILD_toReturn.put("count", count);

//	//	//	Etape	"5" : poids relatif de 10, Contrôle des flux
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

        } catch (Exception e) {
            this.WILD_logException(e); // Ne pas modifier
        } finally {
//	// Fin de la méthode
            WILD_endMethod(); // Ne pas modifier
            return WILD_toReturn; // Ne pas modifier
        }
    }

    /**
     * Accumulate statistical values for all records of a specific column of the
     * current file.
     *
     * @param title Title to use for resulting statistics object.
     * @param conditions A map containing conditions to check while parsing
     * data.
     * @param column Name of the column to use to build statistics.
     * @return A filled {@link Statistics}.
     * @throws Exception If an error occurs while reading or interpreting data.
     */
    private Statistics DVP_accumulate(final String title, final Map<Integer, Map<String, Object>> conditions, final String column) throws Exception {
        final Statistics stats = new Statistics(title);
        Number n;
        try (final DVP_NumberIterator it = DVP_getNumberColumnIterator(column, conditions)) {
            while (it.hasNext()) {
                n = it.next();
                if (n != null)
                    stats.accept(n.doubleValue());
            }
        }

        return stats;
    }

    /**
     * Iterative quantile (low memory/ high IO && processing).
     */
    private Number DVP_quantileBigFile(final float factor, final String colName, final Map<Integer, Map<String, Object>> conditions) throws Exception {
        // A simple test which computes direct quantile
//        final ArrayList<Double> list = new ArrayList<>();
//        Number last;double value;
//        try (final DVP_NumberIterator it = DVP_getNumberColumnIterator(colName, conditions)) {
//            while (it.hasNext()) {
//                last = it.next();
//                if (last != null && !Double.isNaN((value = last.doubleValue())))
//                    list.add(value);
//            }
//        }
//
//        Collections.sort(list);
//        final int idx = (int) ((list.size() - 1) * factor);
//        return list.get(idx);
        return IoCommons.stats_iterativeQuantile(DVP_getNumberColumnIterable(colName, conditions), factor);
    }

    private double DVP_geometricAvgBigFile(final String colName, final Map<Integer, Map<String, Object>> conditions) throws Exception {
        return IoCommons.stats_geometricAverage(DVP_getNumberColumnIterator(colName, conditions));
    }

    /**
     * Get an iterable object for a specific column, ignoring lines which does not match input conditions.
     * Note : specified column must only contain numbers.
     * @param colName Name of the column to read.
     * @param conditions Set of conditions to use as filter for dataset entries.
     * @return An iterable object providing closeable iterator, streaming selected
     * data.
     * @throws Exception
     */
    private Iterable<Number> DVP_getNumberColumnIterable(final String colName, final Map<Integer, Map<String, Object>> conditions) {
        return new Iterable() {
            @Override
            public Iterator iterator() {
                try {
                    return DVP_getNumberColumnIterator(colName, conditions);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }

    private DVP_NumberIterator DVP_getNumberColumnIterator(final String colName, final Map<Integer, Map<String, Object>> conditions) throws Exception {
        Iterator it = new DVP_LazyIterator();
        if (conditions != null && !conditions.isEmpty())
            it = new DVP_ConditionIterator(it, conditions);
        return new DVP_NumberIterator(it, colName);
    }

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES

/**
 * Calcul de statistiques sur les valeurs des enregistrements, combinant l’ensemble des conditions précédemment listées. La liste des statistiques retenues est présentée en Tableau 1. Le Tableau 2 plus loin renvoie à un _nextIndex des conditions pour implémentation.{
 }
 Code généré automatiquement par l'outil Wild
 Méthode d'appel public non modifiable
 *
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @return	Liste des lignes repérées (Integer[]) {}
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
     * @throws java.lang.Exception
 */
    public Integer[] findTopBigFile(
            String i_colGroup,
            String i_colName,
            Map<Integer, Map<String, Object>> i_conditionList,
            String i_statKey,
            Integer i_statParam,
            Integer i_nbTop
    ) throws Exception {
//	// Amorce de la méthode
        WILD_beginMethod(); // Ne pas modifier
        //	Variable générique de retour
        Integer[] WILD_toReturn = null; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
        try {
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            // First, we must find distinct values for grouping column.
            final Set<String> groupValues = DVP_findDistinctValues(i_colGroup, i_conditionList);

//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            // If there's less values in dataset than required in the top. No need for statistical filter
            final boolean skipStats = groupValues.size() <= i_nbTop;
            if (!skipStats) {
                // We've got more groups than required. We'll filter than by keeping only the one with the highest computed statistics.
                final Map<String, Object> statsByGroup = DVP_statByGroup(i_colName, i_statKey, i_statParam, i_colGroup, groupValues, i_conditionList);
                groupValues.clear(); // We'll use it to reference our top values.
                final ArrayList<Map.Entry<String, Comparable>> tmpGroups = (ArrayList) new ArrayList<>(statsByGroup.entrySet());
                Collections.sort(tmpGroups, new Comparator<Map.Entry<String, Comparable>>() {
                    @Override
                    public int compare(Map.Entry<String, Comparable> o1, Map.Entry<String, Comparable> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });

                for (int i = tmpGroups.size() - 1; i >= 0 && groupValues.size() < i_nbTop; i--) {
                	System.out.println(tmpGroups.get(i).getKey());
                    groupValues.add(tmpGroups.get(i).getKey());
                }
            }

//	//	//	Etape	"4" : poids relatif de 100, Condition sur statistique (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            final List<Integer> indices = new ArrayList<>();
            // Find all records related to above defined groups.
            try (final DVP_LazyIterator sourceIt = new DVP_LazyIterator();
                    final DVP_ConditionIterator it = new DVP_ConditionIterator(sourceIt, i_conditionList)) {
                String value;
                while (it.hasNext()) {
                    value = it.next().get(i_colGroup);
                    if (groupValues.contains(value))
                        indices.add((int) sourceIt.getIndex());
                }
            }

//	//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            WILD_toReturn = indices.toArray(new Integer[indices.size()]);

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

        } catch (Exception e) {
            this.WILD_logException(e); // Ne pas modifier
        } finally {
//	// Fin de la méthode
            WILD_endMethod(); // Ne pas modifier
            return WILD_toReturn; // Ne pas modifier
        }
    }

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 *
 * @param i_colGroup	Nom de la colonne de groupement{}
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
	Méthode : findBottomBigFile  - Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

    /**
     * Récupération des numéros de lignes des enregistrements, correspondant à
     * une condition d’ordre sur une statistique, réutilisant la liste des
     * statistiques à implémenter définies en Tableau 2, citées comme «
     * ordonnées » (O). Ordre : BOTTOM{ } Code généré automatiquement par
     * l'outil Wild Méthode susceptible d'être surchargée Cette méthode répond à
     * l'ensemble des sollicitations de la "WildMethod" indiquée dans la
     * documentation WILD
     *
     * @param i_colGroup	Nom de la colonne de groupement{}
     * @param i_colName	Nom de la colonne de calcul{}
     * @param i_conditionList	Liste des conditions{}
     * @param i_statKey	Nom de la statistique{}
     * @param i_statParam	Paramètre associé à la statistique{}
     * @param i_nbBottom	Nombre de valeurs du « bottom »{}
     * @return	{}
     */
    public Integer[] findBottomBigFile(
            String i_colGroup,
            String i_colName,
            Map<Integer, Map<String, Object>> i_conditionList,
            String i_statKey,
            Integer i_statParam,
            Integer i_nbBottom
    ) throws Exception {
//	// Amorce de la méthode
        WILD_beginMethod(); // Ne pas modifier
        //	Variable générique de retour
        Integer[] WILD_toReturn = null; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
        try {
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            // First, we must find distinct values for grouping column.
            final Set<String> groupValues = DVP_findDistinctValues(i_colGroup, i_conditionList);
            // If there's less values in dataset than required in the top. No need for statistical filter
            final boolean skipStats = groupValues.size() <= i_nbBottom;
            if (!skipStats) {
                // We've got more groups than required. We'll filter than by keeping only the one with the highest computed statistics.
                final Map<String, Object> statsByGroup = DVP_statByGroup(i_colName, i_statKey, i_statParam, i_colGroup, groupValues, i_conditionList);
                groupValues.clear(); // We'll use it to reference our top values.
                final ArrayList<Map.Entry<String, Comparable>> tmpGroups = (ArrayList) new ArrayList<>(statsByGroup.entrySet());
                Collections.sort(tmpGroups, new Comparator<Map.Entry<String, Comparable>>() {
                    @Override
                    public int compare(Map.Entry<String, Comparable> o1, Map.Entry<String, Comparable> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });

                for (int i = 0; i < i_nbBottom; i++) {
                    groupValues.add(tmpGroups.get(i).getKey());
                }
            }

//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            final List<Integer> indices = new ArrayList<>();
            // Find all records related to above defined groups.
            try (final DVP_LazyIterator sourceIt = new DVP_LazyIterator();
                    final DVP_ConditionIterator it = new DVP_ConditionIterator(sourceIt, i_conditionList)) {
                String value;
                while (it.hasNext()) {
                    value = it.next().get(i_colGroup);
                    if (groupValues.contains(value))
                        indices.add((int)sourceIt.getIndex());
                }
            }

//	//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            WILD_toReturn = indices.toArray(new Integer[indices.size()]);

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

        } catch (Exception e) {
            this.WILD_logException(e); // Ne pas modifier
        } finally {
//	// Fin de la méthode
            WILD_endMethod(); // Ne pas modifier
            return WILD_toReturn; // Ne pas modifier
        }
    }

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 *
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbBottom	Nombre de valeurs du « bottom » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findBottomBigFile  (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbBottom
	)throws Exception{
return findBottomBigFile (i_colGroup,i_colName,i_conditionList,i_statKey,null,i_nbBottom);
}

/*	### NOUVELLE METHODE ###
	Méthode : findTopPercentBigFile  - Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

    /**
     * Récupération des numéros de lignes des enregistrements, correspondant au
     * percentile sur une statistique, réutilisant la liste des statistiques à
     * implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre
     * : TOP.{ } Code généré automatiquement par l'outil Wild Méthode
     * susceptible d'être surchargée Cette méthode répond à l'ensemble des
     * sollicitations de la "WildMethod" indiquée dans la documentation WILD
     *
     * @param i_colGroup	Nom de la colonne de groupement{}
     * @param i_colName	Nom de la colonne de calcul{}
     * @param i_conditionList	Liste des conditions{}
     * @param i_statKey	Nom de la statistique{}
     * @param i_statParam	Paramètre associé à la statistique{}
     * @param i_nbTop	Percentile du « top »{}
     * @return	{}
     */
    public Integer[] findTopPercentBigFile(
            String i_colGroup,
            String i_colName,
            Map<Integer, Map<String, Object>> i_conditionList,
            String i_statKey,
            Integer i_statParam,
            Integer i_nbTop
    ) throws Exception {
//	// Amorce de la méthode
        WILD_beginMethod(); // Ne pas modifier
        //	Variable générique de retour
        Integer[] WILD_toReturn = null; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
        try {
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            // First, we must find distinct values for grouping column.
            final Set<String> groupValues = DVP_findDistinctValues(i_colGroup, i_conditionList);

//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            final Map<String, Object> statsByGroup = DVP_statByGroup(i_colName, i_statKey, i_statParam, i_colGroup, groupValues, i_conditionList);
            groupValues.clear(); // We'll use it to reference our top values.
            final ArrayList<Map.Entry<String, Comparable>> tmpGroups = (ArrayList) new ArrayList<>(statsByGroup.entrySet());
            Collections.sort(tmpGroups, new Comparator<Map.Entry<String, Comparable>>() {
                @Override
                public int compare(Map.Entry<String, Comparable> o1, Map.Entry<String, Comparable> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });

            final int limitIdx = (int) Math.round(tmpGroups.size() * (100 - i_nbTop) / 100d) - 1;
            if (limitIdx >= 0 && limitIdx < tmpGroups.size()) {
                for (int i = tmpGroups.size() - 1; i > limitIdx; i--) {
                    groupValues.add(tmpGroups.get(i).getKey());
                }
            }

//	//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            final List<Integer> indices = new ArrayList<>();
            if (!groupValues.isEmpty()) {
                // Find all records related to above defined groups.
                try (final DVP_LazyIterator sourceIt = new DVP_LazyIterator();
                        final DVP_ConditionIterator it = new DVP_ConditionIterator(sourceIt, i_conditionList)) {
                    String value;
                    while (it.hasNext()) {
                        value = it.next().get(i_colGroup);
                        if (groupValues.contains(value))
                            indices.add((int) sourceIt.getIndex());
                    }
                }
            }

//	//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            WILD_toReturn = indices.toArray(new Integer[indices.size()]);

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

        } catch (Exception e) {
            this.WILD_logException(e); // Ne pas modifier
        } finally {
//	// Fin de la méthode
            WILD_endMethod(); // Ne pas modifier
            return WILD_toReturn; // Ne pas modifier
        }
    }

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES

/**
 * Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 *
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbTop	Percentile du « top » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findTopPercentBigFile  (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbTop
	)throws Exception{
return findTopPercentBigFile (i_colGroup,i_colName,i_conditionList,i_statKey,null,i_nbTop);
}

/*	### NOUVELLE METHODE ###
	Méthode : findBottomPercentBigFile  - Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM.{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

    /**
     * Récupération des numéros de lignes des enregistrements, correspondant au
     * percentile sur une statistique, réutilisant la liste des statistiques à
     * implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre
     * : BOTTOM.{ } Code généré automatiquement par l'outil Wild Méthode
     * susceptible d'être surchargée Cette méthode répond à l'ensemble des
     * sollicitations de la "WildMethod" indiquée dans la documentation WILD
     *
     * @param i_colGroup	Nom de la colonne de groupement{}
     * @param i_colName	Nom de la colonne de calcul{}
     * @param i_conditionList	Liste des conditions{}
     * @param i_statKey	Nom de la statistique{}
     * @param i_statParam	Paramètre associé à la statistique{}
     * @param i_nbBottom	Percentile du « bottom »{}
     * @return	{}
     */
    public Integer[] findBottomPercentBigFile(
            String i_colGroup,
            String i_colName,
            Map<Integer, Map<String, Object>> i_conditionList,
            String i_statKey,
            Integer i_statParam,
            Integer i_nbBottom
    ) throws Exception {
//	// Amorce de la méthode
        WILD_beginMethod(); // Ne pas modifier
        //	Variable générique de retour
        Integer[] WILD_toReturn = null; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
        try {
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            // First, we must find distinct values for grouping column.
            final Set<String> groupValues = DVP_findDistinctValues(i_colGroup, i_conditionList);

//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            final Map<String, Object> statsByGroup = DVP_statByGroup(i_colName, i_statKey, i_statParam, i_colGroup, groupValues, i_conditionList);
            groupValues.clear(); // We'll use it to reference our top values.
            final ArrayList<Map.Entry<String, Comparable>> tmpGroups = (ArrayList) new ArrayList<>(statsByGroup.entrySet());
            Collections.sort(tmpGroups, new Comparator<Map.Entry<String, Comparable>>() {
                @Override
                public int compare(Map.Entry<String, Comparable> o1, Map.Entry<String, Comparable> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });

            final int limitIdx = (int) Math.round(i_nbBottom / 100d * tmpGroups.size()) - 1;
            if (limitIdx >= 0 && limitIdx < tmpGroups.size()) {
                for (int i = 0; i <= limitIdx; i++) {
                    groupValues.add(tmpGroups.get(i).getKey());
                }
            }

//	//	//	Etape	"4" : poids relatif de 100, Condition sur statistique (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            final List<Integer> indices = new ArrayList<>();
            if (!groupValues.isEmpty()) {
                // Find all records related to above defined groups.
                try (final DVP_LazyIterator sourceIt = new DVP_LazyIterator();
                        final DVP_ConditionIterator it = new DVP_ConditionIterator(sourceIt, i_conditionList)) {
                    String value;
                    while (it.hasNext()) {
                        value = it.next().get(i_colGroup);
                        if (groupValues.contains(value))
                            indices.add((int) sourceIt.getIndex());
                    }
                }
            }

//	//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            WILD_toReturn = indices.toArray(new Integer[indices.size()]);

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

        } catch (Exception e) {
            this.WILD_logException(e); // Ne pas modifier
        } finally {
//	// Fin de la méthode
            WILD_endMethod(); // Ne pas modifier
            return WILD_toReturn; // Ne pas modifier
        }
    }

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES

/**
 * Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM.{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 *
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbBottom	Percentile du « bottom » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findBottomPercentBigFile  (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbBottom
	)throws Exception{
return findBottomPercentBigFile (i_colGroup,i_colName,i_conditionList,i_statKey,null,i_nbBottom);
}

/*	### NOUVELLE METHODE ###
	Méthode : findStatCompareBigFile  - Récupération des numéros de lignes des enregistrements, correspondant à une condition mathématique sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « comparables » (C). Les opérateurs utilisée sont supériorité / infériorité (option inclusion / exclusion) et égalité.{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

    /**
     * Récupération des numéros de lignes des enregistrements, correspondant à
     * une condition mathématique sur une statistique, réutilisant la liste des
     * statistiques à implémenter définies en Tableau 2, citées comme «
     * comparables » (C). Les opérateurs utilisée sont supériorité / infériorité
     * (option inclusion / exclusion) et égalité.{ } Code généré automatiquement
     * par l'outil Wild Méthode susceptible d'être surchargée Cette méthode
     * répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans
     * la documentation WILD
     *
     * @param i_colGroup	Nom de la colonne de groupement{}
     * @param i_colName	Nom de la colonne de calcul{}
     * @param i_conditionList	Conditions de recherche sur la colonne avant
     * calcul de la statistique{}
     * @param i_statKey	Nom de la statistique{}
     * @param i_statParam	Paramètre de la statistique{}
     * @param i_minValue	Valeur inférieure{}
     * @param i_minExclude	Est exclu par le bas ?{}
     * @param i_maxValue	Valeur supérieure{}
     * @param i_maxExclude	Est exclu par le haut ?{}
     * @return	{}
     */
    public Integer[] findStatCompareBigFile(
            String i_colGroup,
            String i_colName,
            Map<Integer, Map<String, Object>> i_conditionList,
            String i_statKey,
            Integer i_statParam,
            Object i_minValue,
            Boolean i_minExclude,
            Object i_maxValue,
            Boolean i_maxExclude
    ) throws Exception {
//	// Amorce de la méthode
        WILD_beginMethod(); // Ne pas modifier
        //	Variable générique de retour
        Integer[] WILD_toReturn = null; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
        try {
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            // First, we must find distinct values for grouping column.
            final Set<String> groupValues = DVP_findDistinctValues(i_colGroup, i_conditionList);

//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            final Map<String, Object> statsByGroup = DVP_statByGroup(i_colName, i_statKey, i_statParam, i_colGroup, groupValues, i_conditionList);
            groupValues.clear(); // We'll use it to reference our top values.
            final ArrayList<Map.Entry<String, Comparable>> tmpGroups = (ArrayList) new ArrayList<>(statsByGroup.entrySet());
            Collections.sort(tmpGroups, new Comparator<Map.Entry<String, Comparable>>() {
                @Override
                public int compare(Map.Entry<String, Comparable> o1, Map.Entry<String, Comparable> o2) {
                    return o1.getValue().compareTo(o2.getValue());
                }
            });

            for (final Map.Entry<String, Comparable> entry : tmpGroups) {
                if (IoCommons.check_strictCompare(entry.getValue(), i_minValue, i_minExclude == null? false : i_minExclude, i_maxValue, i_maxExclude == null? false : i_maxExclude))
                    groupValues.add(entry.getKey());
            }

//	//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***final List<Integer> indices = new ArrayList<>();
            final List<Integer> indices = new ArrayList<>();
            if (!groupValues.isEmpty()) {
                // Find all records related to above defined groups.
                try (final DVP_LazyIterator sourceIt = new DVP_LazyIterator();
                        final DVP_ConditionIterator it = new DVP_ConditionIterator(sourceIt, i_conditionList)) {
                    String value;
                    while (it.hasNext()) {
                        value = it.next().get(i_colGroup);
                        if (groupValues.contains(value))
                            indices.add((int) sourceIt.getIndex());
                    }
                }
            }

//	//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            WILD_toReturn = indices.toArray(new Integer[indices.size()]);

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

        } catch (Exception e) {
            this.WILD_logException(e); // Ne pas modifier
        } finally {
//	// Fin de la méthode
            WILD_endMethod(); // Ne pas modifier
            return WILD_toReturn; // Ne pas modifier
        }
    }

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition mathématique sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « comparables » (C). Les opérateurs utilisée sont supériorité / infériorité (option inclusion / exclusion) et égalité.{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 *
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Conditions de recherche sur la colonne avant calcul de la statistique {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_minValue	Valeur inférieure {}
 * @param i_maxValue	Valeur supérieure {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findStatCompareBigFile  (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Object i_minValue,
	Object i_maxValue
	)throws Exception{
return findStatCompareBigFile (i_colGroup,i_colName,i_conditionList,i_statKey,null,i_minValue,false,i_maxValue,false);
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
    public Map<Integer, Map<String, Object>> findRecordsBigFile(
            Map<Integer, Map<String, Object>> i_conditionList
    ) throws Exception {
//	// Amorce de la méthode
        WILD_beginMethod(); // Ne pas modifier
        //	Variable générique de retour
        final Map<Integer, Map<String, Object>> WILD_toReturn = new HashMap<>(); // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
        try {
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
            try (final DVP_LazyIterator sourceIt = new DVP_LazyIterator();
                    final DVP_ConditionIterator it = new DVP_ConditionIterator(sourceIt, i_conditionList)) {
                while (it.hasNext()) {
                    WILD_toReturn.put((int) sourceIt.getIndex(), (Map) it.next());
                }
            }

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

        } catch (Exception e) {
            this.WILD_logException(e); // Ne pas modifier
        } finally {
//	// Fin de la méthode
            WILD_endMethod(); // Ne pas modifier
            return WILD_toReturn; // Ne pas modifier
        }
    }

/*	### NOUVELLE METHODE ###
	Méthode : saveRecordsBigFile - Récupération des enregistrements sous forme de fichier CSV, pour l’ensemble des conditions opérables sur le fichier.{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

    /**
     * Récupération des enregistrements sous forme de fichier CSV, pour
     * l’ensemble des conditions opérables sur le fichier.{ } Code généré
     * automatiquement par l'outil Wild Méthode susceptible d'être surchargée
     * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod"
     * indiquée dans la documentation WILD
     *
     * @param i_conditionList	Conditions de recherche{}
     * @return	{}
     */
    public String saveRecordsBigFile(
            Map<Integer, Map<String, Object>> i_conditionList
    ) throws Exception {
//	// Amorce de la méthode
        WILD_beginMethod(); // Ne pas modifier
        //	Variable générique de retour
        String WILD_toReturn = null; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
        try {
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            final Path output;
            if (fileFile == null) {
                output = Files.createTempFile("export", ".csv");
            } else {
                final String newFile = fileFile.getParentFile().toPath().resolve("export.csv").toAbsolutePath().toString();
                output = Paths.get(IoFileSystem.DVP_findAvailableName(newFile, null));
            }

            Charset charset;
            try {
                charset = Charset.forName(mimeEncoding);
            } catch (Exception e) {
                WILD_Logger.logError(e, "Impossible de déterminer le jeu de caractère pour sauvegarde.");
                charset = Charset.defaultCharset();
            }

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            try (final BufferedWriter writer = Files.newBufferedWriter(output, charset);
                    final DVP_ConditionIterator it = new DVP_ConditionIterator(new DVP_LazyIterator(), i_conditionList)) {
                DVP_writeIterator(writer, getDataSet("1").DVP_getHeader(), it);
            }

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
            WILD_toReturn = output.toString();

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
            this.WILD_setStep(); // Ne pas modifier
            // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

        } catch (Exception e) {
            this.WILD_logException(e); // Ne pas modifier
        } finally {
//	// Fin de la méthode
            WILD_endMethod(); // Ne pas modifier
            return WILD_toReturn; // Ne pas modifier
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
		WILD_toReturn = this.getDataSet("1").findEqual(i_colName, i_colValue, i_nullSens, i_caseSens, i_specSens);
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
		WILD_toReturn = this.getDataSet("1").findContain(i_colName, i_colValue, i_nullSens, i_caseSens, i_specSens);

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
		WILD_toReturn = this.getDataSet("1").findRegex(i_colName, i_colRegexp, i_nullSens, i_caseSens, i_specSens);
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
		WILD_toReturn = this.getDataSet("1").findWithin(i_colName,i_minValue,i_minExclude,i_maxValue,i_maxExclude);
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
		WILD_toReturn = this.getDataSet("1").findCompare(i_colName,i_minValue,i_minExclude,i_maxValue,i_maxExclude);
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
		WILD_toReturn = this.getDataSet("1").findLines (i_conditionList);

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
	Méthode : calculate - Calcul de statistiques sur les valeurs des enregistrements, combinant l’ensemble des conditions précédemment listées. La liste des statistiques retenues est présentée en Tableau 1. Le Tableau 2 plus loin renvoie à un _nextIndex des conditions pour implémentation.{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Calcul de statistiques sur les valeurs des enregistrements, combinant l’ensemble des conditions précédemment listées. La liste des statistiques retenues est présentée en Tableau 1. Le Tableau 2 plus loin renvoie à un _nextIndex des conditions pour implémentation.{
 }
 Code généré automatiquement par l'outil Wild
 Méthode susceptible d'être surchargée
 Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
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
		WILD_toReturn = this.getDataSet("1").calculate(i_colName, i_conditionList, i_statKey, i_statParam);
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
 * Calcul de statistiques sur les valeurs des enregistrements, combinant l’ensemble des conditions précédemment listées. La liste des statistiques retenues est présentée en Tableau 1. Le Tableau 2 plus loin renvoie à un _nextIndex des conditions pour implémentation.{
 }
 Code généré automatiquement par l'outil Wild
 Méthode d'appel public non modifiable
 *
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @return	Liste des lignes repérées (Integer[]) {}
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
		WILD_toReturn = this.getDataSet("1").findTop(i_colGroup, i_colName, i_conditionList, i_statKey, i_statParam, i_nbTop);
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
 * @param i_colGroup	Nom de la colonne de groupement{}
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
	Méthode : findBottom  - Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM{
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
public Integer[] findBottom  (
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
		WILD_toReturn = this.getDataSet("1").findBottom(i_colGroup, i_colName, i_conditionList, i_statKey, i_statParam, i_nbBottom);
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
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbBottom	Nombre de valeurs du « bottom » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findBottom  (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbBottom
	)throws Exception{
return findBottom (i_colGroup, i_colName,i_conditionList,i_statKey,null,i_nbBottom);
}

/*	### NOUVELLE METHODE ###
	Méthode : findTopPercent  - Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{
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
public Integer[] findTopPercent  (
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
		WILD_toReturn = this.getDataSet("1").findTopPercent(i_colGroup, i_colName, i_conditionList, i_statKey, i_statParam, i_nbTop);
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
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbTop	Percentile du « top » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findTopPercent  (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbTop
	)throws Exception{
return findTopPercent (i_colGroup,i_colName,i_conditionList,i_statKey,null,i_nbTop);
}

/*	### NOUVELLE METHODE ###
	Méthode : findBottomPercent  - Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM.{
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
public Integer[] findBottomPercent  (
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
		WILD_toReturn = this.getDataSet("1").findBottomPercent(i_colGroup, i_colName, i_conditionList, i_statKey, i_statParam, i_nbBottom);
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
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbBottom	Percentile du « bottom » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findBottomPercent  (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbBottom
	)throws Exception{
return findBottomPercent (i_colGroup, i_colName,i_conditionList,i_statKey,null,i_nbBottom);
}

/*	### NOUVELLE METHODE ###
	Méthode : findStatCompare  - Récupération des numéros de lignes des enregistrements, correspondant à une condition mathématique sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « comparables » (C). Les opérateurs utilisée sont supériorité / infériorité (option inclusion / exclusion) et égalité.{
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
public Integer[] findStatCompare  (
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
		WILD_toReturn = this.getDataSet("1").findStatCompare(i_colGroup, i_colName, i_conditionList, i_statKey, i_statParam, i_minValue, i_minExclude, i_maxValue, i_maxExclude);
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
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Conditions de recherche sur la colonne avant calcul de la statistique {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_minValue	Valeur inférieure {}
 * @param i_maxValue	Valeur supérieure {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findStatCompare  (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Object i_minValue,
	Object i_maxValue
	)throws Exception{
return findStatCompare (i_colGroup, i_colName,i_conditionList,i_statKey,null,i_minValue,false,i_maxValue,false);
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
		WILD_toReturn = this.getDataSet("1").findRecords(i_conditionList);
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


    public boolean matchConditions(Map<String, String> record, Map<Integer, Map<String, Object>> i_conditionList) throws Exception {
        if (i_conditionList == null || i_conditionList.isEmpty()) {
            return true;
        }

        /* Re-order conditions according to their _nextIndex. For performance purpose,
         * we won't check missing indices or doublons, so there's a slight chance
         * for the result to be unpredictible if user didn't specify them correctly.
         */
        final int[] order = new int[i_conditionList.size()];
        int j = 0;
        for (final Integer i : i_conditionList.keySet()) {
            order[j++] = i;
        }
        Arrays.sort(order);

        boolean match = evaluateCondition(record, i_conditionList.get(order[0]));
        for (j = 1; j < order.length; j++) {
            // Pour chaque condition, préparation des paramètres génériques contenus dans la map
            Map<String, Object> condition = i_conditionList.get(order[j]);

            final Object tmpOperator = condition.get("CombinOperator");
            final String CombinOperator = (tmpOperator == null) ? null : tmpOperator.toString().toLowerCase();
            final boolean isAnd = "and".equals(CombinOperator);
            // Combinaison des résultats en fonction de l'opérateur
            if (isAnd) {
                match = (match && evaluateCondition(record, condition));
            } else {
                match = (match || evaluateCondition(record, condition));
            }
        }
        return match;
    }

    /**
     * Evaluate a single condition against given record.
     * @param record The record to check.
     * @param condition Describes the condition to apply.
     * @return True if the condition accepts given data, false otherwise.
     */
    protected boolean evaluateCondition(final Map<String, String> record, final Map<String, Object> condition) {
        String WildCode = ((String) condition.get("WildCode")).toLowerCase();
        final String ColumnName = ((String) condition.get("ColumnName"));

        final String recordValue = record.get(ColumnName);

        boolean currentMatch;
        final boolean negation;
        if (WildCode.startsWith("!")) {
            negation = true;
            WildCode = WildCode.substring(1);
        } else
            negation = false;
        switch (WildCode) {
            case "equal":
                Object columnValue = condition.get("ColumnValue");
                Boolean NullSens = ((Boolean) condition.get("NullSens"));
                Boolean CaseSens = ((Boolean) condition.get("CaseSens"));
                Boolean SpecSens = ((Boolean) condition.get("SpecSens"));

                currentMatch = DVP_equals(recordValue, columnValue,
                        NullSens == null? true : !NullSens,
                        CaseSens == null? true : !CaseSens,
                        SpecSens == null? true : !SpecSens
                );

                break;
            case "contain":
                columnValue = condition.get("ColumnValue");
                NullSens = ((Boolean) condition.get("NullSens"));
                CaseSens = ((Boolean) condition.get("CaseSens"));
                SpecSens = ((Boolean) condition.get("SpecSens"));

                currentMatch = DVP_contains(recordValue, columnValue,
                        NullSens == null? true : !NullSens,
                        CaseSens == null? true : !CaseSens,
                        SpecSens == null? true : !SpecSens
                );

                break;
            case "regex":
                columnValue = condition.get("ColumnValue");
                NullSens = ((Boolean) condition.get("NullSens"));
                CaseSens = ((Boolean) condition.get("CaseSens"));
                SpecSens = ((Boolean) condition.get("SpecSens"));

                if (columnValue == null)
                    currentMatch = false;
                else
                    currentMatch = DVP_regex(recordValue, columnValue.toString(), NullSens, CaseSens, SpecSens);

                break;
            case "within":
                Object columnMinValue = condition.get("ColumnMinValue");
                Object columnMaxValue = condition.get("ColumnMaxValue");
                Boolean columnMinValueInclude = ((Boolean) condition.get("ColumnMinValueInclude"));
                Boolean columnMaxValueInclude = ((Boolean) condition.get("ColumnMaxValueInclude"));

                currentMatch = DVP_within(
                        recordValue,
                        columnMinValue,
                        columnMinValueInclude == null? true : columnMinValueInclude,
                        columnMaxValue,
                        columnMaxValueInclude == null? true : columnMaxValueInclude
                );

                break;
            case "compare":
                columnMinValue = condition.get("ColumnMinValue");
                columnMaxValue = condition.get("ColumnMaxValue");
                columnMinValueInclude = ((Boolean) condition.get("ColumnMinValueInclude"));
                columnMaxValueInclude = ((Boolean) condition.get("ColumnMaxValueInclude"));

                currentMatch = DVP_compare(
                        recordValue,
                        columnMinValue,
                        columnMinValueInclude == null? true : columnMinValueInclude,
                        columnMaxValue,
                        columnMaxValueInclude == null? true : columnMaxValueInclude
                );

                break;
            default:
                throw new IllegalArgumentException("unrecognized wildCode:" + WildCode);
        }

        // Gestion des cas contraires à la condition, c'est-à-dire dont le WildCode est préfixé de "!"
        if (negation) {
            currentMatch = !currentMatch;
        }

        return currentMatch;
    }

    /**
     * Check if given value is equal to given reference.
     * @param value The value to test.
     * @param refValue The reference value.
     * @param ignoreCase If input are strings, and it is true, case will be ignored in comparison.
     * @param ignoreSpaces If input are strings, and it is true, all blank characters will be ignored by comparison.
     * @param ignoreSpecialCharacters If input are strings, and it is true, all accents and special characters will be ignored by comparison.
     * @return True if we consider value is equal to reference value. false otherwise.
     */
    private boolean DVP_equals(String value, Object refValue, final boolean ignoreCase, final boolean ignoreSpaces, final boolean ignoreSpecialCharacters) {
        if (value == null && refValue == null)
            return true;
        else if (value == null || refValue == null)
            return false;

        if (refValue instanceof String) {
            String strRefValue = (String) refValue;
            value = IoCommons.transform(value, ignoreSpaces, ignoreSpecialCharacters, ignoreCase);
            strRefValue = IoCommons.transform(strRefValue, ignoreSpaces, ignoreSpecialCharacters, ignoreCase);
            return strRefValue.equals(value);
        }

        return refValue.equals(ObjectConverters.convert(value, refValue.getClass()));
    }

    /**
     * Check if given value contains given reference.
     * @param value The value to test.
     * @param refValue The reference value.
     * @param ignoreCase If input are strings, and it is true, case will be ignored in comparison.
     * @param ignoreSpaces If input are strings, and it is true, all blank characters will be ignored by comparison.
     * @param ignoreSpecialCharacters If input are strings, and it is true, all accents and special characters will be ignored by comparison.
     * @return True if we consider value contains reference value. false otherwise.
     */
    private boolean DVP_contains(String value, Object refValue, final boolean ignoreCase, final boolean ignoreSpaces, final boolean ignoreSpecialCharacters) {
        if (value == null && refValue == null)
            return true;
        else if (value == null || refValue == null)
            return false;

        final String strRefValue = IoCommons.transform(refValue.toString(), ignoreSpaces, ignoreSpecialCharacters, ignoreCase);
        value = IoCommons.transform(value, ignoreSpaces, ignoreSpecialCharacters, ignoreCase);

        return value.contains(strRefValue);
    }

    /**
     * Check if given value contains given pattern.
     * @param value The value to test.
     * @param refValue The reference value.
     * @param ignoreCase If input are strings, and it is true, case will be ignored in comparison.
     * @param ignoreSpaces If input are strings, and it is true, all blank characters will be ignored by comparison.
     * @param ignoreSpecialCharacters If input are strings, and it is true, all accents and special characters will be ignored by comparison.
     * @return True if we consider value contains regex pattern. false otherwise.
     */
    private boolean DVP_regex(String value, String regex, final boolean ignoreCase, final boolean ignoreSpaces, final boolean ignoreSpecialCharacters) {
        if (value == null && regex == null)
            return true;
        else if (value == null || regex == null)
            return false;

        value = IoCommons.transform(value, ignoreSpaces, ignoreSpecialCharacters, ignoreCase);

        return Pattern.compile(regex).matcher(value).find();
    }

    /**
     * Check that given string is defined in the interval represented by input boundaries.
     * @param value Value to check.
     * @param minValue Lower boundary
     * @param includeMin True If lower boundary is inclusive. False if it is exclusive.
     * @param maxValue  Upper boundary
     * @param includeMax True If upper boundary is inclusive. False if it is exclusive.
     * @return True if given string is contained in defined interval. False otherwise.
     */
    private boolean DVP_within(String value, Object minValue, boolean includeMin, Object maxValue, boolean includeMax) {
        if (value == null)
            return minValue == null || maxValue == null;

        value = IoCommons.transform(value, true, true, true);
        boolean within = minValue != null || maxValue != null;
        // If a lower bound is defined, we ensure our value is greather than it.
        if (minValue != null) {
            final int comparison = value.compareTo(IoCommons.transform(minValue.toString(), true, true, true));
            within = includeMin? comparison >= 0 : comparison > 0;
        }

        // Check upper bound only if present and if lower bound has been validated.
        if (within && maxValue != null) {
            final int comparison = value.compareTo(IoCommons.transform(maxValue.toString(), true, true, true));
            within &= includeMax? comparison <= 0 : comparison < 0;
        }

        return within;
    }

    private boolean DVP_compare(final String value, Object minValue, final boolean includeMin, Object maxValue, final boolean includeMax) {
        final String trimmedValue;
        if (value == null || (trimmedValue = value.trim()).isEmpty())
            return minValue == null || maxValue == null;
        else if (minValue instanceof String || maxValue instanceof String)
            return DVP_within(value, minValue, includeMin, maxValue, includeMax);

        Class targetClass;
        if (minValue != null)
            targetClass = minValue.getClass();
        else if (maxValue != null)
            targetClass = maxValue.getClass();
        else
            return false;

        final boolean isNumber = Number.class.isAssignableFrom(targetClass);
        if (isNumber)
            targetClass = Double.class;
        if (!Comparable.class.isAssignableFrom(targetClass))
            return false;

        final Comparable convertedValue = (Comparable) ObjectConverters.convert(trimmedValue, targetClass);
        if (isNumber)
            return IoCommons.check_strictCompare(((Number)convertedValue).doubleValue(), minValue == null? null : ((Number)minValue).doubleValue(), true, maxValue == null? null : ((Number)maxValue).doubleValue(), true);

        return IoCommons.check_strictCompare(convertedValue, minValue, true, maxValue, true);
    }

    /**
     * Find all distinct values for a specific column.
     * @param columnName Name of the column we want distinct values for.
     * @param conditions Filter read lines according this the set of conditions defined.
     * @return All distinct values found in the current file. Never null, but can be empty.
     * @throws Exception
     */
    private Set<String> DVP_findDistinctValues(final String columnName, final Map<Integer, Map<String, Object>> conditions) throws Exception {
        try (final DVP_ConditionIterator it = new DVP_ConditionIterator(new DVP_LazyIterator(), conditions)) {
            final HashSet<String> groupValues = new HashSet<>();
            String value;
            while (it.hasNext()) {
                value = it.next().get(columnName);
                if (value != null && !value.isEmpty())
                    groupValues.add(value);
            }
            return groupValues;
        }
    }

    /**
     *
     * @param statColumn Column to compute statistics on.
     * @param statKey Name of the statistical operation to perform.
     * @param statParam A parameter for the statistics.
     * @param groupColumn Name of the column to group on.
     * @param groupValues Values considered as distinct group identifier.
     * @param filterConditions Filter to apply on source data.
     * @return A map, whose keys are group identifier, and value is the statistics result.
     * @throws Exception
     */
    private Map<String, Object> DVP_statByGroup(
            final String statColumn,
            final String statKey,
            final Integer statParam,
            final String groupColumn,
            final Set<String> groupValues, // Note : in optimized version, this parameter is not needed.
            final Map<Integer, Map<String, Object>> filterConditions
    ) throws Exception {
        switch (statKey) {
            case "maximum":
            case "minimum":
            case "avg":
            case "var":
            case "std_dev":
            case "significant_nb":
            case "unsignificant_nb":
                return (Map) DVP_statByGroupOptimized(statColumn, statKey, groupColumn, filterConditions);

            default:
                final HashMap<String, Object> statsByGroup = new HashMap<>(groupValues.size());
                final HashMap<Integer, Map<String, Object>> conditionCopy = new HashMap<>(filterConditions);
                int max = Collections.max(conditionCopy.keySet()) + 1;
                for (final String groupBy : groupValues) {
                    final HashMap<String, Object> groupByCondition = new HashMap<>();
                    groupByCondition.put("WildCode", "equal");
                    groupByCondition.put("ColumnName", groupColumn);
                    groupByCondition.put("ColumnValue", groupBy);
                    groupByCondition.put("CombinOperator", "AND");
                    conditionCopy.put(max, groupByCondition);
                    Map<String, Object> stat = calculateBigFile(statColumn, conditionCopy, statKey, statParam);
                    statsByGroup.put(groupBy, stat.get("result"));
                }
                return statsByGroup;
        }
    }

    /**
     * This method compute statistics grouped by distinct values on a column.
     * The difference with {@link #DVP_statByGroup(java.lang.String, java.lang.String, java.lang.Integer, java.lang.String, java.util.Set, java.util.Map) }
     * is that here, we compute statistics with a single pass on entire dataset.
     *
     * For the moment, only the following statistics are supported :
     * maximum, minimum, avg, var, std_dev, significant_nb, unsignificant_nb,
     * @param statColumn Column to use for statistics computing.
     * @param statKey Name of the statistics to compute.
     * @param groupColumn
     * @param filterConditions
     * @return
     * @throws Exception
     */
    private Map<String, Double> DVP_statByGroupOptimized(
            final String statColumn,
            final String statKey,
            final String groupColumn,
            final Map<Integer, Map<String, Object>> filterConditions
    ) throws Exception {
        final HashMap<String, Statistics> statsByGroup = new HashMap<>();
        try (final DVP_ConditionIterator it = new DVP_ConditionIterator(new DVP_LazyIterator(), filterConditions)) {
            Map<String, String> record;
            Statistics stats;
            String groupValue, value;
            double dValue;
            while (it.hasNext()) {
                record = it.next();
                groupValue = record.get(groupColumn);
                stats = statsByGroup.get(groupValue);
                if (stats == null) {
                    stats = new Statistics(groupValue);
                    statsByGroup.put(groupValue, stats);
                }

                value = record.get(statColumn);
                // Do not skip it. It is needed for unsignificant number computing.
                if (value == null || (value = value.trim()).isEmpty())
                    dValue = Double.NaN;
                // On first encountered non-numeric value, a NumberFormatException
                // is thrown, avoiding parsing of a column containing something else than a string.
                else dValue = Double.parseDouble(value);

                stats.accept(dValue);
            }
        }

        if (statsByGroup.isEmpty())
            return Collections.EMPTY_MAP;

        // TODO : refactor with java 8 lambda
        switch (statKey.toLowerCase()) {
            case "maximum":
                for (final Map.Entry s : statsByGroup.entrySet()) {
                    s.setValue(((Statistics) s.getValue()).maximum());
                }
                break;
            case "minimum":
                for (final Map.Entry s : statsByGroup.entrySet()) {
                    s.setValue(((Statistics) s.getValue()).minimum());
                }
                break;

            case "avg":
                for (final Map.Entry s : statsByGroup.entrySet()) {
                    s.setValue(((Statistics) s.getValue()).mean());
                }
                break;

            case "var":
                for (final Map.Entry s : statsByGroup.entrySet()) {
                    s.setValue(Math.pow(((Statistics) s.getValue()).standardDeviation(true), 2));
                }
                break;

            case "std_dev":
                for (final Map.Entry s : statsByGroup.entrySet()) {
                    s.setValue(((Statistics) s.getValue()).standardDeviation(true));
                }
                break;

            case "significant_nb":
                for (final Map.Entry s : statsByGroup.entrySet()) {
                    s.setValue(((Statistics) s.getValue()).count());
                }
                break;

            case "unsignificant_nb":
                for (final Map.Entry s : statsByGroup.entrySet()) {
                    s.setValue(((Statistics) s.getValue()).countNaN());
                }
                break;
        }
//for( String o:statsByGroup.keySet())System.out.println(o+":"+statsByGroup.get(o));
        return (Map) statsByGroup;
    }

    /**
     *
     * @return Number of lines remaining in the file AFTER header line.
     * @throws Exception
     */
    private long DVP_countLines() throws Exception {
        final int headerIdx = DVP_getHeaderLine();
        long count = 0;
        String line;
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(DVP_newStream()))) {
            do {
                line = reader.readLine();
                count++;
            } while (line != null);
        }

        return count - headerIdx - 1;
    }

    /**
     * Check that current file is readable and contains content (at least one line).
     * Notes :
     * - We'll never fail. If an exception is raised, we consider the file
     * as unreadable.
     * - We only check that file exists and has at least one byte of data. So if
     * it has only one empty line (containing only blank characters) we consider
     * it has content.
     *
     * @return True if current file has at least one readable line. False otherwise.
     */
    public boolean DVP_hasContent() {
        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(DVP_newStream()))) {
            return reader.read() < 0;
        } catch (IOException e) {
            WILD_Logger.logError(e, "An error occured while checking file first character.");
            return false;
        }
    }

    /**
     * Allow decoding of a flat file lazily, loading data in memory line by
     * line.
     *
     * Note : If user does not specify an upper limit, we use {@link Integer#MAX_VALUE},
     * so it fits better with public API which use integers as indices.
     *
     * @author Alexis Manin (Geomatys)
     * @author Quentin Boileau (Geomatys) - For original code
     */
    private class DVP_LazyIterator implements Iterator<Map<String, String>>, Closeable {

        final long startIndex, endIndex;

        BufferedReader reader;
        String[] header = null;

        Map<String, String> _next;

        private long _nextIndex = 0;

        public DVP_LazyIterator() throws Exception {
            this(0, Long.MAX_VALUE);
        }

        public DVP_LazyIterator(final long startIndex, final long endIndex) throws Exception {
            ArgumentChecks.ensurePositive("Start index", startIndex);
            ArgumentChecks.ensurePositive("End index", endIndex);
            this.startIndex = Math.min(startIndex, endIndex);
            // We limit ourselves in term of indices, to ensure we're always able to cast them to int.
            this.endIndex = Math.min(Integer.MAX_VALUE, Math.max(startIndex, endIndex));
        }

        @Override
        public synchronized boolean hasNext() {
            Exception error = null;
            boolean hasNext = false;
            try {
                hasNext = hasNextImpl();
            } catch (Exception e) {
                error = e;
                throw new RuntimeException(error);
            } finally {
                if (!hasNext || error != null) {
                    // If no more element is available, we can close iterator immediately.
                    try {
                        close();
                    } catch (IOException ex) {
                        if (error != null)
                            error.addSuppressed(ex);
                        else
                            throw new RuntimeException(error);
                    }
                }
            }

            return hasNext;
        }

        private boolean hasNextImpl() throws Exception {
            if (_next != null)
                return true;
            else if (_nextIndex < 0) // If _nextIndex is negative, the underlying stream has been closed.
                return false;

            // First, we init file header.
            if (header == null) {
                final int headerIdx = DVP_getHeaderLine();
                resetReader();
                while (_nextIndex < headerIdx) {
                    reader.readLine();
                    _nextIndex++;
                }

                header = DVP_splitLine(reader.readLine());
                _nextIndex++;
            }

            if (header.length < 1)
                return false;

            while (_nextIndex < startIndex) {
                if (reader.readLine() == null)
                    return false; // No more data in source stream.
                _nextIndex++;
            }

            if (_nextIndex > endIndex) {
                if (_nextIndex > Integer.MAX_VALUE)
                    WILD_Logger.logEvent(this.getClass().getCanonicalName(), "Trop de lignes dans le fichier d'entrée : plus de 2 milliards.");
                return false;
            }

            while (_next == null) {
                String line = reader.readLine();
                _nextIndex++;
                if (line == null)
                    return false;

                final String[] parsedRecord;
                if (!(line = line.trim()).isEmpty() && (parsedRecord = DVP_splitLine(line)) != null && parsedRecord.length > 0) {
                    _next = DVP_mapRecordByIndex(header, parsedRecord);
                }
            }

            return _next != null;
        }

        @Override
        public synchronized Map<String, String> next() {
            if (!hasNext() || _next == null)
                throw new IllegalStateException("No more data to read.");
            try {
                return _next;
            } finally {
                _next = null;
            }
        }

        /**
         * Give the line number the iterator stepped on after last call to {@link #hasNext() }.
         * @return Number of the line currently pointed by iterator. If iterator
         * is already closed, or never used, the returned value is negative. Line
         * numbers start at 0.
         */
        public long getIndex() {
            return _nextIndex -1;
        }

        @Override
        public void close() throws IOException {
            _nextIndex = -1;
            if (reader != null)
                reader.close();
        }

        private void resetReader() throws Exception {
            close();
            reader = new BufferedReader(new InputStreamReader(DVP_newStream()));
            _nextIndex = 0;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Read-only !");
        }
    }

    private class DVP_ConditionIterator extends FilteringIterator<Map<String, String>> {

        private final ConditionChain validator;

        public DVP_ConditionIterator(final Iterator<Map<String, String>> source, final Map<Integer, Map<String, Object>> conditions) {
            super(source);
            this.validator = new ConditionChain(conditions);
        }

        @Override
        protected boolean accept(Map<String, String> input) {
            return validator.test(input);
        }
    }

    /**
     * An iterator whose aim is to focus on a single column whose values MUST be
     * numbers. The iterator simply takes the read string, and call {@link Double#valueOf(java.lang.String) }
     * to convert it.
     */
    private class DVP_NumberIterator extends ConvertingIterator<Map<String, String>, Number> {

        private final String columnKey;

        public DVP_NumberIterator(final Iterator<Map<String, String>> source, final String columnKey) {
            super(source);
            this.columnKey = columnKey;
        }

        @Override
        protected Number convert(Map<String, String> next) throws NumberFormatException {
            String input = next.get(columnKey);
            if (input == null || (input = input.trim()).isEmpty()) {
                return Double.NaN;
            } else return Double.valueOf(input);
        }
    }
}


