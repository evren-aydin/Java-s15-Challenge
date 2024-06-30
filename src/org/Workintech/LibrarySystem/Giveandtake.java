package org.Workintech.LibrarySystem;

import org.Workintech.LibrarySystem.Users.User;

public interface Giveandtake {

    void returnTheBook(User user, Book book);
    void borrowBook(User user,Book book);
}
