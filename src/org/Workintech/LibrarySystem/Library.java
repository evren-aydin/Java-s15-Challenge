package org.Workintech.LibrarySystem;

import org.Workintech.LibrarySystem.Users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library implements Filterable, Giveandtake {
        private String name;
        private Map<Integer,Book> books;
        private Map<Integer,User> users;
        private Map<User,List<Book>> borrowedBooks;
        private final int limit;
    public Library(String name) {
        this.name = name;
        this.books= new HashMap<>();
        this.users=new HashMap<>();
        this.borrowedBooks = new HashMap<>();
        this.limit=5;
    }

    public void addBook(Book book){
        books.put(book.getBook_id(),book);
    }
    public void addUser(User user){
        users.put(user.getId(),user);
    }
    public User getUser(int id){
        return users.get(id);
    }

    public void listBooks(){
        for(Book book:books.values()){
            System.out.println(book+"\n");
        }

    }
    public Book getBookId(int bookId){
        return books.get(bookId);
    }
    @Override
    public void borrowBook(User user,Book book){
        if(books.containsValue(book) && book.isStatus()){
            book.setStatus(false);
            //Kullanıcının ödünç aldığı kitaplar listesini al
            List<Book> borrowedBookList = borrowedBooks.get(user);
            //Eğer liste yoksa, yeni bir liste oluştur.
            if(borrowedBookList==null ){
                borrowedBookList=new ArrayList<>();
                borrowedBooks.put(user,borrowedBookList);
            }
            // liste sayısı 5 ten az ise kitabı listeye ekle
            if(borrowedBookList.size()<limit){
                borrowedBookList.add(book);

                //kullanıcının bakiyesinden kitap fiyatını düş
                user.deductBalance(book.getPrice());
                System.out.println("Fatura : "+ book.getPrice()+ " TL");
            }
            else{
                System.out.println("ödünç alma limiti doldu");
            }
        }else{
            System.out.println("kitap kütüphanede yok !!");
        }
    }
    @Override
    public void returnTheBook(User user,Book book){

        //Kullanıcının ödünç aldığı kitapların listesini al
        List<Book> userBorrowedBooks= borrowedBooks.get(user);
        //kullanıcı kitabı gerçekten ödünç almış mı ?
        if(userBorrowedBooks !=null && book.isStatus()==false&& userBorrowedBooks.contains(book)){
            //Kitabu kullanıcının listesinden çıkar
            userBorrowedBooks.remove(book);

            //kullanıcının başka aldığı kitap yoksa  kullanıcıyı listeden(mapten) çıkar
            if(userBorrowedBooks.isEmpty()){
                borrowedBooks.remove(user);
            }
            // kitabın durumunu güncelle
            book.setStatus(true);

            // kullanıcının bakiyesine kitabın fiyatını ekliyoruz.

            user.addBalance(book.getPrice());
            System.out.println("İade:"+ book.getPrice()+"TL iade edildi");

        }else{
            System.out.println("bu kitap kullanıcı tarafından ödünç alınmamış");
        }
    }
    @Override
    public List<Book> getBooksByAuthor(String authorName){
        List<Book> result = new ArrayList<>();
        for(Book book: books.values()){

            if(book.getAuthor().getName().trim().equalsIgnoreCase(authorName.trim())){
                result.add(book);
            }
        }
        return result;
    }
    @Override
    public List<Book> getBooksByCategory(String categoryName){
        List<Book> result = new ArrayList<>();
        for(Book book: books.values()){
            if(book.getCategory().getName().equalsIgnoreCase(categoryName)){
                result.add(book);
            }
        }
        return result;
    }
    @Override
    public List<Book> getBooksById(int id){
        List<Book> result = new ArrayList<>();
        for(Book book: books.values()){
            if(book.getBook_id()==id){
                result.add(book);
            }
        }
        return result;
    }

    public Book getBook(int id){
        return books.get(id);
    }


    @Override
    public List<Book> getBooksByName(String name){
        List<Book> result = new ArrayList<>();
        for(Book book: books.values()){
            if(book.getTitle().equalsIgnoreCase(name)){//büyük küçük harf duyarlılığını kaldırara karşılaştırma yapar
                result.add(book);
            }
        }
        return result;
    }

    public void getRemove(int id){
            books.remove(id);

    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + books+
                "}";
    }


}
