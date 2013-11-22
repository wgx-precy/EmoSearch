package com.example.emosearch.helper;

import java.util.ArrayList;

import com.example.emosearch.model.PreferencesModel;
import com.example.emosearch.model.ProfileModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EmoSearchHelper extends SQLiteOpenHelper {
	 
    // Log
    private static final String LOG = "DatabaseHelper";
 
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "EmoSearchDb";
 
    // Table Names
    private static final String TABLE_PROFILE = "profile";
    private static final String TABLE_PREFERENCES = "preferences";
    
    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";
 
    // Profile Table - column names
    private static final String KEY_PROFILE_ID = "profileId";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PREFERENCES_ID = "pId";
 
    // Preferences Table - column names
    private static final String KEY_PREFEREMCES_ID = "preferencesId";
    private static final String KEY_LEVEL1 = "level1";
    private static final String KEY_LEVEL2 = "level2";
    private static final String KEY_HS_RATING = "hsRating";
    private static final String KEY_TE_RATING = "teRating";
 
 
    // Table Create Statements
    // Profile table create statement
    private static final String CREATE_TABLE_PROFILE = "create table " 
	  + TABLE_PROFILE + "(" 
	  + KEY_PROFILE_ID + " integer primary key autoincrement, " 
	  + KEY_USER_ID+ " text, "
	  + KEY_PASSWORD+ " text, "
	  + KEY_PREFERENCES_ID+ " text); ";
 
    // Preferences table create statement
    private static final String CREATE_TABLE_PREFERENCES = "CREATE TABLE "
            + TABLE_PREFERENCES + "("
    		+ KEY_PREFEREMCES_ID + " integer primary key autoincrement, " 
            + KEY_LEVEL1 + " TEXT, " 
    		+ KEY_LEVEL2 + " TEXT, " 
            + KEY_HS_RATING + " TEXT, " 
            + KEY_TE_RATING + " TEXT " + ");";
    
 
    public EmoSearchHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
 
        // creating required tables
        db.execSQL(CREATE_TABLE_PROFILE);
        db.execSQL(CREATE_TABLE_PREFERENCES);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PREFERENCES);
 
        // create new tables
        onCreate(db);
    }
    
    /*
     * Creating a trip
     */
    public void createProfile(ProfileModel profile) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        double preferencesId = Math.random();
        
        ContentValues values = new ContentValues();
//        values.put(KEY_PROFILE_ID, Math.random());
        values.put(KEY_USER_ID, profile.getUserId());
        values.put(KEY_PASSWORD, profile.getPassword());
        values.put(KEY_PREFERENCES_ID, preferencesId);
        
        db.insert(TABLE_PROFILE, null, values);
        Log.d("insert","inserted");
        db.close();
    }
    
    public boolean login(String userId, String password){
    	SQLiteDatabase db = this.getWritableDatabase();
    	String queryString =
    		    "SELECT userId, password FROM "+ TABLE_PROFILE  +
    		    " WHERE userId = ? and password = ?";
    	
    	String[] whereArgs = new String[] {
    		    userId, password
    		};
    	    	   
    	String uId = null;
    	String pwd = null;
    	
        Cursor cursor = db.rawQuery(queryString, whereArgs);
    	
        if (cursor.moveToFirst()) {
            do {            	
                uId = cursor.getString(cursor.getColumnIndexOrThrow("userId"));    
                pwd = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            } while (cursor.moveToNext());
        }
        
        if(uId.equals(userId) && pwd.equals(password)){
        	return true;
        }else return false;
        
    }
    
    /*
     * Creating a preference table
     */
    public void createPreferences(PreferencesModel preference) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(KEY_PREFERENCES_ID, preference.getPreferencesId());
        values.put(KEY_LEVEL1, preference.getL1Id());
        values.put(KEY_LEVEL2, preference.getLevel2Id());
        values.put(KEY_HS_RATING, preference.getHsRating());
        values.put(KEY_TE_RATING, preference.getTeRating());             
        // insert row
        db.insert(TABLE_PREFERENCES, null, values);        
    }
    
    public ArrayList<String> getFromPreferences(String userId, String hsRating, String teRating){
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	String queryString =
    		    "SELECT level1, level2 FROM TABLE_PREFERENCES pre, TABLE_PROFILE pro " +
    		    "WHERE pro.userId = ? and pre.preferencesId=pro.preferencesId ";
    	
    	String[] whereArgs = new String[] {
    		    userId
    		};
    	    	
    	
        Cursor cursor = db.rawQuery(queryString, whereArgs);    	    	
    	
    	ArrayList<String> category = new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
            	            	
            	category.add( cursor.getString(cursor.getColumnIndexOrThrow("level1"))
            			+cursor.getString(cursor.getColumnIndexOrThrow("level2")));
            } while (cursor.moveToNext());
        }
                
        if (cursor != null && !cursor.isClosed()) {
        	cursor.close();
        }
    	return category;
    }
    
    public void updatePreferences(String userId, String hsRating, String teRating){
    	SQLiteDatabase db = this.getWritableDatabase();
    	
    	String queryString =
    		    "SELECT preferencesId FROM TABLE_PROFILE " +
    		    "WHERE userId = ? ";
    	
    	String[] whereArgs = new String[] {
    		    userId
    		};
    	    	
    	
        Cursor cursor = db.rawQuery(queryString, whereArgs);
    	String preferenceId = null;
        if (cursor.moveToFirst()) {
            do {
            	preferenceId=cursor.getString(cursor.getColumnIndexOrThrow("preferencesId"));
            } while (cursor.moveToNext());
        }
        
        String[] whereArg = new String[]{preferenceId};
        
        if (cursor != null && !cursor.isClosed()) {
        	cursor.close();
        }
        
        ContentValues val = new ContentValues();
        val.put(KEY_HS_RATING, hsRating);
        val.put(KEY_TE_RATING, teRating);
        
    	db.update(TABLE_PROFILE, val, "preferencesId = ?", whereArg);
    }
}