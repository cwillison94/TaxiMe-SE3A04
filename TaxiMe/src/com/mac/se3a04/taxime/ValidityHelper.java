package com.mac.se3a04.taxime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is helper class that checks whether an email and password is 
 * an acceptable entity
 * 
 * @author Cole Willison
 * @version 1.0
 * @since 2014-11-05
 * */
public class ValidityHelper {
	/**
	 * This method returns true if the email is valid by structure. It uses
	 * a regular expressions to determine this.
	 * 
	 * @param String email
	 * @return Boolean
	 * @see Pattern
	 * @see Matcher
	 * 
	 * */
	public static boolean isEmailValid(String email) {
		// regular expression for an email
		String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		CharSequence inputStr = email;
		Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(inputStr);
		if (matcher.matches())
			return true;
		else
			return false;
	}
	
	/**
	 * TODO:
	 * -insert proper password algorithm, currently returns true if greater then 6 characters
	 * 
	 * This method returns true if the password is valid in structure.
	 * 
	 * @param String password
	 * @return Boolean
	 * 
	 * */
	public static boolean isPasswordValid(String password) {
		return password.length() > 6;
	}

}
