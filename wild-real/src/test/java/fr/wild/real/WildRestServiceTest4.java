
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
public class WildRestServiceTest4 {

	/**
	 * Test d'écriture du contenu multi-part
	 * @throws Exception 
	 */
	@Test
	public void cookiesTester() throws Exception {
		final Wild4Test tester = new Wild4Test();	
		final String requestTester = ""
				+ "<service>"
				+ "<parameter id=\"maxJobDuration\" type=\"behavior\"><name>Durée maximale d'exécution, en seconde</name><value>500</value></parameter>"
				+ "<parameter id=\"outputFile\" type=\"behavior\"><name>Répertoire de sortie</name><value>output.html</value></parameter>"
				+ "<parameter id=\"url\" type=\"request\"><name>Adresse HTTP du service (submit du formulaire)</name>"
					+ "<value>http://seee.eaufrance.fr/Ic2A/</value></parameter>"
				+ "<parameter id=\"path\" type=\"request\"><name>URI ressource</name>"
					+ "<value>?api=calcul</value></parameter>"
				+ "<parameter id=\"query\" type=\"request\"><name>Nom de l'opération demandée au service distant</name><value>POST</value></parameter>"
				+ "<parameter id=\"multipart\" type=\"request\"><name>Fichier de données</name><value>entree_I2M2_op100.txt</value></parameter>"
				+ "<parameter id=\"indicateur\" type=\"parameter\"><name>Type d'indicateur à calculer</name><value>I2M2</value></parameter>"
				+ "<parameter id=\"version\" type=\"parameter\"><name>Version de l'indicateur 'xx.xx.xx'</name><value>1.0.1</value></parameter>"
				+ "</service>"
				+ "";
		System.out.println(requestTester);
		Node requestNode = fr.wild.common.IoCommons.cast_String2Node(requestTester);	

		tester.addObject("wws","real","WildRestService",new Object[]{requestNode});
		tester.executeMethod("wws", "urlConnect");	
		
		String str = tester.getFromMethod("wws", "getDatas");
		
		WildRestService rs = tester.getObject("wws");
		rs.DEV_dumpStats();
		
		//System.out.println(StringUtils.toString("Stats",(Map<?, ?>) tester.getFromMethod("wws", "getStats")));
	
	}

}
