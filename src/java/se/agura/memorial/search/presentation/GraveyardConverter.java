package se.agura.memorial.search.presentation;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import se.agura.memorial.search.api.Graveyard;

public class GraveyardConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
			throws ConverterException {

		Graveyard g = null;
		g = (Graveyard) new SearchFormBackingBean().getGraveyards().get(arg2);

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
