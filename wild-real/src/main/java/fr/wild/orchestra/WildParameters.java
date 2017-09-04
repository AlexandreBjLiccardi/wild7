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

import static fr.wild.common.IoCommons.cast_xmlEscape;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * PAramètres mobilisables par les briques, notamment pour le passage d'une brique à une autre
 * @author alexandre.liccardi
 * @version [ab]
 */

public class WildParameters {
    
    protected ConcurrentHashMap<String,Object> parametersWith = new ConcurrentHashMap<String,Object>(); // Liste colntenant les valeurs elles-mêmes

    /**
     * Liste de paramètres
     * @return
     */
    public synchronized ConcurrentHashMap<String, Object> getParameters() {
        return parametersWith ;
    }

    /**
     * "Put" de HashMap
     * @param key
     * @param value
     */
    public synchronized void put(String key, Object value){
        parametersWith.put(key, value);
    }

    /**
     * "Get" de HashMap
     * @param key
     * @param value
     */
    @SuppressWarnings("unchecked")
    public synchronized HashMap<String, Object> get(String k) {
        return (HashMap<String, Object>) parametersWith.get(k);
    }

    /**
     * Renvoie les variables contenues sous forme de chaîne de caractères lisibles par un être humain
     * @return
     */
    @SuppressWarnings("unchecked")
    public synchronized String getString(){
        Set<String> ks =  parametersWith.keySet();
        String parametersInitString = null ;
        if(ks.size()>0){
            parametersInitString = "<callParameters>";
            for(String k:ks){
                parametersInitString += "<callParameter ident=\""+k+"\""
                    + " value = \""+cast_xmlEscape(((HashMap<String, Object>) parametersWith.get(k)).get("value"))+"\""
                    + " type = \""+cast_xmlEscape(((HashMap<String, Object>) parametersWith.get(k)).get("type"))+"\"";
                String descr = cast_xmlEscape((String) ((HashMap<String, Object>) parametersWith.get(k)).get("description")) ; 
                if(descr!=null&&descr.length()>0) parametersInitString += " description = \""+descr+"\"";
                parametersInitString += "/>";
            }
            parametersInitString += "</callParameters>";
        }
        return parametersInitString;
    }

    /**
     * Suppression d'une entrée
     * @param kStr
     */
    public synchronized void remove(String kStr) {
        parametersWith.remove(kStr);
    }

    /**
     * Liste des clés
     * @return
     */
    public synchronized Set<String> keySet() {
        return parametersWith.keySet();
    }

    /**
     * Affiche le contenu de la liste de variables
     */
    public void show() {
        for(String k:parametersWith.keySet())System.out.println(k+":"+parametersWith.get(k));
    }

    /**
     * Renvoie la liste de variables, à un format de Map hors cas de synchronisation (plus léger)
     * @return
     */
    public HashMap<String, Object> getParameters_hash() {
        return new HashMap<String, Object>(parametersWith);
    }

    public void putNext(String initKey, Object getRetObj) {
        Integer i=1;
        String newKey = initKey +"["+(i++)+"]" ;
        while(parametersWith.keySet().contains(newKey))newKey =  initKey +"["+(i++)+"]" ;
        this.put(newKey, getRetObj);
    }
    
}