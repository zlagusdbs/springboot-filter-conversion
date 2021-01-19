package com.spharos.conversion.configuration.conversion.filter;

import com.spharos.conversion.configuration.conversion.core.filter.AbstractProcessingFilter;
import com.spharos.conversion.configuration.conversion.core.manager.ProcessingManager;
import com.spharos.conversion.exception.model.NotDefinedFieldRuntimeException;

public class ConversionFilter extends AbstractProcessingFilter {
    private ConversionFilter(ConversionFilterBuilder ConversionFilterBuilder) {
        super.setProcessingManager(ConversionFilterBuilder.processingManager);
        super.setFilterChain(ConversionFilterBuilder.isFilterChain);
    }


    public static final ConversionFilterBuilder builder(ProcessingManager processingManager) {
        if (processingManager == null)
            throw new NotDefinedFieldRuntimeException();

        return new ConversionFilterBuilder(processingManager);
    }


    public static class ConversionFilterBuilder {
        private ConversionFilterBuilder() {
        }

        private ConversionFilterBuilder(ProcessingManager processingManager) {
            this.processingManager = processingManager;
        }


        private ProcessingManager processingManager;
        private boolean isFilterChain;

        public ConversionFilterBuilder setFilterChain(boolean filterChain) {
            isFilterChain = filterChain;

            return this;
        }

        public ConversionFilter build() {
            return new ConversionFilter(this);
        }
    }
}
