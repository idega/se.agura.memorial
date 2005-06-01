package se.agura.memorial.search.business;

import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.search.impl.MalmoChurchSearch;

import com.idega.business.IBOServiceBean;

public class SearchImplBrokerBean extends IBOServiceBean  implements SearchImplBroker{

	private MalmoChurchSearch malmoChurchSearch = null;
	
	public SearchImplBrokerBean() {
		malmoChurchSearch = new MalmoChurchSearch();
	}
	
	public ObituarySearch getSearch() {
		return malmoChurchSearch; 
	}
	
	
}
