/**
 * 
 */
package se.agura.memorial.search.business;

import com.idega.business.IBOHomeImpl;

/**
 * @author Dainis
 *
 */
public class SearchFormSessionHomeImpl extends IBOHomeImpl implements
		SearchFormSessionHome {
	protected Class getBeanInterfaceClass() {
		return SearchFormSession.class;
	}

	public SearchFormSession create() throws javax.ejb.CreateException {
		return (SearchFormSession) super.createIBO();
	}

}
