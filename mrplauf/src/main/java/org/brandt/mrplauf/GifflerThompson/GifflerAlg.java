package org.brandt.mrplauf.GifflerThompson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.brandt.mrplauf.MrplaufApplication;
import org.brandt.mrplauf.entities.Arbeitsplan;
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
	List<Schritt> finalSchritt;
	
	@Autowired
	ProduktionsAuftragRepository auftraege;
	
	@Autowired 
	RessourceRepository ressourcen;
	
	public GifflerAlg() {
		maschines = new HashMap<Integer, LocalDate>();
		finalSchritt = new ArrayList();
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
		
		
		while(auftrag.size() != 0) {
			
			List<Schritt> S = getFirstJobs();
			
			
			while(S.size() != 0) {
				
				List<Ressource> R = getRessourcesByStep(S);
				
				while(R.size() != 0) {
					Ressource firstRessource = R.get(0);
				    Schritt operation = S.stream()
				    .filter(x -> x.getRessource().getID() == firstRessource.getID())
				    .collect(Collectors.toList()).get(0);
				    
				    S = saveOperation(operation,S);
				    R = getRessources(S);
				    
				}
				
			}
			
			List<Schritt> prevJobs = getFirstJobs();
			deleteFromAuftrage(prevJobs);
			
			
		}
		
	}
	
	public List<Schritt> getFirstJobs(){
		
		List<Schritt> schrittListe = auftrag.stream()
				.map(x -> {return x.getAp().getSchritte().get(0);})
				.collect(Collectors.toList());
		
		schrittListe.forEach(x -> log.info(x.getName()));
		return schrittListe;
	}
	
	public List<Ressource> getRessourcesByStep(List<Schritt> S){
		
		List<Ressource> R = S.stream()
		.map(x -> x.getRessource())
		.collect(Collectors.toList());		
		return R;		
	}
	
	
	public List<Schritt> saveOperation(Schritt step, List<Schritt> S){
		List<Schritt> tmp = S;
		
		if(step.getParents().isEmpty()) {
			int ressourceID = step.getRessource().getID();
			step.setStart(maschines.get(ressourceID));
			step.setEnde(step.getStart().plusDays(step.getDauer()));
			maschines.put(ressourceID, step.getEnde());
		}else {
			if(MaxParent(step.getParents()).isAfter(maschines.get(step.getRessource().getID())))
				step.setStart(MaxParent(step.getParents()));
			else
				step.setStart(maschines.get(step.getRessource().getID()));
				step.setEnde(step.getStart().plusDays(step.getDauer()));
				maschines.put(step.getRessource().getID(), step.getEnde());
		}
		finalSchritt.add(step);
		tmp.removeIf(x -> x.getID() == step.getID());
		return tmp;
	}
	
	
	public LocalDate MaxParent(List<Schritt> s) {
		
		LocalDate maxDate = LocalDate.MIN;
		for(Schritt schritt : s) {
			LocalDate endDate = schritt.getEnde();
			if(maxDate.isBefore(endDate))
				maxDate = endDate;
		}
		
		return maxDate;
				
	}
	
	
	public List<Ressource> getRessources(List<Schritt> S){
		
		return S.stream()
		.map(x -> x.getRessource())
		.collect(Collectors.toList());
	}
	
	
	public void deleteFromAuftrage(List<Schritt> S) {
		for(Produktionsauftrag auf : auftrag) {
			Arbeitsplan ap = auf.getAp();
			ap.getSchritte().remove(0);
		}
		
		auftrag.removeIf(x -> x.getAp().getSchritte().isEmpty());
	}
	
	
	public List<Schritt> getList(){
		return finalSchritt;
	}
	
	
	
	
	
	
	
	
	
}
