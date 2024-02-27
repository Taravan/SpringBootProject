package com.example.springBootProject.repository;

import com.example.springBootProject.model.Employee;
import com.example.springBootProject.model.Job;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository underTest;
    @Autowired
    private JobRepository jobRepository;

    @Test
    void checkIfTelNumTakenByAnotherEmployee() {
        // given
        String telNum = "123456789";
        Job job = new Job("Technician", "Do some work.");
        jobRepository.save(job);
        Employee employee = new Employee(
                "Tom",
                "Novak",
                LocalDate.of(2014, 12, 25),
                telNum,
                job
        );
        underTest.save(employee);
        // when
        Optional<Employee> employeeWithTelNumExists = underTest.findEmployeeByTelNum(telNum);
        //then
        assertThat(employeeWithTelNumExists).isPresent();
        assertThat(employeeWithTelNumExists).contains(employee);
    }

    @Test
    void checkIfTelNumNotTakenByAnotherEmployee() {
        // given
        String telNum = "123456789";
        // when
        Optional<Employee> employeeWithTelNumExists = underTest.findEmployeeByTelNum(telNum);
        // then
        assertThat(employeeWithTelNumExists).isEmpty();
    }
}