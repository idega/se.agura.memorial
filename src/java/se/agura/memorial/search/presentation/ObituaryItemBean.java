package se.agura.memorial.search.presentation;

import se.agura.memorial.search.api.Grave;

import com.idega.content.bean.ContentItemBean;

public abstract class ObituaryItemBean extends ContentItemBean {
	
    private Integer obituaryId;
	private String obituaryText; 
	private String presonPicturePath; 
	private String gravePicturePath;	
	private String uniqueAvenyId;
    private Integer graveId;
	
    private Grave grave;	
	
	
	
	public ObituaryItemBean() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Grave getBody() {
		return grave;
	}
	



	public void setBody(Grave grave) {
		this.grave = grave;
	}
	



	public Integer getGraveId() {
		return graveId;
	}
	



	public void setGraveId(Integer graveId) {
		this.graveId = graveId;
	}
	



	public String getGravePicturePath() {
		return gravePicturePath;
	}
	



	public void setGravePicturePath(String gravePicturePath) {
		this.gravePicturePath = gravePicturePath;
	}
	



	public Integer getObituaryId() {
		return obituaryId;
	}
	



	public void setObituaryId(Integer obituaryId) {
		this.obituaryId = obituaryId;
	}
	



	public String getObituaryText() {
		return obituaryText;
	}
	



	public void setObituaryText(String obituaryText) {
		this.obituaryText = obituaryText;
	}
	



	public String getPresonPicturePath() {
		return presonPicturePath;
	}
	



	public void setPresonPicturePath(String presonPicturePath) {
		this.presonPicturePath = presonPicturePath;
	}
	



	public String getUniqueAvenyId() {
		return uniqueAvenyId;
	}
	



	public void setUniqueAvenyId(String uniqueAvenyId) {
		this.uniqueAvenyId = uniqueAvenyId;
	}
	
	

}
