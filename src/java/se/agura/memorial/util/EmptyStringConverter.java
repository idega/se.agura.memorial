package se.agura.memorial.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class EmptyStringConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
			throws ConverterException {
		// there's no need to convert names to objects
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ConverterException {

		String s = (String) arg2;
		return formatString(s);
	}

	private String formatString(String arg1) {

		StringBuffer sb = new StringBuffer(arg1 == null ? "" : leftTrimRightTrim(arg1));

		if (sb.length() == 0) {
			sb.append('?');
		}

		return sb.toString();
	}
	
	private String leftTrimRightTrim(String s) {
		if (s != null) {
			return s.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
		} else {
			return s;
		}
	}

}
