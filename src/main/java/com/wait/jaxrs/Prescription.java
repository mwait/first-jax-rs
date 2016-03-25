package com.wait.jaxrs;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Prescription")
public class Prescription {
	private long id;
	private String description;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
