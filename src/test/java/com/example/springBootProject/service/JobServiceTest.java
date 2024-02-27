package com.example.springBootProject.service;

import com.example.springBootProject.dto.JobDTO;
import com.example.springBootProject.dto.NewJobDTO;
import com.example.springBootProject.dto.UpdateJobDTO;
import com.example.springBootProject.exception.customException.AlreadyExistsException;
import com.example.springBootProject.exception.customException.DoesNotExistException;
import com.example.springBootProject.model.Job;
import com.example.springBootProject.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.aggregator.ArgumentAccessException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobServiceTest {

    @InjectMocks
    private JobService underTest;

    @Mock
    private JobRepository repository;
    @Mock
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new JobService(repository, messageSource);
    }


    @Test
    public void testGetAllJobs() {
        // Given
        Job job1 = new Job("Job1", "Desc of job 1.");
        JobDTO jobDTO1 = new JobDTO(null,"Job1", "Desc of job 1.");

        // Mocking
        when(repository.findAll())
                .thenReturn(new ArrayList<>(List.of(job1)));

        // When
        List<JobDTO> returnedJobs = underTest.getJobs();

        // Then
        assertIterableEquals(new ArrayList<>(List.of(jobDTO1)), returnedJobs);
    }

    @Test
    public void testGetJobIfPresent() {
        // Given
        Job job = new Job("Job1", "Desc of job 1.");
        JobDTO jobDTO = new JobDTO(null,"Job1", "Desc of job 1.");

        // Mocking
        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(job));

        // When
        JobDTO returnedJob = underTest.getJob(1L, Locale.ENGLISH);

        // Then
        assertEquals(jobDTO, returnedJob);
    }

    @Test
    public void testGetJobThatDoesNotExist() {
        // Given
        JobDTO jobDTO = new JobDTO(null,"Job1", "Desc of job 1.");

        // Mocking
        when(repository.findById(anyLong()))
                .thenReturn(Optional.empty());

        // When
        // Then
        assertThrows(DoesNotExistException.class, () -> underTest.getJob(1L, Locale.ENGLISH));
    }

    @Test
    public void testCreateNewJobIfNotPresentYet() {
        // Given
        Job job = new Job("Job1", "Desc of job 1.");
        NewJobDTO newJobDTO = new NewJobDTO("Job1", "Desc of job 1.");
        JobDTO jobDTO = new JobDTO(null ,"Job1", "Desc of job 1.");

        // Mocking
        when(repository.save(any(Job.class)))
                .thenReturn(job);
        when(repository.findJobByName(anyString()))
                .thenReturn(Optional.empty());

        // When
        JobDTO returnedJob = underTest.addNewJob(newJobDTO, Locale.ENGLISH);

        // Then
        assertEquals(jobDTO, returnedJob);
        verify(repository, times(1)).save(any(Job.class));
    }

    @Test
    public void testCreateNewJobIfAlreadyPresent() {
        // Given
        NewJobDTO newJobDTO = new NewJobDTO("Job1", "Desc of job 1.");

        // Mocking
        when(repository.findJobByName(anyString()))
                .thenReturn(Optional.of(new Job()));
        //when(messageSource.getMessage(anyString(), any(), any()))
        // .thenReturn("");

        // When
        // Then
        assertThrows(AlreadyExistsException.class, () -> underTest.addNewJob(newJobDTO, Locale.ENGLISH));
        verify(repository, never()).save(any(Job.class));
    }

    @Test
    public void testDeleteJobIfExists() {
        // Given
        Long jobId = 1L;

        // Mocking
        when(repository.existsById(anyLong()))
                .thenReturn(true);
        doNothing().when(repository).deleteById(anyLong());

        // When
        // Then
        assertAll(() -> underTest.deleteJob(jobId, Locale.ENGLISH));
    }

    @Test
    public void testDeleteJobIfNotExist() {
        // Given
        Long jobId = 1L;

        // Mocking
        when(repository.existsById(anyLong()))
                .thenReturn(false);

        // When
        // Then
        assertThrows(DoesNotExistException.class, () -> underTest.deleteJob(jobId, Locale.ENGLISH));
    }

    // TODO
    @Test
    public void testUpdateJobIfExist() {
        // Given
        JobDTO jobDTO = new JobDTO(null,"Job1", "Desc of job 1.");

        // Mocking
        when(repository.findById(anyLong()))
                .thenReturn(Optional.empty());

        // When
        // Then
    }

    @Test
    public void testUpdateJobIfNotExist() {
        // Given
        Long jobId = 1L;
        UpdateJobDTO jobDTO = new UpdateJobDTO("Job1", "Desc of job 1.");

        // Mocking
        when(repository.findById(anyLong()))
                .thenReturn(Optional.empty());

        // When
        // Then
        assertThrows(DoesNotExistException.class, () -> underTest.updateJob(jobId, jobDTO, Locale.ENGLISH));
    }

    // TODO
    @Test
    public void testUpdateJobIfNameAlreadyTaken() {
        // Given
        JobDTO jobDTO = new JobDTO(null,"Job1", "Desc of job 1.");

        // Mocking
        when(repository.findById(anyLong()))
                .thenReturn(Optional.empty());

        // When
        // Then
    }

}