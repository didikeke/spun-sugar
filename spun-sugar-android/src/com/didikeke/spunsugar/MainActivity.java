package com.didikeke.spunsugar;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

    protected ApplicationEx mApp;
	private TabHost mTabHost;
	private TabWidget mTabWidget;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mApp = (ApplicationEx) getApplication();
		mTabHost = (TabHost)findViewById(android.R.id.tabhost);
		mTabWidget = (TabWidget)findViewById(android.R.id.tabs);
		
		createTabs();
        
    }

	private void createTabs() {
		mTabHost.setup();
		mTabHost.bringToFront();

		TabSpec tab_1 = mTabHost.newTabSpec("tab1");
		TabSpec tab_2 = mTabHost.newTabSpec("tab2");
		TabSpec tab_3 = mTabHost.newTabSpec("tab3");
		TabSpec tab_4 = mTabHost.newTabSpec("tab4");

		tab_1.setIndicator(getString(R.string.items),getResources().getDrawable(R.drawable.tab_items_icon))
				.setContent(new Intent(this,ItemsActivity.class));
		tab_2.setIndicator(getString(R.string.holds),getResources().getDrawable(R.drawable.tab_holds_icon))
				.setContent(new Intent(this,HoldsActivity.class));
		tab_3.setIndicator(getString(R.string.history),getResources().getDrawable(R.drawable.tab_history_icon))
				.setContent(new Intent(this,ReadingHistoryActivity.class));
		tab_4.setIndicator(getString(R.string.search),getResources().getDrawable(R.drawable.tab_search_icon))
				.setContent(new Intent(this,SearchActivity.class));

		mTabHost.addTab(tab_1);
		mTabHost.addTab(tab_2);
		mTabHost.addTab(tab_3);
		mTabHost.addTab(tab_4);
        
		changeTabsBackground();
		
		mTabHost.setOnTabChangedListener(new OnTabChangeListener(){

			@Override
			public void onTabChanged(String tabId) {
				changeTabsBackground();
			}
			
		});
	}
    
	private void changeTabsBackground() {
		for (int i =0; i < mTabWidget.getChildCount(); i++) {
			View vvv = mTabWidget.getChildAt(i);
			if(mTabHost.getCurrentTab()==i){
				vvv.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_bg_selected));
			}
			else {
				vvv.setBackgroundDrawable(getResources().getDrawable(R.drawable.tab_bg));
			}
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	 MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.layout.main_menus, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.about:
	        //newGame();
	        return true;
	    case R.id.logout:
	        mApp.setClient(null);
	        Intent intent = new Intent(this, LoginActivity.class);
	        startActivity(intent);
	        finish();	        
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	    
}