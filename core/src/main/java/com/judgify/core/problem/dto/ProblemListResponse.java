package com.judgify.core.problem.dto;

import com.judgify.core.problem.entity.Problem;
import com.judgify.core.problem.enums.Difficulty;

public record ProblemListResponse(
        Long id,
        String title,
        String slug,
        Difficulty difficulty
) {
    public static ProblemListResponse from(Problem problem) {
        return new ProblemListResponse(
                problem.getId(),
                problem.getTitle(),
                problem.getSlug(),
                problem.getDifficulty()
        );
    }
}
