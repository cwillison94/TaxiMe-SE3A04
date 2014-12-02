package com.mac.se3a04.taxime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mac.se3a04.taxime.R;

public class ProfileEdit extends Activity implements OnClickListener {

	private EditText etFirstName;
	private EditText etLastName;
	private EditText etProffession;
	private EditText etAge;
	private TextView tvAccount;
	private Button bConfirm;
	
	private Spinner spGender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_edit);
		
		getActionBar().setHomeButtonEnabled(true);
		
		setUpWidgets();
		populateInputs();
	}

	private void setUpWidgets() {
		etFirstName = (EditText) findViewById(R.id.etFirstName);
		etLastName = (EditText) findViewById(R.id.etLastName);
		etProffession = (EditText) findViewById(R.id.etProffession);
		etAge = (EditText) findViewById(R.id.etAge);
		tvAccount = (TextView) findViewById(R.id.tvAccount);
		bConfirm = (Button) findViewById(R.id.bConfirmChanges);
		spGender = (Spinner) findViewById(R.id.spGender);
		bConfirm.setOnClickListener(this);
	}

	@SuppressWarnings("unchecked")
	private void populateInputs() {
		Bundle b = getIntent().getExtras();
		if (b != null) {
			String[] profile = b.getStringArray(Profile.PROFILE_KEY);
			tvAccount.setText("Account Email: " + profile[0]);
			etFirstName.setText(profile[1]);
			etLastName.setText(profile[2]);
			spGender.setSelection(((ArrayAdapter<String>) spGender.getAdapter()).getPosition(profile[3]));
			etAge.setText(profile[4]);
			etProffession.setText(profile[5].replaceAll("_", " "));
		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.bConfirmChanges) {
			
			Toast.makeText(this, String.valueOf(spGender.getSelectedItem()), Toast.LENGTH_SHORT).show();
			new UserAccessController(this, null).setProfile(etFirstName.getText().toString(),
					etLastName.getText().toString(), String.valueOf(spGender.getSelectedItem()), etProffession
							.getText().toString().replaceAll(" ","_"), etAge.getText().toString());

			finish();
			
			startActivity(new Intent(this, TaxiMeMainActivity.class));
			
		}
	}
}
