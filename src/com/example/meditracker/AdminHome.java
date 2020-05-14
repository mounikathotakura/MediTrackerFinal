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
import android.widget.EditText;

public class AdminHome extends Activity {
	Button Logout,Viewpatient,Viewemployee,Viewaccountant,Viewdoctor,Delete;
	EditText name;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_home);
		Logout=(Button)findViewById(R.id.button1);
		Viewpatient=(Button)findViewById(R.id.button3);
		Viewemployee=(Button)findViewById(R.id.button5);
		Viewaccountant=(Button)findViewById(R.id.button4);
		Viewdoctor=(Button)findViewById(R.id.button2);
db=openOrCreateDatabase("MEDITRACKERDB", Context.MODE_PRIVATE, null);
		
		Logout.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(AdminHome.this,Login.class);
				startActivity(ad);
				finish();
			}
        	 });
		
		Viewpatient.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				
		    		Cursor c=db.rawQuery("SELECT * FROM Patient", null);
		    		if(c.getCount()==0)
		    		{
		    			showMessage("Error", "No records found");
		    			return;
		    		}
		    		StringBuffer buffer=new StringBuffer();
		    		while(c.moveToNext())
		    		{
		    			buffer.append("Name: "+c.getString(0)+"\n");
		    			buffer.append("Disease: "+c.getString(2)+"\n");
		    			buffer.append("Emailid: "+c.getString(3)+"\n");
		    			buffer.append("Mobilenumber: "+c.getString(5)+"\n");
		    			buffer.append("Dateofadmission: "+c.getString(6)+"\n");
		    			buffer.append("------------------------\n");
		    			
		    		}
		    		showMessage("Patient Details", buffer.toString());
			}
        	 });
	
	Viewemployee.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v) {
			
	    		Cursor c=db.rawQuery("SELECT * FROM Employee", null);
	    		if(c.getCount()==0)
	    		{
	    			showMessage("Error", "No records found");
	    			return;
	    		}
	    		StringBuffer buffer=new StringBuffer();
	    		while(c.moveToNext())
	    		{
	    			buffer.append("Name: "+c.getString(0)+"\n");
	    			buffer.append("Designation: "+c.getString(1)+"\n");
	    			buffer.append("Workinghours: "+c.getString(1)+"\n");
	    			buffer.append("Emailid: "+c.getString(1)+"\n");
	    			buffer.append("Mobilenumber: "+c.getString(1)+"\n");
	    			buffer.append("-----------------------\n");
	    			
	    		}
	    		showMessage("Employee Details", buffer.toString());
		}
    	 });
	Viewdoctor.setOnClickListener(new OnClickListener(){
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
	Viewaccountant.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v) {
			
	    		Cursor c=db.rawQuery("SELECT * FROM Accountant", null);
	    		if(c.getCount()==0)
	    		{
	    			showMessage("Error", "No records found");
	    			return;
	    		}
	    		StringBuffer buffer=new StringBuffer();
	    		while(c.moveToNext())
	    		{
	    			buffer.append("Name: "+c.getString(0)+"\n");
	    			buffer.append("Workinghours: "+c.getString(1)+"\n");
	    			buffer.append("Emailid: "+c.getString(2)+"\n");
	    			buffer.append("Mobilenumber: "+c.getString(4)+"\n");
	    			buffer.append("------------------------\n");
	    			
	    		}
	    		showMessage("Accountant Details", buffer.toString());
		}   
		});
	}
		public void onClick(View v) {

	//if(v==Delete)
	{
		Cursor c=db.rawQuery("SELECT * FROM Patient", null);
		if(name.getText().toString().trim().length()==0)
		{
			showMessage("Error", "Please enter viewmodule");
			return;
		}
		Cursor c1=db.rawQuery("SELECT * FROM Patient WHERE name='"+name.getText()+"'", null);
		if(c.moveToFirst())
		{
			db.execSQL("DELETE FROM Patient WHERE name='"+name.getText()+"'");
			showMessage("Success", "Record Deleted");
		}
		else
		{
			showMessage("Error", "Invalid name");
		}
		clearText();}
    }
	
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_home, menu);
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
