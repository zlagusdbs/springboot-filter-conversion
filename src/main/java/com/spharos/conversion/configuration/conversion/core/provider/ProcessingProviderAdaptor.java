package com.spharos.conversion.configuration.conversion.core.provider;

public abstract class ProcessingProviderAdaptor {
    public ProcessingProviderAdaptor(ProcessingProvider processingProvider) {
        if (this.processingProvider == null)
            this.processingProvider = processingProvider;
    }


    private ProcessingProvider processingProvider;


    protected ProcessingProvider getProcessingProvider() {
        return this.processingProvider;
    }


    public ProcessingProvider and(){
        return this.processingProvider;
    }

    public ProcessingProvider ok(){
        return this.processingProvider;
    }
}
