package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Models.Developpeur;
import Models.Projet;
import Models.Service;
import Models.Taches;
import Service.GestiontacheServiceImpl;

/**
 * Servlet implementation class MesTacheServlets
 */
public class MesTacheServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MesTacheServlets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/devellopeur/MesTaches.jsp");
	        dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String projectName = request.getParameter("projectName");
	    long userId = (long) request.getSession().getAttribute("userId");

	    // Utilisez votre service pour obtenir le projet correspondant
	    GestiontacheServiceImpl gestiontacheService = new GestiontacheServiceImpl();

	    long developpeurId = gestiontacheService.getDeveloppeurByUserId(userId);
	    System.out.println(userId);

	    // Recherche du projet
	    Projet projet = gestiontacheService.searchProjects(projectName);

	    // Vérifie si le projet a été trouvé
	    if (projet == null) {
	        // Si le projet n'est pas trouvé, génère un HTML d'erreur
	    	String errorHtml = "<html><head><style>" +
	                   ".error-message {" +
	                   "    color: red;" +
	                   "    font-size: 18px;" +
	                   "    font-weight: bold;" +
	                   "}" +
	                   "</style></head><body>" +
	                   "<div class=\"error-message\">Projet non trouvé. Veuillez vérifier le nom du projet.</div>" +
	                   "</body></html>";

	        
	        // Ajoute le HTML d'erreur à la requête
	        request.setAttribute("errorHtml", errorHtml);
	    } else {
	        // Si le projet est trouvé, continue avec le reste du code
	        long projectId = projet.getId();
	        List<Service> services = gestiontacheService.getServicesForProjetAndDeveloppeur(projectId, developpeurId);
	        
	        // Ajoute le projet et les services à la requête
	        request.setAttribute("projet", projet);
	        request.setAttribute("services", services);

	        System.out.println(services);
	    }

	    // Envoie les résultats à la page JSP
	    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/devellopeur/MesTaches.jsp");
	    dispatcher.forward(request, response);
	}

	}


