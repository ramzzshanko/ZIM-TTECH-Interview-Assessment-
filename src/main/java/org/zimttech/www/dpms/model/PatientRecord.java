package org.zimttech.www.dpms.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "patient_records")
public class PatientRecord extends AbstractEntity{

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="patient", referencedColumnName="id")
    private Patient patient;

    @Column(nullable = false)
    private LocalDate dateOfRecord = LocalDate.now();

    private String healthFacilityName;

    private double temperature;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="bloodPressure", referencedColumnName="id")
    private BloodPressure bloodPressure;

    private double height;

    private double weight;

    private double bmi;

    private BMIFlag bmiFlag;

    private double bloodGlucose;

    public void setBmi(){
        this.bmi = this.weight / Math.pow(this.height, 2);
        if(patient.getAge() >= 18 && patient.getAge() <= 65) {
            if (this.bmi >= 18.5 && this.bmi < 25) {
                bmiFlag = BMIFlag.NORMAL;
            } else if (this.bmi >= 25) {
                bmiFlag = BMIFlag.HIGH;
            } else {
                bmiFlag = BMIFlag.LOW;
            }
        }else{
            bmiFlag = BMIFlag.NORMAL;
        }
    }

}
