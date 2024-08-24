package com.scm.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="division")
public class Division {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer stdid;
	private Integer teaid;
	
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
	public Integer getStdid() {
		return stdid;
	}
	public void setStdid(Integer stdid) {
		this.stdid = stdid;
	}
	public Integer getTeaid() {
		return teaid;
	}
	public void setTeaid(Integer teaid) {
		this.teaid = teaid;
	}
	
}
