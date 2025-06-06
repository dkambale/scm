package com.scm.app.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@ManyToMany
	private List<Subject> subjectList;

	public List<Subject> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
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

}
