package se.agura.memorial.search.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.FinderException;

import se.agura.memorial.search.api.Grave;
import se.agura.memorial.search.api.GraveInformation;
import se.agura.memorial.search.api.Graveyard;

import com.idega.data.GenericEntity;
import com.idega.data.IDOLookup;
import com.idega.data.query.Column;
import com.idega.data.query.MatchCriteria;
import com.idega.data.query.Order;
import com.idega.data.query.SelectQuery;
import com.idega.data.query.Table;
import com.idega.data.query.WildCardColumn;
import com.idega.util.database.ConnectionBroker;


/**
 * @author Igors
 *
 */
public class MalmoSearchBMPBean extends GenericEntity implements MalmoSearch{

	private final static String ENTITY_NAME = "GA_GRAVSATT";
	
	private final static String DATABASE_NAME="malmo";
	
	private static String TABLE_NAME = "GA_Gravsatt";
	private static String COL_API_DB_CONNECTION = "";

	public static final String COLUMN_NAME_GRAVE_ID = "Grav_ID";
	public static final String COLUMN_NAME_LOP_NR = "LOPNR";

	public static final String COLUMN_NAME_FIRST_NAME = "Fornamn";
	public static final String COLUMN_NAME_LAST_NAME = "Efternamn";
	public static final String COLUMN_NAME_DATE_OF_BIRTH = "Dodsdatum";
	public static final String COLUMN_NAME_DATE_OF_DEATH = "Begr_Datum";
	public static final String COLUMN_NAME_HOME_TOWN = "HOMETOWN";
	public static final String COLUMN_NAME_BURIAL_PLACE = "BURIAL_PLACE";
	public static final String COLUMN_NAME_CEMETERY = "CEMETERY";
	public static final String COLUMN_NAME_DEPARTMENT = "DEPARTMENT";
	public static final String COLUMN_NAME_BLOCK = "BLOCK";
	public static final String COLUMN_NAME_GRAVE_NUMBER = "GRAVE_NUMBER";
	public static final String COLUMN_NAME_ID = "ID";	
	public static final String COLUMN_NAME_KGARD_ID = "KGARD_ID";	
	public static final String COLUMN_NAME_AVDELNING_ID = "AVDELNING_ID";	
	public static final String COLUMN_NAME_KVARTER_KGARD_ID = "KVARTER_KGARD_ID";	
	public static final String COLUMN_NAME_KVARTER_LOPNR = "KVARTER_LOPNR";	
	public static final String COLUMN_NAME_BENAMNING = "BENAMNING";	
	public static final String COLUMN_KGARD_ID="KGARD_ID";
	private final static String TABLE_NAME_GA_GRAVSATT = "GA_Gravsatt";	
	private final static String TABLE_NAME_GA_GRAV = "GA_Grav";
	private final static String TABLE_NAME_GA_AVDELNING = "GA_AVDELNING";
	private final static String TABLE_NAME_GA_KVARTER = "GA_KVARTER";
	private final static String TABLE_NAME_GA_KGARD = "GA_KGARD";
	private final static String TABLE_NAME_SYS_LKF = "SYS_LKF";	
	

	
	
	public MalmoSearchBMPBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public String getEntityName() {
		
		return ENTITY_NAME;
	}

	public void initializeAttributes() {
		
		addAttribute(getIDColumnName());
		addAttribute(COLUMN_NAME_GRAVE_ID, "grav ID  ", true, true, Integer.class);
		addAttribute(COLUMN_NAME_LOP_NR, "Lop Nr ", true, true, Integer.class);		
		addAttribute(COLUMN_NAME_FIRST_NAME, "person first name  ", true, true, String.class, 40);
		addAttribute(COLUMN_NAME_LAST_NAME, "person last name ", true, true, String.class, 25);
		addAttribute(COLUMN_NAME_DATE_OF_BIRTH, "Date of birth ", true, true, Timestamp.class); 
		addAttribute(COLUMN_NAME_DATE_OF_DEATH, "Date of death ", true, true, Timestamp.class);
		addAttribute(COLUMN_NAME_HOME_TOWN, "Place of birth ", true, true, String.class, 50);
		addAttribute(COLUMN_NAME_BURIAL_PLACE, "Place of dead ", true, true, String.class, 50);
		addAttribute(COLUMN_NAME_CEMETERY, "Name of cemetery ", true, true, String.class, 30);
		addAttribute(COLUMN_NAME_DEPARTMENT, "Description of the departament ", true, true, String.class, 30);
		addAttribute(COLUMN_NAME_BLOCK, "Name of block ", true, true, String.class, 30);
		addAttribute(COLUMN_NAME_GRAVE_NUMBER, "Grave number ", true, true, String.class, 15);

		

	}

    
	  
