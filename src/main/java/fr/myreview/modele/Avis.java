package fr.myreview.modele;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Avis {

    private Compte compte;
    private Restaurant restaurant;
    private String commentaire;
    private int note;

}
