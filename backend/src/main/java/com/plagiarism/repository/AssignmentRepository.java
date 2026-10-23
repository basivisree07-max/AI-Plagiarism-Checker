package com.plagiarism.repository;

import com.plagiarism.model.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AssignmentRepository
        extends MongoRepository<Assignment, String> {

    List<Assignment> findByTeacherId(String teacherId);
}