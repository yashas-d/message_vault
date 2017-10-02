package com.JellyWorks.Helpers;

import java.util.HashSet;
import java.util.Set;

public class TypeChecks {

	public static boolean isNumeric(String s){
		try{
			Integer.parseInt(s);
			return true;
		}
		catch(NumberFormatException e){
			return false;
		}
	}
	private static Set<String> dates = new HashSet<String>();
	static {
	    for (int year = 0; year < 50; year++) {
	        for (int month = 1; month <= 12; month++) {
	            for (int day = 1; day <= 31; day++) {
	                StringBuilder date = new StringBuilder();
	                date.append(Integer.toString(month));
	                date.append("/");
	                date.append(Integer.toString(day));
	                date.append("/");
	                date.append(Integer.toString(year));
	                dates.add(date.toString());
	            }
	        }
	    }
	}

	public static boolean isValidDate(String dateString) {
	    return dates.contains(dateString);
	}
}
