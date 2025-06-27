package com.scm.app.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;
    private Long entityId;
    private String entityType;
    private String action;
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    // Constructors
    public AuditLog() {}

    public AuditLog(Long accountId, Long entityId, String entityType, String action, String createdBy) {
        this.accountId = accountId;
        this.entityId = entityId;
        this.entityType = entityType;
        this.action = action;
        this.createdBy = createdBy;
        this.createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
