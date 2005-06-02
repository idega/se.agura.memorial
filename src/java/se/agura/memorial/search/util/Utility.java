package se.agura.memorial.search.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {


	public static Date stringToDate(String s){
		Date date = null;
		
		if (s == null) return null;
		
		if (s.length()!=8) return null;
	    try {
			DateFormat formatter = new SimpleDateFormat("yyyymmdd");
			//TODO  what will happen if date is like this: 200301
	        date = (Date)formatter.parse(s);
	    } catch (ParseException e) {
	    }	
		
		
		return date;
	}
	
	
	
}
