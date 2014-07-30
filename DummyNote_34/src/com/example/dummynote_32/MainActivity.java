package com.example.dummynote_32;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //告訴ListView 哪一個view 來顯示當list 是空的時候
        getListView().setEmptyView(findViewById(R.id.empty));
        setAdapter();
    }
    
    
    private String[] note_array = {
    	"gosolin",
    	"crota",
    	"louk",
    	"magicion"
    };
    
    private DB mDbHelper;
    private Cursor mNotesCursor;
    
    
    private void setAdapter(){
    	mDbHelper = new DB(this);
    	mDbHelper.open();
    	fillData();
    }
    
    private void fillData(){
    	mNotesCursor = mDbHelper.getAll();
    	startManagingCursor(mNotesCursor);
    	
    	String[] from = new String[]{DB.KEY_NOTE};
    	int[] to = new int[]{android.R.id.text1};
    	
    	// create a simple cursor adapter
    	SimpleCursorAdapter adapter =
    			new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,mNotesCursor,from,to);
    	setListAdapter(adapter);
    }
    
    // 下面開始是與新增刪除按鈕有關
    private int mNoteNumber = 1;
    protected static final int MENU_INSERT = Menu.FIRST;
    protected static final int MENU_DELETE = Menu.FIRST+1;
    
    public boolean onCreateOptionsMenu(Menu menu){
    	menu.add(0, MENU_INSERT, 0, "新增記事");
    	menu.add(0, MENU_DELETE, 0, "刪除記事");
    	return super.onCreateOptionsMenu(menu);
    }
    
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	case MENU_INSERT:
    		String noteName = "Note " + mNoteNumber++;
    		mDbHelper.create(noteName);
    		fillData();
    		break;
    	case MENU_DELETE:
    		mDbHelper.delete(getListView().getSelectedItemId());
    		fillData();
    		break;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
}
