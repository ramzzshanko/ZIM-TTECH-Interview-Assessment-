package org.zimttech.www.dpms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zimttech.www.dpms.model.BPFlag;
import org.zimttech.www.dpms.model.BloodPressure;
import org.zimttech.www.dpms.repository.BloodPressureRepository;

@Service
public class BloodPressureService {
    @Autowired
    BloodPressureRepository repository;

    public BloodPressure createBloodPressure(BloodPressure bloodPressure) {
        return repository.save(bloodPressure);
    }

    public BPFlag processBP(double systolic, double diastolic){
        if(systolic < 120 && diastolic < 80){
            return BPFlag.NORMAL;
        }else if(systolic < 130 && diastolic < 80){
            return BPFlag.ELEVATED;
        }else if((systolic>=130 && systolic < 139) || (diastolic > 80 && diastolic < 90)){
            return BPFlag.STAGE1;
        }else if((systolic >= 140 && systolic < 180) || (diastolic >=90 && diastolic < 120)){
            return BPFlag.STAGE2;
        }else{
            return BPFlag.CRISIS;
        }
    }

}
