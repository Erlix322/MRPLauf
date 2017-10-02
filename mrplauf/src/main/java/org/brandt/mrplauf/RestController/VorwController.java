package org.brandt.mrplauf.RestController;

import org.brandt.mrplauf.GifflerThompson.VorwAlg;
import org.brandt.mrplauf.repositories.ProduktionsAuftragRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VorwController {
	
	
	@Autowired
	VorwAlg algorithm;
	
	@RequestMapping("/vorw")
	public String vorw() {
		
		algorithm.getVorwListe();
		return "TODO";
	}
}
