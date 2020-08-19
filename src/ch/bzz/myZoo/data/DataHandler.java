package ch.bzz.myZoo.data;

import ch.bzz.myZoo.model.Tier;
import ch.bzz.myZoo.model.User;
import ch.bzz.myZoo.model.Zoo;
import ch.bzz.myZoo.service.Config;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * data handler for reading and writing the csv files
 * <p>
 * M133: MyZoo
 *
 * @author Mia Gudelj
 */

public class DataHandler {
    private static final DataHandler instance = new DataHandler();
    private static Map<String, Tier> tierMap = new HashMap<>();
    private static Map<String, Zoo> zooMap = new HashMap<>();

    /**
     * default constructor: defeat instantiation
     */
    private DataHandler() {
    }

    /**
     * @return the instance of this class
     */
    public static DataHandler getInstance() {
        return DataHandler.instance;
    }

    /**
     * reads all data from the csv-file into the tierMap
     *
     * @return a map with all animals
     */
    public static void readTiere() {

        FileReader fileReader;
        // Steuert die Verarbeitung des FileReaders
        BufferedReader bufferedReader;

        try {
            String tierPath = Config.getProperty("tierFile");
            fileReader = new FileReader(tierPath);
            bufferedReader = new BufferedReader(fileReader);

        } catch (FileNotFoundException fileEx) {
            fileEx.printStackTrace();
            throw new RuntimeException();
        }

        try {
            // nimmt eine Zeile aus der Datei entgegen
            String line;
            Tier tier = null;

            while ((line = bufferedReader.readLine()) != null) {
                // Datei verarbeiten
                tier = new Tier();
                String[] values = line.split("; ");

                tier.setTierUUID(values[0]);
                tier.setArt(values[1]);
                tier.setName(values[2]);
                tier.setGeburtsdatum(values[3]);
                tier.setAlter(new Integer(values[4]));
                tier.setBeine(new Integer(values[5]));
                tier.setFell(values[6]);

                Zoo zoo = getZooMap().get(values[7]);
                tier.setZoo(zoo);

                tierMap.put(values[0], tier);
            }

        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            throw new RuntimeException();

        } finally {
            // files schliessen
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
                throw new RuntimeException();
            }
        }
    }

    /**
     * reads all data from the csv-file into the zooMap
     *
     */
    public static void readZoos() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        try {
            String zooPath = Config.getProperty("zooFile");
            fileReader = new FileReader(zooPath);
            bufferedReader = new BufferedReader(fileReader);

        } catch (FileNotFoundException fileEx) {
            fileEx.printStackTrace();
            throw new RuntimeException();
        }

        try {
            String line;
            Zoo zoo = null;
            while ((line = bufferedReader.readLine()) != null) {
                zoo = new Zoo();
                String[] values = line.split("; ");
                zoo.setZooUUID(values[0]);
                zoo.setZoo(values[1]);

                zooMap.put(values[0], zoo);
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
                throw new RuntimeException();
            }
        }
    }

    /**
     * writes the tierMap to the csv-file
     *
     * @param tierMap map with all the animals
     */
    public static void writeTiere(Map<String, Tier> tierMap) {
        Writer writer = null;
        FileOutputStream fileOutputStream = null;

        try {
            String tierPath = Config.getProperty("tierFile");
            fileOutputStream = new FileOutputStream(tierPath);
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "utf-8"));

            for (Map.Entry<String, Tier> tierEntry : tierMap.entrySet()) {
                Tier tier = tierEntry.getValue();
                String contents = String.join("; ",
                        tier.getTierUUID(),
                        tier.getArt(),
                        tier.getName(),
                        tier.getGeburtsdatum(),
                        Integer.toString(tier.getAlter()),
                        Integer.toString(tier.getBeine()),
                        Boolean.toString(tier.getFell()),
                        tier.getZoo().getZooUUID()
                );
                writer.write(contents + '\n');
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            throw new RuntimeException();

        } finally {

            try {
                if (writer != null) {
                    writer.close();
                }

                if (fileOutputStream != null) {
                    fileOutputStream.close();

                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Gets the tier
     *
     * @return value of tierMap
     */
    public static Map<String, Tier> getTierMap() {
        if (tierMap.isEmpty()) {
            readTiere();
        }
        return tierMap;
    }

    /**
     * Sets the tierMap
     *
     * @param tierMap the value to set
     */

    public static void setTierMap(Map<String, Tier> tierMap) {
        DataHandler.tierMap = tierMap;
    }

    /**
     * Gets the zooMap
     *
     * @return value of zooMap
     */
  public static Map<String, Zoo> getZooMap() {
        if (zooMap.isEmpty()) {
            readZoos();
        }
        return zooMap;
    }

    /**
     * Sets the zooMap
     *
     * @param zooMap the value to set
     */

    public static void setZooMap(Map<String, Zoo> zooMap) {
        DataHandler.zooMap = zooMap;
    }



    /**
     * write the zooMap to the csv-file
     *
     * @param zooMap map with all the zoos
     */
    public static void writeZoo(Map<String, Zoo> zooMap) {
        Writer writer = null;
        FileOutputStream fileOutputStream = null;

        try {
            String path = Config.getProperty("zooFile");
            fileOutputStream = new FileOutputStream(path);
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream, "utf-8"));

            for (Map.Entry<String, Zoo> zooEntry : zooMap.entrySet()) {
                Zoo zoo = zooEntry.getValue();
                String contents = String.join("; ",
                        zoo.getZooUUID(),
                        zoo.getZoo()
                );
                writer.write(contents + '\n');
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            throw new RuntimeException();

        } finally {

            try {
                if (writer != null) {
                    writer.close();
                }

                if (fileOutputStream != null) {
                    fileOutputStream.close();

                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * reads all data from the csv-file into the userMap
     *
     * @param passwort
     * @param username
     */
    public static User readUser(String username, String passwort) {

        BufferedReader bufferedReader;
        FileReader fileReader;
        User user = null;

        try {
            String userPath = Config.getProperty("userFile");
            fileReader = new FileReader(userPath);
            bufferedReader = new BufferedReader(fileReader);

        } catch (FileNotFoundException fileEx) {
            fileEx.printStackTrace();
            throw new RuntimeException();
        }

        try {
            String line;
            user = new User();
            while ((line = bufferedReader.readLine()) != null &&
                    user.getUserRole().equals("guest")) {

                String[] values = line.split("; ");

                if (username.equals(values[0]) && passwort.equals(values[2])) {
                    user.setUsername(values[0]);
                    user.setUserRole(values[1]);
                }
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
                throw new RuntimeException();
            }
        }
        return user;
    }
}
