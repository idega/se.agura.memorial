package se.agura.memorial.search.presentation;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import se.agura.memorial.obituary.bussiness.ObituarySessionBean;
import se.agura.memorial.search.api.CustomMemorialDate;
import se.agura.memorial.search.api.Graveyard;
import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.search.business.SearchImplBroker;
import se.agura.memorial.search.data.GraveDatabaseConn;
import se.agura.memorial.search.data.GraveDatabaseConnHome;
import se.agura.memorial.search.data.GraveGraveyard;
import se.agura.memorial.search.data.GraveGraveyardHome;
import se.agura.memorial.search.data.GraveLocallyStored;
import se.agura.memorial.search.data.GraveLocallyStoredHome;
import se.agura.memorial.util.Utility;

import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.data.IDOLookup;
import com.idega.data.IDOLookupException;
import com.idega.presentation.IWContext;

public class NewPersonBackingBean {
	String firstName;
	String lastName;
	String dateOfBirth = null;
	String dateOfDeath = null;
	String dateOfBurial = null;

	String hometown;
	String burialPlace;
	
	public final Integer LOCAL_DATABASE_CONNECTION_PRIMARY_KEY = new Integer(2);
	
	private Integer dateOfBirth_year = null;
	private Integer dateOfBirth_month = new Integer(0);
	private Integer dateOfBirth_day = new Integer(0);

	private Integer dateOfDeath_year = null;
	private Integer dateOfDeath_month = new Integer(0);
	private Integer dateOfDeath_day = new Integer(0);
	
	private Integer dateOfBurial_year = null;
	private Integer dateOfBurial_month = new Integer(0);
	private Integer dateOfBurial_day = new Integer(0);

	private ObituarySessionBean obituarySessionBean = null;	
	String newGraveyard;
	Integer existingGraveyardId;
	
	String department;
	String block;
	String parish;
	String commune;
	String country;	
	String graveNumber;
	Boolean createObituaryAfterSaving;
	
