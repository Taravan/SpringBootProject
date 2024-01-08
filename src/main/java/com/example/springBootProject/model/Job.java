package com.example.springBootProject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that mirrors job database table.
 * Table can be created using this model.
 */

@Entity(name = "Job")
@Table(name = "job")
public class Job {

    @Id
    @SequenceGenerator(
            name = "job_sequence",
            sequenceName = "job_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "job_sequence"
    )
    @Column(name = "job_id", updatable = false)
    private Long id;
    @Column(name = "job_name", nullable = false)
    private String name;
    @Column(name = "job_description", nullable = false)
    private String description;

    // Relationships
    @JsonBackReference
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();


    // Constructors
    public Job() {
    }

    public Job(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + description + '\'' +
                '}';
    }
}
