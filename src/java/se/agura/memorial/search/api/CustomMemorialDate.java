package se.agura.memorial.search.api;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Dainis
 * Custom date class to storing incomplete dates like 1890-??-??.
 * Of course full dates can br stored too.
 * 
 * There are no consitency checks for now, so actually 
 * date 2007-77-99 can be created, it's up to programmer to avoid such situations	
 */
public class CustomMemorialDate {
	private Integer day = null;
	private Integer month = null;
	private Integer year = null;
	
	public CustomMemorialDate(Integer year,  Integer month, Integer day) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	
	public CustomMemorialDate(int year, int month,  int day) {
		this.day = new Integer(day);
		this.month = new Integer(month);
		this.year = new Integer(year);
	}	
	


	
	public CustomMemorialDate(Date date) {		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);	
		
		this.day = new Integer(cal.get(GregorianCalendar.DAY_OF_MONTH));
		this.month = new Integer(cal.get(GregorianCalendar.MONTH));
		this.year = new Integer(cal.get(GregorianCalendar.YEAR));
	}
		
	public String getString() {
		String str = "";
		if (year!=null){
			if((year.intValue()>1500) && (year.intValue()<2200)) str=year.toString();
			else str="????";
		}
		else str="????";
		
		if (month!=null){
			if((month.intValue()==0)&&(month.intValue()>13)) str=str + "??";
			else 
				if (month.intValue() < 10) str = str + "0" + month.toString();
				else  str = str + month.toString();
		}
		else str=str + "??";
		
		if (day!=null){
			if((day.intValue()==0)&&(day.intValue()>31)) str=str + "??";
			else 
				if (day.intValue() < 10) str = str + "0" + day.toString();
				else  str = str + day.toString();
		}
		else str=str + "??";
		
		
		return str;
	}

	public String getValue() {
		if((year != null)||(month != null)||(day != null)) return this.getString();
		else return null;
	}

	
	public String getFormatedString() {
		String str = "";
		if (year!=null){
			if((year.intValue()>1500) && (year.intValue()<2200)) str=year.toString();
			else str="???? ";
		}
		else str="???? ";
		
		if (month!=null){
			if((month.intValue()==0)&&(month.intValue()>13)) str=str + "?? ";
			else 
				if (month.intValue() < 10) str = str + "0" + month.toString();
				else  str = str + month.toString();
		}
		else str=str + "?? ";
		
		if (day!=null){
			if((day.intValue()==0)&&(day.intValue()>31)) str=str + "??";
			else 
				if (day.intValue() < 10) str = str + "0" + day.toString();
				else  str = str + day.toString();
		}
		else str=str + "??";
		
		
		return str;
	}
	
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	
	
	public String toString() {
		StringBuffer sb = new StringBuffer("");
		
		String s = null;
		
		s = (this.year != null) ? this.year.toString() : "????";
		if (s.length() < 4) {
			for (int i = 0; i < (4 - s.length()); i++) {
				sb.append('0');
			}
		}			
		sb.append(s);
		sb.append(' ');
		
		
		s = (this.month != null) ? this.month.toString() : "??";
		if (s.length() < 2) {
			for (int i = 0; i < (2 - s.length()); i++) {
				sb.append('0');
			}
		}			
		sb.append(s);
		sb.append(' ');			
		
		s = (this.day != null) ? this.day.toString() : "??";
		if (s.length() < 2) {
			for (int i = 0; i < (2 - s.length()); i++) {
				sb.append('0');
			}
		}			
		sb.append(s);			
		
		return sb.toString();
	}
	
	
	public Date toDate() {		
		GregorianCalendar cal = new GregorianCalendar(this.year.intValue(),
				this.month.intValue(), this.day.intValue());
		return cal.getTime();
	}
	
	
}
