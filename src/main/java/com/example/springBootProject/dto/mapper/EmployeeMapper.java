package com.example.springBootProject.dto.mapper;

import com.example.springBootProject.dto.EmployeeDTO;
import com.example.springBootProject.dto.NewEmployeeDTO;
import com.example.springBootProject.dto.UpdateEmployeeDTO;
import com.example.springBootProject.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 *  Interface for mapping employee DTOs to models and vice versa using map struct.
 */

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "jobId", target = "job.id")
    Employee newEmployeeDTOToEmployee(NewEmployeeDTO employeeDTO);

    void updateEmployeeDTOToEmployee(UpdateEmployeeDTO employeeDTO, @MappingTarget Employee employee);

    EmployeeDTO employeeToEmployeeDTO(Employee employee);

}
