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

import static fr.wild.common.IoCommons.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.w3c.dom.Node;
import static fr.wild.common.IoWilds.conf_translate;



/**
 * Elément d'exécution "Object" (classe d'objet JAVA)
 * Correspond à une classe JAVA existante sous forme de code dans son répertoire modèle (par exemple "real"), sous forme de fichier indépendant.
 * @author    alexandre.liccardi
 * @version [ab]
 */
public class WildObject extends WildBrick{

    /**
     * Constructeur
     * @param i_diceNode    Noeud d'appel (issu du composant d'appel)
     * @param i_prevRunnable    Elément parent (un WildComponent)
     * @throws Exception
     */
    public WildObject(Node i_diceNode, WildBrick i_prevRunnable) throws Exception  {
        initialize(null, null, i_prevRunnable,i_diceNode);
        String[] names = diceName.split("\\.");
        objectName = names[1] ;
        modelName = names[0] ;
        objectClass = Class.forName("fr.wild."+modelName+"."+objectName,true, wildModelList.getWildClassLoader());
    }

    /**
     * Constructeur directement à partir du modèle, pour exécution via Wild4Test
     * @param wildObjectNode
     * @param service_DiceModelList
     * @throws Exception
     */
    public WildObject(Node i_diceNode, String[] wildTokens, WildModelList i_diceModelList) throws Exception {
        initialize(i_diceModelList, wildTokens, null,i_diceNode);
        String[] names = diceName.split("\\.");
        objectName = names[1] ;
        modelName = names[0] ;
        objectClass = Class.forName("fr.wild."+modelName+"."+objectName,true, wildModelList.getWildClassLoader());
    }


    /**
     * Constructeur directement à partir du modèle, pour exécution via Wild4Test
     * @param strings
     * @param service_DiceModelList
     * @param objectModel
     * @param objectName2
     * @param callParameters
     * @throws Exception
     */
    public WildObject(String[] wildTokens, WildModelList service_DiceModelList,
        String objectModel, String objectName2,
        Object[] callParameters) throws Exception {
        // Définition des variables propres aux WildObjects
        diceName = objectName2;
        objectName = objectName2 ;
        modelName = objectModel ;
        // Définition de la liste de modèles
        wildModelList = service_DiceModelList ;
        // Construction du token
        wildToken = new WildToken(wildTokens,new String[]{objectModel,diceName});
        // Vérification des dossiers d'exécution
        execFolder = conf_translate((String)wildModelList.getProperty("execution_path"), this) ;
        execTempFolder = conf_translate((String)wildModelList.getProperty("execution_temp_path"), this) ;
        // Passage du Logger
        wildLogger = new WildLogger(this);
        // Sélection de la classe
        objectClass = Class.forName("fr.wild."+modelName+"."+objectName);
        // Passage des paramètres selon le cast WILD
        Object[] addedCallParameters ;
        if(callParameters!=null){
            addedCallParameters = new Object[callParameters.length+1];
            addedCallParameters[0] = this;
            System.arraycopy(callParameters, 0, addedCallParameters, 1, callParameters.length);
        }else{
            addedCallParameters = new Object[]{this};
        }
        // Instanciation de l'objet
        currentObjectInstance = (WildClass) getConstructor(objectClass,addedCallParameters).newInstance(addedCallParameters);
    }



    /**
     * Identification du meilleur constructeur.
	 *
     * @param modelName    nom du modèle dans fr.onema.wild
     * @param objectName    nom de l'objet dans fr.onema.wild.{modelName}
     * @param callers    Tableau des objets servant à l'invocation
     * @return    "meilleur" constructeur, fonctionnel pour JAVA
     * @throws SecurityException
     * @throws ClassNotFoundException
     */
    private static Constructor<?> getConstructor(Class clazz, Object[] callers) throws SecurityException, ClassNotFoundException{
        final Constructor<?>[] list = clazz.getConstructors();

		loop:
        for(Constructor<?> canBeRet:list){
            if (canBeRet.getParameterTypes().length == callers.length) {
				for(int i=0; i<callers.length; i++) {
					if (callers[i]!=null && !canBeRet.getParameterTypes()[i].isInstance(callers[i])) {
						continue loop;
					}
				}
				return canBeRet ;
			}
        }
        return null;
    }


    /**
     * Passage des paramètres pour actualisation depuis un parents, en sortie de méthode etc...
     * Filtré selon le modèle, avec balises "Invokation"
     * @param i_parametersWith    Tableau de paramètre contenant la liste des paramètres et leurs valeurs
     * @throws Exception
     */
    public void setParameters(HashMap<String,Object> i_parametersWith) throws Exception{
        if(diceNode!=null){
            HashMap<String, Object> parametersWith2 = (wildModelList.getModel(modelName).getInvokation(objectName, xml_getFirstNode(diceNode,"./Invokation"), i_parametersWith));
            if(parametersWith2!=null)for(String k:parametersWith2.keySet())parametersWith.put(k, parametersWith2.get(k));
        }else{
            for(String k:i_parametersWith.keySet())parametersWith.put(k, i_parametersWith.get(k));
        }

    }

