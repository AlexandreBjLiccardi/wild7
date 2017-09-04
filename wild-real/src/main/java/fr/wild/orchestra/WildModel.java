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

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;



/**
 * Classe de gestion de modèle
 * Un modèle est un répertoire dans le {wildpath}, contenant des fichiers *.wild et un fichier *.6conf
 *
 * La classe WildModel contient la description des briques WILD, chaque instance de cette classe renvoyant à un modèle.
 * Ces modèles correspondent à des jeux de fichiers regroupés dans un répertoire
 * (dont l'adresse est définie par la variable de configuration {wild_path}),
 * qui contient un fichier .WILD par brique sous-jacente et éventuellement un fichier .CONF6D de re-configuration des variables globales,
 * des comportements en cas d'erreur et des messages dutilisation.
 *
 * L'adresse de chaque brique sera {wild_path}/[NOM_DU_MODELE]/[NOM_DE_LA_BRIQUE].WILD.
 * L'adresse du fichier de configuration sera {wild_path}/[NOM_DU_MODELE/config.6conf.
 * Cette classe contient essentiellement les listes de description des briques et ses accesseurs, 
 * nécessaires à l'invocation, aux retours, à l'émission de messages et au traitement d'erreurs.
 *
 * Un modèle instancie un wrapper (WildWrapper) par fichier.
 * La généalogie est aussi résolue, pour permettre notamment l'appel de méthodes héritées des classes.
 * Deux informations sont conservées : (1) stockage de l'invocation héritée, (2) nom de la brique JAVA à l'origine de la méthode héritée
 * (parce que JAVA ne résout pas les héritages dynamiquement, dans tous les cas).
 * L'héritage est reproduit aussi pour la gestion des erreurs et des messages.
 * Les variables de configuration, les erreurs et les messages sont définies comme tels : 
 * pour chaque brique, si la variable est redéfinie à l'intérieur de la brique elle prévaut,
 * sinon on remonte à la brique d'appel, sinon on remonte au fichier de configuration produit pour le modèle,
 * sinon on remonte au fichier de configuration WILD (configuration/config.6conf).
 *
 * @author    alexandre.liccardi
 * @version [ab]
 */
public class WildModel implements Serializable{

    private static final long serialVersionUID = 1L;

    private String buildError = null ; // Eventuelle erreur de construction du modèle

    private HashMap<String, WildWrapper> diceBrickWrapper = new HashMap<>() ; // Liste contenant pour chaque élément (chaque fichier *.wild), un wrapper
    private HashMap<String,HashMap<String, WildWrapper>> diceMethodWrapper = new HashMap<>() ; // Liste contenant pour chaque méthode (chaque tag "WildMethod") de chaque objet (chaque fichier *.wild), un wrapper
    private String modelName ; // Nom du modèle, correspondant au nom du répertoire de fichiers dans le {wildpath}
    private WildWrapper configWrapper ; // wrapper du fichier de configuration générique, pour récupération des exceptions et messages
    private WildLogger wildLogger = null ; // logger lors de l'appel depuis un ExecList

    private HashMap<String,HashMap<String,String>> methodCatalog ; // Catalogue des méthodes et de leur élément d'instanciation. Nom de la brique, Nom de la méthode, Nom de la brique initiale
    private WildModelList wmList ; // Liste de modèles du contexte

    private String jarPath ;


    /**
     * Copie du modèle pour clonage
     * @param dM    Modèle à cloner
     */
    public WildModel(WildModel dM){
        configWrapper = (dM.configWrapper==null)?null:new WildWrapper(dM.configWrapper);
        for(String k:dM.diceBrickWrapper.keySet())diceBrickWrapper.put(k, new WildWrapper(dM.diceBrickWrapper.get(k)));
        for(String k:dM.diceMethodWrapper.keySet()){
            HashMap<String, WildWrapper> newObj = new HashMap<>();
            for(String k2:dM.diceMethodWrapper.get(k).keySet())    newObj.put(k2, new WildWrapper(dM.diceMethodWrapper.get(k).get(k2)));
            diceMethodWrapper.put(k, newObj);
        }
        methodCatalog = new HashMap<>(dM.methodCatalog);
        modelName = dM.modelName;
        jarPath = dM.jarPath ;
    }

    /**
     * Renvoie le nom du modèle
     * @return
     */
    public String getName(){
        return modelName;
    }

    /**
     * Récupère la liste des noms des sous briques contenus par le modèle
     * @return
     */
    public List<String> getBrickNames(){
        List<String> toRet = new ArrayList<>();
        Set<String> asList = diceBrickWrapper.keySet();
        toRet.addAll(asList);
        return toRet;
    }

