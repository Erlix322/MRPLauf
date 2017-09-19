package org.brandt.mrplauf.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Arbeitsplan {

	@Id
	int ID;
	String Name;
	
	@OneToMany(fetch=FetchType.EAGER)
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
	
	
}
