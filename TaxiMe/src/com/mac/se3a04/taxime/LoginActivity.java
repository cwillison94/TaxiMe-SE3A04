package com.mac.se3a04.taxime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This class handles user login requests, its is also the entry point for the
 * application. Uses: UserAccessController.
 * 
 * @author Cole Willison
 * @version 1.0
 * @since 2014-11-05
 * 
 * */
public class LoginActivity extends Activity implements OnClickListener {
	/* APP DEBUG TAG */
	public static final String DEBUG_TAG = "TAXI_ME";
	private EditText etLoginEmail;
	private EditText etLoginPassword;
	private Button bLogin;
	private TextView tvNewUser;
	private TextView tvLoginError;
	private UserAccessController userAccController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setUpWidgets();

		//userAccController = new UserAccessController(getBaseContext(), tvLoginError);
		//userAccController.attemptAutoLogin();
	}

	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		userAccController = new UserAccessController(this, tvLoginError);
		userAccController.attemptAutoLogin();
	}
	

	/**
	 * This method initializes the necessary widgets from the xml file and also
	 * sets any OnClickListeners where appropriate
	 * 
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
	}

	// click event handler
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bLogin:
			userAccController.login(etLoginEmail.getText().toString(), etLoginPassword.getText()
					.toString());
			break;
		case R.id.tvNewUser:
			startActivity(new Intent(this, Registration.class));
			break;
		}
	}

}
