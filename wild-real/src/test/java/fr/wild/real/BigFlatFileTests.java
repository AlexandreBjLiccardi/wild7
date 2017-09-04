package fr.wild.real;

import fr.wild.orchestra.Wild4Test;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;

/**
 * @author Quentin Boileau (Geomatys)
 */
public abstract class BigFlatFileTests {

    protected static final String[] EXPECTED_HEADERS = new String[] {
            "id_releve",
            "soussecteur_hydro",
            "code_sandre_station",
            "date_mesure",
            "temperature_mesure",
            "remarque_mesure",
            "code_departement",
            "localisation",
            "x_position",
            "y_position"
    };

    public static final Path EXEC_DIR = Paths.get("wild/execution/TestUser/TEMP_TEST_SERVICE").toAbsolutePath();
    public static final String BIGFILE_10000 = "BigFlatFileTests/export_rnt_20052016_10000.csv";
    public static final String BIGFILE_65000 = "BigFlatFileTests/export_rnt_20052016_65000.csv";
    public static final String BIGFILE_500000 = "BigFlatFileTests/export_rnt_20052016_500000.csv";
    public static final String BIGFILE_1000000 = "BigFlatFileTests/export_rnt_20052016_1000000.csv";

    protected static Wild4Test wildModelTester;

    /**
     * Initialise la classe de tests en décompressant les données de test dans un dossier temporaire
     * Et initialise Wild4Test pour optimizer le temps d'execution des tests
     */
    @BeforeClass
    public static void setUp() throws Exception {

        URL bigFile10000URL = BigFlatFileTests_readBigFile.class.getResource("/data/big_flat_file/export_rnt_20052016_10000.zip");
        URL bigFile65000URL = BigFlatFileTests_readBigFile.class.getResource("/data/big_flat_file/export_rnt_20052016_65000.zip");
        URL bigFile500000URL = BigFlatFileTests_readBigFile.class.getResource("/data/big_flat_file/export_rnt_20052016_500000.zip");
        URL bigFile1000000URL = BigFlatFileTests_readBigFile.class.getResource("/data/big_flat_file/export_rnt_20052016_1000000.zip");

        Path deflateDir = Paths.get("wild/execution/TestUser/TEMP_TEST_SERVICE/BigFlatFileTests").toAbsolutePath();
        unzipNIO(Paths.get(bigFile10000URL.toURI()), deflateDir);
        unzipNIO(Paths.get(bigFile65000URL.toURI()), deflateDir);
        unzipNIO(Paths.get(bigFile500000URL.toURI()), deflateDir);
        unzipNIO(Paths.get(bigFile1000000URL.toURI()), deflateDir);

        wildModelTester = new Wild4Test();
    }

    @AfterClass
    public static synchronized void tearDown() throws Exception {
        Files.walkFileTree(EXEC_DIR, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return super.postVisitDirectory(dir, exc);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return super.visitFile(file, attrs);
            }
        });
    }

    protected void printHeaders(HashMap<Integer, String> headers) {
        for (Integer key : headers.keySet()) {
            System.out.println(key + ":" + headers.get(key));// Affichage test
        }
        System.out.println("############");
    }

    protected void printData(HashMap<Integer, HashMap<String, Object>> lines) {
        for (Integer key : lines.keySet()) {
            for (String key_cols : lines.get(key).keySet()) {
                System.out.println(key + "_" + key_cols + ":" + lines.get(key).get(key_cols));// Affichage test
            }
        }
        System.out.println("############");
    }

    protected String[] toArray(HashMap<Integer, String> headers) {
        String[] headerArr = new String[headers.size()];
        for (Integer key : headers.keySet()) {
            headerArr[key-1] = headers.get(key);
        }
        return headerArr;
    }

    /**
     * Unzip les fichiers du premier niveau de profondeur contenu dans un zip en utilisant l'API NIO de Java7
     *
     * @param zipPath chemin vers le fichier zip
     * @param target chemin vers le dossier de sortie
     * @throws IOException
     */
    private static void unzipNIO(Path zipPath, final Path target) throws IOException {

        if(Files.notExists(target)) Files.createDirectories(target);

        try (FileSystem zipFS = FileSystems.newFileSystem(zipPath, null)) {
            final Path root = zipFS.getPath("/");
            try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(root)) {
                for (Path path : dirStream) {
                    Files.copy(path, target.resolve(path.getFileName().toString()), StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }
}
