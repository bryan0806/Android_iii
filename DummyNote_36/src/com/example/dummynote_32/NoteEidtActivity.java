package com.example.dummynote_32;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteEidtActivity extends Activity {

	private DB mDbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_eidt);
		
		mDbHelper = new DB(this);
		mDbHelper.open();
		findViews();
		showViews(savedInstanceState);
		
	}
	
	private EditText field_note;
	private Button button_confirm;
	
	private void findViews(){
		field_note = (EditText)findViewById(R.id.editText1);
		button_confirm = (Button)findViewById(R.id.button1);
	}
	
	private Long mRowId;
	private void showViews(Bundle savedInstanceState){
		if(mRowId==null){
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras.getLong(DB.KEY_ROWID):null;
		}
		
		populateFields();
		
		//³]©w OK «ö¶slistener
		button_confirm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mDbHelper.update(mRowId, field_note.getText().toString());
				setResult(RESULT_OK);
				finish();
			}
		});
	}
	
	private void populateFields(){
		if(mRowId != null){
			Cursor note = mDbHelper.get(mRowId);
			startManagingCursor(note);
			
			field_note.setText(note.getString(note.getColumnIndexOrThrow(DB.KEY_NOTE)));
		}
	}
	
	
}
