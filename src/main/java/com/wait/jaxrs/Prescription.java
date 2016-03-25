package com.wait.jaxrs;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Prescription")
public class Prescription {
	private long id;
	private String description;
	
	Map<Long, Medicine> prescriptions = new HashMap<Long, Medicine>();
	
	public Prescription() {
		init();
	}
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
	
	@GET
	@Path("medicines/{id}/")
	public Medicine getMedicine(@PathParam("id") int medicineid) {
		System.out.println("-----------getMedicine with id: "+ medicineid);
		Medicine medicine = prescriptions.get(new Long(medicineid));
		return medicine;
	}
	
	public final void init() {
		Medicine medicine = new Medicine();
		medicine.setId(23);
		medicine.setDescription("Medicine 23");
		prescriptions.put(medicine.getId(), medicine);
	}

}
