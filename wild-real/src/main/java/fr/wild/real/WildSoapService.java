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

import static fr.wild.common.IoCommons.cast_2String;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper; 
import fr.wild.common.IoCommons;
import fr.wild.orchestra.WildObject;
import fr.wild.utils.DomNodeList;
import fr.wild.utils.URLException;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.geotoolkit.nio.IOUtilities;
import org.geotoolkit.util.DomUtilities;
import org.geotoolkit.xsd.xml.v2001.Import;
import org.geotoolkit.xsd.xml.v2001.OpenAttrs;
import org.geotoolkit.xsd.xml.v2001.Schema;
import org.geotoolkit.xsd.xml.v2001.TopLevelElement;
import org.geotoolkit.xsd.xml.v2001.XSDMarshallerPool;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Code généré automatiquement par l'outil Wild
 * Client d'un service protocole SOAP
 */
public class WildSoapService extends WildWebService{

	private static final String NS_WSDL_SOAP = "http://schemas.xmlsoap.org/wsdl/soap/";
	private static final String NS_WSDL_HTTP = "http://schemas.xmlsoap.org/wsdl/http/";
	private static final String NS_SOAP_ENV = "http://schemas.xmlsoap.org/soap/envelope/";
	private static final String NS_XSD = "http://www.w3.org/2001/XMLSchema";

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildSoapService(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected String soapHeader; // Entête SOAP


// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***
	/** URL du port du service tel que définie dans le WSDL */
	private String serviceUrl;
	private URL url;
	private HttpURLConnection urlConnection;
	private String wsdlResponseXsd;
	private String schemaNamespace;

// CE CONSTRUCTEUR DOIT ETRE MODIFIE.
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
	protected void WILD_initialize_WildSoapService(
			WildObject i_WILD_dObject,
			Node i_parametersNode,
			String i_parametersXmlFile,
			Map<String,Map<String,Object>> i_parametersMap
			) throws Exception {

		// Amorce de la classe
		// Initialisation de la classe d'objet selon le schéma Wild
		WILD_dObject = i_WILD_dObject ;
		WILD_initialize_WildWebService(i_WILD_dObject, null, null, null, null);
		// Préparation des variables d'invocation (considérées comme champs globaux)

		// Mode try de récupération des erreurs pour log
		try {
			setHeader(null,null,null,null,i_parametersNode, i_parametersXmlFile, i_parametersMap);
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
	public WildSoapService(
			WildObject i_WILD_dObject,
			Node i_parametersNode
			) throws Exception{
		this.WILD_initialize_WildSoapService(i_WILD_dObject,i_parametersNode,null,null);
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
	public WildSoapService(
			WildObject i_WILD_dObject,
			String i_parametersXmlFile
			) throws Exception{
		this.WILD_initialize_WildSoapService(i_WILD_dObject,null,i_parametersXmlFile,null);
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
	public WildSoapService(
			WildObject i_WILD_dObject,
			Map<String,Map<String,Object>> i_parametersMap
			) throws Exception{
		this.WILD_initialize_WildSoapService(i_WILD_dObject,null,null,i_parametersMap);
	}

/*	### NOUVELLE METHODE ###
	Méthode : setHeader - Construction de l'amorce de requête (entête SOAP){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

	/**
	 * Construction de l'amorce de requête (entête SOAP){
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @param i_urlConnexion	URL du WSDL connexion{}
	 * @param i_methodName	Nom de la méthode SOAP appelée{}
	 * @param i_endPoint	EndPoint si différent de celui spécifié dans le WSDL{}
	 * @param i_nameSpace	schéma namespace si différent de celui spécifié dans le WSDL{}
	 * @param i_xmlParameters	Paramètres sous forme XML{}
	 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres{}
	 * @param i_xmlParametersMap	Paramètres sous forme de Map{}
	 */
	public void setHeader (
			String i_urlConnexion,
			String i_methodName,
			String i_endPoint,
			String i_nameSpace,
			Node i_xmlParameters,
			String i_xmlParametersFile,
			Map<String,Map<String,Object>> i_xmlParametersMap
			)throws Exception{
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier

		int errorServiceCode = 0;
		long timeService = 0;
		boolean serviceSuccess = false;
		String requestURL = null;
		Node parametersRequestURL = IoCommons.param_mapToNode("service",parametersMap);

		// Mode try de récupération des erreurs pour log
		try{	

			//	Etape	"1" : poids relatif de 10, Identification de la structure de passage de variables (SOAP, Map ou XML)
			this.WILD_setStep(); // Ne pas modifier

			final Map<String,Map<String,Object>> surcharge = IoCommons.param_readParameters(i_xmlParameters, i_xmlParametersFile, (Map)i_xmlParametersMap);
			if (surcharge != null) {
				parametersMap.putAll(surcharge);
			}
			if (i_urlConnexion != null) {
				parametersMap.put(PROP_REQUEST_URL, Collections.singletonMap("value", (Object)i_urlConnexion));
			}
			if (i_methodName != null) {
				parametersMap.put(PROP_REQUEST_QUERY, Collections.singletonMap("value", (Object)i_methodName));
			}
			if (i_endPoint != null) {
				parametersMap.put(PROP_REQUEST_ENDPOINT, Collections.singletonMap("value", (Object)i_endPoint));
			}
			if (i_nameSpace != null) {
				parametersMap.put(PROP_REQUEST_NAMESPACE, Collections.singletonMap("value", (Object)i_nameSpace));
			}
			final int timeOut = getParameterValue(PROP_REQUEST_TIMEOUT, null, 0);
			//	Etape	"2" : poids relatif de 10, Parse de la structure
			this.WILD_setStep(); // Ne pas modifier
			requestURL = (String) getParameterValue(PROP_REQUEST_URL,null,null);
			final StringBuilder urlBuilder = new StringBuilder(createURL(requestURL));

			// recuperation du WSDL
			url = new URL(urlBuilder.toString());
			urlConnection = (HttpURLConnection) url.openConnection();
			Document wsdl  ;
			final long before = System.currentTimeMillis();

			InputStream in = openRichException(urlConnection, timeOut);
			wsdl = DomUtilities.read(in);

			final long after = System.currentTimeMillis();
			timeService = (after-before)  ;

			final Element root = wsdl.getDocumentElement();
			Element types = (Element) IoCommons.xml_getFirstNode(root, "//types");
			if(types == null || !types.hasChildNodes())types = (Element)IoCommons.xml_getFirstNode(root, "//*:types");

			final Collection<Element> messages = new DomNodeList(IoCommons.xml_getNodes(root, "//*:message")).elements();
			final Collection<Element> portTypes = new DomNodeList(IoCommons.xml_getNodes(root, "//*:portType")).elements();
			final Collection<Element> bindings = new DomNodeList(IoCommons.xml_getNodes(root, "//*:binding")).elements();
			final Element serviceNode = (Element) IoCommons.xml_getFirstNode(root, "//*:service");


			//	Etape	"3" : poids relatif de 10, Composition du node
			this.WILD_setStep(); // Ne pas modifier
			final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			docFactory.setNamespaceAware(true);
			final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			//lecture du XSD
			final Unmarshaller unmarshaller = XSDMarshallerPool.getInstance().acquireUnmarshaller();
			Element schemaNode = DomUtilities.firstElement(types, "schema");
			Schema schema = null;
			if (schemaNode==null){
				schemaNode = DomUtilities.firstElement(types, "xsd:schema");
				if(schemaNode==null)schemaNode = DomUtilities.firstElement(types, "s:schema");
				if(schemaNode!=null) {
					//les namespaces ne sont pas correctement encapsulé
					//on place le xsd dans un nouveau document
					final Document temp = docBuilder.newDocument();
					schemaNode = (Element) temp.adoptNode(schemaNode);
					temp.appendChild(schemaNode);
					
					final NamedNodeMap atts = root.getAttributes();
					for (int i=0,n=atts.getLength();i<n;i++) {
						final Attr att = (Attr) atts.item(i);
						final String attName = att.getNodeName();
						if (attName.startsWith("xmlns") && !schemaNode.hasAttribute(attName)) {
							schemaNode.setAttribute(attName, att.getValue());				
						}
					}
					final StringWriter writer = new StringWriter();
					DomUtilities.write(temp, writer);
					schema = (Schema) unmarshaller.unmarshal(new StringReader(writer.toString()));
				}
				
			} else {
				schema = (Schema) unmarshaller.unmarshal(schemaNode);
			}
			
			XSDMarshallerPool.getInstance().recycle(unmarshaller);

			//on converti les imports en path absolu
			for(OpenAttrs attrs : schema.getIncludeOrImportOrRedefine()) {
				if(attrs instanceof Import) {
					final Import imp = (Import)attrs;
					String location = imp.getSchemaLocation();
					if(location!=null&&!(location.startsWith("http") || location.startsWith("https"))){
						location = requestURL.substring(0, requestURL.lastIndexOf("/")+1) + location;
						imp.setSchemaLocation(location);
					}
				}
			}

			//on sauvegarde le xsd a coté du fichier de sortie pour s'en servir lors de la validation
			final File xsdFile = new File((String)getParameterValue(PROP_BEHAVIOR_OUTPUTFILE, null, null)+".xsd");
			wsdlResponseXsd = xsdFile.toURI().toString();
			final Marshaller marshaller = XSDMarshallerPool.getInstance().acquireMarshaller();
			//Le validateur XSD de java n'aime pas les déclarations de namespace qui ne sont pas a la racine
			//du document même si pourtant cela est correct. On force ici le namespace xlink.
			marshaller.setProperty("com.sun.xml.bind.namespacePrefixMapper", new NamespacePrefixMapper() {
				@Override
				public String[] getPreDeclaredNamespaceUris() {
					return new String[] {"http://www.w3.org/1999/xlink"};
				}
				@Override
				public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
					if (namespaceUri.equals("http://www.w3.org/1999/xlink")) {
						return "xlink";
					}
					return suggestion;
				}
			});
			marshaller.marshal(schema, xsdFile);
			XSDMarshallerPool.getInstance().recycle(marshaller);


			//recherche de la description de l'operation
			final String opName = (String) getParameterValue(PROP_REQUEST_QUERY, null, null);
			if (opName==null) {
				throw new Exception("Operation SOAP non définie");
			}

			//boucle sur les ports
			TopLevelElement inputDescription = null;
			Boolean testXsd = false ;
			for (final Element port : new DomNodeList(IoCommons.xml_getNodes(serviceNode, "//*:port")).elements()) {
				testXsd = true ;
				final String bindingType = port.getAttribute("binding");
				if (bindingType != null) {
					Element bindingNode = null;
					Element bindingOperationNode = null;
					String bindingName = null;
					String portTypeRef = null;
					//boucle sur les bindings
					bindingLoop:
					for (Element binding : bindings) {
						final String bt = binding.getAttribute("type");
						final String bn = binding.getAttribute("name");
						if (DEV_equalsNoPrefix(bindingType,bt) || DEV_equalsNoPrefix(bindingType,bn)) {
							//boucle sur les operations
							for (Element operation : new DomNodeList(IoCommons.xml_getNodes(serviceNode, "//*:operation")).elements()) {
								if (opName.equals(operation.getAttribute("name"))) {
									//on a trouvé l'opération
									bindingNode = binding;
									bindingName = binding.getAttribute("name");
									portTypeRef = binding.getAttribute("type");
									bindingOperationNode = operation;

									//on sauvegarde le chemin de <soap:address location="">
									serviceUrl = DomUtilities.textAttributeValue(port, "soap:address", "location", String.class);
									break bindingLoop;
								}
							}
						}
					}
					if (bindingName==null) {
						continue;
					}

					//boucle sur les portTypes
					Element input = null;
					String messageType = null;
					portLoop:
					for (Element portType : portTypes) {
						final String portName = portType.getAttribute("name");
						if (DEV_equalsNoPrefix(bindingName,portName) ||
							DEV_equalsNoPrefix(portTypeRef,portName)) {

							for (Element operation : new DomNodeList(IoCommons.xml_getNodes(serviceNode, "//*:operation")).elements()) {
								if (opName.equals(operation.getAttribute("name"))) {
									input = (Element) IoCommons.xml_getFirstNode(operation,"//*:input");
									messageType = input.getAttribute("message");
									if (messageType != null && messageType.contains(":")){
										//on enleve le prefixe du namespace s'il est défini
										messageType = messageType.split(":")[1];
									}
									break portLoop;
								}
							}
						}
					}
					if (input==null || messageType==null) {
						throw new Exception("Définition du paramètre Input de l'opération "+opName+" non trouvée dans le WSDL.");
					}

					//boucle sur les messages
					String xsdTypeName = null;
					messageLoop:
					for (Element message : messages) {
						if (messageType.equals(message.getAttribute("name"))) {
							final Collection<Element> parts = new DomNodeList(IoCommons.xml_getNodes(serviceNode, "//*:part")).elements();
							for (Element part : parts) {
								if ("parameters".equals(part.getAttribute("name"))) {
									xsdTypeName = part.getAttribute("element");
									break messageLoop;
								}
							}
						}
					}
					if (xsdTypeName==null) {
						throw new Exception("Type XSD du paramètre Input de l'opération "+opName+" non trouvée dans le WSDL.");
					}

					//on recupere la définition du input type dans le XSD
					if (xsdTypeName.contains(":")) {
						//on enleve le prefix
						xsdTypeName = xsdTypeName.split(":")[1];
					}
					inputDescription = schema.getElementByName(xsdTypeName);
					if(inputDescription!=null) break;
				}
			}

			if (testXsd&&inputDescription==null) {
				
				throw new Exception("Type du paramètre d'entrée de l'opération "+opName+" non trouvée dans le XSD.");
			}

			//construction de l'entete du message soap
			schemaNamespace = (schema==null) ? null : schema.getTargetNamespace();
			final String targetNamespace = getParameterValue(PROP_REQUEST_NAMESPACE, null, schemaNamespace);

			soapHeader = DEV_buildSoapMessage(opName, targetNamespace, parametersMap);
			stats.put(STAT_REQUEST_SOAP_HEADER, soapHeader);
			//WILD_echo(soapHeader);
			serviceSuccess = true;
		}catch(Exception e){
			if(e.getCause()!=null&&(Exception) e.getCause() instanceof URLException)e=(Exception) e.getCause();
			//this.WILD_logException(e); // Ne pas modifier
			//en reregistre les statistiques de l'erreur
			if (e instanceof URLException) 	stats.put(STAT_ERROR_SERVICE_NODE,	((URLException)e).getContentNode());
			/*if (IoCommons.execution_isInterrupt(e))*/	
			e.printStackTrace();
			stats.put(STAT_ERROR_SERVICE_NODE,IoCommons.cast_String2Node("<Errors status = \"URL error\">"+IoCommons.cast_xmlEscape(e.toString())+"</Errors>"));
			stats.put("JAVA_ERROR", e);
			throw e;
		}finally{
			stats.put(STAT_ERROR_SERVICE_CODE,		errorServiceCode);
			stats.put(STAT_TIME_SERVICE,			timeService);
			stats.put(STAT_SERVICE_SUCCESS,			serviceSuccess);
			stats.put(STAT_REQUEST_URL,				requestURL);
			stats.put(STAT_PARAMETERS_REQUEST_URL,  parametersRequestURL);
			stats.put(STAT_PROC_END_KEY, IoCommons.date_now());
			stats.put(STAT_PROC_END_KEY+"_ts", IoCommons.date_nowLong());
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
		}
		
		//on utilise le endpoint défini par l'utilisateur s'il est présent
		serviceUrl = getParameterValue(PROP_REQUEST_ENDPOINT,null,serviceUrl);
		
	}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
	/**
	 * Construction de l'amorce de requête (entête SOAP){
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_urlConnexion	URL du WSDL connexion {}
	 * @param i_methodName	Nom de la méthode SOAP appelée {}
	 * @param i_endPoint	EndPoint si différent de celui spécifié dans le WSDL {}
	 * @param i_nameSpace	schéma namespace si différent de celui spécifié dans le WSDL {}
	 * @param i_xmlParameters	Paramètres sous forme XML {}
	 */
	 public void setHeader (
			String i_urlConnexion,
			String i_methodName,
			String i_endPoint,
			String i_nameSpace,
			Node i_xmlParameters
			)throws Exception{
		setHeader(i_urlConnexion,i_methodName,i_endPoint,i_nameSpace,i_xmlParameters,null,null);
	}

	/**
	 * Construction de l'amorce de requête (entête SOAP){
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_urlConnexion	URL du WSDL connexion {}
	 * @param i_methodName	Nom de la méthode SOAP appelée {}
	 * @param i_endPoint	EndPoint si différent de celui spécifié dans le WSDL {}
	 * @param i_nameSpace	schéma namespace si différent de celui spécifié dans le WSDL {}
	 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres {}
	 */
	 public void setHeader (
			String i_urlConnexion,
			String i_methodName,
			String i_endPoint,
			String i_nameSpace,
			String i_xmlParametersFile
			)throws Exception{
		setHeader(i_urlConnexion,i_methodName,i_endPoint,i_nameSpace,null,i_xmlParametersFile,null);
	}

	/**
	 * Construction de l'amorce de requête (entête SOAP){
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_urlConnexion	URL du WSDL connexion {}
	 * @param i_methodName	Nom de la méthode SOAP appelée {}
	 * @param i_endPoint	EndPoint si différent de celui spécifié dans le WSDL {}
	 * @param i_nameSpace	schéma namespace si différent de celui spécifié dans le WSDL {}
	 * @param i_xmlParametersMap	Paramètres sous forme de Map {}
	 */
	 public void setHeader (
			String i_urlConnexion,
			String i_methodName,
			String i_endPoint,
			String i_nameSpace,
			Map<String,Map<String,Object>> i_xmlParametersMap
			)throws Exception{
		setHeader(i_urlConnexion,i_methodName,i_endPoint,i_nameSpace,null,null,i_xmlParametersMap);
	}
 
	/**
	 * Construction de l'amorce de requête (entête SOAP){
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_urlConnexion	URL du WSDL connexion {}
	 * @param i_methodName	Nom de la méthode SOAP appelée {}
	 * @param i_xmlParameters	Paramètres sous forme XML {}
	 */
	 public void setHeader (
			String i_urlConnexion,
			String i_methodName,
			Node i_xmlParameters
			)throws Exception{
		setHeader(i_urlConnexion,i_methodName,null,null,i_xmlParameters,null,null);
	}

 
	/**
	 * Construction de l'amorce de requête (entête SOAP){
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_urlConnexion	URL du WSDL connexion {}
	 * @param i_methodName	Nom de la méthode SOAP appelée {}
	 * @param i_xmlParametersFile	Adresse du fichier contenant les valeurs de paramètres {}
	 */
	 public void setHeader (
			String i_urlConnexion,
			String i_methodName,
			String i_xmlParametersFile
			)throws Exception{
		setHeader(i_urlConnexion,i_methodName,null,null,null,i_xmlParametersFile,null);
	}

	/**
	 * Construction de l'amorce de requête (entête SOAP){
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode d'appel public non modifiable
	 *
	 * @param i_urlConnexion	URL du WSDL connexion {}
	 * @param i_methodName	Nom de la méthode SOAP appelée {}
	 * @param i_xmlParametersMap	Paramètres sous forme de Map {}
	 */
	 public void setHeader (
			String i_urlConnexion,
			String i_methodName,
			Map<String,Map<String,Object>> i_xmlParametersMap
			)throws Exception{
		setHeader(i_urlConnexion,i_methodName,null,null,null,null,i_xmlParametersMap);
	}

	/**
	 * Récupération des données pour enregistrement dans un fichier (capture de flux){
	 }
	 * Code généré automatiquement par l'outil Wild
	 * Méthode susceptible d'être surchargée
	 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
	 *
	 * @return	{}
	 */
	public String getDatas ()throws Exception {
		// Amorce de la méthode
		WILD_beginMethod(); // Ne pas modifier
		//	Variable générique de retour
		String WILD_toReturn = null ; // Ne pas modifier

		//chemin de sortie
		final String outputMode = getParameterValue(PROP_BEHAVIOR_OUTPUTFILEMODE, null, "OVERRIDE");
		final boolean append = "APPEND".equalsIgnoreCase(outputMode);
		final String outPath = (String) getParameterValue(PROP_BEHAVIOR_OUTPUTFILE,null,null);
		if (outPath == null) {
			throw new Exception("Fichier de sortie de la réponse non défini");
		}
		WILD_toReturn = outPath;
		
		String errorServiceCode = "0";
		Node errorServiceNode = null;
		long timeService = 0;
		boolean serviceSuccess = false;
		String requestURL = null;
		Node parametersRequestURL = IoCommons.param_mapToNode("service",parametersMap);
		final int timeOut = getParameterValue(PROP_REQUEST_TIMEOUT, null, 0);

		// Mode try de récupération des erreurs pour log
		final long before = System.currentTimeMillis();
		try{

			//	Etape	"1" : poids relatif de 10, Création des flux entrants et sortants
			this.WILD_setStep(); // Ne pas modifier

			requestURL = serviceUrl;
			requestURL = createURL(requestURL);
			url = new URL(requestURL);
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setDoOutput( true );
			urlConnection.setRequestMethod("POST");
			urlConnection.setDoOutput( getParameterValue(PROP_REQUEST_DOOUTPUT, null, true) );
			urlConnection.setInstanceFollowRedirects( getParameterValue(PROP_REQUEST_FOLLOWREDIRECT, null, true) );
			urlConnection.setUseCaches( getParameterValue(PROP_REQUEST_USECACHES, null, false) );
			urlConnection.setRequestProperty("Connection", getParameterValue(PROP_REQUEST_CONNECTION, null, "Keep-Alive") );
			urlConnection.setRequestProperty("Content-Type", getParameterValue(PROP_REQUEST_CONTENTTYPE, null, "text/xml;charset=UTF-8"));
			final String charset = getParameterValue(PROP_REQUEST_CHARSET, null, "utf-8");
			urlConnection.setRequestProperty("charset", charset);
			final String opName = (String) getParameterValue(PROP_REQUEST_QUERY, null, null);
			urlConnection.setRequestProperty("SOAPAction", opName);
			urlConnection.setRequestProperty("Accept", "text/xml");


			//	Etape	"2" : poids relatif de 10, Capture du flux
			this.WILD_setStep(); // Ne pas modifier
			//envoi de la requete SOAP
		
			OutputStream out1 = urlConnection.getOutputStream();
			out1.write(soapHeader.getBytes());
			out1.flush();
			out1.close();
			
			//recuperation du résultat
			
			InputStream in = openRichException(urlConnection, timeOut);
			OutputStream out2 = new BufferedOutputStream(new FileOutputStream(outPath,append));
			IOUtils.copy(in, out2);
			out2.flush();
			out2.close();

			final long after = System.currentTimeMillis();

			//	Etape	"2" : poids relatif de 10, Instanciation de la connexion
			this.WILD_setStep(); // Ne pas modifier

			errorServiceCode = ((Integer)urlConnection.getResponseCode()).toString();
			timeService = (after-before);//1000;
			serviceSuccess = true;

			//on désencapsule le basee64
			String format = IoCommons.detectFormat(outPath);
			if ("text/plain".equals(format)) {
				//ni du json ni du xml, on suppose qu'il s'agit de base64
				final Path path = new File(outPath).toPath();
				final String base64 = IOUtilities.toString(path);
				final byte[] datas = Base64.decodeBase64(base64);
				IOUtilities.writeStream(new ByteArrayInputStream(datas), path);
			}

			//on désencapsule le résultat soap
			format = IoCommons.detectFormat(outPath);
			if ("application/xml".equals(format)) {
				final Element doc = IoCommons.xml_getDocument(outPath).getDocumentElement();
				Element response = null;
				if(doc.getNodeName().toLowerCase().endsWith("envelope")) {
					final NodeList envNodes = doc.getChildNodes();
					loop:
					for (int l1=0,l1n=envNodes.getLength();l1<l1n;l1++) {
						Node cdt = envNodes.item(l1);
						if(cdt instanceof Element && cdt.getNodeName().toLowerCase().endsWith("body")) {
							final NodeList bodyNodes = cdt.getChildNodes();
							for (int l2=0,l2n=bodyNodes.getLength();l2<l2n;l2++) {
								if(bodyNodes.item((l2)) instanceof Element) {
									response = (Element) bodyNodes.item(l2);
									break loop;
								}
							}
						}
					}	
				}

				if (response!=null) {
					//on sauvegarde le contenu
					final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
					final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
					final Document bodyDoc = docBuilder.newDocument();
					response = (Element) bodyDoc.adoptNode(response);
					bodyDoc.appendChild(response);
					//on copie les namespaces de l'element racine
					final NamedNodeMap atts = doc.getAttributes();
					for(int i=atts.getLength()-1;i>=0;i--) {
						final Attr att = (Attr) atts.item(i);
						if(att==null) continue;
						if(att.getNamespaceURI()==null){
							bodyDoc.getDocumentElement().setAttributeNode((Attr)bodyDoc.adoptNode(att));
						}else{
							bodyDoc.getDocumentElement().setAttributeNodeNS((Attr)bodyDoc.adoptNode(att));
						}
					}
					DomUtilities.write(bodyDoc, new File(outPath));
				}
			}
		
			//	Etape	"3" : poids relatif de 10, Vérification des flux et clôture
			this.WILD_setStep(); // Ne pas modifier
			checkDatas(outPath,wsdlResponseXsd);

		}catch(Exception e){
			if(e.getCause()!=null&&(Exception) e.getCause() instanceof URLException)e=(Exception) e.getCause();
			//this.WILD_logException(e); // Ne pas modifier
			if (IoCommons.execution_isInterrupt(e)) errorServiceCode = "url-client-thread-interruption" ;
			else errorServiceCode = ((Integer)urlConnection.getResponseCode()).toString() ;
			if(e instanceof URLException)stats.put(STAT_ERROR_SERVICE_NODE,((URLException)e).getContentNode());
			else stats.put(STAT_ERROR_SERVICE_NODE,IoCommons.cast_String2Node("<Errors status = \"URL error\">"+IoCommons.cast_xmlEscape(e.toString())+"</Errors>"));
			if(e.getCause()!=null)stats.put(STAT_ERROR_SERVICE_MESSAGE,	e.getCause().toString());
			else stats.put(STAT_ERROR_SERVICE_MESSAGE,	e.getMessage());
			final long after = System.currentTimeMillis();
			timeService = (after-before);
			serviceSuccess = false;		
			throw e;
		}finally{
			// Fin de la méthode
			WILD_endMethod(); // Ne pas modifier
			//statistiques
			stats.put(STAT_ERROR_SERVICE_CODE,		errorServiceCode);
			stats.put(STAT_TIME_SERVICE,			timeService);
			stats.put(STAT_SERVICE_SUCCESS,			serviceSuccess);
			stats.put(STAT_REQUEST_URL,				requestURL);
			stats.put(STAT_PARAMETERS_REQUEST_URL,  parametersRequestURL);
		}
		return WILD_toReturn ; // Ne pas modifier
	}

	/**
	 * Retourne le namespace XML du schema WSDL.
	 *
	 * @return namespace XML du schema WSDL.
	 */
	public String DEV_getSchemaNamespace() {
		return schemaNamespace;
	}

	/**
	 * Construction du message SOAP a partir des parametres.
	 *
	 * @param opName nom de l'operation SOAP
	 * @param targetNamespaces namespace xml de l'operation
	 * @param parameters
	 * @return
	 * @throws ParserConfigurationException
	 * @throws TransformerException
	 * @throws IOException
	 */
	static String DEV_buildSoapMessage(String opName, String targetNamespaces, Map<String,?> parameters) throws ParserConfigurationException, TransformerException, IOException {
		final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		docFactory.setNamespaceAware(true);
		final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		final Document doc = docBuilder.newDocument();
		final Element env = doc.createElementNS(NS_SOAP_ENV,"soapenv:Envelope");
		env.setAttribute("xmlns:soapenv", NS_SOAP_ENV);
		env.setAttribute("xmlns:ns", targetNamespaces);
		doc.appendChild(env);

		final Element header = doc.createElementNS(NS_SOAP_ENV, "soapenv:Header");
		env.appendChild(header);

		final Element body = doc.createElementNS(NS_SOAP_ENV, "soapenv:Body");
		env.appendChild(body);

		final Element opNode = doc.createElementNS(targetNamespaces, "ns:"+opName);
		body.appendChild(opNode);

		for (Entry<String,?> entry : parameters.entrySet()) {
			if (entry.getKey().startsWith(TYPE_PARAMETER)) {
				final String[] parts = entry.getKey().split("\\.");
				if (parts.length==3 && !opName.equals(parts[2])) {
					
					//ce parametre est pour une autre operation
					continue;
				}

				final Map<String,Object> properties = (Map<String,Object>) entry.getValue();
				if (!properties.containsKey("position")) continue;

				String path = (String) properties.get("position");
				if (path==null) {
					//on prend le nom de la propriété comme chemin
					path = parts[1];
				}
				final String[] segments = path.split("/");

				Object value = properties.get("value");
				if (value==null) continue;

				if (!(value instanceof Collection)) {
					value = Arrays.asList(value);
				}

				for (Object v : (Collection)value) {

					//creation du path
					Node node = opNode;
					for (int i=0;i<segments.length-1;i++) {
						Element cdt = DomUtilities.firstElement(env, "ns:"+segments[i]);
						if (cdt == null) {
							cdt = doc.createElementNS(targetNamespaces, "ns:"+segments[i]);
							node.appendChild(cdt);
						}
						node = cdt;
					}

					//ajout de la valeur
					final String tip = segments[segments.length-1];
					if (tip.startsWith("@")) {
						final String attName = tip.substring(1);
						Attr att = ((Element)node).getAttributeNode(attName);
						if (att==null) {
							att = doc.createAttribute(attName);
							att.setValue(String.valueOf(v));
							((Element)node).setAttributeNode(att);
						} else {
							att.setValue(String.valueOf(v));
						}
					} else {
						final Node child = doc.createElementNS(targetNamespaces, "ns:"+tip);
						child.setTextContent(String.valueOf(v));
						node.appendChild(child);
					}
				}

			}
		}

		final String encoding = IoCommons.param_getValue((Map)parameters, PROP_REQUEST_CHARSET, null, "utf-8");
		final StringWriter writer = new StringWriter();
		IoCommons.xml_write(doc, encoding, writer);
		return writer.toString();
	}

	/**
	 * Methode utilitaire pour enlever le prefix d'un nom.
	 * 
	 * @param name
	 * @return 
	 */
	private static String DEV_stripPrefix(String name) {
		if (name==null) return null;
		final int idx = name.indexOf(':');
		return idx>=0 ? name.substring(idx+1) : name;
	}

	/**
	 * 
	 * @param name1
	 * @param name2
	 * @return 
	 */
	private static boolean DEV_equalsNoPrefix(String name1, String name2) {
		if(name1 == name2) return true;
		if(name1==null || name2==null) return false;
		return DEV_stripPrefix(name1).equals(DEV_stripPrefix(name2));
	}

}

