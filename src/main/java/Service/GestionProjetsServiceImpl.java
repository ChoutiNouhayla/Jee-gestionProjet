// GestionProjetsServiceImpl.java
package Service;

import java.sql.SQLException;

import java.util.Date;
import java.util.List;


import Data.ProjetDataAccess;
import Models.Developpeur;
import Models.Projet;
import Models.Service;

public class GestionProjetsServiceImpl implements GestionProjets {

    private ProjetDataAccess projetDataAccess;
    
    public List<Projet> searchProjects(String searchQuery) {
        // Appeler la méthode de recherche de la couche de données
    	ProjetDataAccess projetDataAccess = new ProjetDataAccess();
        return projetDataAccess.searchProjects(searchQuery);
    }

    public void supprimerProjet(String nomProjet) {
    	// Utilisez la classe de données (ProjetData) pour interagir avec la base de données
    	try {
    		ProjetDataAccess.deleteProjet(nomProjet);
    	} catch (SQLException e) {
    	e.printStackTrace();
    	System.out.println("erreur");
    	}
    	}
    public List<Service> getProjetsByDeveloppeur(long developpeurId) {
    	ProjetDataAccess projetDataAccess = new ProjetDataAccess();
        return projetDataAccess.getProjetsByDeveloppeur(developpeurId);
    }
    // Injectez la dépendance vers ProjetDataAccess (par exemple, via un constructeur)
    public GestionProjetsServiceImpl(ProjetDataAccess projetDataAccess) {
    	
        this.projetDataAccess = projetDataAccess;
    }
    
    @Override
    public boolean projetExiste(String nomProjet) {
        // Appelez votre DAO pour vérifier si le nom du projet existe
        // Retournez true si le projet existe, sinon false
    	 projetDataAccess=new ProjetDataAccess();
        return projetDataAccess.projetExiste(nomProjet);
    }
    public void ajouterProjet(Projet projet) {
    	 projetDataAccess=new ProjetDataAccess();
        projetDataAccess.ajouterProjet(projet);
    }

    public GestionProjetsServiceImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	  public void ajouterCompetence(String projetId, String[] technologies, String methodologie, String dateReunion) {
	    	 projetDataAccess=new ProjetDataAccess();
	        projetDataAccess.ajouterTechnologi(projetId,technologies,methodologie,dateReunion);
	    }
    public Projet afficherProjet(long projetId) {
        return projetDataAccess.afficherProjet(projetId);
    }
	@Override
	public void modifierProjet(long id,String nomProjet, String descriptionProjet, String clientProjet, Date dateDebut,
			Date dateLivraison, int joursDeveloppement, Long chefProjet) {
		ProjetDataAccess projetDataAccess = new ProjetDataAccess();
    	projetDataAccess.modifierProjet(id,nomProjet, descriptionProjet, clientProjet, dateDebut, dateLivraison, joursDeveloppement, chefProjet);
		
	}

	@Override
	public List<Developpeur> getDeveloppeursByProjetId(String projetId)   {
		 projetDataAccess=new ProjetDataAccess();
	      return projetDataAccess.getDeveloppeursByProjetId(projetId);
		
	}

	@Override
	public void AffectDevelopeur(String projetId, String[] developpeurIds) {
		 projetDataAccess=new ProjetDataAccess();
		  projetDataAccess.AffectDevelopeur(projetId, developpeurIds);
		
	}

	
		
	}

 
   



