package com.saumya.springbootbackend.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.saumya.springbootbackend.exception.StudentNotFoundException;
import com.saumya.springbootbackend.model.Student;
import com.saumya.springbootbackend.repository.StudentRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	
	@PostMapping("/student")
	Student newStudent(@RequestBody Student newStudent) {
		return studentRepository.save(newStudent);
	}
	
	@GetMapping("/students")
	List<Student> getAllStudents(){
		return studentRepository.findAll();
	}
	
	@GetMapping("/student/{id}")
	Student getStudentById(@PathVariable Long id){
		return studentRepository.findById(id)
				.orElseThrow(()-> new StudentNotFoundException(id));
	}
	
	@PutMapping("/student/{id}")
	Student updateStudent(@RequestBody Student newStudent, @PathVariable Long id) {
		return studentRepository.findById(id)
				.map(student -> {
					student.setUsername(newStudent.getUsername());
					student.setName(newStudent.getName());
					student.setEmail(newStudent.getEmail());
					return studentRepository.save(student);
					

				}).orElseThrow(()->new StudentNotFoundException(id));
		
	}
		
	@DeleteMapping("/student/{id}")
	String deleteStudent(@PathVariable Long id) {
		if(!studentRepository.existsById(id)) {
			throw new StudentNotFoundException(id);
			
		}
		studentRepository.deleteById(id);
		return "student with id "+id+" has been deleted successfully.";
		
	}
	


}
