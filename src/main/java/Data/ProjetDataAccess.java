package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Models.ChefDeProjet;
import Models.Developpeur;
import Models.Directeur;
import Models.Projet;
import Models.Service;
import Models.User;

public class ProjetDataAccess {

	// Méthode pour ajouter un projet à la base de données
	public boolean projetExiste(String nomProjet) {
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "root", "")) {
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

	public void ajouterProjet(Projet projet) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DatabaseConnectionManager.getConnection();
			String sql = "INSERT INTO projet (nom_projet, client, date_debut, date_livraison, jours_developpement, description, methodologie, directeur_id, chef_de_projet_id) VALUES (?, ?, ?, ?, ?, ?, NULL, ?, ?)";
			preparedStatement = connection.prepareStatement(sql);

			// Set values for the parameters
			preparedStatement.setString(1, projet.getNomProjet());
			preparedStatement.setString(2, projet.getClient());
			preparedStatement.setDate(3, new java.sql.Date(projet.getDateDebut().getTime()));
			preparedStatement.setDate(4, new java.sql.Date(projet.getDateLivraison().getTime()));
			preparedStatement.setInt(5, projet.getJoursDeveloppement());
			preparedStatement.setString(6, projet.getDescription());

			preparedStatement.setLong(7, projet.getDirecteur().getId());
			preparedStatement.setLong(8, projet.getChefDeProjet().getId());

			// Execute the query
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle SQL exceptions
		} finally {
			closeResources(connection, preparedStatement, null);
		}
	}

	// Méthode pour supprimer un projet de la base de données
	public void supprimerProjet(long projetId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DatabaseConnectionManager.getConnection();
			String sql = "DELETE FROM projet WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, projetId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle SQL exceptions
		} finally {
			closeResources(connection, preparedStatement, null);
		}
	}

	// Méthode pour afficher un projet à partir de la base de données
	public Projet afficherProjet(long projetId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DatabaseConnectionManager.getConnection();
			String sql = "SELECT * FROM projet WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, projetId);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				// Build and return the Projet object
				Projet projet = mapResultSetToProjet(resultSet);
				return projet;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Handle SQL exceptions
		} finally {
			closeResources(connection, preparedStatement, resultSet);
		}

		return null; // Return null if not found
	}

	// Méthode pour modifier un projet dans la base de données
	public void modifierProjet(long id,String nomProjet, String descriptionProjet, String clientProjet,
            Date dateDebut, Date dateLivraison, int joursDeveloppement, long chefProjetId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DatabaseConnectionManager.getConnection();
			String sql = "UPDATE projet SET nom_projet = ?, client = ?, date_debut = ?, date_livraison = ?, jours_developpement = ?, description = ?, chef_de_projet_id = ? WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);

			// Set values for the parameters
			preparedStatement.setString(1, nomProjet);
			preparedStatement.setString(2, clientProjet);
			preparedStatement.setDate(3, new java.sql.Date(dateDebut.getTime()));
			preparedStatement.setDate(4, new java.sql.Date(dateLivraison.getTime()));
			preparedStatement.setInt(5, joursDeveloppement);
			preparedStatement.setString(6,descriptionProjet);
			preparedStatement.setLong(7, chefProjetId);
			preparedStatement.setLong(8, id);
			// Execute the query
			preparedStatement.executeUpdate();
			System.out.println(preparedStatement);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur");
			// Handle SQL exceptions
		} finally {
			closeResources(connection, preparedStatement, null);
			System.out.println(preparedStatement);
		}
	}

	private static Projet mapResultSetToProjet(ResultSet resultSet) throws SQLException {
		Projet projet = new Projet();
		projet.setId(resultSet.getLong("id"));
		projet.setNomProjet(resultSet.getString("nom_projet"));
		projet.setClient(resultSet.getString("client"));
		projet.setDateDebut(resultSet.getDate("date_debut"));
		projet.setDateLivraison(resultSet.getDate("date_livraison"));
		projet.setJoursDeveloppement(resultSet.getInt("jours_developpement"));
		projet.setDescription(resultSet.getString("description"));
		projet.setMethodologie(resultSet.getString("methodologie"));

		// Mappage d'autres attributs
		projet.setDirecteur(mapResultSetToDirecteur(resultSet));
		projet.setChefDeProjet(mapResultSetToChefDeProjet(resultSet));

		return projet;
	}

	// Ajouter des méthodes de mappage pour d'autres entités (Directeur,
	// ChefDeProjet, etc.)
	private static Directeur mapResultSetToDirecteur(ResultSet resultSet) throws SQLException {
		Directeur directeur = new Directeur();
		directeur.setId(resultSet.getLong("directeur_id"));
		// Ajouter d'autres attributs si nécessaire
		return directeur;
	}

	private static ChefDeProjet mapResultSetToChefDeProjet(ResultSet resultSet) throws SQLException {
		ChefDeProjet chefDeProjet = new ChefDeProjet();
		chefDeProjet.setId(resultSet.getLong("chef_de_projet_id"));
		// Ajouter d'autres attributs si nécessaire
		return chefDeProjet;
	}

	// Méthode utilitaire pour fermer les ressources de base de données
	private static void closeResources(Connection connection, PreparedStatement preparedStatement,
			ResultSet resultSet) {
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

	public List<Projet> searchProjects(String searchQuery) {
		List<Projet> projects = new ArrayList<>();
		try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "root", "")) {
			String sql = "SELECT * FROM projet WHERE nom_projet LIKE ?";
			try (PreparedStatement preparedStatement1 = connection1.prepareStatement(sql)) {
				preparedStatement1.setString(1, "%" + searchQuery + "%");
				try (ResultSet resultSet1 = preparedStatement1.executeQuery()) {
					while (resultSet1.next()) {
						Projet projet = new Projet();
						projet.setId(resultSet1.getLong("id"));
						projet.setNomProjet(resultSet1.getString("nom_projet"));
						projet.setClient(resultSet1.getString("client"));
						projet.setDateDebut(resultSet1.getDate("date_debut"));
						projet.setDateLivraison(resultSet1.getDate("date_livraison"));
						projet.setJoursDeveloppement(resultSet1.getInt("jours_developpement"));
						projet.setDescription(resultSet1.getString("description"));
						projet.setMethodologie(resultSet1.getString("methodologie"));
						// Ajoutez d'autres propriétés selon votre modèle de données

						projects.add(projet);
						;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// Gérer les exceptions SQLException de manière appropriée
		}
		return projects;
	}



// Exemple de méthode pour la suppression du projet dans la base de données
public static void deleteProjet(String nomProjet) throws SQLException {
try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "root", "")) {
String sql = "DELETE FROM projet WHERE nom_projet=?";
try (PreparedStatement preparedStatement = connection1.prepareStatement(sql)) {
preparedStatement.setString(1, nomProjet);
preparedStatement.executeUpdate();
}
} catch (SQLException e) {
throw e;

}
}
public List<Service> getProjetsByDeveloppeur(long userId) {
    List<Service> projets = new ArrayList<>();

    try (Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "root", "")) {
        String sql = "SELECT p.id, p.nom_projet, p.client, p.description, p.date_debut, p.date_livraison, p.jours_developpement, p.methodologie, cdp.id as chef_de_projet_id, cdp.nom as chef_de_projet_nom, cdp.prenom as chef_de_projet_prenom, cdp.salaire as chef_de_projet_salaire, MAX(s.duree) as max_duree, MAX(s.description) as max_service_description FROM projet p JOIN service s ON p.id = s.projet_id JOIN developpeur d ON d.id = s.developpeur_id JOIN chefdeprojet cdp ON p.chef_de_projet_id = cdp.id WHERE d.user_id = ? GROUP BY p.id, p.nom_projet, p.client, p.description, p.date_debut, p.date_livraison, p.jours_developpement, p.methodologie, cdp.id, cdp.nom, cdp.prenom, cdp.salaire;";
        
        try (PreparedStatement preparedStatement = connection1.prepareStatement(sql)) {
            preparedStatement.setLong(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Projet projet = new Projet();
                    projet.setId(resultSet.getLong("id"));
                    projet.setNomProjet(resultSet.getString("nom_projet"));
                    projet.setClient(resultSet.getString("client"));
                    projet.setDescription(resultSet.getString("description"));
                    projet.setDateDebut(resultSet.getDate("date_debut"));
                    projet.setDateLivraison(resultSet.getDate("date_livraison"));
                    projet.setJoursDeveloppement(resultSet.getInt("jours_developpement"));
                    projet.setMethodologie(resultSet.getString("methodologie"));

                    ChefDeProjet chefDeProjet = new ChefDeProjet();
                    chefDeProjet.setId(resultSet.getLong("chef_de_projet_id"));
                    chefDeProjet.setNom(resultSet.getString("chef_de_projet_nom"));
                    chefDeProjet.setPrenom(resultSet.getString("chef_de_projet_prenom"));

                    projet.setChefDeProjet(chefDeProjet);

                    Service service = new Service();
                    service.setDuree(resultSet.getInt("max_duree"));
                    service.setDescription(resultSet.getString("max_service_description"));

                    service.setProjet(projet);

                    projets.add(service);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return projets;
}
public void ajouterTechnologi(String projetId, String[] technologies, String methodologie, String dateReunion) {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "root", "")) {
        // Insérer la date de réunion et la méthodologie dans la table projet
        String updateProjetSql = "UPDATE projet SET Date_Reunion = ?, methodologie = ? WHERE id = ?";
        try (PreparedStatement updateProjetStatement = connection.prepareStatement(updateProjetSql)) {
            updateProjetStatement.setString(1, dateReunion);
            updateProjetStatement.setString(2, methodologie);
            updateProjetStatement.setString(3, projetId);
            updateProjetStatement.executeUpdate();
        }

        // Insérer les technologies dans la table technologieProjet
        if (technologies != null) {
            String insertTechnologieProjetSql = "INSERT INTO projettechnologie (projet_id, technologie_id) VALUES (?, ?)";
            for (String technologie : technologies) {
                // Rechercher l'id de la technologie dans la table technologie
                String selectTechnologieIdSql = "SELECT id FROM technologie WHERE name = ?";
                try (PreparedStatement selectTechnologieIdStatement = connection.prepareStatement(selectTechnologieIdSql)) {
                    selectTechnologieIdStatement.setString(1, technologie);
                    try (ResultSet resultSet = selectTechnologieIdStatement.executeQuery()) {
                        if (resultSet.next()) {
                            String technologieId = resultSet.getString("id");

                            // Insérer l'association dans la table technologieProjet
                            try (PreparedStatement insertTechnologieProjetStatement = connection.prepareStatement(insertTechnologieProjetSql)) {
                                insertTechnologieProjetStatement.setString(1, projetId);
                                insertTechnologieProjetStatement.setString(2, technologieId);
                                insertTechnologieProjetStatement.executeUpdate();
                            }
                        }
                    }
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        // Gérer les erreurs SQL
    }
}

public List<Developpeur> getDeveloppeursByProjetId(String projetId)  {
    List<Developpeur> developpeurs = new ArrayList<>();

    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "root", "")) {

    // Requête SQL avec des jointures
    String sql = "SELECT DISTINCT developpeur.id, developpeur.nom, developpeur.prenom, developpeur.salaire, developpeur.user_id, " +
                 "user.email, user.password, user.role " +
                 "FROM projettechnologie " +
                 "JOIN competences ON projettechnologie.technologie_id = competences.technologie_id " +
                 "JOIN developpeur ON competences.developpeur_id = developpeur.id " +
                 "JOIN user ON developpeur.user_id = user.id " +
                 "WHERE projettechnologie.projet_id = ?";

    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, projetId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // Créer un objet Developpeur à partir des résultats de la requête
            Developpeur developpeur = new Developpeur();
            developpeur.setId(resultSet.getLong("id"));
            developpeur.setNom(resultSet.getString("nom"));
            developpeur.setPrenom(resultSet.getString("prenom"));
            developpeur.setSalaire(resultSet.getDouble("salaire"));

            // Créer un objet User à partir des résultats de la requête
            User user = new User();
            user.setId(resultSet.getLong("user_id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(resultSet.getString("role"));

            // Associer l'utilisateur au développeur
            developpeur.setUser(user);

            // Ajouter le développeur à la liste
            developpeurs.add(developpeur);
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Gérer l'exception selon vos besoins
    }

  
} catch (SQLException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
	return developpeurs;}


public void AffectDevelopeur(String projetId, String[] developpeurIds) {
	try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet", "root", "")) {
        String query = "INSERT INTO service (developpeur_id, projet_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (String developpeurId : developpeurIds) {
                preparedStatement.setLong(1, Long.parseLong(developpeurId));
                preparedStatement.setLong(2, Long.parseLong(projetId));
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les erreurs de base de données correctement
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Gérez les erreurs de connexion à la base de données
    }
	
}

}
