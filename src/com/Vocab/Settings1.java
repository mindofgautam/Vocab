package com.Vocab;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
//import android.widget.Toast;
import android.widget.Toast;

public class Settings1  extends Activity {
	public String ct,comp,categ,ct2;
	public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings1);
        
        SQLiteDatabase db;
        db = openOrCreateDatabase(
       		"vocab.db"
       		, SQLiteDatabase.CREATE_IF_NECESSARY
       		, null
      		);
        db.setVersion(1);
        db.setLocale(Locale.getDefault());
        db.setLockingEnabled(true);
        Cursor cur=db.query("settings",null,null,null,null,null,null);
        cur.moveToFirst();
        ct=cur.getString(0);
        int cate=Integer.parseInt(cur.getString(1));
        int compo=Integer.parseInt(cur.getString(2));
        Spinner spinner = (Spinner) findViewById(R.id.spinner); 
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);   
        spinner.setAdapter(adapter);
        spinner.setSelection(cate);
             
       
        spinner.setOnItemSelectedListener(new OnItemSelectedListener()
        {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos,
					long id) {
				SQLiteDatabase db;
		        db = openOrCreateDatabase(
		       		"vocab.db"
		       		, SQLiteDatabase.CREATE_IF_NECESSARY
		       		, null
		      		);
		        db.setVersion(1);
		        db.setLocale(Locale.getDefault());
		        db.setLockingEnabled(true);
		        ContentValues values = new ContentValues();
		        values.put("comp",pos);
				db.update("settings",values,"tem=?",new String[]{"1141"});
				
				// TODO Auto-generated method stub
				// Toast.makeText(parent.getContext(), "The Category is " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        
        }
        );
        
      /*  Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource( this, R.array.complexity_array, android.R.layout.simple_spinner_item);    adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setSelection(compo);
             
        
        spinner2.setOnItemSelectedListener(new OnItemSelectedListener()
        {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int pos,
					long id) {
				
				SQLiteDatabase db;
		        db = openOrCreateDatabase(
		       		"vocab.db"
		       		, SQLiteDatabase.CREATE_IF_NECESSARY
		       		, null
		      		);
		        db.setVersion(1);
		        db.setLocale(Locale.getDefault());
		        db.setLockingEnabled(true);
		        ContentValues values = new ContentValues();
		        values.put("categ",pos);
				db.update("settings",values,"tem=?",new String[]{"1141"});
				
				// TODO Auto-generated method stub
				Toast.makeText(parent.getContext(), "The Complexity is " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        
        }
        ); */
        
      
      
      
      //cur.close();
	EditText e1=(EditText)findViewById(R.id.seditText1);
	e1.setText(ct);
	}
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
		
     menu.add(1,1,Menu.FIRST,"Save");
     menu.add(1,2,Menu.FIRST+1,"Back");
     return true;
    }
@Override
public boolean onOptionsItemSelected(MenuItem item)
{
	
	

	
	AlertDialog.Builder alert_box=new AlertDialog.Builder(this);
alert_box.setMessage("Settings Updated!");
alert_box.setPositiveButton("OK",new DialogInterface.OnClickListener() {
	 @Override
	 public void onClick(DialogInterface dialog, int which) {
	 // navigating to main menu
			
	       
		 Intent navi1=new Intent(Settings1.this,Vocab.class);
		 
		 startActivity(navi1);
	 		finish();
		}
	 });
	switch(item.getItemId())
	{
	
	case 1:
		SQLiteDatabase db;
        db = openOrCreateDatabase(
       		"vocab.db"
       		, SQLiteDatabase.CREATE_IF_NECESSARY
       		, null
      		);
        db.setVersion(1);
        db.setLocale(Locale.getDefault());
        db.setLockingEnabled(true);
		
		EditText e2=(EditText)findViewById(R.id.seditText1);
		ct2=e2.getText().toString();
				ContentValues values = new ContentValues();
		values.put("ctime",ct2);
		db.update("settings",values,"ctime=?",new String[]{ct});
				alert_box.show();
		
				return(true);
		
	case 2:
		Intent navi1=new Intent(Settings1.this,Vocab.class);
		startActivity(navi1);
		finish();
		
	}
	return super.onOptionsItemSelected(item);
}
}
