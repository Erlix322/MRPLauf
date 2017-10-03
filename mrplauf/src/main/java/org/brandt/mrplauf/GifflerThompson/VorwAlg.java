package org.brandt.mrplauf.GifflerThompson;

import static org.hamcrest.CoreMatchers.hasItems;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import org.brandt.mrplauf.MrplaufApplication;
import org.brandt.mrplauf.entities.Produktionsauftrag;
import org.brandt.mrplauf.entities.Schritt;
import org.brandt.mrplauf.repositories.ProduktionsAuftragRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VorwAlg {
	
	private static final Logger log = LoggerFactory.getLogger(MrplaufApplication.class);

	
	@Autowired
	ProduktionsAuftragRepository auftrage;
	
	
	List<Produktionsauftrag> auftrag;
	List<Schritt> tmpSchritte;
	
	public VorwAlg() {
		log.info("Start");
		
				
	}
	
	private void initDate() {
		tmpSchritte.forEach(x->{
			x.setStart(LocalDate.now());
		});
	}
	
	public List<Schritt> getVorwListe() {
		
		auftrag = (List<Produktionsauftrag>) auftrage.findAll();
		List<Schritt> list = new ArrayList<Schritt>();
		calcFAZ();
		
		
		return list;
	}
	
	//Berechnung der frühsten Anfangszeitpunkte
	private void calcFAZ() {
		tmpSchritte = 	auftrag.get(0).getAp().getSchritte();
		initDate();
		
		//Berechne FAZ
		tmpSchritte.forEach(i -> {
			if(i.getParents() == null)i.setStart(LocalDate.now());
			else calcMaxParentSumme(i);		
		});
		
		//Berechne FEZ
		tmpSchritte.forEach(i->{
			i.setEnde(i.getStart().plusDays(i.getDauer()));
		});
		
		tmpSchritte.forEach(x->log.info(x.getName()+"FAZ"+x.getStart()+"FEZ"+x.getEnde()+"\n"));
		
	}
	
	//Summe der Vorgaenger
	private void calcMaxParentSumme(Schritt s) {
		StringTokenizer st = new StringTokenizer(s.getParents(),";");
		
		//SID der Parentschritte
		List<Integer> ids = new ArrayList();
	     while (st.hasMoreTokens()) {
	    	 String token = st.nextToken();
	         ids.add(Integer.parseInt(token));	         
	     }	    
	     
	    
	     //MaxEnde der Parents suchen
	     
	     Schritt step = tmpSchritte.stream().filter(x-> ids.contains(x.getID()))
	     .max((curr,prev) ->  Math.max(curr.getDauer(), prev.getDauer()))
	     .get();
	     
	    /*
	     tmpSchritte.stream().filter(x->x.getID() == step.getID())
	    	.findFirst()
	    	.get()
	    	.setEnde(step.getEnde().plusDays(s.getDauer()));
	     */
	     
	     //Set FAZ
	     tmpSchritte.stream().filter(x->x.getID() == s.getID())
	    	.findFirst()
	    	.get()
	    	.setStart(step.getStart().plusDays(step.getDauer()));
	     
	     	     
	}
	
	
	
	
}
