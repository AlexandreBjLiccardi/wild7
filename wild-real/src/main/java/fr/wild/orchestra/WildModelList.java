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
package fr.wild.orchestra;

import org.hibernate.SessionFactory;

import static fr.wild.common.Connector.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Node;

import fr.wild.hibernate.Model;

/**
 * Liste des modèles mobilisables par la liste d'exécution.
 * Sert "d'entrée-sortie" vers les modèles
 * Sérialisées pour permettre un multi-thread réel
 * @author alexandre.liccardi
 * @version [ab]
 */
public class WildModelList implements Serializable{

    private static final long serialVersionUID = 1L;
    // Objets de serialisation
    private WildModelList copyO ; // Préparation du flux pour serialization
    private ByteArrayOutputStream baos ; // Préparation du flux pour serialization
    private  ObjectOutputStream oos ; // Préparation du flux pour serialization
    // Objets de description des modèles
    private HashMap<String,WildModel> dmList = new HashMap<>(); // Liste des modèles pris en compte
    private final String[] unUseDir = {"components","scenarios"}; // Répertoires dans lesquels aucun modèle est recherché (modèles composés)
    private WildWrapper configWrapper ; // wrapper du fichier de configuration générique, pour récupération des exceptions et messages
    private WildLogger wildLogger = null ; // logger lors de l'appel depuis un ExecList

    private SessionFactory dbFactory = null;
    private WildClassLoader wildClassLoader;

    /**
     * Constructeur
     * @param i_diceLogger_loop    Logger précédemment instancié
     * @param i_dbFactory Session hibernate factory pour éviter sa recréation
     * @throws XPathExpressionException
     * @throws ClassNotFoundException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public WildModelList(WildLogger i_diceLogger_loop,SessionFactory i_dbFactory) throws Exception {
        wildLogger = i_diceLogger_loop ;
        dbFactory = i_dbFactory ;
        initialize();
    }

    /**
     * Constructeur, sans connexion DB
     * @param i_diceLogger_loop
     * @throws Exception
     */
    public WildModelList(WildLogger i_diceLogger_loop) throws Exception {
        wildLogger = i_diceLogger_loop ;
        dbFactory = null ;
        initialize();
    }

    /**
     * Fonction d'initialisation de la liste de modèles
     * construit l'ensemble des modèles du dossier wild
     * puis construit les composants
     * enfin construit les scénarios
     * @throws XPathExpressionException
     * @throws ClassNotFoundException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public void initialize() throws Exception{
        // Chargement de la configuration
        configWrapper = new WildWrapper("Configuration","configuration/config.6conf",null);
        if(!wildLogger.hasModels())    wildLogger.setListModel(this);
        // Parse des dossiers "Model"
        flushAppModels();
        // Parse des scenarios et des composants
        flushCompModels();
    }

    /**
     * Flush des modèles applicatifs, c'est-à-dire des répertoires contenus dans WILD, hors composants et scenarios
     * @throws Exception
     */
    private void flushAppModels() throws Exception {
        String wildPath = (String) configWrapper.getParameter("wild_path") ;

        String[] listDir = new File(wildPath).list();
        for(String thisDir:listDir){
            if(!Arrays.asList(unUseDir).contains(thisDir)){
                WildModel dModel = null;
                try {
                    dModel = new WildModel(thisDir,this);
                } catch (Exception e) {
                    wildLogger.logError("FlushFailure",thisDir+" - JAVA : "+e.getMessage());
                }
                if(dModel!=null&&!dModel.isEmpty()){
                    dmList.put(thisDir,dModel);
                }
            }
        }
        setJarPathes();
    }
    
    /**
     * Flush des modèles composés, c'est-à-dire des répertoires composants et scenarios contenus dans WILD
     * @throws Exception
     */
    private void flushCompModels() throws Exception {
        for(int i=0 ; i < unUseDir.length ; i++){
            WildModel dModel = null;
            try {
                dModel = new WildModel(unUseDir[i], this);
            } catch (Exception e) {
                e.printStackTrace();
                wildLogger.logError("FlushFailure",unUseDir[i]+" - JAVA : "+e.getMessage());
            }
            if(dModel!=null&&!dModel.isEmpty())dmList.put(unUseDir[i],dModel);
        }
    }

