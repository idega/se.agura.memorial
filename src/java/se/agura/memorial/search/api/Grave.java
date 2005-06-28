package se.agura.memorial.search.api;


/**
 * @author Igors
 * 
 */

public class Grave {

    private String graveId;
	private String firstName; 
	private String lastName; 
	private CustomMemorialDate dateOfBirth; 
	private CustomMemorialDate dateOfDeath; 
	private CustomMemorialDate dateOfBurial; 		
    private String hometown;
	
	private GraveInformation graveInfo;
	

	public Grave(String graveId, 
			     String firstName, 
			     String lastName, 
				 CustomMemorialDate dateOfBirth, 
				 CustomMemorialDate dateOfDeath, 
				 CustomMemorialDate dateOfBurial,
			     String hometown, 
			     GraveInformation graveInfo) {
		super();

		this.graveId = graveId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfDeath = dateOfDeath;
		this.dateOfBurial = dateOfBurial;
		this.hometown = hometown;
		this.graveInfo = graveInfo;
	}


	public CustomMemorialDate getDateOfBirth() {
		return dateOfBirth;
	}
	

	public void setDateOfBirth(CustomMemorialDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	

	public CustomMemorialDate getDateOfDeath() {
		return dateOfDeath;
	}
	

	public void setDateOfDeath(CustomMemorialDate dateOfDeath) {
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
	 

	
	
		
	public CustomMemorialDate getDateOfBurial() {
		return dateOfBurial;
	}
	public void setDateOfBurial(CustomMemorialDate dateOfBurial) {
		this.dateOfBurial = dateOfBurial;
	}
}
