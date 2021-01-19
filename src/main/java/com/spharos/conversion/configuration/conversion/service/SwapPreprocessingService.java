package com.spharos.conversion.configuration.conversion.service;

import com.spharos.conversion.configuration.conversion.core.ConversionHttpServletRequest;
import com.spharos.conversion.configuration.conversion.core.service.PreprocessingService;
import com.spharos.conversion.exception.model.NoSuchMberRuntimeException;
import com.spharos.conversion.repository.MberCustMapngRepository;
import com.spharos.conversion.support.ConversionProperty;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class SwapPreprocessingService implements PreprocessingService {
    @Autowired
    private ConversionProperty conversionProperty;

    @Autowired
    private MberCustMapngRepository mberCustMapngRepository;


    @Override
    public void service(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String custid = this.findCustid(((ConversionHttpServletRequest) httpServletRequest), this.conversionProperty.getTargets());

        ((ConversionHttpServletRequest) httpServletRequest).addHeader("custId", custid);
        ((ConversionHttpServletRequest) httpServletRequest).addHeader(
                "mberNo",
                mberCustMapngRepository.findByCustid(custid)
                        .orElseThrow(
                                () -> new NoSuchMberRuntimeException()
                        )
                        .getMberNo()
        );
    }

    private String findCustid(ConversionHttpServletRequest conversionHttpServletRequest, String... targets) {
        for (String target : targets) {
            if (StringUtils.isNotBlank(conversionHttpServletRequest.getHeader(target)))
                return conversionHttpServletRequest.getHeader(target);

            if (StringUtils.isNotBlank(conversionHttpServletRequest.getBody().get(target)))
                return conversionHttpServletRequest.getBody().get(target);

            if (StringUtils.isNotBlank(conversionHttpServletRequest.getParameter(target)))
                return conversionHttpServletRequest.getParameter(target);
        }

        return null;
    }
}
