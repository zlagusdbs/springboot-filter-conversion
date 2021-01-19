package com.spharos.conversion.service;

import com.spharos.conversion.configuration.conversion.core.ConversionHttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@Slf4j
public class ConversionProcessingService {
    public void conversion(ConversionHttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        if (log.isDebugEnabled())
            this.logging(httpServletRequest);


        // TODO: WebClient !!!!!!!!!!
        WebClient.builder()
                .baseUrl("http://localhost:5555")
                .build()
                .post()
                .uri("/api/virtual/cancel")         // URI+URL Parameter
                .headers(                              // Header
                        httpHeader -> {
                            httpHeader.add("headerName", "headerValue");
                        }
                )
                .body(                                 // Body Parameter
                        BodyInserters.fromFormData("name", "value")
                                .with("additional name", "additional value")
                )
                .exchange()
                .block();
    }

    private void logging(ConversionHttpServletRequest httpServletRequest) {
        ConversionProcessingService.log.debug("==============================Request==============================");
        ConversionProcessingService.log.debug("ConversionProcessingService.conversion: " + httpServletRequest.getRequestURI());

        ConversionProcessingService.log.debug("==============================Header==============================");
        httpServletRequest.getHeader().forEach((key, value) -> ConversionProcessingService.log.debug("key:" + key + "\t" + "value:" + value));

        ConversionProcessingService.log.debug("==============================Body==============================");
        httpServletRequest.getBody().forEach((key, value) -> ConversionProcessingService.log.debug("key:" + key + "\t" + "value:" + value));
    }
}
