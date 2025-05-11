package com.example.feedback.controller;

import com.example.feedback.model.Faculty;
import com.example.feedback.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty")
@CrossOrigin
public class FacultyController {

    @Autowired
    private FacultyRepository facultyRepository;

    @GetMapping("/{schoolName}")
    public List<Faculty> getFacultyBySchool(@PathVariable String schoolName) {
        return facultyRepository.findBySchoolName(schoolName);
    }
}
