package org.nbempire.java.filerenamer.service;

import org.nbempire.java.filerenamer.domain.FileName;

/**
 * TODO : JavaDoc : for FileNameService.
 *
 * @author Thales - PNT Equipo 6.
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
     * @author Thales - PNT Equipo 6.
     */
    String rename(FileName fileName, String inputPattern, String outputPattern);

    /**
     * TODO : JavaDoc : for FileNameService.createFrom().
     *
     * @param name
     *
     * @return {@link FileName}
     *
     * @author Thales - PNT Equipo 6.
     */
    FileName createFrom(String name);

}
