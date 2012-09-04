/**
 * Created by: Nahuel Barrios.
 * On: 08/09/10 at 17:52hs.
 */
package org.nbempire.java.filerenamer;

import org.nbempire.java.boc6.console.ConsoleUtil;
import org.nbempire.java.bocui.commandline.CommandLineKeys;

/**
 * Main class that has the main method to execute.
 *
 * @author Nahuel Barrios.
 * @since 0.1
 */
public class Application {

    /**
     * Método principal que debe ser ejecutado para iniciar la aplicación.
     *
     * @param arguments
     *
     * @since 0.1
     */
    public static void main(String[] arguments) {
        // BasicConfigurator.configure();// Configuro Log4j.

        ConsoleUtil.startApplication();

        if (arguments.length == 3) {
//            FileRenamer fileRenamer = (FileRenamer) new ClassPathXmlApplicationContext("/applicationContext.xml")
//                    .getBean("fileRenamer");

            FileRenamer fileRenamer = new FileRenamer();

            int updatedFiles = fileRenamer.doMagic(arguments[0], arguments[1], arguments[2]);
            if (updatedFiles == 0) {
                System.out.println("No se encontraron archivos para renombrar, o surgió algún error durante el proceso.");
            }
            System.out.println("Se renombraron " + updatedFiles + " archivos.");

        } else {
            System.out
                    .println("Se deben ingresar 3 parametros: path del directorio (relativo/absoluto); patron de entrada; patron de salida.");
            ConsoleUtil.printUsage(CommandLineKeys.JAVA_JAR_PREFFIX
                                           + " FileRenamer.jar unDirectorio/otroDirectorio \"%a - %t\" \"%t - %a\"");
        }

        ConsoleUtil.exitApplication();
    }

}
