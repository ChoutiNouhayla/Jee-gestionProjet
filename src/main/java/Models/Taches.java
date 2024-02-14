/**
 * 
 */
package Models;

import java.util.Date;

/**
 * 
 */
public class Taches {

	private Long id;
    private String description;
    private Integer avancement;

    private Service Service;
    private Date updated_at;
   
    
	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAvancement() {
		return avancement;
	}

	public void setAvancement(Integer avancement) {
		this.avancement = avancement;
	}



	public Service getService() {
		return Service;
	}

	public void setService(Service service) {
		Service = service;
	}

	public Taches() {
		// TODO Auto-generated constructor stub
	}

}
