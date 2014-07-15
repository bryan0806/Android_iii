package com.example.lab_calculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calculator extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        
        addViews();
        setListeners();
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.calculator, menu);
        return true;
    }
    
    Button add;
    Button minus;
    Button times;
    Button divide;
    EditText firstnum;
    EditText secondnum;
    TextView symbol;
    TextView answer;
    
    private void addViews(){
    		add = (Button)findViewById(R.id.add);
    		minus = (Button)findViewById(R.id.minus);
    		times = (Button)findViewById(R.id.times);
    		divide = (Button)findViewById(R.id.divide);
    		firstnum = (EditText)findViewById(R.id.firstNum);
    		secondnum = (EditText)findViewById(R.id.secondNum);
    		symbol = (TextView)findViewById(R.id.symbol);
    		answer = (TextView)findViewById(R.id.answer);
    }
    
    private void setListeners(){
    		
    		
    	
    		add.setOnClickListener(new View.OnClickListener() {
    			    
				@Override
				public void onClick(View v) {
					Integer fnum = Integer.parseInt(firstnum.getText().toString());
        		        Integer snum = Integer.parseInt(secondnum.getText().toString());
					Integer result;
					result = fnum+snum;
					symbol.setText("+");
					answer.setText(result.toString());
				}
			});
    		
    		minus.setOnClickListener(new View.OnClickListener() {
			    
				@Override
				public void onClick(View v) {
					Integer fnum = Integer.parseInt(firstnum.getText().toString());
        		        Integer snum = Integer.parseInt(secondnum.getText().toString());
					Integer result;
					result = fnum-snum;
					symbol.setText("-");
					answer.setText(result.toString());
				}
			});
    		
    		times.setOnClickListener(new View.OnClickListener() {
			    
				@Override
				public void onClick(View v) {
					Integer fnum = Integer.parseInt(firstnum.getText().toString());
        		        Integer snum = Integer.parseInt(secondnum.getText().toString());
					Integer result;
					result = fnum*snum;
					symbol.setText("X");
					answer.setText(result.toString());
				}
			});
    		
    		divide.setOnClickListener(new View.OnClickListener() {
			    
				@Override
				public void onClick(View v) {
					Integer fnum = Integer.parseInt(firstnum.getText().toString());
        		        Integer snum = Integer.parseInt(secondnum.getText().toString());
					Integer result;
					result = fnum/snum;
					symbol.setText("/");
					answer.setText(result.toString());
				}
			});
    }
    
    
}
