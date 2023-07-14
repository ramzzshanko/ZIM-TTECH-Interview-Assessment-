package org.zimttech.www.dpms.model;

public enum BMIFlag {
    NORMAL("Normal"),
    HIGH("High"),
    LOW("Low");

    private String description;

    BMIFlag(String description){
        this.description = description;
    }
}
