package org.example.exception;

public class BookNotFoundException extends BookException{

    public BookNotFoundException(){
        super("Book not found");
    }

    public BookNotFoundException(String message){
        super(message);
    }
}
