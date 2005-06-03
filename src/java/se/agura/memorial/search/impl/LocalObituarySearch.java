package se.agura.memorial.search.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import se.agura.memorial.search.api.Grave;
import se.agura.memorial.search.api.Graveyard;
import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.search.data.GraveGraveyard;
import se.agura.memorial.search.data.GraveGraveyardHome;
import se.agura.memorial.search.data.GraveLocallyStored;
import se.agura.memorial.search.data.GraveLocallyStoredHome;


import com.idega.data.IDOLookup;

public class LocalObituarySearch implements ObituarySearch {

	public Collection findGraves(String firstName, String lastName,
			String personIdentifier, String dateOfBirthFrom,
			String dateOfBirthTo, String dateOfDeathFrom, String dateOfDeathTo,
			String hometown, String graveyard) 
	{
		List result = new ArrayList();
		
		GraveLocallyStoredHome ggh = null;	            
        try {
			ggh = (GraveLocallyStoredHome) IDOLookup.getHome(GraveLocallyStored.class);
			Collection coll = ggh.findAll();
			
			int counter = 0;
			Iterator it = coll.iterator();
			while (it.hasNext()) {
				Object o = it.next();

				GraveLocallyStored g = (GraveLocallyStored) o;				
				
				result.add(new Grave(
						g.getColumID(),
						g.getFirstName(),
						g.getLastName(),						
						g.getDateOfBirth(),
						g.getDateOfDeath(),
						null,
						null));

			}				
			
		} catch (Exception e) {
			System.out.println("error occured while getting graves:");
			e.printStackTrace();
		}
		
	
		
		return result;
	}

	public Collection getGraveyards() {

		List result = new ArrayList();
		
		GraveGraveyardHome ggh = null;	            
        try {
			ggh = (GraveGraveyardHome) IDOLookup.getHome(GraveGraveyard.class);
			Collection coll = ggh.findAll();
			
			int counter = 0;
			Iterator it = coll.iterator();
			while (it.hasNext()) {
				Object o = it.next();
				System.out.println("one more graveyard!");
				GraveGraveyard g = (GraveGraveyard) o;				
				
				result.add(new Graveyard(
						++counter,//()g.getPrimaryKey(),
						g.getGraveyardName(),
						g.getGraveyardName(),
						1));

			}				
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error occured while getting graveyards:");
			e.printStackTrace();
		}
		
	
		
		return result;
	}

	public Grave findGrave(String graveId) {
         
	    Grave grave = null;
		
		GraveLocallyStoredHome ggh = null;	            
        try {
			ggh = (GraveLocallyStoredHome) IDOLookup.getHome(GraveLocallyStored.class);
			Collection coll = ggh.findAll();
			
			int counter = 0;
			Iterator it = coll.iterator();
			if (it.hasNext()) {
				Object o = it.next();

				GraveLocallyStored g = (GraveLocallyStored) o;				
				
				grave = new Grave(
						(String)g.getPrimaryKey(),
						g.getFirstName(),
						g.getLastName(),						
						g.getDateOfBirth(),
						g.getDateOfDeath(),
						null,
						null);
				

			}				
			
		} catch (Exception e) {
			System.out.println("error occured while getting one grave:");
			e.printStackTrace();
		}
		
		return grave;
	}

}
