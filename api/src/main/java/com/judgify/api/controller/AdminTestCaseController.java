package com.judgify.api.controller;

import com.judgify.api.service.TestCaseService;
import com.judgify.core.problem.dto.TestCaseCreateRequest;
import com.judgify.core.problem.dto.TestCaseResponse;
import com.judgify.core.problem.dto.TestCaseUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// TODO: add admin authorization
@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin Test Cases", description = "Admin test case management APIs")
public class AdminTestCaseController {

    private final TestCaseService testCaseService;

    public AdminTestCaseController(TestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    @GetMapping("/problems/{problemId}/test-cases")
    @Operation(summary = "List test cases of a problem")
    public ResponseEntity<List<TestCaseResponse>> listTestCases(@PathVariable Long problemId) {
        return ResponseEntity.ok(testCaseService.getTestCasesByProblemId(problemId));
    }

    @PostMapping("/problems/{problemId}/test-cases")
    @Operation(summary = "Create a new test case for a problem")
    public ResponseEntity<TestCaseResponse> createTestCase(
            @PathVariable Long problemId,
            @Valid @RequestBody TestCaseCreateRequest request) {
        TestCaseResponse created = testCaseService.createTestCase(problemId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/test-cases/{id}")
    @Operation(summary = "Update an existing test case")
    public ResponseEntity<TestCaseResponse> updateTestCase(
            @PathVariable Long id,
            @Valid @RequestBody TestCaseUpdateRequest request) {
        return ResponseEntity.ok(testCaseService.updateTestCase(id, request));
    }

    @DeleteMapping("/test-cases/{id}")
    @Operation(summary = "Delete a test case")
    public ResponseEntity<Void> deleteTestCase(@PathVariable Long id) {
        testCaseService.deleteTestCase(id);
        return ResponseEntity.noContent().build();
    }
}

