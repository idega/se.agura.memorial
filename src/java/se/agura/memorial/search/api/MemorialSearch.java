package se.agura.memorial.search.api;


import se.agura.memorial.search.impl.MalmoSearchBMPBean;

import com.idega.presentation.Block;
import com.idega.presentation.ui.Form;
import com.idega.presentation.Table;
import com.idega.presentation.IWContext;


/**
 * @author Igors
 *
 */
public class MemorialSearch extends Block{

	/**
	 * @param args
	 */
	
	public void main(IWContext iwc) throws Exception {
		
		Form form = new Form();
		Table table = new Table();
		table.setBorder(2);
		table.add("This is a test");
		
		MalmoSearchBMPBean b = new MalmoSearchBMPBean();
		
		b.getGraveyards();
		
		form.add(table);
		add(form);
		
		
	}
}	
	
