package se.agura.memorial.search.presentation;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.FinderException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import se.agura.memorial.search.api.Grave;
import se.agura.memorial.search.api.Graveyard;
import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.search.business.SearchFormSessionBean;
import se.agura.memorial.search.business.SearchImplBroker;
import se.agura.memorial.search.data.GraveDatabaseConn;
import se.agura.memorial.search.data.GraveDatabaseConnHome;
import se.agura.memorial.search.util.Utility;

import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.data.IDOLookup;
import com.idega.data.IDOLookupException;
import com.idega.idegaweb.IWBundle;
import com.idega.presentation.IWContext;

public class SearchFormBackingBean {
	
	private static final String IW_BUNDLE_IDENTIFIER = "se.agura.memorial";
	
	private String firstName;
	private String surname;
	private String dateOfBirthFrom;
	private String dateOfBirthTo;
	private String dateOfDeceaseFrom;
	private String dateOfDeceaseTo;
	private String hometown;	
	private String personIdentifier;
	
	private Graveyard graveyard;
	private boolean graveyardSetToNull = false;
	private static Map graveyards;
	
	private Integer databaseId = new Integer(1);

	List searchResults = new ArrayList();
	
	private SearchFormSessionBean searchFormSessionBean = null;

	public SearchFormBackingBean() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		try {
			searchFormSessionBean = (SearchFormSessionBean) IBOLookup.getSessionInstance(iwc, SearchFormSessionBean.class);
		} catch (IBOLookupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		searchFormSessionBean.setDatabaseId(this.databaseId); 
				
		initGraveyards();
	}
	
	private void initGraveyards() {
		
		graveyards = new LinkedHashMap();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		
		try {
			SearchImplBroker sib = (SearchImplBroker) IBOLookup.getServiceInstance(iwc, SearchImplBroker.class);
			
			ObituarySearch os = sib.getSearch(this.databaseId.intValue());
			List listOfGraveyards = (List) os.getGraveyards();
			
			for (Iterator it = listOfGraveyards.iterator(); it.hasNext();) {
				Graveyard g = (Graveyard) it.next();
				graveyards.put(new Integer(g.getId()).toString(), g);
			}
			
		} catch (IBOLookupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}		
	}
	

	private boolean moreThen100ResultsFound;

	

	public int getSearchResultCount() {
		if  (searchResults == null) return 0; 
		return searchResults.size();
	}

	public Graveyard getGraveyard() {
		return graveyard;
	}

	public void setGraveyard(Graveyard graveyard) {
		if (graveyardSetToNull) {
			this.graveyard = null;
			this.graveyardSetToNull = false;
			return;
		}
		this.graveyard = graveyard;
	}

	public Integer getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(Integer databaseId) {
		this.databaseId = databaseId;		
		searchFormSessionBean.setDatabaseId(this.databaseId); 
	}

	public String getDateOfBirthFrom() {
		return dateOfBirthFrom;
	}

	public void setDateOfBirthFrom(String dateOfBirthFrom) {
		this.dateOfBirthFrom = dateOfBirthFrom;
	}

	public String getDateOfBirthTo() {
		return dateOfBirthTo;
	}

	public void setDateOfBirthTo(String dateOfBirthTo) {
		this.dateOfBirthTo = dateOfBirthTo;
	}

	public String getDateOfDeceaseFrom() {
		return dateOfDeceaseFrom;
	}

	public void setDateOfDeceaseFrom(String dateOfDeceaseFrom) {
		this.dateOfDeceaseFrom = dateOfDeceaseFrom;
	}

	public String getDateOfDeceaseTo() {
		return dateOfDeceaseTo;
	}

	public void setDateOfDeceaseTo(String dateOfDeceaseTo) {
		this.dateOfDeceaseTo = dateOfDeceaseTo;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = Utility.leftTrimRightTrim(firstName);
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = Utility.leftTrimRightTrim(hometown);
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = Utility.leftTrimRightTrim(surname);
	}

	// this is needed for converter
	public Map getGraveyards() {
		return graveyards;
	}

