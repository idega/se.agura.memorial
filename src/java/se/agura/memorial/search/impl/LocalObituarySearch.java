package se.agura.memorial.search.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import se.agura.memorial.search.api.CustomMemorialDate;
import se.agura.memorial.search.api.Grave;
import se.agura.memorial.search.api.GraveInformation;
import se.agura.memorial.search.api.Graveyard;
import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.search.data.GraveGraveyard;
import se.agura.memorial.search.data.GraveGraveyardHome;
import se.agura.memorial.search.data.GraveLocallyStored;
import se.agura.memorial.search.data.GraveLocallyStoredHome;
import se.agura.memorial.util.Utility;

import com.idega.data.IDOLookup;

public class LocalObituarySearch implements ObituarySearch {

	public Collection findGraves(String firstName, 
			                     String lastName,
			                     String personIdentifier,
			                     CustomMemorialDate dateOfBirthFrom,
			                     CustomMemorialDate dateOfBirthTo,
								 CustomMemorialDate dateOfDeathFrom,
								 CustomMemorialDate dateOfDeathTo,
								 String hometown, 
								 String graveyard) 
	{
		List result = new ArrayList();
		
		GraveLocallyStoredHome ggh = null;	            
        try {
			ggh = (GraveLocallyStoredHome) IDOLookup.getHome(GraveLocallyStored.class);
			

			Collection coll = ggh.findGraves(
					firstName,
					lastName,
					personIdentifier,
					dateOfBirthFrom,
					dateOfBirthTo,
					dateOfDeathFrom,
					dateOfDeathTo,
					hometown,
					graveyard);
			
			int counter = 0;
			Iterator it = coll.iterator();
			while (it.hasNext()) {
				Object o = it.next();

				GraveLocallyStored g = (GraveLocallyStored) o;				
				
				result.add(new Grave(
						g.getColumID(),
						g.getFirstName(),
						g.getLastName(),						
						Utility.dateToMemorialDate(g.getDateOfBirth()),
						Utility.dateToMemorialDate(g.getDateOfDeath()),
						null,
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
				GraveGraveyard g = (GraveGraveyard) o;				
				result.add(new Graveyard(
						((Integer)g.getPrimaryKey()).intValue(),
						g.getGraveyardName(),
						g.getGraveyardName(),
						1));

			}				
			
		} catch (Exception e) {
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
			Collection coll = ggh.findByGraveID(graveId);
			
			int counter = 0;
			Iterator it = coll.iterator();
			if (it.hasNext()) {
				Object o = it.next();
                
				
				GraveLocallyStored g = (GraveLocallyStored) o;	
				GraveInformation aa = null;
				grave = new Grave(
						g.getColumID(),						
						g.getFirstName(),
						g.getLastName(),						
						Utility.dateToMemorialDate(g.getDateOfBirth()),
						Utility.dateToMemorialDate(g.getDateOfDeath()),
						null,  //burial date 
						g.getHomeTown(),
						aa = new GraveInformation(
								g.getGraveNumber(),
								g.getBlock(),
								g.getDepartment(),
								g.getCemetery(),
								g.getParish(),
								g.getCommune(),
								g.getCountry() ));

			}				
			
		} catch (Exception e) {
			System.out.println("error occured while getting one grave:");
			e.printStackTrace();
		}
		
		return grave;
	}

}
