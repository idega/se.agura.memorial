package se.agura.memorial.search.data;






import java.sql.Date;
import java.util.Collection;
import javax.ejb.FinderException;
import com.idega.data.GenericEntity;
import com.idega.data.IDOLookup;
import com.idega.data.query.Column;

import com.idega.data.query.Order;
import com.idega.data.query.SelectQuery;
import com.idega.data.query.Table;
import com.idega.data.query.WildCardColumn;

public class GraveLocallyStoredBMPBean extends GenericEntity  implements GraveLocallyStored{

  		public static String TABLE_NAME = "MS_GRAVE_LOCALLY_STORED";
		public static String COL_API_DB_CONNECTION = "default";		
  		
		public static final String DATABASE_NAME = "dafault";		

  		public static final String COLUMN_NAME_ID = "MS_GRAVE_LOCALLY_STORED_ID";
		public static final String COLUMN_NAME_FIRST_NAME = "FIRST_NAME";
		public static final String COLUMN_NAME_LAST_NAME = "LAST_NAME";
		public static final String COLUMN_NAME_DATE_OF_BIRTH = "DATE_OF_BIRTH";
		public static final String COLUMN_NAME_DATE_OF_DEATH = "DATE_OF_DEATH";
		public static final String COLUMN_NAME_HOMETOWN = "HOMETOWN";
		public static final String COLUMN_NAME_BURIAL_PLACE = "BURIAL_PLACE";
		//public static final String COLUMN_NAME_CEMETERY = "CEMETERY";
		public static final String COLUMN_NAME_CEMETERY_ID = "CEMETERY_ID";		
		public static final String COLUMN_NAME_DEPARTMENT = "DEPARTMENT";
		public static final String COLUMN_NAME_BLOCK = "BLOCK";
		public static final String COLUMN_NAME_GRAVE_NUMBER = "GRAVE_NUMBER";

		public GraveLocallyStoredBMPBean() {
			super();
		}
		
		public void initializeAttributes() {
 
			addAttribute(getIDColumnName());
			addAttribute(COLUMN_NAME_FIRST_NAME, "person first name  ", true, true, String.class, 40);
			addAttribute(COLUMN_NAME_LAST_NAME, "person last name ", true, true, String.class, 25);
			addAttribute(COLUMN_NAME_DATE_OF_BIRTH, "Date of birth ", true, true, Date.class); 
			addAttribute(COLUMN_NAME_DATE_OF_DEATH, "Date of death ", true, true, Date.class);
			addAttribute(COLUMN_NAME_HOMETOWN, "Place of birth ", true, true, String.class, 50);
			addAttribute(COLUMN_NAME_BURIAL_PLACE, "Place of dead ", true, true, String.class, 50);
			addAttribute(COLUMN_NAME_DEPARTMENT, "Description of the departament ", true, true, String.class, 30);
			addAttribute(COLUMN_NAME_BLOCK, "Name of block ", true, true, String.class, 30);
			addAttribute(COLUMN_NAME_GRAVE_NUMBER, "Grave number ", true, true, String.class, 15);
			addManyToOneRelationship(COLUMN_NAME_CEMETERY_ID, GraveGraveyard.class);
			


		}

		public void insertStartData() throws Exception {
			GraveLocallyStoredHome home = (GraveLocallyStoredHome) IDOLookup.getHome(GraveLocallyStored.class);

			GraveLocallyStored data1 = home.create();
			data1.setFirstName("Anders Beril Ove");
			data1.setLastName("Andersson");			
			data1.setDateOfBirth(Date.valueOf("1945-03-02"));			
			data1.setDateOfDeath(Date.valueOf("2001-01-02"));

			data1.setHomeTown("Ystad");
			data1.setBurialPlace("Stockholm");			
			data1.setCemeteryId(1);			
			data1.setDepartment("Rad 12");			
			data1.setBlock("Kv 4");			
			data1.setGraveNumber("0045");			
			
			data1.store();
				
		}

		


	    public String getColumID() {
	        return getStringColumnValue(COLUMN_NAME_ID);
	    }		
		
		public String getFirstName() {
	        return getStringColumnValue(getColumnNameFirstName());
	    }		

	    public String getLastName() {
	        return getStringColumnValue(getColumnNameLastName());
	    }		

	    public Date getDateOfBirth() {
			return getDateColumnValue(getColumnNameDateOfBirth()) ;
			

	    }		

	    public Date getDateOfDeath() {
			return getDateColumnValue(getColumnNameDateOfDeath()) ;

	    }		

		
		public static String getGraveLocallyStoredTableName() {
			return TABLE_NAME;
		}

		public static String getColumnNameFirstName() {
			return COLUMN_NAME_FIRST_NAME;
		}

		public static String getColumnNameLastName() {
			return COLUMN_NAME_LAST_NAME;
		}

		public static String getColumnNameDateOfBirth() {
			return COLUMN_NAME_DATE_OF_BIRTH;
		}

		public static String getColumnNameDateOfDeath() {
			return COLUMN_NAME_DATE_OF_DEATH;
		}

		public static String getColumnNameHomeTown() {
			return COLUMN_NAME_HOMETOWN;
		}

		public static String getColumnNameBurialPlace() {
			return COLUMN_NAME_BURIAL_PLACE;
		}

		public static String getColumnNameBlock() {
			return COLUMN_NAME_BLOCK;
		}


