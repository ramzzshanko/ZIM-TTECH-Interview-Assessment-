package org.zimttech.www.dpms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.MessageFormat;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PatientNotFoundById extends Exception{

    private Long id;

    public PatientNotFoundById(Long id) {
        super(MessageFormat.format("Patient with id {0} not found", id));
    }

}
