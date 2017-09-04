package fr.wild.real;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

public class BigFlatFileTests_writeBigFile extends BigFlatFileTests {

	// Chemin du fichier à tester
	private final String fileTestPath_output = "BigFlatFileTests/output.csv";
	private final String fileTestPath_output2 = "BigFlatFileTests/output2.csv";

	@Before
	public void setupTest() throws Exception {
		Files.createFile(EXEC_DIR.resolve(fileTestPath_output));
	}

	@After
	public void tearDownTest() throws Exception {
		Files.deleteIfExists(EXEC_DIR.resolve(fileTestPath_output));
		Files.deleteIfExists(EXEC_DIR.resolve(fileTestPath_output2));
	}

	@Test
	public void testWrite_OldAPI_65000 () throws Exception {
		write(BIGFILE_65000, false, 65000);
	}

	@Test
	public void testWrite_NewAPI_65000 () throws Exception {
		write(BIGFILE_65000, true, 65000);
	}

	@Test
	public void testWrite_OldAPI_500000 () throws Exception {
		// les 3314 suplémentaire sont les enregistrements invalide (comme un retours à la ligne avant la fin d'un enregistrement
		// qui en crée deux au lieu d'un)
		int totalRecords = 500000 + 3314;
		write(BIGFILE_500000, false, totalRecords);
	}

	@Test
	public void testWrite_NewAPI_500000 () throws Exception {
		// les 3314 suplémentaire sont les enregistrements invalide (comme un retours à la ligne avant la fin d'un enregistrement
		// qui en crée deux au lieu d'un)
		int totalRecords = 500000 + 3314;
		write(BIGFILE_500000, true, totalRecords);
	}

	@Test
	public void testWrite_OldAPI_1000000 () throws Exception {
		// les 3321 suplémentaire sont les enregistrements invalide (comme un retours à la ligne avant la fin d'un enregistrement
		// qui en crée deux au lieu d'un)
		int totalRecords = 1000000 + 3321;
		write(BIGFILE_1000000, false, totalRecords);
	}

	@Test
	public void testWrite_NewAPI_1000000 () throws Exception {
		// les 3321 suplémentaire sont les enregistrements invalide (comme un retours à la ligne avant la fin d'un enregistrement
		// qui en crée deux au lieu d'un)
		int totalRecords = 1000000 + 3321;
		write(BIGFILE_1000000, true, totalRecords);
	}