		public static String getColumnNameCemeteryId() {
			return COLUMN_NAME_CEMETERY_ID;
		}
		
		
		public static String getColumnNameDepartment() {
			return COLUMN_NAME_DEPARTMENT;
		}

		public static String getColumnNameGraveNumber() {
			return COLUMN_NAME_GRAVE_NUMBER;
		}
		
		
		public String getEntityName() {
			return getGraveLocallyStoredTableName();
		}


        public void setDatabaseName(String name) {
            setColumn(DATABASE_NAME, name);
        }
        public String getDatabaseName() {
            return getStringColumnValue(DATABASE_NAME);
        }	
        
        public void setAPIDBConnection(String name) {
            setColumn(COL_API_DB_CONNECTION, name);
        }
        public String getAPIDBConnection() {
            return getStringColumnValue(COL_API_DB_CONNECTION);
        }   
		
		
		public String getCemetery() {
			return null;
		}

		public void setCemetery(String cemetery) {
			// TODO 
		}


		public void setFirstName(String firstName) {
			setColumn(getColumnNameFirstName(), firstName);
		}

		public void setLastName(String lastName) {
			setColumn(getColumnNameLastName(), lastName);
		}

		public void setDateOfBirth(Date dateOfBirth) {
			setColumn(getColumnNameDateOfBirth(), dateOfBirth);
		}

		public void setDateOfDeath(Date dateOfDeath) {
			setColumn(getColumnNameDateOfDeath(), dateOfDeath);
		}
		
		
		public void setHomeTown(String homeTown) {
			setColumn(getColumnNameHomeTown(), homeTown);
		}

		public void setBurialPlace(String burialPlace) {
			setColumn(getColumnNameBurialPlace(), burialPlace);
		}

		public void setCemeteryId(int cemetery) {
			setColumn(getColumnNameCemeteryId(), cemetery);
		}
		public void setDepartment(String department) {
			setColumn(getColumnNameDepartment(), department);
		}	

		public void setBlock(String block) {
			setColumn(getColumnNameBlock(), block);
		}		

		public void setGraveNumber(String graveNumber) {
			setColumn(getColumnNameGraveNumber(), graveNumber);
		}			
				
		public Collection ejbFindGraves(String firstName, String lastName, String personIdentifier, String dateOfBirthFrom, String dateOfBirthTo, String dateOfDeathFrom, String dateOfDeathTo, String hometown, String graveyard) throws FinderException {
			
		    Table table = new Table(TABLE_NAME);
			SelectQuery query = new SelectQuery(table);
/*		    
		    Column colGraveID = new Column(table, COLUMN_NAME_GRAVE_LOCALLY_STORED_ID);
			Column colFirstName = new Column(table, COLUMN_NAME_FIRST_NAME);
			Column colLastName = new Column(table, COLUMN_NAME_LAST_NAME);		
			Column colDateOfBirth = new Column(table, COLUMN_NAME_DATE_OF_BIRTH);		
			Column colDateOfDeath = new Column(table, COLUMN_NAME_DATE_OF_DEATH);		
			Column colGraveyard = new Column(table, COLUMN_NAME_CEMETERY);			
			Column colHomeTown = new Column(table, COLUMN_NAME_HOMETOWN);
			
			Order orderByFirstName = new Order(colFirstName, true);
			Order orderByLastName = new Order(colLastName, true);		

			
			query.addColumn(colGraveID);
			query.addColumn(colFirstName);
			query.addColumn(colLastName);
			query.addColumn(colDateOfBirth);
			query.addColumn(colDateOfDeath);

			String sqlStatement = query.toString();					
			StringBuffer sqlQuery = new StringBuffer();
				
			if (graveyard != null)  query.addCriteria(new MatchCriteria(colGraveyard, MatchCriteria.LIKE, "%" + graveyard.trim() + "%"));
			if (hometown != null)  query.addCriteria(new MatchCriteria(colHomeTown, MatchCriteria.LIKE, "%" + hometown.trim() + "%"));
			if (firstName != null)  query.addCriteria(new MatchCriteria(colFirstName, MatchCriteria.LIKE, "%" + firstName.trim() + "%"));
			if (lastName != null)  query.addCriteria(new MatchCriteria(colLastName, MatchCriteria.LIKE, "%" + lastName.trim() + "%"));
//			if (dateOfBirthTo != null){
//				
//			}
//			else
//				if (dateOfBirthFrom != null)  query.addCriteria(new MatchCriteria(colDateOfBirth, MatchCriteria.LIKE, dateOfBirthFrom.trim() + "%"));
				
			if (dateOfDeathFrom != null)  query.addCriteria(new MatchCriteria(colDateOfDeath, MatchCriteria.LIKE, dateOfDeathFrom.trim() + "%"));
			if (dateOfDeathTo != null)  query.addCriteria(new MatchCriteria(colDateOfDeath, MatchCriteria.LIKE, "%" + dateOfDeathTo.trim() + "%"));
			
			query.addOrder(orderByLastName);
			query.addOrder(orderByFirstName);
			sqlStatement=query.toString();
			
				*/
			return this.idoFindPKsByQuery(query);
		}		

		public Collection ejbFindAll() throws FinderException {
			Table table = new Table(this);
			Column name = new Column(table, getColumnNameFirstName());
			Order order = new Order(name, true);
			SelectQuery query = new SelectQuery(table);
			query.addColumn(new WildCardColumn(table));
			query.addOrder(order);
			
			return this.idoFindPKsByQuery(query);
		}



	}

