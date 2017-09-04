/*
 *  ONEMA Dice project.
 *  Copyright (C) 2016 ONEMA
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this project. If not, see http://www.gnu.org/licenses.
 */
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import com.fasterxml.jackson.core.JsonEncoding;
import com.vividsolutions.jts.geom.Geometry;
import fr.wild.orchestra.WildObject;

// Dépendances, bibliothèques JAVA par exemple.
import java.util.HashMap;
import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.File;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemAlreadyExistsException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import javax.naming.NamingException;

import org.opengis.feature.AttributeType;
import org.opengis.filter.Filter;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.spatial.BBOX;
import org.opengis.geometry.Envelope;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.util.FactoryException;
import org.opengis.util.GenericName;

import org.apache.sis.geometry.Envelopes;
import org.apache.sis.internal.system.DataDirectory;
import org.apache.sis.internal.system.DefaultFactories;
import org.apache.sis.internal.util.Constants;
import org.apache.sis.referencing.CRS;
import org.apache.sis.referencing.IdentifiedObjects;
import org.apache.sis.referencing.crs.AbstractCRS;
import org.apache.sis.referencing.cs.AxesConvention;
import org.apache.sis.referencing.factory.GeodeticObjectFactory;
import org.apache.sis.referencing.operation.CoordinateOperationContext;
import org.apache.sis.referencing.operation.CoordinateOperationFinder;
import org.apache.sis.referencing.operation.DefaultCoordinateOperationFactory;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.util.ArgumentChecks;
import org.apache.sis.util.ArraysExt;
import org.apache.sis.util.ObjectConverters;
import org.apache.sis.util.Utilities;
import org.apache.sis.util.collection.Cache;

import org.geotoolkit.data.AbstractFeatureStore;
import org.geotoolkit.data.AbstractFileFeatureStoreFactory;
import org.geotoolkit.data.FeatureCollection;
import org.geotoolkit.data.FeatureIterator;
import org.geotoolkit.data.FeatureStore;
import org.geotoolkit.data.FeatureStoreUtilities;
import org.geotoolkit.data.geojson.GeoJSONFeatureStoreFactory;
import org.geotoolkit.data.geojson.GeoJSONStreamWriter;
import org.geotoolkit.data.gml.GMLFeatureStoreFactory;
//import org.geotoolkit.data.kml.KmzContextInterpreter;
import org.geotoolkit.data.kml2.KMLFeatureStoreFactory;
import org.geotoolkit.data.mapinfo.mif.MIFFeatureStoreFactory;
import org.geotoolkit.data.memory.GenericMappingFeatureCollection;
import org.geotoolkit.data.memory.mapping.FeatureMapper;
import org.geotoolkit.data.query.Query;
import org.geotoolkit.data.query.QueryBuilder;
import org.geotoolkit.data.shapefile.ShapefileFeatureStoreFactory;

import org.geotoolkit.factory.FactoryFinder;

import org.geotoolkit.feature.Feature;
import org.geotoolkit.feature.FeatureTypeBuilder;
import org.geotoolkit.feature.FeatureUtilities;
import org.geotoolkit.feature.Property;
import org.geotoolkit.feature.type.ComplexType;
import org.geotoolkit.feature.type.FeatureType;
import org.geotoolkit.feature.type.GeometryType;
import org.geotoolkit.feature.type.PropertyDescriptor;
import org.geotoolkit.feature.type.PropertyType;
import org.geotoolkit.feature.xml.jaxp.JAXPStreamFeatureWriter;
import org.geotoolkit.lang.Setup;

import org.geotoolkit.storage.DataStore;
import org.geotoolkit.storage.DataStoreFactory;
import org.geotoolkit.storage.DataStores;
import org.geotoolkit.storage.FactoryMetadata;

import org.geotoolkit.util.NamesExt;
import org.opengis.referencing.AuthorityFactory;
import org.opengis.referencing.crs.CRSFactory;
import org.opengis.referencing.crs.DerivedCRS;
import org.opengis.referencing.crs.ProjectedCRS;
import org.opengis.referencing.crs.SingleCRS;
import org.opengis.referencing.operation.CoordinateOperation;
import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
import org.opengis.referencing.operation.CoordinateOperationFactory;
import org.opengis.referencing.operation.OperationNotFoundException;
import org.opengis.referencing.operation.SingleOperation;

/**
 * Code généré automatiquement par l'outil Wild
 * Fichier contenant des données géographiques / géométriques et possiblement ses attributs
 */
public class WildSigFile extends WildFile {

        /**
         * {@link Integer} flag to define export with default CRS axes convention
         * and intersection with destination CRS domain of validity.
         * @see #DEV_createQuery(org.geotoolkit.feature.type.FeatureType, java.lang.Integer, java.lang.Integer)
         */
        public static int DEFAULT_AXES_CONVENTION_WITH_DOMAINOFVALIDY = 0;

        /**
         * {@link Integer} flag to define export with default CRS axes convention
         * and <strong>disable</strong> intersection with destination CRS domain of validity.
         * @see #DEV_createQuery(org.geotoolkit.feature.type.FeatureType, java.lang.Integer, java.lang.Integer)
         */
        public static int DEFAULT_AXES_CONVENTION_WITHOUT_DOMAINOFVALIDY = 1;

        /**
         * {@link Integer} flag to define export with <strong>forced longitude latitude CRS axis order</strong>
         * as its possible and intersection with destination CRS domain of validity.
         * @see #DEV_createQuery(org.geotoolkit.feature.type.FeatureType, java.lang.Integer, java.lang.Integer)
         */
        public static int FORCED_LONG_LAT_WITH_DOMAINOFVALIDY = 2;

        /**
         * {@link Integer} flag to define export with <strong>forced longitude latitude CRS axis order</strong>
         * as its possible and <strong>disable</strong> intersection with destination CRS domain of validity.
         * @see #DEV_createQuery(org.geotoolkit.feature.type.FeatureType, java.lang.Integer, java.lang.Integer)
         */
        public static int FORCED_LONG_LAT_WITHOUT_DOMAINOFVALIDY = 3;

	// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
		protected WildSigFile(){}

	// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
		protected String crsAsWkt; // Référentiel de projection de coordonnées, en WKT
		protected String repWork; // Chemin relatif de l'ensemble des fichiers de travail
		protected String subWork; // Nom SHP commun à l'ensemble des fichiers de travail
		protected HashMap<Integer, Geometry> geometryList; // Liste de tous les objets contenus
		protected HashMap<Integer, HashMap<String, Object>> attributeList; // Carte des attributs des objets contenus dans le shape
		protected HashMap<String, String> typeList; // Carte des attributs des objets contenus dans le shape

	// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
		protected String pathToFile;	//Chemin vers le fichier{}
		protected Integer srid;	//Système de référence géographique{}
		protected Integer confmodel;	//Configurations spécifiques au fichier{}

        //-- feature initialization
        protected FeatureType inputFeatureType;
        protected FeatureStore featureReader;
        private String sourceFeatureTypeName;

        /**
         * CRS use to getwkt and getgeojson string output.
         * wgs84.
         *
         * @see #getWkt(java.lang.Integer)
         * @see #getGeojson(java.lang.Integer)
         */
        private static final Integer GETWKTGEOJSONCRS = 4326;

