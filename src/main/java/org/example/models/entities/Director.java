package org.example.models.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder @EqualsAndHashCode
public class Director {

    private Integer id;

    private String firstName;

    private String lastName;



    public Director(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }

}
