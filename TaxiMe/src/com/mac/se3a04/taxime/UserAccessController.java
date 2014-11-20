package com.mac.se3a04.taxime;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

public class UserAccessController {
	private Context context;
	private TextView tvError;
	private UserRecordsDBhandler dbHandler;

	public UserAccessController(Context context, TextView tvError) {
		this.context = context;
		this.tvError = tvError;
	}

	public void attemptAutoLogin() {
		dbHandler = new UserRecordsDBhandler(this.context, this.tvError);
		String email = getEmailStored();
		if (email != null) {
			dbHandler.setFlag(UserRecordsDBhandler.FLAG_LOGIN_STATUS);
			dbHandler.execute(email);

		} else {
			return;
		}

	}

	public void login(String email, String password) {
		// TODO encrypt password
		dbHandler = new UserRecordsDBhandler(this.context, this.tvError);
		hideKeyboard();
		if (isEmailValid(email) && isPasswordValid(password)) {
			dbHandler = new UserRecordsDBhandler(this.context, this.tvError);
			dbHandler.setFlag(UserRecordsDBhandler.FLAG_LOGIN);
			dbHandler.execute(email, password);
			writeEmailToStorage(email);
		} else {
			this.tvError.setText(this.context.getResources().getString(R.string.login_error));
		}
	}

	public void sumbitRegistration(String email, String password, String password2,
			String firstName, String lastName, String sex, String proffesion, String age) {
		dbHandler = new UserRecordsDBhandler(this.context, this.tvError);
		hideKeyboard();
		if (!isEmpty(email, password, password2, firstName, lastName, sex, proffesion, age)) {
			if (password.equals(password2)) {
				if (isEmailValid(email) && isPasswordValid(password)) {
					dbHandler.setFlag(UserRecordsDBhandler.FLAG_REGISTRATION);
					dbHandler.execute(email, password, firstName, lastName, sex, proffesion, age);
				} else {
					displayError("User email or password is invalid. Password must be greater than 6 characters.");
				}
			} else {
				displayError("Passwords do not match");
			}
		} else {
			displayError("Please fill in all fields");
		}

	}

	/**
	 * "Accept" - select.php "Denied" - select.php "Success" - registration.php
	 * "userExits" - registration.php "LoggedIn" - getLoginStatus.php "Logout" -
	 * logout.php
	 * 
	 * 
	 * */
	public void processServerResults(String result) {
		Log.d(LoginActivity.DEBUG_TAG, result);
		if (result.equals("Accept") || result.equals("Success") || result.equals("LoggedIn")) {
			((Activity) this.context).finish();
			this.context.startActivity(new Intent(this.context, TaxiMeMainActivity.class).putExtra(
					"email", getEmailStored()));
			if (result.equals("Success")) {
				Toast.makeText(this.context, "Account was Successfully Created!",
						Toast.LENGTH_SHORT).show();
			}
		} else if (result.equals("userExits")) {
			tvError.setText(this.context.getResources().getString(R.string.registration_exists));
		} else if (result.equals("Denied")) {
			tvError.setText(this.context.getResources().getString(R.string.login_error));
		} else if (result.equals("Logout")) {
			// code branch for result.equals("Logout")
			((Activity) context).finish();
			this.context.startActivity(new Intent(this.context, LoginActivity.class));

		}
	}

	public void logout() {
		dbHandler = new UserRecordsDBhandler(this.context, this.tvError);
		String email = getEmailStored();
		if (email != null) {
			dbHandler.setFlag(UserRecordsDBhandler.FLAG_LOGOUT);
			dbHandler.execute(email);

		} else {
			return;
		}

	}

	private void displayError(String errorMessage) {
		this.tvError.setText(errorMessage);
	}

	private boolean isEmpty(String email, String password, String password2, String firstName,
			String lastName, String sex, String proffesion, String age) {
		if (email.equals("") || password.equals("") || password2.equals("") || firstName.equals("")
				|| lastName.equals("") || sex.equals("") || proffesion.equals("") || age.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	private String getEmailStored() {
		String emailString = null;
		FileManager fileManager = new FileManager(this.context, FileManager.fname);
		try {
			emailString = fileManager.readFile();
		} catch (IOException e) {
			Toast.makeText(this.context, e.toString(), Toast.LENGTH_SHORT).show();
			Log.d(LoginActivity.DEBUG_TAG, e.toString());
		}
		return emailString;
	}

	private void writeEmailToStorage(String email) {
		FileManager fileManager = new FileManager(this.context, FileManager.fname);
		try {
			// attempt to write user email to to file
			fileManager.writeToFile(email);
		} catch (IOException e) {
			Log.d(LoginActivity.DEBUG_TAG, e.toString());
		}
	}

	/**
	 * This method returns true if the email is valid by structure. It uses a
	 * regular expressions to determine this.
	 * 
	 * @param String
	 *            email
	 * @return Boolean
	 * @see Pattern
	 * @see Matcher
	 * 
	 * */
	private boolean isEmailValid(String email) {
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
	 * TODO: -insert proper password algorithm, currently returns true if
	 * greater then 6 characters
	 * 
	 * This method returns true if the password is valid in structure.
	 * 
	 * @param String
	 *            password
	 * @return Boolean
	 * 
	 * */
	private boolean isPasswordValid(String password) {
		return password.length() > 6;
	}

	/**
	 * This methods hide the keyboard is it currently open
	 * 
	 * @param None
	 * @return void
	 * 
	 * */
	private void hideKeyboard() {
		InputMethodManager inputManager = (InputMethodManager) this.context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		// get the current view status
		View view = ((Activity) this.context).getCurrentFocus();
		if (view != null) {
			// this closes the keyboard
			inputManager.hideSoftInputFromWindow(view.getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

}
