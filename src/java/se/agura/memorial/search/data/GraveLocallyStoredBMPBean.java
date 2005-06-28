package se.agura.memorial.search.data;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.ejb.FinderException;

import se.agura.memorial.search.util.Utility;

import com.idega.data.GenericEntity;
import com.idega.data.IDOLookup;
import com.idega.data.IDOQuery;
import com.idega.data.IDORelationshipException;
import com.idega.data.query.Column;
import com.idega.data.query.MatchCriteria;
import com.idega.data.query.Order;
import com.idega.data.query.SelectQuery;
import com.idega.data.query.Table;

public class GraveLocallyStoredBMPBean extends GenericEntity  implements GraveLocallyStored{

	public static String ENTITY_NAME = "MS_GRAVE_LOCALLY_STORED";
	public static String TABLE_GRAVEYARD = "MS_GRAVE_GRAVEYARD";

	public static String COL_API_DB_CONNECTION = "default";		
	
	public static final String DATABASE_NAME = "dafault";		

	public static final String COLUMN_NAME_ID = "MS_GRAVE_LOCALLY_STORED_ID";
	public static final String COLUMN_NAME_FIRST_NAME = "FIRST_NAME";
	public static final String COLUMN_NAME_LAST_NAME = "LAST_NAME";
	public static final String COLUMN_NAME_DATE_OF_BIRTH = "DATE_OF_BIRTH";
	public static final String COLUMN_NAME_DATE_OF_DEATH = "DATE_OF_DEATH";
	public static final String COLUMN_NAME_HOMETOWN = "HOMETOWN";
	public static final String COLUMN_NAME_BURIAL_PLACE = "BURIAL_PLACE";
	//public static final String COLUMN_NAME_CEMETERY = "GRAVEYARD_NAME";
	public static final String COLUMN_NAME_GRAVEYARD_ID = "GRAVEYARD_ID";		
	public static final String COLUMN_NAME_DEPARTMENT = "DEPARTMENT";
	public static final String COLUMN_NAME_BLOCK = "BLOCK";
	public static final String COLUMN_NAME_GRAVE_NUMBER = "GRAVE_NUMBER";
	private static final String COLUMN_NAME_GRAVEYARD_NAME = "graveyard_name";

	public GraveLocallyStoredBMPBean() {
		super();
	}

	public void initializeAttributes() {

		addAttribute(getIDColumnName());
		addAttribute(COLUMN_NAME_FIRST_NAME, "person first name  ", true, true,	String.class, 40);
		addAttribute(COLUMN_NAME_LAST_NAME, "person last name ", true, true, String.class, 25);
		addAttribute(COLUMN_NAME_DATE_OF_BIRTH, "Date of birth ", true, true, Date.class);
		addAttribute(COLUMN_NAME_DATE_OF_DEATH, "Date of death ", true, true,Date.class);
		addAttribute(COLUMN_NAME_HOMETOWN, "Place of birth ", true, true,String.class, 50);
		addAttribute(COLUMN_NAME_BURIAL_PLACE, "Place of dead ", true, true,String.class, 50);
		addAttribute(COLUMN_NAME_DEPARTMENT, "Description of the departament ",	true, true, String.class, 30);
		addAttribute(COLUMN_NAME_BLOCK, "Name of block ", true, true,String.class, 30);
		addAttribute(COLUMN_NAME_GRAVE_NUMBER, "Grave number ", true, true,	String.class, 15);
		addManyToOneRelationship(COLUMN_NAME_GRAVEYARD_ID, GraveGraveyard.class);

	}

//	public void insertStartData() throws Exception {
//		GraveLocallyStoredHome home = (GraveLocallyStoredHome) IDOLookup.getHome(GraveLocallyStored.class);
//
//		GraveLocallyStored data1 = home.create();
//		data1.setFirstName("Anders Beril Ove");
//		data1.setLastName("Andersson");			
//		data1.setDateOfBirth(Date.valueOf("1945-03-02"));			
//		data1.setDateOfDeath(Date.valueOf("2001-01-02"));
//
//		data1.setHomeTown("Ystad");
//		data1.setBurialPlace("Stockholm");			
//		
//
//		try {
//			GraveGraveyardHome ggh = (GraveGraveyardHome) IDOLookup.getHome(GraveGraveyard.class);
//			GraveGraveyard gg = ggh.findByPrimaryKey(new Integer(2));
//			data1.setGraveGraveyard(gg);
//		} catch (Exception e){
//			System.out.println("we got problems when tried to get GraveGraveyard gg = ggh.findByPrimaryKey(new Integer(2))");
//			data1.setGraveGraveyard(null); 	
//		}
//				
//		data1.setDepartment("Rad 12");			
//		data1.setBlock("Kv 4");			
//		data1.setGraveNumber("0045");			
//		
//		data1.store();
//			
//	}


