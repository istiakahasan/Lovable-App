package com.example.lovable_App.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;

public record ApiError (HttpStatus status,
                        String message,
                        Instant  timestamp,
                        @JsonInclude(JsonInclude.Include.NON_NULL)
                        List<ApiFieldError> errors) {

    public ApiError(HttpStatus status, String message) {
        this(status, message, Instant.now(), null);
    }//Here we are doing constructor *chaining*  so that Instant.now() should not be declared every time


    public ApiError(HttpStatus status, String message, List<ApiFieldError> errors) {
        this(status, message, Instant.now(), errors);
    }
}
record ApiFieldError(String field,String message){}