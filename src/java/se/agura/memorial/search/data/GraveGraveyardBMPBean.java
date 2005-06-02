package se.agura.memorial.search.data;

import java.util.Collection;

import javax.ejb.FinderException;

import com.idega.data.GenericEntity;
import com.idega.data.IDOLookup;
import com.idega.data.query.SelectQuery;
import com.idega.data.query.Table;
import com.idega.data.query.WildCardColumn;

public class GraveGraveyardBMPBean extends GenericEntity  implements GraveGraveyard{ 
	private final static String ENTITY_NAME = "MS_GRAVE_GRAVEYARD";
	private static final String COLUMN_GRAVEYARD_NAME = "graveyard_name";
//	private static final String COLUMN_DATABASE_CONNECTION = "database_connection";
	
	
	public String getEntityName() {		
		return ENTITY_NAME;
	}
	
    public void setGraveyardName(String name) {
        setColumn(COLUMN_GRAVEYARD_NAME, name);
    }
    public String getGraveyardName() {
        return getStringColumnValue(COLUMN_GRAVEYARD_NAME);
    }
	
	// i am pretty unsure, if this will work at all :) 
	// seems like it is supposed to work with ID
//    public void setGraveDatabaseConn(GraveDatabaseConn conn) {
//        setColumn(COLUMN_DATABASE_CONNECTION, conn);
//    }
	
	// i am pretty unsure, if this will work at all :) 
	// seems like it is supposed to work with ID
//    public GraveDatabaseConn getGraveDatabaseConn() {        
//		return (GraveDatabaseConn) getColumnValue(COLUMN_DATABASE_CONNECTION);		
//    }
	

	public void initializeAttributes() {
		addAttribute(getIDColumnName()); // Primary key
//		addManyToOneRelationship(COLUMN_DATABASE_CONNECTION, GraveDatabaseConn.class);
		addAttribute(COLUMN_GRAVEYARD_NAME, "Graveyard name", true, true,
				String.class, 200);
	}

	public void insertStartData() throws Exception {
		GraveGraveyardHome home = (GraveGraveyardHome) IDOLookup.getHome(GraveDatabaseConn.class);

		GraveGraveyard data1 = home.create();
		data1.setGraveyardName("Local 1. graveyard");		
//		data1.setGraveDatabaseConn(null);  // TODO
		data1.store();

		GraveGraveyard data2 = home.create();
		data1.setGraveyardName("Local 2. graveyard");
//		data1.setGraveDatabaseConn(null);  // TODO
		data2.store();
	}
	
	public Collection ejbFindAll() throws FinderException {
		Table table = new Table(this);
		
		SelectQuery query = new SelectQuery(table);
		query.addColumn(new WildCardColumn());  //the same as *
		
		return this.idoFindPKsByQuery(query);
	}	

}
