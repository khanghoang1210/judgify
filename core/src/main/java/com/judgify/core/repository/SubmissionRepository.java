package com.judgify.core.repository;

import com.judgify.core.entity.Submission;
import com.judgify.core.enums.SubmissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByStatus(SubmissionStatus status);

    Optional<Submission> findFirstByStatusOrderByCreatedAtAsc(SubmissionStatus status);
}
