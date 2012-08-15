package org.nbempire.java.filerenamer;

import java.io.File;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * TODO : JavaDoc : for FileRenamerComponenteTest.
 *
 * @author Thales - PNT Equipo 6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext-componenteTest.xml")
public class FileRenamerComponenteTest {

    @Autowired
    private FileRenamer fileRenamer;

    /**
     * Test method for {@link org.nbempire.java.filerenamer.FileRenamer#doMagic(java.lang.String, java.lang.String, java.lang.String)} .
     */
    @Test
    public void testDoMagic() {
        String path = System.getProperty("user.dir");
        path += "\\src\\test\\resources";
        fileRenamer.doMagic(path, "%a - %t", "%t - %a");

        File[] files = new File(path).listFiles();
        Assert.assertNotNull(files);
        Assert.assertEquals(4, files.length);
        // TODO - Mejorar este test.
        // File file = files[0];
        // Assert.assertEquals(, file.getName());
        // file = files[1];
        // file = files[2];
        // file = files[3];
    }

}
