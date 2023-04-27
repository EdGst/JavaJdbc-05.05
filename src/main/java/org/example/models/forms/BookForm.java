package org.example.models.forms;


import lombok.*;
import org.example.models.entities.Book;

@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class BookForm {

    private String title;
    private String description;

    public BookForm(Book book){                         // on veut un book pour pouvoir initialiser
        this.title = book.getTitle();
        this.description = book.getDescription();
    }

    public Book toEntity(){

        return Book.builder()
                .title(getTitle())
                .description(getDescription())
                .build();
    }

}