    /**
     * Récupère la liste des noms des méthodes contenues par le modèle
     * @return
     */
    public List<String[]> getMethodNames(){
        List<String[]> toRet = new ArrayList<>();
        Set<String> objects = diceBrickWrapper.keySet();
        for(String object:objects){
            String[] toRetConstructor = new String[]{object, "<init>"};
            toRet.add(toRetConstructor);
            Set<String> methods = diceMethodWrapper.get(object).keySet();
            for(String method:methods){
                String[] toRetMeth = new String[]{object, method};
                toRet.add(toRetMeth);
            }
        }
        return toRet;
    }

    /**
     * Constructeur, construit le modèle et résout les dépendances
     * @param    wildpathFile    chemin d'accès disque à la base durépertoire contenant le modèle : {wildpath}/{modelName}
     * @throws Exception
     */
    public WildModel(String wildpathFile, WildModelList i_wildModel) throws Exception {
        initialize(wildpathFile, null, i_wildModel) ;
    }
    
    /**
     * Constructeur, construit le modèle et résout les dépendances
     * @param    wildpathFile    chemin d'accès disque à la base durépertoire contenant le modèle : {wildpath}/{modelName}
     * @param     i_wildLogger    logger
     * @throws Exception
     */
    public WildModel(String wildpathFile, WildLogger i_wildLogger) throws Exception {
        initialize(wildpathFile, i_wildLogger, null) ;
    }

    /**
     * Méthode de construction du modèle
     * On construit d'abord l'ensemble des wrappers
     * On règle les cas d'héritage à l'intérieur du modèle (via l'attribut @inherits)
     * @param wildpathFile
     * @param i_dmList
     * @throws Exception
     */
    private void initialize(String wildpathFile, WildLogger i_wildLogger, WildModelList i_wildModel) throws Exception {
        try{
            if(i_wildLogger!=null)this.wildLogger = i_wildLogger;
            else this.wildLogger = i_wildModel.getLogger();
            buildModel(wildpathFile, i_wildModel);
            solveInheritance();
        }catch(Exception e){
            e.printStackTrace();
            this.wildLogger.logError("WildWrapperError",e.getMessage());
        }
    }

    /**
     * Renseigne si le modèle est vide (aucun fichier .wild conforme trouvé
     * @return TRUE si non vide
     */
    public Boolean isEmpty(){
        return diceBrickWrapper.isEmpty();
    }

    /**
     * Construit le modèle (générique)
     * @param    wildpathFile    chemin d'accès disque à la base durépertoire contenant le modèle : {wildpath}/{modelName}
     * @throws Exception
     */
    private void buildModel(String wildpathFile, WildModelList i_wmList) throws Exception{
        wmList = i_wmList ;
        // Résolution des chemins de fichiers
        wildpathFile = ((String)i_wmList.getProperty("wild_path")).replaceAll("/$","").replaceAll("\\$","")+"/"+wildpathFile.replaceAll("/$","").replaceAll("\\$","");
        File mFile = new File(wildpathFile) ;
        if(!mFile.exists())return;
        modelName = mFile.getName() ;
        this.wildLogger.logEvent("BuildModelAttempt","BUILDING SCHEMA {"+modelName+"} FROM : "+wildpathFile);
        String[] listDir = mFile.list();
        // Chargement de la configuration de base
        if((new File(wildpathFile+"/config.6conf").exists()))configWrapper = new WildWrapper(wildLogger,"Configuration",wildpathFile+"/config.6conf") ;
        else configWrapper = wmList.getConfigWrapper() ;
        // Scan de chaque élément
        for(String thisDir:listDir)if(thisDir.endsWith(".wild")){
            String objectName = thisDir.replaceAll(".wild$","");
            HashMap<Integer,Node> methodList = new HashMap<>();
            HashMap<String, WildWrapper> diceMethodWrapper_o = new  HashMap<>();
            // Construction spécifique des scénarios
            if(modelName.equals("scenarios")){
                this.wildLogger.logEvent("BuildModelAttempt","BUILDING SCENARIO {"+modelName+"}.{"+objectName+"} FROM : "+thisDir);
                if(diceBrickWrapper==null)diceBrickWrapper = new HashMap<>() ;
                try{
                    diceBrickWrapper.put(objectName, new WildWrapper(wildLogger,"WildScenario",wildpathFile+"/"+thisDir, i_wmList.getModelList()));
                }catch(NullPointerException e){
                    buildError = "Please check sub-elements of : "+this.getName()+"."+objectName+". JAVA : "+e.getMessage();
                }
            }else if(modelName.equals("components")){
            // Construction spécifique des composants
                this.wildLogger.logEvent("BuildModelAttempt","BUILDING COMPONENT {"+modelName+"}.{"+objectName+"} FROM : "+thisDir);
                if(diceBrickWrapper==null)diceBrickWrapper = new HashMap<>() ;
                try{
                    diceBrickWrapper.put(objectName, new WildWrapper(wildLogger,"WildComponent",wildpathFile+"/"+thisDir, i_wmList.getModelList()));
                }catch(NullPointerException e){
                    buildError = "Please check sub-elements of : "+this.getName()+"."+objectName+". JAVA : "+e.getMessage();
                }
            }else{
            // Construction spécifique des objets
                this.wildLogger.logEvent("BuildModelAttempt","BUILDING OBJECT {"+modelName+"}.{"+objectName+"} FROM : "+thisDir);
                if(diceBrickWrapper==null)diceBrickWrapper = new HashMap<>() ;
                diceBrickWrapper.put(objectName, new WildWrapper(wildLogger,"WildObject",wildpathFile+"/"+thisDir, i_wmList.getModelList()));
                methodList = diceBrickWrapper.get(objectName).getListNode();
                for(int i=0 ; i<methodList.size() ; i++){
                    WildWrapper dW = new WildWrapper(wildLogger,"WildMethod",methodList.get(i), null);
                    String methodName = dW.getName() ;
                    diceMethodWrapper_o.put(methodName, dW);
                }
            }
            // Scan de chaque méthode pour les composants et les objets
            if(this.buildError==null){
                diceMethodWrapper.put(objectName, diceMethodWrapper_o);
                this.wildLogger.logEvent("BuildBrickSuccess","{"+objectName+"}");
            }else this.wildLogger.logError("WildWrapperError",buildError);
        }
        //// La recherche de JAR est éliminée dans la version de test
        //jarPath = i_wmList.getModel_BestOffer(this.modelName);

        if(this.buildError==null)this.wildLogger.logEvent("BuildModelSuccess","{"+modelName+"} with default JAR path : "+jarPath);
    }

