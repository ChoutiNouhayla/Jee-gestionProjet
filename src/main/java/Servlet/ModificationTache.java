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
 * Servlet implementation class ModificationTache
 */
public class ModificationTache extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificationTache() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		GestiontacheServiceImpl gestiontacheService = new GestiontacheServiceImpl();
		long serviceId =Long.parseLong(request.getParameter("serviceId")) ;
			 System.out.println(serviceId);

		 List<Taches> taches=gestiontacheService.getTacheForService(serviceId);
		 request.setAttribute("taches", taches);
		 
	        
	        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/devellopeur/MesTaches.jsp");
	        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	   
		 
	}

}
