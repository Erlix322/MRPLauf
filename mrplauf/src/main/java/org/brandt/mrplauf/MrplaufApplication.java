package org.brandt.mrplauf;


import org.brandt.mrplauf.entities.Produkt;
import org.brandt.mrplauf.initializer.Initializer;
import org.brandt.mrplauf.repositories.ProduktRepository;
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
	public CommandLineRunner demo(Initializer initializer,ProduktRepository repository) {
		return (args) -> {
			//Initialisiere Datenbank
			initializer.initialize();	
		

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Produkt customer : repository.findAll()) {
				log.info(customer.getName());
			}
			log.info("");

			// fetch an individual customer by ID
			
		};
	}
}
