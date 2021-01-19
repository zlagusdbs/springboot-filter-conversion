package com.spharos.conversion.configuration.conversion.core;

import com.spharos.conversion.configuration.conversion.core.provider.ProcessingProvider;
import com.spharos.conversion.configuration.conversion.core.provider.ProcessingProviderAdaptor;

public class ConversionRequests extends ProcessingProviderAdaptor {
    public ConversionRequests(ProcessingProvider processingProvider) {
        super(processingProvider);
    }


    private String[] patterns;


    public String[] getPatterns() {
        return patterns;
    }

    public ConversionRequests antMatchers(String... patterns) {
        this.patterns = patterns;

        return this;
    }
}
