package com.mac.se3a04.taxime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class myJSONLatLongParser {
	
	private static final String RESULT_TAG = "results";
	private static final String LAT_TAG = "lat";
	private static final String LONG_TAG = "lng";
	private String jsonStringMaps;
	
	public myJSONLatLongParser(String jsonStringMaps){
		this.jsonStringMaps = jsonStringMaps;
	}
	
	public double[] getLatLong(){
		try {
			JSONObject jsonObj = new JSONObject(this.jsonStringMaps);
			
			JSONArray results = jsonObj.getJSONArray(RESULT_TAG);
			
			JSONObject jsonLoc  = results.getJSONObject(3);
			
			double[] loc = new double[2];
			
			loc[0] = jsonLoc.getDouble(LAT_TAG);
			loc[1] = jsonLoc.getDouble(LONG_TAG);
			
			return loc;
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

}
