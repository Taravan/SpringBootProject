package com.example.springBootProject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

/**
 * Class that mirrors employee database table.
 * Table can be created using this model.
 */

@Entity(name = "Employee")
@Table(name = "employee",
        uniqueConstraints = {
            @UniqueConstraint(name = "employee_telephone_number_unique", columnNames = "employee_telephone_number")
        })
public class Employee {
    @Id
    @SequenceGenerator(
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    @Column(name = "employee_id", updatable = false)
    private Long id;
    @Column(name = "employee_first_name", nullable = false)
    private String firstName;
    @Column(name = "employee_last_name", nullable = false)
    private String lastName;
    @Transient
    private Integer age;
    @Column(name = "employee_date_of_birth", nullable = false)
    private LocalDate dob;
    @Column(name = "employee_telephone_number", nullable = false)
    private String telNum;

    //Relationships
    @JsonManagedReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    // Constructors
    public Employee() {
    }

    public Employee(String firstName, String lastName, LocalDate dob, String telNum, Job job){
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.telNum = telNum;
        this.job = job;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", telNum=" + telNum +
                '}';
    }
}
