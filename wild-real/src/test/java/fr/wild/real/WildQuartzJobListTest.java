
package fr.wild.real;

import fr.wild.orchestra.Wild4Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import org.geotoolkit.nio.IOUtilities;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test de manipulation de la liste de job quartz.
 *
 */
public class WildQuartzJobListTest {

	private static final String LIST_CONFIG =
			"<configuration>\n" +
			"	<parameter id=\"nJobs\" type=\"jobsConfiguration\">\n" +
			"		<value>3</value>\n" +
			"	</parameter>\n" +
			"	<parameter id=\"nSchedules\" type=\"jobsConfiguration\">\n" +
			"		<value>1000</value>\n" +
			"	</parameter>\n" +
			"	<parameter id=\"maxDuration\" type=\"jobsConfiguration\">\n" +
			"		<value>60</value>\n" +
			"	</parameter>\n" +
			"</configuration>";

	private static final String JOB_CONFIG =
			"<job id=\"TestGetData_20160909\">\n" +
			"<configuration>\n" +
			"	<parameter id=\"maxDuration\" type=\"jobConfiguration\">\n" +
			"		<value>30</value>\n" +
			"	</parameter>\n" +
			"	<parameter id=\"frequency\" type=\"jobConfiguration\">\n" +
			"		<value>5</value>\n" +
			"	</parameter>\n" +
			"	<parameter id=\"killIfTwice\" type=\"jobConfiguration\">\n" +
			"		<value>true</value>\n" +
			"	</parameter>\n" +
			"	<parameter id=\"javaClass\" type=\"jobConfiguration\">\n" +
			"		<name>Nom du type de service JAVA utilisé</name>\n" +
			"		<value>FakeScenario</value>\n" +
			"	</parameter>\n" +
			"</configuration>\n" +
			"<service>\n" +
			"	<parameter id=\"sleep\" type=\"fake\">\n" +
			"		<value>2</value>\n" +
			"	</parameter>\n" +
			"</service>\n" +
			"</job>";

	
	@Test
	public void testCreate() throws Exception {

		final Path listConfigPath = Files.createTempFile("list", ".xml");
		listConfigPath.toFile().deleteOnExit();

		IOUtilities.writeString(LIST_CONFIG, listConfigPath);

		final Wild4Test builder = new Wild4Test();
		builder.addObject("list", "real", "WildQuartzJobList", new Object[]{listConfigPath.toFile().getPath()});
		final WildQuartzJobList list = builder.getObject("list");

	}

	@Test
	public void testExecuteJob() throws Exception {

		//list config
		final Path listConfigPath = Files.createTempFile("list", ".xml");
		listConfigPath.toFile().deleteOnExit();
		IOUtilities.writeString(LIST_CONFIG, listConfigPath);

		//job config
		final Path jobConfigPath = Files.createTempFile("job", ".xml");
		jobConfigPath.toFile().deleteOnExit();
		IOUtilities.writeString(JOB_CONFIG, jobConfigPath);

		final Wild4Test builder = new Wild4Test();
		builder.addObject("list", "real", "WildQuartzJobList", new Object[]{listConfigPath.toFile().getPath()});
		final WildQuartzJobList list = builder.getObject("list");

		final String jid = list.addJob(jobConfigPath.toFile().getPath());

		//job executé toutes les 5 secondes, durée 2 secondes

		Thread.sleep(1000);
		//1 sec.
		assertEquals(null, list.isDone(jid)); //pas encore de resultat
		Map<String, Object> infoJob = list.infoJob(jid);
		assertEquals(1,infoJob.get("status")); //en cours

		Thread.sleep(2000);
		//3 sec.
		assertEquals(true, list.isDone(jid)); //terminé avec succes
		infoJob = list.infoJob(jid);
		assertEquals(2,infoJob.get("status")); //terminé avec succes

		Thread.sleep(3000);
		//6 sec.
		assertEquals(true, list.isDone(jid)); // derniere execution terminé avec succes
		infoJob = list.infoJob(jid);
		assertEquals(1,infoJob.get("status")); //en cours

		Thread.sleep(2000);
		//8 sec.
		assertEquals(true, list.isDone(jid)); //terminé avec succes
		infoJob = list.infoJob(jid);
		assertEquals(2,infoJob.get("status")); //terminé avec succes

		list.deleteJob(jid);

		assertNull(list.infoJob(jid));
		assertEquals(0, list.getListInfos().size());

	}

}
