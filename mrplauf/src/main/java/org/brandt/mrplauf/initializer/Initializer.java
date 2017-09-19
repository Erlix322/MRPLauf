package org.brandt.mrplauf.initializer;

import org.brandt.mrplauf.entities.Arbeitsplan;
import org.brandt.mrplauf.entities.Produkt;
import org.brandt.mrplauf.entities.Ressource;
import org.brandt.mrplauf.repositories.ArbeitsplanRepository;
import org.brandt.mrplauf.repositories.ProduktRepository;
import org.brandt.mrplauf.repositories.RessourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Initializer {

	@Autowired
	ProduktRepository produkte;
	
	@Autowired
	RessourceRepository ressourcen;
	
	@Autowired 
	ArbeitsplanRepository arbeitsplan;
	
	public void initialize() {
		initProdukts();
		initRessources();
		initArbeitsplan();
		
	}

	
	
	private void initArbeitsplan() {
		arbeitsplan.save(new Arbeitsplan(1,"Tisch"));
		arbeitsplan.save(new Arbeitsplan(2,"Hochbett"));		
	}



	private void initProdukts() {
		produkte.save(new Produkt(1,"Tisch"));
		produkte.save(new Produkt(2,"Stuhl"));
		produkte.save(new Produkt(3,"Schrank"));
	}
	
	
	private void initRessources() {
		ressourcen.save(new Ressource(1,"Zuschnitt"));
		ressourcen.save(new Ressource(2,"CNC-Bohren"));
		ressourcen.save(new Ressource(3,"Montage"));
	}
}
