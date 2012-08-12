package com.Vocab;

import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
//import android.widget.Toast;
import android.widget.Toast;
/** 
 * 
 * @author KK88655
 *class to generate quiz
 */
public class Test2  extends Activity {
	
	TextView ctr;
	/**
	 * variable declarations
	 */
	long elapsed;     
	final static long INTERVAL=1000;     
	final static long TIMEOUT=50000;  
	Button qb1;
	  RadioButton r1;
	  RadioButton r2;
	  RadioButton r3;
	  
	  /**
	   * function to quit test and move back to main menu
	   */
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
		
     menu.add(1,2,Menu.FIRST,"Quit");
     return true;
    }
@Override
public boolean onOptionsItemSelected(MenuItem item)
{
switch(item.getItemId())
{
case 2:
	Intent navi=new Intent(Test2.this,Vocab.class);
	startActivity(navi);
	finish();
	
}
return super.onOptionsItemSelected(item);
}
	/**
	 * function to pass the timer limit and user log to the class to calculate score
	 */
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.test2);
        
        TextView sit=(TextView)findViewById(R.id.test222textView1);
        Typeface font1=Typeface.createFromAsset(getAssets(), "CANDARA.TTF");
        sit.setTypeface(font1);
        Bundle bundle=getIntent().getExtras();
        final String s=bundle.getString("un");
        final int g=bundle.getInt("un1");
        final long ter=g*1000;
      // Toast.makeText(getApplicationContext(),String.valueOf(g),Toast.LENGTH_LONG).show();
       qb1=(Button)findViewById(R.id.qbutton1);
        ctr=(TextView)findViewById(R.id.ctr);
        //timer code
        TimerTask task=new TimerTask()
        { 
        	 @Override             
        	 public void run() 
        	 {
        		 elapsed+=INTERVAL;                 
        		 if(elapsed>=ter)
        			 
        		 {    
        			 this.cancel();           
        			vend();
        			 displayText("Time Up!!");
        			//boolean ab=qb1.performClick();
        			 return;                 
        		} 
        		 displayText("Time: " + (ter-elapsed) / 1000);
        	 
        	 }
        };
        
        Timer timer = new Timer();         
        timer.scheduleAtFixedRate(task, INTERVAL, INTERVAL);  
        
        
        
        TextView tt1=(TextView)findViewById(R.id.test2textView2);
         r1=(RadioButton)findViewById(R.id.option1);
        r2=(RadioButton)findViewById(R.id.option2);
        r3=(RadioButton)findViewById(R.id.option3);
        
        
        Random randomGenerator = new Random();
        Random randomGenerator2 = new Random();

        int a = randomGenerator.nextInt(866);
        String quest;
		final String ans1;
		String ans2, ans3;

         
        // Toast.makeText(getApplicationContext(),String.valueOf(a) , Toast.LENGTH_LONG).show();
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
        Cursor cur2=db.query("tes",null,null,null,null,null,null);
        cur.moveToFirst();
        cur2.moveToFirst();
        TextView ty=(TextView)findViewById(R.id.testindex);
        ty.append(cur2.getString(1));
        ty.append(".");
        cur.moveToPosition(a);
        quest=cur.getString(2);
        ans1=cur.getString(1);
        cur.moveToPosition((a+1)%867);
        ans2=cur.getString(1);
        cur.moveToPosition((a+2)%867);
        ans3=cur.getString(1);
        tt1.setText(quest);
        r1.setClickable(true);
		 r2.setClickable(true);
		 r3.setClickable(true);

        int b = randomGenerator2.nextInt(3);
       // Toast.makeText(getApplicationContext(), String.valueOf(b),Toast.LENGTH_LONG).show();
        /**
         * shuffling the answer 
         */
       if(b==0)
       {
        r1.setText(ans1);
        r2.setText(ans2);
        r3.setText(ans3);
       }
       else if(b==1)
       {
    	   r1.setText(ans3);
           r2.setText(ans1);
           r3.setText(ans2);
       }
       else if(b==2)
       {
    	   r1.setText(ans2);
           r2.setText(ans3);
           r3.setText(ans1);
       }
	
       
       
       qb1.setOnClickListener (new OnClickListener()
		{
    	  
			public void onClick(View v)
			{int tep=0,tep2;
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
		        ContentValues values = new ContentValues();
		        Cursor cur2=db.query("tes",null,null,null,null,null,null);
		        cur2.moveToFirst();
				/**
				 *  check for user answer
				 */
				String uans="";
			
				if(r1.isChecked()==true)
				{
					uans=r1.getText().toString();
					
				}
				else if(r2.isChecked()==true)
				{
					uans=r2.getText().toString();
				
				}
			
				else if(r3.isChecked()==true)
				{
					uans=r3.getText().toString();
				}
			/**
			 * updation of the database with respect to the user selection of answer
			 */
				
				/**
				 * action script when the user's answer is correct
				 */
				if(uans.equals(ans1))
				{
					tep=Integer.parseInt(cur2.getString(0));
					tep2=Integer.parseInt(cur2.getString(1));
					tep++;
					tep2++;
					values.put("score",tep);
					values.put("count", tep2);
					db.update("tes",values,"ven=?",new String[]{"11"});
				
				
				}
			/**
			 * action script when the answer is wrong
			 */
				else
				{	
				/**
				 * printing the right answer when the user's answer is wrong
				 */
				
					Toast.makeText(getApplicationContext(),"The right answer : "+ ans1, Toast.LENGTH_SHORT).show();
				
					tep2=Integer.parseInt(cur2.getString(1));
					tep2++;
					values.put("count", tep2);
					db.update("tes",values,"ven=?",new String[]{"11"});
					
				}
				
			//	Toast.makeText(getApplicationContext(), String.valueOf(tep), Toast.LENGTH_LONG).show();
			//	Toast.makeText(getApplicationContext(), String.valueOf(tep2), Toast.LENGTH_LONG).show();
				
				/**
				 * moving to result1 class after the quiz gets over to calculate score
				 */
			if(tep2==16)
			{
				Intent i = new Intent(Test2.this, Result1.class);
				Bundle bundle = new Bundle(); 
				 bundle.putString("un",s);
				 i.putExtras(bundle);
				startActivity(i); 
				finish();
			}
			/**
			 * generating different questions 
			 */
			else
			{
				/**
				 * intent that defines the action after the last question
				 */
			Intent i = new Intent(Test2.this, Test2.class);
			Bundle bundle = new Bundle(); 
			 bundle.putString("un",s);
			 bundle.putInt("un1",g);
			 i.putExtras(bundle);
			startActivity(i); 
			finish();
			}
			}
		});
	db.close();
	
	}

	
	/**
	 * prevents user from choosing the answer after the time limit is done
	 */
	public void vend()
	{	
		Log.i("tag", "hi babes");
		r1.setClickable(false);
		 r2.setClickable(false);
		 r3.setClickable(false);
	
	}
	
	 private void displayText(final String text){  
		 
		 this.runOnUiThread(new Runnable(){            
			 /**
			  * function to make timer run
			  */
			 @Override            
			 public void run() { 
				 
		 ctr.setText(text);             
		 
			 }});     } 
	
	
	
	
	
	
	
	
	
}