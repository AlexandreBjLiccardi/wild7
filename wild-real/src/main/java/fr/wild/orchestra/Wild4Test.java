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

import static fr.wild.common.IoWilds.config_getTab;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Classe spécifique créée pour tests de packages
 * Correspond à une version simplifiée de WildExecList
 *
 * Cette classe permet le test d'une méthode d'un objet disposant d'un fichier WILD,
 * dont le fichier WILD est déposé dans [RépertoireDesModèles]/[NomDuModèle]/[NomDeLObjet].wild
 *
 * Le passage des paramètre se fait
 *
 * @author alexandre.liccardi
 * @version [ab]
 *
 */
public class Wild4Test {

    private WildModelList service_DiceModelList ; // Ensemble des éléments WILD utilisables par l'application
    private final LinkedHashMap<String, Object> config_table = config_getTab() ; // Liste des paramètres de configuration et leur valeur, pour éviter les consultations du fichier de configuration.
    private WildLogger diceLogger_loop ; // Log4j pour execList

    private String userId = "TestUser";
    private String className = "Wild4Test";
    private String executionId = "TEST_SERVICE" ;

    private HashMap<String,WildObject> object4Test = null;

    /**
     * Getters
     * @return
     */
    public String getClassName() {
        return className;
    }
    public String getExecutionId() {
        return executionId;
    }
    public String getUserId() {
        return userId  ;
    }

    /**
     * Constructeur    (et exécuteur)
     * @throws Exception
     */
    public Wild4Test() throws Exception{
        System.out.println("WILD service starts...");
        // Définition de propriétés d'exécution
        System.setProperty("log4j.configurationFile", "configuration/log4j2.xml");
        // Construction du Logger à partir de la classe de test. Les valeurs sont instanciées par défaut, userId = "TestUser", className = "Wild4Test", executionId = "TEST_SERVICE"
        diceLogger_loop = new WildLogger(this);
        // Construction du modèle d'instanciation WILD pour appel par API reflection.
        // Ne doit pas instancier les scenarios et les components.
        flushModel();
        System.out.println("WILD service started.");
    }

    private WildObject getWildObject(String objectModel, String objectName, Object[] callParameters) throws Exception{
    	WildObject wO = new WildObject(
                new String[]{getUserId(),getExecutionId(),getClassName(),getClassName()},
                service_DiceModelList,
                objectModel,
                objectName,
                callParameters);
    	wO.DVP_use4Test();
         return wO;
    }

    private Object executeWildMethod(WildObject wildObject, String methodName, Object[] callParameters) throws Exception{
        return wildObject.executeMethod(methodName,callParameters);
    }

    /**
     * Création des modèles DICE
     * Crée et stocke un WildModelList, ce modèle est appelé par l'ensemble des scénarios pour ne pas être rechargé depuis le XML.
     * @throws Exception
     */
     public void flushModel() throws Exception{
        writeLog("FlushAttempt");
        try {
            service_DiceModelList = new WildModelList(this.diceLogger_loop);
        } catch (Exception e) {
            this.diceLogger_loop.logEvent("FlushFailure");
            e.printStackTrace();
        }
        writeLog("FlushSuccess");
     }

    /**
     * Ecriture de log
     * @param Source    Information de provenance du wrapper
     * @throws Exception
     */
    private void writeLog(String Source) throws Exception{
        diceLogger_loop.logEvent(Source);
    }

