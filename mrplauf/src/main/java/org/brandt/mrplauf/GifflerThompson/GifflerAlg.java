package org.brandt.mrplauf.GifflerThompson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.brandt.mrplauf.MrplaufApplication;
import org.brandt.mrplauf.entities.Produktionsauftrag;
import org.brandt.mrplauf.entities.Ressource;
import org.brandt.mrplauf.entities.Schritt;
import org.brandt.mrplauf.repositories.ProduktionsAuftragRepository;
import org.brandt.mrplauf.repositories.RessourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GifflerAlg {

	
	private static final Logger log = LoggerFactory.getLogger(MrplaufApplication.class);
	
	List<Produktionsauftrag> auftrag;
	List<Produktionsauftrag> TMP;
	HashMap<Integer, LocalDate> maschines;
	
	@Autowired
	ProduktionsAuftragRepository auftraege;
	
	@Autowired 
	RessourceRepository ressourcen;
	
	public GifflerAlg() {
		maschines = new HashMap<Integer, LocalDate>();
		
	}
	
	
	public void initDictionary() {
		List<Ressource> tmpM = (List<Ressource>) ressourcen.findAll();
		
		for(Ressource ress : tmpM) {
			if(!maschines.containsKey(ress.getID()))
				maschines.put(ress.getID(), LocalDate.now());
		}
		
	}
	
	public void initTime() {
		auftrag = (List<Produktionsauftrag>) auftraege.findAll();
		List<Schritt> schrittListe = auftrag.stream()
		.map( x -> {return x.getAp().getSchritte();})
		.flatMap(List::stream).collect(Collectors.toList());
		
		schrittListe.forEach(x -> x.setStart(LocalDate.now()));		
		//schrittListe.forEach(x -> log.info(x.getStart().toString()));
	}
	
	public void planeZeit() {		
		initTime();
		initDictionary();		
	}
	
	public List<Schritt> getFirstJobs(){
		
		List<Schritt> schrittListe = auftrag.stream()
				.map(x -> {return x.getAp().getSchritte().get(0);})
				.collect(Collectors.toList());
		
		return schrittListe;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
