package se.agura.memorial.search.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import se.agura.memorial.search.api.Grave;
import se.agura.memorial.search.api.GraveInformation;
import se.agura.memorial.search.api.Graveyard;
import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.search.util.Utility;

import com.idega.data.query.Column;
import com.idega.data.query.InCriteria;
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

	public static final String COLUMN_NAME_FIRST_NAME = "Fornamn";
	public static final String COLUMN_NAME_LAST_NAME = "Efternamn";
	public static final String COLUMN_NAME_DATE_OF_BIRTH = "Pers_nr";
	public static final String COLUMN_NAME_DATE_OF_DEATH = "Gravs_DATUM";
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
	
		
	
	public int getYearFromString(String date) {
		int result = 0;
		String str;
		if(date.length()<4) return 0;
		str = date.substring(date.length()-4,date.length()-2);
		
		result = Integer.valueOf(str).intValue();
		
		if(result<1800) result=0;
		if(result<2050) result=0;
		
		
		return result;
	}

	
	public Collection getListOfYear(String beginDate, String endDate) {
		
		int beginYear,endYear;
		beginYear=getYearFromString(beginDate);
		endYear=getYearFromString(endDate);
		String tmp=null;

		List result = new ArrayList();
		
		
		for (int i=beginYear;i<=endYear;i++){
			
			result.add(Integer.toString(i));
		}
		return result;

	}			
				
	public Collection findGraves(String firstName, String lastName, String personIdentifier, String dateOfBirthFrom, String dateOfBirthTo, String dateOfDeathFrom, String dateOfDeathTo, String hometown, String graveyard) {
		String sqlStatement=null;		
		Collection result = new ArrayList();;
		
		 
		
	    Table table = new Table(TABLE_NAME);
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
			
			if (graveyard != null)  query.addCriteria(new MatchCriteria(colGraveyard, MatchCriteria.LIKE, "%" + graveyard.trim() + "%"));
			if (hometown != null)  query.addCriteria(new MatchCriteria(colHomeTown, MatchCriteria.LIKE, "%" + hometown.trim() + "%"));
			
        }
            
		if (firstName != null)  query.addCriteria(new MatchCriteria(colFirstName, MatchCriteria.LIKE, "%" + firstName.trim() + "%"));
		if (lastName != null)  query.addCriteria(new MatchCriteria(colLastName, MatchCriteria.LIKE, "%" + lastName.trim() + "%"));
		if (dateOfBirthTo != null){
			
			Collection years = getListOfYear("tdummy database","ddd");
			query.addCriteria(new InCriteria(colDateOfBirth,years));
	
			sqlStatement=query.toString();	
		}
		else
			if (dateOfBirthFrom != null)  query.addCriteria(new MatchCriteria(colDateOfBirth, MatchCriteria.LIKE, dateOfBirthFrom.trim() + "%"));
			
		if (dateOfDeathFrom != null)  query.addCriteria(new MatchCriteria(colDateOfDeath, MatchCriteria.LIKE, dateOfDeathFrom.trim() + "%"));
		if (dateOfDeathTo != null)  query.addCriteria(new MatchCriteria(colDateOfDeath, MatchCriteria.LIKE, "%" + dateOfDeathTo.trim() + "%"));
		
		query.addOrder(orderByLastName);
		query.addOrder(orderByFirstName);
		
		

		sqlStatement=query.toString();					
		Connection conn = null;
		Statement Stmt = null;

		try {
			conn = ConnectionBroker.getConnection(DATABASE);
			ResultSet RS = null;
			Stmt = conn.createStatement();
			RS = Stmt.executeQuery(sqlStatement);

			int count = 0;
			while (RS.next() && count <= 100) {
				Grave grave = new Grave(
						RS.getString("Grav_ID")+":"+RS.getString("LopNr"), 
						RS.getString(COLUMN_NAME_FIRST_NAME), 
						RS.getString(COLUMN_NAME_LAST_NAME), 
						null,//Date dateOfBirth, 
						null,//Date dateOfDeath, 
						null,//RS.getString(hometown, 
						null
//						new GraveInformation(
//								RS.getString(graveNumber, 
//								RS.getString(block, 
//								RS.getString(department, 
//								RS.getString(cemetery)
								);
				
				
//						  RS.getInt(COLUMN_NAME_GRAVE_ID),
//						  RS.getInt(COLUMN_NAME_LOP_NR),
//						  RS.getString(COLUMN_NAME_FIRST_NAME), 
//						  RS.getString(COLUMN_NAME_LAST_NAME),
//						  RS.getString(COLUMN_NAME_DATE_OF_BIRTH),
//						  RS.getString(COLUMN_NAME_DATE_OF_DEATH),
//						  count+1);
				
				count++;
				result.add(grave);
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
		StringBuffer sqlQuery = new StringBuffer();
		Grave grave = null;	
		String sqlStatement = "select ";
		
		sqlStatement += " GA_Gravsatt.Grav_ID graveID,";
		sqlStatement += " GA_Gravsatt.LopNR lopNr,";			
		sqlStatement += " GA_Gravsatt.FORNAMN firstName,";
		sqlStatement += " GA_Gravsatt.EFTERNAMN lastName,";
		sqlStatement += " GA_Gravsatt.PERS_NR dateOfBirth,";
		sqlStatement += " GA_Gravsatt.Gravs_Datum dateOfDeath,";
		sqlStatement += " GA_Gravsatt.HEMORT homeTown,";
		//sqlStatement += " '???' burialPlace,";
		sqlStatement += " GA_Gravsatt.HEMORT burialPlace,";
		
		sqlStatement += " GA_KGard.BENAMNING cemetery,";
		sqlStatement += " GA_Avdelning.BENAMNING department,";
		sqlStatement += " GA_KVarter.BENAMNING blockName,";
		sqlStatement += " GA_Grav.GRAVNUMMER gravyNumber";
		sqlStatement += " From GA_Gravsatt,GA_KGard,GA_KVarter,GA_Avdelning,GA_Grav";

		sqlStatement += " where GA_Gravsatt.Grav_ID="+getGravId(graveId);
		sqlStatement += " and GA_Gravsatt.LOPNR="+getLopNr(graveId);		
		
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
			conn = ConnectionBroker.getConnection(DATABASE);
			ResultSet RS = null;
			Stmt = conn.createStatement();
			RS = Stmt.executeQuery(sqlStatement);

			int count = 0;
			while (RS.next() && count <= 300) {
						grave = new Grave(
						RS.getString("Grav_ID")+":"+RS.getString("LopNr"), 
						RS.getString(COLUMN_NAME_FIRST_NAME), 
						RS.getString(COLUMN_NAME_LAST_NAME), 
						Utility.stringToDate(RS.getString(COLUMN_NAME_DATE_OF_BIRTH)), 
						Utility.stringToDate(RS.getString(COLUMN_NAME_DATE_OF_DEATH)), 
						RS.getString(COLUMN_NAME_HOME_TOWN),
						new GraveInformation(
								RS.getString(COLUMN_NAME_GRAVE_NUMBER), 
								RS.getString(COLUMN_NAME_BLOCK), 
								RS.getString(COLUMN_NAME_DEPARTMENT), 
								RS.getString(COLUMN_NAME_CEMETERY)
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
