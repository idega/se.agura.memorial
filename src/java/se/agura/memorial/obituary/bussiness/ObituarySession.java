/**
 * 
 */
package se.agura.memorial.obituary.bussiness;

import com.idega.business.IBOSession;

/**
 * @author is
 *
 */
public interface ObituarySession extends IBOSession {
	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#getDatabaseId
	 */
	public Integer getDatabaseId() throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#setDatabaseId
	 */
	public void setDatabaseId(Integer databaseId)
			throws java.rmi.RemoteException;

}
