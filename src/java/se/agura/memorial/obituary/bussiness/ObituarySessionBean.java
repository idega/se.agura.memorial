package se.agura.memorial.obituary.bussiness;
import com.idega.business.IBOSessionBean;

public class ObituarySessionBean extends IBOSessionBean  implements ObituarySession{
	
	private Integer databaseId = null;
	
	public ObituarySessionBean(){}

	public Integer getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(Integer databaseId) {
		this.databaseId = databaseId;
	}

}