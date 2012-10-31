/**
 * Created by: Nahuel Barrios.
 * On: 31/10/12 at 12:14hs.
 */
package org.nbempire.java.filerenamer;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Nahuel Barrios.
 * @since 14
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext-componenteTest.xml")
public class ApplicationComponentTest {

    /**
     * Test method for main.
     */
    @Test(expected = IllegalArgumentException.class)
    public void main_withInvalidDirectoryPath_throwIllegalArgumentException() throws Exception {
        Application.main(new String[]{"un path cualquiera", "%a - %t", "%t - %a"});
    }

    @Test
    @Ignore
    public void main_withValidParameters_renameFiles() throws Exception {
        String fileSeparator = System.getProperty("file.separator");

        String path = System.getProperty("user.dir") + fileSeparator;
        path += "src" + fileSeparator + "test" + fileSeparator + "resources" + fileSeparator + "test1";

        String inputPattern = "%a - %t";
        String outputPattern = "%t - %a";
        Application.main(new String[]{path, inputPattern, outputPattern});

        //  TODO : Hacer el rollback, ver como hacer para que el System.exit() no me salga del test.

        ////  Rollback
        //Application.main(new String[]{path, outputPattern, inputPattern});
    }
}
