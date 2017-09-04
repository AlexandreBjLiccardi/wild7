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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.w3c.dom.Node;

/**
 * Elément d'exécution "Méthode" (Méthode d'objet)
 * Correspond à une méthode JAVA existante sous forme de code dans son répertoire modèle (par exemple "real"), à l'intérieur d'un fichier objet.
 * @author    alexandre.liccardi
 * @version [ab]
 */
public class WildMethod extends WildBrick {


    /**
     * Constructeur
     * @param i_diceNode    Noeud d'appel (issu du composant d'appel)
     * @param i_prevDiceRunnable Elément parent (un WildComponent)
     * @throws Exception
     */
    public WildMethod(Node i_diceNode, WildBrick i_prevDiceRunnable) throws Exception  {
        initialize(null, null, i_prevDiceRunnable,i_diceNode);
    }

    public WildMethod(String methodName, WildObject i_prevDiceRunnable) throws Exception {
    	setName(methodName);
        this.initialize(null, null, i_prevDiceRunnable, null);
    }

    /**
     * Passage des paramètres pour actualisation depuis un parents, en sortie de méthode etc...
     * Filtré selon le modèle, avec balises "Invokation"
     * @param i_parametersWith    Tableau de paramètre contenant la liste des paramètres et leurs valeurs
     * @throws Exception
     */
    public void setParameters(ConcurrentHashMap <String,Object> i_parametersWith) throws Exception{
        String[] names = this.getArrayWilds()[2].split("\\.");
        String objectName = names[1] ;
        String modelName = names[0] ;
        HashMap<String,Object> parametersWith_lk = new HashMap<String,Object>();
        for(String k:i_parametersWith.keySet())parametersWith_lk.put(k, i_parametersWith.get(k));
        setParameters(wildModelList.getModel(modelName).getInvokation(objectName,diceName, xml_getFirstNode(diceNode,"./Invokation"), parametersWith_lk));
    }

    /**
     * Execution de chaque méthode, par réflexion
     * Utilise l'objet passé depuis la classe parent
     * @throws Exception
     */
    protected synchronized void execute_beforeLoop() throws Exception{
        execute_beforeLoop(null);
    }

    protected synchronized void execute_beforeLoop(Object[] i_callers) throws Exception{
        Object[] callers = null ;
        if(i_callers==null){
			HashMap<String,Object> parametersWith_lk = parametersWith.getParameters_hash();
            callers = cast_map2Array(parametersWith_lk,true); // Conversion des paramètres d'appel en paramètres de méthode
        }else{
            callers = i_callers;
        }
        Method a_mLaunch = null ;
        Class toCall = Class.forName("fr.wild."+getModelName()+"."+wildModelList.getModel(getModelName()).getMethodFirstObject(parentDiceBrick.getName(),this.diceName));
        Class[] castedCallers = null;
        try{
	        castedCallers = cast_array2ClassArray(callers, this.wildClassLoader);
	        a_mLaunch = toCall.getMethod(diceName,castedCallers);
        }catch(NoSuchMethodException e1){
        try{
        	castedCallers = cast_array2ClassArray(true,callers, this.wildClassLoader);
	        a_mLaunch = toCall.getMethod(diceName,castedCallers);
        }catch(NoSuchMethodException e2){
        	Method[] allMethods = toCall.getDeclaredMethods();
            for (Method m : allMethods) {
                String mname = m.getName();
                if(mname.equals(diceName)&&m.getParameterTypes().length==castedCallers.length){
                	a_mLaunch = m;
                	break;
                }
             }
        }}
        receiver = a_mLaunch.invoke(currentObjectInstance, callers); // Appel de la méthode par réflexion
    }

    /**
     * Renvoie une liste de tableaux, décrivant l'état d'avancement des exécutions pour l'élément et ses sous-éléments
     * Utilise les étapes ("Steps") mentionnées dans les WILDS.
     */
    public List<Object[]> getStep(){
        List<Object[]> toRet = new ArrayList<Object[]>();
        Object[] step = wildModelList.getStep(getArrayWilds(), currentObjectInstance.WILD_getStepProgress(parentDiceBrick.getLastElement())); // Commande propre au type d'élément appelé
        if(step!=null)toRet.add(new Object []{"WildMethod", this.diceName, step[0], step[1]}); // Appel récursif des sous éléments
        else toRet.add(new Object []{"WildMethod", this.diceName, null, null});
        return toRet ;
    }

    /**
     * Récupération de l'objet instancié par la dernière exécution de la dernière méthode
     * @return
     */
    public Object getReceiver(){
        return this.receiver;
    }

}