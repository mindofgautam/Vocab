package com.Vocab;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


/**
 * 
 * @author KK88655
 * This class is used to add a word to the database
 *@t1 text field that accepts word
 *@t2 text field that accepts meaning
 *@t2 text field that accepts form
 */
public class Addword1 extends Activity {
	 EditText t1;
	 EditText t2;
	 EditText t3;
		public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.addword1);
	        TextView sit=(TextView)findViewById(R.id.addwordtitle);
	        Typeface font1=Typeface.createFromAsset(getAssets(), "CANDARA.TTF");
	        sit.setTypeface(font1);
	       
	        
	        
		}
		/**
		 * Code to create menu buttons in the layout
		 */
		 @Override
		    public boolean onCreateOptionsMenu(Menu menu)
		    {
			
		     menu.add(1,1,Menu.FIRST,"Add Now");
		     menu.add(1,2,Menu.FIRST+1,"Back");
		     return true;
		    }
		 /**
		  * Code to create dialog box that appears after inserting a word
		  */
		 
		 @Override
		 public boolean onOptionsItemSelected(MenuItem item)
		 {
			 AlertDialog.Builder alert_box=new AlertDialog.Builder(this);
			 alert_box.setMessage("Word Added!");
			 alert_box.setPositiveButton("OK",new DialogInterface.OnClickListener() {
				 @Override
				 public void onClick(DialogInterface dialog, int which) {
				 // navigating to main menu
					 Intent navi1=new Intent(Addword1.this,Vocab.class);
				 		startActivity(navi1);
				 		finish();
					}
				 });
			 /**
			  * Code to create alert boxes that appears after error validation
			  */
			 AlertDialog.Builder alert_box2=new AlertDialog.Builder(this);
			 alert_box2.setMessage("The 'Word' field cannot be empty.");
			 alert_box2.setPositiveButton("OK",new DialogInterface.OnClickListener() {
				 @Override
				 public void onClick(DialogInterface dialog, int which) {
				 // setting focus on word field
					t1=(EditText)findViewById(R.id.aeditText1);
					 t1.requestFocus(1);
					}
				 });
			AlertDialog.Builder alert_box3=new AlertDialog.Builder(this);
			 alert_box3.setMessage("The 'Meaning' field cannot be empty.");
			 alert_box3.setPositiveButton("OK",new DialogInterface.OnClickListener() {
				 @Override
				 public void onClick(DialogInterface dialog, int which) {
				 // setting focus on word field
					 t2=(EditText)findViewById(R.id.aeditText2);
					 t2.requestFocus(1);
					}
				 });
			 AlertDialog.Builder alert_box4=new AlertDialog.Builder(this);
			 alert_box4.setMessage("The 'Form' field cannot be empty.");
			 alert_box4.setPositiveButton("OK",new DialogInterface.OnClickListener() {
				 @Override
				 public void onClick(DialogInterface dialog, int which) {
				 // setting focus on word field
					 t3=(EditText)findViewById(R.id.aeditText3);
					 t3.requestFocus(1);
					}
				 });

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
		         * Creating the sql statement to insert the values into the database
		         */
		 	switch(item.getItemId())
		 	{
		 	case 1:
		 		 EditText t1=(EditText)findViewById(R.id.aeditText1);
		 		 String word=t1.getText().toString();
		 		EditText t2=(EditText)findViewById(R.id.aeditText2);
		 		 String meaning=t2.getText().toString();
		 		EditText t3=(EditText)findViewById(R.id.aeditText3);
		 		 String form=t3.getText().toString();
		 		EditText t4=(EditText)findViewById(R.id.aeditText4);
		 		 String pronunciation=t4.getText().toString();
		 		EditText t5=(EditText)findViewById(R.id.aeditText5);
		 		 String synonyms=t5.getText().toString();
		 		EditText t6=(EditText)findViewById(R.id.aeditText6);
		 		 String antonyms=t6.getText().toString();
		 		EditText t7=(EditText)findViewById(R.id.aeditText7);
		 		 String rwords=t7.getText().toString();
		 		EditText t8=(EditText)findViewById(R.id.aeditText8);
		 		 String usage=t8.getText().toString();
		 		ContentValues values = new ContentValues();
		        
		 		if(word.equals(""))
		 			{
		 			alert_box2.show();                                                                            
		 			}
		 		else if(meaning.equals(""))
		 		{
		 			alert_box3.show();
		 		}
		 		else if(form.equals(""))
		 		{
		 			alert_box4.show();
		 		}
		 		else{
		 			
		 		values.put("word", word);
		        values.put("meaning", meaning);
		        values.put("form", form);
		        values.put("pronunciation", pronunciation);
		        values.put("synonyms", synonyms);
		        values.put("antonyms", antonyms);
		        values.put("rwords", rwords);
		        values.put("usage", usage);
		                              
		        
		        db.insert("words", null, values);
		        alert_box.show();
		 		}
		        

		 		 
		 		break;
/**
 * Moving to the main menu
 */
		 	case 2:
		 		Intent navi1=new Intent(Addword1.this,Vocab.class);
		 		startActivity(navi1);
		 		finish();
		 		
		 	}
		 	return super.onOptionsItemSelected(item);
		 }


}