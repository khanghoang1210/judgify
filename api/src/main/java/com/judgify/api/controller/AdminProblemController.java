package com.judgify.api.controller;

import com.judgify.core.problem.service.ProblemService;
import com.judgify.core.problem.dto.ProblemCreateRequest;
import com.judgify.core.problem.dto.ProblemResponse;
import com.judgify.core.problem.dto.ProblemUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/problems")
@Tag(name = "Admin Problems", description = "Admin problem management APIs")
public class AdminProblemController {

    private final ProblemService problemService;

    public AdminProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @PostMapping
    @Operation(summary = "Create a new problem")
    public ResponseEntity<ProblemResponse> createProblem(@Valid @RequestBody ProblemCreateRequest request) {
        ProblemResponse problem = problemService.createProblem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(problem);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing problem")
    public ResponseEntity<ProblemResponse> updateProblem(
            @PathVariable Long id,
            @Valid @RequestBody ProblemUpdateRequest request) {
        ProblemResponse problem = problemService.updateProblem(id, request);
        return ResponseEntity.ok(problem);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a problem")
    public ResponseEntity<Void> deleteProblem(@PathVariable Long id) {
        problemService.deleteProblem(id);
        return ResponseEntity.noContent().build();
    }
}
