package Servlet;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import Data.ListProjet;
import Models.Projet;
import Service.GestionProjetsServiceImpl;

/**
 * Servlet implementation class ListeProjetsServlet
 */
public class ListeProjetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListeProjetsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String searchQuery = request.getParameter("searchQuery");

		 GestionProjetsServiceImpl projectService = new GestionProjetsServiceImpl();
	        List<Projet> searchResults = projectService.searchProjects(searchQuery);

	        if (searchResults.isEmpty()) {
	            // Aucun projet trouvé, définir un message d'erreur
	            request.setAttribute("errorMessage", "Aucun projet trouvé pour la recherche : " + searchQuery);
	        } else {
	            // Projets trouvés, les mettre dans la portée de la requête
	            request.setAttribute("searchResults", searchResults);
	        }

	        // Rediriger vers la page de résultats de recherche
	        request.getRequestDispatcher("WEB-INF/directeur/searchProjet.jsp").forward(request, response);
	    }
		 
	    
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
