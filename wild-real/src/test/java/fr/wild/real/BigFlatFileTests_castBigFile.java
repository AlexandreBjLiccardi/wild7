package fr.wild.real;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

public class BigFlatFileTests_castBigFile extends BigFlatFileTests {

	@Test
	public void testCastFlat_OldAPI_65000() throws Exception{
		testUseCase(BIGFILE_65000, false);
	}

	@Test
	public void testCastFlat_OldAPI_500000() throws Exception{
		testUseCase(BIGFILE_500000, false);
	}

	@Test
	public void testCastFlat_OldAPI_1000000() throws Exception{
		testUseCase(BIGFILE_1000000, false);
	}

	@Test
	public void testCastFlat_NewAPI_65000() throws Exception{
		testUseCase(BIGFILE_65000, true);
	}

	@Test
	public void testCastFlat_NewAPI_500000() throws Exception{
		testUseCase(BIGFILE_500000, true);
	}

	@Test
	public void testCastFlat_NewAPI_1000000() throws Exception{
		testUseCase(BIGFILE_1000000, true);
	}

    private void testUseCase(String fileTestPath, boolean asBifFile) throws Exception{

		String castFileMethod = "castFile";
		String getHeaderMethod = "getHeader";
		if(asBifFile) {
			castFileMethod = "castBigFile";
		}

		// Construction à partir d'un fichier csv local
		String fileRef = "testBigFlatFile";
		wildModelTester
		.addObject(fileRef,"real","WildFrCsvFile",new Object[]{
				fileTestPath
		});

		// Valeurs par défaut
		wildModelTester.executeMethod(fileRef, castFileMethod);
		if (wildModelTester.isError(fileRef)) {
			Assert.fail("Erreur lors du cast du fichier "+fileTestPath);
		}

		HashMap<Integer,String> headers = wildModelTester
				.getFromMethod(fileRef, getHeaderMethod); // Sélection des noms d'en-tête
		for(Integer key :headers.keySet())System.out.println(key+":"+headers.get(key));// Affichage test

		//test des en-têtes
		Assert.assertArrayEquals(EXPECTED_HEADERS,  toArray(headers));

		// Valeurs forcées
		/*
		wildModelTester
				.executeMethod("testBigFlatFile","castFile"); // Construction du fichier (1er essai, en forçant le nombre de lignes tests)
		headers = wildModelTester
				.getFromMethod("testBigFlatFile", "getHeader"); // Sélection des noms d'en-tête
		for(Integer key:headers.keySet())System.out.println(key+":"+headers.get(key));// Affichage test
		*/
		// Nettoyage
		wildModelTester.free(fileRef);
	}

}
