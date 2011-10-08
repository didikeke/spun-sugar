package com.didikeke.spunsugar;

import static com.didikeke.spunsugar.util.ItemUtils.toArray;

import java.io.IOException;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.didikeke.spunsugar.client.SpunSugarClient;
import com.didikeke.spunsugar.client.domain.Item;

public abstract class AbstractLoadActivity extends ListActivity {

    protected ApplicationEx mApp;
    protected SpunSugarClient mClient;
    
    protected Button mRefreshButton;
    
    private List<Item> mCachedItems;
    
    private View.OnClickListener mRefreshButtonListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            refreshItems();
        }
        
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items);

        mApp = (ApplicationEx) getApplication();
        mClient = mApp.getClient();
        mRefreshButton = (Button)findViewById(R.id.refresh_button);
        mRefreshButton.setOnClickListener(mRefreshButtonListener);
        
        if(null != mCachedItems){
            displayItems(mCachedItems);
        }else{
            refreshItems();
        }
    }

    private void refreshItems() {
        LoadTask holdsTask = new LoadTask();
        holdsTask.execute();
    }
    
    public abstract List<Item> getRemoteData() throws IOException;
    
    
    protected void displayItems(List<Item> items){
        String[] array = toArray(items);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, array);
        setListAdapter(adapter);
    }
    
    private class LoadTask extends AsyncTask<String,Void,List<Item>>{

        Exception exception = null;
        private ProgressDialog signInDialog;
        
        @Override 
        protected void onPreExecute(){
            signInDialog = new ProgressDialog(AbstractLoadActivity.this);   
            signInDialog.setTitle(null);
            signInDialog.setMessage("Loading...");
            signInDialog.setButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int i) {
                    LoadTask.this.cancel(true);
                    signInDialog.dismiss();
                }
            });
            signInDialog.show();
        }
        
        @Override
        protected List<Item> doInBackground(String... args) {
            
            try{
                List<Item> items = getRemoteData();
                mCachedItems = items;
                return items;
                
                
            }catch(Exception e){
                exception = e;                
            }
            return null;
        }
        
        protected void onPostExecute(List<Item> holds) {  
            
            if(null == exception){
                //SUCCESS
                signInDialog.dismiss();
                displayItems(holds);
                
            }else{
                signInDialog.dismiss();
                Toast.makeText(AbstractLoadActivity.this, exception.getMessage() ,Toast.LENGTH_LONG).show();
            }
        }
        
    }

 
}