    /**
     * Récupère la liste des Wrapppers d'objet contenus dans le modèle
     * @return
     */
    public HashMap<String, WildWrapper> getWrappers(){
        return diceBrickWrapper;
    }

    /**
     * Récupère la liste des Wrapppers de méthode contenus dans le modèle
     * @return
     */
    public HashMap<String, HashMap<String, WildWrapper>> getMethodWrappers(){
        return diceMethodWrapper;
    }

// // Méthodes de résolution des héritages entre objets, en utilisant l'attribut @inherits

    /**
     * Renvoie, de manière récursive, la liste des méthodes héritées (de l'ancêtre le plus proche au plus lointain)
     * @param objectName Nom de l'objet
     * @return Liste des méthodes héritées
     */
    public List<String> getInheritance(String objectName){
        List<String> toRet = new ArrayList<>();
        // Récupération de la liste des valeurs d'héritage
        String val = (String) getInfo(new String[]{this.modelName,objectName}, "inherits");
        // Boucle et ajout récursif des valeurs identifiées
        if(val!=null&&!val.equals("WildClass")){
            toRet.add(val);
            if((String)getInfo(new String[]{this.modelName,val}, "inherits")!=null)
                toRet.addAll(getInheritance(val));
        }
        return toRet ;
    }

    /**
     * Résout : l'object le plus haut dans le modèle, contenant une méthode,
     * à partir d'un object, en remontant le modèle par héritage
     * Calcul
     * @param objectName    Nom de l'object
     * @param methodName    Nom de la méthode
     * @return
     */
    private String getFirstInheritance(String objectName, String methodName){
        if(diceMethodWrapper.get(objectName).get(methodName)==null)return null;
        String val = (String) getInfo(new String[]{this.modelName,objectName}, "inherits");
        if(val!=null&&!val.equals("WildClass")&&diceMethodWrapper.get(val).get(methodName)!=null)return getFirstInheritance(val, methodName);
        return objectName ;
    }

    /**
     * Résout les conditions d'héritages entre objets, pour les méthodes, en actualisant la liste de Wrappers
     */
    private void solveInheritance(){
        if(diceBrickWrapper.isEmpty()||diceMethodWrapper.isEmpty())return;
        // Pour chaque objet
        int i = 0 ; // On fait deux tours a minima : on est pas sûrs de l'ordre !
        while(i < 2)for(String oName:diceBrickWrapper.keySet()){
            // On récupère la liste des objets parents
            List<String> inheritance = getInheritance(oName);
            HashMap<String, WildWrapper> diceMethodWrapper_o = diceMethodWrapper.get(oName);
            // Et on ré-affecte chaque méthode du parent à l'enfant, si celle-ci n'existe pas encore
            if(inheritance!=null&&!inheritance.isEmpty())for(String iName:inheritance){
                if(diceMethodWrapper.get(iName)!=null&&!diceMethodWrapper.get(iName).isEmpty()){
                Set<String> oMethodNames = diceMethodWrapper_o.keySet();
                Set<String> iMethodNames = diceMethodWrapper.get(iName).keySet();
                for(String mName:iMethodNames)if(!oMethodNames.contains(mName))diceMethodWrapper_o.put(mName,diceMethodWrapper.get(iName).get(mName));
            }}
            diceMethodWrapper.put(oName, diceMethodWrapper_o);
            i++;
        }
        methodCatalog = new HashMap<>();
        for(String oName:diceBrickWrapper.keySet()){
            HashMap<String,String> sub_methodCatalog = new HashMap<>();
            for(String mName:diceMethodWrapper.get(oName).keySet())sub_methodCatalog.put(mName, getFirstInheritance(oName, mName));
            methodCatalog.put(oName, sub_methodCatalog);
        }
    }

