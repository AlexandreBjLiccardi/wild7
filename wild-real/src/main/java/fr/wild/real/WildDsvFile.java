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

// Dépendances, bibliothèques JAVA par exemple.


/**
 * Code généré automatiquement par l'outil Wild
 * Fichier texte délimité, délimiteurs non précisés
 */
public class WildDsvFile extends WildFlatFile{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildDsvFile(){}

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
protected void WILD_initialize_WildDsvFile(
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
	this.WILD_initialize_WildFlatFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,i_delimiter,i_quoter,i_escapeChar,i_lineDelimiter);
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

public WildDsvFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_mimeEncoding,
	String i_delimiter,
	String i_quoter,
	String i_escapeChar,
	String i_lineDelimiter
) throws Exception{
	if(mimeEncoding==null)mimeEncoding="UTF-8";
	this.WILD_initialize_WildDsvFile(i_WILD_dObject,i_pathToFile,i_mimeEncoding,i_delimiter,i_quoter,i_escapeChar,i_lineDelimiter);
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

public WildDsvFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_delimiter,
	String i_quoter,
	String i_escapeChar,
	String i_lineDelimiter
) throws Exception{
	this.WILD_initialize_WildDsvFile(i_WILD_dObject,i_pathToFile,"UTF-8",i_delimiter,i_quoter,i_escapeChar,i_lineDelimiter);
}


}

