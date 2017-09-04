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

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.wild.hibernate.ExecutionArchive;
import fr.wild.hibernate.ExecutionChainsaw;
import fr.wild.orchestra.WildBrick;
import static fr.wild.common.IoCommons.*;

/**
 * Classe d'accès aux fichiers spécifiques WILD
 * @author alexandre.liccardi
 * @version 0.1a "Ambiant Ambivalence"
 */
public class IoWilds {

    /**
     * config_getTab renvoie une table contenant tous les paramètres définis dans le fichier de configuration
     * Fichier nécessairement dans "configuration/config.6conf"
     * @return Map (Identifiant du Paramètre, Valeur du paramètre (type donné par #@type")
     */
    public synchronized static LinkedHashMap<String,Object> config_getTab(){
        try{
            LinkedHashMap<String,Object> toRet = xml_getIndexedValues(xml_getFirstNode("configuration/config.6conf","//Configuration"), "Parameter", "ident", "type");
            NodeList nL = xml_getNodes("configuration/config.6conf","//Configuration/Parameter[Choice]");
            for(int i =0 ; i<nL.getLength();i++){
                NodeList nL2 = xml_getNodes(nL.item(i),"Choice");
                LinkedHashMap<String,Object> params1 = new LinkedHashMap<String,Object>();
                for(int j =0 ; j<nL2.getLength();j++){
                    LinkedHashMap<String,Object> params = new LinkedHashMap<String,Object>();
                    NamedNodeMap attributesList = nL2.item(j).getAttributes() ;
                    for (int k = 0; k < attributesList.getLength(); k++) params.put(attributesList.item(k).getNodeName(), attributesList.item(k).getNodeValue());
                        params1.put(xml_getFirstValue(nL2.item(j), "@name"), params);
                    }
                    toRet.put(xml_getFirstValue(nL.item(i), "@ident"), params1);
                }
            return toRet ;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

// LECTURE DES MESSAGES ET EXCEPTIONS

    /**
     * "Traduit" les messages embarqués "#6D#" depuis un message configuré
     * @param msg    Message à traduire
     * @param exec    Exécution DICE à utiliser pour récupération des paramètres
     * @return        Message modifié
     */
    public synchronized static String conf_translate(Object msg, ExecutionChainsaw exec){
        return conf_translate(msg, exec, null);
    }

    /**
     * "Traduit" les messages embarqués "#6D#" depuis un message configuré
     * @param msg    Message à traduire
     * @param dR    Brique DICE à utiliser pour récupération des paramètres
     * @return        Message modifié
     */
    public synchronized static String conf_translate(Object msg, WildBrick dR){
        return conf_translate(msg, (ExecutionChainsaw)null, dR);
    }

    /**
     * "Traduit" les messages embarqués "#6D#" depuis un message configuré
     * @param msg    Message à traduire
     * @param dA    Archive d'exécution utilisée pour "traduction"
     * @return        Message modifié
     */
    public synchronized static String conf_translate(Object msg, ExecutionArchive dA){
        String toRet = cast_2String(msg) ;
        if(dA != null){
            toRet = toRet
            .replaceAll("#6D#ScenarioName#6D#", dA.getScenarioName())
            .replaceAll("#6D#UserId#6D#", dA.getUser().getIdUser())
            .replaceAll("#6D#ExecutionId#6D#", dA.getIdExecution())
            .replaceAll("#6D#UserName#6D#", dA.getUser().getUserName())
            .replaceAll("#6D#UserForname#6D#", dA.getUser().getUserForname())
            .replaceAll("#6D#UserFullname#6D#",  dA.getUser().getUserForname()+" "+ dA.getUser().getUserName())
            .replaceAll("#6D#UserMail#6D#", dA.getUser().getUserMail())
            .replaceAll("#6D#JavaServiceId#6D#", dA.getExecutionList().getJavaService().getIdJavaservice())
            .replaceAll("#6D#DateEnd#6D#", cast_2String(dA.getDateArchive()));
        }
        return toRet ;
    }
    
    /**
     * "Traduit" les messages embarqués "#6D#" depuis un message configuré
     * @param msg    Message à traduire
     * @param exec    Exécution DICE à utiliser pour récupération des paramètres
     * @param dR    Brique DICE à utiliser pour récupération des paramètres
     * @return        Message modifié
     */
    public synchronized static String conf_translate(Object msg, ExecutionChainsaw exec, WildBrick dR){
        String toRet = cast_2String(msg) ;
        if(dR != null){
            toRet = toRet
            .replaceAll("#6D#ScenarioName#6D#", dR.getArrayWilds()[0])
            .replaceAll("#6D#ScenarioId#6D#", dR.getTokens()[2])
            .replaceAll("#6D#UserId#6D#", dR.getTokens()[0])
            .replaceAll("#6D#ExecutionId#6D#",dR.getTokens()[1])
        ;
        if( dR.getArrayWilds().length>1)toRet = toRet
                .replaceAll("#6D#ComponentName#6D#", dR.getArrayWilds()[1])
                .replaceAll("#6D#ComponentId#6D#", dR.getTokens()[3]);
        if( dR.getArrayWilds().length>2)toRet = toRet
                .replaceAll("#6D#ObjectName#6D#", dR.getArrayWilds()[2])
                .replaceAll("#6D#ObjectId#6D#", dR.getTokens()[4]);
        if( dR.getArrayWilds().length>3)toRet = toRet
                .replaceAll("#6D#MethodName#6D#", dR.getArrayWilds()[3])
                .replaceAll("#6D#MethodId#6D#", Integer.toString(dR.getLastElement()));
        }
        if(exec != null){
            toRet = toRet
            .replaceAll("#6D#ExecutionId#6D#", exec.getIdExecution())
            .replaceAll("#6D#UserId#6D#", exec.getUser().getIdUser())
            .replaceAll("#6D#UserName#6D#", exec.getUser().getUserName())
            .replaceAll("#6D#UserForname#6D#", exec.getUser().getUserForname())
            .replaceAll("#6D#UserFullname#6D#", exec.getUser().getUserForname()+" "+exec.getUser().getUserName())
            .replaceAll("#6D#UserMail#6D#", exec.getUser().getUserMail())
            .replaceAll("#6D#JavaServiceId#6D#", exec.getJavaService().getIdJavaservice())
            .replaceAll("#6D#DatePost#6D#", cast_2String(exec.getDatePost()))
            ;
        if(exec.getDateBegin() != null){
                toRet = toRet
            .replaceAll("#6D#DateBegin#6D#", cast_2String(exec.getDateBegin())) ;
        }
        if(exec.getDateEnd() != null){
            toRet = toRet
            .replaceAll("#6D#DateEnd#6D#", cast_2String(exec.getDateEnd()));
        }
        }
        return toRet ;
    }
    public synchronized static String conf_translate(Object msg, Object[] tokens, WildBrick dR){
        String toRet = cast_2String(msg) ;
        if(dR != null){
            toRet = toRet
            .replaceAll("#6D#ScenarioName#6D#", dR.getArrayWilds()[0])
            .replaceAll("#6D#ScenarioId#6D#", dR.getTokens()[2])
            .replaceAll("#6D#UserId#6D#", dR.getTokens()[0])
            .replaceAll("#6D#ExecutionId#6D#",dR.getTokens()[1])
        ;
        if( dR.getArrayWilds().length>1)toRet = toRet
                .replaceAll("#6D#ComponentName#6D#", dR.getArrayWilds()[1])
                .replaceAll("#6D#ComponentId#6D#", dR.getTokens()[3]);
        if( dR.getArrayWilds().length>2)toRet = toRet
                .replaceAll("#6D#ObjectName#6D#", dR.getArrayWilds()[2])
                .replaceAll("#6D#ObjectId#6D#", dR.getTokens()[4]);
        if( dR.getArrayWilds().length>3)toRet = toRet
                .replaceAll("#6D#MethodName#6D#", dR.getArrayWilds()[3])
                .replaceAll("#6D#MethodId#6D#", Integer.toString(dR.getLastElement()));
        }
        if(tokens != null){
            String[] loop = {"#6D#UserId#6D#","#6D#ExecutionId#6D#","#6D#ScenarioId#6D#","#6D#ComponentId#6D#","#6D#ObjectId#6D#","#6D#MethodId#6D#"};
            for(int i=0 ; i < tokens.length ; i++)toRet = toRet.replaceAll(loop[i], (String)tokens[i]);
        }
        return toRet ;
    }

// LECTURE DE WILDS

    /* Fonction de parcours de noeuds wild
     * pathFile : chemin du wrapper
     * xPathNode : nom de balise de l'élément à requêter
     */
    /**
     * Fonction de parcours de noeuds wild
     * Transforme un fichier wild en liste d'objets instanciés et parsés
     * @param pathFile     chemin du wrapper
     * @param xPathNode    xPath de départ de conversion
     * @return    Map d'object standards WILD
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws ClassNotFoundException
     */
    public synchronized static HashMap<String, HashMap<String, Object>> wild_nodeList(String pathFile, String xPathNode) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException, ClassNotFoundException{
        return wild_nodeList( xml_getDocument(pathFile), xPathNode) ;
    }

    /**
     * Fonction de parcours de noeuds wild
     * Transforme un noeud wild en liste d'objets instanciés et parsés
     * @param xmlFilecontent Contenu XML du wrapper
     * @param xPathNode    xPath de départ de conversion
     * @return Map d'object standards WILD
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public synchronized static HashMap<String, HashMap<String, Object>> wild_nodeList(Node xmlFilecontent, String xPathNode) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException, ClassNotFoundException{
        HashMap<String, HashMap<String, Object>> toRet = new HashMap<String, HashMap<String, Object>>();
        NodeList toRetNodeList = xml_getNodes(xmlFilecontent, xPathNode);
        if(toRetNodeList==null)return null;
        for(int i = 0 ; i < toRetNodeList.getLength() ; i++){
            Object[] evalNode = wild_solveNode(toRetNodeList.item(i), i, xPathNode);
            String ident = (String)evalNode[0] ;
            int j = 1 ;
            while(toRet.get(ident)!=null)ident = ident+"#6D#"+j;
            toRet.put(ident,(HashMap<String, Object>)evalNode[1]);
        }
        if(!toRet.isEmpty())return toRet ;
        return null ;
    }

    /**
     * Fonction de parcours de noeuds wild
     * Résoud un noeud WILD (noeud vers map d'objets indexés et instanciés)
     * @param nD    Noeud XML à parser
     * @param i    Identifiant du numéro d'ordre "0"
     * @param xPathNode
     * @return Map d'object standards WILD
     * @throws ClassNotFoundException
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public synchronized static Object[] wild_solveNode(Node nD, int i, String xPathNode) throws ClassNotFoundException, DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        HashMap<String, Object> toRetNode = new HashMap<String, Object>();
        String[] paramNotGet = {"ident","description","value"};
        NamedNodeMap nDMap = nD.getAttributes() ;
        String textContent = xml_getText(nD);
        String value = null ;
        String description = null ;
        String ident = null ;
        if(nDMap != null){
             ident = (nDMap.getNamedItem("ident")!=null)?nDMap.getNamedItem("ident").getNodeValue():xPathNode+"#6D#"+(i+1);
             Node nd_value = nDMap.getNamedItem("value");
             Node nd_description = nDMap.getNamedItem("description");
             if(nd_description == null){
                 description = textContent ;
                 if(nd_value != null)value = nd_value.getNodeValue();
             }else{
                 description = nd_description.getNodeValue();
                 value = (nd_value != null)?nd_value.getNodeValue():textContent;
             }
             if(nDMap.getNamedItem("ident")==null && nd_value == null)value = textContent;
             for (int j = 0; j < nDMap.getLength(); j++)if(!Arrays.asList(paramNotGet).contains(nDMap.item(j).getNodeName()))toRetNode.put(nDMap.item(j).getNodeName(), nDMap.item(j).getNodeValue());
             toRetNode.put("description", description);
             String typeParam = (nDMap.getNamedItem("type")!=null)?nDMap.getNamedItem("type").getNodeValue():"String";
             toRetNode.put("value", cast_2Object(typeParam,value)[1]);
             toRetNode.put("order#6D#", i+1);
         }
        Object[] toRet = {ident,toRetNode};
        return toRet ;
    }

    /**
     * Fonction de parcours de noeuds wild
     * Transforme un noeud wild en liste d'objets instanciés et parsés
     * @param xmlFilecontent Contenu XML du wrapper
     * @param xPathNode_loop    xPath de départ de boucle
     * @param xPathNode    xPath de départ de conversion
     * @return Map d'object standards WILD
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public synchronized static HashMap<String, HashMap<String, Object>> wild_nodeList(Node xmlFilecontent, String xPathNode_loop, String xPathNode) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException, ClassNotFoundException{
        HashMap<String, HashMap<String, Object>> toRet = new HashMap<String, HashMap<String, Object>>();
        NodeList toRetNodeList = xml_getNodes(xmlFilecontent, xPathNode_loop);
        if(toRetNodeList==null)return null;
        int i_l = 1 ;
        for(int i = 0 ; i < toRetNodeList.getLength() ; i++){
            Boolean hasDefault = false ;
            HashMap<String, Object> toRetNode = new HashMap<String, Object>();
            NodeList nDList = xml_getNodes(toRetNodeList.item(i),xPathNode);
            if(nDList==null)continue;
            for(int j = 0 ; j < nDList.getLength() ; j++){
                Object[] evalNode = wild_solveNode(nDList.item(j), j, xPathNode);
                toRetNode.put((String)evalNode[0],(HashMap<String, Object>)evalNode[1]);
                if(((HashMap<String, Object>) evalNode[1]).get("default")!=null)hasDefault = true ;
            }

            toRet.put(xPathNode_loop+"#6D#"+(i_l++),toRetNode);
            if(hasDefault){
                HashMap<String, Object> toRetNode_default = new HashMap<String, Object>();
                for(String defK:toRetNode.keySet())
                    if(((HashMap<String, Object>) toRetNode.get(defK)).get("default")==null)toRetNode_default.put(defK, toRetNode.get(defK));
                toRet.put(xPathNode_loop+"#6D#"+(i_l++),toRetNode_default);
            }

        }

        if(!toRet.isEmpty())return toRet ;
        return null ;
    }

    /**
     * Fonction de parcours de noeuds wild
     * Renvoie un tableau ordonné par le tag "order#6D#", i.e. "l'élément d'ordre orderToFind"
     * @param tabToFindWithin    Map convertie à ordonner
     * @param orderToFind        Numéro d'ordre de l'élément à renvoyer
     * @return Map d'object standards WILD
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public synchronized static Object[] xml_wildNodeSearchOrder(HashMap<String, Object> tabToFindWithin, int orderToFind) throws ClassNotFoundException{
        Set<String> kS = tabToFindWithin.keySet() ;
        for(String k:kS){
            Integer kI = cast_2Integer(((HashMap<String, HashMap<String, Object>>) tabToFindWithin.get(k)).get("order#6D#"));
            if(kI == orderToFind){
                Object[] toRet = {k,tabToFindWithin.get(k)};
                return toRet ;
            }
        }
        return null;
    }
    
    /**
     * Fonction de parcours de noeuds wild
     * Transforme un fichier wild en map d'objets
     * récupération d'un élément unique
     * @param pathFile    chemin du wrapper
     * @param xPathNode    xPath de départ de conversion
     * @param xident    "@ident" de l'élément à récupérer
     * @return Map d'object standards WILD
     * @throws XPathExpressionException
     * @throws ClassNotFoundException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public synchronized static HashMap<String, Object> wild_node(String pathFile, String xPathNode,String xident) throws XPathExpressionException, ClassNotFoundException, SAXException, IOException, ParserConfigurationException{
        HashMap<String, HashMap<String, Object>> hM = wild_nodeList(pathFile, xPathNode) ;
        if(hM==null)return null;
        return hM.get(xident);
    }

}
