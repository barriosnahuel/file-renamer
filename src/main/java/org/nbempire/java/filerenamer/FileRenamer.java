/**
 * Created by: Nahuel Barrios.
 * On: 08/09/10 at 17:52hs.
 */
package org.nbempire.java.filerenamer;

import org.nbempire.java.filerenamer.service.FileNameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * The FileRenamer.
 *
 * @author Nahuel Barrios.
 * @since 0.1
 */
@Component
public class FileRenamer {

    /**
     * Class logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(FileRenamer.class);

    @Autowired
    private FileNameService fileNameService;

    /**
     * Main method of this FileRenamer that does all the magic to rename files. It renames every file under the specified {@code directoryPath}.
     * It's necessary that all files match the {@code inputPattern} to make possible the rename action to the {@code outputPattern}.
     *
     * @param directoryPath String with the system directory to work with.
     * @param inputPattern  Input pattern specification of the files under the {@code directoryPath}.
     * @param outputPattern The output pattern which will be used to rename the files.
     * @return The number of parsed files. <b>Not the number of renamed files.</b>
     * @since 0.1
     */
    int doMagic(String directoryPath, String inputPattern, String outputPattern) {
        logger.debug("--> doMagic: directoryPath: " + directoryPath);
        File[] files = new File(directoryPath).listFiles();

        if (files == null) {
            logger.warn("The specified directory path is invalid.");
            throw new IllegalArgumentException("The specified directory path is invalid.");
        }

        int counter = 0;
        while (counter < files.length) {
            File file = files[counter];

            try {
                String newName = fileNameService.rename(fileNameService.createFrom(file.getName()), inputPattern, outputPattern);
                boolean renamed = file.renameTo(new File(file.getParent() + "/" + newName));
                if (!renamed) {
                    throw new IllegalArgumentException("The file: \"" + newName + "\" wasn't renamed.");
                }
            } catch (IllegalArgumentException illegalArgumentException) {
                logger.info("The following file wasn't renamed: \"" + file.getName() + "\"");
                logger.warn(illegalArgumentException.getMessage());
            }
            counter++;
        }

        logger.debug("<-- doMagic: directoryPath: " + directoryPath + "; " + counter + " renamed files.");
        return counter;
    }

}
