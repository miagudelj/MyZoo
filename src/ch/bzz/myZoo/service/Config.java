package ch.bzz.myZoo.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.*;
import java.io.*;
import java.util.*;

/**
 * configure the web services and properties
 * <p>
 * M133: MyZoo
 *
 * @author Marcel Suter (Ghwalin)
 * @author Mia Gudelj
 */

@ApplicationPath("/resource")

public class Config extends Application {
    private static final String PROPERTIES_PATH = "/home/bzz/webapp/tier.properties";
    private static Properties properties = null;

    /**
     * define all provider classes
     *
     * @return set of classes
     */
    @Override
    public Set<Class<?>> getClasses() {
        HashSet providers = new HashSet<Class<?>>();
        providers.add(ZooService.class);
        providers.add(UserService.class);
        providers.add(GehegeService.class);

        return providers;
    }


    /**
     * Gets the value of a property
     *
     * @param property the key of the property to be read
     * @return the value of the property
     */

    public static String getProperty(String property) {
        if (Config.properties == null) {
            setProperties(new Properties());
            readProperties();
        }
        String value = Config.properties.getProperty(property);
        if (value == null) return "";
        return value;
    }

    /**
     * reads the properties file
     */
    private static void readProperties() {

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(PROPERTIES_PATH);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {

            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }

        }

    }

    /**
     * Sets the properties
     *
     * @param properties the value to set
     */

    private static void setProperties(Properties properties) {
        Config.properties = properties;
    }
}