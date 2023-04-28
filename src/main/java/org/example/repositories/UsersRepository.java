package org.example.repositories;

import org.example.models.entities.Users;

import java.util.List;

public interface UsersRepository {

    Users getOne(Integer id);
    List<Users> getMany();
    Users add(Users users);
    boolean update(Integer id, Users users);
    boolean delete(Integer id);
}
