package se.agura.memorial.obituary.presentation;


public class ObituaryCreateBackingBean {

	private String obituaryText = "...";
	
	private static final String IW_BUNDLE_IDENTIFIER = "se.agura.memorial";
	
	
	
	
	public ObituaryCreateBackingBean() {
		
//
	}
	
	
	
	public String getObituaryText() {
		return obituaryText;
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
