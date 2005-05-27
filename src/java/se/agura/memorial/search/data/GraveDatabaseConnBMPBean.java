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
	
	
	public String getEntityName() {		
		return ENTITY_NAME;
	}

	public void initializeAttributes() {
		addAttribute(getIDColumnName()); // Primary key
		addAttribute(COLUMN_DATABASE_NAME, "Database name", true, true,
				String.class, 200);
		addAttribute(COLUMN_API_DB_CONNECTION,
				"Identificator for database connection", true, true,
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

	public void insertStartData() throws Exception {
		GraveDatabaseConnHome home = (GraveDatabaseConnHome) IDOLookup.getHome(GraveDatabaseConn.class);

//		GraveDatabaseConn data1 = home.create();
//		data1.setDatabaseName("First test");
//		data1.setAPIDBConnection("First test conn");
//		data1.store();
//
//		GraveDatabaseConn data2 = home.create();
//		data2.setDatabaseName("Second test");
//		data2.setAPIDBConnection("Second test conn");
//		data2.store();
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
