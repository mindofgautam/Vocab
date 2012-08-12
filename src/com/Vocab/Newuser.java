package com.Vocab;



import java.io.ByteArrayOutputStream;
import java.util.Locale;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 
 * @author KK88655
 *Class that creates a profile for a new user
 */
public class Newuser extends Activity {
	/**
	 * variable declarations
	 */
	int trick=0;
	Button bt5;
	Bitmap thumbnail;
	Intent cameraIntent;
	ImageView i;
	Cursor imagecursor;
	int me;
	int pid;
	Editable name;
	String gender;
	String img;
	ByteArrayOutputStream baos;
	byte[] byteArray;
	private static final int CAMERA_PIC_REQUEST = 1337;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * defining the radio buttons for gender selection and its action script
         */
        setContentView(R.layout.newuser);
        TextView sit=(TextView)findViewById(R.id.newusertextView1);
        Typeface font1=Typeface.createFromAsset(getAssets(), "CANDARA.TTF");
        sit.setTypeface(font1);
        
        
        
        /**
         * click event for image
         */
        i=(ImageView)findViewById(R.id.imageView1);
        i.setClickable(true); 
        i.setOnClickListener(new OnClickListener() 
        {        
        	@Override        
        	public void onClick(View v) 
        	{            
        		 int width = thumbnail.getWidth();
        	        int height = thumbnail.getHeight();
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
        	        Bitmap resizedBitmap = Bitmap.createBitmap(thumbnail, 0, 0, 
        	                          width, height, matrix, true); 
        	        
        	        
        	        /**
        	         * setting the bitmap to a image view
        	         */
        	        i.setImageBitmap(resizedBitmap);          
        		         
        				     
        	}
        });
        
        
        final RadioButton ro1=(RadioButton)findViewById(R.id.gen1);
        final RadioButton ro2=(RadioButton)findViewById(R.id.gen2);
        Button bt4=(Button)findViewById(R.id.newuserbutton2);
		bt4.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{	if(trick==0)
					{
					Toast.makeText(getApplicationContext(), "Please Capture yourself!!",Toast.LENGTH_LONG).show();
					}
			else{
				
			
				
				
				
				
				EditText e1=(EditText)findViewById(R.id.newusereditText1);
				 name=e1.getText();
				 
				 
					 
				 
				 pid=me;
				 //Toast.makeText(getApplicationContext(), name, Toast.LENGTH_LONG).show();
				
				 	if(ro1.isChecked()==true)
				 			{
				 				gender="Male";
						
				 			}
					else if(ro2.isChecked()==true)
							{
								gender="Female";
					
							}
					else
					{
						gender="Male";
					}
				 	/**
				 	 * opening the database and entering the user's details in it
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
			        values.put("name",name.toString());
			        values.put("gender", gender);
			        //Toast.makeText(getApplicationContext(),, Toast.LENGTH_LONG).show();
			        values.put("pid",byteArray);
			        if(name.toString().equals(""))
					 {
						 Toast.makeText(getApplicationContext(), "Name field cannot be empty",Toast.LENGTH_LONG).show();
						 e1.requestFocus();
					 }
			        else
			        {
			        db.insert("profiles",null,values);
			        //Toast.makeText(getApplicationContext(), "success",Toast.LENGTH_LONG).show();
			        Intent i = new Intent(Newuser.this, Bet.class);
					startActivity(i); 
					 finish();
			        }
			
				 	}
			}});
        
		
        
        /**
         * button for capturing the user's picture, calling the camera function
         */
        bt5=(Button)findViewById(R.id.newuserbutton1);
		bt5.setOnClickListener (new OnClickListener()
		{
			public void onClick(View v)
			{	trick=5;
				 i=(ImageView)findViewById(R.id.imageView1);
				 /**
				  * this intent opens the camera function
				  */
				 cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
				startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);

			}
		});
	
	}
	/**
	 * function that defines what to do when the picture is taken
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{   super.onActivityResult(requestCode, resultCode, data);
	if (resultCode == RESULT_CANCELED) {
		Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
		return;
	}
	
	if (requestCode == CAMERA_PIC_REQUEST) {   
		    	/**
		    	 * converting the captured image into a bitmap
		    	 */
		    	thumbnail = (Bitmap) data.getExtras().get("data");
		    	
		    	//trying to store in db
		     baos = new ByteArrayOutputStream();
		    	//thumbnail.compress(Bitmap.CompressFormat.PNG, 100, baos);
		    	//byteArray=baos.toByteArray();
		    	/**
		    	 * compressing the image to a PNG  format
		    	 */
		     thumbnail.compress(CompressFormat.PNG, 0 /*ignored for PNG*/, baos); 
		   /**
		    * converting the image into a byte array
		    */
		     byteArray = baos.toByteArray();	
		     
		    	String s=byteArray.toString();
		        //Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
		       
		    	

		    	
		    	/**
		    	 * inserting image into database 
		    	 */
		    	/*  int width = thumbnail.getWidth();
		          int height = thumbnail.getHeight();
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
		          Bitmap resizedBitmap = Bitmap.createBitmap(thumbnail, 0, 0, 
		                            width, height, matrix, true); */


/**
 * setting the image for preview and inserting the image into the sdcard
 */
		    	i.setImageBitmap(thumbnail);
		    	MediaStore.Images.Media.insertImage(getContentResolver(), thumbnail, null, null);
				bt5.setClickable(false);
					   
	}   
		}  


}
