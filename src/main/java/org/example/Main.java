package org.example;


import org.example.models.entities.Book;
import org.example.services.BookServiceImpl;
import org.example.services.MovieServiceImpl;
import org.example.services.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        BookServiceImpl bookService = new BookServiceImpl();
        MovieServiceImpl movieService = new MovieServiceImpl();
        UserServiceImpl userService = new UserServiceImpl();

//        List<Book> books = bookService.getMany();
//        books.forEach(System.out::println);

//        Book book = bookService.getOne(6);
//        System.out.println(book);

//        Author author = new Author(null,"toto","la menace","el toto");
//        BookForm bookForm = new BookForm("Toto sur discord","Il fait beau",3,null);
//        Book book = bookService.add(bookForm.toEntity());
//        System.out.println(book);

        Book book = bookService.getAllInfoById(1);
        book.affichage();



//        Director director = new Director(null,"toto","la menace");
//        MovieForm movieForm = new MovieForm("Toto sur discord","Il fait beau",null,director);
//        Movie movie = movieService.add(movieForm.toEntity());
//        System.out.println(movie);

//        BookForm bookForm = new BookForm("Toto au beau vivier","Il fait beau");
//        System.out.println(bookService.update(6,bookForm.toEntity()));

//        List<Movie> movies = movieService.getMany();
//        movies.forEach(System.out::println);

//        Movie movie = movieService.getOne(6);
//        System.out.println(movie);

//        MovieForm movieForm = new MovieForm("Toto à la plage","Il fait beau");
//        Movie movie = movieService.add(movieForm.toEntity());
//        System.out.println(movie);

//        MovieForm movieForm = new MovieForm("Toto au beau vivier","Il fait beau");
//        System.out.println(movieService.update(6,movieForm.toEntity()));

//        System.out.println(movieService.delete(6));

//        UserForm userForm = new UserForm("BeauVivier","prendsTonVerre@soulard.de","hick!");
//        System.out.println(userService.register(userForm.toEntity()));
//        System.out.println(userService.login("prendsTonVerre@soulard.be","hick!"));


    }
}




















