package se.agura.memorial.experiment;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

public class GraveyardSearchBean {
	String firstName;
	String surname;
	
	Date dateOfBirthFrom;
	Date dateOfBirthTo;
	
	Date dateOfDeceaseFrom;
	Date dateOfDeceaseTo;
	
	String hometown;
	
	Cemetery cemetery;		
	
	static Map cemeteries;	
	
	public GraveyardSearchBean() {
		cemeteries = new LinkedHashMap();
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		
		
		
		Cemetery c1 = new Cemetery("1. cem", "1"); //name, id
		Cemetery c2 = new Cemetery("2. cem", "2"); 
		Cemetery c3 = new Cemetery("3. cem", "3"); 
		
		cemeteries.put(c1.getId(), c1);  //key, value
		cemeteries.put(c2.getId(), c2);  //key, value		
		cemeteries.put(c3.getId(), c3);  //key, value		
		
		this.setCemetery(c1);			
	}
	
	String database;

	public Cemetery getCemetery() {
		return cemetery;
	}

	public void setCemetery(Cemetery cemetery) {
		this.cemetery = cemetery;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public Date getDateOfBirthFrom() {
		return dateOfBirthFrom;
	}

	public void setDateOfBirthFrom(Date dateOfBirthFrom) {
		this.dateOfBirthFrom = dateOfBirthFrom;
	}

	public Date getDateOfBirthTo() {
		return dateOfBirthTo;
	}

	public void setDateOfBirthTo(Date dateOfBirthTo) {
		this.dateOfBirthTo = dateOfBirthTo;
	}

	public Date getDateOfDeceaseFrom() {
		return dateOfDeceaseFrom;
	}

	public void setDateOfDeceaseFrom(Date dateOfDeceaseFrom) {
		this.dateOfDeceaseFrom = dateOfDeceaseFrom;
	}

	public Date getDateOfDeceaseTo() {
		return dateOfDeceaseTo;
	}

	public void setDateOfDeceaseTo(Date dateOfDeceaseTo) {
		this.dateOfDeceaseTo = dateOfDeceaseTo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}	
	
	/**
	 * 
	 * searches 
	 */
	public String search() {
		
		return "success";
	}
	
	/**
	 * 
	 * clears everything
	 */
	public String clear() {
		
		setFirstName(null);
		setCemetery(null);
		return "success";
		
	}	
	
	//this is needed for converter
	public Map getCemeteries() {
		return cemeteries;
	}
	
	// we must convert map to list of items or else 
	// keys and values are swapped in HTML select if map is passed to jsf:selectItems tag
	public List getCemeteriesItems() {
		List list = new ArrayList();
		Cemetery c; 	
		
		Iterator it = cemeteries.keySet().iterator();
	    while (it.hasNext()) {	
	        Object key = it.next();		

			c = (Cemetery) cemeteries.get(key);
			list.add(new SelectItem(c.getId(), // value
					c.getName(), // label
					"some description" // desc )
			));
			
	    }

		return list;
	}
	
	

	
	
}
