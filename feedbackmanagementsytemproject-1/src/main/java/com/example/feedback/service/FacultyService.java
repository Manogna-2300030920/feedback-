package com.example.feedback.service;

import com.example.feedback.model.Faculty;
import com.example.feedback.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public List<Faculty> getAllFacultyByDepartment(String department) {
        return facultyRepository.findByDepartment(department);
    }
}
