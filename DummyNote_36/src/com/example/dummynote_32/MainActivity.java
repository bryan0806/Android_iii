package com.example.dummynote_32;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //�i�DListView ���@��view ����ܷ�list �O�Ū��ɭ�
        getListView().setEmptyView(findViewById(R.id.empty));
        
        //new !! ���U ContextMenu
        registerForContextMenu(getListView());
        
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
    
    // �U���}�l�O�P�s�W�R�����s����
    private int mNoteNumber = 1;
    protected static final int MENU_INSERT = Menu.FIRST;
    protected static final int MENU_DELETE = Menu.FIRST+1;
    
    public boolean onCreateOptionsMenu(Menu menu){
    	menu.add(0, MENU_INSERT, 0, "�s�W�O��");
    	menu.add(0, MENU_DELETE, 0, "�R���O��");
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
    
    private static final int ACTIVITY_EDIT=0x1001;
    
    protected void onListItemClick(ListView l,View v,int position,long id){
    	super.onListItemClick(l, v, position, id);
    	Intent intent = new Intent(this,NoteEidtActivity.class);
    	intent.putExtra(DB.KEY_ROWID, id);
    	startActivityForResult(intent,ACTIVITY_EDIT);
    }
    
    protected void onActivityResult(int requestCode,int resultCode,Intent intent){
    	super.onActivityResult(requestCode, resultCode, intent);
    	
    	if(resultCode==RESULT_OK){//�T�w���e���s�觹���e ���Uok �|���
    		if(requestCode==ACTIVITY_EDIT){ //�T�w�O���e ���U�e����note���خɪ�startActivityForResult���W���P�@�Ӹ��X
    			fillData(); //��s�e��
    		}
    	}	
    }
    
    public void onCreateContextMenu(ContextMenu menu,View v,ContextMenuInfo menuInfo){ // �إ�ContextMenu
    	menu.add(0, MENU_DELETE, 0, "�R���O��");
    	menu.setHeaderTitle("Detail view");
    	super.onCreateContextMenu(menu, v, menuInfo);
    }
    
    public boolean onContextItemSelected(MenuItem item){
    	AdapterView.AdapterContextMenuInfo info;
    	info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
    	switch (item.getItemId()){
    	case MENU_DELETE:
    		Log.d("MENU", "item"+info.id);
    		mDbHelper.delete(info.id);
    		fillData();
    		break;
    	}
    	return super.onContextItemSelected(item);
    }
    
}
