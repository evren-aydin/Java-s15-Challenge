package org.Workintech.LibrarySystem.Users;
import org.Workintech.LibrarySystem.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class User {
  private int id;
  private String name;
  private String email;
  private String password;
  private double balance;
  private List<Book> borrowedBooks;

    public User(int id, String name, String email,String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password=password;
        this.borrowedBooks =new ArrayList<>();
        this.balance=0.0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId() == user.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
    public void addBorrowBook(Book book) {
        borrowedBooks.add(book);
    }
    public void addBalance(double amount) {
        this.balance += amount;
    }

    public void deductBalance(double amount) {
        this.balance -= amount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", borrowedBooks=" + borrowedBooks +
                '}';
    }


}
