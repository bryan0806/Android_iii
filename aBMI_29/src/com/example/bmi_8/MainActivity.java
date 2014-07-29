package com.example.bmi_8;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View.OnClickListener; // �@�}�l�۰�import ���~ import ��t�~�@��onclicklistener!! �n�`�N�O���@��
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static final String PREF = "BMI_PREF";
	public static final String PREF_HEIGHT = "BMI_HEIGHT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //�@���]�w������findViewById �H�� Set ������listeners
        findViews();
        setListeners();     
    }
    
    
    Button button;
    Spinner fieldfeet;
    Spinner fieldinch;
    EditText fieldweight;
    TextView result;
    TextView fieldsuggest;
    
    private void findViews(){ // �@����������
    	button = (Button) findViewById(R.id.submit);
    	fieldfeet = (Spinner)findViewById(R.id.feet); //new !! �s������
    	fieldinch = (Spinner)findViewById(R.id.inch); // new !!!!!!
    	fieldweight = (EditText)findViewById(R.id.bmi_weight);
    	result = (TextView)findViewById(R.id.result);
    	fieldsuggest = (TextView)findViewById(R.id.suggest);
    	
    	ArrayAdapter<CharSequence> adapter_feet = ArrayAdapter.createFromResource(this, R.array.feets, android.R.layout.simple_spinner_dropdown_item);
    	adapter_feet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	fieldfeet.setAdapter(adapter_feet);
    	
    	ArrayAdapter<CharSequence> adapter_inch = ArrayAdapter.createFromResource(this, R.array.inches, android.R.layout.simple_spinner_dropdown_item);
    	adapter_inch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	fieldinch.setAdapter(adapter_inch);
    	
    }
    
    private int feet;
	private int inch;
	
	private Spinner.OnItemSelectedListener getFeet = new OnItemSelectedListener(){
		
		@Override
		public void onItemSelected(AdapterView parent, View v, int position,
				long id) {
			// TODO Auto-generated method stub
			feet = parent.getSelectedItemPosition()+2;
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private Spinner.OnItemSelectedListener getInch = new OnItemSelectedListener(){

		@Override
		public void onItemSelected(AdapterView parent, View v, int position,
				long id) {
			// TODO Auto-generated method stub
			inch = parent.getSelectedItemPosition();
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};
    
    private void setListeners(){
    	button.setOnClickListener(calBMI);
    	fieldfeet.setOnItemSelectedListener(getFeet);
    	fieldinch.setOnItemSelectedListener(getInch);
    }
    
    
    
    private OnClickListener calBMI = new OnClickListener(){

		public void onClick(View v) {
			/*
			// �Nview �ন double ���L�{
			try { // ��Q�ntry ...catch �����e ��_�� ���k�� ��� "surround with.."-> try..catch�N�i�H�]�w
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
				
				openOptionsDialog(); // �W�[��ܮػPToast�u�ȯd��
			} catch (NumberFormatException e) { // �{���۰ʷ|��A���諸���e ���w�@�ӱ������O �o��]�����B�zdouble�ҥH��NumberFormatException
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e){
				e.printStackTrace();
			}*/
			
			//Intent intent = new Intent(); //�s�WIntent����
			//intent.setClass(MainActivity.this, Report.class); // �]�w�U�@�ӭn���檺activity
			
			//Bundle bundle = new Bundle();  //�إ�Bundle(�@��)�x�s�n���U�@��activity �����
			//bundle.putString("KEY_HEIGHT", fieldheight.getText().toString());
			result.setText("your feet: "+feet +"  your inch: " + inch );
			//bundle.putString("KEY_WEIGHT", fieldweight.getText().toString());
			//intent.putExtras(bundle);
			
			//startActivity(intent); //�ҰʤU�@��activity
			
		}

    	
    };
    
    private void openOptionsDialog(){
    	Toast.makeText(MainActivity.this, "(���X)�p�⾹", Toast.LENGTH_LONG).show();
    	
    	new AlertDialog.Builder(MainActivity.this) //���U�Ӫ��C�@��^�ǭȳ��OBuilder���� �ҥH�i�H�@���s������]�w�U�h�����o�ӹ�ܮ�
    	.setTitle(R.string.about_title)
    	.setMessage(R.string.about_msg)
    	.setPositiveButton("�T�{", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// �禡�̭��g�ŭ����o���ʧ@ �u�����X����ܮ�
				
			}
		})
		.setNegativeButton(R.string.homepage_label, new DialogInterface.OnClickListener() { //�W�[�t�~�@�����s
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Uri uri = Uri.parse("http://tw.yahoo.com/"); //���}
				Intent intent = new Intent(Intent.ACTION_VIEW,uri); // ����}�]�Jintent
				startActivity(intent); // ����intent
			}
		})
		.show();
    }

    protected static final int MENU_ABOUT = Menu.FIRST;
    protected static final int MENU_QUIT = Menu.FIRST+1;
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) { //�o�@��i�ηƹ��k�� source->override/implement method �ӼW�[
		// TODO Auto-generated method stub
		menu.add(0, MENU_ABOUT, 0, "����").setIcon(android.R.drawable.ic_menu_help);
		menu.add(0, MENU_QUIT, 0, "����");// �p���W�[����menu
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) { //menu�\��w�q
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
