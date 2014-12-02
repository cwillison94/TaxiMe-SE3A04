package com.mac.se3a04.taxime;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;

public class OfferPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_offer_page);
		getActionBar().setHomeButtonEnabled(true);
		setUpWidgets();
	}


	private void setUpWidgets() {
		AutoCompleteTextView autoCompViewFrom = (AutoCompleteTextView) findViewById(R.id.actFrom);
		AutoCompleteTextView autoCompViewTo = (AutoCompleteTextView) findViewById(R.id.actTo);

		// set the adapters for the results
		autoCompViewFrom.setAdapter(new PlacesAutoCompleteAdapter(this,
				R.layout.auto_complete_list_item));
		autoCompViewTo.setAdapter(new PlacesAutoCompleteAdapter(this,
				R.layout.auto_complete_list_item));
	}
}
