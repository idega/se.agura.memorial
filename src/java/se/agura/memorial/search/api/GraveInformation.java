package se.agura.memorial.search.api;

/**
 * 
 */

/**
 * @author Igors
 * 
 */
public class GraveInformation {

	private int graveID; // GA_Gravsatt.Grav_ID
	private int lopNr; // GA_Gravsatt.LopNr
	private String firstName; // GA_Gravsatt.FORNAMN
	private String lastName; // GA_Gravsatt.EFTERNAMN
	private String dateOfBirth; // GA_Gravsatt.PERS_NR
	private String dateOfDeath; // GA_Gravsatt.DODSDATUM
	private int rowNr; // counter	
	
	public GraveInformation() {
	
	}

	public void init(
            int graveID,
            int lopNr,
            String firstName,
            String lastName,
            String dateOfBirth,
            String dateOfDeath,
            int rowNr) {


			this.setGraveID(graveID);
			this.setLopNr(lopNr);
			this.setFirstName(firstName);
			this.setLastName(lastName);
			this.setDateOfBirth(dateOfBirth);
			this.setDateOfDeath(dateOfDeath); 
			this.setRowNr(rowNr);
	}

	public int getRowNr() {
		return rowNr;
	}
	

	public void setRowNr(int rowNr) {
		this.rowNr = rowNr;
	}
	

	public String getDateOfBirth() {
		return dateOfBirth;
	}
	

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	

	public int getLopNr() {
		return lopNr;
	}
	

	public void setLopNr(int lopNr) {
		this.lopNr = lopNr;
	}
	

	public String getDateOfDeath() {
		return dateOfDeath;
	}
	

	public void setDateOfDeath(String dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	

	public String getFirstName() {
		return firstName;
	}
	

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	

	public int getGraveID() {
		return graveID;
	}
	

	public void setGraveID(int graveID) {
		this.graveID = graveID;
	}
	

	public String getLastName() {
		return lastName;
	}
	

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	


}
