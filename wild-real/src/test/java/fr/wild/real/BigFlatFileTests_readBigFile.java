package fr.wild.real;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Random;

public class BigFlatFileTests_readBigFile extends BigFlatFileTests {

    private static String SAMPLE1 = "BigFlatFileTests/read_sample1.csv";
    private static String SAMPLE2 = "BigFlatFileTests/read_sample2.csv";

    @Before
    public void setupTest() throws Exception {
        URL sample1 = BigFlatFileTests_readBigFile.class.getResource("/data/big_flat_file/read_sample1.csv");
        Files.copy(Paths.get(sample1.toURI()), EXEC_DIR.resolve(SAMPLE1), StandardCopyOption.REPLACE_EXISTING);
        URL sample2 = BigFlatFileTests_readBigFile.class.getResource("/data/big_flat_file/read_sample2.csv");
        Files.copy(Paths.get(sample2.toURI()), EXEC_DIR.resolve(SAMPLE2), StandardCopyOption.REPLACE_EXISTING);
    }

    @After
    public void tearDownTest() throws Exception {
		Files.deleteIfExists(EXEC_DIR.resolve(SAMPLE1));
		Files.deleteIfExists(EXEC_DIR.resolve(SAMPLE2));
    }

    @Test
    public void testReadBigFile_OldAPI_65000() throws Exception {
        testReadBigFile(BIGFILE_65000, false);
    }

    @Test
    public void testReadBigFile_OldAPI_500000() throws Exception {
        testReadBigFile(BIGFILE_500000, false);
    }

    @Test
    public void testReadBigFile_OldAPI_1000000() throws Exception {
        testReadBigFile(BIGFILE_1000000, false);
    }


    @Test
    public void testReadBigFile_NewAPI_65000() throws Exception {
        testReadBigFile(BIGFILE_65000, true);
    }

    @Test
    public void testReadBigFile_NewAPI_500000() throws Exception {
        testReadBigFile(BIGFILE_500000, true);
    }

    @Test
    public void testReadBigFile_NewAPI_1000000() throws Exception {
        testReadBigFile(BIGFILE_1000000, true);
    }

    @Test
    public void testSpacialCases_OldAPI() throws Exception {
        testSpecialCases(false);
    }

    @Test
    public void testSpacialCases_NewAPI() throws Exception {
        testSpecialCases(true);
    }

    /**
     * Test la lecture d'un gros fichier en utilisant les API readFile ou readBigFile
     *
     * @param testFile fichier à tester
     * @param asBigFile  utilisation des API readFile si {@code false}, readBigFile sinon
     */
    private void testReadBigFile(String testFile, Boolean asBigFile) throws Exception {

        String castMethod = "castFile";
        String readMethod = "readFile";
        if (asBigFile) {
            castMethod = "castBigFile";
            readMethod = "readBigFile";
        }

        // Nombre d'entrées dans le fichier
        Integer maxSel = 1000;

        // Construction à partir d'un fichier csv local
        String fileRef = "testBigFlatFile";
        wildModelTester
                .addObject(fileRef, "real", "WildFrCsvFile", new Object[]{
                        testFile
                });

        // Construction du fichier
        wildModelTester.executeMethod(fileRef, castMethod); // Construction du fichier (1er essai, valeurs par défaut)
        if (wildModelTester.isError(fileRef)) {
            Assert.fail("Erreur lors du cast du fichier "+testFile);
        }

        // Sélection des noms d'en-tête
        HashMap<Integer, String> headers = wildModelTester.getFromMethod(fileRef, "getHeader");

        //test des en-têtes
        Assert.assertArrayEquals(EXPECTED_HEADERS,  toArray(headers));
        printHeaders(headers);

        Random rand = new Random();
        maxSel -= 100;
        int randomNum = rand.nextInt((maxSel - 100) + 1) + 100;

        // Récupération d'une valeur (une ligne)
        HashMap<Integer, HashMap<String, Object>> lines = wildModelTester
                .getFromMethod("testBigFlatFile", readMethod, new Object[]{randomNum});
        printData(lines);
        Assert.assertFalse(lines.isEmpty());
        Assert.assertTrue(lines.size() == 1);

        // Récupération d'un jeu de valeurs
        HashMap<Integer, HashMap<String, Object>> lines_2 = wildModelTester
                .getFromMethod("testBigFlatFile", readMethod, new Object[]{randomNum, randomNum + 100});
        printData(lines_2);
        Assert.assertFalse(lines_2.isEmpty());
        Assert.assertTrue(lines_2.size() == 101);

        // Nettoyage
        wildModelTester.free(fileRef);
    }

    /**
     * Test la lecture de fichiers ou les nombres d'en-tête et de valeurs ne correspondent pas.
     * Exemple 1 : plus d'en-têtes que de valeurs
     * Exemple 2 : moins d'en-têtes que de valeurs
     *
     * @param asBigFile  utilisation des API readFile si {@code false}, readBigFile sinon
     */
    private void testSpecialCases(boolean asBigFile) throws Exception {

        String castMethod = "castFile";
        String readMethod = "readFile";
        if (asBigFile) {
            castMethod = "castBigFile";
            readMethod = "readBigFile";
        }

        //////
        //test sample 1 (plus d'en-têtes que de valeurs)
        wildModelTester.addObject("testBigFlatFile", "real", "WildFrCsvFile", new Object[]{SAMPLE1});
        wildModelTester.executeMethod("testBigFlatFile", castMethod);
        HashMap<Integer, String> headers = wildModelTester.getFromMethod("testBigFlatFile", "getHeader");
        HashMap<Integer, HashMap<String, Object>> lines = wildModelTester.getFromMethod("testBigFlatFile", readMethod, new Object[]{1});

        Assert.assertArrayEquals(new String[]{"field1","field2","field3","field4"}, toArray(headers));
        HashMap<String, Object> record = lines.get(1);
        Assert.assertEquals("value1", record.get("field1"));
        Assert.assertEquals("value2", record.get("field2"));
        Assert.assertEquals("value3", record.get("field3"));
        Assert.assertEquals(null, record.get("field4"));
        wildModelTester.free("testBigFlatFile");

        //////
        //test sample 2 (moins d'en-têtes que de valeurs)
        wildModelTester.addObject("testBigFlatFile2", "real", "WildFrCsvFile", new Object[]{SAMPLE2});
        wildModelTester.executeMethod("testBigFlatFile2", castMethod);
        headers = wildModelTester.getFromMethod("testBigFlatFile2", "getHeader");
        //lis l'enregistrement 101 car la détection des headers retiens la plus longue ligne sur les 100 premières
        lines = wildModelTester.getFromMethod("testBigFlatFile2", readMethod, new Object[]{1});

        Assert.assertArrayEquals(new String[]{"field1","field2","field3"}, toArray(headers));
        record = lines.get(1);
        Assert.assertEquals("value1", record.get("field1"));
        Assert.assertEquals("value2", record.get("field2"));
        Assert.assertEquals("value3", record.get("field3"));
        wildModelTester.free("testBigFlatFile2");
    }
}
