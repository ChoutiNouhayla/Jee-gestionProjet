package Service;

import java.util.Date;
import java.util.List;

import Models.Developpeur;
import Models.Projet;
import Models.Service;

public interface GestionProjets {
    void ajouterProjet(Projet projet);
    void supprimerProjet(String projetId);
    Projet afficherProjet(long projetId);

	boolean projetExiste(String nomProjet);
	void modifierProjet(long id,String nomProjet, String descriptionProjet, String clientProjet, Date dateDebut,
			Date dateLivraison, int joursDeveloppement, Long chefProjet);
	  public List<Service> getProjetsByDeveloppeur(long developpeurId);
	 void ajouterCompetence(String projetId, String[] technologies, String methodologie, String dateReunion);
	 public List<Developpeur> getDeveloppeursByProjetId(String projetId) ;
	 public void AffectDevelopeur(String projetId, String[] developpeurIds);
}
