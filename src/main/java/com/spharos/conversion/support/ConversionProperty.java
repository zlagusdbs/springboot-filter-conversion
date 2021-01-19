package com.spharos.conversion.support;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ConversionProperty {
    @Value("${spring.profiles.active}")
    private String configProfileActive;

    @Value("${spring.config.additional-location}")
    private String configAdditionalLocation;

    @Value("${spharos.conversion.targets}")
    private String[] targets;

    @Value("${interface.url.api}")
    private String apiDomain;
}
