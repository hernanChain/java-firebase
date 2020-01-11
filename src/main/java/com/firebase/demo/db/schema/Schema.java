package com.firebase.demo.db.schema;

import com.firebase.demo.exception.DuplicateRecordException;
import com.firebase.demo.exception.ResourceNotFoundException;
import com.firebase.demo.model.Student;

public interface Schema {
	public Student addStudent(Student student) throws DuplicateRecordException;
	public Student updateStudent(Student student) throws ResourceNotFoundException;
	public Student getStudent(String code) throws ResourceNotFoundException;
	public boolean deleteStudent(String code) throws ResourceNotFoundException;
	
}
