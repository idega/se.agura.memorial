package se.agura.memorial.util;

import java.util.Collection;

public class MemorialHeplInfo {
  
    private String title;
	private Collection info;
	public Collection getInfo() {
		return info;
	}
	
	public MemorialHeplInfo() {

		super();
		
	}
	
	public void setInfo(Collection info) {
		this.info = info;
	}
	
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	
}
