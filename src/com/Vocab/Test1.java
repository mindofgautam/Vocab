package com.Vocab;



import java.io.ByteArrayInputStream;
import java.util.Locale;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
//import android.widget.Toast;
import android.widget.Toast;
/**
 * 
 * @author KK88655
 * class to view the details of the logged user
 */
public class Test1 extends Activity {
	/**
	 * variable declaration
	 * @imageByteArray 
	 * 
	 */
	Bitmap theImage;
	byte[] imageByteArray;
	ImageView vv;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.test1);
        /**
         * retrieving the user name 
         */
        Bundle bundle=getIntent().getExtras();
        final String s=bundle.getString("un");
       // Toast.makeText(getApplicationContext(), s,Toast.LENGTH_LONG).show();
        String gen="";
        int tests=0,hscore=0,temp=0;
        /**
         * creating variables for the text views and image view in the layout
         */
        TextView wel=(TextView)findViewById(R.id.nametextView1);
        TextView wel2=(TextView)findViewById(R.id.gendertextView2);
        TextView wel3=(TextView)findViewById(R.id.highscoretextView3);
        TextView wel4=(TextView)findViewById(R.id.teststextView4);
        wel.append(s);
        vv=(ImageView)findViewById(R.id.profimageView1);
        /**
         * action for image click
         */
        vv.setClickable(true); 
        vv.setOnClickListener(new OnClickListener() 
        {        
        	@Override        
        	public void onClick(View v) 
        	{            
        		 int width = theImage.getWidth();
        	        int height = theImage.getHeight();
        	        int newWidth = 130;
        	        int newHeight = 120;
        	        float scaleWidth = ((float) newWidth) / width;
        	        float scaleHeight = ((float) newHeight) / height;
        	        // createa matrix for the manipulation
        	        Matrix matrix = new Matrix();
        	        // resize the bit map
        	        matrix.postScale(scaleWidth, scaleHeight);
        	     // rotate the Bitmap
        	        matrix.postRotate(-90);
        	        // recreate the new Bitmap
        	        Bitmap resizedBitmap = Bitmap.createBitmap(theImage, 0, 0, 
        	                          width, height, matrix, true); 
        	        
        	        
        	        /**
        	         * setting the bitmap to a image view
        	         */
        	        vv.setImageBitmap(resizedBitmap);          
        		         
        				     
        	}
        });
        
        
        
        
        
        
        /*
         *opening the database 
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
         * retrieving the byte array from the database
         */
        while (cur.isAfterLast() == false) 
	        {
	           if(cur.getString(1).equals(s))
	           {
	        	   imageByteArray=cur.getBlob(3);
	        	   gen=cur.getString(2);
	           break;
	           }
	           else
 	           {
 	       	    cur.moveToNext();
 	           }
	        
	        }
        
        
        /**
         * converting the byte array from the database to a bitmap
         */
        wel2.append(gen);
        ByteArrayInputStream imageStream = new ByteArrayInputStream(imageByteArray);
        theImage = BitmapFactory.decodeStream(imageStream);
      /*  
        //rotating the image
        int width = theImage.getWidth();
        int height = theImage.getHeight();
        int newWidth = 130;
        int newHeight = 120;
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // createa matrix for the manipulation
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
     // rotate the Bitmap
        matrix.postRotate(-90);
        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(theImage, 0, 0, 
                          width, height, matrix, true); */
        
        
        /**
         * setting the bitmap to a image view
         */
        vv.setImageBitmap(theImage);
        cur.close();
        
        Cursor cur2=db.query("testdata",null,null,null,null,null,null);
        cur2.moveToFirst();
       /**
        * retrieving the score from the database 
        */
        while (cur2.isAfterLast() == false) 
        {
           if(cur2.getString(2).equals(s))
           {	
        	   temp=Integer.parseInt(cur2.getString(1));
        	   if(temp>=hscore)
        	   {
        		   hscore=temp;
        	   }
        	   tests++;
           break;
           }
           else
	           {
	       	    cur2.moveToNext();
	           }
        
        }
        
        /**
         * printing the highscore and no of tests taken
         */
        wel3.append(String.valueOf(hscore));
        wel4.append(String.valueOf(tests));
       
        //cur2.close();
        db.close();
        
        
     //   Toast.makeText(getApplicationContext(), s,Toast.LENGTH_LONG).show();
    /**
     * button to direct to stats class to view the users full details
     */
        Button bt2=(Button)findViewById(R.id.test1button2);
		bt2.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent i = new Intent(Test1.this, Stats.class);
				Bundle bundle = new Bundle(); 
				 bundle.putString("un",s);
				 i.putExtras(bundle);
				startActivity(i); 
				 //finish();	
			}
		});
        
		
		/**
		 * Button to delete profile
		 */
		Button bt4=(Button)findViewById(R.id.delprofbutton);
		bt4.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{
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
			        * clearing off the user's details in the database
			        */
			        db.delete("profiles", "name=?", new String[] {s});
			        db.delete("testdata", "name=?", new String[] {s});
			        Intent i = new Intent(Test1.this, Bet.class);
			        startActivity(i);
			        finish();
			}
		});
		
		
		
        
        Button b1=(Button)findViewById(R.id.test1button1);
        b1.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{ 
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
		        /**
		         * updating the score and question number
		         */
		        values.put("score",0);
		        values.put("count",1);
		        db.update("tes",values,"ven=?",new String[]{"11"});
				db.close();
		        Intent i = new Intent(Test1.this, Difflevel.class);
				Bundle bundle = new Bundle(); 
				 bundle.putString("un",s);
				 i.putExtras(bundle);
				startActivity(i); 
				finish();	
			}
		});
       
        
	}
	
	 /**
     * function that switches the layout to the main menu
     */
	@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
	
     menu.add(1,1,Menu.FIRST,"Back");
     
     return true;
    }
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		
		switch(item.getItemId())
		{
		case 1:
			Intent navi=new Intent(Test1.this,Vocab.class);
			startActivity(navi);
			finish();
			break;
		
			
		}
		return super.onOptionsItemSelected(item);
	}
}
