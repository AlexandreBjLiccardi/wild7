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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import fr.wild.utils.FiniteNumberIterator;
import net.sf.saxon.tree.linked.DocumentImpl;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Constructor;
import java.math.BigInteger;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.wild.orchestra.*;
import java.io.FileNotFoundException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.apache.sis.internal.util.DoubleDouble;
import org.apache.sis.math.Statistics;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.geotoolkit.nio.IOUtilities;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Text;

/**
 * Classe générique entrée / sortie, transformation d'objets
 * @author alexandre.liccardi
 * @version 0.1a "Ambiant Ambivalence"
 * @param <R>
 * @param <T>
 */
public  class IoCommons<R, T> {
	
	public static String detectFormat(String i_serviceResponseFile) throws FileNotFoundException, IOException {

		final File file = new File(i_serviceResponseFile);
		if (!file.exists()) {
			return null;
			//throw new FileNotFoundException("Le fichier "+i_serviceResponseFile+" n'existe pas.");
		}

		String responseFormat = "text/plain";
		try (DataInputStream in = new DataInputStream(new FileInputStream(i_serviceResponseFile))) {
			loop:
			for (int c = in.read(); c>=0; c=in.read()) {
				switch (c) {
					case ' ': continue;
					case '<': responseFormat = "application/xml"; break loop;
					case '{': responseFormat = "application/json"; break loop;
					default: break loop;
				}
			}
		}
		return responseFormat;
	}
	public static String detectFormat_str(String i_serviceResponseFile) throws FileNotFoundException, IOException {

		String responseFormat = "text/plain";
		try (InputStream  in = new ByteArrayInputStream(i_serviceResponseFile.getBytes(StandardCharsets.UTF_8))) {
			loop:
			for (int c = in.read(); c>=0; c=in.read()) {
				switch (c) {
					case ' ': continue;
					case '<': responseFormat = "application/xml"; break loop;
					case '{': responseFormat = "application/json"; break loop;
					default: break loop;
				}
			}
		}
		return responseFormat;
	}
    /**
     * Find non-alphanumeric characters. Compiled staticly to avoid costly operation
     * on each call to {@link IoCommons#check_escapeAll(java.lang.String) }.
     */
    private final static Pattern NON_ALPHANUMERIC = Pattern.compile("[^a-zA-Z0-9]+");

	private static final XPathFactory XPATH_FACTORY = XPathFactory.newInstance();

    // PARSERS JSON

	/* Fonctions de parcours xPath d'un fichier JSON
	 * json_getFirstValue
	 * json_getValues
	 * json_getNode
	 */

	/**
     * Renvoie la première occurence d'un Path dans un fichier, un document ou un noeud, sous forme de valeur
     * @param jsonFilePath                        Chemin d'accès au fichier
     * @param jPath                               path de recherche dans le fichier
     * @return                                    Résultat de la requête dans le document
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static Object json_getFirstValue(String jsonFilePath, String jPath) throws IOException {
        final List lst = json_getValues(jsonFilePath, jPath);
		return lst.isEmpty() ? null : lst.get(0);
    }

    /**
     * Renvoie la première occurence d'un Path dans un fichier, un document ou un noeud, sous forme de valeur
     * @param jsonMap                            JSon sous forme de Map
     * @param jPath                               Path de recherche dans le fichier
     * @return                                    Résultat de la requête dans le document
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static Object json_getFirstValue(Map jsonMap, String jPath) {
		final List lst = json_getValues(jsonMap, jPath);
		return lst.isEmpty() ? null : lst.get(0);
    }

	/**
     * Renvoie les occurences d'un Path dans un fichier Json sous forme de valeur
	 *
     * @param jsonFilePath                        Chemin d'accès au fichier
     * @param jPath                               Path de recherche dans le fichier
     * @return                                    Résultat de la requête dans le document
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
	public static List json_getValues(String jsonFilePath, String jPath) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
		final Map jsonNode = mapper.readValue(new File(jsonFilePath),Map.class);
		return json_getValues(jsonNode, jPath);
    }

    /**
     * Renvoie les occurences d'un Path dans un fichier Json sous forme de valeur.
	 * Supporte la syntaxe XPath simple ou JsonPath tel que définie dans https://github.com/jayway/JsonPath.
	 *
     * @param jsonMap                             JSon sous forme de Map
     * @param jPath                               Path de recherche dans le fichier
     * @return                                    Résultat de la requête dans le document
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static List json_getValues(Map jsonMap, String jPath) {

		if (jPath.startsWith("/")) {
			//syntax xpath
			while(jPath.startsWith("/")) jPath = jPath.substring(1);
			final List results = new ArrayList();
			json_find(jsonMap, jPath, results);
			return results;

		} else {
			//syntax jsonpath
			Object value = JsonPath.parse(jsonMap).read(jPath);
			if (value==null) {
				return Collections.EMPTY_LIST;
			} else if(value instanceof List) {
				return (List) value;
			} else {
				return Collections.singletonList(value);
			}
		}
    }

	private static void json_find(Map jsonMap, String xpath, List<Object> results) {
		final int idx = xpath.indexOf('/');
		if (idx>0) {
			final String segment = xpath.substring(0, idx);
			final String subPath = xpath.substring(idx+1);
			final Object candidate = jsonMap.get(segment);
			if (candidate instanceof Map) {
				//sous document
				json_find((Map)candidate, subPath, results);
			} else if(candidate instanceof Collection) {
				//liste de valeurs
				for (Object cdt : ((Collection)candidate)) {
					if (cdt instanceof Map) {
						//sous document
						json_find((Map)cdt, subPath, results);
					}
				}
			}
		} else {
			final Object candidate = jsonMap.get(xpath);
			if (candidate instanceof Collection) {
				results.addAll((Collection)candidate);
			} else if (candidate != null) {
				results.add(candidate);
			}
		}
	}

	/**
	 * Lecture du json sous forme de noeud.
	 *
	 * @param jsonFilePath
	 * @return
	 * @throws IOException 
	 */
	public static JsonNode json_getNode(String jsonFilePath) throws IOException {
		final ObjectMapper mapper = new ObjectMapper();
		return mapper.readTree(new File(jsonFilePath));
	}

	// PARSERS de PARAMETRES

	/**
	 * Convertion des parametres en Map Java.
	 *
	 * @param i_parametersNode Paramètres sous forme de nœud XML
	 * @param i_parametersXmlFile Paramètres sous forme de fichier XML dont on donne le lien
	 * @param i_parametersMap Paramètres sous forme de Map
	 * @return Map de parametres
	 */
	public static Map<String, Map<String,Object>> param_readParameters(
			Node i_parametersNode,
			String i_parametersXmlFile,
			Map<String,Map<String,Object>> i_parametersMap)
			throws SAXException, IOException, ParserConfigurationException {

		//on s'assure de ne jamais avoir une map nulle
		if (i_parametersMap == null) {
			i_parametersMap = new LinkedHashMap<>();
		}

		// lecture du fichier xml
		if (i_parametersXmlFile != null && !i_parametersXmlFile.isEmpty()) {
			i_parametersNode = IoCommons.xml_getDocument(i_parametersXmlFile);
		}

		// transformation en Map Java
		if (i_parametersNode != null) {

			if (i_parametersNode instanceof Document) {
				i_parametersNode = ((Document)i_parametersNode).getDocumentElement();
			}

			final NodeList nodes = i_parametersNode.getChildNodes();
			for (int i=0,n=nodes.getLength();i<n;i++) {
				final Node node = nodes.item(i);
				if ("parameter".equalsIgnoreCase(node.getNodeName())) {
					//lecture d'un parametre
					final Map.Entry<String, Map<String, Object>> entry = param_readParameter(node);
					i_parametersMap.put(entry.getKey(), entry.getValue());
				}else if ("operation".equalsIgnoreCase(node.getNodeName())) {
					//lecture d'une operation
					final String opName = ((Element)node).getAttribute("id");
					final String opOrder = ((Element)node).getAttribute("order");
					final NodeList subNodes = node.getChildNodes();
					for (int k = 0, kn = subNodes.getLength(); k < kn; k++) {
						final Node subNode = subNodes.item(k);
						if ("parameter".equalsIgnoreCase(subNode.getNodeName())) {
							//lecture d'un parametre
							final Map.Entry<String, Map<String, Object>> entry = param_readParameter(subNode);
							//on ajoute l'ordre de l'operation
							entry.getValue().put("order", opOrder);
							i_parametersMap.put(entry.getKey()+"."+opName, entry.getValue());
						}
					}
				}
			}
		}

		//verification de la structure de la Map
		for (Entry<String,Map<String,Object>> entry : i_parametersMap.entrySet()) {
			Object value = entry.getValue();
			if (!(value instanceof Map)) {
				throw new IOException("La valeur de clé "+entry.getKey()+" doit être une Map, mais est actuellement : "+value);
			}
		}

		return i_parametersMap;
	}

	/**
	 * Conversion d'un noeud DOM de parametre en Map Java.
	 *
	 * @param node noeud DOM parametre.
	 * @return Entry avec la clé <type>.<id> et la map des propriétés.
	 */
	public static Map.Entry<String, Map<String, Object>> param_readParameter(Node node) {
		String id = null;
		String type = null;
		final Map<String, Object> properties = new LinkedHashMap<>();

		//lecture des attributes
		final NamedNodeMap attributes = node.getAttributes();
		for (int k = 0, kn = attributes.getLength(); k < kn; k++) {
			final Node item = attributes.item(k);
			final String attName = item.getNodeName();
			final String attValue = item.getNodeValue();
			if ("id".equals(attName)) {
				id = attValue;
			} else if ("type".equals(attName)) {
				type = attValue;
			} else {
				final Object previous = properties.get(attName);
				if (previous instanceof List) {
					((List) previous).add(attValue);
				} else if (previous != null) {
					final List list = new ArrayList(2);
					list.add(previous);
					list.add(attValue);
					properties.put(attName, list);
				} else {
					properties.put(attName, attValue);
				}
			}
		}

		//lecture des sous noeuds
		final NodeList attNodes = node.getChildNodes();
		for (int k = 0, kn = attNodes.getLength(); k < kn; k++) {
			final Node item = attNodes.item(k);
			if (item instanceof Element) {
				final String attName = item.getNodeName();
				final Object attValue = ((Element) item).getTextContent();
				final Object previous = properties.get(attName);
				if (previous instanceof List) {
					((List) previous).add(attValue);
				} else if (previous != null) {
					final List list = new ArrayList(2);
					list.add(previous);
					list.add(attValue);
					properties.put(attName, list);
				} else {
					properties.put(attName, attValue);
				}
			}
		}

		return new AbstractMap.SimpleEntry<>(type+"."+id, properties);
	}

	/**
	 * Transforme une Map de parametres en noeud DOM.
	 *
	 * @param parentName nom du noeud parent
	 * @param parameters
	 * @return
	 */
	public static Node param_mapToNode(String parentName, Map<String, ?> parameters) throws ParserConfigurationException {
		final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		final Document doc = docBuilder.newDocument();

		final Element root = (Element) doc.createElement(parentName);
		doc.appendChild(root);

		final Map<String,Element> operationNodes = new LinkedHashMap<>();

		for (Entry<String,?> entry : parameters.entrySet()) {
			final String[] keyParts = entry.getKey().split("\\.");
			final String type = keyParts[0];
			final String id = keyParts[1];
			final String operation = keyParts.length==3 ? keyParts[2] : null;
			final Map<String,Object> properties = (Map<String,Object>) entry.getValue();

			Element parent = root;
			if (operation != null) {
				parent = operationNodes.get(operation);
				if (parent == null) {
					parent = doc.createElement("operation");
					parent.setAttribute("id", operation);
					if (properties.containsKey("order")) {
						parent.setAttribute("order", String.valueOf(properties.get("order")));
					}
					root.appendChild(parent);
					operationNodes.put(operation, parent);
				}
			}

			final Element paramNode = doc.createElement("parameter");
			paramNode.setAttribute("type", type);
			paramNode.setAttribute("id", id);
			if (properties.containsKey("position")) {
				paramNode.setAttribute("position", String.valueOf(properties.get("position")));
			}
			parent.appendChild(paramNode);

			for (Entry<String,Object> property : properties.entrySet()) {
				final String key = property.getKey();
				if ("order".equals(key)) continue;
				Object value = property.getValue();
				if (value instanceof Collection) {
					value = ((Collection)value).toArray();
				}
				if (value != null) {
					if (value.getClass().isArray()) {
						for (int i=0,n=Array.getLength(value); i<n; i++) {
							final Element propNode = doc.createElement(key);
							propNode.setTextContent(String.valueOf(Array.get(value, i)));
							paramNode.appendChild(propNode);
						}
					} else {
						final Element propNode = doc.createElement(key);
						propNode.setTextContent(String.valueOf(value));
						paramNode.appendChild(propNode);
					}
				}
			}
		}

		return doc;
	}

