package se.agura.memorial.experiment;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class CemeteryConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
			throws ConverterException {	
	
		Cemetery c = null;
		c = (Cemetery) new GraveyardSearchBean().getCemeteries().get(arg2);	 

		return c;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ConverterException {
		String s;
		
		try {

			Cemetery c = (Cemetery) arg2;
			s = c.getId();		
			
		} catch (Exception e) {
			
			s = (String) arg2; 	
			
		}

		return s;
	}

}
