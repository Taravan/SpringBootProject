package com.example.springBootProject.dto.mapper;

import com.example.springBootProject.dto.JobDTO;
import com.example.springBootProject.dto.NewJobDTO;
import com.example.springBootProject.dto.UpdateJobDTO;
import com.example.springBootProject.model.Job;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 *  Interface for mapping job DTOs to models and vice versa using map struct.
 */

@Mapper
public interface JobMapper {

    JobMapper INSTANCE = Mappers.getMapper(JobMapper.class);

    Job newJobDTOToJob(NewJobDTO jobDTO);

    void updateJobDTOToJob(UpdateJobDTO jobDTO, @MappingTarget Job job);

    JobDTO jobToJobDTO(Job job);

}
