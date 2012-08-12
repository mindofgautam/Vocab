package com.Vocab;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
//import android.widget.Toast;
import android.widget.Toast;
/**
 * 
 * @author KK88655
 *Class that allows the user to select the difficulty level of the test
 */
public class Difflevel extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difflevel);
        
        TextView sit=(TextView)findViewById(R.id.selectleveltextView1);
        Typeface font1=Typeface.createFromAsset(getAssets(), "CANDARA.TTF");
        sit.setTypeface(font1);
        
        Bundle bundle=getIntent().getExtras();
        final String s=bundle.getString("un");
       // Toast.makeText(getApplicationContext(), s,Toast.LENGTH_LONG).show();
        /**
         * defining the radio group and individual radio buttons
         */
        RadioGroup rr=(RadioGroup)findViewById(R.id.rg1);
        RadioButton rb1=(RadioButton)findViewById(R.id.lev1);
        RadioButton rb2=(RadioButton)findViewById(R.id.lev2);
        RadioButton rb3=(RadioButton)findViewById(R.id.lev3);
        
        
        /**
         * action script for the radio group
         */
        rr.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

        	@Override
        	public void onCheckedChanged(RadioGroup group, int checkedId) {
        		int g;
        		/**
        		 * getting the id of the radio button that is selected
        		 */
        	int tmp = (Integer) findViewById(checkedId).getId();
        	//Toast.makeText(getApplicationContext(), String.valueOf(tmp),Toast.LENGTH_LONG).show();
        	
        	/**
        	 * easy, moderate and hard
        	 */
        	if(tmp==2131230752)
        	{
        		g=25;
        	}
        	else if(tmp==2131230753)
        	{
        		g=20;
        	}
        	else
        	{
        		g=15;
        	}
        	
        	/**
        	 * sending the difficulty level and moving to the actual test class
        	 */
        	Intent i = new Intent(Difflevel.this, Test2.class);
			Bundle bundle = new Bundle(); 
			 bundle.putString("un",s);
			 bundle.putInt("un1",g);
			 i.putExtras(bundle);
			startActivity(i); 
        	finish();
        	
        	}

        	});


        
        
	}
}
