package com.judgify.core.repository;

import com.judgify.core.entity.SubmissionResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionResultRepository extends JpaRepository<SubmissionResult, Long> {

    List<SubmissionResult> findBySubmissionId(Long submissionId);
}
