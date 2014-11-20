package com.mac.se3a04.taxime;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This fragmentProfile class while display user profiles, both the current user
 * and others
 * 
 * */
public class FragmentProfile extends Fragment {
	public static final String NAME_KEY = "name";
	public static final String AGE_KEY = "age";
	public static final String SEX_KEY = "sex";
	public static final String PROFF_KEY = "proff";
	
	private final String nameTitle = "Name: ";
	private final String ageTitle = "Age: ";
	private final String sexTitle = "Sex: ";
	private final String proffessionTitle = "Proffession: ";
	private String name, sex, age, proffession;	
	private TextView tvName, tvSex, tvProffession, tvAge;

	public FragmentProfile(){
	}
	
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		View view = inflater.inflate(R.layout.fragment_layout_profile, container, false);
		setUpArguments();
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
			break;
		}
		return super.onOptionsItemSelected(item);
	}



	private void setUpArguments() {
		name = getArguments().getString(NAME_KEY,"Bob Barker");
		sex = getArguments().getString(SEX_KEY, "zygote");
		proffession = getArguments().getString(PROFF_KEY, "paper clip");
		age = getArguments().getString(AGE_KEY,"10");
	}

	private void setUpWidgets(View view) {
		tvName = (TextView) view.findViewById(R.id.tvName);
		tvAge = (TextView) view.findViewById(R.id.tvAge);
		tvProffession = (TextView) view.findViewById(R.id.tvProffession);
		tvSex = (TextView) view.findViewById(R.id.tvSex);
	}
	
	private void populateProfile() {
		tvName.setText(nameTitle + name); 
		tvSex.setText(sexTitle + sex); 
		tvProffession.setText(proffessionTitle + proffession);
		tvAge.setText(ageTitle + age);
	}

}
