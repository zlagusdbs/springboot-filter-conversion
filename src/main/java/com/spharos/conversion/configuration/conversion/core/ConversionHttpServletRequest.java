package com.spharos.conversion.configuration.conversion.core;

import com.spharos.conversion.support.CommonUtility;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConversionHttpServletRequest extends HttpServletRequestWrapper {
    public ConversionHttpServletRequest(HttpServletRequest request) {
        super(request);

        this.initHeader();
        this.initBody();
    }


    private Map<String, String> header;
    private Map<String, String> body;


    public Map<String, String> getHeader() {
        return header;
    }

    public Map<String, String> getBody() {
        return body;
    }


    private void initHeader(){
        this.header = new HashMap<>();
    }

    private void initBody() {
        if (!StringUtils.equalsIgnoreCase(this.getMethod(), "POST") && !StringUtils.equalsIgnoreCase(this.getMethod(), "PUT")) {
            this.body = new HashMap<>();

            return;
        }

        try (
                InputStream inputStream = this.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
        ) {
            StringBuilder stringBuilder = new StringBuilder();
            char[] charBuffer = new char[128];
            int bytesRead = bufferedReader.read(charBuffer);
            while (bytesRead > 0) {
                stringBuilder.append(charBuffer, 0, bytesRead);
                bytesRead = bufferedReader.read(charBuffer);
            }

            this.body = CommonUtility.objectMapper.readValue(stringBuilder.toString(), HashMap.class);
        } catch (Exception e) {
            this.body = new HashMap<>();
        }
    }


    public void addHeader(String name, String value) {
        header.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        String headerValue = super.getHeader(name);
        if (header.containsKey(name))
            headerValue = header.get(name);

        return headerValue;
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        for (String name : header.keySet())
            names.add(name);

        return Collections.enumeration(names);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> values = Collections.list(super.getHeaders(name));
        if (header.containsKey(name))
            values.add(header.get(name));

        return Collections.enumeration(values);
    }
}
