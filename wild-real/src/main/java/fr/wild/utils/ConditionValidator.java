package fr.wild.utils;

import fr.wild.common.IoCommons;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.sis.internal.jdk8.Predicate;
import org.apache.sis.util.ObjectConverter;
import org.apache.sis.util.ObjectConverters;

/**
 * An object whose sole purpose is to check that given records (set of key -> values)
 * respect the rule defined by parameters given at built.
 *
 * @author Alexis Manin (Geomatys)
 */
public class ConditionValidator implements Predicate<Map<String, String>> {

    /**
     * Evaluation method to perform.
     * Note : replace with JDK standard predicate if migrated to jdk8 or above.
     */
    private final Predicate<Map<String, String>> operator;

    /**
     * Represents the binary operator to use when chaining conditions. True
     * means we must use AND condition to chain this validator with a previous
     * result. False means we must use OR operator instead.
     */
    private final boolean binaryOperator;

    /**
     * A flag to indicate that we want the contrary of evaluated condition as
     * result.
     */
    private final boolean negate;

    /**
     *
     * @param condition Parameters of the condition to use to validate records.
     * Any modification in the map after built will be ignored by the validator.
     */
    public ConditionValidator(final Map<String, Object> condition) {
        final Object tmpOperator = condition.get("CombinOperator");
        final String CombinOperator = (tmpOperator == null) ? null : tmpOperator.toString().toLowerCase();
        binaryOperator = "and".equals(CombinOperator);

        final Object tmpCondition = condition.get("WildCode");
        String wildCode;
        if (tmpCondition instanceof String) {
            wildCode = ((String) tmpCondition).toLowerCase();
            negate = wildCode.startsWith("!");
            if (negate)
                wildCode = wildCode.substring(1);

        } else {
            throw new IllegalArgumentException("Input condition is invalid !");
        }

        switch (wildCode) {
            case "equal":
                operator = new Equal(condition);
                break;

            case "contain":
                operator = new Contain(condition);
                break;

            case "regex":
                operator = new Regex(condition);
                break;

            case "within":
                operator = new Within(condition);
                break;

            case "compare":
                operator = new Compare(condition);
                break;

            default:
                throw new IllegalArgumentException("unrecognized wildCode:".concat(wildCode));
        }
    }

    @Override
    public boolean test(final Map<String, String> brutRecord) {
        return negate ^ operator.test(brutRecord);
    }

    /**
     *
     * @param previousResult Result of a previous test.
     * @param brutRecord The record to test.
     * @return True if given record is valid according to the combination of
     * previous result and current condition. False otherwise.
     */
    public boolean chainTest(final boolean previousResult, final Map<String, String> brutRecord) {
        if (binaryOperator)
            return previousResult && test(brutRecord);
        return previousResult || test(brutRecord);
    }

    /**
     * Base class for condition operators.
     */
    private static abstract class AbstractCondition implements Predicate<Map<String, String>> {

        final String targetColumn;

        private AbstractCondition(final Map<String, Object> parameters) {
            final Object tmpObj = parameters.get("ColumnName");
            if (tmpObj instanceof String)
                targetColumn = (String) tmpObj;
            else
                throw new IllegalArgumentException("Impossible de déterminer la colonne cible à valider.");
        }
    }

    /**
     * Factorize parameter parsing for string based validations.
     */
    private static abstract class AbstractStringComparison extends AbstractCondition {

        final Object refValue;

        final boolean ignoreSpaces;
        final boolean ignoreCase;
        final boolean ignoreSpecialCharacters;

        final boolean emptyRef;

        private AbstractStringComparison(final Map<String, Object> parameters) {
            super(parameters);
            Object param = parameters.get("NullSens");
            ignoreSpaces = (param instanceof Boolean)? !(boolean) param : true;
            param = parameters.get("CaseSens");
            ignoreCase = (param instanceof Boolean)? !(boolean) param : true;
            param = parameters.get("SpecSens");
            ignoreSpecialCharacters = (param instanceof Boolean)? !(boolean) param : true;

            param = parameters.get("ColumnValue");
            if (param instanceof String)
                refValue = IoCommons.transform((String) param, ignoreSpaces, ignoreSpecialCharacters, ignoreCase);
            else refValue = param;
            emptyRef = refValue == null || (refValue instanceof String) && ((String)refValue).isEmpty();
        }
    }


    private static class Equal extends AbstractStringComparison {

        final ObjectConverter<? super String, ?> converter;
        final Double refDouble;

        public Equal(Map<String, Object> parameters) {
            super(parameters);
            final boolean isNumber = refValue instanceof Number;
            if (refValue == null || (refValue instanceof String))
                converter = null;
            else if (isNumber)
                converter = ObjectConverters.find(String.class, Double.class);
            else
                converter = ObjectConverters.find(String.class, refValue.getClass());

            if (isNumber)
                refDouble = ((Number)refValue).doubleValue();
            else
                refDouble = null;
        }

