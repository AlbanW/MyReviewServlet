package fr.myreview.facade;

import fr.myreview.modele.Avis;
import fr.myreview.modele.Compte;
import fr.myreview.modele.Restaurant;

import java.util.Collection;

public interface IFacade {

    Restaurant getRestaurant(int id);
    Collection<Restaurant> getRestaurants();
    void addRestaurant(String nom, String description, String adresse);

    Compte connexion(String username, String password);
    Compte getCompte(String uuid);
    void addAvis(int idRestaurant, String uuid, String commentaire, int note);
    void inscription(String prenom, String motDePasse);

    Collection<Avis> getAvis(int idRestaurant);
}
