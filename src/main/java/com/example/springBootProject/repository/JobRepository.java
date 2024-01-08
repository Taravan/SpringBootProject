package com.example.springBootProject.repository;

import com.example.springBootProject.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface with basic CRUD operations
 * for working with job model and its table in database.
 */

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    Optional<Job> findJobByName(String name);
}
