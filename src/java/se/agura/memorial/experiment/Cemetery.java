package se.agura.memorial.experiment;

public class Cemetery {
	
	String name; 
	String id;
	
	
	public Cemetery(String name, String id) {
		super();
		
		this.name = name;
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}

}