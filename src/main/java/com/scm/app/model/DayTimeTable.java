package com.scm.app.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Embeddable
@Table(name = "day_time_table")
public class DayTimeTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@PrimaryKeyJoinColumn
	private Long id;

	String dayName;

	@Embedded
	@OneToMany(targetEntity = TimeSubjectDetails.class, cascade = CascadeType.ALL)
	//@OneToMany(mappedBy = "dayTimeTable", cascade = CascadeType.ALL, orphanRemoval = true)
	List<TimeSubjectDetails> tsd;

//	@ManyToOne
//	@JoinColumn(name = "time_table_id")
//	TimeTable timeTable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public TimeTable getTimeTable() {
//		return timeTable;
//	}
//
//	public void setTimeTable(TimeTable timeTable) {
//		this.timeTable = timeTable;
//	}

	public String getDayName() {
		return dayName;
	}

	public void setDayName(String dayName) {
		this.dayName = dayName;
	}

	public List<TimeSubjectDetails> getTsd() {
		return tsd;
	}

	public void setTsd(List<TimeSubjectDetails> tsd) {
		this.tsd = tsd;
	}

}