package Service;

public class GestionCompetencesServiceImp implements GestionCompetencesService {
	 private Data.GestionCompetenceData GestionCompetenceData = new Data.GestionCompetenceData();
	@Override
	public void ajouterCompetences(long userId, String[] competences) {
		GestionCompetenceData.ajouterCompetence(userId, competences);
		
	}
	

}
