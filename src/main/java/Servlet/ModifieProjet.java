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
import Service.GestionProjetsServiceImpl;

/**
 * Servlet implementation class ModifieProjet
 */
public class ModifieProjet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifieProjet() {
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
		 String projetId = request.getParameter("projetId");
		

	        String[] technologies = request.getParameterValues("technologie");
	        String methodologie = request.getParameter("methodologie");
	        String dateReunion = request.getParameter("date_reunion");

	        // Appeler le service pour effectuer les opérations sur la base de données
	        GestionProjetsServiceImpl gestionProjetService = new GestionProjetsServiceImpl();
	        gestionProjetService.ajouterCompetence(projetId, technologies, methodologie, dateReunion);
	       List<Developpeur> Devellopeur=gestionProjetService.getDeveloppeursByProjetId(projetId);
	   	request.setAttribute("Devellopeur", Devellopeur);
	   	request.setAttribute("projetId", projetId);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/chefDeprojet/AffecterDeveloppeur.jsp");
	    	dispatcher.forward(request, response);

	        // Rediriger ou faire d'autres opérations en fonction de vos besoins
	      
	}

}
