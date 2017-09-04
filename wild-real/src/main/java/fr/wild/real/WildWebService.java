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
package fr.wild.real;

// Dépendances wild.orchestra obligatoires
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.wild.common.IoCommons;
import fr.wild.common.IoFileSystem;
import fr.wild.orchestra.WildObject;
import fr.wild.utils.URLException;

import static fr.wild.real.WildWebService.PROP_BEHAVIOR_OUTPUTFILE;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// Dépendances, bibliothèques JAVA par exemple.
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.GZIPInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


/**
 * Code généré automatiquement par l'outil Wild
 * Client d'un service fournisseur d'un flux web
 */
public abstract class WildWebService extends WildService{
	
	private ExecutorService taskPoolService = Executors.newCachedThreadPool(); // Liste des callables (DiceScenarios) à exécuter, dans le framework Executor
    private CompletionService<InputStream> taskCompletionService = new ExecutorCompletionService<InputStream>(taskPoolService); // Jobs de compression / décompression ou scénarios à exécuter
    private Future<InputStream> subFuture_tolaunch ;

	protected InputStream openRichException(final URLConnection cnx, int timeout) throws Exception{
		try{
			subFuture_tolaunch = taskCompletionService.submit(openRichExceptionThreaded(cnx, timeout));
			return subFuture_tolaunch.get();
		}catch(Exception e){
			if(IoCommons.execution_isInterrupt(e))subFuture_tolaunch.cancel(true);
			throw e;
		}
	}
	
	/**
	 * Dernière requête.
	 */
	protected WildSoapService lastRequest = null;
	/**
	 * Code d’erreur du service.<br>
	 * Type : Integer<br>
	 * Code d’erreur renvoyé par le service.<br>
	 * <ul>
	 * <li>200 : succès</li>
	 * <li>30x : redirection</li>
	 * <li>40x : ressource non trouvée</li>
	 * <li>50x : erreur du service requêté</li>
	 * </ul>
	 */
	public static final String STAT_ERROR_SERVICE_CODE		= "errorServiceCode";
	/**
	 * Message d’erreur du service.<br>
	 * Type : String<br>
	 * Message retour du service lors d'une erreur
	 */
	public static final String STAT_ERROR_SERVICE_MESSAGE   = "errorServiceMessage";
	/**
	 * Noeud XML d’erreur du service.<br>
	 * Type : Dom <br>
	 * Message retour du service lors d'une erreur
	 */
	public static final String STAT_ERROR_SERVICE_NODE   = "errorServiceNode";
	/**
	 * Temps de réponse<br>
	 * Type : Long<br>
	 * Temps de réponse du service requêté, en ms.
	 */
	public static final String STAT_TIME_SERVICE			= "time";
	/**
	 * Succès<br>
	 * Type : Boolean<br>
	 * Succès de l’exécution de la requête
	 */
	public static final String STAT_SERVICE_SUCCESS			= "success";
	/**
	 * Taille de la réponse<br>
	 * Type : Long<br>
	 * Taille de la réponse renvoyée par le service requêté, en octets
	 */
	public static final String STAT_RESPONSE_SIZE			= "size";
	/**
	 * Structure valide<br>
	 * Type : Boolean<br>
	 * Le fichier produit n’est pas corrompu ou mal formé (et namespace pour les XML)
	 */
	public static final String STAT_TEST_STRUCTURE			= "testStructure";
	/**
	 * Nombre d’erreurs de structure<br>
	 * Type : Integer<br>
	 * Nombre d’erreurs de structure
	 */
	public static final String STAT_ERROR_NUMBER_STRUCTURE	= "errorNumberStructure";
	/**
	 * Schema valide<br>
	 * Type : Boolean<br>
	 * Le fichier produit n’est pas validé par un schéma XSD, ou des éléments
	 * apparaissent en trop, ou des éléments attendus n’apparaissent pas
	 */
	public static final String STAT_TEST_SCHEMA				= "testSchema";
	/**
	 * Nombre d’erreurs de schéma<br>
	 * Type : Integer<br>
	 * Nombre d’erreurs renvoyées par la validation XSD en échec,
	 * ou nombre d’élément ne satisfaisant pas à une liste.
	 */
	public static final String STAT_ERROR_NUMBER_SCHEMA		= "errorNumberSchema";
	/**
	 * Erreurs XSD<br>
	 * Type : Liste de Map<br>
	 * Liste des erreurs XSD
	 */
	public static final String STAT_ERROR_SCHEMA		    = "errorsSchema";
	/**
	 * Nombre d’éléments « clés »<br>
	 * Type : Long<br>
	 * Nombre de nœuds répondant à un xpath transmis et défini comme étant
	 * la « clé » du fichier, ou à un objet JSON.
	 */
	public static final String STAT_KEY_NUMBER				= "keyNumber";
	/**
	 * Erreurs éléments « clés »<br>
	 * Type : Liste de Map<br>
	 * Liste de nœuds répondant à un xpath transmis et défini comme étant
	 * la « clé » du fichier, ou à un objet JSON.
	 */
	public static final String STAT_ERROR_KEY		    = "errorsKey";
	/**
	 * Erreurs éléments « clés », indexés par un test spécifique<br>
	 * Type : Liste de Map<br>
	 * Liste de nœuds répondant à un xpath transmis et défini comme étant
	 * la « clé » du fichier, ou à un objet JSON.
	 */
	public static final String STAT_ERROR_CONDITION		    = "errorsTest";
	/**
	 * Format de réponse<br>
	 * Type : String<br>
	 * Type MIME du fichier renvoyé par le service requêté
	 */
	public static final String STAT_RESPONSE_FORMAT			= "responseFormat";
	/**
	 * Adresse du service<br>
	 * Type : String<br>
	 * Adresse URL (WSDL, URL http…)
	 */
	public static final String STAT_REQUEST_URL				= "requestURL";
	/**
	 * Entete soap envoyé<br>
	 * Type : Dom<br>
	 * Entete SOAP générer avec les paramètres utilisateur.
	 */
	public static final String STAT_REQUEST_SOAP_HEADER		= "requestSoapHeader";
	/**
	 * Paramètres de requête<br>
	 * Type : XML<br>
	 * Liste et valeurs des paramètres transmis par le client au service
	 */
	public static final String STAT_PARAMETERS_REQUEST_URL	= "parametersRequestURL";

