package com.example.baseproject.utilities;

/**
 * @author Shivang
 */

import java.util.ArrayList;
import java.util.Vector;

public class StringUtils {

	/**
	 * Private Instance Variable
	 */
	private static StringUtils classInstance = null;

	/**
	 * Private Constructor to make this class singleton
	 */
	private StringUtils(){}

	/**
	 * Method return the class instance
	 * @return StringUtils
	 */
	public static StringUtils getInstance(){
		if(classInstance == null){
			classInstance = new StringUtils();
		}
		return classInstance;
	}

	public boolean hasLength(String str) {
		return hasLength((CharSequence) str);
	}

	public boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * Delete all occurrences of the given substring.
	 * @param inString the original String
	 * @param pattern the pattern to delete all occurrences of
	 * @return the resulting String
	 */
	public String delete(String inString, String pattern) {
		return replace(inString, pattern, "");
	}

	/**
	 * Replace all occurences of a substring within a string with
	 * another string.
	 * @param inString String to examine
	 * @param oldPattern String to replace
	 * @param newPattern String to insert
	 * @return a String with the replacements
	 */
	public String replace(String inString, String oldPattern, String newPattern) {
		if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sb.append(inString.substring(pos, index));
			sb.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sb.append(inString.substring(pos));
		// remember to append any characters to the right of a match
		return sb.toString();
	}

	/**
	 * Formats the given text as a XML CDATA element. This includes adding the
	 * starting and ending CDATA tags. Please notice that this may result in
	 * multiple consecutive CDATA tags.
	 *
	 * @param text the given text
	 */
	public String formatCData(String text) {
		return "<![CDATA[" + text.replaceAll("]]>", "]]]]><![CDATA[>") + "]]>";
	}

	/**
	 * Method call to get extracted string list from comma(,) extracted string
	 * @param inputString
	 * @return ArrayList of String
	 */
	public ArrayList<String> extractCommas(String inputString){
		ArrayList<String> outputList = new ArrayList<String>();
		int commaIndex = inputString.lastIndexOf(",");
		while(commaIndex != -1){
			String st = inputString.substring(commaIndex+1, inputString.length());
			outputList.add(st.trim());
			inputString = inputString.replace(","+st, "");
			commaIndex = inputString.lastIndexOf(",");
		}
		return outputList;
	}
	
	public ArrayList<String> extractAt(String inputString){
		ArrayList<String> outputList = new ArrayList<String>();
		int commaIndex = inputString.lastIndexOf("@");
		while(commaIndex != -1){
			String st = inputString.substring(commaIndex+1, inputString.length());
			
			outputList.add(st.trim());
			inputString = inputString.substring(0, commaIndex);
			commaIndex = inputString.lastIndexOf("@");
		}
		return outputList;
	}

	/**
	 * Method call to get extracted string array by given position, given position will be removed
	 * @param originalString
	 * @param position
	 * @return
	 */
	public String [] SplitString(String originalString, int position ){
		if(position == -1){
			String[] stringArray = {originalString};
			System.out.println("position is -1.................");
			return stringArray;
		}
		String temp = originalString;
		String part1, part2;
		int len = temp.length();
		part1 =  temp.substring(0, position);
		part2 = temp.substring(position+1, len);
		String[] stringArray = {part1, part2};
		return stringArray;
	}

	/**
	 * Method extract the mandatory Field String
	 * @param originalString
	 * @return String Array
	 */
	public String [] SplitMandatoryFieldString(String originalString){
		String temp = originalString;
		String part1, part2;
		int len = temp.length();
		part1 =  temp.substring(0, len-1);
		part2 = temp.substring(len-1);
		if(part2.equalsIgnoreCase("*")){
			String[] stringArray = {part1, part2};
			return stringArray;
		}else{
			String[] stringArray = {part1};
			return stringArray;
		}
	}

	/**
	 * Method call to make a number to comma separated
	 * @param nNum
	 * @return String
	 */
	public String CommaSeparator(String nNum) {
		if (nNum == null)
			return "";
		String result = "";
		String temp = nNum;
		if (Double.parseDouble(temp) >= 1000) {
			String strleft = temp;
			int Length = strleft.length();
			do {
				String PartA = strleft.substring(0, Length - 3);
				String PartB = strleft.substring(Length - 3, Length);
				result = "," + PartB + result;
				strleft = PartA;
				Length = strleft.length();
			} while (Length > 3);
			if (strleft == "") {
				result = result.substring(1, result.length());
			} else {
				result = strleft + result;
			}
			return result;
		}
		return temp;
	}

