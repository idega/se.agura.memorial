package se.agura.memorial.search.presentation;

import java.rmi.RemoteException;

import javax.faces.context.FacesContext;

import se.agura.memorial.search.api.Grave;
import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.search.business.SearchImplBroker;

import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.presentation.IWContext;

public class ObituaryInformationDisplayBackingBean {
	String graveId;
	
	private Grave grave = null;
	
	public String getGraveId() {
		return graveId;
	}

	public void setGraveId(String graveId) {		
		this.graveId = graveId;
		
		//i am almost sure that managed properties are 
		//initialized in order they are defined in faces-config.xml
		
		//now lets get grave		
		
//		MalmoSearchBMPBean ms = new MalmoSearchBMPBean();
//		this.grave = ms.findGrave(this.getGraveId()); 
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		
		try {
			SearchImplBroker sib = (SearchImplBroker) IBOLookup.getServiceInstance(iwc, SearchImplBroker.class);
			
			ObituarySearch os = sib.getSearch(1);
			this.grave = os.findGrave(this.getGraveId());
			
			System.out.println("aaa");
			
		} catch (IBOLookupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}
		
		System.out.println("aaa");
		
		System.out.println("grave: " + grave.getFirstName());
		
	}

	public Grave getGrave() {
		return grave;
	}
	
}
