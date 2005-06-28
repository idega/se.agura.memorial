package se.agura.memorial.search.util;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Set;
import com.idega.data.query.AND;
import com.idega.data.query.BaseLogicGroup;
import com.idega.data.query.Column;
import com.idega.data.query.InCriteria;
import com.idega.data.query.MatchCriteria;
import com.idega.data.query.SelectQuery;
import se.agura.memorial.search.api.CustomMemorialDate;

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
		
		if(firstName != null && lastName != null){
			//'firstname' and 'lastname'
			SelectQuery exactMatch = (SelectQuery)queryBase.clone();
			AND exact = new AND(new MatchCriteria(colFirstName, MatchCriteria.LIKE, firstName.trim()),new MatchCriteria(colLastName, MatchCriteria.LIKE, lastName.trim()));
			exactMatch.addCriteria(exact);
			queries.add(exactMatch);
			
			
//			'%firstname%' and 'lastname' and not in ('firstname' and 'lastname')
			SelectQuery exactMatchIdColumn = (SelectQuery)exactMatch.clone();
			exactMatchIdColumn.removeAllColumns();
			exactMatchIdColumn.addColumn(idColumn);
			exactMatchIdColumn.removeAllOrder();
			InCriteria notExact = new InCriteria(idColumn,exactMatchIdColumn);
			notExact.setAsNotInCriteria();
			
			
			SelectQuery exactLastNameMatch = (SelectQuery)queryBase.clone();
			AND exactLastName = new AND(new MatchCriteria(colFirstName, MatchCriteria.LIKE, "%" + firstName.trim() + "%"),new MatchCriteria(colLastName, MatchCriteria.LIKE, lastName.trim()));
			exactLastNameMatch.addCriteria(exactLastName);
			exactLastNameMatch.addCriteria(notExact);
			queries.add(exactLastNameMatch);
			

//			 'firstname' and '%lastname%' and not in ('%firstname%' and 'lastname')
			SelectQuery exactLastNameIdColumn = (SelectQuery)queryBase.clone();
			exactLastNameIdColumn.addCriteria(exactLastName);
			exactLastNameIdColumn.removeAllColumns();
			exactLastNameIdColumn.addColumn(idColumn);
			exactLastNameIdColumn.removeAllOrder();
			InCriteria notExactLastName = new InCriteria(idColumn,exactLastNameIdColumn);
			notExactLastName.setAsNotInCriteria();
			
			
			SelectQuery exactFirstNameMatch = (SelectQuery)queryBase.clone();
			AND exactFirstName = new AND(new MatchCriteria(colFirstName, MatchCriteria.LIKE, firstName.trim()),new MatchCriteria(colLastName, MatchCriteria.LIKE, "%" + lastName.trim() + "%"));
			exactFirstNameMatch.addCriteria(exactFirstName);
			exactFirstNameMatch.addCriteria(notExactLastName);
			queries.add(exactFirstNameMatch);
			
//			'firstname%' and 'lastname%' and not 'lastname' and not 'firstname'
			
			AND neitherExact = (AND)cloneAndInvert(exact);
			
			SelectQuery startsWith = (SelectQuery)queryBase.clone();
			AND starts = new AND(new MatchCriteria(colFirstName, MatchCriteria.LIKE, firstName.trim() + "%"),new MatchCriteria(colLastName, MatchCriteria.LIKE, lastName.trim() + "%"));
			startsWith.addCriteria(starts);
			startsWith.addCriteria(neitherExact);
			queries.add(startsWith);
			
//			'%firstname%' and '%lastname%' and not 'lastname' and not 'firstname' and not in ('firstname%' and 'lastname%')
			SelectQuery startsWithIdColumn = (SelectQuery)startsWith.clone();
			startsWithIdColumn.removeAllColumns();
			startsWithIdColumn.addColumn(idColumn);
			startsWithIdColumn.removeAllOrder();
			InCriteria notStartsWith = new InCriteria(idColumn,startsWithIdColumn);
			notStartsWith.setAsNotInCriteria();


			SelectQuery anyMatch = (SelectQuery)queryBase.clone();
			AND any = new AND(new MatchCriteria(colFirstName, MatchCriteria.LIKE, "%" + firstName.trim() + "%"),new MatchCriteria(colLastName, MatchCriteria.LIKE, "%" + lastName.trim() + "%"));
			anyMatch.addCriteria(any);
			anyMatch.addCriteria(neitherExact);
			anyMatch.addCriteria(notStartsWith);
			queries.add(anyMatch);
			
		} else if (firstName != null){
			
			SelectQuery exactMatch = (SelectQuery)queryBase.clone();
			exactMatch.addCriteria(new MatchCriteria(colFirstName, MatchCriteria.LIKE, firstName.trim()));
			queries.add(exactMatch);
			
			MatchCriteria notExact = new MatchCriteria(colFirstName, MatchCriteria.NOTLIKE, firstName.trim());
			queryBase.addCriteria(notExact);
			
			
			SelectQuery startsWith = (SelectQuery)queryBase.clone();
			startsWith.addCriteria(new MatchCriteria(colFirstName, MatchCriteria.LIKE, firstName.trim() + "%"));
			queries.add(startsWith);
			
			MatchCriteria notStartsWith = new MatchCriteria(colFirstName, MatchCriteria.NOTLIKE, firstName.trim() + "%");
			queryBase.addCriteria(notStartsWith);
			
			
			SelectQuery anyMatch = (SelectQuery)queryBase.clone();
			anyMatch.addCriteria(new MatchCriteria(colFirstName, MatchCriteria.LIKE, "%" + firstName.trim() + "%"));
			queries.add(anyMatch);
			
		} else if (lastName != null){
			SelectQuery exactMatch = (SelectQuery)queryBase.clone();
			exactMatch.addCriteria(new MatchCriteria(colFirstName, MatchCriteria.LIKE, lastName.trim()));
			queries.add(exactMatch);
			
			MatchCriteria notExact = new MatchCriteria(colFirstName, MatchCriteria.NOTLIKE, lastName.trim());
			queryBase.addCriteria(notExact);
			
			
			SelectQuery startsWith = (SelectQuery)queryBase.clone();
			startsWith.addCriteria(new MatchCriteria(colFirstName, MatchCriteria.LIKE, lastName.trim() + "%"));
			queries.add(startsWith);
			
			MatchCriteria notStartsWith = new MatchCriteria(colFirstName, MatchCriteria.NOTLIKE, lastName.trim() + "%");
			queryBase.addCriteria(notStartsWith);
			
			
			SelectQuery anyMatch = (SelectQuery)queryBase.clone();
			anyMatch.addCriteria(new MatchCriteria(colFirstName, MatchCriteria.LIKE, "%" + lastName.trim() + "%"));
			queries.add(anyMatch);
			
		}
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
