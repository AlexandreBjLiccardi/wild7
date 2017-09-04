package fr.wild.real;

import fr.wild.utils.StringUtils;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

public class BigFlatFileTests_calculator extends BigFlatFileTests {

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

    @Test
    public void testConditions_NewAPI_10000() throws Exception {
        // Construction à partir d'un fichier csv local
        final String fileRef = "testBigFlatFile";
        wildModelTester
                .addObject(fileRef, "real", "WildFrCsvFile", new Object[]{
            BIGFILE_10000
        });

        Map<Integer, Map<String, Object>> conditionList = new HashMap();
        Map<String, Object> firstCondition = new HashMap();
        firstCondition.put("WildCode", "equal");
        firstCondition.put("ColumnName", "temperature_mesure");
        final double expectedTm = 8.444;
        firstCondition.put("ColumnValue", expectedTm);
        conditionList.put(1, firstCondition);

        Map<String, Object> secondCondition = new HashMap();
        secondCondition.put("WildCode", "contain");
        secondCondition.put("ColumnName", "soussecteur_hydro");
        final String expectedSh = "m1";
        secondCondition.put("ColumnValue", expectedSh);
        secondCondition.put("CombinOperator", "OR");
        conditionList.put(2, secondCondition);

        Map<String, Object> thirdCondition = new HashMap();
        thirdCondition.put("WildCode", "!compare");
        thirdCondition.put("ColumnName", "temperature_mesure");
        thirdCondition.put("ColumnMinValue", 8);
        thirdCondition.put("ColumnMaxValue", 10);
        thirdCondition.put("CombinOperator", "AND");
        conditionList.put(3, thirdCondition);

        final String method = "findRecordsBigFile";
        final Map<Integer, Map<String, Object>> result = wildModelTester.getFromMethod(fileRef, method, new Object[]{conditionList});
        Assert.assertEquals("Number of entries", 324, result.size());
        for (final Map.Entry<Integer, Map<String, Object>> line : result.entrySet()) {
            final Object tm = line.getValue().get("temperature_mesure");
            final Double dTm = tm == null || (tm instanceof String) && ((String)tm).trim().isEmpty()? null : Double.valueOf(tm.toString());
            final Object sh = line.getValue().get("soussecteur_hydro");
            final boolean firstValid = dTm != null && dTm.equals(expectedTm);
            final boolean secondValid = sh != null && sh.toString().toLowerCase().contains(expectedSh);
            final boolean thirdValid = !(dTm != null && dTm >= 8d &&  dTm <= 10d);
            if (!((firstValid || secondValid) && thirdValid))
                Assert.fail(new StringBuilder("Une valeur inattendue est apparue : ").append("tm: ").append(tm).append(", sh:").append(sh).toString());
        }
    }

