package org.zimttech.www.dpms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "blood_pressure")
@NoArgsConstructor
public class BloodPressure extends AbstractEntity{

    private double systolic;

    private double diastolic;

    private BPFlag flag = BPFlag.NORMAL;

    public BloodPressure(double systolic, double diastolic, BPFlag flag){
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.flag = flag;
    }

}
