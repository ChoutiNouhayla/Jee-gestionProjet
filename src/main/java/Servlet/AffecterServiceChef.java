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
import Service.GestiontacheServiceImpl;

/**
 * Servlet implementation class AffecterServiceChef
 */
public class AffecterServiceChef extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AffecterServiceChef() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/chefDeprojet/AffecterService.jsp");
		    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String projectName = request.getParameter("projectName");


		    // Utilisez votre service pour obtenir le projet correspondant
		    GestiontacheServiceImpl gestiontacheService = new GestiontacheServiceImpl();

		   
		   

		    
		    Projet projet = gestiontacheService.searchProjects(projectName);

		    // Vérifie si le projet a été trouvé
		    if (projet == null) {
		        // Si le projet n'est pas trouvé, génère un HTML d'erreur
		    	  String errorMessage = "Projet non trouvé. Veuillez vérifier le nom du projet. ";
	        	    request.setAttribute("error", errorMessage);

		        
		        // Ajoute le HTML d'erreur à la requête
		        request.setAttribute("errorHtml", errorMessage);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/chefDeprojet/AffecterService.jsp");
			    dispatcher.forward(request, response);
		    } else {
		        // Si le projet est trouvé, continue avec le reste du code
		    	 List<Developpeur> developpeurId = gestiontacheService.getDevelopersByProjectName(projectName);
		        
		     
		        request.setAttribute("developpeurId", developpeurId);

		        System.out.println(developpeurId);
		    }

		    // Envoie les résultats à la page JSP
		    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/chefDeprojet/AffecterService.jsp");
		    dispatcher.forward(request, response);
		}}


