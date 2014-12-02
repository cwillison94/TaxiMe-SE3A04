package com.mac.se3a04.taxime;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class pollingService extends Service {
	private TaxiMeHandler tmHandler;
	
	public pollingService(TaxiMeHandler tmHandler) {
		this.tmHandler = tmHandler;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		

		return super.onStartCommand(intent, flags, startId);
	}

}
