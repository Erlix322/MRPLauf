package org.brandt.mrplauf.entities;

public class JsonStep {

	
	String paid;
	String text;
	int duration;
	String start_date;
	String parent;
	
	public JsonStep(String id,String text,int duration,String start_date, String parent) {
		this.paid = id;
		this.text = text;
		this.duration = duration;
		this.start_date = start_date;
		this.parent = parent;
	}
	
}
