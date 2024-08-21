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
@Table(name="schoolclass")
public class SchoolClass {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	// add schoolbranchid if JPA is not adding that
		
	@ManyToOne
	@JoinColumn(name="schoolbranch_id")
	private SchoolBranch sb;
	
	@OneToMany(mappedBy="std")
	private List<Student> student = new ArrayList<Student>();
	
	
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


	public SchoolBranch getSb() {
		return sb;
	}


	public void setSb(SchoolBranch sb) {
		this.sb = sb;
	}


	public List<Student> getStudent() {
		return student;
	}


	public void setStudent(List<Student> student) {
		this.student = student;
	}

}
