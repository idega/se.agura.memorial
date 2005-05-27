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
public interface GraveCemeteryHome extends IDOHome {
	public GraveCemetery create() throws javax.ejb.CreateException;

	public GraveCemetery findByPrimaryKey(Object pk)
			throws javax.ejb.FinderException;

	/**
	 * @see se.agura.memorial.search.data.GraveCemeteryBMPBean#ejbFindAll
	 */
	public Collection findAll() throws FinderException;

}
