package fr.wild.real;

import fr.wild.orchestra.Wild4Test;

import org.junit.Test;

public class SigFile_exportTest {
final Integer initSrid = null ; // SRID d'entrée
final Integer destSrid = 4326 ; // SRID de sortie
final Integer confValue = 1 ; // Valeur de configuration spécifique au fichier
final String inputPath = "c:/test/sig/TRONCON_HYDROGRAPHIQUE.shp" ; // Chemin du fichier d'entrée
final String outputPath = "c:/test/sig/out" ; // Chemin du fichier de sortie

   @Test
   public void SigFile_SHP() throws Exception {
	   final String fileRef = "sigFile";

	   Wild4Test wildModelTester = new Wild4Test();
	   wildModelTester.addObject(fileRef, "real", "WildShpFile", new Object[]{
			   inputPath,  destSrid, confValue
	   });
	   wildModelTester.executeMethod(fileRef, "exportShp", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportMifmid", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportGeojson", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportGml", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportKml", new Object[]{
			   outputPath,  destSrid
	   });

	   System.out.println(wildModelTester.getFromMethod(fileRef, "getGeojson", new Object[]{
			   0, 10
	   }));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getWkt", new Object[]{
			   0, 10
	   }));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getGeojson", new Object[]{10}));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getWkt", new Object[]{10}));
    }
   @Test
   public void SigFile_MIFMID() throws Exception {
	   final String fileRef = "sigFile";

	   Wild4Test wildModelTester = new Wild4Test();
	   wildModelTester.addObject(fileRef, "real", "WildMifMidFile", new Object[]{
			   inputPath,  destSrid, confValue
	   });
	   wildModelTester.executeMethod(fileRef, "exportShp", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportMifmid", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportGeojson", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportGml", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportKml", new Object[]{
			   outputPath,  destSrid
	   });
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getGeojson", new Object[]{
			   0,10
	   }));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getWkt", new Object[]{
			   0,10
	   }));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getGeojson", new Object[]{10}));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getWkt", new Object[]{10}));
   }
   @Test
   public void SigFile_GML() throws Exception {
	   final String fileRef = "sigFile";

	   Wild4Test wildModelTester = new Wild4Test();
	   wildModelTester.addObject(fileRef, "real", "WildGmlFile", new Object[]{
			   inputPath,  destSrid, confValue
	   });
	   wildModelTester.executeMethod(fileRef, "exportShp", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportMifmid", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportGeojson", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportGml", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportKml", new Object[]{
			   outputPath,  destSrid
	   });
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getGeojson", new Object[]{
			   0, 10
	   }));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getWkt", new Object[]{
			   0, 10
	   }));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getGeojson", new Object[]{10}));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getWkt", new Object[]{10}));
   }
   @Test
   public void SigFile_KML() throws Exception {
	   final String fileRef = "sigFile";

	   Wild4Test wildModelTester = new Wild4Test();
	   wildModelTester.addObject(fileRef, "real", "WildKmlFile", new Object[]{
			   inputPath,  destSrid, confValue
	   });
	   wildModelTester.executeMethod(fileRef, "exportShp", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportMifmid", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportGeojson", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportGml", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportKml", new Object[]{
			   outputPath,  destSrid
	   });
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getGeojson", new Object[]{
			   0, 10
	   }));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getWkt", new Object[]{
			   0, 10
	   }));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getGeojson", new Object[]{10}));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getWkt", new Object[]{10}));
   }
   @Test
   public void SigFile_GJ() throws Exception {
	   final String fileRef = "sigFile";

	   Wild4Test wildModelTester = new Wild4Test();
	   wildModelTester.addObject(fileRef, "real", "WildGeoJsonFile", new Object[]{
			   inputPath,  destSrid, confValue
	   });
	   wildModelTester.executeMethod(fileRef, "exportShp", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportMifmid", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportGeojson", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportGml", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportKml", new Object[]{
			   outputPath,  destSrid
	   });
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getGeojson", new Object[]{
			   0,10
	   }));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getWkt", new Object[]{
			   0,10
	   }));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getGeojson", new Object[]{10}));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getWkt", new Object[]{10}));
   }
   @Test
   public void SigFile_Full() throws Exception {
	   final String fileRef = "sigFile";
// Cas où le format est déterminé automatiquement
	   Wild4Test wildModelTester = new Wild4Test();
	   wildModelTester.addObject(fileRef, "real", "WildSigFile", new Object[]{
			   inputPath, destSrid, confValue
	   });
	   wildModelTester.executeMethod(fileRef, "exportShp", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportMifmid", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportGeojson", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportGml", new Object[]{
			   outputPath,  destSrid
	   });
	   wildModelTester.executeMethod(fileRef, "exportKml", new Object[]{
			   outputPath,  destSrid
	   });
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getGeojson", new Object[]{
			   0, 10
	   }));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getWkt", new Object[]{
			   0, 10
	   }));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getGeojson", new Object[]{10}));
	   System.out.println(wildModelTester.getFromMethod(fileRef, "getWkt", new Object[]{10}));
   }
}
