package com.example.bmi_8;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Report extends Activity { // 可藉由滑鼠右鍵 new -> other 來新增 android activity

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);
	}
}
