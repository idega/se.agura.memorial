package se.agura.memorial.search.business;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.ejb.FinderException;

import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.search.data.GraveDatabaseConn;
import se.agura.memorial.search.data.GraveDatabaseConnHome;

import com.idega.business.IBOServiceBean;
import com.idega.data.IDOLookup;
import com.idega.data.IDOLookupException;

public class SearchImplBrokerBean extends IBOServiceBean  implements SearchImplBroker{
	
	private Map map = new HashMap();
	
	public SearchImplBrokerBean() {
	
		try {
			Collection c = getAllDatabaseConnections();
			
			for (Iterator iter = c.iterator(); iter.hasNext();) {
				GraveDatabaseConn gdc = (GraveDatabaseConn) iter.next();
				
				try {
					Class implClass = Class.forName(gdc.getAPIDBConnection());
					ObituarySearch os = (ObituarySearch) implClass.newInstance();
					map.put(gdc.getPrimaryKey(), os);					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}	
				
			}			
			
		} catch (IDOLookupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FinderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	    
		
	}	
	
	
	public ObituarySearch getSearch(int databaseConnId) {	

		return (ObituarySearch) map.get(new Integer(databaseConnId));
		
	}
	
	private Collection getAllDatabaseConnections()throws FinderException,IDOLookupException {
		GraveDatabaseConnHome gdch = (GraveDatabaseConnHome) IDOLookup.getHome(GraveDatabaseConn.class);
		Collection coll = gdch.findAll();		
		return coll;		
	}
	
}
