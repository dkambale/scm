package com.scm.app.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "schoolbranch")
public class SchoolBranch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name="institute_id")
	private Institute inst;

	@OneToMany(mappedBy="sb")
	private List<SchoolClass> schoolclass = new ArrayList<SchoolClass>();
	
	public List<SchoolClass> getSchoolclass() {
		return schoolclass;
	}

	public void setSchoolclass(List<SchoolClass> schoolclass) {
		this.schoolclass = schoolclass;
	}

	public Institute getInst() {
		return inst;
	}

	public void setInst(Institute inst) {
		this.inst = inst;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
