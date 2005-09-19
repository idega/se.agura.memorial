package se.agura.memorial.obituary.presentation;

import java.rmi.RemoteException;

import javax.faces.context.FacesContext;

import se.agura.memorial.obituary.bussiness.ObituarySessionBean;
import se.agura.memorial.search.api.Grave;
import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.search.business.SearchImplBroker;

import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.presentation.IWContext;

public class ObituaryInformationDisplayBackingBean {

	private String obituaryText = "...";
	
	private static final String IW_BUNDLE_IDENTIFIER = "se.agura.memorial";
	
	
	private String graveId = null;
	private Integer actionId = null;	
	private Integer databaseId = new Integer(1);
	
	private Grave grave = null;
	
	private ObituarySessionBean obituarySessionBean = null;
	
	public ObituaryInformationDisplayBackingBean() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		try {
			obituarySessionBean = (ObituarySessionBean) IBOLookup.getSessionInstance(iwc, ObituarySessionBean.class);
		} catch (IBOLookupException e) {
			e.printStackTrace();
		}
		
		obituarySessionBean.setDatabaseId(this.databaseId); 
				
		initObituary();
	}
	
	private void initObituary() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		

		  
	
	}
	
	
	public String getObituaryText() {
		return obituaryText;
	}
	

	public void setObituaryText(String obituaryText) {
		this.obituaryText = obituaryText;
	}
	

	public String getGraveId() {
		return graveId;
	}

	
	public void setGraveId(String graveId) {		
		this.graveId = graveId;
		
	
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		
		try {
			SearchImplBroker sib = (SearchImplBroker) IBOLookup.getServiceInstance(iwc, SearchImplBroker.class);
			
			ObituarySearch os = sib.getSearch(this.getDatabaseId().intValue());
			this.grave = os.findGrave(this.getGraveId());
			
		} catch (IBOLookupException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();	
		}
	
	}

    public String onClick()
    {        
        
        boolean result = true;


        if(result)
            return "success";
        else
            return "failure";
    }

	
	public Integer getActionId() {
		return actionId;
	}
	

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
//		switch(this.actionId.intValue()){
//		   case 4:   this.obituaryText="4";
//		             break;
//		   case 5:   this.obituaryText="5";
//		   			 break;
//		   case 7:   this.obituaryText="7";
//			 		 break;
//		   case 8:   this.obituaryText="8";
//		   			 break;
//					 
//		}		
	}
	

	public Grave getGrave() {
		return grave;
	}

	public Integer getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(Integer databaseId) {
		this.databaseId = databaseId;		
	}
	
}
