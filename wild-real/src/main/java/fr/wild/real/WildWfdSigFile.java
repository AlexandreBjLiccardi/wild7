
package fr.wild.real;
import java.io.File;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opengis.feature.Property;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.WKTReader;

// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

import static fr.wild.common.IoCommons.*;
import org.apache.sis.referencing.CRS;
import org.apache.sis.referencing.CommonCRS;
import org.geotoolkit.data.FeatureCollection;
import org.geotoolkit.data.FeatureIterator;
import org.geotoolkit.data.FeatureReader;
import org.geotoolkit.data.query.QueryBuilder;
import org.geotoolkit.data.session.Session;
import org.geotoolkit.data.shapefile.ShapefileFeatureStore;
import org.geotoolkit.feature.Attribute;
import org.geotoolkit.feature.Feature;
import org.geotoolkit.feature.FeatureBuilder;
import org.geotoolkit.feature.FeatureTypeBuilder;
import org.geotoolkit.feature.type.FeatureType;
import org.geotoolkit.geometry.jts.JTS;
import org.opengis.util.FactoryException;

// Dépendances, bibliothèques JAVA par exemple.


/**
 * Code généré automatiquement par l'outil Wild
 * Fichier SIG du rapportage DCE (fichier SHP embarquant des fonctionnalités complémentaires)
 */
public class WildWfdSigFile extends WildShpFile implements Serializable{



	private static final float ceil_detectEncod = (float) 0.3;

	// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildWfdSigFile(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	private String wfdSchema; // Nom du schéma DCE pour le fichier
	private String wfdRBDistrict; // Code du bassin versant pour le fichier
	private String wfdCreateDate; // Date de production du fichier
	private WildXlsFile wfdReferenceBook; // Fichier XLS de description des formats de fichier
	private WildPgService wfdPgConnexion; // Base de données PostgreSQL PostGIS de log et d'export éventuel
	private HashMap<String,HashMap<Integer,HashMap<String,Object>>> wfdErrorList; // Map des erreurs récupérées selon tous les différents tests

// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	private String pathToFile;	//Chemin vers le fichier{}
	private String pathToDescriptionFile;	//Chemin vers le fichier Excel de description WFD{}
	private String clientId;	//Identifiant de l'utilisateur{}
	private String clientPwd;	//Mot de passe de l'utilisateur{}
	private String hostName;	//Nom de l'hôte{}
	private String hostIp;	//IP de l'hôte{}
	
	private String uniqId = "thematicId";
	
	private String[] wfdRBDistrictCandidates = new String[]{
			"A","B1","B2","C","D","E","F","G","H","I","J","K","L","M","Z"
	};
	
// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***
	private HashMap<Integer,HashMap<String,Object>> attributeList; // Carte des attributs des objets contenus dans le shape
	private HashMap<Integer,String> typeList; // Carte des attributs des types contenus dans le shape
	private CoordinateReferenceSystem crsShpFile ; // Système de coordonnées propre à SHP
	private ShapefileFeatureStore dataStore = null ; // Correspond au flux de lecture du SHP
	//protected HashMap<String,HashMap<Integer,HashMap<String,Object>>> errorList ;
	private HashMap<String,HashMap<String,Integer>> distinctErrorList ;
	private String crsAsWkt; // Référentiel de projection de coordonnées, en WKT
	private HashMap<Integer,Geometry> geometryList; // Liste de tous les objets contenus dans le shape
	private String[] checkExtents = new String[]{"shp","shx","prj"};
	private Boolean withFiles = true ;
	private CoordinateReferenceSystem sourceCRS ;
	private HashMap<Integer, String[]> shp_properties;
	
	private String[] favorite = new String[]{"UTF-8","ISO-8859-1"};
	private String[] needConvert = new String[]{"windows-1252"};
	private String[] forbiddenProperties = new String[]{"Shape_Leng","Shape_Area","the_geom"};
	private HashMap<String,String[]> synonymsClass ;
	private HashMap<String,String[]> synonymsGeometry ;
	private HashMap<Integer, HashMap<String, Object>> fullErrorsList = null;
	
