/**
 * Created by: Nahuel Barrios.
 * On: 08/09/10 at 17:52hs.
 */
package org.nbempire.java.filerenamer.domain;

/**
 * TODO : JavaDoc : for FileName.
 *
 * @author Nahuel Barrios.
 * @since 0.1
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
     * @since 0.1
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
     * @since 0.1
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
     * @since 0.1
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Accessor for the attribute of the entity.
     *
     * @return {@link Extensions} the extension.
     *
     * @since 0.1
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
     * @since 0.1
     */
    public void setExtension(Extensions extension) {
        this.extension = extension;
    }

    /**
     * @return
     *
     * @since 0.1
     */
    @Override
    public String toString() {
        return "FileName [name=" + name + ", extension=" + extension + "]";
    }

}
