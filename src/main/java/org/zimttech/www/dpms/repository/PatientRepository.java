package org.zimttech.www.dpms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zimttech.www.dpms.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
