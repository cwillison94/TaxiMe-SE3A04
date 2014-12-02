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

/**
 * This is a wrapper class for the user records database. This is a polymorphic
 * class which is capable of handling login/logout requests, registration
 * requests, and profile information requests.
 * 
 * 
 * Note: YOU MUST set the static type FLAG in order to properly configure this
 * class. The default flag is FLAG_LOGIN.
 * 
 * @author Cole Willison
 * @version 0.5
 * @since 2014-11-05
 * 
 *        TODO: -Implement Logout -Implement Profile request
 * */
public class UserRecordsDBhandler extends AsyncTask<String, Void, String> {

	// Flags used to specify which code branch is executed
	public static final int FLAG_LOGIN = -1;
	public static final int FLAG_REGISTRATION = -2;
	public static final int FLAG_LOGOUT = -3;
	public static final int FLAG_LOGIN_STATUS = -4;
	public static final int FLAG_GET_PROFILE = -5;
	public static final int FLAG_SET_PROFILE = -6;

	private int flag = FLAG_LOGIN;
	private Context context;
	private ProgressDialog dialog;
	private UserAccessController uaController;

	/**
	 * Class Constructor.
	 * 
	 * @param Context
	 *            context application context
	 * @param UserAccessController
	 *            the controller that processes database results
	 * 
	 * */
	public UserRecordsDBhandler(Context context, UserAccessController uaController) {
		this.context = context;
		this.uaController = uaController;

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
		String link;
		if (flag == FLAG_LOGIN) {
			String username = (String) arg0[0];
			String password = (String) arg0[1];
			link = String.format(
					"http://taxime.comeze.com/TaxiMe/select.php?userEmail=%s&userPassword=%s",
					username, password).toString();
		} else if (flag == FLAG_REGISTRATION) {

			String useremail = (String) arg0[0];
			String password = (String) arg0[1];
			String fname = (String) arg0[2];
			String lname = (String) arg0[3];
			String sex = (String) arg0[4];
			String proffession = (String) arg0[5];
			String age = (String) arg0[6];

			link = "http://taxime.comeze.com/TaxiMe/registration.php?LoginStatus=" + 1
					+ "&userEmail=" + useremail + "&userPassword=" + password + "&fname=" + fname
					+ "&lname=" + lname + "&sex=" + sex + "&proffession=" + proffession + "&age="
					+ age;

		} else if (flag == FLAG_LOGIN_STATUS) {
			String useremail = (String) arg0[0];
			link = "http://taxime.comeze.com/TaxiMe/getLoginStatus.php?userEmail=" + useremail;
		} else if (flag == FLAG_GET_PROFILE) {
			String useremail = (String) arg0[0];
			link = "http://taxime.comeze.com/TaxiMe/profile.php?command=get&userEmail=" + useremail;
		} else if (flag == FLAG_SET_PROFILE) {
			String useremail = (String) arg0[0];
			String fname = (String) arg0[1];
			String lname = (String) arg0[2];
			String sex = (String) arg0[3];
			String proffession = (String) arg0[4];
			String age = (String) arg0[5];
			link = "http://taxime.comeze.com/TaxiMe/profile.php?command=set&userEmail=" + useremail + "&fname=" + fname
					+ "&lname=" + lname + "&sex=" + sex + "&proffession=" + proffession + "&age="
					+ age;
		} else {
			//flag == FLAG_LOGOUT
			String useremail = (String) arg0[0];
			link = "http://taxime.comeze.com/TaxiMe/logout.php?userEmail=" + useremail;
		}
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
		if (flag == FLAG_GET_PROFILE || flag == FLAG_SET_PROFILE) {
			Toast.makeText(context, consoleResult, Toast.LENGTH_SHORT).show();
		} else {
			uaController.processServerResults(consoleResult);
		}
		// stop the progress dialog, must to called to avoid memory leak
		dialog.dismiss();
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
}
