package ch.bzz.bookshelf.data;

import ch.bzz.bookshelf.model.Book;
import ch.bzz.bookshelf.model.Publisher;
import ch.bzz.bookshelf.service.Config;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * data handler for reading and writing the csv files
 * <p>
 * M133: Bookshelf
 *
 * @author Marcel Suter
 */

public class DataHandler {
    private static final DataHandler instance = new DataHandler();
    private static Map<String, Book> bookMap = new HashMap<>();
    private static Map<String, Publisher> publisherMap = new HashMap<>();

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
     * reads all data from the csv-file into the bookMap
     *
     * @return a map with all books
     */
    public static void readBooks() {

        BufferedReader bufferedReader;
        FileReader fileReader;
        try {
            String bookPath = Config.getProperty("bookFile");
            fileReader = new FileReader(bookPath);
            bufferedReader = new BufferedReader(fileReader);

        } catch (FileNotFoundException fileEx) {
            fileEx.printStackTrace();
            throw new RuntimeException();
        }

        try {
            String line;
            Book book = null;
            while ((line = bufferedReader.readLine()) != null) {
                book = new Book();
                String[] values = line.split(";");
                book.setBookUUID(values[0]);
                book.setTitle(values[1]);
                book.setAuthor(values[2]);
                book.setPrice(new BigDecimal(values[4]));
                book.setIsbn(values[5]);
                Publisher publisher = getPublisherMap().get(values[3]);
                book.setPublisher(publisher);

                bookMap.put(values[0], book);
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
     * reads all data from the csv-file into the publisherMap
     *
     */
    public static void readPublishers() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        try {
            String publisherPath = Config.getProperty("publisherFile");
            fileReader = new FileReader(publisherPath);
            bufferedReader = new BufferedReader(fileReader);

        } catch (FileNotFoundException fileEx) {
            fileEx.printStackTrace();
            throw new RuntimeException();
        }

        try {
            String line;
            Publisher publisher = null;
            while ((line = bufferedReader.readLine()) != null) {
                publisher = new Publisher();
                String[] values = line.split(";");
                publisher.setPublisherUUID(values[0]);
                publisher.setPublisher(values[1]);

                publisherMap.put(values[0], publisher);
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
     * Gets the bookMap
     *
     * @return value of bookMap
     */
    public static Map<String, Book> getBookMap() {
        if (bookMap.isEmpty()) {
            readBooks();
        }
        return bookMap;
    }

    /**
     * Sets the bookMap
     *
     * @param bookMap the value to set
     */

    public static void setBookMap(Map<String, Book> bookMap) {
        DataHandler.bookMap = bookMap;
    }

    /**
     * Gets the publisherMap
     *
     * @return value of publisherMap
     */
    public static Map<String, Publisher> getPublisherMap() {
        if (publisherMap.isEmpty()) {
            readPublishers();
        }
        return publisherMap;
    }

    /**
     * Sets the publisherMap
     *
     * @param publisherMap the value to set
     */

    public static void setPublisherMap(Map<String, Publisher> publisherMap) {
        DataHandler.publisherMap = publisherMap;
    }
}
