package org.example.models.entities;

import lombok.*;

@Builder @AllArgsConstructor @NoArgsConstructor @ToString @Getter @Setter @EqualsAndHashCode
public class Users {

    private Integer id;
    private String pseudo;
    private String email;
    private String mdp;


    public Users(String pseudo, String email, String mdp) {
        this.pseudo = pseudo;
        this.email = email;
        this.mdp = mdp;
    }
}
