package org.brandt.mrplauf.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ressource {

	@Id
	int ID;
	
	String Name;
	
	public Ressource() {}
	public Ressource(int ID,String Name) {
		this.ID = ID;
		this.Name = Name;
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
