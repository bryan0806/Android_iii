package com.example.servicedemo;


import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

	private static final String TAG = "MyService";
	MediaPlayer player;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	// 後面的implement Methods 可由source -> override/implement Methods
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "My Service Created", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onCreate");
		player = MediaPlayer.create(this, R.raw.sa);
		player.setLooping(false);
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onStart");
		player.start();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
		Log.d(TAG, "onDestroy");
		player.stop();
		super.onDestroy();
	}
	
	
	
}
