package org.zimttech.www.dpms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import static jakarta.persistence.TemporalType.TIMESTAMP;

@Data
@MappedSuperclass
public class AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Temporal(TIMESTAMP)
    private Date dateCreated;

    @Temporal(TIMESTAMP)
    private Date updatedAt;

    @Version
    private int version;

    @PrePersist
    protected void onCreate() {
        this.dateCreated = new Date(System.currentTimeMillis());
        this.updatedAt = this.dateCreated;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date(System.currentTimeMillis());
    }
}
