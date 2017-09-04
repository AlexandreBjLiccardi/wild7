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

import static fr.wild.common.IoWilds.config_getTab;

import java.util.HashMap;
import java.util.Locale;

import fr.wild.orchestra.Wild4Test;
import fr.wild.real.WildFrCsvFile;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Classe exemple pour test de développement.
 *
 * @author alexandre.liccardi
 * @version 0.1b [ab]
 */
public class xQueryCrossSchemaTester {

    // Table de configuration issue des valeurs par défaut
    private HashMap<String, Object> diceConfig = config_getTab() ; 


    @Test
    public void testXquery() throws Exception {
        Locale.setDefault(new Locale("fr","FR"));
        
        Wild4Test wildModelTester = new Wild4Test();

        // Création d'un objet xQuery à partir d'un dépôt officiel européen
        wildModelTester
		.addObject("testSurCrossSchema","real","WildXqueryFile",new Object[]{
				"https://svn.eionet.europa.eu/repositories/Reportnet/Dataflows/WaterFrameworkDirective/WFD2016/QA-QC/Project/trunk/QAQC/XQuery/wfd_art13_crossChecks_2016.xquery"
		});
        // Modification à la volée des zones de commentaires de code, initialement prévus pour une utilisation via FME
        // 	NB. la méthode réalise un autocommit sur le disque du fichier xQuery pointé par URL
		wildModelTester
		.executeMethod("testSurCrossSchema","unnerve",new Object[]{
				"fme:get-attribute","fme:get-attribute(\"MONITORING\")"
		});
		
		// Activation et remplacement des inputs de variables pour injection de valeur de chemin de fichiers locaux
		// alterCode sans motif de reconnaissance : simple remplacement
		wildModelTester
		.executeMethod("testSurCrossSchema","alterCode",new Object[]{
				"declare variable $ENV := xmlconv:getENVExml();",
				"declare variable $ENV := \"\";\n"
				+ "declare variable $source_url := 'http://cdrtest.eionet.europa.eu/fr/eu/wfd2016/';\n"
				+ "declare variable $SWBlist as xs:string external;\n"
				+ "declare variable $GWBlist  as xs:string external;\n"
				+ "declare variable $RBDSUCAlist  as xs:string external;\n"
				+ "declare variable $SWMETlist as xs:string external;\n"
				+ "declare variable $GWMETlist as xs:string external;\n"
				+ "declare variable $RBMPPoMlist as xs:string external;\n"
				+ "declare variable $Monitoringlist as xs:string external;"
		});

		// Suppression de tous les commentaires, certains n'étant pas cohérent ou interférant avec les standards XML
		wildModelTester
		.executeMethod("testSurCrossSchema","uncommentDataset");
		// Passage des variables
		HashMap<String, String> i_listToProcess = new HashMap<String, String>();
		i_listToProcess.put("SWBlist","xQueryCrossSchemaTester/SWB_FRJ_99999999.xml");
		i_listToProcess.put("GWBlist","xQueryCrossSchemaTester/GWB_FRJ_99999999.xml");
		i_listToProcess.put("RBDSUCAlist","xQueryCrossSchemaTester/RBDSUCA_FRJ_99999999.xml");
		i_listToProcess.put("SWMETlist","xQueryCrossSchemaTester/SWMET_FRJ_99999999.xml");
		i_listToProcess.put("GWMETlist","xQueryCrossSchemaTester/GWMET_FRJ_99999999.xml");
		i_listToProcess.put("RBMPPoMlist","xQueryCrossSchemaTester/RBMPPoM_FRJ_99999999.xml");
		i_listToProcess.put("Monitoringlist","xQueryCrossSchemaTester/Monitoring_FRJ_99999999.xml");
		// Exécution du test multi fichiers XML en entrée
		// NB. la méthode réalise un autocommit sur le disque du fichier xQuery pointé par URL
		String fileOutput = 
				wildModelTester
				.getFromMethod("testSurCrossSchema","process",new Object[]{
						 "xQueryCrossSchemaTester/test.xml", 
						 i_listToProcess				 
				});
		// Nettoyage du fichier autocommitté
/*		wildModelTester
		.executeMethod("testSurCrossSchema","delete");*/
		// Message de fin d'exécution		
		System.out.println("__ Good boy : "+fileOutput);

    }

}