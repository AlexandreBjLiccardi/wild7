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
import fr.wild.real.WildSigFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.hibernate.Session;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;


/**
 * WildBrick est la classe "mère", abstraite, décrivant les éléments exécutables depuis un appel d'exécution.
 * Ces éléments (DiceScenarios, DiceComponents, DiceObjects, DiceMethods) répondent à  des règles d'exécution communes, se contiennent hiérarchiquement et sont capable de s'appeler ou de s'instancier.
 * Certaines fonctions ont un fonctionnement commun mais des spécificités selon la classe fille, par convention il a été tenté au maximum de traiter les différents cas dans les classes filles (minimisation de l'utilsiation des switch/case)
 * @author alexandre.liccardi
 * @version [ab]
 */
@SuppressWarnings("rawtypes")
public abstract class WildBrick implements Callable<ConcurrentHashMap<String,Object>>{


    //-- Static attribut needed to add RGF93 grid
    //-- flag to define if epsg base has already been initialized
    private static boolean IS_APACHE_INITIALIZED = false;
    static {
        if (!IS_APACHE_INITIALIZED) {
            try {
                WildSigFile.DEV_InitEpsgDB();
                IS_APACHE_INITIALIZED = true;
            } catch (Exception ex) {
                //-- do nothing, let's program continue with default epsg base.
            }
        }
    }

    protected String objectName ; // Nom de l'objet (utilisé exclusivement pour WildObject)
    protected String modelName ; // Nom du modèle (utilisé exclusivement pour WildObject)
    protected WildToken wildToken ; // Token contenant les identifiants de la classe
    protected WildModelList wildModelList ; // Liste des modèles WILDS pré-traités
    protected WildBrick parentDiceBrick = null ; // WildBrick ayant appelé la classe en cours
    protected Node diceNode ; // Noeud d'appel (issu du Dice Runnable parent)
    protected String diceName ; // Nom WILD de la classe en cours
    protected Integer methodX = 0; // Marqueur du numéro de sous élément traité, utile lors de l'instanciation d'éléments contenant plusieurs fois un màªme objet WILD
    protected String parametersInitString = null ; // Brin XML contenant les paramètres initialement transmis à  l'élément
    protected String parentDescription ; // Description portée par l'élément parent

    protected String execFolder ; // Chemin du répertoire d'exécution (oà¹ sont copiés les fichiers au final)
    protected String execTempFolder ; // Chemin du répertoire d'exécution

    protected WildParameters parametersWith = new WildParameters() ;
    protected ConcurrentHashMap<Integer,WildBrick> subList = new ConcurrentHashMap<Integer, WildBrick>(); // Liste des sous éléments internes, ordonnés
    protected List<Integer> currentLastSubElements = new ArrayList<Integer>() ;// Liste des numéro (composants) ou des identifiants (méthodes) du dernier sous élément ouvert (i.e. en cours de traitement), ou liste dans le cas du multi-thread.

    protected WildClass currentObjectInstance ; // Instance d'objet pour appel de méthodes
    protected Object receiver ; // Objet utilisé pour recevoir les retour d'exécution des méthodes
    protected LinkedHashMap<String, HashMap<String, Object>> outputs = new LinkedHashMap<String, HashMap<String, Object>>(); // Liste d'objets dits "outputs", passés pour information directement des objects aux scénarios
    protected Boolean checkedOutput = false ; // Les outputs ont-ils été vérifiés en amont ?

    protected String[] urlAvoidList = new String[]{"http","www.","ftp:"}; // Début de chaîne de caractère pour lesquelles fielPath n'est pas interprété (4 caractères)

    protected WildLogger wildLogger ; // Logger pour le WildBrick
    protected String threadName ; // Nom du thread lancé par le WildBrick parent, en 0.6 thread de composant

    protected Boolean onError = false ; // La brique est-elle en erreur (arràªt de l'exécution des briques sub)?
    protected Map<String, String> errorMessage = null ; // Message d'erreur renvoyé en cas d'échec d'exécution

