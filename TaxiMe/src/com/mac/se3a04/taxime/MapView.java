package com.mac.se3a04.taxime;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mac.se3a04.taxime.R;

/**
 * This is maps fragment view.
 * 
 * @author Cole Willison
 * @version 0.01
 * @since 2014-10-28
 * */
public class MapView extends Fragment {
	
	public static final String MAPS_API_KEY = "AIzaSyAmVMZRNeiIdgjUPj1TGEO8REUFQqk_ly4";
	private GoogleMap googleMap;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_layout_map, container, false);

		try {
			// Loading map
			initilizeMap();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return view;
	}

	/**
	 * Initializes and sets McMaster as the default view
	 * 
	 * @param None
	 * @return void
	 * */
	private void initilizeMap() {
		if (googleMap == null) {
			googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			googleMap.setMyLocationEnabled(true);

			LatLng mcmaster = new LatLng(43.2633, -79.9189);
			googleMap.moveCamera(CameraUpdateFactory.newLatLng(mcmaster));
			googleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
			googleMap.addMarker(new MarkerOptions().title("McMaster")
					.snippet("The Best University Arround!").position(mcmaster));
			
			// check if map is created successfully or not
			if (googleMap == null) {
				Toast.makeText(getActivity(), "Sorry! unable to create maps", Toast.LENGTH_SHORT)
						.show();
			}
		}
	}
}
