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
public class GraveCemeteryHomeImpl extends IDOFactory implements
		GraveCemeteryHome {
	protected Class getEntityInterfaceClass() {
		return GraveCemetery.class;
	}

	public GraveCemetery create() throws javax.ejb.CreateException {
		return (GraveCemetery) super.createIDO();
	}

	public GraveCemetery findByPrimaryKey(Object pk)
			throws javax.ejb.FinderException {
		return (GraveCemetery) super.findByPrimaryKeyIDO(pk);
	}

	public Collection findAll() throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((GraveCemeteryBMPBean) entity).ejbFindAll();
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

}
