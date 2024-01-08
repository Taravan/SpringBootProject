package com.example.springBootProject.model.service;

import com.example.springBootProject.dto.JobDTO;
import com.example.springBootProject.dto.NewJobDTO;
import com.example.springBootProject.dto.UpdateJobDTO;
import com.example.springBootProject.dto.mapper.JobMapper;
import com.example.springBootProject.exception.customException.AlreadyExistsException;
import com.example.springBootProject.exception.customException.DoesNotExistException;
import com.example.springBootProject.repository.JobRepository;
import com.example.springBootProject.model.Job;
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
 * Service class for job business logic.
 */

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final MessageSource messageSource;

    @Autowired
    public JobService(JobRepository repository, MessageSource messageSource) {
        this.jobRepository = repository;
        this.messageSource = messageSource;
    }


    public List<JobDTO> getJobs() {
        return jobRepository.findAll()
                .stream()
                .map(JobMapper.INSTANCE::jobToJobDTO)
                .collect(Collectors.toList());
    }

    public JobDTO getJob(Long jobId, Locale locale) {
        return jobRepository.findById(jobId)
                .map(JobMapper.INSTANCE::jobToJobDTO)
                .orElseThrow(() -> new DoesNotExistException(
                        messageSource.getMessage(
                                "job.id.notExist",
                                new Long[]{jobId},
                                locale
                        )
                ));
    }

    public void addNewJob(NewJobDTO job, Locale locale) {
        Optional<Job> jobByName = jobRepository.findJobByName(job.name());
        if (jobByName.isPresent()) {
            throw new AlreadyExistsException(
                    messageSource.getMessage(
                            "job.name.exist",
                            new String[]{job.name()},
                            locale
                    )
            );
        }
        jobRepository.save(JobMapper.INSTANCE.newJobDTOToJob(job));
    }

    public void deleteJob(Long id, Locale locale) {
        boolean exists = jobRepository.existsById(id);
        if (!exists) {
            throw new DoesNotExistException(
                    messageSource.getMessage(
                            "job.id.notExist",
                            new Long[]{id},
                            locale
                    )
            );
        }
        jobRepository.deleteById(id);
    }

    @Transactional
    public void updateJob(Long jobId, UpdateJobDTO jobDTO, Locale locale) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new DoesNotExistException(
                        messageSource.getMessage(
                                "job.id.notExist",
                                new Long[]{jobId},
                                locale
                        )
                ));

        Optional<Job> jobByName = jobRepository.findJobByName(jobDTO.name());
        if (jobByName.isPresent() && !Objects.equals(job.getName(), jobDTO.name())) {
            throw new AlreadyExistsException(
                    messageSource.getMessage(
                            "job.name.exist",
                            new String[]{jobDTO.name()},
                            locale
                    )
            );
        }

        JobMapper.INSTANCE.updateJobDTOToJob(jobDTO, job);
    }


    // NOT methods for controller
    public boolean jobExists(Long jobId) {
        return jobRepository.existsById(jobId);
    }

    public Job getJobById(Long jobId, Locale locale) {
        return jobRepository.findById(jobId)
                .orElseThrow(() -> new DoesNotExistException(
                        messageSource.getMessage(
                                "job.id.notExist",
                                new Long[]{jobId},
                                locale
                        )
                ));
    }
}
