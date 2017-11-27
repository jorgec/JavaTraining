package wbb.billing02.views;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Month {
	
	public static HashMap<String, Double> getMonths() {
		
		// get a list of the month names
		String[] months = new DateFormatSymbols().getMonths();
		
		// prepare a hashmap to store monthName: maxHours
		HashMap<String, Double> monthsMap = new HashMap<String, Double>();
		
		for(int i = 0; i < months.length; i++ ) {
			// get the month start; January = 0, etc
			Calendar monthStart = new GregorianCalendar(2017, i, 1);
			
			// get the number of days in this month and multiply by 24
			Double maxHours = (double) (monthStart.getActualMaximum(Calendar.DAY_OF_MONTH) * 24);			
			
			monthsMap.put(months[i], maxHours );			
		}
		
		return monthsMap;
		
	}
	
	public static Double getMonthHours(String month) {
		HashMap<String, Double> months = getMonths();		
		return months.get(month);
	}

}
