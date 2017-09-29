package org.brandt.mrplauf.RestController;

import java.util.List;
import java.util.stream.Collectors;

import org.brandt.mrplauf.GifflerThompson.GifflerAlg;
import org.brandt.mrplauf.Util.JsonFormatter;
import org.brandt.mrplauf.entities.Produktionsauftrag;
import org.brandt.mrplauf.entities.Schritt;
import org.brandt.mrplauf.repositories.ProduktionsAuftragRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuftragController {

	@Autowired
	ProduktionsAuftragRepository auftraege;
	
	@Autowired
	GifflerAlg giffler;
	
	@Autowired
	JsonFormatter formatter;
	
	@RequestMapping("/auftraege/{id}")
	@CrossOrigin
	public Iterable<Produktionsauftrag> getProduktionsauftraege(@PathVariable("id") int id) {
		
		List<Produktionsauftrag> list = (List<Produktionsauftrag>) auftraege.findAll();
		return 
				
		list.stream()
		.filter(x -> x.getID() == id)
		.collect(Collectors.toList());	
		
	}
	
	@RequestMapping("/giffler")
	@CrossOrigin
	public String getGiffler() throws JSONException {		
		giffler.planeZeit();
		return formatter.format(giffler.getList());	
	}
}
