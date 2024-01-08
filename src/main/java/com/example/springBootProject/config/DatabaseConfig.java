package com.example.springBootProject.config;

import com.example.springBootProject.model.Employee;
import com.example.springBootProject.repository.EmployeeRepository;
import com.example.springBootProject.model.Job;
import com.example.springBootProject.repository.JobRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

/**
 * Filling database with some mocking data.
 */

@Configuration
public class DatabaseConfig {

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository, JobRepository jobRepository) {
        return args -> {

            Job uklizec = new Job("Uklizec", "Ten co uklizi.");
            Job vratny = new Job("Vratny", "Ten co otvira dvere.");

            jobRepository.saveAll(List.of(uklizec, vratny));


            Employee jan = new Employee("Jan", "Novak", LocalDate.of(2000, 1, 22), "123456789", uklizec);

            Employee franta = new Employee("Frantaísek", "Maly", LocalDate.of(2006, 4, 2), "923456259", vratny);

            employeeRepository.saveAll(List.of(jan, franta));
        };
    }
}
