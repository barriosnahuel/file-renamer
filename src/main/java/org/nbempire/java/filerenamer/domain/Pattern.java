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

    private List<String> patternsName;

    private List<String> fieldsSeparators;

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

    public List<String> getFieldsSeparators() {
        return fieldsSeparators;
    }

    public void setFieldsSeparators(List<String> fieldsSeparators) {
        this.fieldsSeparators = fieldsSeparators;
    }
}
