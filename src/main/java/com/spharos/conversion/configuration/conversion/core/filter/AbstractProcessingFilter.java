package com.spharos.conversion.configuration.conversion.core.filter;

import com.spharos.conversion.configuration.conversion.core.ConversionHttpServletRequest;
import com.spharos.conversion.configuration.conversion.core.manager.ProcessingManager;
import com.spharos.conversion.configuration.conversion.core.provider.ProcessingProvider;
import com.spharos.conversion.exception.model.FilterServiceProcessingRuntimeException;
import com.spharos.conversion.support.ConversionProperty;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Slf4j
public abstract class AbstractProcessingFilter extends OncePerRequestFilter {
    @Autowired
    private ConversionProperty conversionProperty;


    private ProcessingManager processingManager;
    private boolean isFilterChain;


    public void setProcessingManager(ProcessingManager processingManager) {
        this.processingManager = processingManager;
    }

    public void setFilterChain(boolean filterChain) {
        isFilterChain = filterChain;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        ConversionHttpServletRequest conversionHttpServletRequest = new ConversionHttpServletRequest(httpServletRequest);
        if (this.isTarget(conversionHttpServletRequest, this.conversionProperty.getTargets())) {
            try {
                ProcessingProvider processingProvider = this.findProvider(this.processingManager.processingProviders, conversionHttpServletRequest.getRequestURI())
                        .orElseThrow(
                                () -> new FilterServiceProcessingRuntimeException("not found provider")
                        );

                if (processingProvider.getPreprocessingService() != null)
                    processingProvider.getPreprocessingService().service(conversionHttpServletRequest, httpServletResponse);

                if (this.isFilterChain)
                    filterChain.doFilter(conversionHttpServletRequest, httpServletResponse);

                if (processingProvider.getPostprocessingService() != null)
                    processingProvider.getPostprocessingService().service(conversionHttpServletRequest, httpServletResponse);

            } catch(FilterServiceProcessingRuntimeException fspe){
                throw fspe;
            } catch (Exception e) {
                AbstractProcessingFilter.log.error("An internal error occurred while trying to filtering");
            }
        } else {
            filterChain.doFilter(conversionHttpServletRequest, httpServletResponse);
        }
    }


    public boolean isTarget(ConversionHttpServletRequest conversionHttpServletRequest, String[] targets) throws IOException {
        for (String target : conversionProperty.getTargets()) {
            if (io.micrometer.core.instrument.util.StringUtils.isNotBlank(conversionHttpServletRequest.getHeader(target)))
                return true;

            if (io.micrometer.core.instrument.util.StringUtils.isNotBlank(conversionHttpServletRequest.getBody().get(target)))
                return true;

            if (StringUtils.isNotBlank(conversionHttpServletRequest.getParameter(target)))
                return true;
        }

        return false;
    }


    public Optional<ProcessingProvider> findProvider(Map<String, ProcessingProvider> procProves, String uri) {
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        Map<String, ProcessingProvider> processingProviders = procProves;
        ProcessingProvider processingProvider;
        try {
            for (Map.Entry<String, ProcessingProvider> entry : processingProviders.entrySet()) {
                processingProvider = entry.getValue();
                for (String pattern : processingProvider.getConversionRequests().getPatterns()) {
                    if (antPathMatcher.match(pattern, uri))
                        return Optional.ofNullable(processingProvider);
                }
            }
        }catch(RuntimeException re){
            re.printStackTrace();
        }

        return Optional.empty();
    }
}
