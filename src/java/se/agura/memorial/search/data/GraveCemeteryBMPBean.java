package se.agura.memorial.search.data;

import java.util.Collection;

import javax.ejb.FinderException;

import com.idega.data.GenericEntity;
import com.idega.data.query.SelectQuery;
import com.idega.data.query.Table;
import com.idega.data.query.WildCardColumn;

public class GraveCemeteryBMPBean extends GenericEntity  implements GraveCemetery{
	private final static String ENTITY_NAME = "MS_GRAVE_CEMETERY";
	private static final String COLUMN_CEMETERY_NAME = "cemetery_name";
	
	public String getEntityName() {		
		return ENTITY_NAME;
	}

	public void initializeAttributes() {
		addAttribute(getIDColumnName()); // Primary key
		addManyToOneRelationship("database_connection",GraveDatabaseConn.class);
		addAttribute(COLUMN_CEMETERY_NAME, "Cemetery name", true, true,
				String.class, 200);
	}
	
	public Collection ejbFindAll() throws FinderException {
		Table table = new Table(this);
		
		SelectQuery query = new SelectQuery(table);
		query.addColumn(new WildCardColumn());  //the same as *
		
		return this.idoFindPKsByQuery(query);
	}	

}
