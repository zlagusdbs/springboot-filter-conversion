package com.spharos.conversion.exception.model;

import com.spharos.conversion.exception.ConversionRuntimeException;

public class NotDefinedFieldRuntimeException extends ConversionRuntimeException {
    public NotDefinedFieldRuntimeException() {
        this("not defined field, plz check the field");
    }

    public NotDefinedFieldRuntimeException(String message) {
        super(message);
    }
}
