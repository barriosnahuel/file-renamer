/**
 * Created by: Nahuel Barrios.
 * On: 11/09/12 at 17:30hs.
 */
package org.nbempire.java.filerenamer.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.nbempire.java.filerenamer.MainKeys;
import org.nbempire.java.filerenamer.domain.Pattern;
import org.nbempire.java.filerenamer.service.PatternService;

/**
 * Implementation of {@link PatternService}.
 *
 * @author Nahuel Barrios.
 * @since 0.2
 */
public class PatternServiceImpl implements PatternService {

    /**
     * The lenght of the pattern key used when specifing a pattern. It's 2 because user must use a {@code %} followed only by <b>one</b>
     * character.
     */
    private static final int PATTERN_KEY_LENGTH = 2;

    @Override
    public Pattern createFrom(String aPattern) {
        Pattern thePattern = new Pattern();

        String fieldsSeparator = getFieldsSeparator(aPattern);
        thePattern.setFieldsSeparator(fieldsSeparator);

        thePattern.setPatternsName(getPatternsName(aPattern, fieldsSeparator));
        return thePattern;
    }

    /**
     * Inspects the {@code pattern} to guess which is the fields separator.
     *
     * @param pattern
     *         String with a pattern of a file name.
     *
     * @return The fields separator.
     *
     * @since 0.2
     */
    private String getFieldsSeparator(String pattern) {
        int firstBreakpoint = pattern.indexOf(MainKeys.SYMBOL_FIELDS_PREFFIX);
        if (firstBreakpoint < 0) {
            throw new IllegalArgumentException("Missing " + MainKeys.SYMBOL_FIELDS_PREFFIX + " preffix for pattern keys.");
        }
        pattern = pattern.substring(firstBreakpoint + 2);

        return pattern.substring(0, pattern.indexOf(MainKeys.SYMBOL_FIELDS_PREFFIX));
    }

    /**
     * Inspects the {@code pattern} to identify pattern's members.
     *
     * @param pattern
     *         The pattern.
     *
     * @return Set of Strings containing each identified pattern member.
     *
     * @since 0.2
     */
    private List<String> getPatternsName(String pattern, String fieldsSeparator) {
        List<String> patternsName = new ArrayList<String>();
        while (!pattern.equals("")) {

            int firstBreakpoint = pattern.indexOf(MainKeys.SYMBOL_FIELDS_PREFFIX);
            String aKey = pattern.substring(firstBreakpoint, firstBreakpoint + PATTERN_KEY_LENGTH);

            patternsName.add(aKey);

            pattern = pattern.substring(firstBreakpoint + PATTERN_KEY_LENGTH);
        }

        return patternsName;
    }

}
