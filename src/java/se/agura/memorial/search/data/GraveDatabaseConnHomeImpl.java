/**
 * 
 */
package se.agura.memorial.search.data;

import java.util.Collection;

import javax.ejb.FinderException;

import com.idega.data.IDOFactory;

/**
 * @author Dainis
 *
 */
public class GraveDatabaseConnHomeImpl extends IDOFactory implements
		GraveDatabaseConnHome {
	protected Class getEntityInterfaceClass() {
		return GraveDatabaseConn.class;
	}

	public GraveDatabaseConn create() throws javax.ejb.CreateException {
		return (GraveDatabaseConn) super.createIDO();
	}

	public GraveDatabaseConn findByPrimaryKey(Object pk)
			throws javax.ejb.FinderException {
		return (GraveDatabaseConn) super.findByPrimaryKeyIDO(pk);
	}

	public Collection findAll() throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((GraveDatabaseConnBMPBean) entity)
				.ejbFindAll();
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	public Collection findAllNameContaining(String s) throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((GraveDatabaseConnBMPBean) entity)
				.ejbFindAllNameContaining(s);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

}
