package com.Vocab;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.SimpleCursorAdapter;
//import android.widget.Toast;
/**
 * Class that searches a given word from the database and displays it
 */

public class Search1 extends Activity implements OnInitListener{
	public int i=1;
	String s;
	TextToSpeech talker;
	String talkword;
	 Button blisten;
	
		public void onCreate(Bundle savedInstanceState) {
			
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.search1);
	        
	        /**
	         * action listener for the listen button
	         */
	       blisten=(Button)findViewById(R.id.listenbutton1);
	       blisten.setVisibility(4);
			blisten.setOnClickListener (new OnClickListener()
			{
				public void onClick(View v)
				{
					
					talkme();

				}
			});

	        
	        
	        /**
	         * getting the user name from the previous class
	         */
	        Bundle bundle=getIntent().getExtras();
	        s=bundle.getString("wo");
	        talkword=s;
	        //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();

	        /**
	         * assigning a font for the page title
	         */
	        TextView sit=(TextView)findViewById(R.id.searchtextView1);
	        Typeface font1=Typeface.createFromAsset(getAssets(), "CANDARA.TTF");
	        sit.setTypeface(font1);
	    /**
	     * setting the size and id for all the views
	     */
	        final TextView tv1 = new TextView(this);
	        tv1.setId(11);
	        tv1.setTextSize(17);
	       // tv1.setTextColor(R.color.tc);
	        /**
	         * creating the layouts for displaying the word details
	         */
	        final TextView tv2 = new TextView(this);
	        tv2.setId(12);
	        tv2.setTextSize(17);
	        final TextView tv3 = new TextView(this);
	        tv3.setId(13);
	        tv3.setTextSize(17);
	        final TextView tv4 = new TextView(this);
	        tv4.setId(14);
	        tv4.setTextSize(17);
	        final TextView tv5 = new TextView(this);
	        tv5.setId(15);
	        tv5.setTextSize(17);
	        final TextView tv6 = new TextView(this);
	        tv6.setId(16);
	        tv6.setTextSize(17);
	        final TextView tv7 = new TextView(this);
	        tv7.setId(17);
	        tv7.setTextSize(17);
	        final TextView tv11 = new TextView(this);
	        tv11.setId(111);
	        
	        /**
	         * setting the parameters of each layouts
	         */
	        LayoutParams p1 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	        tv1.setLayoutParams(p1);
	        LayoutParams p2 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	        tv2.setLayoutParams(p2);
	        LayoutParams p3 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	        tv3.setLayoutParams(p3);
	        LayoutParams p4 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	        tv4.setLayoutParams(p4);
	        LayoutParams p5 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	        tv5.setLayoutParams(p5);
	        LayoutParams p6 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	        tv6.setLayoutParams(p6);
	        LayoutParams p7 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	        tv7.setLayoutParams(p7);
	        
