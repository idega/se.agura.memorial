package se.agura.memorial.search.presentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import se.agura.memorial.search.api.Graveyard;
import se.agura.memorial.search.impl.MalmoSearchBMPBean;

public class SearchFormBackingBean {
	String firstName;
	String surname;
	Date dateOfBirthFrom;
	Date dateOfBirthTo;
	Date dateOfDeceaseFrom;
	Date dateOfDeceaseTo;
	String hometown;
	Graveyard Graveyard;
	static Map graveyards;

	public SearchFormBackingBean() {
		graveyards = new LinkedHashMap();

		/*
		Graveyard g1 = new Graveyard(1, "1. dist", "1. cem", 21); // name, id
		Graveyard g2 = new Graveyard(2, "2. dist", "2. cem", 22);
		Graveyard g3 = new Graveyard(3, "3. dist", "3. cem", 23);

		graveyards.put(new Integer(g1.getId()).toString(), g1); // key, value
		graveyards.put(new Integer(g2.getId()).toString(), g2); // key, value
		graveyards.put(new Integer(g3.getId()).toString(), g3); // key, value
		

		this.setGraveyard(g1);
		*/
		
		MalmoSearchBMPBean b = new MalmoSearchBMPBean();
		List listOfGraveyards = b.getGraveyards();

		for (Iterator it = listOfGraveyards.iterator(); it.hasNext();) {
			Graveyard g = (Graveyard) it.next();
			graveyards.put(new Integer(g.getId()).toString(), g);
		}		
		
		
	}

	String database;

	public Graveyard getGraveyard() {
		return Graveyard;
	}

	public void setGraveyard(Graveyard Graveyard) {
		this.Graveyard = Graveyard;
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
		this.firstName = firstName.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
	}

	/**
	 * searches
	 */
	public String search() {

		return "success";
	}

	/**
	 * clears everything
	 */
	public String clear() {

		setFirstName(null);
		setGraveyard(null);
		return "success";

	}

	// this is needed for converter
	public Map getGraveyards() {
		return graveyards;
	}

	// we must convert map to list of items or else
	// keys and values are swapped in HTML select if map is passed to
	// jsf:selectItems tag
	public List getGraveyardsItems() {
		List list = new ArrayList();
		Graveyard g;

		Iterator it = graveyards.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();

			g = (Graveyard) graveyards.get(key);
			list.add(new SelectItem(new Integer(g.getId()).toString(), // value
					g.getBenamning(), // label
					"some description" // desc )
			));

		}

		return list;
	}
}
