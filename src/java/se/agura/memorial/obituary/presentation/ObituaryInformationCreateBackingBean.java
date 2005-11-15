package se.agura.memorial.obituary.presentation;

import java.awt.Image;

import java.io.IOException;
import java.rmi.RemoteException;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.apache.myfaces.custom.fileupload.UploadedFile;


import se.agura.memorial.obituary.bussiness.ObituarySessionBean;
import se.agura.memorial.obituary.data.ObituaryItemBean;
import se.agura.memorial.search.api.Grave;
import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.search.business.SearchImplBroker;

import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.presentation.IWContext;

public class ObituaryInformationCreateBackingBean {

	private String obituaryText;
	private String tmpObituaryText;

	private String personImagePath;
	private String graveImagePath;	
	private String tmpPersonImagePath;
	private String tmpGraveImagePath;
	private String personFullName;		
	private String graveImageResourcePath;
	private String personImageResourcePath;
	

	
	private UploadedFile personImage;
	private UploadedFile graveImage;
	private UploadedFile tmpPersonImage;
	private UploadedFile tmpGraveImage;

	private Grave grave = null;	
	
	private boolean removeOldPersonImage=false;
	private boolean removeOldGraveImage=false;

	
	private UploadedFile myFile;

	private static final String IW_BUNDLE_IDENTIFIER = "se.agura.memorial";
	private static final String CONTENT_PATH = "/content/files/cms/obituary/";
	private static final String TMP_CONTENT_PATH = "/content/files/cms/obituary/temp/";

	
	
	
	private String graveId = null;
	private Integer databaseId = null;
	private ObituarySessionBean obituarySessionBean = null;	
	
	public ObituaryInformationCreateBackingBean() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		try {
			obituarySessionBean = (ObituarySessionBean) IBOLookup.getSessionInstance(iwc, ObituarySessionBean.class);
		} catch (IBOLookupException e) {
			e.printStackTrace();
		}
		