        //-- Static attribut needed to add RGF93 grid
        private static final FilterFactory2 FF = (FilterFactory2) FactoryFinder.getFilterFactory(null);
        public static final String NAME = "wild";
        private static final Path CONFIGURATION_PATH;
            static {
                Path tmpPath = Paths.get(System.getProperty("user.home"), "."+NAME);
                if (!Files.isDirectory(tmpPath)) {
                    try {
                        Files.createDirectory(tmpPath);
                    } catch (IOException ex) {
                        try {
                            tmpPath = Files.createTempDirectory(NAME);
                        } catch (IOException ex1) {
                            ex.addSuppressed(ex1);
                            throw new ExceptionInInitializerError(ex);
                        }
                    }
                }
                CONFIGURATION_PATH = tmpPath;
            }
        private static final Path EPSG_PATH = CONFIGURATION_PATH.resolve("EPSG");
        private static final String RGF93_RESOURCE = "/fr/wild/rgf93/gr3df97a.txt";
        private static final Path RGF93_PATH = CONFIGURATION_PATH.resolve("rgf93").resolve("gr3df97a.txt");

// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***

     // CE CONSTRUCTEUR DOIT ETRE MODIFIE.
        /**
         * Code généré automatiquement par l'outil Wild
         * Fonction d'initialisation, commune à tous les constructeurs.
         * "Constructeur unique"
         * NB. i_WILD_dObject est nécessairement passé
         *
         * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
         * @param i_pathToFile	Chemin vers le fichier{}
         * @param i_srid	Système de référence géographique{}
         * @param i_confmodel	Configurations spécifiques au fichier{}
         */
        protected void WILD_initialize_WildSigFile(
        	WildObject i_WILD_dObject,
        	String i_pathToFile,
        	Integer i_srid,
        	Integer i_confmodel
        ) throws Exception {

//        	// Amorce de la classe
        	// Initialisation de la classe d'objet selon le schéma Wild
        	WILD_dObject = i_WILD_dObject ;
        	WILD_initialize_WildFile(i_WILD_dObject,i_pathToFile,"UTF-8",null,null,null);
        	// Préparation des variables d'invocation (considérées comme champs globaux)
        	this.pathToFile = i_pathToFile;
        	this.srid = i_srid;
        	this.confmodel = i_confmodel;
System.out.println("!!! NEW PARAMETER !!! value :"+this.confmodel);
//	// Mode try de récupération des erreurs pour log
	try{

        {
//      // *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
            //-- get file name extension
            final int lastIndexOf  = i_pathToFile.lastIndexOf(".");
            final String extension = i_pathToFile.substring(lastIndexOf+1).toLowerCase();
//	    // *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

            switch (extension) {
                case "shp" : {
                    sourceFeatureTypeName = ShapefileFeatureStoreFactory.NAME;
                    break;
                }
                case "mif" : {
                    sourceFeatureTypeName = MIFFeatureStoreFactory.NAME;
                    break;
                }
                case "gml" : {
                    sourceFeatureTypeName = GMLFeatureStoreFactory.NAME;
                    break;
                }
                case "geojson" :
                case "json"    : {
                    sourceFeatureTypeName = GeoJSONFeatureStoreFactory.NAME;
                    break;
                }
                case "kml" : {
                    sourceFeatureTypeName = KMLFeatureStoreFactory.NAME;
                    break;
                }
                default : throw new IllegalArgumentException("File with the following extension is not supported. Given file extension : "+extension
                +"The supported extension are : shp, mif, gml, geojson.");
            }
            DEV_initImportFeatureStore(sourceFeatureTypeName, i_pathToFile);
        }
	} catch(Exception e){
		this.WILD_logException(e);
	}
}


     // CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
     /**
     * Code généré automatiquement par l'outil Wild
     * Constructeur, appelle nécessairement WILD_initialize()
     * NB. i_WILD_dObject est nécessairement passé
     *
     * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
     * @param i_pathToFile	Chemin vers le fichier{}
     * @param i_srid	Système de référence géographique{}
     * @param i_confmodel	Configurations spécifiques au fichier{}
     */

     public WildSigFile(
      	WildObject i_WILD_dObject,
      	String i_pathToFile,
      	Integer i_srid,
      	Integer i_confmodel
      ) throws Exception{
      	if(srid==null)srid=4326;
      	if(confmodel==null)confmodel=0;
      	this.WILD_initialize_WildSigFile(i_WILD_dObject,i_pathToFile,(i_srid == null) ? 4326 : i_srid,(i_confmodel == null) ? 0 : i_confmodel);
      }

     // CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
     /**
     * Code généré automatiquement par l'outil Wild
     * Constructeur, appelle nécessairement WILD_initialize()
     * NB. i_WILD_dObject est nécessairement passé
     *
     * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
     * @param i_pathToFile	Chemin vers le fichier{}
     */

      public WildSigFile(
      	WildObject i_WILD_dObject,
      	String i_pathToFile
      ) throws Exception{
      	this.WILD_initialize_WildSigFile(i_WILD_dObject,i_pathToFile,4326,0);
      }

//CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
/**
* Code généré automatiquement par l'outil Wild
* Constructeur, appelle nécessairement WILD_initialize()
* NB. i_WILD_dObject est nécessairement passé
*
* @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
* @param i_pathToFile	Chemin vers le fichier{}
* @param i_srid	Système de référence géographique{}
*/
public WildSigFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	Integer i_srid
) throws Exception{
	this.WILD_initialize_WildSigFile(i_WILD_dObject,i_pathToFile,(i_srid == null) ? 4326 : i_srid,0);
}

//-- nouvelle methodes privées
/**
 * Create an appropriate output path from input path and first output path part and the expected extension.<br><br>
 *
 * For example :<br>
 * if <strong>input path </strong> = mypath/mydata.example <br>
 * <strong>outputPath</strong> = outPath/ <br>
 * and <strong>extension</strong> = shp.<br>
 * The given <strong>output</strong> will be : outPath/mydata.shp
 *
 * @param inputPath Path from data.
 * @param outputPathPart first part of output path.
 * @param outputExtension expected file extension.
 * @return output path composed by given path informations.
 */
private URI DEV_createOutPutURI(final String inputPath, final String outputPathPart, final String outputExtension)
        throws IOException {
    final Path in       = Paths.get(new File(inputPath).toURI());
    final Path out      = Paths.get(new File(outputPathPart).toURI());

    //-- create folders if doesn't exist
    if (!Files.exists(out))
        Files.createDirectories(out);

    //-- if path is not a directory, means assume that user as already set an appropriate Path.
    //-- if path is not conform FeatureStore will throw exception later.
    final boolean isDir = Files.isDirectory(out);
    if (!isDir) return out.toUri();

    //-- get the filename from the input
    final String fileName = in.getFileName().toString();
    final String[] split = fileName.split(Pattern.quote("."));

    final String currentExtension = split[split.length - 1].trim();

    //-- if extension match means output path is conform
    if (currentExtension.equalsIgnoreCase(outputExtension.trim()))
        return out.resolve(fileName).toUri();

    //-- create a new appropriate filename from input and given extension
    final StringBuilder strBuild = new StringBuilder(split[0]);
    strBuild.append(".");
    for (int s = 1; s < split.length - 1; s++) {
        strBuild.append(split[s]);
    }
    strBuild.append(outputExtension);

    return out.resolve(strBuild.toString()).toUri();
}

/**
 * Returns a created {@link Query} from given CRS code, index of requested feature and internaly CRS domain of validity.
 *
 * @param srid CRS code to reprojected features for query. May be {@code null}. If {@code null} all features will be requested.
 * @param featureIndex index of the selected {@link Feature}.
 *
 * @return created {@link Query}.
 * @throws FactoryException
 */
