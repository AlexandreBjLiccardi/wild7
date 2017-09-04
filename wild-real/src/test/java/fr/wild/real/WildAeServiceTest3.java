
package fr.wild.real;

import fr.wild.orchestra.Wild4Test;
import static fr.wild.real.WildAeService.*;
import static fr.wild.real.WildWebService.*;
import fr.wild.utils.StringUtils;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Node;

/**
 *
 * @author Johann Sorel (Geomatys)
 */
public class WildAeServiceTest3 {


	@Test
	public void chainSawTest() throws Exception {
		
	
	final String requestTester = 
	 
			"<service>"
			+ "<parameter id=\"outputFile\" type=\"behavior\"><value>C:\\Users\\alexandre.liccardi\\temp_onema</value></parameter>"
			+ "<parameter id=\"outputFormat\" position=\"outputFormat\" type=\"parameter\"><value>text/xml</value></parameter>"
			+ "<parameter id=\"domain\" position=\"domain\" type=\"parameter\"><value>3.1</value></parameter>"
			+ "<parameter id=\"endPoint\" type=\"request\"><value>http://adour-garonne.eaufrance.fr/services/Monitoring/2/SMonitoring.php</value></parameter>"
			+ "<parameter id=\"url\" type=\"request\"><value>http://adour-garonne.eaufrance.fr/services/Monitoring/2/Monitoring.wsdl</value></parameter>"
			+ "<parameter id=\"typeTest\" type=\"behavior\"><value>complet</value></parameter>"
			+ "<parameter id=\"allAtOnce\" type=\"behavior\"><value>true</value></parameter>"
			+ "<parameter id=\"saveAsXml\" type=\"behavior\"><value>true</value></parameter>"
			+ "<parameter id=\"uniqId\" type=\"behavior\"><value>testTry</value></parameter>"
			+ "</service>";	
	/*
	final String requestTester = 
			"<service>"
			+ "<parameter id=\"outputFile\" type=\"behavior\"><value>C:\\Users\\alexandre.liccardi\\temp_onema</value></parameter>"
			+ "<parameter id=\"outputFormat\" position=\"outputFormat\" type=\"parameter\"><value>text/xml</value></parameter>"
			+ "<parameter id=\"domain\" position=\"domain\" type=\"parameter\"><value>3.1</value></parameter>"
			+ "<parameter id=\"url\" type=\"request\"><value>http://wssie.eau-loire-bretagne.fr/AELB-IWS-MONITORING/services/MonitoringService?wsdl</value></parameter>"
			+ "<parameter id=\"typeTest\" type=\"behavior\"><value>complet</value></parameter>"
			+ "<parameter id=\"allAtOnce\" type=\"behavior\"><value>true</value></parameter>"
			+ "<parameter id=\"saveAsXml\" type=\"behavior\"><value>true</value></parameter>"
			+ "</service>";	
			
	
		final String requestTester = 
				"<service>"
				+ "<parameter id=\"outputFile\" type=\"behavior\"><value>C:\\Users\\alexandre.liccardi\\temp_onema</value></parameter>"
				+ "<parameter id=\"outputFormat\" position=\"outputFormat\" type=\"parameter\"><value>text/xml</value></parameter>"
				+ "<parameter id=\"domain\" position=\"domain\" type=\"parameter\"><value>3.1</value></parameter>"
				+ "<parameter id=\"url\" type=\"request\"><value>http://sweb.agoap.com/services/coursDo/Monitoring/3/Monitoring.wsdl</value></parameter>"
				+ "<parameter id=\"typeTest\" type=\"behavior\"><value>complet</value></parameter>"
				+ "<parameter id=\"allAtOnce\" type=\"behavior\"><value>true</value></parameter>"
				+ "</service>";
		
		final String requestTester = 
				"<service>"
				+ "<parameter id=\"outputFile\" type=\"behavior\"><value>C:\\Users\\alexandre.liccardi\\temp_onema</value></parameter>"
				+ "<parameter id=\"outputFormat\" position=\"outputFormat\" type=\"parameter\"><value>text/xml</value></parameter>"
				+ "<parameter id=\"domain\" position=\"domain\" type=\"parameter\"><value>3.1</value></parameter>"
				+ "<parameter id=\"url\" type=\"request\"><value>http://rhin-meuse.eaufrance.fr/Monitoring/1.m/Monitoring.wsdl</value></parameter>"
				+ "<parameter id=\"typeTest\" type=\"behavior\"><value>complet</value></parameter>"
				+ "<parameter id=\"allAtOnce\" type=\"behavior\"><value>true</value></parameter>"
				+ "</service>";
				
				
		
		final String requestTester = 
				"<service>"
				+ "<parameter id=\"outputFile\" type=\"behavior\"><value>C:\\Users\\alexandre.liccardi\\temp_onema</value></parameter>"
				+ "<parameter id=\"outputFormat\" position=\"outputFormat\" type=\"parameter\"><value>text/xml</value></parameter>"
				+ "<parameter id=\"domain\" position=\"domain\" type=\"parameter\"><value>3.1</value></parameter>"
				+ "<parameter id=\"url\" type=\"request\"><value>http://www.eau-seine-normandie.fr/KitSiceSipe/services/Monitoring.wsdl</value></parameter>"
				+ "<parameter id=\"typeTest\" type=\"behavior\"><value>complet</value></parameter>"
				+ "<parameter id=\"allAtOnce\" type=\"behavior\"><value>true</value></parameter>"
				+ "</service>";
					
		
		final String requestTester = 
				"<service>"
				+ "<parameter id=\"outputFile\" type=\"behavior\"><value>C:\\Users\\alexandre.liccardi\\temp_onema</value></parameter>"
				+ "<parameter id=\"outputFormat\" position=\"outputFormat\" type=\"parameter\"><value>text/xml</value></parameter>"
				+ "<parameter id=\"domain\" position=\"domain\" type=\"parameter\"><value>3.1</value></parameter>"
				+ "<parameter id=\"url\" type=\"request\"><value>http://eaureunion.fr/WebServices/wsdl/Monitoring.wsdl</value></parameter>"
				+ "<parameter id=\"typeTest\" type=\"behavior\"><value>complet</value></parameter>"
				+ "<parameter id=\"allAtOnce\" type=\"behavior\"><value>true</value></parameter>"
				+ "</service>";
		
		final String requestTester = 
				"<service>"
				+ "<parameter id=\"outputFile\" type=\"behavior\"><value>C:\\Users\\alexandre.liccardi\\temp_onema</value></parameter>"
				+ "<parameter id=\"outputFormat\" position=\"outputFormat\" type=\"parameter\"><value>text/xml</value></parameter>"
				+ "<parameter id=\"domain\" position=\"domain\" type=\"parameter\"><value>3.1</value></parameter>"
				+ "<parameter id=\"url\" type=\"request\"><value>http://sice.eaurmc.fr/service/monitoring?wsdl</value></parameter>"
				+ "<parameter id=\"typeTest\" type=\"behavior\"><value>complet</value></parameter>"
				+ "<parameter id=\"allAtOnce\" type=\"behavior\"><value>true</value></parameter>"
				+ "</service>";*/
		Node requestNode = fr.wild.common.IoCommons.cast_String2Node(requestTester);
		
		final Wild4Test tester = new Wild4Test();
		
		final Map<String,Object> parameters = new HashMap<>();

	
		

		tester.addObject("wws","real","WildAeService",new Object[]{requestNode});
		WildAeService wws = tester.getObject("wws");

		
		
		
		try {
			wws.jAeChainsaw();
			wws.DEV_dumpStats();
			//wws.pgSaveTest();
			//System.out.println(StringUtils.toString("Stats",wws.getStats()));
		} finally {
			/*System.out.println(StringUtils.toString("Stats",*//*))*/;
		}
	}

}
