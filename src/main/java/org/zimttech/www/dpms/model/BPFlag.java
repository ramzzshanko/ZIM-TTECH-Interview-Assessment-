package org.zimttech.www.dpms.model;

import lombok.Data;

public enum BPFlag {
    NORMAL("Normal"),
    ELEVATED("Elevated"),
    STAGE1("Hypertension Stage 1"),
    STAGE2("Hypertension Stage 2"),
    CRISIS("Hypertension Crisis");

    private final String description;

    BPFlag(String description) {
        this.description = description;
    }

    private String getDescription() {
        return this.description;
    }
}
