package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Service.GestionProjetsServiceImpl;

/**
 * Servlet implementation class AffecterDevlopeur
 */
public class AffecterDevlopeur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AffecterDevlopeur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String projetId = request.getParameter("projetId");
				

		 String[] selectedDevelopers = request.getParameterValues("selectedDevelopers");
		 GestionProjetsServiceImpl gestionProjetService = new GestionProjetsServiceImpl();
		 gestionProjetService.AffectDevelopeur(projetId, selectedDevelopers);
		  request.setAttribute("successMessage", "Les développeurs ont été affectés avec succès.");
		  RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chefDeprojet/ConsulterProjet.jsp");
	    	dispatcher.forward(request, response);
		 
		 
	}

}