    protected Class objectClass ; // Classe de l'objet, définie dans les instantiations (classes filles)
    protected WildClassLoader wildClassLoader ; // Contexte de chargement .JAR

	protected Boolean WILD_4Test = false ; // Indique si l'objet est construit depuis Wild4Test (à true)

	/**
     * Retourne la classe de l'objet parent
     * @return
     */
    public Class getParentObjectClass(){
        return this.getParent().objectClass;
    }
    /**
     * Retourne le contexte de chargement JAR de l'objet parent
     * @return
     */
    public WildClassLoader getParentClassLoader(){
        WildClassLoader wLoader =this.getParent().wildClassLoader ;
        return wLoader ;
    }

    /**
     * Accède à la session hibernate
     * loop jusqu'au scénario
     */
    public Session getConnect(){
        return this.parentDiceBrick.getConnect();
    }

    /**
     * Consultation de la brique parente
     * @return    Brique parente si non scénario (sinon null)
     */
    public WildBrick getParent(){
        return this.parentDiceBrick ;
    }
    /**
     * Méthode de récupération du modèle de l'objet, à  l'intérieur de la liste commune passée à  toutes les briques
     * Comportement spécifiques selon si Scénario,... se base sur la longueur du WildToken
     * @return
     */
    public WildModel getModel(){
    	if(wildModelList.getModel(getModelName())!=null)return wildModelList.getModel(getModelName());
        switch(wildToken.getArrayWild().length){
            case 1: return wildModelList.getModel("scenarios");
            case 2: return wildModelList.getModel("components");
            case 3: return wildModelList.getModel(getArrayWilds()[2].split("\\.")[0]);
            case 4: return wildModelList.getModel(parentDiceBrick.getArrayWilds()[2].split("\\.")[0]);
        }
        return null;
    }

    /**
     * Récupération des paramètres de retour de la classe
     * @param keepItAll     Si le paramètre n'est pas mentionné dans le return, on le garde à  TRUE
     * @throws Exception
     * @return Liste et valeurs des paramètres de retour
     */
    public HashMap<String,Object> getParameters(Boolean keepItAll) throws Exception{
        HashMap<String,Object> parametersWith_lk = new HashMap<String,Object>(parametersWith.getParameters());
        String[] parentSplitted = null ;
        if(parentDiceBrick!=null) parentSplitted = parentDiceBrick.getName().split("\\.") ;
        if(parentSplitted!=null&&parentSplitted.length>1)return getModel().getReturn(parentSplitted[1], diceName, receiver, xml_getFirstNode(diceNode,"./Return"), parametersWith_lk, keepItAll); // Cas de WildMethod
        String[] splitted = diceName.split("\\.") ;
        if(splitted.length>1)return getModel().getReturn(splitted[1], null, receiver, xml_getFirstNode(diceNode,"./Return"), parametersWith_lk, keepItAll); // Cas de WildObject
        return getModel().getReturn(diceName, null, receiver, xml_getFirstNode(diceNode,"./Return"), parametersWith_lk, keepItAll); // Autres cas*/
    }

    /**
     * Récupération des paramètres de retour de la classe
     * Par défaut, si le paramètre n'est pas mentionné dans le return, on le garde
     * @throws Exception
     * @return Liste et valeurs des paramètres de retour
     */
    public HashMap<String,Object> getParameters() throws Exception{
        return getParameters(true);
    }

    /**
     * Méthode générique
     * Surchargée pour les Scénarios, les composants et les objets
     * Par défaut, remplace les paramètres de la classe par ceux transmis
     * @param parametersWith2
     * @throws Exception
     */
    public void setParameters(ConcurrentHashMap<String, Object> parametersWith2) throws Exception{
        setParameters(new HashMap<String, Object>(parametersWith2));
    }

