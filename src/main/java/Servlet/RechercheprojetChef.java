package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Models.Projet;
import Models.Service;
import Models.Technologie;
import Service.GestiontacheServiceImpl;

/**
 * Servlet implementation class RechercheprojetChef
 */
public class RechercheprojetChef extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechercheprojetChef() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String destinationPage = "WEB-INF/chefDeprojet/ConsulterProjet.jsp";
        request.getRequestDispatcher(destinationPage).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String projectName = request.getParameter("projectName");
	   

	    // Utilisez votre service pour obtenir le projet correspondant
	    GestiontacheServiceImpl gestiontacheService = new GestiontacheServiceImpl();

	 

	    // Recherche du projet
	    Projet projet = gestiontacheService.searchProjects(projectName);

	    // Vérifie si le projet a été trouvé
	    if (projet == null) {
	        // Si le projet n'est pas trouvé, génère un HTML d'erreur
	    	 String errorHtml = "Projet non trouvé. Veuillez vérifier le nom du projet.";
     	    request.setAttribute("error", errorHtml);

	        
	        // Ajoute le HTML d'erreur à la requête
	        request.setAttribute("errorHtml", errorHtml);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chefDeprojet/ConsulterProjet.jsp");
	    	dispatcher.forward(request, response);
	    } else {
	        // Si le projet est trouvé, continue avec le reste du code
	      
	    	List<Technologie> technologie = gestiontacheService.getTechnologie(projectName);
	        // Ajoute le projet et les services à la requête
	    	// Avant de dispatcher vers la JSP
	    	List<String> listTechnologie = new ArrayList<>(); // Utilisation de ArrayList pour l'implémentation List

	    	for (int i = 0; i < technologie.size(); i++) { // Utilisation de '<' plutôt que '<='
	    	    String technologieName = technologie.get(i).getName();
	    	    listTechnologie.add(technologieName);
	    	}
	    	List<Long> listTechnologieId = new ArrayList<>();

	    	for (int i = 0; i < technologie.size(); i++) {
	    	    Long technologieId = technologie.get(i).getId();
	    	    listTechnologieId.add(technologieId);
	    	}
	    	System.out.println(listTechnologieId);

	    	request.setAttribute("technologie", listTechnologie);
	    	request.setAttribute("projet", projet); 
	    	request.setAttribute("TechnologiId", listTechnologieId); 
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chefDeprojet/UpdateProjet.jsp");
	    	dispatcher.forward(request, response);

	        

	        System.out.println(projet);
	    }

	   
	}

}
 