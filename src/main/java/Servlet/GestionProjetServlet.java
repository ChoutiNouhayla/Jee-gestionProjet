package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import Data.ListUser;
import Models.ChefDeProjet;
import Models.Directeur;
import Models.Projet;
import Service.GestionProjetsServiceImpl;

/**
 * Servlet implementation class GestionProjetServlet
 */

public class GestionProjetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionProjetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String destinationPage = "WEB-INF/directeur/ajouterProjet.jsp";
        request.getRequestDispatcher(destinationPage).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Récupérer les paramètres du formulaire
            String nomProjet = request.getParameter("nomProjet");
            
            String descriptionProjet = request.getParameter("descriptionProjet");
            String clientProjet = request.getParameter("clientProjet");
            String dateDebutString = request.getParameter("dateDebut");
            String dateLivraisonString = request.getParameter("dateLivraison");
            int joursDeveloppement = Integer.parseInt(request.getParameter("joursDeveloppement"));
            String chefProjetEmail = request.getParameter("chefProjet");

            // Convertir les dates de string en objet Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateDebut = new Date(dateFormat.parse(dateDebutString).getTime());
            Date dateLivraison = new Date(dateFormat.parse(dateLivraisonString).getTime());

            // Appeler le DAO pour obtenir l'ID du chef de projet
            ListUser userDAO = new ListUser();
            HttpSession session = request.getSession();

            long idDirect = (long) session.getAttribute("userId");

            long chefProjetId = userDAO.getUserIdByEmail(chefProjetEmail);

            ChefDeProjet c = new ChefDeProjet();
            c.setId(chefProjetId);
            Directeur d = new Directeur();
            d.setId(idDirect);

            // Créer un objet Projet avec toutes les informations
            Projet projet = new Projet();
            projet.setNomProjet(nomProjet);
            projet.setDescription(descriptionProjet);
            projet.setClient(clientProjet);
            projet.setDateDebut(dateDebut);
            projet.setDateLivraison(dateLivraison);
            projet.setJoursDeveloppement(joursDeveloppement);
            projet.setChefDeProjet(c);
            projet.setDirecteur(d);

            // Appeler le service pour ajouter le projet
            GestionProjetsServiceImpl projectService = new GestionProjetsServiceImpl();

            if (projectService.projetExiste(nomProjet)) {
                // Le nom du projet existe déjà, afficher un message d'erreur
                String errorMessage = "Le nom du projet existe déjà. Veuillez choisir un autre nom.";
                request.getSession().setAttribute("erreurAjoutProjet", errorMessage);

                // Utiliser le dispatcher pour rediriger vers la page d'ajout avec un message d'erreur
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/directeur/ajouterProjet.jsp");
                dispatcher.forward(request, response);
            } else {
                projectService.ajouterProjet(projet);
                request.setAttribute("successMessage", "le projet a ete ajouter avec success");
                String successHtml = "<div style=\"background-color: #4CAF50; color: white; padding: 10px; margin: 10px 0;\">" + 
                        request.getAttribute("successMessage") + "</div>";
       		    
       		    // Ajouter la chaîne HTML à la requête
       		    request.setAttribute("successHtml", successHtml);
                
                // Utiliser le dispatcher pour rediriger vers la page de confirmation
       		 RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/directeur/ajouterProjet.jsp");
             dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Rediriger avec un message d'erreur
          
            // Utiliser le dispatcher pour rediriger vers la page d'ajout avec un message d'erreur
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/directeur/ajouterProjet.jsp");
            dispatcher.forward(request, response);
            System.out.println("Erreur lors de l'ajout du projet : " + e.getMessage());
        }
    }



}
