package Service;

import java.util.List;


import Data.GestionTachesData;
import Models.Developpeur;
import Models.Projet;
import Models.Service;
import Models.Taches;
import Models.Technologie;

public class GestiontacheServiceImpl implements GestionTacheService {

	
	private GestionTachesData gestionTachesData = new GestionTachesData();

    public long getDeveloppeurByUserId(long userId) {
        return gestionTachesData.getDeveloppeurByUserId(userId);
    }

    public Projet searchProjects(String projectName) {
        return gestionTachesData.getProjetsByNom(projectName);
    }

    public List<Service> getServicesForProjetAndDeveloppeur(Long projetId, Long developpeurId) {
        return gestionTachesData.getServicesForProjetAndDeveloppeur(projetId, developpeurId);
    }
    public List<Taches> getTacheForService(Long ServiceId) {
        return gestionTachesData.getTache(ServiceId);
    }
    public List<Technologie> getTechnologie(String projectName) {
		return gestionTachesData.getTechnologiesProjet(projectName);
    	
    }

    public void addProgressionToTache(long tacheId,  int pourcentage,String description) {
        gestionTachesData.addProgressionToTache(tacheId, pourcentage,description);
    }

	@Override
	public List<Developpeur> getDevelopersByProjectName(String projectName) {
		// TODO Auto-generated method stub
		return gestionTachesData.getDevelopersByProjectName(projectName);
	}



}
