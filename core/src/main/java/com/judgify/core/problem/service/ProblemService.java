package com.judgify.core.problem.service;

import com.judgify.core.problem.dto.ProblemCreateRequest;
import com.judgify.core.problem.dto.ProblemListResponse;
import com.judgify.core.problem.dto.ProblemResponse;
import com.judgify.core.problem.dto.ProblemUpdateRequest;
import com.judgify.core.problem.entity.Problem;
import com.judgify.core.problem.exception.DuplicateSlugException;
import com.judgify.core.problem.exception.ProblemNotFoundException;
import com.judgify.core.problem.repository.ProblemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Transactional(readOnly = true)
    public List<ProblemListResponse> getPublishedProblems() {
        return problemRepository.findAllByPublishedTrue()
                .stream()
                .map(ProblemListResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProblemResponse getPublishedProblemBySlug(String slug) {
        Problem problem = problemRepository.findBySlugAndPublishedTrue(slug)
                .orElseThrow(() -> new ProblemNotFoundException(slug));
        return ProblemResponse.from(problem);
    }

    @Transactional
    public ProblemResponse createProblem(ProblemCreateRequest request) {
        if (problemRepository.existsBySlug(request.slug())) {
            throw new DuplicateSlugException(request.slug());
        }

        Problem problem = Problem.builder()
                .title(request.title())
                .slug(request.slug())
                .description(request.description())
                .difficulty(request.difficulty())
                .timeLimitMs(request.timeLimitMs())
                .memoryLimitMb(request.memoryLimitMb())
                .published(request.published())
                .build();

        Problem saved = problemRepository.save(problem);
        return ProblemResponse.from(saved);
    }

    @Transactional
    public ProblemResponse updateProblem(Long id, ProblemUpdateRequest request) {
        Problem problem = problemRepository.findById(id)
                .orElseThrow(() -> new ProblemNotFoundException(id));

        if (request.slug() != null && !request.slug().equals(problem.getSlug())) {
            if (problemRepository.existsBySlug(request.slug())) {
                throw new DuplicateSlugException(request.slug());
            }
            problem.setSlug(request.slug());
        }

        if (request.title() != null) {
            problem.setTitle(request.title());
        }
        if (request.description() != null) {
            problem.setDescription(request.description());
        }
        if (request.difficulty() != null) {
            problem.setDifficulty(request.difficulty());
        }
        if (request.timeLimitMs() != null) {
            problem.setTimeLimitMs(request.timeLimitMs());
        }
        if (request.memoryLimitMb() != null) {
            problem.setMemoryLimitMb(request.memoryLimitMb());
        }
        if (request.published() != null) {
            problem.setPublished(request.published());
        }

        Problem saved = problemRepository.save(problem);
        return ProblemResponse.from(saved);
    }

    @Transactional
    public void deleteProblem(Long id) {
        if (!problemRepository.existsById(id)) {
            throw new ProblemNotFoundException(id);
        }
        problemRepository.deleteById(id);
    }
}
