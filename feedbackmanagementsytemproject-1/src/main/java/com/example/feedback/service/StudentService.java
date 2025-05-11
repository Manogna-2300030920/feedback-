package com.example.feedback.service;

import com.example.feedback.model.Student;
import com.example.feedback.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentService(StudentRepository studentRepo, PasswordEncoder passwordEncoder) {
        this.studentRepo = studentRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // Register a new student with encoded password
    public Student registerStudent(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepo.save(student);
    }

    // Login using email and password
    public Optional<Student> loginStudent(String email, String password) {
        Optional<Student> studentOptional = studentRepo.findByEmail(email);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if (passwordEncoder.matches(password, student.getPassword())) {
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }

    // Get a student by ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepo.findById(id);
    }

    // Login using username instead of email (if applicable)
    public Optional<Student> login(String username, String password) {
        Optional<Student> studentOptional = studentRepo.findByUsername(username);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if (passwordEncoder.matches(password, student.getPassword())) {
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }

    // Optional: Update student profile
    public Optional<Student> updateStudent(Long id, Student updatedStudent) {
        return studentRepo.findById(id).map(existingStudent -> {
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setUsername(updatedStudent.getUsername());
            // Only update password if provided
            if (updatedStudent.getPassword() != null && !updatedStudent.getPassword().isEmpty()) {
                existingStudent.setPassword(passwordEncoder.encode(updatedStudent.getPassword()));
            }
            return studentRepo.save(existingStudent);
        });
    }

    // Optional: Delete student by ID
    public boolean deleteStudent(Long id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return true;
        }
        return false;
    }
}