/**
 * 
 */
package se.agura.memorial.search.data;

import java.sql.Timestamp;


import com.idega.data.IDOEntity;

/**
 * @author is
 *
 */
public interface GraveLocallyStored extends IDOEntity {
	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setDatabaseName
	 */
	public void setDatabaseName(String name);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getDatabaseName
	 */
	public String getDatabaseName();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setAPIDBConnection
	 */
	public void setAPIDBConnection(String name);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getAPIDBConnection
	 */
	public String getAPIDBConnection();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setID
	 */
	public void setID(int id);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setFirstName
	 */
	public void setFirstName(String firstName);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setLastName
	 */
	public void setLastName(String lastName);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setDateOfBirth
	 */
	public void setDateOfBirth(Timestamp dateOfBirth);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setDateOfDeath
	 */
	public void setDateOfDeath(Timestamp dateOfDeath);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setHomeTown
	 */
	public void setHomeTown(String homeTown);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setBurialPlace
	 */
	public void setBurialPlace(String burialPlace);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setCemetery
	 */
	public void setCemetery(String cemetery);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setDepartment
	 */
	public void setDepartment(String department);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setBlock
	 */
	public void setBlock(String block);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setGraveNumber
	 */
	public void setGraveNumber(String graveNumber);

}