    /**
     * Méthode générique
     * Surchargée pour les Scénarios, les composants et les objets
     * Par défaut, remplace les paramètres de la classe par ceux transmis
     * @param parametersWith2
     * @throws Exception
     */
    public void setParameters(LinkedHashMap<String, Object> parametersWith2) throws Exception{
        setParameters(new HashMap<String, Object>(parametersWith2));
    }

    /**
     * Méthode générique
     * Surchargée pour les Scénarios, les composants et les objets
     * Par défaut, remplace les paramètres de la classe par ceux transmis
     * @param parametersWith2
     * @throws Exception
     */
    public void setParameters(Map<String, Object> parametersWith2) throws Exception{
        if(parametersWith == null)parametersWith = new WildParameters();
        if(parametersWith2!=null)for(String k:parametersWith2.keySet())parametersWith.put(k, parametersWith2.get(k));
    }

    /**
     * Informations associées à  la brique, pour identification par les logs
     * @return    {Identifiant d'exécution, niveau d'adminsitration, criticité}
     */
    public Object getInfo(String name){
        return wildModelList.getInfo(this, name);
    }

    /**
     * Récupération de la description de l'étape, à  partir d'un numéro de sous élément
     * @param numSub    Numéro du sous élément
     * @return    Tableau de description d'une étape, avec {Niveau de la brique, nom de la brique, {avancement min. de l'étape, avancement max de l'étape},description, nom de l'étape, étape en cours, nombre d'étapes}
     */
    public Object[] getStep(Integer numSub){
        Object[] step = wildModelList.getStep(this, numSub);
        if(step!=null)return new Object[] {getClass().getSimpleName(), this.getName(),step[0], step[1], numSub, subList.size()};
        return null;
    }

    /**
     * Récupération de la liste descriptive des étapes en cours
     * @return
     */
    public List<Object[]> getStep() {
        List<Object[]> toRet = new ArrayList<Object[]>();
        for(Integer cE:currentLastSubElements){
            Object[] step = getStep(cE);
            if(step!=null) {
                toRet.add(step); // Commande propre au type d'élément appelé
                if(subList!=null)toRet.addAll(subList.get(cE).getStep()); // Appel récursif des sous éléments
            }
        }
        return toRet ;
    }

    /**
     * Récupération de la description d'un output
     * @param name    Identifiant de l'output
     * @return
     */
    public HashMap<String, Object> getOutput_descr(String ident){
        return wildModelList.getOutput(this, ident);
    }

    /**
     * Récupération de la description d'une description
     * @param name    Identifiant de la description
     * @return
     */
    public HashMap<String, Object> getException_descr(String ident){
        return wildModelList.getOutput(this, ident);
    }

    /**
     * Récupération de la description d'un message
     * @param name    Identifiant du message
     * @return
     */
    public HashMap<String, Object> getMessage_descr(String ident){
        return wildModelList.getOutput(this, ident);
    }

    /**
     * répertoire d'exécution (= celui qui est zippé in fine)
     * @return
     */
    public String getExecFolder() {
        return execFolder;
    }

    /**
     * répertoire d'exécution temporaire (= celui dans lequel les objets instanciés écrivent)
     * @return
     */
    public String getExecTempFolder() {
        return execTempFolder;
    }

    /**
     * Description fournie par l'élément parent
     * @return
     */
    public String getParentDescription(){
        return parentDescription ;
    }

    /**
     * Nom WILD de l'élément
     * @return
     */
    public String getName(){
        return diceName ;
    }

    /**
     * Nom du thread
     * @return
     */
    public String getThreadName(){
        return threadName ;
    }

    /**
     * Méthode générique, développée dans les classes filles
     * Par défaut ("Raw"), renvoie la liste intégrale des paramètres reà§us par le Dice Runnable
     * @return
     * @throws Exception
     */
    public ConcurrentHashMap<String, Object> getRawParameters() throws Exception{
        return parametersWith.getParameters() ;
    }

