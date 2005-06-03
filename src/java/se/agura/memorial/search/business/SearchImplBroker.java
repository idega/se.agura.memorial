/**
 * 
 */
package se.agura.memorial.search.business;



import se.agura.memorial.search.api.ObituarySearch;

import com.idega.business.IBOService;

/**
 * @author Dainis
 *
 */
public interface SearchImplBroker extends IBOService {
	/**
	 * @see se.agura.memorial.search.business.SearchImplBrokerBean#getSearch
	 */
	public ObituarySearch getSearch(int databaseConnId)
			throws java.rmi.RemoteException;

}
