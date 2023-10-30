package com.example.demoBoot.controllers;

import com.example.demoBoot.beans.Student;
import com.example.demoBoot.exceptions.ResourceNotFoundException;
import com.example.demoBoot.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(maxAge = 3600,origins = "http://localhost:3000")
public class StudentController {
    //public static List<Student> students = new ArrayList<Student>();
    @Autowired
    private StudentRepository studentRepository;
/*
    public StudentController(){
        students.add(new Student("Carlos", "Lopez"));
        students.add(new Student("Miguel", "Ramirez"));
        students.add(new Student("Jairo", "Vargas"));
        students.add(new Student("Camilo", "Garcia"));
        students.add(new Student("Sofia", "Franco"));
        students.add(new Student("Ana", "Sanchez"));
    }
*/
    @GetMapping("/students")
    public List<Student> getStudents(){
        //return students;
        return (List<Student>) studentRepository.findAll();
    }

    /* */
    @GetMapping("/student/{firstname}/{lastname}")
    public Student studentPathVariable(@PathVariable("firstname") String firstname,
                                       @PathVariable("lastname") String lastname ){
        return new Student(firstname, lastname);
    }
    /* */
    // get employee by id rest api
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getEmployeeById(@PathVariable Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not exist with id :" + id));
        return ResponseEntity.ok(student);
    }
    /* */


    @GetMapping("/student/query")
    public Student studentQueryParam(@RequestParam(name="firstname") String firstname,
                                   @RequestParam(name="lastname") String lastname ){
        return new Student(firstname, lastname);
    }

    /* */
    @PostMapping("/student")
    public Student addStudent(@RequestBody Student student ){
        return studentRepository.save(student);
    }
    /* */
    /* * /
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
    /* */

    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/student/{id}")
    public ResponseEntity<Student> updateEmployee(@PathVariable Long id, @RequestBody Student studentDetails){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        student.setFirstname(studentDetails.getFirstname());
        student.setLastname(studentDetails.getLastname());

        Student updatedEmployee = studentRepository.save(student);
        return ResponseEntity.ok(updatedEmployee);
    }

    /* * /
    @DeleteMapping("/student/{firstname}")
    public void deleteStudent(@PathVariable("firstname") String firstname){
        for (Student stud : students) {
            if (stud.getFirstname().equals(firstname)) {
                students.remove(stud);
                break;
            }
        }
    }
    */

    // delete employee rest api
    @DeleteMapping("/student/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id){
        Student employee = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        studentRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
