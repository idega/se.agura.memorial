package se.agura.memorial.obituary.presentation;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.apache.commons.httpclient.HttpException;

import se.agura.memorial.obituary.bussiness.ObituarySessionBean;
import se.agura.memorial.obituary.data.ObituaryItemBean;
import se.agura.memorial.search.api.Grave;
import se.agura.memorial.search.api.Graveyard;
import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.search.business.SearchImplBroker;
import se.agura.memorial.util.MemorialHeplInfo;

import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.idegaweb.IWUserContext;
import com.idega.presentation.IWContext;
import com.idega.slide.business.IWSlideSession;

public class ObituaryInformationDisplayBackingBean {

	private String obituaryText = "...";
	
	private static final String IW_BUNDLE_IDENTIFIER = "se.agura.memorial";
	
	
	private String graveId = null;
	private Integer actionId = null;	
	private Integer databaseId = null;
	private Grave grave = null;
	private Graveyard graveyard;
	private boolean graveyardSetToNull = false;
	private Map graveyards;	
	
	private MemorialHeplInfo mhi = null;

	private ObituarySessionBean obituarySessionBean = null;
	
	public ObituaryInformationDisplayBackingBean() throws HttpException, IOException {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		try {
			obituarySessionBean = (ObituarySessionBean) IBOLookup.getSessionInstance(iwc, ObituarySessionBean.class);
		} catch (IBOLookupException e) {
			e.printStackTrace();
		}
		initObituary();
		
	}
	
	private void initObituary() throws HttpException, IOException {

		if(obituarySessionBean.getObituaryText()!=null) obituaryText=obituarySessionBean.getObituaryText();
		
		if (this.databaseId==null) return;
			
		graveyards = new LinkedHashMap();

		
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);

		try {
			
			SearchImplBroker sib = (SearchImplBroker) IBOLookup.getServiceInstance(iwc, SearchImplBroker.class);
			ObituarySearch os = sib.getSearch(this.getDatabaseId().intValue());
			List listOfGraveyards = (List) os.getGraveyards();
			
			grave = os.findGrave(this.getGraveId());
			
	        ObituaryItemBean oib = new ObituaryItemBean(); 
			oib.setDatabaseId(getDatabaseId());  
			oib.setGraveId(getGraveId());
			oib.setContentLanguage("en"); //TODO multilanguage
			
						
			IWUserContext iwuc = IWContext.getInstance();
			IWSlideSession session = (IWSlideSession) IBOLookup.getSessionInstance(iwuc, IWSlideSession.class);
			oib.load(session.getWebdavResource(oib.getRootPath()));
			obituaryText = oib.getObituaryText();
			obituarySessionBean.setObituaryText(getObituaryText()); 
			
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
	
	
	public MemorialHeplInfo getMhi() {
		return mhi;
	}
	

	public void setMhi(MemorialHeplInfo mhi) {
		this.mhi = mhi;
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

	
	public void setGraveId(String graveId) throws HttpException, IOException {		

		if(graveId == null) return;
		
		if(graveId == "") return;

		this.graveId = graveId;
		
		obituarySessionBean.setDatabaseId(getDatabaseId()); 

		obituarySessionBean.setGraveId(getGraveId()); 
		

		initObituary();	
		
		

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
