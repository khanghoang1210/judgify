package com.judgify.core.problem.dto;

import com.judgify.core.problem.entity.TestCase;

import java.time.LocalDateTime;

public record TestCaseResponse(
        Long id,
        Long problemId,
        String input,
        String expectedOutput,
        boolean sample,
        Integer orderIndex,
        LocalDateTime createdAt
) {
    public static TestCaseResponse from(TestCase testCase) {
        return new TestCaseResponse(
                testCase.getId(),
                testCase.getProblem() != null ? testCase.getProblem().getId() : null,
                testCase.getInput(),
                testCase.getExpectedOutput(),
                testCase.isSample(),
                testCase.getOrderIndex(),
                testCase.getCreatedAt()
        );
    }
}

