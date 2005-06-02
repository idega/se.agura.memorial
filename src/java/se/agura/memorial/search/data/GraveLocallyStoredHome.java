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
public interface GraveLocallyStoredHome extends IDOHome {
	public GraveLocallyStored create() throws javax.ejb.CreateException;

	public GraveLocallyStored findByPrimaryKey(Object pk)
			throws javax.ejb.FinderException;

	/**
	 * @see se.agura.memorial.search.data.GraveLocallyStoredBMPBean#ejbFindAll
	 */
	public Collection findAll() throws FinderException;

}
