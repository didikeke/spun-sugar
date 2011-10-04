package com.didikeke.spunsugar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.didikeke.spunsugar.client.SpunSugarClient;
import com.didikeke.spunsugar.client.domain.User;

public class LoginActivity extends Activity {

    
    private EditText mTextUsername;
    private EditText mTextPassword;    
    private Button mButtonSignIn;
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        mTextUsername = (EditText)findViewById(R.id.editUsername);
        mTextPassword = (EditText)findViewById(R.id.editPassword);
        
        mButtonSignIn = (Button)findViewById(R.id.button_sign_in);
        mButtonSignIn.setOnClickListener(mSignInOnClickListener);
        
    }
    
    
    private View.OnClickListener mSignInOnClickListener = new View.OnClickListener() {
        
        public void onClick(View v) {
             
            String username = mTextUsername.getText().toString();
            String password = mTextPassword.getText().toString(); 
            if("".equals(username)){
                Toast.makeText(LoginActivity.this, "No empty Username" ,Toast.LENGTH_LONG).show();
                return;
            }
            if("".equals(password)){
                Toast.makeText(LoginActivity.this, "No empty Password" ,Toast.LENGTH_LONG).show();
                return;
            }
            
            SignInTask task = new SignInTask();
            task.execute(username,password);            
        }    
        
    };
    
    private class SignInTask extends AsyncTask<String,Void,User>{

        Exception exception = null;
        private ProgressDialog signInDialog;
        
        @Override 
        protected void onPreExecute(){
            signInDialog = new ProgressDialog(LoginActivity.this);   
            signInDialog.setTitle(null);
            signInDialog.setMessage("Sign In...");
            signInDialog.setButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int i) {
                    SignInTask.this.cancel(true);
                    signInDialog.dismiss();
                }
            });
            signInDialog.show();
        }
        
        @Override
        protected User doInBackground(String... args) {
            
            String username = args[0];
            String password = args[1];
            
            try{
            	SpunSugarClient client = new SpunSugarClient(username,password);
            	User user = client.getUser();
            	ApplicationEx app = (ApplicationEx)LoginActivity.this.getApplication();
            	app.setClient(client);
            	return user;
            }catch(Exception e){
                exception = e;                
            }
            return null;
        }
        
        protected void onPostExecute(User user) {  
            
            if(null == exception){
                //SUCCESS
                signInDialog.dismiss();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }else{
                signInDialog.dismiss();
                Toast.makeText(LoginActivity.this, exception.getMessage() ,Toast.LENGTH_LONG).show();
            }
        }
        
    }
    
}