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
import static fr.wild.common.IoWilds.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Classe de lecture des fichiers de description *.6conf et *.wild
 * @author    alexandre.liccardi
 * @version [ab]
 */
public class WildWrapper implements Serializable{

// Variables

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // Eléments passés en entrée
    private int dType = -1 ; // Type d'objet Dice : scénario, composant ou objet
    private Node dNode = null ; // Noeud descripteur de l'élément (contenu du fichier)
    private String identifier = null ; // Identifiant (@ident) de l'élément traité
    private HashMap<String,WildModel> dmList = null ; // Modèles présents dans le contexte d'exécution
    private WildLogger wildLogger = null ; // logger lors de l'appel depuis un ExecList

    // Eléments pré-calculés
    private HashMap<String, HashMap<String, Object>> dExceptions = new HashMap<String, HashMap<String, Object>>(); // Liste des exceptions prévues pour l'élément
    private HashMap<String, HashMap<String, Object>> dMessages = new HashMap<String, HashMap<String, Object>>(); // Liste des Messages prévues pour l'élément
    private HashMap<String, HashMap<String, Object>> dInvokers = new HashMap<String, HashMap<String, Object>>(); // Paramètres d'invocation prévus pour l'élément
    private HashMap<String, Object> dReturn = new HashMap<String, Object>(); // Paramètre de retour prévus pour l'élément
    private HashMap<String, HashMap<String, Object>> dProducts = new HashMap<String, HashMap<String, Object>>(); // Liste des produits prévus pour l'élément
    private HashMap<String, Object> dInfos = new HashMap<String, Object>(); // Liste des infos prévues pour l'élément
    private HashMap<String, HashMap<String, Object>> dOutputs = new HashMap<String, HashMap<String, Object>>(); // Liste des outputs prévus pour l'élément
    private HashMap<String,Object[]> dSteps = new HashMap<String,Object[]>(); // Liste des étapes prévues pour l'élément
    private HashMap<String,HashMap<String, Object>> dParameters = new HashMap<String,HashMap<String, Object>>(); // Liste des propriétés définies pour l'élément, notamment pour les configurations

    // Eléments calculés par le code
    private HashMap<String, Integer> dResources = null ; // Ressources système nécessaires à l'exécution de l'élément
    private HashMap<Integer,Node> listNode ; // Liste des sous éléments directs
    private HashMap<Integer,HashMap<Integer,Node>> listSubNode = new HashMap<Integer,HashMap<Integer,Node>>() ; // Liste des sous éléments de niveau 2, c'est à dire "WildMethod" pour "WildComponent"
    private HashMap<Integer,HashMap<Integer,Object[]>> listNode_invokers = new  HashMap<Integer,HashMap<Integer,Object[]>>() ; // Liste des noeuds d'invokation appelés par un scénario ou un composant. Indexé par (numéro d'objet, numéro de méthode ; pour chaque objet : méthode "0" = constructeur) ou (numéro de composant, 0). Voir setListNode().
    private HashMap<Integer,HashMap<Integer,Object[]>> listNode_returns = new  HashMap<Integer,HashMap<Integer,Object[]>>() ; // Liste des noeuds de retour appelés par un scénario ou un composant. Indexé par (numéro d'objet, numéro de méthode) ou (numéro de composant, 0). Voir setListNode().
    private final String[] diceElementList = {"Configuration","WildScenario","WildComponent","WildObject","WildMethod"}; // Liste des classes d'éléments Dice

// Constructeurs

    /**
     * Instanciation du Wrapper pour clonage
     * Utilisé lors de l'instanciation multiThread, permet de reconstruire la liste de modèles passée à un scénario sans recalcul.
     * Reconstruit les objets JAVA de la classe, un par un, car la classe WildWrapper n'est pas serializable.
     * @param dW    DiceWrapper à cloner
     */
    public WildWrapper(WildWrapper dW){
        if(dW.dExceptions!=null)dExceptions = new HashMap<String, HashMap<String, Object>>(dW.dExceptions);
        if(dW.dMessages!=null)dMessages = new HashMap<String, HashMap<String, Object>>(dW.dMessages);
        if(dW.dInvokers!=null)dInvokers = new HashMap<String, HashMap<String, Object>>(dW.dInvokers);
        if(dW.dReturn!=null)dReturn = new HashMap<String, Object>(dW.dReturn);
        if(dW.dInfos!=null)dInfos = new HashMap<String, Object>(dW.dInfos);
        if(dW.dProducts!=null)dProducts = new HashMap<String, HashMap<String, Object>>(dW.dProducts);
        if(dW.dOutputs!=null)dOutputs = new HashMap<String, HashMap<String, Object>>(dW.dOutputs);
        if(dW.dSteps!=null)dSteps = new HashMap<String,Object[]>(dW.dSteps);
        if(dW.dParameters!=null)dParameters = new HashMap<String, HashMap<String, Object>>(dW.dParameters);
        if(dW.dResources!=null)dResources = new HashMap<String, Integer>(dW.dResources);
        if(dW.listNode!=null)listNode = new HashMap<Integer,Node>(dW.listNode);
        if(dW.listSubNode!=null)listSubNode = new HashMap<Integer,HashMap<Integer,Node>>(dW.listSubNode);
        if(dW.listNode_invokers!=null)listNode_invokers = new HashMap<Integer,HashMap<Integer,Object[]>>(dW.listNode_invokers);
        if(dW.listNode_returns!=null)listNode_returns = new HashMap<Integer,HashMap<Integer,Object[]>>(dW.listNode_returns);
        dType=dW.dType ;
        dNode=dW.dNode;
        identifier=dW.identifier;
    }

