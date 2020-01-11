package com.firebase.demo.db.schema;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firebase.demo.db.connection.FireBaseConnection;
import com.firebase.demo.exception.DuplicateRecordException;
import com.firebase.demo.exception.ResourceNotFoundException;
import com.firebase.demo.model.Student;

import net.thegreshams.firebase4j.error.FirebaseException;
import net.thegreshams.firebase4j.model.FirebaseResponse;
import net.thegreshams.firebase4j.service.Firebase;

@Component
public class SchemaImpl implements Schema {

	private final String STUDENT_SCHEMA;
	private final Firebase firebase;
	private final ObjectMapper mapper;

	public SchemaImpl(@Autowired FireBaseConnection fireBaseConnection,
					  @Value("${student_schema}") String studentSchema) throws UnsupportedEncodingException, FirebaseException {
		this.firebase = fireBaseConnection.getFirebase();
		this.STUDENT_SCHEMA = studentSchema;
		this.mapper = new ObjectMapper();
		this.mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

	}

	public Student addStudent(Student student) throws DuplicateRecordException {
		FirebaseResponse response;
		try {
			response = firebase.get(STUDENT_SCHEMA + "/" + student.getCode());
			Map<String, Object> bodyResponse = response.getBody();
			if(!bodyResponse.isEmpty()) {
				throw new DuplicateRecordException("Code " + student.getCode() + " already exist");
			}
			firebase.patch(STUDENT_SCHEMA + "/" + student.getCode(), mapper.writeValueAsString(student));
		} catch (UnsupportedEncodingException | JsonProcessingException | FirebaseException e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public Student updateStudent(Student student) throws ResourceNotFoundException {
		try {
			FirebaseResponse response = firebase.put(STUDENT_SCHEMA + "/" + student.getCode(),
					mapper.writeValueAsString(student));
			Map<String, Object> bodyResponse = response.getBody();
			if(bodyResponse.isEmpty()) {
				throw new ResourceNotFoundException("Code " + student.getCode() + " not found");
			}
		} catch (UnsupportedEncodingException | JsonProcessingException | FirebaseException e) {
			e.printStackTrace();
		}
		return student;
	}

	public Student getStudent(String code) throws ResourceNotFoundException {
		FirebaseResponse response;
		Student student = new Student();
		try {
			response = firebase.get(STUDENT_SCHEMA + "/" + code);
			Map<String, Object> bodyResponse = response.getBody();
			if(bodyResponse.isEmpty()) {
				throw new ResourceNotFoundException("Code " + code + " not found");
			}
			student.setFirstName(bodyResponse.get("firstName").toString());
			student.setLastName(bodyResponse.get("lastName").toString());
			student.setEmail(bodyResponse.get("email").toString());
			student.setCode(bodyResponse.get("code").toString());
			student.setSemester(bodyResponse.get("semester").toString());
		} catch (FirebaseException | IOException e) {
			e.printStackTrace();
		}
		return student;
	}

	public boolean deleteStudent(String code) throws ResourceNotFoundException {
		FirebaseResponse response;
		try {
			response = firebase.get(STUDENT_SCHEMA + "/" + code);
			Map<String, Object> bodyResponse = response.getBody();
			if(bodyResponse.isEmpty()) {
				throw new ResourceNotFoundException("Code " + code + " not found");
			}
			firebase.delete(STUDENT_SCHEMA + "/" + code);
			return true;
		} catch (UnsupportedEncodingException | FirebaseException e) {
			e.printStackTrace();
		}
		return false;
	}
}
