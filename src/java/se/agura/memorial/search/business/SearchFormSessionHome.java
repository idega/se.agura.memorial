/**
 * 
 */
package se.agura.memorial.search.business;

import com.idega.business.IBOHome;

/**
 * @author is
 *
 */
public interface SearchFormSessionHome extends IBOHome {
	public SearchFormSession create() throws javax.ejb.CreateException,
			java.rmi.RemoteException;

}
