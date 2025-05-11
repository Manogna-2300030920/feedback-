package com.example.feedback.model;

import jakarta.persistence.*; // or javax.persistence.* for older Spring versions
import lombok.Data;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Fixed typo: GenerateValue → GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true) // Fixed typo: mullable → nullable
    private String email;

    @Column(nullable = false)
    private String password;

    // Additional fields
    private String name;
    private String school;
    private String className; // "class" is a reserved keyword, use className
}
