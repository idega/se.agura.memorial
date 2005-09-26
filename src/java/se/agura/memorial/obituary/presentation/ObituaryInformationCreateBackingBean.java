package se.agura.memorial.obituary.presentation;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.myfaces.custom.fileupload.UploadedFile;

import se.agura.memorial.obituary.bussiness.ObituarySessionBean;
import se.agura.memorial.obituary.data.ObituaryItemBean;

import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.presentation.IWContext;

public class ObituaryInformationCreateBackingBean {

	private String obituaryText = "...";

	private String personImagePath;

	private String graveImagePath;	

	private UploadedFile personImage;

	private UploadedFile graveImage;
	
	private UploadedFile myFile;

	private static final String IW_BUNDLE_IDENTIFIER = "se.agura.memorial";
	
	
	private String graveId = null;
	private Integer databaseId = new Integer(1);
	private ObituarySessionBean obituarySessionBean = null;	
	
	public ObituaryInformationCreateBackingBean() {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		try {
			obituarySessionBean = (ObituarySessionBean) IBOLookup.getSessionInstance(iwc, ObituarySessionBean.class);
		} catch (IBOLookupException e) {
			e.printStackTrace();
		}
		
		obituarySessionBean.setDatabaseId(this.databaseId);
		initData();
	}
	
	private void initData() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
        this.obituaryText="bbbb";
		this.databaseId=new Integer(1);
//		this.graveId="5060";
		//this.personImagePath="ddssd";
		//this.graveImagePath="gravelsdklflk";
		
		
	
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
	

    public String onClick()
    {        
        
        boolean result = true;


        if(result)
            return "rrr";
        else
            return "failure";
    }

    public String save()
    {        
        ObituaryItemBean oib = new ObituaryItemBean(); 
//		oib.setDatabaseId(2);
//		oib.setGraveId("5050");
//		oib.setObituaryText("bla bla bla");
//		oib.setGravePicturePath("pictureLink");
//		oib.setPersonPicturePath("pictureLink");
		
		oib.setBody("bla bla bla ");
		oib.setDatabaseId(Integer.valueOf("1").intValue());  // bilo integer
		oib.setGraveId(Integer.valueOf("3002").toString());  // bilo Integer
		oib.setGravePicturePath("wwwww");
		oib.setPersonPicturePath("eeeee");		
		oib.store();
		
		
		
        boolean result = true;


        if(result)
            return "rrr";
        else
            return "failure";
    }
	
	
	
}
