package org.zimttech.www.dpms.common;

import lombok.Data;
import org.zimttech.www.dpms.model.PatientRecord;

import java.time.LocalDate;

@Data
public class CreatePatientRecordRequest {

    private Long patientId;

    private LocalDate dateOfRecord = LocalDate.now();

    private String healthFacilityName;

    private double temperature;

    private double systolic;

    private double diastolic;

    private double height;

    private double weight;

    private double bloodGlucose;

}