    /**
     * Récupération des paramètres de l'élément parent
     * @return
     * @throws Exception
     */
    public ConcurrentHashMap<String, Object> getParentParameters() throws Exception{
        return cast_Map2ConcurrentHashMap(map_merge(new HashMap<String,Object>(parametersWith.getParameters()),parentDiceBrick.getParameters(),true)) ;
    }

    /**
     * Méthode de construction, appelée par l'ensemble des constructeurs de WildBrick
     * @param i_diceModelList    Liste des modèles WILD de l'élément parent
     * @param wildTokens    Identifiant uniques de l'élément parent
     * @param i_parentDiceBrick    WildBrick parent
     * @param i_diceNode    Noeud d'invocation
     * @throws Exception
     */
    public synchronized void initialize(WildModelList i_diceModelList, String[] wildTokens, WildBrick i_parentDiceBrick, Node i_diceNode) throws Exception{
        // Le noeud d'appel est forcément renseigné
        diceNode = i_diceNode;
        if(diceNode!=null)diceName = (String)xml_getFirstValue(diceNode,"./@ident");
        parentDescription = (String)xml_getFirstValue(diceNode,"./@description");
        if(parentDescription==null)parentDescription = xml_getText(diceNode);
        // Traitement des cas hérités (WildComponent, WildObject, WildMethod) --- Entorse à  la règle "cas spécifiques en classe fille"
        if(i_parentDiceBrick!=null){
            parentDiceBrick = i_parentDiceBrick ;
            wildModelList = parentDiceBrick.getDiceModelList();
        // La création d'un WildBrick est forcément accompagnée d'une création de Token
            wildTokens = parentDiceBrick.getTokens() ;
            wildToken = new WildToken(wildTokens, (String[])cast_mergeArrays(parentDiceBrick.getArrayWilds(), new String[]{diceName}));
        }else{
        // Traitement des cas non hérités (WildScenario)
            wildModelList = i_diceModelList.deepClone() ;
            wildToken = new WildToken(wildTokens,new String[]{diceName});
        }
        buildJars();
        diceNode = i_diceNode ;
        execFolder = conf_translate((String)wildModelList.getProperty("execution_path"), this) ;
        execTempFolder = conf_translate((String)wildModelList.getProperty("execution_temp_path"), this) ;
        wildLogger = new WildLogger(this);
        // Construction des éléments enfants
        if(diceNode!=null)setSub();

    }

    /**
     * Ajout des modèles au contexte de chargement wildClassLoader
     */
    protected void buildJars(){
        wildClassLoader = wildModelList.getWildClassLoader();
    }

    public List<String> getModelsList(){
        return this.wildModelList.getModelsList();
    }

    /**
     * Passage d'un objet instancié, pour appel de méthode, à  l'élément
     * @param i_currentObjectInstance    Objet instancié, répondant à  la classe abstraite WildClass, transmis
     */
    public void setCurrentObjectInstance(WildClass i_currentObjectInstance){
        currentObjectInstance = i_currentObjectInstance ;
    }

    /**
     * Récupération de l'objet instancié, notamment pour les objects
     * @return
     */
    public WildClass getCurrentObjectInstance(){
        return currentObjectInstance ;
    }

    /**
     * Récupératon des noms d'éléments WILD (généalogie)
     * @return
     */
    public String[] getArrayWilds() {
        return wildToken.getArrayWild() ;
    }

    /**
     * Récupératon des noms d'éléments WILD (généalogie) avec modèles et objects séparés
     * @return
     */
    public String[] getWildGenes() {
        String[] wildToks = wildToken.getArrayWild() ;
        String[] toRet = new String[wildToks.length+1];
        for(int i = 0 ; i < wildToks.length ; i++){
            if(i==2){
                String[] sepMod = wildToks[i].split("\\.");
                toRet[i] = sepMod[0] ;
                toRet[i+1] = sepMod[1] ;
            }else if (i>2){
                toRet[i+1] = wildToks[i] ;
            }else{
                toRet[i] = wildToks[i] ;
            }
        }
        return toRet ;
    }

