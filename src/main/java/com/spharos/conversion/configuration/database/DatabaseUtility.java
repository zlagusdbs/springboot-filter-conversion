package com.spharos.conversion.configuration.database;

import com.osc.core.util.AES256Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Slf4j
public class DatabaseUtility {
    @Autowired
    private AES256Util aes256Util;

    public static DataSource makeDataSource(DatabaseInfoVendor.Properties properties) {
        return DataSourceBuilder
                .create()
                .driverClassName(properties.getDriverClassName())
                .url(properties.getUrl() + (StringUtils.isEmpty(properties.getParams()) ? "" : "?" + properties.getParams()))
                .username(properties.getUsername())
                .password(properties.getPassword())
                .build();
    }
}
