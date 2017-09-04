package fr.wild.real;

import fr.wild.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class BigFlatFileTests_selector extends BigFlatFileTests {

    @Test
    public void testQuery_OldAPI_10000() throws Exception {
        testUseCase(BIGFILE_10000, false);
    }

    @Test
    public void testQuery_OldAPI_65000() throws Exception {
        testUseCase(BIGFILE_65000, false);
    }

    @Ignore // Takes too much time
    @Test
    public void testQuery_OldAPI_500000() throws Exception {
        testUseCase(BIGFILE_500000, false);
    }

    @Ignore // Takes too much time
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

    private void testUseCase(String fileTestPath, boolean asBifFile) throws Exception {

        String castFileMethod = "castFile";
        String getHeaderMethod = "getHeader";
        String getEqualsTo = "findEqual";
        String getContains = "findContain";
        String getRegex = "findRegex";
        String getWithin = "findWithin";
        String getCompare = "findCompare";
        String getLines = "findLines";
        String getRecords = "findRecords";

        if (asBifFile) {
            castFileMethod = "castBigFile";
            getEqualsTo = "findEqualBigFile";
            getContains = "findContainBigFile";
            getRegex = "findRegexBigFile";
            getWithin = "findWithinBigFile";
            getCompare = "findCompareBigFile";
            getLines = "findLinesBigFile";
            getRecords = "findRecordsBigFile";
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
        // Test des fonctions de recherche

        // // Egalité stricte
        Integer[] result = wildModelTester.getFromMethod(fileRef, getEqualsTo, new Object[]{
            "temperature_mesure", 8.444000
        //"soussecteur_hydro","y06-lagly"
        });
        TestUtils.show(getEqualsTo, result);
        Assert.assertEquals(getNbResult(fileTestPath, 1), result.length);

        // // Recherche de contenu
        result = wildModelTester.getFromMethod(fileRef, getContains, new Object[]{
            "temperature_mesure", 82
        //"soussecteur_hydro","y"
        });
        TestUtils.show(getContains, result);
        Assert.assertEquals(getNbResult(fileTestPath, 2), result.length);

        // // Recherche par expression régulière
        result = wildModelTester.getFromMethod(fileRef, getRegex, new Object[]{
            "soussecteur_hydro", "m1"
        //"soussecteur_hydro","y"
        });
        TestUtils.show(getWithin, result);
        Assert.assertEquals(getNbResult(fileTestPath, 3), result.length);

        // // Recherche par bornes alphabêtique
        result = wildModelTester.getFromMethod(fileRef, getWithin, new Object[]{
            "soussecteur_hydro", "m1", "m12"
        //"soussecteur_hydro","y"
        });
        TestUtils.show(getWithin, result);
        Assert.assertEquals(getNbResult(fileTestPath, 4), result.length);

        // // Recherche par opérateur mathématique
        result = wildModelTester.getFromMethod(fileRef, getCompare, new Object[]{
            "temperature_mesure", 8, 10
        //"soussecteur_hydro","y"
        });
        TestUtils.show(getCompare, result);
        Assert.assertEquals(getNbResult(fileTestPath, 5), result.length);

        // Exemple de combinaison de conditions
        Map<Integer, Map<String, Object>> conditionList = new HashMap();
        Map<String, Object> firstCondition = new HashMap();
        firstCondition.put("WildCode", "equal");
        firstCondition.put("ColumnName", "temperature_mesure");
        firstCondition.put("ColumnValue", 8.444000);
        conditionList.put(1, firstCondition);
        Map<String, Object> secondCondition = new HashMap();
        secondCondition.put("WildCode", "contain");
        secondCondition.put("ColumnName", "soussecteur_hydro");
        secondCondition.put("ColumnValue", "m1");
        secondCondition.put("CombinOperator", "OR");
        conditionList.put(2, secondCondition);
        Map<String, Object> thirdCondition = new HashMap();
        thirdCondition.put("WildCode", "!compare");
        thirdCondition.put("ColumnName", "temperature_mesure");
        thirdCondition.put("ColumnMinValue", 8);
        thirdCondition.put("ColumnMaxValue", 10);
        thirdCondition.put("CombinOperator", "AND");
        conditionList.put(3, thirdCondition);

        // // Récupération des index de lignes
        result = wildModelTester.getFromMethod(fileRef, getLines, new Object[]{
            conditionList
        });
        TestUtils.show(getLines, result, false);
        Assert.assertEquals(getNbResult(fileTestPath, 6), result.length);

        // // Récupération des enregistrements
        Map<Integer, Map<String, Object>> resultRecords = wildModelTester.getFromMethod(fileRef, getRecords, new Object[]{
            conditionList
        });
        System.out.println(StringUtils.toString(getRecords, resultRecords));
        Assert.assertEquals(getNbResult(fileTestPath, 7), resultRecords.size());

        // Nettoyage
        wildModelTester.free(fileRef);
    }

    public int getNbResult(String file, int testNum) {
        if (BIGFILE_10000.equals(file)) {
            switch(testNum) {
                case 1 : return 18;
                case 2 : return 293;
                case 3 : return 489;
                case 4 : return 489;
                case 5 : return 996;
                case 6 : return 324;
                case 7 : return 324;
            }
        }
        if (BIGFILE_65000.equals(file)) {
            switch(testNum) {
                case 1 : return 178;
                case 2 : return 2356;
                case 3 : return 11945;
                case 4 : return 6953;
                case 5 : return 10531;
                case 6 : return 10076;
                case 7 : return 10076;
            }
        }
        if (BIGFILE_500000.equals(file)) {
            switch(testNum) {
                case 1 : return 850;
                case 2 : return 16036;
                case 3 : return 12289;
                case 4 : return 7443;
                case 5 : return 63792;
                case 6 : return 10987;
                case 7 : return 10987;
            }
        }
        if (BIGFILE_1000000.equals(file)) {
            switch(testNum) {
                case 1 : return 2038;
                case 2 : return 32823;
                case 3 : return 25234;
                case 4 : return 16066;
                case 5 : return 147970;
                case 6 : return 22107;
                case 7 : return 22107;
            }
        }
        throw new IllegalArgumentException("unrecognized file or test number:" + file + "(" + testNum + ")");
    }
}
