package com.Vocab;



import java.util.Locale;
import java.util.Random;



import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
//import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.RemoteViews;
import android.widget.Toast;
/**
 * 
 * @author KK88655
 *Class that defines the functionalities of the widget
 */
public class MyWidget extends AppWidgetProvider {
	
	/**
	 * function that defines what the widget does when it is updated
	 */
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
	int[] appWidgetIds) {
		

		/**
		 * Starting a service that defines the onupdate function
		 */
		 context.startService(new Intent(context, UpdateService.class));    
	}
	
	
	/**
	 * 
	 * @author KK88655
	 *Class that is a service which get the word to be displayed
	 */
	public static class UpdateService extends Service {
		@Override
		public void onStart(Intent intent, int startId) {
			RemoteViews updateViews = buildUpdate(this);	
			
		
		// Push update for this widget to the home screen
		ComponentName thisWidget = new ComponentName(this, MyWidget.class);
		AppWidgetManager manager = AppWidgetManager.getInstance(this);
		manager.updateAppWidget(thisWidget, updateViews);
		}

		
		public RemoteViews buildUpdate(Context context) 
		{
			//Resources res = context.getResources();
			RemoteViews updateViews = new RemoteViews(this.getPackageName(),
					
					R.layout.widget_layout);
							
					/**
					 * opening the database and retrieving the word to be displayed on the widget
					 */
					try{
					 SQLiteDatabase db;
				        db = openOrCreateDatabase(
				       		"vocab.db"
				       		, SQLiteDatabase.CREATE_IF_NECESSARY
				       		, null
				      		);
				        db.setVersion(1);
				        db.setLocale(Locale.getDefault());
				        db.setLockingEnabled(true);
				        Random randomGenerator2 = new Random();
				        int bb = randomGenerator2.nextInt(866);
				        //Toast.makeText(getApplicationContext(), String.valueOf(bb),Toast.LENGTH_LONG).show();
				        Cursor cur=db.query("words",null,null,null,null,null,null);
				        cur.moveToFirst();
				        cur.moveToPosition(bb);
				        String a=cur.getString(1);
				        String b=cur.getString(2);
				        //Toast.makeText(getApplicationContext(), a,Toast.LENGTH_LONG).show();
				        updateViews.setTextViewText(R.id.widgetword,a.toUpperCase());
				        updateViews.setTextViewText(R.id.widgetmeaning,b);
				        Intent intent5=new Intent(context,Search1.class);
						 Bundle bundle = new Bundle(); 
						 bundle.putString("wo",a );
						 intent5.putExtras(bundle);
						 intent5.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					/**
					 * setting the onclick activity for the widget	 
					 */
						PendingIntent pendingIntent = PendingIntent.getActivity(context,0, intent5, PendingIntent.FLAG_CANCEL_CURRENT );                
						updateViews.setOnClickPendingIntent(R.id.widget, pendingIntent);
					db.close();
					}
					catch(Exception e)
					{
						 Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
						 Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
						 Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
						 Toast.makeText(getApplicationContext(), e.toString(),Toast.LENGTH_LONG).show();
					}
					
					
					return updateViews;
			
		}
		@Override
		public IBinder onBind(Intent intent) {
			// TODO Auto-generated method stub
			return null;
		}
	




}
}
