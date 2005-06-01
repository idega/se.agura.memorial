/**
 * 
 */
package se.agura.memorial.search.business;


import com.idega.business.IBOHomeImpl;

/**
 * @author Dainis
 *
 */
public class SearchImplBrokerHomeImpl extends IBOHomeImpl implements
		SearchImplBrokerHome {
	protected Class getBeanInterfaceClass() {
		return SearchImplBroker.class;
	}

	public SearchImplBroker create() throws javax.ejb.CreateException {
		return (SearchImplBroker) super.createIBO();
	}

}
