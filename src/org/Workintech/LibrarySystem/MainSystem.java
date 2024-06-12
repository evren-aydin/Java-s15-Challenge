package org.Workintech.LibrarySystem;

import org.Workintech.LibrarySystem.Users.Admin;
import org.Workintech.LibrarySystem.Users.NormalUser;
import org.Workintech.LibrarySystem.Users.User;

import java.util.List;
import java.util.Scanner;

public class MainSystem {
    private static Scanner scanner= new Scanner(System.in);
    private static Library library= new Library("Workintech Library");
    private static User currentUser= null;
    private static void login() {
        System.out.println("Kullanıcı ID'si giriniz:");
        int userId = scanner.nextInt();
        System.out.println("Kullanıcı Şifre:");
        String password = scanner.next();


        User user = library.getUser(userId);

        if(user !=null && user.getPassword().equals(password)){
            currentUser=user;
            System.out.println("Giriş başarılı. "+"Hoşgeldin "+ user.getName());
        }else{
            System.out.println("Geçersiz kullanıcı adı ve şifre");
        }

    }
    private static void newUser() {
        System.out.println("Yeni Kullanıcı ID'si giriniz:");
        int userId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Kullanıcı adı giriniz:");
        String name = scanner.nextLine();
        System.out.println("Şifre giriniz :");
        String password = scanner.next();
        System.out.println("Email adresi giriniz :");
        String email = scanner.next();

        scanner.nextLine();
        System.out.println("Kullanıcı türünü seçiniz:\n"+"1. normal\n"+"2. admin");

        int userType= scanner.nextInt();
        scanner.nextLine();

        User user;
        if(userType==1){
            user = new NormalUser(userId,name,email,password);
        }else{
            user= new Admin(userId,name,email,password);
        }
        library.addUser(user);
        System.out.println("Yeni kullanıcı oluşturuldu");
    }

    //ADMIN METHODS
    private static void addBook() {
        System.out.println("Kitap ID'sini giriniz:");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Kitap başlığını giriniz:");
        String bookTitle = scanner.nextLine();
        System.out.println("Kitap fiyatını giriniz:");
        double bookPrice = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Kitap yazarını giriniz:");
        String authorName = scanner.nextLine();
        System.out.println("Kitap kategorisini giriniz:");
        String categoryName = scanner.nextLine();
        Book book = new Book(bookId, new Author(authorName), bookTitle, bookPrice, true, new Category(categoryName));
        library.addBook(book);
        System.out.println("Kitap eklendi.");

    }


    private static void removeBookById() {

        System.out.println("Lütfen silmek istediğiniz kitabın Id'sini giriniz : ");
        int bookId= scanner.nextInt();

        library.getRemove(bookId);
        System.out.println("Kitap silinmiştir. Güncel liste aşağıda:\n");
        library.listBooks();
    }


    private static void listBooksByCategory() {
        System.out.println("Lütfen listelemek istediğiniz Kitapların Kategori adını giriniz");
        String category = scanner.nextLine();

        List<Book> books = library.getBooksByCategory(category);
        for (Book book : books) {
            System.out.println(book+"\n");
        }


    }

    private static void listBookByAuthor() {
        System.out.println("Lütfen listelemek istediğiniz Kitapların Yazar ismini giriniz");
        String author = scanner.nextLine();

        List<Book> books = library.getBooksByAuthor(author);
        for (Book book : books) {
            System.out.println(book+"\n");
        }
    }
    //normalUser methods
    private static void borrowBook() {
        System.out.println("Kitap ID'sini giriniz:");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        Book book = library.getBook(bookId);
        if (book == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }
        library.borrowBook(currentUser, book);
    }


    private static void returnBook() {
        System.out.println("Kitap ID'sini giriniz:");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        Book book = library.getBook(bookId);
        if (book == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }
        library.returnTheBook(currentUser, book);

    }

    private static void showLoginMenu() {
        System.out.println("1. Giriş Yap");
        System.out.println("2. Yeni Kullanıcı Oluştur");
        System.out.println("3. Çıkış");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                newUser();
                break;
            case 3:
                System.out.println("Çıkış yapıldı");
                System.exit(0);
            default:
                System.out.println("Geçersiz seçim.");
        }
    }

    private static void showAdminMenu() {
        System.out.println("1. Kitap Ekle");
        System.out.println("2. Kitap Sil");
        System.out.println("3. Kategorilere göre kitapları listele");
        System.out.println("4. Yazara göre kitapları listele");
        System.out.println("5. Çıkış yap");


        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addBook();
                break;
            case 2:
                removeBookById();
                break;
            case 3:
                listBooksByCategory();
                break;
            case 4:
                listBookByAuthor();
                break;
            case 5:
                currentUser = null;
                System.out.println("Çıkış yapıldı.");
                System.exit(0);
            default:
                System.out.println("Geçersiz seçim.");
        }
    }
    private static void showUserMenu() {
        System.out.println("1. Kitap ödünç al");
        System.out.println("2. Kitap iade et");
        System.out.println("3. Çıkış yap");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                borrowBook();
                break;
            case 2:
                returnBook();
                break;
            case 3:
                currentUser = null;
                System.out.println("Çıkış yapıldı.");
                System.exit(0);
            default:
                System.out.println("Geçersiz seçim.");
        }
    }


    // Başlangıç Değerleri - kütüphanedeki kullanıcı ve kitaplar
    private static void initialInstance(){

        library.addBook(new Book(1,new Author("Pierre Franckh"),"Rezonans Kanunu",95.05,true,new Category("Kişisel Gelişim")));
        library.addBook(new Book(2,new Author("J.K. Rowling"), "Harry Potter", 30.0, true, new Category("Fantasy")));
        library.addBook(new Book(3,new Author("J.R.R. Tolkien"), "The Lord of the Rings", 45.0, true, new Category("Fantasy")));
        library.addBook(new Book(4,new Author("Mustafa Kemal Atatürk"),"Çocuklar için Nutuk",37.70,true,new Category("Tarih")));
        library.addBook(new Book(5,new Author("Matt Haig"),"Gece Yarısı Kütüphanesi",140.65,true,new Category("Edebiyat")));
        library.addBook(new Book(6,new Author("İlber Ortaylı"),"Gel Dünyayı Keşfedelim",86.25,true,new Category("Tarih")));
        library.addBook(new Book(7,new Author("Jules Payot"),"İrade Terbiyesi",39.10,true,new Category("Felsefe")));
        library.addBook(new Book(8,new Author("Jeffrey E. Young"),"Hayatı Yeniden Keşfedin",191.50,true,new Category("Kişisel Gelişim")));
        library.addBook(new Book(9,new Author("Antoine de Saint"),"Küçük Prens",44.15,true,new Category("Çocuk")));
        library.addBook(new Book(10,new Author("Victor Hugo"),"Bir İdam Mahkumunun Son Günü",22.10,true,new Category("Edebiyat")));

        library.addUser(new Admin(1,"Evren","evren@email.com", "evren123"));
        library.addUser(new NormalUser(2,"Selim","selim@email.com", "selim123"));
        library.addUser(new NormalUser(3,"Mehmet","mehmet@email.com", "mehmet123"));

    }
    public static void main(String[] args) {

        initialInstance();

        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                if (currentUser instanceof Admin) {
                    showAdminMenu();
                } else {
                    showUserMenu();
                }
            }
        }
    }


}
