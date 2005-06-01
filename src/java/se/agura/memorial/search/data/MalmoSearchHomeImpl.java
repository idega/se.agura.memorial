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
public class MalmoSearchHomeImpl extends IDOFactory implements MalmoSearchHome {
	protected Class getEntityInterfaceClass() {
		return MalmoSearch.class;
	}

	public MalmoSearch create() throws javax.ejb.CreateException {
		return (MalmoSearch) super.createIDO();
	}

	public MalmoSearch findByPrimaryKey(Object pk)
			throws javax.ejb.FinderException {
		return (MalmoSearch) super.findByPrimaryKeyIDO(pk);
	}

	public Collection findAll() throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((MalmoSearchBMPBean) entity).ejbFindAll();
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

	public Collection findGravesTST(String firstName, String lastName,
			String personIdentifier, String dateOfBirthFrom,
			String dateOfBirthTo, String dayOfDeathFrom, String dayOfDeathTo,
			String region, String graveyard, String database)
			throws FinderException {
		com.idega.data.IDOEntity entity = this.idoCheckOutPooledEntity();
		java.util.Collection ids = ((MalmoSearchBMPBean) entity)
				.ejbFindGravesTST(firstName, lastName, personIdentifier,
						dateOfBirthFrom, dateOfBirthTo, dayOfDeathFrom,
						dayOfDeathTo, region, graveyard, database);
		this.idoCheckInPooledEntity(entity);
		return this.getEntityCollectionForPrimaryKeys(ids);
	}

}