    /**
     * Constructeurs
     * @param i_dType        Type de noeud (nom de la classe, hérité de WildBrick) à construire)
     * @param wildpath        Adresse physique, sur disque, du fichier de description
     * @throws Exception
     */
    public WildWrapper(String i_dType, String wildpath) throws Exception{
        initialize(i_dType, null, null, wildpath, null);
    }

    /**
     * Constructeurs
     * @param i_dType        Type de noeud (nom de la classe, hérité de WildBrick) à construire)
     * @param wildpath        Adresse physique, sur disque, du fichier de description
     * @param i_dmList        Liste des modèles précédemment instanciés, auxquels l'instanciation actuelle peut faire référence
     * @throws Exception
     */
    public WildWrapper(String i_dType, String wildpath, HashMap<String,WildModel> i_dmList) throws Exception{
        initialize(i_dType, null, null, wildpath, i_dmList);
    }

    /**
     * Constructeurs
     * @param i_dType        Type de noeud (nom de la classe, hérité de WildBrick) à construire)
     * @param i_dNode        Contenu XML du noeud décrivant l'objet
     * @param i_dmList        Liste des modèles précédemment instanciés, auxquels l'instanciation actuelle peut faire référence
     * @throws Exception
     */
    public WildWrapper(String i_dType, Node i_dNode, HashMap<String,WildModel> i_dmList) throws Exception{
        initialize(i_dType, i_dNode, null, null, i_dmList);
    }

    /**
     * Constructeurs
     * @param i_diceLogger    WildLogger passé, pour chaînage à l'intérieur d'un même modèle
     * @param i_dType        Type de noeud (nom de la classe, hérité de WildBrick) à construire)
     * @param wildpath        Adresse physique, sur disque, du fichier de description
     * @throws Exception
     */
    public WildWrapper(WildLogger i_diceLogger, String i_dType, String wildpath) throws Exception {
        initialize(i_dType, null, i_diceLogger, null, null);
    }

    /**
     * Constructeurs
     * @param i_diceLogger    WildLogger passé, pour chaînage à l'intérieur d'un même modèle
     * @param i_dType        Type de noeud (nom de la classe, hérité de WildBrick) à construire)
     * @param wildpath        Adresse physique, sur disque, du fichier de description
     * @param i_dmList        Liste des modèles précédemment instanciés, auxquels l'instanciation actuelle peut faire référence
     * @throws Exception
     */
    public WildWrapper(WildLogger i_diceLogger, String i_dType, String wildpath, HashMap<String,WildModel> i_dmList) throws Exception {
        initialize(i_dType, null, i_diceLogger, wildpath, i_dmList);
    }

    /**
     * Constructeurs
     * @param i_diceLogger    WildLogger passé, pour chaînage à l'intérieur d'un même modèle
     * @param i_dType        Type de noeud (nom de la classe, hérité de WildBrick) à construire)
     * @param i_dNode        Contenu XML du noeud décrivant l'objet
     * @param i_dmList        Liste des modèles précédemment instanciés, auxquels l'instanciation actuelle peut faire référence
     * @throws Exception
     */
    public WildWrapper(WildLogger i_diceLogger, String i_dType, Node i_dNode, HashMap<String,WildModel> i_dmList) throws Exception {
        initialize(i_dType, i_dNode,i_diceLogger,null, i_dmList);
    }

    /**
     * Fonction d'initialisation de l'objet
     * @param i_dType        Type de noeud (nom de la classe, hérité de WildBrick) à construire)
     * @param i_dNode        Contenu XML du noeud décrivant l'objet
     * @param i_diceLogger    WildLogger passé, pour chaînage à l'intérieur d'un même modèle
     * @param wildpath        Adresse physique, sur disque, du fichier de description
     * @param i_dmList        Liste des modèles précédemment instanciés, auxquels l'instanciation actuelle peut faire référence
     * @throws Exception
     */
    private void initialize(
            String i_dType,
            Node i_dNode,
            WildLogger i_diceLogger,
            String wildpath,
            HashMap<String,WildModel> i_dmList) throws Exception{
        if(i_diceLogger!=null)wildLogger = i_diceLogger ;
        if(i_dmList!=null)dmList = i_dmList ;
        for(int i = 0 ; i < diceElementList.length && dType == -1 ; i++)if(diceElementList[i].equals(i_dType))dType = i + 1 ;
        if(wildpath!=null)wrapperLog(wildpath);
        if(wildpath!=null&&i_dNode==null)dNode = xml_getFirstNode(wildpath, "./"+diceElementList[dType-1]);
        else dNode = i_dNode ;
        buildElement();
        getResources() ;
    }

// Fonctions de préparation de la classe

