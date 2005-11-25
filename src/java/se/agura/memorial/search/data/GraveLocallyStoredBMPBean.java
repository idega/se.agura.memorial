package se.agura.memorial.search.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


import javax.ejb.FinderException;

import se.agura.memorial.search.api.CustomMemorialDate;
import se.agura.memorial.util.Utility;

import com.idega.data.GenericEntity;
import com.idega.data.IDOLookup;
import com.idega.data.IDOQuery;
import com.idega.data.IDORelationshipException;
import com.idega.data.query.Column;
import com.idega.data.query.MatchCriteria;
import com.idega.data.query.Order;
import com.idega.data.query.SelectQuery;
import com.idega.data.query.Table;
import com.idega.util.database.ConnectionBroker;

public class GraveLocallyStoredBMPBean extends GenericEntity  implements GraveLocallyStored{

	public static String ENTITY_NAME = "MS_GRAVE_LOCALLY_STORED";
	public static String TABLE_GRAVEYARD = "MS_GRAVE_GRAVEYARD";
	
	public final Integer LOCAL_DATABASE_CONNECTION_PRIMARY_KEY = new Integer(2);

	public static String COL_API_DB_CONNECTION = "default";		
	
	public static final String DATABASE_NAME = "default";
	private int maxResult = Utility.MAX_RESULT;
	
	private Integer graveId;
	
	public static final String COLUMN_GRAVEYARD_ID = "MS_GRAVE_GRAVEYARD_ID";
	public static final String COLUMN_GRAVEYARD_NAME = "GRAVEYARD_NAME";
	public static final String COLUMN_NAME_ID = "MS_GRAVE_LOCALLY_STORED_ID";
	public static final String COLUMN_NAME_FIRST_NAME = "FIRST_NAME";
	public static final String COLUMN_NAME_LAST_NAME = "LAST_NAME";
	public static final String COLUMN_NAME_DATE_OF_BIRTH = "DATE_OF_BIRTH";
	public static final String COLUMN_NAME_DATE_OF_DEATH = "DATE_OF_DEATH";
	public static final String COLUMN_NAME_BURIAL_DATE = "BURIAL_DATE";
	public static final String COLUMN_NAME_HOMETOWN = "HOMETOWN";
	public static final String COLUMN_NAME_BURIAL_PLACE = "BURIAL_PLACE";
	public static final String COLUMN_NAME_GRAVEYARD_ID = "GRAVEYARD_ID";		
	public static final String COLUMN_NAME_DEPARTMENT = "DEPARTMENT";
	public static final String COLUMN_NAME_BLOCK = "BLOCK";
	public static final String COLUMN_NAME_COUNTRY = "COUNTRY";
	public static final String COLUMN_NAME_COMMUNE = "COMMUNE";
	public static final String COLUMN_NAME_PARISH = "PARISH";	
	public static final String COLUMN_NAME_GRAVE_NUMBER = "GRAVE_NUMBER";
	private static final String COLUMN_NAME_GRAVEYARD_NAME = "GRAVEYARD_NAME as GRAVEYARD";
	private static final String COLUMN_NICKNAME_GRAVEYARD_NAME = "GRAVEYARD";

	public GraveLocallyStoredBMPBean() {
		super();
	}