    /**
     * Exécution d'une méthode et récupération de la valeur
     * @param eName    Identifiant de l'objet à appeler
     * @param mName    Nom de la méthode à appeler
     * @param callParameters4Method    Paramètres d'exécution de la méthode
     */
    @SuppressWarnings("unchecked")
    public void executeMethod(String eName, String mName, Object[] callParameters4Method) {
        try {
            executeWildMethod(object4Test.get(eName), mName, callParameters4Method);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Exécution d'une méthode et récupération de la valeur
     * @param eName    Identifiant de l'objet à appeler
     * @param mName    Nom de la méthode à appeler
     */
    public void executeMethod(String eName, String mName) {
        try {
            executeWildMethod(object4Test.get(eName), mName, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Exécution d'une méthode et récupération de la valeur
     * @param eName    Identifiant de l'objet à appeler
     * @param mName    Nom de la méthode à appeler
     * @param callParameters4Method    Paramètres d'exécution de la méthode
     * @return    Valeur instanciée
     */
    @SuppressWarnings("unchecked")
    public <T> T getFromMethod(String eName, String mName, Object[] callParameters4Method) {
        try {
            return (T) executeWildMethod(object4Test.get(eName), mName, callParameters4Method);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Exécution d'une méthode et récupération de la valeur
     * @param eName    Identifiant de l'objet à appeler
     * @param mName    Nom de la méthode à appeler
     * @return    Valeur instanciée
     */
    @SuppressWarnings("unchecked")
    public <T> T getFromMethod(String eName, String mName) {
        try {
            return (T) executeWildMethod(object4Test.get(eName), mName, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Méthode d'instanciation et d'ajout d'un nouvel objet à la liste des objets disponibles
     * @param eName    Nom de "rangement" (= Identifiant) de l'objet à instancier
     * @param objectModel    Nom du modèle de l'objet à créer
     * @param objectName    Nom de l'objet à créer
     * @param callParameters    Liste des valeurs de paramètres d'instanciation
     */
    public void addObject(String eName, String objectModel, String objectName, Object[] callParameters) {
        // Initialisation de la liste
        if(object4Test==null)object4Test = new HashMap<>();
        // Ajout de la nouvelle entrée
        try {
            object4Test.put(eName, getWildObject(objectModel, objectName, callParameters) );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode d'instanciation et d'ajout d'un nouvel objet à la liste des objets disponibles
     * @param eName    Nom de "rangement" de l'objet à instancier
     * @param objectModel    Nom du modèle de l'objet à créer
     * @param objectName    Nom de l'objet à créer
     */
    public void addObject(String eName, String objectModel, String objectName) {
        addObject(eName, objectModel, objectName, null);
    }

    /**
     * Suppression d'un objet à disposition
     * @param string
     */
    public void free(String string) {
        if(object4Test!=null)object4Test.remove(string);
    }

    /**
     * Renvoie l'instance d'une classe WILD, préalablement ajoutée à la liste
     * @param eName    Idenitfiant de l'objet
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getObject(String eName) {
        return (T) this.object4Test.get(eName).getCurrentObjectInstance();
    }
    
    /**
     * Récupération d'une valeur d'outPut associée à un objet
     * @param oName    Identifiant de l'objet à appeler (peut être le dernier élément de la chaîne, après le "." de l'objet)
     * @param outpName    Nom de l'output à ramener
     * @return    Valeur instanciée
     */
    @SuppressWarnings("unchecked")
    public <T> T getFromOutput(String eName, String outpName) {
        try {
        	HashMap<String, Object> toRet = null;
        	LinkedHashMap<String, HashMap<String, Object>> var = object4Test.get(eName).getOutputs();
        	for(String var_key:var.keySet())if(var_key.endsWith("."+outpName)) toRet = var.get(var_key);          	
        	if(toRet==null) toRet = var.get(outpName); 
        	if(toRet!=null)return (T) toRet.get("value");
        	return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /** 
	 * Récupération de la liste d'erreur rencontrées associées à un objet
	 * @param eName    Idenitfiant de l'objet     
	 */
    public HashMap<String,HashMap<String,String>> getErrorList(String eName){
    	return this.object4Test.get(eName).getCurrentObjectInstance().getErrorList();
    }
    /** 
	 * Récupération d'un booléen mentionnant si au moins une erreur existe
	 * @param eName    Idenitfiant de l'objet     
	 */
    public Boolean isError(String eName){
    	if(getErrorList(eName)==null)return false ;
    	return this.object4Test.get(eName).getCurrentObjectInstance().isError();
    }
}