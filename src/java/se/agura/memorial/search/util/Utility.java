package se.agura.memorial.search.util;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import se.agura.memorial.search.api.CustomMemorialDate;

public class Utility {


	public static java.sql.Date stringToSQLDate(String s){
//		java.sql.Date date = null;
		String tmp = null;
		java.util.Date tmpDate = null; 
		int year=2005,month=5,day=3;
		

		java.sql.Date date = new java.sql.Date(year, month, day);
		/*
		if (s == null) return null;
		
		if (s.length()<8) return null;
	    try {
			DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			tmp = s.substring(0,8);
			tmpDate = (java.util.Date)formatter.parse(tmp);
			date = (java.sql.Date)tmpDate;

	    } catch (ParseException e) {
	    }	
		
		*/
		return date;
	}
	
	
	public static Date stringToDate(String s){
		Date date = null;
		String tmp = null;
		

		
		if (s == null) return null;
		
		if (s.length()<8) return null;
	    try {
			DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
			tmp = s.substring(0,8);
	        date = (Date)formatter.parse(tmp);

	    } catch (ParseException e) {
	    }	
		
		
		return date;
	}
	
	
	public static CustomMemorialDate stringToMemorialDate(String date) {

		CustomMemorialDate memorialDate = new CustomMemorialDate(null,null,null);
		

		if (date == null) return memorialDate;

		if (date.length() >= 4) {
			String str;
			Integer tmp;
			str = date.substring(0, 4);
			tmp = Integer.valueOf(str);

			if ((tmp.intValue() > 2500) || (tmp.intValue() < 1700))
				return memorialDate;
			else
				memorialDate.setYear(tmp);
			if (date.length() >= 6) {
				str = date.substring(4, 6);
				tmp = Integer.valueOf(str);
				if ((tmp.intValue() > 12) || (tmp.intValue() < 1))
					return memorialDate;
				else
					memorialDate.setMonth(tmp);

			}
			else return memorialDate;
			if (date.length() >= 8) {
				str = date.substring(6, 8);
				tmp = Integer.valueOf(str);
				if ((tmp.intValue() > 31) || (tmp.intValue() < 1))
					return memorialDate;
				else
					memorialDate.setDay(tmp);

			}

		}
         return memorialDate;
	}	
	
	public static CustomMemorialDate dateToMemorialDate(Date date) {

		CustomMemorialDate memorialDate = new CustomMemorialDate(null,null,null);
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);	
		
		memorialDate.setDay(new Integer(cal.get(GregorianCalendar.DAY_OF_MONTH)));
		memorialDate.setMonth(new Integer(cal.get(GregorianCalendar.MONTH)));
		memorialDate.setYear(new Integer(cal.get(GregorianCalendar.YEAR)));
		
         return memorialDate;
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
