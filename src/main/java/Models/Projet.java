package Models;

import java.util.Date;

public class Projet {

	
	private Long id;
    private String nomProjet;
    private String client;
    private Date dateDebut;
    private Date dateLivraison;
    private Integer joursDeveloppement;
    private String description;
    private String methodologie;

    private Directeur directeur;
    private ChefDeProjet chefDeProjet;

	
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getNomProjet() {
		return nomProjet;
	}



	public void setNomProjet(String nomProjet) {
		this.nomProjet = nomProjet;
	}



	public String getClient() {
		return client;
	}



	public void setClient(String client) {
		this.client = client;
	}



	public Date getDateDebut() {
		return dateDebut;
	}



	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}



	public Date getDateLivraison() {
		return dateLivraison;
	}



	public void setDateLivraison(Date dateLivraison) {
		this.dateLivraison = dateLivraison;
	}



	public Integer getJoursDeveloppement() {
		return joursDeveloppement;
	}



	public void setJoursDeveloppement(Integer joursDeveloppement) {
		this.joursDeveloppement = joursDeveloppement;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getMethodologie() {
		return methodologie;
	}



	public void setMethodologie(String methodologie) {
		this.methodologie = methodologie;
	}



	public Directeur getDirecteur() {
		return directeur;
	}



	public void setDirecteur(Directeur directeur) {
		this.directeur = directeur;
	}



	public ChefDeProjet getChefDeProjet() {
		return chefDeProjet;
	}



	public void setChefDeProjet(ChefDeProjet chefDeProjet) {
		this.chefDeProjet = chefDeProjet;
	}



	public Projet() {
		// TODO Auto-generated constructor stub
	}
}
