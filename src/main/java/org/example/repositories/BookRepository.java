package org.example.repositories;

import org.example.models.entities.Book;

public interface BookRepository extends BaseRepository<Book> {

    public abstract Book getAllInfoByID(Integer id);

}
