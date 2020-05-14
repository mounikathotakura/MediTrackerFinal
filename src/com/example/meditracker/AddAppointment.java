package com.example.meditracker;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class AddAppointment extends Activity implements OnClickListener{
	EditText Date,Day,Time,Doctorname,Patientname;
	Button Booked,Delete;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_add_appointment);
       Date=(EditText)findViewById(R.id.editText1);
        Day=(EditText)findViewById(R.id.editText2);
        Time=(EditText)findViewById(R.id.editText3);
        Doctorname=(EditText)findViewById(R.id.editText4);
        Patientname=(EditText)findViewById(R.id.editText5);
        //Br=(EditText)findViewById(R.id.editText8);
        Booked=(Button)findViewById(R.id.button1);
        Booked.setOnClickListener(this);
        Delete=(Button)findViewById(R.id.button2);
        Delete.setOnClickListener(this);
        db=openOrCreateDatabase("MEDITRACKERDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS Appointment(Date NUMBER,Day VARCHAR,Time NUMBER,Doctorname VARCHAR,Patientname VARCHAR);");
	}
	public void onClick(View ad)
    {
    	if(ad==Booked)
    	{
    		if(Date.getText().toString().trim().length()==0||
    		   Day.getText().toString().trim().length()==0||
    		   Time.getText().toString().trim().length()==0||
    		   Patientname.getText().toString().trim().length()==0||
    		   Doctorname.getText().toString().trim().length()==0)
    		   
    		{
    			showMessage("Error", "Please enter all values");
    			return;
    		}
    
    		db.execSQL("INSERT INTO Appointment VALUES('"+Date.getText()+"','"+Day.getText().toString()+"','"+Time.getText()+"','"+Doctorname.getText().toString()+"','"+Patientname.getText().toString()+"');");
    		showMessage("Success", "Appointment booked Successfully");
    		clearText();
			  }
    	
    	if(ad==Delete)
    	{
    		if(Date.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter date");
    			return;
    		}
    		Cursor c=db.rawQuery("SELECT * FROM Appointment WHERE Date='"+Date.getText()+"'", null);
    		if(c.moveToFirst())
    		{
    			db.execSQL("DELETE FROM Appointment WHERE Date='"+Date.getText()+"'");
    			showMessage("Success", "Record Deleted");
    		}
    		else
    		{
    			showMessage("Error", "Invalid date");
    		}
    		clearText();
    		}
    	
    		

    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_appointment, menu);
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
    	Date.setText("");
    	Day.setText("");
    	Time.setText("");
    	Doctorname.setText("");
    	Patientname.setText("");
    }
    }