	private void write(String baseFile, Boolean asBigFile, int nbLine) throws Exception {

		Integer maxSel = 6000 ;
		String castFileMethod = "castFile";
		String readFileMethod = "readFile";
		String writeFileMethod = "writeFile";
		String writeNbRecordOutput = "writeFile_output2";

		if (asBigFile) {
			castFileMethod = "castBigFile";
			readFileMethod = "readBigFile";
			writeFileMethod = "writeBigFile";
			writeNbRecordOutput = "writeBigFile_output2";
		}

	 	// Construction à partir d'un fichier csv local
		wildModelTester.addObject("testBigFlatFile","real","WildFrCsvFile",new Object[]{baseFile});
        wildModelTester.addObject("fileTestPath_output","real","WildFrCsvFile",new Object[]{fileTestPath_output});

		wildModelTester.executeMethod("testBigFlatFile", castFileMethod); // Construction du fichier (1er essai, valeurs par défaut)
		if (wildModelTester.isError("testBigFlatFile")) {
			Assert.fail("Erreur lors du cast du fichier "+baseFile);
		}

		HashMap<Integer,String> headers = wildModelTester.getFromMethod("testBigFlatFile", "getHeader"); // Sélection des noms d'en-tête
        printHeaders(headers);

		// Récupération aléatoire de 1 000 enregistrements depuis le fichier
		Random rand = new Random();
		maxSel -= 1000 ;
		int randomNum = rand.nextInt((maxSel - 1000) + 1) + 1000;
		HashMap<Integer,HashMap<String,Object>> datas = null;
		datas = wildModelTester
				.getFromMethod("testBigFlatFile", readFileMethod, new Object[]{randomNum, randomNum+1000});

	 	// Input depuis n'importe quel élément iterable, par exemple  les enregistrements précédents
		List<HashMap<String,Object>> al = new ArrayList<>(datas.values());
		int dataSize = datas.size();
		Iterator iterate = al.iterator() ;

	 	// Ecritures du fichier
		wildModelTester.executeMethod("fileTestPath_output", writeFileMethod, new Object[]{
                    datas, true
            });
		System.out.println("Nb lignes depuis map : "+wildModelTester
				.getFromOutput("fileTestPath_output", writeNbRecordOutput));
		//1001 + header line
		int expectedNbLines = dataSize + 1;
		Assert.assertEquals(expectedNbLines, countFileLine(fileTestPath_output));

        // Depuis un Iterator
		wildModelTester.executeMethod("fileTestPath_output", writeFileMethod, new Object[]{
                    iterate, true
            });
		//1001 + 1001 + header line
		expectedNbLines = dataSize + dataSize + 1;
		Assert.assertEquals(expectedNbLines, countFileLine(fileTestPath_output));

		System.out.println("Nb lignes depuis iterator : "+wildModelTester
				.getFromOutput("fileTestPath_output", writeNbRecordOutput));

		//FIXME désactivé pour les ancienne API a cause du renommage de la méthode writeFile(InputStream, Boolean) par DVP_writeFile(InputStream, Boolean)
		if (asBigFile) {
			// Input depuis un fichier entier
			File forImportFile = wildModelTester.getFromMethod("testBigFlatFile", "getFile");
			try (InputStream ip = new FileInputStream(forImportFile)) {
				// Depuis un flux
				wildModelTester.executeMethod("fileTestPath_output", writeFileMethod, new Object[]{
								ip, true
						});
				System.out.println("Nb lignes depuis stream : " + wildModelTester
						.getFromOutput("fileTestPath_output", writeNbRecordOutput));
			}
			//nbLine + dataSize + dataSize + header line
			expectedNbLines = nbLine + dataSize + dataSize + 1;
			Assert.assertEquals(expectedNbLines, countFileLine(fileTestPath_output));


			//depuis un stream dans un fichier vide
			wildModelTester.addObject("fileTestPath_output2","real","WildFrCsvFile",new Object[]{fileTestPath_output2});
			try (InputStream ip = new FileInputStream(forImportFile)) {
				// Depuis un flux
				wildModelTester.executeMethod("fileTestPath_output2", writeFileMethod, new Object[]{ip, true});
				System.out.println("Nb lignes depuis stream : " + wildModelTester
						.getFromOutput("fileTestPath_output2", writeNbRecordOutput));
			}
			//nbLine + header line
			expectedNbLines = nbLine + 1;
			Assert.assertEquals(expectedNbLines, countFileLine(fileTestPath_output2));

			// Sélection des noms d'en-tête
			HashMap<Integer, String> newFileHeaders = wildModelTester.getFromMethod("fileTestPath_output2", "getHeader");
			//test des en-têtes
                        Assert.assertEquals("Header size", EXPECTED_HEADERS.length, newFileHeaders.size());
                        Set<String> headerSet = new HashSet<>(newFileHeaders.values());
                        for (final String str : EXPECTED_HEADERS) {
                            Assert.assertTrue("Missing header in written file : ".concat(str), headerSet.contains(str));
                        }
			wildModelTester.free("fileTestPath_output2");

		}

		// Nettoyage
		wildModelTester.free("testBigFlatFile");
		wildModelTester.free("fileTestPath_output");
	}

	private int countFileLine(String file) throws IOException {
		return Files.readAllLines(EXEC_DIR.resolve(file), Charset.forName("UTF-8")).size();
	}

}
