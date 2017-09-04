
package fr.wild.real;

import fr.wild.orchestra.Wild4Test;
import fr.wild.utils.PostMultiPartObject;
import fr.wild.utils.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Alexandre Liccardi (ONEMA)
 */
public class WildRestServiceTest2 {

	/**
	 * Test d'écriture du contenu multi-part
	 * @throws Exception 
	 */
	@Test
	public void cookiesTester() throws Exception {
		final Wild4Test tester = new Wild4Test();	
		final String requestTester = "<service><parameter id=\"maxJobDuration\" type=\"behavior\"><name>Durée maximale d'exécution, en seconde</name><value>500</value></parameter><parameter id=\"outputFile\" type=\"behavior\"><name>Répertoire de sortie</name><value>output.json</value></parameter><parameter id=\"url\" type=\"request\"><name>Adresse HTTP du service</name><value>http://api.hubeau.fr/v0/etat_piscicole/poissons</value></parameter><parameter id=\"query\" type=\"request\"><name>Nom de l'opération demandée au service distant</name><value>GET</value></parameter><parameter id=\"format\" type=\"parameter\"><name>Format de restitution des données (CSV, JSON...)</name><value>json</value></parameter><parameter id=\"code_station\" type=\"parameter\"><name>Code(s) de la station (codification ONEMA), si plusieurs codes, séparer les code par une virgule, le nombre maximum de code est 200</name><value>06250157,01020102</value></parameter><parameter id=\"testKey\" type=\"result\"><name>Parse natif JSON des resultats</name><value>data/localisation</value><value>data/code_station</value><value>data/code_cours_eau</value><value>data/nom_cours_eau</value><value>data/uri_cours_eau</value><value>data/numero_operation</value><value>data/date_operation</value><value>data/code_espece_poisson</value><value>data/nom_poisson</value><value>data/effectif</value><value>data/poids</value><value>data/densite</value><value>data/geometry</value></parameter><parameter id=\"testJson\" type=\"behavior\"><value>3</value></parameter></service>";
		
		Node requestNode = fr.wild.common.IoCommons.cast_String2Node(requestTester);	
		
		tester.addObject("wws","real","WildRestService",new Object[]{requestNode});
		tester.executeMethod("wws", "urlConnect");	
		
		String str = tester.getFromMethod("wws", "getDatas");
		System.out.println(StringUtils.toString("Stats",(Map<?, ?>) tester.getFromMethod("wws", "getStats")));
	
	}

}
