package com.mac.se3a04.taxime;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This class handles user login requests, its is also the entry point for the application.
 * Uses: UserRecordsDBHandler, ValidityHelper
 * 
 * @author Cole Willison
 * @version 1.0
 * @since 2014-11-05
 * 
 * TODO:
 * -implement autologin is LoginStatus is 1 --> use the fileManager to read/write the user email to storage
 * */
public class LoginActivity extends Activity implements OnClickListener {
	private EditText etLoginEmail;
	private EditText etLoginPassword;
	private Button bLogin;
	private TextView tvNewUser;
	private TextView tvLoginError;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setUpWidgets();

	}
	
	/**
	 * This method initializes the necessary widgets from the xml file 
	 * and also sets any OnClickListeners where appropriate
	 * @param None
	 * @return void
	 * */
	private void setUpWidgets() {
		etLoginEmail = (EditText) findViewById(R.id.etLoginEmail);
		etLoginPassword = (EditText) findViewById(R.id.etLoginPassword);
		bLogin = (Button) findViewById(R.id.bLogin);
		bLogin.setOnClickListener(this);
		tvNewUser = (TextView) findViewById(R.id.tvNewUser);
		tvNewUser.setOnClickListener(this);
		tvLoginError = (TextView) findViewById(R.id.tvLoginError);
		// loginForm = (RelativeLayout) findViewById(R.id.loginForm);
	}

	//click event handler
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bLogin:
			String email = etLoginEmail.getText().toString();
			String password = etLoginPassword.getText().toString();
			login(email, password);
			break;
		case R.id.tvNewUser:
			// Toast.makeText(this, "New user ", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(this, Registration.class));
			break;
		}
	}

	/**
	 * This method handles the login request. It checks if the information is of 
	 * proper structure then then sends it to the database handler
	 * 
	 * @param String email
	 * @param String password
	 * @return void
	 * 
	 * @see UserRecordsDBhandler
	 * 
	 * */
	private void login(String email, String password) {
		//hide keyboard for ascetics 
		hideKeyboard();
		if (ValidityHelper.isEmailValid(email) && ValidityHelper.isPasswordValid(password)) {
			//send information to database handler under LOGIN mode
			new UserRecordsDBhandler(this, tvLoginError, UserRecordsDBhandler.FLAG_LOGIN).execute(email, password);
		} else {
			displayLoginError();
		}

		FileManager fileManager = new FileManager(this, Registration.fname);
		try {
			fileManager.writeToFile(email);
		} catch (IOException e) {
			Toast.makeText(this, "IO error", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * This methods hide the keyboard is it currently open
	 * 
	 * @param None
	 * @return void
	 * 
	 * */
	private void hideKeyboard() {
		InputMethodManager inputManager = (InputMethodManager) this
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		// get the current view status
		View view = this.getCurrentFocus();
		if (view != null) {
			// this closes the keyboard
			inputManager.hideSoftInputFromWindow(view.getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}


	/**
	 * Displays the login error
	 * 
	 * @param None
	 * @return void
	 * 
	 * */
	private void displayLoginError() {
		tvLoginError.setText(getResources().getString(R.string.login_error));
	}
}
