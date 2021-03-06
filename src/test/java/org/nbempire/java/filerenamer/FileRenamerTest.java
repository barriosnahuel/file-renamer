/**
 * Created by: Nahuel Barrios.
 * On: 17/08/12 at 19:22hs.
 */
package org.nbempire.java.filerenamer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Test for {@link FileRenamer}.
 *
 * @author Nahuel Barrios.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext-componenteTest.xml")
public class FileRenamerTest {

    private static final String KEY_ARTIST = "artista";

    private static final String KEY_TITLE = "titulo";

    /**
     * Class logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(FileRenamerTest.class);

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
        String path = System.getProperty("user.dir") + File.separator;
        path += "src" + File.separator + "test" + File.separator + "resources" + File.separator + "test1";

        String inputPattern = "%a - %t";
        String outputPattern = "%t - %a";
        int renamedFiles = fileRenamer.doMagic(path, inputPattern, outputPattern);

        assertEquals(3, renamedFiles);

        File[] files = new File(path).listFiles();
        assertNotNull(files);
        assertEquals(3, files.length);

        check(files);

        //  Rollback.
        logger.info("Rollback...");
        renamedFiles = fileRenamer.doMagic(path, outputPattern, inputPattern);
        assertEquals("En el rollback deberían haberse modificado la misma cantidad de archivos.", 3, renamedFiles);
    }

    /**
     * Test method for {@link org.nbempire.java.filerenamer.FileRenamer#doMagic(java.lang.String, java.lang.String, java.lang.String)} .
     */
    @Test
    public void testDoMagic_forTest2WithRightPatterns_renameFiles() {
        String path = System.getProperty("user.dir") + File.separator;
        path += "src" + File.separator + "test" + File.separator + "resources" + File.separator + "test2";

        String inputPattern = "%a --- %t";
        String outputPattern = "%t - %a";
        int renamedFiles = fileRenamer.doMagic(path, inputPattern, outputPattern);

        assertEquals(3, renamedFiles);

        File[] files = new File(path).listFiles();
        assertNotNull(files);
        assertEquals(3, files.length);

        check(files);

        //  Rollback.
        logger.info("Rollback...");
        renamedFiles = fileRenamer.doMagic(path, outputPattern, inputPattern);
        assertEquals("En el rollback deberían haberse modificado la misma cantidad de archivos.", 3, renamedFiles);
    }

    /**
     * Test method for {@link org.nbempire.java.filerenamer.FileRenamer#doMagic(java.lang.String, java.lang.String, java.lang.String)} .
     */
    @Test
    public void testDoMagic_forTest3With1FileDoesntMatchPattern_renameAllFilesButThatOne() {
        String path = System.getProperty("user.dir") + File.separator;
        path += "src" + File.separator + "test" + File.separator + "resources" + File.separator + "test3";

        String inputPattern = "%a --- %t";
        String outputPattern = "%t - %a";
        int renamedFiles = fileRenamer.doMagic(path, inputPattern, outputPattern);

        final int EXPECTED_RENAMED_FILES = 3;
        assertEquals(EXPECTED_RENAMED_FILES, renamedFiles);

        File[] files = new File(path).listFiles();
        assertNotNull(files);
        assertEquals(4, files.length);

        for (File eachFile : files) {
            String fileName = eachFile.getName();
            if (fileName.indexOf('0') >= 0) {
                assertEquals("artista0 _'- titulo0.mp3", fileName);
            } else {
                int indexOfArtist = fileName.indexOf(KEY_ARTIST);
                int indexOfTitle = fileName.indexOf(KEY_TITLE);
                assertTrue("Se perdió la información del artista.", indexOfArtist >= 0);
                assertTrue("Se perdió la información del título.", indexOfTitle >= 0);

                assertTrue("titulo debería haber quedado antes que artista", indexOfTitle < indexOfArtist);
            }
        }

        //  Rollback.
        logger.info("Rollback...");
        renamedFiles = fileRenamer.doMagic(path, outputPattern, inputPattern);
        assertEquals("En el rollback deberían haberse modificado la misma cantidad de archivos.", EXPECTED_RENAMED_FILES, renamedFiles);
    }

