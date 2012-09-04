/**
 * Created by: Nahuel Barrios.
 * On: 08/09/10 at 17:52hs.
 */
package org.nbempire.java.filerenamer.service;

import org.nbempire.java.filerenamer.domain.FileName;

/**
 * Service for the entity {@link FileName}.
 *
 * @author Nahuel Barrios.
 * @since 0.1
 */
public interface FileNameService {

    /**
     * Rename the <code>fileName</code> from <code>inputPattern</code> to <code>outputPattern</code>.
     *
     * @param fileName
     *         The FileName to rename.
     * @param inputPattern
     *         The input pattern of the file name.
     * @param outputPattern
     *         The desired output pattern of the file name.
     *
     * @return {@link String} The renamed file name.
     *
     * @since 0.1
     */
    String rename(FileName fileName, String inputPattern, String outputPattern);

    /**
     * Creates a new FileName from the specified <code>name</code>
     *
     * @param name
     *         A file name.
     *
     * @return {@link FileName} The created FileName.
     *
     * @since 0.1
     */
    FileName createFrom(String name);

}
