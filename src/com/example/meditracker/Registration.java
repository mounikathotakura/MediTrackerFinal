package com.example.meditracker;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Registration extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		Button r1,r2,r3,r4;
        r1=(Button)findViewById(R.id.button1);
        r2=(Button)findViewById(R.id.button2);
        r3=(Button)findViewById(R.id.button3);
        r4=(Button)findViewById(R.id.button4);
        
        r1.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(Registration.this,DoctorRegistration.class);
				startActivity(ad);
			}
        	 });
        r2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(Registration.this,PatientRegistration.class);
				startActivity(ad);
			}
        	 });
        r3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(Registration.this,AccountantRegistration.class);
				startActivity(ad);
			}
        	 });
        r4.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
	 			// TODO Auto-generated method stub
				Intent ad= new Intent(Registration.this,EmployeeRegistration.class);
				startActivity(ad);
			}
        	 });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registration, menu);
		return true;
	}

}
