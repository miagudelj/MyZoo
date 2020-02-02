package ch.bzz.bookshelf.model;

import java.math.BigDecimal;

/**
 * a book in the bookshelf
 * <p>
 * Bookshelf
 *
 * @author Marcel Suter (Ghwalin)
 */
public class Book {
    private String bookUUID;
    private String title;
    private String author;
    private Publisher publisher;
    private BigDecimal price;
    private String isbn;

    /**
     * Gets the bookUUID
     *
     * @return value of bookUUID
     */
    public String getBookUUID() {
        return bookUUID;
    }

    /**
     * Sets the bookUUID
     *
     * @param bookUUID the value to set
     */

    public void setBookUUID(String bookUUID) {
        this.bookUUID = bookUUID;
    }

    /**
     * Gets the title
     *
     * @return value of title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title
     *
     * @param title the value to set
     */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the author
     *
     * @return value of author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author
     *
     * @param author the value to set
     */

    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the publisher
     *
     * @return value of publisher
     */
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * Sets the publisher
     *
     * @param publisher the value to set
     */

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets the price
     *
     * @return value of price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the price
     *
     * @param price the value to set
     */

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets the isbn
     *
     * @return value of isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the isbn
     *
     * @param isbn the value to set
     */

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
