package com.judgify.core.problem.dto;

import com.judgify.core.problem.enums.Difficulty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record ProblemCreateRequest(
        @NotBlank(message = "Title is required")
        String title,

        @NotBlank(message = "Slug is required")
        @Pattern(regexp = "^[a-z0-9]+(-[a-z0-9]+)*$", message = "Slug must be lowercase with hyphens only")
        String slug,

        String description,

        @NotNull(message = "Difficulty is required")
        Difficulty difficulty,

        @Positive(message = "Time limit must be positive")
        Integer timeLimitMs,

        @Positive(message = "Memory limit must be positive")
        Integer memoryLimitMb,

        Boolean published
) {
    public ProblemCreateRequest {
        if (timeLimitMs == null) timeLimitMs = 1000;
        if (memoryLimitMb == null) memoryLimitMb = 256;
        if (published == null) published = false;
    }
}
