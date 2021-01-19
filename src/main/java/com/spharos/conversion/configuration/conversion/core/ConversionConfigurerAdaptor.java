package com.spharos.conversion.configuration.conversion.core;

import com.spharos.conversion.configuration.conversion.core.manager.ProcessingManager;

public abstract class ConversionConfigurerAdaptor {
    private ProcessingManager processingManager = null;


    protected ProcessingManager getProcessingManager() {
        if (this.processingManager == null)
            this.processingManager = this.createProcessingManager();

        return this.processingManager != null ? this.processingManager : null;
    }


    protected abstract ProcessingManager createProcessingManager();
}
