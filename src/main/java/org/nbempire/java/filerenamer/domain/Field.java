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
     * A constructor method for the {@link Field} type.
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
     * @return {@link String} the key.
     *
     * @since 0.1
     */
    public String getKey() {
        return key;
    }

    /**
     * Setter for the attribute of the entity.
     *
     * @param key
     *         {@link String} the key to set.
     *
     * @since 0.1
     */
    public void setKey(String key) {
        this.key = key;
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

    /**
     * Setter for the attribute of the entity.
     *
     * @param value
     *         {@link String} the value to set.
     *
     * @since 0.1
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Field [key=" + key + ", value=" + value + "]";
    }

    @Override
    public int compareTo(Field otherObject) {
        int firstIdx = sortPattern.indexOf(key);
        int secondIdx = sortPattern.indexOf(otherObject.getKey());

        return new Integer(firstIdx).compareTo(secondIdx);
    }
}
