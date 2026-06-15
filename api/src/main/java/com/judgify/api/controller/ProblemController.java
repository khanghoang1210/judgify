package com.judgify.api.controller;

import com.judgify.core.problem.service.ProblemService;
import com.judgify.core.problem.dto.ProblemListResponse;
import com.judgify.core.problem.dto.ProblemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
@Tag(name = "Problems", description = "Public problem APIs")
public class ProblemController {

    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping
    @Operation(summary = "Get all published problems")
    public ResponseEntity<List<ProblemListResponse>> getProblems() {
        List<ProblemListResponse> problems = problemService.getPublishedProblems();
        return ResponseEntity.ok(problems);
    }

    @GetMapping("/{slug}")
    @Operation(summary = "Get a published problem by slug")
    public ResponseEntity<ProblemResponse> getProblemBySlug(@PathVariable String slug) {
        ProblemResponse problem = problemService.getPublishedProblemBySlug(slug);
        return ResponseEntity.ok(problem);
    }
}
