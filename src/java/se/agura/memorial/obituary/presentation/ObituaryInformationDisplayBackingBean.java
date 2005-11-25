package se.agura.memorial.obituary.presentation;


import java.io.IOException;
import java.net.MalformedURLException;
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
import com.idega.xml.XMLException;


public class ObituaryInformationDisplayBackingBean {

	private String obituaryText = null;
	private String graveImageResourcePath = null;
	private String personImageResourcePath = null;
	
	private String labelGraveImage = null;
	private String labelPersonImage = null;

	private static final String IW_BUNDLE_IDENTIFIER = "se.agura.memorial";
	private static final String CONTENT_PATH = "/content/files/cms/obituary/";

	private String graveId = null;
	private String personFullName = null;	
	private Integer databaseId = null;
	private Grave grave = null;
	private Graveyard graveyard;
	private boolean graveyardSetToNull = false;
	private Map graveyards;	
	
	private MemorialHeplInfo mhi = null;

	private ObituarySessionBean obituarySessionBean = null;
	
	public ObituaryInformationDisplayBackingBean() throws HttpException, IOException, XMLException {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		try {
			obituarySessionBean = (ObituarySessionBean) IBOLookup.getSessionInstance(iwc, ObituarySessionBean.class);
		} catch (IBOLookupException e) {
			e.printStackTrace();
		}
		//personFullName = "";
		labelGraveImage = "Picture of grave";
		labelPersonImage = "Picture of person";
		
		initObituary(); 
		
	}
	
	public String getGraveImageResourcePath() {
		return graveImageResourcePath;
	}
	

	public void setGraveImageResourcePath(String graveImageResourcePath) {
		this.graveImageResourcePath = graveImageResourcePath;
	}
	

	public String getPersonImageResourcePath() {
		return personImageResourcePath;
	}
	

	public String getPersonFullName() {
		if(databaseId!=null)return personFullName;
		else return "";
	}
	

	public void setPersonFullName(String personFullName) {
		this.personFullName = personFullName;
	}
	

	public void setPersonImageResourcePath(String personImageResourcePath) {
		this.personImageResourcePath = personImageResourcePath;
	}
	

	private void initObituary() throws HttpException, IOException, XMLException {


		if (obituarySessionBean!=null)
		{
			graveImageResourcePath = createGraveImageResourcePath();
			personImageResourcePath = createPersonImageResourcePath();
			obituaryText=obituarySessionBean.getObituaryText();
			graveId=obituarySessionBean.getGraveId();
			databaseId=obituarySessionBean.getDatabaseId();

		}
		if (databaseId==null) return;
			
		graveyards = new LinkedHashMap();
		personFullName = "";
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);

		try {
			
			SearchImplBroker sib = (SearchImplBroker) IBOLookup.getServiceInstance(iwc, SearchImplBroker.class);
			ObituarySearch os = sib.getSearch(getDatabaseId().intValue());
			List listOfGraveyards = (List) os.getGraveyards();
			
			grave = os.findGrave(getGraveId());
			if(grave!=null)
			{	
				if(grave.getFirstName()!=null)	personFullName = grave.getFirstName();
				if(grave.getLastName()!=null) personFullName = personFullName+ " "+grave.getLastName();
			}
			ObituaryItemBean oib = new ObituaryItemBean(); 
			oib.setDatabaseId(getDatabaseId());  
			oib.setGraveId(getGraveId());
			oib.setContentLanguage("en"); //TODO multilanguage
						
			
			IWUserContext iwuc = IWContext.getInstance();
			IWSlideSession session = (IWSlideSession) IBOLookup.getSessionInstance(iwuc, IWSlideSession.class);
			
			boolean result = oib.load(session.getWebdavResource(oib.getRootPath()));
			
			obituaryText = oib.getObituaryText();
			obituarySessionBean.setObituaryText(getObituaryText());
			obituarySessionBean.setPersonFullName(personFullName);
			
			
			for (Iterator it = listOfGraveyards.iterator(); it.hasNext();) {
				Graveyard g = (Graveyard) it.next();
				graveyards.put(new Integer(g.getId()).toString(), g);
			}
			
			if(obituarySessionBean!=null)
			{
				obituaryText=obituarySessionBean.getObituaryText();
				if(result)
				{
				     graveImageResourcePath = createGraveImageResourcePath();
				     personImageResourcePath = createPersonImageResourcePath();
					 labelGraveImage = "Picture of grave";
					 labelPersonImage = "Picture of person";
			    }
				else
				{
				     graveImageResourcePath = "";
				     personImageResourcePath = "";
					 labelGraveImage = "";
					 labelPersonImage = "";
					
				}
				personFullName =obituarySessionBean.getPersonFullName(); 
				

			
			}	

					
			
		} catch (IBOLookupException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();	
		} catch(MalformedURLException e) {
			//e.printStackTrace();
		}
		
		
		
		
			
	}
	
	public String getLabelGraveImage() {
		return labelGraveImage;
	}
	

	public void setLabelGraveImage(String labelGraveImage) {
		this.labelGraveImage = labelGraveImage;
	}
	

	public String getLabelPersonImage() {
		return labelPersonImage;
	}
	

	public void setLabelPersonImage(String labelPersonImage) {
		this.labelPersonImage = labelPersonImage;
	}
	

	private String createGraveImageResourcePath() {
		String path = CONTENT_PATH;
		path += String.valueOf(obituarySessionBean.getDatabaseId());
		path += "/" + obituarySessionBean.getGraveId();
		path += ".GraveImage/";
		path += "grave.jpg";
		return path;
		
	}
	
	private String createPersonImageResourcePath() {
		String path = CONTENT_PATH;
		path += String.valueOf(obituarySessionBean.getDatabaseId());
		path += "/" + obituarySessionBean.getGraveId();
		path += ".PersonImage/";
		path += "person.jpg";
		return path;
		
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

	
	public void setGraveId(String graveId) throws HttpException, IOException, XMLException {		

		if(graveId == null) return;
		
		if(graveId == "") return;

		this.graveId = graveId;
		
		obituarySessionBean.setDatabaseId(getDatabaseId()); 

		obituarySessionBean.setGraveId(getGraveId()); 
		
		graveImageResourcePath = createGraveImageResourcePath();
		personImageResourcePath = createPersonImageResourcePath();

		

		initObituary();	
		

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
