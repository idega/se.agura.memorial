package se.agura.memorial.search.api;


import se.agura.memorial.search.api.GraveInformation;

public class Graves extends GraveInformation{
  		private int graveID; // GA_Gravsatt.Grav_ID
  		private int lopNr; // GA_Gravsatt.LopNr
		private String firstName; // GA_Gravsatt.FORNAMN
		private String lastName; // GA_Gravsatt.EFTERNAMN
		private String dateOfBirth; // GA_Gravsatt.PERS_NR
		private String dateOfDeath; // GA_Gravsatt.DODSDATUM
		
	    private String home_town;
	    private String burial_place;		
	    private String cemetery;
	    private String department;
	    private String block;
	    private String grave_number;
	    private String grandID;
		private int rowNr; 
		
		
		
		public Graves()	{
			super();
			
		}
		
		public void init(
		
	            		int graveID,
	            		int lopNr,
	            		String firstName,
	            		String lastName,
	            		String dateOfBirth,
	            		String dateOfDeath,					  
	            		String home_town, 
				        String burial_place,
				        String cemetery,
				        String department,
				        String block,
				        String grave_number) {


			this.setGraveID(graveID);
			this.setFirstName(firstName);
			this.setLastName(lastName);
			this.setDateOfBirth(dateOfBirth);
			this.setDateOfDeath(dateOfDeath); 

			this.setHomeTown(home_town);
			this.setBurialPlace(burial_place);
			this.setCemetery(cemetery);
			this.setDepartment(department);
			this.setBlock(block);
			this.setGraveNumber(grave_number);
			this.setRowNr(0);

		}

		public int getRowNr() {
			return rowNr;
		}
		

		public void setRowNr(int rowNr) {
			this.rowNr = rowNr;
		}
		

		public String getDepartment() {
			return department;
		}
		



		public String getCemetery() {
			return cemetery;
		}
		

		public void setCemetery(String cemetery) {
			this.cemetery = cemetery;
		}
		

		public void setDepartment(String department) {
			this.department = department;
		}
		

		public String getGraveNumber() {
			return grave_number;
		}
		

		public String getBlock() {
			return block;
		}
		

		public void setBlock(String block) {
			this.block = block;
		}
		

		public String getBurialPlace() {
			return burial_place;
		}
		

		public void setBurialPlace(String burial_place) {
			this.burial_place = burial_place;
		}
		

		public void setGraveNumber(String grave_number) {
			this.grave_number = grave_number;
		}
		

		public int getGraveID() {
			return graveID;
		}
		

		public void setGraveID(int graveID) {
			this.graveID = graveID;
		}
		

		public String getHomeTown() {
			return home_town;
		}
		

		public void setHomeTown(String home_town) {
			this.home_town = home_town;
		}
		

		
		
		 

		
		
					  
}
