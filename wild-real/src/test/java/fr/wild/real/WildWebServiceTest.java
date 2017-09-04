
package fr.wild.real;

import fr.wild.orchestra.Wild4Test;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import org.apache.commons.io.IOUtils;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Johann Sorel (Geomatys)
 */
public class WildWebServiceTest {

	/**
	 * Test de detection du type de fichier.
	 */
	@Test
	public void testFormatDetection() throws Exception {
		final Wild4Test tester = new Wild4Test();

		//test format xml
		File file = File.createTempFile("noname", "");
		file.deleteOnExit();
		try (OutputStream out = new FileOutputStream(file)) {
			IOUtils.write("<service>\n</service>", out);
		}

		tester.addObject("wws","real","WildRestService",new Object[]{new HashMap()});
		WildWebService wws = tester.getObject("wws");

		wws.checkDatas(file.getPath(), null);
		assertEquals("application/xml",wws.getStats().get(WildWebService.STAT_RESPONSE_FORMAT));


		//test format json
		file = File.createTempFile("noname", "");
		file.deleteOnExit();
		try (OutputStream out = new FileOutputStream(file)) {
			IOUtils.write("{\n\"name\":\"test\"\n}", out);
		}

		wws.checkDatas(file.getPath(), null);
		assertEquals("application/json",wws.getStats().get(WildWebService.STAT_RESPONSE_FORMAT));

	}

}
