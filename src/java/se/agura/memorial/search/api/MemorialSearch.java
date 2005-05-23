package se.agura.memorial.search.api;


/**
 * 
 */

import com.idega.presentation.Block;
import com.idega.presentation.ui.Form;
import com.idega.presentation.Table;
import com.idega.presentation.IWContext;


/**
 * @author is
 *
 */
public class MemorialSearch extends Block{

	/**
	 * @param args
	 */
	
	public void main(IWContext iwc) throws Exception {

		// TODO Auto-generated method stub

		Form form = new Form();
		Table table = new Table();
		table.setBorder(2);
		table.add("This is a test");		
		form.add(table);
		add(form);
		
        //System.out.println("ashdjahjd");
		
		

		
	}
}	
	
/*

	If you check out com.idega.block.survey you can see a simple block that  
	has both business and data beans.  Most of the queries in the ejbFind  
	methods are however built up using IDOQuery exept for in  
	SurveyReplyBMPBean.  We try to use SelectQuery more now and here is one  
	example of such method:


	
	* This finder returns a collection of all groups of the grouptype(s)  
	that are defined in the groupTypes parameter
	* It also returns the groups that are defined as topnodes in the  
	ic_domain_group_relation table
	* It excludes groups that have been deleted and don't have any active  
	relations to parent groups
	      * If returnSpecifiedGroupTypes is set as false then it excludes  
	the grouptype(s) defined in the groupTypes paremeter
	* excluding user representative groups
	* @return all groups of certain type(s) that have not been deleted
	* @throws FinderException
	*/
	
	/*
	public Collection ejbFindAllGroups(String[] groupTypes, boolean	returnSpecifiedGroupTypes)
	throws FinderException { Table groupTable = new Table(this, "g");
	Column idCol = new Column(groupTable, getColumnNameGroupID());

	Table groupRelSubTable = new Table(GroupRelationBMPBean.TABLE_NAME,	"gr");
	Column relatedGroupIDSubCol = new Column(groupRelSubTable,  
	GroupRelationBMPBean.RELATED_GROUP_ID_COLUMN);
//	Column relationshipTypeSubCol = new Column(groupRelSubTable,GroupRelationBMPBean.RELATIONSHIP_TYPE_COLUMN);
	Column groupRelationstatusSubCol = new Column(groupRelSubTable,  
	GroupRelationBMPBean.STATUS_COLUMN);

	SelectQuery firstSubQuery = new SelectQuery(groupRelSubTable);
	firstSubQuery.addColumn(relatedGroupIDSubCol);
//	subQuery.addCriteria(new MatchCriteria(relationshipTypeSubCol,MatchCriteria.EQUALS, RELATION_TYPE_GROUP_PARENT));
	firstSubQuery.addCriteria(new InCriteria(groupRelationstatusSubCol,  
	new String[]{GroupRelation.STATUS_ACTIVE,  
	GroupRelation.STATUS_PASSIVE_PENDING}));

	Table groupDomainRelSubTable = new  
	Table(GroupDomainRelationBMPBean.TABLE_NAME, "gdr");
	Column gdr_RelatedGroupIDSubCol = new Column(groupDomainRelSubTable,GroupDomainRelationBMPBean.RELATED_GROUP_ID_COLUMN);
	Column gdr_relationshipTypeSubCol = new  
	Column(groupDomainRelSubTable,  
	GroupDomainRelationBMPBean.RELATIONSHIP_TYPE_COLUMN);
	Column gdr_groupRelationstatusSubCol = new  
	Column(groupDomainRelSubTable,  
	GroupDomainRelationBMPBean.STATUS_COLUMN);

	SelectQuery secondSubQuery = new SelectQuery(groupDomainRelSubTable);
	secondSubQuery.addColumn(gdr_RelatedGroupIDSubCol);
	secondSubQuery.addCriteria(new  
	MatchCriteria(gdr_relationshipTypeSubCol, MatchCriteria.EQUALS,	GroupDomainRelationTypeBMPBean.RELATION_TYPE_TOP_NODE));
	secondSubQuery.addCriteria(new  
	MatchCriteria(gdr_groupRelationstatusSubCol, MatchCriteria.IS,  
	MatchCriteria.NULL));

	InCriteria firstInCriteria = new InCriteria(idCol, firstSubQuery);
	InCriteria secondInCriteria = new InCriteria(idCol, secondSubQuery);

	SelectQuery query = new SelectQuery(groupTable);
	query.addColumn(new WildCardColumn(groupTable));
	if (groupTypes != null && groupTypes.length != 0) {
	    Column typeCol = new Column(groupTable, getGroupTypeColumnName());
	    if (groupTypes.length == 1){
	            query.addCriteria(new MatchCriteria(typeCol,returnSpecifiedGroupTypes?MatchCriteria.EQUALS:MatchCriteria.NOTEQUALS,	groupTypes[0]));
	        } else {
	            query.addCriteria(new InCriteria(typeCol, groupTypes,!returnSpecifiedGroupTypes));
	        }
	    }
	query.addCriteria(new OR(firstInCriteria, secondInCriteria));
	query.addOrder(groupTable, getNameColumnName(), true);
	return this.idoFindPKsByQuery(query);
	}


	/*
	 * We have now methods in GenericEntity that help us createing queries  
	like this one and also have support for IDOEntities (which  
	GenericEntity implements) in SelectQuery.  Here is another example that  
	uses some of that:

	
	public Collection ejbFindByNames(String first, String middle, String last) throws FinderException {
	    SelectQuery query = idoSelectQuery();
	if (first != null || middle != null || last != null) {
	if (first != null && !"".equals(first)) {
	    query.addCriteria(new MatchCriteria(idoQueryTable(),  
	getColumnNameFirstName(),MatchCriteria.LIKE,"%"+first+"%",true));
	}
	if (middle != null && !"".equals(middle)) {
	    query.addCriteria(new MatchCriteria(idoQueryTable(),  
	getColumnNameFirstName(),MatchCriteria.LIKE,"%"+middle+"%",true));
	}
	if (last != null && !"".equals(last)) {
	    query.addCriteria(new MatchCriteria(idoQueryTable(),  
	getColumnNameFirstName(),MatchCriteria.LIKE,"%"+last+"%",true));
	}
//	 append is deleted filter
	query.addCriteria(getNotDeletedCriteria());
	return super.idoFindPKsByQuery(query);
	}
	throw new FinderException("No legal names provided");
	}



/*
	Here is an example of code that executes sql query.  The variable  
	datasourceName refers to WEB-INF/idegaweb/properties/db.properties  
	where you can see 'default.something=something', to define the  
	connection to the malmo database, another set of properties are added  
	to the file but with e.g. 'malmochurch' instead of default.

	SelectQuery subsetQuery = ....;

	Connection conn = null;
	Statement Stmt = null;
	try {
	conn = ConnectionBroker.getConnection(datasourceName);

	    ResultSet RS = null;
	    List placeHolderValues = subsetQuery.getValues();
	    if(placeHolderValues==null || placeHolderValues.isEmpty()){
	    Stmt = conn.createStatement();
	    RS = Stmt.executeQuery(subsetQuery.toString());
	}
//	 use PreparedStatement
	else{
	    Stmt = conn.prepareStatement(subsetQuery.toString(true));
	    DatastoreInterface dsi = DatastoreInterface.getInstance(_entity);
	     
	    dsi.insertIntoPreparedStatement(placeHolderValues,(PreparedStatement)Stmt,1);
	    RS = ((PreparedStatement)Stmt).executeQuery();
	}

	    HashMap mapOfEntities = new HashMap();
	    while(RS.next())
	{
	//...  here the returning classes would be created and added to the	list.
	}

	RS.close();
	} catch (SQLException sqle) {
	sqle.printStackTrace();
	....
	} finally {
	if (Stmt != null) {
	try {
	Stmt.close();
	}
	catch (SQLException e) {
	e.printStackTrace();
	}
	}
	if (conn != null) {
	ConnectionBroker.freeConnection(datasourceName, connection);
	}
	}





//	This is an example of Dropdown in JSF page. You can also see in this example how we localize strings in our jsf-pages. It is from the jsp page simpletemplate.jsp in the com.idega.content bundle (view-node .../workspace/content/pages/templatesettings in the workspace) but it will be moved to the com.idega.builder bundle soon so the localization refers to that bundle. (#{localizedStrings[bundle-identifier][localization-string-key]})

	<h:outputLabel for="component">
	<h:outputText id="componentLabel" value="#{localizedStrings['com.idega.builder']['create_simple_template.Component']}: " styleClass="label"/>
	</h:outputLabel>
	<h:selectOneMenu id="component" value="#{simpleTemplateCreationBean.selectedComponent}" styleClass="formInput">
	<f:selectItems value="#{simpleTemplateCreationBean.componentSelectItemList}"/>
	</h:selectOneMenu>


	Methods from the managed bean simpleTemplateCreationBean :

	private Collection getAllComponents()throws FinderException,IDOLookupException{
	ICObjectHome home = (ICObjectHome) IDOLookup.getHome(ICObject.class);
	return home.findAllByObjectType(ICObjectBMPBean.COMPONENT_TYPE_BLOCK);
	}

	public List getComponentSelectItemList(){
	List l = new ArrayList();
	l.add(new SelectItem(NO_COMPONENT_SELECTED,"[Select component]"));
	try {
	IWContext iwc = IWContext.getInstance();
	Collection c = getAllComponents();
	for (Iterator iter = c.iterator(); iter.hasNext();) {
	try {
	ICObject item = (ICObject) iter.next();
	l.add(new SelectItem(String.valueOf(item.getPrimaryKey()),item.getBundle(iwc.getIWMainApplication()).getComponentName(item.getClassName(), iwc.getCurrentLocale())));
	}
	catch (EJBException e1) {
	e1.printStackTrace();
	}
	catch (IWBundleDoesNotExist e1) {
//	 e1.printStackTrace();
	}
	}
	}
	catch (IDOLookupException e) {
	e.printStackTrace();
	}
	catch (FinderException e) {
	e.printStackTrace();
	}

	return l;
	}

	public String getSelectedComponent() {
	return selectedComponent;
	}


	
	

}

*/
