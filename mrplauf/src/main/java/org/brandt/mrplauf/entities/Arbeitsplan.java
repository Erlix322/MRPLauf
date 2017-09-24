package org.brandt.mrplauf.entities;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Arbeitsplan implements Cloneable {



	@Id
	int ID;
	String Name;
	
	@OneToMany(fetch=FetchType.EAGER)
	@OrderBy("ID")
	List<Schritt> schritte;
	
	public Arbeitsplan() {}
	
	public Arbeitsplan(int ID,String Name, List<Schritt> schritte) {
		this.ID = ID;
		this.Name = Name;
		this.schritte = schritte;
	}

	public List<Schritt> getSchritte() {
	   
	    return schritte;    
		
	}

	public void setSchritte(List<Schritt> schritte) {		
		this.schritte = schritte;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
	@Override
	public Arbeitsplan clone()  {
		// TODO Auto-generated method stub
		List<Schritt> l = new ArrayList();
		for(Schritt s : this.getSchritte()) {
			l.add(s.clone());
		}
		return new Arbeitsplan(this.getID(),this.getName(),l);
	}
	
}
