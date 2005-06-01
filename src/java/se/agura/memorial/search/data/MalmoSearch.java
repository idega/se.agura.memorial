/**
 * 
 */
package se.agura.memorial.search.data;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.ejb.FinderException;

import se.agura.memorial.search.api.Grave;

import com.idega.data.IDOEntity;

/**
 * @author is
 *
 */
public interface MalmoSearch extends IDOEntity {
	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#setDatabaseName
	 */
	public void setDatabaseName(String name);

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#getDatabaseName
	 */
	public String getDatabaseName();

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#setAPIDBConnection
	 */
	public void setAPIDBConnection(String name);

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#getAPIDBConnection
	 */
	public String getAPIDBConnection();

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#setFirstName
	 */
	public void setFirstName(String firstName);

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#setLastName
	 */
	public void setLastName(String lastName);

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#setDateOfBirth
	 */
	public void setDateOfBirth(Timestamp dateOfBirth);

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#setDateOfDeath
	 */
	public void setDateOfDeath(Timestamp dateOfDeath);

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#setHomeTown
	 */
	public void setHomeTown(String homeTown);

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#setBurialPlace
	 */
	public void setBurialPlace(String burialPlace);

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#setCemetery
	 */
	public void setCemetery(String cemetery);

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#setDepartment
	 */
	public void setDepartment(String department);

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#setBlock
	 */
	public void setBlock(String block);

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#setGraveNumber
	 */
	public void setGraveNumber(String graveNumber);

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#getGraveyards
	 */
	public List getGraveyards(String database);

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#findGraves
	 */
	public Collection findGraves(String firstName, String lastName,
			String personIdentifier, String dateOfBirthFrom,
			String dateOfBirthTo, String dateOfDeathFrom, String dateOfDeathTo,
			String homeTown, String graveyard, String database)
			throws FinderException;

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#findGrave
	 */
	public Grave findGrave(int gravID, int lopNr, String database);

}
