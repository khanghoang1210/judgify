package com.judgify.core.problem.exception;

public class DuplicateSlugException extends RuntimeException {

    public DuplicateSlugException(String slug) {
        super("Problem with slug '" + slug + "' already exists");
    }
}
