package se.agura.memorial.search.presentation;

import se.agura.memorial.search.data.GraveLocallyStored;
import se.agura.memorial.search.data.GraveLocallyStoredHome;

import com.idega.data.IDOLookup;

public class NewPersonBean {
	String firstName;
	String lastName;
	String dateOfBirth;
	String dateOfDeath;
	String hometown;
	String burialPlace;
	String cemetery;
	String department;
	String block;
	String graveNumber;
	Boolean createObituaryAfterSaving;
	
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getBurialPlace() {
		return burialPlace;
	}
	public void setBurialPlace(String burialPlace) {
		this.burialPlace = burialPlace;
	}
	public String getCemetery() {
		return cemetery;
	}
	public void setCemetery(String cemetery) {
		this.cemetery = cemetery;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getDateOfDeath() {
		return dateOfDeath;
	}
	public void setDateOfDeath(String dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getGraveNumber() {
		return graveNumber;
	}
	public void setGraveNumber(String graveNumber) {
		this.graveNumber = graveNumber;
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
	public Boolean getCreateObituaryAfterSaving() {
		return createObituaryAfterSaving;
	}
	public void setCreateObituaryAfterSaving(Boolean createObituaryAfterSaving) {
		this.createObituaryAfterSaving = createObituaryAfterSaving;
	}
		
	public String save() {
		//
        try {
			GraveLocallyStoredHome home = (GraveLocallyStoredHome) IDOLookup.getHome(GraveLocallyStored.class);
			GraveLocallyStored gls = home.create();
			
			gls.setFirstName(this.getFirstName());			
			gls.setLastName(this.getLastName());
			
			gls.setDateOfBirth(new java.sql.Date(new java.util.Date().getTime())); //TODO
			gls.setDateOfDeath(new java.sql.Date(new java.util.Date().getTime())); //TODO
			
			gls.setHomeTown(this.getHometown());
			
			gls.setCemetery(this.getCemetery());
			gls.setBurialPlace(this.getBurialPlace());
			gls.setBlock(this.getBlock());
			gls.setDepartment(this.getDepartment());
			gls.setGraveNumber(this.getGraveNumber());
			
			gls.setAPIDBConnection("API DB CONN TEST"); //TODO
			gls.setDatabaseName("Local"); //TODO
						
			gls.store();			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "failure";
		}		
		
		return "success";
	}
}
