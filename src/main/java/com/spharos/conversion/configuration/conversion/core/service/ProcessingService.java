package com.spharos.conversion.configuration.conversion.core.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ProcessingService {
    void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
