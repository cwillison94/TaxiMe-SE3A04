package com.mac.se3a04.taxime;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
	private UserAccessController userAccController;
	private Button bSumbit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		setUpWidgets();
	}
	
	

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		userAccController = new UserAccessController(this, tvErrorMessage);
	}



	/**
	 * This method initializes the necessary widgets from the xml file and also
	 * sets any OnClickListeners where appropriate
	 * 
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

	// handles the submit button click
	@Override
	public void onClick(View v) {
		// make sure the ID of the touch event is the submit button
		if (v.getId() == R.id.bSubmitRegistration) {
			userAccController.sumbitRegistration(etEmail.getText().toString(), etPassword.getText()
					.toString(), etPassword2.getText().toString(),
					etFirstName.getText().toString(), etLastName.getText().toString(), etSex
							.getText().toString(), etProffession.getText().toString(), etAge
							.getText().toString());
		}
	}
}
