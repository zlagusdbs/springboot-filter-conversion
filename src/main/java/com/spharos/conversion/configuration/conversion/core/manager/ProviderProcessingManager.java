package com.spharos.conversion.configuration.conversion.core.manager;

import com.spharos.conversion.configuration.conversion.core.provider.ProcessingProvider;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProviderProcessingManager extends ProcessingManager {
    private ProviderProcessingManager(ConversionProviderProcessingManagerBuilder conversionProviderManagerBuilder) {
        super.processingProviders = conversionProviderManagerBuilder.processingProviders;
    }


    public static ProviderProcessingManager.ConversionProviderProcessingManagerBuilder builder() {
        return new ProviderProcessingManager.ConversionProviderProcessingManagerBuilder();
    }


    public static final class ConversionProviderProcessingManagerBuilder {
        Map<String, ProcessingProvider> processingProviders = new LinkedHashMap<>();

        public ConversionProviderProcessingManagerBuilder addProvider(ProcessingProvider processingProvider) {
            this.processingProviders.put(processingProvider.getClass().getName(), processingProvider);

            return this;
        }

        public ProviderProcessingManager build() {
            return new ProviderProcessingManager(this);
        }
    }
}
