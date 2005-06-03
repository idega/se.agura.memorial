/**
 * 
 */
package se.agura.memorial.search.data;

import java.sql.Date;


import com.idega.data.IDOEntity;

/**
 * @author is
 *
 */
public interface GraveLocallyStored extends IDOEntity {
	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getColumID
	 */
	public String getColumID();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getFirstName
	 */
	public String getFirstName();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getLastName
	 */
	public String getLastName();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getDateOfBirth
	 */
	public Date getDateOfBirth();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getDateOfDeath
	 */
	public Date getDateOfDeath();

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
	public void setDateOfBirth(Date dateOfBirth);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setDateOfDeath
	 */
	public void setDateOfDeath(Date dateOfDeath);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setHomeTown
	 */
	public void setHomeTown(String homeTown);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setBurialPlace
	 */
	public void setBurialPlace(String burialPlace);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setGraveGraveyard
	 */
	public void setGraveGraveyard(GraveGraveyard graveGraveyard);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getGraveGraveyard
	 */
	public GraveGraveyard getGraveGraveyard(GraveGraveyard graveGraveyard);

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
