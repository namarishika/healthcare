package com.cpg.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="patient")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public class PatientRegistration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="patient_id")
	int patientId;
	
	@NotNull
	@NotBlank
	@Column(name="blood_group")
	String bloodGroup;
	
	@NotNull
	@Column(name="medication")
	boolean medication;
	
	@NotNull
	@NotBlank
	@Column(name="policy_number")
	String policyNumber;
	
	@NotNull
	@Column(name="allergies")
	boolean allergies;
	
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
	Date createdTime;
	
    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
	Date updatedTime;
	

	@OneToOne(cascade = CascadeType.ALL)
	User user;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	Policy policy;
	
	public PatientRegistration() {
		
	}
	
	public PatientRegistration(String bloodGroup, boolean medication, boolean allergies, String policyNumber,User user) {
		super();
		this.bloodGroup = bloodGroup;
		this.medication = medication;
		this.allergies = allergies;
		this.policyNumber = policyNumber; 
		this.user = user;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date  updatedTime) {
		this.updatedTime = updatedTime;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public boolean isMedication() {
		return medication;
	}
	public void setMedication(boolean medication) {
		this.medication = medication;
	}
	public boolean isAllergies() {
		return allergies;
	}
	public void setAllergies(boolean allergies) {
		this.allergies = allergies;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public int getPatientId() {
		return patientId;
	}

	
//	public Policy getPolicy() {
//		return policy;
//	}


	@Override
	public String toString() {
		return "PatientRegistration [patientId=" + patientId + ", bloodGroup=" + bloodGroup + ", medication="
				+ medication + ", allergies=" + allergies + ", policyNumber=" + policyNumber + ", createdTime=" + createdTime
				+ ", updatedTime=" + updatedTime + "]";
	}
}