package com.example.springBootProject.controller;

import com.example.springBootProject.dto.NewJobDTO;
import com.example.springBootProject.dto.UpdateJobDTO;
import com.example.springBootProject.dto.JobDTO;
import com.example.springBootProject.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

/**
 *  Controller for handling all requests manipulating with jobs.
 */

@RestController
@RequestMapping(path = "api/v1/job")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<JobDTO> getJobs() {
        return jobService.getJobs();
    }

    @GetMapping(path = "{jobId}")
    public JobDTO getJob(@PathVariable("jobId") Long jobId,
                         @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return jobService.getJob(jobId, locale);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JobDTO createNewJob(@Valid @RequestBody NewJobDTO job,
                                       @RequestHeader(value = "Accept-Language", required = false)Locale locale) {
        return jobService.addNewJob(job, locale);
    }

    @DeleteMapping(path = "{jobId}")
    public void deleteJob(@PathVariable("jobId") Long jobId,
                          @RequestHeader(value = "Accept-Language", required = false)Locale locale) {
        jobService.deleteJob(jobId, locale);
    }

    @PutMapping(path = "{jobId}")
    public void updateJob(@PathVariable("jobId") Long jobId,
                          @Valid @RequestBody UpdateJobDTO jobDTO,
                          @RequestHeader(value = "Accept-Language", required = false)Locale locale) {
        jobService.updateJob(jobId, jobDTO, locale);
    }

}
