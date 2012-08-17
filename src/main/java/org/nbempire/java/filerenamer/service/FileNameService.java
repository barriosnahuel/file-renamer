/**
 * Created by: Nahuel Barrios.
 * On: 08/09/10 at 17:52hs.
 */
package org.nbempire.java.filerenamer.service;

import org.nbempire.java.filerenamer.domain.FileName;

/**
 * TODO : JavaDoc : for FileNameService.
 *
 * @author Nahuel Barrios.
 * @since 0.1
 */
public interface FileNameService {

    /**
     * TODO : JavaDoc : for FileNameService.rename().
     *
     * @param fileName
     * @param inputPattern
     * @param outputPattern
     *
     * @return {@link String}
     *
     * @since 0.1
     */
    String rename(FileName fileName, String inputPattern, String outputPattern);

    /**
     * TODO : JavaDoc : for FileNameService.createFrom().
     *
     * @param name
     *
     * @return {@link FileName}
     *
     * @since 0.1
     */
    FileName createFrom(String name);

}
