package se.agura.memorial.obituary.presentation;

import java.awt.Image;

import java.io.IOException;


import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.apache.myfaces.custom.fileupload.UploadedFile;


import se.agura.memorial.obituary.bussiness.ObituarySessionBean;
import se.agura.memorial.obituary.data.ObituaryItemBean;
import se.agura.memorial.search.api.Grave;

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
//	private String graveImageResourcePath;
//	private String personImageResourcePath;
	

	private Boolean personImageRemove;
	private Boolean graveImageRemove;
	
	private Boolean personImageRendered;
	private Boolean graveImageRendered;

	private UploadedFile personImage;
	private UploadedFile graveImage;
	private UploadedFile tmpPersonImage;
	private UploadedFile tmpGraveImage;

	private Grave grave = null;	

	
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
	
//	
//	private String createGraveImageResourcePath() {
//		if(databaseId ==null)return "";
//		
//		String path = CONTENT_PATH;
//		path += String.valueOf(obituarySessionBean.getDatabaseId());
//		path += "/" + obituarySessionBean.getGraveId();
//		path += ".GraveImage/";
//		path += "grave.jpg";
//		return path;
//		
//	}
//	
//	private String createPersonImageResourcePath() {
//		if(databaseId ==null)return "";
//
//		String path = CONTENT_PATH;
//		path += String.valueOf(obituarySessionBean.getDatabaseId());
//		path += "/" + obituarySessionBean.getGraveId();
//		path += ".PersonImage/";
//		path += "person.jpg";
//		return path;
//		
//	}
	
	private void initObituaryCreate() {
		
		    this.databaseId = this.obituarySessionBean.getDatabaseId();
			this.graveId = this.obituarySessionBean.getGraveId();
			this.obituaryText = this.obituarySessionBean.getObituaryText();
			this.personFullName =this.obituarySessionBean.getPersonFullName(); 
			this.graveImagePath = this.obituarySessionBean.getGraveImagePath();
			this.personImagePath = this.obituarySessionBean.getPersonImagePath();		
			this.graveImageRendered = this.obituarySessionBean.getGraveImageRendered();
			this.personImageRendered = this.obituarySessionBean.getPersonImageRendered();
			this.graveImageRemove = this.obituarySessionBean.getRemoveOldGraveImage();
			this.personImageRemove = this.obituarySessionBean.getRemoveOldPersonImage();
			
	
	}	
	
    public Boolean getGraveImageRemove() {
		return graveImageRemove;
	}
	


	public void setGraveImageRemove(Boolean graveImageRemove) {
		this.graveImageRemove = graveImageRemove;
	}
	


	public Boolean getPersonImageRemove() {
		return personImageRemove;
	}
	


	public Boolean getGraveImageRendered() {
		return graveImageRendered;
	}
	


	public void setGraveImageRendered(Boolean graveImageRendered) {
		this.graveImageRendered = graveImageRendered;
	}
	


	public Boolean getPersonImageRendered() {
		return personImageRendered;
	}
	


	public void setPersonImageRendered(Boolean personImageRendered) {
		this.personImageRendered = personImageRendered;
	}
	


	public void setPersonImageRemove(Boolean personImageRemove) {
		this.personImageRemove = personImageRemove;
	}
	


	public UploadedFile getTmpGraveImage() {
		return tmpGraveImage;
	}
	

	public String getPersonFullName() {
		return personFullName;
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
//        if(obituarySessionBean!=null)
//		{
//			graveId = obituarySessionBean.getGraveId();
//			obituaryText = obituarySessionBean.getObituaryText();
//		    databaseId = obituarySessionBean.getDatabaseId();
//			this.personImageRemove=this.obituarySessionBean.getRemoveOldPersonImage();
//			this.graveImageRemove=this.obituarySessionBean.getRemoveOldGraveImage();
//
//			graveId = obituarySessionBean.getGraveId();
//		
//			FacesContext facesContext = FacesContext.getCurrentInstance();
//			IWContext iwc = IWContext.getIWContext(facesContext);
//		
//			try {
//				SearchImplBroker sib = (SearchImplBroker) IBOLookup.getServiceInstance(iwc, SearchImplBroker.class);
//			
//				ObituarySearch os = sib.getSearch(getDatabaseId().intValue());
//				grave = os.findGrave(getGraveId());
//			
//			
//			} catch (IBOLookupException e) {
//				e.printStackTrace();
//			} catch (RemoteException e) {
//				e.printStackTrace();	
//			}
//		}	
	

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
			if(tmpPersonImage!=null) 	tmpPersonImageFile = ImageIO.read(getPersonImage().getInputStream());
		} catch (IOException e) {
			//e.printStackTrace();
		}
		try {
			if(tmpGraveImage!=null) tmpGraveImageFile = ImageIO.read(getGraveImage().getInputStream());
		} catch (IOException e) {

			//e.printStackTrace();
		}
		obituarySessionBean.setTmpObituaryText(this.getObituaryText());
		obituarySessionBean.setTmpGraveImageFile(this.getGraveImage());
		obituarySessionBean.setTmpPersonImageFile(this.getPersonImage());
		obituarySessionBean.setRemoveOldGraveImage(this.getGraveImageRemove());
		obituarySessionBean.setRemoveOldPersonImage(this.getPersonImageRemove());
		
        ObituaryItemBean oib = new ObituaryItemBean(); 
		oib.setDatabaseId(this.getDatabaseId());
		oib.setGraveId(this.getGraveId());
		oib.setTmpGraveImageFile(this.getGraveImage());	
		oib.setTmpPersonImageFile(this.getPersonImage());	
		oib.setRemoveOldPersonImage(this.getPersonImageRemove());
		oib.setRemoveOldGraveImage(this.getGraveImageRemove());

		oib.storeTMP();
		if(this.graveImage!=null) obituarySessionBean.setTmpGraveImagePath(oib.getTmpGraveImagePath());
		else obituarySessionBean.setTmpGraveImagePath(this.getGraveImagePath());
		
		if(this.personImage!=null) obituarySessionBean.setTmpPersonImagePath(oib.getTmpPersonImagePath());
		else obituarySessionBean.setTmpPersonImagePath(this.getPersonImagePath());
		
		if(this.graveImageRemove.booleanValue()) obituarySessionBean.setTmpGraveImagePath(null);
		if(this.personImageRemove.booleanValue()) obituarySessionBean.setTmpPersonImagePath(null);
		
		return "success";
    }

    public String save()
    {        
		 return "success";
    }
	
	
	
}
