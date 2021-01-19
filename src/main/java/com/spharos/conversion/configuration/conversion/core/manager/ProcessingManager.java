package com.spharos.conversion.configuration.conversion.core.manager;

import com.spharos.conversion.configuration.conversion.core.provider.ProcessingProvider;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class ProcessingManager {
    public Map<String, ProcessingProvider> processingProviders = new LinkedHashMap<>();
}
