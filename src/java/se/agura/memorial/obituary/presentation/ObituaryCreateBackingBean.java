package se.agura.memorial.obituary.presentation;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import java.security.MessageDigest;
import java.io.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ObituaryCreateBackingBean {

	private String obituaryText = "...";
	
	private UploadedFile myFile;

	private static final String IW_BUNDLE_IDENTIFIER = "se.agura.memorial";
	
	
	private String graveId = null;
	private Integer databaseId = new Integer(1);
	
	
	public ObituaryCreateBackingBean() {
		
//
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
            return "success";
        else
            return "failure";
    }

	
	
}
