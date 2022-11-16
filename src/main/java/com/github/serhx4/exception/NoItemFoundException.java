package com.github.serhx4.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ResponseStatusException;

public class NoItemFoundException extends ResponseStatusException {

    public NoItemFoundException(HttpStatus status) {
        super(status);
    }

    public NoItemFoundException(HttpStatus status, @Nullable String reason) {
        super(status, reason);
    }
}
