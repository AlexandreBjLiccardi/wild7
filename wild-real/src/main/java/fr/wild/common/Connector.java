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
package fr.wild.common;

import java.io.File;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import fr.wild.orchestra.WildModelList;


/**
 * Classe générique de connecteur Hibernate,
 * charge le précédent modèle
 * @author alexandre.liccardi
 * @version 0.1b [ab]
 */
public class Connector {
	/**
	 * Ping une base à l'aide d'un driver JDBC : l'entrée recherchée existe-t-elle ?
	 * 
	 * @param amoJdbc
	 * @param clientId
	 * @param clientPwd
	 * @param sqlStr
	 * @return true si au moins une ligne est renvoyée
	 * @throws SQLException
	 */
    public static  Boolean ping_jdbc_result(String amoJdbc, String clientId, String clientPwd, String sqlStr) throws SQLException{
    	Boolean isResult = false ;
    	Statement st = null;
    	ResultSet rs = null;
    	Connection connectionJdbc = null;
    	try{
    	connectionJdbc = DriverManager.getConnection(amoJdbc, clientId, clientPwd);
    	st = connectionJdbc.createStatement();
    	 rs = st.executeQuery(sqlStr);
    	while (rs.next())isResult = true ;
    	}finally{
    		if(rs!=null)rs.close();
    		if(st!=null)st.close();
    		if(connectionJdbc!=null)connectionJdbc.close();
    	}
    	return isResult;
    }
	
    /**
     * Construction de la connexion Hibernate
     * @param listOfModels    Liste de modèles WILD
     * @return
     */
    public static SessionFactory getDbSessionFactory(WildModelList listOfModels){
        HashMap<String, Object> diceConfig    = new HashMap<String, Object>() ;
        diceConfig.put("db_host", listOfModels.getProperty("db_host"));
        diceConfig.put("db_port", listOfModels.getProperty("db_port"));
        diceConfig.put("db_dbName", listOfModels.getProperty("db_dbName"));
        diceConfig.put("db_user", listOfModels.getProperty("db_user"));
        diceConfig.put("db_passWord", listOfModels.getProperty("db_passWord"));
        return getDbSessionFactory(diceConfig);
    }

