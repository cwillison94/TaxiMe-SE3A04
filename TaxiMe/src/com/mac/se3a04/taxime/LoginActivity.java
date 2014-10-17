package com.mac.se3a04.taxime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {
	private EditText etLoginEmail;
	private EditText etLoginPassword;
	private Button bLogin;
	private TextView tvNewUser;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setUpWidgets();
	}
	
	private void setUpWidgets() {
		etLoginEmail = (EditText)findViewById(R.id.etLoginEmail);
		etLoginEmail.setOnClickListener(this);
		etLoginPassword = (EditText)findViewById(R.id.etLoginPassword);
		etLoginPassword.setOnClickListener(this);
		bLogin = (Button)findViewById(R.id.bLogin);
		bLogin.setOnClickListener(this);
		tvNewUser = (TextView)findViewById(R.id.tvNewUser);
		tvNewUser.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.bLogin:
			String email = etLoginEmail.getText().toString();
			String password = etLoginPassword.getText().toString();
			login(email, password);
			break;
		case R.id.tvNewUser:
			Toast.makeText(this, "New user ",Toast.LENGTH_SHORT).show();
			break;
		}	
	}
	
	private void login(String email, String password) {
		//TODO insert login code -->check if user email and password are in database
		if (isEmailValid(email) && isPasswordValid(password)) {
			Toast.makeText(this, "Login Succesful", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(this,TaxiMeMainActivity.class));
		} else if (!isEmailValid(email)) {
			Toast.makeText(this, "Email Invalid", Toast.LENGTH_SHORT).show();
		} else if (!isPasswordValid(password)) {
			Toast.makeText(this, "Password Invalid", Toast.LENGTH_SHORT).show();
		}
	}
	
	private boolean isEmailValid(String email) {
	    boolean isValid = false;

	    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
	    CharSequence inputStr = email;

	    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(inputStr);
	    if (matcher.matches()) {
	        isValid = true;
	    }
	    return isValid;
	}
	
	private boolean isPasswordValid(String password) {
		//TODO insert password validity code
		return true;
	}
}
