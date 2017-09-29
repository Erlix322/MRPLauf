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
		ct = new HashMap();
		JSONObject wrapper = new JSONObject();
	
		JSONArray ja = new JSONArray();
	    JSONObject jo;
		for(Schritt job : list) {	
			jo = new JSONObject();
			String ressId = getRessID(job.getRessource().getID());
			String text = "PA: " +job.paid;
			int duration = job.getDauer();
			String start_date = job.getStart().format(formatter);
			String parent = job.getRessource().getID()+"";
			JsonStep st = new JsonStep(ressId,text,duration,start_date,parent);
			jo.put("id", ressId);
			jo.put("text", text);
			jo.put("duration", duration);
			jo.put("start_date", start_date);
			jo.put("parent", parent);
			ja.put(jo);
		}		
		//add machines
		for(int key : ct.keySet()) {
			JSONObject jk = new JSONObject();
			jk.put("id", key);
			jk.put("text", "Machine: " + key);
			ja.put(jk);
		}
		
		wrapper.put("data", ja);
		
		return  wrapper.toString();		
	}
	
	private String getRessID(int ressId) {
		String ret;
		if(ct.containsKey(ressId)) {
			ct.put(ressId, ct.get(ressId) + 1);
		}else {
			ct.put(ressId, 0);
		
		}
		
		return ressId+"."+ct.get(ressId).toString();
	}
	
	
}
