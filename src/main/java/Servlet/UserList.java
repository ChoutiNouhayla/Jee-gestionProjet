package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import Data.ListUser;
import Models.User;

/**
 * Servlet implementation class UserList
 */
public class UserList extends HttpServlet {
	
       
	private ListUser userDAO;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserList() {
        super();
        this.userDAO = new ListUser();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public List<User> getChefsDeProjet() {
        return userDAO.getUsersByRole("chef de projet");
    }
	

}
