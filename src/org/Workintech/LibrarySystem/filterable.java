package org.Workintech.LibrarySystem;

import java.util.List;

public interface filterable {
    List<Book> getBooksByName(String name);
    List<Book> getBooksById(int id);
    List<Book> getBooksByCategory(String categoryName);
    List<Book> getBooksByAuthor(String authorName);
}
