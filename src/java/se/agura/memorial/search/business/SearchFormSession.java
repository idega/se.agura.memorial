/**
 * 
 */
package se.agura.memorial.search.business;

import com.idega.business.IBOSession;

/**
 * @author Dainis
 *
 */
public interface SearchFormSession extends IBOSession {
	/**
	 * @see se.agura.memorial.search.business.SearchFormSessionBean#getDatabaseId
	 */
	public Integer getDatabaseId() throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.search.business.SearchFormSessionBean#setDatabaseId
	 */
	public void setDatabaseId(Integer databaseId)
			throws java.rmi.RemoteException;

}