	public NewPersonBackingBean() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		try {
			obituarySessionBean = (ObituarySessionBean) IBOLookup.getSessionInstance(iwc, ObituarySessionBean.class);
		} catch (IBOLookupException e) {
			e.printStackTrace();
		}
		
		  
	}	
	
	public String getBlock() {
		return block;
	}
	public void setBlock(String block) {
		this.block = block;
	}
	public String getBurialPlace() {
		return burialPlace;
	}
	public void setBurialPlace(String burialPlace) {
		this.burialPlace = burialPlace;
	}


	public Integer getDateOfBirth_day() {
		return dateOfBirth_day;
	}
	
	public void setDateOfBirth_day(Integer dateOfBirth_day) {
		this.dateOfBirth_day = dateOfBirth_day;
	}
	
	public Integer getDateOfBirth_month() {
		return dateOfBirth_month;
	}
	
	public void setDateOfBirth_month(Integer dateOfBirth_month) {
		this.dateOfBirth_month = dateOfBirth_month;
	}
	
	public Integer getDateOfBirth_year() {
		return dateOfBirth_year;
	}
	
	public void setDateOfBirth_year(Integer dateOfBirth_year) {
		this.dateOfBirth_year = dateOfBirth_year;
	}
	
	public Integer getDateOfDeath_day() {
		return dateOfDeath_day;
	}
	
	public void setDateOfDeath_day(Integer dateOfDeath_day) {
		this.dateOfDeath_day = dateOfDeath_day;
	}
	
	public Integer getDateOfDeath_month() {
		return dateOfDeath_month;
	}
	
	public void setDateOfDeath_month(Integer dateOfDeath_month) {
		this.dateOfDeath_month = dateOfDeath_month;
	}
	
	public Integer getDateOfDeath_year() {
		return dateOfDeath_year;
	}
	
	public String getCommune() {
		return commune;
	}
	

	public void setCommune(String commune) {
		this.commune = commune;
	}
	

	public String getCountry() {
		return country;
	}
	

	public void setCountry(String country) {
		this.country = country;
	}
	

	public Integer getDateOfBurial_day() {
		return dateOfBurial_day;
	}
	

	public void setDateOfBurial_day(Integer dateOfBurial_day) {
		this.dateOfBurial_day = dateOfBurial_day;
	}
	

	public Integer getDateOfBurial_month() {
		return dateOfBurial_month;
	}
	

	public void setDateOfBurial_month(Integer dateOfBurial_month) {
		this.dateOfBurial_month = dateOfBurial_month;
	}
	

	public Integer getDateOfBurial_year() {
		return dateOfBurial_year;
	}
	

	public void setDateOfBurial_year(Integer dateOfBurial_year) {
		this.dateOfBurial_year = dateOfBurial_year;
	}
	

	public String getParish() {
		return parish;
	}
	

	public void setParish(String parish) {
		this.parish = parish;
	}
	

	public void setDateOfDeath_year(Integer dateOfDeath_year) {
		this.dateOfDeath_year = dateOfDeath_year;
	}
	
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setDateOfDeath(String dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getGraveNumber() {
		return graveNumber;
	}
	public void setGraveNumber(String graveNumber) {
		this.graveNumber = graveNumber;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Boolean getCreateObituaryAfterSaving() {
		return createObituaryAfterSaving;
	}
	public void setCreateObituaryAfterSaving(Boolean createObituaryAfterSaving) {
		this.createObituaryAfterSaving = createObituaryAfterSaving;
	}
	
	public List getMonthSelectItemList() {

		List l = new ArrayList();


				l.add(new SelectItem("0","Month"));

				l.add(new SelectItem("1","January"));
				l.add(new SelectItem("2","February"));
				l.add(new SelectItem("3","March"));
				l.add(new SelectItem("4","April"));
				l.add(new SelectItem("5","May"));
				l.add(new SelectItem("6","June"));
				l.add(new SelectItem("7","July"));
				l.add(new SelectItem("8","August"));
				l.add(new SelectItem("9","Septemder"));
				l.add(new SelectItem("10","Oktober"));
				l.add(new SelectItem("11","November"));
				l.add(new SelectItem("12","December"));

				
	
		
		return l;
		
	}	
	
	public List getDateOfBirthDaySelectItemList() {
        
		List l = new ArrayList();
		Calendar cal = null;
		if (dateOfBirth_year != null ){
			cal = new GregorianCalendar(dateOfBirth_year.intValue(), dateOfBirth_month.intValue()-1, 1);
		}	
		else{
			cal = new GregorianCalendar(2001, dateOfBirth_month.intValue()-1, 1);
		}

		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
	
		l.add(new SelectItem("0","DD"));
	 
		for (int i=1;i<=days;i++) l.add(new SelectItem(String.valueOf(i),String.valueOf(i) ) );

		return l;

	}	

	public List getDateOfDeathDaySelectItemList() {
        
		List l = new ArrayList();
		Calendar cal = null;
		if (dateOfDeath_year != null ){
			cal = new GregorianCalendar(dateOfDeath_year.intValue(), dateOfDeath_month.intValue()-1, 1);
		}	
		else{
			cal = new GregorianCalendar(2001, dateOfDeath_month.intValue()-1, 1);
		}

		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
	
		l.add(new SelectItem("0","DD"));
	 
		for (int i=1;i<=days;i++) l.add(new SelectItem(String.valueOf(i),String.valueOf(i) ) );

		return l;

	}	
	
	public List getDateOfBurialDaySelectItemList() {
        
		List l = new ArrayList();
		Calendar cal = null;
		if (dateOfBurial_year != null ){
			cal = new GregorianCalendar(dateOfBurial_year.intValue(), dateOfBurial_month.intValue()-1, 1);
		}	
		else{
			cal = new GregorianCalendar(2001, dateOfBurial_month.intValue()-1, 1);
		}

		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
	
		l.add(new SelectItem("0","DD"));
	 
		for (int i=1;i<=days;i++) l.add(new SelectItem(String.valueOf(i),String.valueOf(i) ) );

		return l;

	}	
	
	
	
	public CustomMemorialDate getDateOfBirth() {
		
		
		if ((dateOfBirth_year == null ) 
			&& (dateOfBirth_month == null )
            && (dateOfBirth_day == null ) )return null;
		
		CustomMemorialDate date = null;
		Integer year=null,month=null,day=null;

		
		if ((dateOfBirth_year != null )&& (dateOfBirth_year.intValue()!=0)){
			if ((dateOfBirth_year.intValue()>1500) && (dateOfBirth_year.intValue()<2050) ) year = dateOfBirth_year; 
		}
		
		if ((dateOfBirth_month != null )&& (dateOfBirth_month.intValue()!=0)){
			 if (dateOfBirth_month.intValue() < 12 ) month = dateOfBirth_month;
		}

		if ((dateOfBirth_day != null ) &&(dateOfBirth_month.intValue()!=0) && (dateOfBirth_day.intValue()!=0)){
			if (dateOfBirth_day.intValue()<32) day = dateOfBirth_day;
		}	
		date = 	new CustomMemorialDate(year,month,day);	

		return date;		
	}
	
	public CustomMemorialDate getDateOfDeath() {
		
		
		if ((dateOfDeath_year == null ) 
			&& (dateOfDeath_month == null )
            && (dateOfDeath_day == null ) )return null;
		
		CustomMemorialDate date = null;
		Integer year=null,month=null,day=null;

		
		if ((dateOfDeath_year != null )&& (dateOfDeath_year.intValue()!=0)){
			if ((dateOfDeath_year.intValue()>1500) && (dateOfDeath_year.intValue()<2050) ) year = dateOfDeath_year; 
		}
		
		if ((dateOfDeath_month != null )&& (dateOfDeath_month.intValue()!=0)){
			 if (dateOfDeath_month.intValue() < 12 ) month = dateOfDeath_month;
		}

		if ((dateOfDeath_day != null ) &&(dateOfDeath_month.intValue()!=0) && (dateOfDeath_day.intValue()!=0)){
			if (dateOfDeath_day.intValue()<32) day = dateOfDeath_day;
		}	
		date = 	new CustomMemorialDate(year,month,day);	
				
		return date;		
	}	
	
	public CustomMemorialDate getDateOfBurial() {
		
		
		if ((dateOfBurial_year == null ) 
			&& (dateOfBurial_month == null )
            && (dateOfBurial_day == null ) )return null;
		
		CustomMemorialDate date = null;
		Integer year=null,month=null,day=null;

		
		if ((dateOfBurial_year != null )&& (dateOfBurial_year.intValue()!=0)){
			if ((dateOfBurial_year.intValue()>1500) && (dateOfBurial_year.intValue()<2050) ) year = dateOfBurial_year; 
		}
		
		if ((dateOfBurial_month != null )&& (dateOfBurial_month.intValue()!=0)){
			 if (dateOfBurial_month.intValue() < 12 ) month = dateOfBurial_month;
		}

		if ((dateOfBurial_day != null ) &&(dateOfBurial_month.intValue()!=0) && (dateOfBurial_day.intValue()!=0)){
			if (dateOfBurial_day.intValue()<32) day = dateOfBurial_day;
		}	
		date = 	new CustomMemorialDate(year,month,day);	
				
		return date;		
	}	

	
	public String save() {
	
		// if user entered new graveyard, then 
		// insert new cemetery in grave_graveyard table
		// and use that new value
		
		if (this.getNewGraveyard() != null) { 
			
			try {
		
				GraveDatabaseConnHome gdch = (GraveDatabaseConnHome) IDOLookup.getHome(GraveDatabaseConn.class);
				GraveDatabaseConn conn = gdch.findByPrimaryKey(LOCAL_DATABASE_CONNECTION_PRIMARY_KEY); 
				
				GraveGraveyardHome ggh = (GraveGraveyardHome) IDOLookup.getHome(GraveGraveyard.class);
				GraveGraveyard gg = ggh.create();
				gg.setGraveyardName(getNewGraveyard());
				gg.setGraveDatabaseConn(conn);
				gg.store();
				
				this.setExistingGraveyardId((Integer)gg.getPrimaryKey());
				
			} catch (IDOLookupException e) {				
				e.printStackTrace();				
			} catch (CreateException e) {				
				e.printStackTrace();
			} catch (FinderException e) {
				e.printStackTrace();
			}
			
			
			
		} 
		
        try {
			if(dateOfBirth == null) dateOfBirth = getDateOfBirth().getFormatedString();
			if(dateOfDeath == null) dateOfDeath = getDateOfDeath().getFormatedString();
			if(dateOfBurial == null) dateOfBurial = getDateOfBurial().getFormatedString();
			
			
			GraveLocallyStoredHome home = (GraveLocallyStoredHome) IDOLookup.getHome(GraveLocallyStored.class);
			GraveLocallyStored gls = home.create();
			
			gls.setFirstName(getFirstName());			
			gls.setLastName(getLastName());
			

			gls.setDateOfBirth(new java.sql.Date(Utility.stringToDate(dateOfBirth).getTime())); 
			gls.setDateOfDeath(new java.sql.Date(Utility.stringToDate(dateOfDeath).getTime()));
			gls.setDateOfBurial(new java.sql.Date(Utility.stringToDate(dateOfBurial).getTime()));
			
			
			gls.setHomeTown(getHometown());			
			
			try {
				GraveGraveyardHome ggh = (GraveGraveyardHome) IDOLookup.getHome(GraveGraveyard.class);
				GraveGraveyard gg = ggh.findByPrimaryKey(getExistingGraveyardId());
				gls.setGraveGraveyard(gg);
			} catch (Exception e){
				System.out.println("we got problems when tried to get GraveGraveyard gg = ggh.findByPrimaryKey(LOCAL_DATABASE_CONNECTION_PRIMARY_KEY)");
				gls.setGraveGraveyard(null); 	
			}	
			
			
			gls.setBurialPlace(this.getBurialPlace());
			gls.setBlock(this.getBlock());
			gls.setDepartment(this.getDepartment());
			gls.setGraveNumber(this.getGraveNumber());
			gls.setCommune(this.getCommune());
			gls.setParish(this.getParish());
			gls.setCountry(this.getCountry()); 

			gls.getPrimaryKey();
			
						
			gls.store();			
			
			obituarySessionBean.setPersonFullName(getFirstName()+" "+getLastName());
			obituarySessionBean.setGraveId(gls.getColumID());
			obituarySessionBean.setDatabaseId(LOCAL_DATABASE_CONNECTION_PRIMARY_KEY);  
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}	
			
		if (this.createObituaryAfterSaving.booleanValue() ==  true) return "goToObituary";
		else return "goToSearch";
	}
	
	
	public Integer getExistingGraveyardId() {
		return existingGraveyardId;
	}
	
	public void setExistingGraveyardId(Integer existingGraveyardId) {
		this.existingGraveyardId = existingGraveyardId;
	}
	
	public String getNewGraveyard() {
		return newGraveyard;
	}
	
	public void setNewGraveyard(String newGraveyard) {
		this.newGraveyard = newGraveyard;
	}
	
	
	public List getGraveyardSelectItemList() {
		List l = new ArrayList();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);		
		
		SearchImplBroker sib;
		try {
			sib = (SearchImplBroker) IBOLookup.getServiceInstance(iwc, SearchImplBroker.class);
			ObituarySearch os = sib.getSearch(LOCAL_DATABASE_CONNECTION_PRIMARY_KEY.intValue());
			List listOfGraveyards = (List) os.getGraveyards();
			
			for (Iterator iter = listOfGraveyards.iterator(); iter.hasNext();) {
				Graveyard g = (Graveyard) iter.next();
				l.add(new SelectItem(new Integer(g.getId()), // java.lang.Integer
						g.getBenamning()));
			}			
			
			
		} catch (IBOLookupException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}		
		

		return l;
		
	}	
	
}
