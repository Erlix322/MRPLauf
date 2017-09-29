package org.brandt.mrplauf.RestController;

import java.util.List;
import java.util.stream.Collectors;

import org.brandt.mrplauf.entities.Produktionsauftrag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	
	@RequestMapping("/")
	public String index() {		
		
		return "index";
	}
}
