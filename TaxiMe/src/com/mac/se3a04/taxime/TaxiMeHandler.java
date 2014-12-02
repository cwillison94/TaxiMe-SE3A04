package com.mac.se3a04.taxime;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class TaxiMeHandler extends AsyncTask<String, Void, String> {

	private int flag;
	private Context context;
	private ProgressDialog dialog;

	/**
	 * Class Constructor.
	 * 
	 * @param Context
	 *            context application context
	 * @param UserAccessController
	 *            the controller that processes database results
	 * 
	 * */
	public TaxiMeHandler(Context context) {
		this.context = context;

	}

	@Override
	protected void onPreExecute() {
		showProgressDialog();
	}

	/**
	 * Creates and shows a progressDialog which shows the requests handled by
	 * excute are being processed
	 * 
	 * @param None
	 * @return void
	 * */
	private void showProgressDialog() {
		dialog = new ProgressDialog(this.context, AlertDialog.THEME_HOLO_DARK);
		dialog.setTitle("Processing request");
		dialog.setMessage("analyzing...");
		dialog.show();
	}

	@Override
	protected String doInBackground(String... arg0) {
		String link = null;
		/*
		 * if (flag == FLAG_LOGIN) { String username = (String) arg0[0]; String
		 * password = (String) arg0[1]; link = String.format(
		 * "http://taxime.comeze.com/TaxiMe/select.php?userEmail=%s&userPassword=%s"
		 * , username, password).toString();
		 */
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet();
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
			return sb.toString();
		} catch (Exception e) {
			return new String("Exception: " + e.getMessage());
		}
	}

	// handles the result from the server
	@Override
	protected void onPostExecute(String consoleResult) {
		Log.d("TAXI_ME", consoleResult);

		// stop the progress dialog, must to called to avoid memory leak
		dialog.dismiss();
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
