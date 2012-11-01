/**
 * Created by: Nahuel Barrios.
 * On: 11/09/12 at 17:24hs.
 */
package org.nbempire.java.filerenamer.service;

import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nbempire.java.filerenamer.domain.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Nahuel Barrios.
 * @since 0.2
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext-componenteTest.xml")
public class PatternServiceTest {

    /**
     * Class logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(PatternServiceTest.class);

    /**
     * The service to test.
     */
    @Autowired
    private PatternService patternService;

    /**
     * Test method for createFrom.
     */
    @Test
    public void createFrom_validInput_createsEntity() throws Exception {
        String inputPattern = "%a - %t";

        Pattern pattern = patternService.createFrom(inputPattern);
        Assert.assertNotNull("pattern mustn't be null.", pattern);
        Assert.assertEquals(" - ", pattern.getFieldsSeparator());

        List<String> patternsName = pattern.getPatternsName();
        Assert.assertNotNull("patternsName mustn't be null.", patternsName);
        Assert.assertEquals(2, patternsName.size());

        logger.debug("Patterns found:");
        for (String eachPatternName : patternsName) {
            logger.debug(eachPatternName);
            Assert.assertEquals(2, eachPatternName.length());
        }
    }

    /**
     * Test method for createFrom.
     */
    @Test
    public void createFrom_validInputWithoutNaturalOrderingOnFields_createsEntity() throws Exception {
        String inputPattern = "%t - %a";

        Pattern pattern = patternService.createFrom(inputPattern);
        Assert.assertNotNull("pattern mustn't be null.", pattern);
        Assert.assertEquals(" - ", pattern.getFieldsSeparator());

        List<String> patternsName = pattern.getPatternsName();
        Assert.assertNotNull("patternsName mustn't be null.", patternsName);
        Assert.assertEquals(2, patternsName.size());

        Iterator<String> iterator = patternsName.iterator();
        Assert.assertEquals("%t", iterator.next());
        Assert.assertEquals("%a", iterator.next());
    }

    /**
     * Test method for createFrom.
     */
    @Test(expected = IllegalArgumentException.class)
    public void createFrom_invalidInputBecauseItHasntPercentageSymbol_createsEntity() throws Exception {
        Pattern pattern = patternService.createFrom("a - t");

        Assert.fail("Should throw an exception, but the return was: " + pattern);
    }

}
