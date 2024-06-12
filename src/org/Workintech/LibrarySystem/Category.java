package org.Workintech.LibrarySystem;

import java.util.HashSet;
import java.util.Set;

public class Category {

    private String name;
    private Set<Book> books;

    public Category(String name) {
        this.name = name;
        this.books= new HashSet<>();
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

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
