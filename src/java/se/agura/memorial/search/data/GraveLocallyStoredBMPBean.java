package se.agura.memorial.search.data;




import java.sql.Timestamp;
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

  		public static String TABLE_NAME = "GRAVE_LOCALLY_STORED";
		public static String COL_API_DB_CONNECTION = "";		
  		
		public static final String DATABASE_NAME = "IdegaWeb";		
		
		

		public static final String COLUMN_NAME_GRAVE_LOCALLY_STORED_ID = "GRAVE_LOCALLY_STORED_ID";
		public static final String COLUMN_NAME_FIRST_NAME = "FIRST_NAME";
		public static final String COLUMN_NAME_LAST_NAME = "LAST_NAME";
		public static final String COLUMN_NAME_DATE_OF_BIRTH = "DATE_OF_BIRTH";
		public static final String COLUMN_NAME_DATE_OF_DEATH = "DATE_OF_DEATH";
		public static final String COLUMN_NAME_HOMETOWN = "HOMETOWN";
		public static final String COLUMN_NAME_BURIAL_PLACE = "BURIAL_PLACE";
		public static final String COLUMN_NAME_CEMETERY = "CEMETERY";
		public static final String COLUMN_NAME_DEPARTMENT = "DEPARTMENT";
		public static final String COLUMN_NAME_BLOCK = "BLOCK";
		public static final String COLUMN_NAME_GRAVE_NUMBER = "GRAVE_NUMBER";

		public GraveLocallyStoredBMPBean() {
			super();
		}
		
		public void initializeAttributes() {
 
			addAttribute(getIDColumnName());
			addAttribute(COLUMN_NAME_GRAVE_LOCALLY_STORED_ID, "ID of Grave ", true, true, Integer.class);
			addAttribute(COLUMN_NAME_FIRST_NAME, "person first name  ", true, true, String.class, 40);
			addAttribute(COLUMN_NAME_LAST_NAME, "person last name ", true, true, String.class, 25);
			addAttribute(COLUMN_NAME_DATE_OF_BIRTH, "Date of birth ", true, true, Timestamp.class); 
			addAttribute(COLUMN_NAME_DATE_OF_DEATH, "Date of death ", true, true, Timestamp.class);
			addAttribute(COLUMN_NAME_HOMETOWN, "Place of birth ", true, true, String.class, 50);
			addAttribute(COLUMN_NAME_BURIAL_PLACE, "Place of dead ", true, true, String.class, 50);
			addAttribute(COLUMN_NAME_CEMETERY, "Name of cemetery ", true, true, String.class, 30);
			addAttribute(COLUMN_NAME_DEPARTMENT, "Description of the departament ", true, true, String.class, 30);
			addAttribute(COLUMN_NAME_BLOCK, "Name of block ", true, true, String.class, 30);
			addAttribute(COLUMN_NAME_GRAVE_NUMBER, "Grave number ", true, true, String.class, 15);


//			this.addManyToManyRelationShip(Address.class, "SR_SUPPLIER_IC_ADDRESS");
//			this.addManyToManyRelationShip(Phone.class, "SR_SUPPLIER_IC_PHONE");

			
//			addIndex("IDX_GRAVE_LSI_1", new String[]{getIDColumnName(), getColumnNameIsValid()});

		}

		public void insertStartData() throws Exception {
			GraveLocallyStoredHome home = (GraveLocallyStoredHome) IDOLookup.getHome(GraveLocallyStored.class);

			GraveLocallyStored data1 = home.create();
			data1.setID(1);			
			data1.setFirstName("Anders Beril Ove");
			data1.setLastName("Andersson");			
			data1.setDateOfBirth(Timestamp.valueOf("1945.03.02"));			
			data1.setDateOfDeath(Timestamp.valueOf("2001.01.02"));
			data1.setHomeTown("Ystad");
			data1.setBurialPlace("Stockholm");			
			data1.setCemetery("Skogskyrkärden");			
			data1.setDepartment("Rad 12");			
			data1.setBlock("Kv 4");			
			data1.setGraveNumber("0045");			
			
			data1.store();


			
				
		}


		public static String getGraveLocallyStoredTableName() {
			return TABLE_NAME;
		}

		public static String getColumnNameID() {
			return COLUMN_NAME_GRAVE_LOCALLY_STORED_ID;
		}

		public static String getColumnNameFirstName() {
			return COLUMN_NAME_FIRST_NAME;
		}

		public static String getColumnNameDateOfBirth() {
			return COLUMN_NAME_LAST_NAME;
		}

		public static String getColumnNameDateOfDeath() {
			return COLUMN_NAME_DATE_OF_BIRTH;
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

		public static String getColumnNameCemetery() {
			return COLUMN_NAME_CEMETERY;
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
		
		
		
		public void setID(int id) {
			setColumn(getColumnNameID(), id);
		}
		
		
		public void setFirstName(String firstName) {
			setColumn(getColumnNameFirstName(), firstName);
		}

		public void setLastName(String lastName) {
			setColumn(getColumnNameFirstName(), lastName);
		}

		public void setDateOfBirth(Timestamp dateOfBirth) {
			setColumn(getColumnNameDateOfBirth(), dateOfBirth);
		}

		public void setDateOfDeath(Timestamp dateOfDeath) {
			setColumn(getColumnNameDateOfDeath(), dateOfDeath);
		}
		
		
		public void setHomeTown(String homeTown) {
			setColumn(getColumnNameHomeTown(), homeTown);
		}

		public void setBurialPlace(String burialPlace) {
			setColumn(getColumnNameBurialPlace(), burialPlace);
		}

		public void setCemetery(String cemetery) {
			setColumn(getColumnNameCemetery(), cemetery);
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
				
		
		public Collection ejbFindAll() throws FinderException {
			Table table = new Table(this);

			Column name = new Column(table, getColumnNameFirstName());
			Order order = new Order(name, true);
			
			SelectQuery query = new SelectQuery(table);
			query.addColumn(new WildCardColumn(table));
//			query.addCriteria(new MatchCriteria(isValid, MatchCriteria.EQUALS, true));
//			query.addCriteria(new MatchCriteria(suppMan, MatchCriteria.EQUALS, supplierManager.getPrimaryKey()));
			query.addOrder(order);
			
			return this.idoFindPKsByQuery(query);
			

		}



	}

