/**
 * 
 */
package se.agura.memorial.obituary.bussiness;

import com.idega.business.IBOHome;

/**
 * @author is
 *
 */
public interface ObituarySessionHome extends IBOHome {
	public ObituarySession create() throws javax.ejb.CreateException,
			java.rmi.RemoteException;

}
