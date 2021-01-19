package com.spharos.conversion.configuration.conversion.core.provider;

import com.spharos.conversion.configuration.conversion.core.ConversionRequests;
import com.spharos.conversion.configuration.conversion.core.service.PostprocessingService;
import com.spharos.conversion.configuration.conversion.core.service.PreprocessingService;

public class ProcessingProvider {
    protected ProcessingProvider() {
        if (this.conversionRequests == null)
            this.conversionRequests = new ConversionRequests(this);
    }


    private PreprocessingService preprocessingService;
    private PostprocessingService postprocessingService;
    private ConversionRequests conversionRequests;


    public ConversionRequests getConversionRequests() {
        return conversionRequests;
    }

    public void setConversionRequests(ConversionRequests conversionRequests) {
        this.conversionRequests = conversionRequests;
    }

    public PreprocessingService getPreprocessingService() {
        return preprocessingService;
    }

    public ProcessingProvider setPreprocessingService(PreprocessingService preprocessingService) {
        this.preprocessingService = preprocessingService;

        return this;
    }

    public PostprocessingService getPostprocessingService() {
        return postprocessingService;
    }

    public ProcessingProvider setPostprocessingService(PostprocessingService postprocessingService) {
        this.postprocessingService = postprocessingService;

        return this;
    }
}
