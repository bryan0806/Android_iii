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
        
        // �]�w���s�ʧ@ ��k�@
        Button button = (Button) findViewById(R.id.submit);
        button.setOnClickListener(calBMI);
    }
    
    private OnClickListener calBMI = new OnClickListener(){

		public void onClick(View v) {
			// �Nview �ন double ���L�{
			DecimalFormat nf = new DecimalFormat("0.00");
			EditText fieldheight = (EditText)findViewById(R.id.bmi_height); //�o��쥻�N�OEditText�ҥH�Τ@��EditText �h��
			EditText fieldweight = (EditText)findViewById(R.id.bmi_weight);
			double height = Double.parseDouble(fieldheight.getText().toString())/100;
			double weight = Double.parseDouble(fieldweight.getText().toString());
			double BMI = weight / (height * height);
			
			//�C�L�Ĥ@����
			TextView result = (TextView)findViewById(R.id.result);
			result.setText(R.string.bmi_result + "Your BMI is " + nf.format(BMI)); // �ثe�����D���� �e��������...
			
			// �C�L�ĤG����
			TextView fieldsuggest = (TextView)findViewById(R.id.suggest);
			if(BMI>25){
				fieldsuggest.setText(R.string.advice_heavy);
			}
		}

    	
    };
}
