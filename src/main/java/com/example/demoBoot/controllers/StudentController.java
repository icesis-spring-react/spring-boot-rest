package com.example.demoBoot.controllers;

import com.example.demoBoot.beans.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(maxAge = 3600)
public class StudentController {
    public static List<Student> students = new ArrayList<Student>();

    public StudentController(){
        students.add(new Student("Carlos", "Lopez"));
        students.add(new Student("Miguel", "Ramirez"));
        students.add(new Student("Jairo", "Vargas"));
        students.add(new Student("Camilo", "Garcia"));
        students.add(new Student("Sofia", "Franco"));
        students.add(new Student("Ana", "Sanchez"));
    }

    @GetMapping("/students")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Student> getStudents(){
        return students;
    }

    @GetMapping("/student/{firstname}/{lastname}")
    public Student studentPathVariable(@PathVariable("firstname") String firstname,
                                       @PathVariable("lastname") String lastname ){
        return new Student(firstname, lastname);
    }

    @GetMapping("/student/query")
    public Student studentQueryParam(@RequestParam(name="firstname") String firstname,
                                   @RequestParam(name="lastname") String lastname ){
        return new Student(firstname, lastname);
    }

    @PostMapping("/student")
    public void addStudent(@RequestBody Student student ){
        students.add(student);
    }

    @PutMapping("/student/{firstname}")
    public void updateStudent(@PathVariable("firstname") String firstname,
                              @RequestBody Student student ){
        for (Student stud : students) {
            if (stud.getFirstname().equals(firstname)) {
                stud.setFirstname(student.getFirstname());
                stud.setLastname(student.getLastname());
            }
        }
    }

    @DeleteMapping("/student/{firstname}")
    public void deleteStudent(@PathVariable("firstname") String firstname){
        for (Student stud : students) {
            if (stud.getFirstname().equals(firstname)) {
                students.remove(stud);
                break;
            }
        }
    }
}
