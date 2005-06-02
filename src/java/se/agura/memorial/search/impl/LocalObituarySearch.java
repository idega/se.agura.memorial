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

import com.idega.data.IDOLookup;

public class LocalObituarySearch implements ObituarySearch {

	public Collection findGraves(String firstName, String lastName,
			String personIdentifier, String dateOfBirthFrom,
			String dateOfBirthTo, String dateOfDeathFrom, String dateOfDeathTo,
			String hometown, String graveyard) 
	{
		// TODO make this work using data.GraveLocallyStored
		return null;
	}

	public Collection getGraveyards() {
		// TODO make this work using GraveGraveyard
		List result = new ArrayList();
		
		GraveGraveyardHome ggh = null;	            
        try {
			ggh = (GraveGraveyardHome) IDOLookup.getHome(GraveGraveyard.class);
			Collection coll = ggh.findAll();
			
			Iterator it = coll.iterator();
			while (it.hasNext()) {
				Object o = it.next();

				GraveGraveyard g = (GraveGraveyard) o;
				
				
				result.add(new Graveyard(
						1,//()g.getPrimaryKey(),
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
		// TODO make this work using data.GraveLocallyStored
		return null;
	}

}
