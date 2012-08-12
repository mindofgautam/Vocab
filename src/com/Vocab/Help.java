package com.Vocab;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class Help extends Activity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        TextView sit=(TextView)findViewById(R.id.helptextView1);
        Typeface font1=Typeface.createFromAsset(getAssets(), "CANDARA.TTF");
        sit.setTypeface(font1);

}
}