    private void testUseCase(String fileTestPath, boolean asBifFile) throws Exception {

        String castFileMethod = "castFile";
        String getHeaderMethod = "getHeader";
        String calculator = "calculate";

        if (asBifFile) {
            calculator = "calculateBigFile";
        }

        // Construction à partir d'un fichier csv local
        String fileRef = "testBigFlatFile";
        wildModelTester
                .addObject(fileRef, "real", "WildFrCsvFile", new Object[]{
            fileTestPath
        });

        // Valeurs par défaut
        if (!asBifFile) {
            wildModelTester.executeMethod(fileRef, castFileMethod);
            if (wildModelTester.isError(fileRef)) {
                Assert.fail("Erreur lors du cast du fichier " + fileTestPath);
            }
        }

        HashMap<Integer, String> headers = wildModelTester
                .getFromMethod(fileRef, getHeaderMethod); // Sélection des noms d'en-tête
        for (Integer key : headers.keySet()) {
            System.out.println(key + ":" + headers.get(key));// Affichage test
        }// Test des fonctions de calcul
// // Condition générique
        Map<Integer, Map<String, Object>> conditionList = new HashMap();
  /*      Map<String, Object> firstCondition = new HashMap();
        firstCondition.put("WildCode", "equal");
        firstCondition.put("ColumnName", "temperature_mesure");
        firstCondition.put("ColumnValue", 8.444000);
        conditionList.put(1, firstCondition);*/
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

// // Calcul des statistiques pour test
        HashMap<String, Object> resultAvg = wildModelTester.getFromMethod(fileRef, calculator, new Object[]{
            "temperature_mesure", conditionList, "avg"
        });
        System.out.println(StringUtils.toString(calculator + " avg", resultAvg));
        Assert.assertEquals("AVG", (double)getNbResult(fileTestPath, 1), (double)resultAvg.get("result"), 1E-12);

        HashMap<String, Object> resultGeomAvg = wildModelTester.getFromMethod(fileRef, calculator, new Object[]{
            "temperature_mesure", conditionList, "geom_avg"
        });
        System.out.println(StringUtils.toString(calculator + " geom_avg", resultGeomAvg));
        Assert.assertEquals("geom_avg", ((Number)getNbResult(fileTestPath, 2)).doubleValue(), ((Number)resultGeomAvg.get("result")).doubleValue(), 1E-12);

        HashMap<String, Object> resultMaximum = wildModelTester.getFromMethod(fileRef, calculator, new Object[]{
            "temperature_mesure", conditionList, "maximum"
        });
        System.out.println(StringUtils.toString(calculator + " maximum", resultMaximum));
        Assert.assertEquals("Maximum", getNbResult(fileTestPath, 3), resultMaximum.get("result"));

        HashMap<String, Object> resultMinimum = wildModelTester.getFromMethod(fileRef, calculator, new Object[]{
            "temperature_mesure", conditionList, "minimum"
        });
        System.out.println(StringUtils.toString(calculator + " minimum", resultMinimum));
        Assert.assertEquals("Minimum", getNbResult(fileTestPath, 4), resultMinimum.get("result"));

        HashMap<String, Object> resultVar = wildModelTester.getFromMethod(fileRef, calculator, new Object[]{
            "temperature_mesure", conditionList, "var"
        });
        System.out.println(StringUtils.toString(calculator + " var", resultVar));
        Assert.assertEquals("Variance", (double)getNbResult(fileTestPath, 5), (double)resultVar.get("result"), 1E-11);

        HashMap<String, Object> resultStdDev = wildModelTester.getFromMethod(fileRef, calculator, new Object[]{
            "temperature_mesure", conditionList, "std_dev"
        });
        System.out.println(StringUtils.toString(calculator + " std_dev", resultStdDev));
        Assert.assertEquals("Standard deviation", (double)getNbResult(fileTestPath, 6), (double)resultStdDev.get("result"), 1E-8);

        HashMap<String, Object> resultPerc10 = wildModelTester.getFromMethod(fileRef, calculator, new Object[]{
            "temperature_mesure", conditionList, "percentile_x", 10
        });
        System.out.println(StringUtils.toString(calculator + " percentile_" + 10, resultPerc10));
        Assert.assertEquals("percentile_x : 10%", (double)getNbResult(fileTestPath, 7), resultPerc10.get("result"));

        HashMap<String, Object> resultPerc90 = wildModelTester.getFromMethod(fileRef, calculator, new Object[]{
            "temperature_mesure", conditionList, "percentile_x", 90
        });
        System.out.println(StringUtils.toString(calculator + " percentile_" + 90, resultPerc90));
        Assert.assertEquals("percentile_x : 90%", (double)getNbResult(fileTestPath, 8), resultPerc90.get("result"));

        HashMap<String, Object> resultMedian = wildModelTester.getFromMethod(fileRef, calculator, new Object[]{
            "temperature_mesure", conditionList, "median"
        });
        System.out.println(StringUtils.toString(calculator + " median", resultMedian));
        Assert.assertEquals("Median", (double)getNbResult(fileTestPath, 9), resultMedian.get("result"));

        HashMap<String, Object> resultModal = wildModelTester.getFromMethod(fileRef, calculator, new Object[]{
            "temperature_mesure", conditionList, "modal_value"
        });
        System.out.println(StringUtils.toString(calculator + " modal_value", resultModal));
        Assert.assertEquals("Modal value", getNbResult(fileTestPath, 10), resultModal.get("result").toString());
        Assert.assertEquals("Modal value : associated max", getNbResult(fileTestPath, 11), ((Number)resultModal.get("associated_max_value")).intValue());

        HashMap<String, Object> resultSignificantNb = wildModelTester.getFromMethod(fileRef, calculator, new Object[]{
            "temperature_mesure", conditionList, "significant_nb"
        });
        System.out.println(StringUtils.toString(calculator + " significant_nb", resultSignificantNb));
        Assert.assertEquals("Significant number", getNbResult(fileTestPath, 12), ((Number)resultSignificantNb.get("result")).intValue());

        HashMap<String, Object> resultUnSignificantNb = wildModelTester.getFromMethod(fileRef, calculator, new Object[]{
            "temperature_mesure", conditionList, "unsignificant_nb"
        });
        System.out.println(StringUtils.toString(calculator + " unsignificant_nb", resultUnSignificantNb));
        Assert.assertEquals("Unsignificant number", getNbResult(fileTestPath, 13), ((Number)resultUnSignificantNb.get("result")).intValue());

        HashMap<String, Object> resultDistinctNb = wildModelTester.getFromMethod(fileRef, calculator, new Object[]{
            "temperature_mesure", conditionList, "distinct_nb"
        });
        System.out.println(StringUtils.toString(calculator + " distinct_nb", resultDistinctNb));
        Assert.assertEquals("Distinct number", getNbResult(fileTestPath, 14), ((Number)resultDistinctNb.get("result")).intValue());

        HashMap<String, Object> resultFrequencies = wildModelTester.getFromMethod(fileRef, calculator, new Object[]{
            "temperature_mesure", conditionList, "frequencies"
        });
        System.out.println(StringUtils.toString(calculator + " frequencies", resultFrequencies));
        Assert.assertEquals("Frequencies : result size", getNbResult(fileTestPath, 15), ((Map)resultFrequencies.get("result")).size());
        Assert.assertEquals("Frequencies : count", getNbResult(fileTestPath, 15), ((Number)resultFrequencies.get("count")).intValue());

        HashMap<String, Object> resultCounts = wildModelTester.getFromMethod(fileRef, calculator, new Object[]{
            "temperature_mesure", conditionList, "values_nb"
        });
        System.out.println(StringUtils.toString(calculator + " values_nb", resultCounts));
        Assert.assertEquals("Values nb : result size", getNbResult(fileTestPath, 16), ((HashMap)resultFrequencies.get("result")).size());
        Assert.assertEquals("Values nb : count", getNbResult(fileTestPath, 16), ((Number)resultFrequencies.get("count")).intValue());

        // Nettoyage
        wildModelTester.free(fileRef);
    }

