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
 * The FileRenamer.
 *
 * @author Nahuel Barrios.
 * @since 0.1
 */
@Component
public class FileRenamer {

    @Autowired
    private FileNameService fileNameService;

    /**
     * Main method of this FileRenamer that does all the magic to rename files. It renames every file under the specified
     * <code>directoryPath</code>. It's necessary that all files match the <code>inputPattern</code> to make possible the rename action to
     * the <code>outputPattern</code>.
     *
     * @param directoryPath
     *         String with the system directory to work with.
     * @param inputPattern
     *         Input pattern specification of the files under the <code>directoryPath</code>.
     * @param outputPattern
     *         The output pattern which will be used to rename the files.
     *
     * @return The number of renamed files.
     *
     * @since 0.1
     */
    public int doMagic(String directoryPath, String inputPattern, String outputPattern) {
        System.out.println("--> doMagic: directoryPath: " + directoryPath);
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

        System.out.println("<-- doMagic: directoryPath: " + directoryPath + "; Renombrados: " + counter + " archivos.");
        return counter;
    }

}
