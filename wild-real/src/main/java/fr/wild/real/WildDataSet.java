
package fr.wild.real;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;
import static fr.wild.common.IoCommons.*;

// Dépendances, bibliothèques JAVA par exemple.
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Code généré automatiquement par l'outil Wild
 * Jeu de données "virtuel". Traitement par HashMap, plutôt réservé aux "petits volumes".
 */
public class WildDataSet extends WildClass{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildDataSet(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation
	protected HashMap<Integer,String> headerNames ; // Nom des colonnes
	
// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	protected HashMap<Integer,HashMap<String,Object>> mapContents;	//Contenu à affecter d'office{}


// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***

// CE CONSTRUCTEUR DOIT ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Fonction d'initialisation, commune à tous les constructeurs.
 * "Constructeur unique"
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_mapContents	Contenu à affecter d'office{}
 */
protected void WILD_initialize_WildDataSet(
	WildObject i_WILD_dObject,
	HashMap<Integer,HashMap<String,Object>> i_mapContents
) throws Exception {

//	// Amorce de la classe
	// Initialisation de la classe d'objet selon le schéma Wild
	WILD_dObject = i_WILD_dObject ;
	WILD_initialize_WildClass(i_WILD_dObject);
	// Préparation des variables d'invocation (considérées comme champs globaux)
	this.mapContents = i_mapContents;

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
	
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			setData (mapContents);
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
 * @param i_mapContents	Contenu à affecter d'office{}
 */

public WildDataSet(
	WildObject i_WILD_dObject,
	HashMap<Integer,HashMap<String,Object>> i_mapContents
) throws Exception{
	this.WILD_initialize_WildDataSet(i_WILD_dObject,i_mapContents);
}

/*	### NOUVELLE METHODE ###
	Méthode : add - Ajout d'un enregistrement.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ajout d'un enregistrement.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_enrContents	Nouvel enregistrement, sous forme de Map{}
 */
public void add (
	HashMap<String,Object> i_enrContents
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		if(mapContents==null)mapContents=new HashMap<Integer,HashMap<String,Object>>();
		Integer recNum = this.mapContents.size() + 1; // Numéro d'enregistrement de la nouvelle entrée
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Cast du paramètre
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		// Essentiellement : réécriture du nom de variable si existe déjà
		// Par proximité de nom : espaces, cast...
		if(i_enrContents!=null&&!i_enrContents.isEmpty())i_enrContents = DVP_siblingRecord(i_enrContents);
//	//	//	Etape	"2" : poids relatif de 10, Ajout du paramètre
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		mapContents.put(recNum,i_enrContents);
		
	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}


/*	### NOUVELLE METHODE ###
	Méthode : dropFilter - Suppression des enregistrements répondant à une condition.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Suppression des enregistrements répondant à une condition.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_filter	Motif à reconnaître pour suppression (filtre){}
 * @param i_modeFilter	Mode d'exécution, "dropIfContains", "dropIfNotContains"{}
 * @param i_searchValue	Valeur cherchée{}
 * @param i_targetedColumns	Colonnes dans lesquelles s'effectue la recherche{}
 */
public void dropFilter (
	HashMap<String,Object> i_filter,
	String i_modeFilter,
	String i_searchValue,
	List<String> i_targetedColumns
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		List<Integer> toDel = null ;
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Sélection des enregistrements
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		switch(i_modeFilter){
		case "dropIfContains":
			if(i_targetedColumns!=null)toDel = DVP_getSearchWhithin_contains(i_targetedColumns,i_searchValue);
			break;
		case "dropIfNotContains":
			if(i_targetedColumns!=null){
				List<Integer> noToDel = DVP_getSearchWhithin_contains(i_targetedColumns,i_searchValue);
				for(Integer int_hm : mapContents.keySet())if(!noToDel.contains(int_hm)){
					if(toDel == null)toDel = new ArrayList();
					toDel.add(int_hm);
				}
			}
			break;
		default:
			if(i_filter!=null)toDel = DVP_getPatternMatchers(i_filter);
			break;
		}
		
//	//	//	Etape	"2" : poids relatif de 10, Suppression des entrées
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		for(Integer numEnr:toDel)mapContents.remove(numEnr);
		DVP_reorder();
		Integer nbdel = (toDel==null)?0:toDel.size() ;
//	//	//	Output	"dropFilter_output1" : Nombre d'enregistrements supprimés (Integer)
		this.WILD_setOutput("dropFilter_output1",nbdel); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
/**
 * Suppression des enregistrements répondant à une condition.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_filter	Motif à reconnaître pour suppression (filtre) {}
 */
 public void dropFilter (
	HashMap<String,Object> i_filter
	)throws Exception{dropFilter(i_filter,null,null,null);
}
/**
 * Suppression des enregistrements répondant à une condition.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_modeFilter	Mode d'exécution, "dropIfContains", "dropIfNotContains" {}
 * @param i_searchValue	Valeur cherchée {}
 * @param i_targetedColumns	Colonnes dans lesquelles s'effectue la recherche {}
 */
 public void dropFilter (
	String i_modeFilter,
	String i_searchValue,
	List<String> i_targetedColumns
	)throws Exception{dropFilter(null,i_modeFilter,i_searchValue,i_targetedColumns);
}
 
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Suppression d'un enregistrement.(WildDataSet){ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_filter	Motif à reconnaître pour suppression (filtre){}
 */
public void drop (
	HashMap<String,Object> i_filter
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		i_filter = DVP_siblingRecord(i_filter);
//	//	//	Etape	"1" : poids relatif de 10, Sélection de l'enregistrement
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		Integer score_del = null;
		for(Integer int_hm : mapContents.keySet()){
			for(String filter_key : i_filter.keySet())if(
					(i_filter.get(filter_key) == null && mapContents.get(int_hm).get(filter_key) == null)
					||i_filter.get(filter_key).equals(mapContents.get(int_hm).get(filter_key))
			){
				score_del = int_hm;
				break;
			}
			if(score_del != null)break;				
		}

//	//	//	Etape	"2" : poids relatif de 10, Suppression de l'entrée
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(score_del != null)mapContents.remove(score_del);
		DVP_reorder();
	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : update - Mise à jour d'un enregistrement{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Mise à jour d'un enregistrement{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_filtre	Motif à reconnaître pour affection (filtre){}
 * @param i_newEnr	Données à mettre à jour{}
 */
public void update (
	HashMap<String,Object> i_filtre,
	HashMap<String,Object> i_newEnr
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		i_filtre = DVP_siblingRecord(i_filtre);
		i_newEnr = DVP_siblingRecord(i_newEnr);
//	//	//	Etape	"1" : poids relatif de 10, Sélection de l'enregistrement
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		List<Integer> toUpdate = DVP_getPatternMatchers(i_filtre);
//	//	//	Etape	"2" : poids relatif de 10, Mise à jour de l'entrée
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		for(Integer numEnr:toUpdate){
			// On n'actualise que les nouvelles valeurs de champs
			HashMap<String, Object> currentRecord = mapContents.get(numEnr);
			for(String newEnrEntry:i_newEnr.keySet())currentRecord.put(newEnrEntry, i_newEnr.get(newEnrEntry));
			mapContents.remove(numEnr);
			mapContents.put(numEnr, currentRecord);
		}
	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : addColumn - Ajoute une colonne, contenant une valeur précise{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ajoute une colonne, contenant une valeur précise{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_columnName	Nom de la colonne{}
 * @param i_colContents	Contenu de la colonne{}
 */
public void addColumn (
	String i_columnName,
	HashMap<Integer,Object> i_colContents
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		Integer colAffected ; // NUméro de la nouvelle colonne
		Integer maxRowNum = i_colContents.size() ; // Nombre maximal d'enregistrements à mettre à jour
//	//	//	Etape	"1" : poids relatif de 20, Cast du WildDataSet (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		Object[] newCol = DVP_siblingColumn(i_columnName);
		colAffected = (Integer) ((newCol[0] == null)?headerNames.size() + 1 : newCol[0]);
		i_columnName = (String) newCol[1];
//	//	//	Etape	"2" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 50, Ecriture dufichier (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		HashMap<Integer,HashMap<String,Object>> mapContents_reaffect = mapContents ; // Pour affectation dynamique
		for(Integer i_record:mapContents.keySet()){
			HashMap<String,Object> mapContents_record = mapContents.get(i_record);
			if(maxRowNum >= i_record)mapContents_record.put(i_columnName, i_colContents.get(i_record));
			mapContents_reaffect.put(i_record, mapContents_record);
		}
		mapContents = mapContents_reaffect ;
//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		this.headerNames.put(headerNames.size()+1, i_columnName);
//	//	//	Output	"addColumn_output1" : Position de la colonne rajoutée (Integer)
		this.WILD_setOutput("addColumn_output1",colAffected); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}





/*	### NOUVELLE METHODE ###
	Méthode : addColumnJoinBy - Ajoute une colonne, à partir d'une matrice, avec jointure sur un nom de colonne donné{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ajoute une colonne, à partir d'une matrice, avec jointure sur un nom de colonne donné{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_columnName	Nom de la colonne à ajouter{}
 * @param i_columnCompare_dataset	Nom de la colonne comparée, dataset{}
 * @param i_inputJoin	Valeurs de comparaison{}
 * @param i_colContents	Contenu de la colonne{}
 */
public void addColumnJoinBy (
	String i_columnName,
	String i_columnCompare_dataset,
	HashMap<Integer,Object> i_inputJoin,
	HashMap<Integer,Object> i_colContents
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		Integer colAffected ; // NUméro de la nouvelle colonne
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		
//	//	//	Etape	"1" : poids relatif de 20, Cast du WildDataSet (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		Object[] newCol = DVP_siblingColumn(i_columnName);
		colAffected = (Integer) ((newCol[0] == null)?headerNames.size() + 1 : newCol[0]);
		i_columnName = (String) newCol[1];
//	//	//	Etape	"2" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		
//	//	//	Etape	"3" : poids relatif de 50, Ecriture dufichier (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

	// Pour chaque entrée, on check la correspondance de la valeur de comparaison, et le cas échéant on ajoute la colonne
		HashMap<Integer,HashMap<String,Object>> mapContents_reaffect = mapContents ; // Pour affectation dynamique
		for(Integer intRecord : mapContents.keySet()){
			HashMap<String,Object > mapContents_record = mapContents.get(intRecord);
			for(Integer iJ:i_inputJoin.keySet()){
				if(
						(i_inputJoin.get(iJ)==null&&mapContents.get(intRecord).get(i_columnCompare_dataset)==null)
						||(i_inputJoin.get(iJ).equals(mapContents.get(intRecord).get(i_columnCompare_dataset)))
				){
					mapContents_record.put(i_columnName, i_colContents.get(iJ));
					mapContents_reaffect.put(intRecord, mapContents_record);
					break;
				}
			}
		}
		mapContents = mapContents_reaffect ;
//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		this.headerNames.put(headerNames.size()+1, i_columnName);
//	//	//	Output	"addColumnJoinBy_output1" : Position de la colonne rajoutée (Integer)
		this.WILD_setOutput("addColumnJoinBy_output1", colAffected); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}


/*	### NOUVELLE METHODE ###
	Méthode : dropColumn - Supprime une colonne{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Supprime une colonne{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_columnName	Nom de la colonne{}
 */
public void dropColumn (
	String i_columnName
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		Integer colAffected ; // NUméro de la nouvelle colonne
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Cast du WildDataSet (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		Object[] newCol = DVP_siblingColumn(i_columnName);
		colAffected = (Integer) ((newCol[0] == null)?null : newCol[0]);
		i_columnName = (String) newCol[1];
//	//	//	Etape	"2" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		
//	//	//	Etape	"3" : poids relatif de 50, Ecriture dufichier (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		HashMap<Integer,HashMap<String,Object>> mapContents_reaffect = mapContents ; // Pour affectation dynamique
		if(colAffected!=null)for(Integer intRecord : mapContents.keySet()){
			HashMap<String,Object > mapContents_record = mapContents.get(intRecord);
			mapContents_record.remove(i_columnName);
			mapContents_reaffect.put(intRecord, mapContents_record);
		}
		mapContents = mapContents_reaffect ;
//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		for(Integer k:this.headerNames.keySet())if(headerNames.get(k).equals(i_columnName)){
			headerNames.remove(k);
			break;
		}
//	//	//	Output	"dropColumn_output1" : Position de la colonne supprimée (Integer)
		this.WILD_setOutput("dropColumn_output1",colAffected); // Ne pas modifier
		
	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : replaceValue - Remplace une valeur dans la totalité du fichier{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Remplace une valeur dans la totalité du fichier{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_formerValue	Valeur à remplacer{}
 * @param i_newValue	Valeur de remplacement{}
 */
public void replaceValue (
	Object i_formerValue,
	Object i_newValue
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		Integer replaceNb = 0 ; // Nombre de remplacements
//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		HashMap<Integer,HashMap<String,Object>> mapContents_reaffect = mapContents ; // Pour affectation dynamique
		
		for(Integer intRecord : mapContents.keySet()){
			HashMap<String,Object > mapContents_record = mapContents.get(intRecord);
			for(String ks:mapContents.get(intRecord).keySet()){
				Object value = mapContents.get(intRecord) ;
				if(value instanceof String){
					if(((String) value).contains((String)i_formerValue)){
						replaceNb++ ;
						value = ((String) value).replace((String)i_formerValue, (String)i_newValue);
					}
				}
				else if(value.equals(i_formerValue)){
					replaceNb++ ;
					value = i_newValue ;
				}
				mapContents_record.put(ks,value);
			}	
			mapContents_reaffect.put(intRecord, mapContents_record);
		}
		mapContents = mapContents_reaffect ;
//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"replaceValue_output1" : Nombre de remplacements (Integer)
		this.WILD_setOutput("replaceValue_output1",replaceNb); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : replaceValueFilterBy - Remplace une valeur dans l'emprise indiquée{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Remplace une valeur dans l'emprise indiquée{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_formerValue	Valeur à remplacer{}
 * @param i_newValue	Valeur de remplacement{}
 * @param i_enrContents	Nouvel enregistrement, sous forme de Map{}
 */
public void replaceValueFilterBy (
	Object i_formerValue,
	Object i_newValue,
	HashMap<String,Object> i_enrContents
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		Integer replaceNb = 0 ; // Nombre de remplacements
		List<Integer> toReplaceList ; // résultats du filtre (numéros de ligne à mettre à jour)
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		i_enrContents = DVP_siblingRecord(i_enrContents);
//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec sélection et réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		toReplaceList = DVP_getPatternMatchers(i_enrContents);	
//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		HashMap<Integer,HashMap<String,Object>> mapContents_reaffect = mapContents ; // Pour affectation dynamique
		for(Integer intRecord :toReplaceList){
			HashMap<String,Object > mapContents_record = mapContents.get(intRecord);
			for(String ks:mapContents.get(intRecord).keySet()){
				Object value = mapContents.get(intRecord) ;
				if(value instanceof String){
					if(((String) value).contains((String)i_formerValue)){
						replaceNb++ ;
						value = ((String) value).replace((String)i_formerValue, (String)i_newValue);
					}
				}
				else if(value.equals(i_formerValue)){
					replaceNb++ ;
					value = i_newValue ;
				}
				mapContents_record.put(ks,value);
			}	
			mapContents_reaffect.put(intRecord, mapContents_record);
		}
		mapContents = mapContents_reaffect ;
//	//	//	Output	"replaceValueFilterBy_output1" : Nombre de remplacements (Integer)
	this.WILD_setOutput("replaceValueFilterBy_output1",replaceNb); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : concatenate - Concatène deux colonnes{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Concatène deux colonnes{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_col_1	Nom de colonne devant{}
 * @param i_col_2	Nom de colonne derrière{}
 * @param i_link	Opérateur de liaison{}
 */
public void concatenate (
	String i_col_1,
	String i_col_2,
	String i_link
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		Object[] cols = DVP_siblingColumn(i_col_1+i_link+i_col_2);
		String concatColName =	(String) cols[1] ;	
		
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		i_col_1 = (String)DVP_siblingColumn(i_col_1)[1];
		i_col_2 = (String)DVP_siblingColumn(i_col_2)[1];
//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		HashMap<Integer,HashMap<String,Object>> mapContents_reaffect = mapContents ; // Pour affectation dynamique
		for(Integer intRecord :mapContents.keySet()){
			HashMap<String,Object > mapContents_record = mapContents.get(intRecord);
			String newVar ;
			if(mapContents_record.get(i_col_1)==null&mapContents_record.get(i_col_2)==null)newVar=null;
			else if(mapContents_record.get(i_col_1)==null)newVar = mapContents_record.get(i_col_2).toString();
			else if(mapContents_record.get(i_col_2)==null)newVar = mapContents_record.get(i_col_1).toString();
			else newVar = mapContents_record.get(i_col_1).toString() + i_link + mapContents_record.get(i_col_2).toString();
			mapContents_record.put(concatColName,newVar);
		}
		mapContents = mapContents_reaffect ;
		this.headerNames.put(this.headerNames.size()+1, concatColName); 
//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"concatenate_output1" : Position de la colonne rajoutée (Integer)
	this.WILD_setOutput("concatenate_output1",(Integer)cols[0]); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : concatenateFilterBy - Concatène deux colonnes, si les valeurs correspondent au filtre{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Concatène deux colonnes, si les valeurs correspondent au filtre{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_col_1	Nom de colonne devant{}
 * @param i_col_2	Nom de colonne derrière{}
 * @param i_link	Opérateur de liaison{}
 * @param i_colContents	Motif à reconnaître pour affectation (filtre){}
 */
public void concatenateFilterBy (
	String i_col_1,
	String i_col_2,
	String i_link,
	HashMap<String,Object> i_colContents
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		Object[] cols = DVP_siblingColumn(i_col_1+i_link+i_col_2);
		String concatColName =	(String) cols[1] ;	
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		i_colContents = DVP_siblingRecord(i_colContents);
		List<Integer> toConcat = DVP_getPatternMatchers(i_colContents);	
		i_col_1 = (String)DVP_siblingColumn(i_col_1)[1];
		i_col_2 = (String)DVP_siblingColumn(i_col_2)[1];
//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec sélection et réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		HashMap<Integer,HashMap<String,Object>> mapContents_reaffect = mapContents ; // Pour affectation dynamique
		for(Integer intRecord :toConcat){
			HashMap<String,Object > mapContents_record = mapContents.get(intRecord);
			String newVar ;
			if(mapContents_record.get(i_col_1)==null&mapContents_record.get(i_col_2)==null)newVar=null;
			else if(mapContents_record.get(i_col_1)==null)newVar = mapContents_record.get(i_col_2).toString();
			else if(mapContents_record.get(i_col_2)==null)newVar = mapContents_record.get(i_col_1).toString();
			else newVar = mapContents_record.get(i_col_1).toString() + i_link + mapContents_record.get(i_col_2).toString();
			mapContents_record.put(concatColName,newVar);
		}
		mapContents = mapContents_reaffect ;
//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"concatenateFilterBy_output1" : Position de la colonne rajoutée (Integer)
		this.WILD_setOutput("concatenateFilterBy_output1",cols[0]); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : setData - Ajoute les données à la liste du WildDataSet{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Ajoute les données à la liste du WildDataSet{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_mapContents	Contenu à affecter d'office{}
 */
public void setData (
	HashMap<Integer,HashMap<String,Object>> i_mapContents
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***
		List<String> headerNames_ar = new ArrayList<String>(); // Liste pour identification des en-tête non rencontrées
		if(headerNames==null)headerNames = new HashMap();
		Integer i_col = 1 ; // Incrément pour ordre de la colonne A PARTIR DE 1
//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Affectation de la variable
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		mapContents = i_mapContents ;
//	//	//	Etape	"2" : poids relatif de 10, Elaboration des statistiques
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	// Parcours des en-têtes existant pour s'assurer de ne pas en rater
		if(mapContents!=null)for(Integer int_hm : mapContents.keySet())
	// Comparaison sur MINUSCULES, enregistrement sur première occurence rencontrées, sans cast
			for(String header_hm : mapContents.get(int_hm).keySet())
				if(!headerNames_ar.contains(header_hm.toLowerCase())){
					headerNames_ar.add(header_hm.toLowerCase());
					headerNames.put(i_col++,header_hm);
				}
//	//	//	Output	"setData_output1" : Nombre d'enregistrements, nombre de colonnes (Integer[])
	//	this.WILD_setOutput("setData_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}

/*	### NOUVELLE METHODE ###
	Méthode : flatten - "Aplatit" le fichier vers un format texte séparateur{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * "Aplatit" le fichier vers un format texte séparateur{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @return	{}
 */
public String flatten ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Composition du contenu
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
	Méthode : flattenToFile - "Aplatit" le fichier vers un format texte séparateur, l'enregistre dans un fichier de sortie{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * "Aplatit" le fichier vers un format texte séparateur, l'enregistre dans un fichier de sortie{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_fileName_OUT	Nom du fichier de sortie{}
 * @param i_keepFile	Suppression du fichier initial{}
 * @return	{}
 */
public String flattenToFile (
	String i_fileName_OUT,
	Boolean i_keepFile
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Création des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 50, Parcours du jeu de données avec réécriture (++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Output	"flattenToFile_output1" : Adresse du fichier (String)
	//	this.WILD_setOutput("flattenToFile_output1",/*** Valeur à remonter en output ***/); // Ne pas modifier

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
 * "Aplatit" le fichier vers un format texte séparateur, l'enregistre dans un fichier de sortie{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_fileName_OUT	Nom du fichier de sortie {}
 * @return	Adresse du fichier (String) {}
 */
 public String flattenToFile (
	String i_fileName_OUT
	)throws Exception{
return flattenToFile(i_fileName_OUT,false);
} 


/*	### NOUVELLE METHODE ###
	Méthode : setHeaders - Actualisation des noms de colonnes{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Actualisation des noms de colonnes{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_newColumns	Nouveaux noms de colonne{}
 */
public void setHeaders (
	String[] i_newColumns
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Mise à jour de la variable
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(i_newColumns != null){
			headerNames =  new HashMap();
			for(Integer i=0; i_newColumns.length > i ; i ++)headerNames.put(i+1, i_newColumns[i]);
		}	
//	//	//	Etape	"2" : poids relatif de 10, Mise à jour des enregistrements
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(i_newColumns != null&&this.mapContents!=null&&this.mapContents.size()>0){
			HashMap<Integer, HashMap<String, Object>> formerMap = new HashMap(this.mapContents) ;
			this.mapContents = new HashMap();
			for(Integer k:formerMap.keySet()){
				HashMap value = this.DVP_siblingRecord(formerMap.get(k));
				this.mapContents.put(k, value );
			}
		}	
		
	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
} 

/*	### NOUVELLE METHODE ###
	Méthode : getColumn - Récupération d'une colonne, avec n*[numéro d'enregistrement et la valeur voulue]{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération d'une colonne, avec n*[numéro d'enregistrement et la valeur voulue]{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_getColumn	Nom de la colonne à récupérer{}
 * @return	{}
 */
public HashMap<Integer,Object> getColumn (
	String i_getColumn
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	HashMap<Integer,Object> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Sélection de l'enregistrement
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		for(Integer k:this.headerNames.keySet())if(this.headerNames.get(k).equals(i_getColumn)){
			WILD_toReturn = new HashMap();
			break;
		}
//	//	//	Etape	"2" : poids relatif de 10, Mise à jour de l'entrée
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(WILD_toReturn!=null)for(Integer k:this.mapContents.keySet())if(mapContents.get(k)!=null) WILD_toReturn.put(k, mapContents.get(k));
	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier
		return WILD_toReturn ; // Ne pas modifier
	}
}



/*	### NOUVELLE METHODE ###
	Méthode : findEqual - Récupération des numéros de lignes des enregistrements, correspondant à une égalité stricte, selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une égalité stricte, selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne{}
 * @param i_colValue	Valeur d'objet de comparaison{}
 * @param i_nullSens	Sensible aux valeurs vides{}
 * @param i_caseSens	Sensible à la casse{}
 * @param i_specSens	Sensible aux caractères spéciaux{}
 * @return	{}
 */
public Integer[] findEqual (
	String i_colName,
	Object i_colValue,
	Boolean i_nullSens,
	Boolean i_caseSens,
	Boolean i_specSens
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	List<Integer> listIndexes = new ArrayList();
//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		for(Integer index:this.mapContents.keySet()){
			HashMap<String, Object> record = this.mapContents.get(index);
			if(record == null)continue ;
			Object recordValue = record.get(i_colName);
			if(check_equal(recordValue,i_colValue,i_nullSens,i_caseSens,i_specSens))listIndexes.add(index);
		}
//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		WILD_toReturn = listIndexes.toArray(new Integer[listIndexes.size()]);
//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		listIndexes = null;		
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
 * Récupération des numéros de lignes des enregistrements, correspondant à une égalité stricte, selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne {}
 * @param i_colValue	Valeur d'objet de comparaison {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findEqual (
	String i_colName,
	Object i_colValue
	)throws Exception{
return findEqual(i_colName,i_colValue,false,false,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findContain - Récupération des numéros de lignes des enregistrements, correspondant à une condition de contenu (la cible contient le terme recherché), selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non. Ne porte que sur les chaînes de caractère.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition de contenu (la cible contient le terme recherché), selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non. Ne porte que sur les chaînes de caractère.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne{}
 * @param i_colValue	Valeur d'objet de comparaison{}
 * @param i_nullSens	Sensible aux valeurs vides{}
 * @param i_caseSens	Sensible à la casse{}
 * @param i_specSens	Sensible aux caractères spéciaux{}
 * @return	{}
 */
public Integer[] findContain (
	String i_colName,
	Object i_colValue,
	Boolean i_nullSens,
	Boolean i_caseSens,
	Boolean i_specSens
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		List<Integer> listIndexes = new ArrayList();
//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		for(Integer index:this.mapContents.keySet()){
			HashMap<String, Object> record = this.mapContents.get(index);
			if(record == null)continue ;
			Object recordValue = record.get(i_colName);
			if(check_contain(recordValue,i_colValue,i_nullSens,i_caseSens,i_specSens))listIndexes.add(index);
		}
//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		WILD_toReturn = listIndexes.toArray(new Integer[listIndexes.size()]);
//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		listIndexes=null;
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
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition de contenu (la cible contient le terme recherché), selon options de suppression des caractères non signifiants en début, en fin ou en milieux de caractères, sensible à la casse ou non, sensible au caractère spéciaux dont accents ou non. Ne porte que sur les chaînes de caractère.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne {}
 * @param i_colValue	Valeur d'objet de comparaison {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findContain (
	String i_colName,
	Object i_colValue
	)throws Exception{
return findContain(i_colName,i_colValue,false,false,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findRegex - Récupération des numéros de lignes des enregistrements, correspondant à un terme recherché (la cible répond à une expression régulière comportant le terme recherché).{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à un terme recherché (la cible répond à une expression régulière comportant le terme recherché).{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne{}
 * @param i_colRegexp	Expression régulière testée{}
 * @param i_nullSens	Sensible aux valeurs vides{}
 * @param i_caseSens	Sensible à la casse{}
 * @param i_specSens	Sensible aux caractères spéciaux{}
 * @return	{}
 */
public Integer[] findRegex (
	String i_colName,
	String i_colRegexp,
	Boolean i_nullSens,
	Boolean i_caseSens,
	Boolean i_specSens
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		List<Integer> listIndexes = new ArrayList();
//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		for(Integer index:this.mapContents.keySet()){
			HashMap<String, Object> record = this.mapContents.get(index);
			if(record == null)continue ;
			Object recordValue = record.get(i_colName);
			if(check_regex(recordValue,i_colRegexp,i_nullSens,i_caseSens,i_specSens))listIndexes.add(index);
		}
//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		WILD_toReturn = listIndexes.toArray(new Integer[listIndexes.size()]);
//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		listIndexes = null;
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
 * Récupération des numéros de lignes des enregistrements, correspondant à un terme recherché (la cible répond à une expression régulière comportant le terme recherché).{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne {}
 * @param i_colRegexp	Expression régulière testée {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findRegex (
	String i_colName,
	String i_colRegexp
	)throws Exception{
return findRegex(i_colName,i_colRegexp,false,false,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findWithin - Récupération des numéros de lignes des enregistrements, dont une valeur précise est contenue entre deux bornes (incluses). Pour les champs de type chaîne de caractères, l’ordre alphabétique est retenu (sensible à la casse, pas de suppression des caractères non signifiants). Une valeur « vide » signifie une absence de borne.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, dont une valeur précise est contenue entre deux bornes (incluses). Pour les champs de type chaîne de caractères, l’ordre alphabétique est retenu (sensible à la casse, pas de suppression des caractères non signifiants). Une valeur « vide » signifie une absence de borne.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne{}
 * @param i_minValue	Valeur inférieure{}
 * @param i_minExclude	Est exclu par le bas ?{}
 * @param i_maxValue	Valeur supérieure{}
 * @param i_maxExclude	Est exclu par le haut ?{}
 * @return	{}
 */
public Integer[] findWithin (
	String i_colName,
	Object i_minValue,
	Boolean i_minExclude,
	Object i_maxValue,
	Boolean i_maxExclude
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		List<Integer> listIndexes = new ArrayList();
//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		for(Integer index:this.mapContents.keySet()){
			HashMap<String, Object> record = this.mapContents.get(index);
			if(record == null)continue ;
			Object recordValue = record.get(i_colName);
			if(check_within(recordValue, i_minValue,i_minExclude,i_maxValue,i_maxExclude))listIndexes.add(index);
		}
//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		WILD_toReturn = listIndexes.toArray(new Integer[listIndexes.size()]);
//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		listIndexes = null;
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
 * Récupération des numéros de lignes des enregistrements, dont une valeur précise est contenue entre deux bornes (incluses). Pour les champs de type chaîne de caractères, l’ordre alphabétique est retenu (sensible à la casse, pas de suppression des caractères non signifiants). Une valeur « vide » signifie une absence de borne.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne {}
 * @param i_minValue	Valeur inférieure {}
 * @param i_maxValue	Valeur supérieure {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findWithin (
	String i_colName,
	Object i_minValue,
	Object i_maxValue
	)throws Exception{
return findWithin(i_colName,i_minValue,false,i_maxValue,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findCompare - Récupération des numéros de lignes des enregistrements, correspondant à une comparaison mathématique (opérateur de comparaison mathématique : supériorité / infériorité, égalité -  numérique ou date). Choix de l’exclusion ou de l’inclusion des bornes.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une comparaison mathématique (opérateur de comparaison mathématique : supériorité / infériorité, égalité -  numérique ou date). Choix de l’exclusion ou de l’inclusion des bornes.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne{}
 * @param i_minValue	Valeur inférieure{}
 * @param i_minExclude	Est exclu par le bas ?{}
 * @param i_maxValue	Valeur supérieure{}
 * @param i_maxExclude	Est exclu par le haut ?{}
 * @return	{}
 */
public Integer[] findCompare (
	String i_colName,
	Object i_minValue,
	Boolean i_minExclude,
	Object i_maxValue,
	Boolean i_maxExclude
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		List<Integer> listIndexes = new ArrayList();
//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		for(Integer index:this.mapContents.keySet()){
			HashMap<String, Object> record = this.mapContents.get(index);
			if(record == null)continue ;
			Object recordValue = record.get(i_colName);
			if(check_compare(recordValue, i_minValue,i_minExclude,i_maxValue,i_maxExclude))listIndexes.add(index);
		}
//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		WILD_toReturn = listIndexes.toArray(new Integer[listIndexes.size()]);
//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		listIndexes = null;
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
 * Récupération des numéros de lignes des enregistrements, correspondant à une comparaison mathématique (opérateur de comparaison mathématique : supériorité / infériorité, égalité -  numérique ou date). Choix de l’exclusion ou de l’inclusion des bornes.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne {}
 * @param i_minValue	Valeur inférieure {}
 * @param i_maxValue	Valeur supérieure {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findCompare (
	String i_colName,
	Object i_minValue,
	Object i_maxValue
	)throws Exception{
return findCompare(i_colName,i_minValue,false,i_maxValue,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findLines - Récupération des numéros de lignes des enregistrements, combinant l’ensemble des conditions précédemment listées. Une liste des indexations des conditions est fournie  en Tableau 1, et permet le stockage des conditions dans une table de paramètres. Chacune des conditions répond soit à une condition OU, soit à une condition ET (opérateur de combinaison de condition), à l’exception de la première condition listée. Chacune des conditions répond soit à une condition positive (on sélection les numéros de lignes répondant à la condition) soit négative (on exclut sélection les numéros de lignes répondant à la condition). Une condition négative est préfixée du caractère « ! ».{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, combinant l’ensemble des conditions précédemment listées. Une liste des indexations des conditions est fournie  en Tableau 1, et permet le stockage des conditions dans une table de paramètres. Chacune des conditions répond soit à une condition OU, soit à une condition ET (opérateur de combinaison de condition), à l’exception de la première condition listée. Chacune des conditions répond soit à une condition positive (on sélection les numéros de lignes répondant à la condition) soit négative (on exclut sélection les numéros de lignes répondant à la condition). Une condition négative est préfixée du caractère « ! ».{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_conditionList	liste des conditions{}
 * @return	{}
 */
public Integer[] findLines (
	Map<Integer, Map<String,Object>> i_conditionList
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

	Integer[] allStrands = (Integer[]) this.mapContents.keySet().toArray(new Integer[mapContents.size()]);
	Integer[] keepStrands = null;

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	for(Integer aCondition:i_conditionList.keySet()){
	// Pour chaque condition, préparation des paramètres génériques contenus dans la map
		Map<String,Object> condition = i_conditionList.get(aCondition);
		Integer[] baseStrands = allStrands ;
		String CombinOperator = (condition.get("CombinOperator")!=null)?((String)condition.get("CombinOperator")).toLowerCase():null;
		String WildCode = (condition.get("WildCode")!=null)?((String)condition.get("WildCode")).toLowerCase():null;
		String ColumnName = (condition.get("ColumnName")!=null)?((String)condition.get("ColumnName")):null;
	// Préparation d'une liste de recherche, éventuellement limitée par les précédentes requêtes
		if(aCondition!=1&&CombinOperator.equals("and"))baseStrands = keepStrands ;
		HashMap<Integer,HashMap<String,Object>> newMapContents = new HashMap();
		for(Integer anIndex:baseStrands)newMapContents.put(anIndex,  this.mapContents.get(anIndex));
	// Construction d'un dataSet pour récupération des méthodes précédemment développées
		WildDataSet newWildDataSet = new WildDataSet(WILD_dObject,null);
		newWildDataSet.DVP_feedNoIndex(newMapContents,this.DVP_getHeader());
	// Requête dans selectionStrands, en fonction du WildCode communiqué
		Integer[] selectionStrands = null ;
		switch (WildCode.replaceAll("^!","")){
		case "equal":
			Object ColumnValue = (condition.get("ColumnValue")!=null)?condition.get("ColumnValue"):null;
			Boolean NullSens = (condition.get("NullSens")!=null)?((Boolean)condition.get("NullSens")):null;
			Boolean CaseSens = (condition.get("CaseSens")!=null)?((Boolean)condition.get("CaseSens")):null;
			Boolean SpecSens = (condition.get("SpecSens")!=null)?((Boolean)condition.get("SpecSens")):null;
			selectionStrands = newWildDataSet.findEqual(ColumnName, ColumnValue, NullSens, CaseSens, SpecSens);
		break;
		case "contain":
			Object ColumnValue1 = (condition.get("ColumnValue")!=null)?condition.get("ColumnValue"):null;
			Boolean NullSens1 = (condition.get("NullSens")!=null)?((Boolean)condition.get("NullSens")):null;
			Boolean CaseSens1 = (condition.get("CaseSens")!=null)?((Boolean)condition.get("CaseSens")):null;
			Boolean SpecSens1 = (condition.get("SpecSens")!=null)?((Boolean)condition.get("SpecSens")):null;
			selectionStrands = newWildDataSet.findContain(ColumnName, ColumnValue1, NullSens1, CaseSens1, SpecSens1);
			break;
		case "regex":
			String ColumnValue2 = (condition.get("ColumnValue")!=null)?((String)condition.get("ColumnValue")):null;
			Boolean NullSens2 = (condition.get("NullSens")!=null)?((Boolean)condition.get("NullSens")):null;
			Boolean CaseSens2 = (condition.get("CaseSens")!=null)?((Boolean)condition.get("CaseSens")):null;
			Boolean SpecSens2 = (condition.get("SpecSens")!=null)?((Boolean)condition.get("SpecSens")):null;
			selectionStrands = newWildDataSet.findRegex(ColumnName, ColumnValue2, NullSens2, CaseSens2, SpecSens2);
			break;
		case "within":
			Object ColumnMinValue = (condition.get("ColumnMinValue")!=null)?condition.get("ColumnMinValue"):null;
			Object ColumnMaxValue = (condition.get("ColumnMaxValue")!=null)?condition.get("ColumnMaxValue"):null;
			Boolean ColumnMinValueInclude = (condition.get("ColumnMinValueInclude")!=null)?((Boolean)condition.get("ColumnMinValueInclude")):null;
			Boolean ColumnMaxValueInclude = (condition.get("ColumnMaxValueInclude")!=null)?((Boolean)condition.get("ColumnMaxValueInclude")):null;
			selectionStrands = newWildDataSet.findWithin(ColumnName, ColumnMinValue, ColumnMinValueInclude, ColumnMaxValue, ColumnMaxValueInclude);
			break;
		case "compare":
			Object ColumnMinValue2 = (condition.get("ColumnMinValue")!=null)?condition.get("ColumnMinValue"):null;
			Object ColumnMaxValue2 = (condition.get("ColumnMaxValue")!=null)?condition.get("ColumnMaxValue"):null;
			Boolean ColumnMinValueInclude2 = (condition.get("ColumnMinValueInclude")!=null)?((Boolean)condition.get("ColumnMinValueInclude")):null;
			Boolean ColumnMaxValueInclude2 = (condition.get("ColumnMaxValueInclude")!=null)?((Boolean)condition.get("ColumnMaxValueInclude")):null;
			selectionStrands = newWildDataSet.findCompare(ColumnName, ColumnMinValue2, ColumnMinValueInclude2, ColumnMaxValue2, ColumnMaxValueInclude2);
			break;
		}
	// Gestion des cas contraires à la condition, c'est-à-dire dont le WildCode est préffixé de "!"		
		if(WildCode.startsWith("!")){
			List<Integer> selectionStrands_list = Arrays.asList(selectionStrands);
			List<Integer> selectionStrandsExcept_list = new ArrayList();
			for(Integer index:baseStrands)if(!selectionStrands_list.contains(index))selectionStrandsExcept_list.add(index);
			selectionStrands = selectionStrandsExcept_list.toArray(new Integer[selectionStrandsExcept_list.size()]);
		}		
	// Combinaison des résultats en fonction de l'opérateur
		if(aCondition==1||CombinOperator.equals("and")||keepStrands==null) keepStrands = selectionStrands ;
		else {
			// Arrays.asList() ne fonctionne pas ! Unsupported operation
			List<Integer> keepStrands_list = new ArrayList();
			for(Integer got:keepStrands)keepStrands_list.add(got);
			for(Integer select:selectionStrands)if(!keepStrands_list.contains(select))keepStrands_list.add(select);
			keepStrands = keepStrands_list.toArray(new Integer[keepStrands_list.size()]);
		}
	}
//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		WILD_toReturn = keepStrands ;
//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
	Méthode : calculate - Calcul de statistiques sur les valeurs des enregistrements, combinant l’ensemble des conditions précédemment listées. La liste des statistiques retenues est présentée en Tableau 1. Le Tableau 2 plus loin renvoie à un index des conditions pour implémentation.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Calcul de statistiques sur les valeurs des enregistrements, combinant l’ensemble des conditions précédemment listées. La liste des statistiques retenues est présentée en Tableau 1. Le Tableau 2 plus loin renvoie à un index des conditions pour implémentation.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Liste des conditions{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre associé à la statistique{}
 * @return	{}
 */
public Map<String, Object> calculate (
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Map<String, Object> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		Map<Integer, Map<String,Object>> newContents = this.findRecords(i_conditionList);
//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
// // Récupération de la colonne de valeurs
// // Avec un cast pour être sûrs du type
	List<Object> calculList = new ArrayList<Object>();
	for(Integer index : newContents.keySet()){
		String className = cast_getSimpleClass(newContents.get(index).get(i_colName));
		calculList.add(cast_2Object(className, newContents.get(index).get(i_colName))[1]);
	}
// // Comportement propre au nom de la statistique communiqué par i_statKey
	WILD_toReturn = new HashMap();
switch(i_statKey.toLowerCase()){
case "maximum":
	WILD_toReturn.put("result",stats_n(calculList,calculList.size()-1));
	break;
case "minimum":
	WILD_toReturn.put("result",stats_n(calculList,0));
	break;	
case "percentile_x":
	Integer indexPerc = (int) Math.round(((double)i_statParam / (double)100) * (calculList.size()));
	if(indexPerc==0) indexPerc = 1 ;	
	WILD_toReturn.put("result",stats_n(calculList,indexPerc-1));
	break;	
case "median":
	Integer indexMed = (int) (0.5 * (calculList.size()));
	if(indexMed==0) indexMed = 1 ;
	WILD_toReturn.put("result",stats_n(calculList,indexMed-1));
	break;	
case "avg":
	WILD_toReturn.put("result",stats_avg(calculList));
	break;	
case "geom_avg":
	WILD_toReturn.put("result",stats_geomAvg(calculList));
	break;	
case "var":
	WILD_toReturn.put("result",stats_var(calculList));
	break;	
case "std_dev":
	WILD_toReturn.put("result",stats_stdDev(calculList));
	break;	
case "significant_nb":
	Integer significant_nb = 0 ;
	for(Object value:calculList)if(value!=null&&!value.toString().trim().isEmpty())significant_nb++;	
	WILD_toReturn.put("result",significant_nb);
	break;	
case "unsignificant_nb":
	Integer unsignificant_nb = 0 ;
	for(Object value:calculList)if(value==null||value.toString().trim().isEmpty())unsignificant_nb++;
	WILD_toReturn.put("result",unsignificant_nb);
	break;	
case "distinct_nb":
	Set<Object> uniqueDistinct= new HashSet<Object>(calculList);
	WILD_toReturn.put("result",uniqueDistinct.size());
	break;	
case "values_nb":
	HashMap<Object,Integer> counts = new HashMap();
	for(Object aF:calculList)
		if(counts.get(aF)==null)counts.put(aF, 1);
		else counts.put(aF, counts.get(aF)+1);
	WILD_toReturn.put("result",counts);	
	WILD_toReturn.put("count",counts.size());
	break;	
case "frequencies":
	HashMap<Object,Integer> counts2 = new HashMap();
	HashMap<Object,Double> counts_frequencies = new HashMap();
	for(Object aF:calculList)
		if(counts2.get(aF)==null)counts2.put(aF, 1);
		else counts2.put(aF, counts2.get(aF)+1);
	for(Object aF:counts2.keySet())counts_frequencies.put(aF, (double)counts2.get(aF)/(double)calculList.size());
	WILD_toReturn.put("result",counts_frequencies);
	WILD_toReturn.put("count",counts_frequencies.size());
	break;	
case "modal_value":
	HashMap<Object,Integer> counts3 = new HashMap();
	for(Object aF:calculList)
		if(counts3.get(aF)==null)counts3.put(aF, 1);
		else counts3.put(aF, counts3.get(aF)+1);
	Object modalValue = null ;
	List<Object> modalValues = new ArrayList();
	Integer lastMax = 0 ;
	for(Object aF:counts3.keySet())if(counts3.get(aF)>lastMax){
		modalValue = aF;
		lastMax=counts3.get(aF);
	}
	for(Object aF:counts3.keySet())if(counts3.get(aF)==lastMax)	modalValues.add(aF);
	if(modalValues.size()>1)WILD_toReturn.put("result",modalValues);
	else WILD_toReturn.put("result",modalValue);
	WILD_toReturn.put("associated_max_value",lastMax);
	break;	
default :
	break;
}
		
//	//	//	Etape	"4" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"5" : poids relatif de 10, Contrôle des flux
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

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
 
/**
 * Calcul de statistiques sur les valeurs des enregistrements, combinant l’ensemble des conditions précédemment listées. La liste des statistiques retenues est présentée en Tableau 1. Le Tableau 2 plus loin renvoie à un index des conditions pour implémentation.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Map<String,Object> calculate (
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey
	)throws Exception{
return calculate(i_colName,i_conditionList,i_statKey,null);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findTop - Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Liste des conditions{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre associé à la statistique{}
 * @param i_nbTop	Nombre de valeurs du « top »{}
 * @return	{}
 */
public Integer[] findTop (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam,
	Integer i_nbTop
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//		// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//		//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
			this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//		//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
			this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	// // Réduction du jeux de données
			Map<Integer, Map<String, Object>> newContents_map = this.findRecords(i_conditionList);
			HashMap<Integer, HashMap<String, Object>> newContents = new HashMap();
			for(Integer index : newContents_map.keySet())newContents.put(index, (HashMap<String, Object>) newContents_map.get(index));
				
	// // Construction d'un jeu de "clés" = colonnes de groupement distinctes
			List<Object> newGroupKeys = new ArrayList();
			for(Integer index : newContents.keySet())if(!newGroupKeys.contains(newContents.get(index).get(i_colGroup)))
				newGroupKeys.add(newContents.get(index).get(i_colGroup));
			
//		//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
			this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	// // Map d'accueil des résultats du calcul
			HashMap<Object,Object> newGroupStats= new HashMap();
	// // Liste pour détermination des seuils
			List<Object> newGroupKeysList= new ArrayList();
			List<Object> newGroupStatsList= new ArrayList();
	// // DataSet de calcul
			WildDataSet nWds = new WildDataSet(this.WILD_dObject,null);
			nWds.DVP_feedNoIndex(newContents, DVP_getHeader());
	// // Pour chaque "clé", calcul de la statistique
			Integer i = 1 ;
			for(Object groupKey:newGroupKeys){
				Map<Integer, Map<String,Object>> conditionList = new HashMap();
				Map<String,Object> firstCondition = new HashMap();
				firstCondition.put("WildCode", "equal");
				firstCondition.put("ColumnName", i_colGroup);
				firstCondition.put("ColumnValue", groupKey);
				conditionList.put(1, firstCondition);
				Map<String,Object> stat_res = nWds.calculate(i_colName, conditionList, i_statKey, i_statParam) ;
				newGroupStats.put(groupKey, stat_res.get("result"));
				newGroupStatsList.add(stat_res.get("result"));
			}		
//		//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
			this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	// // Identification des seuils de la statistique
			Integer index_limit = newGroupStatsList.size()-1-i_nbTop ;
			if(index_limit<0)index_limit=0;
			if(index_limit>newGroupStatsList.size()-1)index_limit=newGroupStatsList.size()-1;
			Object limit = stats_n(newGroupStatsList, index_limit);
	// // Conversion des seuils en liste et en conditions
			Map<Integer, Map<String,Object>> conditionList = new HashMap();
			Integer cond_i = 1 ;
			
			for(Object groupKey:newGroupKeys){
				if(newGroupStats.get(groupKey)!=null&&(Double)newGroupStats.get(groupKey)>(Double)limit){
					newGroupKeysList.add(groupKey);
					Map<String,Object> subCondition = new HashMap();
					subCondition.put("WildCode", "equal");
					subCondition.put("ColumnName", i_colGroup);
					subCondition.put("ColumnValue", groupKey);
					if(cond_i!=1) subCondition.put("CombinOperator", "OR");
					conditionList.put((cond_i++), subCondition);
				}
			}
//		//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
			this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	// // Recherche des lignes en tableau
			WILD_toReturn = nWds.findLines(conditionList);
//		//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
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

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
 
/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbTop	Nombre de valeurs du « top » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findTop (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbTop
	)throws Exception{
return findTop(i_colGroup,i_colName,i_conditionList,i_statKey,null,i_nbTop);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findBottom  - Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Liste des conditions{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre associé à la statistique{}
 * @param i_nbBottom	Nombre de valeurs du « bottom »{}
 * @return	{}
 */
public Integer[] findBottom  (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam,
	Integer i_nbBottom
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
// // Réduction du jeux de données
		Map<Integer, Map<String, Object>> newContents_map = this.findRecords(i_conditionList);
		HashMap<Integer, HashMap<String, Object>> newContents = new HashMap();
		for(Integer index : newContents_map.keySet())newContents.put(index, (HashMap<String, Object>) newContents_map.get(index));
			
// // Construction d'un jeu de "clés" = colonnes de groupement distinctes
		List<Object> newGroupKeys = new ArrayList();
		for(Integer index : newContents.keySet())if(!newGroupKeys.contains(newContents.get(index).get(i_colGroup)))
			newGroupKeys.add(newContents.get(index).get(i_colGroup));
		
//	//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
// // Map d'accueil des résultats du calcul
		HashMap<Object,Object> newGroupStats= new HashMap();
// // Liste pour détermination des seuils
		List<Object> newGroupKeysList= new ArrayList();
		List<Object> newGroupStatsList= new ArrayList();
// // DataSet de calcul
		WildDataSet nWds = new WildDataSet(this.WILD_dObject,null);
		nWds.DVP_feedNoIndex(newContents, DVP_getHeader());
// // Pour chaque "clé", calcul de la statistique
		Integer i = 1 ;
		for(Object groupKey:newGroupKeys){
			Map<Integer, Map<String,Object>> conditionList = new HashMap();
			Map<String,Object> firstCondition = new HashMap();
			firstCondition.put("WildCode", "equal");
			firstCondition.put("ColumnName", i_colGroup);
			firstCondition.put("ColumnValue", groupKey);
			conditionList.put(1, firstCondition);
			Map<String,Object> stat_res = nWds.calculate(i_colName, conditionList, i_statKey, i_statParam) ;
			newGroupStats.put(groupKey, stat_res.get("result"));
			newGroupStatsList.add(stat_res.get("result"));
		}		
//	//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
// // Identification des seuils de la statistique
		Integer index_limit = i_nbBottom ;
		if(index_limit<0)index_limit=0;
		if(index_limit>newGroupStatsList.size()-1)index_limit=newGroupStatsList.size()-1;
		Object limit = stats_n(newGroupStatsList, index_limit);
// // Conversion des seuils en liste et en conditions
		Map<Integer, Map<String,Object>> conditionList = new HashMap();
		Integer cond_i = 1 ;
		for(Object groupKey:newGroupKeys)
			if(newGroupStats.get(groupKey)!=null&&(Double)newGroupStats.get(groupKey)<(Double)limit){
				newGroupKeysList.add(groupKey);
				Map<String,Object> subCondition = new HashMap();
				subCondition.put("WildCode", "equal");
				subCondition.put("ColumnName", i_colGroup);
				subCondition.put("ColumnValue", groupKey);
				if(cond_i!=1) subCondition.put("CombinOperator", "OR");
				conditionList.put((cond_i++), subCondition);
			}
		
//	//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
// // Recherche des lignes en tableau
		WILD_toReturn = nWds.findLines(conditionList);
//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
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

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
 
/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition d’ordre sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbBottom	Nombre de valeurs du « bottom » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findBottom  (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbBottom
	)throws Exception{
return findBottom (i_colGroup,i_colName,i_conditionList,i_statKey,null,i_nbBottom);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findTopPercent  - Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Liste des conditions{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre associé à la statistique{}
 * @param i_nbTop	Percentile du « top »{}
 * @return	{}
 */
public Integer[] findTopPercent  (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam,
	Integer i_nbTop
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//		// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//		//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
			this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//		//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
			this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	// // Réduction du jeux de données
			Map<Integer, Map<String, Object>> newContents_map = this.findRecords(i_conditionList);
			HashMap<Integer, HashMap<String, Object>> newContents = new HashMap();
			for(Integer index : newContents_map.keySet())newContents.put(index, (HashMap<String, Object>) newContents_map.get(index));
				
	// // Construction d'un jeu de "clés" = colonnes de groupement distinctes
			List<Object> newGroupKeys = new ArrayList();
			for(Integer index : newContents.keySet())if(!newGroupKeys.contains(newContents.get(index).get(i_colGroup)))
				newGroupKeys.add(newContents.get(index).get(i_colGroup));
			
//		//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
			this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	// // Map d'accueil des résultats du calcul
			HashMap<Object,Object> newGroupStats= new HashMap();
	// // Liste pour détermination des seuils
			List<Object> newGroupKeysList= new ArrayList();
			List<Object> newGroupStatsList= new ArrayList();
	// // DataSet de calcul
			WildDataSet nWds = new WildDataSet(this.WILD_dObject,null);
			nWds.DVP_feedNoIndex(newContents, DVP_getHeader());
	// // Pour chaque "clé", calcul de la statistique
			Integer i = 1 ;
			for(Object groupKey:newGroupKeys){
				Map<Integer, Map<String,Object>> conditionList = new HashMap();
				Map<String,Object> firstCondition = new HashMap();
				firstCondition.put("WildCode", "equal");
				firstCondition.put("ColumnName", i_colGroup);
				firstCondition.put("ColumnValue", groupKey);
				conditionList.put(1, firstCondition);
				Map<String,Object> stat_res = nWds.calculate(i_colName, conditionList, i_statKey, i_statParam) ;
				newGroupStats.put(groupKey, stat_res.get("result"));
				newGroupStatsList.add(stat_res.get("result"));
			}		
//		//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
			this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	// // Identification des seuils de la statistique
			
			Integer index_limit = (int) Math.round(newGroupStatsList.size()-((double)i_nbTop / (double)100 * (newGroupStatsList.size())))-1;
			if(index_limit<0)index_limit=0;
			if(index_limit>newGroupStatsList.size()-1)index_limit=newGroupStatsList.size()-1;
			Object limit = stats_n(newGroupStatsList, index_limit);
	// // Conversion des seuils en liste et en conditions
			Map<Integer, Map<String,Object>> conditionList = new HashMap();
			Integer cond_i = 1 ;
			for(Object groupKey:newGroupKeys)
				if(newGroupStats.get(groupKey)!=null&&(Double)newGroupStats.get(groupKey)>(Double)limit){
					newGroupKeysList.add(groupKey);
					Map<String,Object> subCondition = new HashMap();
					subCondition.put("WildCode", "equal");
					subCondition.put("ColumnName", i_colGroup);
					subCondition.put("ColumnValue", groupKey);
					if(cond_i!=1) subCondition.put("CombinOperator", "OR");
					conditionList.put((cond_i++), subCondition);
				}
			
//		//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
			this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	// // Recherche des lignes en tableau
			WILD_toReturn = nWds.findLines(conditionList);
//		//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
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

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
 
/**
 * Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : TOP.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbTop	Percentile du « top » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findTopPercent  (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbTop
	)throws Exception{
return findTopPercent (i_colGroup,i_colName,i_conditionList,i_statKey,null,i_nbTop);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findBottomPercent  - Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Liste des conditions{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre associé à la statistique{}
 * @param i_nbBottom	Percentile du « bottom »{}
 * @return	{}
 */
public Integer[] findBottomPercent  (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam,
	Integer i_nbBottom
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//		// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//		//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
			this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//		//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
			this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	// // Réduction du jeux de données
			Map<Integer, Map<String, Object>> newContents_map = this.findRecords(i_conditionList);
			HashMap<Integer, HashMap<String, Object>> newContents = new HashMap();
			for(Integer index : newContents_map.keySet())newContents.put(index, (HashMap<String, Object>) newContents_map.get(index));
				
	// // Construction d'un jeu de "clés" = colonnes de groupement distinctes
			List<Object> newGroupKeys = new ArrayList();
			for(Integer index : newContents.keySet())if(!newGroupKeys.contains(newContents.get(index).get(i_colGroup)))
				newGroupKeys.add(newContents.get(index).get(i_colGroup));
			
//		//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
			this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	// // Map d'accueil des résultats du calcul
			HashMap<Object,Object> newGroupStats= new HashMap();
	// // Liste pour détermination des seuils
			List<Object> newGroupKeysList= new ArrayList();
			List<Object> newGroupStatsList= new ArrayList();
	// // DataSet de calcul
			WildDataSet nWds = new WildDataSet(this.WILD_dObject,null);
			nWds.DVP_feedNoIndex(newContents, DVP_getHeader());
	// // Pour chaque "clé", calcul de la statistique
			Integer i = 1 ;
			for(Object groupKey:newGroupKeys){
				Map<Integer, Map<String,Object>> conditionList = new HashMap();
				Map<String,Object> firstCondition = new HashMap();
				firstCondition.put("WildCode", "equal");
				firstCondition.put("ColumnName", i_colGroup);
				firstCondition.put("ColumnValue", groupKey);
				conditionList.put(1, firstCondition);
				Map<String,Object> stat_res = nWds.calculate(i_colName, conditionList, i_statKey, i_statParam) ;
				newGroupStats.put(groupKey, stat_res.get("result"));
				newGroupStatsList.add(stat_res.get("result"));
			}		
//		//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
			this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	// // Identification des seuils de la statistique
			Integer index_limit = (int) Math.round(((double)i_nbBottom / (double)100 * (newGroupStatsList.size())));
			if(index_limit<0)index_limit=0;
			if(index_limit>newGroupStatsList.size()-1)index_limit=newGroupStatsList.size()-1;
			Object limit = stats_n(newGroupStatsList, index_limit);
	// // Conversion des seuils en liste et en conditions
			Map<Integer, Map<String,Object>> conditionList = new HashMap();
			Integer cond_i = 1 ;
			for(Object groupKey:newGroupKeys)
				if(newGroupStats.get(groupKey)!=null&&(Double)newGroupStats.get(groupKey)<(Double)limit){
					newGroupKeysList.add(groupKey);
					Map<String,Object> subCondition = new HashMap();
					subCondition.put("WildCode", "equal");
					subCondition.put("ColumnName", i_colGroup);
					subCondition.put("ColumnValue", groupKey);
					if(cond_i!=1) subCondition.put("CombinOperator", "OR");
					conditionList.put((cond_i++), subCondition);
				}
			
//		//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
			this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	// // Recherche des lignes en tableau
			WILD_toReturn = nWds.findLines(conditionList);
//		//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
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

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
 
/**
 * Récupération des numéros de lignes des enregistrements, correspondant au percentile sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « ordonnées » (O). Ordre : BOTTOM.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Liste des conditions {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_nbBottom	Percentile du « bottom » {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findBottomPercent  (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_nbBottom
	)throws Exception{
return findBottomPercent (i_colGroup,i_colName,i_conditionList,i_statKey,null,i_nbBottom);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findStatCompare  - Récupération des numéros de lignes des enregistrements, correspondant à une condition mathématique sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « comparables » (C). Les opérateurs utilisée sont supériorité / infériorité (option inclusion / exclusion) et égalité.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition mathématique sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « comparables » (C). Les opérateurs utilisée sont supériorité / infériorité (option inclusion / exclusion) et égalité.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul{}
 * @param i_conditionList	Conditions de recherche sur la colonne avant calcul de la statistique{}
 * @param i_statKey	Nom de la statistique{}
 * @param i_statParam	Paramètre de la statistique{}
 * @param i_minValue	Valeur inférieure{}
 * @param i_minExclude	Est exclu par le bas ?{}
 * @param i_maxValue	Valeur supérieure{}
 * @param i_maxExclude	Est exclu par le haut ?{}
 * @return	{}
 */
public Integer[] findStatCompare  (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Integer i_statParam,
	Object i_minValue,
	Boolean i_minExclude,
	Object i_maxValue,
	Boolean i_maxExclude
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Integer[] WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//		//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
			this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	// // Réduction du jeux de données
			Map<Integer, Map<String, Object>> newContents_map = this.findRecords(i_conditionList);
			HashMap<Integer, HashMap<String, Object>> newContents = new HashMap();
			for(Integer index : newContents_map.keySet())newContents.put(index, (HashMap<String, Object>) newContents_map.get(index));
				
	// // Construction d'un jeu de "clés" = colonnes de groupement distinctes
			List<Object> newGroupKeys = new ArrayList();
			for(Integer index : newContents.keySet())if(!newGroupKeys.contains(newContents.get(index).get(i_colGroup)))
				newGroupKeys.add(newContents.get(index).get(i_colGroup));
			
//		//	//	Etape	"3" : poids relatif de 100, Calcul de la statistique (+++)
			this.WILD_setStep(); // Ne pas modifier
		// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
	// // Map d'accueil des résultats du calcul
			HashMap<Object,Object> newGroupStats= new HashMap();
	// // Liste pour détermination des seuils
			List<Object> newGroupKeysList= new ArrayList();
			List<Object> newGroupStatsList= new ArrayList();
	// // DataSet de calcul
			WildDataSet nWds = new WildDataSet(this.WILD_dObject,null);
			nWds.DVP_feedNoIndex(newContents, DVP_getHeader());
	// // Pour chaque "clé", calcul de la statistique
			Integer i = 1 ;
			for(Object groupKey:newGroupKeys){
				Map<Integer, Map<String,Object>> conditionList = new HashMap();
				Map<String,Object> firstCondition = new HashMap();
				firstCondition.put("WildCode", "equal");
				firstCondition.put("ColumnName", i_colGroup);
				firstCondition.put("ColumnValue", groupKey);
				conditionList.put(1, firstCondition);
				Map<String,Object> stat_res = nWds.calculate(i_colName, conditionList, i_statKey, i_statParam) ;
				newGroupStats.put(groupKey, stat_res.get("result"));
				newGroupStatsList.add(stat_res.get("result"));
			}	
//	//	//	Etape	"4" : poids relatif de 100, Conditionsur statistique (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		
// // Conversion des seuils en liste et en conditions
					Map<Integer, Map<String,Object>> conditionList = new HashMap();
					Integer cond_i = 1 ;
					for(Object groupKey:newGroupKeys)
						if(check_compare(newGroupStats.get(groupKey),i_minValue, i_minExclude, i_maxValue,	i_maxExclude)){
							newGroupKeysList.add(groupKey);
							Map<String,Object> subCondition = new HashMap();
							subCondition.put("WildCode", "equal");
							subCondition.put("ColumnName", i_colGroup);
							subCondition.put("ColumnValue", groupKey);
							if(cond_i!=1) subCondition.put("CombinOperator", "OR");
							conditionList.put((cond_i++), subCondition);
						}
					
//				//	//	Etape	"5" : poids relatif de 20, Construction du retour (+)
					this.WILD_setStep(); // Ne pas modifier
				// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
			// // Recherche des lignes en tableau
					WILD_toReturn = nWds.findLines(conditionList);		

//	//	//	Etape	"6" : poids relatif de 10, Contrôle des flux
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

// LES METHODES SUIVANTES CORRESPONDENT AUX APPELS PUBLICS ET NE DOIVENT PAS ETRE MODIFIEES
 
/**
 * Récupération des numéros de lignes des enregistrements, correspondant à une condition mathématique sur une statistique, réutilisant la liste des statistiques à implémenter définies en Tableau 2, citées comme « comparables » (C). Les opérateurs utilisée sont supériorité / infériorité (option inclusion / exclusion) et égalité.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode d'appel public non modifiable
 * 
 * @param i_colGroup	Nom de la colonne de groupement{}
 * @param i_colName	Nom de la colonne de calcul {}
 * @param i_conditionList	Conditions de recherche sur la colonne avant calcul de la statistique {}
 * @param i_statKey	Nom de la statistique {}
 * @param i_minValue	Valeur inférieure {}
 * @param i_maxValue	Valeur supérieure {}
 * @return	Liste des lignes repérées (Integer[]) {}
 */
 public Integer[] findStatCompare  (
	String i_colGroup,
	String i_colName,
	Map<Integer, Map<String,Object>> i_conditionList,
	String i_statKey,
	Object i_minValue,
	Object i_maxValue
	)throws Exception{
return findStatCompare (i_colGroup,i_colName,i_conditionList,i_statKey,null,i_minValue,false,i_maxValue,false);
} 

/*	### NOUVELLE METHODE ###
	Méthode : findRecords - Récupération des enregistrements sous forme de WildDataSet (Map JAVA), pour l’ensemble des conditions opérables sur le fichier.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des enregistrements sous forme de WildDataSet (Map JAVA), pour l’ensemble des conditions opérables sur le fichier.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_conditionList	Conditions de recherche{}
 * @return	{}
 */
public Map<Integer,Map<String,Object>> findRecords (
	Map<Integer, Map<String,Object>> i_conditionList
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	Map<Integer,Map<String,Object>> WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		Integer[] selection = this.findLines(i_conditionList) ;
				
//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
		if(selection!=null){ WILD_toReturn = new HashMap();
			for(Integer index:selection)WILD_toReturn.put(index, this.mapContents.get(index));
		}
//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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
	Méthode : saveRecords - Récupération des enregistrements sous forme de fichier CSV, pour l’ensemble des conditions opérables sur le fichier.{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * Récupération des enregistrements sous forme de fichier CSV, pour l’ensemble des conditions opérables sur le fichier.{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 * @param i_conditionList	Conditions de recherche{}
 * @return	{}
 */
public String saveRecords (
	Map<Integer, Map<String,Object>> i_conditionList
	)throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
	//	Variable générique de retour
	String WILD_toReturn = null ; // Ne pas modifier

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 20, Parse du fichier (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"2" : poids relatif de 100, Vérification de la condition (+++)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"3" : poids relatif de 20, Construction du retour (+)
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"4" : poids relatif de 10, Contrôle des flux
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




/**
 * Ajout d'un enregistrement sous forme de tableau,
 * on lui affecte automatique les noms de colonnes selon l'ordre des valeurs
 * Ajout d'un enregistrement, sans contrôle des entêtes car affectées automatiquement
 * @param record
 * @throws Exception
 */
/*public void DVP_add(String[] record) throws Exception{
	HashMap<String, Object> mapForRecord = new HashMap();
	for(Integer i=1; i<=this.headerNames.size();i++)
		if(i<=record.length)
			mapForRecord.put(headerNames.get(i), record[i-1]);
	DVP_add(mapForRecord);
}*/
public void DVP_add(Object[] record) throws Exception{
	HashMap<String, Object> mapForRecord = new HashMap();
	for(Integer i=1; i<=this.headerNames.size();i++)
		if(i<=record.length)
			mapForRecord.put(headerNames.get(i), record[i-1]);
	DVP_add(mapForRecord);
}
/**
 * Supprime tous les enregistrements du jeu de données
 * @throws Exception
 */
public void DVP_empty() throws Exception{
	this.mapContents = new HashMap();
}
/**
 * Ajout d'un enregistrement, sans contrôle des entêtes
 * @param record
 * @throws Exception
 */
public void DVP_add(HashMap<String, Object> record) throws Exception{
	if(mapContents==null)mapContents=new HashMap<Integer,HashMap<String,Object>>();
	Integer recNum = this.mapContents.size() + 1;
	mapContents.put(recNum, record);
}

/**
 * Méthode de réaffectation des numéro d'enregistrement; pour s'assurer de la continuité
 * Part de 1 (et non de  0!!!!)
 */
private void DVP_reorder(){
	HashMap<Integer, HashMap<String, Object>> mapContents_reaffect = new HashMap<Integer, HashMap<String, Object>>();
	Integer i_enr = 1;
	for(Integer int_hm : mapContents.keySet())mapContents_reaffect.put(i_enr++, mapContents.get(int_hm));
	mapContents = mapContents_reaffect;
 }
/**
 * Méthode de cast d'un enregostrement, reconnait les "noms de colonnes" pour éviter les doublons proches
 */
private HashMap<String, Object> DVP_siblingRecord(HashMap<String, Object> i_enrContents){
	// Essentiellement : réécriture du nom de variable si existe déjà
	// Par proximité de nom : espaces, cast...
	HashMap<String,Object> i_enrContents_reaffect = new HashMap(i_enrContents) ; // Pour réaffectation dynamique
	Boolean op_done = false ; // flag pour mémoire
	if(headerNames==null){
		headerNames = new HashMap<Integer, String>();
		Integer i = 1 ;
		for(String ks:i_enrContents.keySet())headerNames.put(i++, ks);
	}
	for(Integer i_header : this.headerNames.keySet())
		for(String ks:i_enrContents.keySet())
		if(			headerNames.get(i_header)
						.toLowerCase()
						.replaceAll(" ","_")
				.equals(
					ks
						.toLowerCase()
						.replaceAll(" ","_")
				)
				&&!headerNames.get(i_header).equals(ks)
		){
			i_enrContents_reaffect.remove(ks);
			i_enrContents_reaffect.put(headerNames.get(i_header), i_enrContents.get(ks));
			if(!op_done)op_done = true ;
		}
	if(op_done)i_enrContents = i_enrContents_reaffect ;
	i_enrContents_reaffect = null;
	return i_enrContents ;
}

/**
 * Permet de retrouver une liste de numéros d'enregistrement correspondant à une suite de valeur indexées
 * opérateur ET
 * @param i_filter	Suite de valeurs à tester
 * @return	Liste des numéros des enregistrements "matchant"
 */
private List<Integer> DVP_getPatternMatchers(HashMap<String, Object> i_filter){
	Integer score_w = i_filter.size() ; // Nombre d'éléments attendus
	List<Integer> toDel = new ArrayList<Integer>(); // Liste des numéros d'enregistrement à supprimer
	for(Integer int_hm : mapContents.keySet()){
		Integer score_g = 0 ; // Nombre d'éléments reconnu
		for(String filter_key : i_filter.keySet())if(
				(i_filter.get(filter_key) == null && mapContents.get(int_hm).get(filter_key) == null)
				||i_filter.get(filter_key).equals(mapContents.get(int_hm).get(filter_key))
		)score_g++;
		if(score_w == score_g)toDel.add(int_hm);
	}
	return toDel ;
}

/**
 * Permet de rechercher une valeur dans une liste de colonnes
 * Renvoie les lignes où la valeur est trouvée
 * @param i_filter	Suite de valeurs à tester
 * @return	Liste des numéros des enregistrements "matchant"
 */
private List<Integer> DVP_getSearchWhithin_contains(List<String> i_targetedColumns, String searchValue){
	List<Integer> toDel = new ArrayList<Integer>(); // Liste des numéros d'enregistrement à supprimer
	for(Integer int_hm : mapContents.keySet())
		for(String colWanted:i_targetedColumns)
			if(String.valueOf(mapContents.get(int_hm).get(colWanted)).contains(searchValue)){
				toDel.add(int_hm);
				break;
	}
	return toDel ;
}
/**
 * Retrouve le nom de colonne le plus proche
 * @param i_columnName	Nouveau nom de colonne
 * @return [position de la colonne, nom de la colonne]
 */
private Object[] DVP_siblingColumn(String i_columnName){
		Integer colAffected ; // NUméro de la nouvelle colonne
		Integer op_done = null ;
		for(Integer i_header : this.headerNames.keySet())if(
				headerNames.get(i_header).toLowerCase().replaceAll(" ","_")
		.equals(
				i_columnName.toLowerCase().replaceAll(" ","_")
		)){
					i_columnName = headerNames.get(i_header) ;
					op_done = i_header ;
					break;
		}
		colAffected = (op_done == null)?null : op_done;
		return new Object[]{
				colAffected, i_columnName
		};
}

public void DVP_show(String colToShow){
	for(Integer i = 1; i <= mapContents.size() ; i++)System.out.println("l."+i+":"+mapContents.get(i).get(colToShow));
}
/**
 * Affichage dans la console du contenu du jeu de données
 * Format plat avec séparateur ||
 */
public void DVP_show(){
	String newLineHeader = "" ;
	for(Integer j=1; this.headerNames.size() >= j ; j ++)
		newLineHeader += "||"+headerNames.get(j);
		System.out.println(newLineHeader);
	for(Integer i = 1; i <= mapContents.size() ; i++){
		String newLine = "" ;
		for(Integer j=1; this.headerNames.size() >= j ; j ++)
			newLine += "||"+ mapContents.get(i).get(headerNames.get(j));
		System.out.println(newLine);
	}
}
/**
 * Récupération du nombre d'enregistrement
 * @return
 */
public Integer DVP_getLength(){
	return mapContents.size();
}

/**
 * Récupération d'un enregistrement unique (équivaut à une ligne)
 * @param rowNum	Numéro de ligne
 * @return
 */
public HashMap<String, Object> DVP_getData(Integer rowNum){
	if(rowNum > DVP_getLength() && rowNum > 0)return null;
	return mapContents.get(rowNum) ;
}
/**
 * Récupération d'un jeui d'enregistrements (équivaut à ensemble de lignes)
 * @param rowNumFrom	Numéro de ligne à partir
 * @param rowNumTo	Numéro de ligne jusqu'à
 * @return
 */
public HashMap<Integer,HashMap<String, Object>> DVP_getData(Integer rowNumFrom, Integer rowNumTo){
	if(rowNumFrom > DVP_getLength() && rowNumFrom > 0)return null;
	if(rowNumTo > DVP_getLength() && rowNumFrom > 0)return null;
	if(rowNumTo < rowNumFrom)return null;
	HashMap<Integer,HashMap<String, Object>> toRet = new HashMap();
	Integer order = 1 ;
	for(Integer i = rowNumFrom ; i <= rowNumTo ; i++)toRet.put(order++,  mapContents.get(i));
	return toRet ;
}

/**
 * Récupération d'un jeu d'enregistrements (équivaut à ensemble de lignes), ensemble du jeu sous forme de Map
 * @return
 */
public HashMap<Integer,HashMap<String, Object>> DVP_getData(){
	return mapContents ;
}
/**
 * Récupération d'une valeur unique (équivaut à une cellule)
 * @param rowNum	Numéro de ligne
 * @param headerName	Numéro de colonne
 * @return
 */
public <T> T DVP_getData(Integer rowNum, String headerName){
	if(rowNum > DVP_getLength() && rowNum > 0)return null;
	if(mapContents.get(rowNum)!=null)return (T) mapContents.get(rowNum).get(headerName) ;
	else return null;
}
/**
 * Définition d'une valeur unique (équivaut à une cellule)
 * @param rowNum	Numéro de ligne
 * @param headerName	Numéro de colonne
 * @param newValue	Nouvelle valeur
 */
public void DVP_setData(Integer rowNum, String headerName, Object newValue){
	if(rowNum > DVP_getLength() && rowNum > 0)return ;
	HashMap <String,Object> newEnr = mapContents.get(rowNum);
	if(newEnr == null)newEnr = new HashMap <String,Object>();
	newEnr.put(headerName, newValue);
	mapContents.put(rowNum, newEnr);
}
/**
 * Récupération des entêtes
 * Commence à 1 et pas à 0
 * @return
 */
public HashMap<Integer,String> DVP_getHeader(){
	return this.headerNames ;
}


public void DVP_addToDataSet(WildDataSet dataSet) throws Exception{
	if(dataSet==null)return ;
	HashMap<Integer, HashMap<String, Object>> wDsData = dataSet.DVP_getData();
	if(wDsData==null||wDsData.isEmpty())return ;
	if(this.mapContents==null||this.mapContents.isEmpty()){
		this.mapContents = wDsData;
		this.setData(mapContents);
	}else{
		for(Integer k:wDsData.keySet())DVP_add(wDsData.get(k));
	}
	
}
/**
 * Affectation d'un contenu, sans vérification des index
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_mapContents	Contenu sous forme de map
 * @param i_headers	Nom des colonnes d'en-tête
 * @throws Exception
 */
public void DVP_feedNoIndex(/*WildObject i_WILD_dObject,*/HashMap<Integer,HashMap<String,Object>> i_mapContents, HashMap<Integer,String> i_headers) throws Exception{
	//WILD_dObject = i_WILD_dObject;
	this.mapContents = i_mapContents;
	this.headerNames = i_headers;
}
}

