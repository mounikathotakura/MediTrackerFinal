package com.example.meditracker;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddBill extends Activity implements OnClickListener{
	EditText Makesummary;
	Button Generate;
	SQLiteDatabase db;
	TextView gid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		setContentView(R.layout.activity_add_bill);
       Makesummary=(EditText)findViewById(R.id.editText1);
        Generate=(Button)findViewById(R.id.button1);
        Generate.setOnClickListener(this);
        gid=(TextView)findViewById(R.id.textView3);
		gid.setText(globalvariabel.GetUserName().toString());
        db=openOrCreateDatabase("MEDITRACKERDB", Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS Bil(Makesummary VARCHAR);");
	}
	public void onClick(View ad)
    {
    	if(ad==Generate)
    	{
    		if(Makesummary.getText().toString().trim().length()==0)
    		{
    			showMessage("Error", "Please enter all values");
    			return;
    		}
    		else{
    		db.execSQL("INSERT INTO Bil VALUES('"+Makesummary.getText().toString()+"');");
    		showMessage("Success", "Bill generated Successfully");
    		clearText();
			  }
    	}
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_bill, menu);
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
    	Makesummary.setText("");
    	
    }
}
