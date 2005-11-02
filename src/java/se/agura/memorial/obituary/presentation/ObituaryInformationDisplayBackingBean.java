package se.agura.memorial.obituary.presentation;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.apache.commons.httpclient.HttpException;

import se.agura.memorial.obituary.bussiness.ObituarySessionBean;
import se.agura.memorial.obituary.data.ObituaryItemBean;
import se.agura.memorial.search.api.Grave;
import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.search.business.SearchImplBroker;
import se.agura.memorial.util.MemorialHeplInfo;

import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.presentation.IWContext;
import com.idega.slide.util.WebdavExtendedResource;

public class ObituaryInformationDisplayBackingBean {

	private String obituaryText = "...";
	
	private static final String IW_BUNDLE_IDENTIFIER = "se.agura.memorial";
	
	
	private String graveId = null;
	private Integer actionId = null;	
	private Integer databaseId = null;
	
	private Grave grave = null;
	
	private MemorialHeplInfo mhi = null;

	private ObituarySessionBean obituarySessionBean = null;
	
	public ObituaryInformationDisplayBackingBean() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		try {
			obituarySessionBean = (ObituarySessionBean) IBOLookup.getSessionInstance(iwc, ObituarySessionBean.class);
		} catch (IBOLookupException e) {
			e.printStackTrace();
		}
		
		//obituarySessionBean.setDatabaseId(this.databaseId); 
				
		//initObituary();
	}
	
	private void initObituary() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		
        //this.obituaryText="aaaa";

		
		  
	
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
		this.graveId = graveId;
		
	
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		
		try {
			SearchImplBroker sib = (SearchImplBroker) IBOLookup.getServiceInstance(iwc, SearchImplBroker.class);
			
			ObituarySearch os = sib.getSearch(this.getDatabaseId().intValue());
			this.grave = os.findGrave(this.getGraveId());
			//this.databaseId = new Integer(1);
			this.obituaryText = "obituary text";
			this.graveId = "3007";
			
//	        ObituaryItemBean oib = new ObituaryItemBean(); 
//			oib.setDatabaseId(Integer.valueOf("1").intValue());  
//			
//			oib.setGraveId(Integer.valueOf("3007").toString());
//			WebdavExtendedResource webdavResource;
//			//webdavResource.setHttpURL("http://localhost:8080/memorial/content/files/cms/obituary/test.txt.xml");
//			webdavResource.setPath("http://localhost:8080/memorial/content/files/cms/obituary/test.txt.xml");
//			oib.load(webdavResource);
//			this.obituaryText = oib.getObituaryText();
			
			
		} catch (IBOLookupException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();	
		}
	
//		List mList = new ArrayList();
//		mList.add("info 1");
//		
//		MemorialHeplInfo mhi = new MemorialHeplInfo();
//		mhi.setInfo(mList);
		//mhi.setTitle("TOPIC 1");		
		

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
