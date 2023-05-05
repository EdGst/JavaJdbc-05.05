package org.example.repositories;

import org.example.models.entities.Users;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
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


        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DemoJdbc", "postgres", "postgres");
            PreparedStatement psmt = conn.prepareStatement("Select * from users where user_id = ?");

            psmt.setInt(1, id);

            ResultSet rs = psmt.executeQuery();

            if (!rs.next()) {
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


        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DemoJdbc", "postgres", "postgres");

            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery("Select * from users");

            List<Users> users = new ArrayList<>();

            while (rs.next()) {
                users.add(buildUsers(rs));
            }

            conn.close();
            return users;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Users add(Users users) {


        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DemoJdbc", "postgres", "postgres");
            PreparedStatement psmt = conn.prepareStatement("Insert into users (pseudo, email, mdp) values (?, ?, ?) Returning *");

            psmt.setString(1, "pseudo");
            psmt.setString(2, "email");
            String hashedPassword = BCrypt.hashpw(users.getMdp(), BCrypt.gensalt());
            psmt.setString(3, hashedPassword);

            ResultSet rs = psmt.executeQuery();

            if(!rs.next())
                throw new UsersException("Failed");

            conn.close();
            return buildUsers(rs);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    public boolean update(Integer id, Users users) {

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DemoJdbc", "postgres", "postgres");
            PreparedStatement psmt = conn.prepareStatement("Update users set pseudo = ? , email = ?, mdp = ? where user_id = ?");

            psmt.setString(1, "pseudo");
            psmt.setString(2, "email");
            psmt.setString(3, "mdp");

            int nbRow = psmt.executeUpdate();
            conn.close();

            return nbRow ==1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public boolean delete(Integer id) {


        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DemoJdbc", "postgres", "postgres");
            PreparedStatement psmt = conn.prepareStatement("Delete from Users where user_id = ");

            psmt.setInt(1, id);

            int nbRow = psmt.executeUpdate();
            conn.close();

            return nbRow == 1;


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
