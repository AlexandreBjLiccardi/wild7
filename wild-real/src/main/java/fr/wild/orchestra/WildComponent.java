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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;
import org.w3c.dom.Node;


/**
 * Elément d'exécution "Component" (composant)
 * Correspond à une série d'appels d'objets et de méthode, correspond à un "composant" ETL
 * @author    alexandre.liccardi
 * @version [ab]
 */
public class WildComponent extends WildBrick{

    /**
     * Constructeur
     * @param i_diceNode    Noeud d'appel (issu du scénario d'appel)
     * @param i_prevDiceRunnable    Elément parent (un DiceScénario)
     * @throws Exception
     */
    public WildComponent(Node i_diceNode,WildBrick i_prevDiceRunnable) throws Exception  {
        initialize(null, null, i_prevDiceRunnable, i_diceNode );
    }

    /**
     * Passage des paramètres pour actualisation depuis un parent, en sortie de méthode etc...
     * Filtré selon le modèle, avec balises "Invokation"
     * @param i_parametersWith    Tableau de paramètre contenant la liste des paramètres et leurs valeurs
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public synchronized void setParameters(HashMap<String,Object> i_parametersWith) throws Exception{
        Node test = xml_getFirstNode(diceNode,"./Invokation") ;
        if(test!=null){
            HashMap<String, Object> param_invoke = (getModel().getInvokation(diceName, test, i_parametersWith));
            if(i_parametersWith!=null) for(String k:param_invoke.keySet())parametersWith.put(k, param_invoke.get(k));
        }else if(i_parametersWith!=null)for(String k:i_parametersWith.keySet())parametersWith.put(k, i_parametersWith.get(k));

        setParametersInitString();
    }

    /**
     * Passage des paramètres pour actualisation depuis un parent, en sortie de méthode etc...
     * Filtré selon le modèle, avec balises "Invokation"
     * @param i_parametersWith    Tableau de paramètre contenant la liste des paramètres et leurs valeurs
     * @throws Exception
     */
    public synchronized void setParameters(ConcurrentHashMap<String,Object> i_parametersWith) throws Exception{
         setParameters(new HashMap<String,Object>(i_parametersWith));
    }

    /**
     * Passage des paramètres pour actualisation depuis un parent, en sortie de méthode etc...
     * Filtré selon le modèle, avec balises "Invokation"
     * @param i_parametersWith    Tableau de paramètre contenant la liste des paramètres et leurs valeurs
     * @throws Exception
     */
    public synchronized void setParameters(LinkedHashMap<String,Object> i_parametersWith) throws Exception{
         setParameters(new HashMap<String,Object>(i_parametersWith));
    }

    /**
     * Renvoie l'attribut signifiant si l'élément peut être exécuté en parallèle d'un autre (mode multiThread) ou pas
     * Pour la série "Ambivalent", seuls les scénarios sont multithread
     * @return TRUE si parallélisable
     */
    public synchronized boolean allowParallel() {
        return (boolean) wildModelList.getInfo(getArrayWilds(), "allowParallel");
    }

}