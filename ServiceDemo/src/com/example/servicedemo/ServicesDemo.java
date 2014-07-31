package com.example.servicedemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class ServicesDemo extends Activity implements OnClickListener {

	Button buttonStart,buttonStop;
	private static final String TAG = "ServicesDemo";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_demo);
        
        buttonStart = (Button)findViewById(R.id.start);
        buttonStop = (Button)findViewById(R.id.stop);
        
        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.start:
			Log.d(TAG, "onClick:starting service");
			startService(new Intent(this,MyService.class));
			break;
		case R.id.stop:
			Log.d(TAG, "onClick:stopping service");
			stopService(new Intent(this,MyService.class));
			break;
		}
		
	}
}