	public void initializeAttributes() {

		addAttribute(getIDColumnName());
		addAttribute(COLUMN_NAME_FIRST_NAME, "person first name  ", true, true,	String.class, 40);
		addAttribute(COLUMN_NAME_LAST_NAME, "person last name ", true, true, String.class, 25);
		addAttribute(COLUMN_NAME_DATE_OF_BIRTH, "Date of birth ", true, true, Date.class);
		addAttribute(COLUMN_NAME_DATE_OF_DEATH, "Date of death ", true, true,Date.class);
		addAttribute(COLUMN_NAME_BURIAL_DATE, "Date of burial ", true, true,Date.class);
		addAttribute(COLUMN_NAME_HOMETOWN, "Place of birth ", true, true,String.class, 50);
		addAttribute(COLUMN_NAME_BURIAL_PLACE, "Place of dead ", true, true,String.class, 50);
		addAttribute(COLUMN_NAME_DEPARTMENT, "Description of the departament ",	true, true, String.class, 30);
		addAttribute(COLUMN_NAME_BLOCK, "Name of block ", true, true,String.class, 30);
		addAttribute(COLUMN_NAME_PARISH, "Parish", true, true,String.class, 30);
		addAttribute(COLUMN_NAME_COUNTRY, "Country ", true, true,String.class, 30);
		addAttribute(COLUMN_NAME_COMMUNE, "Commune ", true, true,String.class, 30);		
		addAttribute(COLUMN_NAME_GRAVE_NUMBER, "Grave number ", true, true,	String.class, 15);
		addManyToOneRelationship(COLUMN_NAME_GRAVEYARD_ID, GraveGraveyard.class);

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
		

		try {
			GraveGraveyardHome ggh = (GraveGraveyardHome) IDOLookup.getHome(GraveGraveyard.class);
			GraveGraveyard gg = ggh.findByPrimaryKey(new Integer(2));
			data1.setGraveGraveyard(gg);
		} catch (Exception e){
			System.out.println("we got problems when tried to get GraveGraveyard gg = ggh.findByPrimaryKey(new Integer(2))");
			data1.setGraveGraveyard(null); 	
		}

		data1.setDepartment("Rad 12");			
		data1.setBlock("Kv 4");			
		data1.setGraveNumber("0045");			
		data1.setParish(" parish1");		
		data1.setCommune(" commune1");
		data1.setCountry(" country1");		
		
		data1.store();

		
		GraveLocallyStored data2 = home.create();
		data2.setFirstName("Anna ");
		data2.setLastName("Brigadere");			
		data2.setDateOfBirth(Date.valueOf("1941-03-02"));			
		data2.setDateOfDeath(Date.valueOf("1975-04-02"));

		data2.setHomeTown("Ystad");
		data2.setBurialPlace("Stockholm");			
		


		try {
			GraveGraveyardHome ggh = (GraveGraveyardHome) IDOLookup.getHome(GraveGraveyard.class);
			GraveGraveyard gg = ggh.findByPrimaryKey(new Integer(2));
			data2.setGraveGraveyard(gg);
		} catch (Exception e){
			System.out.println("we got problems when tried to get GraveGraveyard gg = ggh.findByPrimaryKey(new Integer(2))");
			data2.setGraveGraveyard(null); 	
		}

				
		data2.setDepartment("Rad 15");			
		data2.setBlock("Kv 3");			
		data2.setGraveNumber("0015");			
		data2.setParish(" parish2");		
		data2.setCommune(" commune2");
		data2.setCountry(" country2");		
		
		data2.store();
		
			
	}

	public void setGraveGraveyard(GraveGraveyard graveGraveyard) {
		setColumn(COLUMN_NAME_GRAVEYARD_ID, graveGraveyard);
	}
	
	public GraveGraveyard getGraveGraveyard(GraveGraveyard graveGraveyard) {			
		return (GraveGraveyard) getColumnValue(COLUMN_NAME_GRAVEYARD_ID);	
	}

	
    public Integer getGraveId() {
		return graveId;
	}
	

	public void setGraveId(Integer graveId) {
		this.graveId = graveId;
	}
	

	public String getColumID() {
        return getStringColumnValue(COLUMN_NAME_ID);
    }		
	
	public String getFirstName() {
        return getStringColumnValue(COLUMN_NAME_FIRST_NAME);
    }		

	public String getHomeTown() {
        return getStringColumnValue(COLUMN_NAME_HOMETOWN);
    }		

	public String getGraveNumber() {
        return getStringColumnValue(COLUMN_NAME_GRAVE_NUMBER);
    }		

	public String getCemetery() {
		return getStringColumnValue("GRAVEYARD");
    }		
	public String getDepartment() {
        return getStringColumnValue(COLUMN_NAME_DEPARTMENT);
    }		

	public String getBurialPlace() {
        return getStringColumnValue(COLUMN_NAME_BURIAL_PLACE);
    }		
	
	public String getBlock() {
        return getStringColumnValue(COLUMN_NAME_BLOCK);
    }		

	public String getCountry() {
        return getStringColumnValue(COLUMN_NAME_COUNTRY);
    }		
	public String getCommune() {
        return getStringColumnValue(COLUMN_NAME_COMMUNE);
    }		
	public String getParish() {
        return getStringColumnValue(COLUMN_NAME_PARISH);
    }		

	
	public String getLastName() {
        return getStringColumnValue(COLUMN_NAME_LAST_NAME);
    }		

    public Date getDateOfBirth() {
		return getDateColumnValue(COLUMN_NAME_DATE_OF_BIRTH) ;		

    }		

    public Date getDateOfDeath() {
		return getDateColumnValue(COLUMN_NAME_DATE_OF_DEATH) ;

    }	

    public Date getDateOfBurial() {
		return getDateColumnValue(COLUMN_NAME_BURIAL_DATE) ;		

    }		

	
	public String getGraveGraveyardId() {			
		return getStringColumnValue(COLUMN_NAME_GRAVEYARD_ID);	
	}

	
	public void setFirstName(String firstName) {
		setColumn(COLUMN_NAME_FIRST_NAME, firstName);
	}

