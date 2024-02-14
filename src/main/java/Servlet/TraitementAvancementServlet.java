package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Models.Taches;
import Service.GestiontacheServiceImpl;

/**
 * Servlet implementation class TraitementAvancementServlet
 */
public class TraitementAvancementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TraitementAvancementServlet() {
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
		doGet(request, response);
		GestiontacheServiceImpl gestiontacheService = new GestiontacheServiceImpl();
		long tacheId =Long.parseLong(request.getParameter("tacheId")) ;
			 System.out.println(tacheId);
        int avancement = Integer.parseInt(request.getParameter("avancement"));
        String description =request.getParameter("description");
		 gestiontacheService.addProgressionToTache(tacheId, avancement,description);
		 
		 request.setAttribute("successMessage", "l'avancement a ete ajouter avec success");

		    // Créer une chaîne HTML avec le message de succès
		 String successHtml = "<div style=\"background-color: #4CAF50; color: white; padding: 10px; margin: 10px 0;\">" + 
                 request.getAttribute("successMessage") + "</div>";
		    
		    // Ajouter la chaîne HTML à la requête
		    request.setAttribute("successHtml", successHtml);

	        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/devellopeur/MesTaches.jsp");
	        dispatcher.forward(request, response);
	}

}
