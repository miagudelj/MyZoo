package ch.bzz.bookshelf.model;

import ch.bzz.bookshelf.data.DataHandler;

import java.util.Map;

/**
 * the bookshelf with a map of books
 * <p>
 * Bookshelf
 *
 * @author Marcel Suter (Ghwalin)
 */
public class Bookshelf {

    private Map<String, Book> bookMap;

    /**
     * constructor: read all the books
     */
    public Bookshelf() {
        bookMap = DataHandler.readBooks();
    }
    /**
     * Gets the bookMap
     *
     * @return value of bookMap
     */
    public Map<String, Book> getBookMap() {
        return bookMap;
    }

    /**
     * Sets the bookMap
     *
     * @param bookMap the value to set
     */

    public void setBookMap(Map<String, Book> bookMap) {
        this.bookMap = bookMap;
    }
}