    /**
     * Récupératon des identifiants uniques
     * @return
     */
    public String[] getTokens(){
    	return wildToken.getArrayTokens();
    }

    /**
     * Récupération de la liste de modèole prétraitée
     * @return
     */
    public WildModelList getDiceModelList() {
        return wildModelList;
    }

    /**
     * Construction des éléments enfants
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws SecurityException
     * @throws ClassNotFoundException
     */
    protected void setSub() throws Exception{
            HashMap<Integer,Node> listNodes = wildModelList.getSub(this);
            if(listNodes == null)return;
            methodX = 1 ;
            Set<Integer> kS = listNodes.keySet();
            // Par reflexion, instanciation d'un nouveau WildBrick de la sous classe fille
            for(Integer k:kS)subList.put(methodX++, (WildBrick) Class.forName("fr.wild.orchestra."+listNodes.get(k).getNodeName()).getConstructors()[0].newInstance(new Object[] {listNodes.get(k), this}));
            methodX = 0 ;
    }

    /**
     * Récupération du point d'avancement, du parent, lors de la construction du sous élément
     * Utile lorsque plusieurs WildBrick de même nom WILD sont appelés par un même Dice Runnable
     * @return Numéro d'ordre
     */
    public Integer getMethodXParentProgress() {
        if(parentDiceBrick!=null)return parentDiceBrick.getMethodX();
        return null;
    }

    /**
     * Numéro d'ordre du sous élément en cours, lors de la construction des éléments enfants
     * @return
     */
    protected Integer getMethodX() {
        return methodX;
    }

    /**
     * Appel générique de l'interface Callable
     * Commun à  tous les DiceBricks : appelle execute_ThreadManager()
     */
    public synchronized ConcurrentHashMap<String,Object> call(){
        threadName = Thread.currentThread().getName() ;
        try {
            return execute_threadsManager() ;
        } catch (Exception e) {
            this.setOnError(wildLogger.solveError(e,null, null));
            this.wildLogger.logError(e);
        }
        return null;
    }

    /**
     * Méthode d'exécution de l'élément et de ses sous éléments
     * @return    Map contenant le produit des exécutions
     * @throws Exception
     */
    protected synchronized ConcurrentHashMap<String, Object> execute_threadsManager() throws Exception {
        currentLastSubElements = new ArrayList<Integer>();

        // Exécution de l'élément en cours. Cette fonction est spécifique à  la class de runnable
        logParameters(true);
        execute_beforeLoop();
        // Traitement des éléments enfants

        for(Integer i=1; i<= subList.size(); i++)if(!onError){
        // Passage des arguments dans tous les cas
            subList.get(i).setCurrentObjectInstance(currentObjectInstance);
            if(getArrayWilds().length == 3) subList.get(i).setParameters(getParentParameters());
            else subList.get(i).setParameters(parametersWith.getParameters());
                currentLastSubElements.add(i);
                if(checkCondition(i)){
                    subList.get(i).execute_threadsManager();
                    setParameters(map_merge(new HashMap<String,Object>(parametersWith.getParameters()),subList.get(i).getParameters(),true));
                    LinkedHashMap<String, HashMap<String, Object>> ret_outputs = subList.get(i).getOutputs() ;
                    if(!onError)onError = subList.get(i).isOnError();
                    if(onError&&errorMessage==null)errorMessage = subList.get(i).getError();
                    if(ret_outputs!=null)for(String k:ret_outputs.keySet())parametersWith.put(k, ret_outputs.get(k));
                    filterParameters();
                    if(ret_outputs!=null)outputs.putAll(ret_outputs);
                    WildClass getRetObj = subList.get(i).getCurrentObjectInstance();
                    if(getRetObj!=null)parametersWith.putNext(subList.get(i).getName(), getRetObj);
                    currentLastSubElements.clear();
                }
                closeAllBricks();
        }
        execute_afterLoop();
        logParameters(false);
        return cast_Map2ConcurrentHashMap(getParameters());
    }

