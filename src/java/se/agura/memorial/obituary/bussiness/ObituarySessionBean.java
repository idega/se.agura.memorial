package se.agura.memorial.obituary.bussiness;
import com.idega.business.IBOSessionBean;

public class ObituarySessionBean extends IBOSessionBean  implements ObituarySession{
	
	private Integer databaseId = null;
	
	private String obituaryText = null;
	private String tmpObituaryText = null;
	
	private String graveId = null;
	
	public ObituarySessionBean(){}

	
	
	public String getObituaryText() {
		return obituaryText;
	}
	



	public void setObituaryText(String obituaryText) {
		this.obituaryText = obituaryText;
	}
	



	public String getTmpObituaryText() {
		return tmpObituaryText;
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