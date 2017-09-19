package org.brandt.mrplauf.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Arbeitsplan {

	@Id
	int ID;
	String Name;
	
	public Arbeitsplan() {}
	
	public Arbeitsplan(int ID,String Name) {
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
