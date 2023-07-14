package org.zimttech.www.dpms.repository;

import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zimttech.www.dpms.model.Patient;
import org.zimttech.www.dpms.model.PatientRecord;

import java.util.List;

public interface PatientRecordRepository extends JpaRepository<PatientRecord, Long> {
    List<PatientRecord> findByPatient(Patient patient);

    @Query(value = "SELECT r.bmi_flag, count(*) FROM patient_records r GROUP BY r.bmi_flag", nativeQuery = true)
    List<Tuple> getBMIStats();

    @Query(value = "select b.flag, count(*) from patient_records r\n" +
            "join blood_pressure b on r.blood_pressure = b.id\n" +
            "group by b.flag", nativeQuery = true)
    List<Tuple> getBPStats();
}
