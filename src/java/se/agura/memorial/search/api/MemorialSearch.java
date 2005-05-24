package se.agura.memorial.search.api;



import java.util.Iterator;
import java.util.List;

import se.agura.memorial.search.impl.MalmoSearchBMPBean;

import com.idega.presentation.Block;
import com.idega.presentation.IWContext;
import com.idega.presentation.Table;
import com.idega.presentation.ui.DropdownMenu;
import com.idega.presentation.ui.Form;
import com.idega.presentation.ui.SelectDropdown;
import com.idega.presentation.ui.SubmitButton;
import com.idega.presentation.ui.TextInput;

/**
 * @author Igors
 *
 */
public class MemorialSearch extends Block{

	/**
	 * @param args
	 */

	private static String PARAMETER_ACTION = "ACTION";
	private static String ACTION_SEARCH = "SEARCH";
	private static String PARAMETER_NAME_TO_SEARCH_FOR = "tt_ntsf";

	
	public void main(IWContext iwc) throws Exception {
		
		Form form = new Form();
		Table table = new Table(5,7);
		table.setBorder(0);
		table.add("First Name",1,1);

		table.add("Last Name",2,1);

		table.add("Date of Birth",3,1);
		
		table.add("Date of Death",3,3);

		table.add("Database",5,5);
		
		table.add("Home Town",1,5);

		table.add("Cemetery",2,5);
		
		table.add("-",4,2);
		
		table.add("-",4,4);		
		

		
		TextInput editFirstName = new TextInput(PARAMETER_NAME_TO_SEARCH_FOR);
		TextInput editLastName = new TextInput(PARAMETER_NAME_TO_SEARCH_FOR);
		TextInput editDateOfBirth_1 = new TextInput(PARAMETER_NAME_TO_SEARCH_FOR);
		TextInput editDateOfBirth_2 = new TextInput(PARAMETER_NAME_TO_SEARCH_FOR);
		TextInput editDateOfDeath_1 = new TextInput(PARAMETER_NAME_TO_SEARCH_FOR);		
		TextInput editDateOfDeath_2 = new TextInput(PARAMETER_NAME_TO_SEARCH_FOR);
		TextInput editHomeTown = new TextInput(PARAMETER_NAME_TO_SEARCH_FOR);
		TextInput editCemetery = new TextInput(PARAMETER_NAME_TO_SEARCH_FOR);
		DropdownMenu editDatabase = new DropdownMenu(PARAMETER_NAME_TO_SEARCH_FOR);		
		
		MalmoSearchBMPBean b = new MalmoSearchBMPBean();
		
		
		DropdownMenu from = new DropdownMenu(PARAMETER_NAME_TO_SEARCH_FOR);
		DropdownMenu to   = new DropdownMenu(PARAMETER_NAME_TO_SEARCH_FOR);
		List myList = b.getGraveyards();
		Iterator iter = myList.iterator();
	//	while (iter.hasNext()) {
//			CurrencyHolder holder = (CurrencyHolder) iter.next();
	//		editDatabase.addMenuElement("Malm�","ss");
			

	//	}

		//editDatabase.add("Malm�");
		
		editDatabase.addMenuElement("0","Malm�");
		editDatabase.addMenuElement("1","test 2");		
		editDatabase.addMenuElement("2","test 3");		
		editDatabase.addMenuElement("-1","                       ");		
		
		
	//	editDatabase.add(b);
		
/*		TextInput editFirstName = new TextInput(PARAMETER_NAME_TO_SEARCH_FOR);
		TextInput editFirstName = new TextInput(PARAMETER_NAME_TO_SEARCH_FOR);
		TextInput editFirstName = new TextInput(PARAMETER_NAME_TO_SEARCH_FOR);
	*/	
		SubmitButton resetBtn = new SubmitButton("Reset", PARAMETER_ACTION, ACTION_SEARCH);
		SubmitButton searchBtn = new SubmitButton("Search", PARAMETER_ACTION, ACTION_SEARCH);

		table.add(resetBtn,5,7);
		table.add(searchBtn,5,7);		
		table.add(editFirstName,1,2);
		table.add(editLastName,2,2);
		table.add(editDateOfBirth_1,3,2);
		table.add(editDateOfBirth_2,5,2);		
		table.add(editDateOfDeath_1,3,4);
		table.add(editDateOfDeath_2,5,4);		
		table.add(editHomeTown,1,6);		
		table.add(editCemetery,2,6);		
		table.add(editDatabase,5,6);		
		
		

		//b.findGraves(null,"Ander",null,null,null,null,null);
		//table.add(getText(iwrb.getLocalizedString("travel.search.defined_product_explained_header", "Check availability.")), 1, 1);

		form.add(table);
		add(form);
		
		
	}
}	
	
