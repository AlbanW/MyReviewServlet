package fr.myreview.modele;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Restaurant {

    private int id;
    private String nom;
    private String adresse;
    private String description;

    public Restaurant(int id, String nom, String adresse, String description) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.description = description;
    }
}
