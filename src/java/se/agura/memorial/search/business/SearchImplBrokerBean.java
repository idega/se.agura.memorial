package se.agura.memorial.search.business;

import se.agura.memorial.search.api.ObituarySearch;

import com.idega.business.IBOServiceBean;

public class SearchImplBrokerBean extends IBOServiceBean  implements SearchImplBroker{

//	private MalmoChurchSearch malmoChurchSearch = null;
//	
//	public SearchImplBrokerBean() {
//		malmoChurchSearch = new MalmoChurchSearch();
//	}
//	
//	public ObituarySearch getSearch() {
//		return malmoChurchSearch; 
//	}
	
	
	private static final int MALMO = 1;
	private static final int LOCAL = 2;
	
	
	private ObituarySearch malmoChurchSearch = null;
	private ObituarySearch localObituarySearch = null;	
	
	public SearchImplBrokerBean() {
		
		// malmoChurchSearch = new MalmoChurchSearch();
		// localObituarySearch = new LocalObituarySearch();		
		
		
		// lame implementation, in future names will be fetched from 
		// GraveDatabaseConn and stored in hashnmap		
		try {
			Class implClass = Class
					.forName("se.agura.memorial.search.impl.MalmoChurchSearch");
			malmoChurchSearch = (ObituarySearch) implClass.newInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		try {
			Class implClass = Class
					.forName("se.agura.memorial.search.impl.LocalObituarySearch");
			localObituarySearch = (ObituarySearch) implClass.newInstance();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
	}	
	
	
	public ObituarySearch getSearch(int databaseConnId) {	
		
		ObituarySearch retImpl = null;
		
		//lame implementation, in future this will me hashmap
		switch(databaseConnId) {
			case MALMO:
				retImpl = malmoChurchSearch;
				break;
			case LOCAL:
				retImpl = localObituarySearch;				
				break;
			default:
				retImpl = null;				
		}
		
		return retImpl;
		
	}
	
}
