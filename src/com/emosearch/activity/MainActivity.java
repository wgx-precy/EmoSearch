package com.emosearch.activity;

import com.emosearch.helper.EmoSearchHelper;
import com.example.emosearch.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		EditText userNameText = (EditText)findViewById(R.id.username);
		EditText passwordText = (EditText)findViewById(R.id.password);
		
		String userName = userNameText.toString();
		String password = passwordText.toString();
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
