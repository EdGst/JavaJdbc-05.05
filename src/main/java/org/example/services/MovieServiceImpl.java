package org.example.services;

import org.example.exception.EntityNotFoundException;
import org.example.models.entities.Movie;
import org.example.repositories.DirectorRepository;
import org.example.repositories.DirectorRepositoryImpl;
import org.example.repositories.MovieRepository;
import org.example.repositories.MovieRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MovieServiceImpl {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;

    public MovieServiceImpl(){

        this.movieRepository = new MovieRepositoryImpl();
        this.directorRepository = new DirectorRepositoryImpl();
    }

    public Movie getOne(Integer id){

        return movieRepository.getOne(id);
    }

    public List<Movie> getMany(){

        return movieRepository.getMany();
    }

    public Movie add(Movie movie){

        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/DemoJdbc", "postgres", "postgres");
            conn.setAutoCommit(false);

            if(movie.getDirector() != null) {
                int id = directorRepository.add(movie.getDirector(), conn).getId();
                movie.setDirectorId(id);
            }

            if(movie.getDirectorId() == null)
                throw new EntityNotFoundException();

            Movie resultMovie = movieRepository.add(movie, conn);
            conn.commit();
            return resultMovie;

        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    public boolean delete(Integer id){

        if(!movieRepository.delete(id))
            throw new EntityNotFoundException();

        return true;
    }

    public boolean update(Integer id, Movie movie){

        if(!movieRepository.update(id,movie))
            throw new EntityNotFoundException();

        return true;
    }
}