private Query DEV_createQuery(final FeatureType inputFeatureType, final Integer srid, final Integer featureIndex)
        throws Exception {

    final QueryBuilder queryBuilder = new QueryBuilder();
    queryBuilder.setTypeName(inputFeatureType.getName());

    //-- srid CRS if exist
    CoordinateReferenceSystem crs = null;
    if (srid != null) crs = CRS.forCode("epsg:"+srid);

    //-- domain of validity if exist
    Envelope domainOfValidity = null;
    if (crs != null) {
        WILD_echo("--------------------------------------------------------------------------------------------------------------------------------------------");
        WILD_echo("Export features into following destination CRS : \n"+crs.toString());
        WILD_echo("--------------------------------------------------------------------------------------------------------------------------------------------");

        //-- adapted flag behavior
        //-- requested axes conventionnal comportement for case 2 and 3
        if (((confmodel & 2) == 2))
            crs = convertCRSAxisConvention(crs, AxesConvention.CONVENTIONALLY_ORIENTED);

        queryBuilder.setCRS(crs);

        //-- requested domain of validity for case 0 and 2
        domainOfValidity = ((confmodel & 1) != 1) ? CRS.getDomainOfValidity(crs) : null;
    }

    //-- create appropriate filter to select only none complex Geometry type which will be writen
    //-- and filter to select only feature which intersect domain of validity
    Filter combined = null;
    ArrayList<GenericName> fields = new ArrayList<GenericName>();
    for (PropertyDescriptor descriptor : inputFeatureType.getDescriptors()) {
        PropertyType type = descriptor.getType();
        if ((type instanceof AttributeType)
         && !(type instanceof ComplexType)) {
            fields.add(descriptor.getName());

            //-- BBox filter creation if domainOfValidity not null.
            if (domainOfValidity != null
             && (type instanceof GeometryType)) {
                try {
                    final BBOX bbox = FF.bbox(FF.property(descriptor.getName()),
                                                          Envelopes.transform(domainOfValidity, ((GeometryType)type).getCoordinateReferenceSystem()));
                    combined = (combined == null) ? bbox : FF.or(combined, bbox);
                } catch (Throwable ex) {
                	ex.printStackTrace();
                    //-- assume no filters
                    //-- pass to the next descriptor
                }
            }
        }
    }

    //-- add supported feature properties
    queryBuilder.setProperties(fields.toArray(new GenericName[fields.size()]));

    //-- get the selected ith elt
    if (featureIndex != null) {
        queryBuilder.setStartIndex(featureIndex);
        WILD_echo("--------------------------------------------------------------------------------------------------------------------------------------------");
        WILD_echo("Begin export features at "+featureIndex+" index.");
    }

    if (domainOfValidity != null) {
    	 WILD_echo("--------------------------------------------------------------------------------------------------------------------------------------------");
         WILD_echo("Export geometry features witch intersects following destination domain of validity : \n "+domainOfValidity.toString());
    }

    if (combined != null)
        queryBuilder.setFilter(combined);

    return queryBuilder.buildQuery();
}

/**
 * Read data from {@link #featureReader} into CRS define by {@link #srid} code
 * and export datas into {@code outStoreType} type, into destination CRS define
 * by {@code destCRSCode} at {@code exportPath} path.
 *
 * @param storeType type of internaly {@link DataStore} to export data.
 * @param destCRSCode {@link CoordinateReferenceSystem} of output exported data.
 * @param exportPath path where data will be stored.
 * @throws DataStoreException if problem during reading/writing
 * @throws FactoryException if problem during store creation.
 */
private void DEV_exportFeatures(final String outStoreType, final Integer destCRSCode, final URI exportPath)
        throws Exception {

    ArgumentChecks.ensureNonNull("outStoreType", outStoreType);
    ArgumentChecks.ensureNonNull("exportPath", exportPath);

    WILD_echo("/***********************************************************************************************************************/");
    DEV_LogInfoSourceData(outStoreType);
    //-- lecture
    FeatureCollection readCollection;

    {
        //-- create read query
        final Query readQuery = DEV_createQuery(inputFeatureType, destCRSCode, null);
        readCollection = featureReader.createSession(false).getFeatureCollection(readQuery);
        DEV_LogInfoFeatureCollection(readCollection, exportPath);
    }

    //-- ecriture
    {
        //-- initialize needed attributs
        final DataStoreFactory outFactory                                   = DataStores.getFactoryById(outStoreType);
        final FactoryMetadata metadata                                      = outFactory.getMetadata();
        final Class<org.opengis.geometry.Geometry>[] supportedGeometryTypes = metadata.supportedGeometryTypes();
        FeatureType featureTyp                                              = readCollection.getFeatureType();

        //-- get fileName of output file (use with multiple feature collections and geojson cases)
        URI outURI = exportPath;
        final Path path = Paths.get(exportPath);

        //-- get the filename from the output path
        final String originFileName = path.getFileName().toString();
        int lastIndexOf             = originFileName.lastIndexOf(".");
        final String fileName       = originFileName.substring(0, lastIndexOf);
        final String extension      = originFileName.substring(lastIndexOf);

        //-- decompose feature collection from theirs internaly featureTypes (shape support only one kind of geometry)
        final FeatureCollection[] cols = (ArraysExt.contains(supportedGeometryTypes,featureTyp.getGeometryDescriptor().getType().getBinding()))
                                        ? new FeatureCollection[]{readCollection}
                                        : FeatureStoreUtilities.decomposeByGeometryType(readCollection, supportedGeometryTypes);

        //-- for each decomposed featureCollection.
        for (FeatureCollection col : cols) {

            if (col.isEmpty()) continue;

            //-- current collection attributs
            FeatureType colFeatureType = col.getFeatureType();
            GenericName colGenericName = colFeatureType.getName();
            final String inTypeName    = colGenericName.tip().toString();

            String colFileName = fileName;

            if (cols.length > 1) {
                //output file path
                colFileName = fileName+"_"+inTypeName;
                outURI = path.getParent().resolve(colFileName+extension).toUri();
            }

            try (AbstractFeatureStore exportFeatureStore = (AbstractFeatureStore) outFactory.create(
                    Collections.singletonMap(AbstractFileFeatureStoreFactory.PATH.getName().getCode(), outURI))) {

                //-- check if featuretype is conform
                if (!DEV_isConform(colFeatureType)) {
                    //-- create a wrapped feature collection if not conform
                    col = DEV_ConvertCollection(col);
                    colFeatureType = col.getFeatureType();
                    colGenericName = colFeatureType.getName();
                }

                //-- do a particularity case about geojson
                //-- into geojson case feature type name must be the same than the file name.
                {
                    if (outStoreType.equalsIgnoreCase("geojson")) {
                        colGenericName = NamesExt.create(colFileName);
                        final FeatureTypeBuilder builder = new FeatureTypeBuilder();
                        builder.copy(col.getFeatureType());
                        builder.setName(colGenericName);
                        colFeatureType = builder.buildFeatureType();
                    }
                }

                exportFeatureStore.createFeatureType(colGenericName, colFeatureType);
                exportFeatureStore.addFeatures(colGenericName, col);
            }
        }
    }
    WILD_echo("/**********************************************************************************************************************/");
}

