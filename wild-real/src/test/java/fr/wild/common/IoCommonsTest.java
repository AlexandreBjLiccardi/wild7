
package fr.wild.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author Johann Sorel (Geomatys)
 */
public class IoCommonsTest {

	private static final String DOCUMENT =
			"{\n" +
			"    \"store\": {\n" +
			"        \"book\": [\n" +
			"            {\n" +
			"                \"category\": \"reference\",\n" +
			"                \"author\": \"Nigel Rees\",\n" +
			"                \"title\": \"Sayings of the Century\",\n" +
			"                \"price\": 8.95\n" +
			"            },\n" +
			"            {\n" +
			"                \"category\": \"fiction\",\n" +
			"                \"author\": \"Evelyn Waugh\",\n" +
			"                \"title\": \"Sword of Honour\",\n" +
			"                \"price\": 12.99\n" +
			"            },\n" +
			"            {\n" +
			"                \"category\": \"fiction\",\n" +
			"                \"author\": \"Herman Melville\",\n" +
			"                \"title\": \"Moby Dick\",\n" +
			"                \"isbn\": \"0-553-21311-3\",\n" +
			"                \"price\": 8.99\n" +
			"            },\n" +
			"            {\n" +
			"                \"category\": \"fiction\",\n" +
			"                \"author\": \"J. R. R. Tolkien\",\n" +
			"                \"title\": \"The Lord of the Rings\",\n" +
			"                \"isbn\": \"0-395-19395-8\",\n" +
			"                \"price\": 22.99\n" +
			"            }\n" +
			"        ],\n" +
			"        \"bicycle\": {\n" +
			"            \"color\": \"red\",\n" +
			"            \"price\": 19.95\n" +
			"        }\n" +
			"    },\n" +
			"    \"expensive\": 10\n" +
			"}";

	/**
	 * Test IoCommons.json_getFirstValue
	 */
	@Test
	public void json_getFirstValueTest() throws DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException{

		final Object result = IoCommons.json_getFirstValue(new ObjectMapper().readValue(DOCUMENT,Map.class), "$.store.book[2]");
		assertTrue(result instanceof Map);
		final Map value = (Map) result;
		assertEquals(5, value.size());
		assertEquals("fiction", value.get("category"));
		assertEquals("Herman Melville", value.get("author"));
		assertEquals("Moby Dick", value.get("title"));
		assertEquals("0-553-21311-3", value.get("isbn"));
		assertEquals(8.99, value.get("price"));
	}

	/**
	 * Test IoCommons.json_getValues
	 */
	@Test
	public void json_getValuesTest() throws DOMException, XPathExpressionException, SAXException, IOException, ParserConfigurationException{

		List result = IoCommons.json_getValues(new ObjectMapper().readValue(DOCUMENT,Map.class), "$.store.book");
		assertEquals(4, result.size());

		result = IoCommons.json_getValues(new ObjectMapper().readValue(DOCUMENT,Map.class), "//store/book");
		assertEquals(4, result.size());
	}


	/**
	 * Test de lecture de parametres a partir d'un fichier.
	 */
	@Test
	public void testReadParametersFromPath() throws SAXException, IOException, ParserConfigurationException{

		final String path = IoCommonsTest.class.getResource("/data/parameter/Parameters.xml").toString();
		final Map<String, Map<String,Object>> parameters = IoCommons.param_readParameters(null, path, null);
		testParameters(parameters);
	}

	/**
	 * Test de lecture de parametres a partir d'un noeud DOM.
	 */
	@Test
	public void testReadParametersFromDom() throws SAXException, IOException, ParserConfigurationException{

		final String path = IoCommonsTest.class.getResource("/data/parameter/Parameters.xml").toString();
		final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		final Document dom = docBuilder.parse(path);
		final Map<String, Map<String,Object>> parameters = IoCommons.param_readParameters(dom, null, null);
		testParameters(parameters);
	}

