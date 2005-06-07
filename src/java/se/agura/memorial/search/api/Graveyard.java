package se.agura.memorial.search.api;

/**
 * @author Igors
 * 
 */

public class Graveyard {
	private int id;

	private String kGard; //  

	private String Benamning; //	   

	private int distrikt_ID;

	public Graveyard(int id, String kGard, String Benamning, int distrikt_ID) {
		super();

		this.id = id;
		this.kGard = kGard;
		this.Benamning = Benamning;
		this.distrikt_ID = distrikt_ID;
	}

	public String getBenamning() {
		return Benamning;
	}

	public void setBenamning(String benamning) {
		Benamning = benamning;
	}

	public int getDistrikt_ID() {
		return distrikt_ID;
	}

	public void setDistrikt_ID(int distrikt_ID) {
		this.distrikt_ID = distrikt_ID;
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

}
