package se.agura.memorial.obituary.bussiness;


import org.apache.myfaces.custom.fileupload.UploadedFile;

import com.idega.business.IBOSessionBean;

public class ObituarySessionBean extends IBOSessionBean  implements ObituarySession{
	
	private Integer databaseId = null;
	
	private String obituaryText = null;
	private String personFullName = null;	
	private String tmpObituaryText = null;
	private String tmpGraveImagePath = null;
	private String tmpPersonImagePath = null;
	private String graveImagePath = null;
	private String personImagePath = null;
	private UploadedFile personImageFile = null;
	private UploadedFile graveImageFile = null;
	private UploadedFile tmpPersonImageFile = null;
	private UploadedFile tmpGraveImageFile = null;

	private String graveId = null;
	
	
	public ObituarySessionBean(){}

	
	
	public String getObituaryText() {
		return obituaryText;
	}
	



	public UploadedFile getGraveImageFile() {
		return graveImageFile;
	}
	



	public void setGraveImageFile(UploadedFile graveImageFile) {
		this.graveImageFile = graveImageFile;
	}
	





	public String getPersonFullName() {
		return personFullName;
	}
	



	public void setPersonFullName(String personFullName) {
		this.personFullName = personFullName;
	}
	



	public UploadedFile getPersonImageFile() {
		return personImageFile;
	}
	



	public void setPersonImageFile(UploadedFile personImageFile) {
		this.personImageFile = personImageFile;
	}
	


	public void setObituaryText(String obituaryText) {
		this.obituaryText = obituaryText;
	}
	



	public String getTmpObituaryText() {
		return tmpObituaryText;
	}
	




	public String getGraveImagePath() {
		return graveImagePath;
	}
	



	public void setGraveImagePath(String graveImagePath) {
		this.graveImagePath = graveImagePath;
	}
	



	public String getPersonImagePath() {
		return personImagePath;
	}
	



	public void setPersonImagePath(String personImagePath) {
		this.personImagePath = personImagePath;
	}
	



	public UploadedFile getTmpGraveImageFile() {
		return tmpGraveImageFile;
	}
	



	public void setTmpGraveImageFile(UploadedFile tmpGraveImageFile) {
		this.tmpGraveImageFile = tmpGraveImageFile;
	}
	



	public String getTmpGraveImagePath() {
		return tmpGraveImagePath;
	}
	



	public void setTmpGraveImagePath(String tmpGraveImagePath) {
		this.tmpGraveImagePath = tmpGraveImagePath;
	}
	



	public UploadedFile getTmpPersonImageFile() {
		return tmpPersonImageFile;
	}
	



	public void setTmpPersonImageFile(UploadedFile tmpPersonImageFile) {
		this.tmpPersonImageFile = tmpPersonImageFile;
	}
	



	public String getTmpPersonImagePath() {
		return tmpPersonImagePath;
	}
	



	public void setTmpPersonImagePath(String tmpPersonImagePath) {
		this.tmpPersonImagePath = tmpPersonImagePath;
	}
	



	public void setTmpObituaryText(String tmpObituaryText) {
		this.tmpObituaryText = tmpObituaryText;
	}
	



	public String getGraveId() {
		return graveId;
	}
	



	public void setGraveId(String graveId) {
		this.graveId = graveId;
	}
	



	public Integer getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(Integer databaseId) {
		this.databaseId = databaseId;
	}

}