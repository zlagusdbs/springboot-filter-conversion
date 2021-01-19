package com.spharos.conversion.controller;

import com.spharos.conversion.configuration.conversion.core.ConversionHttpServletRequest;
import com.spharos.conversion.service.ConversionProcessingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Slf4j
public class ConversionProcessingController {
    @Autowired
    private ConversionProcessingService conversionProcessingService;


    @RequestMapping("/**")
    public void conversion(ConversionHttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        conversionProcessingService.conversion(httpServletRequest, httpServletResponse);
    }
}