    public Object getNbResult(String file, int testNum) {
        if (BIGFILE_10000.equals(file)) {
            switch(testNum) {
                case 1 : return 7.12583950617284;
                case 2 : return 7.09205765004447;
                case 3 : return 7.995;
                case 4 : return 5.154;
                case 5 : return 0.45570614090839806;
                case 6 : return 0.6750601017008767;
                case 7 : return 6.077;
                case 8 : return 7.82;
                case 9 : return 7.419;

                case 10: return "7.569";
                case 11: return 19;

                case 12: return 324;
                case 13: return 0;
                case 14: return 91;
                case 15: return 91;
                case 16: return 91;
            }
        }
        if (BIGFILE_65000.equals(file)) {
            switch(testNum) {
                case 1 : return 10.412097408400358;
                case 2 : return -1;//1.0023391807549742;
                case 3 : return 19.46;
                case 4 : return -0.031;
                case 5 : return 17.893343154546944;
                case 6 : return 4.230052382;
                case 7 : return 4.636;
                case 8 : return 15.008;
                case 9 : return 11.686;

                case 10: return "13.473";
                case 11: return 48;

                case 12: return 10071;
                case 13: return 5;
                case 14: return 649;
                case 15: return 649;
                case 16: return 649;
            }
        }
        if (BIGFILE_500000.equals(file)) {
            switch(testNum) {
                case 1 : return 10.697071857923497;
                case 2 : return 7.594503114629958;
                case 3 : return 24.871;
                case 4 : return 0.024;
                case 5 : return 36.45857952370712;
                case 6 : return 6.038094030710943;
                case 7 : return 2.717;
                case 8 : return 18.557;
                case 9 : return 11.492;

                case 10: return "0.107";
                case 11: return 113;

                case 12: return 10980;
                case 13: return 7;
                case 14: return 890;
                case 15: return 890;
                case 16: return 890;
            }
        }
        if (BIGFILE_1000000.equals(file)) {
            switch(testNum) {
                case 1 : return 13.13586587905124;
                case 2 : return 11.317808724448158;
                case 3 : return 25.137;
                case 4 : return 0.051;
                case 5 : return 29.223031495817455;
                case 6 : return 5.405833099145538;
                case 7 : return 5.128;
                case 8 : return 19.579;
                case 9 : return 13.882;

                case 10: return "10.614";
                case 11: return 69;

                case 12: return 22092;
                case 13: return 15;
                case 14: return 909;
                case 15: return 909;
                case 16: return 909;
            }
        }
        throw new IllegalArgumentException("unrecognized file or test number:" + file + "(" + testNum + ")");
    }
}
