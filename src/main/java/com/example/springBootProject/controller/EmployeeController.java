package com.example.springBootProject.controller;

import com.example.springBootProject.dto.EmployeeDTO;
import com.example.springBootProject.dto.UpdateEmployeeDTO;
import com.example.springBootProject.service.EmployeeService;
import com.example.springBootProject.dto.NewEmployeeDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

/**
 *  Controller for handling all requests manipulating with employees.
 */

@RestController
@RequestMapping(path = "api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable("employeeId") Long employeeId,
                                   @RequestHeader(value = "Accept-Language", required = false)Locale locale) {
        return employeeService.getEmployee(employeeId, locale);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO createNewEmployee(@Valid @RequestBody NewEmployeeDTO employee,
                                            @RequestHeader(value = "Accept-Language", required = false)Locale locale) {
        return employeeService.addNewEmployee(employee, locale);
    }

    @DeleteMapping(path = "{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") Long employeeId,
                               @RequestHeader(value = "Accept-Language", required = false)Locale locale) {
        employeeService.deleteEmployee(employeeId, locale);
    }

    @PutMapping(path = "{employeeId}")
    public void updateEmployee(
            @PathVariable("employeeId") Long employeeId,
            @Valid @RequestBody UpdateEmployeeDTO employee,
            @RequestHeader(value = "Accept-Language", required = false)Locale locale) {
        employeeService.updateEmployee(employeeId, employee, locale);
    }

}
