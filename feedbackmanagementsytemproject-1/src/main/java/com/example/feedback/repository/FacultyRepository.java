package com.example.feedback.repository;

import com.example.feedback.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findBySchoolName(String schoolName);
    List<Faculty> findByDepartment(String department);

}