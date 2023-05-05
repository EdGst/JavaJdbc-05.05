package org.example.models.entities;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter @EqualsAndHashCode
public class Movie {

    private Integer id;
    private String title;
    private String description;
    private Integer directorId;
    private Director director;

    public Movie(String title, String description, Integer directorId) {
        this.title = title;
        this.description = description;
        this.directorId = directorId;
    }
}
