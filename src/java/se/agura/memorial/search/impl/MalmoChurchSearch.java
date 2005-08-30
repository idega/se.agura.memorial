package se.agura.memorial.search.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import se.agura.memorial.search.api.CustomMemorialDate;
import se.agura.memorial.search.api.Grave;
import se.agura.memorial.search.api.GraveInformation;
import se.agura.memorial.search.api.Graveyard;
import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.util.Utility;

import com.idega.data.query.Column;
import com.idega.data.query.MatchCriteria;
import com.idega.data.query.Order;
import com.idega.data.query.SelectQuery;
import com.idega.data.query.Table;
import com.idega.util.database.ConnectionBroker;

public class MalmoChurchSearch implements ObituarySearch {
    
	private static final String DATABASE = "malmo";
	private static String TABLE_NAME = "GA_Gravsatt";

	public static final String COLUMN_NAME_GRAVE_ID = "Grav_ID";
	public static final String COLUMN_NAME_LOP_NR = "LOPNR";
	public static final String COLUMN_NAME_LKF = "Old_LKF";	
	public static final String COLUMN_NAME_FIRST_NAME = "Old_Fornamn";
	public static final String COLUMN_NAME_LAST_NAME = "Old_Efternamn";
	public static final String COLUMN_NAME_PERSON_IDENTIFIER = "Old_Pers_nr";
	public static final String COLUMN_NAME_DATE_OF_DEATH = "Dodsdatum";
	public static final String COLUMN_NAME_BURIAL_DATE = "Begr_datum";
	public static final String COLUMN_NAME_HOME_TOWN = "Old_HEMORT";
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

				
	public Collection findGraves(String firstName, 
			                     String lastName, 
			                     String personIdentifier, 
			                     CustomMemorialDate dateOfBirthFrom, 
								 CustomMemorialDate dateOfBirthTo,
								 CustomMemorialDate dateOfDeathFrom,
								 CustomMemorialDate dateOfDeathTo,
								 String hometown, String graveyard) {
		String sqlStatement=null;		
		Collection result = new ArrayList();

		//TODO Temp: remove asap
//		ObituaryItemBean aa = new ObituaryItemBean();
//		aa.setBody("bla bla bla ");
//		aa.setDatabaseId(Integer.valueOf("1"));
//		aa.setGraveId(Integer.valueOf("3002"));
//		aa.setGravePicturePath("wwwww");
//		aa.setPersonPicturePath("eeeee");
		//aa.store();
//		try {

		//aa.load("http://localhost:8080/memorial/content/files/cms/obituary/1/3002.obituary/en.txt");
//		aa.load("/files/cms/obituary/1/3002.obituary/en.txt");		
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}

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
			
		if ((graveyard != null)
  		 || (hometown != null)
//		 || (firstName != null)
//		 || (lastName != null)
//		 || (dateOfBirthFrom != null)		 
//		 || (dateOfDeathFrom != null)
		 
		) {
			
			
			
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
		
		String str1 = query.toString();
		
		Collection queries = Utility.getNameCriteriaQueries(firstName, lastName, colFirstName, colLastName, query,colGraveID);
		String str2 = queries.toString();
		Iterator iter = queries.iterator();
		while(result.size() < maxResult && iter.hasNext()){
			searchDatabase(result, (SelectQuery)iter.next());
		}		
		
		return result;
		
	}

	
	public Collection getSelectedYears() {
		
		Collection result = new ArrayList();
        result = null;
		
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
	private void appendDateCriteria(CustomMemorialDate fromDate, 
			                        CustomMemorialDate toDate, 
			                        Column dateColumn, 
			                        SelectQuery query) {
		if ( fromDate.getValue() != null && toDate.getValue() != null){
			
			query.addCriteria(new MatchCriteria(dateColumn,MatchCriteria.GREATEREQUAL,fromDate.getYear().toString()+"????"));
			query.addCriteria(new MatchCriteria(dateColumn,MatchCriteria.LESSEQUAL,toDate.getYear().toString()+"XXXX"));
		}
		else{
			String str = "";
			if (fromDate.getValue() != null) {
				str = fromDate.getYear().toString();
				
				if (fromDate.getMonth() != null){   
					if(fromDate.getMonth().intValue() < 10) str = str + "0";
					str = str + fromDate.getMonth().toString();
				}

				if ( (fromDate.getDay()!=null) ){
						if (fromDate.getDay().intValue()<10) str = str + "0";
						str = str + fromDate.getDay().toString();
				}   
				query.addCriteria(new MatchCriteria(dateColumn, MatchCriteria.LIKE, str + "%"));
				
		   } else if (toDate.getValue() != null){
				str = toDate.getYear().toString();
				
				if (toDate.getMonth() != null){   
					if(fromDate.getMonth().intValue() < 10) str = str + "0";
					str = str + fromDate.getMonth().toString();
				}

				if ( (toDate.getDay()!=null) ){
						if (fromDate.getDay().intValue()<10) str = str + "0";
						str = str + fromDate.getDay().toString();
				}   
				query.addCriteria(new MatchCriteria(dateColumn, MatchCriteria.LIKE, str + "%"));
			}   
			
			
		}	
	}

	
	public String getInfo(String Id){
		String result = "";
		
		String sqlStatement = "select Benamning from SYS_LKF Where Kod="+Id;
		
		Connection conn = null;
		Statement Stmt = null;

		try {
			conn = ConnectionBroker.getConnection(DATABASE);
			ResultSet RS = null;
			Stmt = conn.createStatement();
			RS = Stmt.executeQuery(sqlStatement);

			if (RS.next() ) result = RS.getString("Benamning");
			RS.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				ConnectionBroker.freeConnection(DATABASE, conn);
			}
		}
		
		
		return result;
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
		Column colLKF = new Column(table, COLUMN_NAME_LKF);
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
		query.addColumn(colLKF);
		
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
			String str = query.toString();
			RS = Stmt.executeQuery(query.toString());

			int count = 0;
			while (RS.next() && count <= maxResult) {
					String lkf = null,parish = "", commune = "" , country = "";
					lkf = RS.getString(COLUMN_NAME_LKF);
					if(lkf!=null){
						String parish_code = null, commune_code = null, country_code= null;
						
						if (lkf.length() == 6) parish_code = lkf;
						if (lkf.length() >= 4) commune_code = lkf.substring(0,4);
						if (lkf.length() >= 2) country_code = lkf.substring(0,2);						
						
						
						if (parish_code!=null) parish = getInfo(parish_code);
						if (commune_code!=null) commune = getInfo(commune_code);
						if (country_code!=null) country = getInfo(country_code);						
						
					}
					else if(RS.getString(COLUMN_NAME_HOME_TOWN)!=null) parish = RS.getString(COLUMN_NAME_HOME_TOWN);
					
				
				
				
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
								RS.getString("CEMETERY"),
								parish,
								commune,
								country
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
