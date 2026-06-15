package com.judgify.core.problem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.judgify.core.problem.entity.Submission;
import com.judgify.core.problem.enums.SubmissionStatus;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

	List<Submission> findByStatus(SubmissionStatus status);

	Optional<Submission> findFirstByStatusOrderByCreatedAtAsc(SubmissionStatus status);
}
