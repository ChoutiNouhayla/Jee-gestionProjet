package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Models.Service;
import Service.GestionProjetsServiceImpl;

/**
 * Servlet implementation class MesProjetServlets
 */
public class MesProjetServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MesProjetServlets() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 long developpeurId =  (long) request.getSession().getAttribute("userId");

	        // Utiliser le service pour récupérer la liste des projets associés au développeur
	        GestionProjetsServiceImpl projetService = new GestionProjetsServiceImpl();
	        List<Service> projets = projetService.getProjetsByDeveloppeur(developpeurId);

	        // Ajouter les projets à la requête
	        request.setAttribute("projets", projets);

	        // Rediriger vers la JSP
	        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/devellopeur/ConsulterProjet.jsp");
	        dispatcher.forward(request, response);
	    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
