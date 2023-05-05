package org.example.repositories;

import org.example.exception.EntityException;
import org.example.models.entities.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRepositoryImpl extends BaseRepositoryImpl<Movie> implements MovieRepository{

    public MovieRepositoryImpl() {
        super("MOVIE", "MOVIE_ID");
    }

    @Override
    protected Movie buildEntity(ResultSet rs) {

        try {
            return Movie.builder()
                    .id(rs.getInt("MOVIE_ID"))
                    .title(rs.getString("TITLE"))
                    .description(rs.getString("DESCRIPTION"))
                    .directorId(rs.getInt("DIRECTOR_ID"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Movie add(Movie movie,Connection conn){

        try {
            PreparedStatement psmt = conn.prepareStatement("INSERT INTO MOVIE (TITLE,DESCRIPTION,DIRECTOR_ID) VALUES (?,?,?) RETURNING *");
            psmt.setString(1, movie.getTitle());
            psmt.setString(2, movie.getDescription());
            psmt.setInt(3, movie.getDirectorId());
            ResultSet rs = psmt.executeQuery();
            if(!rs.next())
                throw new EntityException("Failed");

            return buildEntity(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Movie add(Movie movie) {

        return add(movie,openConnection());
    }

    @Override
    public boolean update(Integer id, Movie movie) {

        try {
            Connection conn = openConnection();
            PreparedStatement psmt = conn.prepareStatement("UPDATE MOVIE SET TITLE = ?, DESCRIPTION = ?, DIRECTOR_ID = ? WHERE MOVIE_ID = ?");
            psmt.setString(1,movie.getTitle());
            psmt.setString(2, movie.getDescription());
            psmt.setInt(3, movie.getDirectorId());
            psmt.setInt(4,id);

            int nbRows = psmt.executeUpdate();

            conn.close();

            return nbRows == 1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
