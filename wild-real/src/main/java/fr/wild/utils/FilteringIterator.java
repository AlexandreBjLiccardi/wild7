package fr.wild.utils;

import java.util.Iterator;
import org.apache.sis.util.ArgumentChecks;

/**
 * A simple overlay which filters another iterator entries.
 *
 * Note : This class implements {@link AutoCloseable}, so in case the input
 * iterator is a closeable object, this one allow its closing without having to
 * keep a reference on it.
 *
 * @author Alexis Manin (Geomatys)
 */
public abstract class FilteringIterator<T> implements Iterator<T>, AutoCloseable {

    protected final Iterator<T> source;
    protected T _next;

    protected FilteringIterator(final Iterator<T> source) {
        ArgumentChecks.ensureNonNull("Source iterator", source);
        this.source = source;
    }

    @Override
    public boolean hasNext() {
        while (_next == null && source.hasNext()) {
            final T tmp = source.next();
            if (accept(tmp))
                _next = tmp;
        }

        return _next != null;
    }

    @Override
    public T next() {
        if (hasNext())
            try {
                return _next;
            } finally {
                _next = null;
            }
        throw new IllegalStateException("No more element available !");
    }

    @Override
    public void close() throws Exception {
        if (source instanceof AutoCloseable)
            ((AutoCloseable)source).close();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Read-only operation !");
    }

    /**
     * Check if the given object (should be source iterator's _next entry) is a
 valid object to be returned, or if it must be ignored.
     *
     * @param input The object to check.
     * @return True if input object is accepted as a valid token, false if it
     * must be filtered.
     */
    protected abstract boolean accept(final T input);
}
