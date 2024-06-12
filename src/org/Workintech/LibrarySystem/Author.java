package org.Workintech.LibrarySystem;

import java.util.HashSet;
import java.util.Set;

public class Author {

    private String name;
    private Set<Book> books;


    public Author(String name) {
        this.name = name;
        this.books = new HashSet<>();
    }

    public void addBook(Book book){
        books.add(book);
    }

    public String getName() {
        return name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
