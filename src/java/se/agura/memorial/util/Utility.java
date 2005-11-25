package se.agura.memorial.util;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;

import se.agura.memorial.search.api.CustomMemorialDate;
//import se.agura.memorial.search.presentation.ObituaryItemBean;

import com.idega.data.query.AND;
import com.idega.data.query.BaseLogicGroup;
import com.idega.data.query.Column;
import com.idega.data.query.MatchCriteria;
import com.idega.data.query.SelectQuery;

public class Utility {


	public static final int MAX_RESULT = 100;	
	
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

		int m=date.getMonth();
		CustomMemorialDate memorialDate = new CustomMemorialDate(null,null,null);
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);	
		
		memorialDate.setDay(new Integer(cal.get(GregorianCalendar.DAY_OF_MONTH)));
		memorialDate.setMonth(new Integer(cal.get(GregorianCalendar.MONTH)+1));
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
	
	
	/**
	 * 
	 * Clones the query base and returns collection of queries with name conditions showed above
	 * 
	 * 'firstname' and 'lastname'
	 * '%firstname%' and 'lastname' and not in ('firstname' and 'lastname')
	 * 'firstname' and '%lastname%' and not in ('%firstname%' and 'lastname')
	 * 'firstname%' and 'lastname%' and not 'lastname' and not 'firstname'
	 * '%firstname%' and '%lastname%' and not 'lastname' and not 'firstname' and not in ('firstname%' and 'lastname%')
	 * 
	 * @param firstName
	 * @param lastName
	 * @param colFirstName
	 * @param colLastName
	 * @param queryBase
	 * @return Collection of SelectQuery objects that differ in the way how exact the first_name and last_name conditions are.
	 */
	public static Collection getNameCriteriaQueries(String firstName, String lastName, Column colFirstName, Column colLastName, SelectQuery queryBase, Column idColumn) {
		ArrayList queries = new ArrayList();
		boolean no_changes = true; 
		
		if(firstName != null && lastName != null){
			SelectQuery anyMatch = (SelectQuery)queryBase.clone();
			AND any = new AND(new MatchCriteria(colFirstName, MatchCriteria.LIKE, "%" + firstName.trim() + "%"),new MatchCriteria(colLastName, MatchCriteria.LIKE, "%" + lastName.trim() + "%"));
			anyMatch.addCriteria(any);
			queries.add(anyMatch);
			no_changes = false;			
			
		} else if (firstName != null){
			
			SelectQuery anyMatch = (SelectQuery)queryBase.clone();
			anyMatch.addCriteria(new MatchCriteria(colFirstName, MatchCriteria.LIKE, "%" + firstName.trim() + "%"));
			queries.add(anyMatch);
			no_changes = false;	
			
		} else if (lastName != null){

			SelectQuery anyMatch = (SelectQuery)queryBase.clone();
			anyMatch.addCriteria(new MatchCriteria(colLastName, MatchCriteria.LIKE, "%" + lastName.trim() + "%"));
			queries.add(anyMatch);
			no_changes = false;	
			
		}
		
		
		if (no_changes) queries.add(queryBase);

		return queries;
	}
	
	
	/**
	 * Inverts the two MatchCriterias contained in the AND criteria. (LIKE -> NOT LIKE)
	 * 
	 * @param BaseLogicGroup criteria. AND or OR criteria.
	 * @return
	 */
	private static BaseLogicGroup cloneAndInvert(BaseLogicGroup criteria) {
		BaseLogicGroup inverted = (BaseLogicGroup)criteria.clone();
		Set criterias = inverted.getCriterias();
		for (Iterator iter = criterias.iterator(); iter.hasNext();) {
			MatchCriteria element = (MatchCriteria) iter.next();
			element.setMatchType(MatchCriteria.NOTLIKE);
		}
		return inverted;
	}
	
}
