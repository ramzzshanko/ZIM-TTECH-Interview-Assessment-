package org.zimttech.www.dpms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zimttech.www.dpms.exceptions.PatientNotFoundById;
import org.zimttech.www.dpms.model.Patient;
import org.zimttech.www.dpms.service.PatientService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/patient")
public class PatientResource {

    @Autowired
    PatientService service;

    @GetMapping("")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(service.getAllPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") Long id) throws PatientNotFoundById {
        Patient patient = service.getPatientById(id)
                .orElseThrow(()-> new PatientNotFoundById(id));
        return ResponseEntity.ok(patient);
    }

    @PostMapping("")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.savePatient(patient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Long id, @RequestBody Patient patient) throws PatientNotFoundById {
        Patient existing = service.getPatientById(id)
                .orElseThrow(()-> new PatientNotFoundById(id));
        existing.setPatientId(patient.getPatientId());
        existing.setAddress(patient.getAddress());
        existing.setDateOfBirth(patient.getDateOfBirth());
        existing.setFirstName(patient.getFirstName());
        existing.setLastName(patient.getLastName());
        existing.setDateOnDiabeticMedication(patient.getDateOnDiabeticMedication());
        existing.setIsDiabetic(patient.getIsDiabetic());

        return ResponseEntity.ok(service.savePatient(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,Boolean>> deletePatient(@PathVariable("id") Long id) throws PatientNotFoundById {
        Patient patient = service.getPatientById(id)
                .orElseThrow(()-> new PatientNotFoundById(id));

        service.removePatient(patient);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
