/**
 * Created by: Nahuel Barrios.
 * On: 17/08/12 at 19:22hs.
 */
package org.nbempire.java.filerenamer;

import java.io.File;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test for {@link FileRenamer}.
 *
 * @author Nahuel Barrios.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext-componenteTest.xml")
public class FileRenamerComponenteTest {

    private static final String KEY_ARTIST = "artista";
    private static final String KEY_TITLE = "titulo";

    /**
     * Class logger.
     */
    final static Logger logger = LoggerFactory.getLogger(FileRenamerComponenteTest.class);

    /**
     * The FileRenamer to test.
     */
    @Autowired
    private FileRenamer fileRenamer;

    /**
     * Test method for {@link org.nbempire.java.filerenamer.FileRenamer#doMagic(java.lang.String, java.lang.String, java.lang.String)} .
     */
    @Test
    public void testDoMagic_forTest1WithRightPatterns_renameFiles() {
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
        logger.info("Rollback...");
        renamedFiles = fileRenamer.doMagic(path, outputPattern, inputPattern);
        Assert.assertEquals("En el rollback deberían haberse modificado la misma cantidad de archivos.", 3, renamedFiles);
    }

    /**
     * Test method for {@link org.nbempire.java.filerenamer.FileRenamer#doMagic(java.lang.String, java.lang.String, java.lang.String)} .
     */
    @Test
    public void testDoMagic_forTest2WithRightPatterns_renameFiles() {
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
        logger.info("Rollback...");
        renamedFiles = fileRenamer.doMagic(path, outputPattern, inputPattern);
        Assert.assertEquals("En el rollback deberían haberse modificado la misma cantidad de archivos.", 3, renamedFiles);
    }

    /**
     * Test method for {@link org.nbempire.java.filerenamer.FileRenamer#doMagic(java.lang.String, java.lang.String, java.lang.String)} .
     */
    @Test
    public void testDoMagic_forTest3With1FileDoesntMatchPattern_renameAllFilesButThatOne() {
        String fileSeparator = System.getProperty("file.separator");

        String path = System.getProperty("user.dir") + fileSeparator;
        path += "src" + fileSeparator + "test" + fileSeparator + "resources" + fileSeparator + "test3";

        String inputPattern = "%a --- %t";
        String outputPattern = "%t - %a";
        int renamedFiles = fileRenamer.doMagic(path, inputPattern, outputPattern);

        int numberOfParsedFiles = 4;
        Assert.assertEquals(numberOfParsedFiles, renamedFiles);

        File[] files = new File(path).listFiles();
        Assert.assertNotNull(files);
        Assert.assertEquals(numberOfParsedFiles, files.length);

        for (File eachFile : files) {
            String fileName = eachFile.getName();
            if (fileName.indexOf('0') >= 0) {
                Assert.assertEquals("artista0 _'- titulo0.mp3", fileName);
            } else {
                int indexOfArtist = fileName.indexOf(KEY_ARTIST);
                int indexOfTitle = fileName.indexOf(KEY_TITLE);
                Assert.assertTrue("Se perdió la información del artista.", indexOfArtist >= 0);
                Assert.assertTrue("Se perdió la información del título.", indexOfTitle >= 0);

                Assert.assertTrue("titulo debería haber quedado antes que artista", indexOfTitle < indexOfArtist);
            }
        }

        //  Rollback.
        logger.info("Rollback...");
        renamedFiles = fileRenamer.doMagic(path, outputPattern, inputPattern);
        Assert.assertEquals("En el rollback deberían haberse modificado la misma cantidad de archivos.", numberOfParsedFiles, renamedFiles);
    }

}
