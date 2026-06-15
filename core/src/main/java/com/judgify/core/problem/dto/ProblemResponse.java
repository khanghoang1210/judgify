package com.judgify.core.problem.dto;

import com.judgify.core.problem.entity.Problem;
import com.judgify.core.problem.enums.Difficulty;

import java.time.LocalDateTime;

public record ProblemResponse(
        Long id,
        String title,
        String slug,
        String description,
        Difficulty difficulty,
        int timeLimitMs,
        int memoryLimitMb,
        boolean published,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static ProblemResponse from(Problem problem) {
        return new ProblemResponse(
                problem.getId(),
                problem.getTitle(),
                problem.getSlug(),
                problem.getDescription(),
                problem.getDifficulty(),
                problem.getTimeLimitMs(),
                problem.getMemoryLimitMb(),
                problem.isPublished(),
                problem.getCreatedAt(),
                problem.getUpdatedAt()
        );
    }
}