	// we must convert map to list of items or else
	// keys and values are swapped in HTML select if map is passed to
	// jsf:selectItems tag
	public List getGraveyardsItems() {
		List list = new ArrayList();
		Graveyard g;

		Iterator it = graveyards.keySet().iterator();
		while (it.hasNext()) {
			Object key = it.next();

			g = (Graveyard) graveyards.get(key);
			list.add(new SelectItem(new Integer(g.getId()).toString(), // value
					g.getBenamning(), // label
					null // desc )
			));
		}

		return list;
	}

	public List getSearchResults() {			
		return searchResults;
	}	


	public void setPersonIdentifier(String personIdentifier) {
		this.personIdentifier = personIdentifier;
	}
	
	public String getPersonIdentifier() {
		return personIdentifier;
	}
		
	private Collection getAllDatabaseConnections() throws FinderException,IDOLookupException {

		GraveDatabaseConnHome gdch = (GraveDatabaseConnHome) IDOLookup.getHome(GraveDatabaseConn.class);
		Collection coll = gdch.findAll();		
		return coll;	
		
	} 	
	
	public List getDatabaseSelectItemList() {
		List l = new ArrayList();
		
		try {
			Collection c = getAllDatabaseConnections();
			
			for (Iterator iter = c.iterator(); iter.hasNext();) {
				GraveDatabaseConn gdc = (GraveDatabaseConn) iter.next();
				l.add(new SelectItem(gdc.getPrimaryKey(), // java.lang.Integer
						gdc.getDatabaseName()));
			}			
			
		} catch (IDOLookupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FinderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		return l;
		
	}	
	

	/**
	 * searches
	 */
	public String search() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
	
		this.moreThen100ResultsFound = false; 
		
		searchResults = new ArrayList();	
	
			
		IWContext iwc = IWContext.getIWContext(facesContext);
		try {
			SearchImplBroker sib = (SearchImplBroker) IBOLookup
					.getServiceInstance(iwc, SearchImplBroker.class);
			ObituarySearch os = sib.getSearch(getDatabaseId().intValue());
	
			searchResults = (ArrayList) 
				os.findGraves(
						getFirstName(),
						getSurname(),
						getPersonIdentifier(),
						getDateOfBirthFrom(),
						getDateOfBirthTo(),
						getDateOfDeceaseFrom(),
						getDateOfDeceaseTo(),	           
						getHometown(),
						this.graveyard != null ? this.graveyard.getBenamning(): null);
	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}			
		if (searchResults == null) searchResults = new ArrayList();  
	
		if (searchResults.size() > 100) {
			
			this.moreThen100ResultsFound = true; 
			
			
	        
	        IWContext iwContext = IWContext.getIWContext(facesContext);
			IWBundle bundle = iwContext.getIWMainApplication().getBundle(IW_BUNDLE_IDENTIFIER);
			ValueBinding vbSummary = bundle.getValueBinding("messages.more_than_100_hits_summary"); 
			ValueBinding vbDetail = bundle.getValueBinding("messages.more_than_100_hits_detail");  
			
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_WARN, 
					vbSummary.getValue(facesContext).toString(), // summary
					vbDetail.getValue(facesContext).toString()// detail
					));			
						
			//remove the last element
			int count = 0;
			for (Iterator it = searchResults.iterator(); it.hasNext();) {				
				count++;				
				Grave g = (Grave) it.next();
				if (count > 100) it.remove();
			}
			
		}		
	
		return "success";
	}



	/**
	 * clear search form
	 */
	public String clear() {
	
		setFirstName(null);
		setSurname(null);
		setDateOfBirthFrom(null);
		setDateOfBirthTo(null);
		setDateOfDeceaseFrom(null);
		setDateOfDeceaseTo(null);
		setPersonIdentifier(null);
		setHometown(null);		
		setGraveyard(null);
		
		searchResults = new ArrayList();
		return "success";
	
	}



	private int stringToInt(String s) {
		
		if (s == null) {
			return 0;			
		}		

		try {
			int i = Integer.parseInt(s);
			return i;
		} catch (NumberFormatException nfe) {
			return 0;
		}

	}
	
	
	public void changeDatabase(ValueChangeEvent vce) {
		setDatabaseId((Integer)vce.getNewValue());	
		
		graveyard = null; //this won't work	if we don't use the following code	
		// a little hack, actually right way to do that would be
		// process this event in right phase
		// I just dont know how to do that _yet_
		graveyardSetToNull = true;
		
		initGraveyards();		
		searchResults = new ArrayList();
	}
	

}
