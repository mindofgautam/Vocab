package com.Vocab;

import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Flashcard extends Activity {
	String qn,ans;
	Button b1,b2;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flashcard);
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
        Random randomGenerator = new Random();
        int a = randomGenerator.nextInt(866);
        cur.moveToPosition(a);
        qn=cur.getString(1);
        ans=cur.getString(2);
        b1= (Button)findViewById(R.id.fcardbutton1);
        b1.setText(qn+"\n\nTap to view the answer.");
        b1.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{
				 b1.setText(qn+"\n\n");
				 b1.append(ans);
				 b1.setClickable(false);
			}
		});
        b2= (Button)findViewById(R.id.fcardbutton2);
        b2.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent i = new Intent(Flashcard.this, Flashcard.class);
				startActivity(i); 
				 finish();	
				
			}
		});
	
	
	}

}
