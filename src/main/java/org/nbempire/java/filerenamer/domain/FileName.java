package org.nbempire.java.filerenamer.domain;

/**
 * TODO : JavaDoc : for FileName.
 *
 * @author Nahuel Barrios.
 */
public class FileName {

    private String name;
    private Extensions extension;

    /**
     * A constructor method for the {@link FileName} type.
     *
     * @param name
     * @param extension
     *
     * @author Nahuel Barrios.
     */
    public FileName(String name, Extensions extension) {
        this.name = name;
        this.extension = extension;
    }

    /**
     * Accessor for the attribute of the entity.
     *
     * @return {@link String} the name.
     *
     * @author Nahuel Barrios.
     * @since 26/05/2012.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the attribute of the entity.
     *
     * @param name
     *         {@link String} the name to set.
     *
     * @author Nahuel Barrios.
     * @since 26/05/2012.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accessor for the attribute of the entity.
     *
     * @return {@link Extensions} the extension.
     *
     * @author Nahuel Barrios.
     * @since 26/05/2012.
     */
    public Extensions getExtension() {
        return extension;
    }

    /**
     * Setter for the attribute of the entity.
     *
     * @param extension
     *         {@link Extensions} the extension to set.
     *
     * @author Nahuel Barrios.
     * @since 26/05/2012.
     */
    public void setExtension(Extensions extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "FileName [name=" + name + ", extension=" + extension + "]";
    }

}
