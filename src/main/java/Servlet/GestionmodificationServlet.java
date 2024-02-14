package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Date;

import Data.ListUser;
import Models.ChefDeProjet;
import Models.Directeur;
import Models.Projet;
import Service.GestionProjetsServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class GestionmodificationServlet
 */
public class GestionmodificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionmodificationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  String action = request.getParameter("actionField");
		  if ("Modifier".equals(action)) {
			  try {
			    	String nomProjet = request.getParameter("nomProjet");
			    	String ancienNomProjet = request.getParameter("ancienNomProjet");
			    	long id =Long.parseLong(request.getParameter("id")) ;
		            String descriptionProjet = request.getParameter("descriptionProjet");
		            String clientProjet = request.getParameter("clientProjet");
		            String dateDebutString = request.getParameter("dateDebut");
		            String dateLivraisonString = request.getParameter("dateLivraison");
		            int joursDeveloppement = Integer.parseInt(request.getParameter("joursDeveloppement"));
		            String chefProjetEmail = request.getParameter("chefProjet");
		            
		            System.out.println(request.getParameter("id"));
		            System.out.println(request.getParameter("nomProjet"));
		            System.out.println(request.getParameter("dateLivraison"));
		            

		            // Convertir les dates de string en objet Date
		            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		            Date dateDebut = new Date(dateFormat.parse(dateDebutString).getTime());
		            Date dateLivraison = new Date(dateFormat.parse(dateLivraisonString).getTime());

		            // Appeler le DAO pour obtenir l'ID du chef de projet
		            ListUser userDAO = new ListUser();
		            

		            long chefProjetId = userDAO.getUserIdByEmail(chefProjetEmail);

		            ChefDeProjet c = new ChefDeProjet();
		            c.setId(chefProjetId);
		          

		            // Créer un objet Projet avec toutes les informations
		            Projet projet = new Projet();
		            projet.setId(id);
		            projet.setNomProjet(nomProjet);
		            projet.setDescription(descriptionProjet);
		            projet.setClient(clientProjet);
		            projet.setDateDebut(dateDebut);
		            projet.setDateLivraison(dateLivraison);
		            projet.setJoursDeveloppement(joursDeveloppement);
		            projet.setChefDeProjet(c);
		        

		            // Appeler le service pour ajouter le projet
		            GestionProjetsServiceImpl projectService = new GestionProjetsServiceImpl();

		            if (!projectService.projetExiste(nomProjet) || nomProjet.equals(ancienNomProjet) ) {
		                // Le nom du projet existe déjà, afficher un message d'erreur
		              projectService.modifierProjet(id,nomProjet, descriptionProjet, clientProjet, dateDebut, dateLivraison, joursDeveloppement,chefProjetId);
		                
		                // Utiliser le dispatcher pour rediriger vers la page de confirmation
		                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/directeur/searchProjet.jsp");
		                dispatcher.forward(request, response);
		            } else {
		            	  String errorMessage = "Le nom du projet existe déjà. Veuillez choisir un autre nom.";
			                request.getSession().setAttribute("Erreur lors du modification du projet", errorMessage);

			                // Utiliser le dispatcher pour rediriger vers la page d'ajout avec un message d'erreur
			                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/directeur/modifierProjet.jsp");
			                dispatcher.forward(request, response);
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		            // Rediriger avec un message d'erreur
		            String errorMessage = "Erreur lors du modification du projet";
		            request.getSession().setAttribute("Erreur lors du modification du projet", errorMessage);

		            // Utiliser le dispatcher pour rediriger vers la page d'ajout avec un message d'erreur
		            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/directeur/modifierProjet.jsp");
		            try {
						dispatcher.forward(request, response);
					} catch (ServletException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		            System.out.println("Erreur lors du modification du projet : " + e.getMessage());
		        }
	        } else if ("Supprimer".equals(action)) {
	            supprimerProjet(request, response);
	        } else {
	            // Gérer d'autres actions si nécessaire
	        }
		 
	    }

	  

	    private void supprimerProjet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	        // Implémentez la logique de suppression du projet
	        // Récupérez l'identifiant du projet à supprimer
	    	String nomProjet = request.getParameter("nomProjet");
	      

	        // Utilisez un service pour effectuer la suppression en fonction de vos besoins
	        GestionProjetsServiceImpl projetService = new GestionProjetsServiceImpl();
	        projetService.supprimerProjet(nomProjet);

	        // Redirigez ou affichez un message de confirmation
	        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/directeur/searchProjet.jsp");
            dispatcher.forward(request, response);
	    }
	}


