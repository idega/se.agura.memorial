package se.agura.memorial.search.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

	public Collection findGraves(String firstName, String lastName,
			String dateOfBirth, String dayOfDeath, String region,
			String graveyard, String database) {
		String pTable = "GA_Gravsatt";
		boolean beginWhere = false;

		StringBuffer sqlQuery = new StringBuffer();
		sqlQuery.append("SELECT * FROM ").append(pTable);

		if (firstName != null)
			if (!beginWhere)
				sqlQuery.append(" WHERE Fornamn LIKE:").append(firstName);
			else
				sqlQuery.append(" AND Fornamn LIKE:").append(firstName);

		if (lastName != null)
			if (!beginWhere)
				sqlQuery.append(" WHERE Efternamn LIKE:").append(lastName);
			else
				sqlQuery.append(" AND Efternamn LIKE:").append(lastName);

		return null;//this.idoFindPKsBySQL(sqlQuery.toString());
	}

	public void findGrave(String identifier) {
		// TODO Auto-generated method stub

	}

}