    /**
     * Renvoie l'object le plus haut dans le modèle, contenant une méthode,
     * à partir d'un object, en remontant le modèle par héritage
     * Préenregistré dans le modèle
     * @param objectName    Nom de l'object
     * @param methodName    Nom de la méthode
     * @return
     */
    public String getMethodFirstObject(String objectName, String methodName){
    	return methodCatalog.get(objectName).get(methodName);
    }

    /**
     * Récupération d'une info (attribut d'en-tête) à partir de la généalogie
     * @param ident    tableau de généalogie {nom du modèle, nom de l'objet, nom de la méthode}
     * @return
     */
    public Object getInfo(String[] arrayWilds, String ident){
        return  getInfo(arrayWilds, ident, true);
    }

    /**
     * Récupération d'une info (attribut d'en-tête) à partir de la généalogie
     * @param ident    tableau de généalogie {nom du modèle, nom de l'objet, nom de la méthode}
     * @param admitConfig true si on ramène la valeur par défaut si nul. Permet de finir la boucle de recherche par héritage.
     * @return
     */
    public Object getInfo(String[] arrayWilds, String ident, Boolean admitConfig){
        Object toRet = null ;
        switch(arrayWilds.length){
        case 3 : toRet = diceMethodWrapper.get(arrayWilds[1]).get(arrayWilds[2]).getInfo(ident) ; // Nom de modèle + nom d'objet
                    if(toRet!=null)return toRet ;

        case 2 :
            toRet = diceBrickWrapper.get(arrayWilds[1]).getInfo(ident) ; // Nom de modèle + nom d'objet
                    if(toRet!=null)return toRet ;
                    if(!ident.equals("inherits")){
                        List<String> inherit = getInheritance(arrayWilds[1]) ; // Recherche de l'exception pour chaque objet parent
                        for(String iName:inherit){
                                toRet = getInfo(new String[]{modelName,iName},ident,false) ;
                                if(toRet != null) return toRet ;
                    }}
        }
        return null;
    }

    /**
     * Récupération d'un message dans la configuration du modèle
     * @param ident
     * @return
     */
    public HashMap<String,Object> getMessage(String ident){
        return getMessage(new String[]{modelName}, ident, true);
    }

    /**
     * Récupération d'un message en fonction de la généalogie
     * @param arrayWilds tableau de généalogie {nom du modèle, nom de l'objet, nom de la méthode}
     * @param ident
     * @return
     */
    public HashMap<String,Object> getMessage(String[] arrayWilds, String ident){
        return  getMessage(arrayWilds, ident, true);
    }

    /**
     * Récupération d'un message en fonction de la généalogie
     * @param arrayWilds tableau de généalogie {nom du modèle, nom de l'objet, nom de la méthode}
     * @param ident
     * @param admitConfig true si on ramène la valeur par défaut si nul. Permet de finir la boucle de recherche par héritage.
     * @return
     */
    public HashMap<String,Object> getMessage(String[] arrayWilds, String ident, Boolean admitConfig){
        HashMap<String,Object> toRet = null ;
        switch(arrayWilds.length){// Pas de break ! Si absent, on teste le suivant
        // Méthode
        case 3 : toRet = diceMethodWrapper.get(arrayWilds[1]).get(arrayWilds[2]).getMessage(ident) ; // Nom de modèle + nom d'objet
            if(toRet!=null && !toRet.isEmpty())return toRet ;
        // Object
        case 2 :
            //System.out.println("++++++++++++ SEARCH FOR IN "+this.modelName+"."+arrayWilds[1]+" FOR MESSAGE : "+ ident);
            toRet = diceBrickWrapper.get(arrayWilds[1]).getMessage(ident) ; // Nom de modèle + nom d'objet
                    if(toRet!=null && !toRet.isEmpty())return toRet ;
                    List<String> inherit = getInheritance(arrayWilds[1]) ; // Recherche de l'exception pour chaque objet parent
                    for(String iName:inherit){
                            toRet = getMessage(new String[]{modelName,iName},ident,false) ;
                            if(toRet != null && !toRet.isEmpty())return toRet ;
                    }
            //System.out.println("++++++++++++ END FAIL SEARCH FOR "+this.modelName+"."+arrayWilds[1]+" FOR MESSAGE : "+ ident);
        // Modèle : configuration
        case 1 : if(admitConfig)return configWrapper.getMessage(ident) ; // Seulement le nom de modèle
        }
        return null;
    }

    /**
     * Ramène une exception depuis la configuration
     * @param identE
     * @return
     */
    public HashMap<String,Object> getException(String identE){
        return getException(new String[]{modelName}, identE , true);
    }

