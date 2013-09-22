/**
 * Created by: Nahuel Barrios.
 * On: 11/09/12 at 17:30hs.
 */
package org.nbempire.java.filerenamer.service.impl;

import org.nbempire.java.filerenamer.MainKeys;
import org.nbempire.java.filerenamer.domain.Pattern;
import org.nbempire.java.filerenamer.service.PatternService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link PatternService}.
 *
 * @author Nahuel Barrios.
 * @since 0.2
 */
@Service
public class PatternServiceImpl implements PatternService {

    /**
     * The lenght of the pattern key used when specifing a pattern. It's 2 because user must use a {@code %} followed only by <b>one</b> character.
     */
    private static final int PATTERN_KEY_LENGTH = 2;

    @Override
    public Pattern createFrom(String aPattern) {
        Pattern thePattern = new Pattern();

        List<String> fieldsSeparator = getFieldsSeparator(aPattern);
        thePattern.setFieldsSeparators(fieldsSeparator);

        thePattern.setPatternsName(getPatternsName(aPattern));
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
    private List<String> getFieldsSeparator(String pattern) {
        int firstBreakpoint = pattern.indexOf(MainKeys.SYMBOL_FIELDS_PREFFIX);
        if (firstBreakpoint < 0) {
            throw new IllegalArgumentException("Missing " + MainKeys.SYMBOL_FIELDS_PREFFIX + " preffix for pattern keys.");
        }
        List<String> separators = new ArrayList<String>();

        int beginIndexOfSeparator = firstBreakpoint + 2;
        String pending = pattern.substring(beginIndexOfSeparator);

        while (pending.length() > 0) {
            firstBreakpoint = pending.indexOf(MainKeys.SYMBOL_FIELDS_PREFFIX);
            if (firstBreakpoint >= 0) {
                separators.add(pending.substring(0, firstBreakpoint));
                pending = pending.substring(firstBreakpoint + 2);
            } else {
                break;
            }
        }

        return separators;
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
    private List<String> getPatternsName(String pattern) {
        List<String> patternsName = new ArrayList<String>();
        while (!pattern.equals("")) {

            int firstBreakpoint = pattern.indexOf(MainKeys.SYMBOL_FIELDS_PREFFIX);
            if (firstBreakpoint < 0) {
                break;
            }
            String aKey = pattern.substring(firstBreakpoint, firstBreakpoint + PATTERN_KEY_LENGTH);

            patternsName.add(aKey);

            pattern = pattern.substring(firstBreakpoint + PATTERN_KEY_LENGTH);
        }

        return patternsName;
    }

}
