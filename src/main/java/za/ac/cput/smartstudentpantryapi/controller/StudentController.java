package za.ac.cput.smartstudentpantryapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.smartstudentpantryapi.model.Student;
import za.ac.cput.smartstudentpantryapi.service.StudentService;

import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public ResponseEntity<Student> registerUser(@RequestBody Student student) {
        try {
            Student savedStudent = studentService.registerStudent(student);
            return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
        } catch (Exception e) {
            // This prints the exact database error to your IntelliJ console
            e.printStackTrace();
            // REMOVED the 'null' from this line
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Student> loginUser(@RequestBody Student loginRequest) {
        Optional<Student> student = studentService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());

        if (student.isPresent()) {
            return new ResponseEntity<>(student.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}