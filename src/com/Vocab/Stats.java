package com.Vocab;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author KK88655
 *Class that displays the user statistics
 *
 */
public class Stats extends Activity {
	int i;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stats);
        
        TextView sit=(TextView)findViewById(R.id.statisticstextView1);
        Typeface font1=Typeface.createFromAsset(getAssets(), "CANDARA.TTF");
        sit.setTypeface(font1);
        /**
         * retriving the user name from the database to view the performance
         */
        Bundle bundle=getIntent().getExtras();
        final String s=bundle.getString("un");
       // Toast.makeText(getApplicationContext(), s,Toast.LENGTH_LONG).show();
        TextView qb=(TextView)findViewById(R.id.statstextView4);
        qb.append(s);
        
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
        Cursor cur=db.query("testdata",null,null,null,null,null,null);
        cur.moveToFirst();
        
        String[] str=new String[cur.getCount()];
        String[] str2=new String[cur.getCount()];
        int cc=cur.getCount();
       if(cur.getCount() >0) 
        {             
    	   /**
    	    * comparing the entries in the database and retriving the details of the logged user
    	    */
        	 str = new String[cur.getCount()];
        	 str2= new String[cur.getCount()];
        	 i = 0;              
        	do{              
        		if(cur.getString(2).equals(s))
        		{
        		str[i] = String.valueOf(i+1);//cur.getString(0);
        		str2[i] = cur.getString(1);
        		i++;
        		}
        		
        	}while (cur.moveToNext());
	        	   
        	
        } 
	/**
	 * creating variables for the two text views test no and score
	 */
       TextView ttv1=(TextView)findViewById(R.id.testnotextView4);
       TextView ttv2=(TextView)findViewById(R.id.testscoretextView5);
       
       for(int j=0;j<i;j++)
       {
    	   ttv1.append(str[j]+"\n\n");
    	   ttv2.append(str2[j]+"\n\n");
       }
	
	
	
	}
/**
 * function that switches the layout to the main menu
 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
		
     menu.add(1,2,Menu.FIRST,"Main Menu");
     return true;
    }
	
	/*
	 * Menu Handler to move to main menu layout
	 */
@Override
public boolean onOptionsItemSelected(MenuItem item)
{
switch(item.getItemId())
{
case 2:
	/**
	 * intent to move from current layout to main menu
	 */
	Intent navi=new Intent(Stats.this,Vocab.class);
	startActivity(navi);
	finish();
	
}
return super.onOptionsItemSelected(item);
}



}
