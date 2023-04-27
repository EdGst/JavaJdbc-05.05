package org.example.models.forms;

import lombok.*;
import org.example.models.entities.Book;
import org.example.models.entities.Users;

@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class UsersForm {

    private String pseudo;
    private String email;
    private String mdp;

    public UsersForm(Users users){
        this.pseudo = users.getPseudo();
        this.email = users.getEmail();
        this.mdp = users.getMdp();
    }

    public Users toEntity(){

        return Users.builder()
                .pseudo(getPseudo())
                .email(getEmail())
                .mdp(getMdp())
                .build();

    }

}
