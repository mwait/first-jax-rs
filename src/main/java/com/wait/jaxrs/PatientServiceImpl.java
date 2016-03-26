package com.wait.jaxrs;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.wait.jaxrs.exceptions.SomeBusinessException;

public class PatientServiceImpl implements PatientService {
long currentId = 12;
Map<Long, Patient> patients = new HashMap<Long, Patient>();
Map<Long, Prescription> prescriptions = new HashMap<Long, Prescription>();

public PatientServiceImpl() {
	init();
}

final void init() {
	Patient patient = new Patient();
	patient.setName("Mark");
	patient.setId(12);
	patients.put(patient.getId(), patient);
	
	Prescription prescription = new Prescription();
	prescription.setDescription("prescription 23");
	prescription.setId(23);
	prescriptions.put(prescription.getId(), prescription);
	
}

@Override
public Patient getPatient(String id) {
	System.out.println("-----getPatient, Patient id is: "+id);
	long idNumber=Long.parseLong(id);
	Patient patient = patients.get(idNumber);
	if (patient == null) {
		throw new WebApplicationException(Response.Status.NOT_FOUND);
	}
	return patient;
}

@Override
public Response addPatient(Patient patient) {
	System.out.println("------addPatient, Patient name is: "+patient.getName());
	patient.setId(++currentId);
	patients.put(patient.getId(), patient);
	return Response.ok(patient).build();
}

@Override
public Response updatePatient(Patient patient) {
	System.out.println("------updatePatient, updatePatient name is: "+patient.getName());
	Patient currentPatient = patients.get(patient.getId());
	Response response;
	if(currentPatient!=null) {
		patients.put(patient.getId(), patient);
		response=Response.ok().build();
	} else {
		//response = Response.notModified().build();
		//alternatywa dla WebApplicationException(Response.Status.NOT_FOUND)
		throw new NotFoundException();
	}
	return response;
}

@Override
public Response deletePatients(String id) {
	System.out.println("------- deletePatients, Patient id is: "+id);
	long idNumber=Long.parseLong(id);
	Patient patient = patients.get(idNumber);
	
	Response response;
	if(patient !=null) {
		response = Response.ok().build();
		patients.remove(idNumber);
	} else {
		//response = Response.notModified().build();
		throw new SomeBusinessException("Business Exception");
	}
	return response;
}

@Override
public Prescription getPrescription(String prescriptionId) {
	System.out.println("-----getPrescription, Prescription id is: "+prescriptionId);
	long idNumber=Long.parseLong(prescriptionId);
	Prescription prescription = prescriptions.get(idNumber);
	return prescription;
}



}
