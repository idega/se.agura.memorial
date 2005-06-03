/**
 * 
 */
package se.agura.memorial.search.data;



import com.idega.data.IDOEntity;

/**
 * @author Dainis
 *
 */
public interface GraveGraveyard extends IDOEntity {
	/**
	 * @see se.agura.memorial.search.data.GraveGraveyardBMPBean#setGraveyardName
	 */
	public void setGraveyardName(String name);

	/**
	 * @see se.agura.memorial.search.data.GraveGraveyardBMPBean#getGraveyardName
	 */
	public String getGraveyardName();

	/**
	 * @see se.agura.memorial.search.data.GraveGraveyardBMPBean#setGraveDatabaseConn
	 */
	public void setGraveDatabaseConn(GraveDatabaseConn conn);

	/**
	 * @see se.agura.memorial.search.data.GraveGraveyardBMPBean#getGraveDatabaseConnId
	 */
	public GraveDatabaseConn getGraveDatabaseConnId();

}
