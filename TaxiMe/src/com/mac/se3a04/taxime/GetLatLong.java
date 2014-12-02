package com.mac.se3a04.taxime;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class GetLatLong extends AsyncTask<String,Void,double[]>{
	
	private final String BASE = "https://maps.googleapis.com/maps/api/geocode/json?";
	private final String ADDRESS_TAG ="address=";
	private final String KEY_TAG ="key=";
	
	private Context context;

	public GetLatLong(Context context){
		this.context = context;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected double[] doInBackground(String... params) {
		String link = null;
		try {
			
			link = BASE + ADDRESS_TAG + URLEncoder.encode(params[0],"UTF-8") + "&" + KEY_TAG + Container.API_KEY_BROWSER;
			Log.d(Container.DEBUG_TAG, "link: " + link);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			HttpClient client = new DefaultHttpClient();
			
			/*HttpGet request = new HttpGet(link);
			
			request.setURI(new URI(link));
			HttpResponse response = client.execute(request);
			BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));

			StringBuffer sb = new StringBuffer();
			String line = "";
			while ((line = in.readLine()) != null) {
				sb.append(line);
				break;
			}
			in.close();
			*/
			
			
            HttpGet httpGet = new HttpGet(link);

            HttpResponse httpResponse = client.execute(httpGet);

        
            HttpEntity httpEntity = httpResponse.getEntity();
            String resp = EntityUtils.toString(httpEntity);
			
			
			Log.d(Container.DEBUG_TAG, resp);
			return new myJSONLatLongParser(resp).getLatLong();
		} catch (Exception e) {
			return null;
		}
	}
	
	
	@Override
	protected void onPostExecute(double[] result) {
		super.onPostExecute(result);
		
		String msg = "lat:" + result[0] + "lng:" + result[1];
		
		Toast.makeText(this.context, msg, Toast.LENGTH_SHORT).show();
		
		Log.d(Container.DEBUG_TAG, msg);
		
		
	}
	

}
