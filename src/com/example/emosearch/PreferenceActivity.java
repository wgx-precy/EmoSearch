package com.example.emosearch;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.util.SparseArray;
import android.view.Menu;
import android.widget.ExpandableListView;


public class PreferenceActivity extends Activity {
	SparseArray<Group> groups = new SparseArray<Group>();
	List<ArrayList> category = new ArrayList();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preference);
		category = set_list();
		c_createData(category);
		ExpandableListView c_listView = (ExpandableListView) findViewById(R.id.listView);
		MyExpandableListAdapter c_exadapter = new MyExpandableListAdapter(this,groups);
		c_listView.setAdapter(c_exadapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.preference, menu);
		return true;
	}
	
	public List<ArrayList> set_list()
	{
		ArrayList level1 = new ArrayList();
		ArrayList<String> foodCategory = new ArrayList<String>();
		ArrayList<String> barCategory = new ArrayList<String>();
		ArrayList<String> showsCategory = new ArrayList<String>();
		ArrayList<String> movieCategory = new ArrayList<String>();
		ArrayList<String> travelCategory = new ArrayList<String>();
		ArrayList<String> sportCategory = new ArrayList<String>();
		
		level1.add(foodCategory);
		level1.add(barCategory);
		level1.add(showsCategory);
		level1.add(movieCategory);
		level1.add(travelCategory);
		level1.add(sportCategory);
		
		foodCategory.add("Street Vendors");
		foodCategory.add("Cake shop");
		foodCategory.add("Fast food");
		foodCategory.add("Organic Store");
		foodCategory.add("Do-it-Yourself");
		foodCategory.add("Fine dine");
		
		barCategory.add("Heavy Metal pubs");
		barCategory.add("Lounge bars");
		barCategory.add("Irish pubs");
		barCategory.add("Brewery");
		barCategory.add("Old timers");	
		return level1;
	}
	
	
	public void c_createData(List<ArrayList> values) {
		List<String> category1 = new ArrayList<String>();
		category1.add("Food");
		category1.add("Bars");
		category1.add("Shows");
		category1.add("Movies");
		category1.add("Travel");
		category1.add("Sport");
		for(int i=0;i<category1.size();i++){
			Group group = new Group(category1.get(i));			
			for(int j = 0; j < values.get(i).size(); j++){
				group.children.add((String) values.get(i).get(j));
		      }
		      groups.append(i, group);
		}	      
	  }

}
