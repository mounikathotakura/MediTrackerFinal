package com.example.meditracker;
import android.app.Application;


public class GlobalClass extends Application {
	public String UserName;
	public String Password;
	public String GetUserName()
	{
		return UserName;
	}
	public void SetUserName(String _susername)
	{
		UserName=_susername;
	}
	public String GetPassword()
	{
		return Password;
	}
	public void SetPassword(String _spassword)
	{
		Password=_spassword;;
	}
	


}
