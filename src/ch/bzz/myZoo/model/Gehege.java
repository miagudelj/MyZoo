package ch.bzz.myZoo.model;

import ch.bzz.myZoo.data.*;

import java.util.Map;

/**
 * the cache with a map of animals
 * <p>
 * Zoo
 *
 * @author Mia Gudelj
 * @version 1.0
 * @since 02.03.20
 */
public class Gehege {

    //declare variables
    private int anzahlTiere;
    private Map<String, Tier> tierMap;

    /**
     * constructor: read all the animals
     */
    public Gehege() {
        tierMap = DataHandler.getTierMap();
    }

    /**
     * Gets the tierMap
     *
     * @return value of tierMap
     */
    public Map<String, Tier> getTierMap() {

        return tierMap;
    }

    /**
     * Sets the tierMap
     *
     * @param tierMap the value to set
     */

    public void setTierMap(Map<String, Tier> tierMap) {

        this.tierMap = tierMap;
    }

    /**
     * Gets the anzahlTiere
     *
     * @return value of anzahlTiere
     */
    public int getAnzahlTiere() {
        anzahlTiere = tierMap.size();
        return anzahlTiere;
    }
}
