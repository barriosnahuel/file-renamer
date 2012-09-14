/**
 * Created by: Nahuel Barrios.
 * On: 08/09/10 at 17:52hs.
 */
package org.nbempire.java.filerenamer.service;

import org.nbempire.java.filerenamer.domain.FileName;

/**
 * Service for the entity {@link org.nbempire.java.filerenamer.domain.FileName}.
 *
 * @author Nahuel Barrios.
 * @since 0.1
 */
public interface FileNameService {

    /**
     * Rename the {@code fileName} from {@code input} to {@code output}.
     *
     * @param fileName
     *         The FileName to rename.
     * @param input
     *         The input pattern of the file name.
     * @param output
     *         The desired output pattern of the file name.
     *
     * @return {@link String} The renamed file name.
     *
     * @since 0.1
     */
    String rename(FileName fileName, String input, String output);

    /**
     * Creates a new FileName from the specified {@code name}
     *
     * @param name
     *         A file name.
     *
     * @return {@link org.nbempire.java.filerenamer.domain.FileName} The created FileName.
     *
     * @since 0.1
     */
    FileName createFrom(String name);

}
