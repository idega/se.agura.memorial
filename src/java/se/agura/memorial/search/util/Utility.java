package se.agura.memorial.search.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Utility {


	public static Date stringToDate(String s){
		Date date = null;
		
		if (s == null) return null;
		
		if (s.length()<8) return null;
	    try {
			DateFormat formatter = new SimpleDateFormat("yyyymmdd");
	        date = (Date)formatter.parse(s.substring(0,8));
	    } catch (ParseException e) {
	    }	
		
		
		return date;
	}
	
	
	
}
