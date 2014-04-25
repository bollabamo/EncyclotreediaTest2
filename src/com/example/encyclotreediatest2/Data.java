package com.example.encyclotreediatest2;

public class Data {
	int _id;
	String _title;
	String _trail;
	String _quickfacts;
	String _extratext;
	
	public Data(){
	}
	
	public Data(int id, String title, String trail, String quickfacts, String extratext){
		this._id = id;
		this._title = title;
		this._trail = trail;
		this._quickfacts = quickfacts;
		this._extratext = extratext;
	}
	
	public Data(String title, String trail, String quickfacts, String extratext){
		this._title = title;
		this._trail = trail;
		this._quickfacts = quickfacts;
		this._extratext = extratext;
	}
	
	public int getId(){
		return this._id;
	}
	
	public void setId(int id){
		this._id = id;
	}
	
	public String getTitle(){
		return this._title;
	}
	
	public void setTitle(String title){
		this._title = title;
	}
	
	public String getTrail(){
		return this._trail;
	}
	
	public void setTrail(String trail){
		this._trail = trail;
	}
	
	public String getQuickFacts(){
		return this._quickfacts;
	}
	
	public void setQuickFacts(String quickfacts){
		this._quickfacts = quickfacts;
	}
	
	public String getExtraText(){
		return this._extratext;
	}
	
	public void setExtraText(String extratext){
		this._extratext = extratext;
	}
	
	public String __toString(){
		return this._title;
	}


}



