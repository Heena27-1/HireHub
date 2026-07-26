package com.hirehub.hirehub_backend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiError {

    private boolean success;

    private String message;
}