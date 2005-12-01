package se.agura.memorial.obituary.presentation;





import javax.faces.context.FacesContext;
import org.apache.myfaces.custom.fileupload.UploadedFile;

import se.agura.memorial.obituary.bussiness.ObituarySessionBean;
import se.agura.memorial.obituary.data.ObituaryItemBean;

import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.presentation.IWContext;

public class ObituaryInformationPreviewBackingBean {


	private String obituaryText;

	private String personImagePath;
	private String graveImagePath;	

	private UploadedFile personImage;
	private UploadedFile graveImage;

	private String personFullName = null;		
	

	private static final String IW_BUNDLE_IDENTIFIER = "se.agura.memorial";
	
	
	private String graveId;
	private Integer databaseId;
	private ObituarySessionBean obituarySessionBean = null;	
	
	public ObituaryInformationPreviewBackingBean() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		try {
			obituarySessionBean = (ObituarySessionBean) IBOLookup.getSessionInstance(iwc, ObituarySessionBean.class);
		} catch (IBOLookupException e) {
			e.printStackTrace();
		}
		
		initData();
	}
	
	private void initData() {
        if(obituarySessionBean!=null)
		{
		    obituaryText = obituarySessionBean.getTmpObituaryText();
			graveId = obituarySessionBean.getGraveId();
			databaseId= obituarySessionBean.getDatabaseId();
			graveImage=obituarySessionBean.getTmpGraveImageFile();
			personImage=obituarySessionBean.getTmpPersonImageFile();
			graveImagePath= obituarySessionBean.getTmpGraveImagePath();
			personImagePath= obituarySessionBean.getTmpPersonImagePath();
			personFullName =obituarySessionBean.getPersonFullName(); 			

		}

	
	}	
	

	public UploadedFile getGraveImage() {
		return graveImage;
	}
	

	public void setGraveImage(UploadedFile graveImage) {
		this.graveImage = graveImage;
	}
	

	public UploadedFile getPersonImage() {
		return personImage;
	}
	

	public String getPersonFullName() {
		return personFullName;
	}
	

	public void setPersonFullName(String personFullName) {
		this.personFullName = personFullName;
	}
	

	public void setPersonImage(UploadedFile personImage) {
		this.personImage = personImage;
	}
	

	public String getGraveImagePath() {
		return graveImagePath;
	}
	

	public void setGraveImagePath(String graveImagePath) {
		this.graveImagePath = graveImagePath;
	}
	

	public String getPersonImagePath() {
//		return "http://localhost:8080/memorial/content"+personImagePath;
		return personImagePath;

	}
	

	public void setPersonImagePath(String personImagePath) {
		this.personImagePath = personImagePath;
	}
	

	public String getObituaryText() {
		return obituaryText;
	}
	

	public Integer getDatabaseId() {
		return databaseId;
	}
	



	public void setDatabaseId(Integer databaseId) {
		this.databaseId = databaseId;
	}
	


	public String getGraveId() {
		return graveId;
	}


	public void setGraveId(String graveId) {
		this.graveId = graveId;
	}
	
	public void setObituaryText(String obituaryText) {
		this.obituaryText = obituaryText;
	}
	
    public String save()
    {        
        ObituaryItemBean oib = new ObituaryItemBean(); 
		oib.setDatabaseId(getDatabaseId());
		oib.setGraveId(getGraveId());
		oib.setObituaryText(getObituaryText());
		oib.setPersonImageFile(getPersonImage());	
		oib.setGraveImageFile(getGraveImage());	
		oib.setRemoveOldPersonImage(obituarySessionBean.getRemoveOldPersonImage());
		oib.setRemoveOldGraveImage(obituarySessionBean.getRemoveOldGraveImage());
		oib.setContentLanguage("en");
		oib.store();
		obituarySessionBean.setRemoveOldGraveImage(Boolean.FALSE);
		obituarySessionBean.setRemoveOldPersonImage(Boolean.FALSE);

		obituarySessionBean.setObituaryText(getObituaryText());
		obituarySessionBean.setGraveImagePath(oib.getGraveImagePath());
		obituarySessionBean.setPersonImagePath(oib.getPersonImagePath());
		 
		if(this.personImage!=null)
			 obituarySessionBean.setPersonImageRendered(Boolean.TRUE);
		else
			 obituarySessionBean.setPersonImageRendered(Boolean.FALSE);
		
		if(this.graveImage!=null)
			obituarySessionBean.setGraveImageRendered(Boolean.TRUE);
		else
			obituarySessionBean.setGraveImageRendered(Boolean.FALSE);			

		
        return "success";
    }
	
	
	
}
