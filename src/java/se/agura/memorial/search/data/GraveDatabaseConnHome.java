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
public interface GraveDatabaseConnHome extends IDOHome {
	public GraveDatabaseConn create() throws javax.ejb.CreateException;

	public GraveDatabaseConn findByPrimaryKey(Object pk)
			throws javax.ejb.FinderException;

	/**
	 * @see se.agura.memorial.search.data.GraveDatabaseConnBMPBean#ejbFindAll
	 */
	public Collection findAll() throws FinderException;

	/**
	 * @see se.agura.memorial.search.data.GraveDatabaseConnBMPBean#ejbFindAllNameContaining
	 */
	public Collection findAllNameContaining(String s) throws FinderException;

}
