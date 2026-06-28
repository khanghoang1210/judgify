package com.judgify.core.problem.exception;

public class TestCaseNotFoundException extends RuntimeException {

    public TestCaseNotFoundException(Long id) {
        super("Test case not found with id: " + id);
    }
}

