/**
 * Created by: Nahuel Barrios.
 * On: 08/09/10 at 17:52hs.
 */
package org.nbempire.java.filerenamer.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.nbempire.java.filerenamer.domain.Extensions;
import org.nbempire.java.filerenamer.domain.Field;
import org.nbempire.java.filerenamer.domain.FileName;
import org.nbempire.java.filerenamer.domain.Pattern;
import org.nbempire.java.filerenamer.service.FileNameService;
import org.nbempire.java.filerenamer.service.PatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the interface {@link org.nbempire.java.filerenamer.service.FileNameService}.
 *
 * @author Nahuel Barrios.
 * @since 0.1
 */
@Service
public class FileNameServiceImpl implements FileNameService {

    /**
     * A service for the {@link org.nbempire.java.filerenamer.domain.Pattern} entity.
     */
    @Autowired
    private PatternService patternService;

    public String rename(FileName fileName, String input, String output) {
        Pattern inputPattern = patternService.createFrom(input);

        Map<String, String> keysAndValues = this.getFieldAndValues(fileName, inputPattern);

        SortedSet<Field> fields = new TreeSet<Field>();
        for (String eachPatternName : inputPattern.getPatternsName()) {
            fields.add(new Field(output, eachPatternName, keysAndValues.get(eachPatternName)));
        }

        String newName = "";
        Pattern outputPattern = patternService.createFrom(output);
        for (Field field : fields) {
            newName += field.getValue() + outputPattern.getFieldsSeparator();
        }
        newName = newName.substring(0, newName.length() - outputPattern.getFieldsSeparator().length());

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
     * @param fileName
     *         The FileName to parse.
     * @param input
     *         The input pattern.
     *
     * @return Map containing a pair of patternKeyword-keywordValue.
     *
     * @since 0.1
     */
    private Map<String, String> getFieldAndValues(FileName fileName, Pattern input) {
        Map<String, String> fields = new HashMap<String, String>();

        String nameWithoutExtension = fileName.getName();
        if (nameWithoutExtension != null) {
            int breakpoint = nameWithoutExtension.indexOf(input.getFieldsSeparator());
            if (breakpoint < 0) {
                throw new IllegalArgumentException("The file \"" + getCompleteName(fileName) + "\" won't be renamed because it doesn't match the " +
                                                           "pattern.");
            }

            for (String eachPatternKeyword : input.getPatternsName()) {
                String keywordValue;

                breakpoint = nameWithoutExtension.indexOf(input.getFieldsSeparator());
                if (breakpoint < 0) {
                    //There isn't any more field. There only is the last field.
                    keywordValue = nameWithoutExtension;
                } else {
                    keywordValue = nameWithoutExtension.substring(0, breakpoint);
                    nameWithoutExtension = nameWithoutExtension.substring(breakpoint + input.getFieldsSeparator().length());
                }
                fields.put(eachPatternKeyword, keywordValue);
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