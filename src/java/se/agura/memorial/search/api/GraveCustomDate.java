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
public class GraveCustomDate {
	private Integer day = null;
	private Integer month = null;
	private Integer year = null;
	
	public GraveCustomDate(Integer day, Integer month, Integer year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	
	public GraveCustomDate(int day, int month, int year) {
		this.day = new Integer(day);
		this.month = new Integer(month);
		this.year = new Integer(year);
	}	
	
	
	public GraveCustomDate(Date date) {		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);	
		
		this.day = new Integer(cal.get(GregorianCalendar.DAY_OF_MONTH));
		this.month = new Integer(cal.get(GregorianCalendar.MONTH));
		this.year = new Integer(cal.get(GregorianCalendar.YEAR));
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
			for (int i =0; i < s.length(); i++) {
				sb.append('0');
			}
		}			
		sb.append(s);
		sb.append(' ');
		
		
		s = (this.month != null) ? this.month.toString() : "??";
		if (s.length() < 2) {
			for (int i =0; i < s.length(); i++) {
				sb.append('0');
			}
		}			
		sb.append(s);
		sb.append(' ');			
		
		s = (this.day != null) ? this.day.toString() : "??";
		if (s.length() < 2) {
			for (int i =0; i < s.length(); i++) {
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