    /**
     * Récupération d'une exception en fonction de la généalogie
     * @param arrayWilds tableau de généalogie {nom du modèle, nom de l'objet, nom de la méthode}
     * @param ident
     * @return
     */
    public HashMap<String,Object> getException(String[] arrayWilds, String identE){
        return  getException(arrayWilds, identE, true);
    }

    /**
     * Récupération d'une exception en fonction de la généalogie
     * @param arrayWilds tableau de généalogie {nom du modèle, nom de l'objet, nom de la méthode}
     * @param ident
     * @param admitConfig true si on ramène la valeur par défaut si nul. Permet de finir la boucle de recherche par héritage.
     * @return
     */
    public HashMap<String,Object> getException(String[] arrayWilds, String ident, Boolean admitConfig){
        HashMap<String,Object> toRet = null ;
        switch(arrayWilds.length){ // Pas de break
        // Méthodes
        case 3 : toRet = diceMethodWrapper.get(arrayWilds[1]).get(arrayWilds[2]).getException(ident) ; // Nom de modèle + nom d'objet
                    if(toRet!=null && !toRet.isEmpty())return toRet ;
        // Objects
        case 2 : toRet = diceBrickWrapper.get(arrayWilds[1]).getException(ident) ; // Nom de modèle + nom d'objet
                    if(toRet!=null && !toRet.isEmpty())return toRet ;
                    List<String> inherit = getInheritance(arrayWilds[1]) ; // Recherche de l'exception pour chaque objet parent
                    for(String iName:inherit){
                            toRet = getException(new String[]{modelName,iName},ident,false) ;
                            if(toRet != null && !toRet.isEmpty()) return toRet ;
                        }
        // Configuration
        case 1 : if(admitConfig)return configWrapper.getException(ident) ; // Seulement le nom de modèle
        }
        return null;
    }

    /**
     * Récupération des tables de description des variables directement transmises depuis les objets instanciés
     * @param arrayWilds    tableau de généalogie {nom du modèle, nom de l'objet, nom de la méthode}
     * @param ident
     * @return
     */
    public HashMap<String,Object> getOutput(String[] arrayWilds, String ident){

        HashMap<String,Object> toRet = null ;
        switch(arrayWilds.length){ // Pas de break, pas de configuration
        // Méthodes
        case 3 : toRet = diceMethodWrapper.get(arrayWilds[1]).get(arrayWilds[2]).getOutput(ident) ; // Nom de modèle + nom d'objet
                    if(toRet!=null && !toRet.isEmpty())return toRet ;
        // Objects
        case 2 : toRet = diceBrickWrapper.get(arrayWilds[1]).getOutput(ident) ; // Nom de modèle + nom d'objet
                    if(toRet!=null && !toRet.isEmpty())return toRet ;
                    List<String> inherit = getInheritance(arrayWilds[1]) ; // Recherche de l'exception pour chaque objet parent
                    for(String iName:inherit){
                            toRet = getOutput(new String[]{modelName,iName},ident) ;
                            if(toRet != null && !toRet.isEmpty()) return toRet ;
                        }
        }
        return null;

    }

    /**
     * Actualise les valeurs d'un tableau passé en argument, par les valeurs produites par un élément instanciée, pour le retour de paramètres depuis une méthode
     * @param objectName        Nom de l'objet
     * @param methodName        Nom de la méthode
     * @param parametersWith    Liste contenant les valeurs à utiliser pour les Outputs
     * @return
     */
    public HashMap<Integer, HashMap<String, Object>> getOutputs(String objectName, String methodName, HashMap<String,Object> parametersWith){
        return diceMethodWrapper.get(objectName).get(methodName).getOutputs(parametersWith) ;
    }

    /**
     * Renvoie la description WILD d'un Output, selon son identifiant
     * @param objectName        Nom de l'objet
     * @param methodName        Nom de la méthode
     * @return
     */
    public HashMap<String, Object> getOutputs(String objectName, String methodName, String ident){
        if(methodName==null)return diceBrickWrapper.get(objectName).getOutputs().get(ident) ;
        return diceMethodWrapper.get(objectName).get(methodName).getOutputs().get(ident) ;
    }

    /**
     * Renvoie la description WILD d'un Output, selon son identifiant
     * @param objectName        Nom de l'objet
     * @param methodName        Nom de la méthode
     * @return
     */
    public HashMap<String, Object> getOutputs(String objectName, String ident){
        return getOutputs(objectName, null, ident) ;
    }

