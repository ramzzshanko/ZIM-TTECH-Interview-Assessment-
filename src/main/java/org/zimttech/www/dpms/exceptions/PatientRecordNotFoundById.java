package org.zimttech.www.dpms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientRecordNotFoundById extends Exception{

    public PatientRecordNotFoundById(Long id) {
        super((MessageFormat.format("Patient record with id {0} not found",id)));
    }

}
