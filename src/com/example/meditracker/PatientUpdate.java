package com.example.meditracker;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.Toast;



public class PatientUpdate extends Activity implements OnClickListener{
	
	EditText patientname,date,day,time,doctorname ;
	Button update;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_update);
	    patientname=(EditText)findViewById(R.id.editText5);
        date=(EditText)findViewById(R.id.editText2);
        day =(EditText)findViewById(R.id.editText1);
        time=(EditText)findViewById(R.id.editText3);
        doctorname=(EditText)findViewById(R.id.editText4);
              update = (Button)findViewById(R.id.button1);
              update.setOnClickListener(this);      
         db=openOrCreateDatabase("MEDITRACKERDB", Context.MODE_PRIVATE, null);
         db.execSQL("CREATE TABLE IF NOT EXISTS Appointment(patientname VARCHAR ,date VARCHAR,day VARCHAR,time VARCHAR,doctorname VARCHAR);");
		}   	

	
public void onClick(View v) {
	

	if(v==update)
	{
		if(patientname.getText().toString().trim().length()==0||
				date.getText().toString().trim().length()==0||
				day.getText().toString().trim().length()==0||
				time.getText().toString().trim().length()==0||
				doctorname.getText().toString().trim().length()==0)
		{
			showMessage("Error", "Please enter all fields");
			return;
		}
		
		else{
		Cursor c=db.rawQuery("SELECT * FROM Appointment WHERE Patientname='"+patientname.getText()+"'", null);
		
		if(c.moveToFirst())
		{
			db.execSQL("UPDATE Appointment SET Date='"+date.getText()+"',Day='"+day.getText()+"',Time='"+time.getText()+
					"',Doctorname='"+doctorname.getText()+"'WHERE Patientname='"+patientname.getText()+"'");
			showMessage("Success", "Record Modified");
			clearText();
		}
		else
		{
			showMessage("Error", "Invalid patientname");
		}
		
	
		}
		
	}
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
	date.setText("");
	day.setText("");
	time.setText("");
	doctorname.setText("");
	patientname.setText("");
	
	
}
}
