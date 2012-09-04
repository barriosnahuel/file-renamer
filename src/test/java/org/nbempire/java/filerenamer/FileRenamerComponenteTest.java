/**
 * Created by: Nahuel Barrios.
 * On: 17/08/12 at 19:22hs.
 */
package org.nbempire.java.filerenamer;

import java.io.File;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Test for {@link FileRenamer}.
 *
 * @author Nahuel Barrios.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:/applicationContext-componenteTest.xml")
public class FileRenamerComponenteTest {

    private static final String KEY_ARTIST = "artista";
    private static final String KEY_TITLE = "titulo";

    /**
     * The FileRenamer to test.
     */
    //@Autowired
    private FileRenamer fileRenamer = new FileRenamer();

    /**
     * Test method for {@link org.nbempire.java.filerenamer.FileRenamer#doMagic(java.lang.String, java.lang.String, java.lang.String)} .
     */
    @Test
    public void testDoMagic_forrTest1WithRightPatterns_renameFiles() {
        String fileSeparator = System.getProperty("file.separator");

        String path = System.getProperty("user.dir") + fileSeparator;
        path += "src" + fileSeparator + "test" + fileSeparator + "resources" + fileSeparator + "test1";

        String inputPattern = "%a - %t";
        String outputPattern = "%t - %a";
        int renamedFiles = fileRenamer.doMagic(path, inputPattern, outputPattern);

        Assert.assertEquals(3, renamedFiles);

        File[] files = new File(path).listFiles();
        Assert.assertNotNull(files);
        Assert.assertEquals(3, files.length);

        for (File eachFile : files) {
            String fileName = eachFile.getName();

            int indexOfArtist = fileName.indexOf(KEY_ARTIST);
            int indexOfTitle = fileName.indexOf(KEY_TITLE);
            Assert.assertTrue("Se perdió la información del artista.", indexOfArtist >= 0);
            Assert.assertTrue("Se perdió la información del título.", indexOfTitle >= 0);

            Assert.assertTrue("titulo debería haber quedado antes que artista", indexOfTitle < indexOfArtist);
        }

        //  Rollback.
        System.out.println("Rollback...");
        renamedFiles = fileRenamer.doMagic(path, outputPattern, inputPattern);
        Assert.assertEquals("En el rollback deberían haberse modificado la misma cantidad de archivos.", 3, renamedFiles);
    }

    /**
     * Test method for {@link org.nbempire.java.filerenamer.FileRenamer#doMagic(java.lang.String, java.lang.String, java.lang.String)} .
     */
    @Test
    public void testDoMagic_forrTest2WithRightPatterns_renameFiles() {
        String fileSeparator = System.getProperty("file.separator");

        String path = System.getProperty("user.dir") + fileSeparator;
        path += "src" + fileSeparator + "test" + fileSeparator + "resources" + fileSeparator + "test2";

        String inputPattern = "%a --- %t";
        String outputPattern = "%t - %a";
        int renamedFiles = fileRenamer.doMagic(path, inputPattern, outputPattern);

        Assert.assertEquals(3, renamedFiles);

        File[] files = new File(path).listFiles();
        Assert.assertNotNull(files);
        Assert.assertEquals(3, files.length);

        for (File eachFile : files) {
            String fileName = eachFile.getName();

            int indexOfArtist = fileName.indexOf(KEY_ARTIST);
            int indexOfTitle = fileName.indexOf(KEY_TITLE);
            Assert.assertTrue("Se perdió la información del artista.", indexOfArtist >= 0);
            Assert.assertTrue("Se perdió la información del título.", indexOfTitle >= 0);

            Assert.assertTrue("titulo debería haber quedado antes que artista", indexOfTitle < indexOfArtist);
        }

        //  Rollback.
        System.out.println("Rollback...");
        renamedFiles = fileRenamer.doMagic(path, outputPattern, inputPattern);
        Assert.assertEquals("En el rollback deberían haberse modificado la misma cantidad de archivos.", 3, renamedFiles);
    }

}
