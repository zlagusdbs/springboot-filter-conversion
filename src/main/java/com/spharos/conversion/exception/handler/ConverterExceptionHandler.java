package com.spharos.conversion.exception.handler;

import com.spharos.conversion.exception.mapper.ConverterExceptionMapper;
import com.spharos.conversion.exception.model.UnKnownRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = {ConverterExceptionMapper.class})
@Slf4j
public class ConverterExceptionHandler {
    @ExceptionHandler(value = {
            UnKnownRuntimeException.class,
            RuntimeException.class
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String runtimeException(RuntimeException re) {
        ConverterExceptionHandler.log.debug("ConverterExceptionHandler.runtimeException: " + re.getMessage());

        return "There was a problem with the server." + "\n" +
                "Please contact the administrator.";
    }

    @ExceptionHandler(value = {
            Exception.class
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String exception(Exception e) {
        ConverterExceptionHandler.log.debug("ConverterExceptionHandler.exception: " + e.getMessage());

        return "There was a problem with the server." + "\n" +
                "Please contact the administrator.";
    }
}