        @Override
        public boolean test(Map<String, String> t) {
            String value = t.get(targetColumn);
            if (emptyRef && (value == null || value.isEmpty()))
                return true;
            else if (value == null || refValue == null)
                return false;

            if (converter == null) {
                value = IoCommons.transform(value, ignoreSpaces, ignoreSpecialCharacters, ignoreCase);
                return refValue.equals(value);
            }

            if (refDouble != null)
                return refDouble.equals(converter.apply(value));
            else
                return refValue.equals(converter.apply(value));
        }
    }

    private static class Contain extends AbstractStringComparison {

        final String strRefValue;

        public Contain(Map<String, Object> parameters) {
            super(parameters);
            if (refValue == null || (refValue instanceof String))
                strRefValue = (String) refValue;
            else
                strRefValue = refValue.toString();
        }

        @Override
        public boolean test(Map<String, String> t) {
            String value = t.get(targetColumn);
            if (emptyRef && (value == null || value.isEmpty()))
                return true;
            else if (value == null || refValue == null)
                return false;

            value = IoCommons.transform(value, ignoreSpaces, ignoreSpecialCharacters, ignoreCase);
            return value.contains(strRefValue);
        }
    }

    private static class Regex extends AbstractStringComparison {

        final Pattern refPattern;

        public Regex(Map<String, Object> parameters) {
            super(parameters);
            if (refValue == null || (refValue instanceof String)) {
                if (ignoreSpaces)
                    refPattern = Pattern.compile((String) refValue, Pattern.CASE_INSENSITIVE);
                else
                   refPattern = Pattern.compile((String) refValue);
            } else {
                throw new IllegalArgumentException("La valeur d'entrée n'est pas une chaîne de caractère.");
            }
        }

        @Override
        public boolean test(Map<String, String> t) {
            String value = t.get(targetColumn);
            if (emptyRef && (value == null || value.isEmpty()))
                return true;
            else if (value == null || refValue == null)
                return false;

            value = IoCommons.transform(value, false, ignoreSpecialCharacters, ignoreCase);// case is managed by pattern.
            return refPattern.matcher(value).find();
        }
    }

    private static abstract class AbstractComparison extends AbstractCondition {

        final Object min, max;
        final boolean includeMin, includeMax;

        private AbstractComparison(final Map<String, Object> condition) {
            super(condition);
            min = condition.get("ColumnMinValue");
            max = condition.get("ColumnMaxValue");

            Object tmpParam = ((Boolean) condition.get("ColumnMinValueInclude"));
            includeMin = (tmpParam instanceof Boolean) ? (boolean) tmpParam : true;
            tmpParam = ((Boolean) condition.get("ColumnMaxValueInclude"));
            includeMax = (tmpParam instanceof Boolean) ? (boolean) tmpParam : true;
        }
    }

    /**
     * Check that a string is between two values,
     */
    private static class Within extends AbstractComparison {

        final String min, max;

        public Within(Map<String, Object> condition) {
            super(condition);
            if (super.min != null)
                min = IoCommons.transform(super.min.toString(), true, true, true);
            else min = null;

            if (super.max != null)
                max = IoCommons.transform(super.max.toString(), true, true, true);
            else max = null;
        }

        @Override
        public boolean test(Map<String, String> t) {
            String value = t.get(targetColumn);
            if (value == null)
                return min == null || max == null;

            value = IoCommons.transform(value, true, true, true);
            boolean within = min != null || max != null;
            // If a lower bound is defined, we ensure our value is greather than it.
            if (min != null) {
                final int comparison = value.compareTo(min);
                within = includeMin ? comparison >= 0 : comparison > 0;
            }

            // Check upper bound only if present and if lower bound has been validated.
            if (within && max != null) {
                final int comparison = value.compareTo(max);
                within &= includeMax ? comparison <= 0 : comparison < 0;
            }

            return within;
        }
    }

    private static class Compare extends AbstractComparison {

        final ObjectConverter<String, ? extends Comparable> converter;
        final boolean isNumber;
        final Object realMin, realMax;

        public Compare(Map<String, Object> condition) {
            super(condition);

            Class targetClass;
            if (min != null)
                targetClass = min.getClass();
            else if (max != null)
                targetClass = max.getClass();
            else
                targetClass = null;

            if (targetClass == null)
                isNumber = false;
            else {
                isNumber = Number.class.isAssignableFrom(targetClass);
                if (isNumber)
                    targetClass = Double.class;
                else if (!Comparable.class.isAssignableFrom(targetClass)) {
                    targetClass = null;
                }
            }

            if (targetClass != null)
                converter = ObjectConverters.find(String.class, targetClass);
            else
                converter = null;

            if (isNumber) {
                realMin = min == null? null : ((Number)min).doubleValue();
                realMax = min == null? null : ((Number)max).doubleValue();
            } else if (converter == null) {
                realMin = min == null? null : min.toString();
                realMax = max == null? null : max.toString();
            } else {
                realMin = min;
                realMax = max;
            }
        }

        @Override
        public boolean test(Map<String, String> t) {
            String value = t.get(targetColumn);

            if (value == null || (value = value.trim()).isEmpty())
                return min == null || max == null;

            return IoCommons.check_strictCompare(converter.apply(value), realMin, includeMin, realMax, includeMax);
        }
    }
}
