package org.example.repositories;

import org.example.exception.UsersNotFoundException;
import org.example.models.entities.Users;

import java.sql.*;
import java.util.List;

public class UsersRepositoryImpl implements UsersRepository {


    private Users buildUsers(ResultSet rs) {


        try {
            return Users.builder()
                    .id(rs.getInt("user_id"))
                    .pseudo(rs.getString("pseudo"))
                    .email(rs.getString("email"))
                    .mdp(rs.getString("mdp"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Users getOne(Integer id) {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DemoJdbc", "postgres", "postgres");
            PreparedStatement psmt = conn.prepareStatement("Select * from users where user_id");

            psmt.setInt(1,id);

            ResultSet rs = psmt.executeQuery();

            if(!rs.next()){
                throw new UsersNotFoundException();
            }
            conn.close();
            return buildUsers(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Users> getMany() {
        return null;
    }

    @Override
    public Users add(Users users) {
        return null;
    }

    @Override
    public boolean update(Integer id, Users users) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
