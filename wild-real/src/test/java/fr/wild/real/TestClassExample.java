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
public class TestClassExample {

    // Table de configuration issue des valeurs par défaut
    private HashMap<String, Object> diceConfig = config_getTab() ; 


    @Test
    public void testCsv() throws Exception {
        Locale.setDefault(new Locale("fr","FR"));
        
        Wild4Test wildModelTester = new Wild4Test();

        // Construction des objets à tester
        wildModelTester.addObject("myCsvFirstFile","real","WildFrCsvFile",new Object[]{"TEST.CSV"});
        wildModelTester.addObject("myCsvSecondFile","real","WildFrCsvFile",new Object[]{"TEST_2.CSV"});

        // On peut facilement écraser un objet par la même commande avec un nom similaire
        wildModelTester.addObject("myCsvFirstFile","real","WildFrCsvFile",new Object[]{"TEST_3.CSV"});

        // Exécution d'une méthode sur un objet
        wildModelTester.executeMethod("myCsvFirstFile","commit");

        // Ou avec un paramètre
        wildModelTester.executeMethod("myCsvFirstFile","test4Test",new Object[]{"Hello buddies !"});

        // Récupération d'une valeur de méthode
        String myString = wildModelTester.getFromMethod("myCsvFirstFile","test4Test",new Object[]{"Hello buddies !"});
        System.out.println(myString);

        // Certains objets prennent d'autres objets en argument. Il faut donc les récupérer et les manipuler.
        // Dans cet exemple, on récupère l'objet myCsvFirstFile, que l'on va processer dans myCsvSecondFile
        WildFrCsvFile myCsvFirstFile = wildModelTester.getObject("myCsvFirstFile");
        System.out.println((String)wildModelTester.getFromMethod("myCsvSecondFile","test4Test_withObject",
                new Object[]{myCsvFirstFile}).toString());

        // On peut aussi écrire
        System.out.println((String)wildModelTester.getFromMethod("myCsvSecondFile","test4Test_withObject",
                new Object[]{wildModelTester.getObject("myCsvFirstFile")}).toString());

        // Repartir du bon pied en supprimant les objets qui ne seront plus utilisés
        wildModelTester.free("myCsvSecondFile");

    }

}