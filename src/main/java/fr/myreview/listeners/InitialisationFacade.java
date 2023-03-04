package fr.myreview.listeners;

import fr.myreview.facade.MyReviewFacade;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class InitialisationFacade implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MyReviewFacade facade = new MyReviewFacade();
        sce.getServletContext().setAttribute("facade", facade);

        facade.inscription("alban", "alban");
        facade.inscription("admin", "admin");

        facade.addRestaurant("Le chinois", "Petit restaurant Chinois", "1 rue de la chine");
        facade.addRestaurant("Le kebab", "Petit restaurant Kebab", "1 rue du kebab");
        facade.addRestaurant("Le italien", "Petit restaurant Italien", "1 rue de l'italie");
        facade.addRestaurant("Le japonais", "Petit restaurant Japonais", "1 rue du japon");
        facade.addRestaurant("Le français", "Petit restaurant Français", "1 rue de la france");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
