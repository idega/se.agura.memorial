package se.agura.memorial.search.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import se.agura.memorial.search.api.GraveInformation;
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

	public List getGraveyards() {

		String sqlStatement = "select * from ga_kgard order by Benamning";
		String datasourceName = "malmo";
		Connection conn = null;
		Statement Stmt = null;

		List result = new ArrayList();

		try {
			conn = ConnectionBroker.getConnection(datasourceName);

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
			while (RS.next() && count < 1000) {
				Graveyard g = new Graveyard(RS.getInt("ID"),
											RS.getString("KGard"),						
											RS.getString("Benamning"),						
											RS.getInt("Distrikt_ID")
						);
				count++;
				System.out.println(g.getBenamning());

				//		...  here the returning classes would be created and added to the list.
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
				ConnectionBroker.freeConnection(datasourceName, conn);
			}
		}
		return result;

	}

	public List findGraves(String firstName, String lastName,
			String dateOfBirth, String dayOfDeath, String region,
			String graveyard, String database) {

		List result = new ArrayList();
		
		boolean beginWhere = false;

		StringBuffer sqlQuery = new StringBuffer();
		
		
	
		
		String sqlStatement = "select ";


		sqlStatement+=" GA_Gravsatt.FORNAMN firstName,";
		sqlStatement+=" GA_Gravsatt.EFTERNAMN lastName,";		
		sqlStatement+=" GA_Gravsatt.PERS_NR dateOfBirth,";		
		sqlStatement+=" GA_Gravsatt.DODSDATUM  dateOfDeath,";		
		sqlStatement+=" GA_Gravsatt.HEMORT homeTown_code1,";		
		sqlStatement+=" SYS_LKF.Kod homeTown_code2,";		
		sqlStatement+=" GA_KGard.BENAMNING cemetry,";		
		sqlStatement+=" GA_KVarter.BENAMNING blockName,";		
		sqlStatement+=" GA_Avdelning.BENAMNING department,";		
		sqlStatement+=" GA_Grav.GRAVNUMMER gravyNumber";		
		sqlStatement+=" From GA_Gravsatt,GA_KGard,GA_KVarter,GA_Avdelning,GA_Grav,SYS_LKF";		
		sqlStatement+=" where GA_Gravsatt.GRAV_ID=GA_Grav.ID";		
		sqlStatement+=" and GA_Grav.Avdelning_ID=GA_Avdelning.ID";		
		sqlStatement+=" and GA_Grav.Kvarter_Kgard_ID=GA_Kvarter.Kgard_ID";		
		sqlStatement+=" and GA_Grav.Kvarter_Lopnr=GA_Kvarter.Lopnr";		
		sqlStatement+=" and GA_Kvarter.KGard_ID=GA_KGard.ID";		
		sqlStatement+=" and GA_Gravsatt.Hemort=SYS_LKF.Benamning";		
		sqlStatement+=" and GA_Avdelning.Kvarter_KGard_ID=GA_KVarter.KGard_ID";		
		sqlStatement+=" and GA_Avdelning.Kvarter_Lopnr=GA_KVarter.Lopnr";		
		
		if (firstName!=null) sqlStatement+=" and GA_Gravsatt.FORNAMN LIKE: '%"+firstName.trim()+"%' ";		
		if (lastName!=null) sqlStatement+=" and GA_Gravsatt.EFTERNAMN LIKE: '%"+lastName.trim()+"%' ";		
		if (dateOfBirth!=null) sqlStatement+=" and GA_Gravsatt.PERS_NR  LIKE: '%"+dateOfBirth.trim()+"%' ";		
		if (dayOfDeath!=null) sqlStatement+=" and GA_Gravsatt.FDODSDATUM LIKE: '%"+dayOfDeath.trim()+"%' ";		
		if (region!=null) sqlStatement+=" and GA_Avdelning.Benamning LIKE: '%"+region.trim()+"%' ";		
		if (graveyard!=null) sqlStatement+=" and GA_KGard.Benamning LIKE: '%"+graveyard.trim()+"%' ";		
		//if (firstName!=null) sqlStatement+=" and GA_Gravsatt.FORNAMN LIKE: '%"+database.trim()+"%' ";		
		
		
	
		  	
		  
		
		
		
		
		
		
		String datasourceName = "malmo";
		Connection conn = null;
		Statement Stmt = null;


		try {
			conn = ConnectionBroker.getConnection(datasourceName);
			ResultSet RS = null;
			Stmt = conn.createStatement();
			RS = Stmt.executeQuery(sqlStatement);

			int count = 0;
			while (RS.next() && count <= 100) {
				GraveInformation info = new GraveInformation(
						RS.getString("firstName"),
						RS.getString("lastName"),
  					    RS.getString("dateOfBirth"),
						RS.getString("dateOfDeath"),
						RS.getString("homeTown_code1"),
						RS.getString("cemetry"),
						RS.getString("blockName"),
						RS.getString("department"),
						RS.getString("gravyNumber")); 
						
						
				count++;
				System.out.println(info.getFirstName()+"|"+info.getLastName()+"|"+info.getDateOfBirth());

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
				ConnectionBroker.freeConnection(datasourceName, conn);
			}
		}
		
		


		return result;
	}

	public void findGrave(String identifier) {
		// TODO Auto-generated method stub

	}

}
