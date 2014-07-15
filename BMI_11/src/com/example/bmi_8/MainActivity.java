package com.example.bmi_8;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View.OnClickListener; // 一開始自動import 錯誤 import 到另外一個onclicklistener!! 要注意是哪一個
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 設定按鈕動作 方法一
        Button button = (Button) findViewById(R.id.submit);
        button.setOnClickListener(calBMI);
    }
    
    private OnClickListener calBMI = new OnClickListener(){

		public void onClick(View v) {
			// 將view 轉成 double 的過程
			DecimalFormat nf = new DecimalFormat("0.00");
			EditText fieldheight = (EditText)findViewById(R.id.bmi_height); //這邊原本就是EditText所以用一個EditText 去收
			EditText fieldweight = (EditText)findViewById(R.id.bmi_weight);
			double height = Double.parseDouble(fieldheight.getText().toString())/100;
			double weight = Double.parseDouble(fieldweight.getText().toString());
			double BMI = weight / (height * height);
			
			//列印第一行資料
			TextView result = (TextView)findViewById(R.id.result);
			result.setText(R.string.bmi_result + "Your BMI is " + nf.format(BMI)); // 目前不知道為何 前面的不行...
			
			// 列印第二行資料
			TextView fieldsuggest = (TextView)findViewById(R.id.suggest);
			if(BMI>25){
				fieldsuggest.setText(R.string.advice_heavy);
			}
		}

    	
    };
}
