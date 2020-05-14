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


public class AccountantRegistration extends Activity implements OnClickListener{
	EditText Name,Workinghours,EmailId,Password,Mobile;
	Button Submit;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accountant_registration);

        Name=(EditText)findViewById(R.id.editText1);
        Workinghours=(EditText)findViewById(R.id.editText2);
        EmailId=(EditText)findViewById(R.id.editText3);
        Password=(EditText)findViewById(R.id.editText4);
        Mobile=(EditText)findViewById(R.id.editText5);
        //Br=(EditText)findViewById(R.id.editText8);
        Submit=(Button)findViewById(R.id.button1);
        Submit.setOnClickListener(this);
        db=openOrCreateDatabase("MEDITRACKERDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS Accountant(Name VARCHAR ,Workinghours VARCHAR,EmailId VARCHAR PRIMARY KEY,Password VARCHAR, Mobile NUMBER);");
	}
	public void onClick(View ad)
    {
    	if(ad==Submit)
    	{
    		if(Name.getText().toString().trim().length()==0||
    		   Workinghours.getText().toString().trim().length()==0||
    		   EmailId.getText().toString().trim().length()==0||
    		   Password.getText().toString().trim().length()==0||
    		   Mobile.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter all values");
    			return;
    		}
    		else if(Mobile.getText().toString().length()!=10){
				  Toast.makeText(AccountantRegistration.this, "Enter 10 digits",Toast.LENGTH_LONG).show();
			  }else{
    		db.execSQL("INSERT INTO Accountant VALUES('"+Name.getText().toString()+"','"+Workinghours.getText().toString()+"','"+EmailId.getText().toString()+
    				   "','"+Password.getText().toString()+"','"+Mobile.getText()+"');");
    		showMessage("Success", "Accountant Registered Successfully");
    		clearText();
			  }
    	}
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.accountant_registration, menu);
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
    	Workinghours.setText("");
    	EmailId.setText("");
    	Password.setText("");
    	Mobile.setText("");
    	//Br.setText("");
    	EmailId.requestFocus();
    	
    }

}

