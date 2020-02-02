package ch.bzz.bookshelf.data;

import ch.bzz.bookshelf.model.Book;
import ch.bzz.bookshelf.model.Publisher;
import ch.bzz.bookshelf.service.Config;

import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * data handler for reading and writing the csv files
 * <p>
 * M133: Bookshelf
 *
 * @author Marcel Suter
 */

public class DataHandler {
    private static final DataHandler instance = new DataHandler();
    private static final String bookPath = "/home/bzz/data/books.csv";

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
    public static Map<String, Book> readBooks() {
        Map<String, Book> bookMap = new HashMap<>();
        BufferedReader bufferedReader;
        FileReader fileReader;
        try {
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
                Publisher publisher = new Publisher();
                publisher.setPublisher(values[3]);

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
        return bookMap;
    }
}