    public String getColumID() {
        return getStringColumnValue(COLUMN_NAME_ID);
    }		
	
	public String getFirstName() {
        return getStringColumnValue(COLUMN_NAME_FIRST_NAME);
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


	public void setFirstName(String firstName) {
		setColumn(COLUMN_NAME_FIRST_NAME, firstName);
	}

	public void setLastName(String lastName) {
		setColumn(COLUMN_NAME_LAST_NAME, lastName);
	}

	public void setDateOfBirth(Date dateOfBirth) {
		setColumn(COLUMN_NAME_DATE_OF_BIRTH, dateOfBirth);
	}

	public void setDateOfDeath(Date dateOfDeath) {
		setColumn(COLUMN_NAME_DATE_OF_DEATH, dateOfDeath);
	}
	
	
	public void setHomeTown(String homeTown) {
		setColumn(COLUMN_NAME_HOMETOWN, homeTown);
	}

	public void setBurialPlace(String burialPlace) {
		setColumn(COLUMN_NAME_BURIAL_PLACE, burialPlace);
	}

	
	public void setGraveGraveyard(GraveGraveyard graveGraveyard) {
		setColumn(COLUMN_NAME_GRAVEYARD_ID, graveGraveyard);
	}
	
	public GraveGraveyard getGraveGraveyard(GraveGraveyard graveGraveyard) {			
		return (GraveGraveyard) getColumnValue(COLUMN_NAME_GRAVEYARD_ID);	
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
			
	public Collection ejbFindGraves(
									String firstName, 
									String lastName, 
									String personIdentifier, 
									String dateOfBirthFrom, 
									String dateOfBirthTo, 
									String dateOfDeathFrom, 
									String dateOfDeathTo, 
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
		if(dateOfBirthTo != null){
		  // TODO search from - to
		} else if(dateOfBirthFrom != null){
			//TODO
//			query.addCriteria(new MatchCriteria(colDateOfBirth, MatchCriteria.EQUALS, Utility.stringToSQLDate(dateOfBirthFrom).toString()));
		}
		
		if (dateOfDeathTo != null){
			  // TODO search from - to
		} else if(dateOfDeathFrom != null) {
			//TODO
//			query.addCriteria(new MatchCriteria(colDateOfDeath, MatchCriteria.LIKE, dateOfDeathFrom.trim() + "%"));
		}

		
		Order orderByFirstName = new Order(colFirstName, true);
		Order orderByLastName = new Order(colLastName, true);		
		
		query.addOrder(orderByLastName);
		query.addOrder(orderByFirstName);		
		
		
		Collection queries = Utility.getNameCriteriaQueries(firstName, lastName, colFirstName, colLastName, query,colGraveID);

		int maxResult = Utility.MAX_RESULT;
		Collection result = new ArrayList();
		Iterator iter = queries.iterator();
		while(result.size() < maxResult && iter.hasNext()){
			SelectQuery q = (SelectQuery)iter.next();
			System.out.println();
			System.out.println(q);
			System.out.println();
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
	
	
}

