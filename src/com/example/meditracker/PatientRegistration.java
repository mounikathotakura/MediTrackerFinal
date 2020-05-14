package com.example.meditracker;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class PatientRegistration extends Activity implements OnClickListener{
	EditText Name,Age,Disease,EmailId,Password,Mobile,Dateofadmission;
	Button Submit;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_registration);


        Name=(EditText)findViewById(R.id.editText1);
        Age=(EditText)findViewById(R.id.editText2);
        Disease=(EditText)findViewById(R.id.editText3);
        EmailId=(EditText)findViewById(R.id.editText4);
        Password=(EditText)findViewById(R.id.editText5);
        Mobile=(EditText)findViewById(R.id.editText6);
        Dateofadmission=(EditText)findViewById(R.id.editText7);
        //Br=(EditText)findViewById(R.id.editText8);
        Submit=(Button)findViewById(R.id.button1);
        Submit.setOnClickListener(this);
        db=openOrCreateDatabase("MEDITRACKERDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS Patient(Name VARCHAR ,Age NUMBER,Disease VARCHAR,EmailId VARCHAR PRIMARY KEY,Password VARCHAR, Mobile NUMBER,Dateofadmission NUMBER);");
	}
	public void onClick(View ad)
    {
    	if(ad==Submit)
    	{
    		if(Name.getText().toString().trim().length()==0||
    		   Age.getText().toString().trim().length()==0||
    	       Disease.getText().toString().trim().length()==0||
    		   EmailId.getText().toString().trim().length()==0||
    		   Password.getText().toString().trim().length()==0||
    		   Mobile.getText().toString().trim().length()==0||
    		   Dateofadmission.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter all values");
    			return;
    		}
    		else if(Mobile.getText().toString().length()!=10){
				  Toast.makeText(PatientRegistration.this, "Enter 10 digits",Toast.LENGTH_LONG).show();
			  }else{
    		db.execSQL("INSERT INTO Patient VALUES('"+Name.getText().toString()+"','"+Age.getText().toString()+"','"+Disease.getText().toString()+ 	
    				"','"+EmailId.getText().toString()+"','"+Password.getText().toString()+"','"+Mobile.getText()+"','"+Dateofadmission.getText()+"');");
    		showMessage("Success", "Patient Registered Successfully");
    		clearText();
			  }
    	}
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_registration, menu);
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
    	Name.setText("");
    	Age.setText("");
    	Disease.setText("");
    	EmailId.setText("");
    	Password.setText("");
    	Mobile.setText("");
    	Dateofadmission.setText("");
    	//Br.setText("");
    	EmailId.requestFocus();
    	
    }
}