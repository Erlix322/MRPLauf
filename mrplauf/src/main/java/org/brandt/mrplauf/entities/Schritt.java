package org.brandt.mrplauf.entities;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Schritt {

	@Id
	int ID;
	String Name;
	int Dauer;
	@ManyToMany(fetch=FetchType.EAGER)
	List<Schritt> list;
	@ManyToOne
	Arbeitsplan arbeitsplan;
	@ManyToOne
	Ressource ressource;
	
	public Schritt() {}
	public Schritt(int ID, String Name,int Dauer,List<Schritt> list,Arbeitsplan arbeitsplan, Ressource ressource) {
		this.ID = ID;
		this.Name = Name;
		this.Dauer = Dauer;
		this.list = list;
		this.arbeitsplan = arbeitsplan;
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
	public List<Schritt> getList() {
		return list;
	}
	public void setList(List<Schritt> list) {
		this.list = list;
	}
	public Arbeitsplan getArbeitsplan() {
		return arbeitsplan;
	}
	public void setArbeitsplan(Arbeitsplan arbeitsplan) {
		this.arbeitsplan = arbeitsplan;
	}
	public Ressource getRessource() {
		return ressource;
	}
	public void setRessource(Ressource ressource) {
		this.ressource = ressource;
	}
	
	
	
	
}
