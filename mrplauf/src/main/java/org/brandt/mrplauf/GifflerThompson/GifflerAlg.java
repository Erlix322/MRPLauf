package org.brandt.mrplauf.GifflerThompson;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.brandt.mrplauf.MrplaufApplication;
import org.brandt.mrplauf.entities.Arbeitsplan;
import org.brandt.mrplauf.entities.JsonStep;
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
	List<Schritt> listTmp;
	
	List<JsonStep> jsonStep;
	
	@Autowired
	ProduktionsAuftragRepository auftraege;
	


	
	@Autowired 
	RessourceRepository ressourcen;
	
	public GifflerAlg() {
		maschines = new HashMap<Integer, LocalDate>();
		finalSchritt = new ArrayList();
		listTmp = new ArrayList();
		jsonStep = new ArrayList();
	}
	
	
	public void initDictionary() {
		List<Ressource> tmpM = (List<Ressource>) ressourcen.findAll();
		
		for(Ressource ress : tmpM) {
			if(!maschines.containsKey(ress.getID()))
				maschines.put(ress.getID(), LocalDate.now());
		}
		
		for(Produktionsauftrag pa : auftrag) {
			for(Schritt s : pa.getAp().getSchritte())
				listTmp.add(s);
		}
		
	}
	
	public void initTime() {
		auftrag = (List<Produktionsauftrag>) auftraege.findAll();
		
		List<Produktionsauftrag> auf = new ArrayList();
		for(Produktionsauftrag pa : auftrag) {
			auf.add(pa.clone());
		}
		
		auftrag = auf;
		
		List<Schritt> schrittListe = auftrag.stream()
		.map( x -> {return x.getAp().getSchritte();})
		.flatMap(List::stream).collect(Collectors.toList());
		listTmp = schrittListe;
		schrittListe.forEach(x -> x.setStart(LocalDate.now()));	
		
		
		//schrittListe.forEach(x -> log.info(x.getStart().toString()));
	}
	
	public void planeZeit() {		
		initTime();
		initDictionary();	
		
		
		while(auftrag.size() != 0) {
		    //auftrag.get(0).getAp().getSchritte().forEach(x->log.info("Schritt " + x.getName()));	
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
			prevJobs.forEach(x -> log.info("First Jobs: "+ x.getName() + "  " + x.paid));
			deleteFromAuftrage(prevJobs);
			
			
		}
		
	}
	
	public List<Schritt> getFirstJobs(){
		
		List<Schritt> schrittListe = auftrag.stream()
				.map(x -> {
					Schritt s = x.getAp().getSchritte().get(0);
					s.paid = x.getID() +"";
					return s;					
				})
				.collect(Collectors.toList());
		
		
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
		
		if(step.getParents() == null) {
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
	
	
	public LocalDate MaxParent(String s) {
		
		LocalDate maxDate = LocalDate.MIN;
		
		StringTokenizer st = new StringTokenizer(s,";");
	
		List<Integer> ids = new ArrayList();
	     while (st.hasMoreTokens()) {
	    	 String token = st.nextToken();
	         ids.add(Integer.parseInt(token));	        	 
	         
	     }
	    
	     
		for(int paid : ids) {
			for(Produktionsauftrag pa : auftrag) {
				List<Schritt> schritte = listTmp;
				Schritt ret = schritte.stream().filter(x->{ return x.getID() == paid;})
						  .findAny()
						  .orElse(null);
				if(ret != null) {
					LocalDate endDate = ret.getEnde();
					if(maxDate.isBefore(endDate))
						maxDate = endDate;
				}
				
			}
			
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
			Schritt s = ap.getSchritte().get(0);
			ap.getSchritte().remove(0);
		}		
		auftrag.removeIf(x -> x.getAp().getSchritte().isEmpty());
	}
	
	
	public List<Schritt> getList(){
		return finalSchritt;
	}
	
	
	
	
	
	
	
	
	
	
}
