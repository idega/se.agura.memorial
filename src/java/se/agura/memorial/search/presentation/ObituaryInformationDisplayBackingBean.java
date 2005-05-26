package se.agura.memorial.search.presentation;

import se.agura.memorial.search.api.Grave;
import se.agura.memorial.search.data.MalmoSearchBMPBean;

public class ObituaryInformationDisplayBackingBean {
	int graveID;
	int lopNr;
	
	private Grave grave = null;
	
	String aaa = "aaa";

	public String getAaa() {
		return aaa;
	}

	public void setAaa(String aaa) {
		this.aaa = aaa;
	}

	public int getGraveID() {
		return graveID;
	}

	public void setGraveID(int graveID) {
		
		
		this.graveID = graveID;
	}

	public int getLopNr() {
		return lopNr;
	}

	public void setLopNr(int lopNr) {
		//i am allmost sure that managed properties are 
		//initialized in order they are defined in faces-config.xml
		
		this.lopNr = lopNr;
		
		//now lets get grave		
		MalmoSearchBMPBean mb = new MalmoSearchBMPBean();
		this.grave = mb.findGrave(this.getGraveID(), this.getLopNr(), null); 
		
		System.out.println("grave: " + grave.getFirstName());
	}

	public Grave getGrave() {
		return grave;
	}
	
}
