package com.Vocab;

import java.util.Locale;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author KK88655
 *Class that displays the result of the test 
 */
public class Result1 extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result1);
        /**
         * recieving the user name from the previous class
         */
        
        Bundle bundle=getIntent().getExtras();
        final String s=bundle.getString("un");
       // Toast.makeText(getApplicationContext(), s,Toast.LENGTH_LONG).show();
        /**
         * opening the database and the inserting the user score
         */
        String a;
        SQLiteDatabase db;
        db = openOrCreateDatabase(
       		"vocab.db"
       		, SQLiteDatabase.CREATE_IF_NECESSARY
       		, null
      		);
        db.setVersion(1);
        db.setLocale(Locale.getDefault());
        db.setLockingEnabled(true);
        Cursor cur=db.query("tes",null,null,null,null,null,null);
        cur.moveToFirst();
        a=cur.getString(0);
        TextView t1=(TextView)findViewById(R.id.result1textView2);
        t1.append("  "+a);
        ContentValues values = new ContentValues();
        values.put("score",a);
        values.put("name",s);
        db.insert("testdata",null,values);
 	   
        /**
         * action script for the button that takes the user to the view history class
         */
        Button bt2=(Button)findViewById(R.id.result1button1);
		bt2.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent i = new Intent(Result1.this, Stats.class);
				Bundle bundle = new Bundle(); 
				 bundle.putString("un",s);
				 i.putExtras(bundle);
				startActivity(i); 
				 finish();	
			}
		});
	
	
	}
	/**
	 * code to create menus
	 */
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
		
     menu.add(1,2,Menu.FIRST,"Main Menu");
     return true;
    }
@Override
public boolean onOptionsItemSelected(MenuItem item)
{
switch(item.getItemId())
{
case 2:
	/**
	 * moving to the main menu
	 */
	Intent navi=new Intent(Result1.this,Vocab.class);
	startActivity(navi);
	finish();
	
}
return super.onOptionsItemSelected(item);
}



}
