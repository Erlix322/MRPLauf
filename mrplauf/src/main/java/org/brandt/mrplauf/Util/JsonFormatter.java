package org.brandt.mrplauf.Util;

import java.util.List;

import org.brandt.mrplauf.entities.Produktionsauftrag;
import org.json.JSONArray;



public class JsonFormatter {

	public JsonFormatter() {}
	public  JsonFormatter(List<Produktionsauftrag> paList) {
		
	}

	/*data:[
	      {
	        "id": 1,
	        "text": "Zuschnitt"
	      },
	      {
	        "id": 1.1,
	        "text": "Platte sägen",
	        "duration": 2,
	        "start_date": "03-08-2017",
	        "parent": 1
	      },
	      {
	        "id": 1.2,
	        "text": "Platte2 sägen",
	        "duration": 2,
	        "start_date": "05-08-2017",
	        "parent": 1
	      }
	  ]
	*/
	public String format(List<Produktionsauftrag> list) {
		JSONArray ja = new JSONArray();
		
		for(Produktionsauftrag pa : list) {
			
		}
		
		
		return null;		
	}
	
	
}
