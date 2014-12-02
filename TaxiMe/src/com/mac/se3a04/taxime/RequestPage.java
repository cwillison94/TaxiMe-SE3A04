package com.mac.se3a04.taxime;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class RequestPage extends Activity implements OnClickListener {
	private Button bSumbit;
	private AutoCompleteTextView autoCompViewFrom;
	private AutoCompleteTextView autoCompViewTo;
	
	private TextView tvTempFrom;
	private TextView tvTempTo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_request_page);
		getActionBar().setHomeButtonEnabled(true);
		setUpWidgets();
	}

	private void setUpWidgets() {
		tvTempFrom = (TextView) findViewById(R.id.tvFrom);
		tvTempTo = (TextView) findViewById(R.id.tvTo);
		
		
		bSumbit = (Button) findViewById(R.id.bSubmitRequest);
		bSumbit.setOnClickListener(this);
		
		autoCompViewFrom = (AutoCompleteTextView) findViewById(R.id.actFrom);
		autoCompViewTo = (AutoCompleteTextView) findViewById(R.id.actTo);

		// set the adapters for the results
		autoCompViewFrom.setAdapter(new PlacesAutoCompleteAdapter(this,
				R.layout.auto_complete_list_item));
		autoCompViewTo.setAdapter(new PlacesAutoCompleteAdapter(this,
				R.layout.auto_complete_list_item));
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.bSubmitRequest:
				tvTempFrom.setText(autoCompViewFrom.getText().toString());
				tvTempTo.setText(autoCompViewTo.getText().toString());
				
				new GetLatLong(this).execute(autoCompViewFrom.getText().toString());
				
				break;
		}
	}
}
