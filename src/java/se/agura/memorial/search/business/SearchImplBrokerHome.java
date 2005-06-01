/**
 * 
 */
package se.agura.memorial.search.business;


import com.idega.business.IBOHome;

/**
 * @author Dainis
 *
 */
public interface SearchImplBrokerHome extends IBOHome {
	public SearchImplBroker create() throws javax.ejb.CreateException,
			java.rmi.RemoteException;

}
