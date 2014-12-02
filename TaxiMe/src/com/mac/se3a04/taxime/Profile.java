package com.mac.se3a04.taxime;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mac.se3a04.taxime.R;

/**
 * This fragmentProfile class while display user profiles, both the current user
 * and others
 * 
 * */
public class Profile extends Fragment {
	public static final String PROFILE_KEY = "profile";

	private final String nameTitle = "Name: ";
	private final String ageTitle = "Age: ";
	private final String sexTitle = "Sex: ";
	private final String proffessionTitle = "Proffession: ";
	private String name, sex, age, proffession;
	private TextView tvName, tvSex, tvProffession, tvAge;

	private String[] profile;

	public Profile() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		View view = inflater.inflate(R.layout.fragment_layout_profile, container, false);
		setUpWidgets(view);
		populateProfile();
		return view;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.edit:
			Toast.makeText(getActivity(), "TODO Enter Edit  mode", Toast.LENGTH_SHORT).show();
			//launch profile edit class and pass the current profile attributes
			Intent i = new Intent(getActivity(), ProfileEdit.class);
			Bundle b = new Bundle();
			b.putStringArray(PROFILE_KEY,profile);
			i.putExtras(b);
			getActivity().startActivity(i);
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void setUpWidgets(View view) {
		tvName = (TextView) view.findViewById(R.id.tvName);
		tvAge = (TextView) view.findViewById(R.id.tvAge);
		tvProffession = (TextView) view.findViewById(R.id.tvProffession);
		tvSex = (TextView) view.findViewById(R.id.tvSex);
	}

	private void populateProfile() {
		profile = new UserAccessController(getActivity(),null).getProfile();
		
		name = profile[1] + " " + profile[2];
		sex = profile[3];
		proffession = profile[5].replaceAll("_", " ");
		age = profile[4];
		
		tvName.setText(nameTitle + name);
		tvSex.setText(sexTitle + sex);
		tvProffession.setText(proffessionTitle + proffession);
		tvAge.setText(ageTitle + age);
	}

}
