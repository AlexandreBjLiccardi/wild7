package fr.wild.real;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class BigFlatFileTests_selectorWithStatistics extends BigFlatFileTests {

    @Test
    public void testQuery_OldAPI_10000() throws Exception {
        testUseCase(BIGFILE_10000, false);
    }

    @Test
    public void testQuery_OldAPI_65000() throws Exception {
        testUseCase(BIGFILE_65000, false);
    }

    @Test
    public void testQuery_OldAPI_500000() throws Exception {
        testUseCase(BIGFILE_500000, false);
    }

    @Test
    public void testQuery_OldAPI_1000000() throws Exception {
        testUseCase(BIGFILE_1000000, false);
    }

    @Test
    public void testQuery_NewAPI_10000() throws Exception {
        testUseCase(BIGFILE_10000, true);
    }

    @Test
    public void testQuery_NewAPI_65000() throws Exception {
        testUseCase(BIGFILE_65000, true);
    }

    @Test
    public void testQuery_NewAPI_500000() throws Exception {
        testUseCase(BIGFILE_500000, true);
    }

    @Test
    public void testQuery_NewAPI_1000000() throws Exception {
        testUseCase(BIGFILE_1000000, true);
    }

    private void testUseCase(String fileTestPath, boolean asBigFile) throws Exception {

        System.out.println("------------- PATH : ".concat(fileTestPath));
        String castFileMethod = "castFile";
        String getHeaderMethod = "getHeader";
        String getTop = "findTop"; // code : 1
        String getTopPercent = "findTopPercent"; // code : 2
        String getBottom = "findBottom"; // code : 3
        String getBottomPercent = "findBottomPercent"; // code : 4
        String getStatCompare = "findStatCompare"; // code : 5
        String findRecords = "findRecords"; // code : 6
        String saveRecords = "saveRecords"; // code : 7

        if (asBigFile) {
            castFileMethod = "castBigFile";
            getTop = "findTopBigFile";
            getTopPercent = "findTopPercentBigFile";
            getBottom = "findBottomBigFile";
            getBottomPercent = "findBottomPercentBigFile";
            getStatCompare = "findStatCompareBigFile";
            findRecords = "findRecordsBigFile";
            saveRecords = "saveRecordsBigFile";
        }

        // Construction à partir d'un fichier csv local
        String fileRef = "testBigFlatFile";
        wildModelTester
                .addObject(fileRef, "real", "WildFrCsvFile", new Object[]{
            fileTestPath
        });

        // Valeurs par défaut
        wildModelTester.executeMethod(fileRef, castFileMethod);
        if (wildModelTester.isError(fileRef)) {
            Assert.fail("Erreur lors du cast du fichier " + fileTestPath);
        }

        HashMap<Integer, String> headers = wildModelTester
                .getFromMethod(fileRef, getHeaderMethod); // Sélection des noms d'en-tête
        for (Integer key : headers.keySet()) {
            System.out.println(key + ":" + headers.get(key));// Affichage test
        }

        // Définition d'une condition
        Map<Integer, Map<String, Object>> conditionList = new HashMap();
        Map<String, Object> firstCondition = new HashMap();
        firstCondition.put("WildCode", "compare");
        firstCondition.put("ColumnName", "temperature_mesure");
        firstCondition.put("ColumnMinValue", 8);
        firstCondition.put("ColumnMaxValue", 10);
        conditionList.put(1, firstCondition);

// Test des fonctions de recherche utilisant des statistiques
        Integer[] result = wildModelTester.getFromMethod(fileRef, getTop, new Object[]{
            "soussecteur_hydro", "temperature_mesure", conditionList, "avg", 5
        });
        show(getTop, result);
        Assert.assertNotNull(result);
        Assert.assertEquals("Bad size", getResult(1, fileTestPath), result.length);

        result = wildModelTester.getFromMethod(fileRef, getBottom, new Object[]{
            "soussecteur_hydro", "temperature_mesure", conditionList, "avg", 5
        });
        show(getBottom, result);
        Assert.assertNotNull(result);
        Assert.assertEquals("Bad size", getResult(2, fileTestPath), result.length);

        result = wildModelTester.getFromMethod(fileRef, getTopPercent, new Object[]{
            "soussecteur_hydro", "temperature_mesure", conditionList, "var", 20
        });
        show(getTopPercent, result);
        Assert.assertNotNull(result);
        Assert.assertEquals("Bad size", getResult(3, fileTestPath), result.length);

        result = wildModelTester.getFromMethod(fileRef, getBottomPercent, new Object[]{
            "soussecteur_hydro", "temperature_mesure", conditionList, "var", 20
        });
        show(getBottomPercent, result);
        Assert.assertNotNull(result);
        Assert.assertEquals("Bad size", getResult(4, fileTestPath), result.length);

        result = wildModelTester.getFromMethod(fileRef, getStatCompare, new Object[]{
            "soussecteur_hydro", "temperature_mesure", conditionList, "avg", (Double) 9.00, (Double) 9.50
        });
        show(getStatCompare, result);
        Assert.assertNotNull(result);
        Assert.assertEquals("Bad size", getResult(5, fileTestPath), result.length);

        { // Put scope here allow to dereference following map faster.
            final Map<Integer, Map<String, Object>> foundRecords = wildModelTester.getFromMethod(fileRef, findRecords, new Object[]{
                conditionList
            });
            show(findRecords, foundRecords);
            Assert.assertNotNull(foundRecords);
            Assert.assertEquals("Bad size", getResult(6, fileTestPath), foundRecords.size());
        }

        final String recordFile = wildModelTester.getFromMethod(fileRef, saveRecords, new Object[]{
            conditionList
        });
        if(asBigFile){ 
        	Assert.assertNotNull(recordFile);
        	int lineCount = countLines(recordFile);
        	final HashMap<String, Object> tmpResult = new HashMap<>(2);
        	tmpResult.put("result", recordFile);
        	tmpResult.put("Count", lineCount);
        	show(saveRecords, (Map) Collections.singletonMap(1, tmpResult));
        	Assert.assertEquals("Wrong number of lines", getResult(7, fileTestPath), lineCount);
        }
        // Nettoyage
        wildModelTester.free(fileRef);
    }

    private void show(String opeName, Integer[] WILD_toReturn) {
        show(opeName, WILD_toReturn, false);
    }

    private void show(String opeName, Integer[] WILD_toReturn, Boolean showLines) {
        System.out.println("__________ " + opeName);
        if (showLines)
            for (Integer see : WILD_toReturn) {
                System.out.println(see);
            }
        if (WILD_toReturn == null)
            System.out.println("__________ Nb results : 0");
        else
            System.out.println("__________ Nb results : " + WILD_toReturn.length);
    }

    private void show(String opeName, Map<Integer, Map<String, Object>> WILD_toReturn) {
        show(opeName, WILD_toReturn, false);
    }

    private void show(String opeName, Map<Integer, Map<String, Object>> WILD_toReturn, Boolean showLines) {

        System.out.println("__________ " + opeName);
        if (showLines)
            for (Integer see : WILD_toReturn.keySet()) {
                System.out.println("___ Record :" + see);
                for (String see2 : WILD_toReturn.get(see).keySet()) {
                    System.out.println("______ " + see2 + " : " + WILD_toReturn.get(see).get(see2));
                }
            }
        if (WILD_toReturn == null)
            System.out.println("__________ Nb results : 0");
        else
            System.out.println("__________ Nb results : " + WILD_toReturn.size());
    }

    private static int countLines(String recordFile) throws IOException {
        final Path p = Paths.get(recordFile);
        try (final BufferedReader reader = Files.newBufferedReader(p, Charset.defaultCharset())) {
            int nbLines = 0;
            while (reader.readLine() != null) {
                nbLines++;
            }
            return nbLines;
        }
    }

    private static int getResult(int operationCode, final String fileName) {
        switch (fileName) {
            case BIGFILE_10000: {
                switch (operationCode) {
                    case 1 : return 823;
                    case 2 : return 700;
                    case 3 : return 426;
                    case 4 : return 8;
                    case 5 : return 426;
                    case 6 : return 996;
                    case 7 : return 997;
                    default: throw new IllegalArgumentException("Bad operation code : " + operationCode);
                }
            }
            case BIGFILE_65000: {
                switch (operationCode) {
                    case 1 : return 1432;
                    case 2 : return 2734;
                    case 3 : return 5199;
                    case 4 : return 16;
                    case 5 : return 2043;
                    case 6 : return 10531;
                    case 7 : return 10532;
                    default: throw new IllegalArgumentException("Bad operation code : " + operationCode);
                }
            }
            case BIGFILE_500000: {
                switch (operationCode) {
                    case 1 : return 162;
                    case 2 : return 456;
                    case 3 : return 25206;
                    case 4 : return 1737;
                    case 5 : return 31301;
                    case 6 : return 63792;
                    case 7 : return 63793;
                    default: throw new IllegalArgumentException("Bad operation code : " + operationCode);
                }
            }
            case BIGFILE_1000000: {
                switch (operationCode) {
                    case 1 : return 88;
                    case 2 : return 107;
                    case 3 : return 20455;
                    case 4 : return 1382;
                    case 5 : return 59402;
                    case 6 : return 147970;
                    case 7 : return 147971;
                    default: throw new IllegalArgumentException("Bad operation code : " + operationCode);
                }
            }

            default: throw new IllegalArgumentException("Bad file name : " + fileName);
        }
    }
}
