package com.judgify.core.problem.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TestCaseCreateRequest(
        @NotNull(message = "Input is required")
        String input,

        @NotNull(message = "Expected output is required")
        String expectedOutput,

        Boolean sample,

        @Min(value = 0, message = "Order index must be greater than or equal to 0")
        Integer orderIndex
) {
    public TestCaseCreateRequest {
        if (sample == null) sample = false;
        if (orderIndex == null) orderIndex = 0;
    }
}

