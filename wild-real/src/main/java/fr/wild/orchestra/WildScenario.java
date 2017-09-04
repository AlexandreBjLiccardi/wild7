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

import static fr.wild.common.Connector.*;
import static fr.wild.common.IoCommons.*;
import static fr.wild.common.IoFileSystem.*;
import static fr.wild.common.IoWilds.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.wild.hibernate.ExecutionChainsaw;
import fr.wild.hibernate.ExecutionConsumption;
import fr.wild.hibernate.ExecutionInfo;

/**
 * Elément d'exécution "Scénario" (scénario) Correspond à une série d'appels de
 * composants : une exécution lance un scénario
 *
 * @author ONEMA/DCIE/ALi
 * @version WILD0.1a "Ambivalence"
 */
public class WildScenario extends WildBrick {

    private ExecutionChainsaw execution; // Exécution appelant le scénario

    private String archiveFolder; // Chemin du fichier dans lequel est archivé l'exécution
    private String phpFolder_in; // chemin php d'accueil des fichiers
    private String phpFolder_out; // chemin php de sortie des fichiers
    private HashMap<String, Integer> estimated_resources; // Ressources estimées nécessaires pour l'exécuiondu scénario
    private HashMap<String, HashMap<String, Object>> products; // Map des produits (fichiers ou variables expressément remontées)
    private String summary = null; // Chaîne de caractère transformable en noeud XML, qui contient un résumé de tous les éléments d'exécution pour le scénario

    private String pgArrayProducts; // Chaîne de caractère contenant les résultats de produits, avant affectation au répertoire de sortie, pour écriture dans la table archive de postgresql


    private SessionFactory connect_dicePg ; // Connexion à la base de données postgresql (schéma [dice_workshop]).
    private Session db_sub ; // Session de connexion à l'intérieur d'une boucle

    /**
     * Accède à la session hibernate
     */
    public Session getConnect(){
        return this.getNewConnect();
    }

    /**
     * Crée une nouvelle connexion vers la base de donnée, utilise hibernate
     * @return session hibernate
     */
    public Session getNewConnect(){
        if(db_sub!=null&&db_sub.isOpen())return db_sub ;
        db_sub = connect_dicePg.openSession();
        return this.db_sub ;
    }

    public void closeConnect(){
        if(db_sub!=null&&db_sub.isOpen())db_sub.close();
    }

