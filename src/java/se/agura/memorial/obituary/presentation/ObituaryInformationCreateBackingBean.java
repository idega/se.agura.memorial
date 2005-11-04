package se.agura.memorial.obituary.presentation;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.security.MessageDigest;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import se.agura.memorial.obituary.bussiness.ObituarySessionBean;
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

	private UploadedFile personImage;

	private UploadedFile graveImage;
	
	private Grave grave = null;	
	
	private UploadedFile myFile;

	private static final String IW_BUNDLE_IDENTIFIER = "se.agura.memorial";
	
	
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
	
	
	
	private void initObituaryCreate() {
        if(obituarySessionBean!=null)
		{
		    this.databaseId = obituarySessionBean.getDatabaseId();
		    this.graveId = obituarySessionBean.getGraveId();
			this.obituaryText = obituarySessionBean.getObituaryText();
			
		}

	
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
		this.databaseId = obituarySessionBean.getDatabaseId();
	}
	



	public String getGraveId() {
		
		
		return graveId;
	}
	



	public void setGraveId(String graveId) {
        if(obituarySessionBean!=null)
		{
		
		
			this.graveId = obituarySessionBean.getGraveId();;
			this.obituaryText = obituarySessionBean.getObituaryText();
		
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
	

	}
	



	public void setObituaryText(String obituaryText) {
		this.obituaryText = obituaryText;
	}
	
    public String processMyFile() {
		String myResult = null;
		try {
            MessageDigest md = MessageDigest.getInstance(this.getMyFile().toString());
            InputStream in = new BufferedInputStream(
                myFile.getInputStream());
            try {
                byte[] buffer = new byte[64 * 1024];
                int count;
                while ((count = in.read(buffer)) > 0)
                    md.update(buffer, 0, count);
            } finally {
                in.close();
            }
            byte hash[] = md.digest();
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                int b = hash[i] & 0xFF;
                int c = (b >> 4) & 0xF;
                c = c < 10 ? '0' + c : 'A' + c - 10;
                buf.append((char) c);
                c = b & 0xF;
                c = c < 10 ? '0' + c : 'A' + c - 10;
                buf.append((char) c);
            }
            myResult = buf.toString();
            return "OK";
        } catch (Exception x) {
            FacesMessage message = new FacesMessage(
                FacesMessage.SEVERITY_FATAL,
                x.getClass().getName(), x.getMessage());
            FacesContext.getCurrentInstance().addMessage(
                null, message);
            return null;
        }
    }
	

    public String preview()
    {        
		
	//	FacesContext facesContext = FacesContext.getCurrentInstance();
		
//		IWContext iwc = IWContext.getIWContext(facesContext);

		obituarySessionBean.setTmpObituaryText(getObituaryText());
        return "success";
    }

    public String save()
    {        
		 return "success";
    }
	
	
	
}
