/**
 * 
 */
package se.agura.memorial.search.data;



import com.idega.data.IDOEntity;

/**
 * @author Dainis
 *
 */
public interface GraveDatabaseConn extends IDOEntity {
	/**
	 * @see se.agura.memorial.search.data.GraveDatabaseConnBMPBean#setDatabaseName
	 */
	public void setDatabaseName(String name);

	/**
	 * @see se.agura.memorial.search.data.GraveDatabaseConnBMPBean#getDatabaseName
	 */
	public String getDatabaseName();

	/**
	 * @see se.agura.memorial.search.data.GraveDatabaseConnBMPBean#setAPIDBConnection
	 */
	public void setAPIDBConnection(String name);

	/**
	 * @see se.agura.memorial.search.data.GraveDatabaseConnBMPBean#getAPIDBConnection
	 */
	public String getAPIDBConnection();

	/**
	 * @see se.agura.memorial.search.data.GraveDatabaseConnBMPBean#setDescription
	 */
	public void setDescription(String description);

	/**
	 * @see se.agura.memorial.search.data.GraveDatabaseConnBMPBean#getDescription
	 */
	public String getDescription();

}