    /**
     * Caste pour la méthode un objet, actualisé dans le tableau de paramètre transmis par l'instance de classe.
     * L'héritage n'est pris en compte que pour les méthodes, précédemment ransmises aux objets
     * @param objectName        Nom de l'objet
     * @param methodName        Nom de la méthode
     * @param objectValue        Valeur instanciée du retour
     * @param returnUseAs        Nom dans la liste contenant les paramètres transmis entre instances du retour à mettre à jour
     * @param parametersWith    Liste contenant les paramètres transmis entre instances
     * @return                    Liste contenant les paramètres transmis entre instances (mise à jour)
     * @throws ClassNotFoundException            Type spécifié pour le retour non compatible
     * @throws DOMException                        Erreur de description du noeud
     * @throws XPathExpressionException            Erreur de description du noeud
     * @throws SAXException                        Erreur de description du noeud
     * @throws IOException                        Erreur de description du noeud
     * @throws ParserConfigurationException        Erreur de description du noeud
     */
    public HashMap<String,Object> getReturn(String objectName, String methodName, Object objectValue, Node returnUseAs, HashMap<String,Object> parametersWith, Boolean keepItAll) throws ClassNotFoundException, DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        if(objectName!=null&&methodName!=null)return diceMethodWrapper.get(objectName).get(methodName).getReturn(parametersWith,returnUseAs,objectValue, keepItAll) ;
        if(objectName!=null)return diceBrickWrapper.get(objectName).getReturn(parametersWith,returnUseAs,objectValue, keepItAll) ;
        return null;
    }