	/**
	 * Récupérer la valeur du parametre.<br>
	 * Cette méthode essai de récupérer la valeur dans les clés :
	 * <ul>
	 *	<li>type.id.opName</li>
	 *	<li>type.id</li>
	 * </ul>
	 *
	 * @param <T>
	 * @param parametersMap map de paramètres
	 * @param name
	 * @param defaulValue valeur utilisé si le paramètre n'est pas défini
	 * @param override valeur de surcharge, si définie elle est retournée
	 * @return valeur ou valeur par défaut
	 */
	public static <T extends Object> T param_getValue(Map<String,Map<String,Object>> parametersMap, String name, T override, T defaulValue) {
		if (override!=null) return override;

		// name : type.id.opName
		final String[] parts = name.split("\\.");

		T value = null;
		if (parts.length==3 || "request.query".equals(name)) {
			final Map<String,Object> parameter = parametersMap.get(name);
			if (parameter != null) {
				value = (T) parameter.get("value");
			}
		} else {
			//search if we have a more accurate value for the current operation
			final String opName = param_getValue(parametersMap,"request.query",null,null);
			if (opName!=null) {
				value = param_getValue(parametersMap,name+"."+opName, null, null);
			}
			//search a global parameter
			if(value == null) {
				final Map parameter = parametersMap.get(name);
				if (parameter != null) {
					value = (T) parameter.get("value");
				}
			}
		}
		if(value == null) return defaulValue ;
		if(defaulValue == null) return value ;
		if(value.getClass() == defaulValue.getClass())return value ;
		try {
			return (T) cast_2Object(defaulValue.getClass().getSimpleName(), value)[1];
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

    // PARSERS XML


    /* Fonctions de parcours xPath d'un fichier XML (sans indexes)
     * xml_getFirstValue : renvoie la première occurence d'un xPath dans un fichier, un document ou un noeud, sous forme de valeur
     * xml_getFirstNode : renvoie la première occurence d'un xPath dans un fichier, un document ou un noeud, sous forme de noeud
     * xml_getFileAsNode : convertit un fichier XML en liste de noeuds
     * xml_getNodes : renvoie la liste de noeuds contenus par un fichier XML, répondant au xPath
     * xml_getDocument : convertit un fichier XML en document parsable
     */
    /**
     * Renvoie la première occurence d'un xPath dans un fichier, un document ou un noeud, sous forme de valeur
     * @param xmlFilePath                        Chemin d'accès au fichier
     * @param xPath                                Xpath de recherche dans le fichier
     * @return                                    Résultat de la requête dans le document (première occurencce)
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static String xml_getFirstValue(String xmlFilePath, String xPath) throws DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException {
        if(xml_getFirstNode(xmlFilePath, xPath).getNodeValue()==null)return null ;
        return (String) xml_getFirstNode(xmlFilePath, xPath).getNodeValue().trim() ;
    }

    /**
     * Renvoie la première occurence d'un xPath dans un fichier, un document ou un noeud, sous forme de valeur
     * @param xmlNode                            Noeud XML
     * @param xPath                                Xpath de recherche dans le fichier
     * @return                                    Résultat de la requête dans le document (première occurencce)
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static String xml_getFirstValue(Node xmlNode, String xPath) throws DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException {
        Node toRetNode = xml_getFirstNode(xmlNode, xPath);
        if(toRetNode==null)return null;
        String toRet = (String) toRetNode.getNodeValue();
        if(toRet == null)return null;
        return toRet.trim() ;
    }

    /**
     * Renvoie la première occurence d'un xPath dans une liste de noeuds
     * @param pathFile    liste de noeuds
     * @param xPath    Xpath de recherche dans le fichier
     * @return    Résultat de la requête dans la liste (première occurence, noeud)
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static String xml_getFirstValue(NodeList toSearchIn, String xPath) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
        for(Integer i = 0 ; i< toSearchIn.getLength() ; i++){
        	String returnNode = xml_getFirstValue(toSearchIn.item(i), xPath);
        	if(returnNode!=null&&returnNode.length()!=0)return returnNode;
        }
    	return null ;
    }
    /**
     * Renvoie la liste des occurences d'un xPath dans un fichier, un document ou un noeud, sous forme de valeur
     * Si la valeur (équivalent au xpath "test()") est nulle : ne garde rien pour l'entrée
     * @param xmlNode                        Noeud XML
     * @param xPath                            Xpath de recherche dans le fichier
     * @return                                Résultat de la requête dans le document (toutes les occurencces)
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static ArrayList<String> xml_getValues(Node xmlNode, String xPath) throws DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException {
        return xml_getValues(xmlNode, xPath, false) ;
    }

    /**
     * Renvoie la liste des occurences d'un xPath dans un fichier, un document ou un noeud, sous forme de valeur
     * Si la valeur (équivalent au xpath "test()") est nulle : garde NULL pour l'entrée
     * @param xmlNode                        Noeud XML
     * @param xPath                            Xpath de recherche dans le fichier
     * @return                                Résultat de la requête dans le document (toutes les occurencces)
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static ArrayList<String> xml_getValues_keepNull(Node xmlNode, String xPath) throws DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException {
        return xml_getValues(xmlNode, xPath, true) ;
    }

    /**
     * Renvoie la liste des occurences d'un xPath dans un fichier, un document ou un noeud, sous forme de valeur
     * Si la valeur (équivalent au xpath "test()") est nulle : ne garde rien pour l'entrée
     * @param xmlNode                        Noeud XML
     * @param xPath                            Xpath de recherche dans le fichier
     * @param keepNull                        Si à true : Si la valeur (équivalent au xpath "test()") est nulle : garde NULL pour l'entrée -- Si à false : Si la valeur (équivalent au xpath "test()") est nulle : ne garde rien pour l'entrée
     * @return                                Résultat de la requête dans le document (toutes les occurencces)
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static ArrayList<String> xml_getValues(Node xmlNode, String xPath, Boolean keepNull) throws DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException {
        NodeList toRetNode = xml_getNodes(xmlNode, xPath);
        if(toRetNode==null)return null;
        ArrayList<String> toRet = new ArrayList<String>();
        for(int i=0; i <  toRetNode.getLength() ; i++){
            String bk = xml_getText(toRetNode.item(i));
            if(bk!=null&&bk.length()>0)toRet.add(bk);
            else if(keepNull)toRet.add(null);
        }
        return toRet ;
    }

    /**
     * Renvoie la première occurence d'un xPath dans un fichier, un document ou un noeud, sous forme de noeud
     * @param pathFile    Chemin d'accès au fichier
     * @param xPath    Xpath de recherche dans le fichier
     * @return    Résultat de la requête dans le document (première occurence, noeud)
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static Node xml_getFirstNode(String pathFile, String xPath) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
    	NodeList nl = xml_getNodes(pathFile, xPath);
    	if(nl==null)return null;
    	return nl.item(0) ;
    }
    /**
     * Renvoie la première occurence d'un xPath dans une liste de noeuds
     * @param pathFile    liste de noeuds
     * @param xPath    Xpath de recherche dans le fichier
     * @return    Résultat de la requête dans la liste (première occurence, noeud)
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static Node xml_getFirstNode(NodeList toSearchIn, String xPath) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
        for(Integer i = 0 ; i< toSearchIn.getLength() ; i++){
        	Node returnNode = xml_getFirstNode(toSearchIn.item(i), xPath);
        	if(returnNode!=null)return returnNode;
        }
    	return null ;
    }
    /**
     * Renvoie la première occurence d'un xPath dans un fichier, un document ou un noeud, sous forme de noeud
     * @param xmlNode    Noeud XML
     * @param xPath    Xpath de recherche dans le fichier
     * @return    Résultat de la requête dans le document (première occurence, noeud)
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static Node xml_getFirstNode(Node xmlNode, String xPath) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
        if(xmlNode == null)return null;
        return xml_getNodes(xmlNode, xPath).item(0) ;
    }

    /**
     * Recupère un fichier en tant que noeud XML
     * @param pathFile    Chemin du fichier à récupérer
     * @return    Noeud XML
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws XPathExpressionException
     */
    public static NodeList xml_getFileAsNode(String pathFile) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
        return xml_getNodes(pathFile, "/");
    }

    /**
     * xml_getIndexedValues récupère dans un noeud, un autre noeud avec balise de nommage [n_name], indexé par un identifiant nommé [n_ident] et un type nommé [n_type].
     * Renvoie et cast l'objet indexé et casté", dans une liste.
     * Cast les identifiacteur en lower.
     * Pour les fichiers typés DICE : n_name = "parameter", n_ident = "ident", n_type = "type".
     * @param n_toSeek
     * @param n_name
     * @param n_ident
     * @param n_type
     * @return
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static LinkedHashMap<String,Object> xml_getIndexedValues(Node n_toSeek, String n_name, String n_ident, String n_type) throws DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        LinkedHashMap<String,Object>  toRet = new LinkedHashMap<String,Object>();
        NodeList n_toSeekList = xml_getNodes(n_toSeek,"//"+n_name);
        for(int i=0 ; i < n_toSeekList.getLength() ; i++){
            Node n_toSeekCurr = n_toSeekList.item(i);
            String n_named = xml_getFirstValue(n_toSeekCurr,"./@"+n_ident);
            String n_valued = xml_getFirstValue(n_toSeekCurr,"./text()");
            String n_typed = (xml_getFirstValue(n_toSeekCurr,"./@"+n_type) != null) ? xml_getFirstValue(n_toSeekCurr,"./@"+n_type) : "String";
            if(n_named!= null && !n_named.equals("")){
                toRet.put(n_named.toLowerCase(),null);
                try{
                    Object n_obj = cast_2Object(n_typed, n_valued)[1];
                    toRet.put(n_named,n_obj);
                }catch(Exception e){continue;}
            }
        }
        return toRet ;
    }

    /**
     * Renvoie la première occurence d'un xPath dans un fichier, un document ou un noeud, sous forme de noeud
     * @param pathFile    Chemin d'accès au fichier
     * @param xPath    Xpath de recherche dans le fichier
     * @return    Résultat de la requête dans le document (toutes les occurences, noeud)
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static NodeList xml_getNodes(String pathFile, String xPath) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
        XPath xpath;
		synchronized (XPATH_FACTORY) { xpath = XPATH_FACTORY.newXPath(); }
        if(!new File(pathFile).exists())return null;
        Document docClient = xml_getDocument(pathFile) ;
        return (NodeList) xpath.evaluate(xPath, docClient, XPathConstants.NODESET);
    }

    /**
     * Renvoie la première occurence d'un xPath dans un fichier, un document ou un noeud, sous forme de noeud
     * Crée une copie du fichier XML parsé, dans lequel les espaces de nommage xmlns sont supprimés
     * @param pathFile    Chemin d'accès au fichier
     * @param xPath    Xpath de recherche dans le fichier
     * @return    Résultat de la requête dans le document (toutes les occurences, noeud)
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static NodeList xml_getNodes_noXmlns(String pathFile, String xPath, Boolean keepIfExists) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
        XPath xpath;
		synchronized (XPATH_FACTORY) { xpath = XPATH_FACTORY.newXPath(); }
        if(!new File(pathFile).exists())return null;
        Document docClient = xml_getDocument_noXmlns(pathFile, keepIfExists) ;
        return (NodeList) xpath.evaluate(xPath, docClient, XPathConstants.NODESET);
    }

    /**
     * Renvoie la première occurence d'un xPath dans un fichier, un document ou un noeud, sous forme de noeud
     * @param xmlNode    Noeud XML
     * @param xPath    Xpath de recherche dans le fichier
     * @return    Résultat de la requête dans le document (toutes les occurences, noeud)
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static NodeList xml_getNodes(Node xmlNode, String xPath) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
        if(xmlNode == null)return  null;
        XPath xpath;
		synchronized (XPATH_FACTORY) { xpath = XPATH_FACTORY.newXPath(); }
        return (NodeList) xpath.evaluate(xPath, xmlNode, XPathConstants.NODESET);
    }

    /**
     * Renvoie la première occurence d'un xPath dans un fichier, un document ou un noeud, sous forme de noeud
     * @param xmlNodes    Liste de noeuds XML
     * @param xPath    Xpath de recherche dans le fichier
     * @return    Résultat de la requête dans le document (toutes les occurences, noeud)
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static NodeList xml_getNodes(NodeList xmlNodes, String xPath) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
        if(xmlNodes == null)return  null;
        XPath xpath;
		synchronized (XPATH_FACTORY) { xpath = XPATH_FACTORY.newXPath(); }
        return (NodeList) xpath.evaluate(xPath, xmlNodes, XPathConstants.NODESET);
    }

    /**
     * convertit un fichier XML en document parsable
     * @param pathFile        Chemin d'accès au fichier à convertir en document
     * @return                Document XML accessible via JAVA
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static Document xml_getDocument(String pathFile) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        return docBuilder.parse(pathFile);
    }

    /**
     * convertit un fichier XML en document parsable, supprime les espace de nommage xmlns
     * @param pathFile        Chemin d'accès au fichier à convertir en document
     * @return                Document XML accessible via JAVA
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static Document xml_getDocument_noXmlns(String pathFile, Boolean keepIfExists) throws SAXException, IOException, ParserConfigurationException {
        // Copie du fichier en supprimant les Xmlns
        OutputStream out_FileOutPut = null ;
        int i = 0;
        String newPathFile = pathFile+"_"+i ;
        if(keepIfExists==false || !(new File(newPathFile).exists())){
            try {
                while(new File(newPathFile).exists())newPathFile = pathFile+"_#6D#_"+i++;
                String xmlContents = "";
                byte[] encoded = Files.readAllBytes(Paths.get(pathFile));
                xmlContents =  new String(encoded);
                xmlContents =
                        xmlContents.replaceAll("(<\\?[^<]*\\?>)?", ""). /* remove preamble */
                          replaceAll("xmlns.*?(\"|\').*?(\"|\')", "") /* remove xmlns declaration */
                          .replaceAll("(<)(\\w+:)(.*?>)", "$1$3") /* remove opening tag prefix */
                          .replaceAll("(</)(\\w+:)(.*?>)", "$1$3"); /* remove closing tags prefix */;
                File out_File =  new File(newPathFile) ;
                if(out_File.exists())out_File.delete();
                out_File.getParentFile().mkdirs();
                out_FileOutPut = new FileOutputStream(out_File);
                out_FileOutPut.write(xmlContents.getBytes());
            }catch(Exception e){
                e.printStackTrace();
            } finally {
                if(out_FileOutPut!=null)out_FileOutPut.close();
            }
        }

        // Résolution de l'opération
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        return docBuilder.parse(newPathFile);
    }

    /**
     * xml_getText renvoie l'équivalent de la fonction xpath "text()", appliquée sur un noeud.
     * @param parentNode    Noeud XML duquel le texte est extrait
     * @return
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static String xml_getText(Node parentNode) throws DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        String txt = xml_getFirstValue(parentNode,"./text()");
        if(txt==null)return null;
        return xml_getFirstValue(parentNode,"./text()").trim();
    }

	/**
     * Ecriture d'un document dom.
     *
     * @param doc : document a écrire
	 * @param encoding : encodage de sortie
     * @param output : fichier de sortie, File, URL, URI, OutputStream
     * @throws TransformerException
     */
    public static void xml_write(final Document doc, String encoding, final Object output) throws TransformerException, FileNotFoundException, IOException{
        
		if (encoding == null) encoding = "ISO-8859-1";

		final Source source = new DOMSource(doc);

        final Result result;
        if(output instanceof File){
            result = new StreamResult((File)output);
        }else if(output instanceof Writer){
            result = new StreamResult((Writer)output);
        }else{
            final OutputStream stream = IOUtilities.openWrite(output);
            result = new StreamResult(stream);
        }

        final TransformerFactory factory = TransformerFactory.newInstance();
        final Transformer trs = factory.newTransformer();
        trs.setOutputProperty(OutputKeys.INDENT, "yes");
        trs.setOutputProperty(OutputKeys.ENCODING, encoding);

        trs.transform(source, result);
    }

	/**
	 * Conversion d'un Node en Map Java.
	 * 
	 * @param node
	 * @return Map
	 */
	public static Map<String,Object> xml_nodeToMap(Node node) {
		final Map<String,Object> map = new HashMap<>();
		final NamedNodeMap attributes = node.getAttributes();
		if (attributes!=null) {
			for (int i=0,n=attributes.getLength();i<n;i++) {
				final Attr attr = (Attr) attributes.item(i);
				final String name = attr.getName();
				final String value = attr.getValue();
				map.put(name, value);
			}
		}
		final NodeList childNodes = node.getChildNodes();
		if (childNodes!=null) {
			for (int i=0,n=childNodes.getLength();i<n;i++) {
				final Node child = childNodes.item(i);
				if (child instanceof Element) {
					if (child.getChildNodes().getLength()==1 && child.getChildNodes().item(0) instanceof Text) {
						map.put(((Element) child).getTagName(), child.getTextContent());
					} else {
						map.put(((Element) child).getTagName(), xml_nodeToMap(child));
					}
				}
			}
		}
		return map;
    }

    // MISE A JOUR DE MAPS
    /**
     * Mise à jour de map1 (contenante) contenant une map2 (contenue)
     * Actualise (ajoute ou modifie une entrée de map2) l'ensemble des entrées de map1
     * @param mapToUpdate    Map1 à mettre à jour
     * @param k2            Clé à actualiser (à ajouter ou à modifier) dans map2
     * @param newValue        Nouvelle valeur à affecter à map2(k2)
     * @return                Map modifiée
     */
    public static <T> HashMap<T, HashMap<T, Object>> map_update(HashMap<T,HashMap<T,Object>> mapToUpdate, T k2, Object newValue){
        if(mapToUpdate==null)return null;
        for(T k1:mapToUpdate.keySet()){
            HashMap<T,Object> mapToUpdate_in = mapToUpdate.get(k1);
            mapToUpdate_in.put(k2,newValue);
            mapToUpdate.put(k1, mapToUpdate_in);
        }
        return mapToUpdate ;
    }

    /**
     * Mise à jour de map1 (contenante) contenant une map2 (contenue)
     * Actualise (ajoute ou modifie une entrée de map2) l'entrée d'identifiant k1 de map1
     * @param mapToUpdate    Map1 à mettre à jour
     * @param k1            Clé à actualiser (à ajouter ou à modifier) dans map1
     * @param k2            Clé à actualiser (à ajouter ou à modifier) dans map2
     * @param newValue        Nouvelle valeur à affecter à map1(k1,map2(k2))
     * @return                Map modifiée
     */
    public static <T> HashMap<T, HashMap<T, Object>> map_update(HashMap<T,HashMap<T,Object>> mapToUpdate, T k1, T k2, Object newValue){
        if(mapToUpdate==null)return null;
        HashMap<T,Object> mapToUpdate_in = mapToUpdate.get(k1);
        mapToUpdate_in.put(k2,newValue);
        mapToUpdate.put(k1, mapToUpdate_in);
        return mapToUpdate ;
    }

    
    public static Map<String,Map<String,Object>> map_indexer(Map<String,Object> mapToIndex){
    	if(mapToIndex==null)return new HashMap();
    	Map<String,Map<String,Object>> mapToRet = new HashMap();
    	for(Entry<String,?> e:mapToIndex.entrySet()){
    		if(e.getValue() instanceof Map<?,?>&&map_isIndexed((Map<?,?>)e.getValue()))mapToRet.put(e.getKey(), (Map<String,Object>)e.getValue());
    		else{
    			Map<String,Object> mapToRet_sub = new HashMap();
    			mapToRet_sub.put("value", e.getValue());
    			mapToRet.put(e.getKey(), mapToRet_sub);
    		}
    	}
    	return mapToRet;
    }
    
    public static <S,T> Boolean map_isIndexed(Map<S,T> mapToIndex){
    	S typeParameterClass = null ;
    	if(typeParameterClass instanceof String && mapToIndex.get("value")!=null)return true ;
    	return false ;
    }
    
    /**
     * Merge rajoute une entrée si n'existe pas
     * maps_merge remplace dans la première liste, les valeurs présentes dans la seconde, si la valeurs pour la clé est nulle dans la première liste.
     * Crée une nouvelle clé si n'existe pas.
     * @param tabToUpdate            Table "de référence", qui sera actualisée
     * @param tabToUpdateWith        Table dont les données serviront à l'actualisation
     * @return
     */
    public static  <T> HashMap<String,T> map_merge(HashMap<String,T> tabToUpdate,HashMap<String,T> tabToUpdateWith){
        return map_merge(tabToUpdate,tabToUpdateWith,false);
    }

    /**
     * Merge rajoute une entrée si n'existe pas
     * maps_merge remplace dans la première liste, les valeurs présentes dans la seconde, si la valeurs pour la clé est nulle dans la première liste.
     * Crée une nouvelle clé si n'existe pas.
     * @param tabToUpdate            Table "de référence", qui sera actualisée
     * @param tabToUpdateWith        Table dont les données serviront à l'actualisation
     * @param replaceAnyway            Remplace si la valeur existe déjà
     * @return
     */
    public static <T> HashMap<String,T> map_merge(HashMap<String,T> tabToUpdate,HashMap<String,T> tabToUpdateWith, Boolean replaceAnyway ){
        if(tabToUpdate == null)return tabToUpdateWith ;
        if(tabToUpdateWith == null)return tabToUpdate ;
        Iterator<Entry<String, T>> it = tabToUpdateWith.entrySet().iterator();
        while (it.hasNext()){
          Entry<String, T> key = it.next();
          T nextVal = tabToUpdateWith.get(key.getKey());
          T prevVal = tabToUpdate.get(key.getKey());
          if((nextVal!=null&&prevVal==null)||replaceAnyway)tabToUpdate.put(key.getKey(), nextVal);
        }
        return tabToUpdate ;
    }

    /**
     * Récupère la clé la plus haute d'une map indexée par un Integer
     * @param tabToUpdate    Table dont la clé est lue
     * @return
     */
    public static Integer map_maxKey(Map<Integer,Object> tabToUpdate){
        if(tabToUpdate==null)return null;
        return map_maxKey(tabToUpdate.keySet());
    }

    /**
     * Récupère la clé la plus haute d'une collection indexée par un Integer
     * @param iK    Collection dont la clé est lue
     * @return
     */
    public static Integer map_maxKey(Set<Integer> iK){
        Integer i = -9999;
        for(Integer ik:iK)if(ik>i||i==null)i=ik;
        return i;
    }

    /**
     * Récupère un nom disponible dans une liste, pour éviter les doublons
     * @param key            Nom souhaité
     * @param keySetList    Liste dans laquelle on cherche à rajjouter une clé
     * @return                Nom souhaité éventuellement suffixé
     */
    public static String map_getAvaibleName(String key, HashMap<String,HashMap<String,Object>> keySetList){
        return map_getAvaibleName(key, keySetList.keySet()) ;
    }

    /**
     * Récupère un nom disponible dans une collection, pour éviter les doublons
     * @param key            Nom souhaité
     * @param keySet    collection dans laquelle on cherche à rajjouter une clé
     * @return                Nom souhaité éventuellement suffixé
     */
    public static String map_getAvaibleName(String key, Set<String> keySet){
        int i = 1;
        String key_mod = key ;
        while(keySet.contains(key_mod)){
            key_mod = key + "_" + i ;
            i++;
        }
        return key_mod ;
    }

    // OPERATIONS SUR TABLEAUX
    /**
     * Fusion de deux tableaux
     * @param a tableau 1
     * @param b tableau 2
     * @return    Tableau combiné à la suite
     */
    public static String[] cast_mergeArrays(String[] a, Object[] b){
        int aLen = a.length;
        int bLen = b.length;
        String[] c= new String[aLen+bLen];
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }

    // OPERATIONS DE CONVERSION DE TYPE
    /**
     * La méthode getClass essaie des conversions, en vue de déterminer si l'objet est un Object, Object[], List<Object> , Hashtable<Object,Object>,  HashMap<Object,Object>.
     * correspond à une "customisation" de .getClass().getSimpleName()
     * @param tryO    Objet dont on cherche à déterminer la classe
     * @return    Nom de la classe simplifé
     */
    public static String cast_getClass(Object tryO){
        try{
        	if((boolean) cast_2Object("Integer",tryO)[0])
            return "Integer";
        }catch(Exception e){}
        try{
        	if((boolean) cast_2Object("Long",tryO)[0])
            return "Long";
        }catch(Exception e){}
        try{
        	if((boolean) cast_2Object("Float",tryO)[0])
            return "Float";
        }catch(Exception e){}
        try{
        	if((boolean) cast_2Object("Double",tryO)[0])
        	return "Double";
        }catch(Exception e){}
        try{
            // if try0 is not a boolean a ClassCast exception will be launched
            Boolean.class.cast(tryO);
            return "Boolean";
        }catch(Exception e){}
        try{
        	if((boolean) cast_2Object("Date",tryO)[0])
            return "Date";
        }catch(Exception e){}
        try{
            Class.forName("java.lang.String").cast(tryO);
            return "String";
        }catch(Exception e){}
        try{
            Class.forName("[Ljava.lang.Object;").cast(tryO);
            return "Object[]";
        }catch(Exception e){}
        try{
            Class.forName("java.util.List").cast(tryO);
            return "List<Object>";
        }catch(Exception e){}
        try{
            Class.forName("java.awt.List").cast(tryO);
            return "List<Object>";
        }catch(Exception e){}
        try{
            Class.forName("java.util.Hashtable").cast(tryO);
            return "Hashtable<Object,Object>";
        }catch(Exception e){}
        try{
            Class.forName("java.util.HashMap").cast(tryO);
            return "HashMap<Object,Object>";
        }catch(Exception e){}
        try{
            Class.forName("java.util.LinkedHashMap").cast(tryO);
            return "HashMap<Object,Object>";
        }catch(Exception e){}
        try{
            Class.forName("org.w3c.dom.Node").cast(tryO);
            return "Node";
        }catch(Exception e){}

        return tryO.getClass().getSimpleName(); // En cas d'échec de détermination, on retourne celui mentionné par JAVA
    }
    /**
     * La méthode getSimpleClass essaie des conversions, en vue de déterminer si l'objet est un Object, Object[], List<Object> , Hashtable<Object,Object>,  HashMap<Object,Object>.
     * correspond à une "customisation" de .getClass().getSimpleName()
     * Renvoie un ensemble plus générique que cast_getClass
     * @param tryO    Objet dont on cherche à déterminer la classe
     * @return    Nom de la classe simplifé
     */
    public static String cast_getSimpleClass(Object tryO){
    	String wClass = cast_getClass(tryO) ;
    	switch(wClass){
	    	case "Date" : return "Date" ;
	    	case "String" : return "String" ;
	    	case "Integer":case "Float":case "Double": return "Double" ;
	    	case "Boolean" : return "Boolean" ;
	    	case "HashMap<Object,Object>" : return "HashMap" ;
	    	case "Hashtable<Object,Object>" : return "HashMap" ;
	    	case "List<Object>" : return "List" ;
	    	default : return wClass ;
    	}
    }
    /**
     * Conversion d'une Map en ConcurrentMap
     * Vise entre autres à éliminer les clés nulles
     * @param i_Map    Elément à convertir
     * @return ConcurrentMap "nettoyée"
     */
    public static <T,S> ConcurrentHashMap<T,S> cast_Map2ConcurrentHashMap(Map<T,S> i_Map){
        ConcurrentHashMap<T,S> toRet = new ConcurrentHashMap<T,S>();
        for(T key:i_Map.keySet())if(key!=null)toRet.put(key, i_Map.get(key));
        return toRet;
    }

    /**
     * Conversion en String "Human readable" avec déchiffage de la protection URL / XML
     * @param i_returnUpdater    Elément à convertir
     * @return    Message lisible
     */
    public static String cast_2StringDecode(Object i_returnUpdater){
        return cast_xmlUnescape(cast_2String(i_returnUpdater));
    }

    /**
     * Conversion en String "Human readable"
     * @param i_returnUpdater    Elément à convertir
     * @return    Message lisible
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static String cast_2String(Object i_returnUpdater){
        String toRet ;
        switch(cast_getClass(i_returnUpdater)){
        case "DTMNodeList":
            toRet = "<List>\n";
            NodeList returnUpdaterNL = (NodeList) i_returnUpdater;
            for(int i =0;i<returnUpdaterNL.getLength();i++)toRet+="\n<Elt6d>\n"+cast_xmlEscape(cast_2String(returnUpdaterNL.item(i)))+"\n</Elt6d>";
             return toRet+"\n</List>";
        case "Node":
            StringWriter sw = new StringWriter();
             try {
                Transformer t = TransformerFactory.newInstance().newTransformer();
                t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                t.transform(new DOMSource((Node) i_returnUpdater), new StreamResult(sw));
             } catch (TransformerException te) {
                te.printStackTrace();
             }
             return sw.toString();
        case "Object[]":
            toRet = "<array>";
            Object[] returnUpdater = (Object[]) i_returnUpdater;
            for(Integer i =0; i < returnUpdater.length; i++)toRet += "<Elt6d name='"+i+"'>"+cast_xmlEscape(cast_2String(returnUpdater[i]))+"</Elt6d>";
            toRet +=     "</array>";
            return toRet;
        case "List<Object>":
            toRet = "<list>";
            List<Object> returnUpdaterlist = (List<Object>) i_returnUpdater;
            for(Integer i =0; i < returnUpdaterlist.size(); i++)if(i<2500)toRet +=  "<Elt6d name='"+i+"'>"+cast_xmlEscape(cast_2String(returnUpdaterlist.get(i)))+"</Elt6d>";
            toRet +=     "</list>";
            return toRet;
        case "Hashtable<Object,Object>":
            toRet = "<map>";
            Hashtable<Object,Object> returnUpdatertable = (Hashtable<Object,Object>) i_returnUpdater;
            Iterator it = returnUpdatertable.entrySet().iterator();
            int i = 0 ;
            while (it.hasNext()) {
                if(i++>2500)break;
                Map.Entry pairs = (Map.Entry)it.next();
                toRet +=  "<Elt6d name='"+String.valueOf(pairs.getKey()).replaceAll("'","\\'")+"'>"+cast_xmlEscape(cast_xmlEscape(String.valueOf(pairs.getValue())))+"</Elt6d>";
            }
            toRet +=     "</map>";
            return toRet;
        case "HashMap<Object,Object>":
            toRet = "<map>";
            HashMap<Object,Object> returnUpdatermap = (HashMap<Object,Object>) i_returnUpdater;
            Iterator itmap = returnUpdatermap.entrySet().iterator();
            int i2 = 0 ;
            while (itmap.hasNext()) {
                if(i2++>2500)break;
                Map.Entry pairs = (Map.Entry)itmap.next();
                toRet +=  "<Elt6d name='"+String.valueOf(pairs.getKey()).replaceAll("'","\\'")+"'>"+cast_xmlEscape(String.valueOf(pairs.getValue()))+"</Elt6d>";
            }
            toRet +=     "</map>";
            return toRet;
        default:
            return String.valueOf(i_returnUpdater);
        }
    }

    /**
     * Evalue la longueur (le nombre d'éléments) d'un objet quelque soit son type
     * @param toEvalLength    Objet à évaluer
     * @return                Longueur évaluée
     */
    @SuppressWarnings("rawtypes")
    public static Integer cast_evalLength(Object toEvalLength){
        if(toEvalLength==null)return null;
        String toEvalClass = toEvalLength.getClass().getSimpleName() ;
        if(toEvalClass.contains("[]"))return ((Object[])toEvalLength).length;
        if(toEvalClass.contains("List"))return ((List)toEvalLength).size();
        if(toEvalClass.contains("Map"))return ((Map)toEvalLength).size();
        return 0;
    }

    /**
     * Convertit un String en Noeud XML parsable en JAVA
     * @param toCast    String à caster
     * @return            Noeud XML
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static Node cast_String2Node(String toCast) throws SAXException, IOException, ParserConfigurationException{
        if (toCast == null) return null;
        toCast = toCast.substring( toCast.indexOf("<"), toCast.lastIndexOf(">")+1);
        return (DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(toCast.getBytes("UTF-8"))).getDocumentElement());
    }

    /**
     * La méthode cast_2Integer caste un objet vers un Integer.
     * @param value    Objet à passer en integer
     * @return    Integer évalué
     */
    public static Integer cast_2Integer(Object value){
        if(value == null)return null ;
        return Integer.parseInt(cast_2String(value));
    }

    /**
     * La méthode cast_2Boolean caste un objet vers un Boolean.
     * @param value    Objet à passer en booléen
     * @return    Boolean évalué
     */
    public static <A> Boolean cast_2Boolean(A value) throws ClassNotFoundException{
        return (Boolean) cast_2Boolean(value, false);
    }

    /**
     * La méthode cast_2Boolean caste un objet vers un Boolean.
     * @param value    Objet à passer en booléen
     * @param falseIfNull Si null, on remplace par false
     * @return    Boolean évalué
     */
    public static <A> Boolean cast_2Boolean(A value, Boolean falseIfNull) throws ClassNotFoundException{
        if(falseIfNull)if(value==null)return false;
        return (Boolean) cast_2Object("boolean", value)[1];
    }

    /**
     * La méthode cast_2Object caste une chaîne String vers le type mentionné.
     * @param caster type (ex. "int") - Nom de la classe de Y
     * @param value Instance de la classe X, avec valeur
     * @return 0 : true si succès de la transformation , 2: Objet du type et de la valeur indiqués
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    public static Object[] cast_2Object(String caster, Object value) throws ClassNotFoundException{
		if (value ==null) return new Object[]{true,null};
        if (caster==null) caster = "String";

        caster = caster.trim().toLowerCase();
        // Nettoyage préalable de la valeur
        final String str_value = String.valueOf(value).trim();
        String str_value_low = str_value.toLowerCase();
        for (char rep:"'\"\\/".toCharArray()) str_value_low = str_value_low.replace(rep, '\0');

        try {
            if (caster.equals("string"))						value = str_value ;
            else if (caster.equals("long"))						value = Long.parseLong(str_value_low.replaceAll(" ",""));
            else if (caster.equals("integer") || caster.equals("int"))value = Integer.parseInt(str_value_low.replaceAll(" ",""));
            else if (caster.equals("float"))					value = Float.parseFloat(str_value_low);
            else if (caster.equals("double"))					value = Double.parseDouble(str_value_low);
            else if (caster.equals("boolean"))					value = str_value.equals("true")||str_value.equals("t")||str_value.equals("vrai")||str_value.equals("v")||str_value.equals("1");
            else if (caster.equals("date"))						value = new Date((String)value);
            else if (caster.equals("hashmap<integer,string>"))	value = (HashMap<Integer,String>) value ;
            else if (caster.equals("hashmap"))					value = (HashMap<Integer,String>) value ;

            return new Object[]{true,value};
        }catch (Exception e){
            return new Object[]{false,value.toString()};
        }
    }

    /**
     * La méthode cast_2Object caste une chaîne String vers le type mentionné.
     * Emet une erreur si erreur de transformation
     * @param caster type (ex. "int") - Nom de la classe de Y
     * @param value Instance de la classe X, avec valeur
     * @return Objet du type et de la valeur indiqués
     * @throws Exception erreur de type
     */
    @SuppressWarnings("unchecked")
    public static Object cast_2ObjectThrow(String caster, Object value) throws Exception{
        if(caster==null)caster ="String";
        Object toRet = new Object();
        if(value == null)return null ;
            caster = caster.trim().toLowerCase();
            // Essai de conversion selon le nom de classe précisé dans caster
            if(caster==null)return String.valueOf(value);
            if(value.getClass().getSimpleName().equals(caster))toRet = value ;
            // Nettoyage préalable de la valeur
            String str_value = (String.valueOf(value)).trim();
            if(caster.equals("string"))toRet= str_value ;
            str_value = str_value.toLowerCase();
            if(caster.equals("long"))toRet =  Class.forName("java.lang.Long").cast((Long)Long.parseLong(str_value.replaceAll(" ","")));
            if(caster.equals("integer")||caster.equals("int"))toRet =  Class.forName("java.lang.Integer").cast((Integer)Integer.parseInt(str_value.replaceAll(" ","")));
            if(caster.equals("float")||caster.equals("double"))toRet = Class.forName("java.lang.Float").cast((Float)Float.parseFloat(str_value));
            if(caster.equals("boolean")){
                if(str_value.equals("true")||str_value.equals("t")||str_value.equals("vrai")||str_value.equals("v"))toRet = true;
                if(str_value.equals("false")||str_value.equals("f")||str_value.equals("faux")||str_value.equals("f"))toRet = false;
                toRet = Class.forName("java.lang.Boolean").cast((Boolean)Boolean.parseBoolean(((String) value).trim()));
            }
          if(caster.equals("date")){
              Class<?> cDice = Class.forName("java.util.Date");
              Constructor<?> cCDice = cDice.getConstructor(Class.forName("java.lang.String"));
              toRet = cCDice.newInstance(value);
          }
          if(caster.equals("hashmap<integer,string>")){
              toRet = (HashMap<Integer,String>) value ;
          }
          // Par défaut, si la classe n'est pas prévue, on garde l'objet initial
          if(toRet==null)toRet = value;
          return toRet ;
    }


    public static String cast_className2UseFullClassName(Class classToDeal){
    	String nClassName = classToDeal.getName();
    	List<String[]> equivClasses = new ArrayList();
    	equivClasses.add(new String[]{"InputStream$", "java.io.InputStream"});
    	equivClasses.add(new String[]{"Map$", "java.util.Map"});
    	equivClasses.add(new String[]{"Itr$", "java.util.Iterator"});
    	equivClasses.add(new String[]{"ArrayList", "java.util.List"});
     	for(String[] eq:equivClasses){
    		 Pattern pattern = Pattern.compile(eq[0]);
    		 Matcher matcher = pattern.matcher(nClassName);
    		 if(matcher.find()) return eq[1];
    	}
    	return nClassName ;
    }

    /**
     * Convertit un tableau vers un tableau comportant les classes des objets contenus (pour appel par reflection)
     * @param toConv    tableau à convertir
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Class[] cast_array2ClassArray(Boolean getUseFull, Object[] toConv, Boolean getAbstract){
    	if(getUseFull==null)getUseFull=false;
    	if(toConv == null) return null;
        Class[] cl = new Class[toConv.length];
        for(int i=0; i < toConv.length; i++)if(toConv[i]!=null){
        	if(getUseFull)cl[i] = toConv[i].getClass();
			else
				try {
					cl[i] = Class.forName(cast_className2UseFullClassName(toConv[i].getClass()));
				} catch (ClassNotFoundException e) {
					cl[i] = toConv[i].getClass();
				}
        }
        return cl ;
    }
    public static Class[] cast_array2ClassArray(Object[] toConv, Boolean getAbstract){
    	return  cast_array2ClassArray(null,toConv, getAbstract);
    }
    /**
     * Convertit un tableau vers un tableau comportant les classes des objets contenus (pour appel par reflection)
     * @param toConv    tableau à convertir
     * @param wcL        class loader java fixant le cadre de définition de la classe, utile pour le chargement dynamique des briques
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static Class[] cast_array2ClassArray(Boolean getUseFull, Object[] toConv, WildClassLoader wcL){
    	if(getUseFull==null)getUseFull=false;
    	if(toConv == null) return null;
        Class[] cl = new Class[toConv.length];
        for(int i=0; i < toConv.length; i++)if(toConv[i]!=null){
            try {
                if(!getUseFull)cl[i] = Class.forName(toConv[i].getClass().getName()/*,true,wcL*/);
                else cl[i] = Class.forName(cast_className2UseFullClassName(toConv[i].getClass())/*,true,wcL*/);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return cl ;
    }
    public static Class[] cast_array2ClassArray(Object[] toConv, WildClassLoader wcL){
    	return cast_array2ClassArray(null, toConv, wcL);
    }

    @SuppressWarnings("rawtypes")
    public static Class[] cast_array2ClassArrayArbitraryFrom(Boolean getUseFull, Object[] toConv, WildClassLoader wcL, Integer arbitraryFrom){
    	if(getUseFull==null)getUseFull=false;
    	if(toConv == null) return null;
        Class[] cl = new Class[toConv.length];

        for(int i=0; i < toConv.length; i++){
            if(toConv[i]!=null){
                if(i<arbitraryFrom){
                    try {
                    	 if(!getUseFull)cl[i] = Class.forName(toConv[i].getClass().getName(),true,wcL);
                    	 else cl[i] = Class.forName(cast_className2UseFullClassName(toConv[i].getClass()),true,wcL);
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else{
                    try {
                        if(!getUseFull)cl[i] = Class.forName("[L"+toConv[i].getClass().getName()+";",true,wcL);
                        else cl[i] = Class.forName("[L"+cast_className2UseFullClassName(toConv[i].getClass())+";",true,wcL);
                    } catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break ;
                }
            }
        }
        return cl ;
    }
    public static Class[] cast_array2ClassArrayArbitraryFrom(Object[] toConv, WildClassLoader wcL, Integer arbitraryFrom){
    	return cast_array2ClassArrayArbitraryFrom(null, toConv, wcL, arbitraryFrom);
    }

    /**
     * Convertit une Map en tableau, possiblement ordonné (pour appel par reflection)
     * @param toConv    Map à convertir
     * @param order ordonné si la clé order#6D# est renseignée
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Object[] cast_map2Array(HashMap<String,Object> toConv, Boolean order){
        Object[] toRet = new Object[toConv.size()] ;
        Set<String> ks = toConv.keySet();
        Integer o = 0 ;
        for(String k:ks){
            if(!order){
                toRet[o++] = toConv.get(k);
            }else{
                HashMap<String,Object> contained = (HashMap<String,Object>)toConv.get(k) ;
                o = (Integer)contained.get("order#6D#")-1 ;
                toRet[o] = contained.get("value");
            }
        }
        return toRet ;
    }

    /**
     * Convertit une Map en tableau, possiblement ordonné (pour appel par reflection)
     * Garde la brique d'appel en premier paramètre
     * Utilisé pour instanciation des DiceObjects
     * @param dB    Brique d'appel
     * @param toConv    Map à convertir
     * @param order ordonné si la clé order#6D# est renseignée
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Object[] cast_map2Array(WildBrick dB, HashMap<String,Object> toConv, Boolean order){
        Object[] toRet = new Object[toConv.size()+1] ;
        toRet[0] = dB;
        Set<String> ks = toConv.keySet();
        Integer o = 1 ;
        for(String k:ks){
            if(!order){
                toRet[o++] = toConv.get(k);
            }else{
                HashMap<String,Object> contained = (HashMap<String,Object>)toConv.get(k) ;
                o = (Integer)contained.get("order#6D#") ;
                toRet[o] = contained.get("value");
            }
        }
        return toRet ;
    }


    /**
     * Génère un identifiant aléatoire
     * @return Identifant aléatoire de 32 bits, ne contenant pas "#6D#"
     */
    public static String cast_getRandom(){
        while(true){
            final String random = UUID.randomUUID().toString() ;
            if (!random.contains("#6D#")) return random;
        }
    }

    /**
     * Récupère les identifiants de la dernière méthode DICE envoyée
     * Si aucun, recherche l'object
     * @param dML    Modèle dans lequel la méthode est décrite
     * @return
     */
    public static String[] trace_getLastDiceMethod(WildModelList dML){
        StackTraceElement e[] = Thread.currentThread().getStackTrace();
        List<String> models = dML.getModelsList();
        int scored = 0 ;

       for (StackTraceElement s : e){
         	if(s.getMethodName().equals("invoke0")||s.getMethodName().equals("WILD_initialize_WildFile"))break;
        	scored++ ;
        }
       scored = (scored == 0)?0:scored-1;
         for(String model:models)for(String[] diceMethod:dML.getModel(model).getMethodNames())
        	if(e[scored].getMethodName().equals(diceMethod[1])&&e[scored].getClassName().endsWith("."+diceMethod[0])&&!diceMethod[1].equals("<init>"))return new String[]{model,diceMethod[0],diceMethod[1]};

        return trace_getLastDiceObject(dML);
    }

    /**
     * Récupère les identifiants du dernier objet DICE envoyé
     * @param dML    Modèle dans lequel l'objet est décrit
     * @return
     */
    public static String[] trace_getLastDiceObject(WildModelList dML){
        StackTraceElement e[] = Thread.currentThread().getStackTrace();
        List<String> models = dML.getModelsList();
        for(String model:models) {
            for (StackTraceElement s : e){
                for(String diceObject:dML.getModel(model).getBrickNames()){
                    if(s.getClassName().endsWith("."+diceObject))return new String[]{model,diceObject};
                }
            }
        }
        return null;
    }

    /**
     * Réduit la taille d'une Map
     * @param toPlow    Map à réduire
     * @param sizeMax    Nombre d'entrées conservées
     * @return Map ne contenant que les sizeMax premières entrées
     */
    public static <R,T> Map<R, T> cast_subMap(Map<R, T> toPlow, Integer sizeMax){
        if(toPlow==null)return null;
        if(toPlow.size() <= sizeMax)return toPlow;
        Map<R, T> toRet = new HashMap<R, T>();
        Integer i = 0 ;
        for(R key:toPlow.keySet())if(i++<sizeMax)toRet.put(key, toPlow.get(key));
        return toRet ;
    }
/**
 * Transformation d'une map en noeud XML
 * Opération longue !
 * @param toPlow
 * @param itemName
 * @return
 * @throws ParserConfigurationException
 * @throws DOMException
 * @throws SAXException
 * @throws IOException
 */
    public static <T> Node cast_Map2Node(Map<T,Object> toPlow, String itemName) throws ParserConfigurationException, DOMException, SAXException, IOException{
    	final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		final Document doc = docBuilder.newDocument();
		Element itemElement ;
		try{
			itemElement = doc.createElement(itemName);
			doc.appendChild(itemElement);
		}catch(DOMException e){
			itemName="node"+cast_xmlEscape(itemName.replaceAll("\\.","_"));
			itemElement = doc.createElement(itemName);
			doc.appendChild(itemElement);
		}
		for(Entry e:toPlow.entrySet()){
			Object value = e.getValue() ;
			if(value == null)continue;
			if(value instanceof HashMap){
				Node impNode = cast_Map2Node((Map) value, (e.getKey().toString()));
				NodeList nL = impNode.getChildNodes();
				for(Integer i=0; i<nL.getLength();i++){
					Node newNode = doc.importNode(nL.item(i), true);
					itemElement.appendChild(newNode);
				}
			}
			else if(value instanceof List){
				Integer j=0;
				HashMap<String, Object> nM = new HashMap();
				
				for(Object anElement:(List)value){
					HashMap<String, Object> nHm = new HashMap();
					nHm.put("value", anElement);
					nHm.put("order", j++);
					Node impNode= cast_Map2Node(nHm, e.getKey().toString());
					NodeList nL = impNode.getChildNodes();
					for(Integer i=0; i<nL.getLength();i++){
						Node newNode = doc.importNode(nL.item(i), true);
						itemElement.appendChild(newNode);
					}
				}
			}
			else if("DocumentImpl".equalsIgnoreCase(value.getClass().getSimpleName())){
				NodeList nL = ((Node)value).getChildNodes();
				for(Integer i=0; i<nL.getLength();i++){
					Node newNode = doc.importNode(nL.item(i), true);
					itemElement.appendChild(newNode);
				}
			}
			else if(value instanceof Node){
				Node newNode = doc.importNode((Node)value, true);
				itemElement.appendChild(newNode);
			}
			else try{
				itemElement.setAttribute(cast_xmlEscape(e.getKey()), cast_xmlEscape(value));
			}catch(Exception ee){
				itemElement.setAttribute("attr"+cast_xmlEscape(e.getKey()).replaceAll("\\.","_"), cast_xmlEscape(value));
			}
		}
		return doc ;
    }   
    

    /**
     * Conversion d'une Map vers un message XML
     * @param toPlow    Map à déployer
     * @param tagName    Nom du noeud racine
     * @param ident        "@ident" du noeud racine
     * @return    Message (String) XML
     */
    @SuppressWarnings("unchecked")
    public static <R,T> String cast_Map2XmlString(Map<R, T> toPlow, String tagName, R ident){
        toPlow = cast_subMap(toPlow,2500);
        if(toPlow==null)return null;
        String toRet = "<"+tagName+" ident = \""+cast_xmlEscape(cast_2String(ident))+"\" ";
        Set<R> kPlow = toPlow.keySet();
        Boolean sub = false ;

        // Première passe pour les attrbuts non listés ou multiples
        for(R k:kPlow){
            if(toPlow.get(k)!=null){
                if((toPlow.get(k).getClass().getSimpleName().equals("HashMap"))||(toPlow.get(k).getClass().getName().startsWith("fr.onema.wild"))||(toRet.endsWith("/>"))||(toPlow.get(k).getClass().getSimpleName().contains("List"))){

                } else {
                    if(k.getClass().getSimpleName().equals("String"))toRet += ((String)k).split("#")[0] + "=\""+cast_xmlEscape(toPlow.get(k))+"\" ";
                    else toRet += " ID_"+k + "=\""+cast_xmlEscape(toPlow.get(k))+"\" ";
                }
            }
        }
        // Deuxième passe pour les listes
        for(R k:kPlow){
            if(toPlow.get(k)!=null){
                if(toPlow.get(k).getClass().getSimpleName().equals("HashMap")){
                    toRet += (!toRet.endsWith(">"))?
                            ">"+cast_Map2XmlString((HashMap<R, Object>) toPlow.get(k), tagName, k):
                            cast_Map2XmlString((HashMap<R, Object>) toPlow.get(k), tagName, k);
                    sub=true;
                } else if(toPlow.get(k).getClass().getName().startsWith("fr.onema.wild")){
                    toRet += (!toRet.endsWith(">"))?
                        "><Return ident ='"+k+"' value = '"+cast_xmlEscape(toPlow.get(k))+"'/>":
                        "<Return ident ='"+k+"' value = '"+cast_xmlEscape(toPlow.get(k))+"'/>"    ;
                    sub=true;
                } else if(toRet.endsWith("/>")){
                    toRet = toRet.substring(0, toRet.length()-2);
                    if(k.getClass().getSimpleName().equals("String"))toRet += " "+((String)k).replaceAll("#","")+" =\""+cast_xmlEscape(toPlow.get(k))+"\" ";
                    else toRet += " ID_"+k+" =\""+cast_xmlEscape(toPlow.get(k))+"\" ";
                } else if(toPlow.get(k).getClass().getSimpleName().contains("List")){
                    toRet += (!toRet.endsWith(">"))?
                            ">"+cast_2String((List<String>) toPlow.get(k)):
                                cast_2String((List<String>) toPlow.get(k));
                            sub=true;
                }
            }
        }
        if(!toRet.endsWith("/>")&&!toRet.endsWith("</"+tagName+">"))toRet += "/>";
        if(sub) toRet += "</"+tagName+">";
        return toRet ;
    }

    /**
     * Transforme une chaîne de caractère vers une chaîne de texte XML
     * @param toEscape    Chaîne à transformer
     * @return
     */
    public static String cast_xmlEscape(Object toEscape){
        return cast_2String(toEscape)
                .replaceAll("&","&amp;")
                .replaceAll("\"","&quot;")
                .replaceAll("'","&apos;")
                .replaceAll("<","&lt;")
                .replaceAll(">","&gt;");
    }

    /**
     * Transforme une chaîne de texte XML vers une chaîne de texte classique (URL decode)
     * @param toEscape    Chaîne à transformer
     * @return
     */
    public static String cast_xmlUnescape(Object toEscape){
        return cast_2String(toEscape)
                .replaceAll("&amp;","&")
                .replaceAll("&quot;","\"")
                .replaceAll("&apos;","'")
                .replaceAll("&lt;","<")
                .replaceAll("&gt;",">");
    }

/* Les méthodes getDateNow() et date_nowLong() renvoient la date et l'heure actuelle
 *
 */
    /**
     * Date courante
     * @return
     */
    public static String date_now() {
          Calendar currentDate = Calendar.getInstance();
          SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
          return formatter.format(currentDate.getTime());
    }

    /**
     * Date courante
     * @return
     */
    public static long date_nowLong() {
        return new Date().getTime();
    }

    /**
     * Date courante
     * @return
     */
    public static Date date_nowdate(){
        return Calendar.getInstance().getTime();
    }

    /**
     * Date "vide"
     * @return
     */
    public static Date date_null(){
        return new Date(0);
    }

    // MESURES RESSOURCES SYSTEME
    /**
     * Ressources système disponibles, selon mesure système
     * @param system_max_clock Vitesse prérensignée des coeurs processeurs
     * @return    Tabelau [RAM, ROM, CPU]
     * @throws ClassNotFoundException
     * @throws DOMException
     * @throws XPathExpressionException
     * @throws SAXException
     * @throws IOException
     * @throws ParserConfigurationException
     */
    public static Long[] system_physicalAvailable(Integer system_max_clock) throws ClassNotFoundException, DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        Long[] toRet = {system_physicalAvailable_RAM(),system_physicalAvailable_ROM(),system_physicalAvailable_CPU(system_max_clock)};
        return toRet;
    }

    /**
     *  Ressources système disponibles, selon mesure système
     * @return RAM disponible en MB
     */
    public static Long system_physicalAvailable_RAM(){
        Runtime runtime = Runtime.getRuntime();
        return (runtime.freeMemory() + (runtime.maxMemory() - runtime.totalMemory())) / (1048576) ;
    }

    /**
     *  Ressources système disponibles, selon mesure système
     * @return ROM disponible en MB
     */
    public static Long system_physicalAvailable_ROM() throws ClassNotFoundException, DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        return (long) (new File("configuration/config.6conf")).getUsableSpace()/(1048576);
    }

    /**
     *  Ressources système disponibles, selon mesure système
     * @return Indicateur CPU disponible en MHz
     */
    public static Long system_physicalAvailable_CPU(Integer system_max_clock) throws ClassNotFoundException, DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException{
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
        Double load = osBean.getSystemLoadAverage() ;
        if(load<0)return (long) (osBean.getAvailableProcessors()* system_max_clock );
        return (long) ((1-osBean.getSystemLoadAverage())* osBean.getAvailableProcessors()* system_max_clock );
    }

    /**
     * Transforme un fichier XML par un fichier XSL, en un autre fichier XML
     * @param ixsltFilePath                Chemin du fichier XSL
     * @param ixmlFilePath                Chemin du fichier XML à transformer
     * @param itransformedFilePath        Chemin du fichier final
     * @throws TransformerException
     */
    public static void xml_transformWithXsl(String ixsltFilePath, String ixmlFilePath,String itransformedFilePath) throws TransformerException{
        TransformerFactory tFactory = TransformerFactory.newInstance("net.sf.saxon.TransformerFactoryImpl", null);
        System.setProperty("javax.xml.transform.TransformerFactory","net.sf.saxon.TransformerFactoryImpl");
        Transformer transformer = tFactory.newTransformer(new StreamSource(ixsltFilePath));
        File tOutput = new File(itransformedFilePath);
        if(tOutput.exists())tOutput.delete();
        else tOutput.getParentFile().mkdirs();
        transformer.transform(new StreamSource(ixmlFilePath), new StreamResult(tOutput));
    }

    /**
     * Récupère une référence dans une liste, si la valeur recherchée appartient à la liste des synonymes
     * Si n'est pas trouvé, retourne l'élément recherché
     * @param needle	Valeur recherchée
     * @param stack		Liste de synonymes indexés par la référence
     * @return
     */
    public static String map_searchWithinSynonyms(String needle, HashMap<String,String[]> stack){
    	for(String k:stack.keySet())if(
    				Arrays.asList(stack.get(k)).contains(needle)
    			||	Arrays.asList(stack.get(k)).contains(needle.toLowerCase())
    			||	needle.toLowerCase().equals(k.toLowerCase()))return k;
    	return needle;
    }

    public static Boolean check_equal(
    		Object i_colValue,
    		Object i_ref,
    		Boolean i_nullSens,
    		Boolean i_caseSens,
    		Boolean i_specSens
    ){
    	try{
    		if( i_nullSens == null )i_nullSens = false ;
    		if( i_caseSens == null )i_caseSens = false ;
    		if( i_specSens == null )i_specSens = false ;
    		String className_tValue = cast_getSimpleClass(i_ref);
    		Object wildValue_tValue = cast_2Object(className_tValue, i_ref)[1];
    		Object[] wildValue_recordValue_try = cast_2Object(className_tValue, i_colValue);
	    	Boolean wildValue_recordSuccess = (Boolean) wildValue_recordValue_try[0];
	    	if(!wildValue_recordSuccess)return false;
	    	Object wildValue_recordValue = wildValue_recordValue_try[1];
    		if((!i_nullSens||!i_caseSens||!i_caseSens)&&className_tValue.equals("String")){
    			if(!i_caseSens){
    				wildValue_tValue = ((String)wildValue_tValue).toLowerCase();
    				wildValue_recordValue = ((String)wildValue_recordValue).toLowerCase();
    			}
    			if(!i_specSens){
    				wildValue_tValue = check_escapeAll((String)wildValue_tValue);
    				wildValue_recordValue = check_escapeAll((String)wildValue_recordValue);
    			}
    			if(!i_nullSens){
    				wildValue_tValue = ((String)wildValue_tValue).trim().replaceAll("\\s+","");
    				wildValue_recordValue = ((String)wildValue_recordValue).trim().replaceAll("\\s+","");
    			}
    		}
	    	if(wildValue_recordValue.equals(wildValue_tValue))return true ;
	    	return false;
    	}catch(Exception e) {return false;}
    }


    public static String check_escapeAll(String string) {
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        final CharBuffer buffer = CharBuffer.allocate(string.length());
        char c;
        for (int i = 0, n = string.length(); i < n; ++i) {
            c = string.charAt(i);
            if (c <= '\u007F') buffer.append(c);
        }

        if (buffer.position() <= 0)
            return null;

        buffer.limit(buffer.position());
        buffer.position(0);

        return NON_ALPHANUMERIC.matcher(buffer.toString()).replaceAll("");
    }

    public static Boolean check_contain(
    		Object i_colValue,
    		Object i_ref,
    		Boolean i_nullSens,
    		Boolean i_caseSens,
    		Boolean i_specSens
    ){
    	try{
    		if( i_nullSens == null )i_nullSens = false ;
    		if( i_caseSens == null )i_caseSens = false ;
    		if( i_specSens == null )i_specSens = false ;
    		String className_tValue = "String";
    		String wildValue_tValue = (String) cast_2Object(className_tValue, i_ref)[1];
    		Object[] wildValue_recordValue_try = cast_2Object(className_tValue, i_colValue);
	    	Boolean wildValue_recordSuccess = (Boolean) wildValue_recordValue_try[0];
	    	if(!wildValue_recordSuccess)return false;
	    	String wildValue_recordValue = (String) wildValue_recordValue_try[1];
    		if((!i_nullSens||!i_caseSens||!i_caseSens)&&className_tValue.equals("String")){
    			if(!i_caseSens){
    				wildValue_tValue = wildValue_tValue.toLowerCase();
    				wildValue_recordValue = wildValue_recordValue.toLowerCase();
    			}
    			if(!i_specSens){
    				wildValue_tValue = check_escapeAll(wildValue_tValue);
    				wildValue_recordValue = check_escapeAll(wildValue_recordValue);
    			}
    			if(!i_nullSens){
    				wildValue_tValue = wildValue_tValue.trim().replaceAll("\\s+","");
    				wildValue_recordValue = wildValue_recordValue.trim().replaceAll("\\s+","");
    			}
    		}
	    	return wildValue_recordValue.contains(wildValue_tValue) ;
    	}catch(Exception e) {return false;}
    }
    public static Boolean check_regex(
    		Object i_colValue,
    		String i_colRegexp,
    		Boolean i_nullSens,
    		Boolean i_caseSens,
    		Boolean i_specSens
    ){
	    try{
    		if( i_nullSens == null )i_nullSens = false ;
    		if( i_caseSens == null )i_caseSens = false ;
    		if( i_specSens == null )i_specSens = false ;
	    	String className_tValue = "String";
			Object[] wildValue_recordValue_try = cast_2Object(className_tValue, i_colValue);
	    	Boolean wildValue_recordSuccess = (Boolean) wildValue_recordValue_try[0];
	    	if(!wildValue_recordSuccess)return false;
	    	String wildValue_recordValue = (String) wildValue_recordValue_try[1];
			if((!i_nullSens||!i_caseSens||!i_caseSens)&&className_tValue.equals("String")){
				if(!i_caseSens) wildValue_recordValue = wildValue_recordValue.toLowerCase();
				if(!i_specSens) wildValue_recordValue = check_escapeAll(wildValue_recordValue);
				if(!i_nullSens) wildValue_recordValue = wildValue_recordValue.trim().replaceAll("\\s+","");
			}
			Pattern  pattern = Pattern.compile(i_colRegexp);
			Matcher matcher = pattern.matcher(wildValue_recordValue);
	        return matcher.find() ;
	    }catch(Exception e) {return false;}
    }
    public static Boolean check_within(
    		Object i_colValue,
    		Object i_minValue,
    		Boolean i_minInclude,
    		Object i_maxValue,
    		Boolean i_maxInclude
    ){
    	try{
    		if( i_minInclude == null )i_minInclude = false ;
    		if( i_maxInclude == null )i_maxInclude = false ;
    		String className_minValue = "String";
    		Object wildValue_minValue = cast_2Object(className_minValue, i_minValue)[1];
    		Object wildValue_maxValue = cast_2Object(className_minValue, i_maxValue)[1];
     		Object[] wildValue_recordValue_try = cast_2Object(className_minValue, i_colValue);
	    	Boolean wildValue_recordSuccess = (Boolean) wildValue_recordValue_try[0];
	    	if(!wildValue_recordSuccess)return false;
	    	Object wildValue_recordValue = wildValue_recordValue_try[1];
	    			String usewildValue_minValue = (String)wildValue_minValue ;
	    			String usewildValue_maxValue = (String)wildValue_maxValue ;
	    			String usewildValue_recordValue = (String)wildValue_recordValue ;
	    			usewildValue_minValue = usewildValue_minValue.toLowerCase();
	    			usewildValue_maxValue = usewildValue_maxValue.toLowerCase();
	    			usewildValue_recordValue = usewildValue_recordValue.toLowerCase();
	    			usewildValue_minValue = check_escapeAll(usewildValue_minValue);
	    			usewildValue_maxValue = check_escapeAll(usewildValue_maxValue);
	    			usewildValue_recordValue = check_escapeAll(usewildValue_recordValue);
	    			usewildValue_minValue = usewildValue_minValue.trim().replaceAll("\\s+","");
	    			usewildValue_maxValue = usewildValue_maxValue.trim().replaceAll("\\s+","");
	    			usewildValue_recordValue = usewildValue_recordValue.trim().replaceAll("\\s+","");
	    			Integer positionMin = (usewildValue_minValue==null)?1:usewildValue_recordValue.compareTo(usewildValue_minValue);
	    			Integer positionMax = (usewildValue_maxValue==null)?-1:usewildValue_recordValue.compareTo(usewildValue_maxValue);
	    				if(	(positionMin==0&&!i_minInclude)
	    				||	(positionMax==0&&!i_maxInclude)
	    				||	positionMin<0
	    				||	positionMax>0
	    				)return false ;
	    				return true ;
    	}catch(Exception e) {return false;}
    }
    public static Boolean check_compare(
    		Object i_colValue,
    		Object i_minValue,
    		Boolean i_minInclude,
    		Object i_maxValue,
    		Boolean i_maxInclude
    ){
    	try{
    	if( i_minInclude == null )i_minInclude = false ;
    	if( i_maxInclude == null )i_maxInclude = false ;
    	String className_minValue = cast_getSimpleClass(i_minValue);
		String className_maxValue = cast_getSimpleClass(i_maxValue);
		String className = (className_minValue==null)?className_maxValue:className_minValue;
		if(className==null)return false;
		if(i_minValue!=null&&!className_minValue.equals(className))return false ;
		if(i_maxValue!=null&&!className_maxValue.equals(className))return false ;

		Object wildValue_minValue = cast_2Object(className, i_minValue)[1];
		Object wildValue_maxValue = cast_2Object(className, i_maxValue)[1];
 		Object[] wildValue_recordValue_try = cast_2Object(className, i_colValue);
    	Boolean wildValue_recordSuccess = (Boolean) wildValue_recordValue_try[0];
    	if(!wildValue_recordSuccess)return false;
    	Object wildValue_recordValue = wildValue_recordValue_try[1];
    	switch(className){
    	case "String":
    			String usewildValue_minValue = (String)wildValue_minValue ;
    			String usewildValue_maxValue = (String)wildValue_maxValue ;
    			String usewildValue_recordValue = (String)wildValue_recordValue ;
    			usewildValue_minValue = usewildValue_minValue.toLowerCase();
    			usewildValue_maxValue = usewildValue_maxValue.toLowerCase();
    			usewildValue_recordValue = usewildValue_recordValue.toLowerCase();
    			usewildValue_minValue = check_escapeAll(usewildValue_minValue);
    			usewildValue_maxValue = check_escapeAll(usewildValue_maxValue);
    			usewildValue_recordValue = check_escapeAll(usewildValue_recordValue);
    			usewildValue_minValue = usewildValue_minValue.trim().replaceAll("\\s+","");
    			usewildValue_maxValue = usewildValue_maxValue.trim().replaceAll("\\s+","");
    			usewildValue_recordValue = usewildValue_recordValue.trim().replaceAll("\\s+","");
    			Integer positionMin = (usewildValue_minValue==null)?1:usewildValue_recordValue.compareTo(usewildValue_minValue);
    			Integer positionMax = (usewildValue_maxValue==null)?-1:usewildValue_recordValue.compareTo(usewildValue_maxValue);
    				if(	(positionMin==0&&!i_minInclude)
    				||	(positionMax==0&&!i_maxInclude)
    				||	positionMin<0
    				||	positionMax>0
    				)return false ;
    		return true ;
    	case "Double":
	    		Double usewildValue_minValue_do = (Double)wildValue_minValue ;
	    		Double usewildValue_maxValue_do = (Double)wildValue_maxValue ;
	    		Double usewildValue_recordValue_do = (Double)wildValue_recordValue ;
					if(	(usewildValue_minValue_do!=null && usewildValue_minValue_do == usewildValue_recordValue_do && !i_minInclude)
					||	(usewildValue_maxValue_do!=null && usewildValue_maxValue_do == usewildValue_recordValue_do && !i_maxInclude)
					||	(usewildValue_minValue_do!=null&&usewildValue_minValue_do > usewildValue_recordValue_do)
					||	(usewildValue_maxValue_do!=null&&usewildValue_maxValue_do < usewildValue_recordValue_do)
					)return false ;
			return true ;
    	case "Date":
    		Date usewildValue_minValue_da = (Date)wildValue_minValue ;
    		Date usewildValue_maxValue_da = (Date)wildValue_maxValue ;
    		Date usewildValue_recordValue_da = (Date)wildValue_recordValue ;
    		Integer positionMin_da = usewildValue_recordValue_da.compareTo(usewildValue_minValue_da);
			Integer positionMax_da = usewildValue_recordValue_da.compareTo(usewildValue_maxValue_da);
				if(	(positionMin_da!=null&&positionMin_da==0&&!i_minInclude)
				||	(positionMax_da!=null&&positionMax_da==0&&!i_maxInclude)
				||	(positionMin_da!=null&&positionMin_da<0)
				||	(positionMax_da!=null&&positionMax_da>0)
				)return false ;
		return true ;
    	default :
    		return false ;
    	}
    	}catch(Exception e) {return false;}
    }
    public static Object stats_n(
    		List<Object> i_Values,
    		Integer posRet
    ){
    	try{
    		if(posRet<0)posRet=0;
    		String className = null ;
    		Iterator nextValue = i_Values.iterator();
    		while(className==null)className = cast_getSimpleClass(nextValue.next());
    		switch(className){
        	case "String":
        		List<String> values_str = new ArrayList();
        		for(Object obj:i_Values)values_str.add((String)cast_2Object(className, obj)[1]);
        		Collections.sort(values_str);
        		return values_str.get(posRet);
        	case "Double":
        		
        		List<Double> values_db = new ArrayList();
        		for(Object obj:i_Values){
        			Object[] nClObj=cast_2Object(className, obj);
        			if((Boolean)nClObj[0])values_db.add((Double)nClObj[1]);
        			else values_db.add(null);
        		}
        		// Pour le cas des valeurs ne correspondant pas à des doubles, on les valorise à la moyenne de la série.
        		Double sum = (double) 0 ;
        		Iterator<Double> nextValueDble = values_db.iterator();
        		
        		while(nextValueDble.hasNext()){
        			Double val = nextValueDble.next();
        			if(val!=null)sum += val;
        		}
        		Double mean = sum/((double)values_db.size());
        		nextValueDble = values_db.iterator();
        		Integer nbNullValues = 0 ;
        		while(nextValueDble.hasNext()){
        			Double value = nextValueDble.next();
        			if(value==null){
        				nextValueDble.remove();
        				nbNullValues++;
        			}
        		}
        		for(Integer i=0;i<nbNullValues;i++)values_db.add(mean);
        		Collections.sort(values_db);
        		return values_db.get(posRet);
        	case "Date":
        		List<Date> values_date = new ArrayList();
        		for(Object obj:i_Values)values_date.add((Date)cast_2Object(className, obj)[1]);
        		Collections.sort(values_date);
        		return values_date.get(posRet);
        	case "Boolean":
        		List<Boolean> values_bl = new ArrayList();
        		for(Object obj:i_Values)values_bl.add((Boolean)cast_2Object(className, obj)[1]);
        		Collections.sort(values_bl);
        		return values_bl.get(posRet);
        	default :
        		return null ;
        	}
    	}catch(Exception e) {return null;}
    }
    public static Object stats_avg(
    		List<Object> i_Values
    ){
    	try{
    		if(i_Values==null||i_Values.isEmpty())return null;
    		String className = null ;
    		Iterator nextValue = i_Values.iterator();
    		while(className==null)className = cast_getSimpleClass(nextValue.next());
    		switch(className){
        	case "String":
        		return null;
        	case "Double":
        		Double sum = (double) 0 ;
                        int nb = 0;
        		for(Object obj:i_Values) {
                            // fix for empty string
                            if (obj instanceof String) {
                                continue;
                            }
                            nb++;
                            sum+=(Double)obj;
                        }
        		return (Double)(sum/nb);
        	case "Date":
        		BigInteger total = BigInteger.ZERO;
        		for (Object obj:i_Values) {
        				Date date = (Date)obj;
        				total = total.add(BigInteger.valueOf(date.getTime()));
        		}
        		BigInteger averageMillis = total.divide(BigInteger.valueOf(i_Values.size()));
        		return new Date(averageMillis.longValue());
        	case "Boolean":
        		List<Integer> values_bl = new ArrayList();
        		Double sum_bl = (double) 0 ;
        		for(Object obj:i_Values){
        			Boolean bill = (Boolean)obj;
        			if(bill)sum_bl+=1;
        		}
        		return (Double)(sum_bl/i_Values.size());
        	default :
        		return null ;
        	}
    	}catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object stats_avg(int count, Object sumObj, String className) {
        try {
            switch (className) {
                case "String":
                    return null;
                case "Double":
                    Double sum = (Double) sumObj;
                    return (Double) (sum / count);
                case "Date":
                    BigInteger total = (BigInteger) sumObj;
                    BigInteger averageMillis = total.divide(BigInteger.valueOf(count));
                    return new Date(averageMillis.longValue());
                case "Boolean":
                    Double sum_bl = (double) sumObj;
                    return (Double) (sum_bl / count);
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static Object addToSum(Object sumObj, Object newValue){
    	try{
    		String className = null ;
    		while(className==null)className = cast_getSimpleClass(newValue);
    		switch(className){
        	case "String":
        		return null;
        	case "Double":
        		Double sum = (Double) sumObj;
        		return sum + (Double)newValue;
        	case "Date":
        		BigInteger total = (BigInteger) sumObj;
        		Date date = (Date) newValue;
        		return total.add(BigInteger.valueOf(date.getTime()));
        	case "Boolean":
        		Integer sum_bl = (Integer) sumObj;
        		Boolean bill = (Boolean)newValue;
                        if (bill) sum_bl+=1;
                        else return sum_bl;
        	default :
        		return null ;
        	}
    	}catch(Exception e) {return null;}
    }

    public static Object stats_geomAvg(
    		List<Object> i_Values
    ){
    	try{
		String className = null ;
		Iterator nextValue = i_Values.iterator();
		while(className==null)className = cast_getSimpleClass(nextValue.next());
		switch(className){
    	case "String":
    		return null;
    	case "Double":
    		Double sum = (double) 1 ;
                int count = 0;
    		for(Object obj:i_Values) {
                    // fix for empty string
                    if (obj instanceof String) {
                        continue;
                    }
                    count++;
                    sum*=(Double)obj;
                    if (sum < 0) // A negative term has been introduced, leading to undefined behavior.
                        return -1;
                }
    		return static_nroot(sum,count);
    	case "Date":
    		BigInteger total = BigInteger.ZERO;
    		for (Object obj:i_Values) {
                    Date date = (Date) obj;
                    total = total.multiply(BigInteger.valueOf(date.getTime()));
                    if (total.signum() < 0) // A negative term has been introduced, leading to undefined behavior.
                        return -1;
    		}
    		Double averageMillis = static_nroot(Double.parseDouble(total.toString()),Double.parseDouble(BigInteger.valueOf(i_Values.size()).toString()));
    		return new Date(averageMillis.longValue());
    	case "Boolean":
    		return null;
    	default :
    		return null ;
    	}
    	}catch(Exception e) {return null;}
    }

    public static Object stats_geomAvg(int count, Object sumObj, String className) {
        try {
            switch (className) {
                case "String":
                    return null;
                case "Double":
                    DoubleDouble sum = (DoubleDouble) sumObj;
                    return static_nroot(sum, count);
                case "Date":
                    BigInteger total = (BigInteger) sumObj;
                    Double averageMillis = static_nroot(Double.parseDouble(total.toString()), Double.parseDouble(BigInteger.valueOf(count).toString()));
                    return new Date(averageMillis.longValue());
                case "Boolean":
                    return null;
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static Object addToGeomSum(Object sumObj, Object newValue) {
        try {
            String className = null;
            while (className == null) {
                className = cast_getSimpleClass(newValue);
            }
            switch (className) {
                case "String":
                    return null;
                case "Double":
                    DoubleDouble sum;
                    if (sumObj instanceof Double) {
                        sum = new DoubleDouble((double)sumObj);
                    } else {
                        sum = (DoubleDouble) sumObj;
                    }
                    sum.add(Math.log((Double) newValue), 0);
                    return sum;
                case "Date":
                    BigInteger total = (BigInteger) sumObj;
                    Date date = (Date) newValue;
                    return total.multiply(BigInteger.valueOf(date.getTime()));
                case "Boolean":
                    return null;
                default:
                    return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Double stats_var(
    		List<Object> i_Values
    ){
    	try{
    		String className = null ;
    		Iterator nextValue = i_Values.iterator();
    		while(className==null)className = cast_getSimpleClass(nextValue.next());
    		switch(className){
        	case "String":
        		return null;
        	case "Double":
        		List<Object> values_db = new ArrayList();
        		for(Object obj:i_Values){
        			Object[] nClObj=cast_2Object(className, obj);
        			if((Boolean)nClObj[0])values_db.add((Double)nClObj[1]);
        		}
        		Double mean = (Double) stats_avg(values_db);
        		Double sum = (double) 0 ;
        		for(Object obj:values_db){
        			Double valueDble = (Double) obj;
        			sum += Math.pow(valueDble - mean,2);
        		}
        		return (Double)(sum/values_db.size());
        	case "Date":
        		Double sum_date = (double) 0;
        		Double mean_date = Double.valueOf(((Date)stats_avg(i_Values)).getTime());
        		for (Object obj:i_Values) {
        				Date date = (Date) obj;
        				sum_date += Math.pow(Double.valueOf(date.getTime()) - mean_date,2);
        		}
        		return (Double)(sum_date/i_Values.size());
        	case "Boolean":
        		Double mean_bl = (Double) stats_avg(i_Values);
        		Double sum_bl = (double) 0 ;
        		for(Object obj:i_Values){
        			Boolean valueBl = (Boolean)obj;
        			if(valueBl)sum_bl += Math.pow((double)1 - mean_bl,2);
        			else sum_bl += Math.pow((double)0 - mean_bl,2);
        		}
        		return (Double)(sum_bl/i_Values.size());
        	default :
        		return null ;
        	}
    	}catch(Exception e) {return null;}
    }


	public static Double stats_stdDev(
    		List<Object> i_Values
    ){
    	try{
    		Double var = stats_var(i_Values) ;
    		if(var==null)return null;
        	return Math.sqrt(var) ;
    	}catch(Exception e) {return null;}
    }
    public static double static_nroot(double num, double root){
        return Math.pow(Math.E, Math.log(num)/root);
    }

    public static double static_nroot(DoubleDouble num, double root){
        return Math.pow(Math.E, num.value/root);
    }

    /**
     * Iterative quantile (low memory). No interpolation is done here.
     *
     * /!\ This algorithm is really slow ! The reason is that it is designed to
     * avoid sorting entire dataset in memory. The induced flaw is that it have
     * to build consecutive subsamples to find quantile value.
     *
     * Details:
     * We make a subsample of entire input data from its first values. Then, we
     * sort it, and count how many elements we have in intervals composed of
     * each adjacent subsample value. It gives us a simple repartition of values
     * in source dataset. Once done, we'll refine our results by repeating this
     * operation until our subsample represents a continuous subset of data from
     * source dataset.
     *
     * Ex: our source dataset is composed of values between 1 and 100. We limit
     * ourselves to 4 values in memory. So, we takes the 4 first encountered
     * values in source dataset (TODO : Randomize to optimize sorted datasets),
     * then sort them. It could give : 2 ... 7 ... 28 ... 76. It gives us the
     * following value intervals : [-∞..2[, [2..7[, [7..28[, [28..76[, [76..+∞[.
     * By browsing the source array, we can count, for each above interval, the
     * number of values which belong in it. Then, we can tell in which interval
     * of values the wanted quantile can be found, and make another iteration to
     * refine our intervals. We do so until our subsample only represents
     * consecutive elements of the source dataset, and then we just have to
     * adapt input quantile factor to pick a value in it.
     *
     * @param input The dataset to extract quantile value from.
     *
     * @param factor Wanted position, as a decimal between 0 and 1. 0
     * represents the lowest value in input dataset. 1 represents the highest
     * value in it.
     * @return Quantile operation result.
     * @throws java.lang.Exception If an error occurs while closing source data access.
     */
    public static Number stats_iterativeQuantile(final Iterable<Number> input, final float factor) throws Exception {
        final int limit = 64;
        long searchedIndex = -1;
        final HashSet<Double> subsampleSet = new HashSet<>(limit); // Allow only distinct values in our subset.
        double[] subsample;
        double lowerValue = Double.NEGATIVE_INFINITY;
        double upperValue = Double.POSITIVE_INFINITY;
        long sum, previousSum;
        int rIndex;

        /*
         * Data repartition array count the number of values in source dataset
         * between two given values in our sorted subsample. The first repartition
         * value is the number of elements lower than the lowest subsample value.
         * Last repartition value is the number of values in source dataset which
         * are equal or higher than our highest subsample value.
         */
        long[] repartition = new long[limit + 1];

        double result = Double.NaN;
        double currentValue;
        while (Double.isNaN(result)) {
            // Initialize a subsample with first encountered values in defined bounds.
            int subsampleSize = 0;
            subsampleSet.clear();
            try (final FiniteNumberIterator it = new FiniteNumberIterator(input.iterator())) {
                while (subsampleSize < limit && it.hasNext()) {
                    currentValue = it.next().doubleValue();
                    if (lowerValue < currentValue && upperValue > currentValue && subsampleSet.add(currentValue))
                        subsampleSize++;
                }

                if (subsampleSize <= 0) {
                    result = lowerValue;
                    break;
                }

                subsample = new double[subsampleSize];
                final Iterator<Double> subIt = subsampleSet.iterator();
                int i = 0;
                while (subIt.hasNext()) {
                    subsample[i++] = subIt.next();
                }

                // To avoid big performance degradation in case of non randomly
                // distributed dataset, we try to pick values further away in source
                // data to modify our subsample.
                int step = 2;
                int idx = 0;
                while (forward(it, step) && idx < subsampleSize) {
                    currentValue = it.next().doubleValue();
                    if (lowerValue < currentValue && upperValue > currentValue && subsampleSet.add(currentValue)) {
                        subsampleSet.remove(subsample[idx]);
                        subsample[idx++] = currentValue;
                        step *= 2;
                    }
                }
            }

            // Build data repartition table.
            Arrays.sort(subsample);
            Arrays.fill(repartition, 0);
            try (final FiniteNumberIterator it = new FiniteNumberIterator(input.iterator())) {
                while (it.hasNext()) {
                    currentValue = it.next().doubleValue();
                    if (currentValue >= lowerValue && currentValue < upperValue) {
                        final int ri = Arrays.binarySearch(subsample, currentValue);
                        if (ri < 0)
                            repartition[-ri - 1]++;
                        else
                            repartition[ri + 1]++;
                    }
                }
            }

            if (searchedIndex < 0) {
                long total = 0;
                for (long nb : repartition) {
                    total += nb;
                }
                searchedIndex = (long) (total * factor - 1);
                searchedIndex = Math.min(total - 1, Math.max(0, searchedIndex));
            }

            // Defines new bounds for next iteration
            sum = 0;
            previousSum = 0;
            rIndex = -1;
            while (sum <= searchedIndex) {
                previousSum = sum;
                sum += repartition[++rIndex];
            }
            searchedIndex -= previousSum;

            if (rIndex > 0) {
                lowerValue = subsample[rIndex - 1];
            }

            if (rIndex < subsample.length)
                upperValue = subsample[rIndex];
        }

        return result;
    }

    /**
     * Make given iterator moving forward a given number of times.
     * @param it Iterator to use.
     * @param step The number of steps to move forward. It means that (step -1)
     * values will be ignored from input iterator.
     * @return true if given iterator has a next element after the jump. False
     * otherwise.
     */
    public static boolean forward(final Iterator it, int step) {
        while (--step > 0) {
            if (it.hasNext())
                it.next();
            else
                return false;
        }
        return it.hasNext();
    }

    /**
     * Compute geometric average on a given dataset. The algorithm here tries to
     * avoid managing numbers of great magnitude. To do so, we compute it by
     * adding logs of found values instead of just factorizing them. At the end,
     * we have to compute the mean for this sum, and compute it Exp to get the
     * final result.
     *
     * @param dataset Dataset to compute geometric average upon. Must be iterable
     * multiple times, as the method will need to perform multiple passes on it.
     * @return The computed geometric average.
     */
    public static double stats_geometricAverage(final Iterator<Number> dataset) throws Exception {
        final Statistics stats = new Statistics("Log sum");
        try (final FiniteNumberIterator it = new FiniteNumberIterator(dataset)) {
            double value;
            while (it.hasNext()) {
                value = it.next().doubleValue();
                if (value < 0)
                    return -1;
                stats.accept(Math.log(value));
            }
        }

        return Math.exp(stats.mean());
    }

    /**
     * Modify input string according to given flags.
     *
     * @param target The string to modify.
     * @param removeSpaces If we must remove all blank characters (spaces, tabulations, etc.)
     * @param removeSpecialCharacters To keep only letters and digits. It also remove accents on letters.
     * @param toLowerCase Switch all letters to lower case.
     * @return The modified string.
     */
    public static String transform(String target, final boolean removeSpaces, final boolean removeSpecialCharacters, final boolean toLowerCase) {
        if (target == null || target.isEmpty())
            return target;

        if (removeSpaces)
            target = target.replaceAll("\\s+", "");
        if (removeSpecialCharacters)
            target = IoCommons.check_escapeAll(target);
        if (toLowerCase)
            target = target.toLowerCase();

        return target;
    }

    /**
     * Check that input value is contained in the interval composed of given
     * minimum and maximum values.
     *
     * @param value The value to test.
     * @param minValue Lower bound of interval.
     * @param includeMin If lower bound is inclusive. If value is null, we
     * consider lower bound to be infinity. Null values will be included in it.
     * @param maxValue Upper bound of interval.
     * @param includeMax If upper bound is inclusive. If value is null, we
     * consider upper bound to be infinity. Null values will be included in it.
     * @return True if The value is in the defined bounds. False otherwise.
     */
    public static boolean check_strictCompare(final Comparable value, Object minValue, final boolean includeMin, Object maxValue, final boolean includeMax) {
        boolean within = true;
        // If a lower bound is defined, we ensure our value is greather than it.
        if (minValue != null) {
            final int comparison = value.compareTo(minValue);
            within = includeMin? comparison >= 0 : comparison > 0;
        }

        // Check upper bound only if present and if lower bound has been validated.
        if (within && maxValue != null) {
            final int comparison = value.compareTo(maxValue);
            within &= includeMax? comparison <= 0 : comparison < 0;
        }

        return within;
    }
    
    
    public static boolean system_isWindows() {
		return (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0);
	}

	public static boolean system_isMac() {
		return (System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0);
	}

	public static boolean system_isUnix() {
		String OS = System.getProperty("os.name").toLowerCase(); 
		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
	}

	public static boolean system_isSolaris() {
		return (System.getProperty("os.name").toLowerCase().indexOf("sunos") >= 0);
	}
    
	
	public static String pg_insertStr(HashMap<String, HashMap<String, String>> typeMapper, String schema, String mapKey, HashMap<String, Object> mapToInsert){
		String sqlInsert = "INSERT INTO \""+schema+"\".\""+mapKey+"\"(";
		String sqlValues = "";
		Boolean first = true ;
		
		for(Entry e:mapToInsert.entrySet()){
			if(e.getValue()!=null){
				String value  = "";
				String type= typeMapper.get(mapKey).get(e.getKey());
				if(
						"ARRAY".equalsIgnoreCase(type)&&"[]".equalsIgnoreCase(e.getValue().toString())
				||		"timestamp without time zone".equalsIgnoreCase(type)&&"null".equalsIgnoreCase(e.getValue().toString())				
				)continue;
				switch(type){
					case "bigint" :					value  = e.getValue().toString() ; break;
					case "int" :					value  = e.getValue().toString() ; break;
					case "numeric" :				value  = e.getValue().toString() ; break;
					case "double precision" :		value  = e.getValue().toString() ; break;
					case "boolean" :				value  = e.getValue().toString() ; break;
					case "xml" :					value  = "'"+IoCommons.cast_2String(e.getValue()).replaceAll("'","''")+"'" ; break;
					default : 						value  = "'"+e.getValue().toString().replaceAll("'","''")+"'";
				}
			if(first){	
				sqlInsert+="\""+e.getKey()+"\"";
				sqlValues+=value+"::"+type;
				first=false;
			}else{
				sqlInsert+=",\""+e.getKey()+"\"";
				sqlValues+=","+value+"::"+type;
			}}
		}
		return sqlInsert+") VALUES ("+sqlValues+")";		
	}
	public static HashMap<String, HashMap<String, String>> pg_getSchemaDefs(Connection opened_dbConnect, String schema) throws SQLException{
		HashMap<String, HashMap<String, String>> toRet = new HashMap();
		Statement stmt = null;
        String query = "SELECT table_name,column_name,data_type FROM information_schema.columns WHERE table_schema = '"+schema.replaceAll("''", "")+"'";
        stmt = opened_dbConnect.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
        	HashMap<String, String> toRet_sub ;
        	if(toRet.get(rs.getString(1))!=null){
        		toRet_sub = toRet.get(rs.getString(1));
        		toRet_sub.put(rs.getString(2), rs.getString(3));
        	}else{
        		toRet_sub = new HashMap();
        		toRet_sub.put(rs.getString(2), rs.getString(3));
        	}
        	toRet.put(rs.getString(1), toRet_sub);
        }
		return toRet;
	}
	public static Boolean execution_isInterrupt(Throwable ex){
		ex.printStackTrace();
		if(ex.getClass().getName().toLowerCase().contains("interrupt"))return true;
		if(ex.getCause().getClass().getName().toLowerCase().contains("interrupt"))return true;
		return false ;
	}

}