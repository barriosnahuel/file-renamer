package org.nbempire.java.filerenamer.service.impl;

import java.util.*;

import org.nbempire.java.filerenamer.MainKeys;
import org.nbempire.java.filerenamer.domain.Extensions;
import org.nbempire.java.filerenamer.domain.Field;
import org.nbempire.java.filerenamer.domain.FileName;
import org.nbempire.java.filerenamer.service.FileNameService;
import org.springframework.stereotype.Service;

/**
 * TODO : JavaDoc : for FileNameServiceImpl.
 *
 * @author Nahuel Barrios.
 */
@Service
public class FileNameServiceImpl implements FileNameService {

    public String rename(FileName fileName, String inputPattern, String outputPattern) {
        String fieldsSeparator = this.getFieldsSeparator(inputPattern);

        List<String> inputPatternsName = this.getPatternsName(inputPattern);

        Map<String, String> keysAndValues = this.parseFieldsFromPattern(fileName, inputPattern, fieldsSeparator, inputPatternsName);

        SortedSet<Field> fields = new TreeSet<Field>();
        for (String eachPatternName : inputPatternsName) {
            fields.add(new Field(outputPattern, eachPatternName, keysAndValues.get(eachPatternName)));
        }

        String newName = "";
        for (Field field : fields) {
            newName += field.getValue() + fieldsSeparator;
        }
        newName = newName.substring(0, newName.length() - fieldsSeparator.length());

        if (fileName.getExtension() != null) {
            newName += '.' + fileName.getExtension().toString();
        }

        return newName;
    }

    /**
     * TODO : JavaDoc : for FileNameServiceImpl.getFieldsSeparator().
     *
     * @param pattern
     *
     * @return {@link String}
     *
     * @author Nahuel Barrios.
     */
    private String getFieldsSeparator(String pattern) {
        String tmp = pattern;

        int firstBreakpoint = pattern.indexOf(MainKeys.SYMBOL_FIELDS_PREFFIX);
        tmp = tmp.substring(firstBreakpoint + 2);

        return tmp.substring(0, tmp.indexOf(MainKeys.SYMBOL_FIELDS_PREFFIX));
    }

    /**
     * TODO : JavaDoc : for FileNameServiceImpl.getPatternsName().
     *
     * @param pattern
     *
     * @return {@link List<String>}
     *
     * @author Nahuel Barrios.
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

    private Map<String, String> parseFieldsFromPattern(FileName fileName, String pattern, String fieldsSeparator, List<String> patternsName) {
        Map<String, String> fields = new HashMap<String, String>();
        String source = this.getCompleteName(fileName);

        if (source == null) {
            return fields;
        }

        for (int idx = 0; idx < patternsName.size(); idx++) {
            String aPatternName = patternsName.get(idx);

            int breakpoint = source.indexOf(fieldsSeparator);

            String aValue = null;
            if (breakpoint < 0 && (patternsName.size() - idx == 1)) {
                // Si entro por aca no hay mas separadores, entonces:
                // a) Esta el (.) por la extension.
                // b) No hay ninguna "marca" mas, solo esta el ultimo campo.
                int dotBreakpoint = source.lastIndexOf('.');
                if (dotBreakpoint > 0)// a)
                {
                    aValue = source.substring(0, dotBreakpoint);
                } else
                // b)
                {
                    aValue = source;
                }
            } else {
                aValue = source.substring(0, breakpoint);
            }

            fields.put(aPatternName, aValue);

            source = source.substring(breakpoint + fieldsSeparator.length());
        }

        return fields;
    }

    /**
     * TODO : JavaDoc : for FileNameServiceImpl.getCompleteName().
     *
     * @param fileName
     *
     * @return {@link String}
     *
     * @author Nahuel Barrios.
     */
    private String getCompleteName(FileName fileName) {
        return fileName.getName().concat(".").concat(fileName.getExtension().toString());
    }

    public FileName createFrom(String name) {
        int lastDotPosition = name.lastIndexOf(".");
        return new FileName(name.substring(0, lastDotPosition), Extensions.valueOf(name.substring(lastDotPosition + 1)));
    }

}