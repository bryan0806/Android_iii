package com.example.bmi_8;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.View.OnClickListener; // �@�}�l�۰�import ���~ import ��t�~�@��onclicklistener!! �n�`�N�O���@��
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
        
        //�@���]�w������findViewById �H�� Set ������listeners
        findViews();
        setListeners();     
    }
    
    // ���ŧi �e���W������
    Button button;
    EditText fieldheight;
    EditText fieldweight;
    TextView result;
    TextView fieldsuggest;
    
    private void findViews(){ // �@����������
    	button = (Button) findViewById(R.id.submit);
    	fieldheight = (EditText)findViewById(R.id.bmi_height); //�o��쥻�N�OEditText�ҥH�Τ@��EditText �h��
    	fieldweight = (EditText)findViewById(R.id.bmi_weight);
    	result = (TextView)findViewById(R.id.result);
    	fieldsuggest = (TextView)findViewById(R.id.suggest);
    }
    
    private void setListeners(){
    	button.setOnClickListener(calBMI);
    }
    
    
    private OnClickListener calBMI = new OnClickListener(){

		public void onClick(View v) {
			// �Nview �ন double ���L�{
			DecimalFormat nf = new DecimalFormat("0.00");
			
			
			double height = Double.parseDouble(fieldheight.getText().toString())/100;
			double weight = Double.parseDouble(fieldweight.getText().toString());
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
		}

    	
    };
}
