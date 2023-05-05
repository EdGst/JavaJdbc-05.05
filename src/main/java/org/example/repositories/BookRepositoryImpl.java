package org.example.repositories;

import org.example.exception.EntityException;
import org.example.exception.EntityNotFoundException;
import org.example.models.entities.Author;
import org.example.models.entities.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRepositoryImpl extends BaseRepositoryImpl<Book> implements BookRepository {


    public BookRepositoryImpl() {
        super("BOOK", "BOOK_ID");
    }

    @Override
    protected Book buildEntity(ResultSet rs){

        try {
            return Book.builder()
                    .id(rs.getInt("BOOK_ID"))
                    .title(rs.getString("TITLE"))
                    .description(rs.getString("DESCRIPTION"))
                    .authorId(rs.getInt("AUTHOR_ID"))
                    .author(Author.builder()
                            .id(rs.getInt("AUTHOR_ID"))
                            .firstName(rs.getString("FIRSTNAME"))
                            .lastName(rs.getString("LASTNAME"))
                            .pseudo(rs.getString("PSEUDO"))
                            .build()
                    )
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book add(Book book,Connection conn){

        try{
            PreparedStatement psmt = conn.prepareStatement("INSERT INTO BOOK (TITLE,DESCRIPTION,AUTHOR_ID) VALUES (?,?,?) RETURNING *");
            psmt.setString(1, book.getTitle());
            psmt.setString(2, book.getDescription());
            psmt.setInt(3,book.getAuthorId());
            ResultSet rs = psmt.executeQuery();
            if(!rs.next())
                throw new EntityException("Failed");

            return buildEntity(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Book add(Book book) {

        return add(book,openConnection());
    }

    @Override
    public boolean update(Integer id, Book book) {

        try {
            Connection conn = openConnection();
            PreparedStatement psmt = conn.prepareStatement("UPDATE BOOK SET TITLE = ?, DESCRIPTION = ?, AUTHOR_ID = ? WHERE BOOK_ID = ?");
            psmt.setString(1,book.getTitle());
            psmt.setString(2, book.getDescription());
            psmt.setInt(3,book.getAuthorId());
            psmt.setInt(4,id);

            int nbRows = psmt.executeUpdate();

            conn.close();

            return nbRows == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Book getAllInfoByID(Integer id) {

        try {
            Connection conn = openConnection();

            PreparedStatement psmt = conn.prepareStatement("select BOOK_ID, title, description, a.author_id, firstname, lastname, pseudo from book\n" +
                    "join author a on a.author_id = book.author_id WHERE BOOK_ID = ?");

            psmt.setInt(1,id);

            ResultSet rs = psmt.executeQuery();

            if(!rs.next()){
                throw new EntityNotFoundException();
            }
            conn.close();
            return buildEntity(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
