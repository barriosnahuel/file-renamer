/**
 * Created by: Nahuel Barrios.
 * On: 08/09/10 at 17:52hs.
 */
package org.nbempire.java.filerenamer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Main class that has the main method to execute.
 *
 * @author Nahuel Barrios.
 * @since 0.1
 */
public class Application {

    /**
     * Class logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    /**
     * Main method to execute the application.
     *
     * @param arguments program arguments. It should be three.
     * @since 0.1
     */
    public static void main(String[] arguments) {
        AbstractApplicationContext context = new GenericXmlApplicationContext("/applicationContext.xml");

        if (arguments.length >= 3) {
            FileRenamer fileRenamer = context.getBean(FileRenamer.class);

            int updatedFiles = fileRenamer.doMagic(arguments[0], arguments[1], arguments[2]);
            if (updatedFiles == 0) {
                logger.info("No files to rename or maybe there was an error during process.");
            }
            logger.info(updatedFiles + " files renamed.");

        } else {
            logger.info("Program requires 3 arguments: absolute/relative path to parent directory; input pattern and output pattern.");
            System.out.println("Usage: java -jar FileRenamer.jar aDirectory/otherDirectory \"%a - %t\" \"%t - %a\"");
        }

        if (arguments.length == 3) {
            logger.info("Exit from application");
        }
    }

}
