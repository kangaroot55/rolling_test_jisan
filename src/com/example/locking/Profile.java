package com.example.locking;

public class Profile {

	String email;
	String name;
	String profileurl;
	
	public Profile(String email, String name, String profileurl){
		this.email= email;
		this.name = name;
		this.profileurl = profileurl;
	}
	
	public String get_email(){
		return email;
	}
	public String get_name(){
		return email;
	}
	public String get_profileurl(){
		return profileurl;
	}
	
}
