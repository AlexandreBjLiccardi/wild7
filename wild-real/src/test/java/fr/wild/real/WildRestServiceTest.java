
package fr.wild.real;

import fr.wild.utils.PostMultiPartObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Johann Sorel (Geomatys)
 */
public class WildRestServiceTest {

	/**
	 * Test d'écriture du contenu multi-part
	 */
	@Test
	public void multiPartTest() throws IOException {

		final PostMultiPartObject part1 = new PostMultiPartObject("nom1", null, "Du text et du text.");
		final PostMultiPartObject part2 = new PostMultiPartObject("nom2", "text/plain", "plain text, plain text");
		final Map<String,String> properties = new HashMap<>();
		properties.put("name", "nom3");
		properties.put("filename", "file.bin");
		final PostMultiPartObject part3= new PostMultiPartObject(properties, "application/binary", new byte[]{30,31,32,33,34,35});

		final List lst = new ArrayList();
		lst.add(part1);
		lst.add(part2);
		lst.add(part3);

		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		WildRestService.DEV_writeMultiPart(lst, out, "UTF-8", "123456789");
		final String str = new String(out.toByteArray());

		final String expected = "--123456789\r\n" +
			"Content-Disposition: form-data; name=\"nom1\"\r\n" +
			"\r\n" +
			"Du text et du text.\r\n" +
			"--123456789\r\n" +
			"Content-Disposition: form-data; name=\"nom2\"\r\n" +
			"Content-Type: text/plain\r\n" +
			"\r\n" +
			"plain text, plain text\r\n" +
			"--123456789\r\n" +
			"Content-Disposition: form-data; name=\"nom3\"; filename=\"file.bin\"\r\n" +
			"Content-Type: application/binary\r\n" +
			"Content-Transfer-Encoding: binary\r\n" +
			"\r\n" +
			"\u001E\u001F\u0020\u0021\"\u0023\r\n" +
			"--123456789--\r\n";

		Assert.assertEquals(expected,str);

	}

}