    /**
     * Affichage des paramètres lors des exécutions si le paramétrage l'autorise
     * @param inOrOut true endébut d'exécution, false en fin d'exécution
     * @throws Exception
     */
    protected void logParameters(Boolean inOrOut) throws Exception{
        if(wildModelList.getProperty("log_verbose_showParameters")==null||!(Boolean) wildModelList.getProperty("log_verbose_showParameters"))return;
        if(inOrOut){
            if(parentDiceBrick!=null)wildLogger.logMap(parametersWith.getParameters_hash(), "Outcoming parameters for : "+this.getName()+" ["+parentDiceBrick.getLastElement()+"]");
            else wildLogger.logMap(parametersWith.getParameters_hash(), "Outcoming parameters for : "+this.getName()+".");
        }else{
            if(parentDiceBrick!=null)wildLogger.logMap(parametersWith.getParameters_hash(), "Incoming parameters for : "+this.getName()+" ["+parentDiceBrick.getLastElement()+"]");
            else wildLogger.logMap(parametersWith.getParameters_hash(), "Incoming parameters for : "+this.getName()+".");
        }

    }

    /**
     * En V0.3 l'exécution est bloquée en mode parallèle
     * @return FALSE
     */
    public boolean allowParallel() {
        return false;
    }

    /**
     * Méthode générique, développée dans les classes filles
     * @throws Exception
     */
    protected synchronized void execute_beforeLoop() throws Exception {
    }

    /**
     * Méthode générique, développée dans les classes filles
     * @throws Exception
     */
    protected synchronized void execute_afterLoop() throws Exception {
    }

    /**
     * Ecriture des paramètres en début d'exécution de brique
     */
    protected void setParametersInitString(){
            parametersInitString = parametersWith.getString();
    }

    /**
     * Création d'un chaîne contenant les paramètres en début d'exécution de la brique
     * @return chaîne contenant les paramètres
     */
    public String getParametersInitString(){
        return parametersInitString ;
    }

    /**
     * Dernier identifiant unique (= identifiant unique de la brique en cours)
     * @return
     */
    public String getLastToken() {
        return wildToken.getLastToken();
    }

    /**
     * Numéro du dernier élément traité dans la chaîne d'exécution
     * @return
     */
    public Integer getLastElement(){
        Integer iMax = 0;
        for(Integer i:this.currentLastSubElements)if(i>iMax)iMax=i;
        return iMax ;
    }

    /**
     * Récupération des "outputs" variables directement transmises des objets instanciés aux scénarios
     * @return Liste et valeur des variables
     */
    public  LinkedHashMap<String, HashMap<String, Object>> getOutputs(){
        if(!checkedOutput){
            checkedOutput = true;
            outputs = new LinkedHashMap<String, HashMap<String, Object>>();
            for(Integer k:subList.keySet()){

                String outputNames_suff = (parentDiceBrick!=null) ? diceName+"["+parentDiceBrick.getLastElement()+"].":diceName+"." ;

                LinkedHashMap<String, HashMap<String, Object>> outdB = subList.get(k).getOutputs();
                Set<String> outdBK = outdB.keySet();
                for(String kout:outdBK)outputs.put(outputNames_suff+kout,outdB.get(kout));
        }}
        return outputs ;
    }

