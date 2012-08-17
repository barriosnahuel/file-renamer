package org.nbempire.java.filerenamer.service;

import org.nbempire.java.filerenamer.domain.FileName;

/**
 * TODO : JavaDoc : for FileNameService.
 *
 * @author Nahuel Barrios.
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
     * @author Nahuel Barrios.
     */
    String rename(FileName fileName, String inputPattern, String outputPattern);

    /**
     * TODO : JavaDoc : for FileNameService.createFrom().
     *
     * @param name
     *
     * @return {@link FileName}
     *
     * @author Nahuel Barrios.
     */
    FileName createFrom(String name);

}
