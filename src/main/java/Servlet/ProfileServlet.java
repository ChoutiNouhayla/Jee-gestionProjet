package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import Models.User;
import Service.UserProfileServiceImpl;

/**
 * Servlet implementation class ProfileServlet
 */
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 
        HttpSession session = request.getSession();
        long userId = (long) session.getAttribute("userId"); // Assuming you have stored the user ID in the session

        // Fetch user data from the service
        UserProfileServiceImpl userProfileService = new UserProfileServiceImpl();
        Map<String, Object> userProfile = userProfileService.getUserProfile(userId);

        // Fetch user competencies
        List<String> competencies = userProfileService.getUserCompetencies(userId);

        // Set attributes in the request scope
        request.setAttribute("userProfile", userProfile);
        request.setAttribute("competencies", competencies);

        // Forward the request to the JSP page
        request.getRequestDispatcher("WEB-INF/devellopeur/Profile.jsp").forward(request, response);
   
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