    /**
     * Test method for {@link org.nbempire.java.filerenamer.FileRenamer#doMagic(java.lang.String, java.lang.String, java.lang.String)} .
     */
    @Test
    public void testDoMagic_forTestHiddenFiles_renameFiles() {
        String path = System.getProperty("user.dir") + File.separator;
        path += "src" + File.separator + "test" + File.separator + "resources" + File.separator + "test-hidden-files";

        File[] files = new File(path).listFiles();
        assertNotNull("Test path must be a directory", files);
        assertEquals(4, files.length);
        assertEquals(".this-is-a-hidden-file", files[0].getName());

        String inputPattern = "%a - %t";
        String outputPattern = "%t - %a";
        int renamedFiles = fileRenamer.doMagic(path, inputPattern, outputPattern);

        final int EXPECTED_RENAMED_FILES = 3;
        assertEquals(EXPECTED_RENAMED_FILES, renamedFiles);

        files = new File(path).listFiles();
        assertNotNull("Test path must be a directory", files);
        assertEquals(4, files.length);
        assertEquals(".this-is-a-hidden-file", files[0].getName());

        //  Rollback.
        logger.info("Rollback...");
        renamedFiles = fileRenamer.doMagic(path, outputPattern, inputPattern);
        assertEquals("En el rollback deberían haberse modificado la misma cantidad de archivos.", EXPECTED_RENAMED_FILES, renamedFiles);
    }

    /**
     * Test method for {@link org.nbempire.java.filerenamer.FileRenamer#doMagic(java.lang.String, java.lang.String, java.lang.String)} .
     */
    @Test
    public void testDoMagic_forTestWithInnerDirectoryWithFilesToRename_renameFilesAtFirstDirectoryOnly() {
        String path = System.getProperty("user.dir") + File.separator;
        path += "src" + File.separator + "test" + File.separator + "resources" + File.separator + "test-with-inner-directory";

        File[] files = new File(path).listFiles();
        assertNotNull("Test path must be a directory", files);
        assertEquals(3, files.length);
        File directory = files[2];
        assertEquals("do not rename", directory.getName());
        assertTrue("Should be a directory", directory.isDirectory());

        String inputPattern = "%a - %t";
        String outputPattern = "%t - %a";
        int renamedFiles = fileRenamer.doMagic(path, inputPattern, outputPattern);

        final int EXPECTED_RENAMED_FILES = 2;
        assertEquals(EXPECTED_RENAMED_FILES, renamedFiles);

        files = new File(path).listFiles();
        assertNotNull("Test path must be a directory", files);
        assertEquals(3, files.length);
        assertEquals("do not rename", files[0].getName());

        //  Rollback.
        logger.info("Rollback...");
        renamedFiles = fileRenamer.doMagic(path, outputPattern, inputPattern);
        assertEquals("En el rollback deberían haberse modificado la misma cantidad de archivos.", EXPECTED_RENAMED_FILES, renamedFiles);
    }

    private void check(File[] files) {
        for (File eachFile : files) {
            String fileName = eachFile.getName();

            int indexOfArtist = fileName.indexOf(KEY_ARTIST);
            int indexOfTitle = fileName.indexOf(KEY_TITLE);
            assertTrue("Se perdió la información del artista.", indexOfArtist >= 0);
            assertTrue("Se perdió la información del título.", indexOfTitle >= 0);

            assertTrue("titulo debería haber quedado antes que artista", indexOfTitle < indexOfArtist);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDoMagic_withInvalidDirectoryPath_throwIllegalArgumentException() {
        //noinspection ResultOfMethodCallIgnored
        fileRenamer.doMagic("sarasa", "", "");
    }

}
