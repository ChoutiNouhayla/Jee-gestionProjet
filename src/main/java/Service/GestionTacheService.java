package Service;

import java.util.List;

import Models.Developpeur;
import Models.Projet;
import Models.Service;
import Models.Taches;
import Models.Technologie;

public interface GestionTacheService {
	 public long getDeveloppeurByUserId(long userId);
	 public Projet searchProjects(String projectName) ;
	 public List<Service> getServicesForProjetAndDeveloppeur(Long projetId, Long developpeurId);
	  public void addProgressionToTache(long tacheId, int pourcentage,String description);
	  public List<Taches> getTacheForService(Long ServiceId) ;
	  public List<Technologie> getTechnologie(String projectName) ;
	  public List<Developpeur> getDevelopersByProjectName(String projectName);
}
