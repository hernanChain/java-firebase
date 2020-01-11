package com.firebase.demo;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.firebase.demo.db.connection.FireBaseConnection;
import com.firebase.demo.db.schema.SchemaImpl;
import com.firebase.demo.exception.DuplicateRecordException;
import com.firebase.demo.model.Student;

@SpringBootApplication
public class FireBaseAppApplication {

	public static void main(String[] args) throws DuplicateRecordException, BeansException {
		ConfigurableApplicationContext ctx = SpringApplication.run(FireBaseAppApplication.class, args);
		ctx.getBean(FireBaseConnection.class);
		SchemaImpl schema = ctx.getBean(SchemaImpl.class);
		schema.addStudent(new Student("Hernan", "Cadena", "201611279", "hernan.cadena@uptc.edu.co", "7"));
		schema.addStudent(new Student("Jimmy", "Plazas", "201611123", "jimmy.plazas@uptc.edu.co", "1"));
		schema.addStudent(new Student("Karem", "Pinto", "201611124", "karem.pinto@uptc.edu.co", "2"));
		schema.addStudent(new Student("Miguel", "Palacios", "201611125", "miguel.palacios@uptc.edu.co", "3"));
		schema.addStudent(new Student("Camilo", "Triana", "201611126", "camilo.triana@uptc.edu.co", "4"));
	}

}
	