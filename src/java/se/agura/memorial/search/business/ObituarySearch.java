package se.agura.memorial.search.business;
/**
 * 
 
Here we need interface for thee actual search classes, we could call 
it ObituarySearch
It will need three methods to begin with at least
findGraves(String bean.firstName, String bean.lastName, Interval 
bean.dateOfBirth, Interval bean.dayOfDeath, String bean.region, String 
bean.graveyard)  - For the search form
getGraveyards() - For the dropdown in the search form (or to load to 
the local database once and from there to the dropdown)
findGrave(string identifier) - for the grave-details in the Obituary 
part

findGraves could return list of Grave objects which is class you 
could define in se.agura.memorial.search.business.  The fields needed 
would be: String identifier, String firstName, String lastName Date 
dateOfBirth, Date dateOfDeath, Region region, GraveInformation info; 
where GraveInformation would be similar class with the fields:  String 
graveNumber, String block, String department, String cemetery.  Region 
may not be necessary to begin with, I let you know.  It would then be 
Region with the fields: String name, String type where type can be 
county, Commune, Parish.


 * 
 */


import java.util.Collection;
import java.util.List;

import javax.ejb.FinderException;

import se.agura.memorial.search.api.Grave;
/**
 * @author Igors
 *
 */
public interface ObituarySearch {
              
	public Collection findGraves (
			           String firstName,
			           String lastName,
					   String personIdentifier,
			           String dateOfBirthFrom,
			           String dateOfBirthTo,			           
			           String dayOfDeathFrom,
			           String dayOfDeathTo,			           
			           String region,
			           String graveyard,
			           String database)  throws FinderException ;
	
	public List getGraveyards(String database);	

	
	public Grave findGrave(int gravID,int LopNr,String database);
	
}