	public void setLastName(String lastName) {
		setColumn(COLUMN_NAME_LAST_NAME, lastName);
	}

	public void setParish(String parish) {
		setColumn(COLUMN_NAME_PARISH, parish);
	}

	public void setCountry(String country) {
		setColumn(COLUMN_NAME_COUNTRY, country);
	}
	
	public void setCommune(String commune) {
		setColumn(COLUMN_NAME_COMMUNE, commune);
	}

	public void setDateOfBirth(Date dateOfBirth) {
		setColumn(COLUMN_NAME_DATE_OF_BIRTH, dateOfBirth);
	}

	public void setDateOfDeath(Date dateOfDeath) {
		setColumn(COLUMN_NAME_DATE_OF_DEATH, dateOfDeath);
	}
	
	public void setDateOfBurial(Date dateOfBurial) {
		setColumn(COLUMN_NAME_BURIAL_DATE, dateOfBurial);
	}
	
	public void setHomeTown(String homeTown) {
		setColumn(COLUMN_NAME_HOMETOWN, homeTown);
	}

	public void setBurialPlace(String burialPlace) {
		setColumn(COLUMN_NAME_BURIAL_PLACE, burialPlace);
	}

	
	

	public void setGraveGraveyardId(Integer id) {			
		setColumn(COLUMN_NAME_GRAVEYARD_ID,id);	
	}

	
	public void setDepartment(String department) {
		setColumn(COLUMN_NAME_DEPARTMENT, department);
	}	

	public void setBlock(String block) {
		setColumn(COLUMN_NAME_BLOCK, block);
	}		

