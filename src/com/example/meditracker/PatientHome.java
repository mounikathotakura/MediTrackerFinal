package com.example.meditracker;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
public class PatientHome extends Activity {
	Button Logout,Viewdoctors,addappointment,update;
	SQLiteDatabase db;
	TextView gid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		setContentView(R.layout.activity_patient_home);
		Logout=(Button)findViewById(R.id.button3);
		Viewdoctors=(Button)findViewById(R.id.button1);
		addappointment=(Button)findViewById(R.id.button4);
		update=(Button)findViewById(R.id.button2);
		gid=(TextView)findViewById(R.id.textView3);
		gid.setText(globalvariabel.GetUserName().toString());
	 
		db=openOrCreateDatabase("MEDITRACKERDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS Doctor(Name VARCHAR ,Designation VARCHAR,EmailId VARCHAR PRIMARY KEY,Password VARCHAR, Mobile NUMBER,Workinghours VARCHAR,Consulationfees NUMBER);");
		
		Logout.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(PatientHome.this,Login.class);
				startActivity(ad);
				finish();
			}
        	 });
		Viewdoctors.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
		    		Cursor c=db.rawQuery("SELECT * FROM Doctor", null);
		    		if(c.getCount()==0)
		    		{
		    			showMessage("Error", "No records found");
		    			return;
		    		}
		    		StringBuffer buffer=new StringBuffer();
		    		while(c.moveToNext())
		    		{
		    			buffer.append("Name: "+c.getString(0)+"\n");
		    			buffer.append("Disgnation: "+c.getString(1)+"\n");
		    			buffer.append("Emailid: "+c.getString(2)+"\n");
		    			buffer.append("Mobilenumber: "+c.getString(4)+"\n");
		    			buffer.append("Workinghours: "+c.getString(5)+"\n");
		    			buffer.append("Consultationfees: "+c.getString(6)+"\n");
		    			buffer.append("------------------------\n");
		    			
		    		}
		    		showMessage("Doctor Details", buffer.toString());
			}   
			});

		addappointment.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(PatientHome.this,AddAppointment.class);
				startActivity(ad);
			}
        	 });
		update.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(PatientHome.this,PatientUpdate.class);
				startActivity(ad);
			}
        	 });	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_home, menu);
		return true;
	}
	public void showMessage(String title,String message)
    {
    	Builder builder=new Builder(this);
    	builder.setCancelable(true);
    	builder.setTitle(title);
    	builder.setMessage(message);
    	builder.show();
	}
    public void clearText()
    {
    	
    }

}
