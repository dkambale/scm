package com.scm.app.service;

import java.util.List;
import java.util.Optional;

import com.scm.app.model.Assignment;
import com.scm.app.model.Course;
import com.scm.app.util.AuditLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.app.model.Student;
import com.scm.app.repo.StudentRepo;

@Service
public class StudentService {

	@Autowired
	StudentRepo repo;

	@Autowired
	private AuditLogger auditLogger;

	public Student saveStudent(Student std) {
		Student saved = repo.save(std);

		auditLogger.logAction(
				std.getAccountId(),
				saved.getRollno(),
				"Student",
				"Create",
				std.getCreatedBy()
		);


		return saved;

	}

	public List<Student> getAll() {
		return repo.findAll();
	}

	public Student getById(Long id) {
		Optional<Student> std = repo.findById(id);
		return std.isPresent()? std.get() : new Student();
	}

	public Student saveInstitute(Student student) {
		Student saved = repo.save(student);
		auditLogger.logAction(
				student.getAccountId(),
				saved.getRollno(),
				"Student",
				"Create",
				student.getCreatedBy()
		);


		return saved;
	}
	
	
	public boolean deleteById(Long id) {
		Student existing = repo.findById(id).orElse(null);
		if (existing != null) {
			repo.deleteById(id);

			//  Audit Log for DELETE
			auditLogger.logAction(
					existing.getAccountId(),
					existing.getRollno(),
					"Student",
					"Delete",
					existing.getCreatedBy()
			);
			return true;

		}else {
			return false;
		}

	}

	public List<Student> getByDivisionIdAndClassId(Long classId, Long divisionId) {
		return repo.getByDivisionIdAndClassId(divisionId, classId);
	}
	
}
