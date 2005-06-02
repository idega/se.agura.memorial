package se.agura.memorial.search.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class Utility {


	public static Date stringToDate(String s){
		Date date = null;
		String tmp = null;
		
		if(s!=null) return null;
		
		if (s == null) return null;
		
		if (s.length()<8) return null;
	    try {
			DateFormat formatter = new SimpleDateFormat("yyyymmdd");
			tmp = s.substring(0,8);
	        //date = (Date)formatter.parse(tmp);
			date = (Date)formatter.parse("20010101");
	    } catch (ParseException e) {
	    }	
		
		
		return date;
	}
	
	
	
}
