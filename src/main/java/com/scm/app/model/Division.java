package com.scm.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "division")
public class Division {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Long schoolClassId;
	private Long schoolBranchId;
	private Long accountId;
	private String createdBy;
	private String modifiedBy;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSchoolClassId() {
		return schoolClassId;
	}

	public void setSchoolClassId(Long schoolClassId) {
		this.schoolClassId = schoolClassId;
	}

	public Long getSchoolBranchId() {
		return schoolBranchId;
	}

	public void setSchoolBranchId(Long schoolBranchId) {
		this.schoolBranchId = schoolBranchId;
	}

}
