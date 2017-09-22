package org.brandt.mrplauf.entities;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Schritt {

	@Id
	int ID;
	String Name;
	int Dauer;
	LocalDate start;
	LocalDate ende;
	public LocalDate getStart() {
		return start;
	}
	
	public void setStart(LocalDate start) {
		this.start = start;
	}
	public LocalDate getEnde() {
		return ende;
	}
	public void setEnde(LocalDate ende) {
		this.ende = ende;
	}
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.DETACH)
	@OrderBy("ID")
	Set<Schritt> parents;

	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.DETACH)
	Ressource ressource;
	
	public Schritt() {}
	public Schritt(int ID, String Name,int Dauer,List<Schritt> list, Ressource ressource) {
		this.ID = ID;
		this.Name = Name;
		this.Dauer = Dauer; 
		this.setList(list);

		this.ressource = ressource;
		
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
	public int getDauer() {
		return Dauer;
	}
	public void setDauer(int dauer) {
		Dauer = dauer;
	}
	public List<Schritt> getParents() {
		return parents.stream().collect(Collectors.toList());
	}
	public void setList(List<Schritt> list) {
		if(list != null) {			
			this.parents = list.stream().collect(Collectors.toSet());			
		}	
		else this.parents = null;
	
	}
	
	public Ressource getRessource() {
		return ressource;
	}
	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}
	
	
	
	
}
