package ch.bzz.myZoo.model;

/**
 * short description
 * <p>
 * myZoo_services
 *
 * @author Mia Gudelj
 * @version 1.0
 * @since 01.04.20
 */
public class User {

    String username;
    String userRole;

    public User() {
        userRole = "guest";
    }

    /**
     * Gets the username
     *
     * @return value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username
     *
     * @param username the value to set
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the userRole
     *
     * @return value of userRole
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * Sets the userRole
     *
     * @param userRole the value to set
     */

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
