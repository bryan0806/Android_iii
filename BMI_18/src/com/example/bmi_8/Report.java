package com.example.bmi_8;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Report extends Activity { // 可藉由滑鼠右鍵 new -> other 來新增 android activity

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);
		findViews();
		showViews();
		setListeners();
		
	}
	
	// 先宣告 畫面上的元件
    Button button;
    TextView result;
    TextView fieldsuggest;
	
    private void findViews(){ // 一次全部完成
    	button = (Button) findViewById(R.id.return_button);
    	result = (TextView)findViewById(R.id.view_result);
    	fieldsuggest = (TextView)findViewById(R.id.view_suggest);
    }
    
    private void setListeners(){
    	button.setOnClickListener(backMain);
    }
    
    private Button.OnClickListener backMain = new Button.OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			finish();
		}
    };
    
    private void showViews(){
    	try { // 把想要try ...catch 的內容 選起來 按右鍵 選擇 "surround with.."-> try..catch就可以設定
			DecimalFormat nf = new DecimalFormat("0.00");
			// NEW Content here !!!!!!!!!!!
			Bundle bundle = this.getIntent().getExtras();  // 取出上一個activity夾帶的bundle
			double height = Double.parseDouble(bundle.getString("KEY_HEIGHT"))/100;
			double weight = Double.parseDouble(bundle.getString("KEY_WEIGHT"));
			
			//NEW Content finished !!!!!!!!!!!
			//double height = Double.parseDouble(fieldheight.getText().toString())/100;
			//double weight = Double.parseDouble(fieldweight.getText().toString());
			double BMI = weight / (height * height);
			
			//列印第一行資料
			
			result.setText(R.string.bmi_result + "Your BMI is " + nf.format(BMI)); // 目前不知道為何 前面的不行...
			
			// 列印第二行資料
			
			if(BMI>25){
				fieldsuggest.setText(R.string.advice_heavy);
			}else if(BMI<20){
				fieldsuggest.setText(R.string.advice_light);
			}else{
				fieldsuggest.setText(R.string.advice_average);
			}
			
			//openOptionsDialog(); // 增加對話框與Toast短暫留言
		} catch (NumberFormatException e) { // 程式自動會找你剛剛選的內容 給定一個接收類別 這邊因為式處理double所以給NumberFormatException
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
    }
    
}
