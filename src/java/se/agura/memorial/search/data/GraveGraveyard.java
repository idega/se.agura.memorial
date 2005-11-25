/**
 * 
 */
package se.agura.memorial.search.data;


import javax.ejb.FinderException;

import com.idega.data.IDOEntity;

/**
 * @author is
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
	 * @see se.agura.memorial.search.data.GraveGraveyardBMPBean#getGraveDatabaseConn
	 */
	public GraveDatabaseConn getGraveDatabaseConn();

	/**
	 * @see se.agura.memorial.search.data.GraveGraveyardBMPBean#getGraveyardByID
	 */
	public String getGraveyardByID(String graveId) throws FinderException;

}
