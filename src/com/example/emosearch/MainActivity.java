package com.example.emosearch;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
    /** Called when the user clicks the Send button */
    public void Home(View view) {
    	Intent intent = new Intent(this, HomeActivity.class);
    	startActivity(intent);
        // Do something in response to button
    }
    public void SignUp(View view) {
    	Intent intent = new Intent(this, SignUpActivity.class);
    	startActivity(intent);
        // Do something in response to button
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
