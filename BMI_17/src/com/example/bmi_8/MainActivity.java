package com.example.bmi_8;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View.OnClickListener; // 一開始自動import 錯誤 import 到另外一個onclicklistener!! 要注意是哪一個
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //一次設定全部的findViewById 以及 Set 全部的listeners
        findViews();
        setListeners();     
    }
    
    // 先宣告 畫面上的元件
    Button button;
    EditText fieldheight;
    EditText fieldweight;
    TextView result;
    TextView fieldsuggest;
    
    private void findViews(){ // 一次全部完成
    	button = (Button) findViewById(R.id.submit);
    	fieldheight = (EditText)findViewById(R.id.bmi_height); //這邊原本就是EditText所以用一個EditText 去收
    	fieldweight = (EditText)findViewById(R.id.bmi_weight);
    	result = (TextView)findViewById(R.id.result);
    	fieldsuggest = (TextView)findViewById(R.id.suggest);
    }
    
    private void setListeners(){
    	button.setOnClickListener(calBMI);
    }
    
    
    private OnClickListener calBMI = new OnClickListener(){

		public void onClick(View v) {
			/*
			// 將view 轉成 double 的過程
			try { // 把想要try ...catch 的內容 選起來 按右鍵 選擇 "surround with.."-> try..catch就可以設定
				DecimalFormat nf = new DecimalFormat("0.00");
				
				
				double height = Double.parseDouble(fieldheight.getText().toString())/100;
				double weight = Double.parseDouble(fieldweight.getText().toString());
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
				
				openOptionsDialog(); // 增加對話框與Toast短暫留言
			} catch (NumberFormatException e) { // 程式自動會找你剛剛選的內容 給定一個接收類別 這邊因為式處理double所以給NumberFormatException
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e){
				e.printStackTrace();
			}*/
			
			Intent intent = new Intent(); //新增Intent物件
			intent.setClass(MainActivity.this, Report.class); // 設定下一個要執行的activity
			startActivity(intent); //啟動下一個activity
			
		}

    	
    };
    
    private void openOptionsDialog(){
    	Toast.makeText(MainActivity.this, "(跳出)計算器", Toast.LENGTH_LONG).show();
    	
    	new AlertDialog.Builder(MainActivity.this) //接下來的每一行回傳值都是Builder物件 所以可以一條龍的接續設定下去完成這個對話框
    	.setTitle(R.string.about_title)
    	.setMessage(R.string.about_msg)
    	.setPositiveButton("確認", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// 函式裡面寫空值讓她不動作 只有跳出此對話框
				
			}
		})
		.setNegativeButton(R.string.homepage_label, new DialogInterface.OnClickListener() { //增加另外一顆按鈕
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("http://tw.yahoo.com/"); //網址
				Intent intent = new Intent(Intent.ACTION_VIEW,uri); // 把網址包入intent
				startActivity(intent); // 執行intent
			}
		})
		.show();
    }

    protected static final int MENU_ABOUT = Menu.FIRST;
    protected static final int MENU_QUIT = Menu.FIRST+1;
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) { //這一行可用滑鼠右鍵 source->override/implement method 來增加
		// TODO Auto-generated method stub
		menu.add(0, MENU_ABOUT, 0, "關於").setIcon(android.R.drawable.ic_menu_help);
		menu.add(0, MENU_QUIT, 0, "結束");// 如此增加兩顆menu
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) { //menu功能定義
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case MENU_ABOUT:
			openOptionsDialog();
			break;
		case MENU_QUIT:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
    
    
    
}
