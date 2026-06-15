package com.judgify.core.problem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.judgify.core.problem.entity.TestCase;

@Repository
public interface TestCaseRepository extends JpaRepository<TestCase, Long> {

	List<TestCase> findByProblemIdOrderByOrderIndexAsc(Long problemId);
}