    /**
     * Filtre des paramètres, notamment des outputs, en supprimant ceux d'objets précédents (en doublon car réindexés)
     */
    @SuppressWarnings("unchecked")
    protected void filterParameters(){
        LinkedHashMap<String, Object> toRet_mod = new LinkedHashMap<String,Object>(parametersWith.getParameters()) ;
        for(String kStr : toRet_mod.keySet()){
            Boolean write = false ;
            if(toRet_mod.get(kStr).getClass().getSimpleName().toLowerCase().contains("hashmap")){
                HashMap<String,Object> toRet_in = (HashMap<String,Object>)toRet_mod.get(kStr);
                if(toRet_in.get("output")!=null&&(Boolean)(toRet_in.get("output"))){
                    for(Integer k:subList.keySet())if(kStr.startsWith(subList.get(k).getName()+"["))write=true ;
                }else if(kStr!=null){
                     write = true ;
                }
                if(!write)parametersWith.remove(kStr);
        }}
    }

    /**
     * Place la brique "en erreur" : flag + stockage de l'erreur d'exécution
     * @param map
     */
    public void setOnError(Map<String, String> map) {
        onError = true ;
        errorMessage = map;
        if(parentDiceBrick!=null)parentDiceBrick.setOnError(map);
    }

    /**
     * Récupération de l'erreur d'exécution, valeur + description
     * @return
     */
    public Map<String, String> getError(){
        return errorMessage;
    }

    /**
     * L'exécution a-t-elle été msie en erreur ?
     * @return true si erreur
     */
    public Boolean isOnError() {
        return onError ;
    }

    /**
     * Récupère le nom "court", sans le préfixe du modèel pour les objets
     * @return
     */
    public String getShortName() {
        String[] spl = this.diceName.split("\\.");
        return spl[spl.length-1] ;
    }

    /**
     * Méthode de destruction des objects instanciés, afin de libérer de la mémoire
     */
    public void closeBrick(){
        this.currentObjectInstance = null ;
    }

    /**
     * Méthode de destruction des objects instanciés, afin de libérer de la mémoire
     * Implique, pour les composants, la destruction des objets enfants
     */
    public void closeAllBricks(){
        if(getArrayWilds().length == 2)for(Integer i:subList.keySet())subList.get(i).closeBrick();
    }

    public void freeThread(){
        //Thread.currentThread().interrupt();
    }

    /**
     * Récupération du nom du modèle de la brique
     * @return
     */
    public String getModelName() {
        if(this.modelName!=null)return modelName ;
        if(this.parentDiceBrick!=null&&this.parentDiceBrick.modelName!=null)return this.parentDiceBrick.modelName ;
        return this.getModel().getName();
    }

    /**
     * Traitement des conditions associées au noeud de la brique
     * Si @if n'existe pas : return true
     * Si @if existe, il faut que la condition soit remplie : return true
     * @param i
     * @return
     */
    public Boolean checkCondition(Integer i){
        WildBrick wB = subList.get(i) ;
        String value = null;
        try {
            value = xml_getFirstValue(wB.getNode(), "./@if");
            if(value==null||value.length()==0)return true ;

            // Condition : il n'y a pas de produit
            if(value.equals("!#6D#isProduct#6D#")){
                if(outputs.isEmpty())return true ;
                return false ;
            }

            // Condition : il y a au moins un produit
            if(value.equals("#6D#isProduct#6D#")){
                if(!outputs.isEmpty())return true ;
                return false ;
            }
            try{
                Object newV = outputs.get(value);
                if(newV==null)return false;
                if(newV.getClass().getSimpleName().equals("Boolean")&&((Boolean)newV))return true ;
                if(newV != null)return true ;

            }catch(Exception e){}
        } catch (DOMException | XPathExpressionException | SAXException
                | IOException | ParserConfigurationException e) {}
        return true ;
    }

    /**
     * Récupération du noeud
     * @return
     */
    public Node getNode(){
        return this.diceNode;
    }

    /**
     * Setter de nom (notamment pour accès directs via Wild4Test)
     * @param i_diceName
     */
    public void setName(String i_diceName){
        diceName = i_diceName;
    }

	/**
	 * Récupération de la liste d'erreur rencontrées pour la brique
	 */
    public HashMap<String,HashMap<String,String>> getErrorList(){
    	return wildLogger.getErrorList();
    }


}