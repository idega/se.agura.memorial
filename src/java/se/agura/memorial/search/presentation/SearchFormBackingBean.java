package se.agura.memorial.search.presentation;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.model.SelectItem;

import se.agura.memorial.search.api.Grave;
import se.agura.memorial.search.api.Graveyard;
import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.search.business.SearchImplBroker;

import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
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
	
	private static Map graveyards;

	List searchResults = new ArrayList();

	public SearchFormBackingBean() {
		graveyards = new LinkedHashMap();

//		MalmoSearchBMPBean b = new MalmoSearchBMPBean();
//		List listOfGraveyards = b.getGraveyards("test dummy database");

//		for (Iterator it = listOfGraveyards.iterator(); it.hasNext();) {
//			Graveyard g = (Graveyard) it.next();
//			graveyards.put(new Integer(g.getId()).toString(), g);
//		}
		
		//MySession ms2 = (MySession) IBOLookup.getSessionInstance(iwc, MySession.class);
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		
		try {
			SearchImplBroker sib = (SearchImplBroker) IBOLookup.getServiceInstance(iwc, SearchImplBroker.class);
			
			ObituarySearch os = sib.getSearch();
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

	String database;

	private boolean moreThen100ResultsFound;

	public int getSearchResultCount() {
		return searchResults.size();
	}

	public Graveyard getGraveyard() {
		return graveyard;
	}

	public void setGraveyard(Graveyard Graveyard) {
		this.graveyard = Graveyard;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
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
		this.firstName = leftTrimRightTrim(firstName);
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = leftTrimRightTrim(hometown);
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = leftTrimRightTrim(surname);
	}

	/**
	 * searches
	 */
	public String search() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
	
		this.moreThen100ResultsFound = false; 
		
		searchResults = new ArrayList();
		
		
//		try {
			
		IWContext iwc = IWContext.getIWContext(facesContext);
		try {
			SearchImplBroker sib = (SearchImplBroker) IBOLookup
					.getServiceInstance(iwc, SearchImplBroker.class);
			ObituarySearch os = sib.getSearch();

			searchResults = (ArrayList) 
				os.findGraves(
						this.getFirstName(),
						this.getSurname(),
						this.getPersonIdentifier(),
						this.getDateOfBirthFrom(),
						this.getDateOfBirthTo(),
						this.getDateOfDeceaseFrom(),
						this.getDateOfDeceaseTo(),	           
						this.getHometown(),
						this.graveyard != null ? this.graveyard.getBenamning(): null);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}			
			
			
// searchResults = new ArrayList(new MalmoSearchBMPBean().findGraves(
//					this.getFirstName(), this.getSurname(), 
//					this.getPersonIdentifier(), 
//					this.getDateOfBirthFrom(),	this.getDateOfBirthTo(), 
//					this.getDateOfDeceaseFrom(), this.getDateOfDeceaseTo(), 
//					this.getHometown(),
//					this.graveyard != null ? this.graveyard.getBenamning(): null, 
//					null));
			
//		} catch (FinderException e) {
//			
//			facesContext.addMessage(null, new FacesMessage(
//					FacesMessage.SEVERITY_ERROR,
//					"Error occured while searching", // summary
//					"Error occured while searching in database: " + e.getMessage()// detail
//			));
//			
//		}
		
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
		setGraveyard(null);
		
		return "success";

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
					"some description" // desc )
			));

		}

		return list;
	}

	public List getSearchResults() {
		
		if (this.moreThen100ResultsFound) {		
//			FacesContext facesContext = FacesContext.getCurrentInstance();
//			facesContext.addMessage(null, new FacesMessage(
//					FacesMessage.SEVERITY_WARN, "More than 100 hits",
//					"Your search resulted in more than 100 hits – only the first 100 is shown. Please delimit your search"));
		}
		
		return searchResults;
	}
	
	private String leftTrimRightTrim(String s) {
		if (s != null) {
			return s.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
		} else {
			return s;
		}
	}

	public String getPersonIdentifier() {
		return personIdentifier;
	}

	public void setPersonIdentifier(String personIdentifier) {
		this.personIdentifier = personIdentifier;
	}	
	

}
