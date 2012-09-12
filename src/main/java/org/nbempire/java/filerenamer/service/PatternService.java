/**
 * Created by: Nahuel Barrios.
 * On: 11/09/12 at 17:18hs.
 */
package org.nbempire.java.filerenamer.service;

import org.nbempire.java.filerenamer.domain.Pattern;

/**
 * @author Nahuel Barrios.
 * @since 0.2
 */
public interface PatternService {

    /**
     * Creates a {@link Pattern} from the specified {@code aPattern}.
     *
     * @param aPattern
     *         The specified pattern. It must have a {@code %} followed only by one character to indicate a field.
     *
     * @return The created Pattern.
     *
     * @since 0.2
     */
    Pattern createFrom(String aPattern);

}
