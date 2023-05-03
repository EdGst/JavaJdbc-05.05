package org.example.services;

import org.example.exception.BookNotFoundException;
import org.example.models.entities.Book;
import org.example.models.entities.Users;
import org.example.repositories.UsersRepository;
import org.example.repositories.UsersRepositoryImpl;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class UsersServiceImpl {

    private UsersRepository usersRepository;

    public UsersServiceImpl() {
        this.usersRepository = new UsersRepositoryImpl();
    }

    public Users getOne(Integer id){

        return usersRepository.getOne(id);

    }

    public List<Users> getMany(){

        return  usersRepository.getMany();

    }

    public Users add(Users users){
        return usersRepository.add(users);
    }

    public boolean delete(Integer id){

        if(!usersRepository.delete(id)){
            throw new BookNotFoundException();

        }
        return true;
    }

    public boolean update(Integer id, Users users){

        if(!usersRepository.update(id, users)){
            throw new RuntimeException();
        }
        return true;
    }

    public boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }




}
