package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestionCompetenceData {

    private Connection getConnection() throws SQLException {
        // À adapter selon votre configuration de base de données
        String URL = "jdbc:mysql://localhost:3306/projet";
        String USER = "root";
        String PASSWORD = "";
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void ajouterCompetence(long userId, String[] competences) {
        try (Connection connection = getConnection()) {
            long developpeurId = getDeveloppeurIdByUserId(userId, connection);
            List<Long> technologieIds = getTechnologieIdsByNames(competences, connection);

            String sqlInsertCompetence = "INSERT INTO competences (developpeur_id, technologie_id) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertCompetence)) {
                for (Long technologieId : technologieIds) {
                    preparedStatement.setLong(1, developpeurId);
                    preparedStatement.setLong(2, technologieId);
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérez les erreurs de la base de données
        }
    }

    private long getDeveloppeurIdByUserId(long userId, Connection connection) throws SQLException {
        String sql = "SELECT id FROM developpeur WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getLong("id");
                }
            }
        }
        // Gérer le cas où le développeur n'est pas trouvé
        throw new SQLException("Le développeur avec le user_id " + userId + " n'a pas été trouvé.");
    }

    private List<Long> getTechnologieIdsByNames(String[] competences, Connection connection) throws SQLException {
        List<Long> technologieIds = new ArrayList<>();
        String sql = "SELECT id FROM technologie WHERE name = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (String competence : competences) {
                preparedStatement.setString(1, competence);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        technologieIds.add(resultSet.getLong("id"));
                    } else {
                        // Gérer le cas où la technologie n'est pas trouvée
                        throw new SQLException("La technologie avec le nom " + competence + " n'a pas été trouvée.");
                    }
                }
            }
        }

        return technologieIds;
    }
}
