package com.spharos.conversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(
        value = {
                "com.osc",
                "com.spharos.conversion"
        }
)
public class ConversionApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConversionApplication.class, args);
    }
}
