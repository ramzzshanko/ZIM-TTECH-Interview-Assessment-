package org.zimttech.www.dpms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zimttech.www.dpms.model.BloodPressure;

public interface BloodPressureRepository extends JpaRepository<BloodPressure, Long> {
}
