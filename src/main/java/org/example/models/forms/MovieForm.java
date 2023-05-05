package org.example.models.forms;

import lombok.*;
import org.example.models.entities.Director;
import org.example.models.entities.Movie;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieForm {

    private String title;
    private String description;

    private Integer directorId;
    private Director director;

    public MovieForm(Movie movie){

        this.title = movie.getTitle();
        this.description = movie.getDescription();
    }

    public Movie toEntity(){

        return Movie.builder()
                .title(getTitle())
                .description(getDescription())
                .directorId(getDirectorId())
                .director(getDirector())
                .build();
    }
}
