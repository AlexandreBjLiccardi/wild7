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

import fr.wild.utils.PostMultiPartObject;
import fr.wild.common.IoCommons;
import fr.wild.common.IoFileSystem;
import fr.wild.orchestra.WildObject;
import fr.wild.utils.DomNodeList;
import fr.wild.utils.StringUtils;
import fr.wild.utils.URLException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.concurrent.Callable;

import org.apache.commons.codec.binary.Base64;
import org.geotoolkit.math.XMath;
import org.geotoolkit.nio.IOUtilities;
import org.geotoolkit.temporal.object.ISODateParser;
import org.geotoolkit.temporal.object.TemporalUtilities;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * Code généré automatiquement par l'outil Wild
 * Webservice spécifique aux Agences de l'Eau
 */
public class WildAeService extends WildWebService implements Callable{
	private Boolean SandreErrorTimedOut = false ;
	private static final List<Entry<Double,String>> QUESUS = new ArrayList<>();
	private static final List<Entry<Double,String>> TARGET_NAMESPACES = new ArrayList<>();
	static {
		QUESUS.add(new AbstractMap.SimpleImmutableEntry<>(3.0, "http://xml.sandre.eaufrance.fr/scenario/quesu/3/sandre_sc_quesu.xsd"));
		QUESUS.add(new AbstractMap.SimpleImmutableEntry<>(2.1, "http://xml.sandre.eaufrance.fr/scenario/quesu/2.1/sandre_sc_quesu.xsd"));
		QUESUS.add(new AbstractMap.SimpleImmutableEntry<>(2.0, "http://xml.sandre.eaufrance.fr/scenario/quesu/2/sandre_sc_quesu.xsd"));
		QUESUS.add(new AbstractMap.SimpleImmutableEntry<>(1.0, "http://xml.sandre.eaufrance.fr/scenario/quesu/1/sandre_sc_quesu.xsd"));
		QUESUS.add(new AbstractMap.SimpleImmutableEntry<>(0.1, "http://xml.sandre.eaufrance.fr/scenario/sw_qu/1/sandre_sc_wsQualite.xsd"));
		TARGET_NAMESPACES.add(new AbstractMap.SimpleImmutableEntry<>(2.1, "http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2.1/Monitoring.wsdl"));
		TARGET_NAMESPACES.add(new AbstractMap.SimpleImmutableEntry<>(2.0, "http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2"));
		TARGET_NAMESPACES.add(new AbstractMap.SimpleImmutableEntry<>(1.1, "http://xml.sandre.eaufrance.fr/wsdl/Monitoring/1.1/Monitoring.wsdl"));
		TARGET_NAMESPACES.add(new AbstractMap.SimpleImmutableEntry<>(1.0, "http://xml.sandre.eaufrance.fr/wsdl/Monitoring/1/Monitoring.wsdl"));
	}


	/**
	 * Mode de traitement des requete, par lot ou une par une.<br>
	 * Type : Boolean
	 */
	public static final String PROP_BEHAVIOR_ALLATONCE = "behavior.allAtOnce";
	/**
	 * Type de test<br>
	 * Type : String<br>
	 * Valeur :
	 * <ul>
	 * <li>complet -> faire les tests SANDRE</li>
	 * <li>partiel -> pas de tests SANDRE</li>
	 * </ul>
	 *
	 */
	public static final String PROP_BEHAVIOR_TYPETEST = "behavior.typeTest";
	/**
	 * Nom du schema XSD utilisé par le service du sandre<br>
	 * Type : String<br>
	 */
	public static final String PROP_BEHAVIOR_SANDRE_XSD	= "behavior.sandreXsd";

	/**
	 * NAMESPACE dans le WSDL<br>
	 * Type : String<br>
	 * Version de NAMESPACE initialement demandé par le WSDL
	 */
	public static final String STAT_NAMESPACE_WSDL = "namespaceWsdl";
	/**
	 * Succès GetData<br>
	 * Type : Boolean<br>
	 * Succès de l’opération
	 * Requete : GetSites,GetSiteDescription,GetDataAvailability,getData
	 */
	public static final String STAT_SUCCESS = WildWebService.STAT_SERVICE_SUCCESS;
	/**
	 * Temps GetData<br>
	 * Type : Long<br>
	 * Temps de réponse
	 * Requete : GetSites,GetSiteDescription,GetDataAvailability,getData
	 */
	public static final String STAT_TIME = WildWebService.STAT_TIME_SERVICE;
	/**
	 * Taille de la réponse<br>
	 * Type : Long<br>
	 * Taille du fichier de réponse, en octets
	 */
	public static final String STAT_SIZE = WildWebService.STAT_RESPONSE_SIZE;
	/**
	 * Validité XSD<br>
	 * Type : Boolean<br>
	 * Requete : GetSites,GetSiteDescription,GetDataAvailability,getData
	 */
	public static final String STAT_XSD_VALID = WildWebService.STAT_TEST_SCHEMA;
	/**
	 * Erreurs XSD<br>
	 * Type : Liste de Map<br>
	 * Liste des erreurs XSD
	 * Requete : GetSites,GetSiteDescription,GetDataAvailability,getData
	 */
	public static final String STAT_XSD_ERRORS = WildWebService.STAT_ERROR_SCHEMA;
	/**
	 * Nombre d’erreurs XSD<br>
	 * Type : Long<br>
	 * Nombre d’erreurs XSD
	 * Requete : GetSites,GetSiteDescription,GetDataAvailability,getData
	 */
	public static final String STAT_XSD_NUMBER_ERRORS = WildWebService.STAT_ERROR_NUMBER_SCHEMA;
	/**
	 * Validité espaces de nommage<br>
	 * Type : Boolean<br>
	 * Succès si le xml de réponse est valide
	 * Requete : GetSites,GetSiteDescription,GetDataAvailability,getData
	 */
	public static final String STAT_VALID_XML = WildWebService.STAT_TEST_STRUCTURE;
	/**
	 * Validité SANDRE<br>
	 * Type : Boolean<br>
	 * Existe-t-il des erreurs SANDRE
	 * Requete : GetSites,GetSiteDescription,GetDataAvailability,getData
	 */
	public static final String STAT_SANDRE_VALID = "validSandre";
	/**
	 * Erreurs SANDRE<br>
	 * Type : Listde de Map<br>
	 * Liste des erreurs SANDRE
	 * Requete : GetSites,GetSiteDescription,GetDataAvailability,getData
	 */
	public static final String STAT_SANDRE_ERRORS = "errorsSandre";
	/**
	 * Nombre d’erreurs SANDRE<br>
	 * Type : Long<br>
	 * Nombre d’erreurs SANDRE
	 * Requete : GetSites,GetSiteDescription,GetDataAvailability,getData
	 */
	public static final String STAT_SANDRE_NUMBER_ERRORS = "numberErrorsSandre";
	/**
	 * QUESU <br>
	 * Type : String<br>
	 * Version de QUESU valide identifié
	 * Requete : GetSiteDescription,getData
	 */
	public static final String STAT_QUESU = "quesu";
	/**
	 * Nombre de sites<br>
	 * Type : Integer<br>
	 * Nombre d’éléments correspondant au noeud xpath ns:sites/ns:CdSite dans la	 réponse
	 * Requete : GetSites,GetSiteDescription,GetDataAvailability,getData
	 */
	public static final String STAT_NUMBER = "numberSites";


// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildAeService(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected String testMode; // Type de test soit complete soit partial
	protected Boolean allAtOnce; // Toutes les stations sont requêtées dans une même requête
	protected String scenarioVersion; // Version SANDRE du scenario
	protected Map<String,Map<String,Object>> scenarioInfos; // Répertoire des versions des codes et des URL SANDRE pour détermination automatique
	protected String xsd_control; // Chemain vers éventuel XSD de contrôle
	protected List<String> list_CdSite; // Liste des sites à requêter établie dynamiquement
	protected Date DateDebutDonnees; // Date de début des données pour récupération des analyses
	protected Date DateFinDonnees; // Date de fin des données pour récupération des analyses
	protected Integer n_codesSite; // Taille de l'échantillon de sites
	protected Map<String,String> outputFiles; // Chemin des fichiers de sortie de l'ensemble des dernières opérations opGet*

// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***

