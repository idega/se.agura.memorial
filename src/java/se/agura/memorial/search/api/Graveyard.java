package se.agura.memorial.search.api;

/**
 * @author Igors
 * 
 */

public class Graveyard {
	private int id;

	private String kGard; //  

	private String Benamning; //	   

	private int distriktID;

	public Graveyard(int id, String kGard, String Benamning, int distriktID) {
		super();

		this.id = id;
		this.kGard = kGard;
		this.Benamning = Benamning;
		this.distriktID = distriktID;
	}

	public String getBenamning() {
		return Benamning;
	}

	public void setBenamning(String benamning) {
		Benamning = benamning;
	}

	public int getDistrikt_ID() {
		return distriktID;
	}

	public void setDistrikt_ID(int distrikt_ID) {
		this.distriktID = distrikt_ID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKGard() {
		return kGard;
	}

	public void setKGard(String gard) {
		kGard = gard;
	}
	
	public int hashCode() {
		String tempKGard = getKGard();
		if ( tempKGard == null) {
			return getId() + getDistrikt_ID();
		}
		return getId() + getDistrikt_ID() + tempKGard.hashCode();
	}
	
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}	
		Graveyard graveyard = (Graveyard) object;
		String tempKGard = getKGard();
		String graveyardKGard = graveyard.getKGard();
		if (tempKGard == null && graveyardKGard == null) {
			return  (graveyard.getId() == getId() && graveyard.getDistrikt_ID() == getDistrikt_ID());
		}
		if (tempKGard == null || graveyardKGard == null) {
			return false;
		}
		return  (graveyard.getId() == getId() && graveyard.getDistrikt_ID() == getDistrikt_ID() && graveyardKGard.equals(tempKGard));
	}

}
