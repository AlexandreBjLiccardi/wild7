package fr.wild.utils;

import java.util.Iterator;
import org.apache.sis.util.ArgumentChecks;

/**
 * An simple overlay over a source iterator. The aim is to allow conversion on
 * the fly of data returned by a specific iterator.
 *
 * Note : This class implements {@link AutoCloseable}, so in case the input
 * iterator is a closeable object, this one allow its closing without having to
 * keep a reference on it.
 *
 * @author Alexis Manin (Geomatys)
 * @param <I> Type of objects returned by source iterator.
 * @param <O> Type of objects to return.
 */
public abstract class ConvertingIterator<I, O> implements Iterator<O>, AutoCloseable {

    protected final Iterator<I> source;

    protected ConvertingIterator(final Iterator<I> source) {
        ArgumentChecks.ensureNonNull("Source iterator", source);
        this.source = source;
    }

    @Override
    public boolean hasNext() {
        return source.hasNext();
    }

    @Override
    public O next() {
        return convert(source.next());
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
     * Converts input object (Which is likely to be next element of the iteration).
     * @param next Object to convert.
     * @return The result of the conversion.
     * @throws IllegalArgumentException If input object cannot be converted.
     */
    protected abstract O convert(I next) throws IllegalArgumentException;
}
