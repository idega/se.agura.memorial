package se.agura.memorial.search.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import com.idega.data.query.Column;
import com.idega.data.query.MatchCriteria;
import com.idega.data.query.Order;
import com.idega.data.query.SelectQuery;
import com.idega.data.query.Table;
import com.idega.util.database.ConnectionBroker;
import se.agura.memorial.search.api.Grave;
import se.agura.memorial.search.api.GraveInformation;
import se.agura.memorial.search.api.Graveyard;
import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.search.presentation.ObituaryItemBean;
import se.agura.memorial.search.util.Utility;

public class MalmoChurchSearch implements ObituarySearch {
    
	private static final String DATABASE = "malmo";
	private static String TABLE_NAME = "GA_Gravsatt";

	public static final String COLUMN_NAME_GRAVE_ID = "Grav_ID";
	public static final String COLUMN_NAME_LOP_NR = "LOPNR";
	public static final String COLUMN_NAME_FIRST_NAME = "Fornamn";
	public static final String COLUMN_NAME_LAST_NAME = "Efternamn";
	public static final String COLUMN_NAME_PERSON_IDENTIFIER = "Pers_nr";
	public static final String COLUMN_NAME_DATE_OF_DEATH = "Dodsdatum";
	public static final String COLUMN_NAME_BURIAL_DATE = "Begr_datum";
	public static final String COLUMN_NAME_HOME_TOWN = "HEMORT";
	public static final String COLUMN_NAME_BURIAL_PLACE = "BURIAL_PLACE";
	public static final String COLUMN_NAME_CEMETERY = "CEMETERY";
	public static final String COLUMN_NAME_DEPARTMENT = "BENAMNING AS DEPARTMENT";
	public static final String COLUMN_NAME_BLOCK = "BENAMNING AS BLOCK";
	public static final String COLUMN_NAME_GRAVE_NUMBER = "GRAVNUMMER";
	public static final String COLUMN_NAME_GRAVEYARD = "BENAMNING AS CEMETERY";
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
	private int maxResult = Utility.MAX_RESULT;	

				
	public Collection findGraves(String firstName, String lastName, String personIdentifier, String dateOfBirthFrom, String dateOfBirthTo, String dateOfDeathFrom, String dateOfDeathTo, String hometown, String graveyard) {
		String sqlStatement=null;		
		Collection result = new ArrayList();

		//TODO Temp: remove asap
		ObituaryItemBean aa = new ObituaryItemBean();
		aa.setBody("bla bla bla ");
		aa.setDatabaseId(Integer.valueOf("1"));
		aa.setGraveId(Integer.valueOf("3002"));
		aa.setGravePicturePath("wwwww");
		aa.setPresonPicturePath("eeeee");
		aa.store();
		
	    Table table = new Table(TABLE_NAME);
	    Column colGraveID = new Column(table, COLUMN_NAME_GRAVE_ID);
		Column colLopNr = new Column(table, COLUMN_NAME_LOP_NR);
		Column colFirstName = new Column(table, COLUMN_NAME_FIRST_NAME);
		Column colLastName = new Column(table, COLUMN_NAME_LAST_NAME);		
		Column colPersonIdentifier = new Column(table, COLUMN_NAME_PERSON_IDENTIFIER);		
		Column colDateOfDeath = new Column(table, COLUMN_NAME_DATE_OF_DEATH);		
		Column colDateOfBurial = new Column(table, COLUMN_NAME_BURIAL_DATE);
		
		Order orderByFirstName = new Order(colFirstName, true);
		Order orderByLastName = new Order(colLastName, true);		

		SelectQuery query = new SelectQuery(table);
		query.addColumn(colGraveID);
		query.addColumn(colLopNr);
		query.addColumn(colFirstName);
		query.addColumn(colLastName);
		query.addColumn(colPersonIdentifier);
		query.addColumn(colDateOfDeath);
		query.addColumn(colDateOfBurial);

		sqlStatement=query.toString();					
		
		StringBuffer sqlQuery = new StringBuffer();
			
		if ((graveyard != null) || (hometown != null)) {
			
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
			
			if (graveyard != null)  query.addCriteria(new MatchCriteria(colGraveyard, MatchCriteria.LIKE, graveyard.trim()));
			if (hometown != null)  query.addCriteria(new MatchCriteria(colHomeTown, MatchCriteria.LIKE, "%" + hometown.trim() + "%"));
        }
		
		/*date of birt is found from the person identifier*/
		appendDateCriteria(dateOfBirthFrom, dateOfBirthTo, colPersonIdentifier, query);
		appendDateCriteria(dateOfDeathFrom, dateOfDeathTo, colDateOfDeath, query);
		
		if(personIdentifier != null){
			query.addCriteria(new MatchCriteria(colPersonIdentifier,MatchCriteria.LIKE,personIdentifier));
		}
		
		query.addOrder(orderByLastName);
		query.addOrder(orderByFirstName);
		
		Collection queries = Utility.getNameCriteriaQueries(firstName, lastName, colFirstName, colLastName, query,colGraveID);
		
		Iterator iter = queries.iterator();
		while(result.size() < maxResult && iter.hasNext()){
			searchDatabase(result, (SelectQuery)iter.next());
		}		
		
		return result;
		
	}


