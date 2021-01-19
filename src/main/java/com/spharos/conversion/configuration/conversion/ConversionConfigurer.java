package com.spharos.conversion.configuration.conversion;

import com.spharos.conversion.configuration.conversion.core.ConversionConfigurerAdaptor;
import com.spharos.conversion.configuration.conversion.core.manager.ProcessingManager;
import com.spharos.conversion.configuration.conversion.core.manager.ProviderProcessingManager;
import com.spharos.conversion.configuration.conversion.filter.ConversionFilter;
import com.spharos.conversion.configuration.conversion.provider.ByPathProvider;
import com.spharos.conversion.configuration.conversion.provider.SwapProvider;
import com.spharos.conversion.configuration.conversion.service.ByPathPreprocessingService;
import com.spharos.conversion.configuration.conversion.service.SwapPreprocessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConversionConfigurer extends ConversionConfigurerAdaptor {
    @Autowired
    private SwapPreprocessingService swapPreprocessingService;

    @Autowired
    private ByPathPreprocessingService byPathPreprocessingService;


    @Override
    protected ProcessingManager createProcessingManager() {
        //@formatter:off
        return ProviderProcessingManager.builder()
                .addProvider(
                        new ByPathProvider()
                                .setPreprocessingService(byPathPreprocessingService)
                                .getConversionRequests()
                                        .antMatchers("/**/mber/mber-spoint")
                                        .ok()

                )
                .addProvider(
                        new SwapProvider()
                                .setPreprocessingService(swapPreprocessingService)
                                .getConversionRequests()
                                        .antMatchers("/**")
                                        .ok()
                )
                .build();
        //@formatter:on
    }


    @Bean
    public ConversionFilter httpRequestWrapperFilter() {
        //@formatter:off
        return ConversionFilter.builder(super.getProcessingManager())
                    .setFilterChain(true)
                    .build();
        //@formatter:on
    }
}
