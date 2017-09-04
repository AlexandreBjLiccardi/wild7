package fr.wild.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import org.apache.sis.internal.jdk8.Predicate;

/**
 * Execute a suite of validation against a given record. The validation constraints
 * must be given at built, as specified by Wild format.
 *
 * @author Alexis Manin (Geomatys)
 */
public class ConditionChain implements Predicate<Map<String, String>> {

    final Predicate<Map<String, String>> firstTest;
    final ConditionValidator[] executionChain;

    /**
     *
     * @param conditions A map containing the set of conditions to check. Any
     * modification of the map after built will be ignored by the chain executor.
     */
    public ConditionChain(final Map<Integer, Map<String, Object>> conditions) {
        if (conditions.size() <= 0) {
            firstTest = new Predicate<Map<String, String>>() {
                @Override
                public boolean test(Map<String, String> t) {
                    return true;
                }
            };
            executionChain = null;
        } else if (conditions.size() == 1) {
            firstTest = new ConditionValidator(conditions.values().iterator().next());
            executionChain = null;
        } else {
            final ArrayList<Integer> order = new ArrayList<>(conditions.keySet());
            Collections.sort(order);

            executionChain = new ConditionValidator[conditions.size() - 1];
            firstTest = new ConditionValidator(conditions.get(order.get(0)));
            for (int i = 1 ; i < order.size() ; i++) {
                executionChain[i - 1] = new ConditionValidator(conditions.get(order.get(i)));
            }
        }
    }

    @Override
    public boolean test(Map<String, String> t) {
        boolean result = firstTest.test(t);
        if (executionChain != null)
            for (int i = 0; i < executionChain.length; i++) {
                result = executionChain[i].chainTest(result, t);
            }
        return result;
    }
}