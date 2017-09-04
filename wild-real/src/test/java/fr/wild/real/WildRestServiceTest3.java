
package fr.wild.real;

import fr.wild.orchestra.Wild4Test;
import fr.wild.utils.StringUtils;
import java.util.Map;
import org.junit.Test;
import org.w3c.dom.Node;

/**
 *
 * @author Alexandre Liccardi (ONEMA)
 */
public class WildRestServiceTest3 {

	/**
	 * Test d'écriture du contenu multi-part
	 * @throws Exception 
	 */
	@Test
	public void cookiesTester() throws Exception {
		final Wild4Test tester = new Wild4Test();	
		final String requestTester = ""
				+ "<service><parameter id=\"maxJobDuration\" type=\"behavior\"><name>Durée maximale d'exécution, en seconde</name><value>500</value></parameter><parameter id=\"outputFile\" type=\"behavior\"><name>Répertoire de sortie</name><value>output.html</value></parameter><parameter id=\"url\" type=\"request\"><name>Adresse HTTP du service (submit du formulaire)</name><value>http://orobnat.sante.gouv.fr/orobnat/rechercherResultatQualite.do</value></parameter><parameter id=\"query\" type=\"request\"><name>Nom de l'opération demandée au service distant</name><value>POST</value></parameter><parameter id=\"url\" type=\"cookies\"><name>Adresse HTTP envoyée pour récupération du cookie</name><value>http://orobnat.sante.gouv.fr/orobnat/afficherPage.do?methode=menu&amp;idRegion=73&amp;dpt=031&amp;usd=AEP</value></parameter><parameter id=\"format\" type=\"cookies\"><name>Format de restitution des données (CSV, JSON...)</name><value>json</value></parameter><parameter id=\"methode\" type=\"parameter\"><name>Méthode spécifique</name><value>rechercher</value></parameter><parameter id=\"posPLV\" type=\"parameter\"><name>Variable interne obligatoire</name><value>0</value></parameter><parameter id=\"reseau\" type=\"parameter\"><name>Réseau à requêter</name><value>000239_031</value></parameter></service>"
				+ "";
		
		Node requestNode = fr.wild.common.IoCommons.cast_String2Node(requestTester);	

		tester.addObject("wws","real","WildRestService",new Object[]{requestNode});
		tester.executeMethod("wws", "urlConnect");	
		
		String str = tester.getFromMethod("wws", "getDatas");
		System.out.println(StringUtils.toString("Stats",(Map<?, ?>) tester.getFromMethod("wws", "getStats")));
	
	}

}
