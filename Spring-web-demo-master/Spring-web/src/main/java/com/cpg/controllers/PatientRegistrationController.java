package com.cpg.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpg.exceptions.InvalidPatientException;
import com.cpg.models.PatientRegistration;
import com.cpg.services.PatientRegistrationService;


@RestController
public class PatientRegistrationController {
	
	private static final Logger logger = LoggerFactory.getLogger(PatientRegistrationController.class);
	
	@Autowired
	PatientRegistrationService service;
	
	@PostMapping("/patient")
	public ResponseEntity<PatientRegistration> addPatient( @Valid @RequestBody PatientRegistration patientRegistration) {
		
		PatientRegistration patientRegistration1 = service.addPatient(patientRegistration);
		
		if(patientRegistration1!=null) {
			logger.info("patient successfully registered");
			return new ResponseEntity<>(patientRegistration1,HttpStatus.OK);
		}
		
		logger.trace("add patient method accessed");
		throw new InvalidPatientException("Patients details can not be null");
	}
	
	@DeleteMapping("/patient/{id}")
	public ResponseEntity<PatientRegistration> deletePatient(@PathVariable int id) {
		
		PatientRegistration patientRegistration1 = service.removePatient(id);
		
		if(patientRegistration1!=null) {
			logger.info("patient successfully removed");
			return new ResponseEntity<>(patientRegistration1,HttpStatus.OK);
		}
		
		logger.trace("delete patient method accessed");
		throw new InvalidPatientException("Invalid patient id");
	}
	
	@PutMapping("/patient/{id}")
	public ResponseEntity<PatientRegistration> updatePatient(@PathVariable int id, @RequestBody PatientRegistration patientRegistration) {
		
		PatientRegistration patientRegistration1 = service.updatePatient(id, patientRegistration);
		
		if(patientRegistration1!=null) {
			logger.info("patient information successfully updated");
			return new ResponseEntity<>(patientRegistration1,HttpStatus.OK);
		}
		
		logger.trace("update patient method accessed");
		throw new InvalidPatientException("Invalid patient id / Patients details are null");
	}
	
	@GetMapping("/patient/{id}")
	public ResponseEntity<PatientRegistration> getPatient(@PathVariable int id) {
		
		PatientRegistration patientRegistration = service.getPatient(id);
		
		if(patientRegistration==null) {
			logger.info("patient information fetched successfully");
			throw new InvalidPatientException("Invalid patient id");
		}
		
		logger.trace("get patient method accessed");
		return new ResponseEntity<>(patientRegistration,HttpStatus.OK);
	}
	
	@GetMapping("/patient")
	public ResponseEntity<List<PatientRegistration>> getAllPatient() {
		
		List<PatientRegistration> patients = service.getAllPatients();
		
		if(patients.isEmpty()) {
			logger.trace("getAllPatients method accessed");
			throw new InvalidPatientException("No patients found");
		}
		
		return new ResponseEntity<>(patients,HttpStatus.OK);
	}
	
	@GetMapping("/patients")
	public ResponseEntity<List<PatientRegistration>> getPatientsByDate(@RequestParam("date") @DateTimeFormat(iso = ISO.DATE) LocalDate createdTime){
		
		List<PatientRegistration> patients = service.getRegisteredPatientsByDates(createdTime);
		
		if(patients.isEmpty()) {
			logger.trace("getAllPatientsByDate method accessed");
			throw new InvalidPatientException("No patients found");
		}
		
		return new ResponseEntity<>(patients, HttpStatus.OK);
	}
	
	@GetMapping("/bydates")
	public ResponseEntity<List<PatientRegistration>> getTreatmentByDate(@RequestParam("fromdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @NotNull LocalDate fromDate,
			@RequestParam("todate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @NotNull LocalDate toDate) {
		List<PatientRegistration> patients = service.getTreatmentByDate(fromDate,toDate);
		
		if(patients.isEmpty()) {
			throw new InvalidPatientException("Unable to find patients from Date: "+fromDate+" to Date: "+toDate);
		}
		return new ResponseEntity<>(patients, HttpStatus.OK);
	}

}