	/**
	 * Numero d'execution de la requete.
	 */
	private int requestNumber = 0;
	/**
	 * Parametres spécifique de la requete.
	 */
	private final Map<String,Map<String,Object>> overrideParameters = new HashMap<>();
	/**
	 * Target namespace valide pour le service.
	 */
	private String validTargetNamespace = null;
	/**
	 * Quesu valide pour le service.
	 */
	private String validQuesu = null;
	/**
	 * Identifiant des sites pour les tests.
	 */
	private final List<String> testSites = new ArrayList<>();


// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
	/**
	 * Code généré automatiquement par l'outil Wild
	 * Fonction d'initialisation, commune à tous les constructeurs.
	 * "Constructeur unique"
	 * NB. i_WILD_dObject est nécessairement passé
	 *
	 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
	 * @param i_parametersNode	Paramètres sous forme de nœud XML{}
	 * @param i_parametersXmlFile	Paramètres sous forme de fichier XML dont on donne le lien{}
	 * @param i_parametersMap	Paramètres sous forme de Map{}
	 */
	protected void WILD_initialize_WildAeService(
		WildObject i_WILD_dObject,
		Node i_parametersNode,
		String i_parametersXmlFile,
		Map<String,Object> i_parametersMap
			) throws Exception {

		// Amorce de la classe
		// Initialisation de la classe d'objet selon le schéma Wild
		WILD_dObject = i_WILD_dObject ;
		WILD_initialize_WildWebService(i_WILD_dObject,null,null,null,null);

		// Mode try de récupération des erreurs pour log
		try{	
			parametersMap.putAll( IoCommons.param_readParameters(i_parametersNode, i_parametersXmlFile, (Map)i_parametersMap) );
			UNIQ_TEST_ID = this.getParameterValue(PROP_BEHAVIOR_UNIQ_TEST_ID, null, this.WILD_dObject.getLastToken());
			this.WILD_Logger.WILD_setLastToken(UNIQ_TEST_ID);
			
			String newPath = getParameterValue(PROP_BEHAVIOR_OUTPUTFILE, null, "");
			String wkSpace = WILD_dObject.getDiceModelList().getProperty("qWorkspace_path") ;
			if(wkSpace==null||"/".equalsIgnoreCase(wkSpace))wkSpace="";
			if(newPath==null||newPath.length()==0) newPath = wkSpace+"/"+this.WILD_dObject.getDiceModelList().getProperty("qExec_path")+"/"+UNIQ_TEST_ID;
			parametersMap.put(PROP_BEHAVIOR_OUTPUTFILE,Collections.singletonMap("value", (Object) newPath ));
			File execDir = new File(newPath) ;
			execDir.mkdirs();

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
	 * @param i_parametersNode	Paramètres sous forme de nœud XML{}
	 */
	public WildAeService(
		WildObject i_WILD_dObject,
		Node i_parametersNode
			) throws Exception{
		this.WILD_initialize_WildAeService(i_WILD_dObject,i_parametersNode,null,null);
	}


// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
	/**
	 * Code généré automatiquement par l'outil Wild
	 * Constructeur, appelle nécessairement WILD_initialize()
	 * NB. i_WILD_dObject est nécessairement passé
	 *
	 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
	 * @param i_parametersXmlFile	Paramètres sous forme de fichier XML dont on donne le lien{}
	 */
	public WildAeService(
		WildObject i_WILD_dObject,
		String i_parametersXmlFile
			) throws Exception{
		this.WILD_initialize_WildAeService(i_WILD_dObject,null,i_parametersXmlFile,null);
	}


// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
	/**
	 * Code généré automatiquement par l'outil Wild
	 * Constructeur, appelle nécessairement WILD_initialize()
	 * NB. i_WILD_dObject est nécessairement passé
	 *
	 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
	 * @param i_parametersMap	Paramètres sous forme de Map{}
	 */
	public WildAeService(
		WildObject i_WILD_dObject,
		Map<String,Object> i_parametersMap
			) throws Exception{
		this.WILD_initialize_WildAeService(i_WILD_dObject,null,null,i_parametersMap);
	}

// CETTE METHODE DOIT ETRE MODIFIE.

	/**
	 * Sélection automatique du scénario SANDRE{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public String[] selectScenario() throws Exception{
		//Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		String[] WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try {

			// Etape	"1" : poids relatif de 10, Construction de l'ordre SOAP
			this.WILD_setStep(); // Ne pas modifier

			// Etape	"2" : poids relatif de 10, Test des différentes valeurs de version
			this.WILD_setStep(); // Ne pas modifier

			// Etape	"3" : poids relatif de 10, Interprétation du résultat
			this.WILD_setStep(); // Ne pas modifier

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		
		return WILD_toReturn ; // Ne pas modifier
	}

	public String opGetCapabilities ()throws Exception{
		WILD_echo("... getCapabilities");
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		String WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try{

			//	Etape	"1" : poids relatif de 10, Construction de l'ordre SOAP
			this.WILD_setStep(); // Ne pas modifier

			final String outPath = getParameterValue(PROP_BEHAVIOR_OUTPUTFILE, null, null) +"/getCapabilities.xml";

			final Map<String,Map<String,Object>> parameters = new HashMap<>(parametersMap);
			parameters.putAll(overrideParameters);
			parameters.put(PROP_REQUEST_QUERY, Collections.singletonMap("value", (Object)"getCapabilities"));
			parameters.put(PROP_BEHAVIOR_OUTPUTFILE, Collections.singletonMap("value", (Object)outPath));
			lastRequest = new WildSoapService(WILD_dObject, parameters);

			//	Etape	"2" : poids relatif de 10, Appel et capture du flux
			this.WILD_setStep(); // Ne pas modifier

			//	Etape	"3" : poids relatif de 10, Vérification du résultat
			this.WILD_setStep(); // Ne pas modifier

			//	Etape	"4" : poids relatif de 10, Sélection dans le résultat
			this.WILD_setStep(); // Ne pas modifier
			if (lastRequest.getStats().containsKey(STAT_SERVICE_SUCCESS)&&!(Boolean)lastRequest.getStats().get(STAT_SERVICE_SUCCESS)) {
				//le setHeader a causer une erreur
				WILD_toReturn = null;
			} else {
				WILD_toReturn = lastRequest.getDatas();
			}
			
			lastRequest.parseDatas(null, null, null);

			//	Etape	"5" : poids relatif de 10, Construction du fichier final
			this.WILD_setStep(); // Ne pas modifier
			
			stats.put(STAT_NAMESPACE_WSDL, lastRequest.DEV_getSchemaNamespace());

		}catch(Exception e){
			//this.WILD_logException(e); // Ne pas modifier
			//throw e;
			lastRequest.getStats().put("JAVA_ERROR", e);
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}

		return WILD_toReturn ; // Ne pas modifier
	}

	// CETTE METHODE DOIT ETRE MODIFIE.
	/**
	 * Récupération d'un échantillon de n_codesSite sites{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public String opGetSitesSubset ()throws Exception{
		WILD_echo("... getSites");
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		String WILD_toReturn = null ; // Ne pas modifier


		// Mode try de récupération des erreurs pour log
		try{
			final String outPath = getParameterValue(PROP_BEHAVIOR_OUTPUTFILE, null, null) +"/getSites.xml";

			// Etape	"1" : poids relatif de 10, Construction de l'ordre SOAP
			this.WILD_setStep(); // Ne pas modifier
			final Map<String,Map<String,Object>> parameters = new HashMap<>(parametersMap);
			parameters.putAll(overrideParameters);
			parameters.put(PROP_REQUEST_QUERY, Collections.singletonMap("value", (Object)"getSites"));
			parameters.put(PROP_BEHAVIOR_OUTPUTFILE, Collections.singletonMap("value", (Object)outPath));

			lastRequest = new WildSoapService(WILD_dObject, parameters);
			if (lastRequest.getStats().containsKey(STAT_SERVICE_SUCCESS)&&!(Boolean)lastRequest.getStats().get(STAT_SERVICE_SUCCESS)) {
				//le setHeader a causer une erreur
				return WILD_toReturn;
			}
			
			// Etape	"2" : poids relatif de 10, Appel et capture du flux
			this.WILD_setStep(); // Ne pas modifier
			WILD_toReturn = lastRequest.getDatas();
			if (!"200".equals(lastRequest.getStats().get(STAT_ERROR_SERVICE_CODE))) {
				//le getData a causé une erreur
				return WILD_toReturn;
			}
			lastRequest.parseDatas(null, null, null);

			// Etape	"3" : poids relatif de 10, Vérification du résultat
			this.WILD_setStep(); // Ne pas modifier
			String cdSitePath = "//*:Result/*:DescriptifSite/*:CdSite";
			//le fichier contient un champ result avec le vrai résultat en base64
			Element doc = IoCommons.xml_getDocument(outPath).getDocumentElement();
			final String base64 = IoCommons.xml_getFirstValue(doc,"//*:Result/text()");
			final Node base64_test = IoCommons.xml_getFirstNode(doc,cdSitePath);
			if (base64!=null&&base64_test==null) {
				final byte[] datas = Base64.decodeBase64(base64);
				IOUtilities.writeStream(new ByteArrayInputStream(datas), new File(outPath).toPath());
				cdSitePath = "//*:DescriptifSite/*:CdSite";
				doc = IoCommons.xml_getDocument(outPath).getDocumentElement();
			}
			// Etape	"4" : poids relatif de 10, Sélection dans le résultat
			this.WILD_setStep(); // Ne pas modifier

			final Object schemaState = lastRequest.getStats().get(STAT_TEST_SCHEMA);
			if (schemaState==null || Boolean.TRUE.equals(schemaState)) {
				testSites.clear();
				final List<String> candidates = IoCommons.xml_getValues(doc,cdSitePath);
				Integer nSites = getParameterValue("behavior.numberOfSites", null, 10) ;
				for (int i=0;i<nSites && !candidates.isEmpty();i++) {
					final int index = XMath.clamp( (int)(Math.random()*(candidates.size()-1)),0, candidates.size()-1);
					testSites.add(candidates.get(index));
				}
				lastRequest.getStats().put(STAT_NUMBER, candidates.size());
			}


			// Etape	"5" : poids relatif de 10, Construction du fichier final
			this.WILD_setStep(); // Ne pas modifier

		}catch(Exception e){
			//this.WILD_logException(e); // Ne pas modifier
			//throw e;
			lastRequest.getStats().put("JAVA_ERROR", e);
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}

		return WILD_toReturn ; // Ne pas modifier
	}

	// CETTE METHODE DOIT ETRE MODIFIE.
	/**
	 * Appel au service de description des sites{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public String opGetSiteDescription ()throws Exception{
		WILD_echo("... getSiteDescription");
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		// Variable générique de retour
		String WILD_toReturn = null ; // Ne pas modifier


		// Mode try de récupération des erreurs pour log
		try {
			final String outPath = getParameterValue(PROP_BEHAVIOR_OUTPUTFILE, null, null) +"/getSiteDescription.xml";

			// Etape	"1" : poids relatif de 10, Construction de l'ordre SOAP
			this.WILD_setStep(); // Ne pas modifier
			final Map<String,Map<String,Object>> parameters = new HashMap<>(parametersMap);
			parameters.putAll(overrideParameters);
			parameters.put(PROP_REQUEST_QUERY, Collections.singletonMap("value", (Object)"getSiteDescription"));
			parameters.put(PROP_BEHAVIOR_OUTPUTFILE, Collections.singletonMap("value", (Object)outPath));

			lastRequest = new WildSoapService(WILD_dObject, parameters);
			if (lastRequest.getStats().containsKey(STAT_SERVICE_SUCCESS)&&!(Boolean)lastRequest.getStats().get(STAT_SERVICE_SUCCESS)) {
				//le setHeader a causer une erreur
				return WILD_toReturn;
			}

			// Etape	"2" : poids relatif de 10, Appel et capture du flux
			this.WILD_setStep(); // Ne pas modifier
			WILD_toReturn = lastRequest.getDatas();
			if (!"200".equals(lastRequest.getStats().get(STAT_ERROR_SERVICE_CODE))) {
				//le getData a causé une erreur
				return WILD_toReturn;
			}
			// Etape	"3" : poids relatif de 10, Vérification du résultat
			this.WILD_setStep(); // Ne pas modifier
			
			//le fichier contient un champ result avec le vrai résultat en base64
			final Element doc = IoCommons.xml_getDocument(outPath).getDocumentElement();
			final String base64 = IoCommons.xml_getFirstValue(doc,"//*:Result/text()");
			if (base64!=null) {
				final byte[] datas = Base64.decodeBase64(base64);
				IOUtilities.writeStream(new ByteArrayInputStream(datas), new File(outPath).toPath());
			}

			// Etape	"4" : poids relatif de 10, Sélection dans le résultat
			this.WILD_setStep(); // Ne pas modifier
			lastRequest.parseDatas(null, null, null);

			// Etape	"5" : poids relatif de 10, Construction du fichier final
			this.WILD_setStep(); // Ne pas modifier

		}catch(Exception e){
			//this.WILD_logException(e); // Ne pas modifier
			//throw e;
			lastRequest.getStats().put("JAVA_ERROR", e);
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		
		return WILD_toReturn ; // Ne pas modifier
	}

	// CETTE METHODE DOIT ETRE MODIFIE.
	/**
	 * Appel au service de disponibilité des données{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public String opGetDataAvailabilty ()throws Exception{
		WILD_echo("... getDataAvailabilty");
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		String WILD_toReturn = null ; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try{
			final String outPath = getParameterValue(PROP_BEHAVIOR_OUTPUTFILE, null, null) +"/getDataAvailability.xml";

			// Etape	"1" : poids relatif de 10, Construction de l'ordre SOAP
			this.WILD_setStep(); // Ne pas modifier
			final Map<String,Map<String,Object>> parameters = new HashMap<>(parametersMap);
			parameters.putAll(overrideParameters);
			parameters.put(PROP_REQUEST_QUERY, Collections.singletonMap("value", (Object)"getDataAvailability"));
			parameters.put(PROP_BEHAVIOR_OUTPUTFILE, Collections.singletonMap("value", (Object)outPath));

			lastRequest = new WildSoapService(WILD_dObject, parameters);
			if (lastRequest.getStats().containsKey(STAT_SERVICE_SUCCESS)&&!(Boolean)lastRequest.getStats().get(STAT_SERVICE_SUCCESS)) {
				//le setHeader a causer une erreur
				return WILD_toReturn;
			}

			// Etape	"2" : poids relatif de 10, Appel et capture du flux
			this.WILD_setStep(); // Ne pas modifier
			WILD_toReturn = lastRequest.getDatas();
			if (!"200".equals(lastRequest.getStats().get(STAT_ERROR_SERVICE_CODE))) {
				//le getData a causé une erreur
				return WILD_toReturn;
			}
			lastRequest.parseDatas(null, null, null);
			// Etape	"3" : poids relatif de 10, Vérification du résultat
			this.WILD_setStep(); // Ne pas modifier
	//		String cdYearPath = "//*:Result/*:DataSite/*:Resultats/*:Annee";
			// Etape	"4" : poids relatif de 10, Sélection dans le résultat
			this.WILD_setStep(); // Ne pas modifier
			// Etape	"5" : poids relatif de 10, Construction du fichier final
			this.WILD_setStep(); // Ne pas modifier

		}catch(Exception e){
			//this.WILD_logException(e); // Ne pas modifier
			//throw e;
			lastRequest.getStats().put("JAVA_ERROR", e);
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		
		return WILD_toReturn ; // Ne pas modifier
	}

	// CETTE METHODE DOIT ETRE MODIFIE.
	/**
	 * Appel au service de récupération des données{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public String opGetData ()throws Exception {
		WILD_echo("... getData");
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		String WILD_toReturn = null ; // Ne pas modifier


		// Mode try de récupération des erreurs pour log
		try {
			final String outPath = getParameterValue(PROP_BEHAVIOR_OUTPUTFILE, null, null) +"/getData.xml";

			// Etape	"1" : poids relatif de 10, Construction de l'ordre SOAP
			this.WILD_setStep(); // Ne pas modifier
			final Map<String,Map<String,Object>> parameters = new HashMap<>(parametersMap);
			parameters.putAll(overrideParameters);
			parameters.put(PROP_REQUEST_QUERY, Collections.singletonMap("value", (Object)"getData"));
			parameters.put(PROP_BEHAVIOR_OUTPUTFILE, Collections.singletonMap("value", (Object)outPath));

			lastRequest = new WildSoapService(WILD_dObject, parameters);
			if (lastRequest.getStats().containsKey(STAT_SERVICE_SUCCESS)&&!(Boolean)lastRequest.getStats().get(STAT_SERVICE_SUCCESS)) {
				//le setHeader a causer une erreur
				return WILD_toReturn;
			}

			// Etape	"2" : poids relatif de 10, Appel et capture du flux
			this.WILD_setStep(); // Ne pas modifier
			WILD_toReturn = lastRequest.getDatas();
			
			if (!"200".equals(lastRequest.getStats().get(STAT_ERROR_SERVICE_CODE))) {
				//le getData a causé une erreur
				return WILD_toReturn;
			}
			// Etape	"3" : poids relatif de 10, Vérification du résultat
			this.WILD_setStep(); // Ne pas modifier

			//le fichier contient un champ result avec le vrai résultat en base64
			final Element doc = IoCommons.xml_getDocument(outPath).getDocumentElement();
			final String base64 = IoCommons.xml_getFirstValue(doc,"//*:Result/text()");
			if (base64!=null) {
				final byte[] datas = Base64.decodeBase64(base64);
				IOUtilities.writeStream(new ByteArrayInputStream(datas), new File(outPath).toPath());
			}

			// Etape	"4" : poids relatif de 10, Sélection dans le résultat
			this.WILD_setStep(); // Ne pas modifier
			lastRequest.parseDatas(null, null, null);

			// Etape	"5" : poids relatif de 10, Construction du fichier final
			this.WILD_setStep(); // Ne pas modifier

		}catch(Exception e){
			//this.WILD_logException(e); // Ne pas modifier
			//throw e;
			lastRequest.getStats().put("JAVA_ERROR", e);
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}

		return WILD_toReturn ; // Ne pas modifier
	}



	
	// CETTE METHODE DOIT ETRE MODIFIE.
	/**
	 * Orchestration des méthodes opGet*{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 */
	public void jAeChainsaw ()throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		// Mode try de récupération des erreurs pour log
		try{
			// Etape	"1" : poids relatif de 10, Lecture et préparation des paramètres
			this.WILD_setStep(); // Ne pas modifier
			overrideParameters.clear();
			 //get capabilities
			final String capaFile = opGetCapabilities();
			if("200".equals(lastRequest.getStats().get(STAT_ERROR_SERVICE_CODE)))lastRequest.checkDatas(null, null);	
			DVP_saveRequestStatistics();
								
			// Etape	"2" : poids relatif de 10, getSites()
			this.WILD_setStep(); // Ne pas modifier
			overrideParameters.clear();
			// get sites
				final List<Entry<Double,String>> targetNamespaces = new ArrayList<>(TARGET_NAMESPACES);
				// validTargetNamespace est déjà attribué ? Si oui, test sur la valeur retenue.
				if(validTargetNamespace != null){
					final Map<String,Object> outputSchemaParam = new HashMap<>();
					outputSchemaParam.put("position", "outputSchema");
					outputSchemaParam.put("value", validTargetNamespace);
					final Map<String,Object> namespaceParam = new HashMap<>();
					namespaceParam.put("value", validTargetNamespace);
					overrideParameters.put("parameter.outputSchema", outputSchemaParam);
					opGetSitesSubset();
					DVP_saveRequestStatistics();				
					if (!"200".equals(lastRequest.getStats().get(STAT_ERROR_SERVICE_CODE)))
						validTargetNamespace = null;
				}if(validTargetNamespace == null){
				//test de différents target_namespace jusqu'a avoir une réponse valide
					for(Entry<Double,String> couple : targetNamespaces) {
						final Map<String,Object> outputSchemaParam = new HashMap<>();
						outputSchemaParam.put("position", "outputSchema");
						outputSchemaParam.put("value", couple.getValue());
						final Map<String,Object> namespaceParam = new HashMap<>();
						namespaceParam.put("value", couple.getValue());
						overrideParameters.put("parameter.outputSchema", outputSchemaParam);
						opGetSitesSubset();
						DVP_saveRequestStatistics();
						if ("200".equals(lastRequest.getStats().get(STAT_ERROR_SERVICE_CODE))) {
							validTargetNamespace = couple.getValue();
							break;
						}
					}
				}
DVP_dieWith();	
				//verification du XSD
				final Object schemaState = lastRequest.getStats().get(STAT_TEST_SCHEMA);
				boolean fatalError = (validTargetNamespace == null) || !(schemaState==null || Boolean.TRUE.equals(schemaState));
				if (fatalError) return;// Comportement à gérer ?
			
			// Etape	"3" : poids relatif de 10, getSiteDescription()
			this.WILD_setStep(); // Ne pas modifier

			//preparation de la liste des site a tester
			boolean allAtOnce = getParameterValue(PROP_BEHAVIOR_ALLATONCE, null, Boolean.FALSE);
			
			final List<Map<String,Object>> sitesParams = new ArrayList<>();
			if (allAtOnce) {
				final Map<String,Object> siteParam = new HashMap<>();
				siteParam.put("position", "sites/CdSite");
				siteParam.put("value", testSites);
				sitesParams.add(siteParam);
			} else {
				for(String site : testSites) {
					final Map<String,Object> siteParam = new HashMap<>();
					siteParam.put("position", "sites/CdSite");
					siteParam.put("value", site);
					sitesParams.add(siteParam);
				}
			}

			//execution des operations pour les sites
			for (Map<String,Object> siteParam : sitesParams) {

				//execution du getSiteDesciption
				overrideParameters.clear();

				//on cherche a résoudre le quesu avec le quesu valide en priorité
				final List<Entry<Double,String>> quesus = new ArrayList<>(QUESUS);
				
				// validQuesu est déjà attribué ? Si oui, test sur la valeur retenue.
				if(validQuesu != null){
					final Map<String,Object> quesuParams = new HashMap<>();
					quesuParams.put("position", "outputSchema");
					quesuParams.put("value", validQuesu);
					overrideParameters.put("parameter.outputSchema", quesuParams);
					overrideParameters.put("parameter.list_CdSite", siteParam);
					opGetSiteDescription();
					if (!"200".equals(lastRequest.getStats().get(STAT_ERROR_SERVICE_CODE))) 
						validQuesu = null;
					else{
						String validQuesuKey = null;
						for(Entry<Double,String> couple : quesus) if(couple.getValue().equals(validQuesu))validQuesuKey=(String.valueOf(couple.getKey()).replaceAll("^0.1","1.0").replaceAll(".0$",""));
						
						this.parametersMap.put(PROP_BEHAVIOR_SANDRE_XSD,Collections.singletonMap("value",(Object)("QUESU;"+validQuesuKey)));
						
						//verification du XSD et SANDRE
						Object schemaState_2 = lastRequest.getStats().get(STAT_TEST_SCHEMA);
						if (schemaState_2==null || Boolean.TRUE.equals(schemaState_2)) {
							if ("complet".equals(IoCommons.param_getValue(parametersMap, PROP_BEHAVIOR_TYPETEST, null, "partiel"))) {
								// test SANDRE
								jAeParserSandre();
							}
						}
					}
					DVP_saveRequestStatistics();
				}if(validQuesu == null){
				//test de différents validQuesu jusqu'a avoir une réponse valide
					for(Entry<Double,String> couple : quesus) {
						final Map<String,Object> quesuParams = new HashMap<>();
						quesuParams.put("position", "outputSchema");
						quesuParams.put("value", couple.getValue());
						overrideParameters.put("parameter.outputSchema", quesuParams);
						overrideParameters.put("parameter.list_CdSite", siteParam);
						opGetSiteDescription();
						if ("200".equals(lastRequest.getStats().get(STAT_ERROR_SERVICE_CODE))) {
							
							validQuesu = couple.getValue();
							final String validQuesuKey=(String.valueOf(couple.getKey()).replaceAll("0.1","1.0").replaceAll(".0$",""));
							this.parametersMap.put(PROP_BEHAVIOR_SANDRE_XSD,Collections.singletonMap("value",(Object)("QUESU;"+validQuesuKey)));
							//verification du XSD et SANDRE
							Object schemaState_5 = lastRequest.getStats().get(STAT_TEST_SCHEMA);
							if (schemaState_5==null || Boolean.TRUE.equals(schemaState_5)) {
								if ("complet".equals(IoCommons.param_getValue(parametersMap, PROP_BEHAVIOR_TYPETEST, null, "partiel"))) {
									// test SANDRE
									jAeParserSandre();
								}
							}
							DVP_saveRequestStatistics();
							break;
						}else{
							DVP_saveRequestStatistics();
						}
					}
				}
				
				//execution du getDataAvailibility
				overrideParameters.clear();
				//on cherche a résoudre le namespace
				//liste des target namespaces avec le namespace valide en priorité
				//final List<Entry<Double,String>> targetNamespaces = new ArrayList<>(TARGET_NAMESPACES);
				
				// validTargetNamespace est déjà attribué ? Si oui, test sur la valeur retenue.
				if(validTargetNamespace != null){
					final Map<String,Object> outputSchemaParam = new HashMap<>();
					outputSchemaParam.put("position", "outputSchema");
					outputSchemaParam.put("value", validTargetNamespace);
					final Map<String,Object> namespaceParam = new HashMap<>();
					namespaceParam.put("value", validTargetNamespace);
					overrideParameters.put("parameter.outputSchema", outputSchemaParam);
					overrideParameters.put("parameter.list_CdSite", siteParam);
					opGetDataAvailabilty();
					DVP_saveRequestStatistics();
					if ("200".equals(lastRequest.getStats().get(STAT_ERROR_SERVICE_CODE)))
						validTargetNamespace = null;
				}
				//test de différents target_namespace jusqu'a avoir une réponse valide
				if(validTargetNamespace==null)for(Entry<Double,String> couple : targetNamespaces) {
					final Map<String,Object> outputSchemaParams = new HashMap<>();
					outputSchemaParams.put("position", "outputSchema");
					outputSchemaParams.put("value", couple.getValue());
					final Map<String,Object> namespaceParam = new HashMap<>();
					namespaceParam.put("value", couple.getValue());
					overrideParameters.put("parameter.outputSchema", outputSchemaParams);
					overrideParameters.put("parameter.list_CdSite", siteParam);
					opGetDataAvailabilty();
					DVP_saveRequestStatistics();
					if ("200".equals(lastRequest.getStats().get(STAT_ERROR_SERVICE_CODE))) {
						validTargetNamespace = couple.getValue();
						break;
					}
				}
				
				//lecture des MIN/MAX année du getDataAvailibility
				String outPath = IoCommons.param_getValue(lastRequest.getParametersMap(),PROP_BEHAVIOR_OUTPUTFILE,null,null);
				try{
					final Element doc = IoCommons.xml_getDocument(outPath).getDocumentElement();
					final List<String> dates = IoCommons.xml_getValues(doc,"//*:Result/*:DataSite/*:Resultats/*:Annee");
					final TreeSet<Calendar> candidates = new TreeSet<>();
					final ISODateParser dateParser = new ISODateParser();
					if(dates!=null&&!dates.isEmpty()){
					for(String d : dates) {
						try {
							//on test le format standard ISO-8601
							candidates.add((Calendar) dateParser.getCalendar(d).clone());
						}catch(NumberFormatException ex){
							//on essai les formattages plus irrégulier
							candidates.add(TemporalUtilities.parseDateCal(d));
						}
					}
					String dateDebut, dateFin;
					if(getParameterValue("behavior.pickAYear", null, false)){
						Calendar dateRandom_min = (Calendar) candidates.toArray()[(int)(Math.random() * (candidates.size()-1))];
						Calendar dateRandom_max = (Calendar) dateRandom_min.clone();
						dateRandom_max.add(Calendar.YEAR, 1);
						dateDebut = TemporalUtilities.toISO8601(dateRandom_min.getTime());
						dateFin = TemporalUtilities.toISO8601(dateRandom_max.getTime());
					}else{
						 dateDebut = TemporalUtilities.toISO8601(candidates.first().getTime());
						 dateFin = TemporalUtilities.toISO8601(candidates.last().getTime());
					}
					//date de début
					final Map<String,Object> dateDebutParam = new HashMap<>();
					dateDebutParam.put("position", "temporalConstraints/DateDebutDonnees");
					dateDebutParam.put("value", dateDebut);
					overrideParameters.put("parameter.DateDebutDonnees", dateDebutParam);
					//date de fin
					final Map<String,Object> dateFinParam = new HashMap<>();
					dateFinParam.put("position", "temporalConstraints/DateFinDonnees");
					dateFinParam.put("value", dateFin);
					overrideParameters.put("parameter.DateFinDonnees", dateFinParam);
					}}catch(Exception e){}
			
				//analyticConstraints : vide, element obligatoire
				final Map<String,Object> anCstParam = new HashMap<>();
				anCstParam.put("position", "analyticConstraints");
				anCstParam.put("value", "");
				overrideParameters.put("parameter.analyticConstraints", anCstParam);

				//execution du getData

				//on cherche a résoudre le quesu avec le quesu valide en priorité
				
				// validQuesu est déjà attribué ? Si oui, test sur la valeur retenue.
				if(validQuesu != null){
					final Map<String,Object> quesuParams = new HashMap<>();
					quesuParams.put("position", "outputSchema");
					quesuParams.put("value", validQuesu);
					overrideParameters.put("parameter.outputSchema", quesuParams);
					overrideParameters.put("parameter.list_CdSite", siteParam);
					opGetData();
					if (!"200".equals(lastRequest.getStats().get(STAT_ERROR_SERVICE_CODE))) 
						validQuesu = null;
					else{
						String validQuesuKey = null;
						for(Entry<Double,String> couple : quesus) if(couple.getValue().equals(validQuesu))validQuesuKey=(String.valueOf(couple.getKey()).replaceAll("^0.1","1.0").replaceAll(".0$",""));
						this.parametersMap.put(PROP_BEHAVIOR_SANDRE_XSD,Collections.singletonMap("value",(Object)("QUESU;"+validQuesuKey)));
						//verification du XSD et SANDRE
						Object schemaState_3 = lastRequest.getStats().get(STAT_TEST_SCHEMA);
						if (schemaState_3==null || Boolean.TRUE.equals(schemaState_3)) {
							if ("complet".equals(IoCommons.param_getValue(parametersMap, PROP_BEHAVIOR_TYPETEST, null, "partiel"))) {
								// test SANDRE
								jAeParserSandre();
							}
						}
					}
					DVP_saveRequestStatistics();						
				}
				// 	Sinon test de toutes les valeurs de validQuesu possibles
				if(validQuesu==null)for (Entry<Double,String> couple : quesus) {
					final Map<String,Object> quesuParams = new HashMap<>();
					quesuParams.put("position", "outputSchema");
					quesuParams.put("value", couple.getValue());
					overrideParameters.put("parameter.outputSchema", quesuParams);
					overrideParameters.put("parameter.list_CdSite", siteParam);
					opGetData();
					if ("200".equals(lastRequest.getStats().get(STAT_ERROR_SERVICE_CODE))) {
						validQuesu = couple.getValue();
						final String validQuesuKey=(String.valueOf(couple.getKey()).replaceAll("0.1","1.0").replaceAll(".0$",""));
						this.parametersMap.put(PROP_BEHAVIOR_SANDRE_XSD,Collections.singletonMap("value",(Object)("QUESU;"+validQuesuKey)));
						//verification du XSD et SANDRE
						Object schemaState_4 = lastRequest.getStats().get(STAT_TEST_SCHEMA);
						if (schemaState_4==null || Boolean.TRUE.equals(schemaState_4)) {
							if ("complet".equals(IoCommons.param_getValue(parametersMap, PROP_BEHAVIOR_TYPETEST, null, "partiel"))) {
								// test SANDRE
								jAeParserSandre();
							}
						}
						DVP_saveRequestStatistics();
						break;
					}
					DVP_saveRequestStatistics();
				}
			}

			// Etape	"4" : poids relatif de 10, getDataAvailability()
			this.WILD_setStep(); // Ne pas modifier

			// Etape	"5" : poids relatif de 10, getData()
			this.WILD_setStep(); // Ne pas modifier

			// Etape	"6" : poids relatif de 10, Interprétation des résultats et construction des statistiques
			this.WILD_setStep(); // Ne pas modifier

		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		//	DVP_saveRequestStatistics();
			throw e;
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
	}

	// CETTE METHODE DOIT ETRE MODIFIE.
	/**
	 * Exécution du parseur SANDRE{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public Boolean jAeParserSandre ()throws Exception{
		WILD_echo("... validation sandre");
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		// Variable générique de retour
		Boolean WILD_toReturn = null ; // Ne pas modifier
		selectScenario() ;
		// Mode try de récupération des erreurs pour log
		try {
			final String outPath   = getParameterValue(PROP_BEHAVIOR_OUTPUTFILE, null, null) +"/sandreToken.xml";
			final String sandreXsd = getParameterValue(PROP_BEHAVIOR_SANDRE_XSD, null, "QUESU;1");
			final String inputPath = lastRequest.getParameterValue(PROP_BEHAVIOR_OUTPUTFILE, null, null);
			final File outFile     = new File(inputPath);
			final String fileText  = IOUtilities.toString(outFile.toPath());
			final List multipart   = new ArrayList();
			/*Integer nMaxSandreAttempts = (Integer) WILD_getConf("nMaxSandreAttempts");
			if(nMaxSandreAttempts==null)nMaxSandreAttempts = 10 ;*/
			Integer nMaxSandreAttempts = getParameterValue("behavior.nMaxSandreAttempts", null, 0);
			if(nMaxSandreAttempts==0)nMaxSandreAttempts=WILD_getConf("nMaxSandreAttempts");
			if(nMaxSandreAttempts==null)nMaxSandreAttempts=10;
			// Etape	"1" : poids relatif de 10, Construction de l'ordre REST
			this.WILD_setStep(); // Ne pas modifier

			final Map<String,String> fileProperties = new HashMap<>();
			fileProperties.put("name", "XML");
			fileProperties.put("filename", outFile.getName());
			multipart.add(new PostMultiPartObject(fileProperties, "application/xml", fileText.getBytes()));
			multipart.add(new PostMultiPartObject("XSD", null, sandreXsd));
			multipart.add(new PostMultiPartObject("NomSI", null, "Onema-Wild"));
			multipart.add(new PostMultiPartObject("VersionSI", null, "1.0"));

			final Map<String,Map<String,Object>> parameters = new HashMap<>();
			parameters.put(PROP_REQUEST_URL,		Collections.singletonMap("value", (Object)"http://sandre.eaufrance.fr/PS/parseurSANDRE"));
			parameters.put(PROP_REQUEST_QUERY,		Collections.singletonMap("value", (Object)"POST"));
			parameters.put(PROP_BEHAVIOR_OUTPUTFILE,Collections.singletonMap("value", (Object)outPath));
			parameters.put(PROP_REQUEST_MULTIPART,	Collections.singletonMap("value", (Object)multipart));

			// Etape	"2" : poids relatif de 10, Récupération du token
			this.WILD_setStep(); // Ne pas modifier

			final WildRestService request = new WildRestService(WILD_dObject, (Map)parameters);
			request.urlConnect();
			final Element tokenDoc = IoCommons.xml_getDocument(request.getDatas()).getDocumentElement();
			final String token     = IoCommons.xml_getFirstValue(tokenDoc,"//*:jeton/text()");
			final String lienAcq   = IoCommons.xml_getFirstValue(tokenDoc,"//*:LienAcquittement/text()");

			// Etape	"3" : poids relatif de 10, Boucle pour récupération du résultat de validation
			this.WILD_setStep(); // Ne pas modifier

			Node statusDoc             = null;
			Node accuseReception       = null;
			String acceptation         = null;
			Collection<Element> errors = null;
			for (Integer n_sa = 0 ;  n_sa < nMaxSandreAttempts ; n_sa++) {
				Thread.sleep(5000);
				final String acqPath = getParameterValue(PROP_BEHAVIOR_OUTPUTFILE, null, null) +"/sandreAcquittement.xml";
				parameters.put(PROP_REQUEST_URL,		Collections.singletonMap("value", (Object)lienAcq));
				parameters.put(PROP_REQUEST_QUERY,		Collections.singletonMap("value", (Object)"GET"));
				parameters.put(PROP_BEHAVIOR_OUTPUTFILE,Collections.singletonMap("value", (Object)acqPath));
				//jeton deja inclu dans l'url d'acquittement
				//parameters.put("parameter.jeton", Collections.singletonMap("value", (Object)token));
				final WildRestService status = new WildRestService(WILD_dObject, (Map)parameters);
				status.urlConnect();
				statusDoc       = IoCommons.xml_getDocument(status.getDatas()).getDocumentElement();
				accuseReception = IoCommons.xml_getFirstNode(statusDoc, "//*:AccuseReception");
				acceptation     = IoCommons.xml_getFirstValue(accuseReception, "//*:Acceptation/text()");
				errors          = new DomNodeList(IoCommons.xml_getNodes(accuseReception, "//*:Erreur")).elements();
				if (!"0".equals(acceptation)) break;
			}
			// Si on a dépasé le nombre de tentaives, et que l'on a pas trouvé de retour SANDRE correct
			SandreErrorTimedOut = false;
			if("0".equals(acceptation)){
				SandreErrorTimedOut = true;
				throw new Exception("SANDRE PARSER TIME OUT : TOO MUCH ATTEMPTS");
			}
			
			// Etape	"4" : poids relatif de 10, Récupération du résultat de validation
			this.WILD_setStep(); // Ne pas modifier

			// Etape	"5" : poids relatif de 10, Interprétation des résultats et construction des statistiques
			this.WILD_setStep(); // Ne pas modifier

			//document valide si valeur == 1
			lastRequest.getStats().put(STAT_SANDRE_VALID, "1".equals(String.valueOf(acceptation).trim()));
			lastRequest.getStats().put(STAT_SANDRE_NUMBER_ERRORS, errors.size());
			final List<Map> allErrors = new ArrayList<>();
			for (Element error : errors) allErrors.add(IoCommons.xml_nodeToMap(error));
			lastRequest.getStats().put(STAT_SANDRE_ERRORS, allErrors);

		}catch(Exception e){
			if (e instanceof URLException) 	lastRequest.getStats().put(STAT_ERROR_SERVICE_NODE,	((URLException)e).getContentNode());
			/*if (IoCommons.execution_isInterrupt(e))*/	lastRequest.getStats().put(STAT_ERROR_SERVICE_NODE,IoCommons.cast_String2Node("<Errors status = \"URL error\">"+IoCommons.cast_xmlEscape(e.toString())+"</Errors>"));
			lastRequest.getStats().put("JAVA_ERROR", e);
			//this.WILD_logException(e); // Ne pas modifier
		//	throw e;
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		
		return WILD_toReturn ; // Ne pas modifier
	}

	
	
	// CETTE METHODE DOIT ETRE MODIFIE.
	/**
	 * Récupération des statistiques d'exécution de l'ensemble des services{
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public Map<String, Object> jAeGetStats ()throws Exception {
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		Map<String, Object> WILD_toReturn = stats; // Ne pas modifier

		// Mode try de récupération des erreurs pour log
		try {

			// Etape	"1" : poids relatif de 10, Transmission des variables
			this.WILD_setStep(); // Ne pas modifier
		}catch(Exception e){
			this.WILD_logException(e); // Ne pas modifier
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		
		return WILD_toReturn ; // Ne pas modifier
	}

	/**
	 * Copie des statistiques de la derniere requete dans l'objet service courant.
	 */
	private void DVP_saveRequestStatistics() throws Exception {
			if(SandreErrorTimedOut){
				lastRequest.getStats().put(STAT_ERROR_SERVICE_CODE, "sandre_timeout");
				SandreErrorTimedOut= false ;
			}
			final Map<String, Object> lastRequestStats = lastRequest.getStats();
			final Map<String, Object> resultMap = lastRequest.getResultMap();
			final Map queryStats = new HashMap(lastRequestStats);
			queryStats.put(STAT_VALID_XML, validTargetNamespace);
			queryStats.put(STAT_QUESU, validQuesu);
			queryStats.put(STAT_PROC_END_KEY, IoCommons.date_now());
			queryStats.put(STAT_PROC_END_KEY+"_ts", IoCommons.date_nowLong());
			queryStats.put(STAT_PROC_START_KEY,lastRequestStats.get(STAT_PROC_START_KEY));
			final String method = lastRequest.getParameterValue(WildSoapService.PROP_REQUEST_QUERY, null, "");
			final String id = requestNumber+"."+method;
			stats.put(id, queryStats);
		
		requestNumber++;
		DVP_carryOn();
	}

	/**
	 * Comparateur donnant la priorité à une valeur en particulier.
	 */
	private static final class PriorityComparator implements Comparator<Entry<Double, String>> {

		private final String priority;

		public PriorityComparator(String priority) {
			this.priority = priority;
		}

		@Override
		public int compare(Entry<Double, String> o1, Entry<Double, String> o2) {
			if(o1.getValue().equals(priority)) return -1;
			if(o2.getValue().equals(priority)) return +1;
			return o1.getKey().compareTo(o2.getKey());
		}
	}

	@Override
	public Object call() throws Exception {
		try{
			this.jAeChainsaw();
			List<String> intendedServices = Arrays.asList(new String[]{
					"getCapabilities",
					"getSites",
					"getSiteDescription",
					"getDataAvailability",
					"getData"
					});
			stats.put("intendedServices", Collections.singletonMap("intendedService",intendedServices));
		}catch(Exception e){
			throw e;
		}finally{
			this.DEV_dumpStats();
			
		}
		return null;
	}

}

