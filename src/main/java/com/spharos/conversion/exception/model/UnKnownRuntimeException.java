package com.spharos.conversion.exception.model;

import com.spharos.conversion.exception.ConversionRuntimeException;

public class UnKnownRuntimeException extends ConversionRuntimeException {
    public UnKnownRuntimeException() {
        this("Un Known Server Exception");
    }

    public UnKnownRuntimeException(String message) {
        super(message);
    }
}
