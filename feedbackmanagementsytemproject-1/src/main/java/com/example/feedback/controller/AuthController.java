package com.example.feedback.controller;

import com.example.feedback.model.Student;
import com.example.feedback.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private StudentService studentService;

    // Register a new student
    @PostMapping("/register")
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        Student registeredStudent = studentService.registerStudent(student);
        return new ResponseEntity<>(registeredStudent, HttpStatus.CREATED);
    }

    // Login and validate credentials
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (username != null && password != null) {
            Optional<Student> studentOptional = studentService.login(username, password);
            if (studentOptional.isPresent()) {
                return ResponseEntity.ok(studentOptional.get());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
            }
        } else {
            return ResponseEntity.badRequest().body("Username and password are required");
        }
    }
}
