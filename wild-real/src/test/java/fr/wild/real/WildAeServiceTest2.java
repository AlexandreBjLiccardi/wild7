
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
public class WildAeServiceTest2 {


	@Test
	public void chainSawTest() throws Exception {
		final Wild4Test tester = new Wild4Test();
		final Map<String,Object> parameters = new HashMap<>();
	/*	
	final String requestTester = 
			"<service>"
			+ "<parameter id=\"outputFile\" type=\"behavior\"><value>C:\\Users\\alexandre.liccardi\\temp_onema</value></parameter>"
			+ "<parameter id=\"outputFormat\" position=\"outputFormat\" type=\"parameter\"><value>text/xml</value></parameter>"
			+ "<parameter id=\"domain\" position=\"domain\" type=\"parameter\"><value>3.1</value></parameter>"
			+ "<parameter id=\"endPoint\" type=\"request\"><value>http://adour-garonne.eaufrance.fr/services/Monitoring/2/SMonitoring.php</value></parameter>"
			+ "<parameter id=\"url\" type=\"request\"><value>http://adour-garonne.eaufrance.fr/services/Monitoring/2/Monitoring.wsdl</value></parameter>"
			+ "<parameter id=\"typeTest\" type=\"behavior\"><value>complet</value></parameter>"
			+ "<parameter id=\"allAtOnce\" type=\"behavior\"><value>true</value></parameter>"
			+ "</service>";	
	
		Node requestNode = fr.wild.common.IoCommons.cast_String2Node(requestTester);
		tester.addObject("wws","real","WildAeService",new Object[]{requestNode});
		*/

		
		final String wsdlUrl = "http://rhin-meuse.eaufrance.fr/Monitoring/1.m/Monitoring.wsdl";
		parameters.put(PROP_REQUEST_ENDPOINT, Collections.singletonMap("value", "http://rhin-meuse.eaufrance.fr/Monitoring/1.m/SMonitoring.php"));
		final Map<String,Object> analyticConstraintsParam = new HashMap<>();
		analyticConstraintsParam.put("value", "");
		analyticConstraintsParam.put("position", "analyticConstraints");
		parameters.put("parameter.analyticConstraints.getSites", analyticConstraintsParam);
		final Map<String,Object> spatialConstraintsParam = new HashMap<>();
		spatialConstraintsParam.put("value", "");
		spatialConstraintsParam.put("position", "spatialConstraints");
		parameters.put("parameter.spatialConstraints.getSites", spatialConstraintsParam);
		final Map<String,Object> domainConstraintsParam = new HashMap<>();
		domainConstraintsParam.put("value", "*");
		domainConstraintsParam.put("position", "domainConstraints/CdFamillePar");
		parameters.put("parameter.domainConstraints.getSites", domainConstraintsParam);
		
		parameters.put(PROP_BEHAVIOR_ALLATONCE, Collections.singletonMap("value", true));
		
		
		
		/*
		
		final String wsdlUrl = "http://sweb.agoap.com/services/coursDo/Monitoring/3/Monitoring.wsdl";
		parameters.put(PROP_BEHAVIOR_ALLATONCE, Collections.singletonMap("value", false));*/
		
		final String workspace = System.getProperty("user.home")+File.separator+"temp_onema";
		new File(workspace).mkdirs();
		parameters.put(PROP_REQUEST_URL, Collections.singletonMap("value", wsdlUrl));
		parameters.put(PROP_BEHAVIOR_OUTPUTFILE, Collections.singletonMap("value", workspace));
		parameters.put(PROP_BEHAVIOR_TYPETEST, Collections.singletonMap("value", "complet"));
		
		
		final Map<String,Object> domainParam = new HashMap<>();
		domainParam.put("value", "3.1");
		domainParam.put("position", "domain");
		final Map<String,Object> outputFormatParam = new HashMap<>();
		outputFormatParam.put("value", "text/xml");
		outputFormatParam.put("position", "outputFormat");
		
		parameters.put("parameter.domain", domainParam);
		parameters.put("parameter.outputFormat", outputFormatParam);
		

		tester.addObject("wws","real","WildAeService",new Object[]{parameters/*requestNode*/});
		WildAeService wws = tester.getObject("wws");

		
		
		
		try {
			wws.jAeChainsaw();
		} finally {
			System.out.println(StringUtils.toString("Stats",wws.getStats()));
		}
	}

}
