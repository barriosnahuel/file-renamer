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
 * TODO : JavaDoc : for FileNameServiceComponenteTest.
 *
 * @author Nahuel Barrios.
 * @since 0.1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext-componenteTest.xml")
public class FileNameServiceComponenteTest {

    private FileNameService service = new FileNameServiceImpl();

    @Test
    public void renameToPattern_withCommonPatter_returnCorrectFileName() {
        FileName fileName = new FileName("artista - titulo.wow (original remix)", Extensions.mp3);
        String newName = service.rename(fileName, "%a - %t", "%t - %a");

        Assert.assertNotNull(newName);
        Assert.assertEquals("titulo.wow (original remix) - artista.mp3", newName);
    }

    @Test
    public void createFrom() {
        FileName fileName = service.createFrom("nombre.mp3");
        Assert.assertNotNull(fileName);
        Assert.assertEquals("nombre", fileName.getName());
        Assert.assertEquals(Extensions.mp3, fileName.getExtension());
    }

}
