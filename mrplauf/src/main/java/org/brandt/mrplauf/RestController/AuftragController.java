package org.brandt.mrplauf.RestController;

import java.util.List;

import org.brandt.mrplauf.entities.Produktionsauftrag;
import org.brandt.mrplauf.repositories.ProduktionsAuftragRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuftragController {

	@Autowired
	ProduktionsAuftragRepository auftraege;
	
	@RequestMapping("/")
	public Iterable<Produktionsauftrag> getProduktionsauftraege() {
		return auftraege.findAll();
		
	}
}
