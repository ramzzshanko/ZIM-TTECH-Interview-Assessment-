package org.zimttech.www.dpms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zimttech.www.dpms.model.Patient;
import org.zimttech.www.dpms.model.PatientRecord;
import org.zimttech.www.dpms.repository.PatientRecordRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PatientRecordService {

    @Autowired
    PatientRecordRepository repository;

    public PatientRecord savePatientRecord(PatientRecord patientRecord) {
        return repository.save(patientRecord);
    }

    public List<PatientRecord> getAllPatientRecords() {
        return repository.findAll();
    }

    public List<PatientRecord> getPatientRecordsByPatient(Patient patient){
        return repository.findByPatient(patient);
    }

    public Optional<PatientRecord> getPatientRecordById(Long id){
        return repository.findById(id);
    }

    public void removePatientRecord(PatientRecord patientRecord){
        repository.delete(patientRecord);
    }

}
