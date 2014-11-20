package com.mac.se3a04.taxime;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * The class handles user registration.
 * 
 * USES: UserRecordsDBhandler, ValidityHelper
 * 
 * @author Cole Willison
 * @version 1.0
 * @since 2014-11-05
 * */
public class Registration extends Activity implements OnClickListener {
	public static String fname = "TaxiMeUserEmail.txt";
	private EditText etEmail;
	private EditText etPassword;
	private EditText etPassword2;
	private EditText etFirstName;
	private EditText etLastName;
	private EditText etSex;
	private EditText etProffession;
	private EditText etAge;
	private TextView tvErrorMessage;
	private Button bSumbit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		setUpWidgets();
	}

	/**
	 * This method initializes the necessary widgets from the xml file 
	 * and also sets any OnClickListeners where appropriate
	 * @param None
	 * @return void
	 * */
	private void setUpWidgets() {
		etEmail = (EditText) findViewById(R.id.etEmail);
		etPassword = (EditText) findViewById(R.id.etPassword);
		etPassword2 = (EditText) findViewById(R.id.etPassword2);
		etFirstName = (EditText) findViewById(R.id.etFirstName);
		etLastName = (EditText) findViewById(R.id.etLastName);
		etSex = (EditText) findViewById(R.id.etSex);
		etProffession = (EditText) findViewById(R.id.etProffession);
		etAge = (EditText) findViewById(R.id.etAge);
		bSumbit = (Button) findViewById(R.id.bSubmitRegistration);
		tvErrorMessage = (TextView) findViewById(R.id.tvErrorMessage);
		bSumbit.setOnClickListener(this);
	}

	//handles the submit button click
	@Override
	public void onClick(View v) {
		// make sure the ID of the touch event is the submit button
		if (v.getId() == R.id.bSubmitRegistration) {
			// ensure all fields are not empty
			if (!(empty(etEmail) || empty(etPassword) || empty(etPassword2) || empty(etFirstName)
					|| empty(etLastName) || empty(etSex) || empty(etProffession) || empty(etAge))) {
				// ensure password fields match
				if (getText(etPassword).equals(getText(etPassword2))) {
					if (ValidityHelper.isEmailValid(getText(etEmail))
							&& ValidityHelper.isPasswordValid(getText(etPassword))) {
						// submit the data to User Records Database and
						// configure as REGISTRATION
						new UserRecordsDBhandler(this, tvErrorMessage,
								UserRecordsDBhandler.FLAG_REGISTRATION).execute(getText(etEmail),
								getText(etPassword), getText(etFirstName), getText(etLastName),
								getText(etSex), getText(etProffession), getText(etAge));

						FileManager fileManager = new FileManager(this, fname);
						try {
							// attempt to write user email to to file
							fileManager.writeToFile(getText(etEmail));
						} catch (IOException e) {
							Toast.makeText(this, "IO error", Toast.LENGTH_SHORT).show();
						}
					} else {
						//email or password is invalid
						tvErrorMessage
								.setText("User email or password is invalid(must be more than 6 characters).");
					}
				} else {
					// if passwords do not match
					tvErrorMessage.setText("Passwords do not match, please try again.");
				}
			} else {
				// Not all input fields are filled
				tvErrorMessage.setText("Please fill in all fields.");
			}
		}
	}

	/**
	 * This method returns true if the specified EditText field
	 * is empty, ie. when it equals the empty string
	 * 
	 * @param EditText
	 * @return Boolean
	 * */
	private boolean empty(EditText etField) {
		return etField.getText().toString().equals("");
	}

	/**
	 * This method gets the string within the specified EditText
	 * field.
	 * 
	 * @param EditText
	 * @return String
	 * 
	 * */
	private String getText(EditText etField) {
		return etField.getText().toString();
	}
}
