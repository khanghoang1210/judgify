package com.judgify.core.problem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.judgify.core.problem.entity.Problem;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long> {

	Optional<Problem> findBySlug(String slug);

	Optional<Problem> findBySlugAndPublishedTrue(String slug);

	List<Problem> findAllByPublishedTrue();

	boolean existsBySlug(String slug);
}
