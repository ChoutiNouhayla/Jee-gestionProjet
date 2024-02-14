package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Models.Projet;

public class ListProjet {
	public boolean projetExiste(String nomProjet) {
        try ( Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "root", "")) {
            String query = "SELECT COUNT(*) FROM projet WHERE nom_projet = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, nomProjet);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0; // Si count > 0, le projet existe
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de la vérification de l'existence du projet", e);
        }
        return false;
    }
	 public List<Projet> getAllProjets() {
	        List<Projet> projets = new ArrayList<>();

	        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "root", "")) {
	            String query = "SELECT * FROM projet";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    while (resultSet.next()) {
	                        Projet projet = new Projet();
	                        projet.setId(resultSet.getLong("id"));
	                        projet.setNomProjet(resultSet.getString("nom_projet"));
	                        projet.setDescription(resultSet.getString("description"));
	                        projet.setClient(resultSet.getString("client"));
	                        projet.setDateDebut(resultSet.getDate("date_debut"));
	                        projet.setDateLivraison(resultSet.getDate("date_livraison"));
	                        projet.setJoursDeveloppement(resultSet.getInt("jours_developpement"));

	                        // Ajouter le projet à la liste
	                        projets.add(projet);
	                        System.out.println(projet.getNomProjet());
	                    }
	                    
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RuntimeException("Erreur lors de la récupération de la liste des projets", e);
	        }

	        return projets;
	    }
	}


