/**
 * 
 */
package se.agura.memorial.search.data;

import java.util.Collection;

import javax.ejb.FinderException;

import se.agura.memorial.search.api.CustomMemorialDate;

import com.idega.data.IDOHome;
import com.idega.data.IDORelationshipException;

/**
 * @author is
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
			String personIdentifier, CustomMemorialDate dateOfBirthFrom,
			CustomMemorialDate dateOfBirthTo,
			CustomMemorialDate dateOfDeathFrom,
			CustomMemorialDate dateOfDeathTo, String hometown, String graveyard)
			throws FinderException, IDORelationshipException;

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#ejbFindByGraveID
	 */
	public Collection findByGraveID(String graveId) throws FinderException;

}
