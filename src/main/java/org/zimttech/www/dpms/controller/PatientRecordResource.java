package org.zimttech.www.dpms.controller;

import jakarta.persistence.Tuple;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zimttech.www.dpms.common.CreatePatientRecordRequest;
import org.zimttech.www.dpms.exceptions.PatientNotFoundById;
import org.zimttech.www.dpms.exceptions.PatientRecordNotFoundById;
import org.zimttech.www.dpms.model.*;
import org.zimttech.www.dpms.repository.PatientRecordRepository;
import org.zimttech.www.dpms.service.BloodPressureService;
import org.zimttech.www.dpms.service.PatientRecordService;
import org.zimttech.www.dpms.service.PatientService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/record")
public class PatientRecordResource {

    @Autowired
    PatientRecordService service;

    @Autowired
    PatientService patientService;

    @Autowired
    BloodPressureService bpService;

    @Autowired
    PatientRecordRepository repository;

    @GetMapping("")
    public ResponseEntity<List<PatientRecord>> getAllPatientRecords() {
        return ResponseEntity.ok(service.getAllPatientRecords());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientRecord> getPatientRecordById(@PathVariable("id") Long id) throws PatientRecordNotFoundById {
        PatientRecord record = service.getPatientRecordById(id)
                .orElseThrow(()-> new PatientRecordNotFoundById(id));
        return ResponseEntity.ok(record);
    }

    @PostMapping("")
    public ResponseEntity<PatientRecord> createPatientRecord(@Valid @RequestBody CreatePatientRecordRequest patientRecord) throws PatientNotFoundById {
        PatientRecord record = new PatientRecord();
        Patient patient = patientService.getPatientById(patientRecord.getPatientId())
                .orElseThrow(()-> new PatientNotFoundById(patientRecord.getPatientId()));
        record.setPatient(patient);
        record.setDateOfRecord(patientRecord.getDateOfRecord());

        BPFlag flag = bpService.processBP(patientRecord.getSystolic(), patientRecord.getDiastolic());

        BloodPressure bloodPressure = new BloodPressure(patientRecord.getSystolic(), patientRecord.getDiastolic(), flag);

        BloodPressure bloodPressure2 = bpService.createBloodPressure(bloodPressure);
        record.setBloodPressure(bloodPressure2);
        record.setHeight(patientRecord.getHeight());
        record.setWeight(patientRecord.getWeight());
        record.setBmi();
        record.setTemperature(patientRecord.getTemperature());
        record.setHealthFacilityName(patientRecord.getHealthFacilityName());
        record.setBloodGlucose(patientRecord.getBloodGlucose());

        return ResponseEntity.status(HttpStatus.CREATED).body(service.savePatientRecord(record));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientRecord> updatePatientRecord(@PathVariable("id") Long id, @RequestBody PatientRecord patientRecord) throws PatientRecordNotFoundById {
        PatientRecord record = service.getPatientRecordById(id)
                .orElseThrow(()-> new PatientRecordNotFoundById(id));
        record.setDateOfRecord(patientRecord.getDateOfRecord());
        record.setPatient(patientRecord.getPatient());
        record.setBloodGlucose(patientRecord.getBloodGlucose());
        record.setBmi(patientRecord.getBmi());
        record.setWeight(patientRecord.getWeight());
        record.setHeight(patientRecord.getHeight());
        record.setHealthFacilityName(patientRecord.getHealthFacilityName());
        record.setBloodPressure(patientRecord.getBloodPressure());
        return ResponseEntity.ok(service.savePatientRecord(record));
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Map<String, Long>>> getStats(){
        List<Tuple> bmiStats = repository.getBMIStats();
        List<Tuple> bpStats = repository.getBPStats();

        Map<String, Long> bmiMap = new HashMap();
        bmiStats.stream().forEach(tuple -> bmiMap.put(BMIFlag.values()[(Short) tuple.get(0)].name(), (Long) tuple.get(1)));

        Map<String, Long> bpMap = new HashMap<>();
        bpStats.stream().forEach(tuple -> bpMap.put(BPFlag.values()[(Short) tuple.get(0)].name(), (Long) tuple.get(1)));

        Map<String, Map<String, Long>> response = new HashMap<>();

        response.put("bmi", bmiMap);
        response.put("bp", bpMap);

        return ResponseEntity.ok(response);


    }


}