    /**
     * Passage des paramètres pour actualisation depuis un parents, en sortie de méthode etc...
     * Filtré selon le modèle, avec balises "Invokation"
     * @param i_parametersWith    Tableau de paramètre contenant la liste des paramètres et leurs valeurs
     * @throws Exception
     */
    public void setParameters(ConcurrentHashMap<String,Object> i_parametersWith) throws Exception{
        setParameters(new HashMap<String,Object>(i_parametersWith));
    }

    /**
     * Passage des paramètres pour actualisation depuis un parents, en sortie de méthode etc...
     * Filtré selon le modèle, avec balises "Invokation"
     * @param i_parametersWith    Tableau de paramètre contenant la liste des paramètres et leurs valeurs
     * @throws Exception
     */
    public void setParameters(LinkedHashMap<String,Object> i_parametersWith) throws Exception{
         setParameters(new HashMap<String,Object>(i_parametersWith));
    }

    /**
     * Instanciation d'une classe, par réflexion.
     * L'objet est conservé et passé aux méthodes d'appel
     * @throws Exception
     */
    protected synchronized void execute_beforeLoop() throws Exception{
        HashMap<String,Object> parametersWith_lk = new HashMap<String,Object>();
        for(String k:parametersWith.keySet())parametersWith_lk.put(k, parametersWith.get(k));
        Object[] callers = cast_map2Array(this,parametersWith_lk,true);
        currentObjectInstance = (WildClass) getConstructor(objectClass,callers).newInstance(callers);
    }

    /**
     * Après avoir exécuté les sous éléments, l'objet est détrut pour préserver la ressource système.
     * On conserve une copie des outputs.
     */
    protected void execute_afterLoop() throws Exception{
        saveOutputs();
        currentObjectInstance.WILD_drop();
    }

    private void saveOutputs(){
        outputs = getOutputs() ;
    }

    /**
     * Récupération des "outputs" variables directement transmises des objets instanciés aux scénarios
     *  @return Liste et valeur des variables
     */
    public synchronized  LinkedHashMap<String, HashMap<String, Object>> getOutputs(){
        if(currentObjectInstance!=null){
            String outputNames_suff = (parentDiceBrick!=null) ? diceName+"["+parentDiceBrick.getLastElement()+"].":diceName+"." ;
            LinkedHashMap<String, HashMap<String, Object>> toRet = new LinkedHashMap<String, HashMap<String, Object>>();
            if(currentObjectInstance.WILD_getOutputs()!=null&&!currentObjectInstance.WILD_getOutputs().isEmpty())
                for(String k:currentObjectInstance.WILD_getOutputs().keySet())toRet.put(outputNames_suff+k, currentObjectInstance.WILD_getOutputs().get(k));
            return toRet ;
        }
        return outputs ;
    }

    /**
     * Renvoie une liste de tableaux, décrivant l'état d'avancement des exécutions pour l'élément et ses sous-éléments
     * Utilise les étapes ("Steps") mentionnées dans les WILDS.
     */
    public List<Object[]> getStep(){
        List<Object[]> toRet = new ArrayList<Object[]>();
        if(currentLastSubElements==null||currentLastSubElements.size()==0||currentObjectInstance==null){ // this.currentObjectInstance ne peut être renvoyé avant la fin de l'exécution du constructeur !
            Object[] msgErr = this.getStep(0) ;
            String msg = (msgErr==null)?null:(String)msgErr[3];
            toRet.add(new Object []{"WildObject", this.diceName, null, msg, 0, subList.size()});
        }
        for(Integer cE:currentLastSubElements){
            toRet.add(new Object []{"WildObject", this.diceName, null, null, cE, subList.size()});
            toRet.addAll(subList.get(cE).getStep()); // Appel récursif des sous éléments
        }
        return toRet ;
    }
    
    /**
     * Force l'exécution d'une méthode, pour appel direct depuis Wild4Test
     * @param methodName
     * @param callParameters
     * @return
     * @throws Exception
     */
    public Object executeMethod(String methodName, Object[] callParameters) throws Exception {
        WildMethod method4Exec = new WildMethod(methodName,this);
        method4Exec.setCurrentObjectInstance(this.getCurrentObjectInstance());
        method4Exec.execute_beforeLoop(callParameters);
        return method4Exec.getReceiver();
    }
    /**
     * Indique si l'objet est instancié pour test, ou dans une chaîne de briques
     */
	public void DVP_use4Test() {
		this.WILD_4Test = true ;		
	}

}