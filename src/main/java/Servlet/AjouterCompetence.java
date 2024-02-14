package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Service.GestionCompetencesServiceImp;

/**
 * Servlet implementation class AjouterCompetence
 */
public class AjouterCompetence extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterCompetence() {
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
	    long userId = (long) request.getSession().getAttribute("userId");

	    // Obtenez les compétences cochées depuis le formulaire
	    String[] competences = request.getParameterValues("competence");

	    // Appelez le service pour ajouter les compétences
	    GestionCompetencesServiceImp gestionCompetencesService = new GestionCompetencesServiceImp();
	    gestionCompetencesService.ajouterCompetences(userId, competences);

	    // Stockez le message de succès dans la session
	    request.getSession().setAttribute("successMessage", "Compétences ajoutées avec succès!");

	    // Utilisez l'objet RequestDispatcher pour dispatcher la requête vers la page de profil
	    RequestDispatcher dispatcher = request.getRequestDispatcher("ProfileServlet");

	    // Avant de faire la redirection, ajoutez un attribut pour indiquer le succès
	    request.setAttribute("success", true);

	    // Dispatcher la requête
	    dispatcher.forward(request, response);
	}
}
	


