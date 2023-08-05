package com.saumya.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.saumya.springbootbackend.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

}
