package org.example.repositories;

import org.example.exception.EntityException;
import org.example.models.entities.Director;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DirectorRepositoryImpl extends  BaseRepositoryImpl<Director> implements DirectorRepository {

    public DirectorRepositoryImpl(){
        super("Director", "Director_ID");
    }

    @Override
    protected Director buildEntity(ResultSet rs) {
        try {

            return Director.builder()

                    .id(rs.getInt("Director_ID"))
                    .firstName(rs.getString("Firstname"))
                    .lastName(rs.getString("Lastname"))
                    .build();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Director add(Director director, Connection conn){

        try {

            PreparedStatement psmt = openConnection()
                    .prepareStatement("INSERT INTO DIRECTOR (FIRSTNAME,LASTNAME) VALUES (?,?) RETURNING *");
            psmt.setString(1, director.getFirstName());
            psmt.setString(2, director.getLastName());

            ResultSet rs = psmt.executeQuery();

            if(!rs.next())
                throw new EntityException("Failed");

            return buildEntity(rs);


        } catch (SQLException e){
            throw new RuntimeException(e);
        }


    }

    @Override
    public  Director add(Director director){


        return add(director, openConnection());

    }

    @Override
    public boolean update(Integer id, Director director){
        try {
            Connection conn = openConnection();
            PreparedStatement psmt = conn.prepareStatement
                    ("UPDATE DIRECTOR SET FIRSTNAME = ?, LASTNAME = ? WHERE DIRECTOR_ID = ?");
            psmt.setString(1,director.getFirstName());
            psmt.setString(2, director.getLastName());
            psmt.setInt(3, director.getId());

            int nbRow = psmt.executeUpdate();

            conn.close();

            return nbRow == 1;


        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Director> getByKeyword(String keyword){

        try {
            Connection conn = openConnection();
            PreparedStatement psmt = conn.prepareStatement("SELECT * FROM DIRECTOR" +
                                                                "WHERE FIRSTNAME LIKE '%?%' OR " +
                                                                "LASTNAME LIKE '%?%' OR ");

            psmt.setString(1, keyword);
            psmt.setString(2, keyword);

            ResultSet rs = psmt.executeQuery();

            List<Director> directors = new ArrayList<>();

            while(rs.next()){
                directors.add(buildEntity(rs));
            }

            conn.close();
            return directors;


        } catch (SQLException e){
            throw new RuntimeException(e);
        }

    }



}


/*

try {



        } catch (SQLException e){
        throw new RuntimeException(e);
        }

*/

















