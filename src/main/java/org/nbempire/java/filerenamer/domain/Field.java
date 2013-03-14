/**
 * Created by: Nahuel Barrios.
 * On: 08/09/10 at 17:52hs.
 */
package org.nbempire.java.filerenamer.domain;

/**
 * @author Barrios, Nahuel.
 * @since 0.1
 */
public class Field implements Comparable<Field> {

    private String sortPattern;
    private String key;
    private String value;

    /**
     * A constructor method for the Field type.
     *
     * @param fieldKey
     * @param fieldValue
     *
     * @since 0.1
     */
    public Field(String sortPattern, String fieldKey, String fieldValue) {
        this.sortPattern = sortPattern;
        this.key = fieldKey;
        this.value = fieldValue;
    }

    /**
     * Accessor for the attribute of the entity.
     *
     * @return {@link String} the value.
     *
     * @since 0.1
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Field [key=" + key + ", value=" + value + "]";
    }

    @Override
    public int compareTo(Field otherObject) {
        int firstIdx = sortPattern.indexOf(key);
        int secondIdx = sortPattern.indexOf(otherObject.key);

        return new Integer(firstIdx).compareTo(secondIdx);
    }
}
