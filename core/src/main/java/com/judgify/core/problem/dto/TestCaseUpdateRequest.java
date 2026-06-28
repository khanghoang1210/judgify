package com.judgify.core.problem.dto;

import jakarta.validation.constraints.Min;

public record TestCaseUpdateRequest(
        String input,
        String expectedOutput,
        Boolean sample,

        @Min(value = 0, message = "Order index must be greater than or equal to 0")
        Integer orderIndex
) {
}

