package com.abhi.bookshackerearth.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "book")
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "bookID")
    private int bookID;

    @Column(name = "title")
    private String title;

    @Column(name = "authors")
    private String authors;

    @Column(name = "average_rating")
    private String average_rating;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "language_code")
    private String language_code;

    @Column(name = "ratings_count")
    private String ratings_count;

    @Column(name = "price")
    private String price;

    public Book() {
    }

    public Book(int bookID, String title, String authors, String average_rating, String isbn, String language_code, String ratings_count, String price) {
        this.bookID = bookID;
        this.title = title;
        this.authors = authors;
        this.average_rating = average_rating;
        this.isbn = isbn;
        this.language_code = language_code;
        this.ratings_count = ratings_count;
        this.price = price;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getAverage_rating() {
        return average_rating;
    }

    public void setAverage_rating(String average_rating) {
        this.average_rating = average_rating;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getLanguage_code() {
        return language_code;
    }

    public void setLanguage_code(String language_code) {
        this.language_code = language_code;
    }

    public String getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(String ratings_count) {
        this.ratings_count = ratings_count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", average_rating='" + average_rating + '\'' +
                ", isbn='" + isbn + '\'' +
                ", language_code='" + language_code + '\'' +
                ", ratings_count='" + ratings_count + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
