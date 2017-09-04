
package fr.wild.real;


/**
 *
 * @author Johann Sorel (Geomatys)
 */
public class TestUtils {


    public static void show(String opeName, Integer[] WILD_toReturn) {
        show(opeName, WILD_toReturn, false);
    }

    public static void show(String opeName, Integer[] WILD_toReturn, Boolean showLines) {
        System.out.println("__________ " + opeName);
        if (showLines) {
            for (Integer see : WILD_toReturn) {
                System.out.println(see);
            }
        }
        System.out.println("__________ Nb results : " + WILD_toReturn.length);
    }

}
