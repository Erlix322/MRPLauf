package org.brandt.mrplauf;


import org.brandt.mrplauf.GifflerThompson.GifflerAlg;
import org.brandt.mrplauf.entities.Produkt;
import org.brandt.mrplauf.entities.Produktionsauftrag;
import org.brandt.mrplauf.entities.Schritt;
import org.brandt.mrplauf.initializer.Initializer;
import org.brandt.mrplauf.repositories.ProduktRepository;
import org.brandt.mrplauf.repositories.ProduktionsAuftragRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class MrplaufApplication {

	
	private static final Logger log = LoggerFactory.getLogger(MrplaufApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MrplaufApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(GifflerAlg giffler, Initializer initializer,ProduktRepository repository,ProduktionsAuftragRepository auftraege) {
		return (args) -> {
			//Initialisiere h2 Datenbank
			initializer.initialize();	
		    

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Produktionsauftrag customer : auftraege.findAll()) {
				log.info("Auftrag: "+customer.getName() + "Arbeitsplan:"+customer.getAp().getName());
				for(Schritt s : customer.getAp().getSchritte()) {
                    log.info("["+s.getID()+"]"+"Schritt: " + s.getName());
					log.info("    "+s.getParents());
					
				}
			}
			
			giffler.planeZeit();
			giffler.getList().forEach(x -> log.info("Ressource:"+x.getRessource().getID()+"ID: " +x.getID() + " Name: " + x.getName() + " Start: " + x.getStart() + " Ende: " + x.getEnde()));
			log.info("");

			// fetch an individual customer by ID
			
		};
	}
}
