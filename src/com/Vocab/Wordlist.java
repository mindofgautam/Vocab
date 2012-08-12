package com.Vocab;

import java.util.Arrays;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
/**
 * 
 * @author KK88655
 *class to retrieve the words from the database and show it in a listview
 */
public class Wordlist extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordlist);
        /**
         * changing the font of the text view
         */
        TextView sit=(TextView)findViewById(R.id.wordlistextView1);
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
        Cursor cur=db.query("words",null,null,null,null,null,null);
        cur.moveToFirst();
       /**
        * populating the string to get it fixed in list view
        */
        String[] str=new String[cur.getCount()];
        if(cur.getCount() >0) 
        {             
        	 str = new String[cur.getCount()];
        	int i = 0;              
        	do{                  
        		str[i] = cur.getString(1);
        		i++;
        	}while (cur.moveToNext());
	        	   
        	
        } 
       cur.close();
       db.close();
       /**
        * Defining the list view and sorting the words in alphabetical order
        */
       ListView l=(ListView)findViewById(R.id.listView1);
       Arrays.sort(str);
       l.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , str));
       /**
        * the action that takes place when the user clicks any word in the list
        */
       l.setOnItemClickListener( new OnItemClickListener()
       {          
       
       /**
        * creation of adapter to pass the selected word from the wordlist and display its details
        */
       
       	@Override        
       	public void onItemClick(AdapterView<?> adapter, View view, int pos, long arg3) 
       	{ 
       		String str=adapter.getItemAtPosition(pos).toString();
       		Intent i = new Intent(Wordlist.this, Search1.class);
			Bundle bundle = new Bundle(); 
			 bundle.putString("wo",str);
			 i.putExtras(bundle);
			startActivity(i); 
			 //finish();	
       	}
	});
       }
	

	
	
}
