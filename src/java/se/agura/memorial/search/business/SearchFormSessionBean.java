package se.agura.memorial.search.business;

import com.idega.business.IBOSessionBean;

public class SearchFormSessionBean extends IBOSessionBean  implements SearchFormSession{
	
	private Integer databaseId = null;
	
	public SearchFormSessionBean(){}

	public Integer getDatabaseId() {
		return databaseId;
	}

	public void setDatabaseId(Integer databaseId) {
		this.databaseId = databaseId;
	}

}
