/**
 * Created by: Nahuel Barrios.
 * On: 08/09/10 at 17:52hs.
 */
package org.nbempire.java.filerenamer.domain;

/**
 * Entity that represents the name of a file.
 *
 * @author Nahuel Barrios.
 * @since 0.1
 */
public class FileName {

    /**
     * The name of the file <b>without</b> the file extension.
     */
    private String name;

    /**
     * The file extension.
     */
    private Extensions extension;

    /**
     * A constructor method for the {@link org.nbempire.java.filerenamer.domain.FileName} type.
     *
     * @param name
     *         String with the file name without the extension.
     * @param extension
     *         The file extension.
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
     * Accessor for the attribute of the entity.
     *
     * @return {@link org.nbempire.java.filerenamer.domain.Extensions} the extension.
     *
     * @since 0.1
     */
    public Extensions getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return "FileName [name=" + name + ", extension=" + extension + "]";
    }

}
