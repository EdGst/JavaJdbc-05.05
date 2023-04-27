package org.example.exception;

public class UsersNotFoundException extends UsersException{

    public UsersNotFoundException(){
        super("Users not found");
    }
    public UsersNotFoundException(String message) {
        super(message);
    }
}