    /**
     * Construction de l'élément wrappé, par remplissage des listes contenues
     * @throws XPathExpressionException            erreur si erreur wrapper
     * @throws SAXException                        erreur si erreur wrapper
     * @throws IOException                        erreur si erreur wrapper
     * @throws ParserConfigurationException        erreur si erreur wrapper
     * @throws ClassNotFoundException            erreur si erreur wrapper
     */
    private void buildElement() throws Exception{
        // Cas génériques : exceptions, messages, invokers, outputs, products
        dExceptions = wild_nodeList(dNode, "./Exception");
        if(dExceptions==null||dExceptions.isEmpty())dExceptions = wild_nodeList(dNode, "./FamilyParameter/Exception");
        dMessages = wild_nodeList(dNode, "./Message");
        if(dMessages==null||dMessages.isEmpty())dMessages = wild_nodeList(dNode, "./FamilyParameter/Message");
        dInvokers = wild_nodeList(dNode, "./Invokation","Parameter");
        dOutputs = map_update(wild_nodeList(dNode, "./Output"),"output",true);
        dProducts = wild_nodeList(dNode, "./Product");
        dParameters = wild_nodeList(dNode, "./Parameter");
        if(dParameters==null||dParameters.isEmpty())dParameters = wild_nodeList(dNode, "./FamilyParameter/Parameter");

        // Cas des infos et des retours, liées au premier noeud seulement (rammène les attributs du noeud parent) ou noeud unique
        HashMap<String, HashMap<String, Object>> dInfos_temp = wild_nodeList(dNode, ".") ;
        if(dInfos_temp!=null){
            identifier = dInfos_temp.keySet().iterator().next();
            dInfos = dInfos_temp.get(identifier);
            wrapperLog(identifier);
        }
        HashMap<String, HashMap<String, Object>> dReturns_temp = wild_nodeList(dNode, "./Return/Parameter");
        if(dReturns_temp!=null)dReturn = dReturns_temp.get(dReturns_temp.keySet().iterator().next());

        // Le cas des étapes est spécifique, car il prend en compte des statistiques d'agrégation
        dSteps = evalStep();

        // Détection des noeuds "enfants"
        if(dType>1&&dType<5) setListNode() ;
        if(dType==2||dType==3){
            // Pour les scénarios et les composants, on vérifie que les appels aux compositions fonctionnent :
            // - les sous éléments et les méthodes invoquées existent (composants seulement)
            for(Integer i:listNode.keySet()){
                String subBrickName = xml_getFirstValue(listNode.get(i),"./@ident") ;
                if(dType==2)if(!dmList.get("components").getWrappers().keySet().contains(subBrickName))
                    System.out.println("Missing : "+subBrickName);
                if(dType==3){
                    String[] subName = subBrickName.split("\\.") ;
                    if(!dmList.get(subName[0]).getWrappers().keySet().contains(subName[1]))throw new NullPointerException("Missing : "+xml_getFirstValue(listNode.get(i),"./@ident"));
                    for(Integer i_meth:listSubNode.get(i).keySet())if(!dmList.get(subName[0]).getMethodWrappers().get(subName[1]).keySet().contains(xml_getFirstValue(listSubNode.get(i).get(i_meth),"./@ident")))throw new NullPointerException("Missing : "+xml_getFirstValue(listSubNode.get(i).get(i_meth),"./@ident"));
                }
            }
            // - les invokations existent
            for(Integer i:listNode.keySet()){
                String subBrickName = xml_getFirstValue(listNode.get(i),"./@ident") ;
                if(dType==2)if(dmList.get("components").getInvokation(subBrickName, listNode.get(i), null)==null)throw new NullPointerException("Missing invoker for : "+subBrickName);
                if(dType==3){
                    String[] subName = xml_getFirstValue(listNode.get(i),"./@ident").split("\\.") ;
                    if(dmList.get(subName[0]).getInvokation(subName[1], listNode.get(i), null)==null)throw new NullPointerException("Missing invoker for : "+subBrickName);
                    for(Integer i_meth:listSubNode.get(i).keySet())
                        if(dmList.get(subName[0]).getInvokation(subName[1],xml_getFirstValue(listSubNode.get(i).get(i_meth),"./@ident"), listNode.get(i), null)==null)throw new NullPointerException("Missing invoker for : "+xml_getFirstValue(listSubNode.get(i).get(i_meth),"./@ident"));
                }
            }
        }
    }

