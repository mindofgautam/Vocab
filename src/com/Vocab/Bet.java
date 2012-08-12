package com.Vocab;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Toast;
/**
 * 
 * @author KK88655
 *Class that authenticates a user and has an option for creating a profile
 */
public class Bet extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bet);
      
        TextView sit=(TextView)findViewById(R.id.bettitle);
        Typeface font1=Typeface.createFromAsset(getAssets(), "CANDARA.TTF");
        sit.setTypeface(font1);
        
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
        Cursor cur=db.query("profiles",null,null,null,null,null,null);
        cur.moveToFirst();
       
       /**
        * populating the array with the list of users available
        */
        
        String[] pro=new String[cur.getCount()];
              if(cur.getCount() >0) 
        {             
        	 pro = new String[cur.getCount()];
        	int i = 0;              
        	do{                  
        		pro[i] = cur.getString(1);
        		i++;
        	}while (cur.moveToNext());
	        	   
        	
        } 
       cur.close();
       db.close();
        
       final AutoCompleteTextView textview= (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, pro);
       textview.setAdapter(adapter);
	/**
	 * moving to the create profile class
	 */
       Button bun2=(Button)findViewById(R.id.betbutton1);
       bun2.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent i = new Intent(Bet.this, Newuser.class);
				startActivity(i); 
				 finish();	
			}
		});
       
       /**
        * authenticating the user and taking him to his home page
        */
       
       Button bun1=(Button)findViewById(R.id.betbutton2);
       bun1.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{String stt=textview.getText().toString();
				SQLiteDatabase db;
	        db = openOrCreateDatabase(
	           		"vocab.db"
	           		, SQLiteDatabase.CREATE_IF_NECESSARY
	           		, null
	          		);
	            db.setVersion(1);
	            db.setLocale(Locale.getDefault());
	            db.setLockingEnabled(true);
	    		Cursor cur=db.query("profiles",null,null,null,null,null,null);
	            cur.moveToFirst();
	            int y=0;
	            /**
	             * moving to the test class
	             */
	            
	            while (cur.isAfterLast() == false) 
	            {
	               if(cur.getString(1).equals(stt))
	               {	y=11;
	               
	            	   Intent navi=new Intent(Bet.this,Test1.class);
	            	   Bundle bundle = new Bundle(); 
						 bundle.putString("un",stt);
						 navi.putExtras(bundle);
	           		startActivity(navi);
	           		finish();
	           	 break;
	               }
	               else
	               {
	           	    cur.moveToNext();
	               }
	           	
	            }
	            cur.close();   	
	        	db.close();
	           	if(y!=11)
	           	{
	        	Toast.makeText(getApplicationContext(), "User Name does not exist!", Toast.LENGTH_LONG).show();
	           	}
	            
			}
		});
       
      /**
       * actionscript for the autocomplete textview
       */
      
       textview.setOnItemClickListener( new OnItemClickListener()
       {          //int i=1;
       
       
       
       	@Override        
       	public void onItemClick(AdapterView<?> adapter, View view, int pos, long arg3) 
       	{   String st=adapter.getItemAtPosition(pos).toString();
       	SQLiteDatabase db;
        db = openOrCreateDatabase(
       		"vocab.db"
       		, SQLiteDatabase.CREATE_IF_NECESSARY
       		, null
      		);
        db.setVersion(1);
        db.setLocale(Locale.getDefault());
        db.setLockingEnabled(true);
		Cursor cur=db.query("profiles",null,null,null,null,null,null);
        cur.moveToFirst();
        int x=0;
        /**
         * authenticating process
         */
        while (cur.isAfterLast() == false) 
        {
           if(cur.getString(1).equals(st))
           {	x=11;
        	   Intent navi=new Intent(Bet.this,Test1.class);
        	   Bundle bundle = new Bundle(); 
				 bundle.putString("un",st);
				 navi.putExtras(bundle);
       		startActivity(navi);
       		finish();
       	 break;
           }
           else
           {
       	    cur.moveToNext();
           }
       	
        }
        cur.close();   	
    	db.close();
       	if(x!=11)
       	{
    	Toast.makeText(getApplicationContext(), "User Name does not exist!", Toast.LENGTH_LONG).show();
       	}
       	}
       });
       
	
	}

}
