package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import Models.User;
import Service.AuthService;
import Service.AuthServiceImpl;


public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AuthService authService;

    public LoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        AuthService authService = new AuthServiceImpl(null);  // Assuming AuthServiceImpl has a parameterless constructor

        User authenticatedUser = authService.authenticate(email, password);

        if (authenticatedUser != null) {
            // Authentication successful
            HttpSession session = request.getSession();
            session.setAttribute("userId", authenticatedUser.getId());
            session.setAttribute("userRole", authenticatedUser.getRole());

            if ("directeur".equals(authenticatedUser.getRole())) {
                // Forward to the directeur.jsp page using RequestDispatcher
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/directeur/directeur.jsp");
                dispatcher.forward(request, response);
            } else if ("developpeur".equals(authenticatedUser.getRole())) {
                // For the "developpeur" role, redirect to the appropriate page
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/devellopeur/devellopeur.jsp");
                dispatcher.forward(request, response);
            } else if ("chef de projet".equals(authenticatedUser.getRole())) {
                // For the "chef de projet" role, redirect to the appropriate page
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/chefDeprojet/chefdeProjet.jsp");
                dispatcher.forward(request, response);
            } else {
                // Unknown role, handle accordingly (you can redirect to a generic page or show an error)
                response.sendRedirect("error.jsp");
            }
        } else {
        	
        	    // Authentication failed
        	    // Redirect to the login page with an error message
        	    String errorMessage = "Invalid email or password. Please try again.";
        	    request.setAttribute("error", errorMessage);

        	    RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
        	    dispatcher.forward(request, response);
        
        }
    }


    private String getRedirectPage(String role) {
        switch (role) {
            case "developpeur":
                return "devellopeur/devellopeur.jsp?role=developpeur";
            case "chef de projet":
                return "chefDeprojet/chefdeProjet.jsp?role=chef_de_projet";
            default:
                return "Login.jsp";
        }
    }
}