	/**
	 * Test de conversion de Map vers DOM.
	 */
	@Test
	public void testParameterMapToDom() throws SAXException, IOException, ParserConfigurationException, DOMException, XPathExpressionException{

		final Map<String,Object> param1 = new LinkedHashMap<>();
		param1.put("name","Durée maximale d'exécution, en seconde");
		param1.put("value","3600");
		final Map<String,Object> param2 = new LinkedHashMap<>();
		param2.put("name","Adresse WSDL du service");
		param2.put("value","http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2/Monitoring.wsdl");
		final Map<String,Object> param3 = new LinkedHashMap<>();
		param3.put("name","Element de test");
		param3.put("value",Arrays.asList("element 1","element 2","element 3"));
		final Map<String,Object> param4 = new LinkedHashMap<>();
		param4.put("name","Nom de domaine SANDRE");
		param4.put("value","3.1");
		param4.put("position","domain");
		param4.put("order","1");
		final Map<String,Object> param5 = new LinkedHashMap<>();
		param5.put("name","xPath de la \"clé\" de décompte des éléments produits");
		param5.put("value","//CdSite/text()[1]");
		param5.put("order","1");

		final Map<String,Object> parameters = new LinkedHashMap<>();
		parameters.put("behavior.maxJobDuration", param1);
		parameters.put("request.url", param2);
		parameters.put("behavior.elements", param3);
		parameters.put("parameter.domain.getSites", param4);
		parameters.put("result.key.getSites", param5);

		final Node node = IoCommons.param_mapToNode("service",parameters);
		final String text = IoCommons.cast_2String(node);
		final String expected =
				"<service>"
				+ "<parameter id=\"maxJobDuration\" type=\"behavior\"><name>Durée maximale d'exécution, en seconde</name><value>3600</value></parameter>"
				+ "<parameter id=\"url\" type=\"request\"><name>Adresse WSDL du service</name><value>http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2/Monitoring.wsdl</value></parameter>"
				+ "<parameter id=\"elements\" type=\"behavior\"><name>Element de test</name><value>element 1</value><value>element 2</value><value>element 3</value></parameter>"
				+ "<operation id=\"getSites\" order=\"1\">"
				+ "<parameter id=\"domain\" position=\"domain\" type=\"parameter\"><name>Nom de domaine SANDRE</name><value>3.1</value><position>domain</position></parameter>"
				+ "<parameter id=\"key\" type=\"result\"><name>xPath de la \"clé\" de décompte des éléments produits</name><value>//CdSite/text()[1]</value></parameter>"
				+ "</operation>"
				+ "</service>";
		assertEquals(expected, text);

	}


	private void testParameters(Map<String,?> candidate){

		assertEquals(5,candidate.size());
		final Map<String,Object> param1 = (Map<String,Object>) candidate.get("behavior.maxJobDuration");
		final Map<String,Object> param2 = (Map<String,Object>) candidate.get("request.url");
		final Map<String,Object> param3 = (Map<String,Object>) candidate.get("behavior.elements");
		final Map<String,Object> param4 = (Map<String,Object>) candidate.get("parameter.domain.getSites");
		final Map<String,Object> param5 = (Map<String,Object>) candidate.get("result.key.getSites");

		assertNotNull(param1);
		assertEquals("Durée maximale d'exécution, en seconde",	param1.get("name"));
		assertEquals("3600",									param1.get("value"));

		assertNotNull(param2);
		assertEquals("Adresse WSDL du service",												param2.get("name"));
		assertEquals("http://xml.sandre.eaufrance.fr/wsdl/Monitoring/2/Monitoring.wsdl",	param2.get("value"));

		assertNotNull(param3);
		assertEquals("Element de test",										param3.get("name"));
		assertEquals(Arrays.asList("element 1","element 2","element 3"),	param3.get("value"));

		assertNotNull(param4);
		assertEquals("Nom de domaine SANDRE",	param4.get("name"));
		assertEquals("3.1",						param4.get("value"));
		assertEquals("domain",					param4.get("position"));
		assertEquals("1",						param4.get("order"));

		assertNotNull(param5);
		assertEquals("xPath de la \"clé\" de décompte des éléments produits",	param5.get("name"));
		assertEquals("//CdSite/text()[1]",										param5.get("value"));
		assertEquals("1",														param5.get("order"));
	}


}