    /**
     * Construction de la connexion Hibernate
     * @param diceConfig    Paramètres DICE
     * @return
     */
    public static SessionFactory getDbSessionFactory(
        HashMap<String, Object> diceConfig){

        // Construction de l'ordre de connexion
        String jbdcConnectString = "jdbc:postgresql://"
            +(String)diceConfig.get("db_host")+":"+(Integer)diceConfig.get("db_port")
            +"/"+(String)diceConfig.get("db_dbName");
        String pgUser = (String)diceConfig.get("db_user") ;
        String pgPassword = (String)diceConfig.get("db_passWord") ;
        // Configuration de la connexion
        Configuration configuration=new Configuration()
            .configure(new File("configuration/hibernate.cfg.xml"))
            // Ajout des classes spécifiques à l'application
            .addClass(fr.wild.hibernate.AdminLevel.class)
            .addClass(fr.wild.hibernate.Approval.class)
            .addClass(fr.wild.hibernate.CgiAbility.class)
            .addClass(fr.wild.hibernate.CgiItem.class)
            .addClass(fr.wild.hibernate.CommentDrop.class)
            .addClass(fr.wild.hibernate.CommentFile.class)
            .addClass(fr.wild.hibernate.Criticity.class)
            .addClass(fr.wild.hibernate.Drop.class)
            .addClass(fr.wild.hibernate.DropRightsManagement.class)
            .addClass(fr.wild.hibernate.ExecutionArchive.class)
            .addClass(fr.wild.hibernate.ExecutionChainsaw.class)
            .addClass(fr.wild.hibernate.ExecutionConsumption.class)
            .addClass(fr.wild.hibernate.ExecutionInfo.class)
            .addClass(fr.wild.hibernate.ExecutionList.class)
            .addClass(fr.wild.hibernate.JavaConsumption.class)
            .addClass(fr.wild.hibernate.JavaService.class)
            .addClass(fr.wild.hibernate.JavaServiceInfo.class)
            .addClass(fr.wild.hibernate.Model.class)
            .addClass(fr.wild.hibernate.ModelsExecution.class)
            .addClass(fr.wild.hibernate.Preference.class)
            .addClass(fr.wild.hibernate.PreferencesGroup.class)
            .addClass(fr.wild.hibernate.PrivateMessage.class)
            .addClass(fr.wild.hibernate.Project.class)
            .addClass(fr.wild.hibernate.PublicationLevel.class)
            .addClass(fr.wild.hibernate.ReadPrivateMessage.class)
            .addClass(fr.wild.hibernate.ReferenceConcept.class)
            .addClass(fr.wild.hibernate.ReferenceFile.class)
            .addClass(fr.wild.hibernate.ReferenceWord.class)
            .addClass(fr.wild.hibernate.River.class)
            .addClass(fr.wild.hibernate.Status.class)
            .addClass(fr.wild.hibernate.Tag.class)
            .addClass(fr.wild.hibernate.User.class)
            .addClass(fr.wild.hibernate.UserConsumption.class)
            .addClass(fr.wild.hibernate.UserFile.class)
            .addClass(fr.wild.hibernate.UsersPreference.class)
            .addClass(fr.wild.hibernate.ValidateLevel.class)
            .addClass(fr.wild.hibernate.VarType.class)
            .setProperty("hibernate.connection.url", jbdcConnectString)
            .setProperty("hibernate.connection.username", pgUser)
            .setProperty("hibernate.connection.password", pgPassword);


        // Construction de la connexion
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    /**
     * Récupération d'une requête
     * @param sessionFactory    Connexion Hibernate
     * @param classCall    Classe de retour
     * @param condition    Text "WHERE" rajoutée
     * @param maxResults    Nombre max. de résultats fournis
     * @return
     */
    public static  <T> List<T> getDbList(SessionFactory sessionFactory, Class<T> classCall, String condition, int maxResults){
        Session session = sessionFactory.openSession();
        List<T> listObjects = getDbList(session,classCall,condition,maxResults);
        session.close();
        return listObjects ;
    }

    /**
     * Récupération d'une requête
     * @param sessionFactory    Connexion Hibernate
     * @param classCall    Classe de retour
     * @param condition    Text "WHERE" rajoutée
     * @return
     */
    public static  <T> List<T> getDbList(SessionFactory sessionFactory, Class<T> classCall, String condition){
        Session session = sessionFactory.openSession();
        List<T> listObjects = getDbList(session,classCall,condition,null);
        session.close();
        return listObjects ;
    }

    /**
     * Récupération d'une requête
     * @param sessionFactory    Connexion Hibernate
     * @param classCall    Classe de retour
     * @return
     */
    public static  <T> List<T> getDbList(SessionFactory sessionFactory, Class<T> classCall){
        return getDbList(sessionFactory, classCall, null);
    }

    /**
     * Récupération d'une requête
     * @param session    Connexion Hibernate (ouverte)
     * @param classCall    Classe de retour
     * @param condition    Text "WHERE" rajoutée
     * @return
     */
    public static  <T> List<T> getDbList(Session session, Class<T> classCall,  String condition){
        return getDbList(session, classCall, condition, null);
    }

    /**
     * Récupération d'une requête
     * @param session    Connexion Hibernate (ouverte)
     * @param classCall    Classe de retour
     * @return
     */
    public static  <T> List<T> getDbList(Session session, Class<T> classCall){
        return getDbList(session, classCall, null, null);
    }

    /**
     * Récupération d'une requête
     * @param session    Connexion Hibernate (ouverte)
     * @param classCall    Classe de retour
     * @param condition    Text "WHERE" rajoutée
     * @param maxResults    Nombre max. de résultats fournis
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getDbList(Session session, Class<T> classCall, String condition, Integer maxResults){
        Transaction tx = null;
        List<T> listObjects = new ArrayList<T>();
        String request = (condition==null)?"FROM "+ classCall.getSimpleName():"FROM "+ classCall.getSimpleName()+" "+condition;
        try {
            tx = session.beginTransaction();
            Query qt = session.createQuery(request);
            if(maxResults!=null)qt.setMaxResults(maxResults);
            listObjects = (List<T>)qt.list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        session.flush();
        return listObjects;
    }

    /**
     * Requête d'un seul élément, à  partir de la valeur de sa clé primaire
     * @param sessionFactory    Connexion Hibernate (instanciée)
     * @param classCall    Classe de retour
     * @param primaryKey    Valeur de clé primaire
     * @return
     */
    public static  <T,D> T getDbEntity(SessionFactory sessionFactory, Class<T> classCall, D primaryKey){
        Session session = sessionFactory.openSession();
        T toRet = getDbEntity(session, classCall, primaryKey);
        session.close();
        return toRet ;
    }

    /**
     * Requête d'un seul élément, à  partir de la valeur de sa clé primaire
     * @param session    Connexion Hibernate (ouverte)
     * @param classCall    Classe de retour
     * @param primaryKey    Valeur de clé primaire
     * @return
     */
    @SuppressWarnings("unchecked")
    public static  <T,D> T getDbEntity(Session session, Class<T> classCall, D primaryKey){
        Transaction tx = null;
        T toRet = null ;
        try{
            tx = session.beginTransaction();
            toRet = (T) session.get(classCall,(Serializable) primaryKey);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        return toRet;
    }

    /**
     * Requête d'un seul élément, à  partir d'une clause WHERE
     * @param session    Connexion Hibernate (ouverte)
     * @param classCall    Classe de retour
     * @param primaryKey    Valeur de clé primaire
     * @return
     */
    public static  <T,D> T getDbEntityWhere(SessionFactory sessionFactory, Class<T> classCall, String WHERE_CLAUSE){
        Session session = sessionFactory.openSession();
        T toRet = getDbEntityWhere(session, classCall, WHERE_CLAUSE);
        session.close();
        return toRet ;
    }

    /**
     * Requête d'un seul élément, à  partir d'une clause WHERE
     * @param session    Connexion Hibernate (ouverte)
     * @param classCall    Classe de retour
     * @param primaryKey    Valeur de clé primaire
     * @return
     */
    public static  <T,D> T getDbEntityWhere(Session session, Class<T> classCall, String WHERE_CLAUSE){
        List<T> req = getDbList(session, classCall, WHERE_CLAUSE, 1);
        if(req.size()==0)return null;
        return req.get(0);
    }

    /**
     * Suppression d'une entrée dans la base de données
     * @param session    Connexion Hibernate (ouverte)
     * @param todel    Objet à supprimer
     */
    public static <T> void deleteDbEntity(Session session, T todel){
        if(todel==null)return;
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.delete(todel);
            tx.commit();
            session.flush();
        } catch (HibernateException e) {
            if (tx != null)tx.rollback();
        }
    }

    /**
     * Sauvegarde (Commit) d'un objet en base
     * @param session    Connexion Hibernate (ouverte)
     * @param obj    Objet à enregistrer
     */
    public static <T> Boolean saveDb(Session session, T obj){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(obj);
            tx.commit();
            session.flush();
            return true ;
        } catch (HibernateException e) {
            if (tx != null)    tx.rollback();
            tx = null;
            session.clear();
        }
        return false;
    }
    
    /**
    * Sauvegarde (Commit) d'un objet en base
    * @param session    Connexion Hibernate
    * @param obj    Objet à enregistrer
    */
    public static <T> void updateDb(Session session, T obj){
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(obj);
            tx.commit();
            session.flush();
        } catch (HibernateException e) {
            if (tx != null)    tx.rollback();
            tx = null;
            session.clear();
        }
    }
}