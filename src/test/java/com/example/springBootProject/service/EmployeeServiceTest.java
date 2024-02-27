package com.example.springBootProject.service;

import com.example.springBootProject.model.Employee;
import com.example.springBootProject.model.Job;
import com.example.springBootProject.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService underTest;

    @Mock
    private EmployeeRepository repository;
    @Mock
    private JobService jobService;
    @Mock
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSaveAnEmployee() {
        // Given


        // When

        // Then

    }
}