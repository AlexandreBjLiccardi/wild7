package fr.wild.real;


import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class BigFlatFileTests_manageBigFile extends BigFlatFileTests {

	private final String fileTestCopyPath = "BigFlatFileTests/copy.csv"; // Chemin de destination, copie
	private final String fileTestCutPath = "BigFlatFileTests/cut.csv"; // Chemin de destination, déplacement

	@Test
	public void testBigFile_OldAPI_65000() throws Exception {
		testUseCase(BIGFILE_65000, false);
	}

	@Test
	public void testBigFile_NewAPI_65000() throws Exception {
		testUseCase(BIGFILE_65000, true);
	}

	@Test
	public void testBigFile_OldAPI_500000() throws Exception {
		testUseCase(BIGFILE_500000, false);
	}

	@Test
	public void testBigFile_NewAPI_500000() throws Exception {
		testUseCase(BIGFILE_500000, true);
	}

	@Test
	public void testBigFile_OldAPI_1000000() throws Exception {
		testUseCase(BIGFILE_1000000, false);
	}

	@Test
	public void testBigFile_NewAPI_1000000() throws Exception {
		testUseCase(BIGFILE_1000000, true);
	}

	private void testUseCase(String testFile, Boolean asBigFile) throws Exception {

		String copyMethod = "copy";
		String moveMethod = "move";
		String deleteMethod = "delete";
		if (asBigFile) {
			copyMethod = "copyToBigFile";
			moveMethod = "moveToBigFile";
			deleteMethod = "deleteBigFile";
		}

		// Construction à partir d'un fichier csv local
		String fileRef = "testBigFlatFile";
		wildModelTester
 			.addObject(fileRef,"real","WildFrCsvFile",new Object[]{
					testFile
			});

 		// Copie (testFile -> BigFlatFileTests/copy.csv)
		testCopy(fileRef, copyMethod, testFile, fileTestCopyPath);

		// Déplacement dans un sens (testFile -> BigFlatFileTests/cut.csv)
		testMove(fileRef, moveMethod, testFile, fileTestCutPath);

 	 	// Déplacement dans l'autre sens (BigFlatFileTests/cut.csv -> testFile)
 		wildModelTester
 			.addObject("testBigFlatFile_cut","real","WildFrCsvFile",new Object[]{fileTestCutPath});
		testMove("testBigFlatFile_cut", moveMethod, fileTestCutPath, testFile);

 		// Suppression du fichier copié
 		wildModelTester
 			.addObject("testBigFlatFile_copy","real","WildFrCsvFile",new Object[]{fileTestCopyPath});
		wildModelTester
			.executeMethod("testBigFlatFile_copy", deleteMethod);
		Assert.assertTrue(Files.notExists(EXEC_DIR.resolve(fileTestCopyPath)));
 	 	// Nettoyage
 		wildModelTester.free(fileRef);
	}

	/**
	 * Test la copie
	 * @param fileRef
	 * @param method "copy" ou "copyBigFile"
	 * @param source
	 * @param target
     * @throws IOException
     */
	private void testCopy(String fileRef, String method, String source, String target) throws IOException {
		wildModelTester.executeMethod(fileRef, method, new Object[]{target});
		Path sourcePath = EXEC_DIR.resolve(source);
		Path targetPath = EXEC_DIR.resolve(target);
		Assert.assertTrue(Files.size(sourcePath) == Files.size(targetPath));
	}

	/**
	 *
	 * @param fileRef
	 * @param method "move" ou "moveBigFile"
	 * @param source
	 * @param target
     * @throws IOException
     */
	private void testMove(String fileRef, String method, String source, String target) throws IOException {
		wildModelTester.executeMethod(fileRef, method, new Object[]{target});
		Path sourcePath = EXEC_DIR.resolve(source);
		Path targetPath = EXEC_DIR.resolve(target);
		Assert.assertTrue(Files.notExists(EXEC_DIR.resolve(sourcePath))); //verification de la suppression
		Assert.assertTrue(Files.exists(targetPath));
		Assert.assertTrue(Files.size(targetPath) > 0);
	}
}
