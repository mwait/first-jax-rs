package com.wait.jaxrs.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.wait.jaxrs.Patient;

public class PatientServiceClient {
	private final static String SERVICE_URL="http://localhost:8080/first-jax-rs/services/patientservice";
	
	public static void main(String[] args) {
		getPatient();
	}
	
	public static void getPatient(){
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(SERVICE_URL).path("/patients").path("/{id}")
				.resolveTemplate("id", 12);
		//Response response = target.request().get();
		//System.out.println(response.getStatus());
		//GET
		Patient patient = target.request().get(Patient.class);
		System.out.println(patient.getName());
		
		//PUT UPDATE
		WebTarget putTarget = client.target(SERVICE_URL).path("/patients");
		patient.setName("Hans");
		Response updateResponse = putTarget.request().put(Entity.entity(patient, MediaType.APPLICATION_XML));
		System.out.println(updateResponse.getStatus());
		updateResponse.close();
		
		//POST CREATE
		Patient newPatient = new Patient();
		newPatient.setName("Garret");
		WebTarget postTarget = client.target(SERVICE_URL).path("/patients");
		Patient createPatient=postTarget.request().post(Entity.entity(newPatient, MediaType.APPLICATION_XML), Patient.class);
		System.out.println(createPatient.getId());
		
		
		
		client.close();
	}
}
