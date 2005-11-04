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
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#getObituaryText
	 */
	public String getObituaryText() throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#setObituaryText
	 */
	public void setObituaryText(String obituaryText)
			throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#getTmpObituaryText
	 */
	public String getTmpObituaryText() throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#setTmpObituaryText
	 */
	public void setTmpObituaryText(String tmpObituaryText)
			throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#getGraveId
	 */
	public String getGraveId() throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#setGraveId
	 */
	public void setGraveId(String graveId) throws java.rmi.RemoteException;

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
