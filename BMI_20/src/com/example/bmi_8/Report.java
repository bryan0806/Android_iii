package com.example.bmi_8;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
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
				showNotification(BMI);
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
    
    protected void showNotification(double BMI){
    	// 首先要取得 NotificationManager
    	NotificationManager barManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    	
    	// 編輯 跳出的通知資訊
    	Notification barMsg = new Notification(
    			android.R.drawable.stat_sys_warning,
    			"歐，你過重囉!",
    			System.currentTimeMillis()
    			);
    	
    	//Pending Intent
    	/* public static PendingIntent getActivity (Context context, int requestCode, Intent intent, int flags)

		*Added in API level 1
		*Retrieve a PendingIntent that will start a new activity, like calling Context.startActivity(Intent). Note that the activity will be started outside of the context of an existing activity, so you must use the Intent.FLAG_ACTIVITY_NEW_TASK launch flag in the Intent.
		*For security reasons, the Intent you supply here should almost always be an explicit intent, that is specify an explicit component to be delivered to through Intent.setClass
		*Parameters
		*context	The Context in which this PendingIntent should start the activity.
		*requestCode	Private request code for the sender
		*intent	Intent of the activity to be launched.
		*flags	May be FLAG_ONE_SHOT, FLAG_NO_CREATE, FLAG_CANCEL_CURRENT, FLAG_UPDATE_CURRENT, or any of the flags as supported by Intent.fillIn() to control which unspecified parts of the intent that can be supplied when the actual send happens.
		*Returns
		*Returns an existing or new PendingIntent matching the given parameters. May return null only if FLAG_NO_CREATE has been supplied.
    	*/
    	PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);
    	
    	// 編輯放在通知中心的訊息
    	barMsg.setLatestEventInfo(this, "你的BMI值過高", "通知監督人", contentIntent);
    	barManager.notify(0, barMsg);
    	
    	
    }
    
}
