package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.User;

public class ListUser {

	public ListUser() {
		// TODO Auto-generated constructor stub
	}
	  // Méthode pour récupérer les utilisateurs par rôle depuis la base de données
		// Méthode pour récupérer les utilisateurs par rôle depuis la base de données
		public List<User> getUsersByRole(String role) {
		    List<User> users = new ArrayList<>();

		    try (
		        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "root", "");
		        PreparedStatement preparedStatement = connection.prepareStatement("SELECT email, role ,id FROM user WHERE role = ?")
		    ) {
		        preparedStatement.setString(1, role);

		        // Exécuter la requête
		        try (ResultSet resultSet = preparedStatement.executeQuery()) {
		            // Parcourir les résultats et créer des objets User
		            while (resultSet.next()) {
		                User user = new User();
		               
		                user.setEmail(resultSet.getString("email"));
		                user.setRole(resultSet.getString("role"));
		                user.setId(resultSet.getLong("id"));
		                users.add(user);
		            }
		        }
		    } catch (SQLException e) {
		        e.printStackTrace(); // Gérer les exceptions de manière appropriée
		    }

		    System.out.println("Number of users retrieved: " + users.size());
		    return users;
		}
		 public int getUserIdByEmail(String email) {
		        int userId = -1; // valeur par défaut si l'utilisateur n'est pas trouvé
		        Connection connection = null;
		        PreparedStatement preparedStatement = null;
		        ResultSet resultSet = null;

		        try {
		            final String URL = "jdbc:mysql://localhost:3306/projet";
		            final String USER = "root";
		            final String PASSWORD = "";
		            String sql = "SELECT id FROM user WHERE email = ?";
		            connection = DriverManager.getConnection(URL, USER, PASSWORD);
		            preparedStatement = connection.prepareStatement(sql);
		            preparedStatement.setString(1, email);

		            resultSet = preparedStatement.executeQuery();

		            if (resultSet.next()) {
		                userId = resultSet.getInt("id");
		            }
		        } catch (SQLException e) {
		            e.printStackTrace(); // Gérer les exceptions de manière appropriée
		        } finally {
		            // Fermer les ressources (ResultSet, PreparedStatement, Connection) dans le bloc finally
		            // ...
		        }

		        return userId;
		    }

}
