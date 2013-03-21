/**
 * Created by: Nahuel Barrios.
 * On: 17/08/12 at 19:22hs.
 */
package org.nbempire.java.filerenamer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.nbempire.java.filerenamer.domain.Extensions;
import org.nbempire.java.filerenamer.domain.FileName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Test for {@link org.nbempire.java.filerenamer.service.impl.FileNameServiceImpl}.
 *
 * @author Nahuel Barrios.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext-componenteTest.xml")
public class FileNameServiceComponenteTest {

    /**
     * The class to test.
     */
    @Autowired
    private FileNameService service;

    @Test
    public void rename_withRightPatterns_returnCorrectFileName() {
        String field1 = "artista";
        String field2 = "titulo.wow (original remix)";

        FileName fileName = new FileName(field1 + " - " + field2, Extensions.mp3);
        String newName = service.rename(fileName, "%a - %t", "%t - %a");

        assertNotNull(newName);
        assertEquals(field2 + " - " + field1 + ".mp3", newName);
    }

    @Test
    public void createFrom_withJustOneWordAndFormatMP3_returnFileName() {
        FileName fileName = service.createFrom("nombre.mp3");
        assertNotNull(fileName);
        assertEquals("nombre", fileName.getName());
        assertEquals(Extensions.mp3, fileName.getExtension());
    }


    @Test
    public void rename_with2patternsSeparatedBySpace3Characters_returnFileName() {
        String field1 = "artista";
        String field2 = "titulo";

        FileName fileName = new FileName(field1 + " --- " + field2, Extensions.mp3);
        String newName = service.rename(fileName, "%a --- %t", "%t -_+ %a");

        assertNotNull(newName);
        assertEquals(field2 + " -_+ " + field1 + ".mp3", newName);
    }

    @Test
    public void rename_with2patternsWithMoreThanOneWordEachSeparatedBy1Character_returnFileName() {
        String field1 = "artista de 4 palabras";
        String field2 = "titulo de mas de 4 palabras";

        FileName fileName = new FileName(field1 + "-" + field2, Extensions.mp3);
        String newName = service.rename(fileName, "%a-%t", "%t _ %a");

        assertNotNull(newName);
        assertEquals(field2 + " _ " + field1 + ".mp3", newName);
    }

    @Test
    public void rename_with2patternsWithMoreThanOneWordEachAndDotsSeparatedBy1Character_returnFileName() {
        String field1 = "artista de 4 palabras";
        String field2 = "titulo de mas.de.4 palabras";

        FileName fileName = new FileName(field1 + " - " + field2, Extensions.mp3);
        String newName = service.rename(fileName, "%a - %t", "%t _ %a");

        assertNotNull(newName);
        assertEquals(field2 + " _ " + field1 + ".mp3", newName);
    }

    @Test
    public void rename_with2patternsOfOneWordSeparatedBySpace_returnFileName() {
        String field1 = "07";
        String field2 = "titulo";

        FileName fileName = new FileName(field1 + " " + field2, Extensions.mp3);
        String newName = service.rename(fileName, "%a %t", "%t - %a");

        assertNotNull(newName);
        assertEquals(field2 + " - " + field1 + ".mp3", newName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rename_withIncorrectPattern_dontRenameFileName() {
        String field1 = "artista";
        String field2 = "titulo";

        FileName fileName = new FileName(field1 + "---------" + field2, Extensions.mp3);
        String renamedFileName = service.rename(fileName, "%a - %t", "%t - %a");
        fail("Debería haber tirado una excepción, pero se renombró el archivo y quedó como: " + renamedFileName);
    }

    @Test
    public void rename_with2patternsAndSecondWithLenghtLessThanLenghtOfFieldsSeparator_returnFileName() {
        String field1 = "parte 1 con varias palabras";
        String field2 = "02";

        FileName fileName = new FileName(field1 + " --- " + field2, Extensions.mp3);
        String newName = service.rename(fileName, "%a --- %t", "%t - %a");

        assertNotNull(newName);
        assertEquals(field2 + " - " + field1 + ".mp3", newName);
    }

    @Test
    public void rename_with3patternsAndFieldsWithoutWhitespaces_returnFileName() {
        String field1 = "anArtist";
        String field2 = "aSongName";
        String field3 = "[some extra info to remove]";

        FileName fileName = new FileName(field1 + " --- " + field2 + " " + field3, Extensions.mp3);
        String newName = service.rename(fileName, "%a --- %t %c", "%t - %a");

        assertNotNull(newName);
        assertEquals(field2 + " - " + field1 + ".mp3", newName);
    }

    @Test
    public void rename_with3patternsAndFieldsWithWhitespaces_returnFileName() {
        String field1 = "an artist";
        String field2 = "a song name";
        String field3 = "[some extra info to remove]";

        FileName fileName = new FileName(field1 + " --- " + field2 + " " + field3, Extensions.mp3);
        String newName = service.rename(fileName, "%a --- %t [%c]", "%t - %a");

        assertNotNull(newName);
        assertEquals(field2 + " - " + field1 + ".mp3", newName);
    }

}