	        LayoutParams p11 = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	        tv11.setLayoutParams(p11);
	        
	        
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
	         * populating the array with list of words available in the database
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
	    //when called from widget   
	     if(!s.equals(""))
	     {blisten.setVisibility(0);
	    	 SQLiteDatabase db3;
 	        db3 = openOrCreateDatabase(
 	       		"vocab.db"
 	       		, SQLiteDatabase.CREATE_IF_NECESSARY
 	       		, null
 	      		);
 	        db3.setVersion(1);
 	        db3.setLocale(Locale.getDefault());
 	        db3.setLockingEnabled(true);
     		Cursor cur3=db3.query("words",null,null,null,null,null,null);
 	        cur3.moveToFirst();
 	     /**
 	      * searching for a particular word and retrieving its details
 	      */
 	        
 	        while (cur3.isAfterLast() == false) 
 	        {
 	           if(cur3.getString(1).equals(s))
 	           {
 	        	   if(i>1)
 	        	   {
 	        		   /**
 	        		    * removing the views if it is already available
 	        		    */
 	        		   ViewGroup vg1 = (ViewGroup)(tv1.getParent());
 	        		   vg1.removeView(tv1);
 	        		   ViewGroup vg2 = (ViewGroup)(tv2.getParent());
 	        		   vg2.removeView(tv2);
 	        		   ViewGroup vg3 = (ViewGroup)(tv3.getParent());
 	        		   vg3.removeView(tv3);
 	        		   ViewGroup vg4 = (ViewGroup)(tv4.getParent());
 	        		   vg4.removeView(tv4);
 	        		   ViewGroup vg5 = (ViewGroup)(tv5.getParent());
 	        		   vg5.removeView(tv5);
 	        		   ViewGroup vg6 = (ViewGroup)(tv6.getParent());
 	        		   vg6.removeView(tv6);
 	        		   ViewGroup vg7 = (ViewGroup)(tv7.getParent());
 	        		   vg7.removeView(tv7);
 	        		   
 	        		   
 	        	   }
 	        	 
 	        	   TextView tv=(TextView)findViewById(R.id.w1);
 	        	   tv.setText(s);
 	        	   
 	        	   /**
 	        	    * creating variables for all the views and assigning the appropriate values
 	        	    */
 	        	   LinearLayout rel1 = (LinearLayout) findViewById(R.id.ll1);     
 		        	tv1.setText("\nMeaning:\n  "+cur3.getString(2));
 		        	rel1.addView(tv1);
 		        	i++;
 		        	
 		        	
 		        
 		        	LinearLayout rel2 = (LinearLayout) findViewById(R.id.ll2);     
 		        	tv2.setText("\n\nForm:\n  "+cur3.getString(3));
 		        	rel2.addView(tv2);
 		        	
 		        	LinearLayout rel3 = (LinearLayout) findViewById(R.id.ll3);     
 		        	tv3.setText("\n\nPronunciation:\n  "+cur3.getString(4));
 		        	rel3.addView(tv3);
 		        	
 		        	LinearLayout rel4 = (LinearLayout) findViewById(R.id.ll4);     
 		        	tv4.setText("\n\nSynonyms:\n  "+cur3.getString(5));
 		        	rel4.addView(tv4);
 		        	
 		        	LinearLayout rel5 = (LinearLayout) findViewById(R.id.ll5);     
 		        	tv5.setText("\n\nAntonyms:\n  "+cur3.getString(6));
 		        	rel5.addView(tv5);
 		        	
 		        	LinearLayout rel6 = (LinearLayout) findViewById(R.id.ll6);     
 		        	tv6.setText("\n\nRelated Words:\n  "+cur3.getString(7));
 		        	rel6.addView(tv6);
 		        	
 		        	LinearLayout rel7 = (LinearLayout) findViewById(R.id.ll7);     
 		        	tv7.setText("\n\nSentence:\n  "+cur3.getString(8));
 		        	rel7.addView(tv7);
 		        	
 		        	//Toast.makeText(getApplicationContext(), cur.getString(3),Toast.LENGTH_LONG).show();
 	           break;
 	           }
 	           else
 	           {
 	       	    cur3.moveToNext();
 	           }
 	           
 	        }
 	        /**
 	         * if word is not available
 	         */
 	        if(cur3.isAfterLast() == true)
 	        {
 	        	Toast.makeText(getApplicationContext(), "Word does not exist",Toast.LENGTH_LONG).show();
 	        }
 	        cur3.close();   	
	        	db3.close();
 	        
 	      

     	
     	
     	}      
	     
	    	 
	    	   /**
	    	    * defining the adapter for the auto complete view
	    	    */
	    	   
	    	   	       
	        
	      AutoCompleteTextView textview= (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, str);
	        textview.setAdapter(adapter);
	       