    /**
     * Récupération du Logger
     * @return
     */
    public WildLogger getLogger(){
        return this.wildLogger;
    }

    /**
     * Retour d'une propriété de configuration ("Parameter")
     * @param paramName    Nom du paramètre
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getProperty(String paramName){
        return (T) configWrapper.getParameter(paramName);
    }

    /**
     * Retour d'une propriété de configuration ("Parameter") dans le contexte d'un modèle
     * @param modelName    Nom du modèle
     * @param paramName Nom du paramètre
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getProperty(String modelName, String paramName){
        return (T) dmList.get(modelName).getProperty(paramName);
    }

    /**
     * Retour d'une erreur standard
     * @param ident    Identifiant de l'erreur
     * @return
     */
    public HashMap<String,Object> getStdError(String ident){
        return configWrapper.getException(ident);
    }

    /**
     * Retour d'un messagestandard
     * @param ident    Identifiant du message
     * @return
     */
    public HashMap<String,Object> getStdMessage(String ident){
        return configWrapper.getMessage(ident);
    }

    /**
     * Méthode de sérialisation
     * @return
     */
    public synchronized WildModelList deepClone() {
        try {
            copyO = new WildModelList(this) ;
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(copyO);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (WildModelList) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Constructeur pour clonage de l'objet
     * @param another    Liste de modèle WildModelList à copier
     * @throws Exception
     */
    public WildModelList(WildModelList another) throws Exception{
        wildLogger = null ;
        configWrapper = new WildWrapper(another.getConfigWrapper());
        dmList = new HashMap<>();
        for(String k:another.dmList.keySet())dmList.put(k,new  WildModel(another.dmList.get(k)));
    }

    /**
     * Récupération du wrapper du fichier de  configuration
     * @return
     */
    public WildWrapper getConfigWrapper() {
        return this.configWrapper;
    }

    /**
     * Retour de la liste, sous forme d'objet JAVA liste
     * @return
     */
    public  HashMap<String,WildModel> getModelList(){
        return dmList;
    }

    /**
     * Récupération d'un modèle par le nom de modèle (inclut "scenarios" et "components")
     * @param modelName    Nom du modèle à récupérer
     * @return
     */
    public WildModel getModel(String modelName){
        return dmList.get(modelName);
    }

    /**
     * Récupération de la description d'une étape affectée à une brique, en communicant :
     * @param dB    la brique elle-même
     * @param ident    le sous élément en cours de traitement
     * @return
     */
    public Object[] getStep(WildBrick dB, Integer ident){
        return getStep(dB.getArrayWilds(), ident);
    }

    /**
     *  Récupération de la description d'une étape affectée à une brique, en communicant :
     * @param arrayWilds    la généalogie de la brique {scenario, component, object, method}, avec object = model.objectName
     * @param ident    le sous élément en cours de traitement
     * @return
     */
    public Object[] getStep(String[] arrayWilds, Integer ident){
        Object[] toRet = null ;
        if(arrayWilds!=null)switch(arrayWilds.length){
            case 4 : // Méthode
                String[] splitm = arrayWilds[2].split("\\.");
                toRet = getModel(splitm[0]).getStep(new String[]{splitm[0],splitm[1],arrayWilds[3]}, ident);
                if(toRet != null)return toRet ;
                break;
            case 3: // Objet
                String[] split = arrayWilds[2].split("\\.");
                toRet = getModel(split[0]).getStep(new String[]{split[0],split[1]}, ident);
                if(toRet != null)return toRet ;
                break; // Pas de suite logique entre object et composants
            case 2: // Composant
                toRet = getModel("components").getStep(new String[]{"components",arrayWilds[1]}, ident);
                if(toRet != null)return toRet ;
                break;
            case 1: // Scénario
                toRet = getModel("scenarios").getStep(new String[]{"scenarios",arrayWilds[0]}, ident);
                if(toRet != null)return toRet ;
        }
        return configWrapper.getStep(ident);
    }

    /**
     * Retour d'une exception affectée à une brique
     * @param dB    Brique de recherche
     * @param ident    identifiant de l'exception
     * @return
     */
    public HashMap<String,Object> getException(WildBrick dB, String ident){
        return getException(dB.getArrayWilds(), ident);
    }

    /**
     * Retour d'une exception affectée à une brique
     * @param dB    Brique de recherche
     * @param ident    Exception (on utilise par défaut le nom de la classe JAVA)
     * @return
     */
    public HashMap<String,Object> getException(WildBrick dB, Exception ident){
        return getException(dB.getArrayWilds(), ident.getClass().getSimpleName());
    }

    /**
     * Retour d'une exception affectée à une brique
     * @param arrayWilds    la généalogie de la brique {scenario, component, object, method}, avec object = model.objectName
     * @param ident    Exception (on utilise par défaut le nom de la classe JAVA)
     * @return
     */
    public HashMap<String, Object> getException(String[] arrayWilds,  Exception ident) {
        return getException(arrayWilds, ident.getClass().getSimpleName());
    }


    /**
     * Retour d'une exception affectée à une brique
     * @param arrayWilds    la généalogie de la brique {sceanrio, component, object, method}, avec object = model.objectName
     * @param ident    Exception (on utilise par défaut le nom de la classe JAVA)
     * @param fromTrace    indique si la méthode est traitée depuis une automatisation des identifiants StackTrace
     * @return
     */
    public HashMap<String, Object> getException(String[] arrayWilds,  Exception ident, Boolean fromTrace) {
        String[] arrayWilds_mod = arrayWilds ;
        if(arrayWilds!=null&&fromTrace){
            arrayWilds_mod = new String[arrayWilds.length+1];
            arrayWilds_mod[0] = null ;
            arrayWilds_mod[1] = null ;
            arrayWilds_mod[2] = arrayWilds[0]+"."+arrayWilds[1] ;
            if(arrayWilds.length==3)arrayWilds_mod[3] = arrayWilds[2] ;
        }
        return getException(arrayWilds_mod, ident.getClass().getSimpleName());
    }

    /**
     * Retour d'une exception affectée à une brique
     * @param arrayWilds    la généalogie de la brique {sceanrio, component, object, method}, avec object = model.objectName
     * @param ident    Exception (on utilise par défaut le nom de la classe JAVA)
     * @param fromTrace    indique si la méthode est traitée depuis une automatisation des identifiants StackTrace
     * @return
     */
    public HashMap<String, Object> getException(String[] arrayWilds,  String ident, Boolean fromTrace) {
        String[] arrayWilds_mod = arrayWilds ;
        if(arrayWilds!=null&&fromTrace){
            arrayWilds_mod = new String[arrayWilds.length+1];
            arrayWilds_mod[0] = null ;
            arrayWilds_mod[1] = null ;
            arrayWilds_mod[2] = arrayWilds[0]+"."+arrayWilds[1] ;
            if(arrayWilds.length==3)arrayWilds_mod[3] = arrayWilds[2] ;
        }
        return getException(arrayWilds_mod, ident);
    }

    /**
     * Retour d'une exception affectée à une brique
     * @param arrayWilds    la généalogie de la brique {sceanrio, component, object, method}, avec object = model.objectName
     * @param ident    Exception (on utilise par défaut le nom de la classe JAVA)
     * @return
     */
    public HashMap<String,Object> getException(String[] arrayWilds, String ident){
        HashMap<String,Object> toRet = null ;
        if(arrayWilds!=null)switch(arrayWilds.length){
            case 4 : // Méthode
                String[] splitm = arrayWilds[2].split("\\.");
                toRet = getModel(splitm[0]).getException(new String[]{splitm[0],splitm[1],arrayWilds[3]}, ident);
                if(toRet != null)return toRet ;
            case 3: // Objet
                String[] split = arrayWilds[2].split("\\.");
                toRet = getModel(split[0]).getException(new String[]{split[0],split[1]}, ident);
                if(toRet != null)return toRet ;
                break; // Pas de suite logique entre object et composants
            case 2: // Composant
                toRet = getModel("components").getException(new String[]{"components",arrayWilds[1]}, ident);
                if(toRet != null)return toRet ;
            case 1: // Scénario
                toRet = getModel("scenarios").getException(new String[]{"scenarios",arrayWilds[0]}, ident);
                if(toRet != null)return toRet ;
        }
        toRet = configWrapper.getException(ident);
        if(toRet != null)return toRet ;
        else return configWrapper.getException("NotAvailableException") ;
    }

    /**
     * Retour d'un message affecté à une brique
     * @param dB    Brique de recherche
     * @param ident    identifiant du message
     * @return
     */
    public HashMap<String,Object> getMessage(WildBrick dB, String ident){
        return getMessage(dB.getArrayWilds(), ident);
    }

    /**
     * Retour d'un message affectée à une brique
     * @param arrayWilds    la généalogie de la brique {scenario, component, object, method}, avec object = model.objectName
     * @param ident    identifiant du message
     * @param fromTrace    indique si la méthode est traitée depuis une automatisation des identifiants StackTrace
     * @return
     */
    public HashMap<String,Object> getMessage(String[] arrayWilds, String ident, Boolean fromTrace){
        String[] arrayWilds_mod = arrayWilds ;
        if(fromTrace!=null&&arrayWilds!= null){
            arrayWilds_mod = new String[arrayWilds.length+1];
            arrayWilds_mod[0] = null ;
            arrayWilds_mod[1] = null ;
            arrayWilds_mod[2] = arrayWilds[0]+"."+arrayWilds[1] ;
            if(arrayWilds.length==3)arrayWilds_mod[3] = arrayWilds[2] ;
        }
        return getMessage(arrayWilds_mod, ident);
    }

    /**
     * Retour d'un message affectée à une brique
     * @param arrayWilds    la généalogie de la brique {scenario, component, object, method}, avec object = model.objectName
     * @param ident        identifiant du message
     * @return
     */
    public HashMap<String,Object> getMessage(String[] arrayWilds, String ident){
        HashMap<String,Object> toRet = null ;
        if(arrayWilds!=null)switch(arrayWilds.length){
            case 4 : // Méthode
                String[] splitm = arrayWilds[2].split("\\.");
                toRet = getModel(splitm[0]).getMessage(new String[]{splitm[0],splitm[1],arrayWilds[3]}, ident);
                if(toRet != null)return toRet ;
            case 3: // Objet
                String[] split = arrayWilds[2].split("\\.");
                toRet = getModel(split[0]).getMessage(new String[]{split[0],split[1]}, ident);
                if(toRet != null)return toRet ;
                break; // Pas de suite logique entre object et composants
            case 2: // Composant
                toRet = getModel("components").getMessage(new String[]{"components",arrayWilds[1]}, ident);
                if(toRet != null)return toRet ;
            case 1: // Scénario
                toRet = getModel("scenarios").getMessage(new String[]{"scenarios",arrayWilds[0]}, ident);
                if(toRet != null)return toRet ;
        }
        toRet = configWrapper.getMessage(ident);
        if(toRet != null)return toRet ;
        else return configWrapper.getMessage("NotAvailableMessage") ;
    }

    /**
     * Retour de la description des outputs (variables remontées depuis les instanciations)
     * @param dB    Brique de recherche
     * @param ident    identifiant de l'output
     * @return
     */
    public HashMap<String,Object> getOutput(WildBrick dB, String ident){
        return getOutput(dB.getArrayWilds(), ident);
    }

    /**
     * Retour de la description des outputs (variables remontées depuis les instanciations)
     * @param arrayWilds    la généalogie de la brique {sceanrio, component, object, method}, avec object = model.objectName
     * @param ident    identifiant de l'output
     * @return
     */
    public HashMap<String,Object> getOutput(String[] arrayWilds, String ident){
        HashMap<String,Object> toRet = null ;
        if(arrayWilds!=null)switch(arrayWilds.length){
            case 4 : // Méthode
                String[] splitm = arrayWilds[2].split("\\.");
                toRet = getModel(splitm[0]).getOutput(new String[]{splitm[0],splitm[1],arrayWilds[3]}, ident);
                if(toRet != null)return toRet ;
            case 3: // Objet
                String[] split = arrayWilds[2].split("\\.");
                toRet = getModel(split[0]).getOutput(new String[]{split[0],split[1]}, ident);
                if(toRet != null)return toRet ;
                break; // Pas de suite logique entre object et composants
            case 2: // Composant
                toRet = getModel("components").getOutput(new String[]{"components",arrayWilds[1]}, ident);
                if(toRet != null)return toRet ;
            case 1: // Scénario
                toRet = getModel("scenarios").getOutput(new String[]{"scenarios",arrayWilds[0]}, ident);
                if(toRet != null)return toRet ;
        }
        return configWrapper.getOutput(ident);
    }

    /**
     * Récupération des infos (attributs d'en-tête) d'une brique
     * @param dB    brique de recherche
     * @param ident    nom de l'attribut
     * @return
     */
    public Object getInfo(WildBrick dB, String ident){
        return getInfo(dB.getArrayWilds(), ident);
    }

    /**
     * Récupération des infos (attributs d'en-tête) d'une brique
     * @param arrayWilds    la généalogie de la brique {scenario, component, object, method}, avec object = model.objectName
     * @param ident    nom de l'attribut
     * @return
     */
    public Object getInfo(String[] arrayWilds, String ident){
        Object toRet = null ;
        if(arrayWilds!=null)switch(arrayWilds.length){
            case 4 : // Méthode
                String[] splitm = arrayWilds[2].split("\\.");
                toRet = getModel(splitm[0]).getInfo(new String[]{splitm[0],splitm[1],arrayWilds[3]}, ident);
                if(toRet != null)return toRet ;
            case 3: // Objet
                String[] split = arrayWilds[2].split("\\.");
                toRet = getModel(split[0]).getInfo(new String[]{split[0],split[1]}, ident);
                if(toRet != null)return toRet ;
                break; // Pas de suite logique entre object et composants
            case 2: // Composant
                toRet = getModel("components").getInfo(new String[]{"components",arrayWilds[1]}, ident);
                if(toRet != null)return toRet ;
            case 1: // Scénario
                toRet = getModel("scenarios").getInfo(new String[]{"scenarios",arrayWilds[0]}, ident);
                if(toRet != null)return toRet ;
        }
        return configWrapper.getInfo(ident);
    }

    /**
     * Récupération des noeuds de sous élément d'une brique
     * @param runner    Brique de recherche
     * @return
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public HashMap<Integer,Node> getSub(WildBrick runner) throws Exception{
        switch(runner.getClass().getSimpleName()){
        case "WildScenario" :
            return dmList.get("scenarios").getSub(runner.getArrayWilds()[0]);
        case "WildComponent" :
            return dmList.get("components").getSub(runner.getArrayWilds()[1]);
        case "WildObject" :
            return dmList.get("components").getSub(runner.getArrayWilds()[1],runner.getMethodXParentProgress()-2);
        default:
            break;
        }
        return null ;
    }

    /**
     * Liste de noms de modèles contenus
     * @return
     */
    public List<String> getModelsList() {
        List<String> toRet = new ArrayList<String>();
        Set<String> asList = dmList.keySet();
        toRet.addAll(asList);
        return toRet;
    }

    /**
     * Récupération dans la base de données de la version du modèle utilsiée par le scénario
     * @param modelName    Nom générique du jar utilisé
     * @return
     */
    public String getModel_BestOffer(String modelName) {
        String modelJarPath = null ;
        if(dbFactory == null)dbFactory = getDbSessionFactory(this);
        Model gotModel = getDbEntityWhere(dbFactory, Model.class, "where short_name ='"+modelName+"' AND favorite = true");
        modelJarPath = (gotModel==null)?null:this.getProperty("wild_path")+File.separator+modelName+File.separator+gotModel.getJarName();
        if(modelJarPath!=null&&new File(modelJarPath).exists())return modelJarPath;
        return null;
    }

    /**
     * Construction des chemins JAR par défaut
     * Désactivé en version développement
     */
    private void setJarPathes(){
        /*
        // Selon paramètrage, vérification du modèle "favorite" à chaque instanciation
        HashMap<String,String> hM = new HashMap<String,String>();
        // Sans recalcul
        Boolean reload = (Boolean)getProperty("auto_reload_modeljar") ;
        if(reload==null||!reload)for(String k:dmList.keySet())hM.put(k, dmList.get(k).getJarPath());
        else for(String k:dmList.keySet())hM.put(k, dmList.get(k).getJarPath());
        // Avec recalcul
        for(String k:dmList.keySet())hM.put(k, getModel_BestOffer(k));
        wildClassLoader = new WildClassLoader(hM);
        */
    }

    public WildClassLoader getWildClassLoader(){
        if(wildClassLoader==null)setJarPathes();
        return wildClassLoader;
    }

}