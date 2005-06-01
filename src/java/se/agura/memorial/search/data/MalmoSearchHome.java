/**
 * 
 */
package se.agura.memorial.search.data;

import java.util.Collection;

import javax.ejb.FinderException;


import com.idega.data.IDOHome;

/**
 * @author is
 *
 */
public interface MalmoSearchHome extends IDOHome {
	public MalmoSearch create() throws javax.ejb.CreateException;

	public MalmoSearch findByPrimaryKey(Object pk)
			throws javax.ejb.FinderException;

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#ejbFindAll
	 */
	public Collection findAll() throws FinderException;

	/**
	 * @see se.agura.memorial.search.data.MalmoSearchBMPBean#ejbFindGravesTST
	 */
	public Collection findGravesTST(String firstName, String lastName,
			String personIdentifier, String dateOfBirthFrom,
			String dateOfBirthTo, String dayOfDeathFrom, String dayOfDeathTo,
			String region, String graveyard, String database)
			throws FinderException;

}
