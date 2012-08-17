/**
 * Created by: Nahuel Barrios.
 * On: 08/09/10 at 17:52hs.
 */
package org.nbempire.java.filerenamer;

import java.io.File;

import org.nbempire.java.filerenamer.service.FileNameService;
import org.nbempire.java.filerenamer.service.impl.FileNameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO : JavaDoc : for FileRenamer.
 *
 * @author Nahuel Barrios.
 * @since 0.1
 */
@Component
public class FileRenamer {

    @Autowired
    private FileNameService fileNameService;

    /**
     * TODO : JavaDoc : for FileRenamer.doMagic().
     *
     * @param directoryPath
     * @param inputPattern
     * @param outputPattern
     *
     * @return {@link int}
     *
     * @since 0.1
     */
    public int doMagic(String directoryPath, String inputPattern, String outputPattern) {
        File[] files = new File(directoryPath).listFiles();

        int counter = 0;
        while (counter < files.length) {
            File file = files[counter];

            try {
                fileNameService = new FileNameServiceImpl();
                String newName = fileNameService.rename(fileNameService.createFrom(file.getName()), inputPattern, outputPattern);
                file.renameTo(new File(file.getParent() + "/" + newName));
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println("El siguiente archivo no pudo ser renombrado: " + file.getName());
            }
            counter++;
        }

        return counter;
    }

}
