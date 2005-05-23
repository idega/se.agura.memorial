package se.agura.memorial.search.impl;

import javax.ejb.FinderException;
import java.util.Collection;
import com.idega.data.GenericEntity;
import com.idega.data.IDOQuery;
import se.agura.memorial.search.api.ObituarySearch;



/**
 * @author Igors
 *
 */
public abstract class MalmoSearchBMPBean extends GenericEntity implements ObituarySearch {

	public MalmoSearchBMPBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    public Collection findGraves(
	   							 String firstName,
	   							 String lastName,
	   							// Interval dateOfBirth,
	   							// Interval dayOfDeath,
	   							 String region,
	   							 String graveyard)  {
	    String pTable = "GA_Gravsatt";
		boolean beginWhere=false;
			

	    StringBuffer sqlQuery = new StringBuffer();
	    sqlQuery.append("SELECT * FROM ").append(pTable);
		
		if (firstName!=null)
			if (!beginWhere)  sqlQuery.append(" WHERE Fornamn LIKE:").append(firstName);
			else   sqlQuery.append(" AND Fornamn LIKE:").append(firstName);
		
		if (lastName!=null)
			if (!beginWhere)  sqlQuery.append(" WHERE Efternamn LIKE:").append(lastName);
			else   sqlQuery.append(" AND Efternamn LIKE:").append(lastName);


		return null ;//this.idoFindPKsBySQL(sqlQuery.toString());
	 }


}
