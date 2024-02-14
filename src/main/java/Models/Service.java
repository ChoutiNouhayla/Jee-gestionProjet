package Models;

public class Service {

	private long id;
	private Developpeur developpeur;
    private Projet projet;
    private int duree;
    private String description;
    
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Developpeur getDeveloppeur() {
		return developpeur;
	}

	public void setDeveloppeur(Developpeur developpeur) {
		this.developpeur = developpeur;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Service() {
		// TODO Auto-generated constructor stub
	}

	public Service(long id, Developpeur developpeur, Projet projet, int duree, String description) {
		super();
		this.id = id;
		this.developpeur = developpeur;
		this.projet = projet;
		this.duree = duree;
		this.description = description;
	}



}