	public void setGraveNumber(String graveNumber) {
		setColumn(COLUMN_NAME_GRAVE_NUMBER, graveNumber);
	}			
			
	
	private void appendDateCriteria(CustomMemorialDate fromDate, 
            						CustomMemorialDate toDate, 
            						Column dateColumn, 
            						SelectQuery query) {
		
		if (fromDate.getValue() != null && toDate.getValue() != null) {
			
			CustomMemorialDate d1,d2;
			
			if ((fromDate.getMonth() == null) || (fromDate.getDay() == null)){
				d1 = new CustomMemorialDate(fromDate.getYear(),Integer.valueOf("1"),Integer.valueOf("1"));
			}
			else d1 = new CustomMemorialDate(fromDate.toDate());
			
			if ((toDate.getMonth() == null) || (toDate.getDay() == null)){
				d2 = new CustomMemorialDate(toDate.getYear(),Integer.valueOf("12"),Integer.valueOf("31"));
			}
			else d2 = new CustomMemorialDate(toDate.toDate());

			query.addCriteria(new MatchCriteria(dateColumn,	MatchCriteria.GREATEREQUAL, d1.getString()));
			query.addCriteria(new MatchCriteria(dateColumn,MatchCriteria.LESSEQUAL, d2.getString()));
			
		} else {
			if (fromDate.getValue() != null) {

				if ((fromDate.getMonth() == null) || (fromDate.getDay() == null )){
					CustomMemorialDate d1,d2;
					
					d1 = new CustomMemorialDate(fromDate.getYear(),Integer.valueOf("1"),Integer.valueOf("1"));
					d2 = new CustomMemorialDate(fromDate.getYear(),Integer.valueOf("12"),Integer.valueOf("31"));

					query.addCriteria(new MatchCriteria(dateColumn,	MatchCriteria.GREATEREQUAL, d1.getString()));
					query.addCriteria(new MatchCriteria(dateColumn,MatchCriteria.LESSEQUAL, d2.getString()));
				}

			} else if (toDate.getValue() != null) {
				CustomMemorialDate d1,d2;
				
				d1 = new CustomMemorialDate(toDate.getYear(),Integer.valueOf("1"),Integer.valueOf("1"));
				d2 = new CustomMemorialDate(toDate.getYear(),Integer.valueOf("12"),Integer.valueOf("31"));

				query.addCriteria(new MatchCriteria(dateColumn,	MatchCriteria.GREATEREQUAL, d1.getString()));
				query.addCriteria(new MatchCriteria(dateColumn,MatchCriteria.LESSEQUAL, d2.getString()));
			}
		}
	}


	
	public Collection ejbFindGraves(
									String firstName, 
									String lastName, 
									String personIdentifier, 
									CustomMemorialDate dateOfBirthFrom, 
									CustomMemorialDate dateOfBirthTo, 
									CustomMemorialDate dateOfDeathFrom, 
									CustomMemorialDate dateOfDeathTo, 
									String hometown,
									String graveyard) 
	throws FinderException, IDORelationshipException { 
		
	    Table table = new Table(ENTITY_NAME);
	    Table tableGraveyard = new Table(TABLE_GRAVEYARD);
		
		SelectQuery query = new SelectQuery(table);
	    
	    Column colGraveID = new Column(table, COLUMN_NAME_ID);
		Column colFirstName = new Column(table, COLUMN_NAME_FIRST_NAME);
		Column colLastName = new Column(table, COLUMN_NAME_LAST_NAME);		
		Column colDateOfBirth = new Column(table, COLUMN_NAME_DATE_OF_BIRTH);		
		Column colDateOfDeath = new Column(table, COLUMN_NAME_DATE_OF_DEATH);		
		Column colHomeTown = new Column(table, COLUMN_NAME_HOMETOWN);
		Column colGraveyard = new Column(tableGraveyard, COLUMN_NAME_GRAVEYARD_NAME);  
		
		String sqlStatement = query.toString();					
		
		query.addColumn(colGraveID);
		query.addColumn(colFirstName);
		query.addColumn(colLastName);
		query.addColumn(colDateOfBirth);
		query.addColumn(colDateOfDeath);
		query.addColumn(colGraveyard);			
		sqlStatement = query.toString();
		sqlStatement = query.toString();
		
		query.addJoin(table,COLUMN_NAME_GRAVEYARD_ID,tableGraveyard,"MS_GRAVE_GRAVEYARD_ID");

		sqlStatement = query.toString();					
		StringBuffer sqlQuery = new StringBuffer();
			
		if (graveyard != null)  query.addCriteria(new MatchCriteria(colGraveyard, MatchCriteria.LIKE, "%" + graveyard.trim() + "%"));
		if (hometown != null)  query.addCriteria(new MatchCriteria(colHomeTown, MatchCriteria.LIKE, "%" + hometown.trim() + "%"));
		
		appendDateCriteria(dateOfBirthFrom, dateOfBirthTo, colDateOfBirth, query);
		appendDateCriteria(dateOfDeathFrom, dateOfDeathTo, colDateOfDeath, query);
		
		Order orderByFirstName = new Order(colFirstName, true);
		Order orderByLastName = new Order(colLastName, true);		
		
		query.addOrder(orderByLastName);
		query.addOrder(orderByFirstName);		
		
		int maxResult = Utility.MAX_RESULT;
		
		Collection queries = Utility.getNameCriteriaQueries(firstName, lastName, colFirstName, colLastName, query,colGraveID);
		String str2 = queries.toString();

		Collection result = new ArrayList();
		Iterator iter = queries.iterator();
		while(result.size() < maxResult && iter.hasNext()){
			SelectQuery q = (SelectQuery)iter.next();
			result.addAll(this.idoFindPKsByQuery(q,maxResult-result.size()));
		}	
		return result;	
	}

	public String getEntityName() {		
		return ENTITY_NAME;
	}

	public Collection ejbFindByGraveID(String graveId) throws FinderException {

		IDOQuery query = this.idoQuery();
		query.appendSelectAllFrom(this).appendWhereEquals(COLUMN_NAME_ID, graveId);
		
		return this.idoFindPKsByQuery(query);
	}
	
	public String getGraveyardByID(String graveId) throws FinderException {

		Table tableGraveyard = new Table(TABLE_GRAVEYARD);
		SelectQuery query = new SelectQuery(tableGraveyard);

		Column colGraveyardId = new Column(tableGraveyard, COLUMN_GRAVEYARD_ID);  
		Column colGraveyard = new Column(tableGraveyard, COLUMN_GRAVEYARD_NAME);  
		
		query.addColumn(colGraveyard);			
		query.addCriteria(new MatchCriteria(colGraveyardId, MatchCriteria.EQUALS,graveId));
		String sql = query.toString();
		Connection conn = null;
		Statement Stmt = null;
		String result = null;

		try {
			conn = ConnectionBroker.getConnection(DATABASE_NAME);
			if (conn == null) {
				return result;
			}
			ResultSet RS = null;
			Stmt = conn.createStatement();
			RS = Stmt.executeQuery(query.toString());
			int count = 0;
			while (RS.next() && count <= 1) {
				result = RS.getString(COLUMN_GRAVEYARD_NAME);
				count++;
			}

			RS.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();

		} finally {
			if (Stmt != null) {
				try {
					Stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				ConnectionBroker.freeConnection(DATABASE_NAME, conn);
			}
		}

	
		return result;	
		
	
		
		
		
		
		
	}	
	
}

