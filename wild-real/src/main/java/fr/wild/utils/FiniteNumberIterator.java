package fr.wild.utils;

import java.util.Iterator;

/**
 * Filters numbers provided by a source iterator to keep only finite one. A
 * finite number is a non null object, which is neither NaN nor infinity.
 * 
 * @author Alexis Manin (Geomatys)
 */
public class FiniteNumberIterator extends FilteringIterator<Number> {

    public FiniteNumberIterator(Iterator<Number> source) {
        super(source);
    }

    @Override
    protected boolean accept(Number input) {
        if (input == null)
            return false;
        if (input instanceof Float) {
            return isFinite((Float) input);
        } else if (input instanceof Double) {
            return isFinite((Double)input);
        }

        // Not null, not a decimal. It should be a finite integer
        return true;
    }

    protected boolean isFinite(final Float input) {
        return !(input.isNaN() || input.isInfinite());
    }

    protected boolean isFinite(final Double input) {
        return !(input.isNaN() || input.isInfinite());
    }
}
