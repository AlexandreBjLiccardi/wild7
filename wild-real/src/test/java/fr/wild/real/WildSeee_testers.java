
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
 * @author Alexandre Liccardi (AFB)
 */
public class WildSeee_testers {

	WildSeeeService WildSeeeService_4Test ;
	Node requestNode ;
	Wild4Test tester ;
	
	private void LaunchTest(String testCode) throws Exception{
			if(tester==null) tester = new Wild4Test();
			final String requestTester = 
				"<service>"
				+"<parameter id=\"url\" type=\"request\"><value>http://seee.eaufrance.fr</value></parameter>"
				+"<parameter id=\"testSeee\" type=\"behavior\"><name>Nom du test SEEE, parmi C9F1, C9F2, C9F3</name><value>"+testCode+"</value></parameter>"
				+ "</service>";	
			Node requestNode = fr.wild.common.IoCommons.cast_String2Node(requestTester);
			tester.addObject("wws","real","WildSeeeService",new Object[]{requestNode});
			WildSeeeService_4Test = tester.getObject("wws");
			try {
				WildSeeeService_4Test.exec_test() ;
			} finally {
				System.out.println(StringUtils.toString("Stats",WildSeeeService_4Test.getStats()));
			}
	}	
	
	@Test
	public void C9F1() throws Exception {
		LaunchTest("C9F1");
	}
	@Test
	public void C9F2() throws Exception {
		LaunchTest("C9F2");
	}
	@Test
	public void C9F3() throws Exception {
		LaunchTest("C9F3");
	}

}
