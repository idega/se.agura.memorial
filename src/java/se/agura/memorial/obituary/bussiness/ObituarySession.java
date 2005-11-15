/**
 * 
 */
package se.agura.memorial.obituary.bussiness;


import org.apache.myfaces.custom.fileupload.UploadedFile;

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
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#getGraveImageFile
	 */
	public UploadedFile getGraveImageFile() throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#setGraveImageFile
	 */
	public void setGraveImageFile(UploadedFile graveImageFile)
			throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#getPersonFullName
	 */
	public String getPersonFullName() throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#setPersonFullName
	 */
	public void setPersonFullName(String personFullName)
			throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#getPersonImageFile
	 */
	public UploadedFile getPersonImageFile() throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#setPersonImageFile
	 */
	public void setPersonImageFile(UploadedFile personImageFile)
			throws java.rmi.RemoteException;

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
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#getGraveImagePath
	 */
	public String getGraveImagePath() throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#setGraveImagePath
	 */
	public void setGraveImagePath(String graveImagePath)
			throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#getPersonImagePath
	 */
	public String getPersonImagePath() throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#setPersonImagePath
	 */
	public void setPersonImagePath(String personImagePath)
			throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#getTmpGraveImageFile
	 */
	public UploadedFile getTmpGraveImageFile() throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#setTmpGraveImageFile
	 */
	public void setTmpGraveImageFile(UploadedFile tmpGraveImageFile)
			throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#getTmpGraveImagePath
	 */
	public String getTmpGraveImagePath() throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#setTmpGraveImagePath
	 */
	public void setTmpGraveImagePath(String tmpGraveImagePath)
			throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#getTmpPersonImageFile
	 */
	public UploadedFile getTmpPersonImageFile() throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#setTmpPersonImageFile
	 */
	public void setTmpPersonImageFile(UploadedFile tmpPersonImageFile)
			throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#getTmpPersonImagePath
	 */
	public String getTmpPersonImagePath() throws java.rmi.RemoteException;

	/**
	 * @see se.agura.memorial.obituary.bussiness.ObituarySessionBean#setTmpPersonImagePath
	 */
	public void setTmpPersonImagePath(String tmpPersonImagePath)
			throws java.rmi.RemoteException;

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