	public static final String TYPE_BEHAVIOR	= "behavior";
	public static final String TYPE_REQUEST		= "request";
	public static final String TYPE_PARAMETER	= "parameter";
	public static final String TYPE_RESULT		= "result";
	public static final String TYPE_COOKIES		= "cookies";
	//protected static final List<String> intendedServices = null ;
	/**
	 * nodeKeyName<br>
	 * Type : String<br>
	 * Nom de l'élément de boucle initial - parse natif si JSON, xpath si XML
	 */
	public static final String PROP_RESULT_KEY				= "result.key";
	/**
	 * attributeNames<br>
	 * Type : List de String<br>
	 * chemins relatifs depuis l'élément de boucle initial,
	 * pour chacune des valeurs récupérées (parse natif si JSON, xpath si XML)
	 */
	public static final String PROP_RESULT_ATTRIBUTES		= "result.attributes";
	/**
	 * jsonObjectsLists< >
	 * Type : List de String<br>
	 * Liste des adresses de parse natif json pour cas JSON
	 */
	public static final String PROP_RESULT_TESTKEY			= "result.testKey";
	/**
	 * endPoint<br>
	 * Type : String{null}<br>
	 * EndPoint si différent de celui spécifié dans le WSDL
	 */
	public static final String PROP_REQUEST_ENDPOINT		= "request.endPoint";
	/**
	 * methodName/restMode<br>
	 * Type : String<br>
	 * <ul>
	 * <li>SOAP = Nom de la méthode SOAP appelée</li>
	 * <li>REST = Protocole GET ou POST</li>
	 * </ul>
	 */
	public static final String PROP_REQUEST_QUERY			= "request.query";
	/**
	 * nameSpace<br>
	 * Type : String{null}<br>
	 * Schéma namespace si différent de celui spécifié dans le WSDL
	 */
	public static final String PROP_REQUEST_NAMESPACE		= "request.namespace";
	/**
	 * urlConnexion<br>
	 * Type : String<br>
	 * <ul>
	 * <li>REST = URL de connexion</li>
	 * <li>SOAP = URL du WSDL connexion</li>
	 * </ul>
	 */
	public static final String PROP_REQUEST_URL				= "request.url";
	/**
	 * Path key<br>
	 * Type : String<br>
	 * Nom de la clé pour l'éventuelle requête d'une URI
	 */
	public static final String PROP_REQUEST_PATH			= "request.path";
	/**
	 * doOutput<br>
	 * Type : Boolean<br>
	 * Valeur par défaut : true<br>
	 */
	public static final String PROP_REQUEST_DOOUTPUT		= "request.doOutput";
	/**
	 * followRedirect<br>
	 * Type : Boolean<br>
	 * Valeur par défaut : true<br>
	 */
	public static final String PROP_REQUEST_FOLLOWREDIRECT	= "request.followRedirect";
	/**
	 * connection<br>
	 * Type : String<br>
	 * Valeur par défaut : Keep-Alive<br>
	 */
	public static final String PROP_REQUEST_CONNECTION		= "request.connection";
	/**
	 * contentType<br>
	 * Type : String<br>
	 * Valeur par défaut : application/x-www-form-urlencoded<br>
	 */
	public static final String PROP_REQUEST_CONTENTTYPE		= "request.contentType";
	/**
	 * charset<br>
	 * Type : String<br>
	 * Valeur par défaut : utf-8<br>
	 */
	public static final String PROP_REQUEST_CHARSET 		= "request.charset";
	/**
	 * useCaches<br>
	 * Type : Boolean<br>
	 * Valeur par défaut : false<br>
	 */
	public static final String PROP_REQUEST_USECACHES		= "request.useCaches";
	/**
	 * multipart<br>
	 * Type : Liste de byte[]<br>
	 * Valeur par défaut : null<br>
	 * fichier à joindre ne mode POST multi-part
	 */
	public static final String PROP_REQUEST_MULTIPART		= "request.multipart";
	/**
	 * request timeout<br>
	 * Type : Integer<br>
	 */
	public static final String PROP_REQUEST_TIMEOUT			= "request.timeOut";
	/**
	 * saveAsXml
	 * true : sauve les résultats de chaque test de service en XML zippé
	 */
	public static final String PROP_BEHAVIOR_XMLSAVE			= "behavior.saveAsXml";
	
