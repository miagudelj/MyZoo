package ch.bzz.myZoo.model;

import javax.validation.constraints.Pattern;
import javax.ws.rs.FormParam;

/**
 * short description
 * <p>
 * Zoo
 *
 * @author Mia Gudelj
 * @version 1.0
 * @since 02.03.20
 */
public class Zoo {

    @Pattern(regexp = "[0-9a-f]{8}-([0-9a-f]{4}-){3}[0-9a-f]{12}")
    @FormParam("zooUUID")
    private String zooUUID;

    private String zoo;

    /**
     * constructor:
     */
    public Zoo() {

    }

    /**
     * Gets the zooUUID
     *
     * @return value of zooUUID
     */
    public String getZooUUID() {
        return zooUUID;
    }

    /**
     * Sets the zooUUID
     *
     * @param zooUUID the value to set
     */

    public void setZooUUID(String zooUUID) {
        this.zooUUID = zooUUID;
    }

    /**
     * Gets the zoo
     *
     * @return value of zoo
     */
    public String getZoo() {
        return zoo;
    }

    /**
     * Sets the zoo
     *
     * @param zoo the value to set
     */

    public void setZoo(String zoo) {
        this.zoo = zoo;
    }
}
