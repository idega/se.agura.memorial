package se.agura.memorial.search.presentation;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.FinderException;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

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
	String dateOfBirth = "19101123";
	String dateOfDeath = "20021031";
	String hometown;
	String burialPlace;
	
	String newGraveyard;
	Integer existingGraveyardId;
	
	String department;
	String block;
	String graveNumber;
	Boolean createObituaryAfterSaving;
	
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getDateOfDeath() {
		return dateOfDeath;
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
		
	public String save() {
	
		// if user entered new graveyard, then 
		// insert new cemetery in grave_graveyard table
		// and use that new value
		
		if (this.getNewGraveyard() != null) { 
			
			try {
		
				GraveDatabaseConnHome gdch = (GraveDatabaseConnHome) IDOLookup.getHome(GraveDatabaseConn.class);
				GraveDatabaseConn conn = gdch.findByPrimaryKey(new Integer(2)); //TODO
				
				GraveGraveyardHome ggh = (GraveGraveyardHome) IDOLookup.getHome(GraveGraveyard.class);
				GraveGraveyard gg = ggh.create();
				gg.setGraveyardName(this.getNewGraveyard());
				gg.setGraveDatabaseConn(conn);
				gg.store();
				
				this.setExistingGraveyardId((Integer)gg.getPrimaryKey());
				
			} catch (IDOLookupException e) {				
				e.printStackTrace();				
			} catch (CreateException e) {				
				e.printStackTrace();
			} catch (FinderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		} 
		
        try {
			GraveLocallyStoredHome home = (GraveLocallyStoredHome) IDOLookup.getHome(GraveLocallyStored.class);
			GraveLocallyStored gls = home.create();
			
			gls.setFirstName(this.getFirstName());			
			gls.setLastName(this.getLastName());
			
			gls.setDateOfBirth(new java.sql.Date(Utility.stringToDate(this.dateOfBirth).getTime())); //TODO some date quality control on jsf page, or better yet, get Date from JSF- not string
			gls.setDateOfDeath(new java.sql.Date(Utility.stringToDate(this.dateOfDeath).getTime())); //TODO some date quality control on jsf page, or better yet, get Date from JSF- not string
			
			gls.setHomeTown(this.getHometown());			
			
			try {
				GraveGraveyardHome ggh = (GraveGraveyardHome) IDOLookup.getHome(GraveGraveyard.class);
				GraveGraveyard gg = ggh.findByPrimaryKey(this.getExistingGraveyardId());
				gls.setGraveGraveyard(gg);
			} catch (Exception e){
				//TODO handle error
				System.out.println("we got problems when tried to get GraveGraveyard gg = ggh.findByPrimaryKey(new Integer(2))");
				gls.setGraveGraveyard(null); 	
			}	
			
			
			gls.setBurialPlace(this.getBurialPlace());
			gls.setBlock(this.getBlock());
			gls.setDepartment(this.getDepartment());
			gls.setGraveNumber(this.getGraveNumber());
			
						
			gls.store();			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "failure";
		}	
			
		
		return "success";
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
			ObituarySearch os = sib.getSearch(2); //TODO dynamic database id here
			List listOfGraveyards = (List) os.getGraveyards();
			
			for (Iterator iter = listOfGraveyards.iterator(); iter.hasNext();) {
				Graveyard g = (Graveyard) iter.next();
				l.add(new SelectItem(new Integer(g.getId()), // java.lang.Integer
						g.getBenamning()));
			}			
			
			
		} catch (IBOLookupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		

		return l;
		
	}	
	
}
