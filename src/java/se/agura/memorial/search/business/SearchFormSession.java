/**
 * 
 */
package se.agura.memorial.search.business;

import com.idega.business.IBOService;

/**
 * @author is
 *
 */
public interface SearchFormSession extends IBOService {
	/**
	 * @see se.agura.memorial.search.business.SearchFormSessionBean#getGraveId
	 */
	public String getGraveId() throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.search.business.SearchFormSessionBean#setGraveId
	 */
	public void setGraveId(String graveId) throws java.rmi.RemoteException;

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
