package za.ac.cput.smartstudentpantryapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.smartstudentpantryapi.model.Student;
import za.ac.cput.smartstudentpantryapi.repository.StudentRepository;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentService(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Student registerStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    public Optional<Student> authenticate(String email, String rawPassword) {
        Optional<Student> student = studentRepository.findByEmail(email);
        if (student.isPresent() && passwordEncoder.matches(rawPassword, student.get().getPassword())) {
            return student;
        }
        return Optional.empty();
    }
}