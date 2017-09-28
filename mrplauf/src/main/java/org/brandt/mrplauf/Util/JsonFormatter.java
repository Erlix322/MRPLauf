package org.brandt.mrplauf.Util;

import java.io.StringWriter;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.brandt.mrplauf.entities.JsonStep;
import org.brandt.mrplauf.entities.Produktionsauftrag;
import org.brandt.mrplauf.entities.Schritt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;


@Service
public class JsonFormatter {

	
	DateTimeFormatter formatter; 
	Map<Integer,Integer> ct;
	public JsonFormatter() {
		formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		ct = new HashMap();
	}
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
	public String format(List<Schritt> list) throws JSONException {
		JSONArray ja = new JSONArray();
	    JSONObject jo;
		for(Schritt job : list) {	
			jo = new JSONObject();
			String ressId = getRessID(job.getRessource().getID());
			String text = job.paid;
			int duration = job.getDauer();
			String start_date = job.getStart().format(formatter);
			String parent = job.getRessource().getID()+"";
			JsonStep st = new JsonStep(ressId,text,duration,start_date,parent);
			jo.put("id", ressId);
			ja.put(jo);
		}		
		
		return  ja.toString();		
	}
	
	private String getRessID(int ressId) {
		if(ct.containsKey(ressId)) {
			ct.put(ressId, ct.get(ressId) + 1);
		}else {
			ct.put(ressId, ressId);
		}
		
		return ct.get(ressId).toString();
	}
	
	
}
