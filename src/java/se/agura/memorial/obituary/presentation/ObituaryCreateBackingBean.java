package se.agura.memorial.obituary.presentation;


public class ObituaryCreateBackingBean {

	private String obituaryText = "...";
	
	private static final String IW_BUNDLE_IDENTIFIER = "se.agura.memorial";
	
	
	private String graveId = null;
	private Integer databaseId = new Integer(1);
	
	
	public ObituaryCreateBackingBean() {
		
//
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
	

    public String onClick()
    {        
        
        boolean result = true;


        if(result)
            return "success";
        else
            return "failure";
    }

	
	
}