	private String pgSchema = null ;
	private String pgTableName ;
	private String geomType = null;
	private List<String> encounteredDictricts = new ArrayList<String>();
	private HashMap<String,String[]> equivalentPropertyNames;
	
// CE CONSTRUCTEUR DOIT ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Fonction d'initialisation, commune à tous les constructeurs.
 * "Constructeur unique"
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_pathToFile	Chemin vers le fichier{}
 * @param i_pathToDescriptionFile	Chemin vers le fichier Excel de description WFD{}
 * @param i_clientId	Identifiant de l'utilisateur{}
 * @param i_clientPwd	Mot de passe de l'utilisateur{}
 * @param i_hostName	Nom de l'hôte{}
 * @param i_hostIp	IP de l'hôte{}
 */
protected void WILD_initialize_WildWfdSigFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_pathToDescriptionFile,
	String i_clientId,
	String i_clientPwd,
	String i_hostName,
	String i_hostIp
) throws Exception {

//	// Amorce de la classe
	// Initialisation de la classe d'objet selon le schéma Wild
	WILD_dObject = i_WILD_dObject ;
	WILD_initialize_WildShpFile(i_WILD_dObject, i_pathToFile,null,null);
	// Préparation des variables d'invocation (considérées comme champs globaux)
	this.pathToFile = i_pathToFile;
	this.pathToDescriptionFile = i_pathToDescriptionFile;
	this.clientId = i_clientId;
	this.clientPwd = i_clientPwd;
	this.hostName = i_hostName;
	this.hostIp = i_hostIp;
	
//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		synonymsClass = new HashMap();
		synonymsClass.put("String", new String[]{"string","texte","text","varchar"});
		synonymsClass.put("Double", new String[]{"double","réel","réel double","integer"});
		synonymsGeometry = new HashMap();
		synonymsGeometry.put("MultiLineString", new String[]{"polyline","multilinestring"});
		synonymsGeometry.put("Point", new String[]{"point"});
		synonymsGeometry.put("MultiPoint", new String[]{"multipoint"});
		synonymsGeometry.put("Polygon", new String[]{"polygon"});
		synonymsGeometry.put("MultiPolygon", new String[]{"multipolygon"});
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		this.wfdPgConnexion = new WildPgService(this.WILD_dObject,clientId,clientPwd,hostName,hostIp);
		wfdReferenceBook = new WildXlsFile(this.WILD_dObject,pathToDescriptionFile);
		DVP_selectWfdSchema() ;
		DVP_checkWithFiles();
		if(!isInstantiable())return ;
		if(this.wfdSchema==null)return ;
		this.castFile();
	}catch(Exception e){
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
 * @param i_pathToDescriptionFile	Chemin vers le fichier Excel de description WFD{}
 * @param i_clientId	Identifiant de l'utilisateur{}
 * @param i_clientPwd	Mot de passe de l'utilisateur{}
 * @param i_hostName	Nom de l'hôte{}
 * @param i_hostIp	IP de l'hôte{}
 */

public WildWfdSigFile(
	WildObject i_WILD_dObject,
	String i_pathToFile,
	String i_pathToDescriptionFile,
	String i_clientId,
	String i_clientPwd,
	String i_hostName,
	String i_hostIp
) throws Exception{
	this.WILD_initialize_WildWfdSigFile(i_WILD_dObject,i_pathToFile,i_pathToDescriptionFile,i_clientId,i_clientPwd,i_hostName,i_hostIp);
}

/*	### NOUVELLE METHODE ###
	Méthode : checkWfdGeometryTypes - Vérification des types de géométries{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Vérification des types de géométries{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public HashMap<Integer,HashMap<String,Object>> checkWfdGeometryTypes ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<Integer,HashMap<String,Object>> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	String typeM = map_searchWithinSynonyms(DVP_getWfdConf(this.wfdSchema,"Shape Type").toLowerCase(), this.synonymsGeometry);
	Integer errorNb = 1 ;
	WILD_toReturn = new HashMap();
	for(Integer i:this.typeList.keySet()){
		String typeFormatted =  map_searchWithinSynonyms(typeList.get(i), this.synonymsGeometry);
		if(!(
				typeFormatted.toLowerCase().equals(typeM.toLowerCase())
		)){
			HashMap<String,Object> error2 = new HashMap();
			error2.put("Description", "Wrong geometry. Expected : "+typeM+" gotten : "+typeList.get(i)+".");
			error2.put("atLine", i);
			error2.put("atId", (String) this.attributeList.get(i).get(this.uniqId));
			error2.put("atAttribute", "geometry");
			WILD_toReturn.put(errorNb++, error2);
	}
	}
	for(Integer i:this.geometryList.keySet())
		if(geometryList.get(i)==null){
			HashMap<String,Object> error2 = new HashMap();
			error2.put("Description", "Geometry is null.");
			error2.put("atLine", i);
			error2.put("atId", (String) this.attributeList.get(i).get(this.uniqId));
			error2.put("atAttribute", "geometry");
			WILD_toReturn.put(errorNb++, error2);
			continue ;
		}
		else if(!geometryList.get(i).isValid()){
			HashMap<String,Object> error2 = new HashMap();
			error2.put("Description", "Geometry is not valid towards WKT specifications.");
			error2.put("atLine", i);
			error2.put("atId", (String) this.attributeList.get(i).get(this.uniqId));
			error2.put("atAttribute", "geometry");
			WILD_toReturn.put(errorNb++, error2);
	}
	if(WILD_toReturn!=null){
		if(this.wfdErrorList==null)this.wfdErrorList=new HashMap();
		this.wfdErrorList.put("GeometryType",WILD_toReturn);	
	}
		
		
//	//	//	Output	"checkWfdGeometryTypes_output1" : Nombre d'erreurs identifiées (Integer)
		this.WILD_setOutput("checkWfdGeometryTypes_output1",errorNb-1); // Ne pas modifier

//	//	//	Output	"checkWfdGeometryTypes_output2" : Map des erreurs récupérées (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("checkWfdGeometryTypes_output2",WILD_toReturn); // Ne pas modifier

//	//	//	Output	"checkWfdGeometryTypes_output3" : Tableau de score par type d'erreurs (HashMap<String,Integer>)
	//	this.WILD_setOutput("checkWfdGeometryTypes_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : checkWfdAttributes - Vérification des attributs (type, nom, ordre){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Vérification des attributs (type, nom, ordre){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public HashMap<Integer,HashMap<String,Object>> checkWfdAttributes ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<Integer,HashMap<String,Object>> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		WILD_toReturn = new HashMap();
		WildDataSet wDs = this.wfdReferenceBook.getDataSet(this.wfdSchema);
		Integer errorNb = 1 ;
		
		WildDataSet wDs_f = this.getDataSet("1");
		
		List<String> javacl = new ArrayList();
		
		for(String k:equivalentPropertyNames.keySet())
			if(!equivalentPropertyNames.get(k)[0].equals(equivalentPropertyNames.get(k)[2])){
				HashMap<String,Object> error0 = new HashMap();
				error0.put("Description", "Approximative property name : "+equivalentPropertyNames.get(k)[2]+".");
				error0.put("atLine", null);
				error0.put("atId", null);
				error0.put("atAttribute", equivalentPropertyNames.get(k)[0]);
				WILD_toReturn.put(errorNb++, error0);
				
		}
		
		for(Integer p_i:shp_properties.keySet()){
			String nom_p = this.shp_properties.get(p_i)[0];
			String classe_p = this.shp_properties.get(p_i)[1];
			String[] arr_attr = equivalentPropertyNames.get(nom_p.toLowerCase());
			if(arr_attr==null){
				HashMap<String,Object> error2 = new HashMap();
				error2.put("Description", "Property missing in configuration file : "+nom_p+".");
				error2.put("atLine", null);
				error2.put("atId", null);
				error2.put("atAttribute", nom_p);
				WILD_toReturn.put(errorNb++, error2);
				continue;
			}
			
			String nom_attr = (arr_attr==null)?null:arr_attr[0];
			String classe_attr = (equivalentPropertyNames.get(nom_p.toLowerCase())!=null)?
					map_searchWithinSynonyms(arr_attr[1].toLowerCase(), synonymsClass)
					:null;
			
			Integer order_m = null ;
			String nom_m = null ;
			String format_m = null ;
			String long_m = null ;
			String valNull_m = null;
			
			
			for(Integer m_i = 1 ; m_i <= wDs.DVP_getLength() ; m_i++)
			if(wDs.DVP_getData(m_i, "Nom")!=null&&wDs.DVP_getData(m_i, "Nom").toString().equals(nom_attr)){
				order_m = ((Double)wDs.DVP_getData(m_i, "Ordre")).intValue();
				nom_m = wDs.DVP_getData(m_i, "Nom").toString();
				format_m = wDs.DVP_getData(m_i, "Format").toString().toLowerCase();
				long_m = wDs.DVP_getData(m_i, "Longueur").toString().trim();
				valNull_m = wDs.DVP_getData(m_i, "Valeur si NULL").toString();
				break;
			}
			
			if(order_m!=p_i){
				HashMap<String,Object> error2 = new HashMap();
				error2.put("Description", "Order is wrong with property : "+nom_p+"( "+p_i+" instead of  "+order_m+").");
				error2.put("atLine", null);
				error2.put("atId", null);
				error2.put("atAttribute", nom_m);
				WILD_toReturn.put(errorNb++, error2);
			}
			if(!(
					classe_p.equals(format_m)
					||(synonymsClass.get(classe_p)!=null&&Arrays.asList(synonymsClass.get(classe_p)).contains(format_m))
			)){
				HashMap<String,Object> error2 = new HashMap();
				error2.put("Description", "Declared class is not compliant : "+classe_p+" instead of  "+format_m+".");
				error2.put("atLine", null);
				error2.put("atId", null);
				error2.put("atAttribute", nom_m);
				WILD_toReturn.put(errorNb++, error2);
			}			
			
			
			for(Integer n:this.attributeList.keySet()){
				// Vérification générale du type
				Object data = this.attributeList.get(n).get(nom_attr);
				
				
				// Ne peut pas être nul
				if(data==null||data.toString().length()==0){
					data = cast_2Object(classe_attr, valNull_m)[1] ;
					wDs_f.DVP_setData(n,nom_m,data);
					HashMap<String,Object> error2 = new HashMap();
					error2.put("Description", "Null value");
					error2.put("atLine", n);
					error2.put("atId", (String) this.attributeList.get(n).get(this.uniqId));
					error2.put("atAttribute", nom_m);
					WILD_toReturn.put(errorNb++, error2);
					continue;
				}
				
				String className = data.getClass().getSimpleName().toLowerCase();
				if(!javacl.contains(className))javacl.add(className);
				
				if(!(
						className.equals(format_m)
						||(synonymsClass.get(classe_p)!=null&&Arrays.asList(synonymsClass.get(classe_p)).contains(format_m))
				)){
					HashMap<String,Object> error2 = new HashMap();
					error2.put("Description", "Instanciated class is not compliant : "+className+" instead of  "+format_m+".");
					error2.put("atLine", n);
					error2.put("atId", (String) this.attributeList.get(n).get(this.uniqId));
					error2.put("atAttribute", nom_m);
					WILD_toReturn.put(errorNb++, error2);
				}			
						
				// Vérification de la taille du champ
				switch(className){
				case "string" :
					if(((String)data).length()>Integer.parseInt(long_m.split("\\.")[0].split(",")[0])){
						HashMap<String,Object> error2 = new HashMap();
						error2.put("Description", "Maximum length of a string variable exceded : "+((String)data).length()+" >  "+Integer.parseInt(long_m)+".");
						error2.put("atLine", n);
						error2.put("atId", (String) this.attributeList.get(n).get(this.uniqId));
						error2.put("atAttribute", nom_m);
						WILD_toReturn.put(errorNb++, error2);
					}
					break;
				case "double" :
					Integer sizeBf_m = Integer.parseInt(long_m.split(",")[0].trim());
					Integer sizeAf_m = Integer.parseInt(long_m.split(",")[1].trim());
					String[] ar_data = data.toString().split("\\.");
					Integer sizeBf = ar_data[0].length();
					Integer sizeAf = (ar_data.length>1)?ar_data[1].length():0;
					if(sizeBf_m < sizeBf || sizeAf_m < sizeAf){
						HashMap<String,Object> error2 = new HashMap();
						error2.put("Description", "Maximum length of a double variable exceded : "+data+" not compliant for ("+long_m+").");
						error2.put("atLine", n);
						error2.put("atId", (String) this.attributeList.get(n).get(this.uniqId));
						error2.put("atAttribute", nom_m);
						WILD_toReturn.put(errorNb++, error2);
					}	
					break;
				default:
					
				}		
			}
		}
		if(WILD_toReturn!=null){
			if(this.wfdErrorList==null)this.wfdErrorList=new HashMap();
			this.wfdErrorList.put("Attributes",WILD_toReturn);	
		}
//	//	//	Output	"checkWfdAttributes_output1" : Nombre d'erreurs identifiées (Integer)
		this.WILD_setOutput("checkWfdAttributes_output1",errorNb-1); // Ne pas modifier

//	//	//	Output	"checkWfdAttributes_output2" : Map des erreurs récupérées (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("checkWfdAttributes_output2",WILD_toReturn); // Ne pas modifier

//	//	//	Output	"checkWfdAttributes_output3" : Tableau de score par type d'erreurs (HashMap<String,Integer>)
	//	this.WILD_setOutput("checkWfdAttributes_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : checkWfdCrs - Vérification du système de projection{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Vérification du système de projection{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public HashMap<Integer,HashMap<String,Object>> checkWfdCrs ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<Integer,HashMap<String,Object>> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		String epsgCode = DVP_getWfdConf(this.wfdSchema,"EPSG");
		epsgCode=epsgCode.split("\\.")[0];
		CoordinateReferenceSystem target2CRS = CRS.forCode("EPSG:"+epsgCode);
		String sourceName = sourceCRS.getCoordinateSystem().getName().getCode();
		String destName = target2CRS.getCoordinateSystem().getName().getCode();
		Integer nbError = 1 ;
		if(!sourceName.equals(destName)){
			HashMap<String,Object> error2 = new HashMap();
			error2.put("Description", "Wrong SRC. Expected : "+sourceName+" gotten : "+destName+".");
			error2.put("atLine", null);
			error2.put("atId", null);
			error2.put("atAttribute", null);
			if(WILD_toReturn==null)WILD_toReturn=new HashMap();
			WILD_toReturn.put(nbError++, error2);
		}
		if(WILD_toReturn!=null){
			if(this.wfdErrorList==null)this.wfdErrorList=new HashMap();
			this.wfdErrorList.put("SRC",WILD_toReturn);	
		}
		
//	//	//	Output	"checkWfdCrs_output1" : Nombre d'erreurs identifiées (Integer)
		this.WILD_setOutput("checkWfdCrs_output1",nbError-1); // Ne pas modifier

//	//	//	Output	"checkWfdCrs_output2" : Map des erreurs récupérées (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("checkWfdCrs_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkWfdCrs_output3" : Tableau de score par type d'erreurs (HashMap<String,Integer>)
	//	this.WILD_setOutput("checkWfdCrs_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : pgWfdLog - Ecriture des logs de traitement vers une base PostgreSQL{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ecriture des logs de traitement vers une base PostgreSQL{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void pgWfdLog ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(fullErrorsList!=null&&pgSchema!=null) this.wfdPgConnexion.loadTo((Map)this.fullErrorsList, this.pgSchema, "errorsList",false);
		Integer nbErrors = (this.fullErrorsList==null)?0:this.fullErrorsList.size();
//	//	//	Output	"pgWfdLog_output1" : Nombre de lignes écrites (Integer)
		this.WILD_setOutput("pgWfdLog_output1",nbErrors); // Ne pas modifier

//	//	//	Output	"pgWfdLog_output2" : Contenu des lignes écrites (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("pgWfdLog_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : pgWfdExport - Export vers une base PostgreSQL / PostGIS{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Export vers une base PostgreSQL / PostGIS{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_outputSchema	Schéma d'export des données{}
 */
public void pgWfdExport (
	String i_outputSchema
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier
	WildDataSet wDs = null ;
	Integer errorNb = 0;
//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		pgSchema = i_outputSchema ;
		if(this.getDataSet("1")!=null){
			pgTableName = (this.wfdRBDistrict+"_"+this.wfdSchema).toLowerCase() ;
			wDs = this.getDataSet("1") ;
			WILD_echo("Nombre d'éléments à charger :  "+wDs.DVP_getLength()+", table : "+pgTableName);
			if(encounteredDictricts.isEmpty())wfdPgConnexion.loadTo((Map)this.getDataSet("1").DVP_getData(), pgSchema, pgTableName,false);
			else{
				String condition = "1=0";
				for(String district : encounteredDictricts)condition += " OR lower(\"thematicId\") like 'fr"+district+"%'";
				HashMap<String,String> deleteDatas = new HashMap();
				deleteDatas.put("schema",  i_outputSchema);
				deleteDatas.put("table", pgTableName);
				deleteDatas.put("condition", condition);		
				wfdPgConnexion.DVP_executeQuery_Blind("deleteFrom", deleteDatas);		
				deleteDatas = null;
				wfdPgConnexion.loadTo((Map)this.getDataSet("1").DVP_getData(), pgSchema, pgTableName,true);
			}
			HashMap<String,String> orderDatas = new HashMap();
			orderDatas.put("schema",  i_outputSchema);
			orderDatas.put("table", pgTableName);
			orderDatas.put("srid",  "4326");
			orderDatas.put("fromColumn", "wktGeometry");
			wfdPgConnexion.DVP_executeQuery_Blind("addGeomColumn", orderDatas);
			wfdPgConnexion.DVP_executeQuery("buildGeom", orderDatas);
			orderDatas = null;
			String userName = System.getProperty("user.name") ;
			String dateToext =  new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date()) ;
			HashMap<Integer, HashMap<String, Object>> fLogsList = new HashMap();
			HashMap<String, Object> logsList = new HashMap();
			logsList.put("TableName", pgTableName);
			logsList.put("User", userName);
			logsList.put("DateId", dateToext);
			fLogsList.put((Integer)1, logsList);
			errorNb = fLogsList.size();
			wfdPgConnexion.loadTo((Map)fLogsList, pgSchema, "logsList",true);
			fLogsList = null;
			
			// Export de la table all vers un SHP, si provient d'un DIFF
			if(encounteredDictricts.isEmpty())return ;
			
				wDs = this.wfdReferenceBook.getDataSet(this.wfdSchema);
				String fields = " \"wktGeometry\"";
   				for(Integer m_i = 1 ; m_i <= wDs.DVP_getLength() ; m_i++)
   					fields += ", \""+wDs.DVP_getData(m_i, "Nom")+"\"";
   				HashMap<String, String> reqList = new HashMap();
   				reqList.put("schema",  i_outputSchema);
   				reqList.put("table", pgTableName);
   				reqList.put("fields", fields);
   				HashMap<Integer, Map<String, Object>>  resreq = (HashMap<Integer, Map<String, Object>>) wfdPgConnexion.DVP_getQuery("getFullDatas", reqList);
   				String epsgCode = DVP_getWfdConf(this.wfdSchema,"EPSG").split("\\.")[0];
   				FeatureType TYPE = DVP_createSchema(
   						geomType, 
   						this.DVP_getWfdConf(this.wfdSchema, "Shape Type").toString(), 
   						epsgCode, 
   						wDs
   						);

   				List<Feature> features = new ArrayList<>();
   				FeatureBuilder featureBuilder = new FeatureBuilder(TYPE);
   				CoordinateReferenceSystem sourceCRS = CommonCRS.WGS84.normalizedGeographic();
   				CoordinateReferenceSystem targetCRS = CRS.forCode("EPSG:"+epsgCode);
   				MathTransform transform = CRS.findOperation(sourceCRS, targetCRS, null).getMathTransform();
   				WILD_echo("Exporting "+resreq.size()+" entities to SHP...");
   				final WKTReader wkt = new WKTReader();
   				for(Integer aRowN:resreq.keySet()){
   					final Map<String, Object> aRow = resreq.get(aRowN);
   					final Geometry targetGeometry = (aRow.get("wktGeometry") ==null)? null:JTS.transform( wkt.read(aRow.get("wktGeometry").toString()), transform);
					featureBuilder.add(targetGeometry);
					for(Integer m_i = 1 ; m_i <= wDs.DVP_getLength() ; m_i++){
						Object attrvalue = aRow.get(wDs.DVP_getData(m_i, "Nom").toString());
						featureBuilder.add(attrvalue);
					}
					features.add(featureBuilder.buildFeature(null));
   				}
   				
   				DVP_commitAsShp(TYPE, features );
			
		}
//	//	//	Output	"pgWfdExport_output1" : Nombre de lignes écrites (Integer)
		this.WILD_setOutput("pgWfdExport_output1",wDs.DVP_getLength()); // Ne pas modifier

//	//	//	Output	"pgWfdExport_output2" : Nombre d'erreurs éventuelles (Integer)
		this.WILD_setOutput("pgWfdExport_output2",errorNb); // Ne pas modifier

//	//	//	Output	"pgWfdExport_output3" : Map des erreurs récupérées (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("pgWfdExport_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier
		
	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : merge - Fusion de plusieurs couches en une seule{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Fusion de plusieurs couches en une seule{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_shpToMerge	Liste des fichiers SHP à fusionner dans l'objet actuel{}
 */
public void merge (
	List<WildWfdSigFile> i_shpToMerge
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

this.wfdRBDistrict = "All";
WildDataSet wDs_conf = null ;

WildDataSet wDs = this.getDataSet("1");
for(WildWfdSigFile shpToMerge:i_shpToMerge){
	WildDataSet wDs_merge = shpToMerge.getDataSet("1");
	if(geomType == null) geomType = shpToMerge.DVP_getFeatureType() ;
	if(this.wfdSchema == null){
		this.wfdSchema=shpToMerge.getWfdSchema();
	 	wDs_conf = this.wfdReferenceBook.getDataSet(this.wfdSchema);
	 	
	}
	if(wDs==null){
		wDs = wDs_merge ;
		String[] header = new String[wDs_conf.DVP_getLength()+1];
		for(Integer i = 1 ; i <= wDs_conf.DVP_getLength() ; i++)header[i-1] = wDs_conf.DVP_getData(i, "Nom").toString();
		header[wDs_conf.DVP_getLength()]="wktGeometry";
		wDs.setHeaders(header);
	}else
		wDs.DVP_addToDataSet(wDs_merge);
}
this.addDataSet(wDs);

String userName = System.getProperty("user.name") ;
String dateToext =  new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date()) ;
fullErrorsList = new HashMap();
Integer errNb = 1 ;
for(WildWfdSigFile shpToMerge:i_shpToMerge){
	HashMap<String, HashMap<Integer, HashMap<String, Object>>> errOutput = shpToMerge.DVP_getErrors() ;
	for(String k1:errOutput.keySet())for(Integer k2:errOutput.get(k1).keySet()){
		HashMap<String, Object> errOutputSub = new HashMap();
		for(String k3:errOutput.get(k1).get(k2).keySet())errOutputSub.put(k3, errOutput.get(k1).get(k2).get(k3));
		errOutputSub.put("Group", k1);
		errOutputSub.put("User", userName);
		errOutputSub.put("DateId", dateToext);
		errOutputSub.put("TableName", shpToMerge.DVP_tableName());
		fullErrorsList.put(errNb++,errOutputSub);
	}
}
String epsgCode = DVP_getWfdConf(this.wfdSchema,"EPSG").split("\\.")[0];
FeatureType TYPE = DVP_createSchema(
		geomType, 
		this.DVP_getWfdConf(this.wfdSchema, "Shape Type").toString(), 
		epsgCode, 
		wDs_conf
		);

List<Feature> features = new ArrayList<>();
FeatureBuilder featureBuilder = new FeatureBuilder(TYPE);
CoordinateReferenceSystem sourceCRS = CommonCRS.WGS84.normalizedGeographic();
CoordinateReferenceSystem targetCRS = CRS.forCode("EPSG:"+epsgCode);
MathTransform transform = CRS.findOperation(sourceCRS, targetCRS, null).getMathTransform();

for(WildWfdSigFile shpToMerge:i_shpToMerge){
	HashMap<Integer,Geometry> map_i = shpToMerge.DVP_getGeometries();
	WildDataSet wDs_merge = shpToMerge.getDataSet("1");
	for(Integer k_g:map_i.keySet()){
		Geometry targetGeometry = (map_i.get(k_g)==null)? null:JTS.transform( map_i.get(k_g), transform);
		featureBuilder.add(targetGeometry);
		for(Integer c_i=1; c_i <= wDs_conf.DVP_getLength(); c_i++){
			Object attrvalue = wDs_merge.DVP_getData(k_g, wDs_conf.DVP_getData(c_i, "Nom").toString());
			featureBuilder.add(attrvalue);
		}
		Feature feature = featureBuilder.buildFeature(null);
        features.add(feature);
	}
}
DVP_commitAsShp(TYPE, features );

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"merge_output1" : Nombre de couches fusionnées (Integer)
		this.WILD_setOutput("merge_output1",i_shpToMerge.size()); // Ne pas modifier

//	//	//	Output	"merge_output2" : Nombre d'objets fusionnés (Integer)
		this.WILD_setOutput("merge_output2",wDs.DVP_getLength()); // Ne pas modifier

//	//	//	Output	"merge_output3" : Nombre d'erreurs éventuelles (Integer)
		this.WILD_setOutput("merge_output3",errNb-1); // Ne pas modifier

//	//	//	Output	"merge_output4" : Map des erreurs récupérées (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("merge_output4",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : mergeDiff - Fusion de plusieurs couches en une seule, stocke la liste de bassins rencontrés, pour une alimentation différentielle{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Fusion de plusieurs couches en une seule, stocke la liste de bassins rencontrés, pour une alimentation différentielle{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_shpToMerge	Liste des fichiers SHP à fusionner dans l'objet actuel{}
 */
public void mergeDiff (
	List<WildWfdSigFile> i_shpToMerge
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

this.wfdRBDistrict = "All";
WildDataSet wDs_conf = null ;

WildDataSet wDs = this.getDataSet("1");
for(WildWfdSigFile shpToMerge:i_shpToMerge){
	WildDataSet wDs_merge = shpToMerge.getDataSet("1");
	if(geomType == null) geomType = shpToMerge.DVP_getFeatureType() ;
	if(this.wfdSchema == null){
		this.wfdSchema=shpToMerge.getWfdSchema();
	 	wDs_conf = this.wfdReferenceBook.getDataSet(this.wfdSchema);
	}
	if(wDs==null){
		wDs = wDs_merge ;
		String[] header = new String[wDs_conf.DVP_getLength()+1];
		for(Integer i = 1 ; i <= wDs_conf.DVP_getLength() ; i++)header[i-1] = wDs_conf.DVP_getData(i, "Nom").toString();
		header[wDs_conf.DVP_getLength()]="wktGeometry";
		wDs.setHeaders(header);
	}else
		wDs.DVP_addToDataSet(wDs_merge);
	this.encounteredDictricts.add(shpToMerge.DVP_getWfdDistrict());
}
this.addDataSet(wDs);

String userName = System.getProperty("user.name") ;
String dateToext =  new SimpleDateFormat("yyyyMMdd_HHmm").format(new Date()) ;
fullErrorsList = new HashMap();
Integer errNb = 1 ;

for(WildWfdSigFile shpToMerge:i_shpToMerge){
	HashMap<String, HashMap<Integer, HashMap<String, Object>>> errOutput = shpToMerge.DVP_getErrors() ;
	for(String k1:errOutput.keySet())for(Integer k2:errOutput.get(k1).keySet()){
		HashMap<String, Object> errOutputSub = new HashMap();
		for(String k3:errOutput.get(k1).get(k2).keySet())errOutputSub.put(k3, errOutput.get(k1).get(k2).get(k3));
		errOutputSub.put("Group", k1);
		errOutputSub.put("User", userName);
		errOutputSub.put("DateId", dateToext);
		errOutputSub.put("TableName", shpToMerge.DVP_tableName());
		fullErrorsList.put(errNb++,errOutputSub);
	}
}

//Pour les exports différentiels, l'export est réalisé à partir de PostGreSQL, en fin d'exécution


//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"merge_output1" : Nombre de couches fusionnées (Integer)
		this.WILD_setOutput("merge_output1",i_shpToMerge.size()); // Ne pas modifier

//	//	//	Output	"merge_output2" : Nombre d'objets fusionnés (Integer)
		this.WILD_setOutput("merge_output2",wDs.DVP_getLength()); // Ne pas modifier

//	//	//	Output	"merge_output3" : Nombre d'erreurs éventuelles (Integer)
		this.WILD_setOutput("merge_output3",errNb-1); // Ne pas modifier

//	//	//	Output	"merge_output4" : Map des erreurs récupérées (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("merge_output4",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"mergeDiff_output5" : Nombre de bassins rencontrés (Integer)
		this.WILD_setOutput("mergeDiff_output5",this.encounteredDictricts.size()); // Ne pas modifier

//	//	//	Output	"mergeDiff_output6" : Liste des bassins rencontrés (List<String>)
		this.WILD_setOutput("mergeDiff_output6",this.encounteredDictricts); // Ne pas modifier
	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}
/*	### NOUVELLE METHODE ###
	Méthode : checkWfdEncoding - Vérification de l'encodage du fichier{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Vérification de l'encodage du fichier{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public HashMap<Integer,HashMap<String,Object>> checkWfdEncoding ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<Integer,HashMap<String,Object>> WILD_toReturn = null ; // Ne pas modifier
	List<String> checkedEncodings = new ArrayList();
	List<String> checkedConverters = new ArrayList();
//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		List<String> encounteredEncodings = new ArrayList();
		List<String> encounteredConvertChars = new ArrayList();
		Integer encodError = 1 ;
		HashMap<Integer,HashMap<String,Object>> wfdErrorList_encod = new HashMap();
		HashMap<String,String> mapTypes = new HashMap(); 
		
		WildDataSet wDs = this.wfdReferenceBook.getDataSet(this.wfdSchema);
		for(Integer k_i:geometryList.keySet()){
			HashMap<String,Object> newRecord = new HashMap();
			String uId = (String) attributeList.get(k_i).get(this.uniqId);
			for(String k:attributeList.get(k_i).keySet())if(!Arrays.asList(forbiddenProperties).contains(k)){
				Object[] value_t = DVP_valueGuessCharset(attributeList.get(k_i).get(k));
				if((Boolean)value_t[2])
					if(!encounteredEncodings.contains(value_t[0]))encounteredEncodings.add((String) value_t[0]);

				if(value_t[3]!=null)
					if(!encounteredConvertChars.contains(value_t[3]))encounteredConvertChars.add((String) value_t[3]);
				
				if(mapTypes.get(k)==null)
					for(String propName:this.equivalentPropertyNames.keySet())
						if(equivalentPropertyNames.get(propName)[0].equals(k)){
							mapTypes.put(k, equivalentPropertyNames.get(propName)[1]);
							break;
						}
				
				if(mapTypes.get(k)==null)mapTypes.put(k, "string");
				Object[] valueAttr_o = DVP_castForCell(value_t[1], mapTypes.get(k));
				Object valueAttr = (valueAttr_o!=null&&(Boolean)valueAttr_o[0])?valueAttr_o[1]:null;
				if(valueAttr_o!=null&&!(Boolean)valueAttr_o[0]){
					HashMap<String,Object> error1 = new HashMap();
					error1.put("Description", "Wrong type : {"+valueAttr_o[1]+"} for "+mapTypes.get(k)+".");
					error1.put("atLine", k_i);
					error1.put("atId", uId);
					error1.put("atAttribute", k);
					wfdErrorList_encod.put(encodError++,error1);
				}
				newRecord.put(k,valueAttr);	
			}
			if(geometryList.get(k_i)!=null)newRecord.put("wktGeometry", geometryList.get(k_i).toString());
			add(newRecord);
		}
		if(wfdErrorList == null&&(  
					!wfdErrorList_encod.isEmpty()
				||	!encounteredEncodings.isEmpty()
				||	!encounteredConvertChars.isEmpty()
		)) wfdErrorList = new HashMap();
		for(String encount : encounteredEncodings){
			HashMap<String,Object> error2 = new HashMap();
			error2.put("Description", "Encoding encountered : "+encount);
			error2.put("atLine", null);
			error2.put("atId", null);
			error2.put("atAttribute", null);
			wfdErrorList_encod.put(encodError++,error2);
		}
		for(String encount : encounteredConvertChars){
			HashMap<String,Object> error2 = new HashMap();
			error2.put("Description", "Encoding implies conversion : "+encount);
			error2.put("atLine", null);
			error2.put("atId", null);
			error2.put("atAttribute", null);
			wfdErrorList_encod.put(encodError++,error2);
		}
		wfdErrorList.put("Encoding", wfdErrorList_encod);
//	//	//	Output	"checkWfdEncoding_output1" : Nombre d'erreurs identifiées (Integer)
		this.WILD_setOutput("checkWfdEncoding_output1",encodError-1); // Ne pas modifier

//	//	//	Output	"checkWfdEncoding_output2" : Map des erreurs récupérées (HashMap<Integer,HashMap<String,Object>>)
	//	this.WILD_setOutput("checkWfdEncoding_output2",/*** Valeur à remonter en output ***/); // Ne pas modifier

//	//	//	Output	"checkWfdEncoding_output3" : Tableau de score par type d'erreurs (HashMap<String,Integer>)
	//	this.WILD_setOutput("checkWfdEncoding_output3",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : writeWfdErrorList - Ecriture vers un fichier externe des erreurs rencontrées{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.


/**
 * Ecriture vers un fichier externe des erreurs rencontrées{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_pathToFile_OUT	Chemin vers le fichier de sortie{}
 * @return	{}
 */
public String writeWfdErrorList (
	String i_pathToFile_OUT
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		Integer i = 1 ;
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		HashMap<Integer, HashMap<String, Object>> dataErrors = new HashMap();
		for(String k:this.wfdErrorList.keySet())for(Integer k_i:wfdErrorList.get(k).keySet()){
					HashMap<String,Object> dataError = new HashMap();
					dataError.put("File", this.pathToFile);
					dataError.put("Schema", this.wfdSchema);
					dataError.put("Basin", this.wfdRBDistrict);
					dataError.put("Group", k);
					for(String k_a:wfdErrorList.get(k).get(k_i).keySet())
						dataError.put(k_a,wfdErrorList.get(k).get(k_i).get(k_a));
					dataErrors.put(i++, dataError);
		}
		WildFrCsvFile destExport = new WildFrCsvFile(this.WILD_dObject, i_pathToFile_OUT);
		WildDataSet wDs = new WildDataSet(this.WILD_dObject,dataErrors);
		destExport.DVP_fillWith(wDs, true);
		
//	//	//	Output	"writeWfdErrorList_output1" : Nombre de lignes écrites (Integer)
		this.WILD_setOutput("writeWfdErrorList_output1",wDs.DVP_getLength()); // Ne pas modifier

//	//	//	Output	"writeWfdErrorList_output2" : Chemin du fichier de sortie (String)
		this.WILD_setOutput("writeWfdErrorList_output2",i_pathToFile_OUT); // Ne pas modifier

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
 * Ecriture vers un fichier externe des erreurs rencontrées{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @return	Chemin du fichier de sortie (String) {}
 */
 public String writeWfdErrorList ()throws Exception{
return writeWfdErrorList("WFD_errorList.csv");
} 

/*	### NOUVELLE METHODE ###
	Méthode : getWfdSchema - Récupération du nom de schéma WFD{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération du nom de schéma WFD{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public String getWfdSchema ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		WILD_toReturn = this.wfdSchema ;
//	//	//	Etape	"1" : poids relatif de 10, Renvoi de la valeur
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : castFile - Méthode de conversion standard du contenu vers un jeu de données (ou une collection).{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Méthode de conversion standard du contenu vers un jeu de données (ou une collection).{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void castFile ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier
	FeatureReader iterator = null ;
//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			
//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		dataStore = new ShapefileFeatureStore(fileFile.toURI());
		iterator = dataStore.getFeatureReader(QueryBuilder.all(dataStore.getName()));

//	//	//	Etape	"2" : poids relatif de 100, Parcours du jeu de données avec résolution des cas spéciaux (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		int i = 1 ;
		sourceCRS = iterator.getFeatureType().getCoordinateReferenceSystem();
		crsShpFile = sourceCRS;
		crsAsWkt = sourceCRS.toWKT();
		if(geomType==null) geomType = iterator.getFeatureType().getName().tip().toString();
		CoordinateReferenceSystem targetCRS = CommonCRS.WGS84.normalizedGeographic();
        MathTransform transform = CRS.findOperation(sourceCRS, targetCRS, null).getMathTransform();
		while(iterator.hasNext()) {
		   final Feature feature = iterator.next();
		  
		   if(geometryList==null) geometryList = new HashMap<>();
		   if(typeList==null) typeList = new HashMap<>();
		   if(attributeList==null)attributeList = new HashMap<>();

		   Geometry rawGeometry = (Geometry) feature.getDefaultGeometryProperty().getValue();
		   Geometry targetGeometry = (rawGeometry==null)? null : JTS.transform( rawGeometry, transform);
		   this.geometryList.put(i, (Geometry) targetGeometry);
		   if(targetGeometry!=null)typeList.put(i,targetGeometry.getGeometryType());
		   HashMap<String,Object> hMProperties = new HashMap<>(); 
		   Integer p_i = 1 ;
		   if(equivalentPropertyNames==null)equivalentPropertyNames = new HashMap();
		   for(Property p:feature.getProperties()){
			   String propName = null ;
			   String propCurrentName = p.getName().tip().toString().toLowerCase() ;
			   		if(equivalentPropertyNames.get(propCurrentName)!=null)
			   			propName = equivalentPropertyNames.get(propCurrentName)[0] ;
			   		else {
			   			WildDataSet wDs = this.wfdReferenceBook.getDataSet(this.wfdSchema);
		   				for(Integer m_i = 1 ; m_i <= wDs.DVP_getLength() ; m_i++)
			   				if(((String) wDs.DVP_getData(m_i, "Nom")).toLowerCase().equals(propCurrentName)){
			   					propName = ((String) wDs.DVP_getData(m_i, "Nom")) ;
			   					String propType = ((String) wDs.DVP_getData(m_i, "Format")).toLowerCase();
			   					equivalentPropertyNames.put(propCurrentName, new String[]{propName,propType,p.getName().tip().toString()});
			   					break;
			   				}
			   		}
			   
			   if(shp_properties == null)shp_properties=new HashMap();
			   if(i==1&&!Arrays.asList(forbiddenProperties).contains(p.getName().tip().toString()))shp_properties.put(p_i++, new String[]{
					   p.getName().tip().toString(),
					   ((Attribute)p).getType().getBinding().getSimpleName()//.toLowerCase()
			   	});
			   if(propName != null)hMProperties.put(propName, p.getValue());
		   }
		   attributeList.put(i, hMProperties);
		   i++;
		}
//	//	//	Etape	"3" : poids relatif de 10, Conversion en WildDataSet migrable en base de données
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	
		// Migration réalisée lors du test d'encoding (miminimisation de passages).
		
		System.out.println(geomType);
//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		
	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		if(iterator!=null)iterator.close();
		if(this.dataStore!=null)this.dataStore.close();
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : isInstantiable - Récupération de l'information d'instanciation (si oui : le SHP est lisible){ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération de l'information d'instanciation (si oui : le SHP est lisible){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public Boolean isInstantiable ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Boolean WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Renvoi de la valeur
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		WILD_toReturn = this.withFiles ;
	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : freeFromTesters - Vide la mémoire du fichier, des informations de tests et des informations nécessaires à la réalisation des tests. Ne garde que le wildDataSet, les résultats de tests, les géométries initiales, le fichier de configuration.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Vide la mémoire du fichier, des informations de tests et des informations nécessaires à la réalisation des tests. Ne garde que le wildDataSet, les résultats de tests, les géométries initiales, le fichier de configuration.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void freeFromTesters ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Suppression des maps d'attributs
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		this.attributeList = null ;
		
//	//	//	Etape	"2" : poids relatif de 10, Suppression des géométries inutilisées
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Libération de la connexion PostGreSQL
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

		if(this.dataStore!=null)this.dataStore.close();
		this.dataStore = null;
		this.wfdPgConnexion.DVP_free();
		this.wfdPgConnexion = null ;
		this.typeList = null;
		this.shp_properties = null ;
		this.attributeList = null ;
		System.gc();
	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/**
 * Méthode de récupération du fichier de description, au format fixé par les métiers
 * @param schemaName
 * @param varName
 * @return
 * @throws Exception
 */
private String DVP_getWfdConf(String schemaName,String varName) throws Exception{
	WildDataSet wDs = this.wfdReferenceBook.getDataSet(schemaName+"#6D#meta");
	return wDs.DVP_getData(1, varName);
}
/**
 * Vérifie si l'ensemble des ficheirs du ficheir SHP produits permettent la lecture du fichier
 * @throws Exception
 */
private void DVP_checkWithFiles() throws Exception{
	String pathNoExt = filePath.replaceAll(this.DVP_getExtension()+"$","");
	for(String ext:checkExtents)if(!(new File(pathNoExt+ext).exists()))withFiles = false ;	
}
/**
 * Sélection du schéma de données, à partir du contenu des fichiers
 * @throws Exception
 */
private void DVP_selectWfdSchema() throws Exception{
	HashMap<String, WildDataSet> wDsHM = this.wfdReferenceBook.getDataSets();
	for(String schemaCandidate:wDsHM.keySet())if(!schemaCandidate.endsWith("#6D#meta")){
		String schemaPattern = DVP_getWfdConf(schemaCandidate,"Nom du fichier");
		for(String wfdRBDistrictCandidate:wfdRBDistrictCandidates)
		if(DVP_checkPattern(wfdRBDistrictCandidate, schemaPattern)){
			wfdRBDistrict = wfdRBDistrictCandidate ;
			this.wfdSchema = schemaCandidate ;
			break;
		}
		if(wfdRBDistrict!=null&&wfdSchema!=null)break;
	}
}
/**
 * Le nom de fichier correspond-t-il à une préconisation WFD ?
 * @param toCheck
 * @param schemaPattern
 * @return
 */
private Boolean DVP_checkPattern(String toCheck, String schemaPattern){
	String reko = schemaPattern.replace("[RBDCode]", toCheck).replace("[YYYYMMDD]","").toLowerCase();
	if(DVP_getFirstPartName().toLowerCase().contains(reko))return true ;
	return false;	
}
/**
 * Résolution de l'encodage utilisé
 * @param strToCast CHaîne de caractère à traiter
 * @return {"Encodage résolu", "valeur à l'encodage UTF8 forcé", "est-ce un problème ?", "si un réencodage a été nécessaire encodage source considéré"}
 */
private Object[] DVP_valueGuessCharset(Object strToCast){
	if(strToCast==null||!(strToCast instanceof String))return new Object[]{"UTF-8",strToCast,false,null};
	if(((String)strToCast).length()==0)return new Object[]{"UTF-8",null,false,null};
	Object[] toRet = new Object[4];
	List<String> candidates = new ArrayList();
	CharsetDetector detector;
    CharsetMatch matches[];
    strToCast = ((String)strToCast).trim();
    byte[] byteData = ((String)strToCast).getBytes();
    detector = new CharsetDetector();
    detector.setText(byteData);
    matches = detector.detectAll();
// Détermination du score max
    Long maxScore = (long) 0 ;
    for(int m = 0; m < matches.length; m += 1) if(matches[m].getConfidence()>maxScore)maxScore=(long) matches[m].getConfidence();
// Détermination du seuil d'erreur
    maxScore = (long) Math.round((float)maxScore*ceil_detectEncod) ;
    
    for(int m = 0; m < matches.length; m++) if(matches[m].getConfidence()>=maxScore)candidates.add(matches[m].getName());
// Composition de la liste des candidats
    for(String res:favorite){if(candidates.contains(res))toRet[0] = res ; break;}
    for(String res:needConvert){if(candidates.contains(res))toRet[3] = res ; break;}
// Sélection du nom d'encodage de retour
    if(toRet[0]==null) toRet[0] = detector.detect().getName();	
// Composition de l'objet de retour
    if(!toRet[0].equals(favorite[0])){
    	Charset charsetFrom = Charset.forName((String) toRet[0]);
    	Charset charsetTo = Charset.forName("UTF-8");
    	toRet[1] = new String(((String) strToCast).getBytes(charsetFrom), charsetTo);
    	toRet[2] = true  ;
    }else{
    	toRet[1] = ((String)strToCast) ;
    	toRet[2] = false ;
    }
    if(toRet[3]!=null){
    	Charset charsetFrom = Charset.forName((String) toRet[3]);
    	Charset charsetTo = Charset.forName((String) toRet[0]);
    	toRet[1] = new String(((String) strToCast).getBytes(charsetFrom), charsetTo);
    }
    
    return toRet ;
}
/**
 * Caste d'une cellule pour écriture du ficheir de log
 * @param object
 * @param string
 * @return
 */
private Object[] DVP_castForCell(Object object, String string) {
	if(object == null)return null;
	for(String clK:synonymsClass.keySet())if(Arrays.asList(synonymsClass.get(clK)).contains(string))
		try {
			return  cast_2Object(clK, object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;
}
/**
 * Accesseur à la liste d'erreurs
 * @return
 */
public HashMap<String, HashMap<Integer, HashMap<String, Object>>> DVP_getErrors(){
	return this.wfdErrorList ;
}
/**
 * Accesseur à la liste des géométries
 * @return
 */
public HashMap<Integer,Geometry> DVP_getGeometries(){
	return this.geometryList ;
}
public String DVP_getFeatureType(){
	return this.geomType;
}
/**
 * Accesseur au nom de table
 * @return
 */
public String DVP_tableName(){
	return this.pgTableName ;
}
/**
 * Accesseur au code de district
 */
public String DVP_getWfdDistrict(){
	return this.wfdRBDistrict.toLowerCase();
}
/**
 * Compose un schéma pour écriture de SHP
 * @param typeName
 * @param geomType
 * @param sridNb
 * @param columnsDs
 * @return
 * @throws SchemaException
 */
private FeatureType DVP_createSchema(
		String typeName,
		String geomType,
		String sridNb,
		WildDataSet columnsDs) throws FactoryException {
		
	geomType = geomType.toLowerCase();
	Class geomClass = null;
	if ("polyline".equals(geomType) || "multilinestring".equals(geomType)) {
		geomClass = MultiLineString.class;
	} else if ("point".equals(geomType)) {
		geomClass = Point.class;
	} else if ("multipoint".equals(geomType)) {
		geomClass = MultiPoint.class;
	} else if ("polygon".equals(geomType)) {
		geomClass = Polygon.class;
	} else if ("multipolygon".equals(geomType)) {
		geomClass = MultiPolygon.class;
	}

	final FeatureTypeBuilder ftb = new FeatureTypeBuilder();
	ftb.setName(typeName);
	ftb.add("the_geom", geomClass, CRS.forCode("EPSG:"+sridNb));
	ftb.setDefaultGeometry("the_geom");
	for(Integer i=1; i <= columnsDs.DVP_getLength() ; i++) {
		final Object fieldName = columnsDs.DVP_getData(i, "Nom");
		if (fieldName!=null) {
			final String fieldTypeName = columnsDs.DVP_getData(i, "Format").toString().toLowerCase();
			Class fieldClass = null;
			if ("string".equals(fieldTypeName) ||
				"text".equals(fieldTypeName) ||
				"texte".equals(fieldTypeName) ||
				"varchar".equals(fieldTypeName)) {
				fieldClass = String.class;
			} else if ("double".equals(fieldTypeName) ||
						"réel".equals(fieldTypeName) ||
						"réel double".equals(fieldTypeName) ||
						"integer".equals(fieldTypeName)) {
				fieldClass = Double.class;
			}
			ftb.add(fieldName.toString(),fieldClass);
		}
	}

	return ftb.buildFeatureType();
}

/**
 * Ecrit la liste de features dans un ficheir SHP, avec commit sur le disque dur
 * @param TYPE
 * @param features
 * @throws Exception
 */
private void DVP_commitAsShp(FeatureType TYPE, List<Feature> features ) throws Exception{

	try (ShapefileFeatureStore store = new ShapefileFeatureStore(fileFile.toURI())) {
		store.createFeatureType(TYPE.getName(), TYPE);
		store.addFeatures(store.getName(), features);
	}
}


}

