package se.agura.memorial.search.presentation;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class DateConverter implements Converter{

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) throws ConverterException {		
		// there's no need to convert dates to objects
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) throws ConverterException {
		// TODO Auto-generated method stub
		
		//this should be correct
		//String s = (String) arg2;
		//return formatDateString(s);	
		
		
		if (arg2 == null) {			
			return "???? ?? ??";
		} else {
			//Date d = (Date) arg2;
			
			//Format formatter = new SimpleDateFormat("yyyy MM DD");
		    //String s = formatter.format(d);
			//return s;
			
			return arg2.toString();
		}
		
		//return "2222 22 22";
		//return arg2.toString();
	}
	
	private String formatDateString(String arg1) {	
		
		StringBuffer sbInput = new StringBuffer(arg1==null?"":leftTrimRightTrim(arg1));
		
		if (sbInput.length() < 8) {
			do {
				sbInput.append('?');
			} while (sbInput.length() < 8);			
		}
		
		if (sbInput.length() > 8) {			
			sbInput.delete(8,sbInput.length());			
		}
		
		StringBuffer sbOutput = new StringBuffer("");
		sbOutput.append(sbInput.substring(0,4));
		sbOutput.append(" ");
		sbOutput.append(sbInput.substring(4,6));
		sbOutput.append(" ");		
		sbOutput.append(sbInput.substring(6,8));

		return sbOutput.toString();
	}
	
	private String leftTrimRightTrim(String s) {
		if (s != null) {
			return s.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
		} else {
			return s;
		}
	}

}
