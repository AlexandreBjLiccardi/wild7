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
public class xmlTester {

    // Table de configuration issue des valeurs par défaut
    private HashMap<String, Object> diceConfig = config_getTab() ; 


    @Test
    public void testXslt() throws Exception {
        Locale.setDefault(new Locale("fr","FR"));
        
        Wild4Test wildModelTester = new Wild4Test();

     // Test XSL à partir de XML
 		wildModelTester
     		.addObject("testSingleSchema2","real","WildXmlFile",new Object[]{
     				"xmlTester/log.6log"
     		});
     // Transformation à partir du fichier XML
     	String fileOutput =
     		wildModelTester
     		.getFromMethod("testSingleSchema2","transformBy",new Object[]{
     				"xmlTester/outputXsl.xml","xmlTester/summary_generator.xsl"
     		});
		// Message de fin d'exécution		
		System.out.println("__ Good boy (1) : "+fileOutput);
		
	     // Test XSD à partir de XML	
		wildModelTester
		.addObject("testSingleSchema","real","WildXmlFile",new Object[]{
				"xmlTester/SWB_FRJ_99999999.xml"
		});
		// Validation à partir du fichier XML
		// NB. Réalise un autocommit avec téléchargement
		System.out.println("__ Good boy (2) : "+
			wildModelTester
			.getFromMethod("testSingleSchema","validateBy",new Object[]{
					"http://dd.eionet.europa.eu/schemas/WFD2016/SWB_2016.xsd"
			}));
	 // Test xQuery à partir de XML
 		wildModelTester
     		.addObject("testSingleSchema2","real","WildXmlFile",new Object[]{
     				"xmlTester/log.6log"
     		});
		
		
    }

}