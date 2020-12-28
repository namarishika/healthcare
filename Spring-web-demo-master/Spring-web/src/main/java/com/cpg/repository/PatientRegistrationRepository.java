package com.cpg.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cpg.models.PatientRegistration;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

@Repository
public interface PatientRegistrationRepository extends JpaRepository<PatientRegistration, Integer> {

	@Query(value = "select * from patient where Date(created_at)=:date", nativeQuery = true)
	List<PatientRegistration> findByCreatedTime(@Param("date") LocalDate createdTime);

	@Query(value="select * from patient p where (Date(p.created_at)>= :from and Date(p.created_at)<= :to)",nativeQuery = true)
	List<PatientRegistration> findByCreatedAt(@Param("from")  @NotNull LocalDate fromDate, @Param("to")  @NotNull LocalDate toDate);
}
