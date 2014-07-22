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

public class Report extends Activity { // �i�ǥѷƹ��k�� new -> other �ӷs�W android activity

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);
		findViews();
		showViews();
		setListeners();
		
	}
	
	// ���ŧi �e���W������
    Button button;
    TextView result;
    TextView fieldsuggest;
	
    private void findViews(){ // �@����������
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
    	try { // ��Q�ntry ...catch �����e ��_�� ���k�� ��� "surround with.."-> try..catch�N�i�H�]�w
			DecimalFormat nf = new DecimalFormat("0.00");
			// NEW Content here !!!!!!!!!!!
			Bundle bundle = this.getIntent().getExtras();  // ���X�W�@��activity���a��bundle
			double height = Double.parseDouble(bundle.getString("KEY_HEIGHT"))/100;
			double weight = Double.parseDouble(bundle.getString("KEY_WEIGHT"));
			
			//NEW Content finished !!!!!!!!!!!
			//double height = Double.parseDouble(fieldheight.getText().toString())/100;
			//double weight = Double.parseDouble(fieldweight.getText().toString());
			double BMI = weight / (height * height);
			
			//�C�L�Ĥ@����
			
			result.setText(R.string.bmi_result + "Your BMI is " + nf.format(BMI)); // �ثe�����D���� �e��������...
			
			// �C�L�ĤG����
			
			if(BMI>25){
				showNotification(BMI);
				fieldsuggest.setText(R.string.advice_heavy);
			}else if(BMI<20){
				fieldsuggest.setText(R.string.advice_light);
			}else{
				fieldsuggest.setText(R.string.advice_average);
			}
			
			//openOptionsDialog(); // �W�[��ܮػPToast�u�ȯd��
		} catch (NumberFormatException e) { // �{���۰ʷ|��A���諸���e ���w�@�ӱ������O �o��]�����B�zdouble�ҥH��NumberFormatException
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
    }
    
    protected void showNotification(double BMI){
    	// �����n���o NotificationManager
    	NotificationManager barManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    	
    	// �s�� ���X���q����T
    	Notification barMsg = new Notification(
    			android.R.drawable.stat_sys_warning,
    			"�ڡA�A�L���o!",
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
    	
    	// �s���b�q�����ߪ��T��
    	barMsg.setLatestEventInfo(this, "�A��BMI�ȹL��", "�q���ʷ��H", contentIntent);
    	barManager.notify(0, barMsg);
    	
    	
    }
    
}