    /**
     * Détection des noeuds "enfants", en se basant sur la position dans [diceElementList]
     * listNode identifie les noeuds enfants.
     * Pour les autres listes, l'index des sous-éléments se compose comme suit :
     * - pour les composants :
     *             listNode_invokers.get(0) n'existe pas
     *             listNode_invokers.get(1) renvoie au premier objet
     *          listNode_invokers.get(1).get(0) renvoie au constructeur du premier objet
     *          listNode_invokers.get(1).get(1) renvoie à la première méthode du premier objet
     *  - pour les scénarios :
     *             listNode_invokers.get(0) n'existe pas
     *             listNode_invokers.get(1) renvoie au premier composant
     *          listNode_invokers.get(1).get(0) renvoie à l'invokation du premier composant
     *          listNode_invokers.get(1).get(1) n'existe pas
     * (idem pour listNode_returns)
     * @throws XPathExpressionException            erreur si erreur wrapper
     * @throws SAXException                        erreur si erreur wrapper
     * @throws IOException                        erreur si erreur wrapper
     * @throws ParserConfigurationException        erreur si erreur wrapper
     * @throws DOMException         erreur si erreur wrapper
     * @throws ClassNotFoundException         erreur si erreur wrapper
     */
    private void setListNode() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException, ClassNotFoundException, DOMException{
        // Résolution pour les sous éléments immédiats (ordre donné par diceElementList)
        listNode = new HashMap<Integer,Node>();
        String nextType = (dType >= diceElementList.length)?null:diceElementList[dType];
        NodeList nL = (nextType != null)?xml_getNodes(dNode,"//"+nextType):null;
        for(int i=0 ; i < nL.getLength() ; i++){
            listNode.put(i, nL.item(i));
            if(dType == 3){
                NodeList nL2 =xml_getNodes(nL.item(i),"./"+diceElementList[4]);
                HashMap<Integer, Node> listSubNode_temp = new HashMap<Integer, Node>();
                for(int j=0 ; j < nL2.getLength() ; j++)listSubNode_temp.put(j, nL2.item(j));
                listSubNode.put(i, listSubNode_temp);
            }
        }
        // Parcours des sous-éléments d'invokation si scénario ou composant
        // Résolution pour composants et scénarios : récupération des noeuds pour l'élément suivant
        if(dType == 3 || dType == 2 ){
            NodeList subList = xml_getNodes(dNode,"//"+this.diceElementList[dType]) ;
            for(Integer i=0; i<subList.getLength(); i++){
                HashMap<Integer,Object[]> o_map_invokers = new HashMap<Integer,Object[]>() ;
                HashMap<Integer,Object[]> o_map_returns = new HashMap<Integer,Object[]>() ;
                String[] fidO = xml_getFirstValue(subList.item(i), "@ident").split("\\.") ; // fidO[0] : nom du modèle ; fidO[1] : nom de l'objet
                Boolean parallelMode = cast_2Boolean(xml_getFirstValue(subList.item(i), "@allowParallel"),true);
                Object[] o_map_invokers_tab = new Object[4] ;
                o_map_invokers_tab[3] = xml_getFirstNode(subList.item(i),"./Invokation");
                o_map_invokers_tab[0] = parallelMode ;
                if(fidO.length>1){
                    o_map_invokers_tab[1] = fidO[0] ;
                    o_map_invokers_tab[2] = fidO[1] ;
                }else{
                    o_map_invokers_tab[1] = "components" ;
                    o_map_invokers_tab[2] = fidO[0] ;
                }
                Object[] o_map_returns_tab = o_map_invokers_tab ;
                o_map_returns_tab[3] = xml_getFirstNode(subList.item(i),"./Return");
                o_map_invokers.put(0, o_map_invokers_tab);
                o_map_returns.put(0, o_map_returns_tab);
                // Traitement des méthodes
                NodeList methodList = xml_getNodes(dNode,"//"+this.diceElementList[dType+1]) ;
                if(dType == 3)for(Integer j=0; j<methodList.getLength(); j++){
                    String idM = xml_getFirstValue(methodList.item(j), "@ident");
                    Object[] m_map_invokers_tab = {idM,xml_getFirstNode(methodList.item(j),"./Invokation")};
                    Object[] m_map_returns_tab = {idM,xml_getFirstNode(methodList.item(j),"./Return")};
                    o_map_invokers.put(j+1, m_map_invokers_tab);
                    o_map_returns.put(j+1, m_map_returns_tab);
                }
                listNode_invokers.put(i+1, o_map_invokers);
                listNode_returns.put(i+1, o_map_returns);
            }
        }
    }


    /**
     * Liste des invokation récupérées par le Wrapper
     * @return
     */
    public HashMap<String, HashMap<String, Object>> getInvokers(){
        return dInvokers ;
    }

    // Accesseurs aux noeuds pour scénarios et composants
    /**
     * Nombre de sous éléments directs contenus
     * @return    Nombre de méthodes contenues dans un objet, pour un composant
     */
    public Integer get_numContains(){
        Integer kMax = 0 ;
        for(Integer k:listNode_invokers.keySet())if(kMax<k)kMax=k;
        for(Integer k:listNode_returns.keySet())if(kMax<k)kMax=k;
        return kMax ;
    }

    /**
     * Nombre de méthodes contenues dans un objet, pour un composant
     * @param subEltIdx    Numéro d'occurence de l'objet
     * @return    Nombre de méthodes contenues dans un objet, pour un composant
     */
    public Integer get_numContains(Integer subEltIdx){
        Integer kMax = 0 ;
        for(Integer k:listNode_invokers.get(subEltIdx).keySet())if(kMax<k)kMax=k;
        for(Integer k:listNode_returns.get(subEltIdx).keySet())if(kMax<k)kMax=k;
        return kMax ;
    }

    /**
     * Renvoie le noeud d'invokation d'un objet ou d'un composant
     * @param subEltIdx    Numéro d'occurence de l'objet ou du composant
     * @return {Mode parallèle, Nom du modèle, Nom de l'élément (@ident), Noeud d'appel}
     */
    public Object[] getInvokation(Integer subEltIdx){
        return listNode_invokers.get(subEltIdx).get(0);
    }

    /**
     * Renvoie le noeud d'invokation d'une méthode dans un composant
     * @param subEltIdx    Numéro d'occurence de l'objet
     * @subMethIdx subEltIdx    Numéro d'occurence de la méthode
     * @return {Nom de la méthode (@ident), Noeud d'appel}
     */
    public Object[] getInvokation(Integer subEltIdx, Integer subMethIdx){
        return listNode_invokers.get(subEltIdx).get(subMethIdx);
    }

    /**
     * Renvoie le noeud de retour d'un objet ou d'un composant
     * @param subEltIdx    Numéro d'occurence de l'objet ou du composant
     * @return {Mode parallèle, Nom du modèle, Nom de l'élément (@ident), Noeud d'appel}
     */
    public Object[] getReturn(Integer subEltIdx){
        return listNode_returns.get(subEltIdx).get(0);
    }

    /**
     * Renvoie le noeud de retour d'une méthode dans un composant
     * @param subEltIdx    Numéro d'occurence de l'objet
     * @subMethIdx subEltIdx    Numéro d'occurence de la méthode
     * @return {Nom de la méthode (@ident), Noeud d'appel}
     */
    public Object[] getReturn(Integer subEltIdx, Integer subMethIdx){
        return listNode_returns.get(subEltIdx).get(subMethIdx);
    }

    /**
     * Accesseur à la lise des noeuds "enfants"
     * @return liste des noeuds "enfants"
     */
    public HashMap<Integer,Node> getListNode(){
        return listNode ;
    }