	/**
	 * @param result
	 * @param query
	 */
	private void searchDatabase(Collection result, SelectQuery query) {
		String sqlStatement;
		sqlStatement=query.toString();
		
		System.out.println();
		System.out.println(sqlStatement);
		System.out.println();
		
		Connection conn = null;
		Statement Stmt = null;

		try {
			conn = ConnectionBroker.getConnection(DATABASE);
			ResultSet RS = null;
			Stmt = conn.createStatement();
			RS = Stmt.executeQuery(sqlStatement);

			int count = result.size();
			while (RS.next() && count < maxResult) {

				Grave grave = new Grave(
						RS.getString(COLUMN_NAME_GRAVE_ID)+":"+RS.getString(COLUMN_NAME_LOP_NR), 
						RS.getString(COLUMN_NAME_FIRST_NAME), 
						RS.getString(COLUMN_NAME_LAST_NAME), 
						Utility.stringToMemorialDate(RS.getString(COLUMN_NAME_PERSON_IDENTIFIER)), 
						Utility.stringToMemorialDate(RS.getString(COLUMN_NAME_DATE_OF_DEATH)), 
						Utility.stringToMemorialDate(RS.getString(COLUMN_NAME_BURIAL_DATE)),
						null, 
						null);
				
				count++;
				result.add(grave);
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
				ConnectionBroker.freeConnection(DATABASE, conn);
			}
		}
	}

	/**
	 * @param fromDate
	 * @param toDate
	 * @param dateColumn
	 * @param query
	 */
	private void appendDateCriteria(String fromDate, String toDate, Column dateColumn, SelectQuery query) {
		if ( fromDate != null && toDate != null){	
			query.addCriteria(new MatchCriteria(dateColumn,MatchCriteria.GREATEREQUAL,fromDate));
			query.addCriteria(new MatchCriteria(dateColumn,MatchCriteria.LESSEQUAL,toDate));
		}
		else if (fromDate != null) {
			query.addCriteria(new MatchCriteria(dateColumn, MatchCriteria.LIKE, fromDate.trim() + "%"));
		} else if (toDate != null){
			query.addCriteria(new MatchCriteria(dateColumn, MatchCriteria.LIKE, toDate.trim() + "%"));
		}
	}