	public void insertStartData() throws Exception {
		
		MalmoSearchHome home = (MalmoSearchHome) IDOLookup.getHome(MalmoSearch.class);

		MalmoSearch data1 = home.create();
		data1.setFirstName("AAA");
		data1.setLastName("BBB");
		data1.store();
		
		MalmoSearch data2 = home.create();
		data2.setFirstName("A");
		data2.setLastName("B");
		
		data2.store();

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
		return COLUMN_NAME_HOME_TOWN;
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
			
	

	public List getGraveyards(String database) {

		String sqlStatement = "select * from ga_kgard order by Benamning";
		database = "malmo"; // for TEST ONLY 
		Connection conn = null;
		Statement Stmt = null;
		List result = new ArrayList();

		try {
			conn = ConnectionBroker.getConnection(database);
			ResultSet RS = null;
			Stmt = conn.createStatement();
			RS = Stmt.executeQuery(sqlStatement);
			int count = 0;
			while (RS.next() && count <= 100) {
				Graveyard g = new Graveyard(RS.getInt("ID"),
						                    RS.getString("KGard"),
						                    RS.getString("Benamning"),
						                    RS.getInt("Distrikt_ID"));
				count++;
			    result.add(g);
//				System.out.println(g.getBenamning());

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
				ConnectionBroker.freeConnection(database, conn);
			}
		}
		return result;

	}

	public Collection ejbFindAll() throws FinderException {

		Table table = new Table(TABLE_NAME_GA_GRAVSATT);
        SelectQuery query = new SelectQuery(table);
        query.addColumn(new WildCardColumn()); 
		
        
        return this.idoFindPKsByQuery(query);
    }

	
	public Collection findGraves(String firstName, String lastName,
			String personIdentifier,
			String dateOfBirthFrom, String dateOfBirthTo,
			String dateOfDeathFrom, String dateOfDeathTo, String homeTown,
			String graveyard, String database)  throws FinderException {
		
		String sqlStatement=null;		
		Collection result = new ArrayList();;
		
		//database = "malmo"; // TEST MODE ONLY
		
	    Table table = new Table(this);
	    Column colGraveID = new Column(table, COLUMN_NAME_GRAVE_ID);
		Column colLopNr = new Column(table, COLUMN_NAME_LOP_NR);
		Column colFirstName = new Column(table, COLUMN_NAME_FIRST_NAME);
		Column colLastName = new Column(table, COLUMN_NAME_LAST_NAME);		
		Column colDateOfBirth = new Column(table, COLUMN_NAME_DATE_OF_BIRTH);		
		Column colDateOfDeath = new Column(table, COLUMN_NAME_DATE_OF_DEATH);		
		
		Order orderByFirstName = new Order(colFirstName, true);
		Order orderByLastName = new Order(colLastName, true);		

		SelectQuery query = new SelectQuery(table);
		query.addColumn(colGraveID);
		query.addColumn(colLopNr);
		query.addColumn(colFirstName);
		query.addColumn(colLastName);
		query.addColumn(colDateOfBirth);
		query.addColumn(colDateOfDeath);

		sqlStatement=query.toString();					
		
		StringBuffer sqlQuery = new StringBuffer();
			
		if ((graveyard != null) || (homeTown != null)) {
			
		    Table tableGA_Grav = new Table(TABLE_NAME_GA_GRAV);
		    Table tableGA_Kvarter = new Table(TABLE_NAME_GA_KVARTER);
		    Table tableGA_Avdelning = new Table(TABLE_NAME_GA_AVDELNING);
		    Table tableGA_KGard = new Table(TABLE_NAME_GA_KGARD);
			Column colGraveyard = new Column(tableGA_KGard, COLUMN_NAME_BENAMNING);			
			Column colHomeTown = new Column(table, COLUMN_NAME_HOME_TOWN);

			query.addJoin(table,COLUMN_NAME_GRAVE_ID,tableGA_Grav,COLUMN_NAME_ID);
			query.addJoin(tableGA_Grav,COLUMN_NAME_AVDELNING_ID,tableGA_Avdelning,COLUMN_NAME_ID);
			query.addJoin(tableGA_Grav,COLUMN_NAME_KVARTER_KGARD_ID,tableGA_Kvarter,COLUMN_NAME_KGARD_ID);
			query.addJoin(tableGA_Grav,COLUMN_NAME_KVARTER_LOPNR,tableGA_Kvarter,COLUMN_NAME_LOP_NR);
			query.addJoin(tableGA_Kvarter,COLUMN_NAME_KGARD_ID,tableGA_KGard,COLUMN_NAME_ID);
			query.addJoin(tableGA_Avdelning,COLUMN_NAME_KVARTER_KGARD_ID,tableGA_Kvarter,COLUMN_KGARD_ID);
			query.addJoin(tableGA_Avdelning,COLUMN_NAME_KVARTER_LOPNR,tableGA_Kvarter,COLUMN_NAME_LOP_NR);
			
			if (graveyard != null)  query.addCriteria(new MatchCriteria(colGraveyard, MatchCriteria.LIKE, "%" + graveyard.trim() + "%"));
			if (homeTown != null)  query.addCriteria(new MatchCriteria(colHomeTown, MatchCriteria.LIKE, "%" + homeTown.trim() + "%"));
			
        }
            
		if (firstName != null)  query.addCriteria(new MatchCriteria(colFirstName, MatchCriteria.LIKE, "%" + firstName.trim() + "%"));
		if (lastName != null)  query.addCriteria(new MatchCriteria(colLastName, MatchCriteria.LIKE, "%" + lastName.trim() + "%"));
		if (dateOfBirthFrom != null)  query.addCriteria(new MatchCriteria(colDateOfBirth, MatchCriteria.LIKE, dateOfBirthFrom.trim() + "%"));
		if (dateOfBirthTo != null)  query.addCriteria(new MatchCriteria(colDateOfBirth, MatchCriteria.LIKE, dateOfBirthFrom.trim() + "%"));
		if (dateOfDeathFrom != null)  query.addCriteria(new MatchCriteria(colDateOfDeath, MatchCriteria.LIKE, dateOfDeathFrom.trim() + "%"));
		if (dateOfDeathTo != null)  query.addCriteria(new MatchCriteria(colDateOfDeath, MatchCriteria.LIKE, "%" + dateOfDeathTo.trim() + "%"));
		
		query.addOrder(orderByLastName);
		query.addOrder(orderByFirstName);
		
		

		sqlStatement=query.toString();					
		Connection conn = null;
		Statement Stmt = null;

		try {
			conn = ConnectionBroker.getConnection(database);
			ResultSet RS = null;
			Stmt = conn.createStatement();
			RS = Stmt.executeQuery(sqlStatement);

			int count = 0;
			while (RS.next() && count <= 100) {
				GraveInformation info = new GraveInformation();
				info.init(RS.getInt(COLUMN_NAME_GRAVE_ID),
						  RS.getInt(COLUMN_NAME_LOP_NR),
						  RS.getString(COLUMN_NAME_FIRST_NAME), 
						  RS.getString(COLUMN_NAME_LAST_NAME),
						  RS.getString(COLUMN_NAME_DATE_OF_BIRTH),
						  RS.getString(COLUMN_NAME_DATE_OF_DEATH),
						  count+1);
				
				count++;
				result.add(info);
//				System.out.println(info.getRowNr()+" "+info.getLastName()+" "+info.getFirstName());
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
				ConnectionBroker.freeConnection(database, conn);
			}
		}
		return result;
	}
	
	
	public Collection ejbFindGravesTST(String firstName, String lastName,
			String personIdentifier,
			String dateOfBirthFrom, String dateOfBirthTo,
			String dateOfDeathFrom, String dateOfDeathTo, String homeTown,
			String graveyard, String database)  throws FinderException {
		
		String sqlStatement=null;		
		Collection result = new ArrayList();;
		
	    Table table = new Table(this);
	    Column colGraveID = new Column(table, COLUMN_NAME_GRAVE_ID);
		Column colLopNr = new Column(table, COLUMN_NAME_LOP_NR);
		Column colFirstName = new Column(table, COLUMN_NAME_FIRST_NAME);
		Column colLastName = new Column(table, COLUMN_NAME_LAST_NAME);		
		Column colDateOfBirth = new Column(table, COLUMN_NAME_DATE_OF_BIRTH);		
		Column colDateOfDeath = new Column(table, COLUMN_NAME_DATE_OF_DEATH);		
		
		Order orderByFirstName = new Order(colFirstName, true);
		Order orderByLastName = new Order(colLastName, true);		

		SelectQuery query = new SelectQuery(table);
		query.addColumn(colGraveID);
		query.addColumn(colLopNr);
		query.addColumn(colFirstName);
		query.addColumn(colLastName);
		query.addColumn(colDateOfBirth);
		query.addColumn(colDateOfDeath);
		StringBuffer sqlQuery = new StringBuffer();

		if ((graveyard != null) || (homeTown != null)) {
			
		    Table tableGA_Grav = new Table(TABLE_NAME_GA_GRAV);
		    Table tableGA_Kvarter = new Table(TABLE_NAME_GA_KVARTER);
		    Table tableGA_Avdelning = new Table(TABLE_NAME_GA_AVDELNING);
		    Table tableGA_KGard = new Table(TABLE_NAME_GA_KGARD);

			Column colGraveyard = new Column(tableGA_KGard, COLUMN_NAME_BENAMNING);			
			Column colHomeTown = new Column(table, COLUMN_NAME_HOME_TOWN);

			query.addJoin(table,COLUMN_NAME_GRAVE_ID,tableGA_Grav,COLUMN_NAME_ID);
			query.addJoin(tableGA_Grav,COLUMN_NAME_AVDELNING_ID,tableGA_Avdelning,COLUMN_NAME_ID);
			query.addJoin(tableGA_Grav,COLUMN_NAME_KVARTER_KGARD_ID,tableGA_Kvarter,COLUMN_NAME_KGARD_ID);
			query.addJoin(tableGA_Grav,COLUMN_NAME_KVARTER_LOPNR,tableGA_Kvarter,COLUMN_NAME_LOP_NR);
			query.addJoin(tableGA_Kvarter,COLUMN_NAME_KGARD_ID,tableGA_KGard,COLUMN_NAME_ID);
			query.addJoin(tableGA_Avdelning,COLUMN_NAME_KVARTER_KGARD_ID,tableGA_Kvarter,COLUMN_KGARD_ID);
			query.addJoin(tableGA_Avdelning,COLUMN_NAME_KVARTER_LOPNR,tableGA_Kvarter,COLUMN_NAME_LOP_NR);
			
			
			
			if (graveyard != null)  query.addCriteria(new MatchCriteria(colGraveyard, MatchCriteria.LIKE, "%" + graveyard.trim() + "%"));
			if (homeTown != null)  query.addCriteria(new MatchCriteria(colHomeTown, MatchCriteria.LIKE, "%" + homeTown.trim() + "%"));
			
        }
            
		if (firstName != null)  query.addCriteria(new MatchCriteria(colFirstName, MatchCriteria.LIKE, "%" + firstName.trim() + "%"));
		if (lastName != null)  query.addCriteria(new MatchCriteria(colLastName, MatchCriteria.LIKE, "%" + lastName.trim() + "%"));
		if (dateOfBirthFrom != null)  query.addCriteria(new MatchCriteria(colDateOfBirth, MatchCriteria.LIKE, dateOfBirthFrom.trim() + "%"));
		if (dateOfBirthTo != null)  query.addCriteria(new MatchCriteria(colDateOfBirth, MatchCriteria.LIKE, dateOfBirthFrom.trim() + "%"));
		if (dateOfDeathFrom != null)  query.addCriteria(new MatchCriteria(colDateOfDeath, MatchCriteria.LIKE, dateOfDeathFrom.trim() + "%"));
		if (dateOfDeathTo != null)  query.addCriteria(new MatchCriteria(colDateOfDeath, MatchCriteria.LIKE, "%" + dateOfDeathTo.trim() + "%"));
		
		query.addOrder(orderByLastName);
		query.addOrder(orderByFirstName);
		
		return this.idoFindPKsByQuery(query);

	}

	
public  Grave findGrave(int gravID,int lopNr,String database) {
	
		StringBuffer sqlQuery = new StringBuffer();
		Grave info = new Grave(); 
		database = "malmo"; // TEST MODE ONLY
		String sqlStatement = "select ";
		
		sqlStatement += " GA_Gravsatt.Grav_ID graveID,";
		sqlStatement += " GA_Gravsatt.LopNR lopNr,";			
		sqlStatement += " GA_Gravsatt.FORNAMN firstName,";
		sqlStatement += " GA_Gravsatt.EFTERNAMN lastName,";
		sqlStatement += " GA_Gravsatt.PERS_NR dateOfBirth,";
		sqlStatement += " GA_Gravsatt.DODSDATUM  dateOfDeath,";
		sqlStatement += " GA_Gravsatt.HEMORT homeTown,";
		//sqlStatement += " '???' burialPlace,";
		sqlStatement += " GA_Gravsatt.HEMORT burialPlace,";
		
		sqlStatement += " GA_KGard.BENAMNING cemetery,";
		sqlStatement += " GA_Avdelning.BENAMNING department,";
		sqlStatement += " GA_KVarter.BENAMNING blockName,";
		sqlStatement += " GA_Grav.GRAVNUMMER gravyNumber";
		sqlStatement += " From GA_Gravsatt,GA_KGard,GA_KVarter,GA_Avdelning,GA_Grav";

		sqlStatement += " where GA_Gravsatt.Grav_ID="+gravID;
		sqlStatement += " and GA_Gravsatt.LOPNR="+lopNr;		
		
		sqlStatement += " and GA_Gravsatt.GRAV_ID=GA_Grav.ID ";
		sqlStatement += " and GA_Grav.Avdelning_ID=GA_Avdelning.ID ";
		sqlStatement += " and GA_Grav.Kvarter_Kgard_ID=GA_Kvarter.Kgard_ID "; 
		sqlStatement += " and GA_Grav.Kvarter_Lopnr=GA_Kvarter.Lopnr "; 
		sqlStatement += " and GA_Kvarter.KGard_ID=GA_KGard.ID "; 	
		sqlStatement += " and GA_Avdelning.Kvarter_KGard_ID=GA_KVarter.KGard_ID ";
		sqlStatement += " and GA_Avdelning.Kvarter_Lopnr=GA_KVarter.Lopnr";
		sqlStatement += " order by GA_Gravsatt.LopNR,GA_Gravsatt.Grav_ID";		

		Connection conn = null;
		Statement Stmt = null;

		try {
			conn = ConnectionBroker.getConnection(database);
			ResultSet RS = null;
			Stmt = conn.createStatement();
			RS = Stmt.executeQuery(sqlStatement);

			int count = 0;
			while (RS.next() && count <= 300) {
				info.init(RS.getInt("graveID"),
						  RS.getInt("lopNr"),
 					      RS.getString("firstName"),
						  RS.getString("lastName"),
						  RS.getString("dateOfBirth"),
						  RS.getString("dateOfDeath"),
						  RS.getString("homeTown"),
						  RS.getString("burialPlace"),
						  RS.getString("cemetery"),
						  RS.getString("department"),
						  RS.getString("blockName"),						    
						  RS.getString("gravyNumber"));
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
				ConnectionBroker.freeConnection(database, conn);
			}
		}
        return info;
	}
}
