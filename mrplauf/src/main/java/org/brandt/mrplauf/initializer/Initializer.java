package org.brandt.mrplauf.initializer;

import java.util.ArrayList;
import java.util.List;

import org.brandt.mrplauf.MrplaufApplication;
import org.brandt.mrplauf.entities.Arbeitsplan;
import org.brandt.mrplauf.entities.Produkt;
import org.brandt.mrplauf.entities.Produktionsauftrag;
import org.brandt.mrplauf.entities.Ressource;
import org.brandt.mrplauf.entities.Schritt;
import org.brandt.mrplauf.repositories.ArbeitsplanRepository;
import org.brandt.mrplauf.repositories.ProduktRepository;
import org.brandt.mrplauf.repositories.ProduktionsAuftragRepository;
import org.brandt.mrplauf.repositories.RessourceRepository;
import org.brandt.mrplauf.repositories.SchrittRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Initializer {
	
	private static final Logger log = LoggerFactory.getLogger(MrplaufApplication.class);

	@Autowired
	ProduktRepository produkte;
	
	@Autowired
	RessourceRepository ressourcen;
	
	@Autowired 
	ArbeitsplanRepository arbeitsplan;
	
	@Autowired
	ProduktionsAuftragRepository auftraege;
	
	@Autowired
	SchrittRepository Schritt;
	
	public void initialize() {
		initProdukts();
		log.info("Produkte initialisiert!");
		initRessources();
		log.info("Ressourcen initialisiert!");
		
		
		initSchritt();
		log.info("Schritt initialisiert!");
		initArbeitsplan();
		log.info("Arbeitsplan initialisiert!");
		initProduktionsauftraege();
		log.info("Aufträge initialisiert!");
	}

	
	
	private void initSchritt() {
		Schritt.save(new Schritt(1,"Platte sägen",2,null,ressourcen.findOne(1)));
		
		Schritt.save(new Schritt(2,"Platte2 sägen",2,"1",ressourcen.findOne(1)));
	
		Schritt.save(new Schritt(3,"Löcher bohren",5,"1",ressourcen.findOne(2)));
		
		Schritt.save(new Schritt(4,"Tisch montieren",5,"2;3",ressourcen.findOne(3)));
		
		
		//2. Arbeitsplan
		Schritt.save(new Schritt(1002,"Beine sägen",3,null,ressourcen.findOne(1)));
		Schritt.save(new Schritt(1003,"Lattenrost sägen",10,null,ressourcen.findOne(1)));
		Schritt.save(new Schritt(1004,"Korpus sägen",5,null,ressourcen.findOne(1)));
		
		Schritt.save(new Schritt(1005,"Korpus montieren",5,"1003;1004",ressourcen.findOne(3)));
		
		Schritt.save(new Schritt(1006,"Bett montieren",7,"1005;1002",ressourcen.findOne(3)));
		
		
		
	}



	private void initProduktionsauftraege() {
	    auftraege.save(new Produktionsauftrag(1,"Meyer Tische", 3,arbeitsplan.findOne(1)));
	    auftraege.save(new Produktionsauftrag(2,"Brandt Hochbett", 1,arbeitsplan.findOne(2)));	
	}



	private void initArbeitsplan() {
		List<Schritt> l = new ArrayList();
		l.add(Schritt.findOne(1));
		l.add(Schritt.findOne(2));
		l.add(Schritt.findOne(3));
		l.add(Schritt.findOne(4));
		arbeitsplan.save(new Arbeitsplan(1,"Tisch",l));
		
		l = new ArrayList();
		l.add(Schritt.findOne(1002));
		l.add(Schritt.findOne(1003));
		l.add(Schritt.findOne(1004));
		l.add(Schritt.findOne(1005));
		l.add(Schritt.findOne(1006));
		arbeitsplan.save(new Arbeitsplan(2,"Hochbett",l));		
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
