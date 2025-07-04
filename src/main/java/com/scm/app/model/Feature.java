package com.scm.app.model;

import jakarta.persistence.*;

@Entity
@Table(name = "features")
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    private String name;
    private String createdBy;

    public Feature(Long id, Long accountId, String name, String createdBy) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    // Constructors
    public Feature() {}

    public Feature(Long accountId, String name) {
        this.accountId = accountId;
        this.name = name;
    }

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getAccountId() { return accountId; }

    public void setAccountId(Long accountId) { this.accountId = accountId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}

