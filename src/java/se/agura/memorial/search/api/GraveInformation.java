package se.agura.memorial.search.api;

/**
 * 
 */

/**
 * @author Igors
 * 
 */
public class GraveInformation {
	private String firstName; // GA_Gravsatt.FORNAMN

	private String lastName; // GA_Gravsatt.EFTERNAMN

	private String dateOfBirth; // GA_Gravsatt.PERS_NR

	private String dateOfDeath; // GA_Gravsatt.DODSDATUM

	private String homeTown; // GA_KGardOLD_HEMORT + SYS.LKF

	private String cemetry; // GA_KGard.BEHAMNING

	private String blockName; // GA_KVarter.BENAMNING

	private String department; // GA_Avdelning.BENAMNING

	private String gravyNumber; // GA_Grav.GRAVNUMMER

	public GraveInformation(String firstName,
			                String lastName,
			                String dateOfBirth,
			                String dateOfDeath,
			                String homeTown,
			                String cemetry,
			                String blockName,
			                String department,
			                String gravyNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfDeath = dateOfDeath;
		this.homeTown = homeTown;
		this.cemetry = cemetry;
		this.blockName = blockName;
		this.department = department;
		this.gravyNumber = gravyNumber;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getCemetry() {
		return cemetry;
	}

	public void setCemetry(String cemetry) {
		this.cemetry = cemetry;
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

	public String getGravyNumber() {
		return gravyNumber;
	}

	public void setGravyNumber(String gravyNumber) {
		this.gravyNumber = gravyNumber;
	}

	public String getHomeTown() {
		return homeTown;
	}

	public void setHomeTown(String homeTown) {
		this.homeTown = homeTown;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