	public Collection getGraveyards() {
		String sqlStatement = "select * from ga_kgard order by Benamning";
		
		Connection conn = null;
		Statement Stmt = null;
		List result = new ArrayList();

		try {
			conn = ConnectionBroker.getConnection(DATABASE);
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
				ConnectionBroker.freeConnection(DATABASE, conn);
			}
		}
		return result;


	}

	public String getLopNr(String str) {

		String[] fields = str.split(":");
		
		return fields[1]; 
	}

	public String getGravId(String str) {

		String[] fields = str.split(":");
		
		return fields[0]; 
	}

	
	
	public Grave findGrave(String graveId) {
		
		Table table = new Table(TABLE_NAME);		
	    Table tableGA_Grav = new Table(TABLE_NAME_GA_GRAV);
	    Table tableGA_Kvarter = new Table(TABLE_NAME_GA_KVARTER);
	    Table tableGA_Avdelning = new Table(TABLE_NAME_GA_AVDELNING);
	    Table tableGA_KGard = new Table(TABLE_NAME_GA_KGARD);

		SelectQuery query = new SelectQuery(table);

	    Column colGraveID = new Column(table, COLUMN_NAME_GRAVE_ID);
		Column colLopNr = new Column(table, COLUMN_NAME_LOP_NR);
		Column colFirstName = new Column(table, COLUMN_NAME_FIRST_NAME);
		Column colLastName = new Column(table, COLUMN_NAME_LAST_NAME);		
		Column colDateOfBirth = new Column(table, COLUMN_NAME_PERSON_IDENTIFIER);		
		Column colDateOfDeath = new Column(table, COLUMN_NAME_DATE_OF_DEATH);		
		Column colGraveyard = new Column(tableGA_KGard, COLUMN_NAME_GRAVEYARD);			
		Column colHomeTown = new Column(table, COLUMN_NAME_HOME_TOWN);
		//Column colBurialPlace = new Column(table, COLUMN_NAME_BURIAL_PLACE); TODO // correct BURIAL_PLACE !!!
		Column colBurialPlace = new Column(table, COLUMN_NAME_HOME_TOWN);		
		Column colDepartment = new Column(tableGA_Avdelning, COLUMN_NAME_DEPARTMENT);
		Column colBlock = new Column(tableGA_Kvarter, COLUMN_NAME_BLOCK);
		Column colGraveNumber = new Column(tableGA_Grav, COLUMN_NAME_GRAVE_NUMBER);			
		Column colDateOfBurial = new Column(table, COLUMN_NAME_BURIAL_DATE);
		
		
		query.addColumn(colGraveID);
		query.addColumn(colLopNr);
		query.addColumn(colFirstName);
		query.addColumn(colLastName);
		query.addColumn(colDateOfBirth);
		query.addColumn(colDateOfDeath);
		query.addColumn(colHomeTown);
		query.addColumn(colBurialPlace);		
		query.addColumn(colGraveyard);		
		query.addColumn(colDepartment);
		query.addColumn(colBlock);
		query.addColumn(colGraveNumber);	
		query.addColumn(colDateOfBurial);
		
		query.addJoin(table,COLUMN_NAME_GRAVE_ID,tableGA_Grav,COLUMN_NAME_ID);
		query.addJoin(tableGA_Grav,COLUMN_NAME_AVDELNING_ID,tableGA_Avdelning,COLUMN_NAME_ID);
		query.addJoin(tableGA_Grav,COLUMN_NAME_KVARTER_KGARD_ID,tableGA_Kvarter,COLUMN_NAME_KGARD_ID);
		query.addJoin(tableGA_Grav,COLUMN_NAME_KVARTER_LOPNR,tableGA_Kvarter,COLUMN_NAME_LOP_NR);
		query.addJoin(tableGA_Kvarter,COLUMN_NAME_KGARD_ID,tableGA_KGard,COLUMN_NAME_ID);
		query.addJoin(tableGA_Avdelning,COLUMN_NAME_KVARTER_KGARD_ID,tableGA_Kvarter,COLUMN_KGARD_ID);
		query.addJoin(tableGA_Avdelning,COLUMN_NAME_KVARTER_LOPNR,tableGA_Kvarter,COLUMN_NAME_LOP_NR);
		
		query.addCriteria(new MatchCriteria(colGraveID, MatchCriteria.EQUALS, getGravId(graveId)));
		query.addCriteria(new MatchCriteria(colLopNr, MatchCriteria.EQUALS, getLopNr(graveId)));		
		
		Grave grave = null;	
		
		Connection conn = null;
		Statement Stmt = null;

		try {
			conn = ConnectionBroker.getConnection(DATABASE);
			ResultSet RS = null;
			Stmt = conn.createStatement();
			RS = Stmt.executeQuery(query.toString());

			int count = 0;
			while (RS.next() && count <= 300) {
					
					grave = new Grave(
						RS.getString(COLUMN_NAME_GRAVE_ID)+":"+RS.getString(COLUMN_NAME_LOP_NR), 
						RS.getString(COLUMN_NAME_FIRST_NAME),    
						RS.getString(COLUMN_NAME_LAST_NAME), 
						Utility.stringToMemorialDate(RS.getString(COLUMN_NAME_PERSON_IDENTIFIER)), 
						Utility.stringToMemorialDate(RS.getString(COLUMN_NAME_DATE_OF_DEATH)), 
						Utility.stringToMemorialDate(RS.getString(COLUMN_NAME_BURIAL_DATE)),
						RS.getString(COLUMN_NAME_HOME_TOWN),
						new GraveInformation(
								RS.getString(COLUMN_NAME_GRAVE_NUMBER), 
								RS.getString("BLOCK"), 
								RS.getString("DEPARTMENT"), 
								RS.getString("CEMETERY")
								));
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
				ConnectionBroker.freeConnection(DATABASE, conn);
			}
		}
		
        return grave;
	}

	

   
}
