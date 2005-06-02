/**
 * 
 */
package se.agura.memorial.search.data;

import java.util.Collection;

import javax.ejb.FinderException;

import com.idega.data.IDOHome;

/**
 * @author Dainis
 *
 */
public interface GraveGraveyardHome extends IDOHome {
	public GraveGraveyard create() throws javax.ejb.CreateException;

	public GraveGraveyard findByPrimaryKey(Object pk)
			throws javax.ejb.FinderException;

	/**
	 * @see se.agura.memorial.search.data.GraveGraveyardBMPBean#ejbFindAll
	 */
	public Collection findAll() throws FinderException;

}
