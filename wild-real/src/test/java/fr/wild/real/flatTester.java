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
public class flatTester {

    // Table de configuration issue des valeurs par défaut
    private HashMap<String, Object> diceConfig = config_getTab() ; 


    @Test
    public void testFlat() throws Exception {
        Locale.setDefault(new Locale("fr","FR"));
        
        Wild4Test wildModelTester = new Wild4Test();

     // Construction à partir d'un fichier csv local, zippé
     		wildModelTester
     		.addObject("testFlat","real","WildFrCsvFile",new Object[]{
     				"flatTester/csvTest.zip"
     		});
     		
     // Concaténation de deux colonnes
     		wildModelTester
     		.executeMethod("testFlat","concatenate",new Object[]{
     				"X_L2",	"Y_L2", ":"
     		});
     // Ajout d'une colonne par jointure
     		HashMap<Integer,Object> i_inputJoin = new HashMap();
     		i_inputJoin.put(1,"00486X0080/F");  
     		i_inputJoin.put(2,"00825X0128/111111");
     		HashMap<Integer,Object> i_colContents = new HashMap();
     		i_colContents.put(1,"A test");
     		i_colContents.put(2,"B test");
     		wildModelTester
     		.executeMethod("testFlat","addColumnJoinBy",new Object[]{
     				"Code national BSS added",	"Code national BSS", i_inputJoin, i_colContents
     		});
     // Ecriture du fichier sur le disque		
     		wildModelTester
     		.executeMethod("testFlat","commit");
    }

}