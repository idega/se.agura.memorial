package se.agura.memorial.search.data;

import java.util.Collection;

import javax.ejb.FinderException;

import com.idega.data.GenericEntity;
import com.idega.data.IDOLookup;
import com.idega.data.query.Column;
import com.idega.data.query.MatchCriteria;
import com.idega.data.query.SelectQuery;
import com.idega.data.query.Table;
import com.idega.data.query.WildCardColumn;

public class GraveDatabaseConnBMPBean extends GenericEntity  implements GraveDatabaseConn{

	private final static String ENTITY_NAME = "MS_GRAVE_DATABASE_CONN";
	
	private final static String COLUMN_DATABASE_NAME = "database_name";
	private final static String COLUMN_API_DB_CONNECTION = "API_DB_connection";	
	private final static String COLUMN_DESCRIPTION = "description";	
	
	public String getEntityName() {		
		return ENTITY_NAME;
	}

	public void initializeAttributes() {
		addAttribute(getIDColumnName()); // Primary key
		addAttribute(COLUMN_DATABASE_NAME, "Database connection name", true, true,
				String.class, 200);
		addAttribute(COLUMN_API_DB_CONNECTION,
				"Full class name of database connection implementation", true, true,
				String.class, 200);
		addAttribute(COLUMN_DESCRIPTION,
				"Description", true, true,
				String.class, 200);
	}
	
	
	public void setDatabaseName(String name) {
		setColumn(COLUMN_DATABASE_NAME, name);
	}
	public String getDatabaseName() {
		return getStringColumnValue(COLUMN_DATABASE_NAME);
	}	
	
	public void setAPIDBConnection(String name) {
		setColumn(COLUMN_API_DB_CONNECTION, name);
	}
	public String getAPIDBConnection() {
		return getStringColumnValue(COLUMN_API_DB_CONNECTION);
	}		
	
	public void setDescription(String description) {
		setColumn(COLUMN_DESCRIPTION, description);
	}
	public String getDescription() {
		return getStringColumnValue(COLUMN_DESCRIPTION);
	}
	
	

	public void insertStartData() throws Exception {
		GraveDatabaseConnHome home = (GraveDatabaseConnHome) IDOLookup.getHome(GraveDatabaseConn.class);

		GraveDatabaseConn data1 = home.create();
		data1.setDatabaseName("Malm\u00f6"); //Malmo:
		data1.setAPIDBConnection("se.agura.memorial.search.impl.MalmoChurchSearch");
		data1.setDescription("Malmo church database search");
		data1.store();

		GraveDatabaseConn data2 = home.create();
		data2.setDatabaseName("\u00d6vriga"); //O:vriga
		data2.setAPIDBConnection("se.agura.memorial.search.impl.LocalObituarySearch");
		data2.setDescription("Localy stored graves search");
		data2.store();
	}	
	
	
	public Collection ejbFindAll() throws FinderException {
		Table table = new Table(this);
		
		SelectQuery query = new SelectQuery(table);
		query.addColumn(new WildCardColumn());  //the same as *
		
		return this.idoFindPKsByQuery(query);
	}	
	
	
	public Collection ejbFindAllNameContaining(String s) throws FinderException {
		Table table = new Table(this);
		
		Column nameCol = new Column(table, COLUMN_DATABASE_NAME);
		
		SelectQuery query = new SelectQuery(table);
		query.addColumn(new WildCardColumn());
		query.addCriteria(new MatchCriteria(nameCol, MatchCriteria.LIKE, "%"+ s +"%"));			
		return this.idoFindPKsByQuery(query);
	}	

}