/**
 * Returns a wrapped {@link FeatureCollection} which transform on the fly all
 * internal {@link Feature} into {@link FeatureType} which will be written by {@link DataStore}.
 *
 * @param sourceCollection collection which contain un-supported {@link FeatureType}.
 * @return supported wrapped featureCollection.
 */
private FeatureCollection DEV_ConvertCollection(final FeatureCollection sourceCollection) {
    return new GenericMappingFeatureCollection(sourceCollection, new FeatureMapp(sourceCollection.getFeatureType()));
}

/**
 * Returns {@code true} if given type from {@link FeatureType} is supported by feature writer, else {@code false}.<br><br>
 * Supported types are : <br>
 * - boolean<br>
 * - primitives (int, float ...)<br>
 * - Number<br>
 * - Geometry <br>
 * - String<br>
 *
 * @param type given type.
 * @return true if type will be written.
 */
private boolean DEV_TypeIsSupported(Class<?> type) {
    return (Boolean.class.isAssignableFrom(type)
         || type.isPrimitive()
         || Number.class.isAssignableFrom(type)
         || Geometry.class.isAssignableFrom(type)
         || String.class.isAssignableFrom(type));
}

/**
 * Returns {@code true} if all internaly {@link PropertyDescriptor} from given
 * {@link FeatureType} will be supported during writing {@link Feature} elsereturn {@code false}.
 *
 * @param featureType source FeatureType which will be analysed.
 * @return true if this Feature will be supported in during writing features else false.
 */
private boolean DEV_isConform(final FeatureType featureType) {
    for (final PropertyDescriptor propDescript : featureType.getDescriptors()) {
        final PropertyType propType = propDescript.getType();
        final Class<?> binding = propType.getBinding();
        if (!DEV_TypeIsSupported(binding)) return false;
    }
    return true;
}

/**
 * Create a {@link FeatureStore} from store type as geojson, mifmid, shapefile etc,
 * and a path to read or export datas.
 *
 * @param inputStoreType type of generate store.
 * @param dataPath path to stored data.
 * @return generate {@link AbstractFeatureStore}.
 * @throws DataStoreException if problem during store creation.
 */
private AbstractFeatureStore DEV_createFeatureStore(final String inputStoreType, final URI dataPath)
        throws DataStoreException {
    final DataStore store = DataStores.getFactoryById(inputStoreType)
                            .create(Collections.singletonMap(AbstractFileFeatureStoreFactory.PATH.getName().getCode(),
                                                             dataPath));
    if (!(store instanceof AbstractFeatureStore))
                throw new DataStoreException("The requested store from type : "+inputStoreType
                        +" is not instance of AbstractFeatureStore. Impossible to export datas.");
    return (AbstractFeatureStore) store;
}

/**
 * Initialize, expected {@link #featureReader} and {@link #inputFeatureType}
 * from {@code inputStoreType} and {@code dataPath}.
 *
 * @param inputStoreType store type for store in reading action.
 * @param dataPath path to stored read data.
 * @throws DataStoreException if problem during store creation.
 */
private void DEV_initImportFeatureStore(final String inputStoreType, final String dataPath)
        throws DataStoreException {
    //-- init store
    featureReader = DEV_createFeatureStore(inputStoreType, new File(dataPath).toURI());
    final Set<GenericName> names = featureReader.getNames();
    if (names == null || names.isEmpty())
        throw new DataStoreException("Names of featureReader should not be empty or null.");

    //-- init feature type
    inputFeatureType = featureReader.getFeatureType(featureReader.getNames().iterator().next());//-- take first element.
}

/**
 * Log informations about source dataswill be exports.<br>
 * Describe source format and destination formats.<br>
 * Source path data location.<br>
 * Feature type of source data.<br>
 *
 * @param outStoreType output export type, into string format.
 */
private void DEV_LogInfoSourceData(final String outStoreType) {
    WILD_echo("Begin Export from "+sourceFeatureTypeName.toUpperCase()+" to "+outStoreType.toUpperCase());
    WILD_echo("-------------------------------------------------------------------------------------------------------------------------");
    WILD_echo("Export source datas from location : "+pathToFile);
    WILD_echo("-------------------------------------------------------------------------------------------------------------------------");
    WILD_echo("Source data feature type is describe as follow : \n"+inputFeatureType.toString());
    WILD_echo("-------------------------------------------------------------------------------------------------------------------------");
}

/**
 * Log informations about feature collections and destination export path.
 *
 * @param col feature collection.
 * @param exportPath destination export path.
 */
private void DEV_LogInfoFeatureCollection(final FeatureCollection col, final URI exportPath) {
    WILD_echo("-------------------------------------------------------------------------------------------------------------------------");
    if (col.isEmpty()) {
        WILD_echo(" !!! Feature collection result is empty no features will be written. "
                + "No features match with export properties. "
                + "Moreover no output created file. !!!".toUpperCase());
    } else {
        final int nbWrittenFeatures = col.size();
        WILD_echo("A sum of "+nbWrittenFeatures+" features are exported at path location: "+exportPath.toString());
    }
}

/**
 * Convert given {@link CoordinateReferenceSystem} into other CRS with same
 * properties but with axes organize by given {@link AxesConvention}.
 *
 * @param crs will be converted.
 * @param axesConvention choosen {@link AxesConvention}.
 * @return new CRS with choosen {@link AxesConvention}.
 */
private CoordinateReferenceSystem convertCRSAxisConvention(final CoordinateReferenceSystem crs,
                                                           final AxesConvention axesConvention) {
    return AbstractCRS.castOrCopy(crs).forConvention(axesConvention);
}

/*	### NOUVELLE METHODE ###
	Méthode : compileMetadatas - Récupère les métadonnées spécifiques à l'IG (CRS, Type, attributs…){
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupère les métadonnées spécifiques à l'IG (CRS, Type, attributs…){
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 *
 */
public void compileMetadatas ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération de la variable
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"compileMetadatas_output1" : Map des métadonnées IG (HashMap<String, Object>)
	//	this.WILD_setOutput("compileMetadatas_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : checkCrsFromRef - Vérification de la validité du système de projection{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Vérification de la validité du système de projection{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 *
 * @param i_crsToCheck	Définition du CRS de comparaison{}
 * @return	{}
 */
