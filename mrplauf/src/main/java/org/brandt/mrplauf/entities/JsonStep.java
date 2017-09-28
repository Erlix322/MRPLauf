package org.brandt.mrplauf.entities;

public class JsonStep {

	
	String ressId;
	String text;
	int duration;
	String start_date;
	String parent;
	
	public JsonStep(String ressId,String text,int duration,String start_date, String parent) {
		this.ressId = ressId;
		this.text = text;
		this.duration = duration;
		this.start_date = start_date;
		this.parent = parent;
	}
	
}
