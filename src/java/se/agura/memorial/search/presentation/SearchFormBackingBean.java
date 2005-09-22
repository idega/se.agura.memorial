package se.agura.memorial.search.presentation;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.FinderException;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import se.agura.memorial.search.api.CustomMemorialDate;
import se.agura.memorial.search.api.Grave;
import se.agura.memorial.search.api.Graveyard;
import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.search.business.SearchFormSessionBean;
import se.agura.memorial.search.business.SearchImplBroker;
import se.agura.memorial.search.data.GraveDatabaseConn;
import se.agura.memorial.search.data.GraveDatabaseConnHome;
import se.agura.memorial.util.MemorialHeplInfo;
import se.agura.memorial.util.Utility;

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

//	private CustomMemorialDate dateOfBirthTo= null;
//	private CustomMemorialDate dateOfBirthFrom= null;
//
//	private CustomMemorialDate dateOfDeceaseTo= null;
//	private CustomMemorialDate dateOfDeceaseFrom= null;

	private Integer dateOfBirthFrom_year= null;
	private Integer dateOfBirthFrom_month = new Integer(0);
	private Integer dateOfBirthFrom_day = new Integer(0);

	private Integer dateOfBirthTo_year = null;
	private Integer dateOfBirthTo_month = new Integer(0);
	private Integer dateOfBirthTo_day = new Integer(0);
	
	private Integer dateOfDeceaseFrom_year = null;
	private Integer dateOfDeceaseFrom_month = new Integer(0);
	private Integer dateOfDeceaseFrom_day = new Integer(0);
	
	private Integer dateOfDeceaseTo_year = null;
	private Integer dateOfDeceaseTo_month = new Integer(0);
	private Integer dateOfDeceaseTo_day = new Integer(0);
	
	private String hometown;	
	private String personIdentifier;
	
	private Graveyard graveyard;
	private boolean graveyardSetToNull = false;
	private static Map graveyards;
	
	private Integer databaseId = new Integer(1);
	private Integer monthId = new Integer(0);	
	private Integer yearId = new Integer(1950);	
	private Integer dayId = new Integer(0);	

	List searchResults = new ArrayList();
	List helpTopics = new ArrayList();

	
	private SearchFormSessionBean searchFormSessionBean = null;

	public SearchFormBackingBean() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		try {
			searchFormSessionBean = (SearchFormSessionBean) IBOLookup.getSessionInstance(iwc, SearchFormSessionBean.class);
		} catch (IBOLookupException e) {
			e.printStackTrace();
		}
		
		searchFormSessionBean.setDatabaseId(this.databaseId); 
				
		initGraveyards();
	}
	
	public List getHelpTopics() {
		return helpTopics;
	}
	

	public void setHelpTopics(List helpTopics) {
		this.helpTopics = helpTopics;
	}
	

	public Integer getDateOfBirthFrom_day() {
		return dateOfBirthFrom_day;
	}
	

	public void setDateOfBirthFrom_day(Integer dateOfBirthFrom_day) {
		this.dateOfBirthFrom_day = dateOfBirthFrom_day;
	}
	

	public Integer getDateOfBirthFrom_month() {
		return dateOfBirthFrom_month;
	}
	

	public void setDateOfBirthFrom_month(Integer dateOfBirthFrom_month) {
		this.dateOfBirthFrom_month = dateOfBirthFrom_month;
	}
	

	public Integer getDateOfBirthFrom_year() {
		return dateOfBirthFrom_year;
	}
	

	public void setDateOfBirthFrom_year(Integer dateOfBirthFrom_year) {
		this.dateOfBirthFrom_year = dateOfBirthFrom_year;
	}
	

	public Integer getDateOfBirthTo_day() {
		return dateOfBirthTo_day;
	}
	

	public void setDateOfBirthTo_day(Integer dateOfBirthTo_day) {
		this.dateOfBirthTo_day = dateOfBirthTo_day;
	}
	

	public Integer getDateOfBirthTo_month() {
		return dateOfBirthTo_month;
	}
	

	public void setDateOfBirthTo_month(Integer dateOfBirthTo_month) {
		this.dateOfBirthTo_month = dateOfBirthTo_month;
	}
	

	public Integer getDateOfBirthTo_year() {
		return dateOfBirthTo_year;
	}
	

	public void setDateOfBirthTo_year(Integer dateOfBirthTo_year) {
		this.dateOfBirthTo_year = dateOfBirthTo_year;
	}
	

	public Integer getDateOfDeceaseFrom_day() {
		return dateOfDeceaseFrom_day;
	}
	

	public void setDateOfDeceaseFrom_day(Integer dateOfDeceaseFrom_day) {
		this.dateOfDeceaseFrom_day = dateOfDeceaseFrom_day;
	}
	

	public Integer getDateOfDeceaseFrom_month() {
		return dateOfDeceaseFrom_month;
	}
	

	public void setDateOfDeceaseFrom_month(Integer dateOfDeceaseFrom_month) {
		this.dateOfDeceaseFrom_month = dateOfDeceaseFrom_month;
	}
	

	public Integer getDateOfDeceaseFrom_year() {
		return dateOfDeceaseFrom_year;
	}
	

	public void setDateOfDeceaseFrom_year(Integer dateOfDeceaseFrom_year) {
		this.dateOfDeceaseFrom_year = dateOfDeceaseFrom_year;
	}
	

	public Integer getDateOfDeceaseTo_day() {
		return dateOfDeceaseTo_day;
	}
	

	public void setDateOfDeceaseTo_day(Integer dateOfDeceaseTo_day) {
		this.dateOfDeceaseTo_day = dateOfDeceaseTo_day;
	}
	

	public Integer getDateOfDeceaseTo_month() {
		return dateOfDeceaseTo_month;
	}
	

	public void setDateOfDeceaseTo_month(Integer dateOfDeceaseTo_month) {
		this.dateOfDeceaseTo_month = dateOfDeceaseTo_month;
	}
	

	public Integer getDateOfDeceaseTo_year() {
		return dateOfDeceaseTo_year;
	}
	

	public void setDateOfDeceaseTo_year(Integer dateOfDeceaseTo_year) {
		this.dateOfDeceaseTo_year = dateOfDeceaseTo_year;
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
			e.printStackTrace();
		} catch (RemoteException e) {
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
	
	public Integer getYearId() {
		return yearId;
	}
	
	public void setYearId(Integer yearId) {
		this.yearId = yearId;
	}

	
	public Integer getMonthId() {
		return monthId;
	}
	
	public void setMonthId(Integer monthId) {
		this.monthId = monthId;
	}

	public Integer getDayId() {
		return dayId;
	}
	
	public void setDayId(Integer dayId) {
		this.dayId = dayId;
	}

	
	public void setDatabaseId(Integer databaseId) {
		this.databaseId = databaseId;		
		searchFormSessionBean.setDatabaseId(this.databaseId); 
	}

	public CustomMemorialDate getDateOfBirthFrom() {
		
		
		if ((dateOfBirthFrom_year == null ) 
			&& (dateOfBirthFrom_month == null )
            && (dateOfBirthFrom_day == null ) )return null;
		
		CustomMemorialDate date = null;
		Integer year=null,month=null,day=null;

		
		if ((dateOfBirthFrom_year != null )&& (dateOfBirthFrom_year.intValue()!=0)){
			if ((dateOfBirthFrom_year.intValue()>1500) && (dateOfBirthFrom_year.intValue()<2050) ) year = dateOfBirthFrom_year; 
		}
		
		if ((dateOfBirthFrom_month != null )&& (dateOfBirthFrom_month.intValue()!=0)){
			 if (dateOfBirthFrom_month.intValue() < 12 ) month = dateOfBirthFrom_month;
		}

		if ((dateOfBirthFrom_day != null ) &&(dateOfBirthFrom_month.intValue()!=0) && (dateOfBirthFrom_day.intValue()!=0)){
			if (dateOfBirthFrom_day.intValue()<32) day = dateOfBirthFrom_day;
		}	
		date = 	new CustomMemorialDate(year,month,day);	
		return date;		
	}

	public CustomMemorialDate getDateOfBirthTo() {
		
		
		if ((dateOfBirthTo_year == null ) 
			&& (dateOfBirthTo_month == null )
            && (dateOfBirthTo_day == null ) )return null;
		
		CustomMemorialDate date = null;
		Integer year=null,month=null,day=null;

		
		if ((dateOfBirthTo_year != null )&& (dateOfBirthTo_year.intValue()!=0)){
			if ((dateOfBirthTo_year.intValue()>1500) && (dateOfBirthTo_year.intValue()<2050) ) year = dateOfBirthTo_year; 
		}
		
		if ((dateOfBirthTo_month != null )&& (dateOfBirthTo_month.intValue()!=0)){
			 if (dateOfBirthTo_month.intValue() < 12 ) month = dateOfBirthTo_month;
		}

		if ((dateOfBirthTo_day != null ) &&(dateOfBirthTo_month.intValue()!=0) && (dateOfBirthTo_day.intValue()!=0)){
			if (dateOfBirthTo_day.intValue()<32) day = dateOfBirthTo_day;
		}	
		date = 	new CustomMemorialDate(year,month,day);	
		return date;		
	}

	public CustomMemorialDate getDateOfDeceaseFrom() {
		
		if ((dateOfDeceaseFrom_year == null ) 
			&& (dateOfDeceaseFrom_month == null )
            && (dateOfDeceaseFrom_day == null ) )return null;
		
		CustomMemorialDate date = null;
		Integer year=null,month=null,day=null;

		
		if ((dateOfDeceaseFrom_year != null )&& (dateOfDeceaseFrom_year.intValue()!=0)){
			if ((dateOfDeceaseFrom_year.intValue()>1500) && (dateOfDeceaseFrom_year.intValue()<2050) ) year = dateOfDeceaseFrom_year; 
		}
		
		if ((dateOfDeceaseFrom_month != null )&& (dateOfDeceaseFrom_month.intValue()!=0)){
			 if (dateOfDeceaseFrom_month.intValue() < 12 ) month = dateOfDeceaseFrom_month;
		}

		if ((dateOfDeceaseFrom_day != null ) &&(dateOfDeceaseFrom_month.intValue()!=0) && (dateOfDeceaseFrom_day.intValue()!=0)){
			if (dateOfDeceaseFrom_day.intValue()<32) day = dateOfDeceaseFrom_day;
		}	
		date = 	new CustomMemorialDate(year,month,day);	
		return date;		
	}


	public CustomMemorialDate getDateOfDeceaseTo() {
		
		if ((dateOfDeceaseTo_year == null ) 
			&& (dateOfDeceaseTo_month == null )
            && (dateOfDeceaseTo_day == null ) )return null;
		
		CustomMemorialDate date = null;
		Integer year=null,month=null,day=null;

		
		if ((dateOfDeceaseTo_year != null )&& (dateOfDeceaseTo_year.intValue()!=0)){
			if ((dateOfDeceaseTo_year.intValue()>1500) && (dateOfDeceaseTo_year.intValue()<2050) ) year = dateOfDeceaseTo_year; 
		}
		
		if ((dateOfDeceaseTo_month != null )&& (dateOfDeceaseTo_month.intValue()!=0)){
			 if (dateOfDeceaseTo_month.intValue() < 12 ) month = dateOfDeceaseTo_month;
		}

		if ((dateOfDeceaseTo_day != null ) &&(dateOfDeceaseTo_month.intValue()!=0) && (dateOfDeceaseTo_day.intValue()!=0)){
			if (dateOfDeceaseTo_day.intValue()<32) day = dateOfDeceaseTo_day;
		}	
		date = 	new CustomMemorialDate(year,month,day);	
		return date;		
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
	
	public List getMonthSelectItemList() {

		List l = new ArrayList();

		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		IWContext iwc = IWContext.getIWContext(facesContext);
		
        IWContext iwContext = IWContext.getIWContext(facesContext);
		IWBundle bundle = iwContext.getIWMainApplication().getBundle(IW_BUNDLE_IDENTIFIER);

				l.add(new SelectItem("0",bundle.getValueBinding("Month_00").getValue(iwc).toString()));
				l.add(new SelectItem("1",bundle.getValueBinding("Month_01").getValue(iwc).toString()));
				l.add(new SelectItem("2",bundle.getValueBinding("Month_02").getValue(iwc).toString()));
				l.add(new SelectItem("3",bundle.getValueBinding("Month_03").getValue(iwc).toString()));
				l.add(new SelectItem("4",bundle.getValueBinding("Month_04").getValue(iwc).toString()));
				l.add(new SelectItem("5",bundle.getValueBinding("Month_05").getValue(iwc).toString()));
				l.add(new SelectItem("6",bundle.getValueBinding("Month_06").getValue(iwc).toString()));
				l.add(new SelectItem("7",bundle.getValueBinding("Month_07").getValue(iwc).toString()));
				l.add(new SelectItem("8",bundle.getValueBinding("Month_08").getValue(iwc).toString()));
				l.add(new SelectItem("9",bundle.getValueBinding("Month_09").getValue(iwc).toString()));
				l.add(new SelectItem("10",bundle.getValueBinding("Month_10").getValue(iwc).toString()));
				l.add(new SelectItem("11",bundle.getValueBinding("Month_11").getValue(iwc).toString()));
				l.add(new SelectItem("12",bundle.getValueBinding("Month_12").getValue(iwc).toString()));

				

				
	
		
		return l;
		
	}	

	public List getDateOfBirthFromDaySelectItemList() {
		        
		List l = new ArrayList();
		Calendar cal = null;
		if (dateOfBirthFrom_year != null ){
			cal = new GregorianCalendar(this.dateOfBirthFrom_year.intValue(), this.dateOfBirthFrom_month.intValue()-1, 1);
		}	
		else{
			cal = new GregorianCalendar(2001, this.dateOfBirthFrom_month.intValue()-1, 1);
		}
		
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
			
		l.add(new SelectItem("0","DD"));
			 
        for (int i=1;i<=days;i++) l.add(new SelectItem(String.valueOf(i),String.valueOf(i) ) );
		
		return l;
		
	}	

	public List getDateOfDeceaseToDaySelectItemList() {

		List l = new ArrayList();
		Calendar cal = null;
		if (dateOfDeceaseTo_year != null ){
			cal = new GregorianCalendar(this.dateOfDeceaseTo_year.intValue(), this.dateOfDeceaseTo_month.intValue()-1, 1);
		}	
		else{
			cal = new GregorianCalendar(2001, this.dateOfDeceaseTo_month.intValue()-1, 1);
		}
		
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
			
		l.add(new SelectItem("0","DD"));
			 
        for (int i=1;i<=days;i++) l.add(new SelectItem(String.valueOf(i),String.valueOf(i) ) );
		
		return l;
		
	}	
	
	public List getDateOfDeceaseFromDaySelectItemList() {

		List l = new ArrayList();
		Calendar cal = null;
		if (dateOfDeceaseFrom_year != null ){
			cal = new GregorianCalendar(this.dateOfDeceaseFrom_year.intValue(), this.dateOfDeceaseFrom_month.intValue()-1, 1);
		}	
		else{
			cal = new GregorianCalendar(2001, this.dateOfDeceaseFrom_month.intValue()-1, 1);
		}
		
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
			
		l.add(new SelectItem("0","DD"));
			 
        for (int i=1;i<=days;i++) l.add(new SelectItem(String.valueOf(i),String.valueOf(i) ) );
		
		return l;
		
	}		

	public List getDateOfBirthToDaySelectItemList() {

		List l = new ArrayList();
		Calendar cal = null;
		if (dateOfBirthTo_year != null ){
			cal = new GregorianCalendar(this.dateOfBirthTo_year.intValue(), this.dateOfBirthTo_month.intValue()-1, 1);
		}	
		else{
			cal = new GregorianCalendar(2001, this.dateOfBirthTo_month.intValue()-1, 1);
		}
		
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
			
		l.add(new SelectItem("0","DD"));
			 
        for (int i=1;i<=days;i++) l.add(new SelectItem(String.valueOf(i),String.valueOf(i) ) );
		
		return l;
		
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
			e.printStackTrace();
		} catch (FinderException e) {
			e.printStackTrace();
		}	
		
		return l;
		
	}	
	


	
    public String onClick()
    {        
        
        boolean result = true;
        

		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		IWContext iwc = IWContext.getIWContext(facesContext);
		
        if(result)
            return "success";
        else
            return "failure";
    }

	
	
	/**
	 * searches
	 */	
	public String search() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
	
		this.moreThen100ResultsFound = false; 
		
		searchResults = new ArrayList();	
		helpTopics = null;
		
		IWContext iwc = IWContext.getIWContext(facesContext);
		try {
			SearchImplBroker sib = (SearchImplBroker) IBOLookup.getServiceInstance(iwc, SearchImplBroker.class);
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
			e.printStackTrace();
		}			
		if (searchResults == null) searchResults = new ArrayList();  
	
		if (searchResults.size() > 99) {
			
			this.moreThen100ResultsFound = true; 
			
			
	        
	        IWContext iwContext = IWContext.getIWContext(facesContext);
			IWBundle bundle = iwContext.getIWMainApplication().getBundle(IW_BUNDLE_IDENTIFIER);
			ValueBinding vbSummary = bundle.getValueBinding("help_button"); 
			ValueBinding vbDetail = bundle.getValueBinding("messages.more_than_100_hits_detail");
			//ValueBinding vbDetail = bundle.getValueBinding("help_button");			
			
			facesContext.addMessage(null, new FacesMessage(
					FacesMessage.SEVERITY_ERROR,// SEVERITY_WARN, 
					vbSummary.getValue(facesContext).toString(), // summary
					vbDetail.getValue(facesContext).toString()// detail
					));
			
						
			//remove the last element
			int count = 0;
			for (Iterator it = searchResults.iterator(); it.hasNext();) {				
				count++;				
				Grave g = (Grave) it.next();
				if (count > 99) it.remove();
			}
			
		}
		return "success";
	}

	public String helpinfo() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
	        
		searchResults = null;
		helpTopics = new ArrayList();
		
		List mList = new ArrayList();
		mList.add("info 1");
		mList.add("info 2");		
		mList.add("info 3");		
		mList.add("info 4");		
		
		MemorialHeplInfo mhi = new MemorialHeplInfo();
		mhi.setInfo(mList);
		mhi.setTitle("TOPIC 1");
		
		helpTopics = (ArrayList) mList;//mhi.getInfo();
		
