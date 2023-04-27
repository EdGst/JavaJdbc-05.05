package org.example.repositories;

import org.example.models.entities.Book;

import java.util.List;

public interface BookRepository {

    Book getOne(Integer id);
    List<Book> getMany();
    Book add(Book book);
    boolean update(Integer id, Book book);
    boolean delete(Integer id);


}
