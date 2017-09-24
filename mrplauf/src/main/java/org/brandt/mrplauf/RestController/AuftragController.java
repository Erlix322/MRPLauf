package org.brandt.mrplauf.RestController;

import java.util.List;
import java.util.stream.Collectors;

import org.brandt.mrplauf.GifflerThompson.GifflerAlg;
import org.brandt.mrplauf.entities.Produktionsauftrag;
import org.brandt.mrplauf.entities.Schritt;
import org.brandt.mrplauf.repositories.ProduktionsAuftragRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuftragController {

	@Autowired
	ProduktionsAuftragRepository auftraege;
	
	@Autowired
	GifflerAlg giffler;
	
	@RequestMapping("/auftr�ge/{id}")
	public Iterable<Produktionsauftrag> getProduktionsauftraege(@PathVariable("id") int id) {
		
		List<Produktionsauftrag> list = (List<Produktionsauftrag>) auftraege.findAll();
		return 
				
		list.stream()
		.filter(x -> x.getID() == id)
		.collect(Collectors.toList());	
		
	}
	
	@RequestMapping("/giffler")
	public Iterable<Schritt> getGiffler() {		
		giffler.planeZeit();
		return giffler.getList();	
	}
}