//		helpTopics.add("TOPIC 1");
//		helpTopics.add("TOPIC 2");		
		
//        IWContext iwContext = IWContext.getIWContext(facesContext);
//		IWBundle bundle = iwContext.getIWMainApplication().getBundle(IW_BUNDLE_IDENTIFIER);
//		ValueBinding vbSummary = bundle.getValueBinding("help_button"); 
//		ValueBinding vbDetail = bundle.getValueBinding("messages.more_than_100_hits_detail");
//		facesContext.addMessage(null, new FacesMessage(
//					FacesMessage.SEVERITY_INFO, 
//					vbSummary.getValue(facesContext).toString(), // summary
//					vbDetail.getValue(facesContext).toString()// detail
//					));
//			
//		facesContext.addMessage(null, new FacesMessage(
//				FacesMessage.SEVERITY_INFO, 
//				vbSummary.getValue(facesContext).toString(), // summary
//				vbDetail.getValue(facesContext).toString()// detail
//				));

		return "success";
	}



	/**
	 * clear search form
	 */
	public String clear() {
		
		helpTopics = null;
		setFirstName(null);
		setSurname(null);
		setDateOfBirthFrom_year(null);
		setDateOfBirthFrom_month(Integer.valueOf("0"));
		setDateOfBirthFrom_day(Integer.valueOf("0"));
		
		setDateOfBirthTo_year(null);
		setDateOfBirthTo_month(Integer.valueOf("0"));
		setDateOfBirthTo_day(Integer.valueOf("0"));

		setDateOfDeceaseFrom_year(null);
		setDateOfDeceaseFrom_month(Integer.valueOf("0"));
		setDateOfDeceaseFrom_day(Integer.valueOf("0"));
		
		setDateOfDeceaseTo_year(null);
		setDateOfDeceaseTo_month(Integer.valueOf("0"));
		setDateOfDeceaseTo_day(Integer.valueOf("0"));
		
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
