/**
 * Created by: Nahuel Barrios.
 * On: 08/09/10 at 17:52hs.
 */
package org.nbempire.java.filerenamer;

import org.nbempire.java.boc6.console.ConsoleUtil;
import org.nbempire.java.bocui.commandline.CommandLineKeys;
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
    final static Logger logger = LoggerFactory.getLogger(Application.class);

    /**
     * Main method to execute the application.
     *
     * @param arguments
     *         program arguments. It should be three.
     *
     * @since 0.1
     */
    public static void main(String[] arguments) {
        // BasicConfigurator.configure();// Configuro Log4j.

        ConsoleUtil.startApplication();

        AbstractApplicationContext context = new GenericXmlApplicationContext("/applicationContext.xml");

        if (arguments.length == 3) {
            FileRenamer fileRenamer = context.getBean(FileRenamer.class);

            int updatedFiles = fileRenamer.doMagic(arguments[0], arguments[1], arguments[2]);
            if (updatedFiles == 0) {
                logger.info("No se encontraron archivos para renombrar, o surgió algún error durante el proceso.");
            }
            logger.info("Se renombraron " + updatedFiles + " archivos.");

        } else {
            logger.info("Se deben ingresar 3 parametros: path del directorio (relativo/absoluto); patron de entrada; patron de salida.");
            ConsoleUtil.printUsage(CommandLineKeys.JAVA_JAR_PREFFIX
                                           + " FileRenamer.jar unDirectorio/otroDirectorio \"%a - %t\" \"%t - %a\"");
        }

        ConsoleUtil.exitApplication();
    }

}
