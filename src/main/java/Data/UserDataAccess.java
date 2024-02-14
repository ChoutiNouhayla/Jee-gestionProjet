package Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.User;

public class UserDataAccess {

    // Méthode pour récupérer les informations de l'utilisateur par e-mail
    public static User getUserByEmail(String email) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Obtenez une connexion à la base de données (vous devez implémenter votre propre logique de connexion)
            connection = DatabaseConnectionManager.getConnection();

            // Écrivez la requête SQL pour sélectionner l'utilisateur par e-mail
            String sql = "SELECT id, email, password, role FROM user WHERE email = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            // Exécutez la requête
            resultSet = preparedStatement.executeQuery();

            // Si un utilisateur est trouvé, créez un objet User et retournez-le
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("role"));
               
                return user;
            } else {
                // Aucun utilisateur trouvé avec cet e-mail
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérez les exceptions SQL selon vos besoins (journalisation, remontée, etc.)
            return null;
        } finally {
            // Fermez les ressources de base de données
            closeResources(connection, preparedStatement, resultSet);
        }
    }

    // Méthode utilitaire pour fermer les ressources de base de données
    private static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérez les exceptions SQL selon vos besoins (journalisation, remontée, etc.)
        }
    }
}