    /**
     * Accesseur à l'identifiant (@ident du noeud parent)
     * @return identifiant ("nom" de l'élément)
     * @throws DOMException                        erreur si erreur wrapper
     * @throws XPathExpressionException            erreur si erreur wrapper
     * @throws SAXException                        erreur si erreur wrapper
     * @throws IOException                        erreur si erreur wrapper
     * @throws ParserConfigurationException        erreur si erreur wrapper
     */
    public String getName() throws DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        if(identifier!=null)return identifier ;
        String name = xml_getFirstValue(dNode,"./@ident");
        if(name != null) return name ;
        return null ;
    }

    /**
     * Calcul et fourniture des ressources nécessaires aux traitements de l'élément wrappé
     * @return    Tableau contenant les valeurs de minRAM, minROM et minCPU (valeurs minimales nécessaires)
     * @throws XPathExpressionException         erreur si erreur wrapper
     * @throws SAXException                        erreur si erreur wrapper
     * @throws IOException                        erreur si erreur wrapper
     * @throws ParserConfigurationException        erreur si erreur wrapper
     */
    public HashMap<String, Integer> getResources() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        if(dResources!=null||dType==1)return dResources ;
            dResources = new HashMap<String, Integer>();
        // Récupération des valeurs en argument
            String[] strResources = {"minRAM","minROM","minCPU"};
            for(int i=0;i<strResources.length;i++){
                Integer val = (dInfos.get(strResources[i])!=null)?Integer.parseInt((String)dInfos.get(strResources[i])):0;
                dResources.put(strResources[i], val);
            }
        // Pour les composants, en branch "bivalence", forcément renseigné
            if(dType == 3)for(int i=0;i<strResources.length;i++)if(dResources.get(strResources[i])==null)return null;
        // Résolution pour scénarios : défini par les plus grandes valeurs pour les composants contenus
            if(dType == 2){
                NodeList objectList = xml_getNodes(dNode,"//"+this.diceElementList[dType]) ;
                for(Integer i=0; i<objectList.getLength(); i++){
                    String idComp = xml_getFirstValue(objectList.item(i), "@ident") ; // fidO[0] : nom du modèle ; fidO[1] : nom de l'objet
                    HashMap<String, Integer> dInfos_temp = dmList.get("components").getResources(idComp);
                    for(int ir=0;ir<strResources.length;ir++){
                        Integer val = dInfos_temp.get(strResources[ir]);
                        if(val>dResources.get(strResources[ir]))dResources.put(strResources[ir], val);
                    }
                }
            }
        return dResources ;
    }

    /**
     * Récupération d'une exception
     * @param ident    Index (attribut @ident) de l'exception à récupérer
     * @return    Carte de l'ensemble des attributs disponibles pour l'exception (dont "value")
     */
    public HashMap<String,Object> getException(String ident){
        if(dExceptions==null)return null;
        return dExceptions.get(ident);
    }

    public HashMap<String, HashMap<String, Object>> getExceptions(){
        return dExceptions;
    }

    /**
     * Récupération d'un message
     * @param ident    Index (attribut @ident) du message à récupérer
     * @return    Carte de l'ensemble des attributs disponibles pour le message (dont "value")
     */
    public HashMap<String,Object> getMessage(String ident){
        if(dMessages==null)return null;
        return dMessages.get(ident);
    }

    /**
     * Récupération d'une information
     * @param ident    Index (attribut @ident) de l'information à récupérer
     * @return    Carte de l'ensemble des attributs disponibles pour l'information (dont "value")
     */
    public Object getInfo(String paramName){
        if(dInfos==null)return null;
        return dInfos.get(paramName);
    }

    /**
     * Récupération des produits
     * @return    Carte de l'ensemble des produits disponibles
     */
    public HashMap<String, HashMap<String, Object>> getProducts(){
        return dProducts;
    }

    /**
     * Récupération d'un produit
     * @param ident    Index (attribut @ident) du produit à récupérer
     * @return    Carte de l'ensemble des attributs disponibles pour le produit (dont "value")
     */
    public HashMap<String,Object> getProduct(Integer pos){
        if(dProducts==null)return null;
        return dProducts.get("./Product#6D#"+pos);
    }

    /**
     * Récupère la liste des paramètres, avec les informations par défaut et les types corrigés, pour l'appel d'une méthode.
     * Compare une liste de valeurs en entrée aux invokations disponibles pour la méthode, renvoie la plus proche.
     * @param paramList    Liste des paramètes (instanciés)
     * @return    Carte contenant l'identifiant, les informations, la valeur instanciée du paramètre ainsi que son ordre (clé : order#6D#)
     * @throws ClassNotFoundException    Si la valeur instanciée du tableau de paramètre ne correspond à aucun type JAVA ou si la valeur du @type du wrapper est fausse
     */
    @SuppressWarnings("unchecked")
    public HashMap<String,Object> getInvokation(Object[] paramList) throws ClassNotFoundException{
        // Récupération des invokations
        if(dInvokers==null)return null;
        Set<String> kS = dInvokers.keySet() ;

        // Pour chaque invokation
        for(String k:kS){
            HashMap<String,Object> invoker = dInvokers.get(k);
            // On vérifie la taille
            if(paramList.length==invoker.size()){
                Boolean isOk = true ;
                // On vérifie l'ordre et le type de l'instanciation
                for(int i=0; i<paramList.length ; i++){
                    HashMap<String,Object> parameter = (HashMap<String, Object>) xml_wildNodeSearchOrder(invoker, i+1)[1];
                    // Si une erreur existe, on rejette l'invokation
                    if(!paramList[i].getClass().getSimpleName().toLowerCase().equals(((String)parameter.get("type")).toLowerCase())){
                        isOk = false ;
                        continue ;
                    }
                }
                // Si aucune erreur existe, on alimente et retourne l'invocation
                if(isOk){
                    for(int i=0; i<paramList.length ; i++){
                        Object[] paramSolved = xml_wildNodeSearchOrder(invoker, i+1) ;
                        HashMap<String,Object> parameter = (HashMap<String,Object>)paramSolved[1];
                        parameter.put("value", paramList[i]);
                        invoker.put((String) paramSolved[0], parameter);
                    }
                    return invoker ;
                }
            }
        }
        return null;
    }

    /**
     * Récupère la liste des paramètres, avec les informations par défaut et les types corrigés, pour l'appel d'une méthode.
     * Récupère les informations d'invokation via un noeud et une liste de valeurs indexées
     * @param useAsNode                        Noeud d'invocation (contient un noeud de la forme "<Invokation><Parameter ident ={valeur renvoyant à {parametersWith}} useAs = {Valeur retrouvée dans le wrapper suivant}></Parameter>...</Invokation>"
     * @param parametersWith                Carte contenant les valeurs, indexées par la clé, qui correspond au @ident des paramètres dans le noeud
     * @return                                Carte contenant l'identifiant, les informations, la valeur instanciée du paramètre ainsi que son ordre (clé : order#6D#). Indexé par useAs depuis l'appelant.
     * @throws ClassNotFoundException        Si la valeur instanciée du tableau de paramètre ne correspond à aucun type JAVA ou si la valeur du @type du wrapper est fausse
     * @throws XPathExpressionException        Renvoyé par la fonction {wild_nodeList()} pour traitement du noeud {invokerUseAs}
     * @throws SAXException                    Renvoyé par la fonction {wild_nodeList()} pour traitement du noeud {invokerUseAs}
     * @throws IOException                    Renvoyé par la fonction {wild_nodeList()} pour traitement du noeud {invokerUseAs}
     * @throws ParserConfigurationException    Renvoyé par la fonction {wild_nodeList()} pour traitement du noeud {invokerUseAs}
     */
    @SuppressWarnings({ "unchecked", "unused" })
    public HashMap<String, Object> getInvokation(Node useAsNode, HashMap<String,Object> parametersWith) throws ClassNotFoundException, XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        if(dInvokers==null)return null;

        // Simplification du traitement des useAs par ajout d'une variable indexant les useAs en ident
        HashMap<String, HashMap<String, Object>> invokerUseAs = wild_nodeList(useAsNode,"./Parameter");
        if(invokerUseAs==null)invokerUseAs = wild_nodeList(useAsNode,"./Invokation/Parameter");
        HashMap<String, Object> invokerUseAsSolved = new HashMap<String, Object>() ;
        if(invokerUseAs==null)return null;
        for(String k:invokerUseAs.keySet()){
            String strUseAs = (String) invokerUseAs.get(k).get("useAs") ;
            String key_split = k.split("#6D#")[0];
            Object valueUseAs_f = (invokerUseAs.get(key_split)==null)?null:invokerUseAs.get(key_split).get("value");
            Object valueUseAs = (valueUseAs_f==null&&parametersWith!=null)?parametersWith.get(key_split):valueUseAs_f;
            invokerUseAsSolved.put(strUseAs, valueUseAs);
        }

        // Récupération des invokations
        Set<String> kSInvokers = dInvokers.keySet() ;
        Set<String> kSInvokersSolved = invokerUseAsSolved.keySet() ;

        // Pour chaque invokation
        for(String k:kSInvokers){
            Boolean isOk = true ;
            HashMap<String, Object> invoker = dInvokers.get(k);

            // On vérifie la taille
            if(invokerUseAs.keySet().size()==invoker.size()){

                Set<String> kS_p = invoker.keySet() ;
                // Si une erreur existe, on rejette l'invokation
                for(String k_p:kS_p)if(!kSInvokersSolved.contains(k_p))isOk = false ;
                // Si aucune erreur existe, on alimente et retourne l'invocation
                if(isOk){
                    int i=0;
                    for(String k_p:kS_p){
                        i++;
                        Object[] paramSolved = xml_wildNodeSearchOrder(invoker, i) ;
                        HashMap<String,Object> parameter = (HashMap<String,Object>)paramSolved[1];
                        Object invokerUseAsSolved_value = invokerUseAsSolved.get((String)paramSolved[0]);
                        if(invokerUseAsSolved_value==null)
                            parameter.put("value", null);
                        else if(invokerUseAsSolved_value
                                .getClass()
                                .getSimpleName()
                                .equals("HashMap"))
                            invokerUseAsSolved_value =
                            ((HashMap<String, Object>) invokerUseAsSolved_value)
                            .get("value");
                        parameter.put("value", invokerUseAsSolved_value);
                        invoker.put((String) paramSolved[0], parameter);
                    }
                    return invoker ;
                }
            }
        }
        return null;
    }

    /**
     * Identifie l'objet de retour (une méthode n'a qu'un objet de retour).
     * Actualise le tableau de paramètre par la valeur de cet objet, à partir du noeud d'appel instancié et du noeud de description du retour.
     * @param parametersWith                     Carte des paramètres utilisés
     * @return                                    Carte des paramètres utilisés (mis à jour)
     * @throws ParserConfigurationException     Noeud de retour non conforme
     * @throws IOException                         Noeud de retour non conforme
     * @throws SAXException                     Noeud de retour non conforme
     * @throws XPathExpressionException         Noeud de retour non conforme
     * @throws DOMException                     Noeud de retour non conforme
     * @throws ClassNotFoundException Description du type de retour non conforme
     */
    @SuppressWarnings("unchecked")
    public HashMap<String,Object> getReturn(HashMap<String,Object> parametersWith, Node returnNode, Object objectValue, Boolean keepItAll) throws DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException, ClassNotFoundException{
        if(parametersWith==null)return null;
        HashMap<String,Object> toRetH = new HashMap<String,Object>() ;
        if(keepItAll) toRetH = parametersWith ;
        String useFrom = (String)dReturn.get("useFrom");
        // Gestion des cas directement renvoyés par un useFrom
        if(objectValue==null&&useFrom!=null){
            HashMap<String, Object> toRet = (HashMap<String, Object>) parametersWith.get(useFrom) ;
            if(toRet == null)toRet = new HashMap<String, Object>();
            String useAs = xml_getFirstValue(returnNode,"./Parameter/@useAs") ;
            String describer = xml_getFirstValue(returnNode,"./Parameter/@description") ;
            if(describer==null)describer = xml_getText(xml_getFirstNode(returnNode,"./Parameter"));
            if(describer!=null)toRet.put("description", describer);
            if(describer==null)describer = (String)dReturn.get("description");
            if(describer!=null)toRet.put("description", describer);
            toRetH.put(useAs, toRet);
            toRetH.remove(null);
            return toRetH ;
        }
        // Gestion des cas de correspondance useAs / table de paramètres
        if(!dReturn.isEmpty()){
            String retType = (String)dReturn.get("type") ;
            if((Boolean)cast_2Object(retType,objectValue)[0]){
                HashMap<String, Object> toRet = dReturn ;
                toRet.put("value", objectValue);
                String describer = xml_getFirstValue(returnNode,"./Parameter/@description") ;
                if(describer==null)describer = xml_getText(xml_getFirstNode(returnNode,"./Parameter"));
                if(describer!=null)toRet.put("description", describer);
                String useAs = xml_getFirstValue(returnNode,"./Parameter/@useAs") ;
                toRetH.put(useAs, toRet);
            }
        }
        toRetH.remove(null);
        return toRetH;
    }

    /**
     * Retourne une étape d'exécution de l'élément :
     * Object[], avec {Pourcentage d'avancement, Texte descriptif}
     * @param currentSubElement        Numéro de sous-élément en cours
     * @return    {Pourcentage d'avancement, Texte descriptif}
     */
    public Object[] getStep(int currentSubElement){

        if(currentSubElement==0&&dType!=(int)4)return null;
        if(dSteps==null||dSteps.size()==0)return null;
        Object[] min = {false};
        Object[] max = {true};
        if(currentSubElement < 0)return min ;
        int kMax = 0 ;
        // Cas des objets et méthodes
        if(dType==(int)5){
            if(dSteps.get(Integer.toString(currentSubElement))!=null)return Arrays.copyOfRange(dSteps.get(Integer.toString(currentSubElement)),0,2);
            Set<String> ks = dSteps.keySet();
            try{
                for(String k:ks)if(kMax < (int) dSteps.get(k)[3]) kMax = (int) dSteps.get(k)[3] ;
                if(currentSubElement > kMax)return max ;
            }catch(Exception e){e.printStackTrace();}
            return null;
        }

        if(dType==(int)4)if(dSteps.get(Integer.toString(currentSubElement))!=null)return Arrays.copyOfRange(dSteps.get(Integer.toString(currentSubElement)),0,2);
        // Cas des scénarios et composants
        Set<String> ks = dSteps.keySet();
        for(String k:ks)if(dSteps.get(k).length>3)if(currentSubElement-1 >= (int) dSteps.get(k)[2] && currentSubElement-1 <= (int) dSteps.get(k)[3]){
            if(kMax < (int) dSteps.get(k)[3]) kMax = (int) dSteps.get(k)[3] ;
            return Arrays.copyOfRange(dSteps.get(k),0,2);
        }
        if(currentSubElement > kMax)return max ;
        return null;
    }

    /**
     * Information : le wrapper est-il nul ?
     * @return    TRUE (vide) ou FALSE (non vide)
     */
    public boolean isEmpty() {
        if(dNode == null)return true ;
        return false ;
    }

    /**
     * Construction de la liste des étapes
     * Construction complexe car l'estimation du pourcentage d'avancement implique le parcours préalable de l'ensemble des étapes
     * Et le mode de décompte des étapes n'est pas le même entre [méthodes et objets] et [composants et scénarios], les premiers se basant sur un numéro d'ordre, les second sur un numéro de sous-élément parcouru.
     * @return
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private HashMap<String,Object[]> evalStep() throws DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        if(dType>3)return evalStepObjOrMeth();
        if(dType<4)return evalStepCompOrScenar();
        return null;
    }

    /**
     * Construction de la liste des étapes pour objets et méthodes
     * Construction complexe car l'estimation du pourcentage d'avancement implique le parcours préalable de l'ensemble des étapes
     * Et le mode de décompte des étapes n'est pas le même entre [méthodes et objets] et [composants et scénarios], les premiers se basant sur un numéro d'ordre, les second sur un numéro de sous-élément parcouru.
     * @return
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private HashMap<String,Object[]> evalStepObjOrMeth() throws DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        NodeList stepList = xml_getNodes(dNode,"./Step") ;
        Integer totalStepWeight = 0 ;
        Float progress = (float) 0 ;
        HashMap<String,Object[]> toRet = new HashMap<String,Object[]> ();
        HashMap<Integer, String> order = new HashMap<Integer, String>();
        for(Integer i = 0 ; i < stepList.getLength() ; i++){
            Integer stepWeight = Integer.parseInt(xml_getFirstValue(stepList.item(i),"@weight"));
            String ident = (xml_getFirstValue(stepList.item(i),"@ident")!=null)?xml_getFirstValue(stepList.item(i),"@ident").toLowerCase():i.toString();
            String stepText = xml_getFirstValue(stepList.item(i),"@description");
            if(stepText==null) stepText = xml_getText(stepList.item(i));
            totalStepWeight += stepWeight;
            Object[] stepContents = {stepWeight, stepText, i};
            toRet.put(ident, stepContents);
            order.put(i, ident);
        }
        Set<Integer> ks = order.keySet();
        for(Integer ki:ks){
            String k = order.get(ki);
            Integer progress_i = (Integer)toRet.get(k)[0];
            Object[] progressBundaries = {progress, progress + new Float(progress_i)/totalStepWeight};
            Object[] stepContents = {progressBundaries,toRet.get(k)[1],toRet.get(k)[2]};
            toRet.put(k, stepContents);
            progress = (Float) progressBundaries[1] ;
        }
        return toRet;
    }

    /**
     * Construction de la liste des étapes pour scénarios et composants
     * Construction complexe car l'estimation du pourcentage d'avancement implique le parcours préalable de l'ensemble des étapes
     * Et le mode de décompte des étapes n'est pas le même entre [méthodes et objets] et [composants et scénarios], les premiers se basant sur un numéro d'ordre, les second sur un numéro de sous-élément parcouru.
     * @return
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    private HashMap<String,Object[]> evalStepCompOrScenar() throws DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        // Liste des noeuds <Step> du sous-élément du wrapper
        NodeList stepList = xml_getNodes(dNode,"./Step") ;
        if(stepList==null||stepList.getLength()==0)return null;
        // Préparation des variables
        HashMap<String,Object[]> toRet = new HashMap<String,Object[]> ();
        // Indicateur de position des sous-éléments
        String nextClass = diceElementList[dType];
        Integer totalStepWeight = 0 ;
        Integer childBundary = 0 ;
        Float progress = (float) 0 ;
        for(Integer i = 0 ; i < stepList.getLength() ; i++){
            Integer stepWeight = Integer.parseInt(xml_getFirstValue(stepList.item(i),"@weight"));
            String stepText = xml_getFirstValue(stepList.item(i),"@description");
            if(stepText==null) stepText = xml_getText(stepList.item(i));
            totalStepWeight += stepWeight;
            Integer[] childBundaries = new Integer[2] ;
            if(nextClass != null){
                childBundaries[0] = childBundary ;
                childBundary += xml_getNodes(stepList.item(i),".//"+nextClass).getLength() - 1;
                childBundaries[1] = childBundary ;
            }else{
                childBundaries[0] = null ;
                childBundaries[1] = null ;
            }
            Object[] stepContents = {stepWeight, stepText, childBundaries[0], childBundaries[1]};
            toRet.put(i.toString(), stepContents);
            childBundary++;
        }
        for(Integer i=0; i< toRet.size(); i++){
            Integer progress_i = (Integer)toRet.get(i.toString())[0];
            Object[] progressBundaries = {progress, progress + new Float(progress_i)/totalStepWeight};
            toRet.put(i.toString(), new Object[] {progressBundaries,toRet.get(i.toString())[1],toRet.get(i.toString())[2],toRet.get(i.toString())[3]});
            progress = (Float) progressBundaries[1] ;
        }
        return toRet ;
    }

    /**
     * Traduit les variables stockées par l'objet en liste limitée aux outputs de la méthode
     * @param parametersWith    Carte des variables stockées par l'objet, indexée par {Identifiant de la méthode}#6D#output{Numéro de la variable}
     * @return                    Liste limitée aux outputs de la méthode, indexée par un numéro d'ordre
     */
    public HashMap<Integer, HashMap<String, Object>> getOutputs(HashMap<String,Object> parametersWith) {
        Set<String> kSOutput = dOutputs.keySet() ;
        HashMap<Integer, HashMap<String, Object>> toRet = new HashMap<Integer, HashMap<String, Object>>();
        Integer i = 0;
        for(String kOutput:kSOutput){
            i++;
            HashMap<String, Object> output = dOutputs.get(kOutput);
            if(parametersWith.get(this.identifier+"#6D#output"+i)!=null)output.put("value",parametersWith.get(this.identifier+"#6D#output"+i));
            toRet.put(i, output);
        }
        if(!toRet.isEmpty())return toRet;
        return null;
    }

    public HashMap<String, Object> getOutput(String ident){
    	return dOutputs.get(ident);
    }

    /**
     * Retourne la liste des outputs pour le wrapper
     * @return                    Liste limitée aux outputs de la méthode, indexée par un identifiant
     */
    public HashMap<String, HashMap<String, Object>> getOutputs() {
        return dOutputs;
    }

    /**
     * Consultation des configurations
     * @param propertyName Nom de la propriété (Parameter/@ident)
     * @return Valeur pour le paramètre
     */
    public Object getParameter(String propertyName) {
        if(dParameters.get(propertyName)==null)return null;
        return dParameters.get(propertyName).get("value");
    }

    /**
     * Ecriture de log
     * @param Source    Information de provenance du wrapper
     * @throws Exception
     */
    private void wrapperLog(String Source) throws Exception{
        String logStr = (identifier==null||Source!=null)?" WRAPPING "+diceElementList[dType-1]+" FROM {"+Source+"}.":" WRAPPING "+diceElementList[dType-1]+" named {"+identifier+"}.";
        if(wildLogger!=null)wildLogger.logEvent("BuildWrapperAttempt", logStr);
    }
    
    /**
     * Récupère les noeud des éléments enfant
     * @param subWrapper2    Identifiant de sous élément
     * @return
     */
    public HashMap<Integer, Node> getListNode(Integer subWrapper2) {
        return listSubNode.get(subWrapper2);
    }

}