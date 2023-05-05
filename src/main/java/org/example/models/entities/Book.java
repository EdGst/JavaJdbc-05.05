package org.example.models.entities;

import lombok.*;

@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter @EqualsAndHashCode
public class Book {

    private Integer id;
    private String title;
    private String description;
    private Integer authorId;
    private Author author;

    public Book(String title, String description,Integer authorId) {
        this.title = title;
        this.description = description;
        this.authorId = authorId;
    }


    public void affichage() {
        System.out.printf("Livre %s -> %n %s%n Description : %s%n Prenom de l'auteur : %s%n Nom de l'auteur : %s%n Pseudo de l'auteur : %s%n",
                this.getId(),
                this.getTitle(),
                this.getDescription(),
                this.getAuthor().getFirstName(),
                this.getAuthor().getLastName(),
                this.getAuthor().getPseudo());
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", authorId=" + authorId +
                ", author=" +
                '}';
    }


}
