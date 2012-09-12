/**
 * Created by: Nahuel Barrios.
 * On: 11/09/12 at 17:20hs.
 */
package org.nbempire.java.filerenamer.domain;

import java.util.List;

/**
 * Entity that represents a file name pattern.
 *
 * @author Nahuel Barrios.
 * @since 0.2
 */
public class Pattern {

    private String fieldsSeparator;
    private List<String> patternsName;

    /**
     * s Accessor for the attribute of the entity.
     *
     * @return {@link String} the fieldsSeparator.
     */
    public String getFieldsSeparator() {
        return fieldsSeparator;
    }

    /**
     * Setter for the attribute of the entity.
     *
     * @param fieldsSeparator
     *         {@link String} the fieldsSeparator to set.
     */
    public void setFieldsSeparator(String fieldsSeparator) {
        this.fieldsSeparator = fieldsSeparator;
    }

    /**
     * Accessor for the attribute of the entity.
     *
     * @return {@link List<String>} the patternsName.
     */
    public List<String> getPatternsName() {
        return patternsName;
    }

    /**
     * Setter for the attribute of the entity.
     *
     * @param patternsName
     *         {@link List<String>} the patternsName to set.
     */
    public void setPatternsName(List<String> patternsName) {
        this.patternsName = patternsName;
    }

    @Override
    public String toString() {
        return "Pattern{" +
                       "fieldsSeparator='" + fieldsSeparator + '\'' +
                       ", patternsName=" + patternsName +
                       '}';
    }
}
