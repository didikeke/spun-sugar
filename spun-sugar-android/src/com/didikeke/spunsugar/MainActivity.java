package com.didikeke.spunsugar;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TabHost;

import com.didikeke.spunsugar.client.domain.Item;

public class MainActivity extends ListActivity {

	private ApplicationEx mApp;

	private TabHost tabHost;  
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mApp = (ApplicationEx)getApplication();
        
        
        String[] array = toArray(mApp.getItems());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, array);
        MainActivity.this.setListAdapter(adapter);
        
    }
    
	 private String[] toArray(List<Item> items){
     	String[] result = new String[items.size()];
     	for(int i=0;i<items.size();i++){
     		result[i]= items.get(i).getTitle().replaceAll(" .*", "");
     	}
     	return result;
     }
}