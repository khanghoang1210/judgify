package com.judgify.core.problem.exception;

public class ProblemNotFoundException extends RuntimeException {

    public ProblemNotFoundException(Long id) {
        super("Problem not found with id: " + id);
    }

    public ProblemNotFoundException(String slug) {
        super("Problem not found with slug: " + slug);
    }
}
