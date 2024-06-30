package org.Workintech.LibrarySystem;

import java.util.Objects;

public class Book {

    private int bookId;
    private Author author;
    private String title;
    private double price;
    private boolean status; // durum- ödünç alma durumu- false=ödünç alınan true mevcut
    private Category category;



    public Book(int bookId, String title) {

        this.bookId = bookId;
        this.title = title;

    }

    public Book(int book_id, Author author, String title, double price, boolean status, Category category) {
        this.bookId = book_id;
        this.author = author;
        this.title = title;
        this.price = price;
        this.status = status;
        this.category = category;


    }

    public int getBook_id() {
        return bookId;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public boolean isStatus() {
        return status;
    }

    public Category getCategory() {
        return category;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return getBook_id() == book.getBook_id();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBook_id());
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_id=" + bookId +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", category=" + category +
                '}';
    }
}
