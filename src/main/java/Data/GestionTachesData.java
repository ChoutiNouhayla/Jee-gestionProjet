package Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Models.ChefDeProjet;
import Models.Developpeur;
import Models.Directeur;
import Models.Projet;
import Models.Service;
import Models.Taches;
import Models.Technologie;
import Models.User;

public class GestionTachesData {
	  private static final String URL = "jdbc:mysql://localhost:3306/projet";
	    private static final String USER = "root";
	    private static final String PASSWORD = "";

    public long getDeveloppeurByUserId(Long userId) {
        Developpeur developpeur = new Developpeur();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT id FROM developpeur WHERE user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Créer et retourner un objet Developpeur
                       
                        
                        developpeur.setId(resultSet.getLong("id"));  
                        
                        
                        
                    }
                }
                System.out.println(developpeur.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developpeur.getId();
    }

    public Projet getProjetsByNom(String nomProjet) {
        Projet projet = null; // Initialisez à null par défaut
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM projet WHERE nom_projet LIKE ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + nomProjet + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        // Créer et ajouter des objets Projet à la liste
                        Directeur directeur = new Directeur();
                        ChefDeProjet chefdeprojet = new ChefDeProjet();
                        projet = new Projet();
                        projet.setId(resultSet.getLong("id")); 
                        projet.setNomProjet(resultSet.getString("nom_projet"));     
                        projet.setClient(resultSet.getString("client")); 
                        projet.setDateDebut(resultSet.getDate("date_debut")); 
                        projet.setDateLivraison(resultSet.getDate("date_livraison"));  
                        projet.setJoursDeveloppement(resultSet.getInt("jours_developpement"));  
                        projet.setDescription(resultSet.getString("description")); 
                        projet.setMethodologie(resultSet.getString("methodologie")); 
                        directeur.setId(resultSet.getLong("directeur_id"));
                        projet.setDirecteur(directeur);
                        chefdeprojet.setId(resultSet.getLong("chef_de_projet_id"));
                        projet.setChefDeProjet(chefdeprojet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projet;
    }
    public List<Technologie> getTechnologiesProjet(String nomProjet) {
        List<Technologie> technologies = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT t.* " +
                         "FROM projet p " +
                         "JOIN projettechnologie pt ON p.id = pt.projet_id " +
                         "JOIN technologie t ON pt.technologie_id = t.id " +
                         "WHERE p.nom_projet LIKE ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + nomProjet + "%");
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Technologie technologie = new Technologie();
                        technologie.setId(resultSet.getLong("id"));
                        technologie.setName(resultSet.getString("name"));
                        // Ajouter la technologie à la liste
                        technologies.add(technologie);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return technologies;
    }



    public List<Service> getServicesForProjetAndDeveloppeur(Long projetId, Long developpeurId) {
        List<Service> services = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM service WHERE projet_id = ? AND developpeur_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, projetId);
                statement.setLong(2, developpeurId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                    	 Projet projet = new Projet();
                         Developpeur Developpeur=new Developpeur();
                         Developpeur.setId( resultSet.getLong("developpeur_id"));
                         projet.setId(resultSet.getLong("projet_id"));
                        // Créer et ajouter des objets Service à la liste
                        Service service = new Service(
                        		resultSet.getLong("id"),
                        		Developpeur,
                        		projet,
                                resultSet.getInt("duree"),
                                resultSet.getString("description")
                        );
                        services.add(service);
                        System.out.println(resultSet.getLong("id"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return services;
    }
    
    public List<Taches> getTache(Long serviceId) {
        List<Taches> tache = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM `tache` WHERE  service_id = ? ";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, serviceId);
               
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                    	 Taches taches = new Taches();
                    	 taches.setId(resultSet.getLong("id"));
                    	 taches.setDescription(resultSet.getString("description"));
                    	 taches.setAvancement(resultSet.getInt("avancement"));
                    	 taches.setUpdated_at(resultSet.getDate("updated_at"));
                    	 
                        
                         
                        
                      
                        tache.add(taches);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tache;
    }
    
    

    public void addProgressionToTache(Long tacheId, int pourcentage,String description) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE tache SET avancement = ?,description=? WHERE id = ? ";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, pourcentage);
                statement.setString(2,description);
                statement.setLong(3, tacheId);
                
              
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Developpeur> getDevelopersByProjectName(String projectName) {
        List<Developpeur> developers = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Étape 1 : Obtenir l'ID du projet à partir du nom du projet
            String projectIdQuery = "SELECT id FROM projet WHERE nom_projet = ?";
            try (PreparedStatement projectIdStatement = connection.prepareStatement(projectIdQuery)) {
                projectIdStatement.setString(1, projectName);
                try (ResultSet projectIdResult = projectIdStatement.executeQuery()) {
                    if (projectIdResult.next()) {
                        long projectId = projectIdResult.getLong("id");

                        // Étape 2 : Obtenir les ID des développeurs associés au projet depuis la table service
                        String developerIdsQuery = "SELECT DISTINCT developpeur_id FROM service WHERE projet_id = ?";
                        try (PreparedStatement developerIdsStatement = connection.prepareStatement(developerIdsQuery)) {
                            developerIdsStatement.setLong(1, projectId);
                            try (ResultSet developerIdsResult = developerIdsStatement.executeQuery()) {
                                while (developerIdsResult.next()) {
                                    long developerId = developerIdsResult.getLong("developpeur_id");

                                    // Étape 3 : Obtenir les détails du développeur depuis la table developpeur
                                    String developerDetailsQuery = "SELECT nom, prenom FROM developpeur WHERE id = ?";
                                    try (PreparedStatement developerDetailsStatement = connection.prepareStatement(developerDetailsQuery)) {
                                        developerDetailsStatement.setLong(1, developerId);
                                        try (ResultSet developerDetailsResult = developerDetailsStatement.executeQuery()) {
                                            if (developerDetailsResult.next()) {
                                                String nom = developerDetailsResult.getString("nom");
                                                String prenom = developerDetailsResult.getString("prenom");

                                                // Créer un objet Developer et l'ajouter à la liste
                                                Developpeur developer = new Developpeur( developerId, nom, prenom);
                                                developers.add(developer);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Gérez les exceptions correctement en production
        }

        return developers;
    }
}
