package ch.bzz.myZoo.model;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import java.time.LocalDate;

/**
 * an animal in the cache
 * ein Tier im Gehege
 * <p>
 * Zoo
 *
 * @author Mia Gudelj
 * @version 1.0
 * @since 02.03.20
 */
public class Tier {

    //declare variables

    @FormParam("tierUUID")
    private String tierUUID;

    @FormParam("art")
    @Size(min = 2, max = 30)
    private String art;

    @FormParam("name")
    @Size(min = 2, max = 30)
    private String name;

    @FormParam("geburtsdatum")
    @Pattern(regexp = "([0-9]{2}/){2}[0-9]{4}")
    private String geburtsdatum;

    @FormParam("alter")
    private int alter;

    @FormParam("beine")
    @DecimalMin(value = "0")
    private int beine;

    @FormParam("fell")
    private boolean fell;

    @Valid
    @BeanParam
    private Zoo zoo;

    /**
     * constructor:
     */
    public Tier() {

    }

    /**
     * Gets the art
     *
     * @return value of art
     */
    public String getArt() {
        return this.art;
    }

    /**
     * Sets the art
     *
     * @param art the value to set
     */

    public void setArt(String art) {
        this.art = art;
    }

    /**
     * Gets the name
     *
     * @return value of name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name
     *
     * @param name the value to set
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the tierUUID
     *
     * @return value of tierUUID
     */
    public String getTierUUID() {
        return this.tierUUID;
    }

    public void setTierUUID(String tierUUID) { this.tierUUID = tierUUID; }

    /**
     * Gets the geburtsdatum
     *
     * @return value of geburtsdatum
     */
    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    /**
     * Sets the geburtsdatum
     *
     * @param geburtsDatum the values to set
     */

    public void setGeburtsdatum(String geburtsDatum) {
        this.geburtsdatum = geburtsDatum;
    }

    /**
     * Gets the alter
     *
     * @return value of alter
     */
    public int getAlter() {
        LocalDate heute = LocalDate.now();

        String[] daten = geburtsdatum.split("/");
        Integer[] datenZahl = new Integer[3];

        for (int i = 0; i < daten.length; i++) {
            datenZahl[i] = Integer.parseInt(daten[i]);
        }

        LocalDate date;
        date = LocalDate.of(datenZahl[2], datenZahl[1], datenZahl[0]);

        alter = heute.getYear() - date.getYear();

        return alter;
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

    /**
     * Gets the beine
     *
     * @return value of beine
     */
    public int getBeine() {
        return this.beine;
    }

    /**
     * Sets the beine
     *
     * @param anzahlBeine the value to set
     */

    public void setBeine(int anzahlBeine) {
        this.beine = anzahlBeine;
    }

    /**
     * Gets the fell
     *
     * @return value of fell
     */
    public boolean getFell() {
        return this.fell;
    }

    /**
     * Sets the fell
     *
     * @param hasFellText the value to set
     */

    public void setFell(String hasFellText) {
        if (hasFellText.equalsIgnoreCase("ja")) {
            this.fell = true;
        }
        if (hasFellText.equalsIgnoreCase("nein")) {
            this.fell = false;
        }
    }

    /***
     * gibt den Zoo zurück
     * @return den Zoo des Tieres
     */
    public Zoo getZoo() {
        return zoo;
    }

    /***
     * setzt den Zoo
     * @param zoo
     */
    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

}
