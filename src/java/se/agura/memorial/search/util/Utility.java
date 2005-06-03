package se.agura.memorial.search.util;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {


	public static Date stringToDate(String s){
		Date date = null;
		String tmp = null;
		

		
		if (s == null) return null;
		
		if (s.length()<8) return null;
	    try {
			DateFormat formatter = new SimpleDateFormat("yyyymmdd");
			tmp = s.substring(0,7);
	        date = (Date)formatter.parse(tmp);

	    } catch (ParseException e) {
	    }	
		
		
		return date;
	}
	
	
	/**
	 * Trims string, so "  aabb "  becomes "aabb". 
	 * Of course, one can use String.trim() method :)
	 * 
	 * @param s 
	 * @return 
	 */
	public static String leftTrimRightTrim(String s) {
		if (s != null) {
			return s.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
		} else {
			return s;
		}
	}
	
	
}
