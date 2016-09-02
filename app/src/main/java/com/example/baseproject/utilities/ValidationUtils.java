package com.example.baseproject.utilities;

/**
 * @author Shivang
 */

import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidationUtils {


	/**
	 * Private Instance Variable
	 */
	private static ValidationUtils classInstance = null;

	/**
	 * Private Constructor to make this class singleton
	 */
	private ValidationUtils(){}

	/**
	 * Method return the class instance
	 * @return ValidationUtils
	 */
	public static ValidationUtils getInstance(){
		if(classInstance == null){
			classInstance = new ValidationUtils();
		}
		return classInstance;
	}

	/**
	 * Method call to check email validation
	 * @param emailId
	 * @return boolean
	 */
	public boolean isValidEmailAddress(String emailId) {
		if(emailId.trim().length()==0){
			return false;
		}
		Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*" + "\\@"
				+ "\\w+([-.]\\w+)*" + "\\." + "\\w+([-.]\\w+)*");
		Matcher matcher = pattern.matcher(emailId);
		if (matcher.matches())
			return true;
		else
			return false;
	}

	/**
	 * Method call to check for valid password that have alpha numeric with spacial char
	 * Valid password - pass@12
	 * @param stringPassword
	 * @return
     */
	public boolean isValidPassword(String stringPassword) {
		Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&amp;*()_+}{&quot;:;'?/&gt;.&lt;,]).{4,}$");
		return pattern.matcher(stringPassword).matches();
	}

	/**
	 * Method call to check for valid name that have single space in words and
	 * don't allow space in start and end of name and multi space at a time
	 * Valid name - First Median Last
	 * @param stringName
	 * @return
     */
	public boolean isValidName(String stringName) {
		Pattern pattern = Pattern.compile("[a-zA-z]+([ ][a-zA-Z]+)*");
		return pattern.matcher(stringName).matches();
	}

	/**
	 * Method call to validate name
	 * \\p{L} is a Unicode Character Property that matches any kind of letter from any language
	 * Valid names : Silvana Koch-Mehrin
	 * 				Patrick O'Brian
	 * 				François Hollande
	 * 				Peter Müller
	 * @param stringName
	 * @return
     */
	public boolean isValidName1(String stringName) {
		String regx = "^[\\p{L} .'-]+$";
		Pattern pattern = Pattern.compile(regx);
		return pattern.matcher(stringName).matches();
	}

	/**
	 * Method call to Validate, date Not a back date
	 * @param string
	 * @return boolean
	 */

	public boolean validateDate(String string) {
		//				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date strDate;
		try {
			strDate = sdf.parse(string);
			Date date =new Date() ;
			if (date.after(strDate)) {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * Method call to pick date from picker that is valid not in past in formate yyyy-MM-dd
	 * @param newDate
	 * @return boolean
	 */
	public boolean isVaildDateFromPicker(String newDate){
		Calendar c = Calendar.getInstance(); 
		int currentYear = c.get(Calendar.YEAR);
		int currentMonth = c.get(Calendar.MONTH)+1;
		int currentDay = c.get(Calendar.DAY_OF_MONTH);
		//		boolean flag=true;
		int newDateYear = Integer.parseInt(newDate.substring(0, 4));
		int newDateMonth = Integer.parseInt(newDate.substring(5, 7));
		int newDateDay = Integer.parseInt(newDate.substring(8, 10));
		/*if(newDateYear < currentYear){
			return false;
		}
		if((newDateYear >= currentYear) && (newDateMonth < currentMonth)){
			return false;
		}
		if((newDateYear >= currentYear) && (newDateMonth >= currentMonth) && (newDateDay < currentDay)){
			return false;
		}
		 */
		if(newDateYear >= currentYear && newDateMonth >= currentMonth && newDateDay >= currentDay){
			return true;
		}else{
			return false;
		}
		//		return true;
	}


	/**
	 * Method to pick date from picker that is valid not in past in formate yyyy-MM-dd
	 * @param context
	 * @param newDate
	 * @return boolean
	 */
	public boolean isVaildDateFromPickerWithAlert(Context context, String newDate){
		Calendar c = Calendar.getInstance(); 
		int currentYear = c.get(Calendar.YEAR);
		int currentMonth = c.get(Calendar.MONTH)+1;
		int currentDay = c.get(Calendar.DAY_OF_MONTH);
		//		boolean flag=true;
		int newDateYear = Integer.parseInt(newDate.substring(0, 4));
		int newDateMonth = Integer.parseInt(newDate.substring(5, 7));
		int newDateDay = Integer.parseInt(newDate.substring(8, 10));
		if(newDateYear < currentYear){
			AlertUtils.getInstance().pop_Alert(context, "Validation", "Not a valid Year");
			return false;
		}
		if((newDateYear >= currentYear) && (newDateMonth < currentMonth)){
			AlertUtils.getInstance().pop_Alert(context, "Validation", "Not a valid Month");
			return false;
		}
		if((newDateYear >= currentYear) && (newDateMonth >= currentMonth) && (newDateDay < currentDay)){
			AlertUtils.getInstance().pop_Alert(context, "Validation", "Not a valid Day");
			return false;
		}
		return true;
	}


	// Use to pick date from picker that is valid not in past in formate yyyy-MM-dd
	public boolean isVaildTime(Context context, String fromTime, String toTime){

		boolean flag=true;

		int fromHour = Integer.parseInt(fromTime.substring(0, 2));
		int fromMin = Integer.parseInt(fromTime.substring(3, 5));

		System.out.println("fromHour............................."+ fromHour);
		System.out.println("fromMin............................."+ fromMin);


		int toHour = Integer.parseInt(toTime.substring(0, 2));
		int toMin = Integer.parseInt(toTime.substring(3, 5));

		System.out.println("toHour............................."+ toHour);
		System.out.println("toMin............................."+ toMin);


		if(fromHour > toHour){
			AlertUtils.getInstance().pop_Alert(context, "Validation", "Please Select the valid Hour!");
			flag=false;
		}else if((fromHour == toHour) && (fromMin > toMin)){
			AlertUtils.getInstance().pop_Alert(context, "Validation", "Please Select the valid Minute!");
			flag=false;
		}else if((fromHour == toHour) && (fromMin == toMin)){
			AlertUtils.getInstance().pop_Alert(context, "Validation", "From and To Time cann't be equal!");
			flag=false;
		}
		return flag;
	}

	/**
	 * \\d = only digit allow
	 *	{3} = length

	 *All phone numbers must in �xxx-xxxxxxx� format. For example
		1) 012-6677889 � Passed
		2) 01216677889 � Failed , �-� missing
		3) A12-6677889 � Failed , only digit allow
		4) 012-66778899 � Failed, only 7 digits at the end
	 * Phone validation
	 */

	public boolean phoneNumberValidation(String phoneNumberString) {
		//String sPhoneNumber = "605-8889999";
		//String sPhoneNumber = "605-88899991";
		//String sPhoneNumber = "605-888999A";
		Pattern pattern = Pattern.compile("\\d{3}-\\d{7}");
		Matcher matcher = pattern.matcher(phoneNumberString);
		if (matcher.matches()) {
			System.out.println("Phone Number Valid");
			return true;
		}else{
			System.out.println("Phone Number must be in the form XXX-XXXXXXX");
			return false;
		}
	}

	public boolean mobileNumberValidation(String mobileNumberString){
		String regexStr = "^[0-9]$";
		// if want + at start
		//		String regexStr = "^\+[0-9]{10,13}$";
		//		String regexStr = "^[+][0-9]{10,13}$";
		//		String regexStr = "^[+]?[0-9]{10,13}$";
		//matches numbers only
		//		String regexStr = "^[0-9]*$"
		//matches 10-digit numbers only
		//		String regexStr = "^[0-9]{10}$"
		//matches numbers and dashes, any order really.
		//		String regexStr = "^[0-9\\-]*$"
		//matches 9999999999, 1-999-999-9999 and 999-999-9999
		//		String regexStr = "^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$"
		// to validate phones in the US (7 to 10 digits, extensions allowed, etc.)
		//		String regexStr = "^(?:(?:\\+?1\\s*(?:[.-]\\s*)?)?(?:\\(\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\s*(?:[.-]\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})(?:\\s*(?:#|x\\.?|ext\\.?|extension)\\s*(\\d+))?$";
		//start with 639 and followed by 9 digits
		//		String regexStr = "639[0-9]{9}";

		String number=mobileNumberString;
		if(number.length()<10 || number.length()>13 || number.matches(regexStr)==false  ) {
			return false;
		}else {
			return true;
		}
	}

	private boolean isValidPhoneNumber(CharSequence phoneNumber) {
		if (!TextUtils.isEmpty(phoneNumber)) {
			return Patterns.PHONE.matcher(phoneNumber).matches();
		}
		return false;
	}

	/**
	 * Method call to clean object from memory
	 */
	public void cleanObject(){
		classInstance = null;
	}

}

/**
 * @author Shivang
 */