    /**
     * Génération d'une chaîne de caractère comportant toutes les inforamtions
     * relatives à l'exécution du scénario Utilisée comme LOG
     *
     * @return Chaîne de caractère transformable en noeud XML, qui contient un
     *         résumé de tous les éléments d'exécution pour le scénario
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String getSummary() throws Exception {
        if (summary != null)
            return summary;
        db_sub = getNewConnect();
        ExecutionConsumption eC = getDbEntity(db_sub,
                ExecutionConsumption.class, execution.getIdExecution()); // Consommation du scénario
        HashMap<String, Object> results;
        Short status = (execution.getStatus().getIdStatus() == 7 || execution
                .getStatus().getIdStatus() == 5) ? 100 : execution.getStatus()
                .getIdStatus(); // On affiche les statuts comme "finis" si à 7
        String dateEnd = (execution.getDateEnd() == null) ? date_now()
                : cast_xmlEscape(execution.getDateEnd());
        String xmlStrand = "<Execution " + "idUser = \""
                + cast_xmlEscape(execution.getUser().getIdUser()) + "\" "
                + "idExecution = \""
                + cast_xmlEscape(execution.getIdExecution()) + "\" "
                + "datePost = \"" + cast_xmlEscape(execution.getDatePost())
                + "\" " + "dateBegin = \""
                + cast_xmlEscape(execution.getDateBegin()) + "\" "
                + "dateEnd = \"" + dateEnd + "\" " + "archivePath = \""
                + getArchiveFolder() + "\" " + "status = \"" + status + "\""
                + ">" + "<DiceBricks>";
        // // Analyse des scénarios
        xmlStrand += "<WildScenario ident = \"" + getName()
                + "\" description = \"" + cast_xmlEscape(getInfo("description")) + "\">";
        results = getParameters();
        // Analyse des "retours"
        if (results != null) {
            Set<String> ks = results.keySet();
            if (ks.size() == 1)
                xmlStrand += (cast_Map2XmlString(
                        (HashMap<String, Object>) results.get(ks.iterator()
                                .next()), "Return", getName()));
            else if (ks.size() > 1)
                xmlStrand += (cast_Map2XmlString(results, "Return", getName()));
        }
        // Analyse des paramètres d'invocation
        String parametersInit = getParametersInitString();
        if (parametersInit != null)
            xmlStrand += parametersInit;
        xmlStrand += "</WildScenario>";
        // // Analyse des composants
        LinkedHashMap<Integer, WildComponent> dC = getComponents();
        Set<Integer> ki = dC.keySet();
        for (Integer i : ki) {
            xmlStrand += "<WildComponent ident = \"" + dC.get(i).getName()
                    + "\" order =\"" + (i + 1) + "\"";
            String descr_scenario = dC.get(i).getParentDescription();
            if (descr_scenario != null && descr_scenario.length() > 0)
                xmlStrand += " description_scenario = \""
                        + cast_xmlEscape(descr_scenario) + "\"";
            String descr_component = (String) dC.get(i).getInfo("description");
            if (descr_component != null && descr_scenario.length() > 0)
                xmlStrand += " description = \""
                        + cast_xmlEscape(descr_component) + "\"";
            xmlStrand += ">";
            // Pour chaque composant, on analyse les "retours"
            results = dC.get(i).getParameters(false);
            if (results != null) {
                Set<String> ks = results.keySet();
                if (ks.size() == 1)
                    xmlStrand += (cast_Map2XmlString(
                            (HashMap<String, Object>) results.get(ks.iterator()
                                    .next()), "Return", cast_2String(i)));
                else if (ks.size() > 1)
                    xmlStrand += (cast_Map2XmlString(results, "Return",
                            cast_2String(i)));
            }
            String parametersInit_comp = dC.get(i).getParametersInitString();
            if (parametersInit_comp != null)
                xmlStrand += parametersInit_comp;
            xmlStrand += "</WildComponent>";
        }
        xmlStrand += "</DiceBricks>";
        // // Analyse des produits
        xmlStrand += "<Products>";
        HashMap<String, HashMap<String, Object>> products = getProducts(true); // à     "true" pour un lien vers le répertoire de productions
        if (products != null) {
            Set<String> ksp = products.keySet();
            for (String k : ksp) xmlStrand += cast_Map2XmlString(products.get(k), "Product", k);
        }
        xmlStrand += "</Products>";
        // // Analyse des Outputs
        /*xmlStrand += "<Outputs>";
        HashMap<String, HashMap<String, Object>> outputs = getOutputs();
        Set<String> ks = outputs.keySet();
        for (String k : ks) xmlStrand += cast_Map2XmlString(outputs.get(k), "Output", k);
        xmlStrand += "</Outputs>";*/
        // // Analyse des ressources
        xmlStrand += "<Resources expectedRAM = \"" + getMinRAM()
                + "\" expectedROM = \"" + getMinROM() + "\" expectedCPU = \""
                + getMinCPU() + "\" "; // Attendues d'une part
        if (eC != null)
            xmlStrand += "effectiveRAM = \"" + (float) eC.getEstimatedRam()
                    / (float) (1024 * 1024) + "\" effectiveROM = \""
                    + (float) eC.getEstimatedRom() / (float) (1024 * 1024)
                    + "\" effectiveCPU = \"" + eC.getEstimatedCpu() + "\""; // Consommées
                                                                            // d'autre
                                                                            // part
        xmlStrand += "/>";
        // // Analyse des informations complémentaires d'exécution
        xmlStrand += "<Infos>";
        for (ExecutionInfo executionInfo : getDbList(db_sub,
                ExecutionInfo.class,
                "where id_execution = '" + execution.getIdExecution()
                        + "' order by date_message"))
            xmlStrand += "<Info dateMessage = \""
                    + executionInfo.getDateMessage()
                    + "\" criticity = \"" + executionInfo.getCriticity().getIdCriticity()
                    + "\" >" + executionInfo.getContents() + "</Info>";
        xmlStrand += "</Infos>";
        // // Affichage des erreurs
        xmlStrand += "<Errors>";
        // Produits manquants
        if (products != null) {
            Set<String> ksp = products.keySet();
            for (String k : ksp)
                if (products.get(k) != null
                        && products.get(k).get("type") != null
                        && products.get(k).get("type").equals("FilePath")
                        && products.get(k).get("available") != null
                        && !(boolean) products.get(k).get("available")) {
                    HashMap<String, Object> exception = getDiceModelList()
                            .getException(this, "ProductAvailabilityFailure");
                    xmlStrand += "<Error criticity = \""
                            + exception.get("criticity")
                            + "\" type = \"product\" description = \""
                            + cast_xmlEscape(exception.get("description"))
                            + "\">"
                            + (String) cast_xmlEscape(exception.get("value"))
                                    .replaceAll(
                                            "\\{A\\}",
                                            (String) ((String) products.get(k)
                                                    .get("value")).replaceAll(
                                                    getExecTempFolder(),
                                                    "products").replaceAll(
                                                    "\\\\", "/"))
                            + "[@ident = " + k + " @ description = "
                            + (String) products.get(k).get("description") + "]"
                            + "</Error>";
                }
        }
        // Erreurs d'exécution
        String errors_exec = cast_Map2XmlString(getError(), "Error", getName()
                + "_fatal");
        if (errors_exec != null)
            xmlStrand += errors_exec;
        xmlStrand += "</Errors>";
        xmlStrand += "</Execution>";
        summary = xmlStrand;
        return xmlStrand;
    }

    /**
     * Accesseur au chemin d'archive
     *
     * @return chemin d'archive
     */
    public String getArchiveFolder() {
        return archiveFolder;
    }

    /**
     * Accesseur au chemin de fichier où PHP dépose les fichiers
     *
     * @return chemin de fichier où PHP dépose les fichiers
     */
    public String getPhpFolder_in() {
        return phpFolder_in;
    }

    /**
     * Accesseur au chemin de fichier où PHP récupère les fichiers
     *
     * @return chemin de fichier où PHP récupère les fichiers
     */
    public String getPhpFolder_out() {
        return phpFolder_out;
    }

    /**
     * Récupération de l'exécution d'envoi du scénario
     *
     * @return Exécution d'envoi du scénario
     */
    public ExecutionChainsaw getExecution() {
        return execution;
    }

    // // Traitement des ressources
    /**
     * Identification des ressources permettant le fonctionnement d'un scénario
     * Etablie selon les descriptifs WILD
     *
     * @return Liste ("MinRAM", "MinROM","MinCPU") contenant les informations
     *         nécessaires aux lancement du scénario (ressource minimales
     *         requises)
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public HashMap<String, Integer> getResources()
            throws XPathExpressionException, SAXException, IOException,
            ParserConfigurationException {
        if (estimated_resources == null) {
            estimated_resources = wildModelList.getModel("scenarios")
                    .getResources(diceName);
            String[] resources = { "minRAM", "minROM", "minCPU" };
            for (String resource : resources)
                if (estimated_resources.get(resource) == null
                        || estimated_resources.get(resource) == 0)
                    estimated_resources.put(resource, (Integer) wildModelList
                            .getProperty("standard_scenario_" + resource));
        }
        return estimated_resources;
    }

    /**
     * Détail RAM minimale pour lancement du scénario
     *
     * @return
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public Integer getMinRAM() throws XPathExpressionException, SAXException,
            IOException, ParserConfigurationException {
        return getResources().get("minRAM");
    }

    /**
     * Détail ROM minimale pour lancement du scénario
     *
     * @return
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public Integer getMinROM() throws XPathExpressionException, SAXException,
            IOException, ParserConfigurationException {
        return getResources().get("minROM");
    }

    /**
     * Détail CPU (estimé) minimal pour lancement du scénario
     *
     * @return
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public Integer getMinCPU() throws XPathExpressionException, SAXException,
            IOException, ParserConfigurationException {
        return getResources().get("minCPU");
    }

    /**
     * Identification de l'exécution
     *
     * @return Identifiant d'exécution
     */
    public String getExecutionId() {
        return execution.getIdExecution();
    }

    /**
     * Constructeur
     *
     * @param i_service_DiceModelList
     *            Liste des modèles (WildModel) pré-chargeant l'ensemble des
     *            classes et composants accessibles (fonctionnalités appelables)
     * @param i_execution
     *            Exécution passée depuis la liste d'exéctuion (WildExecList),
     *            contenant les informations d'appel
     * @throws Exception
     */
    public WildScenario(WildModelList i_service_DiceModelList,
            ExecutionChainsaw i_execution,
            SessionFactory i_connect_dicePg) throws Exception {
        connect_dicePg = i_connect_dicePg ;
        execution = i_execution;
        initialize(
                i_service_DiceModelList,
                new String[] { i_execution.getUser().getIdUser(),
                        i_execution.getIdExecution() }, null,
                cast_String2Node((String) execution.getInfoIn()));
        archiveFolder = conf_translate(wildModelList.getProperty("archive_path"),
                this);
        phpFolder_out = conf_translate(
                wildModelList.getProperty("execution_php_path"), execution, this)
                + File.separator + "out";
        phpFolder_in = conf_translate(
                wildModelList.getProperty("execution_php_path"), execution, this)
                + File.separator + "in";
        file_delete(execFolder);
        file_delete(execTempFolder);
    }

    /**
     * Passage des paramètres pour actualisation depuis les informations
     * d'exécution Filtré selon le modèle, avec balises "Invokation"
     *
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void setParameters() throws Exception {
        // parametersWith = new ConcurrentHashMap<String,
        // Object>(wildModelList.getModel("scenarios").getInvokation(diceName,
        // xml_getFirstNode(diceNode,"./Invokation"), null));
        Set<String> ks = parametersWith.keySet();
        for (String k : ks) {
            HashMap<String, Object> innerMap = (HashMap<String, Object>) parametersWith
                    .get(k);
            if (innerMap.get("type").equals("FilePath")) {
                innerMap.put("value", this.execTempFolder + File.separator
                        + innerMap.get("value"));
                parametersWith.put(k, innerMap);
            }
        }

    }

    /**
     * Récupération de la liste des composants, sous forme instanciés, pour
     * récupération des informations internes
     *
     * @return sous éléments DiceComponents
     */
    public LinkedHashMap<Integer, WildComponent> getComponents() {
        LinkedHashMap<Integer, WildComponent> toRet = new LinkedHashMap<Integer, WildComponent>();
        Set<Integer> ki = this.subList.keySet();
        for (Integer i : ki)
            toRet.put(i, (WildComponent) subList.get(i));
        return toRet;
    }

    /**
     * Récupération des produits d'exécution (fichiers ou variables expressément
     * remontées) Par défaut, les chemins vers les fichiers pointent vers le
     * dossier temporaire
     *
     * @return
     */
    public HashMap<String, HashMap<String, Object>> getProducts() {
        return getProducts(true);
    }

    /**
     * Récupération des produits d'exécution (fichiers ou variables expressément
     * remontées).
     * Les produits sont cherchés dans les outputs ou dans les paramètres d'invocation.
     *
     * @parameter forExport true : vers le dossier temporaire ; false : vers les
     *            produits
     * @return
     */
    @SuppressWarnings("unchecked")
    public HashMap<String, HashMap<String, Object>> getProducts(
            Boolean forExport) {
        if (products == null) {
            products = wildModelList.getModel("scenarios").getProducts(this.diceName);
            Set<String> ks = products.keySet();
            LinkedHashMap<String, HashMap<String, Object>> outputs = getOutputs() ;
            for (String k : ks) {
                HashMap<String, Object> productsSub = products.get(k);
                Boolean exportFile = false ;
                try {
                    if(productsSub.get("exportFile")!=null)exportFile = cast_2Boolean((String)productsSub.get("exportFile"));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if(((String) productsSub.get("useFrom")).equals("#6D#isProduct#6D#")){
                    Boolean isProduct = false ;
                    if(products.size()>1)isProduct = true ;
                    productsSub.put("value", isProduct);
                    products.put(k, productsSub);
                }else if (parametersWith != null&& productsSub != null&& parametersWith.get((String) productsSub.get("useFrom")) != null) {
                    productsSub.put("value",((HashMap<String, Object>) parametersWith.get((String) productsSub.get("useFrom"))).get("value"));
                    products.put(k, productsSub);
                    if(exportFile)
                        try {
                            file_write(this.execFolder+File.separator+"products"+File.separator+k+".txt", cast_2String(productsSub)) ;
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                }else if(outputs.get((String) productsSub.get("useFrom"))!=null&&productsSub!=null){
                    productsSub.put("value",(outputs.get((String) productsSub.get("useFrom"))).get("value"));
                    products.put(k, productsSub);
                    if(exportFile)
                        try {
                            String LINE_SEP = "\n";
                            file_write(this.execFolder+File.separator+"products"+File.separator+k+".txt", cast_2String(productsSub)) ;
                            if(productsSub.get("value")!=null&&productsSub.get("value").getClass().getSimpleName().endsWith("List")){
                                ArrayList<Object> productsSubValue_asList =  (ArrayList<Object>) productsSub.get("value") ;
                                String csv_export = "Produit de l'exécution de : "+productsSub.get("useFrom")+LINE_SEP+LINE_SEP+productsSub.get("description")+LINE_SEP;
                                for(Object val_obj:productsSubValue_asList)csv_export+=(LINE_SEP+cast_2StringDecode(val_obj));
                                file_write(this.execFolder+File.separator+"products"+File.separator+k+".csv",csv_export,"windows-1252");
                            }
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                }
            }
        }
        if (!forExport)return products;
        Set<String> ksp = products.keySet();
        HashMap<String, HashMap<String, Object>> toRet = new HashMap<String, HashMap<String, Object>>();
        for (String k : ksp) {
            HashMap<String, Object> val = (HashMap<String, Object>) products
                    .get(k);
            try {
                if (val.get("type")!=null&&val.get("type").equals("FilePath"))
                    val.put("value",
                            ((String) val.get("value")).replaceAll(
                                    getExecTempFolder(), "products")
                                    .replaceAll("\\\\", "/"));
            } catch (Exception e) {
                String extraMsg = ((String) products.get(k).get("description") != null) ? "Identifiant : "
                        + k
                        + " {"
                        + (String) products.get(k).get("description") + "}"
                        : "Identifiant : " + k;
                wildLogger.logError(e, extraMsg);
            }
            toRet.put(k, val);
        }
        return toRet;
    }

    /**
     * Récupération des produits d'exécution (fichiers ou variables expressément
     * remontées) sous forme de tableau [ident, value]
     *
     * @return
     */
    public String[] getArrayProducts() {
        String[] toRet = new String[products.size()];
        Integer i = 0;
        for (String k : products.keySet())
            toRet[i++] = k + "#6D#" + products.get(k).toString();
        return toRet;
    }

    /**
     * Récupération des produits d'exécution (fichiers ou variables expressément
     * remontées) sous forme de tableau [ident, value] ramenés à une chaîne de
     * caractère lisible par postgresql
     *
     * @return
     */
    public String getpgArrayProducts() {
        if (pgArrayProducts != null)
            return pgArrayProducts;
        String toRet = "{";
        int i = 0 ;
        for (String k : products.keySet()) {
            if(!toRet.equals("{"))toRet += ",";
            toRet += '"' + k + "#6D#" + cast_2String(products.get(k).get("value")) + '"';
            if(i++>100)break;
        }
        return toRet += "}";
    }

    /**
     * Sauvegarde des produits (fichiers ou variables expressément remontées)
     *
     * @throws IOException
     */
    public void saveProducts() throws IOException {
        products = getProducts(false);
        if (pgArrayProducts == null)
            pgArrayProducts = getpgArrayProducts();
        if (products != null) {
            for (String k : products.keySet())
                if (products.get(k).get("type")!=null&&products.get(k).get("type").equals("FilePath")) {
                    HashMap<String, Object> product = products.get(k);
                    try {
                        File source = new File(execTempFolder
                                + File.separator
                                +(String) products.get(k).get("value"));
                        if (source.exists()) {
                            File dest = new File(
                                    execFolder
                                            + File.separator+"products"+File.separator
                                            + ((String) products.get(k).get(
                                                    "value")).replaceAll(
                                                    execTempFolder, "products"));
                            dest.getParentFile().mkdirs();
                            file_copy(source, dest);
                            product.put("available", true);
                        } else {
                            product.put("available", false);
                        }
                        products.put(k, product);
                    } catch (Exception e) {
                        String extraMsg = ((String) products.get(k).get(
                                "description") != null) ? "Identifiant : " + k
                                + " {"
                                + (String) products.get(k).get("description")
                                + "}" : "Identifiant : " + k;
                        wildLogger.logError(e, extraMsg);
                        product.put("available", false);
                        if (product.get("value") == null)
                            product.put("value",
                                    "Adresse non renvoyée par l'exécution.");
                    }
                }
        }
    }

    /**
     * Suppression des fichiers temporaires d'exécution
     */
    public void dropFiles() {
        try {
            file_delete(this.execFolder);
            file_delete(this.execTempFolder);
            file_delete(this.phpFolder_in);
            file_delete(this.phpFolder_out);
        } catch (Exception e) {
        }

    }

    /**
     * Récupération des information issues de l'amorce (noeud fourni par
     * l'exécution)
     *
     * @param string
     * @return
     */
    public Object getInfoAmo(String string) {
        try {
            return xml_getFirstValue(diceNode, "@" + string);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Construction des sources (analyse de l'amorce DiceScenario fournie par
     * l'exécution, vérification sur le disque)
     *
     * @param execution
     *            Exécution d'envoi du scénario
     * @param filesDescriber
     *            Map de description des objets décompressés récupérés dans le
     *            dossier de dépôt PHP
     * @return liste des erreurs rencontrées
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<Map<String, String>> buildSources(ExecutionChainsaw execution,ConcurrentHashMap<String, Object> filesDescriber) throws Exception {
        HashMap<String, HashMap<String, Object>> param_invoke = new HashMap<String, HashMap<String, Object>>();
        List<Map<String, String>> sourcesErrors = new ArrayList<Map<String, String>>();
        String extraMsg = null;
        try {
            HashMap<String, HashMap<String, Object>> filesDescriber_rex = new HashMap<String, HashMap<String, Object>>();
            Set<String> ks_fd = filesDescriber.keySet();
            for (String k : ks_fd)filesDescriber_rex.put(k,(HashMap<String, Object>) filesDescriber.get(k));
            Node infoIn_node = cast_String2Node(execution.getInfoIn());
            HashMap<String, String> revertedUseAsSource = new HashMap<String, String>();
            HashMap<String, Object> paramGetter = new HashMap<String, Object>();

            // Traitement des paramètres remontés via une balise <Source>
            HashMap<String, HashMap<String, Object>> sources = wild_nodeList(infoIn_node, "Source");
            if (sources != null) {
                NodeList subSources = xml_getNodes(cast_String2Node(execution.getInfoIn()), "//Source");
                for (int i = 0; i < subSources.getLength(); i++) {
                    WildSourceType typeVal = new WildSourceType(subSources.item(i), this);
                    paramGetter.put(typeVal.getUseAs(), typeVal.getValue());
                    revertedUseAsSource.put(typeVal.getUseAs(), "Source#6D#" + (i + 1));
                    if (typeVal.isOnError()) {
                        this.setOnError(typeVal.getFileSourceError());
                        if(typeVal.getFileSourceErrors()!=null)sourcesErrors.addAll(typeVal.getFileSourceErrors());
                        for(String error:typeVal.getListError()){
                            extraMsg = "Missing file for "+cast_2String(subSources.item(i))+". {"+error+"}";
                            sourcesErrors.add(wildLogger.solveError("SourceParserException",extraMsg));
                            wildLogger.logError("SourceParserException",extraMsg);
                        }
                            //throw new Exception(error);
                    }
                }
            }

            // Traitement des paramètres remontés via une balise <Invokation>
            HashMap<String, HashMap<String, Object>> invokers = wild_nodeList(
                    infoIn_node, "Invokation/Parameter");
            if (invokers != null)
                for (String k : invokers.keySet()) {
                    String keyUseAs = (String) sources.get(k).get("useAs");
                    String value = (String) invokers.get(k).get("value");
                    revertedUseAsSource.put(keyUseAs, k);
                    paramGetter.put(keyUseAs, value);
                }
            // Calage sur l'invokation WILD du scenario (oubli de type dans l'amorce, récupération de métadonnées)
            String newInvokeNode = "<Invokation>";

            for (String k : revertedUseAsSource.keySet())newInvokeNode += "<Parameter ident='" + k + "' useAs ='" + k+ "'/>";
            newInvokeNode += "</Invokation>";
            HashMap<String, Object> param_invoke_o = (wildModelList.getModel("scenarios").getInvokation(diceName,cast_String2Node(newInvokeNode), paramGetter));
            if (param_invoke_o == null){
                extraMsg = "Unable to find invoke for " + this.diceName + ". Node : " + cast_2String(newInvokeNode);
                this.setOnError(wildLogger.solveError("SourceParserException", extraMsg));
                sourcesErrors.add(wildLogger.solveError("SourceParserException",extraMsg));
                wildLogger.logError("SourceParserException",extraMsg);
            }
            if (param_invoke_o != null)for (String k : param_invoke_o.keySet()) {
                param_invoke.put(k,(HashMap<String, Object>) param_invoke_o.get(k));
                param_invoke = map_update(param_invoke, k, "value",paramGetter.get(k));
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.setOnError(wildLogger.solveError(e, extraMsg));
            wildLogger.logError(e, extraMsg);
            return sourcesErrors;
        } finally {
            for (String k : param_invoke.keySet())parametersWith.put(k, param_invoke.get(k));
            if(sourcesErrors.isEmpty()&&(new File(getExecTempFolder()).exists())&&(Boolean)cast_2Boolean(getInfoAmo("joinInput")))file_copy(getExecTempFolder(), getExecFolder()+File.separator+"inputs");
        }
        return sourcesErrors;
    }

    /**
     * Passage des paramètres pour actualisation depuis les informations
     * d'exécution Filtré selon le modèle, avec balises "Invokation" ou
     * "Sources" Les répertoires de type "FilePath" pour les invocations et
     * "*file" pour les sources sont suffixés du répertoire d'exécution
     *
     * @param i_parametersWith
     *            Tableau de paramètre contenant la liste des paramètres et
     *            leurs valeurs
     * @throws Exception
     */
    public void setParameters(HashMap<String, Object> i_parametersWith)
            throws Exception {
        Node test = xml_getFirstNode(diceNode, "./Invokation");
        if (test != null) {
            HashMap<String, Object> param_invoke = (wildModelList
                    .getModel("scenarios").getInvokation(diceName, test,
                    i_parametersWith));
            if (i_parametersWith != null)
                for (String k : param_invoke.keySet())
                    parametersWith.put(k, param_invoke.get(k));
        } else {
            if (i_parametersWith != null)
                for (String k : i_parametersWith.keySet())
                    parametersWith.put(k, i_parametersWith.get(k));
        }
        setParametersInitString();
    }

    /**
     * Passage des paramètres pour actualisation depuis les informations
     * d'exécution Filtré selon le modèle, avec balises "Invokation"
     *
     * @param i_parametersWith
     *            Map d'exécution permettant la mise à jour
     * @throws Exception
     */
    public void setParameters(ConcurrentHashMap<String, Object> i_parametersWith)
            throws Exception {
        setParameters(new HashMap<String, Object>(i_parametersWith));
    }

    /**
     * Passage des paramètres pour actualisation depuis les informations
     * d'exécution Filtré selon le modèle, avec balises "Invokation"
     *
     * @param i_parametersWith
     *            Map d'exécution permettant la mise à jour
     * @throws Exception
     */
    public void setParameters(LinkedHashMap<String, Object> i_parametersWith)
            throws Exception {
        setParameters(new HashMap<String, Object>(i_parametersWith));
    }

    /**
     * Récupération du logger
     * @return
     */
    public WildLogger getLogger() {
        return this.wildLogger;
    }

    /**
     * Sélection d'un fichier XSL de transforamtion du getSummary()
     * Si lm'attribut @xslForSummary n'est pas resneigné : on va chercher le summary_generator par défaut
     * @return
     */
    public String get_xslForSummary(){
        String diceXSLt = null ;
        try {
            diceXSLt = (String) getInfo("xslForSummary");
        } catch (Exception e) {}
        if(diceXSLt == null)return "configuration/summary_generator.xsl";
        else return (String)wildModelList.getProperty("wild_path") +File.separator+"scenarios"+File.separator+diceXSLt;
    }
    
}