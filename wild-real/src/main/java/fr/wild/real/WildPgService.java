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
 * Client d'une base de données PostGreSQL
 */
public class WildPgService extends WildDbmsService{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildPgService(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation

// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
/*
	protected String clientId;	//Identifiant de l'utilisateur{}
	protected String clientPwd;	//Mot de  passe de l'utilisateur{}
	protected String hostName;	//Nom de l'hôte{}
	protected String hostIp;	//IP de l'hôte{}
*/
	public String sqlListTable = "";


// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***

// CE CONSTRUCTEUR DOIT ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Fonction d'initialisation, commune à tous les constructeurs.
 * "Constructeur unique"
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_clientId	Identifiant de l'utilisateur{}
 * @param i_clientPwd	Mot de passe de l'utilisateur{}
 * @param i_hostName	Nom de l'hôte{}
 * @param i_hostIp	IP de l'hôte{}
 */
protected void WILD_initialize_WildPgService(
	WildObject i_WILD_dObject,
	String i_clientId,
	String i_clientPwd,
	String i_hostName,
	String i_hostIp
) throws Exception {

//	// Amorce de la classe
	// Initialisation de la classe d'objet selon le schéma Wild
	WILD_dObject = i_WILD_dObject ;
	WILD_initialize_WildDbmsService(i_WILD_dObject,clientId,clientPwd,hostName,hostIp);
	// Préparation des variables d'invocation (considérées comme champs globaux)
	this.clientId = i_clientId;
	this.clientPwd = i_clientPwd;
	this.hostName = i_hostName;
	this.hostIp = i_hostIp; 
//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	// Amorce de connexion JDBC
		amoJdbc = "jdbc:postgresql://"+hostIp+"/"+hostName;
	// Détermination des structures de requêtes SQL courantes pour PostGreSQL
		sqlQueries.put("getTableList", "select table_schema as schema, table_name as table from information_schema.tables where table_schema = '#6D#schema#6D#' and table_name = '#6D#table#6D#'");
		sqlQueries.put("getSchemaList", "select schema_name as schema from information_schema.schemata where schema_name = '#6D#schema#6D#'");
		sqlQueries.put("getColumnList", "select column_name as column, data_type as type from information_schema.columns where table_schema = '#6D#schema#6D#' and table_name = '#6D#table#6D#'");
		sqlQueries.put("getFullDatas", "select #6D#fields#6D# from \"#6D#schema#6D#\".\"#6D#table#6D#\"");
		sqlQueries.put("dropTable", "drop table if exists \"#6D#schema#6D#\".\"#6D#table#6D#\"");
		sqlQueries.put("createSchema", "create schema \"#6D#schema#6D#\"");
		sqlQueries.put("createTable", "create table \"#6D#schema#6D#\".\"#6D#table#6D#\"(#6D#fieldswithtype#6D#)");
		sqlQueries.put("insert", "insert into \"#6D#schema#6D#\".\"#6D#table#6D#\"(#6D#fields#6D#) values(#6D#values#6D#)");
		sqlQueries.put("addColumn", "alter table \"#6D#schema#6D#\".\"#6D#table#6D#\" add \"#6D#columnName#6D#\" #6D#columnType#6D#");
		sqlQueries.put("addGeomColumn", "alter table \"#6D#schema#6D#\".\"#6D#table#6D#\" add \"geom\" geometry");
		sqlQueries.put("buildGeom", "update \"#6D#schema#6D#\".\"#6D#table#6D#\" set \"geom\" = ST_SetSRID(ST_GeomFromText(\"#6D#fromColumn#6D#\",#6D#srid#6D#),#6D#srid#6D#)");
		sqlQueries.put("addColumn", "alter table \"#6D#schema#6D#\".\"#6D#table#6D#\" add \"#6D#columnName#6D#\" #6D#columnType#6D#");
		sqlQueries.put("deleteFrom", "delete from \"#6D#schema#6D#\".\"#6D#table#6D#\" where #6D#condition#6D#");
	// Détermination des types propres à PostGreSQL
		sqjJavaTypes.put("geometry", new Object[]{"geometry","geometry", true});
		sqjJavaTypes.put("byte[]", new Object[]{"byte[]","binary"});
		sqjJavaTypes.put("boolean",  new Object[]{"boolean","boolean"});
		sqjJavaTypes.put("string",  new Object[]{"string","text"});
		sqjJavaTypes.put("int",  new Object[]{"int","integer"});
		sqjJavaTypes.put("integer",  new Object[]{"integer","integer"});
		sqjJavaTypes.put("bigdecimal",  new Object[]{"bigdecimal","numeric"});
		sqjJavaTypes.put("float",  new Object[]{"float","real"});
		sqjJavaTypes.put("double",  new Object[]{"double","double precision"});
		sqjJavaTypes.put("timestamp",  new Object[]{"timestamp","timestamp"});
		sqjJavaTypes.put("time",  new Object[]{"time","time"});
		sqjJavaTypes.put("date",  new Object[]{"date","date"});
		sqjJavaTypes.put("short",  new Object[]{"short","smallint"});	
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
 * @param i_clientId	Identifiant de l'utilisateur{}
 * @param i_clientPwd	Mot de passe de l'utilisateur{}
 * @param i_hostName	Nom de l'hôte{}
 * @param i_hostIp	IP de l'hôte{}
 */

public WildPgService(
	WildObject i_WILD_dObject,
	String i_clientId,
	String i_clientPwd,
	String i_hostName,
	String i_hostIp
) throws Exception{
	this.WILD_initialize_WildPgService(i_WILD_dObject,i_clientId,i_clientPwd,i_hostName,i_hostIp);
}


}

