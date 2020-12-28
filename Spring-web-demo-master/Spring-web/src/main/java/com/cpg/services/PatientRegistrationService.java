package com.cpg.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cpg.models.PatientRegistration;
import com.cpg.models.User;
import com.cpg.repository.PatientRegistrationRepository;
import com.cpg.repository.UserRepository;

@Service
public class PatientRegistrationService {
	
	@Autowired
	private PatientRegistrationRepository patientRepository; 
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public PatientRegistration addPatient(PatientRegistration patientRegistration){
		
		return patientRepository.save(patientRegistration);	
	}

	@Transactional
	public PatientRegistration removePatient(int patientId){
		
		Optional<PatientRegistration> patientRegistration=patientRepository.findById(patientId);
		
		if(!patientRegistration.isEmpty()) {
			int userId = patientRegistration.get().getUser().getUserId();
			patientRepository.deleteById(patientId);
			userRepository.deleteByUserId(userId);
			return patientRegistration.get();
		}
		return null;
		
	}

	@Transactional
	public PatientRegistration updatePatient(int patientId, PatientRegistration patientRegistration) {
		
		Optional<PatientRegistration> patientRegistration1=patientRepository.findById(patientId);
		
		if(patientRegistration1.isEmpty() || patientRegistration==null) {
			return null;
		}
		
		else {
			PatientRegistration patientRegistration2 = patientRegistration1.get();
			User user = patientRegistration2.getUser();
			BeanUtils.copyProperties(patientRegistration, patientRegistration2,"patientId");
			BeanUtils.copyProperties(patientRegistration.getUser(), user, "userId");
			return patientRepository.save(patientRegistration2);	
		}
	}

	@Transactional
	public PatientRegistration getPatient(int patientId){
		
		Optional<PatientRegistration> patientRegistration = patientRepository.findById(patientId);
		if(patientRegistration.isEmpty())
			return null;
		else
			return patientRegistration.get();
	}

	@Transactional
	public List<PatientRegistration> getAllPatients(){
		
		return patientRepository.findAll();
	}

	@Transactional
	public List<PatientRegistration> getRegisteredPatientsByDates(LocalDate createdDate) {
		
		return patientRepository.findByCreatedTime(createdDate);
	}

	@Transactional
	public List<PatientRegistration> getTreatmentByDate(@NotNull LocalDate fromDate, @NotNull LocalDate toDate) {
		
		return  patientRepository.findByCreatedAt(fromDate,toDate);
	}

}
