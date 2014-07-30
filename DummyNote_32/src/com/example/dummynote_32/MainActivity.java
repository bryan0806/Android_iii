package com.example.dummynote_32;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

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
    
    private void setAdapter(){
    	ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,note_array);
    	setListAdapter(adapter);
    }
    
}
