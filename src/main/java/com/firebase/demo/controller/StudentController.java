package com.firebase.demo.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.firebase.demo.db.schema.Schema;
import com.firebase.demo.exception.DuplicateRecordException;
import com.firebase.demo.exception.ResourceNotFoundException;
import com.firebase.demo.model.Student;

@RestController
@RequestMapping("/students")
public class StudentController {
	@Autowired
	private Schema schema;

	@PostMapping
	public Student addStudent(@RequestBody Student student) {
		if (StringUtils.isEmpty(student.getCode())) {
			ResourceNotFoundException exception = new ResourceNotFoundException("Code must be not null or not empty");
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage(), exception);
		}
		try {
			return schema.addStudent(student);
		} catch (DuplicateRecordException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
		}
	}

	@PutMapping
	public Student updateStudent(@RequestBody Student student) {
		if (StringUtils.isEmpty(student.getCode())) {
			ResourceNotFoundException exception = new ResourceNotFoundException("Code must be not null or not empty");
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage(), exception);
		}
		try {
			return schema.updateStudent(student);
		} catch (ResourceNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

	@GetMapping("/{code}")
	public Student getStudent(@PathVariable(value = "code") String code) {
		try {
			return schema.getStudent(code);
		} catch (ResourceNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}

	@DeleteMapping("/{code}")
	public boolean deleteStudent(@PathVariable(value = "code") String code) {
		try {
			return schema.deleteStudent(code);
		} catch (ResourceNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
		}
	}
}
