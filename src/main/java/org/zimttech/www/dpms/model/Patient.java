package org.zimttech.www.dpms.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@Entity
@Table(name = "patients")
public class Patient extends AbstractEntity{

    @Column(unique = true, nullable = false)
    private String patientId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String address;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private Boolean isDiabetic = false;

    private LocalDate dateOnDiabeticMedication;

    public Long getAge(){
        LocalDate today = LocalDate.now();
        return ChronoUnit.YEARS.between(this.dateOfBirth, today);
    }

}
