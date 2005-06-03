/**
 * 
 */
package se.agura.memorial.search.data;

import java.util.Collection;

import javax.ejb.FinderException;

import com.idega.data.IDOHome;
import com.idega.data.IDORelationshipException;

/**
 * @author Dainis
 *
 */
public interface GraveLocallyStoredHome extends IDOHome {
	public GraveLocallyStored create() throws javax.ejb.CreateException;

	public GraveLocallyStored findByPrimaryKey(Object pk)
			throws javax.ejb.FinderException;

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#ejbFindGraves
	 */
	public Collection findGraves(String firstName, String lastName,
			String personIdentifier, String dateOfBirthFrom,
			String dateOfBirthTo, String dateOfDeathFrom, String dateOfDeathTo,
			String hometown, String graveyard) throws FinderException,
			IDORelationshipException;

}
