package fr.myreview.facade;

import fr.myreview.modele.Avis;
import fr.myreview.modele.Compte;
import fr.myreview.modele.Restaurant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class MyReviewFacade implements IFacade{

    private final Collection<Restaurant> restaurants;
    private final Collection<Compte> comptes;
    private final Collection<Avis> avis;

    public MyReviewFacade() {
        this.restaurants = new ArrayList<>();
        this.comptes = new ArrayList<>();
        this.avis = new ArrayList<>();
    }



    @Override
    public Restaurant getRestaurant(int id) {
        return this.restaurants.stream()
                .filter(restaurant -> restaurant.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Restaurant> getRestaurants() {
        return this.restaurants;
    }

    @Override
    public void addRestaurant(String nom, String description, String adresse) {
        Restaurant resto = new Restaurant(this.restaurants.size(), nom, description, adresse);;
        this.restaurants.add(resto);
    }

    @Override
    public Compte connexion(String username, String password) {
        return this.comptes.stream().filter(compte -> compte.getUsername().equals(username) && compte.getPassword()
                .equals(password))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Compte getCompte(String uuid) {
        return this.comptes.stream().filter(compte -> compte.getUuid().toString().equals(uuid))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void addAvis(int idRestaurant, String uuid, String commentaire, int note) {
        Restaurant resto = this.getRestaurant(idRestaurant);
        Compte compte = this.getCompte(uuid);
        Avis avis = new Avis(compte, resto, commentaire, note);
        this.avis.add(avis);
    }





    @Override
    public void inscription(String prenom, String motDePasse) {
        this.comptes.add(new Compte(UUID.randomUUID(), prenom, motDePasse));
    }

    @Override
    public Collection<Avis> getAvis(int idRestaurant) {
       return this.avis.stream().filter(avis -> avis.getRestaurant().getId() == idRestaurant)
               .toList();
    }
}
