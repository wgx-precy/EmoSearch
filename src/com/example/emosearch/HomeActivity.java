package com.example.emosearch;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends Activity {
	String x_value = "0";
	String y_value = "0";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		final TextView textView = (TextView)findViewById(R.id.textView);
	    // this is the view on which you will listen for touch events
	    final View touchView = findViewById(R.id.touchView);
	    touchView.setOnTouchListener(new View.OnTouchListener() {
	        @Override
	        public boolean onTouch(View v, MotionEvent event) {
	        	x_value = String.valueOf(Math.round(event.getX()/30));
	        	y_value = String.valueOf(20-Math.round(event.getY()/30));
	        	if(event.getX() >= 300){
	        		if(event.getY() <= 300){
	        			textView.setText("Happy value is: " + x_value + "    Energetic value is:" + y_value);
	        		}
	        		if(event.getY() > 300){
	        			textView.setText("Happy value is: " + x_value + "    Tired value is:" + y_value);
	        		}
	        	}
	        	if(event.getX() < 300){
	        		if(event.getY() <= 300){
	        			textView.setText("Sad value is: " + x_value + "    Energetic value is:" + y_value);
	        		}
	        		if(event.getY() > 300){
	        			textView.setText("Sad value is: " + x_value + "    Tired value is:" + y_value);
	        		}
	        	}
	        	return true;
	        }
	    });
	}
	
    /** Called when the user clicks the Send button */
    public void searchfilter(View view) {
    	Intent intent = new Intent(this, FilterActivity.class);
    	intent.putExtra("x_value",x_value);
    	intent.putExtra("y_value",y_value);
    	startActivity(intent);
        // Do something in response to button
    }
    
    public void preferencesetting(View view) {
    	Intent intent = new Intent(this, PreferenceActivity.class);
    	startActivity(intent);
        // Do something in response to button
    }
}
