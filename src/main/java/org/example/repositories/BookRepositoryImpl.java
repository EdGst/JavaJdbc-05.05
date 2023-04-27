package org.example.repositories;

import org.example.exception.BookException;
import org.example.exception.BookNotFoundException;
import org.example.models.entities.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookRepositoryImpl implements BookRepository {

    private Book buildBook(ResultSet rs){
        try {
            return Book.builder()
                    .id(rs.getInt("book_id"))
                    .title(rs.getString("Title"))
                    .description(rs.getString("Description"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book getOne(Integer id)  {

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DemoJdbc", "postgres", "postgres");
            PreparedStatement psmt = conn.prepareStatement("Select * from book where book_id = ?"); // dire qu'on va envoyer une donnée avec le ?

            psmt.setInt(1,id);


            ResultSet rs = psmt.executeQuery();

            if(!rs.next()){
                throw new BookNotFoundException();
            }
            conn.close();
            return buildBook(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Book> getMany() {


        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DemoJdbc", "postgres", "postgres");

            Statement smt = conn.createStatement(); // permet d'écuter une requête
            ResultSet rs = smt.executeQuery("Select * from Book");  // méthode qui va envoyer un résultat

            List<Book> books = new ArrayList<>();  // on instancie une liste de book

            while (rs.next()) {     // tant qu'il y a un résultat restant/à lire, renvoi un booleen

                books.add(buildBook(rs));

//                Integer id = rs.getInt("Book");
//                String title = rs.getString("Title");
//                String descr = rs.getString("Description");
//
//                Book book = new Book(id, title, description);
//                Book book = new Book(rs.getInt("Book_id"), rs.getString("Title"), rs.getString("Description") );

//                books.add(book);
                // System.out.println(book);
            }

//            PreparedStatement preparedStatement = conn.prepareStatement("");
            conn.close();
            return books;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Book add(Book book) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DemoJdbc", "postgres", "postgres");
            PreparedStatement psmt = conn.prepareStatement("Insert into book (Title, Description) values (?, ?) Returning *");

            psmt.setString(1, book.getTitle());
            psmt.setString(2 , book.getDescription());

            ResultSet rs = psmt.executeQuery();

            if (!rs.next())
                throw new BookException("Failed");

            conn.close();
            return buildBook(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean update(Integer id, Book book) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DemoJdbc", "postgres", "postgres");
            PreparedStatement psmt = conn.prepareStatement("Update Book set title = ? ,description = ? where book_id = ?");

            psmt.setString(1,book.getTitle());
            psmt.setString(2, book.getDescription());
            psmt.setInt(3, id);

            int nbRow = psmt.executeUpdate();
            conn.close();

            return nbRow == 1;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean delete(Integer id) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DemoJdbc", "postgres", "postgres");
            PreparedStatement psmt = conn.prepareStatement("Delete from book where book_id = ?");

            psmt.setInt(1,id);


            int nbRow = psmt.executeUpdate();   // nbRow renvoie si il y a supression de ligne ou non
            conn.close();

            return nbRow == 1;

//            return psmt.executeUpdate() == 1;  //S'il renvoie 1, il renvoie vrai .... s'il renvoie autre chose, il renvoie faux



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
