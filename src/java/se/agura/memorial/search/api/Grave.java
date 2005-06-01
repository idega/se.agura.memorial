package se.agura.memorial.search.api;


import java.util.Date;

public class Grave {

    private String graveId;
	private String firstName; 
	private String lastName; 
	private Date dateOfBirth; 
	private Date dateOfDeath; 		
    private String hometown;
	
	private GraveInformation graveInfo;
	

	public Grave(String graveId, 
			     String firstName, 
			     String lastName, 
			     Date dateOfBirth, 
			     Date dateOfDeath, 
			     String hometown, 
			     GraveInformation graveInfo) {
		super();

		this.graveId = graveId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfDeath = dateOfDeath;
		this.hometown = hometown;
		this.graveInfo = graveInfo;
	}


	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	

	public Date getDateOfDeath() {
		return dateOfDeath;
	}
	

	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	

	public String getFirstName() {
		return firstName;
	}
	

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	

	public String getGraveId() {
		return graveId;
	}
	

	public void setGraveId(String graveId) {
		this.graveId = graveId;
	}
	

	public GraveInformation getGraveInfo() {
		return graveInfo;
	}
	

	public void setGraveInfo(GraveInformation graveInfo) {
		this.graveInfo = graveInfo;
	}
	

	public String getHometown() {
		return hometown;
	}
	

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	

	public String getLastName() {
		return lastName;
	}
	

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	 

	
	
		
}
