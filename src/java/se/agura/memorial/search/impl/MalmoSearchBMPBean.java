package se.agura.memorial.search.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import se.agura.memorial.search.api.GraveInformation;
import se.agura.memorial.search.api.Graves;
import se.agura.memorial.search.api.Graveyard;
import se.agura.memorial.search.api.ObituarySearch;

import com.idega.util.database.ConnectionBroker;

/**
 * @author Igors
 *
 */
public class MalmoSearchBMPBean implements ObituarySearch {

	public MalmoSearchBMPBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
			//		List placeHolderValues = subsetQuery.getValues();
			//		if(placeHolderValues==null || placeHolderValues.isEmpty()){
			Stmt = conn.createStatement();
			//		    RS = Stmt.executeQuery(subsetQuery.toString());
			RS = Stmt.executeQuery(sqlStatement);
			//		}
			////		 use PreparedStatement
			//		else{
			//		    Stmt = conn.prepareStatement(subsetQuery.toString(true));
			//		    DatastoreInterface dsi = DatastoreInterface.getInstance(_entity);
			//		     
			//			dsi.insertIntoPreparedStatement(placeHolderValues,(PreparedStatement)Stmt,1);
			//		    RS = ((PreparedStatement)Stmt).executeQuery();
			//		}

			int count = 0;
			while (RS.next() && count <= 100) {
				Graveyard g = new Graveyard(RS.getInt("ID"),
						                    RS.getString("KGard"),
						                    RS.getString("Benamning"),
						                    RS.getInt("Distrikt_ID"));
				count++;
			    result.add(g);
				System.out.println(g.getBenamning());

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

	public List findGraves(String firstName, String lastName,
			String dateOfBirthFrom, String dateOfBirthTo,
			String dayOfDeathFrom, String dayOfDeathTo, String region,
			String graveyard, String database) {

		database = "malmo"; // for TEST MODE ONLY
		
		
		List result = new ArrayList();
		StringBuffer sqlQuery = new StringBuffer();
		String sqlStatement = "select ";
		if ((graveyard == null) && (region == null)) {
			
            boolean beginWhere = false; 
			sqlStatement += " GA_Gravsatt.Grav_ID graveID, \n";
			sqlStatement += " GA_Gravsatt.LopNR lopNr, \n";			
			sqlStatement += " GA_Gravsatt.FORNAMN firstName, \n";
			sqlStatement += " GA_Gravsatt.EFTERNAMN lastName, \n";
			sqlStatement += " GA_Gravsatt.PERS_NR dateOfBirth, \n";
			sqlStatement += " GA_Gravsatt.DODSDATUM dateOfDeath \n";
			sqlStatement += " From GA_Gravsatt ";

			if (firstName != null){
				if(!beginWhere){
					  sqlStatement += " where GA_Gravsatt.FORNAMN LIKE: '%" + firstName.trim() + "%' \n";
					  beginWhere = true;
				}	  
				else 
					  sqlStatement += " and GA_Gravsatt.FORNAMN LIKE: '%" + firstName.trim() + "%' \n"; 
			}
			
			
			if (lastName != null){
				if(!beginWhere){
  					  sqlStatement += " where GA_Gravsatt.EFTERNAMN LIKE: '%" + lastName.trim() + "%' \n";
					  beginWhere = true;						
				}		
				else
					  sqlStatement += " and GA_Gravsatt.EFTERNAMN LIKE: '%" + lastName.trim() + "%' \n";
				
			}	
			if (dateOfBirthFrom != null){
				if(!beginWhere){
					  sqlStatement += " where GA_Gravsatt.PERS_NR  LIKE: '%" + dateOfBirthFrom.trim() + "%' \n";
					  beginWhere = true;						
				}
				else
					  sqlStatement += " and GA_Gravsatt.PERS_NR  LIKE: '%" + dateOfBirthFrom.trim() + "%' \n";
			}
			
			if (dayOfDeathFrom != null){
				if(!beginWhere){
					  sqlStatement += " where GA_Gravsatt.FDODSDATUM LIKE: '%" 	+ dayOfDeathFrom.trim() + "%' \n";
					  beginWhere = true;						
				}
				else
					sqlStatement += " and GA_Gravsatt.FDODSDATUM LIKE: '%" 	+ dayOfDeathFrom.trim() + "%' \n";					
			}
			
			sqlStatement += " order by GA_Gravsatt.EFTERNAMN,GA_Gravsatt.FORNAMN \n";

			
		} else {
			sqlStatement += " GA_Gravsatt.Grav_ID graveID,";
			sqlStatement += " GA_Gravsatt.LopNR lopNr,";			
			sqlStatement += " GA_Gravsatt.FORNAMN firstName,";
			sqlStatement += " GA_Gravsatt.EFTERNAMN lastName,";
			sqlStatement += " GA_Gravsatt.PERS_NR dateOfBirth,";
			sqlStatement += " GA_Gravsatt.DODSDATUM  dateOfDeath";
			sqlStatement += " From GA_Gravsatt,GA_KGard,GA_KVarter,GA_Avdelning,GA_Grav";
			sqlStatement += " where GA_Gravsatt.GRAV_ID=GA_Grav.ID";
			sqlStatement += " and GA_Grav.Avdelning_ID=GA_Avdelning.ID";
			sqlStatement += " and GA_Grav.Kvarter_Kgard_ID=GA_Kvarter.Kgard_ID";
			sqlStatement += " and GA_Grav.Kvarter_Lopnr=GA_Kvarter.Lopnr";
			sqlStatement += " and GA_Kvarter.KGard_ID=GA_KGard.ID";


			if (firstName != null)
				sqlStatement += " and GA_Gravsatt.FORNAMN LIKE: '%" + firstName.trim() + "%' ";
			if (lastName != null)
				sqlStatement += " and GA_Gravsatt.EFTERNAMN LIKE: '%" + lastName.trim() + "%' ";
			if (dateOfBirthFrom != null)
				sqlStatement += " and GA_Gravsatt.PERS_NR  LIKE: '%" + dateOfBirthFrom.trim() + "%' ";
			if (dayOfDeathFrom != null)
				sqlStatement += " and GA_Gravsatt.FDODSDATUM LIKE: '%" + dayOfDeathFrom.trim() + "%' ";
			if (region != null)
				sqlStatement += " and GA_Avdelning.Benamning LIKE: '%" + region.trim() + "%' ";
			if (graveyard != null)
				sqlStatement += " and GA_KGard.Benamning LIKE: '%" + graveyard.trim() + "%' ";

			sqlStatement += " order by GA_Gravsatt.EFTERNAMN,GA_Gravsatt.FORNAMN";

		}

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
				info.init(RS.getInt("graveID"),
						  RS.getInt("lopNr"),
						  RS.getString("firstName"), 
						  RS.getString("lastName"),
						  RS.getString("dateOfBirth"),
						  RS.getString("dateOfDeath"));

				count++;
				result.add(info);
				System.out.println(info.getLastName()+" "+info.getFirstName());
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

public List findGrave(int gravID,int lopNr,String database) {
		List result = new ArrayList();
		StringBuffer sqlQuery = new StringBuffer();

		
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
				Graves info = new Graves(); 
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

				System.out.println("-------------------------------------------------------------------2");
				System.out.println(RS.getString("lastName")+"."+info.getFirstName());
				
				count++;
				result.add(info);
				
				
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

}
