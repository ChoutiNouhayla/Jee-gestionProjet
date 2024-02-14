package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserData {
	  private static final String URL = "jdbc:mysql://localhost:3306/projet";
	    private static final String USER = "root";
	    private static final String PASSWORD = "";
    public Map<String, Object> getUserProfile(long userId) {
        Map<String, Object> userProfile = new HashMap<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT u.id, u.email, u.role, d.nom, d.prenom, d.salaire "
                    + "FROM user u "
                    + "JOIN developpeur d ON u.id = d.id "
                    + "WHERE u.id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        userProfile.put("id", resultSet.getInt("id"));
                        userProfile.put("email", resultSet.getString("email"));
                        userProfile.put("role", resultSet.getString("role"));
                        userProfile.put("nom", resultSet.getString("nom"));
                        userProfile.put("prenom", resultSet.getString("prenom"));
                        userProfile.put("salaire", resultSet.getDouble("salaire"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userProfile;
    }

    public List<String> getUserCompetencies(long userId) {
        List<String> competencies = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT t.name "
                    + "FROM technologie t "
                    + "JOIN competences c ON t.id = c.technologie_id "
                    + "WHERE c.developpeur_id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, userId);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        competencies.add(resultSet.getString("name"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return competencies;
    }
}

