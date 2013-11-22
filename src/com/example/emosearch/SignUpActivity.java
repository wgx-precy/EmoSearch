package com.example.emosearch;

import com.example.emosearch.helper.EmoSearchHelper;
import com.example.emosearch.model.ProfileModel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends Activity {
	private EmoSearchHelper emo_db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_up, menu);
		emo_db = new EmoSearchHelper(this);
		return true;
	}
    /** Called when the user clicks the Send button */
    public void Home(View view) {
    	EditText username = (EditText) findViewById(R.id.username);
    	String user_name = username.getText().toString();
    	EditText password = (EditText) findViewById(R.id.password);
    	String pass_word = password.getText().toString();
    	EditText confirm_password = (EditText) findViewById(R.id.confirm_password);
    	String confirm_pass_word = confirm_password.getText().toString();
    	Log.d("user info",user_name+pass_word+confirm_pass_word);
    	ProfileModel profile = new ProfileModel();
    	profile.setPassword(pass_word);
    	profile.setUserId(user_name);
    	emo_db.createProfile(profile);
    	Intent intent = new Intent(this, HomeActivity.class);
    	startActivity(intent);
        // Do something in response to button
    }

}