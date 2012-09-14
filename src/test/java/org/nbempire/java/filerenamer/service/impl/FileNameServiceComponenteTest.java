/**
 * Created by: Nahuel Barrios.
 * On: 17/08/12 at 19:22hs.
 */
package org.nbempire.java.filerenamer.service.impl;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nbempire.java.filerenamer.domain.Extensions;
import org.nbempire.java.filerenamer.domain.FileName;
import org.nbempire.java.filerenamer.service.FileNameService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test for {@link org.nbempire.java.filerenamer.service.impl.FileNameServiceImpl}.
 *
 * @author Nahuel Barrios.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext-componenteTest.xml")
public class FileNameServiceComponenteTest {

    /**
     * The class to test.
     */
    private FileNameService service = new FileNameServiceImpl();

    @Test
    public void rename_withRightPatterns_returnCorrectFileName() {
        String field1 = "artista";
        String field2 = "titulo.wow (original remix)";

        FileName fileName = new FileName(field1 + " - " + field2, Extensions.mp3);
        String newName = service.rename(fileName, "%a - %t", "%t - %a");

        Assert.assertNotNull(newName);
        Assert.assertEquals(field2 + " - " + field1 + ".mp3", newName);
    }

    @Test
    public void createFrom_withJustOneWordAndFormatMP3_returnFileName() {
        FileName fileName = service.createFrom("nombre.mp3");
        Assert.assertNotNull(fileName);
        Assert.assertEquals("nombre", fileName.getName());
        Assert.assertEquals(Extensions.mp3, fileName.getExtension());
    }


    @Test
    public void rename_with2patternsSeparatedBySpace3Characters_returnFileName() {
        String field1 = "artista";
        String field2 = "titulo";

        FileName fileName = new FileName(field1 + " --- " + field2, Extensions.mp3);
        String newName = service.rename(fileName, "%a --- %t", "%t -_+ %a");

        Assert.assertNotNull(newName);
        Assert.assertEquals(field2 + " -_+ " + field1 + ".mp3", newName);
    }

    @Test
    public void rename_with2patternsWithMoreThanOneWordEachSeparatedBy1Character_returnFileName() {
        String field1 = "artista de 4 palabras";
        String field2 = "titulo de mas de 4 palabras";

        FileName fileName = new FileName(field1 + "-" + field2, Extensions.mp3);
        String newName = service.rename(fileName, "%a-%t", "%t _ %a");

        Assert.assertNotNull(newName);
        Assert.assertEquals(field2 + " _ " + field1 + ".mp3", newName);
    }

    @Test
    public void rename_with2patternsWithMoreThanOneWordEachAndDotsSeparatedBy1Character_returnFileName() {
        String field1 = "artista de 4 palabras";
        String field2 = "titulo de mas.de.4 palabras";

        FileName fileName = new FileName(field1 + " - " + field2, Extensions.mp3);
        String newName = service.rename(fileName, "%a - %t", "%t _ %a");

        Assert.assertNotNull(newName);
        Assert.assertEquals(field2 + " _ " + field1 + ".mp3", newName);
    }

    @Test
    public void rename_with2patternsOfOneWordSeparatedBySpace_returnFileName() {
        String field1 = "07";
        String field2 = "titulo";

        FileName fileName = new FileName(field1 + " " + field2, Extensions.mp3);
        String newName = service.rename(fileName, "%a %t", "%t - %a");

        Assert.assertNotNull(newName);
        Assert.assertEquals(field2 + " - " + field1 + ".mp3", newName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rename_withIncorrectPattern_dontRenameFileName() {
        String field1 = "artista";
        String field2 = "titulo";

        FileName fileName = new FileName(field1 + "---------" + field2, Extensions.mp3);
        String renamedFileName = service.rename(fileName, "%a - %t", "%t - %a");
        Assert.fail("Debería haber tirado una excepción, pero se renombró el archivo y quedó como: " + renamedFileName);
    }

}
