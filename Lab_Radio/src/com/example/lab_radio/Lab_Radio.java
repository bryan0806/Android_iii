package com.example.lab_radio;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class Lab_Radio extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab__radio);
    }
    
    public void onRadioButtonClicked(View view){
    	boolean checked = ((RadioButton)view).isChecked();
    	TextView result = (TextView)findViewById(R.id.result);
    			
    	switch(view.getId()){
    	case R.id.boy:
    		if(checked){
    			result.setText(R.string.boy);
    			break;
    		}
    	case R.id.girl:
    		if(checked){
    			result.setText(R.string.girl);
    			break;
    		}
    		
    	}
    }
    
}
