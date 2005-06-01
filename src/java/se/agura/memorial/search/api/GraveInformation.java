package se.agura.memorial.search.api;

/**
 * 
 */

/**
 * @author Igors
 * 
 */
public class GraveInformation {
	  
	String graveNumber; 
	String block; 
	String department; 
	String cemetery;
	public GraveInformation(
			String graveNumber, 
			String block, 
			String department, 
			String cemetery) {
		super();

		this.graveNumber = graveNumber;
		this.block = block;
		this.department = department;
		this.cemetery = cemetery;
	}
	public String getBlock() {
		return block;
	}
	
	public void setBlock(String block) {
		this.block = block;
	}
	
	public String getCemetery() {
		return cemetery;
	}
	
	public void setCemetery(String cemetery) {
		this.cemetery = cemetery;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getGraveNumber() {
		return graveNumber;
	}
	
	public void setGraveNumber(String graveNumber) {
		this.graveNumber = graveNumber;
	}
	

}
