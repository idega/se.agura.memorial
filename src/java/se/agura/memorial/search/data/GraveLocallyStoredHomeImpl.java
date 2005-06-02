/**
 * 
 */
package se.agura.memorial.search.data;

import java.util.Collection;

import javax.ejb.FinderException;


import com.idega.data.IDOFactory;

/**
 * @author is
 *
 */
public class GraveLocallyStoredHomeImpl extends IDOFactory implements
		GraveLocallyStoredHome {
	protected Class getEntityInterfaceClass() {
		return GraveLocallyStored.class;
	}

	public GraveLocallyStored create() throws javax.ejb.CreateException {
		return (GraveLocallyStored) super.createIDO();
	}

	public GraveLocallyStored findByPrimaryKey(Object pk)
			throws javax.ejb.FinderException {
		return (GraveLocallyStored) super.findByPrimaryKeyIDO(pk);
	}

	public Collection findAll() throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((GraveLocallyStoredBMPBean) entity)
				.ejbFindAll();
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

}
