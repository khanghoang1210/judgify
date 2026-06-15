package com.judgify.core.problem.dto;

import com.judgify.core.problem.enums.Difficulty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record ProblemUpdateRequest(
        String title,

        @Pattern(regexp = "^[a-z0-9]+(-[a-z0-9]+)*$", message = "Slug must be lowercase with hyphens only")
        String slug,

        String description,

        Difficulty difficulty,

        @Positive(message = "Time limit must be positive")
        Integer timeLimitMs,

        @Positive(message = "Memory limit must be positive")
        Integer memoryLimitMb,

        Boolean published
) {
}
