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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
// Dépendances, bibliothèques JAVA par exemple.
import java.util.HashMap;
import java.util.Map;


/**
 * Code généré automatiquement par l'outil Wild
 * Client d'un service fournisseur d'un accès base de données
 */
public abstract class WildDbmsService extends WildService{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildDbmsService(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected String amoJdbc; // Amorce JDBC à compléter

// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	protected String clientId;	//Identifiant de l'utilisateur{}
	protected String clientPwd;	//Mot de passe de l'utilisateur{}
	protected String hostName;	//Nom de l'hôte{}
	protected String hostIp;	//IP de l'hôte{}


// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***

	protected Connection connectionJdbc ; // Connexion au service de données
	protected HashMap<String,String> sqlQueries = new HashMap() ; // Structures d'ordres SQL, en fonction du service
	protected HashMap<String,Object[]> sqjJavaTypes = new HashMap(); // Types de correspondance JAVA vers SQL
	
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
protected void WILD_initialize_WildDbmsService(
	WildObject i_WILD_dObject,
	String i_clientId,
	String i_clientPwd,
	String i_hostName,
	String i_hostIp
) throws Exception {

//	// Amorce de la classe
	// Initialisation de la classe d'objet selon le schéma Wild
	WILD_dObject = i_WILD_dObject ;
	WILD_initialize_WildService(i_WILD_dObject,i_clientId,i_clientPwd,i_hostName,i_hostIp);
	// Préparation des variables d'invocation (considérées comme champs globaux)
	this.clientId = i_clientId;
	this.clientPwd = i_clientPwd;
	this.hostName = i_hostName;
	this.hostIp = i_hostIp;
	sqjJavaTypes.put("geometry", new Object[]{"geometry","geometry"});
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
 * @param i_clientId	Identifiant de l'utilisateur{}
 * @param i_clientPwd	Mot de passe de l'utilisateur{}
 * @param i_hostName	Nom de l'hôte{}
 * @param i_hostIp	IP de l'hôte{}
 */

public WildDbmsService(
	WildObject i_WILD_dObject,
	String i_clientId,
	String i_clientPwd,
	String i_hostName,
	String i_hostIp
) throws Exception{
	if(clientId==null)clientId=null;
	if(clientPwd==null)clientPwd=null;
	this.WILD_initialize_WildDbmsService(i_WILD_dObject,i_clientId,i_clientPwd,i_hostName,i_hostIp);
}

// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Constructeur, appelle nécessairement WILD_initialize()
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_hostName	Nom de l'hôte{}
 * @param i_hostIp	IP de l'hôte{}
 */

public WildDbmsService(
	WildObject i_WILD_dObject,
	String i_hostName,
	String i_hostIp
) throws Exception{
	this.WILD_initialize_WildDbmsService(i_WILD_dObject,null,null,i_hostName,i_hostIp);
}

/*	### NOUVELLE METHODE ###
	Méthode : loadTo - Charge un jeu de données (sous forme de Map) vers une table. Crée la table si elle n'existe pas.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Charge un jeu de données (sous forme de Map) vers une table. Crée la table si elle n'existe pas.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_mapToWrite	La source est une Map{}
 * @param i_outputSchema	Schéma d'export des données{}
 * @param i_tabSchema	Table d'export des données{}
 * @param i_appender	A true, écrit à la suite de la table si elle existe, à false écrase la liste{}
 */
public void loadTo (
	Map<Integer,Map<String, Object>> i_mapToWrite,
	String i_outputSchema,
	String i_tabSchema,
	Boolean i_appender
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier
	
//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Initialisation des connexions
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			if(this.connectionJdbc==null||this.connectionJdbc.isClosed())
			if(clientId!=null&&clientPwd!=null)this.connectionJdbc = DriverManager.getConnection(amoJdbc, clientId, clientPwd);
			else this.connectionJdbc = DriverManager.getConnection(amoJdbc);
//	//	//	Etape	"2" : poids relatif de 20, Vérification de l'existence des éléments en base de données et création éventuelle (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		HashMap<String,String> orderDatas = new HashMap();
		orderDatas.put("schema", i_outputSchema);
		orderDatas.put("table", i_tabSchema);
		Integer ct_schema = DVP_countQuery("getSchemaList", orderDatas);
		if(ct_schema==0){
			DVP_executeQuery("createSchema", orderDatas);
			DVP_createTable(i_mapToWrite,i_outputSchema,i_tabSchema);
		}else {
			Integer ct_table = DVP_countQuery("getTableList", orderDatas);
			if(ct_table!=0&&!i_appender){
				DVP_executeQuery("dropTable", orderDatas);
				DVP_createTable(i_mapToWrite,i_outputSchema,i_tabSchema);
			}else if(ct_table==0){
				DVP_createTable(i_mapToWrite,i_outputSchema,i_tabSchema);
			}
		}		
//	//	//	Etape	"3" : poids relatif de 100, Ecriture des données (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		DVP_batchQuery("insert", orderDatas, i_mapToWrite);
		
//	//	//	Etape	"4" : poids relatif de 20, Ecriture des logs (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 10, Configuration des connexions
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"loadTo_output1" : Nombre de lignes écrites (Integer)
	//	this.WILD_setOutput("loadTo_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"loadTo_output2" : Nombre d'erreurs éventuelles (Integer)
	//	this.WILD_setOutput("loadTo_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"loadTo_output3" : Map des erreurs récupérées (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("loadTo_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
		if(this.connectionJdbc!=null&&!this.connectionJdbc.isClosed())this.connectionJdbc.close();
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
 
private void DVP_createTable(Map<Integer, Map<String, Object>> i_mapToWrite, String i_outputSchema,	String i_tabSchema) throws SQLException {
	HashMap<String,String> orderDatas = new HashMap();
	orderDatas.put("schema", i_outputSchema);
	orderDatas.put("table", i_tabSchema);
	orderDatas.put("fieldswithtype", cast_strMetadatas(i_mapToWrite));
	DVP_executeQuery("createTable", orderDatas);
}


/**
 * Charge un jeu de données (sous forme de Map) vers une table. Crée la table si elle n'existe pas.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_mapToWrite	La source est une Map {}
 * @param i_outputSchema	Schéma d'export des données {}
 * @param i_tabSchema	Table d'export des données {}
 */
 public void loadTo (
	Map<Integer,Map<String, Object>> i_mapToWrite,
	String i_outputSchema,
	String i_tabSchema
	)throws Exception{
loadTo(i_mapToWrite,i_outputSchema,i_tabSchema,true);
} 
 
/**
 * Composeur d'ordres SQL, 
 * en fonction des structures par défaut posées dans sqlQueries,
 * spécifiques à chaque classe fille
 * @param orderToExec	Nom standardisé de l'ordre (i.e. "addColumn", "createTable"... : clé dans sqlQueries)
 * @param orderDatas	Valeurs de remplacement pour les #6D#{index}#6D#, dans la rédaction de l'ordre standardisé contenu par sqlQueries
 * @throws SQLException
 */
protected void DVP_executeQuery(String orderToExec, HashMap<String,String> orderDatas) throws SQLException{
	String query = this.sqlQueries.get(orderToExec);
	for(String relaser:orderDatas.keySet())query = query.replace("#6D#"+relaser+"#6D#", orderDatas.get(relaser));
	if(connectionJdbc == null || connectionJdbc.isClosed())this.connectionJdbc = DriverManager.getConnection(amoJdbc, clientId, clientPwd);
	Statement stmt = connectionJdbc.createStatement();
	//System.out.println(query);
	stmt.execute(query);
	//stmt.close();
}
/**
 * Composeur d'ordres SQL, 
 * en fonction des structures par défaut posées dans sqlQueries,
 * spécifiques à chaque classe fille
 * Neutralise les erreurs (ne renvoie rien)
 * @param orderToExec	Nom standardisé de l'ordre (i.e. "addColumn", "createTable"... : clé dans sqlQueries)
 * @param orderDatas	Valeurs de remplacement pour les #6D#{index}#6D#, dans la rédaction de l'ordre standardisé contenu par sqlQueries
 */
protected void DVP_executeQuery_Blind(String orderToExec, HashMap<String,String> orderDatas) {
	try{DVP_executeQuery(orderToExec, orderDatas);}catch(Exception e){}
}
/**
 * Exécution d'ordre en mode batch, économisant les connexion / déconnexion etc.
 * @param orderToExec	Nom standardisé de l'ordre (i.e. "addColumn", "createTable"... : clé dans sqlQueries)
 * @param orderDatas	Valeurs de remplacement pour les #6D#{index}#6D#, dans la rédaction de l'ordre standardisé contenu par sqlQueries
 * @param batchedDatas	Données indexées par ordre, instanciées, pour alimentation successif des requêtes
 * @throws SQLException
 */
protected void DVP_batchQuery(String orderToExec, Map<String,String> orderDatas, Map<Integer, Map<String, Object>> batchedDatas) throws SQLException{
	String query = this.sqlQueries.get(orderToExec);
	for(String relaser:orderDatas.keySet())query = query.replace("#6D#"+relaser+"#6D#", orderDatas.get(relaser));
	HashMap<String, Object[]> batchedMetaDatas = cast_mapMetadatas(batchedDatas);
	String fNames = "" ;
	String fValues = "" ;
	Boolean first = true ;
	String[] linkedCols = new String[batchedMetaDatas.size()];
	Integer i = 0 ;
	
	for(String fName:batchedMetaDatas.keySet()){
		linkedCols[i++] = fName;
		if(first){
			first = false ;
			fNames = "\""+fName+"\"" ; ;
			fValues = "?" ;
		}else{
			fNames += ",\""+fName+"\"" ;
			fValues += ",?" ;
		}
	}
	query = query.replace("#6D#fields#6D#",fNames).replace("#6D#values#6D#",fValues);
	PreparedStatement stmt = connectionJdbc.prepareStatement(query);
	try{
    	connectionJdbc.setAutoCommit(false);
		for(Map.Entry<Integer,Map<String, Object>> e : batchedDatas.entrySet()) {
			try{
				for(int y = 0 ; y < linkedCols.length ; y++)stmt.setObject(y+1, e.getValue().get(linkedCols[y]));
			stmt.execute();	 
			connectionJdbc.commit();
			}catch(Exception e1){e1.printStackTrace();}
		}	
	 } catch (SQLException e ) {
	       e.printStackTrace();
	       if (connectionJdbc != null) {
	            try {
	                System.err.print("Transaction is being rolled back");
	                connectionJdbc.rollback();
	            } catch(SQLException excep) {
	            	e.printStackTrace();
	            }
	        }
	    } finally {
	    	if(connectionJdbc!=null)connectionJdbc.setAutoCommit(true);
	    }
	
}
/**
 * Casteur pour rédacion d'ordres SQL, à partir de noms de colonnes
 * @param i_mapToWrite	Liste de noms de colonnes
 * @return
 */
private String cast_strMetadatas(Map<Integer, Map<String, Object>> i_mapToWrite) {
	 HashMap<String, Object[]> hM = cast_mapMetadatas(i_mapToWrite) ;
	 String toRet = "";
	 for(String k:hM.keySet())if(toRet.length()==0){
		 toRet += "\""+k+"\" "+hM.get(k)[2];
	 }else{
		 toRet += ",\""+k+"\" "+hM.get(k)[2];
	 }
	 return toRet ;
}
/**
 * Convertisseur de map JAVA vers une map de métadonnées SQL
 * Sert à composer une table SQL à partir d'une map JAVA
 * Utilise comme spécification la map sqjJavaTypes, partiellement surchargée par les classes filles
 * @param i_mapToWrite	Map JAVA contenant les données, à convertir
 * @return
 */
private HashMap<String, Object[]> cast_mapMetadatas(Map<Integer, Map<String, Object>> i_mapToWrite) {
	HashMap<String, Object[]> toRet = new HashMap();
	Integer i = 1 ;
	Integer numMaxAttempts = 100;
	Integer numAttempts = 1;
	for(Map.Entry<Integer,Map<String, Object>> e : i_mapToWrite.entrySet()) 
		for(String e1 : e.getValue().keySet())		
			if(!toRet.keySet().contains(e1)&& (numAttempts++) < numMaxAttempts ){
				String typeSql = (String) (
						(e.getValue()!=null&&e.getValue().get(e1)!=null&&
						sqjJavaTypes.get(e.getValue().get(e1).getClass().getSimpleName().toLowerCase())!=null)?
										sqjJavaTypes.get(e.getValue().get(e1).getClass().getSimpleName().toLowerCase())[1]:null);
				String classSql = (e.getValue()!=null&&e.getValue().get(e1)!=null)? e.getValue().get(e1).getClass().getSimpleName():null;
				if(classSql!=null)toRet.put(e1, 
					new Object[]{
							i++,
							classSql,
							typeSql
					});
			}
	return toRet;
}

/**
 * Exécution d'une requête, récupération des résultats
 * @param orderToExec	Nom standardisé de l'ordre (i.e. "getTableList"... : clé dans sqlQueries)
 * @param orderDatas	Valeurs de remplacement pour les #6D#{index}#6D#, dans la rédaction de l'ordre standardisé contenu par sqlQueries
 * @return
 * @throws SQLException
 */
protected Map<Integer, Map<String, Object>> DVP_getQuery(String orderToExec, HashMap<String,String> orderDatas) throws SQLException{
	Map<Integer, Map<String, Object>> toRet = new HashMap();
	String query = this.sqlQueries.get(orderToExec);
	for(String relaser:orderDatas.keySet())query = query.replace("#6D#"+relaser+"#6D#", orderDatas.get(relaser));
	if(connectionJdbc == null || connectionJdbc.isClosed())this.connectionJdbc = DriverManager.getConnection(amoJdbc, clientId, clientPwd);
	Statement stmt = connectionJdbc.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	Integer i = 1 ;
	while (rs.next()) {
		Integer colCount = rs.getMetaData().getColumnCount();
		HashMap<String,Object> row = new HashMap();
		for(int j=1;j<=colCount;j++)row.put(rs.getMetaData().getColumnName(j), rs.getObject(j));
		toRet.put(i++, row);
    }
	//stmt.close();
	return toRet ;
}
/**
 * Compte le nombre d'éléments renvoyés par une requête standardisée
 * @param orderToExec	Nom standardisé de l'ordre (i.e. "getTableList"... : clé dans sqlQueries)
 * @param orderDatas	Valeurs de remplacement pour les #6D#{index}#6D#, dans la rédaction de l'ordre standardisé contenu par sqlQueries
 * @return
 * @throws SQLException
 */
protected Integer DVP_countQuery(String orderToExec, HashMap<String,String> orderDatas) throws SQLException{
	Integer toRet = 0;
	String query = this.sqlQueries.get(orderToExec);
	for(String relaser:orderDatas.keySet())query = query.replace("#6D#"+relaser+"#6D#", orderDatas.get(relaser));
	Statement stmt = connectionJdbc.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	while (rs.next()) toRet++;
	stmt.close();
	return toRet ;
}
/**
 * Libération de la connexion
 */
public void DVP_free(){
	try {
		if(this.connectionJdbc!=null&&!this.connectionJdbc.isClosed())this.connectionJdbc.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}


}
