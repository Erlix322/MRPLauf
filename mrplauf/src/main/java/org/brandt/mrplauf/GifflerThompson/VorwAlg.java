package org.brandt.mrplauf.GifflerThompson;

import static org.hamcrest.CoreMatchers.hasItems;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
	
	public List<Schritt> getVorwListe() {
		List<Schritt> list = new ArrayList<Schritt>();
		
		
		
		return list;
	}
	
	//Berechnung der frühsten Anfangszeitpunkte
	private void calcFAZ() {
		List<Schritt> schritte = auftrage.findOne(0).getAp().getSchritte();
		//HashMap<int,LocalDate>
		
		schritte.forEach(x -> {
			if(x.getParents() == null)x.setStart(LocalDate.now());
			else calcParentSumme(x, schritte);		
		});	
		
		
	}
	
	//Summe der Vorgaenger
	private void calcParentSumme(Schritt s, List<Schritt> list) {
		StringTokenizer st = new StringTokenizer(s.getParents(),";");
		
		List<Integer> ids = new ArrayList();
	     while (st.hasMoreTokens()) {
	    	 String token = st.nextToken();
	         ids.add(Integer.parseInt(token));	         
	     }	    
	     
	    
	     //Schritte suchen die als Parents im Schritte s stehen.
	     Schritt step = list.stream().filter(x-> ids.contains(x.getID()))
	     .max((curr,prev) -> Math.max(curr.getDauer(), prev.getDauer()))
	     .get();
	     
	     
	     
	     
	         
	}
	
	
	
	
}
