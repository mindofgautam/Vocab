package com.Vocab;

import java.io.IOException;
import java.util.Locale;

import android.app.Activity;
//import android.content.ContentValues;


import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
/**
 * 
 * @author KK88655
 *main class which invokes creation of table and links to other classes
 */
public class Vocab extends Activity {
	/** Called when the activity is first created. */
	
	Intent i;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		/**
		 * calling the database helper class to retrieve the database from assets
		 */
		 DataBaseHelper myDbHelper = new DataBaseHelper(this);
	      
	 
	        try {
	 
	        	myDbHelper.createDataBase();
	 
	 	} catch (IOException ioe) {
	 
	 		throw new Error("Unable to create database");
	 
	 	}
	 
	 	try {
	 /**
	  * calling the database from the from the assets
	  */
	 		myDbHelper.openDataBase();
	 
	 	}catch(SQLException sqle){
	 
	 		throw sqle;
	 
	 	}

		
		
		
		
		
		/**
		 * opening the database
		 */
		
		SQLiteDatabase db;
	        db = openOrCreateDatabase(
	       		"vocab.db"
	       		, SQLiteDatabase.CREATE_IF_NECESSARY
	       		, null
	      		);
	        db.setVersion(1);
	        db.setLocale(Locale.getDefault());
	        db.setLockingEnabled(true);
	        /**
	         * table creation
	         */
	         
	      /*
       final String CREATE_TABLE_WORDS =
	        	"CREATE TABLE words ("
	        	+ "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
	        	+ "word TEXT,"
	        	+ "meaning TEXT,"
	        	+ "form TEXT,"
	        	+ "pronunciation TEXT,"
	        	+ "synonyms TEXT,"
	        	+ "antonyms TEXT,"
	        	+ "rwords TEXT,"
	        	+ "usage TEXT);";
	        db.execSQL(CREATE_TABLE_WORDS);
	       
	          
	      
		
	        
	        
		final String CREATE_TABLE_TES="CREATE TABLE tes (score INTEGER,count INTEGER,ven INTEGER,_id INTEGER PRIMARY KEY AUTOINCREMENT);";
	        db.execSQL(CREATE_TABLE_TES);
	        ContentValues values1 = new ContentValues();
			values1.put("ven","11");
			db.insert("tes",null,values1);
			
	   
	        final String CREATE_TABLE_TE="CREATE TABLE testdata (_id INTEGER PRIMARY KEY AUTOINCREMENT,score INTEGER,name TEXT);";
	        db.execSQL(CREATE_TABLE_TE);
	        final String CREATE_TABLE_PROF="CREATE TABLE profiles (_id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT , gender TEXT, pid BLOB);";
	        db.execSQL(CREATE_TABLE_PROF);
	        db.close();
	        */
	        
	        
		//action sequence for exit button
	        Typeface font1=Typeface.createFromAsset(getAssets(), "BAUHS93.TTF");
	        TextView tit=(TextView)findViewById(R.id.maintextView1);
	        tit.setTypeface(font1);
		Button bt5=(Button)findViewById(R.id.button5);
		bt5.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{
				
				finish();

			}
		});

		
		//action sequence for help button
		ImageButton bthelp=(ImageButton)findViewById(R.id.help3);
		
		bthelp.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent i = new Intent(Vocab.this, Help.class);
				startActivity(i);
				

			}
		});
		
		//action sequence for aboutus button
		ImageButton btabt=(ImageButton)findViewById(R.id.aboutus);
		
		btabt.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent i = new Intent(Vocab.this, Aboutus.class);
				startActivity(i);
				

			}
		});
		
		
		
		
		
		//action sequence for search button
		Button bt1=(Button)findViewById(R.id.button1);
		bt1.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent i = new Intent(v.getContext(), Search1.class);
				 Bundle bundle = new Bundle(); 
				 bundle.putString("wo","" );
				 i.putExtras(bundle);
				startActivity(i); 
				//finish();	
			}
		});

		
		//action sequence for wordlist button
		Button bt22=(Button)findViewById(R.id.button22);
		bt22.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent i = new Intent(v.getContext(), Wordlist.class);
				startActivity(i); 
				//finish();	
			}
		});
		
		
		
		
		//action sequence for add_a_word button
		Button bt2=(Button)findViewById(R.id.button2);
		bt2.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent i = new Intent(Vocab.this, Flashcard.class);
				startActivity(i); 
				 //finish();	
			}
		});

		//action sequence for test
		Button bt3=(Button)findViewById(R.id.button3);
		bt3.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent i = new Intent(Vocab.this, Bet.class);
				startActivity(i); 
				 //finish();	
			
			}
		});



		//action sequence for settings
	/*	Button bt4=(Button)findViewById(R.id.button4);
		bt4.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{ 
				Intent i = new Intent(Vocab.this, Settings1.class);
				 
				startActivity(i); 
				//finish();	
			}
		});*/


	}


}