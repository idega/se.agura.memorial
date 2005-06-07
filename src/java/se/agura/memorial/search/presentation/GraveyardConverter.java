package se.agura.memorial.search.presentation;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import se.agura.memorial.search.api.Graveyard;
import se.agura.memorial.search.api.ObituarySearch;
import se.agura.memorial.search.business.SearchFormSessionBean;
import se.agura.memorial.search.business.SearchImplBroker;

import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.presentation.IWContext;

public class GraveyardConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
			throws ConverterException {

		Graveyard g = null;
		//g = (Graveyard) new SearchFormBackingBean.getGraveyards().get(arg2);
		
		
		/*
		 1. get current data base implementation id (from session bean)
		 2. get database graveyards
		 3. find appropriate graveyard by Id		 
		 */
		
		//1.
		FacesContext facesContext = FacesContext.getCurrentInstance();
		IWContext iwc = IWContext.getIWContext(facesContext);
		try {
			SearchFormSessionBean sb = (SearchFormSessionBean) IBOLookup.getSessionInstance(iwc, SearchFormSessionBean.class);
			
			//2.
			try {
				SearchImplBroker sib = (SearchImplBroker) IBOLookup.getServiceInstance(iwc, SearchImplBroker.class);
				
				ObituarySearch os = sib.getSearch(sb.getDatabaseId().intValue());
				List listOfGraveyards = (List) os.getGraveyards();
				
				Map graveyards = new LinkedHashMap();
				
				for (Iterator it = listOfGraveyards.iterator(); it.hasNext();) {
					Graveyard gr = (Graveyard) it.next();
					graveyards.put(new Integer(gr.getId()).toString(), gr);
				}
				
				//3.
				g = (Graveyard) graveyards.get(arg2);
				
			} catch (IBOLookupException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();	
			}
			
		} catch (IBOLookupException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return g;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ConverterException {
		String s;

		try {

			Graveyard g = (Graveyard) arg2;
			s = new Integer(g.getId()).toString();

		} catch (Exception e) {

			s = (String) arg2;

		}

		return s;
	}

}
