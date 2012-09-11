/**
 * Created by: Nahuel Barrios.
 * On: 08/09/10 at 17:52hs.
 */
package org.nbempire.java.filerenamer.service.impl;

import java.util.*;

import org.nbempire.java.filerenamer.MainKeys;
import org.nbempire.java.filerenamer.domain.Extensions;
import org.nbempire.java.filerenamer.domain.Field;
import org.nbempire.java.filerenamer.domain.FileName;
import org.nbempire.java.filerenamer.service.FileNameService;
import org.springframework.stereotype.Service;

/**
 * Implementation of the interface {@link FileNameService}.
 *
 * @author Nahuel Barrios.
 * @since 0.1
 */
@Service
public class FileNameServiceImpl implements FileNameService {

    public String rename(FileName fileName, String inputPattern, String outputPattern) {
        String inputFieldsSeparator = this.getFieldsSeparator(inputPattern);

        List<String> inputPatternsName = this.getPatternsName(inputPattern);

        Map<String, String> keysAndValues = this.getFieldsFromFileNameBasedOnPatternKeys(fileName, inputFieldsSeparator, inputPatternsName);

        SortedSet<Field> fields = new TreeSet<Field>();
        for (String eachPatternName : inputPatternsName) {
            fields.add(new Field(outputPattern, eachPatternName, keysAndValues.get(eachPatternName)));
        }

        String newName = "";
        String outputFieldsSeparator = this.getFieldsSeparator(outputPattern);
        for (Field field : fields) {
            newName += field.getValue() + outputFieldsSeparator;
        }
        newName = newName.substring(0, newName.length() - outputFieldsSeparator.length());

        if (fileName.getExtension() != null) {
            newName += '.' + fileName.getExtension().toString();
        }

        return newName;
    }

    public FileName createFrom(String name) {
        int lastDotPosition = name.lastIndexOf(".");
        return new FileName(name.substring(0, lastDotPosition), Extensions.valueOf(name.substring(lastDotPosition + 1)));
    }

    /**
     * Inspects the <code>pattern</code> to identify pattern's members.
     *
     * @param pattern
     *         The pattern.
     *
     * @return List of Strings containing each identified pattern member.
     *
     * @since 0.1
     */
    private List<String> getPatternsName(String pattern) {
        String tmp = pattern;

        List<String> patternsName = new ArrayList<String>();
        while (!tmp.equals("")) {

            int firstBreakpoint = tmp.indexOf(MainKeys.SYMBOL_FIELDS_PREFFIX);
            String aKey = tmp.substring(firstBreakpoint, firstBreakpoint + 2);

            patternsName.add(aKey);

            tmp = tmp.substring(firstBreakpoint + 2);
        }

        return patternsName;
    }

    /**
     * Inspects the <code>pattern</code> to guess which is the fields separator.
     *
     * @param pattern
     *         String with a pattern of a file name.
     *
     * @return The fields separator.
     *
     * @since 0.1
     */
    private String getFieldsSeparator(String pattern) {
        String tmp = pattern;

        int firstBreakpoint = pattern.indexOf(MainKeys.SYMBOL_FIELDS_PREFFIX);
        tmp = tmp.substring(firstBreakpoint + 2);

        return tmp.substring(0, tmp.indexOf(MainKeys.SYMBOL_FIELDS_PREFFIX));
    }

    /**
     * @param fileName
     *         The FileName to parse.
     * @param fieldsSeparator
     *         the fields separator from the specified <code>fileName</code>.
     * @param patternKeys
     *         List of Strings containing pattern keywords.
     *
     * @return Map containing a pair of patternKeyword-keywordValue.
     *
     * @since 0.1
     */
    private Map<String, String> getFieldsFromFileNameBasedOnPatternKeys(FileName fileName, String fieldsSeparator, List<String> patternKeys) {
        Map<String, String> fields = new HashMap<String, String>();

        String nameWithoutExtension = fileName.getName();
        if (nameWithoutExtension != null) {
            int breakpoint = nameWithoutExtension.indexOf(fieldsSeparator);
            if (breakpoint < 0) {
                throw new IllegalArgumentException("The file \"" + getCompleteName(fileName) + "\" won't be renamed because it doesn't match the " +
                                                           "pattern.");
            }

            for (String eachPatternKeyword : patternKeys) {
                String keywordValue;

                breakpoint = nameWithoutExtension.indexOf(fieldsSeparator);
                if (breakpoint < 0) {
                    //There isn't any more field. There only is the last field.
                    keywordValue = nameWithoutExtension;
                } else {
                    keywordValue = nameWithoutExtension.substring(0, breakpoint);
                }
                fields.put(eachPatternKeyword, keywordValue);

                nameWithoutExtension = nameWithoutExtension.substring(breakpoint + fieldsSeparator.length());
            }
        }

        return fields;
    }

    /**
     * Concats the file name with its extension to return the complete name.
     *
     * @param fileName
     *         The FileName.
     *
     * @return {@link String} with the file name and its extension concated with a dot.
     *
     * @since 0.1
     */
    private String getCompleteName(FileName fileName) {
        return fileName.getName().concat(".").concat(fileName.getExtension().toString());
    }

}