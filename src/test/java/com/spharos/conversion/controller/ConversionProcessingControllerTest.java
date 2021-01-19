package com.spharos.conversion.controller;

import com.spharos.conversion.ConversionApplicationTestSupport;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Slf4j
class ConversionProcessingControllerTest extends ConversionApplicationTestSupport {
    private int threadPoolCount = 10;
    private ExecutorService executorService = Executors.newFixedThreadPool(this.threadPoolCount);


    @Test
    public void conversion() {
        this.assertThat("C000000001");
        this.assertThat("C000000002");
        this.assertThat("C000000003");
        this.assertThat("C000000004");
        this.assertThat("C000000005");

        this.executorService.shutdown();
    }

    public void assertThat(String mberno) {
        for (int i = 0; i < 100; i++) {
            this.executorService.submit(
                    () -> {
                        WebClient.builder().build();

                        //request mberno와 response mberno가 같아야한다.
                    }
            );
        }
    }
}