	      /**
	       * onclick listener for the autocomplete view
	       */
	    	textview.setOnItemClickListener( new OnItemClickListener()
	        {          //int i=1;
	        
	        
	        
	        	@Override        
	        	public void onItemClick(AdapterView<?> adapter, View view, int pos, long arg3) 
	        	{   
	        		/**
	        		 * opening the database and locating the selected word
	        		 */
	        	
	        		String str=adapter.getItemAtPosition(pos).toString();
	        		talkword=str;
	        		blisten.setVisibility(0);
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
	    	        while (cur.isAfterLast() == false) 
	    	        {
	    	           if(cur.getString(1).equals(str))
	    	           {
	    	        	   if(i>1)
	    	        	   {
	    	        		   ViewGroup vg1 = (ViewGroup)(tv1.getParent());
	    	        		   vg1.removeView(tv1);
	    	        		   ViewGroup vg2 = (ViewGroup)(tv2.getParent());
	    	        		   vg2.removeView(tv2);
	    	        		   ViewGroup vg3 = (ViewGroup)(tv3.getParent());
	    	        		   vg3.removeView(tv3);
	    	        		   ViewGroup vg4 = (ViewGroup)(tv4.getParent());
	    	        		   vg4.removeView(tv4);
	    	        		   ViewGroup vg5 = (ViewGroup)(tv5.getParent());
	    	        		   vg5.removeView(tv5);
	    	        		   ViewGroup vg6 = (ViewGroup)(tv6.getParent());
	    	        		   vg6.removeView(tv6);
	    	        		   ViewGroup vg7 = (ViewGroup)(tv7.getParent());
	    	        		   vg7.removeView(tv7);
	    	        		   
	    	        		   
	    	        	   }
	    	        	   TextView tv=(TextView)findViewById(R.id.w1);
	    	        	   tv.setText(str);
	    	        	   
	    	        	   LinearLayout rel1 = (LinearLayout) findViewById(R.id.ll1);     
	    		        	tv1.setText("\nMeaning:\n  "+cur.getString(2));
	    		        	rel1.addView(tv1);
	    		        	i++;
	    		        	
	    		        	
	    		        
	    		        	LinearLayout rel2 = (LinearLayout) findViewById(R.id.ll2);     
	    		        	tv2.setText("\n\nForm:\n  "+cur.getString(3));
	    		        	rel2.addView(tv2);
	    		        	
	    		        	LinearLayout rel3 = (LinearLayout) findViewById(R.id.ll3);     
	    		        	tv3.setText("\n\nPronunciation:\n  "+cur.getString(4));
	    		        	rel3.addView(tv3);
	    		        	
	    		        	LinearLayout rel4 = (LinearLayout) findViewById(R.id.ll4);     
	    		        	tv4.setText("\n\nSynonyms:\n  "+cur.getString(5));
	    		        	rel4.addView(tv4);
	    		        	
	    		        	LinearLayout rel5 = (LinearLayout) findViewById(R.id.ll5);     
	    		        	tv5.setText("\n\nAntonyms:\n  "+cur.getString(6));
	    		        	rel5.addView(tv5);
	    		        	
	    		        	LinearLayout rel6 = (LinearLayout) findViewById(R.id.ll6);     
	    		        	tv6.setText("\n\nRelated Words:\n  "+cur.getString(7));
	    		        	rel6.addView(tv6);
	    		        	
	    		        	LinearLayout rel7 = (LinearLayout) findViewById(R.id.ll7);     
	    		        	tv7.setText("\n\nSentence:\n  "+cur.getString(8));
	    		        	rel7.addView(tv7);
	    		        	
	    		        	//Toast.makeText(getApplicationContext(), cur.getString(3),Toast.LENGTH_LONG).show();
	    	           break;
	    	           }
	    	           else
	    	           {
	    	       	    cur.moveToNext();
	    	           }
	    	           
	    	        }
	    	        cur.close();   	
		        	db.close();
	    	        
	    	      

	        	
	        	
	        	}      
	        	}); 	        
	          
	    
		}
		/**
		 * creating menus for the current layout
		 */
		@Override
	    public boolean onCreateOptionsMenu(Menu menu)
	    {
			
	     menu.add(1,2,Menu.FIRST,"Back");
	     return true;
	    }
		/**
		 * onclick listener for the menu items
		 */
@Override
public boolean onOptionsItemSelected(MenuItem item)
{
	switch(item.getItemId())
	{
	case 2:
		Intent navi=new Intent(Search1.this,Vocab.class);
		startActivity(navi);
		finish();
		
	}
	return super.onOptionsItemSelected(item);
}

public void say(String text2say){
	talker.speak(text2say, TextToSpeech.QUEUE_FLUSH, null);
}

@Override
public void onInit(int status) {
	
	say(talkword);
	
}

@Override
public void onDestroy() {
	if (talker != null) {
		talker.stop();
		talker.shutdown();
	}

	super.onDestroy();
}



public void talkme()
{
	talker = new TextToSpeech(this, this);
}


}
