package com.example.springBootProject.repository;

import com.example.springBootProject.model.Job;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JobRepositoryTest {

    @Autowired
    private JobRepository underTest;

    @Test
    void checkIfJobNameTaken() {
        // given
        String nameOfJob = "Gardener";
        Job job = new Job(nameOfJob, "Do some work in the garden.");
        underTest.save(job);
        // when
        Optional<Job> jobWithSameName = underTest.findJobByName(nameOfJob);
        // then
        assertThat(jobWithSameName).contains(job);
    }

    @Test
    void checkIfJobNameNotTaken() {
        // given
        String nameOfJob = "Gardener";
        // when
        Optional<Job> jobWithSameName = underTest.findJobByName(nameOfJob);
        // then
        assertThat(jobWithSameName).isEmpty();
    }
}