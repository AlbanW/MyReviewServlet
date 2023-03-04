package fr.myreview.controleur;

import fr.myreview.facade.MyReviewFacade;
import fr.myreview.modele.Avis;
import fr.myreview.modele.Compte;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet(name = "BaseControleur", urlPatterns = "/review/*")
public class BaseControleur extends HttpServlet {

    private static final String LOGIN = "login";
    private static final String HOME = "home";
    private static final String RESTAURANT = "restaurant";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] params = req.getRequestURI().split("/");
        String action = params[params.length - 1];
        String destination = LOGIN;
        MyReviewFacade facade = (MyReviewFacade) req.getServletContext().getAttribute("facade");
        if(RESTAURANT.equalsIgnoreCase(action)) {
            req.setAttribute("restaurant", facade.getRestaurant(Integer.parseInt(req.getParameter("id"))));
            Collection<Avis> avis = facade.getAvis(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("avis", avis);
            int mean = 0;
            for(Avis a : avis) {
                mean += a.getNote();
            }
            if(mean > 0) mean /= avis.size();
            req.setAttribute("avisMean", mean);
            destination = RESTAURANT;
        }
        if(LOGIN.equalsIgnoreCase(action)) {
            destination = LOGIN;
        }
        if(HOME.equalsIgnoreCase(action)) {
            req.setAttribute("restaurant", facade.getRestaurants());
            destination = HOME;
        }

        if(req.getSession().getAttribute("user") == null & !destination.equalsIgnoreCase(LOGIN)) destination = LOGIN;
        if(req.getSession().getAttribute("user") != null & destination.equalsIgnoreCase(LOGIN)) {
            req.setAttribute("restaurant", facade.getRestaurants());
            destination = HOME;
        }

        String destinationUrl = "/WEB-INF/pages/" + destination + ".jsp";
        this.getServletContext().getRequestDispatcher(destinationUrl).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] params = req.getRequestURI().split("/");
        String action = params[params.length - 1];
        String destination = "login";
        MyReviewFacade facade = (MyReviewFacade) req.getServletContext().getAttribute("facade");

        if(RESTAURANT.equalsIgnoreCase(action)) {
            req.setAttribute("avis", facade.getAvis(Integer.parseInt(req.getParameter("id"))));
            String commentaire = req.getParameter("commentaire");
            String note = req.getParameter("note");
            if(commentaire != null && note != null) {
                facade.addAvis(Integer.parseInt(req.getParameter("id")), ((Compte) req.getSession().getAttribute("user")).getUuid().toString(), commentaire, Integer.parseInt(note));
            }
            req.setAttribute("restaurant", facade.getRestaurants());
            req.setAttribute("success", "Votre avis a bien été pris en compte");
            destination = HOME;
        }
        if(LOGIN.equalsIgnoreCase(action)) {
            String username = req.getParameter("pseudo");
            String password = req.getParameter("password");
            destination = LOGIN;
            if(username != null && password != null) {
                Compte login = facade.connexion(username, password);
                if(login != null) {
                    req.getSession().setAttribute("user", login);
                    req.setAttribute("restaurant", facade.getRestaurants());
                    destination = HOME;
                } else {
                    req.setAttribute("error", "Le mot de passe ou le nom d'utilisateur est incorrect");
                }
            }
        }

        if(req.getSession().getAttribute("user") == null & !destination.equalsIgnoreCase(LOGIN)) destination = LOGIN;
        if(req.getSession().getAttribute("user") != null & destination.equalsIgnoreCase(LOGIN)) {
            req.setAttribute("restaurants", facade.getRestaurants());
            destination = HOME;
        }
        String destinationUrl = "/WEB-INF/pages/" + destination + ".jsp";
        this.getServletContext().getRequestDispatcher(destinationUrl).forward(req, resp);
    }
}
