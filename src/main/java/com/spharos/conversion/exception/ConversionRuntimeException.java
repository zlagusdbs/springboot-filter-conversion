package com.spharos.conversion.exception;

public class ConversionRuntimeException extends RuntimeException {
    protected ConversionRuntimeException() {
        super();
    }

    protected ConversionRuntimeException(String message) {
        super(message);
    }

    protected ConversionRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    protected ConversionRuntimeException(Throwable cause) {
        super(cause);
    }

    protected ConversionRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
