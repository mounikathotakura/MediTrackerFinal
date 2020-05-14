package com.example.meditracker;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity {
	EditText Email,Pwd;
	Button Submit;
	 String u;
	 String p;
	 SQLiteDatabase db;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final GlobalClass globalvariabel = (GlobalClass)getApplicationContext();
		setContentView(R.layout.activity_login);
		Email = (EditText) findViewById(R.id.editText1);
		Pwd= (EditText) findViewById(R.id.editText2);
		Submit=(Button) findViewById(R.id.button1);
		Submit.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub			
				if(Email.getText().toString().equals("")||Pwd.getText().toString().equals("")){
					
					Toast.makeText(Login.this, "Please enter the correct fields..!", Toast.LENGTH_LONG).show();
				}else{	 
					 u = Email.getText().toString();
					 p = Pwd.getText().toString();
					 try{
					          db=openOrCreateDatabase("MEDITRACKERDB",SQLiteDatabase.CREATE_IF_NECESSARY,null);
					          db.execSQL("CREATE TABLE IF NOT EXISTS Appointment(Date NUMBER,Day VARCHAR,Time NUMBER,Doctorname VARCHAR,Patientname VARCHAR);");
					          db.execSQL("CREATE TABLE IF NOT EXISTS Employee(Name VARCHAR ,Designation VARCHAR,Workinghours VARCHAR,EmailId VARCHAR PRIMARY KEY,Password VARCHAR, Mobile NUMBER);");
					          db.execSQL("CREATE TABLE IF NOT EXISTS Patient(Name VARCHAR ,Age NUMBER,Disease VARCHAR,EmailId VARCHAR PRIMARY KEY,Password VARCHAR, Mobile NUMBER,Dateofadmission NUMBER);");
					          db.execSQL("CREATE TABLE IF NOT EXISTS Doctor(Name VARCHAR ,Designation VARCHAR,EmailId VARCHAR PRIMARY KEY,Password VARCHAR, Mobile NUMBER,Workinghours VARCHAR,Consulationfees NUMBER);");
					          db.execSQL("CREATE TABLE IF NOT EXISTS Accountant(Name VARCHAR ,Workinghours VARCHAR,EmailId VARCHAR PRIMARY KEY,Password VARCHAR, Mobile NUMBER);");
					          db.execSQL("CREATE TABLE IF NOT EXISTS Bil(Makesummary VARCHAR);");
					        }catch(Exception exception){
					            exception.printStackTrace();
					        }try{
					        	 Cursor cc = db.rawQuery("select * from Doctor where EmailId = '"+u+"' and Password = '"+p+"' ", null);
					        	 Cursor cc1 = db.rawQuery("select * from Patient where EmailId = '"+u+"' and Password = '"+p+"' ", null);
					        	 Cursor cc2 = db.rawQuery("select * from Accountant where EmailId = '"+u+"' and Password = '"+p+"' ", null);					        	 
					        	 Cursor cc3 = db.rawQuery("select * from Employee where EmailId = '"+u+"' and Password = '"+p+"' ", null);					        	 
					        	 if(Email.getText().toString().equals("admin")&& Pwd.getText().toString().equals("admin")){
					        		 Toast.makeText(Login.this, "Welcome To Admin Home Page "  + u , Toast.LENGTH_LONG).show();
					            		globalvariabel.SetUserName(Email.getText().toString().trim());
					    				globalvariabel.SetPassword(Pwd.getText().toString().trim());
					    				Intent i = new Intent(Login.this,AdminHome.class);
					            		startActivity(i);
									}
					        	 // Doctor Login
					        	 else if(cc.moveToFirst())
					        		 {String temp="";					       
						            if (cc != null) {
						            	if(cc.getCount() > 0)
						            	{
						            	//return true;
						            scan g=new scan();
						            g.execute();
						            
						            		Toast.makeText(Login.this, "Welcome To doctor login Page "  + u , Toast.LENGTH_LONG).show();
						            		globalvariabel.SetUserName(Email.getText().toString().trim());
						    				globalvariabel.SetPassword(Pwd.getText().toString().trim());	
						            		Intent i = new Intent(Login.this,DoctorHome.class);
						            		startActivity(i);
						            		clearText();
						            	}
						            	else
						            	{
						            		 Toast.makeText(Login.this, "Login Fails..!", Toast.LENGTH_LONG).show();
						            	}
						            	}
					        		 }
					        	 	//patient login				        	 
					        	 else if(cc1.moveToFirst())
				        		 {String temp="";					       
					            if (cc1 != null) {
					            	if(cc1.getCount() > 0)
					            	{
					            	//return true;
					            scan g=new scan();
					            g.execute();
					            
					            		Toast.makeText(Login.this, "Welcome To patient login Page "  + u , Toast.LENGTH_LONG).show();
					            		globalvariabel.SetUserName(Email.getText().toString().trim());
					    				globalvariabel.SetPassword(Pwd.getText().toString().trim());	
					            		Intent i = new Intent(Login.this,PatientHome.class);
					            		startActivity(i);
					            		clearText();
					            	}
					            	else
					            	{
					            		 Toast.makeText(Login.this, "Login Fails..!", Toast.LENGTH_LONG).show();
					            	}
					            	}
				        		 }
					        	 
					        	//accountant login				        	 
					        	 else if(cc2.moveToFirst())
				        		 {String temp="";					       
					            if (cc2 != null) {
					            	if(cc2.getCount() > 0)
					            	{
					            	//return true;
					            scan g=new scan();
					            g.execute();
					            
					            		Toast.makeText(Login.this, "Welcome To accountant login Page "  + u , Toast.LENGTH_LONG).show();
					            		globalvariabel.SetUserName(Email.getText().toString().trim());
					    				globalvariabel.SetPassword(Pwd.getText().toString().trim());	
					            		Intent i = new Intent(Login.this,AccountantHome.class);
					            		startActivity(i);
					            		clearText();
					            	}
					            	else
					            	{
					            		 Toast.makeText(Login.this, "Login Fails..!", Toast.LENGTH_LONG).show();
					            	}
					            	}
				        		 }
					        	 
					        	//employee login				        	 
					        	 else if(cc3.moveToFirst())
				        		 {String temp="";					       
					            if (cc3 != null) {
					            	if(cc3.getCount() > 0)
					            	{
					            	//return true;
					            scan g=new scan();
					            g.execute();
					            
					            		Toast.makeText(Login.this, "Welcome To employee login Page "  + u , Toast.LENGTH_LONG).show();
					            		globalvariabel.SetUserName(Email.getText().toString().trim());
					    				globalvariabel.SetPassword(Pwd.getText().toString().trim());	
					            		Intent i = new Intent(Login.this,EmployeeHome.class);
					            		startActivity(i);
					            		clearText();
					            	}
					            	else
					            	{
					            		 Toast.makeText(Login.this, "Login Fails..!", Toast.LENGTH_LONG).show();
					            	}
					            	}
				        		 }
					        	 
					        }catch(Exception exception){
					            exception.printStackTrace();
					        }
						}	 	
				}
		});
	}
	 	

	public class scan extends AsyncTask<String, String, String>{

		private ProgressDialog pd;

		protected void onPreExecute() {
			super.onPreExecute();
		 pd = new ProgressDialog(Login.this);
		 pd.setTitle("Please Wait");
		 pd.setMessage("Logging....");
		 pd.setMax(200);
		 pd.show();
		}
				
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
	
}
	 public void clearText()
	    {
	    	Email.setText("");
	    	Pwd.setText("");
	    	
	    	
	    }
}
	
	
