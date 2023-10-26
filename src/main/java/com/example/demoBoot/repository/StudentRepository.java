package com.example.demoBoot.repository;

import com.example.demoBoot.beans.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

}