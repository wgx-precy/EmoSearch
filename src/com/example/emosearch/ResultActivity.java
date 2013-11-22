package com.example.emosearch;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class ResultActivity extends Activity {
	Button map;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		map = (Button) findViewById(R.id.button_map);
		map.setBackgroundResource(R.drawable.back);
		map.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view) {
				
				String place = "Pomme LES";
				
				String uriBegin = "geo: 40.6700, -73.9400";
				String query = Uri.encode(place);
				String uriQuery = uriBegin + "?q=" + query + "&z=12";
				Uri location = Uri.parse(uriQuery);
				Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
				
				PackageManager packageManager = getPackageManager();
				List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
				boolean isIntentSafe = activities.size() > 0;
				
				if (isIntentSafe) {
				    startActivity(mapIntent);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

}