	/**
	 * Method returned last char or digits from a string
	 * @param ccno
	 * @param digitsCount
	 * @return String
	 */
	public String getLastNumbers(String ccno, int digitsCount) {
		try {
			int len = ccno.length();
			int beginIndex = len - digitsCount;
			int endIndex = len;
			return ccno.substring(beginIndex, endIndex);
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * Method call to extract number from a Number String
	 * @param number
	 * @return String
	 */
	public String extractNumber(String number){
		try {
			int pos = 0;
			if(number.startsWith("+91")){
				pos = 3;
			}else if(number.startsWith("0")){
				pos = 1;
			}
			int len = number.length();
			int beginIndex = pos;
			int endIndex = len;
			return number.substring(beginIndex, endIndex);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Method returned the String in upper case
	 * @param text
	 * @return String
	 */
	public String ToProperCase(String text) {
		text = text.toLowerCase();
		StringBuffer buff = new StringBuffer();
		buff.append(text.toLowerCase());
		boolean upper = true;
		for (int i = 0; i < buff.length(); i++) {
			char c = buff.charAt(i);
			if (upper)
				buff.setCharAt(i, Character.toUpperCase(c));
			upper = (c == ' ');
		}
		return buff.toString();
	}

	/**
	 * Split String from separator char
	 * @param original
	 * @param separator
	 * @return String array
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String[] split(String original, String separator) {
		Vector nodes = new Vector();
		String[] result = null;
		try {
			int index = original.indexOf(separator);
			while (index >= 0) {
				nodes.addElement(original.substring(0, index));
				original = original.substring(index + separator.length());
				index = original.indexOf(separator);
			}
			if (!original.trim().equals(""))
				nodes.addElement(original);
			result = new String[nodes.size()];
			if (nodes.size() > 0)
				for (int loop = 0; loop < nodes.size(); loop++)
					result[loop] = (String) nodes.elementAt(loop);
		} catch (Exception e) { /* MBank.ERR = "1011:"+e.getMessage(); */
		}
		return result;
	}

	/**
	 * Split String	
	 * @param strString
	 * @param strDelimiter
	 * @return string
	 */
	public String[] Split(String strString, String strDelimiter) {
		String[] strArray;
		int iOccurrences = 0;
		int iIndexOfInnerString = 0;
		int iIndexOfDelimiter = 0;
		int iCounter = 0;
		if (strString == null) {
			throw new IllegalArgumentException("Input string cannot be null.");
		}
		if (strDelimiter.length() <= 0 || strDelimiter == null) {
			throw new IllegalArgumentException(
					"Delimeter cannot be null or empty.");
		}
		if (strString.startsWith(strDelimiter)) {
			strString = strString.substring(strDelimiter.length());
		}
		if (!strString.endsWith(strDelimiter)) {
			strString += strDelimiter;
		}
		while ((iIndexOfDelimiter = strString.indexOf(strDelimiter,
				iIndexOfInnerString)) != -1) {
			iOccurrences += 1;
			iIndexOfInnerString = iIndexOfDelimiter + strDelimiter.length();
		}
		strArray = new String[iOccurrences];
		iIndexOfInnerString = 0;
		iIndexOfDelimiter = 0;
		while ((iIndexOfDelimiter = strString.indexOf(strDelimiter,
				iIndexOfInnerString)) != -1) {
			strArray[iCounter] = strString.substring(iIndexOfInnerString,
					iIndexOfDelimiter);
			iIndexOfInnerString = iIndexOfDelimiter + strDelimiter.length();
			iCounter += 1;
		}
		return strArray;
	}

	/**
	 * Replace existing chars with new chars in a string
	 * @param source
	 * @param pattern
	 * @param replacement
	 * @return String
	 */
	public String replaceAll(String source, String pattern,	String replacement) {
		if (source == null)
			return "";
		StringBuffer sb = new StringBuffer();
		int idx = -1;
		String workingSource = source;
		while ((idx = workingSource.indexOf(pattern)) != -1) {
			sb.append(workingSource.substring(0, idx));
			sb.append(replacement);
			sb.append(workingSource.substring(idx + pattern.length()));
			workingSource = sb.toString();
			sb.delete(0, sb.length());
		}
		return workingSource;
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
