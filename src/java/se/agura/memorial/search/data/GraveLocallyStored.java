/**
 * 
 */
package se.agura.memorial.search.data;

import java.sql.Date;

import javax.ejb.FinderException;


import com.idega.data.IDOEntity;

/**
 * @author is
 *
 */
public interface GraveLocallyStored extends IDOEntity {
	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setGraveGraveyard
	 */
	public void setGraveGraveyard(GraveGraveyard graveGraveyard);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getGraveGraveyard
	 */
	public GraveGraveyard getGraveGraveyard(GraveGraveyard graveGraveyard);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getGraveId
	 */
	public Integer getGraveId();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setGraveId
	 */
	public void setGraveId(Integer graveId);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getColumID
	 */
	public String getColumID();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getFirstName
	 */
	public String getFirstName();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getHomeTown
	 */
	public String getHomeTown();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getGraveNumber
	 */
	public String getGraveNumber();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getCemetery
	 */
	public String getCemetery();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getDepartment
	 */
	public String getDepartment();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getBurialPlace
	 */
	public String getBurialPlace();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getBlock
	 */
	public String getBlock();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getCountry
	 */
	public String getCountry();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getCommune
	 */
	public String getCommune();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getParish
	 */
	public String getParish();

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
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getDateOfBurial
	 */
	public Date getDateOfBurial();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getGraveGraveyardId
	 */
	public String getGraveGraveyardId();

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setFirstName
	 */
	public void setFirstName(String firstName);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setLastName
	 */
	public void setLastName(String lastName);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setParish
	 */
	public void setParish(String parish);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setCountry
	 */
	public void setCountry(String country);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setCommune
	 */
	public void setCommune(String commune);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setDateOfBirth
	 */
	public void setDateOfBirth(Date dateOfBirth);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setDateOfDeath
	 */
	public void setDateOfDeath(Date dateOfDeath);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setDateOfBurial
	 */
	public void setDateOfBurial(Date dateOfBurial);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setHomeTown
	 */
	public void setHomeTown(String homeTown);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setBurialPlace
	 */
	public void setBurialPlace(String burialPlace);

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#setGraveGraveyardId
	 */
	public void setGraveGraveyardId(Integer id);

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

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#getGraveyardByID
	 */
	public String getGraveyardByID(String graveId) throws FinderException;

}
