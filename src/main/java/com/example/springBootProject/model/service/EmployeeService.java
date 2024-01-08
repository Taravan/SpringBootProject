package com.example.springBootProject.model.service;

import com.example.springBootProject.dto.EmployeeDTO;
import com.example.springBootProject.dto.NewEmployeeDTO;
import com.example.springBootProject.dto.UpdateEmployeeDTO;
import com.example.springBootProject.dto.mapper.EmployeeMapper;
import com.example.springBootProject.repository.EmployeeRepository;
import com.example.springBootProject.exception.customException.AlreadyExistsException;
import com.example.springBootProject.exception.customException.DoesNotExistException;
import com.example.springBootProject.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for employee business logic.
 */

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final JobService jobService;
    private final MessageSource messageSource;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, JobService jobService, MessageSource messageSource) {
        this.employeeRepository = employeeRepository;
        this.jobService = jobService;
        this.messageSource = messageSource;
    }

    public List<EmployeeDTO> getEmployees() {
        System.out.println(employeeRepository.findAll());
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper.INSTANCE::employeeToEmployeeDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getEmployee(Long employeeId, Locale locale) {
        return employeeRepository.findById(employeeId)
                .map(EmployeeMapper.INSTANCE::employeeToEmployeeDTO)
                .orElseThrow(() -> new DoesNotExistException(
                        messageSource.getMessage("employee.id.notExist",
                                new Long[]{employeeId},
                                locale
                        )
                ));
    }

    public void addNewEmployee(NewEmployeeDTO employee, Locale locale) {
        Optional<Employee> employeeByTelNum = employeeRepository
                .findEmployeeByTelNum(employee.telNum());
        if (employeeByTelNum.isPresent()) {
            throw new AlreadyExistsException(
                    messageSource.getMessage("employee.telNum.exist",
                            new String[]{employee.telNum()},
                            locale
                    )
            );
        }
        if (!jobService.jobExists(employee.jobId())) {
            throw new DoesNotExistException(
                    messageSource.getMessage("job.id.notExist",
                            new Long[]{employee.jobId()},
                            locale)
            );
        }

        employeeRepository.save(EmployeeMapper.INSTANCE.newEmployeeDTOToEmployee(employee));

    }

    public void deleteEmployee(Long employeeId, Locale locale) {
        boolean exists = employeeRepository.existsById(employeeId);
        if (!exists) {
            throw new DoesNotExistException(
                    messageSource.getMessage("employee.id.notExist",
                            new Long[]{employeeId},
                            locale
                    )
            );
        }
        employeeRepository.deleteById(employeeId);
    }

    @Transactional
    public void updateEmployee(Long employeeId, UpdateEmployeeDTO employeeDTO, Locale locale) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new DoesNotExistException(
                        messageSource.getMessage(
                                "employee.id.notExist",
                                new Long[]{employeeId},
                                locale
                        )
                ));

        Optional<Employee> employeeByTelNum = employeeRepository
                .findEmployeeByTelNum(employeeDTO.telNum());
        if (employeeByTelNum.isPresent() && !Objects.equals(employee.getTelNum(), employeeDTO.telNum())) {
            throw new AlreadyExistsException(
                    messageSource.getMessage("employee.telNum.exist",
                            new String[]{employeeDTO.telNum()},
                            locale
                    )
            );
        }

        EmployeeMapper.INSTANCE.updateEmployeeDTOToEmployee(employeeDTO, employee);
        employee.setJob(jobService.getJobById(employeeDTO.jobId(), locale));
    }
}
