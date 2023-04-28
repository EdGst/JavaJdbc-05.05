package org.example;

import org.example.models.entities.Book;
import org.example.models.entities.Users;
import org.example.models.forms.BookForm;
import org.example.services.BookServiceImpl;
import org.example.services.UsersServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        BookServiceImpl bookService = new BookServiceImpl();
        UsersServiceImpl usersService = new UsersServiceImpl();

        List<Users> users = usersService.getMany();
        users.forEach(System.out::println);

//        List<Book> books = bookService.getMany();
//        books.forEach(System.out::println);

//        Book book = bookService.getOne(3);
//
//        System.out.println(book);

//        BookForm bookForm = new BookForm("Toto à la plage", "Il fait beau");
//        Book book = bookService.add(bookForm.toEntity());
//        System.out.println(book);

//        BookForm bookForm = new BookForm("Le Coran", "Merci à Dieu");
//
//        boolean book = bookService.update(3, bookForm.toEntity());  // en paramétre je prend le book du bookform
//
//        System.out.println(book);
//        List<Book> books = bookService.getMany();
//        books.forEach(System.out::println);



    }
}