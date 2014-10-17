package com.mac.se3a04.taxime;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This fragmentProfile class while display user profiles, both the current user
 * and others
 * 
 * */
public class FragmentProfile extends Fragment {

	public FragmentProfile() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_layout_home, container, false);

		setUpWidgets(view);
		return view;
	}

	private void setUpWidgets(View view) {
	}

}
