package com.spharos.conversion;

import com.spharos.conversion.support.ConversionProperty;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@Slf4j
public class ConversionApplicationTestSupport {
    @Autowired
    protected ConversionProperty conversionProperty;
}
