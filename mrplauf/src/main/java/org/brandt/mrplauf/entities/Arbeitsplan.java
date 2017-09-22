package org.brandt.mrplauf.entities;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Arbeitsplan {

	@Id
	int ID;
	String Name;
	
	@ManyToMany(fetch=FetchType.EAGER)
	//@Fetch(FetchMode.SELECT)
	Set<Schritt> schritte;
	
	public Arbeitsplan() {}
	
	public Arbeitsplan(int ID,String Name, List<Schritt> schritte) {
		this.ID = ID;
		this.Name = Name;
		this.schritte = schritte.stream().collect(Collectors.toSet());
	}

	public List<Schritt> getSchritte() {
		List<Schritt> ret = schritte.stream().collect(Collectors.toList());
		ret.sort(Comparator.comparing(Schritt::getID));
		return ret;
	}

	public void setSchritte(List<Schritt> schritte) {
		this.schritte = schritte.stream().collect(Collectors.toSet());
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
	
	
}