	/**
	 * schemaFullResults
	 * true : sauve individuellement toute les erreurs rencontrées lors du parse XSD (volumineux)
	 * false : sauve synthèse par type d'erreur (moins volumineux)
	 */
	public static final String PROP_BEHAVIOR_XSDFULL			= "behavior.schemaFullResults";
	/**
	 * uniqId
	 * Idetifiant unique, fourni en entrée et/ou pa le add Quartz
	 */
		public static final String PROP_BEHAVIOR_UNIQID			= "behavior.uniqId";
	/**
	 * typeJson<br>
	 * Type : Integer<br>
	 * Type de test JSON
	 * <ul>
	 *	<li>1 : (par défaut). Tous les objets produits sont référencés dans la liste.</li>
	 *	<li>2 : Tous les éléments de la liste existent dans la réponse.</li>
	 *	<li>3 : Tous les éléments de la liste existent dans la réponse,
	 *         et tous les objets produits sont référencés dans la liste</li>
	 * </ul>
	 */
	public static final String PROP_BEHAVIOR_TESTJSON		= "behavior.testJson";
	/**
	 * serviceResponseFile<br>
	 * Type : String<br>
	 * Chemin d'accès au fichier contenant la réponse du flux
	 */
	public static final String PROP_BEHAVIOR_OUTPUTFILE		= "behavior.outputFile";
	/**
	 * outputFileMode<br>
	 * Type : String<br>
	 * Ecrasement du fichier de sortie ou mise à la suite.<br>
	 * "OVERRIDE" ou "APPEND"
	 */
	public static final String PROP_BEHAVIOR_OUTPUTFILEMODE	= "behavior.outputFileMode";
	/**
	 * xsdTesterFile<br>
	 * Type : String<br>
	 * Chemin d'accès au fichier XSD auquel on vérifie la conformité, cas d'une réponse XML
	 */
	public static final String PROP_BEHAVIOR_XSDCONTROL		= "behavior.xsdControl";
	/**
	 * urlCookie<br>
	 * Type : String<br>
	 * URL spécifique de connexion pour obtention du cookie
	 */
	public static final String PROP_COOKIES_URL				= "cookies.url";
	/**
	 * valuesCookiesStr<br>
	 * Type : String<br>
	 * Chaîne de caractères contenant les valeurs de cookies
	 */
	public static final String PROP_COOKIES_COOKIESVALUE	= "cookies.cookiesValue";
	/**
	 * varCookies<br>
	 * Type : List de String ou {null}<br>
	 * Nom éventuel des variables à retenir, si null les retient tous
	 */
	public static final String PROP_COOKIES_COOKIESLIST		= "cookies.cookiesList";
	/**
	 * Début d'exécution de la classe
	 */
	public static final String STAT_PROC_START_KEY		= "start_date";
	/**
	 * Fin d'exécution de la classe
	 */
	public static final String STAT_PROC_END_KEY		= "end_date";

	
	public static final String PROP_BEHAVIOR_UNIQ_TEST_ID		= "behavior.uniqId";
	protected String UNIQ_TEST_ID ; 
	

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildWebService(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected final Map<String,Object> results = new LinkedHashMap<>(); // Résultats élémentaires
	protected final Map<String,Object> stats = new LinkedHashMap<>(); // Statistiques d'exécution
	protected final List<Map<String,Object>> errors = new ArrayList<>(); // Erreurs rencontrées
	protected Boolean success; // Succès de l'opération
	protected Boolean isValid; // Validité du document de sortie
	protected String outputFilePath; // Chemin d'accès au fichier de récupération du flux


// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***
	//Paramètres sous forme de Map{}
	protected Map<String,Map<String,Object>> parametersMap = new HashMap<>();

	/**
	 * Code généré automatiquement par l'outil Wild
	 * Fonction d'initialisation, commune à tous les constructeurs.
	 * "Constructeur unique"
	 * NB. i_WILD_dObject est nécessairement passé
	 *
	 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
	 * @param i_clientId	Identifiant de l'utilisateur{}
	 * @param i_clientPwd	Mot de passe de l'utilisateur{}
	 * @param i_hostName	Nom de l'hôte{}
	 * @param i_hostIp	IP de l'hôte{}
	 */
	protected void WILD_initialize_WildWebService(
			WildObject i_WILD_dObject,
			String i_clientId,
			String i_clientPwd,
			String i_hostName,
			String i_hostIp
			) throws Exception {

		// Amorce de la classe
		// Initialisation de la classe d'objet selon le schéma Wild
		WILD_dObject = i_WILD_dObject ;
		WILD_initialize_WildService(i_WILD_dObject/*** Paramètres à actualiser ***/, i_clientId, i_clientPwd, i_hostName, i_hostIp);
		// Préparation des variables d'invocation (considérées comme champs globaux)
		this.clientId = i_clientId;
		this.clientPwd = i_clientPwd;
		this.hostName = i_hostName;
		this.hostIp = i_hostIp;

		// Mode try de récupération des erreurs pour log
		try{
			stats.put(STAT_PROC_START_KEY, IoCommons.date_now());
			stats.put(STAT_PROC_START_KEY+"_ts", IoCommons.date_nowLong());
		}catch(Exception e){
			this.WILD_logException(e);
		}
	}


// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
	/**
	 * Code généré automatiquement par l'outil Wild
	 * Constructeur, appelle nécessairement WILD_initialize()
	 * NB. i_WILD_dObject est nécessairement passé
	 *
	 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
	 * @param i_clientId	Identifiant de l'utilisateur{}
	 * @param i_clientPwd	Mot de passe de l'utilisateur{}
	 * @param i_hostName	Nom de l'hôte{}
	 * @param i_hostIp	IP de l'hôte{}
	 */
	public WildWebService(
			WildObject i_WILD_dObject,
			String i_clientId,
			String i_clientPwd,
			String i_hostName,
			String i_hostIp
			) throws Exception{
		this.WILD_initialize_WildWebService(i_WILD_dObject,i_clientId,i_clientPwd,i_hostName,i_hostIp);
	}

// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
	/**
	 * Code généré automatiquement par l'outil Wild
	 * Constructeur, appelle nécessairement WILD_initialize()
	 * NB. i_WILD_dObject est nécessairement passé
	 *
	 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
	 */
	public WildWebService(
			WildObject i_WILD_dObject
			) throws Exception{
		this.WILD_initialize_WildWebService(i_WILD_dObject,null,null,null,null);
	}

/*	### NOUVELLE METHODE ###
	Méthode : checkDatas - Contrôle des données obtenues par capture du flux{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

	/**
	 * Contrôle des données obtenues par capture du flux{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * <br>
	 * Produit les resultats :
	 * <ul>
	 * <li>testStructure</li>
	 * <li>errorNumberStructure</li>
	 * <li>testSchema</li>
	 * <li>errorNumberSchema</li>
	 * <li>keyNumber</li>
	 * <li>responseFormat</li>
	 * <li>responseSize</li>
	 * </ul>
	 *
	 * @param i_serviceResponseFile	Chemin d'accès au fichier contenant la réponse du flux{}
	 * @param i_xsdTesterFile	Chemin d'accès au fichier XSD auquel on vérifie la conformité, cas d'une réponse XML{}
	 * @param i_jsonObjectsLists	Liste des adresses de parse natif json pour cas JSON{}
	 * @param i_typeJson	Type de test JSON - 1,2 ou 3.{}
	 * @return	{}
	 */
	public Boolean checkDatas (
			String i_serviceResponseFile,
			String i_xsdTesterFile,
			List<String> i_jsonObjectsLists,
			Integer i_typeJson
			) throws Exception {
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		Boolean WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try{
			final String resultPath = getParameterValue(PROP_BEHAVIOR_OUTPUTFILE, i_serviceResponseFile, null);
			final File file = new File(resultPath);
			if (!file.exists()) {
				throw new Exception("Fichier de resultat manquant");
			}

			//	Etape	"1" : poids relatif de 10, Identification du cas de test (XML ou JSON)
			this.WILD_setStep(); // Ne pas modifier
			final String responseFormat = IoCommons.detectFormat(resultPath);
			stats.put(STAT_RESPONSE_FORMAT, responseFormat);

			//	Etape	"2" : poids relatif de 10, Vérification des prérequis
			this.WILD_setStep(); // Ne pas modifier

			//	Etape	"3" : poids relatif de 50, Exécution du test (++)
			this.WILD_setStep(); // Ne pas modifier

			//taille de la reponse
			stats.put(STAT_RESPONSE_SIZE, file.length());

			if ("application/xml".equals(responseFormat)) {
				//verification de la structure du XML
				final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				factory.setValidating(false);
				factory.setNamespaceAware(true);
				final DocumentBuilder builder = factory.newDocumentBuilder();
				final List<String> structErrors = new ArrayList<>();
				builder.setErrorHandler(new ErrorHandler() {
					@Override
					public void warning(SAXParseException exception) throws SAXException {}
					@Override
					public void error(SAXParseException exception) throws SAXException {
						structErrors.add(exception.getLocalizedMessage());
					}
					@Override
					public void fatalError(SAXParseException exception) throws SAXException {
						structErrors.add(exception.getLocalizedMessage());
					}
				});
				builder.parse(new File(resultPath));
				stats.put(STAT_TEST_STRUCTURE,			structErrors.isEmpty());
				stats.put(STAT_ERROR_NUMBER_STRUCTURE,	structErrors.size());
				if (!structErrors.isEmpty()) {
					WILD_setOutput("checkDatas_output1", structErrors);
				}

				final String xsdPath = getParameterValue(PROP_BEHAVIOR_XSDCONTROL, i_xsdTesterFile, null);
				final Boolean fullTest = IoCommons.param_getValue(this.parametersMap,PROP_BEHAVIOR_XSDFULL,null,false);
				if (xsdPath != null) {
					//test de validité XSD
					final WildXsdFile wildXsdFile = new WildXsdFile(WILD_dObject, xsdPath);
					wildXsdFile.validate(resultPath);
					final List<HashMap<String, Object>> errors = (fullTest)?wildXsdFile.DVP_getErrors(): wildXsdFile.DVP_getErrors_light();
					boolean testSchema = errors == null || errors.isEmpty();
					final int errorNumberSchema = testSchema ? 0 : errors.size();
					// Le test de validité ne porte pas sur les "erreurs" et les "warnings", bien que ces erreurs soient remontées
					if(testSchema == false){
						List causesExclusion = Arrays.asList(new String[]{"fatal"});
						testSchema = true ;
						for(HashMap error:errors)if(causesExclusion.contains(error.get("level"))){
							testSchema = false ;
							break;
						}
					}
					stats.put(STAT_TEST_SCHEMA,             testSchema);
					stats.put(STAT_ERROR_NUMBER_SCHEMA,		errorNumberSchema);
					stats.put(STAT_ERROR_SCHEMA, 			errors);
					if (testSchema) {
						WILD_setOutput("checkDatas_output2", errors);
					}
				}

				//récupération des clés, NOTE : pas de clés à tester pour XML
				long keyNumber = 0;
				stats.put(STAT_KEY_NUMBER, keyNumber);
			} else if ("application/json".equals(responseFormat)) {

				final int testJson = getParameterValue(PROP_BEHAVIOR_TESTJSON, i_typeJson, 1);
				final List<String> expectedPaths = getParameterValue(PROP_RESULT_TESTKEY, i_jsonObjectsLists, Collections.EMPTY_LIST);

				for(Integer i=0; i<expectedPaths.size(); i++)if(!expectedPaths.get(i).startsWith("//"))expectedPaths.add("//"+expectedPaths.get(i));		
				
				final ObjectMapper mapper = new ObjectMapper();
				final Map base = mapper.readValue(file, Map.class);

				final List<String> responsePaths = new ArrayList<>();
				listPaths(base, "/", responsePaths);

				int nbKey = 0;
				if (testJson==1 || testJson==3) {
					for (String path : responsePaths) {
						String modPath = path.replaceAll("\\[.*\\]","") ;
						if(!(expectedPaths.contains(modPath)||expectedPaths.contains(path))) {
							final Map<String,Object> error = new HashMap<>();
							error.put("message", "La réponse du serveur définie un Path qui n'est pas dans la liste : "+path);
							errors.add(error);
						} else {
							nbKey++;
						}
					}

				} 
				
				
				List<String> simplifiedPathes = new ArrayList();
				for (String path : responsePaths){
					String modPath = path.replaceAll("\\[.*\\]","") ;
					if(!simplifiedPathes.contains(modPath))simplifiedPathes.add(modPath);
				}
				
				if (testJson==2 || testJson==3) {
					for (String path : expectedPaths) {
						if(!(simplifiedPathes.contains(path)||simplifiedPathes.contains("//"+path))) {
							final Map<String,Object> error = new HashMap<>();
							error.put("message", "Path non trouvé dans la réponse du serveur : "+path);
							errors.add(error);
						} else {
							nbKey++;
						}
					}
				}

				// Ajout d'un test spécifique complémentaire (on remarque si une condition de déclassmeent existe)
				final String testJson_errorTester = getParameterValue("behavior.errorTester", null, null);
				final String testJson_errorReason = getParameterValue("behavior.errorReason", null, null);
				final List<Map<String,Object>> errors_spec = new ArrayList<>();
				Boolean onSpecError = false ;
				if(testJson_errorTester!=null&&testJson_errorTester.length()>0)for(Object baseKey:base.keySet()){
					final String[] conditions_test = {"\""+baseKey+"\":\""+base.get(baseKey)+"\"",baseKey+":\""+base.get(baseKey)+"\"","\""+baseKey+"\":"+base.get(baseKey),baseKey+":"+base.get(baseKey)};
					for(String condition:conditions_test)if(testJson_errorTester.equals(testJson_errorTester))onSpecError = true ;
					if(testJson_errorReason!=null&&testJson_errorReason.length()>0)if(baseKey.equals(testJson_errorReason)){
						final Map<String,Object> error = new HashMap<>();
						error.put("message", base.get(baseKey));
						errors_spec.add(error);	
					}
				}
				if(!errors_spec.isEmpty()&&onSpecError)stats.put(STAT_ERROR_CONDITION, errors_spec);	
				else if(onSpecError){
					final Map<String,Object> error = new HashMap<>();
					error.put("message", "Not specified");
					errors_spec.add(error);
					stats.put(STAT_ERROR_CONDITION, errors_spec);	
				}
				//récupération des clés
				stats.put(STAT_KEY_NUMBER, nbKey);
				stats.put(STAT_ERROR_KEY, errors);
			}


			//	Etape	"4" : poids relatif de 20, Compilation des résultats (+)
			this.WILD_setStep(); // Ne pas modifier


		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		
		return WILD_toReturn ; // Ne pas modifier
	}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
	/**
	 * Contrôle des données obtenues par capture du flux{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_serviceResponseFile	Chemin d'accès au fichier contenant la réponse du flux {}
	 * @param i_xsdTesterFile	Chemin d'accès au fichier XSD auquel on vérifie la conformité, cas d'une réponse XML {}
	 * @return	Succès de la validation (Boolean) {}
	 */
	public Boolean checkDatas (
			String i_serviceResponseFile,
			String i_xsdTesterFile
			)throws Exception{
		return checkDatas(i_serviceResponseFile,i_xsdTesterFile,null,null);
	}

/*	### NOUVELLE METHODE ###
	Méthode : parseDatas - Opération de reconnaissance et de lecture des données obtenues par capture du flux, construction d'une Map.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

	/**
	 * Opération de reconnaissance et de lecture des données obtenues par capture du flux, construction d'une Map.{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @param i_serviceResponseFile	Chemin d'accès au fichier contenant la réponse du flux{}
	 * @param i_nodeKeyName	nom de l'élément de boucle initial - parse natif si JSON, xpath si XML{}
	 * @param i_attributeNames	chemins relatifs depuis l'élément de boucle initial, pour chacune des valeurs récupérées (parse natif si JSON, xpath si XML){}
	 */
	public void parseDatas (
			String i_serviceResponseFile,
			String i_nodeKeyName,
			List<String> i_attributeNames
			)throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try{

			//	Etape	"1" : poids relatif de 10, Vérification des prérequis
			this.WILD_setStep(); // Ne pas modifier

			//	Etape	"2" : poids relatif de 50, Parse du fichier (++)
			this.WILD_setStep(); // Ne pas modifier

			final String filePath = getParameterValue(PROP_BEHAVIOR_OUTPUTFILE, i_serviceResponseFile, null);
			final String responseFormat = IoCommons.detectFormat(filePath);
			final String rootPath = getParameterValue(PROP_RESULT_KEY,i_nodeKeyName,null);
			final List<String> valuePaths = getParameterValue(PROP_RESULT_ATTRIBUTES, i_attributeNames, Collections.EMPTY_LIST);

			//recuperation des valeurs
			if ("application/xml".equals(responseFormat)) {
				Node base = IoCommons.xml_getDocument(filePath);

				if (rootPath!=null) {
					base = IoCommons.xml_getFirstNode(base, rootPath);
				}
				for (String xpath : valuePaths) {
					final ArrayList<String> values = IoCommons.xml_getValues(base, xpath);
					if	(values!=null && !values.isEmpty()) {
						final String[] segments = xpath.split("/");
						String resultKey = segments[segments.length-1];
						if (resultKey.startsWith("@") && segments.length>1) {
							resultKey = segments[segments.length-2] + resultKey;
						}
						results.put(resultKey, values);
					}
				}

			} else if ("application/json".equals(responseFormat)) {
				final ObjectMapper mapper = new ObjectMapper();
				Map base = mapper.readValue(new File(filePath), Map.class);
				
				if (rootPath!=null) {
					Object candidate = IoCommons.json_getFirstValue(base, rootPath);
					if(candidate instanceof Map) {
						base = (Map) candidate;
					} else {
						throw new Exception("L'element pour le chemin "+rootPath+" n'est pas un objet, valeur trouvée : "+candidate);
					}
				}
				for (String jpath : valuePaths) {
					final List<Object> values = IoCommons.json_getValues(base, jpath);
					if	(values!=null && !values.isEmpty()) {
						results.put(jpath, values);
					}
				}
			}

			//	Etape	"3" : poids relatif de 20, Compilation des résultats (+)
			this.WILD_setStep(); // Ne pas modifier

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
	}

/*	### NOUVELLE METHODE ###
	Méthode : getStats - Récupération des statistiques d'exécution de la capture du flux{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

	/**
	 * Récupération des statistiques d'exécution de la capture du flux{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public Map<String, Object> getStats ()throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		Map<String, Object> WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try{

			//	Etape	"1" : poids relatif de 10, Fourniture du résultat
			this.WILD_setStep(); // Ne pas modifier
			WILD_toReturn = stats;

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		return WILD_toReturn ; // Ne pas modifier
	}

/*	### NOUVELLE METHODE ###
	Méthode : getResult - Récupération d'un résultat élémentaire concernant la capture du flux{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

	/**
	 * Récupération d'un résultat élémentaire concernant la capture du flux{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @param i_resultName	nom du résultat élémentaire{}
	 * @return	{}
	 */
	public Object getResult (
			String i_resultName
			)throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		Object WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try{

			//	Etape	"1" : poids relatif de 10, Fourniture du résultat
			this.WILD_setStep(); // Ne pas modifier

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
			return WILD_toReturn ; // Ne pas modifier
		}
	}

/*	### NOUVELLE METHODE ###
	Méthode : getResultMap - Récupération des résultats élémentaires concernant la capture du flux{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

	/**
	 * Récupération des résultats élémentaires concernant la capture du flux{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public Map<String, Object> getResultMap ()throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		Map<String, Object> WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try{	

			//	Etape	"1" : poids relatif de 10, Fourniture du résultat
			this.WILD_setStep(); // Ne pas modifier
			WILD_toReturn = (results.isEmpty()) ? Collections.EMPTY_MAP : new LinkedHashMap<>(results);

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		return WILD_toReturn ; // Ne pas modifier
	}

	/**
	 * Vue non modifiable des parametres d'execution.
	 * 
	 * @return 
	 */
	public Map<String, Map<String, Object>> getParametersMap() {
		return Collections.unmodifiableMap(parametersMap);
	}

/*	### NOUVELLE METHODE ###
	Méthode : pgSaveTest - Sauvegarde des résultats élémentaires et des statistiques de test, dans une base distante PG{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

	/**
	 * Sauvegarde des résultats élémentaires et des statistiques de test, dans une base distante PG{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 */
	public void pgSaveTest ()throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try{
			stats.put(STAT_PROC_END_KEY, IoCommons.date_now());
			stats.put(STAT_PROC_END_KEY+"_ts", IoCommons.date_nowLong());
			HashMap<Integer,Object[]> full_log = new HashMap();
			HashMap<String,Object> test_executions = new HashMap();
			test_executions.put("id_test_executions", UNIQ_TEST_ID);
			test_executions.put("date_begin", stats.get(STAT_PROC_START_KEY));
			test_executions.put("date_end", stats.get(STAT_PROC_END_KEY));
			//newLine.put("interrupted", false);
			test_executions.put("fulltime", (Long)stats.get(STAT_PROC_END_KEY+"_ts")- (Long)stats.get(STAT_PROC_START_KEY+"_ts"));
			int i = 1;
			Long full_size = (long) 0;
			final  Boolean outPath = IoCommons.param_getValue(parametersMap,PROP_BEHAVIOR_XMLSAVE,null,false);
			Map<String,Integer> maxForService = new HashMap();

			for(Entry aStat:this.stats.entrySet())if(aStat.getValue()!=null&&aStat.getValue() instanceof HashMap){
				String[] radKey =  ((String)aStat.getKey()).split("\\.");
				if(radKey.length>1){
					Integer currentItemNb = (Integer) IoCommons.cast_2Object("Integer", radKey[0])[1];
					String keyItem = radKey[1];
					if(maxForService.get(keyItem)==null||maxForService.get(keyItem)<currentItemNb)maxForService.put(keyItem, currentItemNb);
				}
				Map<String, Object> currentSubTest = (Map) aStat.getValue();
				
			// Identification des tests réalisés
				if(currentSubTest.get(STAT_REQUEST_URL)!=null){
					Integer nb_errors = (currentSubTest.get(STAT_ERROR_NUMBER_SCHEMA)==null||(Integer)currentSubTest.get(STAT_ERROR_NUMBER_SCHEMA)==0)?
							(Integer)currentSubTest.get(STAT_ERROR_NUMBER_STRUCTURE)
							:(Integer)currentSubTest.get(STAT_ERROR_NUMBER_SCHEMA);
					full_size += 
							(currentSubTest.get(STAT_RESPONSE_SIZE)!=null)?(long)currentSubTest.get(STAT_RESPONSE_SIZE) :(long)0;
					
					HashMap<String,Object> test_service_executions = new HashMap();
					test_service_executions.put("id_test_service_executions",UNIQ_TEST_ID+"."+aStat.getKey());
					test_service_executions.put("id_test_executions",UNIQ_TEST_ID);
					test_service_executions.put("method_name",((String) aStat.getKey()).split("\\.")[((String) aStat.getKey()).split("\\.").length-1]);
					test_service_executions.put("date_begin",currentSubTest.get(STAT_PROC_START_KEY));
					test_service_executions.put("date_end",currentSubTest.get(STAT_PROC_END_KEY));
					test_service_executions.put("interrupted",false);
					test_service_executions.put("disk_size",currentSubTest.get(STAT_RESPONSE_SIZE));
					test_service_executions.put("element_size",currentSubTest.get(STAT_KEY_NUMBER));
					test_service_executions.put("fulltime",currentSubTest.get(STAT_TIME_SERVICE));
					test_service_executions.put("success",currentSubTest.get(STAT_SERVICE_SUCCESS));
					test_service_executions.put("schema_validity",(currentSubTest.get(STAT_ERROR_SCHEMA)==null||((List) currentSubTest.get(STAT_ERROR_SCHEMA)).isEmpty()));
					test_service_executions.put("schema_error_number",nb_errors);
					if(test_service_executions.get("business_validity")!=null)test_service_executions.put("business_validity", currentSubTest.get("validSandre"));
					if(test_service_executions.get("business_error_number")!=null)test_service_executions.put("business_error_number", currentSubTest.get("numberErrorsSandre"));
					test_service_executions.put("requested_url",currentSubTest.get(STAT_REQUEST_URL));
					test_service_executions.put("requested_schema",currentSubTest.get(PROP_BEHAVIOR_XSDCONTROL));
					if(test_service_executions.get("quesu")!=null)test_service_executions.put("requested_scenario","quesu");
					test_service_executions.put("response_format",STAT_RESPONSE_FORMAT);
					full_log.put(i++,new Object[]{"test_service_executions", test_service_executions});
				// Sauvegarde du summary XML
					if(outPath){
						final String fileout = getParameterValue(PROP_BEHAVIOR_OUTPUTFILE, null, null) +"/"+UNIQ_TEST_ID+"."+aStat.getKey()+".xml" ;
						IoFileSystem.file_write(fileout, IoCommons.cast_2String(IoCommons.cast_Map2Node(currentSubTest,"TEST")));
						IoFileSystem.file_zip(fileout,fileout+".zip",true);
						(new File(fileout)).delete();
					}
					// Sélection des paramètres
					
					if(currentSubTest.get(this.STAT_PARAMETERS_REQUEST_URL)!=null&&((Node) currentSubTest.get(this.STAT_PARAMETERS_REQUEST_URL)).getFirstChild()!=null){
						NodeList xmlParams = ((Node) currentSubTest.get(this.STAT_PARAMETERS_REQUEST_URL)).getFirstChild().getChildNodes();
						if(xmlParams!=null)for(Integer j=0;j<xmlParams.getLength();j++){
							HashMap<String,Object> test_service_parameters = new HashMap();
							Node param = xmlParams.item(j);
							NamedNodeMap paramAttrib = param.getAttributes();
							test_service_parameters.put("id_test_service_executions", test_service_executions.get("id_test_service_executions"));
							if(paramAttrib.getNamedItem("id")!=null)test_service_parameters.put("parameter_id",paramAttrib.getNamedItem("id").getNodeValue());
							if(paramAttrib.getNamedItem("position")!=null)test_service_parameters.put("parameter_position",paramAttrib.getNamedItem("position").getNodeValue());
							if(paramAttrib.getNamedItem("type")!=null)test_service_parameters.put("parameter_type",paramAttrib.getNamedItem("type").getNodeValue());
							String values ;
							if(param.hasChildNodes()){
								NodeList nLvalues = param.getChildNodes();
								values = "{";
								Integer valuesNb = 0 ;
								for(Integer k=0;k<nLvalues.getLength();k++){
									String curVal = "\""+nLvalues.item(k).getTextContent().trim().replaceAll("\"","\\\"")+"\"";
									if(nLvalues.item(k).getNodeName().toLowerCase().equalsIgnoreCase("value"))
										values +=( valuesNb++ ==0)? curVal : ","+curVal;
								}
								values += "}";
								if(values.equalsIgnoreCase("{}"))values = null;
								if(valuesNb==1)values=values.replaceAll("^\\{","").replaceAll("\\}$","");
							}else{
								values = param.getTextContent().trim();
							}	
							test_service_parameters.put("parameter_value",values);			
							full_log.put(i++,new Object[]{"test_service_parameters", test_service_parameters});
						}
					}
					

					// Sélection des résultats détaillés
					String[] toReport = new String[]{
							"numberSites",
							"errorNumberStructure",
							"errorNumberSchema",
							"keyNumber",
							"testStructure",
							"responseFormat",
							"time",
							"quesu",
							"size",
							"success",
							"errorServiceCode"};
					for(String nK:toReport)if(currentSubTest.containsKey(nK)){
						HashMap<String,Object> detailed_results = new HashMap();
						detailed_results.put("id_test_service_executions", test_service_executions.get("id_test_service_executions"));
						detailed_results.put("result_id", nK);
						detailed_results.put("result_position", "./"+nK);
						detailed_results.put("result_type", "result");
						detailed_results.put("result_value", currentSubTest.get(nK));
						full_log.put(i++,new Object[]{"detailed_results", detailed_results});
					}
			// Erreurs service
					if(currentSubTest.get(STAT_ERROR_SERVICE_NODE)!=null){
						HashMap<String,Object> schema_errors_service = new HashMap();
						schema_errors_service.put("id_test_service_executions", test_service_executions.get("id_test_service_executions"));
						
						Node xmlServerError = (Node) currentSubTest.get(this.STAT_ERROR_SERVICE_NODE);
						// Cas SOAP
						if(xmlServerError.getFirstChild().getNodeName().equalsIgnoreCase("soapenv:Body")){
							Node specific_error = IoCommons.xml_getFirstNode(xmlServerError.getFirstChild(), "//detail");
							String cdError = null;
							String msgError = "";
						// Sans doute à cause des xmlns, la lecture des noeud est hasardeuse => on force le parcours
						// Cas pour lequel on dispose du détail de l'erreur
							if(specific_error!=null&&specific_error.hasChildNodes()){
								NodeList specific_error_ls = specific_error.getChildNodes();		
								for(Integer l = 0 ; l < specific_error_ls.getLength() ; l++){
									NodeList specific_error_ls2 = specific_error_ls.item(l).getChildNodes();
									for(Integer l2 = 0 ; l2 < specific_error_ls2.getLength() ; l2++){
										if(specific_error_ls2.item(l2).getNodeName().equalsIgnoreCase("CdErreur"))cdError = (specific_error_ls2.item(l2).getTextContent().trim());
										if(specific_error_ls2.item(l2).getNodeName().equalsIgnoreCase("LocationErreur"))msgError = specific_error_ls2.item(l2).getTextContent().trim() + msgError;
										if(specific_error_ls2.item(l2).getNodeName().equalsIgnoreCase("DescriptifErreur"))msgError += ":" + specific_error_ls2.item(l2).getTextContent().trim();
									}
								}
							}else if(xmlServerError.getFirstChild()!=null&&xmlServerError.getFirstChild().getFirstChild()!=null){
							// Cas pour lequel on ne dispose pas du détail de l'erreur
								NodeList specific_error_ls2 = xmlServerError.getFirstChild().getFirstChild().getChildNodes();
								for(Integer l2 = 0 ; l2 < specific_error_ls2.getLength() ; l2++){
									if(specific_error_ls2.item(l2).getNodeName().equalsIgnoreCase("faultcode"))cdError = (specific_error_ls2.item(l2).getTextContent().trim());
									if(specific_error_ls2.item(l2).getNodeName().equalsIgnoreCase("faultstring"))msgError = specific_error_ls2.item(l2).getTextContent().trim();
								}
							}		
							schema_errors_service.put("error_line", 0);
							schema_errors_service.put("error_column", 0);
							schema_errors_service.put("error_level", "S.FATAL");
							schema_errors_service.put("error_message", msgError.trim());
							schema_errors_service.put("error_code", cdError.trim());
							full_log.put(i++,new Object[]{"schema_errors", schema_errors_service});		
						}
					}
					// Erreurs de structure
						if(currentSubTest.get(STAT_ERROR_SCHEMA)!=null){
							for(Object el:(ArrayList)currentSubTest.get(STAT_ERROR_SCHEMA)){
								HashMap<String,Object> hM = (HashMap)el;
								HashMap<String,Object> schema_errors_service = new HashMap();
								schema_errors_service.put("id_test_service_executions", test_service_executions.get("id_test_service_executions"));
								schema_errors_service.put("error_line", hM.get("line"));
								schema_errors_service.put("error_column", hM.get("column"));
								schema_errors_service.put("error_level", ((String) hM.get("level")).trim());
								schema_errors_service.put("error_message", ((String) hM.get("message")).trim());
								if(hM.get("error_code")!=null)schema_errors_service.put("error_code", (String)hM.get("error_code"));
								else schema_errors_service.put("error_code", ((String)hM.get("message")).split(":")[0].trim());
								full_log.put(i++, new Object[]{"schema_errors",schema_errors_service});
							}	
						}
					// Erreurs sandre
						if(currentSubTest.get("errorsSandre")!=null){
							for(Object el:(ArrayList)currentSubTest.get("errorsSandre")){
								HashMap<String,Object> hM = (HashMap)el;
								HashMap<String,Object> sandre_errors = new HashMap();
								sandre_errors.put("id_test_service_executions", test_service_executions.get("id_test_service_executions"));
								sandre_errors.put("error_line", hM.get("LigneErreur"));
								sandre_errors.put("error_column", hM.get("ColonneErreur"));
								sandre_errors.put("error_level", ((String) hM.get("SeveriteErreur")).trim());
								sandre_errors.put("error_message", ((String) hM.get("DescriptifErreur")).trim());
								sandre_errors.put("error_code", ((String) hM.get("CdErreur")).trim());
								full_log.put(i++, new Object[]{"sandre_errors",sandre_errors});
							}	
						}
				}}
			test_executions.put("fullsize", full_size);
			full_log.put(0,new Object[]{"test_executions", test_executions});
			
				Class.forName("org.postgresql.Driver");
				Connection opened_dbConnect ;
				opened_dbConnect = DriverManager.getConnection("jdbc:postgresql://"
						+ WILD_dObject.getDiceModelList().getProperty("db_host") 
						+":"+WILD_dObject.getDiceModelList().getProperty("db_port")
						+"/"+WILD_dObject.getDiceModelList().getProperty("db_dbName"),
						(String)WILD_dObject.getDiceModelList().getProperty("db_user"),
						(String)WILD_dObject.getDiceModelList().getProperty("db_passWord"));

				HashMap<String,HashMap<String,String>> mapTypper = IoCommons.pg_getSchemaDefs(opened_dbConnect, (String) WILD_dObject.getDiceModelList().getProperty("db_defaultSchema"));

				
				for(Integer ki=0;ki<full_log.size();ki++){
					Object[] aRow = full_log.get(ki);
					HashMap<String,Object> currentContains = (HashMap<String,Object>)aRow[1] ;
					String indexTest = (currentContains.get("id_test_service_executions")==null)?null:(String) currentContains.get("id_test_service_executions") ;
					String[] radTest = (indexTest==null)?null:indexTest.split("\\.");
					// Si le test n'est pas le dernier de la série, on ne l'enregistre pas : il existe un test qui "est allé plus loin"
					if(	indexTest!=null
							&& radTest.length==3
							&&!indexTest.equals(UNIQ_TEST_ID+"."+maxForService.get(radTest[2])+"."+radTest[2])){
						continue;
					}
					String order = IoCommons.pg_insertStr(mapTypper,(String) WILD_dObject.getDiceModelList().getProperty("db_defaultSchema"),(String)aRow[0],currentContains);
					Statement statement = null;
					try {
						statement = opened_dbConnect.createStatement();
						statement.executeUpdate(order);
					} catch (SQLException eSql) {
						eSql.printStackTrace();
					} finally {
						if (statement != null) statement.close();
					}

				}
				opened_dbConnect.close();

			//	Etape	"1" : poids relatif de 10, Vérification des prérequis
			this.WILD_setStep(); // Ne pas modifier

			//	Etape	"2" : poids relatif de 50, Passage de l'ordre SQL (++)
			this.WILD_setStep(); // Ne pas modifier
		
			
		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
	}

	/**
	 * Récupérer la valeur du parametre.
	 * 
	 * @param <T>
	 * @param name
	 * @param defaulValue valeur utilisé si le paramètre n'est pas défini
	 * @param override valeur de surcharge, si définie elle est retournée
	 * @return valeur ou valeur par défaut
	 */
	protected <T extends Object> T getParameterValue(String name, T override, T defaulValue) {
		return IoCommons.param_getValue(parametersMap, name, override, defaulValue);
	}

	/**
	 * Construction de l'URL en utilisant part ordre :
	 * <ul>
	 * <li>urlString</li>
	 * <li>hostIp</li>
	 * <li>hostName</li>
	 * </ul>
	 *
	 * @param urlString
	 * @return URL
	 */
	protected String createURL(String urlString) throws Exception {

		//creation de l'URL
		if (urlString == null) {
			if (hostIp != null) {
				urlString = "http://"+hostIp;
			} else if (hostName != null) {
				urlString = "http://"+hostName;
			}
		}
		if (urlString == null) {
			throw new Exception("URL du service non définie");
		}

		return urlString;
	}

	/**
	 * On recupere plus d'informations que la minimaliste IOException standard
	 * pour le debuggage.
	 *
	 * @param cnx
	 * @param timeout
	 * @return
	 * @throws IOException
	 * @throws InterruptedException 
	 */
    protected static Callable<InputStream> openRichExceptionThreaded(final URLConnection cnx, int timeout) throws Exception {
        
    	class CallableInputStream implements Callable<InputStream>{
    		final URLConnection cnx;
    		int timeout;
    		public CallableInputStream(final URLConnection i_cnx, int i_timeout){
    			cnx = i_cnx ;
    			timeout = i_timeout ;
    		}
			@Override
			public InputStream call() throws Exception {
				try {
		            cnx.setConnectTimeout(timeout);
		            cnx.setReadTimeout(timeout*2);
		            InputStream stream = cnx.getInputStream();
		            if ("gzip".equalsIgnoreCase(cnx.getContentEncoding())) {
		                return new GZIPInputStream(stream);
		            } else {
		                return stream;
		            }
		          
		        } catch(IOException ex) {
					throw new URLException(cnx, ex);
		        } catch(Exception ex) {
					throw ex ;
		        }
			}

    		
    	}
		return new CallableInputStream(cnx, timeout);
    }

	

	/**
	 * Liste de tous les xpath dans le json.
	 *
	 * @param jsonMap
	 * @param parentPath
	 * @param results
	 */
	private static void listPaths(Map<String,?> jsonMap, String parentPath, List<String> results) {

		for (Entry<String,?> entry : jsonMap.entrySet()) {
			final String key = entry.getKey();
			final Object value = entry.getValue();

			if (value instanceof Map) {
				results.add(parentPath+"/"+key);
				listPaths((Map)value,parentPath+"/"+key,results);
			} else if (value instanceof List) {
				final List lst = (List) value;
				for (int i=0,n=lst.size();i<n;i++) {
					final Object v = lst.get(i);
					if (v instanceof Map) {
						results.add(parentPath+"/"+key+"["+i+"]");
						listPaths((Map)v,parentPath+"/"+key+"["+i+"]",results);
					} else {
						results.add(parentPath+"/"+key+"["+i+"]");
					}
				}
			} else {
				results.add(parentPath+"/"+key);
			}

		}

	}

	public void DEV_dumpStats() throws DOMException, IOException, ParserConfigurationException, SAXException{
		String fileoutF = getParameterValue(PROP_BEHAVIOR_OUTPUTFILE, null, null) ;
		if(!new File(fileoutF).isDirectory())fileoutF = "summary.xml";
		else fileoutF += "/summary.xml";
		IoFileSystem.file_write(fileoutF, IoCommons.cast_2String(IoCommons.cast_Map2Node(stats,"TEST")));
	}
	
	public String DVP_getUniqId(){
		return this.UNIQ_TEST_ID;
	}
	
	public void DVP_carryOn() throws Exception{
		if((Exception)lastRequest.getStats().get("JAVA_ERROR")!=null){
			this.WILD_logException((Exception)lastRequest.getStats().get("JAVA_ERROR")); 
		}
		if((Exception)lastRequest.getStats().get("JAVA_ERROR")!=null&&
				IoCommons.execution_isInterrupt((Exception)lastRequest.getStats().get("JAVA_ERROR"))
			)throw (Exception)lastRequest.getStats().get("JAVA_ERROR");
	}
	public void DVP_dieWith() throws Exception{
		if((Exception)lastRequest.getStats().get("JAVA_ERROR")!=null)
			throw (Exception)lastRequest.getStats().get("JAVA_ERROR");
	}
	public List<String> getIntendedServices(){
		return null;
	}
}

