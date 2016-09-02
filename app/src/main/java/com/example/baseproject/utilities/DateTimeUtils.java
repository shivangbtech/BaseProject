package com.example.baseproject.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtils {

	/**
	 * Private Instance Variable
	 */
	private static DateTimeUtils classInstance = null;

	/**
	 * Private Constructor to make this class singleton
	 */
	private DateTimeUtils(){}

	/**
	 * Method return the class instance
	 * @return DateTimeUtils
	 */
	public static DateTimeUtils getInstance(){
		if(classInstance == null){
			classInstance = new DateTimeUtils();
		}
		return classInstance;
	}

	/**
	 * Method call to get current date in formate dd/mm/yyyy
	 */
	public String getCurrentDate(){
		Calendar c = Calendar.getInstance(); 
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		String dayString = String .valueOf(day);
		if(dayString.length()<2){
			dayString = "0"+dayString;
		}
		String monthString = String.valueOf(month+1);
		if(monthString.length()<2){
			monthString = "0"+monthString;
		}
		String currentDate = dayString+"/"+monthString+"/"+String.valueOf(year);
		return currentDate;
	}

	/**
	 * Methos call to get current date in formate yyyy-mm-dd
	 * @return String date
	 */
	public String getCurrentDateFormated(){
		Calendar c = Calendar.getInstance(); 
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		String dayString = String .valueOf(day);
		if(dayString.length()<2){
			dayString = "0"+dayString;
		}
		String monthString = String.valueOf(month+1);
		if(monthString.length()<2){
			monthString = "0"+monthString;
		}
		String currentDate = String.valueOf(year)+"-"+monthString+"-"+dayString;
		return currentDate;
	}

	/**
	 * Method call to get current time in formate hh:mm:ss
	 * @return String time
	 */
	public String getCurrentTimeInFormate(){
		Calendar c = Calendar.getInstance(); 
		int hour = c.get(Calendar.HOUR);
		int min = c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);
		int am_pm = c.get(Calendar.AM_PM);

		if(am_pm == 1){
			hour = hour + 12;
		}
		String currentTime = String.valueOf(hour)+":"+String.valueOf(min)+":"+String.valueOf(sec);
		return currentTime;
	}
	
	/**
	 * Method call to get current time in formate hh:mm amPm
	 * @return String
	 */
	public String getCurrentTime(){
		Calendar c = Calendar.getInstance(); 
		int hour = c.get(Calendar.HOUR);
		int min = c.get(Calendar.MINUTE);
		int am_pm = c.get(Calendar.AM_PM);
//		String dayString = String .valueOf(day);
//		if(dayString.length()<2){
//			dayString = "0"+dayString;
//		}
		String am_Pm_String;
		
		if(am_pm == 1){
			am_Pm_String = "PM";
		}else{
			am_Pm_String = "AM";
		}		
		String currentTime = String.valueOf(hour)+":"+String.valueOf(min)+" "+am_Pm_String;
		return currentTime;
	}

	/**
	 * Method call to get date from string in required formate
	 * @param aDate
	 * @param aFormat
	 * @return Date
	 */
	public Date getStringToDate(String aDate,String aFormat) {
		if(aDate==null) return null;
		ParsePosition pos = new ParsePosition(0);
		SimpleDateFormat simpledateformat = new SimpleDateFormat(aFormat);
		Date stringDate = simpledateformat.parse(aDate, pos);
		return stringDate;            

	}

	/**
	 * Method call to get date in formate dd-MM-yyyy from yyyy-MM-dd
	 * @param date String
	 * @return String date
	 */
	public String getDateFormate(String date) {
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
		String inputDateStr=date;
		Date date1 = null;
		try {
			date1 = inputFormat.parse(inputDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String outputDateStr = outputFormat.format(date1);
		return outputDateStr;
	}

	/**
	 * Method call to get difference between current and given date
	 * @param thenDate
	 * @return String difference
	 */
	public String getDateDifference(Date thenDate){
		Calendar now = Calendar.getInstance();
		Calendar then = Calendar.getInstance();
		now.setTime(new Date());
		then.setTime(thenDate);

		// Get the represented date in milliseconds
		long nowMs = now.getTimeInMillis();
		long thenMs = then.getTimeInMillis();

		// Calculate difference in milliseconds
		long diff = nowMs - thenMs;

		// Calculate difference in seconds
		long diffMinutes = diff / (60 * 1000);
		long diffHours = diff / (60 * 60 * 1000);
		long diffDays = diff / (24 * 60 * 60 * 1000);

		if (diffMinutes<60){
			if (diffMinutes==1)
				return diffMinutes + " minute ago";
			else
				return diffMinutes + " minutes ago";
		} else if (diffHours<24){
			if (diffHours==1)
				return diffHours + " hour ago";
			else
				return diffHours + " hours ago";
		}else if (diffDays<30){
			if (diffDays==1)
				return diffDays + " day ago";
			else
				return diffDays + " days ago";
		}else {
			return "a long time ago..";
		}
	}

	/**
	 * Gets the time as an array of three integers. Index 0 contains the number of
	 * seconds, index 1 contains the number of minutes, and index 2 contains the
	 * number of hours.
	 *
	 * @param time the time in milliseconds
	 * @return an array of 3 elements.
	 */
	public int[] getTimeParts(long time) {
		if (time < 0) {
			int[] parts = getTimeParts(time * -1);
			parts[0] *= -1;
			parts[1] *= -1;
			parts[2] *= -1;
			return parts;
		}
		int[] parts = new int[3];

		long seconds = time / 1000;
		parts[0] = (int) (seconds % 60);
		int minutes = (int) (seconds / 60);
		parts[1] = minutes % 60;
		parts[2] = minutes / 60;
		return parts;
	}
	
	public String getCurrentMonthInWords(){
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
		String month_name = month_date.format(cal.getTime());
		return month_name;
	}
	
	public String getCurrentMonthInShortWords(){
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat month_date = new SimpleDateFormat("MMM");
		String month_name = month_date.format(cal.getTime());
		return month_name;
	}
	
	public Date getCurrentDateEmailFormated(){
		Date dDate = null;
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTimeZone(TimeZone.getDefault());
			SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
			String formattedDate = sdf.format(cal.getTime());
			dDate = sdf.parse(formattedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dDate;
	}
	
	/**
	 * Method call to get current date in formate dd_mm_yyyy
	 */
	public String getCurrentDateUnderScoreFormated(){
		Calendar c = Calendar.getInstance(); 
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		String dayString = String .valueOf(day);
		if(dayString.length()<2){
			dayString = "0"+dayString;
		}
		String monthString = String.valueOf(month+1);
		if(monthString.length()<2){
			monthString = "0"+monthString;
		}
		String currentDate = dayString+"_"+monthString+"_"+String.valueOf(year);
		return currentDate;
	}


	/**
	 * Method call to clean object from memory
	 */
	public void cleanObject(){
		classInstance = null;
	}
}
