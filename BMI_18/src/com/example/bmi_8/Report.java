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
    
}
