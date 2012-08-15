package org.nbempire.java.filerenamer.domain;

/**
 * @author Barrios, Nahuel.
 * @version 1.0
 * @since Sep 8, 2010, 5:52:56 PM
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
     * @author Thales - PNT Equipo 6.
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
     * @author Thales - PNT Equipo 6.
     * @since 26/05/2012.
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
     * @author Thales - PNT Equipo 6.
     * @since 26/05/2012.
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Accessor for the attribute of the entity.
     *
     * @return {@link String} the value.
     *
     * @author Thales - PNT Equipo 6.
     * @since 26/05/2012.
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
     * @author Thales - PNT Equipo 6.
     * @since 26/05/2012.
     */
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Field [key=" + key + ", value=" + value + "]";
    }

    public int compareTo(Field otherObject) {
        int firstIdx = sortPattern.indexOf(key);
        int secondIdx = sortPattern.indexOf(otherObject.getKey());

        return new Integer(firstIdx).compareTo(new Integer(secondIdx));
    }

}
