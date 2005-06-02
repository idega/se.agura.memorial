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
public class GraveGraveyardHomeImpl extends IDOFactory implements
		GraveGraveyardHome {
	protected Class getEntityInterfaceClass() {
		return GraveGraveyard.class;
	}

	public GraveGraveyard create() throws javax.ejb.CreateException {
		return (GraveGraveyard) super.createIDO();
	}

	public GraveGraveyard findByPrimaryKey(Object pk)
			throws javax.ejb.FinderException {
		return (GraveGraveyard) super.findByPrimaryKeyIDO(pk);
	}

	public Collection findAll() throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((GraveGraveyardBMPBean) entity)
				.ejbFindAll();
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

}