		  initObituaryCreate();
	}
	
	
	private String createGraveImageResourcePath() {
		if(databaseId ==null)return "";
		
		String path = CONTENT_PATH;
		path += String.valueOf(obituarySessionBean.getDatabaseId());
		path += "/" + obituarySessionBean.getGraveId();
		path += ".GraveImage/";
		path += "grave.jpg";
		return path;
		
	}
	
	private String createPersonImageResourcePath() {
		if(databaseId ==null)return "";

		String path = CONTENT_PATH;
		path += String.valueOf(obituarySessionBean.getDatabaseId());
		path += "/" + obituarySessionBean.getGraveId();
		path += ".PersonImage/";
		path += "person.jpg";
		return path;
		
	}
	
	private void initObituaryCreate() {
		//if (this.databaseId==null) return;
		
		    databaseId = obituarySessionBean.getDatabaseId();
		    graveId = obituarySessionBean.getGraveId();
			obituaryText = obituarySessionBean.getObituaryText();
			personFullName =obituarySessionBean.getPersonFullName(); 
			graveImageResourcePath = createGraveImageResourcePath();
			personImageResourcePath = createPersonImageResourcePath();			
		

	
	}	
	
    public UploadedFile getTmpGraveImage() {
		return tmpGraveImage;
	}
	

	public String getPersonFullName() {
		return personFullName;
	}
	



	public boolean isRemoveOldGraveImage() {
		return removeOldGraveImage;
	}
	


	public void setRemoveOldGraveImage(boolean removeOldGraveImage) {
		this.removeOldGraveImage = removeOldGraveImage;
	}
	


	public boolean isRemoveOldPersonImage() {
		return removeOldPersonImage;
	}
	


	public void setRemoveOldPersonImage(boolean removeOldPersonImage) {
		this.removeOldPersonImage = removeOldPersonImage;
	}
	


	public void setPersonFullName(String personFullName) {
		this.personFullName = personFullName;
	}
	



	private String createTmpGraveImageResourcePath() {
		if(databaseId ==null)return "";
		
		String path = TMP_CONTENT_PATH;
		path += String.valueOf(getDatabaseId());
		path += "/" + getGraveId();
		path += ".GraveImage/";
		path += "grave.jpg";
		return path;
		
	}
	
	private String createTmpPersonImageResourcePath() {
		if(databaseId ==null)return "";
		
		String path = TMP_CONTENT_PATH;
		path += String.valueOf(getDatabaseId());
		path += "/" + getGraveId();
		path += ".PersonImage/";
		path += "person.jpg";
		return path;
		
	}


	public void setTmpGraveImage(UploadedFile tmpGraveImage) {
		this.tmpGraveImage = tmpGraveImage;
	}
	



	public String getTmpGraveImagePath() {
		return tmpGraveImagePath;
	}
	



	public void setTmpGraveImagePath(String tmpGraveImagePath) {
		this.tmpGraveImagePath = tmpGraveImagePath;
	}
	



	public String getTmpObituaryText() {
		return tmpObituaryText;
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
	



	public void setPersonImageResourcePath(String personImageResourcePath) {
		this.personImageResourcePath = personImageResourcePath;
	}
	



	public void setTmpObituaryText(String tmpObituaryText) {
		this.tmpObituaryText = tmpObituaryText;
	}
	



	public UploadedFile getTmpPersonImage() {
		return tmpPersonImage;
	}
	



	public void setTmpPersonImage(UploadedFile tmpPersonImage) {
		this.tmpPersonImage = tmpPersonImage;
	}
	



	public String getTmpPersonImagePath() {
		return tmpPersonImagePath;
	}
	



	public void setTmpPersonImagePath(String tmpPersonImagePath) {
		this.tmpPersonImagePath = tmpPersonImagePath;
	}
	



	public UploadedFile getGraveImage() {
		return graveImage;
	}
	

	public void setGraveImage(UploadedFile graveImage) {
		this.graveImage = graveImage;
	}
	

	public String getGraveImagePath() {
		return graveImagePath;
	}
	

	public void setGraveImagePath(String graveImagePath) {
		this.graveImagePath = graveImagePath;
	}
	

	public UploadedFile getPersonImage() {
		return personImage;
	}
	

	public void setPersonImage(UploadedFile personImage) {
		this.personImage = personImage;
	}
	

	public String getPersonImagePath() {
		return personImagePath;
	}
	

	public void setPersonImagePath(String personImagePath) {
		this.personImagePath = personImagePath;

	}
	

	public UploadedFile getMyFile() {
        return myFile;
    }

    public void setMyFile(UploadedFile myFile) {
        this.myFile = myFile;
    }	
	
	
	public String getObituaryText() {
		return obituaryText;
	}
	

	public Integer getDatabaseId() {
		return databaseId;
	}
	



	public void setDatabaseId(Integer databaseId) {
		databaseId = obituarySessionBean.getDatabaseId();
	}
	



	public String getGraveId() {
		
		
		return graveId;
	}
	



	public void setGraveId(String graveId) {
        if(obituarySessionBean!=null)
		{
			graveId = obituarySessionBean.getGraveId();
			obituaryText = obituarySessionBean.getObituaryText();
		    databaseId = obituarySessionBean.getDatabaseId();
		    graveId = obituarySessionBean.getGraveId();
		
			FacesContext facesContext = FacesContext.getCurrentInstance();
			IWContext iwc = IWContext.getIWContext(facesContext);
		
			try {
				SearchImplBroker sib = (SearchImplBroker) IBOLookup.getServiceInstance(iwc, SearchImplBroker.class);
			
				ObituarySearch os = sib.getSearch(getDatabaseId().intValue());
				grave = os.findGrave(getGraveId());
			
			
			} catch (IBOLookupException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();	
			}
		}	
	

	}
	



	public void setObituaryText(String obituaryText) {
		this.obituaryText = obituaryText;
	}
	

    public String preview()
    {        
		if(databaseId ==null)return "failed";
		
		Image tmpPersonImageFile = null;
		Image tmpGraveImageFile = null;
		try {
			tmpGraveImageFile = ImageIO.read(getGraveImage().getInputStream());
			tmpPersonImageFile = ImageIO.read(getPersonImage().getInputStream());
		} catch (IOException e) {

			e.printStackTrace();
		}
		obituarySessionBean.setTmpObituaryText(getObituaryText());
		obituarySessionBean.setTmpGraveImageFile(getGraveImage());
		obituarySessionBean.setTmpPersonImageFile(getPersonImage());
		
        ObituaryItemBean oib = new ObituaryItemBean(); 
		oib.setDatabaseId(getDatabaseId());
		oib.setGraveId(getGraveId());
		oib.setTmpGraveImageFile(getGraveImage());	
		oib.setTmpPersonImageFile(getPersonImage());	
		oib.storeTMP();
		obituarySessionBean.setTmpGraveImagePath(createTmpGraveImageResourcePath());
		obituarySessionBean.setTmpPersonImagePath(createTmpPersonImageResourcePath());		
		return "success";
    }

    public String save()
    {        
		 return "success";
    }
	
	
	
}
