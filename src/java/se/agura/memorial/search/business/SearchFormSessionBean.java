package se.agura.memorial.search.business;

import com.idega.business.IBOSessionBean;

public class SearchFormSessionBean extends IBOSessionBean  implements SearchFormSession{
	
	private Integer databaseId = null;
	
	private String graveId = null;	
	

	public SearchFormSessionBean(){}

	
	
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