public Boolean checkCrsFromRef (
	String i_crsToCheck
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Boolean WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Opérateur de comparaison
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"checkCrsFromRef_output1" : Liste des erreurs rencontrées (List<String>)
	//	this.WILD_setOutput("checkCrsFromRef_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkCrsFromRef_output2" : Il existe des éléments à retenir (Boolean)
	//	this.WILD_setOutput("checkCrsFromRef_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : checkType - Vérification de la validité du type d'enregistrements{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Vérification de la validité du type d'enregistrements{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 *
 * @param i_typeToType	Nom du type de comparaison{}
 * @return	{}
 */
public Boolean checkType (
	String i_typeToType
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Boolean WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Opérateur de comparaison
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"checkType_output1" : Liste des erreurs rencontrées (List<String>)
	//	this.WILD_setOutput("checkType_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkType_output2" : Il existe des éléments à retenir (Boolean)
	//	this.WILD_setOutput("checkType_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : checkAttribute_isWithin - Test de cohérence (tous les objets géographiques sont représentés dans le référentiel){
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Test de cohérence (tous les objets géographiques sont représentés dans le référentiel){
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 *
 * @param i_attributeToCheck	Nom de l'attribut à vérifier{}
 * @param i_listOfReferences	Eléments à comparer{}
 * @return	{}
 */
public String checkAttribute_isWithin (
	String i_attributeToCheck,
	List<Object> i_listOfReferences
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Construction des objets
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Comparaison des listes
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Compilation des résultats
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"checkAttribute_isWithin_output1" : Liste des erreurs rencontrées (List<String>)
	//	this.WILD_setOutput("checkAttribute_isWithin_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkAttribute_isWithin_output2" : Il existe des éléments à retenir (Boolean)
	//	this.WILD_setOutput("checkAttribute_isWithin_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkAttribute_isWithin_output3" : Chemin d'un fichier de sortie contenant la liste des erreurs (String)
	//	this.WILD_setOutput("checkAttribute_isWithin_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : checkAttribute_fullness - Test de complétude (tous les éléments du référentiel ont au moins un objet géographique){
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Test de complétude (tous les éléments du référentiel ont au moins un objet géographique){
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 *
 * @param i_attributeToCheck	Nom de l'attribut à vérifier{}
 * @param i_listOfReferences	Eléments à comparer{}
 * @return	{}
 */
public String checkAttribute_fullness (
	String i_attributeToCheck,
	List<Object> i_listOfReferences
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Construction des objets
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Comparaison des listes
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Compilation des résultats
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"checkAttribute_fullness_output1" : Liste des erreurs rencontrées (List<String>)
	//	this.WILD_setOutput("checkAttribute_fullness_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkAttribute_fullness_output2" : Il existe des éléments à retenir (Boolean)
	//	this.WILD_setOutput("checkAttribute_fullness_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkAttribute_fullness_output3" : Chemin d'un fichier de sortie contenant la liste des erreurs (String)
	//	this.WILD_setOutput("checkAttribute_fullness_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : checkValidity - Validation WKT des géométries transmises{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Validation WKT des géométries transmises{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 *
 * @return	{}
 */
public Boolean checkValidity ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Boolean WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Opérateur de comparaison
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"checkValidity_output1" : Liste des erreurs rencontrées (List<String>)
	//	this.WILD_setOutput("checkValidity_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkValidity_output2" : Il existe des éléments à retenir (Boolean)
	//	this.WILD_setOutput("checkValidity_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}


/*	### NOUVELLE METHODE ###
	Méthode : changeCrs - Méthode de transformation des géométries, pour le changmeent de SRID ("reprojection"){
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Méthode de transformation des géométries, pour le changmeent de SRID ("reprojection"){
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 *
 * @param i_destSrid	SRID de destination{}
 */
public void changeCrs (
	Integer i_destSrid
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Parcours des géométries avec éventuelle reprojection
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : findCrs - Méthode de détermination du CRS si non renseigné ; en cas d’absence de SRID le constructeur identifie s’il s’agit d’un système géographique ou projeté. Si géométrique, il tente le WGS84, si projeté, il tente le L93. En cas d’erreur, il mentionne un null pour la géométrie et passe à la géométrie suivante. Un output conforme WILD (récupérable par l’orchestra et auto-généré dans la structure de code fournie) renseigne sur la liste des erreurs rencontrées.{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Méthode de détermination du CRS si non renseigné ; en cas d’absence de SRID le constructeur identifie s’il s’agit d’un système géographique ou projeté. Si géométrique, il tente le WGS84, si projeté, il tente le L93. En cas d’erreur, il mentionne un null pour la géométrie et passe à la géométrie suivante. Un output conforme WILD (récupérable par l’orchestra et auto-généré dans la structure de code fournie) renseigne sur la liste des erreurs rencontrées.{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 *
 * @param i_knownSrid	SRID transmis par le constructeur{}
 * @return	{}
 */
public Integer findCrs (
	Integer i_knownSrid
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Test d'existence de l'argument d'appel
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Test si projeté ou géographique
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Affectation en fonction
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Compilation et sauvegarde des erreurs
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"findCrs_output1" : Liste des erreurs (List<Object[]>)
	//	this.WILD_setOutput("findCrs_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES

/**
 * Méthode de détermination du CRS si non renseigné ; en cas d’absence de SRID le constructeur identifie s’il s’agit d’un système géographique ou projeté. Si géométrique, il tente le WGS84, si projeté, il tente le L93. En cas d’erreur, il mentionne un null pour la géométrie et passe à la géométrie suivante. Un output conforme WILD (récupérable par l’orchestra et auto-généré dans la structure de code fournie) renseigne sur la liste des erreurs rencontrées.{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 *
 * @return	SRID déterminé par algorithme (Integer) {}
 */
 public Integer findCrs ()throws Exception{
return findCrs(null);
}

/*	### NOUVELLE METHODE ###
	Méthode : exportShp - Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 *
 * @param i_outputFilePath	Chemin du fichier de sortie{}
 * @param i_destSrid	SRID de destination{}
 * @return	{}
 */
public String exportShp (
	String i_outputFilePath,
	Integer i_destSrid
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
        final URI outputPath = DEV_createOutPutURI(pathToFile, i_outputFilePath, "shp");
        DEV_exportFeatures(ShapefileFeatureStoreFactory.NAME, i_destSrid, outputPath);

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Préparation des fichiers de flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Parcours des géométries avec éventuelle reprojection
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Fermeture des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Compilation et sauvegarde des erreurs
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"exportShp_output1" : Liste des erreurs (List<Object[]>)
	//	this.WILD_setOutput("exportShp_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES

/**
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 *
 * @param i_outputFilePath	Chemin du fichier de sortie {}
 * @return	Chemin de fichier produit (String) {}
 */
 public String exportShp (
	String i_outputFilePath
	)throws Exception{
return exportShp(i_outputFilePath,null);
}

/*	### NOUVELLE METHODE ###
	Méthode : exportGeojson - Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 *
 * @param i_outputFilePath	Chemin du fichier de sortie{}
 * @param i_destSrid	SRID de destination{}
 * @return	{}
 */
public String exportGeojson (
	String i_outputFilePath,
	Integer i_destSrid
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
        final URI outURI = DEV_createOutPutURI(pathToFile, i_outputFilePath, "geojson");
        DEV_exportFeatures(GeoJSONFeatureStoreFactory.NAME, i_destSrid, outURI);

//	//	//	Etape	"1" : poids relatif de 10, Préparation des fichiers de flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Parcours des géométries avec éventuelle reprojection
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Fermeture des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Compilation et sauvegarde des erreurs
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"exportGeojson_output1" : Liste des erreurs (List<Object[]>)
	//	this.WILD_setOutput("exportGeojson_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES

/**
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 *
 * @param i_outputFilePath	Chemin du fichier de sortie {}
 * @return	Chemin de fichier produit (String) {}
 */
 public String exportGeojson (
	String i_outputFilePath
	)throws Exception{
return exportGeojson(i_outputFilePath,null);
}

/*	### NOUVELLE METHODE ###
	Méthode : exportMifmid - Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 *
 * @param i_outputFilePath	Chemin du fichier de sortie{}
 * @param i_destSrid	SRID de destination{}
 * @return	{}
 */
public String exportMifmid (
	String i_outputFilePath,
	Integer i_destSrid
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
        final URI outURI = DEV_createOutPutURI(pathToFile, i_outputFilePath, "mif");
        DEV_exportFeatures(MIFFeatureStoreFactory.NAME, i_destSrid, outURI);

//	//	//	Etape	"1" : poids relatif de 10, Préparation des fichiers de flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Parcours des géométries avec éventuelle reprojection
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Fermeture des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Compilation et sauvegarde des erreurs
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"exportMifmid_output1" : Liste des erreurs (List<Object[]>)
	//	this.WILD_setOutput("exportMifmid_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES

/**
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 *
 * @param i_outputFilePath	Chemin du fichier de sortie {}
 * @return	Chemin de fichier produit (String) {}
 */
 public String exportMifmid (
	String i_outputFilePath
	)throws Exception{
return exportMifmid(i_outputFilePath,null);
}

/*	### NOUVELLE METHODE ###
	Méthode : exportGml - Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 *
 * @param i_outputFilePath	Chemin du fichier de sortie{}
 * @param i_destSrid	SRID de destination{}
 * @return	{}
 */
public String exportGml (
	String i_outputFilePath,
	Integer i_destSrid
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
        final URI outURI = DEV_createOutPutURI(pathToFile, i_outputFilePath, "gml");
        DEV_LogInfoSourceData("gml");
        //-- create read query
        final Query readQuery                  = DEV_createQuery(inputFeatureType, i_destSrid, null);
        final FeatureCollection readCollection = featureReader.createSession(false).getFeatureCollection(readQuery);
        DEV_LogInfoFeatureCollection(readCollection, outURI);
        final JAXPStreamFeatureWriter writer = new JAXPStreamFeatureWriter();
        writer.write(readCollection, Paths.get(outURI));

//	//	//	Etape	"1" : poids relatif de 10, Préparation des fichiers de flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Parcours des géométries avec éventuelle reprojection
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Fermeture des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Compilation et sauvegarde des erreurs
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"exportGml_output1" : Liste des erreurs (List<Object[]>)
	//	this.WILD_setOutput("exportGml_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES

/**
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 *
 * @param i_outputFilePath	Chemin du fichier de sortie {}
 * @return	Chemin de fichier produit (String) {}
 */
 public String exportGml (
	String i_outputFilePath
	)throws Exception{
return exportGml(i_outputFilePath,null);
}

/*	### NOUVELLE METHODE ###
	Méthode : exportKml - Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 *
 * @param i_outputFilePath	Chemin du fichier de sortie{}
 * @param i_destSrid	SRID de destination{}
 * @return	{}
 */
public String exportKml (
	String i_outputFilePath,
	Integer i_destSrid
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try {
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
//            { //-- not available yet
//                final URI outURI = DEV_createOutPutURI(pathToFile, i_outputFilePath, "kmz");
//
//                //-- create read query
//                final Query readQuery                  = DEV_createQuery(inputFeatureType, srid, null, null);
//                final FeatureCollection readCollection = featureReader.createSession(false).getFeatureCollection(readQuery);
//
//                final MapContext context  = MapBuilder.createContext();
//                context.items().add(MapBuilder.createFeatureLayer(readCollection));
//                KmzContextInterpreter kmzContextInterpreter = new KmzContextInterpreter();
//                kmzContextInterpreter.writeKmz(context, Paths.get(outURI));
//            }

//	//	//	Etape	"1" : poids relatif de 10, Préparation des fichiers de flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 10, Parcours des géométries avec éventuelle reprojection
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Fermeture des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Compilation et sauvegarde des erreurs
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"exportKml_output1" : Liste des erreurs (List<Object[]>)
	//	this.WILD_setOutput("exportKml_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES

/**
 * Ecriture d'un fichier depuis la colection de géométrie contenue par l'objet{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 *
 * @param i_outputFilePath	Chemin du fichier de sortie {}
 * @return	Chemin de fichier produit (String) {}
 */
 public String exportKml (
	String i_outputFilePath
	)throws Exception{
return exportKml(i_outputFilePath,null);
}


/*	### NOUVELLE METHODE ###
	Méthode : getWkt - Récupération de variable (getter),en cas d’absence de numéro de collection à extraire l’intégralité de la collection est produite.{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération de variable (getter),en cas d’absence de numéro de collection à extraire l’intégralité de la collection est produite.{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 *
 * @param i_numToGet	numéro de collection de la géométrie à récupérer{}
 * @param i_LengthToGet nombre de Features à recupérer
 * @return	{}
 */
public String getWkt (
	final Integer i_numToGet,
    final Integer i_LengthToGet
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération ou compilation de la variable
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

            {
                DEV_LogInfoSourceData("WKT");
                //-- create read query
                final Query query = DEV_createQuery(inputFeatureType, GETWKTGEOJSONCRS, i_numToGet);

                //-- get feature and write into output String
                try (final FeatureIterator iterator = featureReader.createSession(false).getFeatureCollection(query).iterator()) {
                    if (iterator.hasNext()) {
                        final StringBuilder strBuilder = new StringBuilder();
                        int nbf = 0;
                        while (iterator.hasNext()
                            && nbf++ < i_LengthToGet) {
                            final Geometry geom = (Geometry)iterator.next().getDefaultGeometryProperty().getValue();
                            strBuilder.append(geom.toText());
                        }
                        WILD_toReturn = strBuilder.toString();
                    }
                }
            }

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES

/**
 * Récupération de variable (getter),en cas d’absence de numéro de collection à extraire l’intégralité de la collection est produite.{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 *
 * @return	Géométrie sous forme de String WKT (String) {}
 */
 public String getWkt (final Integer i_LengthToGet)throws Exception{
return getWkt(0, i_LengthToGet);
}

/*	### NOUVELLE METHODE ###
	Méthode : getGeojson - Récupération de variable (getter), en cas d’absence de numéro de collection à extraire l’intégralité de la collection est produite.{
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération de variable (getter), en cas d’absence de numéro de collection à extraire l’intégralité de la collection est produite.{
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 *
 * @param i_numToGet	numéro de collection de la géométrie et des attributs à récupérer{}
 * @param i_LengthToGet nombre de Features à recupérer
 * @return	{}
 */
public String getGeojson (
	final Integer i_numToGet,
    final Integer i_LengthToGet
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Récupération ou compilation de la variable
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

            {
                DEV_LogInfoSourceData("Geojson");
                final Query query = DEV_createQuery(inputFeatureType, GETWKTGEOJSONCRS, i_numToGet);
                //-- get feature and write into output String
                try (final FeatureIterator iterator = featureReader.createSession(false).getFeatureCollection(query).iterator()) {
                    if (iterator.hasNext()) {
                        final StringBuilder strBuilder = new StringBuilder();
                        try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                            int nbF = 0;
                            while (iterator.hasNext()
                               && (nbF++ < i_LengthToGet)) {
                                baos.reset();
                                GeoJSONStreamWriter.writeSingleFeature(baos, iterator.next(), JsonEncoding.UTF8, 4, false);
                                strBuilder.append(baos.toString("UTF-8"));
                            }
                        }
                        WILD_toReturn = strBuilder.toString();
                    }
                }
            }
	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES

    /**
     * Récupération de variable (getter), en cas d’absence de numéro de collection à extraire l’intégralité de la collection est produite.{
     }
     * Code généré automatiquement par l'outil Wild
     * Méthode d'appel public non modifiable
     *
     * @return	GeoJSON sous forme de String WKT (String) {}
     */
     public String getGeojson (final Integer i_LengthToGet)throws Exception{
        return getGeojson(0, i_LengthToGet);
    }

    /**
     * Internaly class use to transform un-writable feature into writable feature.
     * Moreover convert also feature type.
     * Each descriptor from feature type which are declared un-writable, are converted into String descriptor.
     *
     * @see FeatureMapp#transform(org.geotoolkit.feature.Feature)
     */
    private class FeatureMapp implements FeatureMapper {

        final FeatureType ftsource;
        final FeatureType ftDest;

        /**
         * Create a {@link FeatureMapper} adapted to convert un-supported writting {@link Feature}.<br>
         *
         * Moreover this constructor travel all {@link PropertyDescriptor} from given source {@link FeatureType} and create a new {@linkplain #ftDest destination FeatureType}.<br>
         * All transformed Feature will be convert into this destination FeatureType.
         *
         * @param sourceFeatureType given source writting un-supported FeatureType.
         */
        FeatureMapp(final FeatureType sourceFeatureType) {
            this.ftsource = sourceFeatureType;
            FeatureTypeBuilder ftb = new FeatureTypeBuilder();
            ftb.setName(sourceFeatureType.getName());

            for (PropertyDescriptor descriptor : sourceFeatureType.getDescriptors()) {
                final PropertyType propType = descriptor.getType();
                final Class<?> binding = propType.getBinding();

                //-- case supported descriptor in writing
                if (DEV_TypeIsSupported(binding)) {
                    ftb.add(descriptor);
                } else {
                    //-- descriptor which will be convert into string
                    ftb.add(descriptor.getName(), String.class);
                }
            }
            ftDest = ftb.buildFeatureType();
        }

        /**
         * The source featureType of the data will be transform.
         *
         * @return datasource featureType.
         */
        @Override
        public FeatureType getSourceType() {
            return ftsource;
        }

        /**
         * The target featureType of the data in which one, data will be transform.
         *
         * @return target featureType
         */
        @Override
        public FeatureType getTargetType() {
            return ftDest;
        }

        /**
         * Transform given Feature with internaly {@linkplain #getSourceType() internaly source featureType}
         * into another {@linkplain #getTargetType() target featureType}.
         *
         * @param unsupportedFeature
         * @return
         */
        @Override
        public Feature transform(final Feature unsupportedFeature) {

            //-- create new feature type from input featureType ID and name.
            final Feature outFeat = FeatureUtilities.defaultFeature(ftDest, unsupportedFeature.getIdentifier().getID());

            //-- travel all Properties from input Feature and convert its value into String format as possible.
            for (Property inProp : unsupportedFeature.getProperties()) {
                final String currentPropName = inProp.getName().tip().toString();
                final Class<?> binding = inProp.getType().getBinding();

                //-- define if this current descriptor is supported by writing feature action.
                if (DEV_TypeIsSupported(binding)) {
                    outFeat.setPropertyValue(inProp.getName().tip().toString(), inProp.getValue());
                } else {
                    //-- value from property will be convert into String as possible.
                    final Object value = unsupportedFeature.getProperty(currentPropName).getValue();
                    if (binding.isArray()) {
                        final StringBuilder stringBuilder = new StringBuilder("[");
                        int tabLength = Array.getLength(value);
                        for (int t = 0; t < tabLength; t++) {
                            stringBuilder.append(String.valueOf(Array.get(value, t)));
                            if (t != tabLength -1) stringBuilder.append(",");
                        }
                        stringBuilder.append("]");

                        outFeat.setPropertyValue(currentPropName, stringBuilder.toString());
                    } else if (Collection.class.isAssignableFrom(binding)) {
                        outFeat.setPropertyValue(currentPropName,
                                                 Arrays.toString(((Collection) value).toArray()));
                    } else {
                        //-- try to convert in String as possible
                        String result = null;
                        try {
                            result = ObjectConverters.convert(value, String.class);
                        } catch (Exception ex) {
                            result = String.valueOf(value);
                        }
                        outFeat.setPropertyValue(currentPropName, result);
                    }
                }
            }
            return outFeat;
        }
    }

    /**
     * Initialise la base EPSG et la grille NTV2 utilisée par l'application. Si
     * elles n'existent pas, elles seront créées. Dans tous les cas, on force le
     * chargement de la base EPSG dans le système, ce qui permet de lever les
     * potentiels problèmes au démarrage.
     * Si la création de la bdd EPSG rate, on renvoie une exception, car aucun
     * réferencement spatial ne peut être effecctué sans elle. En revanche, la
     * grille NTV2 n'est utile que pour des besoins de précision. Si son installation
     * rate, on n'afficche juste un message d'avertissement.
     * @throws FactoryException Si aucun driver n'est trouvé pour la connection
     * à la base de données EPSG.
     * @throws IOException Si une erreur survient pendant la création / connexion
     * à la base de données.
     * @throws javax.naming.NamingException Si il est impossible d'enregistrer
     * la bdd EPSG auprès de JNDI.
     */
    public static void DEV_InitEpsgDB()
            throws FactoryException, IOException, NamingException {
        // create a database in user directory
        Files.createDirectories(EPSG_PATH);
        Setup.initialize(null);

        // On tente d'installer la grille NTV2 pour améliorer la précision du géo-réferencement.
        try {
            if (!Files.exists(RGF93_PATH)) {
                Files.createDirectories(RGF93_PATH.getParent());

                final URI ntv2URI = WildSigFile.class.getResource(RGF93_RESOURCE).toURI();
                final Path gridPath;
                FileSystem jarFS = null;
                if (ntv2URI.getScheme().equalsIgnoreCase("file")) {
                    gridPath = Paths.get(ntv2URI);
                } else if (ntv2URI.getScheme().equalsIgnoreCase("jar")) {
                    try {
                        jarFS = FileSystems.newFileSystem(ntv2URI, Collections.EMPTY_MAP);
                    } catch (FileSystemAlreadyExistsException e) {
                        jarFS = FileSystems.getFileSystem(ntv2URI);
                    }
                    gridPath = jarFS.getPath(RGF93_RESOURCE);
                } else {
                    throw new IOException("Unknown resource scheme.");
                }

                Files.copy(gridPath, RGF93_PATH);
                if (jarFS !=null) jarFS.close();
            }
            Field dirField = DataDirectory.class.getDeclaredField("directory");
            dirField.setAccessible(true);
            dirField.set(DataDirectory.DATUM_CHANGES, RGF93_PATH.getParent());
        } catch (Exception ex) {
            throw new IllegalArgumentException("La grille de transformation RGF93 ne peut être installée. Des erreurs de reprojection pourraient apparaître au sein de l'application.", ex);
        }

        try {
            // HACK : replace default CRS factories with our own one, to manage cases
            // where ESRI data define bad CRS WKT.
            //-- in attempt to update to jdk8 project
            final Field field = DefaultFactories.class.getDeclaredField("FACTORIES");
            field.setAccessible(true);
            // No class check, we want it to crash if not what expected.
            final Map factories = (Map)field.get(null);

            final HackCRSFactory hackCRSFactory = new HackCRSFactory();
            factories.put(CRSFactory.class, hackCRSFactory);
            if (!DefaultFactories.isDefaultInstance(CRSFactory.class, hackCRSFactory)) {
                throw new IllegalStateException("Submitted CRS factory is not the default one !");
            }

            final HackCoordinateOperationFactory hcoFactory = new HackCoordinateOperationFactory();
            factories.put(CoordinateOperationFactory.class, hcoFactory);
            if (!DefaultFactories.isDefaultInstance(CoordinateOperationFactory.class, hcoFactory)) {
                throw new IllegalStateException("Submitted CRS factory is not the default one !");
            }

        } catch (Exception ex) {
            throw new IllegalStateException("Submitted CRS factory is not the default one !");
        }

        // force loading epsg
        CRS.forCode("EPSG:3395");
    }



    /**
    * HACK : We override default CRS factory behaviour to attempt to replace CRS
    * read from WKT when they're not well defined.
    * @author Alexis Manin (Geomatys)
    */
    public static class HackCRSFactory extends GeodeticObjectFactory {

        static final Map<String,String> CRS_ALIASES;
        static {
            final HashMap<String,String> tmpMap = new HashMap<>(13);
            //old lambert
            tmpMap.put("NTF_Lambert_Zone_I",      "EPSG:27561");
            tmpMap.put("NTF_Lambert_Zone_II",     "EPSG:27562");
            tmpMap.put("NTF_Lambert_Zone_III",    "EPSG:27563");
            tmpMap.put("NTF_Lambert_Zone_IV",     "EPSG:27564");
            //rgf-93 CC-42 <> CC-50
            tmpMap.put("RGF_1993_Lambert_Zone_1", "EPSG:3942");
            tmpMap.put("RGF_1993_Lambert_Zone_2", "EPSG:3943");
            tmpMap.put("RGF_1993_Lambert_Zone_3", "EPSG:3944");
            tmpMap.put("RGF_1993_Lambert_Zone_4", "EPSG:3945");
            tmpMap.put("RGF_1993_Lambert_Zone_5", "EPSG:3946");
            tmpMap.put("RGF_1993_Lambert_Zone_6", "EPSG:3947");
            tmpMap.put("RGF_1993_Lambert_Zone_7", "EPSG:3948");
            tmpMap.put("RGF_1993_Lambert_Zone_8", "EPSG:3949");
            tmpMap.put("RGF_1993_Lambert_Zone_9", "EPSG:3950");
            CRS_ALIASES = Collections.unmodifiableMap(tmpMap);
        }

        @Override
        public CoordinateReferenceSystem createFromWKT(String text) throws FactoryException {
            final CoordinateReferenceSystem crs = super.createFromWKT(text);
            final String code = CRS_ALIASES.get(crs.getName().getCode());
            if (code != null) {
                return CRS.forCode(code);
            }

            return crs;
        }
    }

    /**
    * Hacked operation factory to force NTV2 grid usage when projecting points from
    * NTF-Paris to RGF93.
    * Another hack has been introduced : create operation from derived CRS is very
    * time consuming, due to the fact that SIS performs a big search in EPSG db.
    * We short this behavior here.
    *
    * @author Alexis Manin (Geomatys)
    */
   public static class HackCoordinateOperationFactory extends DefaultCoordinateOperationFactory {

       private final Cache cache;
       private final Method inverseOp;
       private final Constructor crsPairConstructor;

       public HackCoordinateOperationFactory() throws Exception {
           final Field declaredField = DefaultCoordinateOperationFactory.class.getDeclaredField("cache");
           declaredField.setAccessible(true);
           cache = (Cache) declaredField.get(this);
           inverseOp = Class.forName("org.apache.sis.referencing.operation.CoordinateOperationRegistry").getDeclaredMethod("inverse", SingleOperation.class);
           inverseOp.setAccessible(true);
           crsPairConstructor = Class.forName("org.apache.sis.referencing.operation.CRSPair").getDeclaredConstructor(CoordinateReferenceSystem.class, CoordinateReferenceSystem.class);
           crsPairConstructor.setAccessible(true);
       }

       @Override
       public CoordinateOperation createOperation(CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, CoordinateOperationContext context) throws OperationNotFoundException, FactoryException {
           // We don't know how to define a cache policy using operation context. So, for this partiular case, we do not use cache.
           CoordinateOperation op = null;
           if (context != null) {
               op = createOperationUncached(sourceCRS, targetCRS, context);

           } else {
               Object cacheKey = null;
               try {
                   cacheKey = crsPairConstructor.newInstance(sourceCRS, targetCRS);
                   op = (CoordinateOperation) cache.peek(cacheKey);
               } catch (Exception ex) {

               }
               if (op == null) {
                   final Cache.Handler<CoordinateOperation> lock = cache.lock(cacheKey);
                   try {
                       op = lock.peek();
                       if (op != null)
                           return op;
                       op = createOperationUncached(sourceCRS, targetCRS, context);
                   } finally {
                       lock.putAndUnlock(op);
                   }
               }
           }

           if (op != null)
               return op;

           return super.createOperation(sourceCRS, targetCRS, context);
       }

       /**
        * Try to find a proper operation which apply the hack this class is designed
        * for. If we do not identify it as a proper candidate, we send back a null
        * value.
        * @param sourceCRS
        * @param targetCRS
        * @param context
        * @return
        * @throws OperationNotFoundException
        * @throws FactoryException
        */
       public CoordinateOperation createOperationUncached(CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, CoordinateOperationContext context) throws OperationNotFoundException, FactoryException {
           // We perform less time consuming checks immediately : CRS subtype check
           if (sourceCRS instanceof ProjectedCRS && targetCRS instanceof ProjectedCRS) {
               Integer code = IdentifiedObjects.lookupEPSG(((ProjectedCRS) sourceCRS).getBaseCRS());
               if (code != null && code == 4807) {
                   code = IdentifiedObjects.lookupEPSG(((ProjectedCRS) targetCRS).getBaseCRS());
                   if (code != null && code == 4171) {
                       CoordinateReferenceSystem step1CRS = CRS.forCode("EPSG:4275");
                       CoordinateReferenceSystem step2CRS = CRS.forCode("EPSG:4171");

                       final CoordinateOperation step1 = super.createOperation(sourceCRS, step1CRS, context);
                       final CoordinateOperation step2 = super.createOperation(step1CRS, step2CRS, context);
                       final CoordinateOperation step3 = super.createOperation(step2CRS, targetCRS, context);

                       return super.createConcatenatedOperation(Collections.singletonMap("name", "NTF-Paris to RGF93"), step1, step2, step3);
                   }
               }

           } else if (sourceCRS instanceof DerivedCRS) {
               final DerivedCRS derivedCRS = (DerivedCRS)sourceCRS;
               if (Utilities.equalsApproximatively(derivedCRS.getBaseCRS(), targetCRS)) {
                   // We cannot use the same workaround as below, or it will cause
                   // a recursive locking error.
                   final AuthorityFactory registry = CRS.getAuthorityFactory(Constants.EPSG);
                   try {

                       CoordinateOperationFinder coordinateOperationFinder = new CoordinateOperationFinder((registry instanceof CoordinateOperationAuthorityFactory) ?
                               (CoordinateOperationAuthorityFactory) registry : null, this, context);
                       return (CoordinateOperation) inverseOp.invoke(coordinateOperationFinder, derivedCRS.getConversionFromBase());
                   } catch (Exception ex) {

                   }
               } else {
                   derivedCRS.getConversionFromBase();
                   final SingleCRS baseCRS = derivedCRS.getBaseCRS();
                   final CoordinateOperation step1 = super.createOperation(sourceCRS, baseCRS, context);
                   final CoordinateOperation step2 = super.createOperation(baseCRS, targetCRS, context);

                   return createConcatenatedOperation(Collections.singletonMap("name", "derivedToOther"), step1, step2);
               }
           }
           return null;
       }
    }
}