// Méthodes de récupération des invokations

    /**
     * Renvoie le noeud d'invokation d'un objet ou d'un composant
     * @param eltName Nom du sous élément
     * @param subEltIdx    Numéro d'occurence de l'objet ou du composant
     * @return {Mode parallèle, Nom du modèle, Nom de l'élément (@ident), Noeud d'appel}
     */
    public Object[] getInvokation(String eltName, Integer subEltIdx){
        return diceBrickWrapper.get(eltName).getInvokation(subEltIdx) ;
    }

    /**
     * Renvoie le noeud d'invokation d'une méthode dans un composant
     * @param eltName Nom du sous élément
     * @param subEltIdx    Numéro d'occurence de l'objet
     * @subMethIdx subEltIdx    Numéro d'occurence de la méthode
     * @return {Nom de la méthode (@ident), Noeud d'appel}
     */
    public Object[] getInvokation(String eltName, Integer subEltIdx, Integer subMethIdx){
        return diceBrickWrapper.get(eltName).getInvokation(subEltIdx,subMethIdx) ;
    }

    /**
     * Récupère la liste des paramètres, avec les informations par défaut et les types corrigés, pour l'appel d'une méthode.
     * Compare une liste de valeurs en entrée aux invokations disponibles pour la méthode, renvoie la plus proche.
     * L'héritage n'est pris en compte que pour les méthodes, précédemment transmises aux objets
     * @param objectName    Nom de l'objet
     * @param methodName    Nom de la méthode
     * @param paramList        Liste des paramètes (instanciés)
     * @return    Carte contenant l'identifiant, les informations, la valeur instanciée du paramètre ainsi que son ordre (clé : order#6D#)
     * @throws ClassNotFoundException    Si la valeur instanciée du tableau de paramètre ne correspond à aucun type JAVA ou si la valeur du @type du wrapper est fausse
     */
    public HashMap<String,Object> getInvokation(String objectName, String methodName, Object[] paramList) throws ClassNotFoundException{
        HashMap<String,Object> toRet = null ;
        if(methodName != null )toRet = diceMethodWrapper.get(objectName).get(methodName).getInvokation(paramList) ;
        if(toRet != null && !toRet.isEmpty()) return toRet ;
        if(objectName != null )toRet = diceBrickWrapper.get(objectName).getInvokation(paramList) ;
        if(toRet != null && !toRet.isEmpty()) return toRet ;
        return null ;
    }

    /**
     * Récupère la liste des paramètres, avec les informations par défaut et les types corrigés, pour la construction d'un objet.
     * Compare une liste de valeurs en entrée aux invokations disponibles pour le constructeur de l'objet, renvoie la plus proche.
     * @param objectName    Nom de l'objet
     * @param paramList        Liste des paramètes (instanciés)
     * @return    Carte contenant l'identifiant, les informations, la valeur instanciée du paramètre ainsi que son ordre (clé : order#6D#)
     * @throws ClassNotFoundException    Si la valeur instanciée du tableau de paramètre ne correspond à aucun type JAVA ou si la valeur du @type du wrapper est fausse
     */
    public HashMap<String,Object> getInvokation(String objectName, Object[] paramList) throws ClassNotFoundException{
        return getInvokation(objectName, null, paramList) ;
    }

    /**
     * Récupère la liste des paramètres, avec les informations par défaut et les types corrigés, pour l'appel d'une méthode.
     * Récupère les informations d'invokation via un noeud et une liste de valeurs indexées
     * L'héritage n'est pris en compte que pour les méthodes, précédemment ransmises aux objets
     * @param objectName    Nom de l'objet
     * @param methodName    Nom de la méthode
     * @param invokerUseAs    Noeud d'invocation (contient un noeud de la forme "<Invokation><Parameter ident ={valeur renvoyant à {parametersWith}} useAs = {Valeur retrouvée dans le wrapper suivant}></Parameter>...</Invokation>"
     * @param parametersWith    Carte contenant les valeurs, indexées par la clé, qui correspond au @ident des paramètres dans le noeud
     * @return    Carte contenant l'identifiant, les informations, la valeur instanciée du paramètre ainsi que son ordre (clé : order#6D#). Indexé par useAs depuis l'appelant.
     * @throws ClassNotFoundException    Si la valeur instanciée du tableau de paramètre ne correspond à aucun type JAVA ou si la valeur du @type du wrapper est fausse
     * @throws ParserConfigurationException    Renvoyé par la fonction {xml_wildNodeList()} pour traitement du noeud {invokerUseAs}
     * @throws IOException    Renvoyé par la fonction {xml_wildNodeList()} pour traitement du noeud {invokerUseAs}
     * @throws SAXException    Renvoyé par la fonction {xml_wildNodeList()} pour traitement du noeud {invokerUseAs}
     * @throws XPathExpressionException    Renvoyé par la fonction {xml_wildNodeList()} pour traitement du noeud {invokerUseAs}
     */
    public HashMap<String,Object> getInvokation(String objectName, String methodName, Node invokerUseAs, HashMap<String,Object> parametersWith) throws ClassNotFoundException, XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        HashMap<String,Object> toRet = null ;
        if(methodName != null )toRet = diceMethodWrapper.get(objectName).get(methodName).getInvokation(invokerUseAs, parametersWith) ;
        if(toRet != null && !toRet.isEmpty()) return toRet ;
        if(objectName != null )toRet = diceBrickWrapper.get(objectName).getInvokation(invokerUseAs, parametersWith) ;
        if(toRet != null && !toRet.isEmpty()) return toRet ;
        return null ;
    }

    /**
     * Récupère la liste des paramètres, avec les informations par défaut et les types corrigés, pour la construction d'un objet.
     * Récupère les informations d'invokation via un noeud et une liste de valeurs indexées
     * @param objectName    Nom de l'objet
     * @param invokerUseAs    Noeud d'invocation (contient un noeud de la forme "<Invokation><Parameter ident ={valeur renvoyant à {parametersWith}} useAs = {Valeur retrouvée dans le wrapper suivant}></Parameter></Invokation>"
     * @param parametersWith    Carte contenant les valeurs, indexées par la clé, qui correspond au @ident des paramètres dans le noeud
     * @return    Carte contenant l'identifiant, les informations, la valeur instanciée du paramètre ainsi que son ordre (clé : order#6D#). Indexé par useAs depuis l'appelant.
     * @throws ClassNotFoundException    Si la valeur instanciée du tableau de paramètre ne correspond à aucun type JAVA ou si la valeur du @type du wrapper est fausse
     * @throws ParserConfigurationException    Renvoyé par la fonction {xml_wildNodeList()} pour traitement du noeud {invokerUseAs}
     * @throws IOException    Renvoyé par la fonction {xml_wildNodeList()} pour traitement du noeud {invokerUseAs}
     * @throws SAXException    Renvoyé par la fonction {xml_wildNodeList()} pour traitement du noeud {invokerUseAs}
     * @throws XPathExpressionException    Renvoyé par la fonction {xml_wildNodeList()} pour traitement du noeud {invokerUseAs}
     */
    public HashMap<String,Object> getInvokation(String objectName, Node invokerUseAs, HashMap<String,Object> parametersWith) throws ClassNotFoundException, XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        return getInvokation(objectName, null, invokerUseAs, parametersWith) ;
    }

    // Méthodes {getResources} de récupération des ressources minimales requises pour l'exécution ou l'instanciation d'un élément
    // La résolution des calculs se fait depuis le wrapper
    /**
     * Evalue les ressources minimales requises pour l'exécution ou l'instanciation d'un élément (méthode, objet si le nom de méthode n'est pas renseigné, générique si rien n'est renseigné)
     * L'héritage n'est pris en compte que pour les méthodes, précédemment ransmises aux objets
     * @param objectName    Nom de l'objet
     * @param methodName    Nom de la méthode
     * @return    Carte contenant les valeur de minCPU, minRAM, minROM
     */
    public HashMap<String,Integer> getResources(String objectName, String methodName) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        HashMap<String,Integer> toRet = null ;
        if(methodName != null && diceMethodWrapper!=null)toRet = diceMethodWrapper.get(objectName).get(methodName).getResources() ;
        if(toRet != null && !toRet.isEmpty()) return toRet ;
        if(objectName != null && diceBrickWrapper!=null)toRet = diceBrickWrapper.get(objectName).getResources() ;
        if(toRet != null && !toRet.isEmpty()) return toRet ;
        if(configWrapper!=null && configWrapper!=null)toRet = configWrapper.getResources() ;
        if(toRet != null && !toRet.isEmpty()) return toRet ;
        return null ;
    }

    /**
     * Evalue les ressources minimales requises pour l'exécution ou l'instanciation d'un élément (objet, générique le nom d'objet nest pas renseigné)
     * @param objectName    Nom de l'objet
     * @return    Carte contenant les valeur de minCPU, minRAM, minROM
     */
    public HashMap<String, Integer> getResources(String objectName) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        return getResources(objectName, null);
    }

    /**
     * Evalue les ressources minimales requises pour l'instanciation générique du modèle
     * @return    Carte contenant les valeur de minCPU, minRAM, minROM
     */
    public HashMap<String,Integer> getResources() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        return getResources(null, null);
    }

    /**
     * Renvoie le noeud de retour d'un objet ou d'un composant
     * @param eltName Nom du sous élément
     * @param subEltIdx    Numéro d'occurence de l'objet ou du composant
     * @return {Mode parallèle, Nom du modèle, Nom de l'élément (@ident), Noeud d'appel}
     */
    public Object[] getReturn(String eltName, Integer subEltIdx){
        return diceBrickWrapper.get(eltName).getReturn(subEltIdx) ;
    }

    /**
     * Renvoie le noeud de retour d'une méthode dans un composant
     * @param eltName Nom du sous élément
     * @param subEltIdx    Numéro d'occurence de l'objet
     * @subMethIdx subEltIdx    Numéro d'occurence de la méthode
     * @return {Nom de la méthode (@ident), Noeud d'appel}
     */
    public Object[] getReturn(String eltName, Integer subEltIdx, Integer subMethIdx){
        return diceBrickWrapper.get(eltName).getReturn(subEltIdx,subMethIdx) ;
    }

    /**
     * Nombre de sous éléments directs contenus
     * @param eltName Nom du sous élément
     * @return    Nombre de méthodes contenues dans un objet, pour un composant
     */
    public Integer get_numContains(String eltName){
        return diceBrickWrapper.get(eltName).get_numContains() ;
    }

    /**
     * Nombre de méthodes contenues dans un objet, pour un composant
     * @param eltName Nom du sous élément
     * @param subEltIdx    Numéro d'occurence de l'objet
     * @return    Nombre de méthodes contenues dans un objet, pour un composant
     */
    public Integer get_numContains(String eltName,Integer subEltIdx){
        return diceBrickWrapper.get(eltName).get_numContains(subEltIdx) ;
    }

    /**
     * Récupération d'une propriété de configuration ("Parameter")
     * @param propertyName
     * @return
     */
    public <T> T getProperty(String propertyName){
        @SuppressWarnings("unchecked")
        T toRet = (T) configWrapper.getParameter(propertyName);
        if(toRet==null) toRet = wmList.getProperty(propertyName);
        return toRet ;
    }

    /**
     * Récupération d'un wrapper
     * @param subWrapper
     * @return
     */
    public HashMap<Integer,Node> getSub(String subWrapper){
        return getSub(subWrapper,null);
    }

    /**
     * Récupération d'un noeud à l'intérieur d'un Wrapper
     * @param subWrapper Identifiant du wrapper (nom d'objet)
     * @param subWrapper2    Identifiant du noeud
     * @return
     */
    public HashMap<Integer,Node> getSub(String subWrapper,Integer subWrapper2){
        if(subWrapper2 == null){
            return diceBrickWrapper.get(subWrapper).getListNode() ;
        }else{
            return diceBrickWrapper.get(subWrapper).getListNode(subWrapper2) ;
        }
    }

    // Accesseurs aux noeuds de sous éléments pour composants et scenarios
    /**
     * Renvoie le noeud d'invokation d'un objet ou d'un composant
     * @param eltName Nom du sous élément
     * @param subEltIdx    Numéro d'occurence de l'objet ou du composant
     * @return {Mode parallèle, Nom du modèle, Nom de l'élément (@ident), Noeud d'appel}
     */
    public HashMap<String,HashMap<String,Object>> getProducts(String eltName){
        return diceBrickWrapper.get(eltName).getProducts() ;
    }

    /**
     * Récupération de la description d'une étape
     * @param arrayWilds    tableau de généalogie {nom du modèle, nom de l'objet, nom de la méthode}
     * @param ident    Numéro d'identification de l'étape
     * @return
     */
    public Object[] getStep(String[] arrayWilds, Integer ident){
        Object[] toRet = null ;
        switch(arrayWilds.length){
        case 3 : toRet = diceMethodWrapper.get(arrayWilds[1]).get(arrayWilds[2]).getStep(ident) ; // Nom de modèle + nom d'objet
                if(toRet!=null && toRet.length>0)return toRet ;
                break;
        case 2 : toRet = diceBrickWrapper.get(arrayWilds[1]).getStep(ident) ; // Nom de modèle + nom d'objet
                if(toRet!=null && toRet.length>0)return toRet ;
                List<String> inherit = getInheritance(arrayWilds[1]) ; // Recherche de l'exception pour chaque objet parent
                for(String iName:inherit){
                        toRet = getStep(new String[]{modelName,iName},ident) ;
                        if(toRet != null && toRet.length>0) return toRet ;
                }
        }
        return null;
    }

    public String getJarPath() {
        return jarPath;
    }
}