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
import static fr.wild.common.IoCommons.*;

import fr.wild.common.IoCommons;
// Dépendances wild.orchestra obligatoires
import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
// Dépendances, bibliothèques JAVA par exemple.
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.w3c.dom.Node;


/**
 * Code généré automatiquement par l'outil Wild
 * Webservice spécifique à SEEE
 */
public class WildSeeeService extends WildWebService implements Callable{

// Constructeur vide pour permettre la création de constructeurs différents par les classes héritées
	protected WildSeeeService(){}

// VARIABLES GLOBALES ("Fields") directement renseignées par la documentation

// VARIABLES GLOBALES ("Fields") transmises par les constructeurs
	protected Node parametersNode;	//Paramètres sous forme de nœud XML{}
	protected String parametersXmlFile;	//Paramètres sous forme de fichier XML dont on donne le lien{}
	//protected Map<String,Object> parametersMap;	//Paramètres sous forme de Map{} // Pourquoi le surcharger ?


// *** [Généré automatiquement] Ecrivez vos propres variables globales ici. ***

// CE CONSTRUCTEUR DOIT ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Fonction d'initialisation, commune à tous les constructeurs.
 * "Constructeur unique"
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_parametersNode	Paramètres sous forme de nœud XML{}
 * @param i_parametersXmlFile	Paramètres sous forme de fichier XML dont on donne le lien{}
 * @param i_parametersMap	Paramètres sous forme de Map{}
 */
protected void WILD_initialize_WildSeeeService(
	WildObject i_WILD_dObject,
	Node i_parametersNode,
	String i_parametersXmlFile,
	Map<String,Object> i_parametersMap
) throws Exception {

//	// Amorce de la classe
	// Initialisation de la classe d'objet selon le schéma Wild
	WILD_dObject = i_WILD_dObject ;
	WILD_initialize_WildWebService(i_WILD_dObject, null, null, null, null);
	// Préparation des variables d'invocation (considérées comme champs globaux)
	this.parametersNode = i_parametersNode;
	this.parametersXmlFile = i_parametersXmlFile;
	this.parametersMap = map_indexer(i_parametersMap);

//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***
//	// Configuration générique du service, notamment les répertoires de travail
		parametersMap.putAll( IoCommons.param_readParameters(i_parametersNode, i_parametersXmlFile, (Map)i_parametersMap) );
		UNIQ_TEST_ID = this.getParameterValue(PROP_BEHAVIOR_UNIQ_TEST_ID, null, this.WILD_dObject.getLastToken());
		this.WILD_Logger.WILD_setLastToken(UNIQ_TEST_ID);
		String newPath = getParameterValue(PROP_BEHAVIOR_OUTPUTFILE, null, "");
		String wkSpace = WILD_dObject.getDiceModelList().getProperty("qWorkspace_path") ;
		if(wkSpace==null||"/".equalsIgnoreCase(wkSpace))wkSpace="";
		if(newPath==null||newPath.length()==0) newPath = wkSpace+"/"+this.WILD_dObject.getDiceModelList().getProperty("qExec_path")+"/"+UNIQ_TEST_ID;
		parametersMap.put(PROP_BEHAVIOR_OUTPUTFILE,Collections.singletonMap("value", (Object) newPath ));
		File execDir = new File(newPath) ;
		execDir.mkdirs();		
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
 * @param i_parametersNode	Paramètres sous forme de nœud XML{}
 */

public WildSeeeService(
	WildObject i_WILD_dObject,
	Node i_parametersNode
) throws Exception{
	this.WILD_initialize_WildSeeeService(i_WILD_dObject,i_parametersNode,null,null);
}


// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Constructeur, appelle nécessairement WILD_initialize()
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_parametersXmlFile	Paramètres sous forme de fichier XML dont on donne le lien{}
 */

public WildSeeeService(
	WildObject i_WILD_dObject,
	String i_parametersXmlFile
) throws Exception{
	this.WILD_initialize_WildSeeeService(i_WILD_dObject,null,i_parametersXmlFile,null);
}


// CE CONSTRUCTEUR NE DOIT PAS ETRE MODIFIE.
/**
 * Code généré automatiquement par l'outil Wild
 * Constructeur, appelle nécessairement WILD_initialize()
 * NB. i_WILD_dObject est nécessairement passé
 *
 * @param i_WILD_dObject	Brique Wild d'appel de l'objet, pour récupération des informations d'exécution
 * @param i_parametersMap	Paramètres sous forme de Map{}
 */

public WildSeeeService(
	WildObject i_WILD_dObject,
	Map<String,Object> i_parametersMap
) throws Exception{
	this.WILD_initialize_WildSeeeService(i_WILD_dObject,null,null,i_parametersMap);
}

/*	### NOUVELLE METHODE ###
	Méthode : getTest - C9F1 : Test générique d'un service de calcul d'indicateur SEEE, configurations en ligne{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * C9F1 : Test générique d'un service de calcul d'indicateur SEEE, configurations en ligne{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void getTest ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier
WILD_echo("getTest");
//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Etape générique
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
	Méthode : getTestAsynch - C9F2 : Test spécifique d'un service de calcul d'indicateur SEEE, calcul asynchrone{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * C9F2 : Test spécifique d'un service de calcul d'indicateur SEEE, calcul asynchrone{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void getTestAsynch ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier
WILD_echo("getTestAsynch");
//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Etape générique
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
	Méthode : getTestRandomFile - C9F3 : Test spécifique d'un service de calcul d'indicateur SEEE, envoi d'un fichier lourd à traiter{ 
 }

*/
// CETTE METHODE DOIT ETRE MODIFIE.

/**
 * C9F3 : Test spécifique d'un service de calcul d'indicateur SEEE, envoi d'un fichier lourd à traiter{ 
 }
 * Code généré automatiquement par l'outil Wild
 * Méthode susceptible d'être surchargée
 * Cette méthode répond à l'ensemble des sollicitations de la "WildMethod" indiquée dans la documentation WILD
 * 
 */
public void getTestRandomFile ()throws Exception{
//	// Amorce de la méthode
	WILD_beginMethod(); // Ne pas modifier
 // Ne pas modifier
WILD_echo("getTestRandomFile");
//	// Mode try de récupération des erreurs pour log
	try{	
//	// *** [Généré automatiquement] Ecrivez vos variables locales ici. ***

//	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

//	//	//	Etape	"1" : poids relatif de 10, Etape générique
		this.WILD_setStep(); // Ne pas modifier
	// *** [Généré automatiquement] Ecrivez votre code JAVA ici. ***

	}catch(Exception e){
		this.WILD_logException(e); // Ne pas modifier
	}finally{
//	// Fin de la méthode
		WILD_endMethod(); // Ne pas modifier ; // Ne pas modifier
	}
}


	public String exec_test() throws Exception{
		//Sélection d'un des trois tests SEEE à exécuter
		String testtoDo = getParameterValue("behavior.testSeee", null, null) ;
		WILD_echo(testtoDo);
		switch(testtoDo){
			case "C9F1": getTest(); break;
			case "C9F2": getTestAsynch(); break;	
			case "C9F3": getTestRandomFile(); break;
			default: throw new IllegalArgumentException("Unknown scenario " + testtoDo);
		}
		return testtoDo ;
	}


	@Override
	public Object call() throws Exception {
		try{
			
			//Renseignement du test pour log
			List<String> intendedServices = Arrays.asList(new String[]{
						exec_test()
					});
			stats.put("intendedServices", Collections.singletonMap("intendedService",intendedServices));
		}catch(Exception e){
			throw e;
		}finally{
			this.DEV_dumpStats();
			
		}
		return null;
	}

}

